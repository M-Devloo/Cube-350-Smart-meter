package com.github.mdevloo.runnable;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface ExecutorPoolService<T> {
    Future run(final Runnable runnable);

    ScheduledFuture<T> schedule(final Callable<T> runnable, final long period, final TimeUnit timeUnit);

    void close();
}