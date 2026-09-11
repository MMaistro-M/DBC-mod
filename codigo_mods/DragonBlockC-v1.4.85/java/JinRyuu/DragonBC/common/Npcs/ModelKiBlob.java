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

public class ModelKiBlob
extends ModelBiped {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;

    public ModelKiBlob() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.func_78789_a(-10.0f, -10.0f, -10.0f, 20, 20, 20);
        this.Shape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape1.func_78787_b(128, 128);
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape2.func_78789_a(-8.0f, -8.0f, -12.0f, 16, 16, 24);
        this.Shape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape2.func_78787_b(128, 128);
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape3.func_78789_a(-4.0f, -4.0f, -14.0f, 8, 8, 28);
        this.Shape3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape3.func_78787_b(128, 128);
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape4.func_78789_a(-8.0f, -12.0f, -8.0f, 16, 24, 16);
        this.Shape4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape4.func_78787_b(128, 128);
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.func_78789_a(-4.0f, -14.0f, -4.0f, 8, 28, 8);
        this.Shape5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape5.func_78787_b(128, 128);
        this.setRotation(this.Shape5, 0.0f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.func_78789_a(-12.0f, -8.0f, -8.0f, 24, 16, 16);
        this.Shape6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape6.func_78787_b(128, 128);
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape7.func_78789_a(-14.0f, -4.0f, -4.0f, 28, 8, 8);
        this.Shape7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape7.func_78787_b(128, 128);
        this.setRotation(this.Shape7, 0.0f, 0.0f, 0.0f);
        this.Shape1.func_78792_a(this.Shape2);
        this.Shape1.func_78792_a(this.Shape3);
        this.Shape1.func_78792_a(this.Shape4);
        this.Shape1.func_78792_a(this.Shape5);
        this.Shape1.func_78792_a(this.Shape6);
        this.Shape1.func_78792_a(this.Shape7);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        float par3 = f2;
        float par31 = 1.0f;
        this.Shape1.field_78808_h = par3;
        this.Shape1.field_78796_g = par3;
        this.Shape1.field_78795_f = par3;
    }

    public void renderModel(Entity entity, float par8, float par9, float f, float r) {
        this.func_78088_a(entity, 0.0f, 0.0f, r, par8, par9, f);
    }
}

