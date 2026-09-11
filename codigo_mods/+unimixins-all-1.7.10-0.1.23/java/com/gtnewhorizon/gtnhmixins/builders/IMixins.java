/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package com.gtnewhorizon.gtnhmixins.builders;

import com.gtnewhorizon.gtnhmixins.GTNHMixins;
import com.gtnewhorizon.gtnhmixins.builders.IBaseTransformer;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;

public interface IMixins
extends IBaseTransformer {
    @Nonnull
    public MixinBuilder getBuilder();

    public static <E extends Enum<E>> List<String> getMixins(Class<E> mixinsEnum) {
        ArrayList<String> toLoad = new ArrayList<String>();
        ArrayList<String> toNotLoad = new ArrayList<String>();
        MixinBuilder.loadMixins(mixinsEnum, toLoad, toNotLoad);
        GTNHMixins.log("Not loading the following mixins: {}", toNotLoad);
        for (String mixin : toLoad) {
            GTNHMixins.log("Loading {}", mixin);
        }
        return toLoad;
    }

    public static <E extends Enum<E>> List<String> getEarlyMixins(Class<E> mixinsEnum, Set<String> loadedCoreMods) {
        ArrayList<String> toLoad = new ArrayList<String>();
        ArrayList<String> toNotLoad = new ArrayList<String>();
        MixinBuilder.loadEarlyMixins(mixinsEnum, loadedCoreMods, toLoad, toNotLoad);
        GTNHMixins.log("Not loading the following EARLY mixins: {}", toNotLoad);
        return toLoad;
    }

    public static <E extends Enum<E>> List<String> getLateMixins(Class<E> mixinsEnum, Set<String> loadedMods) {
        ArrayList<String> toLoad = new ArrayList<String>();
        ArrayList<String> toNotLoad = new ArrayList<String>();
        MixinBuilder.loadLateMixins(mixinsEnum, loadedMods, toLoad, toNotLoad);
        GTNHMixins.log("Not loading the following LATE mixins: {}", ((Object)toNotLoad).toString());
        return toLoad;
    }
}

