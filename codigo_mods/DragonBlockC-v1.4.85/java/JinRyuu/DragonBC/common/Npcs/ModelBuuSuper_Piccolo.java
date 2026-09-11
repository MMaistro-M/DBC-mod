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

public class ModelBuuSuper_Piccolo
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Head2;
    public ModelRenderer Head3;
    public ModelRenderer Head4;
    public ModelRenderer Head5;
    public ModelRenderer Head6;
    public ModelRenderer Hips;
    public ModelRenderer Torso;
    public ModelRenderer Cape;
    public ModelRenderer CapeNeck;
    public ModelRenderer CapeBack;
    public ModelRenderer CapeR;
    public ModelRenderer CapeL;
    public ModelRenderer FootL;
    public ModelRenderer FootR;

    public ModelBuuSuper_Piccolo() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head5 = new ModelRenderer((ModelBase)this, 37, 10);
        this.Head5.func_78793_a(0.0f, -4.7f, -0.3f);
        this.Head5.func_78790_a(-0.5f, -6.0f, -0.6f, 1, 6, 2, 0.0f);
        this.setRotateAngle(this.Head5, -0.63739425f, 0.0f, 0.0f);
        this.Head6 = new ModelRenderer((ModelBase)this, 44, 11);
        this.Head6.func_78793_a(0.0f, -5.6f, 0.3f);
        this.Head6.func_78790_a(-0.5f, -6.0f, -0.6f, 1, 6, 1, 0.0f);
        this.setRotateAngle(this.Head6, -0.46251225f, 0.0f, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 46, 30);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.3f, -2.0f, 0.3f);
        this.ArmL.func_78790_a(-1.2f, -1.3f, -1.8f, 4, 12, 4, -0.2f);
        this.Body = new ModelRenderer((ModelBase)this, 22, 29);
        this.Body.func_78793_a(0.0f, -3.2f, 0.0f);
        this.Body.func_78790_a(-3.5f, 3.0f, -1.9f, 7, 7, 4, 0.0f);
        this.Head4 = new ModelRenderer((ModelBase)this, 49, 1);
        this.Head4.func_78793_a(0.0f, -5.5f, 0.1f);
        this.Head4.func_78790_a(-1.0f, -5.1f, -0.8f, 2, 5, 2, 0.0f);
        this.setRotateAngle(this.Head4, -0.7285004f, 0.0f, 0.0f);
        this.Cape = new ModelRenderer((ModelBase)this, 75, 14);
        this.Cape.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Cape.func_78790_a(-4.0f, 0.0f, -2.4f, 8, 4, 2, 0.0f);
        this.CapeL = new ModelRenderer((ModelBase)this, 50, 18);
        this.CapeL.field_78809_i = true;
        this.CapeL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeL.func_78790_a(-1.9f, -1.4f, -2.3f, 7, 3, 5, 0.0f);
        this.CapeBack = new ModelRenderer((ModelBase)this, 75, 22);
        this.CapeBack.func_78793_a(0.0f, 1.1f, 2.8f);
        this.CapeBack.func_78790_a(-5.5f, -0.3f, 0.2f, 11, 19, 0, 0.0f);
        this.setRotateAngle(this.CapeBack, 0.0418879f, 0.0f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 28);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 9.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 2.0f, -2.3f, 4, 7, 5, 0.3f);
        this.FootR = new ModelRenderer((ModelBase)this, 0, 43);
        this.FootR.field_78809_i = true;
        this.FootR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FootR.func_78790_a(-2.0f, 0.0f, -2.3f, 4, 15, 5, 0.0f);
        this.FootL = new ModelRenderer((ModelBase)this, 0, 43);
        this.FootL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FootL.func_78790_a(-2.0f, 0.0f, -2.3f, 4, 15, 5, 0.0f);
        this.Torso = new ModelRenderer((ModelBase)this, 21, 19);
        this.Torso.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Torso.func_78790_a(-4.5f, 0.6f, -1.2f, 9, 5, 4, 0.0f);
        this.CapeNeck = new ModelRenderer((ModelBase)this, 71, 4);
        this.CapeNeck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeNeck.func_78790_a(-3.5f, -0.7f, -3.1f, 7, 2, 7, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 25, 0);
        this.Head2.func_78793_a(0.0f, -6.5f, -2.3f);
        this.Head2.func_78790_a(-1.5f, -4.2f, -1.0f, 3, 4, 3, 0.0f);
        this.setRotateAngle(this.Head2, -0.49392816f, 0.0f, 0.0f);
        this.Head3 = new ModelRenderer((ModelBase)this, 38, 1);
        this.Head3.func_78793_a(0.0f, -3.8f, -0.6f);
        this.Head3.func_78790_a(-1.5f, -6.1f, -0.5f, 3, 6, 2, 0.0f);
        this.setRotateAngle(this.Head3, -0.68294734f, 0.0f, 0.0f);
        this.Hips = new ModelRenderer((ModelBase)this, 19, 41);
        this.Hips.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hips.func_78790_a(-4.0f, 9.2f, -2.3f, 8, 3, 5, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -3.2f, 0.4f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.7f);
        this.ArmR = new ModelRenderer((ModelBase)this, 46, 30);
        this.ArmR.func_78793_a(-5.3f, -2.0f, 0.3f);
        this.ArmR.func_78790_a(-3.0f, -1.3f, -1.8f, 4, 12, 4, -0.1f);
        this.CapeR = new ModelRenderer((ModelBase)this, 50, 18);
        this.CapeR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.CapeR.func_78790_a(-5.1f, -1.4f, -2.3f, 7, 3, 5, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 28);
        this.LegR.func_78793_a(-2.0f, 9.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 2.0f, -2.3f, 4, 7, 5, 0.3f);
        this.Head4.func_78792_a(this.Head5);
        this.Head5.func_78792_a(this.Head6);
        this.Head3.func_78792_a(this.Head4);
        this.Body.func_78792_a(this.Cape);
        this.ArmL.func_78792_a(this.CapeL);
        this.Cape.func_78792_a(this.CapeBack);
        this.LegR.func_78792_a(this.FootR);
        this.LegL.func_78792_a(this.FootL);
        this.Body.func_78792_a(this.Torso);
        this.Body.func_78792_a(this.CapeNeck);
        this.Head.func_78792_a(this.Head2);
        this.Head2.func_78792_a(this.Head3);
        this.Body.func_78792_a(this.Hips);
        this.ArmR.func_78792_a(this.CapeR);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body.func_78785_a(f5);
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
        this.CapeBack.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (0.0f > this.CapeBack.field_78795_f) {
            this.CapeBack.field_78795_f *= -1.0f;
        }
        this.CapeBack.field_78796_g = 0.0f;
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

