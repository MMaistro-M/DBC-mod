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

public class ModelLegacyChair
extends ModelBase {
    ModelRenderer Leg1 = new ModelRenderer((ModelBase)this, 0, 0);
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;

    public ModelLegacyChair() {
        this.Leg1.field_78809_i = true;
        this.Leg1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 18, 1);
        this.Leg1.func_78793_a(4.01f, 6.0f, 5.01f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg2.field_78809_i = true;
        this.Leg2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 9, 1);
        this.Leg2.func_78793_a(4.01f, 15.5f, -5.01f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 18, 1);
        this.Leg3.func_78793_a(-5.01f, 6.0f, 5.01f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Leg4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 9, 1);
        this.Leg4.func_78793_a(-5.01f, 15.5f, -5.01f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 8, 2);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 10, 1, 11);
        this.Shape1.func_78793_a(-5.0f, 16.0f, -5.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 4, 4);
        this.Shape2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 1);
        this.Shape2.func_78793_a(-1.5f, 6.51f, 5.5f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 4, 4);
        this.Shape3.field_78809_i = true;
        this.Shape3.func_78789_a(-3.0f, 0.0f, 0.0f, 3, 2, 1);
        this.Shape3.func_78793_a(4.0f, 6.5f, 5.0f);
        this.setRotation(this.Shape3, 0.0f, 0.2094395f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 4, 4);
        this.Shape4.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 1);
        this.Shape4.func_78793_a(-4.0f, 6.5f, 5.0f);
        this.setRotation(this.Shape4, 0.0f, -0.2094395f, 0.0f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 46, 0);
        this.Shape5.func_78789_a(0.0f, 0.0f, 0.0f, 9, 1, 1);
        this.Shape5.func_78793_a(-4.0f, 19.0f, 5.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 46, 0);
        this.Shape6.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 1);
        this.Shape6.func_78793_a(-4.0f, 19.0f, -5.0f);
        this.Shape7 = new ModelRenderer((ModelBase)this, 11, 13);
        this.Shape7.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 9);
        this.Shape7.func_78793_a(-5.0f, 20.0f, -4.0f);
        this.Shape8 = new ModelRenderer((ModelBase)this, 11, 13);
        this.Shape8.field_78809_i = true;
        this.Shape8.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 9);
        this.Shape8.func_78793_a(4.0f, 20.0f, -4.0f);
        this.Shape9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape9.field_78809_i = true;
        this.Shape9.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.Shape9.func_78793_a(2.0f, 8.0f, 5.5f);
        this.setRotation(this.Shape9, -0.0523599f, 0.0f, 0.0f);
        this.Shape10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape10.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.Shape10.func_78793_a(-3.0f, 8.0f, 5.5f);
        this.setRotation(this.Shape10, -0.0523599f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape11.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.Shape11.func_78793_a(-0.5f, 8.0f, 5.6f);
        this.setRotation(this.Shape11, -0.0698132f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Leg1.func_78785_a(f5);
        this.Leg2.func_78785_a(f5);
        this.Leg3.func_78785_a(f5);
        this.Leg4.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        this.Shape5.func_78785_a(f5);
        this.Shape6.func_78785_a(f5);
        this.Shape7.func_78785_a(f5);
        this.Shape8.func_78785_a(f5);
        this.Shape9.func_78785_a(f5);
        this.Shape10.func_78785_a(f5);
        this.Shape11.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

