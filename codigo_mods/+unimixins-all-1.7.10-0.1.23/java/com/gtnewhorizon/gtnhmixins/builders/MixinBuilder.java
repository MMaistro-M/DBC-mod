/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package com.gtnewhorizon.gtnhmixins.builders;

import com.gtnewhorizon.gtnhmixins.builders.AbstractBuilder;
import com.gtnewhorizon.gtnhmixins.builders.IBaseTransformer;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

public class MixinBuilder
extends AbstractBuilder {
    public MixinBuilder() {
    }

    public MixinBuilder(String description) {
    }

    public MixinBuilder addCommonMixins(String ... mixins) {
        return (MixinBuilder)super.addCommonClasses(mixins);
    }

    public MixinBuilder addClientMixins(String ... mixins) {
        return (MixinBuilder)super.addClientClasses(mixins);
    }

    public MixinBuilder addServerMixins(String ... mixins) {
        return (MixinBuilder)super.addServerClasses(mixins);
    }

    public MixinBuilder addSidedMixins(@Nonnull IBaseTransformer.Side side, String ... mixins) {
        return (MixinBuilder)super.addSidedClasses(side, mixins);
    }

    @Override
    public MixinBuilder setApplyIf(@Nonnull Supplier<Boolean> applyIf) {
        return (MixinBuilder)super.setApplyIf(applyIf);
    }

    @Override
    public MixinBuilder addRequiredMod(@Nonnull ITargetMod mod) {
        return (MixinBuilder)super.addRequiredMod(mod);
    }

    @Override
    public MixinBuilder addExcludedMod(@Nonnull ITargetMod mod) {
        return (MixinBuilder)super.addExcludedMod(mod);
    }

    public MixinBuilder setPhase(IBaseTransformer.Phase phase) {
        this.phase = phase;
        return this;
    }

    protected static <E extends Enum<E>> void loadMixins(Class<E> mixinsEnum, List<String> toLoad, List<String> toNotLoad) {
        List<AbstractBuilder> builders = MixinBuilder.getEnabledBuildersForPhase(mixinsEnum, null, toNotLoad);
        Set<ITargetMod> loadedTargets = MixinBuilder.getLoadedTargetedMods(builders, null, Collections.emptySet(), Collections.emptySet());
        MixinBuilder.loadClasses(builders, loadedTargets, toLoad, toNotLoad);
    }

    protected static <E extends Enum<E>> void loadEarlyMixins(Class<E> mixinsEnum, Set<String> loadedCoreMods, List<String> toLoad, List<String> toNotLoad) {
        List<AbstractBuilder> builders = MixinBuilder.getEnabledBuildersForPhase(mixinsEnum, IBaseTransformer.Phase.EARLY, toNotLoad);
        Set<ITargetMod> loadedTargets = MixinBuilder.getLoadedTargetedMods(builders, IBaseTransformer.Phase.EARLY, loadedCoreMods, Collections.emptySet());
        MixinBuilder.loadClasses(builders, loadedTargets, toLoad, toNotLoad);
    }

    protected static <E extends Enum<E>> void loadLateMixins(Class<E> mixinsEnum, Set<String> loadedMods, List<String> toLoad, List<String> toNotLoad) {
        List<AbstractBuilder> builders = MixinBuilder.getEnabledBuildersForPhase(mixinsEnum, IBaseTransformer.Phase.LATE, toNotLoad);
        Set<ITargetMod> loadedTargets = MixinBuilder.getLoadedTargetedMods(builders, IBaseTransformer.Phase.LATE, Collections.emptySet(), loadedMods);
        MixinBuilder.loadClasses(builders, loadedTargets, toLoad, toNotLoad);
    }

    private static <E extends Enum<E>> List<AbstractBuilder> getEnabledBuildersForPhase(Class<E> mixinsEnum, IBaseTransformer.Phase loadingPhase, List<String> toNotLoad) {
        Enum[] constants = (Enum[])mixinsEnum.getEnumConstants();
        ArrayList<AbstractBuilder> list = new ArrayList<AbstractBuilder>(constants.length + 1);
        for (Enum mixin : constants) {
            MixinBuilder builder = ((IMixins)((Object)mixin)).getBuilder();
            MixinBuilder.validateBuilder(builder, mixin, loadingPhase != null);
            if (builder.phase != loadingPhase) continue;
            if (((Boolean)builder.applyIf.get()).booleanValue()) {
                list.add(builder);
                continue;
            }
            builder.addAllClassesTo(toNotLoad);
        }
        return list;
    }

    private static void validateBuilder(MixinBuilder builder, Enum<?> mixin, boolean requirePhase) {
        if (builder == null) {
            throw new NullPointerException("Builder is null for IMixins : " + mixin.name());
        }
        int count = 0;
        if (builder.commonClasses != null) {
            count += builder.commonClasses.size();
        }
        if (builder.clientClasses != null) {
            count += builder.clientClasses.size();
        }
        if (builder.serverClasses != null) {
            count += builder.serverClasses.size();
        }
        if (count == 0) {
            throw new IllegalArgumentException("No mixin class registered for IMixins : " + mixin.name());
        }
        if (requirePhase && builder.phase == null) {
            throw new IllegalArgumentException("No Phase specified for IMixins : " + mixin.name());
        }
    }
}

