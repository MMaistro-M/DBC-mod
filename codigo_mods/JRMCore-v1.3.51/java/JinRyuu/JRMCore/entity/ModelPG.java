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

public class ModelPG
extends ModelBase {
    private int id;
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape_1_1;
    public ModelRenderer shape_1_2;
    public ModelRenderer shape_1_3;
    public ModelRenderer shape_1_4;
    public ModelRenderer shape_1_5;
    public ModelRenderer shape_2_1;
    public ModelRenderer shape_2_2;
    public ModelRenderer shape_2_3;
    public ModelRenderer shape_2_4;
    public ModelRenderer shape_2_5;
    public ModelRenderer shape_3_1;
    public ModelRenderer shape_3_2;
    public ModelRenderer shape_3_3;
    public ModelRenderer shape_3_4;
    public ModelRenderer shape_3_5;
    public ModelRenderer shape_4_1;
    public ModelRenderer shape_4_2;
    public ModelRenderer shape_4_3;
    public ModelRenderer shape_4_4;
    public ModelRenderer shape_4_5;

    public ModelPG(int id) {
        this.id = id;
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        if (id == 0) {
            this.shape3 = new ModelRenderer((ModelBase)this, 26, 0);
            this.shape3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape3.func_78790_a(-0.8f, -7.8f, -1.0f, 2, 2, 2, 0.0f);
            this.shape1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.shape1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape1.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 5, 2, 0.0f);
            this.shape4 = new ModelRenderer((ModelBase)this, 41, 0);
            this.shape4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape4.func_78790_a(-2.4f, 3.8f, -1.0f, 2, 2, 2, 0.0f);
            this.shape2 = new ModelRenderer((ModelBase)this, 13, 0);
            this.shape2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape2.func_78790_a(0.4f, -5.9f, -1.0f, 2, 5, 2, 0.0f);
        } else if (id == 1) {
            this.shape_1_4 = new ModelRenderer((ModelBase)this, 36, 0);
            this.shape_1_4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_1_4.func_78790_a(0.1f, 3.8f, -1.0f, 2, 2, 2, 0.0f);
            this.shape_1_1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.shape_1_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_1_1.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 5, 2, 0.0f);
            this.shape_1_3 = new ModelRenderer((ModelBase)this, 24, 0);
            this.shape_1_3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_1_3.func_78790_a(-0.8f, -6.3f, -1.0f, 2, 2, 2, 0.0f);
            this.shape_1_5 = new ModelRenderer((ModelBase)this, 49, 0);
            this.shape_1_5.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_1_5.func_78790_a(1.6f, 5.8f, -1.0f, 2, 2, 2, 0.0f);
            this.shape_1_2 = new ModelRenderer((ModelBase)this, 12, 0);
            this.shape_1_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_1_2.func_78790_a(0.4f, -4.6f, -1.0f, 2, 4, 2, 0.0f);
        } else if (id == 2) {
            this.shape_2_5 = new ModelRenderer((ModelBase)this, 39, 0);
            this.shape_2_5.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_2_5.func_78790_a(1.3f, 5.4f, 1.4f, 2, 2, 3, 0.0f);
            this.shape_2_1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.shape_2_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_2_1.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 5, 2, 0.0f);
            this.shape_2_3 = new ModelRenderer((ModelBase)this, 19, 0);
            this.shape_2_3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_2_3.func_78790_a(-0.8f, -6.3f, -3.6f, 2, 2, 2, 0.0f);
            this.shape_2_2 = new ModelRenderer((ModelBase)this, 9, 0);
            this.shape_2_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_2_2.func_78790_a(0.4f, -4.6f, -2.1f, 2, 4, 2, 0.0f);
            this.shape_2_4 = new ModelRenderer((ModelBase)this, 29, 0);
            this.shape_2_4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_2_4.func_78790_a(0.0f, 3.7f, 0.2f, 2, 2, 2, 0.0f);
        } else if (id == 3) {
            this.shape_3_1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.shape_3_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_3_1.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 5, 2, 0.0f);
            this.shape_3_5 = new ModelRenderer((ModelBase)this, 39, 0);
            this.shape_3_5.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_3_5.func_78790_a(1.0f, 6.3f, 1.3f, 2, 4, 2, 0.0f);
            this.shape_3_3 = new ModelRenderer((ModelBase)this, 19, 0);
            this.shape_3_3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_3_3.func_78790_a(-0.8f, -5.3f, -4.1f, 2, 4, 2, 0.0f);
            this.shape_3_4 = new ModelRenderer((ModelBase)this, 29, 0);
            this.shape_3_4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_3_4.func_78790_a(-0.1f, 3.5f, 0.1f, 2, 3, 2, 0.0f);
            this.shape_3_2 = new ModelRenderer((ModelBase)this, 9, 0);
            this.shape_3_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_3_2.func_78790_a(0.4f, -2.2f, -2.8f, 2, 2, 3, 0.0f);
        } else if (id == 4) {
            this.shape_4_3 = new ModelRenderer((ModelBase)this, 25, 0);
            this.shape_4_3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_4_3.func_78790_a(-0.8f, -3.5f, -4.1f, 2, 2, 2, 0.0f);
            this.shape_4_1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.shape_4_1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_4_1.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 4, 2, 0.0f);
            this.shape_4_2 = new ModelRenderer((ModelBase)this, 12, 0);
            this.shape_4_2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_4_2.func_78790_a(0.4f, -2.2f, -2.8f, 2, 2, 3, 0.0f);
            this.shape_4_5 = new ModelRenderer((ModelBase)this, 52, 0);
            this.shape_4_5.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_4_5.func_78790_a(1.0f, 3.9f, 2.4f, 2, 2, 2, 0.0f);
            this.shape_4_4 = new ModelRenderer((ModelBase)this, 37, 0);
            this.shape_4_4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.shape_4_4.func_78790_a(-0.1f, 2.3f, 0.1f, 2, 2, 3, 0.0f);
        }
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (this.id == 0) {
            this.shape1.func_78785_a(f5);
            this.shape2.func_78785_a(f5);
            this.shape3.func_78785_a(f5);
            this.shape4.func_78785_a(f5);
        }
        if (this.id == 1) {
            this.shape_1_1.func_78785_a(f5);
            this.shape_1_2.func_78785_a(f5);
            this.shape_1_3.func_78785_a(f5);
            this.shape_1_4.func_78785_a(f5);
            this.shape_1_5.func_78785_a(f5);
        }
        if (this.id == 2) {
            this.shape_2_1.func_78785_a(f5);
            this.shape_2_2.func_78785_a(f5);
            this.shape_2_3.func_78785_a(f5);
            this.shape_2_4.func_78785_a(f5);
            this.shape_2_5.func_78785_a(f5);
        }
        if (this.id == 3) {
            this.shape_3_1.func_78785_a(f5);
            this.shape_3_2.func_78785_a(f5);
            this.shape_3_3.func_78785_a(f5);
            this.shape_3_4.func_78785_a(f5);
            this.shape_3_5.func_78785_a(f5);
        }
        if (this.id == 4) {
            this.shape_4_1.func_78785_a(f5);
            this.shape_4_2.func_78785_a(f5);
            this.shape_4_3.func_78785_a(f5);
            this.shape_4_4.func_78785_a(f5);
            this.shape_4_5.func_78785_a(f5);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

