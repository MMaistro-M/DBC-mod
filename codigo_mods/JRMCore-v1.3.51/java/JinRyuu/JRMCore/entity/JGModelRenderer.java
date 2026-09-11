/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class JGModelRenderer
extends ModelRenderer {
    public JGModelRenderer(ModelBase p_i1172_1_, String p_i1172_2_) {
        super(p_i1172_1_, p_i1172_2_);
    }

    public JGModelRenderer(ModelBase p_i1173_1_) {
        super(p_i1173_1_);
    }

    public JGModelRenderer(ModelBase p_i1174_1_, int p_i1174_2_, int p_i1174_3_) {
        super(p_i1174_1_, p_i1174_2_, p_i1174_3_);
    }

    public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, float p_78790_4_, float p_78790_5_, float p_78790_6_, float p_78790_7_, float p_78790_8_, float p_78790_9_) {
        this.func_78790_a(p_78790_1_, p_78790_2_, p_78790_3_, (int)p_78790_4_, (int)p_78790_5_, (int)p_78790_6_, p_78790_9_);
    }
}

