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

public class ModelGodSidra
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer Hair1;
    public ModelRenderer Nose;
    public ModelRenderer Beard1;
    public ModelRenderer Hair2;
    public ModelRenderer Beard2;
    public ModelRenderer Beard3;
    public ModelRenderer Body2;
    public ModelRenderer Cloth1;
    public ModelRenderer Body3;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmRingR1;
    public ModelRenderer ArmRingR2;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmRingL1;
    public ModelRenderer ArmRingL2;
    public ModelRenderer LegL2;
    public ModelRenderer LegR2;

    public ModelGodSidra() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.LegR2 = new ModelRenderer((ModelBase)this, 0, 37);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-3.5f, 0.1f, -3.5f, 6, 8, 7, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 90, 38);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-2.8f, 4.7f, -2.0f, 4, 8, 4, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 90, 38);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-1.2f, 4.7f, -2.0f, 4, 8, 4, 0.0f);
        this.ArmRingL1 = new ModelRenderer((ModelBase)this, 88, 51);
        this.ArmRingL1.field_78809_i = true;
        this.ArmRingL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingL1.func_78790_a(-1.4f, 8.3f, -2.5f, 5, 1, 5, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -5.9f, -4.6f, 8, 6, 8, -0.1f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 0, 37);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.5f, 0.1f, -3.5f, 6, 8, 7, 0.0f);
        this.ArmRingL2 = new ModelRenderer((ModelBase)this, 88, 51);
        this.ArmRingL2.field_78809_i = true;
        this.ArmRingL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingL2.func_78790_a(0.1f, 9.4f, -2.5f, 5, 1, 5, 0.0f);
        this.setRotateAngle(this.ArmRingL2, 0.0f, 0.0f, 0.15009831f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 91, 16);
        this.Hair2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair2.func_78790_a(-3.0f, -3.0f, 4.6f, 6, 9, 1, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 91, 28);
        this.ArmR1.func_78793_a(-6.5f, 1.6f, -0.5f);
        this.ArmR1.func_78790_a(-2.1f, -1.4f, -1.5f, 3, 6, 3, 0.2f);
        this.earL = new ModelRenderer((ModelBase)this, 1, 1);
        this.earL.field_78809_i = true;
        this.earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earL.func_78790_a(1.0f, -6.1f, -1.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.earL, -0.12217305f, -0.13613568f, 0.5462881f);
        this.earR = new ModelRenderer((ModelBase)this, 1, 1);
        this.earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earR.func_78790_a(-3.0f, -6.1f, -1.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.earR, -0.12217305f, 0.13613568f, -0.5462881f);
        this.Body2 = new ModelRenderer((ModelBase)this, 37, 29);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-6.0f, 5.0f, -4.7f, 12, 5, 9, 0.0f);
        this.Beard2 = new ModelRenderer((ModelBase)this, 5, 27);
        this.Beard2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Beard2.func_78790_a(-3.0f, 1.4f, -5.3f, 6, 1, 1, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 53);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.4f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.1f, 6.0f, -2.6f, 5, 6, 5, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 89, 1);
        this.Hair1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair1.func_78790_a(-4.0f, -3.8f, 2.7f, 8, 12, 2, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 38, 14);
        this.Body1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body1.func_78790_a(-5.5f, 0.0f, -4.0f, 11, 5, 8, 0.0f);
        this.Nose = new ModelRenderer((ModelBase)this, 25, 4);
        this.Nose.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Nose.func_78790_a(-1.0f, -3.0f, -5.9f, 2, 1, 2, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 91, 28);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(6.5f, 1.6f, -0.5f);
        this.ArmL1.func_78790_a(-0.9f, -1.4f, -1.5f, 3, 6, 3, 0.2f);
        this.ArmRingR1 = new ModelRenderer((ModelBase)this, 88, 51);
        this.ArmRingR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingR1.func_78790_a(-3.5f, 8.3f, -2.5f, 5, 1, 5, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 39, 44);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-5.5f, 9.1f, -4.1f, 11, 3, 8, 0.0f);
        this.Cloth1 = new ModelRenderer((ModelBase)this, 54, 0);
        this.Cloth1.func_78793_a(0.0f, 10.0f, -4.2f);
        this.Cloth1.func_78790_a(-2.5f, 0.0f, -0.2f, 5, 10, 0, 0.0f);
        this.setRotateAngle(this.Cloth1, -0.04363323f, 0.0f, 0.0f);
        this.ArmRingR2 = new ModelRenderer((ModelBase)this, 88, 51);
        this.ArmRingR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingR2.func_78790_a(-4.9f, 9.4f, -2.5f, 5, 1, 5, 0.0f);
        this.setRotateAngle(this.ArmRingR2, 0.0f, 0.0f, -0.15009831f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 53);
        this.LegR.func_78793_a(-2.6f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.9f, 6.0f, -2.5f, 5, 6, 5, 0.0f);
        this.Beard1 = new ModelRenderer((ModelBase)this, 4, 20);
        this.Beard1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Beard1.func_78790_a(-4.0f, -1.5f, -5.3f, 8, 3, 2, 0.0f);
        this.Beard3 = new ModelRenderer((ModelBase)this, 5, 15);
        this.Beard3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Beard3.func_78790_a(-3.0f, -2.2f, -5.6f, 6, 2, 2, 0.0f);
        this.LegR.func_78792_a(this.LegR2);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.ArmL2.func_78792_a(this.ArmRingL1);
        this.LegL.func_78792_a(this.LegL2);
        this.ArmRingL1.func_78792_a(this.ArmRingL2);
        this.Hair1.func_78792_a(this.Hair2);
        this.Head.func_78792_a(this.earL);
        this.Head.func_78792_a(this.earR);
        this.Body1.func_78792_a(this.Body2);
        this.Beard1.func_78792_a(this.Beard2);
        this.Head.func_78792_a(this.Hair1);
        this.Head.func_78792_a(this.Nose);
        this.ArmR2.func_78792_a(this.ArmRingR1);
        this.Body2.func_78792_a(this.Body3);
        this.Body1.func_78792_a(this.Cloth1);
        this.ArmRingR1.func_78792_a(this.ArmRingR2);
        this.Head.func_78792_a(this.Beard1);
        this.Beard1.func_78792_a(this.Beard3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 0.8f;
        JGRenderHelper.modelScalePositionHelper(0.8f);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
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
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        this.Cloth1.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

