package io.hizlin.snowflake;

import lombok.SneakyThrows;

import java.time.Instant;

/**
 * 雪花算法
 *
 * @author HizLin
 * v1.2 2021-11-18 重构
 * v1.1 2020-12-18
 * v1.0 2020-10-10
 */
public class Snowflake {

    /* 其它实现:
     * MyBatis-Plus:
     * https://github.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-core/src/main/java/com/baomidou/mybatisplus/core/toolkit/Sequence.java
     * <p>
     * ShardingSphere-Jdbc
     * https://github.com/apache/shardingsphere/blob/master/shardingsphere-features/shardingsphere-sharding/shardingsphere-sharding-core/src/main/java/org/apache/shardingsphere/sharding/algorithm/keygen/SnowflakeKeyGenerateAlgorithm.java
     * Leaf
     * https://github.com/Meituan-Dianping/Leaf/blob/master/leaf-core/src/main/java/com/sankuai/inf/leaf/snowflake/SnowflakeIDGenImpl.java
     */

    //region Builder

    public static SnowflakeBuilder builder() {
        return new SnowflakeBuilder();
    }

    public static class SnowflakeBuilder {

        SnowflakeBuilder() {
        }

        /**
         * 雪花主键 默认布局:
         * |---------------------------------------------------------------------|
         * | Sign 1 bit  | Timestamp 41 bits | Worker 10 bits | Sequence 12 bits |
         * |---------------------------------------------------------------------|
         * <p>
         * Timestamp: [0, 0x200_0000_0000). unit: millisecond. available: 69.7 years;
         * Worker: [0, 0x400). count: 1024;
         * Sequence: [0, 0x1000). count: 4096;
         */

        // 时间部分 位数
        private int timestampBits = 41;
        // 节点部分 位数
        private int workerBits = 10;
        // 序号部分 位数
        private int sequenceBits = 12;

        public SnowflakeBuilder bits(Integer timestamp, Integer worker, Integer sequence) {
            if (timestamp <= 0 || timestamp >= 0x40) {
                throw new IllegalArgumentException("timestamp is out of range.");
            }
            if (worker <= 0 || worker >= 0x40) {
                throw new IllegalArgumentException("worker is out of range.");
            }
            if (sequence <= 0 || sequence >= 0x40) {
                throw new IllegalArgumentException("sequence is out of range.");
            }

            // 由于 Java 不支持原生的 unsigned long, 因此 sign bit 始终为零, 可用位数: 63;
            if (timestamp + worker + sequence >= 0x40) {
                throw new IllegalArgumentException("All bits must be less than 64.");
            }

            this.timestampBits = timestamp;
            this.workerBits = worker;
            this.sequenceBits = sequence;
            return this;
        }

        /**
         * 时钟服务
         */
        private TimeService timeService = SystemTimeService.INSTANCE;

        public SnowflakeBuilder timeService(TimeService service) {
            if (service == null) {
                throw new IllegalArgumentException();
            }
            this.timeService = service;
            return this;
        }

        /**
         * 基准时间; 默认: "2016-11-01T00:00:00Z" (UTC)
         * <p>
         * 其它实现:
         * ShardingSphere-Jdbc v5.x: "2016-11-01T00:00:00" (使用当前系统时区)
         * Leaf / MyBatis-Plus v3.x: "2010-11-04T01:42:54.657Z"
         */
        private long baseEpoch = Instant.parse("2016-11-01T00:00:00Z").toEpochMilli();

        public SnowflakeBuilder baseEpoch(Instant epoch) {
            if (epoch == null) {
                throw new IllegalArgumentException("epoch is null.");
            }
            var ticks = epoch.toEpochMilli();
            if (ticks < 0L) {
                throw new IllegalArgumentException("epoch is out of range.");
            }
            this.baseEpoch = epoch.toEpochMilli();
            return this;
        }

        /**
         * 允许 时钟回拨 时长; 单位: 毫秒;
         * <p>
         * 可能发生回退情况:
         * 1. 闰秒;
         * 2. 时间同步出现故障 (Network Time Protocol);
         * 3. 人为修改系统时间;
         */
        private int allowBackward = 10;

        public SnowflakeBuilder allowBackward(int allow) {
            if (allow < 0) {
                throw new IllegalArgumentException();
            }
            this.allowBackward = allow;
            return this;
        }

        private int sequenceRingBegin = 2;

        public SnowflakeBuilder sequenceRingBegin(int begin) {
            if (begin < 0) {
                throw new IllegalArgumentException();
            }
            this.sequenceRingBegin = begin;
            return this;
        }

        private int sequenceRingLength = 8;

        public SnowflakeBuilder sequenceRingLength(int length) {
            if (length < 0) {
                throw new IllegalArgumentException();
            }
            this.sequenceRingLength = length;
            return this;
        }

