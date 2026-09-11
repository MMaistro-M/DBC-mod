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

public class ModelNapapa
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Beard;
    public ModelRenderer Nose;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Hair;
    public ModelRenderer MuttonchopsR;
    public ModelRenderer MuttonchopsL;
    public ModelRenderer Body2;
    public ModelRenderer Chest;
    public ModelRenderer Body3;
    public ModelRenderer BeltKnot;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;

    public ModelNapapa() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, -7.8f, 0.0f);
        this.Body1.func_78790_a(-7.5f, 0.0f, -2.4f, 15, 7, 9, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 91, 43);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(4.3f, 10.0f, 1.0f);
        this.LegL.func_78790_a(-3.0f, 0.0f, -3.5f, 6, 14, 7, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 51, 3);
        this.Hair.func_78793_a(0.0f, -6.1f, 0.0f);
        this.Hair.func_78790_a(-1.5f, -3.6f, -5.2f, 3, 4, 11, 0.0f);
        this.setRotateAngle(this.Hair, -0.13665928f, 0.0f, 0.0f);
        this.MuttonchopsR = new ModelRenderer((ModelBase)this, 55, 8);
        this.MuttonchopsR.func_78793_a(-3.5f, -3.5f, -2.1f);
        this.MuttonchopsR.func_78790_a(-1.2f, -0.4f, 0.0f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.MuttonchopsR, 0.0f, 0.68294734f, 0.31869712f);
        this.EarR = new ModelRenderer((ModelBase)this, 55, 3);
        this.EarR.func_78793_a(-4.0f, -3.5f, -1.1f);
        this.EarR.func_78790_a(-1.4f, -3.0f, 0.0f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.68294734f, -0.31869712f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -7.9f, 0.6f);
        this.Head.func_78790_a(-4.0f, -7.2f, -4.0f, 8, 8, 8, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 33);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-8.0f, 7.0f, -4.1f, 16, 8, 11, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 91, 43);
        this.LegR.func_78793_a(-4.2f, 10.0f, 1.0f);
        this.LegR.func_78790_a(-3.0f, 0.0f, -3.5f, 6, 14, 7, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 90, 19);
        this.ArmR2.func_78793_a(-0.6f, 7.9f, -0.3f);
        this.ArmR2.func_78790_a(-3.5f, -0.5f, -2.5f, 6, 11, 6, 0.0f);
        this.setRotateAngle(this.ArmR2, -0.4098033f, 0.0f, -0.08726646f);
        this.Beard = new ModelRenderer((ModelBase)this, 44, 5);
        this.Beard.func_78793_a(0.0f, -0.7f, -4.1f);
        this.Beard.func_78790_a(-2.5f, 0.0f, 0.0f, 5, 4, 0, 0.0f);
        this.setRotateAngle(this.Beard, -0.0052359877f, 0.0f, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 92, 3);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(8.5f, -5.3f, 2.0f);
        this.ArmL1.func_78790_a(-1.2f, -2.0f, -2.5f, 4, 10, 5, 0.0f);
        this.setRotateAngle(this.ArmL1, 0.0f, 0.0f, -0.38397244f);
        this.BeltKnot = new ModelRenderer((ModelBase)this, 56, 48);
        this.BeltKnot.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BeltKnot.func_78790_a(-3.0f, 11.9f, 6.9f, 6, 3, 2, 0.0f);
        this.MuttonchopsL = new ModelRenderer((ModelBase)this, 55, 8);
        this.MuttonchopsL.field_78809_i = true;
        this.MuttonchopsL.func_78793_a(3.6f, -3.5f, -2.1f);
        this.MuttonchopsL.func_78790_a(-1.1f, -0.2f, 0.0f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.MuttonchopsL, 0.0f, -0.68294734f, -0.31869712f);
        this.EarL = new ModelRenderer((ModelBase)this, 55, 3);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(4.0f, -3.5f, -1.1f);
        this.EarL.func_78790_a(-0.5f, -3.0f, 0.0f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.68294734f, 0.31869712f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 52);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-7.5f, 14.8f, -3.2f, 15, 3, 9, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 92, 3);
        this.ArmR1.func_78793_a(-8.2f, -5.3f, 2.0f);
        this.ArmR1.func_78790_a(-3.0f, -2.0f, -2.5f, 4, 10, 5, 0.0f);
        this.setRotateAngle(this.ArmR1, 0.0f, 0.0f, 0.38397244f);
        this.Nose = new ModelRenderer((ModelBase)this, 46, 0);
        this.Nose.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Nose.func_78790_a(-1.0f, -4.1f, -4.6f, 2, 2, 2, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 90, 19);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.7f, 7.9f, -0.3f);
        this.ArmL2.func_78790_a(-2.8f, -0.5f, -2.5f, 6, 11, 6, 0.0f);
        this.setRotateAngle(this.ArmL2, -0.4098033f, 0.0f, 0.08726646f);
        this.Chest = new ModelRenderer((ModelBase)this, 50, 28);
        this.Chest.func_78793_a(0.0f, 3.7f, -1.7f);
        this.Chest.func_78790_a(-7.0f, -2.1f, -1.4f, 14, 5, 2, 0.0f);
        this.setRotateAngle(this.Chest, -0.091106184f, 0.0f, 0.0f);
        this.Head.func_78792_a(this.Hair);
        this.Head.func_78792_a(this.MuttonchopsR);
        this.Head.func_78792_a(this.EarR);
        this.Body1.func_78792_a(this.Body2);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.Head.func_78792_a(this.Beard);
        this.Body2.func_78792_a(this.BeltKnot);
        this.Head.func_78792_a(this.MuttonchopsL);
        this.Head.func_78792_a(this.EarL);
        this.Body2.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.Nose);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.Body1.func_78792_a(this.Chest);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
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
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
        this.LegR.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

