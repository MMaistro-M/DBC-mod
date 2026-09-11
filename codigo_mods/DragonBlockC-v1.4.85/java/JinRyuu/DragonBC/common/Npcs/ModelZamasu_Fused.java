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

public class ModelZamasu_Fused
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer HairBase;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer HairR;
    public ModelRenderer HairL;
    public ModelRenderer HairB1;
    public ModelRenderer HairF1;
    public ModelRenderer HairM1;
    public ModelRenderer HairB2;
    public ModelRenderer HairB3;
    public ModelRenderer HairB4;
    public ModelRenderer HairF2;
    public ModelRenderer HairF3;
    public ModelRenderer HairF4;
    public ModelRenderer HairF6;
    public ModelRenderer HairF5;
    public ModelRenderer HairM2;
    public ModelRenderer earR2;
    public ModelRenderer earL2;
    public ModelRenderer Body2;
    public ModelRenderer BarrierofLight;
    public ModelRenderer Body3;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;

    public ModelZamasu_Fused() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer((ModelBase)this, 20, 23);
        this.Body1.func_78793_a(0.0f, -1.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -1.8f, 8, 7, 4, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 3, 18);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(-1.0f, -0.9f, -1.8f, 6, 4, 4, 0.0f);
        this.HairB3 = new ModelRenderer((ModelBase)this, 45, 26);
        this.HairB3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairB3.func_78790_a(-2.8f, -7.0f, -0.1f, 5, 3, 3, 0.0f);
        this.setRotateAngle(this.HairB3, 0.0f, 0.0f, 0.59184116f);
        this.HairM2 = new ModelRenderer((ModelBase)this, 36, 13);
        this.HairM2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairM2.func_78790_a(-3.3f, -12.8f, -3.3f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.HairM2, 0.0f, 0.0f, 0.040142573f);
        this.HairF1 = new ModelRenderer((ModelBase)this, 48, 33);
        this.HairF1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairF1.func_78790_a(-3.3f, -9.8f, -5.4f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.HairF1, 0.0f, 0.0f, -0.13665928f);
        this.HairL = new ModelRenderer((ModelBase)this, 47, 13);
        this.HairL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairL.func_78790_a(2.0f, -11.5f, -3.9f, 2, 7, 5, 0.0f);
        this.setRotateAngle(this.HairL, 0.0f, 0.045378562f, 0.07853982f);
        this.BarrierofLight = new ModelRenderer((ModelBase)this, 62, 9);
        this.BarrierofLight.func_78793_a(0.0f, 0.0f, 7.8f);
        this.BarrierofLight.func_78790_a(-16.5f, -22.6f, 0.0f, 33, 42, 0, 0.0f);
        this.setRotateAngle(this.BarrierofLight, -0.06981317f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -1.2f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.3f, -4.0f, 8, 8, 8, -0.5f);
        this.earR = new ModelRenderer((ModelBase)this, 0, 1);
        this.earR.func_78793_a(-3.2f, -1.9f, -1.5f);
        this.earR.func_78790_a(-4.2f, -2.1f, 0.0f, 4, 3, 0, 0.0f);
        this.setRotateAngle(this.earR, 0.1134464f, 0.5235988f, 0.34906584f);
        this.HairF6 = new ModelRenderer((ModelBase)this, 50, 46);
        this.HairF6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairF6.func_78790_a(1.5f, -7.3f, -6.1f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.HairF6, 0.14137167f, 0.0f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 45);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.1f, 11.0f, 0.0f);
        this.LegL.func_78790_a(-2.1f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.HairF2 = new ModelRenderer((ModelBase)this, 48, 33);
        this.HairF2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairF2.func_78790_a(1.0f, -8.7f, -5.5f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.HairF2, 0.0f, 0.0f, 0.22759093f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 3, 18);
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-5.0f, -0.9f, -1.8f, 6, 4, 4, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 20, 43);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 0, 27);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, -0.1f, 0.0f);
        this.ArmL.func_78790_a(-1.2f, -0.8f, -1.8f, 3, 12, 4, -0.1f);
        this.earL2 = new ModelRenderer((ModelBase)this, 30, 1);
        this.earL2.field_78809_i = true;
        this.earL2.func_78793_a(0.3f, 1.2f, 0.0f);
        this.earL2.func_78790_a(-0.5f, -0.4f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.earL2, 0.0f, 0.34906584f, 0.34906584f);
        this.HairR = new ModelRenderer((ModelBase)this, 47, 13);
        this.HairR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairR.func_78790_a(-3.9f, -11.4f, -3.3f, 2, 7, 5, 0.0f);
        this.setRotateAngle(this.HairR, 0.0f, -0.045378562f, -0.08028515f);
        this.HairF5 = new ModelRenderer((ModelBase)this, 50, 46);
        this.HairF5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairF5.func_78790_a(-4.0f, -7.1f, -6.1f, 2, 5, 1, 0.0f);
        this.setRotateAngle(this.HairF5, 0.27314404f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 0, 27);
        this.ArmR.func_78793_a(-5.0f, -0.1f, 0.0f);
        this.ArmR.func_78790_a(-1.9f, -0.8f, -1.8f, 3, 12, 4, -0.1f);
        this.earR2 = new ModelRenderer((ModelBase)this, 30, 1);
        this.earR2.func_78793_a(-0.5f, 1.2f, 0.0f);
        this.earR2.func_78790_a(-0.4f, -0.4f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.earR2, 0.0f, -0.34906584f, -0.34906584f);
        this.HairF4 = new ModelRenderer((ModelBase)this, 49, 41);
        this.HairF4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairF4.func_78790_a(-3.3f, -6.2f, -6.9f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.HairF4, -0.17453292f, 0.0f, 0.31869712f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 45);
        this.LegR.func_78793_a(-2.1f, 11.0f, 0.0f);
        this.LegR.func_78790_a(-1.9f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 23, 36);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.5f, 7.0f, -1.7f, 7, 2, 3, 0.0f);
        this.HairB1 = new ModelRenderer((ModelBase)this, 36, 13);
        this.HairB1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairB1.func_78790_a(-2.8f, -12.3f, -0.3f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.HairB1, 0.0f, 0.0f, -0.13665928f);
        this.HairBase = new ModelRenderer((ModelBase)this, 33, 0);
        this.HairBase.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairBase.func_78790_a(-2.9f, -11.6f, -4.0f, 6, 6, 7, 0.0f);
        this.setRotateAngle(this.HairBase, -0.18203785f, 0.0f, 0.0f);
        this.HairB2 = new ModelRenderer((ModelBase)this, 36, 13);
        this.HairB2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairB2.func_78790_a(1.5f, -12.2f, -0.4f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.HairB2, 0.0f, 0.0f, 0.2268928f);
        this.HairB4 = new ModelRenderer((ModelBase)this, 45, 26);
        this.HairB4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairB4.func_78790_a(-1.9f, -7.3f, -0.1f, 5, 3, 3, 0.0f);
        this.setRotateAngle(this.HairB4, 0.0f, 0.0f, -0.3577925f);
        this.HairM1 = new ModelRenderer((ModelBase)this, 49, 53);
        this.HairM1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairM1.func_78790_a(-0.6f, -14.1f, -2.4f, 3, 3, 4, 0.0f);
        this.earL = new ModelRenderer((ModelBase)this, 0, 4);
        this.earL.func_78793_a(3.2f, -1.9f, -1.5f);
        this.earL.func_78790_a(0.2f, -2.1f, 0.0f, 4, 3, 0, 0.0f);
        this.setRotateAngle(this.earL, 0.1134464f, -0.5235988f, -0.34906584f);
        this.HairF3 = new ModelRenderer((ModelBase)this, 49, 41);
        this.HairF3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairF3.func_78790_a(0.8f, -7.3f, -6.2f, 2, 2, 2, 0.0f);
        this.ArmL.func_78792_a(this.ShoulderL);
        this.HairB1.func_78792_a(this.HairB3);
        this.HairM1.func_78792_a(this.HairM2);
        this.HairBase.func_78792_a(this.HairF1);
        this.HairBase.func_78792_a(this.HairL);
        this.Body1.func_78792_a(this.BarrierofLight);
        this.Head.func_78792_a(this.earR);
        this.HairF3.func_78792_a(this.HairF6);
        this.HairF1.func_78792_a(this.HairF2);
        this.ArmR.func_78792_a(this.ShoulderR);
        this.Body2.func_78792_a(this.Body3);
        this.earL.func_78792_a(this.earL2);
        this.HairBase.func_78792_a(this.HairR);
        this.HairF4.func_78792_a(this.HairF5);
        this.earR.func_78792_a(this.earR2);
        this.HairF1.func_78792_a(this.HairF4);
        this.Body1.func_78792_a(this.Body2);
        this.HairBase.func_78792_a(this.HairB1);
        this.Head.func_78792_a(this.HairBase);
        this.HairB1.func_78792_a(this.HairB2);
        this.HairB1.func_78792_a(this.HairB4);
        this.HairBase.func_78792_a(this.HairM1);
        this.Head.func_78792_a(this.earL);
        this.HairF1.func_78792_a(this.HairF3);
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

