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

public class ModelPorungaOld
extends ModelBiped {
    ModelRenderer dragon1;
    ModelRenderer dragon2;
    ModelRenderer dragon3;
    ModelRenderer dragon4;
    ModelRenderer dragon5;
    ModelRenderer dragon8;
    ModelRenderer dragon10;
    ModelRenderer dragon12;
    ModelRenderer dragon13;
    ModelRenderer dragon14;
    ModelRenderer dragon15;
    ModelRenderer dragon16;
    ModelRenderer dragon17;
    ModelRenderer dragon18;
    ModelRenderer dragon19;
    ModelRenderer dragon20;
    ModelRenderer dragon22;
    ModelRenderer dragon23;
    ModelRenderer dragon24;

    public ModelPorungaOld() {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.dragon1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.dragon1.func_78789_a(-4.0f, -4.0f, -4.0f, 8, 16, 8);
        this.dragon1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon1.func_78787_b(256, 128);
        this.dragon1.field_78809_i = true;
        this.dragon2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.dragon2.func_78789_a(-4.0f, -18.0f, -3.0f, 8, 16, 8);
        this.dragon2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon2.func_78787_b(256, 128);
        this.setRotation(this.dragon2, 0.4537856f, 0.0174533f, 0.0f);
        this.dragon3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.dragon3.func_78789_a(-4.0f, -25.0f, -16.0f, 8, 16, 8);
        this.dragon3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon3.func_78787_b(256, 128);
        this.setRotation(this.dragon3, -0.4363323f, 0.0f, 0.0f);
        this.dragon4 = new ModelRenderer((ModelBase)this, 40, 37);
        this.dragon4.func_78789_a(-3.0f, -91.0f, 11.0f, 11, 4, 4);
        this.dragon4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon4.func_78787_b(256, 128);
        this.setRotation(this.dragon4, 0.2443461f, -0.3839724f, 0.0f);
        this.dragon5 = new ModelRenderer((ModelBase)this, 0, 47);
        this.dragon5.func_78789_a(-5.0f, -80.0f, 44.0f, 10, 10, 10);
        this.dragon5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon5.func_78787_b(256, 128);
        this.setRotation(this.dragon5, 0.6806784f, 0.0f, 0.0f);
        this.dragon8 = new ModelRenderer((ModelBase)this, 152, 0);
        this.dragon8.func_78789_a(-17.0f, -85.0f, 13.0f, 36, 20, 16);
        this.dragon8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon8.func_78787_b(256, 128);
        this.setRotation(this.dragon8, 0.2443461f, 0.0174533f, 0.0f);
        this.dragon10 = new ModelRenderer((ModelBase)this, 40, 37);
        this.dragon10.func_78789_a(-8.0f, -91.0f, 11.0f, 11, 4, 4);
        this.dragon10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon10.func_78787_b(256, 128);
        this.setRotation(this.dragon10, 0.2443461f, 0.3839724f, 0.0f);
        this.dragon12 = new ModelRenderer((ModelBase)this, 0, 24);
        this.dragon12.func_78789_a(-3.0f, 7.0f, -6.0f, 6, 16, 6);
        this.dragon12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon12.func_78787_b(256, 128);
        this.setRotation(this.dragon12, 0.2617994f, 0.0f, 0.0f);
        this.dragon13 = new ModelRenderer((ModelBase)this, 40, 45);
        this.dragon13.func_78789_a(3.0f, -61.0f, 71.0f, 4, 4, 12);
        this.dragon13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon13.func_78787_b(256, 128);
        this.setRotation(this.dragon13, 0.9773844f, 0.2094395f, 0.0f);
        this.dragon14 = new ModelRenderer((ModelBase)this, 40, 45);
        this.dragon14.func_78789_a(-7.0f, -61.0f, 71.0f, 4, 4, 12);
        this.dragon14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon14.func_78787_b(256, 128);
        this.setRotation(this.dragon14, 0.9773844f, -0.2094395f, 0.0f);
        this.dragon15 = new ModelRenderer((ModelBase)this, 80, 0);
        this.dragon15.func_78789_a(-12.0f, -76.0f, -8.0f, 24, 24, 12);
        this.dragon15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon15.func_78787_b(256, 128);
        this.setRotation(this.dragon15, -0.0872665f, 0.0f, 0.0f);
        this.dragon16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.dragon16.func_78789_a(-7.0f, -53.0f, -9.0f, 14, 27, 10);
        this.dragon16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon16.func_78787_b(256, 128);
        this.setRotation(this.dragon16, -0.122173f, 0.0f, 0.0f);
        this.dragon17 = new ModelRenderer((ModelBase)this, 0, 90);
        this.dragon17.func_78789_a(-43.0f, -58.0f, -19.0f, 13, 26, 12);
        this.dragon17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon17.func_78787_b(256, 128);
        this.setRotation(this.dragon17, -0.2617994f, -0.0174533f, 0.1745329f);
        this.dragon18 = new ModelRenderer((ModelBase)this, 50, 90);
        this.dragon18.func_78789_a(-43.0f, -84.0f, -4.0f, 13, 26, 12);
        this.dragon18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon18.func_78787_b(256, 128);
        this.setRotation(this.dragon18, 0.0f, 0.0f, 0.1745329f);
        this.dragon19 = new ModelRenderer((ModelBase)this, 50, 90);
        this.dragon19.field_78809_i = true;
        this.dragon19.func_78789_a(32.0f, -84.0f, -4.0f, 13, 26, 12);
        this.dragon19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon19.func_78787_b(256, 128);
        this.setRotation(this.dragon19, 0.0f, 0.0f, -0.1745329f);
        this.dragon20 = new ModelRenderer((ModelBase)this, 0, 90);
        this.dragon20.field_78809_i = true;
        this.dragon20.func_78789_a(32.0f, -58.0f, -19.0f, 13, 26, 12);
        this.dragon20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon20.func_78787_b(256, 128);
        this.setRotation(this.dragon20, -0.2617994f, 0.0174533f, -0.1745329f);
        this.dragon22 = new ModelRenderer((ModelBase)this, 116, 88);
        this.dragon22.func_78789_a(0.0f, -92.0f, 22.0f, 0, 27, 13);
        this.dragon22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon22.func_78787_b(256, 128);
        this.setRotation(this.dragon22, 0.2443461f, 0.0174533f, 0.0f);
        this.dragon23 = new ModelRenderer((ModelBase)this, 100, 73);
        this.dragon23.func_78789_a(0.0f, -74.0f, -9.0f, 0, 47, 8);
        this.dragon23.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon23.func_78787_b(256, 128);
        this.setRotation(this.dragon23, -0.2617994f, 0.0f, 0.0f);
        this.dragon24 = new ModelRenderer((ModelBase)this, 1, 52);
        this.dragon24.func_78789_a(-4.0f, -75.1f, 43.0f, 8, 5, 10);
        this.dragon24.func_78793_a(0.0f, 0.0f, 0.0f);
        this.dragon24.func_78787_b(256, 128);
        this.setRotation(this.dragon24, 0.6806784f, 0.0f, 0.0f);
        this.dragon1.func_78792_a(this.dragon2);
        this.dragon1.func_78792_a(this.dragon3);
        this.dragon1.func_78792_a(this.dragon4);
        this.dragon1.func_78792_a(this.dragon5);
        this.dragon1.func_78792_a(this.dragon8);
        this.dragon1.func_78792_a(this.dragon10);
        this.dragon1.func_78792_a(this.dragon12);
        this.dragon1.func_78792_a(this.dragon13);
        this.dragon1.func_78792_a(this.dragon14);
        this.dragon1.func_78792_a(this.dragon15);
        this.dragon1.func_78792_a(this.dragon16);
        this.dragon1.func_78792_a(this.dragon17);
        this.dragon1.func_78792_a(this.dragon18);
        this.dragon1.func_78792_a(this.dragon19);
        this.dragon1.func_78792_a(this.dragon20);
        this.dragon1.func_78792_a(this.dragon22);
        this.dragon1.func_78792_a(this.dragon23);
        this.dragon1.func_78792_a(this.dragon24);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.dragon1.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    }

    public void renderModel(Entity entity, float f) {
        this.func_78088_a(entity, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f);
    }
}

