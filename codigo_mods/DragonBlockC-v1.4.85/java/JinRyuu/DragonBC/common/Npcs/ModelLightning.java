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

public class ModelLightning
extends ModelBiped {
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    public ModelLightning() {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape1.func_78789_a(-8.0f, -8.0f, -8.0f, 16, 16, 16);
        this.Shape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape1.func_78787_b(64, 32);
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 42);
        this.Shape2.func_78789_a(-15.0f, -15.0f, -15.0f, 30, 30, 30);
        this.Shape2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape2.func_78787_b(64, 32);
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        float par3 = f2;
        float par31 = 1.0f;
        this.Shape1.field_78797_d = 3.0f;
        this.Shape1.field_78796_g = par3 * 2.0f;
        this.Shape2.field_78797_d = 3.0f;
        this.Shape2.field_78796_g = -par3 * 2.0f;
        this.Shape2.field_78795_f = -par3 / 3.0f;
        this.Shape2.field_78808_h = -par3 / 3.0f;
        this.Shape1.field_78795_f = -par3 / 3.0f;
        this.Shape1.field_78808_h = -par3 / 3.0f;
    }

    public void renderModel(Entity entity, float par8, float par9, float f, float r) {
        this.func_78088_a(entity, 0.0f, 0.0f, r, par8, par9, f);
    }
}

