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

public class ModelGodMosco
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegL1;
    public ModelRenderer LegR1;
    public ModelRenderer HeadSpike1;
    public ModelRenderer HeadSideR;
    public ModelRenderer HeadSideL;
    public ModelRenderer HeadFront;
    public ModelRenderer HeadSpike2;
    public ModelRenderer FrontDoor;
    public ModelRenderer Body2;
    public ModelRenderer ClothF;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmR4;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;
    public ModelRenderer ArmL4;
    public ModelRenderer LegL2;
    public ModelRenderer LegL3;
    public ModelRenderer LegL4;
    public ModelRenderer LegR2;
    public ModelRenderer LegR3;
    public ModelRenderer LegR4;

    public ModelGodMosco() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.ArmR3 = new ModelRenderer((ModelBase)this, 53, 23);
        this.ArmR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR3.func_78790_a(-4.7f, 6.1f, -3.0f, 5, 5, 6, 0.0f);
        this.setRotateAngle(this.ArmR3, 0.0f, 0.0f, -0.06981317f);
        this.HeadSideL = new ModelRenderer((ModelBase)this, 33, 0);
        this.HeadSideL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadSideL.func_78790_a(3.9f, -4.1f, -1.9f, 1, 4, 3, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 81, 12);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-1.5f, 1.1f, -2.0f, 3, 5, 4, 0.0f);
        this.setRotateAngle(this.LegL2, 0.0f, 0.0f, -0.054105207f);
        this.FrontDoor = new ModelRenderer((ModelBase)this, 0, 14);
        this.FrontDoor.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FrontDoor.func_78790_a(-2.5f, 5.1f, -6.2f, 5, 4, 1, 0.0f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 53, 23);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL3.func_78790_a(-0.4f, 6.1f, -3.0f, 5, 5, 6, 0.0f);
        this.setRotateAngle(this.ArmL3, 0.0f, 0.0f, 0.06981317f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 57, 13);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(0.0f, 1.7f, -2.0f, 3, 5, 4, 0.0f);
        this.setRotateAngle(this.ArmL2, 0.0f, 0.0f, -0.1308997f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 51, 2);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(6.6f, -0.8f, -0.8f);
        this.ArmL1.func_78790_a(-0.6f, -2.2f, -3.0f, 4, 4, 6, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 1, 47);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-6.0f, 12.0f, -5.0f, 12, 2, 9, 0.0f);
        this.ArmR4 = new ModelRenderer((ModelBase)this, 57, 35);
        this.ArmR4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR4.func_78790_a(-4.1f, 10.8f, -2.4f, 4, 4, 5, 0.0f);
        this.setRotateAngle(this.ArmR4, 0.0f, 0.0f, -0.013962634f);
        this.HeadFront = new ModelRenderer((ModelBase)this, 32, 8);
        this.HeadFront.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadFront.func_78790_a(-1.5f, -3.7f, -4.7f, 3, 3, 1, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -2.9f, 0.0f);
        this.Head.func_78790_a(-4.0f, -5.1f, -3.9f, 8, 5, 7, 0.0f);
        this.HeadSpike2 = new ModelRenderer((ModelBase)this, 26, 4);
        this.HeadSpike2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadSpike2.func_78790_a(-0.5f, -7.1f, -0.8f, 1, 1, 1, 0.0f);
        this.LegR1 = new ModelRenderer((ModelBase)this, 79, 2);
        this.LegR1.func_78793_a(-4.1f, 12.0f, -0.6f);
        this.LegR1.func_78790_a(-2.0f, -1.3f, -2.6f, 4, 3, 5, 0.0f);
        this.setRotateAngle(this.LegR1, 0.0f, 0.0f, 0.18203785f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 51, 2);
        this.ArmR1.func_78793_a(-6.6f, -0.8f, -0.8f);
        this.ArmR1.func_78790_a(-3.4f, -2.2f, -3.0f, 4, 4, 6, 0.0f);
        this.ClothF = new ModelRenderer((ModelBase)this, 60, 47);
        this.ClothF.func_78793_a(0.0f, 12.0f, -5.0f);
        this.ClothF.func_78790_a(-2.5f, 0.0f, -0.3f, 5, 8, 0, 0.0f);
        this.setRotateAngle(this.ClothF, -0.04363323f, 0.0f, 0.0f);
        this.HeadSideR = new ModelRenderer((ModelBase)this, 33, 0);
        this.HeadSideR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadSideR.func_78790_a(-4.8f, -4.1f, -1.9f, 1, 4, 3, 0.0f);
        this.ArmL4 = new ModelRenderer((ModelBase)this, 57, 35);
        this.ArmL4.field_78809_i = true;
        this.ArmL4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL4.func_78790_a(0.0f, 10.8f, -2.4f, 4, 4, 5, 0.0f);
        this.setRotateAngle(this.ArmL4, 0.0f, 0.0f, 0.013962634f);
        this.LegL1 = new ModelRenderer((ModelBase)this, 79, 2);
        this.LegL1.field_78809_i = true;
        this.LegL1.func_78793_a(4.1f, 12.0f, -0.6f);
        this.LegL1.func_78790_a(-2.0f, -1.3f, -2.6f, 4, 3, 5, 0.0f);
        this.setRotateAngle(this.LegL1, 0.0f, 0.0f, -0.18203785f);
        this.LegL3 = new ModelRenderer((ModelBase)this, 80, 22);
        this.LegL3.field_78809_i = true;
        this.LegL3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL3.func_78790_a(-1.0f, 5.0f, -3.0f, 5, 7, 6, 0.0f);
        this.setRotateAngle(this.LegL3, 0.0f, 0.0f, 0.23387411f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 81, 12);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-1.5f, 1.1f, -2.0f, 3, 5, 4, 0.0f);
        this.setRotateAngle(this.LegR2, 0.0f, 0.0f, 0.054105207f);
        this.LegR4 = new ModelRenderer((ModelBase)this, 82, 36);
        this.LegR4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR4.func_78790_a(-3.3f, 9.9f, -5.0f, 4, 2, 2, 0.0f);
        this.LegL4 = new ModelRenderer((ModelBase)this, 82, 36);
        this.LegL4.field_78809_i = true;
        this.LegL4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL4.func_78790_a(-0.6f, 9.9f, -5.0f, 4, 2, 2, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 5, 14);
        this.Body1.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Body1.func_78790_a(-6.0f, 0.0f, -4.8f, 12, 5, 9, 0.0f);
        this.HeadSpike1 = new ModelRenderer((ModelBase)this, 24, 0);
        this.HeadSpike1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadSpike1.func_78790_a(-1.0f, -6.1f, -1.3f, 2, 1, 2, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 36, 51);
        this.Body4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body4.func_78790_a(-3.0f, 13.8f, -5.0f, 6, 2, 8, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 57, 13);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-3.1f, 1.7f, -2.0f, 3, 5, 4, 0.0f);
        this.setRotateAngle(this.ArmR2, 0.0f, 0.0f, 0.1308997f);
        this.LegR3 = new ModelRenderer((ModelBase)this, 80, 22);
        this.LegR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR3.func_78790_a(-3.9f, 5.0f, -3.0f, 5, 7, 6, 0.0f);
        this.setRotateAngle(this.LegR3, 0.0f, 0.0f, -0.23387411f);
        this.Body2 = new ModelRenderer((ModelBase)this, 2, 29);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-6.0f, 5.0f, -5.4f, 12, 7, 10, 0.0f);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Head.func_78792_a(this.HeadSideL);
        this.LegL1.func_78792_a(this.LegL2);
        this.Body1.func_78792_a(this.FrontDoor);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.Body2.func_78792_a(this.Body3);
        this.ArmR3.func_78792_a(this.ArmR4);
        this.Head.func_78792_a(this.HeadFront);
        this.HeadSpike1.func_78792_a(this.HeadSpike2);
        this.Body1.func_78792_a(this.ClothF);
        this.Head.func_78792_a(this.HeadSideR);
        this.ArmL3.func_78792_a(this.ArmL4);
        this.LegL2.func_78792_a(this.LegL3);
        this.LegR1.func_78792_a(this.LegR2);
        this.LegR3.func_78792_a(this.LegR4);
        this.LegL3.func_78792_a(this.LegL4);
        this.Head.func_78792_a(this.HeadSpike1);
        this.Body3.func_78792_a(this.Body4);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.LegR2.func_78792_a(this.LegR3);
        this.Body1.func_78792_a(this.Body2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 1.5f;
        JGRenderHelper.modelScalePositionHelper(1.5f);
        this.LegL1.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegR1.func_78785_a(f5);
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
        this.LegR1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR1.field_78796_g = 0.0f;
        this.LegL1.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        this.ClothF.field_78795_f = -0.15f + this.LegR1.field_78795_f * (float)(this.LegR1.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

