package io.hizlin.snowflake;

/**
 *
 */
public class SystemTimeService implements TimeService {

    public static final TimeService INSTANCE = new SystemTimeService();

    @Override
    public long now() {
        return System.currentTimeMillis();
    }
}
