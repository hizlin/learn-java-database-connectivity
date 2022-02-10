//package dev.example.sharding.olds;
//
//import dev.example.olds.TimeHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.governance.repository.api.ConfigurationRepository;
//import org.apache.shardingsphere.governance.repository.api.RegistryRepository;
//import org.apache.shardingsphere.governance.repository.api.config.GovernanceCenterConfiguration;
//// import org.apache.shardingsphere.governance.repository.api.listener.DataChangedEvent;
//// import org.apache.shardingsphere.governance.repository.api.listener.DataChangedEventListener;
//import org.apache.shardingsphere.infra.yaml.config.YamlRootRuleConfigurations;
//import org.apache.shardingsphere.infra.yaml.config.algorithm.YamlShardingSphereAlgorithmConfiguration;
//import org.apache.shardingsphere.infra.yaml.engine.YamlEngine;
//import org.apache.shardingsphere.mode.repository.cluster.listener.DataChangedEvent;
//import org.apache.shardingsphere.mode.repository.cluster.listener.DataChangedEventListener;
//import org.apache.shardingsphere.sharding.yaml.config.YamlShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.yaml.config.rule.YamlTableRuleConfiguration;
//import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlShardingStrategyConfiguration;
//import org.apache.shardingsphere.sharding.yaml.config.strategy.sharding.YamlStandardShardingStrategyConfiguration;
//
//import java.time.LocalDate;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * RegistryCenter
// */
//@Slf4j
//public class LocalRepository implements RegistryRepository, ConfigurationRepository {
//
//    public LocalRepository() {
//        log.info("LocalRegistryRepository.new");
//    }
//
//    @Override
//    public void persistEphemeral(String key, String value) {
//        log.info("LocalRegistryRepository.persistEphemeral");
//        REGISTRY_DATA.put(key, value);
//    }
//
//    private static final Map<String, DataChangedEventListener> temp = new ConcurrentHashMap<>();
//
//    public static void tryRefreshActualDataNodes(int days) {
//        String key = "/schemas/logic_db/rule";
//        DataChangedEventListener listener = temp.get(key);
//        if (listener != null) {
//            // 获取旧的配置
//            String yaml1 = REGISTRY_DATA.get(key);
//
//            // 生成新的配置
//            LocalDate today = LocalDate.now();
//            LocalDate nextDay = today.plusDays(days);
//            LocalDate startDate = TimeHelper.max(ShardingConstant.startDate, today.plusDays(-ShardingConstant.keepDays));
//            if (startDate.isAfter(today)) {
//                throw new IllegalArgumentException("Sharding.StartDate 设置错误");
//            }
//            String yaml2 = tryUpdateYamlConfiguration(yaml1, startDate, nextDay);
//
//            // 触发配置更新事件
//            DataChangedEvent event = new DataChangedEvent(key, yaml2, DataChangedEvent.ChangedType.UPDATED);
//            listener.onChange(event);
//        }
//    }
//
//    static String tryUpdateYamlConfiguration(String yaml, LocalDate startTime, LocalDate endTime) {
//        YamlRootRuleConfigurations root = YamlEngine.unmarshal(yaml, YamlRootRuleConfigurations.class);
//
//        YamlShardingRuleConfiguration shardingRule = (YamlShardingRuleConfiguration) root.getRules()
//                .stream()
//                .filter(r -> r instanceof YamlShardingRuleConfiguration)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("ShardingSphere 配置有误"));
//
//        // 按月拆分日期
//        List<LocalDateRange> ranges = TimeHelper.splitByMonth(startTime, endTime);
//
//        for (YamlTableRuleConfiguration table : shardingRule.getTables().values()) {
//            String inline = makeTableInlineExpression(ShardingDataSourceConfig.DATA_SOURCE_MASTER, table.getLogicTable(), ranges);
//            table.setActualDataNodes(inline);
//        }
//
//        return YamlEngine.marshal(root);
//    }
//
//    static String makeTableInlineExpression(String dataSourceName, String tableName, List<LocalDateRange> ranges) {
//        List<String> list = new ArrayList<>();
//        for (LocalDateRange r : ranges) {
//            // 格式: db0.zdy_sms_log_$->{20201217..20201218}
//            String text = String.format("%s.%s_$->{%s..%s}",
//                    dataSourceName,
//                    tableName,
//                    ShardingConstant.TABLE_SUFFIX_FORMATTER.format(r.getStart()),
//                    ShardingConstant.TABLE_SUFFIX_FORMATTER.format(r.getEnd())
//            );
//            list.add(text);
//        }
//        return String.join(",", list);
//    }
//
//    static String test() {
//        YamlShardingRuleConfiguration shardingRule = new YamlShardingRuleConfiguration();
//
//        // 定义分片算法
//        YamlShardingSphereAlgorithmConfiguration sphereAlgorithm1 = new YamlShardingSphereAlgorithmConfiguration();
//        sphereAlgorithm1.setType("PERDAY");
//        sphereAlgorithm1.setProps(new Properties());
//
//        // 表1:
//        YamlStandardShardingStrategyConfiguration standard1 = new YamlStandardShardingStrategyConfiguration();
//        standard1.setShardingColumn("id");
//        standard1.setShardingAlgorithmName("perday1");
//
//        YamlShardingStrategyConfiguration tableStrategy1 = new YamlShardingStrategyConfiguration();
//        tableStrategy1.setStandard(standard1);
//
//        YamlTableRuleConfiguration tableRule1 = new YamlTableRuleConfiguration();
//        tableRule1.setLogicTable("zdy_sms_log");
//        tableRule1.setActualDataNodes("db0.zdy_sms_log_$->{20201217..20201219}");
//        tableRule1.setTableStrategy(tableStrategy1);
//
//        // 表2:
//        YamlStandardShardingStrategyConfiguration standard2 = new YamlStandardShardingStrategyConfiguration();
//        standard2.setShardingColumn("id");
//        standard2.setShardingAlgorithmName("perday1");
//
//        YamlShardingStrategyConfiguration tableStrategy2 = new YamlShardingStrategyConfiguration();
//        tableStrategy2.setStandard(standard2);
//
//        YamlTableRuleConfiguration tableRule2 = new YamlTableRuleConfiguration();
//        tableRule2.setLogicTable("zdy_sms_audit");
//        tableRule2.setActualDataNodes("db0.zdy_sms_audit_$->{20201217..20201219}");
//
//        // 汇总
//        shardingRule.getShardingAlgorithms().put("perday1", sphereAlgorithm1);
//        shardingRule.getTables().put(tableRule1.getLogicTable(), tableRule1);
//        shardingRule.getTables().put(tableRule2.getLogicTable(), tableRule2);
//
//        String yaml = YamlEngine.marshal(shardingRule);
//        return yaml;
//    }
//
//    //region GovernanceRepository
//
//    private static final Map<String, String> REGISTRY_DATA = new LinkedHashMap<>();
//
//    @Override
//    public void init(String name, GovernanceCenterConfiguration config) {
//
//        log.info("LocalRegistryRepository.init");
//    }
//
//    @Override
//    public String get(String key) {
//        log.info("LocalRegistryRepository.get");
//        return REGISTRY_DATA.get(key);
//    }
//
//    @Override
//    public List<String> getChildrenKeys(String key) {
//        log.info("LocalRegistryRepository.getChildrenKeys");
//        return Collections.emptyList();
//    }
//
//    @Override
//    public void persist(String key, String value) {
//        log.info("LocalRegistryRepository.persist");
//        log.info("Key:{}, Value:{}", key, value);
//        REGISTRY_DATA.put(key, value);
//    }
//
//    @Override
//    public void delete(String key) {
//        log.info("LocalRegistryRepository.delete");
//        REGISTRY_DATA.remove(key);
//    }
//
//
//    @Override
//    public void watch(String key, DataChangedEventListener listener) {
//        log.info("LocalRegistryRepository.watch");
//        temp.put(key, listener);
//    }
//
//    @Override
//    public void close() {
//        log.info("LocalRegistryRepository.close");
//        REGISTRY_DATA.clear();
//    }
//
//    //endregion
//
//    //region TypedSPI
//
//    @Override
//    public String getType() {
//        return "LocalRepository";
//    }
//
//    private Properties props;
//
//    @Override
//    public Properties getProps() {
//        return this.props;
//    }
//
//    @Override
//    public void setProps(Properties props) {
//        this.props = props;
//    }
//
//    //endregion
//}
