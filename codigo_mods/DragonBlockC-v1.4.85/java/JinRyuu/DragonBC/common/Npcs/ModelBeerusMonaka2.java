/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBeerusMonaka2
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer Neck;
    public ModelRenderer Body2;
    public ModelRenderer Cape1;
    public ModelRenderer CapeBack;
    public ModelRenderer CapeSideR;
    public ModelRenderer CapeSideL;
    public ModelRenderer CapeFront;
    public ModelRenderer LegR2;
    public ModelRenderer LegL2;

    public ModelBeerusMonaka2() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.LegL2 = new ModelRenderer((ModelBase)this, 0, 55);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, 10.0f, -2.0f, 4, 2, 4, 0.0f);
        this.earR = new ModelRenderer((ModelBase)this, 33, 4);
        this.earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earR.func_78790_a(-6.5f, -4.5f, -2.2f, 3, 2, 1, 0.0f);
        this.setRotateAngle(this.earR, 0.0f, 0.4098033f, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 18, 23);
        this.Body1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -1.8f, 8, 8, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 39);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.1f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-1.9f, -0.5f, -2.0f, 4, 11, 4, -0.4f);
        this.CapeSideR = new ModelRenderer((ModelBase)this, 47, 35);
        this.CapeSideR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeSideR.func_78790_a(-4.7f, 8.1f, -4.9f, 2, 4, 5, 0.0f);
        this.CapeSideL = new ModelRenderer((ModelBase)this, 47, 35);
        this.CapeSideL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeSideL.func_78790_a(2.7f, 8.2f, -4.9f, 2, 4, 5, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -0.6f, 0.0f);
        this.Head.func_78790_a(-4.1f, -7.7f, -4.0f, 8, 8, 8, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 2, 20);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.8f, 1.5f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -1.6f, -1.8f, 3, 12, 4, -0.2f);
        this.CapeFront = new ModelRenderer((ModelBase)this, 45, 28);
        this.CapeFront.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeFront.func_78790_a(-4.1f, 8.2f, -5.0f, 8, 5, 1, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 0, 55);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.0f, 10.0f, -2.0f, 4, 2, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 39);
        this.LegR.func_78793_a(-2.1f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.1f, -0.5f, -2.0f, 4, 11, 4, -0.4f);
        this.CapeBack = new ModelRenderer((ModelBase)this, 43, 18);
        this.CapeBack.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeBack.func_78790_a(-4.5f, 8.2f, -1.1f, 9, 8, 1, 0.0f);
        this.setRotateAngle(this.CapeBack, 0.09250245f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 2, 20);
        this.ArmR.func_78793_a(-4.8f, 1.5f, 0.0f);
        this.ArmR.func_78790_a(-2.0f, -1.6f, -1.8f, 3, 12, 4, -0.2f);
        this.earL = new ModelRenderer((ModelBase)this, 33, 4);
        this.earL.field_78809_i = true;
        this.earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earL.func_78790_a(3.3f, -4.5f, -2.2f, 3, 2, 1, 0.0f);
        this.setRotateAngle(this.earL, 0.0f, -0.4098033f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 17, 37);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-4.0f, 8.0f, -2.3f, 8, 4, 5, 0.0f);
        this.Cape1 = new ModelRenderer((ModelBase)this, 34, 8);
        this.Cape1.func_78793_a(0.0f, 0.3f, 2.3f);
        this.Cape1.func_78790_a(-4.5f, 5.3f, -5.2f, 9, 3, 6, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 23, 17);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -1.0f, -0.8f, 4, 2, 2, 0.0f);
        this.LegL.func_78792_a(this.LegL2);
        this.Head.func_78792_a(this.earR);
        this.Cape1.func_78792_a(this.CapeSideR);
        this.Cape1.func_78792_a(this.CapeSideL);
        this.Cape1.func_78792_a(this.CapeFront);
        this.LegR.func_78792_a(this.LegR2);
        this.Cape1.func_78792_a(this.CapeBack);
        this.Head.func_78792_a(this.earL);
        this.Body1.func_78792_a(this.Body2);
        this.Body1.func_78792_a(this.Cape1);
        this.Body1.func_78792_a(this.Neck);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        int calc = par7Entity.field_70173_aa;
        if (calc > 100) {
            calc -= 100;
        }
        float r = 360.0f;
        float r2 = 180.0f;
        float n4 = par4;
        float n5 = par5;
        this.Head.field_78796_g = n4 / (r2 / (float)Math.PI);
        this.Head.field_78795_f = n5 / (r2 / (float)Math.PI);
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        this.LegR.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

