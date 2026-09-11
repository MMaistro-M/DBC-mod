/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.JRMCore.client.JGRenderHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelAngelKusu
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
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer Neck;
    public ModelRenderer Body2;
    public ModelRenderer NeckRing;
    public ModelRenderer Boobs;
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
    public ModelRenderer LegR2;
    public ModelRenderer LegR3;
    public ModelRenderer LegL2;
    public ModelRenderer LegL4;

    public ModelAngelKusu() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.NeckRing_3 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_3.func_78790_a(-8.7f, -1.3f, -3.2f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_3, 0.0f, -0.8290314f, 0.0f);
        this.NeckRing_5 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_5.func_78790_a(-8.3f, -1.3f, -3.3f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_5, 0.0f, -0.7841764f, 0.0f);
        this.NeckRing_1 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_1.func_78790_a(-9.7f, -1.3f, -2.9f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_1, 0.0f, 0.8342674f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 7, 16);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -1.3f, -0.8f, 4, 2, 2, 0.0f);
        this.ClothF = new ModelRenderer((ModelBase)this, 44, 52);
        this.ClothF.func_78793_a(0.0f, 9.0f, -2.1f);
        this.ClothF.func_78790_a(-2.5f, 0.0f, 0.1f, 5, 11, 0, 0.0f);
        this.setRotateAngle(this.ClothF, -0.11519173f, 0.0f, 0.0f);
        this.NeckRing_4 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_4.func_78790_a(-8.5f, -1.3f, -3.3f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_4, 0.0f, -0.7382743f, 0.0f);
        this.NeckRing = new ModelRenderer((ModelBase)this, 0, 53);
        this.NeckRing.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing.func_78790_a(-3.5f, -1.3f, 7.9f, 7, 1, 2, 0.0f);
        this.setRotateAngle(this.NeckRing, 0.59184116f, 0.0f, 0.0f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 49, 16);
        this.Hair5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair5.func_78790_a(-4.9f, 1.2f, -3.1f, 2, 3, 2, 0.0f);
        this.NeckRing_7 = new ModelRenderer((ModelBase)this, 0, 56);
        this.NeckRing_7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_7.func_78790_a(-9.6f, -1.3f, -4.2f, 2, 1, 7, 0.0f);
        this.setRotateAngle(this.NeckRing_7, 0.0f, -0.8609709f, 0.0f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 49, 11);
        this.Hair4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair4.func_78790_a(-5.7f, -0.1f, -2.1f, 2, 2, 2, 0.0f);
        this.LegR3 = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR3.func_78790_a(-1.6f, 0.0f, -2.0f, 3, 15, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -4.0f, 0.0f);
        this.Head.func_78790_a(-3.5f, -7.8f, -4.0f, 7, 8, 8, -0.1f);
        this.Boobs = new ModelRenderer((ModelBase)this, 35, 28);
        this.Boobs.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Boobs.func_78790_a(-3.5f, 0.9f, -1.0f, 7, 3, 3, 0.0f);
        this.setRotateAngle(this.Boobs, -0.59184116f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 16, 29);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.0f, 6.0f, -1.5f, 6, 3, 3, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 17, 47);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-1.4f, 13.0f, -4.0f, 3, 2, 2, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 40, 35);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, -1.2f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -1.5f, -1.4f, 3, 13, 3, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 17, 47);
        this.LegR2.field_78809_i = true;
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-1.6f, 13.0f, -4.0f, 3, 2, 2, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 40, 35);
        this.ArmR.func_78793_a(-5.0f, -1.2f, 0.0f);
        this.ArmR.func_78790_a(-2.0f, -1.5f, -1.3f, 3, 13, 3, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 15, 36);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, 0.0f);
        this.NeckRing_2 = new ModelRenderer((ModelBase)this, 19, 54);
        this.NeckRing_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_2.func_78790_a(-8.7f, -1.3f, -3.9f, 2, 1, 9, 0.0f);
        this.setRotateAngle(this.NeckRing_2, 0.0f, -0.82833326f, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 16, 17);
        this.Body1.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -1.8f, 8, 6, 4, 0.0f);
        this.ClothB = new ModelRenderer((ModelBase)this, 44, 52);
        this.ClothB.func_78793_a(0.0f, 9.0f, 2.0f);
        this.ClothB.func_78790_a(-2.5f, 0.0f, 0.0f, 5, 11, 0, 0.0f);
        this.setRotateAngle(this.ClothB, 0.13264503f, 0.0f, 0.0f);
        this.LegL4 = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegL4.field_78809_i = true;
        this.LegL4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL4.func_78790_a(-1.4f, 0.0f, -2.0f, 3, 15, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 20);
        this.LegR.func_78793_a(-1.9f, 9.0f, 0.0f);
        this.LegR.func_78790_a(-2.3f, 0.0f, -2.0f, 4, 9, 4, 0.3f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 25, 1);
        this.Hair1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair1.func_78790_a(-5.1f, -5.4f, -0.1f, 2, 2, 2, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 34, 1);
        this.Hair2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair2.func_78790_a(-5.6f, -3.8f, -0.5f, 3, 3, 3, 0.0f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 47, 3);
        this.Hair3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair3.func_78790_a(-6.2f, -2.3f, -1.1f, 3, 3, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 20);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 9.0f, 0.0f);
        this.LegL.func_78790_a(-1.7f, 0.0f, -2.0f, 4, 9, 4, 0.3f);
        this.NeckRing_6 = new ModelRenderer((ModelBase)this, 19, 54);
        this.NeckRing_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.NeckRing_6.func_78790_a(-8.3f, -1.3f, -5.5f, 2, 1, 9, 0.0f);
        this.setRotateAngle(this.NeckRing_6, 0.0f, -0.7696902f, 0.0f);
        this.NeckRing_2.func_78792_a(this.NeckRing_3);
        this.NeckRing_4.func_78792_a(this.NeckRing_5);
        this.NeckRing.func_78792_a(this.NeckRing_1);
        this.Body1.func_78792_a(this.Neck);
        this.Body1.func_78792_a(this.ClothF);
        this.NeckRing_3.func_78792_a(this.NeckRing_4);
        this.Body1.func_78792_a(this.NeckRing);
        this.Hair4.func_78792_a(this.Hair5);
        this.NeckRing_6.func_78792_a(this.NeckRing_7);
        this.Hair3.func_78792_a(this.Hair4);
        this.LegR.func_78792_a(this.LegR3);
        this.Body1.func_78792_a(this.Boobs);
        this.Body1.func_78792_a(this.Body2);
        this.LegL.func_78792_a(this.LegL2);
        this.LegR.func_78792_a(this.LegR2);
        this.Body2.func_78792_a(this.Body3);
        this.NeckRing_1.func_78792_a(this.NeckRing_2);
        this.Body1.func_78792_a(this.ClothB);
        this.LegL.func_78792_a(this.LegL4);
        this.Head.func_78792_a(this.Hair1);
        this.Hair1.func_78792_a(this.Hair2);
        this.Hair2.func_78792_a(this.Hair3);
        this.NeckRing_5.func_78792_a(this.NeckRing_6);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 0.7f;
        JGRenderHelper.modelScalePositionHelper(0.7f);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        GL11.glPopMatrix();
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
        this.ClothB.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

