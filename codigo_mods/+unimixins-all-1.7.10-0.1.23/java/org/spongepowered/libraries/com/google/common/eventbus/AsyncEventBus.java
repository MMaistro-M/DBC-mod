/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.eventbus;

import java.util.concurrent.Executor;
import org.spongepowered.libraries.com.google.common.annotations.Beta;
import org.spongepowered.libraries.com.google.common.eventbus.Dispatcher;
import org.spongepowered.libraries.com.google.common.eventbus.EventBus;
import org.spongepowered.libraries.com.google.common.eventbus.SubscriberExceptionHandler;

@Beta
public class AsyncEventBus
extends EventBus {
    public AsyncEventBus(String identifier, Executor executor) {
        super(identifier, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super("default", executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }

    public AsyncEventBus(Executor executor) {
        super("default", executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }
}

