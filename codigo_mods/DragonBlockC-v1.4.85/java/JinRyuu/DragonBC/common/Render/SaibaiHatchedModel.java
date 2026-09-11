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

public class SaibaiHatchedModel
extends ModelBase {
    ModelRenderer bottom;
    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer side3;
    ModelRenderer side4;
    ModelRenderer a1;
    ModelRenderer a2;
    ModelRenderer a3;
    ModelRenderer a4;

    public SaibaiHatchedModel() {
        this.field_78090_t = 128;
        this.field_78089_u = 32;
        this.bottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.bottom.func_78789_a(-8.0f, -8.0f, -8.0f, 16, 8, 16);
        this.bottom.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bottom.func_78787_b(64, 32);
        this.bottom.field_78809_i = true;
        this.setRotation(this.bottom, 0.0f, 0.0f, 0.0f);
        this.side1 = new ModelRenderer((ModelBase)this, 64, 0);
        this.side1.func_78789_a(-8.0f, -7.0f, -8.0f, 16, 8, 1);
        this.side1.func_78793_a(0.0f, 15.0f, 0.0f);
        this.side1.func_78787_b(64, 32);
        this.side1.field_78809_i = true;
        this.setRotation(this.side1, 0.0f, 0.0f, 0.0f);
        this.side2 = new ModelRenderer((ModelBase)this, 64, 0);
        this.side2.func_78789_a(-8.0f, -7.0f, -8.0f, 16, 8, 1);
        this.side2.func_78793_a(0.0f, 15.0f, 15.0f);
        this.side2.func_78787_b(64, 32);
        this.side2.field_78809_i = true;
        this.setRotation(this.side2, 0.0f, 0.0f, 0.0f);
        this.side3 = new ModelRenderer((ModelBase)this, 98, 0);
        this.side3.func_78789_a(-8.0f, -7.0f, -8.0f, 1, 8, 14);
        this.side3.func_78793_a(15.0f, 15.0f, 1.0f);
        this.side3.func_78787_b(64, 32);
        this.side3.field_78809_i = true;
        this.setRotation(this.side3, 0.0f, 0.0f, 0.0f);
        this.side4 = new ModelRenderer((ModelBase)this, 98, 0);
        this.side4.func_78789_a(-8.0f, -7.0f, -8.0f, 1, 8, 14);
        this.side4.func_78793_a(0.0f, 15.0f, 1.0f);
        this.side4.func_78787_b(64, 32);
        this.side4.field_78809_i = true;
        this.setRotation(this.side4, 0.0f, 0.0f, 0.0f);
        this.a1 = new ModelRenderer((ModelBase)this, 50, 10);
        this.a1.func_78789_a(0.0f, 0.0f, 0.0f, 14, 0, 14);
        this.a1.func_78793_a(-7.0f, 8.0f, -7.0f);
        this.a1.func_78787_b(64, 32);
        this.a1.field_78809_i = true;
        this.setRotation(this.a1, -0.7853982f, 0.0f, 0.0f);
        this.a2 = new ModelRenderer((ModelBase)this, 50, 10);
        this.a2.func_78789_a(-14.0f, 0.0f, 0.0f, 14, 0, 14);
        this.a2.func_78793_a(7.0f, 8.0f, -7.0f);
        this.a2.func_78787_b(64, 32);
        this.a2.field_78809_i = true;
        this.setRotation(this.a2, 0.0f, 0.0f, -0.7853982f);
        this.a3 = new ModelRenderer((ModelBase)this, 50, 10);
        this.a3.func_78789_a(-14.0f, 0.0f, -14.0f, 14, 0, 14);
        this.a3.func_78793_a(7.0f, 8.0f, 7.0f);
        this.a3.func_78787_b(64, 32);
        this.a3.field_78809_i = true;
        this.setRotation(this.a3, 0.7853982f, 0.0f, 0.0f);
        this.a4 = new ModelRenderer((ModelBase)this, 50, 10);
        this.a4.func_78789_a(0.0f, 0.0f, -14.0f, 14, 0, 14);
        this.a4.func_78793_a(-7.0f, 8.0f, 7.0f);
        this.a4.func_78787_b(64, 32);
        this.a4.field_78809_i = true;
        this.setRotation(this.a4, 0.0f, 0.0f, 0.7853982f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.bottom.func_78785_a(f5);
        this.side1.func_78785_a(f5);
        this.side2.func_78785_a(f5);
        this.side3.func_78785_a(f5);
        this.side4.func_78785_a(f5);
        this.a1.func_78785_a(f5);
        this.a2.func_78785_a(f5);
        this.a3.func_78785_a(f5);
        this.a4.func_78785_a(f5);
    }

    public void renderModel(float f5) {
        this.bottom.func_78785_a(f5);
        this.side1.func_78785_a(f5);
        this.side2.func_78785_a(f5);
        this.side3.func_78785_a(f5);
        this.side4.func_78785_a(f5);
        this.a1.func_78785_a(f5);
        this.a2.func_78785_a(f5);
        this.a3.func_78785_a(f5);
        this.a4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, null);
    }
}

