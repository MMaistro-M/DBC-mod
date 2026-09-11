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

public class ModelAngelCukatail
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Neck;
    public ModelRenderer Body2;
    public ModelRenderer NeckRing;
    public ModelRenderer ClothF;
    public ModelRenderer ClothB;
    public ModelRenderer Body3;
    public ModelRenderer NeckRing_1;
    public ModelRenderer NeckRing_2;
    public ModelRenderer NeckRing_3;
    public ModelRenderer NeckRing_4;
    public ModelRenderer NeckRing_5;
    public ModelRenderer NeckRing_6;
    public ModelRenderer NeckRing_7;
    public ModelRenderer ClothF2;
    public ModelRenderer ClothB2;
    public ModelRenderer LegR2;
    public ModelRenderer LegL2;
    public ModelRenderer ShoulderArmorR;
    public ModelRenderer ShoulderArmorL;

    public ModelAngelCukatail() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.NeckRing_2 = new ModelRenderer((ModelBase)this, 19, 54);
        this.NeckRing_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_2.func_78790_a(-8.7f, -1.5f, -3.9f, 2, 1, 9, 0.0f);
        this.setRotateAngle(this.NeckRing_2, 0.0f, -0.82833326f, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 17, 18);
        this.Body1.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Body1.func_78790_a(-4.5f, 0.1f, -2.2f, 9, 7, 5, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 48, 16);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.7f, -1.2f, 0.0f);
        this.ArmL.func_78790_a(-1.2f, -1.5f, -1.8f, 4, 13, 4, -0.1f);
        this.NeckRing_4 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_4.func_78790_a(-8.5f, -1.5f, -3.3f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_4, 0.0f, -0.7382743f, 0.0f);
        this.NeckRing = new ModelRenderer((ModelBase)this, 0, 53);
        this.NeckRing.func_78793_a(0.0f, -0.3f, 0.4f);
        this.NeckRing.func_78790_a(-3.5f, -1.5f, 7.9f, 7, 1, 2, 0.0f);
        this.setRotateAngle(this.NeckRing, 0.33004177f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 5, 17);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.5f, -0.9f, -0.8f, 5, 2, 3, 0.0f);
        this.NeckRing_7 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_7.func_78790_a(-9.6f, -1.5f, -4.2f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_7, 0.0f, -0.8609709f, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegR.func_78793_a(-2.1f, 9.0f, 0.0f);
        this.LegR.func_78790_a(-2.8f, 0.0f, -2.0f, 5, 15, 4, 0.0f);
        this.ShoulderArmorR = new ModelRenderer((ModelBase)this, 31, 9);
        this.ShoulderArmorR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderArmorR.func_78790_a(-3.8f, -1.7f, -2.2f, 5, 4, 5, 0.0f);
        this.NeckRing_1 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_1.func_78790_a(-9.7f, -1.5f, -2.9f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_1, 0.0f, 0.8342674f, 0.0f);
        this.ClothF = new ModelRenderer((ModelBase)this, 47, 46);
        this.ClothF.func_78793_a(0.0f, 6.9f, -2.0f);
        this.ClothF.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 5, 0, 0.0f);
        this.setRotateAngle(this.ClothF, -0.04363323f, 0.0f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 9.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 5, 15, 4, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 19, 38);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.5f, 9.0f, -2.0f, 9, 3, 4, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 30, 0);
        this.Hair2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair2.func_78790_a(-2.0f, -7.5f, 5.3f, 4, 3, 4, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 30, 0);
        this.Hair1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair1.func_78790_a(-3.0f, -7.3f, 2.6f, 6, 4, 4, 0.0f);
        this.NeckRing_6 = new ModelRenderer((ModelBase)this, 19, 54);
        this.NeckRing_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_6.func_78790_a(-8.3f, -1.5f, -5.5f, 2, 1, 9, 0.0f);
        this.setRotateAngle(this.NeckRing_6, 0.0f, -0.7696902f, 0.0f);
        this.NeckRing_5 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_5.func_78790_a(-8.3f, -1.5f, -3.3f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_5, 0.0f, -0.7841764f, 0.0f);
        this.ClothB2 = new ModelRenderer((ModelBase)this, 33, 46);
        this.ClothB2.func_78793_a(0.0f, 5.0f, 0.0f);
        this.ClothB2.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 11, 0, 0.0f);
        this.setRotateAngle(this.ClothB2, -0.006981317f, 0.0f, 0.0f);
        this.NeckRing_3 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_3.func_78790_a(-8.7f, -1.5f, -3.2f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_3, 0.0f, -0.8290314f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 20, 31);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-4.0f, 7.1f, -1.9f, 8, 2, 4, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 18, 47);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, 13.1f, -4.0f, 5, 2, 2, 0.0f);
        this.ClothB = new ModelRenderer((ModelBase)this, 47, 46);
        this.ClothB.func_78793_a(0.0f, 7.0f, 2.8f);
        this.ClothB.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 5, 0, 0.0f);
        this.setRotateAngle(this.ClothB, 0.054105207f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -3.9f, 0.0f);
        this.Head.func_78790_a(-3.5f, -7.5f, -4.0f, 7, 8, 8, -0.4f);
        this.ClothF2 = new ModelRenderer((ModelBase)this, 33, 46);
        this.ClothF2.func_78793_a(0.0f, 4.9f, 0.0f);
        this.ClothF2.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 11, 0, 0.0f);
        this.setRotateAngle(this.ClothF2, -0.010471975f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 48, 16);
        this.ArmR.func_78793_a(-5.7f, -1.2f, 0.0f);
        this.ArmR.func_78790_a(-2.8f, -1.5f, -1.8f, 4, 13, 4, -0.1f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 51, 0);
        this.Hair3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair3.func_78790_a(-1.0f, -6.5f, 8.5f, 2, 3, 2, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 18, 47);
        this.LegR2.field_78809_i = true;
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.8f, 13.1f, -4.0f, 5, 2, 2, 0.0f);
        this.ShoulderArmorL = new ModelRenderer((ModelBase)this, 31, 9);
        this.ShoulderArmorL.field_78809_i = true;
        this.ShoulderArmorL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderArmorL.func_78790_a(-1.2f, -1.7f, -2.2f, 5, 4, 5, 0.0f);
        this.NeckRing_1.func_78792_a(this.NeckRing_2);
        this.NeckRing_3.func_78792_a(this.NeckRing_4);
        this.Body1.func_78792_a(this.NeckRing);
        this.Body1.func_78792_a(this.Neck);
        this.NeckRing_6.func_78792_a(this.NeckRing_7);
        this.ArmR.func_78792_a(this.ShoulderArmorR);
        this.NeckRing.func_78792_a(this.NeckRing_1);
        this.Body1.func_78792_a(this.ClothF);
        this.Body2.func_78792_a(this.Body3);
        this.Hair1.func_78792_a(this.Hair2);
        this.Head.func_78792_a(this.Hair1);
        this.NeckRing_5.func_78792_a(this.NeckRing_6);
        this.NeckRing_4.func_78792_a(this.NeckRing_5);
        this.ClothB.func_78792_a(this.ClothB2);
        this.NeckRing_2.func_78792_a(this.NeckRing_3);
        this.Body1.func_78792_a(this.Body2);
        this.LegL.func_78792_a(this.LegL2);
        this.Body1.func_78792_a(this.ClothB);
        this.ClothF.func_78792_a(this.ClothF2);
        this.Hair2.func_78792_a(this.Hair3);
        this.LegR.func_78792_a(this.LegR2);
        this.ArmL.func_78792_a(this.ShoulderArmorL);
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
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
        this.LegR.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
        this.ClothF.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.ClothF2.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.ClothB.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.ClothB2.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

