/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package JinRyuu.DragonBC.common.Render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class hair
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape5;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;

    public hair() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 16, 16);
        this.Shape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape1.func_78787_b(128, 128);
        this.Shape1.field_78809_i = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape5.func_78789_a(0.0f, 0.0f, 0.0f, 7, 7, 10);
        this.Shape5.func_78793_a(6.0f, 0.0f, 9.0f);
        this.Shape5.func_78787_b(128, 128);
        this.Shape5.field_78809_i = true;
        this.setRotation(this.Shape5, 1.27409f, 0.3141593f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 64, 0);
        this.Shape3.func_78789_a(0.0f, 0.0f, 0.0f, 5, 5, 10);
        this.Shape3.func_78793_a(8.0f, 4.0f, -6.0f);
        this.Shape3.func_78787_b(128, 128);
        this.Shape3.field_78809_i = true;
        this.setRotation(this.Shape3, -0.2268928f, -0.2617994f, 0.0f);
        this.Shape3.field_78809_i = false;
        this.Shape4 = new ModelRenderer((ModelBase)this, 64, 0);
        this.Shape4.func_78789_a(0.0f, 0.0f, 0.0f, 5, 5, 10);
        this.Shape4.func_78793_a(7.0f, 10.0f, -7.0f);
        this.Shape4.func_78787_b(128, 128);
        this.Shape4.field_78809_i = true;
        this.setRotation(this.Shape4, -0.1047198f, -0.1396263f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 32);
        this.Shape6.func_78789_a(0.0f, 0.0f, 0.0f, 7, 7, 10);
        this.Shape6.func_78793_a(6.0f, 2.0f, 12.0f);
        this.Shape6.func_78787_b(128, 128);
        this.Shape6.field_78809_i = true;
        this.setRotation(this.Shape6, 0.5410521f, 0.296706f, 0.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 0, 49);
        this.Shape7.func_78789_a(0.0f, 0.0f, 0.0f, 5, 5, 8);
        this.Shape7.func_78793_a(6.0f, 8.0f, 15.0f);
        this.Shape7.func_78787_b(128, 128);
        this.Shape7.field_78809_i = true;
        this.setRotation(this.Shape7, 0.0698132f, 0.296706f, 0.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 0, 62);
        this.Shape8.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Shape8.func_78793_a(8.0f, -8.0f, 12.0f);
        this.Shape8.func_78787_b(128, 128);
        this.Shape8.field_78809_i = true;
        this.setRotation(this.Shape8, 0.8028515f, 0.296706f, 0.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 62);
        this.Shape9.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Shape9.func_78793_a(10.0f, -2.0f, 19.0f);
        this.Shape9.func_78787_b(128, 128);
        this.Shape9.field_78809_i = true;
        this.setRotation(this.Shape9, 0.2094395f, 0.296706f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Shape1.func_78785_a(f5);
        this.Shape5.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        this.Shape6.func_78785_a(f5);
        this.Shape7.func_78785_a(f5);
        this.Shape8.func_78785_a(f5);
        this.Shape9.func_78785_a(f5);
    }

    public void renderModel(float f1) {
        this.Shape1.func_78785_a(f1);
        this.Shape3.func_78785_a(f1);
        this.Shape4.func_78785_a(f1);
        this.Shape5.func_78785_a(f1);
        this.Shape6.func_78785_a(f1);
        this.Shape7.func_78785_a(f1);
        this.Shape8.func_78785_a(f1);
        this.Shape9.func_78785_a(f1);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

