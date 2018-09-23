package com.github.mdevloo.runnable;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface ExecutorPoolService {
    Future run(final Runnable runnable);

    ScheduledFuture schedule(final Runnable runnable, final long initialDelay, final long period, final TimeUnit timeUnit);

    void close();
}