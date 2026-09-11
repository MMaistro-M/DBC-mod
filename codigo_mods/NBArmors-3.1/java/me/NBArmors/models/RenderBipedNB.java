/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 */
package me.NBArmors.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;

public class RenderBipedNB
extends RenderBiped {
    public RenderBipedNB(ModelBiped p_i1257_1_, float p_i1257_2_) {
        this(p_i1257_1_, p_i1257_2_, 1.0f);
    }

    public RenderBipedNB(ModelBiped p_i1258_1_, float p_i1258_2_, float p_i1258_3_) {
        super(p_i1258_1_, p_i1258_2_);
        this.field_77071_a = p_i1258_1_;
        this.field_77070_b = p_i1258_3_;
        this.func_82421_b();
    }
}

