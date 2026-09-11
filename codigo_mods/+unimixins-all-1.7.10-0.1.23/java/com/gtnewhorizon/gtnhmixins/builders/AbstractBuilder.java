/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.FMLLaunchHandler
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package com.gtnewhorizon.gtnhmixins.builders;

import com.gtnewhorizon.gtnhmixins.GTNHMixins;
import com.gtnewhorizon.gtnhmixins.builders.IBaseTransformer;
import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class AbstractBuilder {
    @Nullable
    protected List<String> commonClasses;
    @Nullable
    protected List<String> clientClasses;
    @Nullable
    protected List<String> serverClasses;
    @Nullable
    protected List<ITargetMod> requiredMods;
    @Nullable
    protected List<ITargetMod> excludedMods;
    @Nullable
    protected IBaseTransformer.Phase phase;
    @Nonnull
    protected Supplier<Boolean> applyIf = () -> true;

    protected AbstractBuilder addCommonClasses(String ... classes) {
        Objects.requireNonNull(classes);
        if (this.commonClasses == null) {
            this.commonClasses = new ArrayList<String>(4);
        }
        Collections.addAll(this.commonClasses, classes);
        return this;
    }

    protected AbstractBuilder addClientClasses(String ... classes) {
        Objects.requireNonNull(classes);
        if (this.clientClasses == null) {
            this.clientClasses = new ArrayList<String>(4);
        }
        Collections.addAll(this.clientClasses, classes);
        return this;
    }

    protected AbstractBuilder addServerClasses(String ... classes) {
        Objects.requireNonNull(classes);
        if (this.serverClasses == null) {
            this.serverClasses = new ArrayList<String>(4);
        }
        Collections.addAll(this.serverClasses, classes);
        return this;
    }

    protected AbstractBuilder addSidedClasses(@Nonnull IBaseTransformer.Side side, String ... classes) {
        Objects.requireNonNull(side);
        switch (side) {
            case COMMON: {
                return this.addCommonClasses(classes);
            }
            case CLIENT: {
                return this.addClientClasses(classes);
            }
            case SERVER: {
                return this.addServerClasses(classes);
            }
        }
        throw new IllegalArgumentException();
    }

    protected AbstractBuilder setApplyIf(@Nonnull Supplier<Boolean> applyIf) {
        Objects.requireNonNull(applyIf);
        this.applyIf = applyIf;
        return this;
    }

    protected AbstractBuilder addRequiredMod(@Nonnull ITargetMod mod) {
        Objects.requireNonNull(mod);
        if (this.requiredMods == null) {
            this.requiredMods = new ArrayList<ITargetMod>(2);
        }
        this.requiredMods.add(mod);
        return this;
    }

    protected AbstractBuilder addExcludedMod(@Nonnull ITargetMod mod) {
        Objects.requireNonNull(mod);
        if (this.excludedMods == null) {
            this.excludedMods = new ArrayList<ITargetMod>(2);
        }
        this.excludedMods.add(mod);
        return this;
    }

    protected void addClassesForCurrentSide(List<String> toLoad, List<String> toNotLoad) {
        boolean isClient = FMLLaunchHandler.side().isClient();
        if (this.commonClasses != null) {
            toLoad.addAll(this.commonClasses);
        }
        if (this.clientClasses != null) {
            if (isClient) {
                toLoad.addAll(this.clientClasses);
            } else {
                toNotLoad.addAll(this.clientClasses);
            }
        }
        if (this.serverClasses != null) {
            if (!isClient) {
                toLoad.addAll(this.serverClasses);
            } else {
                toNotLoad.addAll(this.serverClasses);
            }
        }
    }

    protected void addAllClassesTo(List<String> list) {
        if (this.commonClasses != null) {
            list.addAll(this.commonClasses);
        }
        if (this.clientClasses != null) {
            list.addAll(this.clientClasses);
        }
        if (this.serverClasses != null) {
            list.addAll(this.serverClasses);
        }
    }

    protected void addAllTargetsTo(Set<ITargetMod> set) {
        if (this.requiredMods != null) {
            set.addAll(this.requiredMods);
        }
        if (this.excludedMods != null) {
            set.addAll(this.excludedMods);
        }
    }

    protected boolean shouldLoad(Set<ITargetMod> loadedTargets) {
        return this.allRequiredModsPresent(loadedTargets) && this.noExcludedModsPresent(loadedTargets);
    }

    protected boolean allRequiredModsPresent(Set<ITargetMod> loadedTargets) {
        if (this.requiredMods == null) {
            return true;
        }
        for (int i = 0; i < this.requiredMods.size(); ++i) {
            if (loadedTargets.contains(this.requiredMods.get(i))) continue;
            return false;
        }
        return true;
    }

    protected boolean noExcludedModsPresent(Set<ITargetMod> loadedTargets) {
        if (this.excludedMods == null) {
            return true;
        }
        for (int i = 0; i < this.excludedMods.size(); ++i) {
            if (!loadedTargets.contains(this.excludedMods.get(i))) continue;
            return false;
        }
        return true;
    }

    protected static void loadClasses(List<AbstractBuilder> builders, Set<ITargetMod> loadedTargets, List<String> toLoad, List<String> toNotLoad) {
        for (int i = 0; i < builders.size(); ++i) {
            AbstractBuilder builder = builders.get(i);
            if (builder.shouldLoad(loadedTargets)) {
                builder.addClassesForCurrentSide(toLoad, toNotLoad);
                continue;
            }
            builder.addAllClassesTo(toNotLoad);
        }
    }

    protected static Set<ITargetMod> getLoadedTargetedMods(List<AbstractBuilder> builders, IBaseTransformer.Phase loadingPhase, Set<String> loadedCoreMods, Set<String> loadedMods) {
        HashSet<ITargetMod> targets = new HashSet<ITargetMod>();
        for (int i = 0; i < builders.size(); ++i) {
            builders.get(i).addAllTargetsTo(targets);
        }
        Iterator iterator = targets.iterator();
        ArrayList<ITargetMod> notPresent = new ArrayList<ITargetMod>();
        while (iterator.hasNext()) {
            ITargetMod target = (ITargetMod)iterator.next();
            TargetModBuilder builder = target.getBuilder();
            TargetModBuilder.validateBuilder(builder, target, loadingPhase);
            if (builder.isTargetPresent(loadedCoreMods, loadedMods)) continue;
            notPresent.add(target);
            iterator.remove();
        }
        if (loadingPhase == null) {
            GTNHMixins.log("ITargetMods found: {}", ((Object)targets).toString());
            GTNHMixins.log("ITargetMods not found: {}", ((Object)notPresent).toString());
        } else {
            GTNHMixins.log("ITargetMods found during Phase {}: {}", new Object[]{loadingPhase, ((Object)targets).toString()});
            GTNHMixins.log("ITargetMods not found during Phase {}: {}", new Object[]{loadingPhase, ((Object)notPresent).toString()});
        }
        return targets;
    }
}

