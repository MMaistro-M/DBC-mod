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

public class ModelKiMakanko
extends ModelBiped {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public ModelKiMakanko() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 8);
        this.Shape1.func_78789_a(-3.0f, -3.0f, -7.0f, 6, 6, 14);
        this.Shape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape1.func_78787_b(128, 128);
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 8);
        this.Shape2.func_78789_a(-2.0f, -2.0f, -8.0f, 4, 4, 16);
        this.Shape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape2.func_78787_b(128, 128);
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 8);
        this.Shape3.func_78789_a(-2.0f, -4.0f, -6.0f, 4, 8, 12);
        this.Shape3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape3.func_78787_b(128, 128);
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 8);
        this.Shape4.func_78789_a(-4.0f, -2.0f, -6.0f, 8, 4, 12);
        this.Shape4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape4.func_78787_b(128, 128);
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape5.func_78789_a(6.0f, -3.0f, -5.0f, 1, 6, 1);
        this.Shape5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape5.func_78787_b(128, 128);
        this.setRotation(this.Shape5, -0.3490659f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.func_78789_a(7.0f, -3.0f, -3.0f, 1, 6, 1);
        this.Shape6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape6.func_78787_b(128, 128);
        this.setRotation(this.Shape6, -0.3490659f, 0.0f, -0.7853982f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape7.func_78789_a(-3.533333f, -8.2f, -0.5333334f, 7, 1, 1);
        this.Shape7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape7.func_78787_b(128, 128);
        this.setRotation(this.Shape7, 0.0f, 0.4537856f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape8.func_78789_a(-3.0f, -8.0f, -0.4666667f, 6, 1, 1);
        this.Shape8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape8.func_78787_b(128, 128);
        this.setRotation(this.Shape8, 0.0f, 0.4014257f, -0.7853982f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape9.func_78789_a(-7.0f, -3.0f, 3.0f, 1, 6, 1);
        this.Shape9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape9.func_78787_b(128, 128);
        this.setRotation(this.Shape9, 0.3490659f, 0.0f, 0.0f);
        this.Shape1.func_78792_a(this.Shape2);
        this.Shape1.func_78792_a(this.Shape3);
        this.Shape1.func_78792_a(this.Shape4);
        this.Shape7.func_78792_a(this.Shape5);
        this.Shape7.func_78792_a(this.Shape6);
        this.Shape7.func_78792_a(this.Shape8);
        this.Shape7.func_78792_a(this.Shape9);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.func_78785_a(f5);
        this.Shape7.func_78785_a(f5);
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
        this.Shape7.field_78808_h = -par3;
    }

    public void renderModel(Entity entity, float par8, float par9, float f, float r) {
        this.func_78088_a(entity, 0.0f, 0.0f, r, par8, par9, f);
    }
}

