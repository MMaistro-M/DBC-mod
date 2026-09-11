/*
 * Decompiled with CFR 0.152.
 */
package com.relocated.fasterxml.jackson.core.async;

public interface NonBlockingInputFeeder {
    public boolean needMoreInput();

    public void endOfInput();
}

