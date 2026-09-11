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

public class ModelJiren_Full_Power
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer EarL;
    public ModelRenderer EarR;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Chest;
    public ModelRenderer TornCloth;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;

    public ModelJiren_Full_Power() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, -8.5f, 0.0f);
        this.Body1.func_78790_a(-7.5f, 0.0f, -3.3f, 15, 8, 7, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 63, 5);
        this.ArmR1.func_78793_a(-8.0f, -5.2f, 0.0f);
        this.ArmR1.func_78790_a(-5.3f, -3.3f, -3.0f, 6, 6, 7, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 41, 41);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(3.0f, 8.5f, 0.0f);
        this.LegL.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 16, 6, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-6.0f, 6.0f, -3.5f, 12, 6, 6, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 48);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-6.0f, 12.0f, -3.0f, 12, 5, 6, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 33, 8);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(4.0f, -4.2f, 0.4f);
        this.EarL.func_78790_a(0.0f, -1.5f, -1.6f, 1, 3, 3, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 41, 41);
        this.LegR.func_78793_a(-3.0f, 8.5f, 0.0f);
        this.LegR.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 16, 6, 0.0f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 67, 31);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 4.3f, -0.2f);
        this.ArmL3.func_78790_a(-2.5f, 0.0f, -1.8f, 5, 9, 5, 0.0f);
        this.TornCloth = new ModelRenderer((ModelBase)this, 68, 48);
        this.TornCloth.func_78793_a(0.0f, 0.0f, 0.0f);
        this.TornCloth.func_78790_a(-6.5f, 12.4f, -3.5f, 13, 1, 7, 0.0f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 67, 31);
        this.ArmR3.func_78793_a(0.0f, 4.3f, -0.2f);
        this.ArmR3.func_78790_a(-2.5f, 0.0f, -1.9f, 5, 9, 5, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this, 38, 33);
        this.Chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest.func_78790_a(-6.0f, 1.2f, -4.1f, 12, 6, 1, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 33, 8);
        this.EarR.func_78793_a(-4.0f, -4.2f, 0.4f);
        this.EarR.func_78790_a(-1.0f, -1.5f, -1.6f, 1, 3, 3, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 65, 19);
        this.ArmR2.func_78793_a(-2.0f, 2.0f, 0.0f);
        this.ArmR2.func_78790_a(-2.7f, -0.2f, -2.5f, 5, 5, 6, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 65, 19);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(2.0f, 2.0f, 0.0f);
        this.ArmL2.func_78790_a(-2.3f, -0.2f, -2.5f, 5, 5, 6, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -8.5f, -0.5f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 63, 5);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(8.0f, -5.2f, 0.0f);
        this.ArmL1.func_78790_a(-0.7f, -3.3f, -3.0f, 6, 6, 7, 0.0f);
        this.Body1.func_78792_a(this.Body2);
        this.Body2.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.EarL);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.Body3.func_78792_a(this.TornCloth);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Body2.func_78792_a(this.Chest);
        this.Head.func_78792_a(this.EarR);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.ArmL1.func_78792_a(this.ArmL2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 1.1f;
        JGRenderHelper.modelScalePositionHelper(1.1f);
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
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

