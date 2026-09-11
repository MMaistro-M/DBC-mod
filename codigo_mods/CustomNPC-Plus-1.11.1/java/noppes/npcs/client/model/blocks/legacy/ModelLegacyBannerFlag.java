/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.legacy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLegacyBannerFlag
extends ModelBase {
    ModelRenderer Flag;

    public ModelLegacyBannerFlag() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.Flag = new ModelRenderer((ModelBase)this, 0, 0);
        this.Flag.func_78789_a(0.0f, 0.0f, 0.0f, 15, 27, 0);
        this.Flag.func_78793_a(-7.5f, -7.0f, -2.0f);
        this.Flag.func_78787_b(32, 32);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Flag.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

