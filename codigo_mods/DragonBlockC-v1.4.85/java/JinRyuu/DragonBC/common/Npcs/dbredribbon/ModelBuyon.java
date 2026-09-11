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
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import JinRyuu.JRMCore.client.JGRenderHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelBuyon
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer tail1;
    public ModelRenderer Head2;
    public ModelRenderer Body2;
    public ModelRenderer Chest;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;

    public ModelBuyon() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.tail3 = new ModelRenderer((ModelBase)this, 88, 116);
        this.tail3.func_78793_a(0.0f, 0.0f, 4.9f);
        this.tail3.func_78790_a(-2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 92, 3);
        this.ArmR.func_78793_a(-8.8f, 2.5f, 2.0f);
        this.ArmR.func_78790_a(-3.5f, -2.0f, -2.5f, 5, 9, 5, 0.0f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.38397244f);
        this.tail2 = new ModelRenderer((ModelBase)this, 67, 116);
        this.tail2.func_78793_a(0.0f, -0.2f, 5.9f);
        this.tail2.func_78790_a(-2.5f, -2.5f, 0.0f, 5, 5, 5, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 0, 88);
        this.Body4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body4.func_78790_a(-7.0f, 20.0f, -3.2f, 14, 2, 9, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 26);
        this.Body1.func_78793_a(0.0f, -0.1f, 0.0f);
        this.Body1.func_78790_a(-8.0f, 0.0f, -2.4f, 16, 7, 9, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 92, 3);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(8.8f, 2.5f, 2.0f);
        this.ArmL.func_78790_a(-1.5f, -2.0f, -2.5f, 5, 9, 5, 0.0f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.38397244f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 46);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-9.0f, 7.0f, -4.1f, 18, 4, 11, 0.0f);
        this.tail5 = new ModelRenderer((ModelBase)this, 111, 109);
        this.tail5.func_78793_a(0.0f, 0.0f, 5.9f);
        this.tail5.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 5, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 90, 19);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.8f, 7.1f, -0.3f);
        this.ArmL2.func_78790_a(-2.8f, -0.5f, -2.5f, 6, 9, 6, 0.0f);
        this.setRotateAngle(this.ArmL2, -0.4098033f, 0.0f, 0.08726646f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 90, 19);
        this.ArmR2.func_78793_a(-0.6f, 7.1f, -0.3f);
        this.ArmR2.func_78790_a(-3.5f, -0.5f, -2.5f, 6, 9, 6, 0.0f);
        this.setRotateAngle(this.ArmR2, -0.4098033f, 0.0f, -0.08726646f);
        this.tail4 = new ModelRenderer((ModelBase)this, 109, 117);
        this.tail4.func_78793_a(0.0f, 0.0f, 5.9f);
        this.tail4.func_78790_a(-1.5f, -1.5f, 0.0f, 3, 3, 6, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this, 53, 43);
        this.Chest.func_78793_a(0.0f, 4.2f, -1.7f);
        this.Chest.func_78790_a(-7.0f, -1.9f, -1.4f, 14, 5, 2, 0.0f);
        this.setRotateAngle(this.Chest, -0.091106184f, 0.0f, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 91, 43);
        this.LegR.func_78793_a(-7.2f, 20.0f, 1.0f);
        this.LegR.func_78790_a(-3.5f, 0.0f, -3.5f, 7, 4, 8, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 42, 114);
        this.tail1.func_78793_a(0.0f, 14.4f, 6.5f);
        this.tail1.func_78790_a(-3.0f, -3.0f, 0.0f, 6, 6, 6, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 36, 2);
        this.Head2.func_78793_a(0.0f, -7.2f, -1.0f);
        this.Head2.func_78790_a(-5.8f, -7.0f, 0.0f, 12, 7, 0, 0.0f);
        this.setRotateAngle(this.Head2, 0.27314404f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 64);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-10.0f, 10.6f, -4.5f, 20, 10, 12, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.6f, -0.5f);
        this.Head.func_78790_a(-4.0f, -7.2f, -4.0f, 8, 7, 8, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 91, 43);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(7.2f, 20.0f, 1.0f);
        this.LegL.func_78790_a(-3.5f, 0.0f, -3.5f, 7, 4, 8, 0.0f);
        this.tail2.func_78792_a(this.tail3);
        this.tail1.func_78792_a(this.tail2);
        this.Body3.func_78792_a(this.Body4);
        this.Body1.func_78792_a(this.Body2);
        this.tail4.func_78792_a(this.tail5);
        this.ArmL.func_78792_a(this.ArmL2);
        this.ArmR.func_78792_a(this.ArmR2);
        this.tail3.func_78792_a(this.tail4);
        this.Body1.func_78792_a(this.Chest);
        this.Head.func_78792_a(this.Head2);
        this.Body2.func_78792_a(this.Body3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 2.3f;
        JGRenderHelper.modelScalePositionHelper(2.3f);
        this.Head.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        this.tail1.func_78785_a(f5);
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
        this.tail1.field_78795_f = 0.2f;
        this.tail1.field_78795_f += r4 / 2.0f;
        this.tail2.field_78795_f = 0.2f;
        this.tail2.field_78795_f += r4 / 2.0f;
        this.tail3.field_78795_f = 0.2f;
        this.tail3.field_78795_f += r4 / 2.0f;
        this.tail4.field_78795_f = 0.2f;
        this.tail4.field_78795_f += r4 / 2.0f;
        this.tail5.field_78796_g = 0.2f;
        this.tail5.field_78796_g += r4;
        this.LegR.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
    }
}

