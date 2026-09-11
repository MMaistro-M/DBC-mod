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

public class ModelAuraG
extends ModelBiped {
    ModelRenderer auro;
    public float inc = 1.6f;
    public float inc2 = 0.0f;
    public float[] offY = new float[]{-12.0f, -17.0f, -22.0f, -27.0f, -33.0f, -38.0f, -41.0f, -44.0f};
    public float[] offZ = new float[]{-8.0f, -5.5f, -3.0f, -1.5f, 0.0f, 3.0f, 5.0f, 8.0f};

    public ModelAuraG() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.auro = new ModelRenderer((ModelBase)this, 0, 0);
        this.auro.func_78789_a(-10.0f, -17.0f, -8.0f, 20, 20, 0);
        this.auro.func_78793_a(0.0f, 20.0f, 0.0f);
        this.setRotation(this.auro, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.auro.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    }

    public void renderModel(Entity entity, float f, float r, float w, float s) {
        this.func_78088_a(entity, s, w, r, 0.0f, 0.0f, f);
    }

    public int func_78104_a() {
        return 8;
    }
}

