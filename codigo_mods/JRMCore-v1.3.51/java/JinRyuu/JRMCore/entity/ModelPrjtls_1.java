/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package JinRyuu.JRMCore.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPrjtls_1
extends ModelBase {
    public ModelRenderer Base;
    public ModelRenderer Front;
    public ModelRenderer Back;
    public ModelRenderer Front_1;
    public ModelRenderer Front_2;
    public ModelRenderer Back_1;
    public ModelRenderer Back_2;
    public ModelRenderer Back_3;
    public ModelRenderer Back_4;
    public ModelRenderer Back_5;

    public ModelPrjtls_1() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Back_1 = new ModelRenderer((ModelBase)this, 29, 22);
        this.Back_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back_1.func_78790_a(-2.0f, -2.25f, 12.7f, 4, 4, 5, 0.0f);
        this.Back_2 = new ModelRenderer((ModelBase)this, 25, 0);
        this.Back_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back_2.func_78790_a(-0.5f, -4.15f, 9.7f, 1, 2, 9, 0.0f);
        this.Front = new ModelRenderer((ModelBase)this, 48, 0);
        this.Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Front.func_78790_a(-2.5f, -2.7f, -9.8f, 5, 5, 3, 0.0f);
        this.Front_2 = new ModelRenderer((ModelBase)this, 52, 17);
        this.Front_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Front_2.func_78790_a(-1.5f, -1.7f, -15.6f, 3, 3, 3, 0.0f);
        this.Front_1 = new ModelRenderer((ModelBase)this, 50, 9);
        this.Front_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Front_1.func_78790_a(-2.0f, -2.25f, -12.5f, 4, 4, 3, 0.0f);
        this.Back_5 = new ModelRenderer((ModelBase)this, 28, 11);
        this.Back_5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back_5.func_78790_a(-3.7f, -0.7f, 9.7f, 2, 1, 9, 0.0f);
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base.func_78790_a(-3.0f, -3.1f, -7.2f, 6, 6, 12, 0.0f);
        this.Back = new ModelRenderer((ModelBase)this, 0, 18);
        this.Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back.func_78790_a(-2.5f, -2.7f, 4.1f, 5, 5, 9, 0.0f);
        this.Back_3 = new ModelRenderer((ModelBase)this, 25, 0);
        this.Back_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back_3.func_78790_a(-0.5f, 1.55f, 9.7f, 1, 2, 9, 0.0f);
        this.Back_4 = new ModelRenderer((ModelBase)this, 28, 11);
        this.Back_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back_4.func_78790_a(1.8f, -0.7f, 9.7f, 2, 1, 9, 0.0f);
        this.Back.func_78792_a(this.Back_1);
        this.Back_1.func_78792_a(this.Back_2);
        this.Base.func_78792_a(this.Front);
        this.Front_1.func_78792_a(this.Front_2);
        this.Front.func_78792_a(this.Front_1);
        this.Back_1.func_78792_a(this.Back_5);
        this.Base.func_78792_a(this.Back);
        this.Back_1.func_78792_a(this.Back_3);
        this.Back_1.func_78792_a(this.Back_4);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Base.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

