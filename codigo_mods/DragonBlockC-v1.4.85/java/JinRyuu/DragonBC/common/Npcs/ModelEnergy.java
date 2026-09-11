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

public class ModelEnergy
extends ModelBiped {
    ModelRenderer aShape1;
    ModelRenderer aShape2;
    ModelRenderer aShape3;
    ModelRenderer aShape4;
    ModelRenderer bShape1;
    ModelRenderer bShape2;
    ModelRenderer bShape3;
    ModelRenderer bShape4;
    ModelRenderer bShape5;
    ModelRenderer bShape6;
    ModelRenderer bShape7;
    ModelRenderer cShape1;
    ModelRenderer cShape2;
    ModelRenderer cShape3;
    ModelRenderer dShape1;
    ModelRenderer eShape1;
    ModelRenderer eShape2;
    ModelRenderer eShape3;
    ModelRenderer eShape4;
    ModelRenderer eShape5;
    ModelRenderer eShape6;
    ModelRenderer eShape7;
    ModelRenderer eShape8;
    ModelRenderer eShape9;

    public ModelEnergy() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.aShape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.aShape1.func_78789_a(-4.0f, -4.0f, 0.0f, 8, 8, 8);
        this.aShape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.aShape1.func_78787_b(128, 128);
        this.setRotation(this.aShape1, 0.0f, 0.0f, 0.0f);
        this.aShape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.aShape2.func_78789_a(-4.0f, -4.0f, -8.0f, 8, 8, 8);
        this.aShape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.aShape2.func_78787_b(128, 128);
        this.setRotation(this.aShape2, 0.0f, 0.0f, 0.0f);
        this.bShape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape1.func_78789_a(-10.0f, -10.0f, -10.0f, 20, 20, 20);
        this.bShape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape1.func_78787_b(128, 128);
        this.setRotation(this.bShape1, 0.0f, 0.0f, 0.0f);
        this.bShape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape2.func_78789_a(-8.0f, -8.0f, -12.0f, 16, 16, 24);
        this.bShape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape2.func_78787_b(128, 128);
        this.setRotation(this.bShape2, 0.0f, 0.0f, 0.0f);
        this.bShape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape3.func_78789_a(-4.0f, -4.0f, -14.0f, 8, 8, 28);
        this.bShape3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape3.func_78787_b(128, 128);
        this.setRotation(this.bShape3, 0.0f, 0.0f, 0.0f);
        this.bShape4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape4.func_78789_a(-8.0f, -12.0f, -8.0f, 16, 24, 16);
        this.bShape4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape4.func_78787_b(128, 128);
        this.setRotation(this.bShape4, 0.0f, 0.0f, 0.0f);
        this.bShape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape5.func_78789_a(-4.0f, -14.0f, -4.0f, 8, 28, 8);
        this.bShape5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape5.func_78787_b(128, 128);
        this.setRotation(this.bShape5, 0.0f, 0.0f, 0.0f);
        this.bShape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape6.func_78789_a(-12.0f, -8.0f, -8.0f, 24, 16, 16);
        this.bShape6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape6.func_78787_b(128, 128);
        this.setRotation(this.bShape6, 0.0f, 0.0f, 0.0f);
        this.bShape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bShape7.func_78789_a(-14.0f, -4.0f, -4.0f, 28, 8, 8);
        this.bShape7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bShape7.func_78787_b(128, 128);
        this.setRotation(this.bShape7, 0.0f, 0.0f, 0.0f);
        this.bShape1.func_78792_a(this.bShape2);
        this.bShape1.func_78792_a(this.bShape3);
        this.bShape1.func_78792_a(this.bShape4);
        this.bShape1.func_78792_a(this.bShape5);
        this.bShape1.func_78792_a(this.bShape6);
        this.bShape1.func_78792_a(this.bShape7);
        this.cShape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cShape1.func_78789_a(-10.0f, -1.0f, -10.0f, 20, 2, 20);
        this.cShape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cShape1.func_78787_b(128, 32);
        this.setRotation(this.cShape1, 0.0f, 0.0f, 0.0f);
        this.cShape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cShape2.func_78789_a(-7.0f, -0.5f, -12.0f, 14, 1, 24);
        this.cShape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cShape2.func_78787_b(128, 32);
        this.setRotation(this.cShape2, 0.0f, 0.0f, 0.0f);
        this.cShape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cShape3.func_78789_a(-12.0f, -0.5f, -7.0f, 24, 1, 14);
        this.cShape3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cShape3.func_78787_b(128, 32);
        this.setRotation(this.cShape3, 0.0f, 0.0f, 0.0f);
        this.cShape1.func_78792_a(this.cShape2);
        this.cShape1.func_78792_a(this.cShape3);
        this.dShape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.dShape1.func_78789_a(-1.0f, -1.0f, -10.0f, 2, 2, 20);
        this.dShape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dShape1.func_78787_b(128, 128);
        this.setRotation(this.dShape1, 0.0f, 0.0f, 0.0f);
        this.eShape1 = new ModelRenderer((ModelBase)this, 0, 8);
        this.eShape1.func_78789_a(-3.0f, -3.0f, -7.0f, 6, 6, 14);
        this.eShape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape1.func_78787_b(128, 128);
        this.setRotation(this.eShape1, 0.0f, 0.0f, 0.0f);
        this.eShape2 = new ModelRenderer((ModelBase)this, 0, 8);
        this.eShape2.func_78789_a(-2.0f, -2.0f, -8.0f, 4, 4, 16);
        this.eShape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape2.func_78787_b(128, 128);
        this.setRotation(this.eShape2, 0.0f, 0.0f, 0.0f);
        this.eShape3 = new ModelRenderer((ModelBase)this, 0, 8);
        this.eShape3.func_78789_a(-2.0f, -4.0f, -6.0f, 4, 8, 12);
        this.eShape3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape3.func_78787_b(128, 128);
        this.setRotation(this.eShape3, 0.0f, 0.0f, 0.0f);
        this.eShape4 = new ModelRenderer((ModelBase)this, 0, 8);
        this.eShape4.func_78789_a(-4.0f, -2.0f, -6.0f, 8, 4, 12);
        this.eShape4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape4.func_78787_b(128, 128);
        this.setRotation(this.eShape4, 0.0f, 0.0f, 0.0f);
        this.eShape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.eShape5.func_78789_a(6.0f, -3.0f, -5.0f, 1, 6, 1);
        this.eShape5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape5.func_78787_b(128, 128);
        this.setRotation(this.eShape5, -0.3490659f, 0.0f, 0.0f);
        this.eShape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.eShape6.func_78789_a(7.0f, -3.0f, -3.0f, 1, 6, 1);
        this.eShape6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape6.func_78787_b(128, 128);
        this.setRotation(this.eShape6, -0.3490659f, 0.0f, -0.7853982f);
        this.eShape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.eShape7.func_78789_a(-3.533333f, -8.2f, -0.5333334f, 7, 1, 1);
        this.eShape7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape7.func_78787_b(128, 128);
        this.setRotation(this.eShape7, 0.0f, 0.4537856f, 0.0f);
        this.eShape8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.eShape8.func_78789_a(-3.0f, -8.0f, -0.4666667f, 6, 1, 1);
        this.eShape8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape8.func_78787_b(128, 128);
        this.setRotation(this.eShape8, 0.0f, 0.4014257f, -0.7853982f);
        this.eShape9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.eShape9.func_78789_a(-7.0f, -3.0f, 3.0f, 1, 6, 1);
        this.eShape9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.eShape9.func_78787_b(128, 128);
        this.setRotation(this.eShape9, 0.3490659f, 0.0f, 0.0f);
        this.eShape1.func_78792_a(this.eShape2);
        this.eShape1.func_78792_a(this.eShape3);
        this.eShape1.func_78792_a(this.eShape4);
        this.eShape7.func_78792_a(this.eShape5);
        this.eShape7.func_78792_a(this.eShape6);
        this.eShape7.func_78792_a(this.eShape8);
        this.eShape7.func_78792_a(this.eShape9);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    }

    public void render(ModelRenderer a, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        a.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        float par3 = f2;
        float par31 = 1.0f;
        this.aShape1.field_78808_h = par3;
        this.aShape2.field_78808_h = par3;
        this.bShape1.field_78808_h = par3;
        this.bShape1.field_78796_g = par3;
        this.bShape1.field_78795_f = par3;
        this.cShape1.field_78796_g = par3;
        this.dShape1.field_78798_e = par3;
        this.dShape1.field_78808_h = par3;
        this.eShape7.field_78808_h = -par3 * 2.0f;
    }

    public void renderModel(byte type, Entity entity, float par8, float par9, float f, float r, boolean b) {
        ModelRenderer render = null;
        if (type == 0) {
            ModelRenderer modelRenderer = render = b ? this.aShape1 : this.aShape2;
        }
        if (type == 1) {
            render = this.bShape1;
        }
        if (type == 2) {
            render = this.cShape1;
        }
        if (type == 3) {
            render = this.dShape1;
        }
        if (type == 4) {
            render = this.eShape7;
        }
        this.render(render, entity, 0.0f, 0.0f, r, par8, par9, f);
    }
}

