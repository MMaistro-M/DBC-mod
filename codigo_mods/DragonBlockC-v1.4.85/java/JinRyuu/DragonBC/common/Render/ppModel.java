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

public class ppModel
extends ModelBase {
    ModelRenderer sw;

    public ppModel() {
        this.field_78090_t = 128;
        this.field_78089_u = 32;
        this.sw = new ModelRenderer((ModelBase)this, 0, 0);
        this.sw.func_78789_a(-0.5f, -16.0f, -0.5f, 1, 16, 1);
        this.sw.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sw.func_78787_b(64, 32);
        this.setRotation(this.sw, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.sw.func_78785_a(f5);
    }

    public void renderModel(float f5) {
        this.sw.func_78785_a(f5);
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

