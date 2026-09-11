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

public class ModelKB
extends ModelBase {
    private int type = 0;
    public ModelRenderer KiBlade1;
    public ModelRenderer KiBlade2;
    public ModelRenderer KiBlade3;
    public ModelRenderer KiBlade4;
    public ModelRenderer BaseHandle4;
    public ModelRenderer HandleBump4;
    public ModelRenderer BottomSpike1;
    public ModelRenderer Handle1;
    public ModelRenderer EdgeBase;
    public ModelRenderer BottomSpike2;
    public ModelRenderer HandleBump1;
    public ModelRenderer Handle2;
    public ModelRenderer HandleBump2;
    public ModelRenderer Handle3;
    public ModelRenderer HandleBump3;
    public ModelRenderer TopSpike1;
    public ModelRenderer Edge1;
    public ModelRenderer TopSpike2;
    public ModelRenderer Edge2;
    public ModelRenderer Edge3;
    public ModelRenderer Edge11;
    public ModelRenderer Edge33;
    public ModelRenderer Edge22;
    public ModelRenderer Edge4;
    public ModelRenderer Edge5;
    public ModelRenderer Edge6;
    public ModelRenderer Edge7;

    public ModelKB() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.KiBlade4 = new ModelRenderer((ModelBase)this, 4, 22);
        this.KiBlade4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.KiBlade4.func_78790_a(-1.0f, 13.9f, -1.0f, 2, 2, 2, 0.0f);
        this.KiBlade2 = new ModelRenderer((ModelBase)this, 1, 9);
        this.KiBlade2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.KiBlade2.func_78790_a(-2.0f, 10.1f, -2.0f, 4, 2, 4, 0.0f);
        this.KiBlade1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.KiBlade1.func_78793_a(0.0f, 2.0f, 0.0f);
        this.KiBlade1.func_78790_a(-2.5f, 7.2f, -2.5f, 5, 3, 5, 0.0f);
        this.KiBlade3 = new ModelRenderer((ModelBase)this, 2, 16);
        this.KiBlade3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.KiBlade3.func_78790_a(-1.5f, 12.0f, -1.5f, 3, 2, 3, 0.0f);
        this.KiBlade3.func_78792_a(this.KiBlade4);
        this.KiBlade1.func_78792_a(this.KiBlade2);
        this.KiBlade2.func_78792_a(this.KiBlade3);
        this.HandleBump1 = new ModelRenderer((ModelBase)this, 15, 10);
        this.HandleBump1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HandleBump1.func_78790_a(-1.5f, -18.0f, -1.5f, 3, 1, 3, 0.0f);
        this.Edge3 = new ModelRenderer((ModelBase)this, 50, 14);
        this.Edge3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge3.func_78790_a(23.3f, -26.8f, -0.5f, 6, 6, 1, 0.0f);
        this.Edge11 = new ModelRenderer((ModelBase)this, 48, 30);
        this.Edge11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge11.func_78790_a(8.3f, -25.2f, -0.5f, 4, 1, 1, 0.0f);
        this.BaseHandle4 = new ModelRenderer((ModelBase)this, 1, 10);
        this.BaseHandle4.func_78793_a(0.0f, -4.1f, 0.0f);
        this.BaseHandle4.func_78790_a(-1.0f, -1.8f, -1.0f, 2, 20, 2, 0.0f);
        this.Handle2 = new ModelRenderer((ModelBase)this, 17, 0);
        this.Handle2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Handle2.func_78790_a(-1.0f, -17.1f, -1.0f, 2, 7, 2, 0.0f);
        this.HandleBump4 = new ModelRenderer((ModelBase)this, 15, 10);
        this.HandleBump4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HandleBump4.func_78790_a(-1.5f, 18.0f, -1.5f, 3, 1, 3, 0.0f);
        this.Edge7 = new ModelRenderer((ModelBase)this, 30, 24);
        this.Edge7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge7.func_78790_a(33.5f, -3.2f, -0.5f, 3, 7, 1, 0.0f);
        this.Handle1 = new ModelRenderer((ModelBase)this, 17, 0);
        this.Handle1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Handle1.func_78790_a(-1.0f, -24.9f, -1.0f, 2, 7, 2, 0.0f);
        this.EdgeBase = new ModelRenderer((ModelBase)this, 0, 0);
        this.EdgeBase.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EdgeBase.func_78790_a(-1.5f, -31.6f, -1.5f, 3, 7, 3, 0.0f);
        this.Edge2 = new ModelRenderer((ModelBase)this, 40, 7);
        this.Edge2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge2.func_78790_a(12.3f, -29.3f, -0.5f, 11, 6, 1, 0.0f);
        this.Edge4 = new ModelRenderer((ModelBase)this, 48, 21);
        this.Edge4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge4.func_78790_a(27.0f, -20.9f, -0.5f, 7, 4, 1, 0.0f);
        this.Edge5 = new ModelRenderer((ModelBase)this, 36, 16);
        this.Edge5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge5.func_78790_a(30.1f, -17.0f, -0.5f, 5, 7, 1, 0.0f);
        this.Edge6 = new ModelRenderer((ModelBase)this, 38, 24);
        this.Edge6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge6.func_78790_a(32.0f, -10.1f, -0.5f, 4, 7, 1, 0.0f);
        this.Edge33 = new ModelRenderer((ModelBase)this, 48, 26);
        this.Edge33.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge33.func_78790_a(29.3f, -23.8f, -0.5f, 3, 3, 1, 0.0f);
        this.BottomSpike1 = new ModelRenderer((ModelBase)this, 28, 12);
        this.BottomSpike1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BottomSpike1.func_78790_a(-1.0f, 18.9f, -1.0f, 2, 4, 2, 0.0f);
        this.TopSpike1 = new ModelRenderer((ModelBase)this, 28, 5);
        this.TopSpike1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.TopSpike1.func_78790_a(-1.0f, -35.5f, -1.0f, 2, 4, 2, 0.0f);
        this.Edge1 = new ModelRenderer((ModelBase)this, 40, 0);
        this.Edge1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge1.func_78790_a(1.4f, -31.0f, -0.5f, 11, 6, 1, 0.0f);
        this.BottomSpike2 = new ModelRenderer((ModelBase)this, 29, 19);
        this.BottomSpike2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BottomSpike2.func_78790_a(-0.5f, 22.7f, -0.5f, 1, 3, 1, 0.0f);
        this.Handle3 = new ModelRenderer((ModelBase)this, 17, 0);
        this.Handle3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Handle3.func_78790_a(-1.0f, -9.4f, -1.0f, 2, 7, 2, 0.0f);
        this.Edge22 = new ModelRenderer((ModelBase)this, 48, 30);
        this.Edge22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Edge22.func_78790_a(19.4f, -23.4f, -0.5f, 4, 1, 1, 0.0f);
        this.HandleBump2 = new ModelRenderer((ModelBase)this, 15, 10);
        this.HandleBump2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HandleBump2.func_78790_a(-1.5f, -10.3f, -1.5f, 3, 1, 3, 0.0f);
        this.HandleBump3 = new ModelRenderer((ModelBase)this, 15, 10);
        this.HandleBump3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HandleBump3.func_78790_a(-1.5f, -2.6f, -1.5f, 3, 1, 3, 0.0f);
        this.TopSpike2 = new ModelRenderer((ModelBase)this, 29, 0);
        this.TopSpike2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.TopSpike2.func_78790_a(-0.5f, -38.4f, -0.5f, 1, 3, 1, 0.0f);
        this.Handle1.func_78792_a(this.HandleBump1);
        this.Edge2.func_78792_a(this.Edge3);
        this.Edge2.func_78792_a(this.Edge11);
        this.Handle1.func_78792_a(this.Handle2);
        this.BaseHandle4.func_78792_a(this.HandleBump4);
        this.Edge6.func_78792_a(this.Edge7);
        this.BaseHandle4.func_78792_a(this.Handle1);
        this.BaseHandle4.func_78792_a(this.EdgeBase);
        this.Edge1.func_78792_a(this.Edge2);
        this.Edge33.func_78792_a(this.Edge4);
        this.Edge4.func_78792_a(this.Edge5);
        this.Edge5.func_78792_a(this.Edge6);
        this.Edge3.func_78792_a(this.Edge33);
        this.BaseHandle4.func_78792_a(this.BottomSpike1);
        this.EdgeBase.func_78792_a(this.TopSpike1);
        this.EdgeBase.func_78792_a(this.Edge1);
        this.BottomSpike1.func_78792_a(this.BottomSpike2);
        this.Handle2.func_78792_a(this.Handle3);
        this.Edge3.func_78792_a(this.Edge22);
        this.Handle2.func_78792_a(this.HandleBump2);
        this.Handle3.func_78792_a(this.HandleBump3);
        this.TopSpike1.func_78792_a(this.TopSpike2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (this.type == 0) {
            this.KiBlade1.func_78785_a(f5);
        } else if (this.type == 1) {
            this.BaseHandle4.func_78785_a(f5);
        }
    }

    public void render(float f5, int type) {
        if (type == 0) {
            this.KiBlade1.func_78785_a(f5);
        } else if (type == 1) {
            this.BaseHandle4.func_78785_a(f5);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

