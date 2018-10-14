package com.github.mdevloo.cube.modbus.service.conf;

import java.util.concurrent.TimeUnit;

public final class CubeServiceConfiguration {

    private final long period;

    private final TimeUnit timeUnit;

    public CubeServiceConfiguration(final long period, final TimeUnit timeUnit) {
        this.period = period;
        this.timeUnit = timeUnit;
    }

    public final long getPeriod() {
        return this.period;
    }

    public final TimeUnit getTimeUnit() {
        return this.timeUnit;
    }
}