        /**
         * 节点编号
         */
        private int workerKey = 0;

        public SnowflakeBuilder workerKey(int worker) {
            if (worker < 0) {
                throw new IllegalArgumentException("worker is out of range.");
            }
            this.workerKey = worker;
            return this;
        }

        public Snowflake build() {

            if (workerKey > ~(-1L << workerBits)) {
                // 为什么放在这里做判断?
                // 放在 bits() / workerKey() 里面, 容易互相限制;
                throw new IllegalArgumentException("worker is out of range.");
            }
            // if (sequenceRingBegin + sequenceRingLength > ~(-1L << sequenceBits)) {
            //     throw new IllegalArgumentException();
            // }

            var properties = new SnowflakeProperties();
            properties.setTimestampBits(this.timestampBits);
            properties.setWorkerBits(this.workerBits);
            properties.setSequenceBits(this.sequenceBits);

            properties.setTimeService(this.timeService);
            properties.setBaseEpoch(this.baseEpoch);
            properties.setAllowBackward(this.allowBackward);

            properties.setSequenceRingBegin(this.sequenceRingBegin);
            properties.setSequenceRingLength(this.sequenceRingLength);

            properties.setWorkerKey(this.workerKey);
            return new Snowflake(properties);
        }
    }

    //endregion

    // private final int timestampBits;
    // private final int workerBits;
    // private final int sequenceBits;

    // 时间部分 掩码
    private final long timestampMask;
    // 节点部分 掩码
    private final long workerMask;
    // 序号部分 掩码
    private final long sequenceMask;

    private final int timestampShift;
    private final int workerShift;
    // private final int sequenceShift = 0;

    private final TimeService timeService;
    private final long baseEpoch;
    private final int allowBackward;

    private final int sequenceRingBegin;
    private final int sequenceRingLength;

    private final long workerKey;

    Snowflake(SnowflakeProperties properties) {
        this.baseEpoch = properties.getBaseEpoch();
        this.timeService = properties.getTimeService();

        this.workerKey = properties.getWorkerKey();
        this.sequenceRingBegin = properties.getSequenceRingBegin();
        this.sequenceRingLength = properties.getSequenceRingLength();
        this.allowBackward = properties.getAllowBackward();

        this.timestampMask = ~(-1L << properties.getTimestampBits());
        this.workerMask = ~(-1L << properties.getWorkerBits());
        this.sequenceMask = ~(-1L << properties.getSequenceBits());

        this.timestampShift = properties.getWorkerBits() + properties.getSequenceBits();
        this.workerShift = properties.getSequenceBits();
    }

    /**
     * 当前时间
     */
    private long lastTimestamp = -1L;
    /**
     * 当前序号
     */
    private long lastSequence = -1L;

