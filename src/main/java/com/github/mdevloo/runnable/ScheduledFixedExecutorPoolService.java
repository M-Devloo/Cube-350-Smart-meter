package com.github.mdevloo.runnable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class ScheduledFixedExecutorPoolService<T> implements ExecutorPoolService<T> {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledFixedExecutorPoolService.class);

    private static final int WAIT_TERMINATION_TIMEOUT = 60;

    private static final TimeUnit WAIT_TERMINATION_TIMEUNIT = TimeUnit.SECONDS;

    private final ScheduledExecutorService executorService;

    public ScheduledFixedExecutorPoolService(final int poolSize) {
        this.executorService = Executors.newScheduledThreadPool(poolSize);
    }

    @Override
    public final Future run(final Runnable runnable) {
        synchronized (this.executorService) {
            return this.executorService.submit(runnable);
        }
    }

    @Override
    public final ScheduledFuture<T> schedule(final Callable<T> runnable,
                                             final long period,
                                             final TimeUnit timeUnit) {
        synchronized (this.executorService) {
            return this.executorService.schedule(runnable, period, timeUnit);
        }
    }

    @Override
    public final void close() {
        synchronized (this.executorService) {
            logger.info("Shutting down the service executor.");

            this.executorService.shutdown();

            try {
                // wait for termination.
                if (!this.executorService.awaitTermination(WAIT_TERMINATION_TIMEOUT, WAIT_TERMINATION_TIMEUNIT)) {
                    this.executorService.shutdownNow(); // Cancel currently executing tasks

                    // Wait a while for tasks to respond to being cancelled
                    if (!this.executorService.awaitTermination(WAIT_TERMINATION_TIMEOUT, WAIT_TERMINATION_TIMEUNIT)) {
                        logger.error("Pool did not terminate. Force quiting.");
                    }
                }
            } catch (final InterruptedException ie) {
                // (Re-)Cancel if current thread also interrupted
                this.executorService.shutdownNow();
                // Preserve interrupt status
                Thread.currentThread().interrupt();
            }
        }
    }
}