/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package com.gtnewhorizon.gtnhmixins.builders;

import com.gtnewhorizon.gtnhmixins.builders.AbstractBuilder;
import com.gtnewhorizon.gtnhmixins.builders.IBaseTransformer;
import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.ITransformers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import javax.annotation.Nonnull;

public class TransformerBuilder
extends AbstractBuilder {
    public TransformerBuilder() {
    }

    public TransformerBuilder(String description) {
    }

    public TransformerBuilder addCommonTransformers(String ... transformers) {
        return (TransformerBuilder)super.addCommonClasses(transformers);
    }

    public TransformerBuilder addClientTransformers(String ... transformers) {
        return (TransformerBuilder)super.addClientClasses(transformers);
    }

    public TransformerBuilder addServerTransformers(String ... transformers) {
        return (TransformerBuilder)super.addServerClasses(transformers);
    }

    public TransformerBuilder addSidedTransformers(@Nonnull IBaseTransformer.Side side, String ... transformers) {
        return (TransformerBuilder)super.addSidedClasses(side, transformers);
    }

    @Override
    public TransformerBuilder setApplyIf(@Nonnull Supplier<Boolean> applyIf) {
        return (TransformerBuilder)super.setApplyIf(applyIf);
    }

    @Override
    public TransformerBuilder addRequiredMod(@Nonnull ITargetMod mod) {
        return (TransformerBuilder)super.addRequiredMod(mod);
    }

    @Override
    public TransformerBuilder addExcludedMod(@Nonnull ITargetMod mod) {
        return (TransformerBuilder)super.addExcludedMod(mod);
    }

    protected static <E extends Enum<E>> void loadTransformers(Class<E> transformerEnum, List<String> toLoad, List<String> toNotLoad) {
        List<AbstractBuilder> builders = TransformerBuilder.getEnabledBuildersForPhase(transformerEnum, toNotLoad);
        Set<ITargetMod> loadedTargets = TransformerBuilder.getLoadedTargetedMods(builders, null, Collections.emptySet(), Collections.emptySet());
        TransformerBuilder.loadClasses(builders, loadedTargets, toLoad, toNotLoad);
    }

    private static <E extends Enum<E>> List<AbstractBuilder> getEnabledBuildersForPhase(Class<E> transformerEnum, List<String> toNotLoad) {
        Enum[] constants = (Enum[])transformerEnum.getEnumConstants();
        ArrayList<AbstractBuilder> list = new ArrayList<AbstractBuilder>(constants.length + 1);
        for (Enum transformer : constants) {
            TransformerBuilder builder = ((ITransformers)((Object)transformer)).getBuilder();
            TransformerBuilder.validateBuilder(builder, transformer);
            if (((Boolean)builder.applyIf.get()).booleanValue()) {
                list.add(builder);
                continue;
            }
            builder.addAllClassesTo(toNotLoad);
        }
        return list;
    }

    private static void validateBuilder(TransformerBuilder builder, Enum<?> transformer) {
        if (builder == null) {
            throw new NullPointerException("Builder is null for ITransformer : " + transformer.name());
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
            throw new IllegalArgumentException("No transformer class registered for ITransformer : " + transformer.name());
        }
    }
}

