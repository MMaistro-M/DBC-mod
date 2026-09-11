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

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelCabba
extends ModelBase {
    public ModelRenderer ArmR;
    public ModelRenderer LegR;
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer LegL;
    public ModelRenderer Hair;
    public ModelRenderer ClothB;
    public ModelRenderer ClothF;
    public ModelRenderer ClothL;
    public ModelRenderer ClothR;
    public ModelRenderer gohan1;
    public ModelRenderer gohan7;
    public ModelRenderer gohan8;
    public ModelRenderer gohan10;
    public ModelRenderer gohan11;
    public ModelRenderer gohan12;
    public ModelRenderer gohan13;
    public ModelRenderer gohan14;
    public ModelRenderer gohan15;
    public ModelRenderer gohan16;
    public ModelRenderer gohan17;
    public ModelRenderer gohan18;
    public ModelRenderer gohan19;
    public ModelRenderer gohan20;
    public ModelRenderer gohan21;
    public ModelRenderer gohan22;

    public ModelCabba() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(0.5f, -2.9f, -6.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Hair, -0.4553564f, -0.22759093f, -0.15009831f);
        this.ClothR = new ModelRenderer((ModelBase)this, 30, 33);
        this.ClothR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ClothR.func_78790_a(-3.3f, 9.2f, -1.4f, 1, 9, 3, 0.0f);
        this.setRotateAngle(this.ClothR, 0.0f, 0.0f, 0.04363323f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 17);
        this.LegR.func_78793_a(-2.0f, 13.0f, 0.0f);
        this.LegR.func_78790_a(-1.5f, 0.0f, -2.0f, 3, 11, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 17);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 13.0f, 0.0f);
        this.LegL.func_78790_a(-1.5f, 0.0f, -2.0f, 3, 11, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 2.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.6f, -4.0f, 8, 8, 8, -0.3f);
        this.ClothB = new ModelRenderer((ModelBase)this, 20, 35);
        this.ClothB.func_78793_a(0.0f, 9.0f, 1.9f);
        this.ClothB.func_78790_a(-1.5f, 0.0f, -0.4f, 3, 9, 1, 0.0f);
        this.setRotateAngle(this.ClothB, 0.11868239f, 0.0f, 0.0f);
        this.ClothL = new ModelRenderer((ModelBase)this, 30, 33);
        this.ClothL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ClothL.func_78790_a(2.6f, 9.0f, -1.4f, 1, 9, 3, 0.0f);
        this.setRotateAngle(this.ClothL, 0.0f, 0.0f, -0.04363323f);
        this.Body = new ModelRenderer((ModelBase)this, 16, 17);
        this.Body.func_78793_a(0.0f, 2.0f, 0.0f);
        this.Body.func_78790_a(-3.5f, 0.0f, -2.0f, 7, 11, 4, 0.0f);
        this.ClothF = new ModelRenderer((ModelBase)this, 20, 35);
        this.ClothF.func_78793_a(0.0f, 9.0f, -1.9f);
        this.ClothF.func_78790_a(-1.5f, 0.0f, -0.4f, 3, 9, 1, 0.0f);
        this.setRotateAngle(this.ClothF, -0.11868239f, 0.0f, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 40, 18);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.5f, 4.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -1.5f, 3, 11, 3, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 40, 18);
        this.ArmR.func_78793_a(-5.0f, 4.0f, 0.0f);
        this.ArmR.func_78790_a(-1.5f, -2.0f, -1.5f, 3, 11, 3, 0.0f);
        this.gohan1 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan1.func_78789_a(-1.0f, -10.0f, -5.066667f, 4, 4, 4);
        this.gohan1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan1, -0.1745329f, 0.0f, 0.0f);
        this.gohan7 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan7.func_78789_a(-0.5f, -11.0f, -6.0f, 3, 2, 3);
        this.gohan7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan7, -0.3665191f, 0.0f, 0.0f);
        this.gohan8 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan8.func_78789_a(0.0f, -11.0f, -7.0f, 2, 2, 2);
        this.gohan8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan8, -0.5585054f, 0.0f, 0.0f);
        this.gohan10 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan10.func_78789_a(-1.533333f, -10.3f, -5.466667f, 4, 5, 4);
        this.gohan10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan10, -0.2617994f, 0.0f, -0.3665191f);
        this.gohan11 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan11.func_78789_a(-0.5f, -11.3f, -6.0f, 5, 4, 4);
        this.gohan11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan11, -0.418879f, 0.0f, -0.3316126f);
        this.gohan12 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan12.func_78789_a(-0.5f, -12.5f, -6.0f, 3, 3, 3);
        this.gohan12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan12, -0.5235988f, 0.0f, -0.2268928f);
        this.gohan13 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan13.func_78789_a(0.0f, -12.66667f, -7.0f, 2, 4, 2);
        this.gohan13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan13, -0.6283185f, 0.0f, -0.0698132f);
        this.gohan14 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan14.func_78789_a(-1.3f, -9.3f, -5.0f, 3, 5, 3);
        this.gohan14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan14, -0.2268928f, 0.0f, 0.4014257f);
        this.gohan15 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan15.func_78789_a(-0.8333333f, -10.8f, -6.0f, 3, 4, 4);
        this.gohan15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan15, -0.4537856f, 0.0f, 0.2617994f);
        this.gohan16 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan16.func_78789_a(-1.0f, -12.46667f, -6.0f, 3, 4, 3);
        this.gohan16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan16, -0.5410521f, 0.0f, 0.1745329f);
        this.gohan17 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.gohan17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan17, -0.2792527f, 0.0f, 0.0f);
        this.gohan18 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan18.func_78789_a(-0.8f, -10.0f, -1.0f, 4, 5, 4);
        this.gohan18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.gohan19 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan19.func_78789_a(-3.266667f, -10.0f, -1.0f, 4, 4, 4);
        this.gohan19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.gohan20 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan20.func_78789_a(-2.0f, -12.0f, -1.0f, 3, 4, 4);
        this.gohan20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan20, -0.1396263f, 0.0f, 0.0f);
        this.gohan21 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan21.func_78789_a(-0.6f, -11.5f, 0.0f, 3, 2, 3);
        this.gohan21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan21, -0.122173f, 0.1745329f, 0.0f);
        this.gohan22 = new ModelRenderer((ModelBase)this, 64, 0);
        this.gohan22.func_78789_a(-2.866667f, -11.53333f, -0.6666667f, 3, 4, 3);
        this.gohan22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.gohan22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.Head.func_78792_a(this.Hair);
        this.Body.func_78792_a(this.ClothR);
        this.Body.func_78792_a(this.ClothB);
        this.Body.func_78792_a(this.ClothL);
        this.Body.func_78792_a(this.ClothF);
        this.Head.func_78792_a(this.gohan1);
        this.Head.func_78792_a(this.gohan7);
        this.Head.func_78792_a(this.gohan8);
        this.Head.func_78792_a(this.gohan10);
        this.Head.func_78792_a(this.gohan11);
        this.Head.func_78792_a(this.gohan12);
        this.Head.func_78792_a(this.gohan13);
        this.Head.func_78792_a(this.gohan15);
        this.Head.func_78792_a(this.gohan16);
        this.Head.func_78792_a(this.gohan17);
        this.Head.func_78792_a(this.gohan18);
        this.Head.func_78792_a(this.gohan19);
        this.Head.func_78792_a(this.gohan20);
        this.Head.func_78792_a(this.gohan21);
        this.Head.func_78792_a(this.gohan22);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        float F = 0.8f;
        GL11.glPushMatrix();
        GL11.glScalef((float)F, (float)F, (float)F);
        GL11.glTranslatef((float)0.0f, (float)((F - 1.0f) * -0.74f * 3.0f), (float)0.0f);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body.func_78785_a(f5);
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
        this.ClothF.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (this.ClothF.field_78795_f > 0.0f) {
            this.ClothF.field_78795_f *= -1.0f;
        }
        this.ClothF.field_78796_g = 0.0f;
        this.ClothB.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (0.0f > this.ClothB.field_78795_f) {
            this.ClothB.field_78795_f *= -1.0f;
        }
        this.ClothB.field_78796_g = 0.0f;
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

