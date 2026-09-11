/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.annotations.GwtIncompatible;
import org.spongepowered.libraries.com.google.common.base.Preconditions;
import org.spongepowered.libraries.com.google.common.util.concurrent.ExecutionList;
import org.spongepowered.libraries.com.google.common.util.concurrent.ForwardingFuture;
import org.spongepowered.libraries.com.google.common.util.concurrent.ListenableFuture;
import org.spongepowered.libraries.com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.spongepowered.libraries.com.google.common.util.concurrent.Uninterruptibles;

@Beta
@GwtIncompatible
public final class JdkFutureAdapters {
    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future) {
        if (future instanceof ListenableFuture) {
            return (ListenableFuture)future;
        }
        return new ListenableFutureAdapter<V>(future);
    }

    public static <V> ListenableFuture<V> listenInPoolThread(Future<V> future, Executor executor) {
        Preconditions.checkNotNull(executor);
        if (future instanceof ListenableFuture) {
            return (ListenableFuture)future;
        }
        return new ListenableFutureAdapter<V>(future, executor);
    }

    private JdkFutureAdapters() {
    }

    private static class ListenableFutureAdapter<V>
    extends ForwardingFuture<V>
    implements ListenableFuture<V> {
        private static final ThreadFactory threadFactory = new ThreadFactoryBuilder().setDaemon(true).setNameFormat("ListenableFutureAdapter-thread-%d").build();
        private static final Executor defaultAdapterExecutor = Executors.newCachedThreadPool(threadFactory);
        private final Executor adapterExecutor;
        private final ExecutionList executionList = new ExecutionList();
        private final AtomicBoolean hasListeners = new AtomicBoolean(false);
        private final Future<V> delegate;

        ListenableFutureAdapter(Future<V> delegate) {
            this(delegate, defaultAdapterExecutor);
        }

        ListenableFutureAdapter(Future<V> delegate, Executor adapterExecutor) {
            this.delegate = Preconditions.checkNotNull(delegate);
            this.adapterExecutor = Preconditions.checkNotNull(adapterExecutor);
        }

        @Override
        protected Future<V> delegate() {
            return this.delegate;
        }

        @Override
        public void addListener(Runnable listener, Executor exec) {
            this.executionList.add(listener, exec);
            if (this.hasListeners.compareAndSet(false, true)) {
                if (this.delegate.isDone()) {
                    this.executionList.execute();
                    return;
                }
                this.adapterExecutor.execute(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            Uninterruptibles.getUninterruptibly(delegate);
                        }
                        catch (Throwable throwable) {
                            // empty catch block
                        }
                        executionList.execute();
                    }
                });
            }
        }
    }
}

