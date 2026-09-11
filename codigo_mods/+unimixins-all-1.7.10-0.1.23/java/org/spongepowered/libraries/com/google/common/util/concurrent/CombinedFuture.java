/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.Nullable;
import org.spongepowered.libraries.com.google.common.annotations.GwtCompatible;
import org.spongepowered.libraries.com.google.common.base.Preconditions;
import org.spongepowered.libraries.com.google.common.collect.ImmutableCollection;
import org.spongepowered.libraries.com.google.common.util.concurrent.AggregateFuture;
import org.spongepowered.libraries.com.google.common.util.concurrent.AsyncCallable;
import org.spongepowered.libraries.com.google.common.util.concurrent.InterruptibleTask;
import org.spongepowered.libraries.com.google.common.util.concurrent.ListenableFuture;

@GwtCompatible
final class CombinedFuture<V>
extends AggregateFuture<Object, V> {
    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, Executor listenerExecutor, AsyncCallable<V> callable) {
        this.init(new CombinedFutureRunningState(futures, allMustSucceed, new AsyncCallableInterruptibleTask(callable, listenerExecutor)));
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> futures, boolean allMustSucceed, Executor listenerExecutor, Callable<V> callable) {
        this.init(new CombinedFutureRunningState(futures, allMustSucceed, new CallableInterruptibleTask(callable, listenerExecutor)));
    }

    private final class CallableInterruptibleTask
    extends CombinedFutureInterruptibleTask {
        private final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable, Executor listenerExecutor) {
            super(listenerExecutor);
            this.callable = Preconditions.checkNotNull(callable);
        }

        @Override
        void setValue() throws Exception {
            CombinedFuture.this.set(this.callable.call());
        }
    }

    private final class AsyncCallableInterruptibleTask
    extends CombinedFutureInterruptibleTask {
        private final AsyncCallable<V> callable;

        public AsyncCallableInterruptibleTask(AsyncCallable<V> callable, Executor listenerExecutor) {
            super(listenerExecutor);
            this.callable = Preconditions.checkNotNull(callable);
        }

        @Override
        void setValue() throws Exception {
            CombinedFuture.this.setFuture(this.callable.call());
        }
    }

    private abstract class CombinedFutureInterruptibleTask
    extends InterruptibleTask {
        private final Executor listenerExecutor;
        volatile boolean thrownByExecute = true;

        public CombinedFutureInterruptibleTask(Executor listenerExecutor) {
            this.listenerExecutor = Preconditions.checkNotNull(listenerExecutor);
        }

        @Override
        final void runInterruptibly() {
            this.thrownByExecute = false;
            if (!CombinedFuture.this.isDone()) {
                try {
                    this.setValue();
                }
                catch (ExecutionException e) {
                    CombinedFuture.this.setException(e.getCause());
                }
                catch (CancellationException e) {
                    CombinedFuture.this.cancel(false);
                }
                catch (Throwable e) {
                    CombinedFuture.this.setException(e);
                }
            }
        }

        @Override
        final boolean wasInterrupted() {
            return CombinedFuture.this.wasInterrupted();
        }

        final void execute() {
            block2: {
                try {
                    this.listenerExecutor.execute(this);
                }
                catch (RejectedExecutionException e) {
                    if (!this.thrownByExecute) break block2;
                    CombinedFuture.this.setException(e);
                }
            }
        }

        abstract void setValue() throws Exception;
    }

    private final class CombinedFutureRunningState
    extends AggregateFuture.RunningState {
        private CombinedFutureInterruptibleTask task;

        CombinedFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends Object>> futures, boolean allMustSucceed, CombinedFutureInterruptibleTask task) {
            super(futures, allMustSucceed, false);
            this.task = task;
        }

        void collectOneValue(boolean allMustSucceed, int index, @Nullable Object returnValue) {
        }

        @Override
        void handleAllCompleted() {
            CombinedFutureInterruptibleTask localTask = this.task;
            if (localTask != null) {
                localTask.execute();
            } else {
                Preconditions.checkState(CombinedFuture.this.isDone());
            }
        }

        @Override
        void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.task = null;
        }

        @Override
        void interruptTask() {
            CombinedFutureInterruptibleTask localTask = this.task;
            if (localTask != null) {
                localTask.interruptTask();
            }
        }
    }
}

