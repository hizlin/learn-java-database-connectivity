package dev.example.olds;

import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * ShardingAlgorithm 分片算法
 * https://shardingsphere.apache.org/document/current/cn/dev-manual/sharding/
 * https://shardingsphere.apache.org/document/current/cn/user-manual/shardingsphere-jdbc/configuration/built-in-algorithm/sharding/
 * <p>
 * 实现: {
 * ModShardingAlgorithm: 取模;
 * HashModShardingAlgorithm: 哈希取模;
 * <p>
 * VolumeBasedRangeShardingAlgorithm: 基于分片容量
 * BoundaryBasedRangeShardingAlgorithm: 基于分片边界
 * <p>
 * AutoIntervalShardingAlgorithm: 自动时间间隔
 * IntervalShardingAlgorithm: 时间范围
 * <p>
 * InlineShardingAlgorithm: 行表达式
 * }
 * <p>
 * 算法接口 {
 * StandardShardingAlgorithm: 标准分片算法; 使用单个字段作为分片条件;
 * ComplexKeysShardingAlgorithm: 复合分片算法; 使用多个字段作为分片条件;
 * HintShardingAlgorithm: 暗示/强制路由; 分片字段不在 SQL 之中;
 * }
 */
public class PerDayTableShardingAlgorithm implements StandardShardingAlgorithm<Long> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String SHARDING_SUFFIX_FORMAT_KEY = "sharding-suffix-pattern";
    private static final String DATE_TIME_ZONE_OFFSET_KEY = "datetime-zone-offset";
    private static final String DATE_TIME_START_KEY = "date-start";
    private static final String KEEP_DAYS_KEY = "keep-days";

    private Properties props = new Properties();

    private int keepDays;

    private int getKeepDays() {
        Preconditions.checkArgument(props.containsKey(KEEP_DAYS_KEY), "% can not be null.", KEEP_DAYS_KEY);
        String value = props.getProperty(KEEP_DAYS_KEY);
        return Integer.parseInt(value);
    }

    /**
     * 以中国时间为分片依据;
     */
    private ZoneOffset datetimeZoneOffset;

    private ZoneOffset getDatetimeZoneOffset() {
        Preconditions.checkArgument(props.containsKey(DATE_TIME_ZONE_OFFSET_KEY), "% can not be null.", DATE_TIME_ZONE_OFFSET_KEY);
        String value = props.getProperty(DATE_TIME_ZONE_OFFSET_KEY);
        return ZoneOffset.of(value);
    }

    /**
     * 起始时间;
     */
    private LocalDate startDate;

    private LocalDate getStartDate() {
        Preconditions.checkArgument(props.containsKey(DATE_TIME_START_KEY), "% can not be null.", DATE_TIME_START_KEY);
        String value = props.getProperty(DATE_TIME_START_KEY);
        return LocalDate.parse(value, DATE_FORMATTER);
    }

    /**
     * 表名后缀格式;
     */
    private DateTimeFormatter tableSuffixPattern;

    private DateTimeFormatter getTableSuffixPattern() {
        Preconditions.checkArgument(props.containsKey(SHARDING_SUFFIX_FORMAT_KEY), "% can not be null.", SHARDING_SUFFIX_FORMAT_KEY);
        return DateTimeFormatter.ofPattern(props.getProperty(SHARDING_SUFFIX_FORMAT_KEY));
    }

    public PerDayTableShardingAlgorithm() {
    }

    //region 属性
    @Override
    public String getType() {
        return "PERDAY";
    }

    @Override
    public Properties getProps() {
        return this.props;
    }

    @Override
    public void setProps(Properties props) {
        this.props = props;
    }
    //endregion

    @Override
    public void init() {
        this.startDate = getStartDate();
        this.keepDays = getKeepDays();
        this.tableSuffixPattern = getTableSuffixPattern();
        this.datetimeZoneOffset = getDatetimeZoneOffset();
    }

    /**
     * 精确分片; 用于 = 以及 IN 场景;
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        LocalDate time = tryGetLocalDate(shardingValue.getValue());
        String suffix = time.format(tableSuffixPattern);

        for (String i : availableTargetNames) {
            if (i.endsWith(suffix)) {
                return i;
            }
        }
        throw new UnsupportedOperationException("Sharding 无法找到目标 TableName.");
    }

    /**
     * 范围分片; 用于 BETWEEN AND, >, >=, <, <= 场景;
     *
     * @param availableTargetNames
     * @param shardingValue
     * @return
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        Range<Long> range = shardingValue.getValueRange();

        // 有效日期最大值;
        LocalDate today = LocalDate.now();
        // 有效日期最小值;
        LocalDate minimum = max(today.plusDays(-keepDays), startDate);

        // 起始日期
        LocalDate start;
        if (range.hasLowerBound()) {
            start = max(minimum, tryGetLocalDate(range.lowerEndpoint()));
        } else {
            start = minimum;
        }

        // 截止日期
        LocalDate end;
        if (range.hasUpperBound()) {
            end = min(today, tryGetLocalDate(range.upperEndpoint()));
        } else {
            end = today;
        }

        String table = shardingValue.getLogicTableName();
        Set<String> tables = new HashSet<>();
        int c = start.compareTo(end);
        // 注意: 以下算法 不会考虑如下情况:
        // time >= '2020-11-01 00:00:00' AND time < '2020-12-01 00:00:00'
        if (c < 0) { // start < end
            while (start.compareTo(end) <= 0) {
                tables.add(tryMakeTableName(table, start));
                start = start.plusDays(1L);
            }
        } else if (c > 0) { // start > end
            // #1 如果 date > '2020-12-10' OR date < '2020-11-10'  则为有效;
            // #2 如果 date > '2020-12-10' AND date < '2020-11-10' 则为无效;
            // if (range.hasLowerBound()) {
            //     while (start.compareTo(today) <= 0) {
            //         tables.add(tryMakeTableName(table, start));
            //         start = start.plusDays(1L);
            //     }
            // }
            // if (range.hasUpperBound()) {
            //     while (minimum.compareTo(end) <= 0) {
            //         tables.add(tryMakeTableName(table, minimum));
            //         minimum = minimum.plusDays(1L);
            //     }
            // }
        } else { // start = end
            tables.add(tryMakeTableName(table, start));
        }

        // 保留交集
        tables.retainAll(availableTargetNames);

        return tables;
    }

    /**
     * 通过 雪花算法主键 计算生成时间;
     *
     * @param key 雪花算法主键
     * @return
     */
    LocalDateTime tryGetLocalDateTime(long key) {
        long milliseconds = 0L; // Snowflake.getEpochMilliseconds(key);
        Instant time = Instant.ofEpochMilli(milliseconds);
        return time.atOffset(ZoneOffset.UTC).withOffsetSameInstant(datetimeZoneOffset).toLocalDateTime();
    }

    LocalDate tryGetLocalDate(long key) {
        long milliseconds = 0L; // Snowflake.getEpochMilliseconds(key);
        Instant time = Instant.ofEpochMilli(milliseconds);
        return time.atOffset(ZoneOffset.UTC).withOffsetSameInstant(datetimeZoneOffset).toLocalDate();
    }

    /**
     * 生成实际表名
     *
     * @param table 逻辑表名: @TableName 注解;
     * @param date
     * @return
     */
    String tryMakeTableName(String table, LocalDate date) {
        return table + "_" + date.format(tableSuffixPattern);
    }

    LocalDate min(LocalDate x, LocalDate y) {
        return x.compareTo(y) < 0 ? x : y;
    }

    LocalDate max(LocalDate x, LocalDate y) {
        return x.compareTo(y) > 0 ? x : y;
    }
}
