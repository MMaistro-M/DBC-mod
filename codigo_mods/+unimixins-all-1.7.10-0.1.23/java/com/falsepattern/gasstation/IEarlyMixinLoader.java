/*
 * Decompiled with CFR 0.152.
 */
package com.falsepattern.gasstation;

import java.util.List;

@Deprecated
public interface IEarlyMixinLoader {
    public List<String> getMixinConfigs();

    default public boolean shouldMixinConfigQueue(String mixinConfig) {
        return true;
    }

    default public void onMixinConfigQueued(String mixinConfig) {
    }
}

