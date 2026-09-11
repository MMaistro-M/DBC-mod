/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.libraries.com.google.common.eventbus;

import org.spongepowered.libraries.com.google.common.eventbus.SubscriberExceptionContext;

public interface SubscriberExceptionHandler {
    public void handleException(Throwable var1, SubscriberExceptionContext var2);
}

