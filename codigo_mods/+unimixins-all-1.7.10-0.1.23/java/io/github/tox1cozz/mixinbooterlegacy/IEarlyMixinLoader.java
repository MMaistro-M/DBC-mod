/*
 * Decompiled with CFR 0.152.
 */
package io.github.tox1cozz.mixinbooterlegacy;

import java.util.List;

public interface IEarlyMixinLoader {
    public List<String> getMixinConfigs();

    default public boolean shouldMixinConfigQueue(String mixinConfig) {
        return true;
    }

    default public void onMixinConfigQueued(String mixinConfig) {
    }
}