    @SneakyThrows
    public synchronized long next() {
        var now = timeService.now();
        // if (now < 0L) {
        //     throw new IllegalStateException();
        // }

        if (now < lastTimestamp) {
            var offset = lastTimestamp - now;
            if (offset > allowBackward) {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds.", offset));
            }

            Thread.sleep(offset);
            now = timeService.now();
            if (now < lastTimestamp) {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds.", offset));
            }
        }

        // 相同毫秒之内, 序列 sequence 递增;
        if (now == lastTimestamp) {
            // 逻辑 #1
            if ((++lastSequence & sequenceMask) == 0L) {
                // 如果序列已达上限, 则需等待下一毫秒;
                now = waitUntilNextTimestamp(now);
                // 此处需要重置序列, 逻辑交给 下方逻辑 #2;
            }
        }

        // 逻辑 #2
        if (now > lastTimestamp) { // 进入新的毫秒, 重置 sequence 序列;

            // 方式1: 以固定值开始; 缺点: 前几个数将会高频出现;
            // sequence = 0L;

            // 方式2: 随机法; 每毫秒的首个序号 [0-9] 随机出现;
            // sequence = ThreadLocalRandom.current().nextLong(0, 10);

            /* 方式3: 抖动法; 每毫秒的首个序号 [0-max) 循环出现;
             * 参考: ShardingSphere-Jdbc v5.x (org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm)
             *
             * 最大抖动偏移; 一般将其设为存储节点数量;
             * 说明:
             * 单个 Worker 每一毫秒最多可生成 4096 个主键 [0-4095);
             * 存储节点平均分配算法 = key % db-count (主键 取模 存储节点数量);
             * 如果不同毫秒 的起始序号都从零开始, 那么编号靠前的存储节点分配的数量, 明显高于靠后节点;
             * 本类采用 抖动法 来避免这种情况;
             * 例如: value = 4;
             * 第 1 毫秒 起始序号 = 0;
             * 第 2 毫秒 起始序号 = 1;
             * 第 3 毫秒 起始序号 = 2;
             * 第 4 毫秒 起始序号 = 3;
             * 第 5 毫秒 起始序号 = 0; // 重新计数, 以此类推;
             *
             * 缺点: 每一毫秒 平均损失序号数量 = value/2.
             */

            // 方式4: 平均抖动法;
            if (lastSequence < 0 || sequenceRingLength < 2) {
                lastSequence = sequenceRingBegin;
            } else {
                /*
                 * 例如: RingBegin=1; RingLength=8; 循环三轮序号如下:
                 * Ring1: |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |
                 * Ring2: |  9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 |
                 * Ring3: | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 |
                 *
                 * 假设当前毫秒 sequence=20 位于本轮第四位置, 那么下一毫秒, 应从第五位置开始.
                 * 当前毫秒 sequence=15; 下一毫秒 sequence=8;
                 * 当前毫秒 sequence=16; 下一毫秒 sequence=1;
                 *
                 * # RingBegin=0; RingLength=8;
                 * Ring1: |  0 |  1 |  2 |  3 |  4 |  5 |  6 |  7 |
                 * Ring2: |  8 |  9 | 10 | 11 | 12 | 13 | 14 | 15 |
                 * Ring3: | 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |
                 * 当前毫秒 sequence=14; 下一毫秒 sequence=7
                 * 当前毫秒 sequence=15; 下一毫秒 sequence=0
                 *
                 * # RingBegin=2; RingLength=8;
                 * Ring1: |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 |
                 * Ring2: | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 |
                 * Ring3: | 18 | 19 | 20 | 21 | 22 | 23 | 24 | 25 |
                 * 当前毫秒 sequence=16; 下一毫秒 sequence=9;
                 * 当前毫秒 sequence=17; 下一毫秒 sequence=2;
                 *
                 * 方法1:
                 * sequence = sequenceRingBegin + (++sequence - sequenceRingBegin) % sequenceRingLength;
                 *
                 * 方法2:
                 * sequence = ++sequence % sequenceRingLength;
                 * if (sequence < sequenceRingBegin) {
                 *     sequence += sequenceRingLength;
                 * }
                 */
                // 注意: 此处无需递增 sequence, 因为 上方逻辑 #1 已递增了;
                lastSequence = /*++*/lastSequence % sequenceRingLength;
                if (lastSequence < sequenceRingBegin) {
                    lastSequence += sequenceRingLength;
                }
            }
        }

        lastTimestamp = now;
        return ((this.lastTimestamp - this.baseEpoch) << this.timestampShift) | (this.workerKey << this.workerShift) | this.lastSequence;
    }

    /**
     * 高频循环等待, 直到进入下一毫秒;
     *
     * @param last
     * @return
     */
    private long waitUntilNextTimestamp(final long last) {
        long next;
        do {
            next = timeService.now();
        } while (next <= last);
        return next;
    }

    /**
     * 获取 key 时间部分
     *
     * @param key
     * @return
     */
    public long getTimestampPart(long key) {
        if (key < 0L) {
            throw new IllegalArgumentException();
        }
        return key >> this.timestampShift;
    }

    /**
     * 获取 key 节点部分
     *
     * @param key
     * @return
     */
    public long getWorkerKeyPart(long key) {
        if (key < 0L) {
            throw new IllegalArgumentException();
        }
        return (key >> this.workerShift) & this.workerMask;
    }

    /**
     * 获取 key 序号部分
     *
     * @param key
     * @return
     */
    public long getSequencePart(long key) {
        if (key < 0L) {
            throw new IllegalArgumentException();
        }
        return key & this.sequenceMask;
    }

    /**
     * 获取 key 生成时间
     *
     * @param key
     * @return
     */
    public Instant getInstant(long key) {
        return Instant.ofEpochMilli(this.baseEpoch + this.getTimestampPart(key));
    }

    /**
     * Unix Timestamp
     *
     * @param key
     * @return
     */
    public long getEpochMilliseconds(long key) {
        return this.baseEpoch + this.getTimestampPart(key);
    }
    // public long getEpochSeconds(long key) {
    //     return (this.baseEpoch + this.getTimestampPart(key)) / 1000L;
    // }

    public long makeQueryKeyByTime(Instant time) {
        if (time == null) {
            throw new IllegalArgumentException("time is null.");
        }
        var timestamp = time.toEpochMilli() - this.baseEpoch;
        if (timestamp < 0 || timestamp > timestampMask) {
            throw new IllegalArgumentException("time is out of range.");
        }

        // var worker = 0L;
        // var sequence = 0L;
        return (timestamp << this.timestampShift); // | (worker << this.workerShift) | sequence;
    }
}
