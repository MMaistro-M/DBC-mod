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

public class ModelLegacyStool
extends ModelBase {
    ModelRenderer Base = new ModelRenderer((ModelBase)this, 9, 3);
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;

    public ModelLegacyStool() {
        this.Base.func_78789_a(-5.0f, 0.0f, -5.0f, 10, 1, 10);
        this.Base.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 12);
        this.Leg1.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Leg1.func_78793_a(2.0f, 17.0f, 2.0f);
        this.setRotation(this.Leg1, 0.3316126f, 0.7853982f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 12);
        this.Leg2.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Leg2.func_78793_a(2.0f, 17.0f, -2.0f);
        this.setRotation(this.Leg2, 0.3316126f, 2.356194f, -0.0081449f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 12);
        this.Leg3.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Leg3.func_78793_a(-2.0f, 17.0f, 2.0f);
        this.setRotation(this.Leg3, 0.3316126f, -0.7853982f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 12);
        this.Leg4.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Leg4.func_78793_a(-2.0f, 17.0f, -2.0f);
        this.setRotation(this.Leg4, 0.3316126f, -2.356194f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 11);
        this.Shape1.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape1.func_78793_a(2.4f, 19.0f, 0.0f);
        this.setRotation(this.Shape1, 0.0f, 1.570796f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 11);
        this.Shape2.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape2.func_78793_a(0.0f, 19.0f, 2.4f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 0, 11);
        this.Shape3.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape3.func_78793_a(0.0f, 19.0f, -3.4f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 11);
        this.Shape4.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 1, 1);
        this.Shape4.func_78793_a(-3.4f, 19.0f, 0.0f);
        this.setRotation(this.Shape4, 0.0f, 1.570796f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Leg1.func_78785_a(f5);
        this.Leg2.func_78785_a(f5);
        this.Leg3.func_78785_a(f5);
        this.Leg4.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

