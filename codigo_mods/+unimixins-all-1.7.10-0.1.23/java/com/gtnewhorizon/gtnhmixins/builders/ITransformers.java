/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package com.gtnewhorizon.gtnhmixins.builders;

import com.gtnewhorizon.gtnhmixins.GTNHMixins;
import com.gtnewhorizon.gtnhmixins.builders.IBaseTransformer;
import com.gtnewhorizon.gtnhmixins.builders.TransformerBuilder;
import java.util.ArrayList;
import javax.annotation.Nonnull;

public interface ITransformers
extends IBaseTransformer {
    @Nonnull
    public TransformerBuilder getBuilder();

    public static <E extends Enum<E>> String[] getTransformers(Class<E> transformerEnum) {
        ArrayList<String> toLoad = new ArrayList<String>();
        ArrayList<String> toNotLoad = new ArrayList<String>();
        TransformerBuilder.loadTransformers(transformerEnum, toLoad, toNotLoad);
        GTNHMixins.log("Not loading the following transformers: {}", toNotLoad);
        for (String transformer : toLoad) {
            GTNHMixins.log("Loading ITransformer {}", transformer);
        }
        return toLoad.toArray(new String[0]);
    }
}

