package io.hizlin.snowflake;

import lombok.Data;

@Data
public class SnowflakeProperties {
    private int timestampBits;
    private int workerBits;
    private int sequenceBits;

    private TimeService timeService;
    private long baseEpoch;
    private int allowBackward;

    private int sequenceRingBegin;
    private int sequenceRingLength;

    private int workerKey;
}
