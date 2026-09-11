/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelKiAuraRing
extends ModelBiped {
    ModelRenderer low;
    ModelRenderer lowm1;
    ModelRenderer lowm2;
    ModelRenderer lowm3;
    ModelRenderer lowm4;
    ModelRenderer lowm5;
    ModelRenderer lowm6;
    ModelRenderer lowm7;
    ModelRenderer lowm8;

    public ModelKiAuraRing() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.low = new ModelRenderer((ModelBase)this, 32, 0);
        this.low.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.low.func_78793_a(0.0f, 20.0f, 0.0f);
        this.setRotation(this.low, 0.0f, 0.0f, 0.0f);
        this.lowm1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm1.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm1.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm1.func_78787_b(64, 32);
        this.setRotation(this.lowm1, 0.8726646f, 1.570796f, 0.0f);
        this.lowm2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm2.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm2.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm2.func_78787_b(64, 32);
        this.setRotation(this.lowm2, 0.8726646f, 0.0f, 0.0f);
        this.lowm3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm3.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm3.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm3.func_78787_b(64, 32);
        this.setRotation(this.lowm3, 0.8726646f, -0.7853982f, 0.0f);
        this.lowm4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm4.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm4.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm4.func_78787_b(64, 32);
        this.setRotation(this.lowm4, 0.8726646f, 3.141593f, 0.0f);
        this.lowm5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm5.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm5.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm5.func_78787_b(64, 32);
        this.setRotation(this.lowm5, 0.8726646f, -1.570796f, 0.0f);
        this.lowm6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm6.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm6.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm6.func_78787_b(64, 32);
        this.setRotation(this.lowm6, 0.8726646f, -2.373648f, 0.0f);
        this.lowm7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm7.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm7.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm7.func_78787_b(64, 32);
        this.setRotation(this.lowm7, 0.8726646f, 2.373648f, 0.0f);
        this.lowm8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.lowm8.func_78789_a(-5.0f, -12.0f, -8.0f, 10, 10, 0);
        this.lowm8.func_78793_a(0.0f, 20.0f, 0.0f);
        this.lowm8.func_78787_b(64, 32);
        this.setRotation(this.lowm8, 0.8726646f, 0.7853982f, 0.0f);
        this.low.func_78792_a(this.lowm1);
        this.low.func_78792_a(this.lowm2);
        this.low.func_78792_a(this.lowm3);
        this.low.func_78792_a(this.lowm4);
        this.low.func_78792_a(this.lowm5);
        this.low.func_78792_a(this.lowm6);
        this.low.func_78792_a(this.lowm7);
        this.low.func_78792_a(this.lowm8);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.low.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        float par3 = f2;
        float par31 = 1.0f;
        this.low.field_78797_d = 28.0f;
        this.low.field_78796_g = -par3 / 5.0f;
    }

    public void renderModel(Entity entity, float par8, float par9, float f, float r) {
        this.func_78088_a(entity, 0.0f, 0.0f, r, par8, par9, f);
    }
}

