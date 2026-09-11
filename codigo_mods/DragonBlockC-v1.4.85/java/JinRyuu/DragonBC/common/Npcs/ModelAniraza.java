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

public class ModelAniraza
extends ModelBase {
    public ModelRenderer Head1;
    public ModelRenderer Body1;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;
    public ModelRenderer LegR1;
    public ModelRenderer LegL1;
    public ModelRenderer HeadExtra1;
    public ModelRenderer HeadExtra1_1;
    public ModelRenderer HeadExtra2;
    public ModelRenderer HeadExtra3;
    public ModelRenderer HeadExtra2_1;
    public ModelRenderer HeadExtra3_1;
    public ModelRenderer Neck1;
    public ModelRenderer Body2;
    public ModelRenderer Chest;
    public ModelRenderer Neck2;
    public ModelRenderer Neck3;
    public ModelRenderer Abs;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer BicepR;
    public ModelRenderer ForeArmR;
    public ModelRenderer BicepL;
    public ModelRenderer ForeArmL;
    public ModelRenderer LegR2;
    public ModelRenderer LegR3;
    public ModelRenderer FootR;
    public ModelRenderer LegL2;
    public ModelRenderer LegL3;
    public ModelRenderer FootL;

    public ModelAniraza() {
        this.field_78090_t = 256;
        this.field_78089_u = 256;
        this.HeadExtra1 = new ModelRenderer((ModelBase)this, 43, 1);
        this.HeadExtra1.func_78793_a(-4.6f, -6.0f, -0.5f);
        this.HeadExtra1.func_78790_a(-4.0f, -1.5f, -2.0f, 4, 3, 4, 0.0f);
        this.setRotateAngle(this.HeadExtra1, 0.0f, 0.0f, -0.19198622f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 144, 54);
        this.ShoulderR.func_78793_a(-12.3f, -54.5f, 4.0f);
        this.ShoulderR.func_78790_a(-11.1f, -3.6f, -5.5f, 11, 10, 11, 0.0f);
        this.Neck2 = new ModelRenderer((ModelBase)this, 62, 27);
        this.Neck2.func_78793_a(0.0f, 0.0f, -1.0f);
        this.Neck2.func_78790_a(-7.0f, -4.1f, 0.3f, 14, 6, 4, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 2, 131);
        this.Body4.func_78793_a(0.0f, 9.4f, 1.0f);
        this.Body4.func_78790_a(-10.0f, -2.7f, -6.9f, 20, 7, 12, 0.0f);
        this.BicepR = new ModelRenderer((ModelBase)this, 146, 78);
        this.BicepR.func_78793_a(-6.1f, 5.7f, 0.0f);
        this.BicepR.func_78790_a(-4.5f, 0.0f, -5.0f, 9, 14, 10, 0.0f);
        this.setRotateAngle(this.BicepR, 0.06632251f, 0.0f, 0.08726646f);
        this.HeadExtra2_1 = new ModelRenderer((ModelBase)this, 43, 9);
        this.HeadExtra2_1.field_78809_i = true;
        this.HeadExtra2_1.func_78793_a(3.5f, -0.4f, 0.0f);
        this.HeadExtra2_1.func_78790_a(0.1f, -0.9f, -1.5f, 4, 2, 3, 0.0f);
        this.setRotateAngle(this.HeadExtra2_1, (float)(-Math.PI) / 90, 0.0f, (float)Math.PI / 90);
        this.Body3 = new ModelRenderer((ModelBase)this, 4, 106);
        this.Body3.func_78793_a(0.0f, 8.0f, 1.2f);
        this.Body3.func_78790_a(-9.0f, -3.0f, -6.4f, 18, 11, 12, 0.0f);
        this.HeadExtra3 = new ModelRenderer((ModelBase)this, 43, 15);
        this.HeadExtra3.func_78793_a(-4.0f, 0.1f, 0.0f);
        this.HeadExtra3.func_78790_a(-4.2f, -0.8f, -2.0f, 4, 2, 4, 0.0f);
        this.setRotateAngle(this.HeadExtra3, -0.05235988f, 0.0f, 0.0f);
        this.LegL3 = new ModelRenderer((ModelBase)this, 49, 157);
        this.LegL3.field_78809_i = true;
        this.LegL3.func_78793_a(0.0f, 6.5f, 0.9f);
        this.LegL3.func_78790_a(-6.0f, -4.8f, -5.1f, 12, 11, 10, 0.0f);
        this.ForeArmR = new ModelRenderer((ModelBase)this, 149, 105);
        this.ForeArmR.func_78793_a(0.0f, 13.8f, 0.0f);
        this.ForeArmR.func_78790_a(-5.0f, -0.5f, -4.2f, 10, 18, 10, 0.0f);
        this.setRotateAngle(this.ForeArmR, -0.14660765f, 0.0f, -0.05235988f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 144, 54);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(12.3f, -54.5f, 4.0f);
        this.ShoulderL.func_78790_a(0.1f, -3.6f, -5.5f, 11, 10, 11, 0.0f);
        this.HeadExtra1_1 = new ModelRenderer((ModelBase)this, 43, 1);
        this.HeadExtra1_1.field_78809_i = true;
        this.HeadExtra1_1.func_78793_a(4.6f, -6.0f, -0.5f);
        this.HeadExtra1_1.func_78790_a(0.0f, -1.5f, -2.0f, 4, 3, 4, 0.0f);
        this.setRotateAngle(this.HeadExtra1_1, 0.0f, 0.0f, 0.19198622f);
        this.FootR = new ModelRenderer((ModelBase)this, 89, 193);
        this.FootR.func_78793_a(0.0f, 15.7f, -0.3f);
        this.FootR.func_78790_a(-5.5f, 0.1f, -9.2f, 11, 5, 14, 0.0f);
        this.setRotateAngle(this.FootR, -0.05235988f, 0.0f, (float)(-Math.PI) / 180);
        this.Neck1 = new ModelRenderer((ModelBase)this, 0, 27);
        this.Neck1.func_78793_a(0.0f, -1.9f, 5.3f);
        this.Neck1.func_78790_a(-11.0f, -1.1f, -5.1f, 22, 3, 7, 0.0f);
        this.FootL = new ModelRenderer((ModelBase)this, 89, 193);
        this.FootL.field_78809_i = true;
        this.FootL.func_78793_a(0.0f, 15.7f, -0.3f);
        this.FootL.func_78790_a(-5.5f, 0.1f, -9.2f, 11, 5, 14, 0.0f);
        this.setRotateAngle(this.FootL, -0.05235988f, 0.0f, (float)Math.PI / 180);
        this.LegL2 = new ModelRenderer((ModelBase)this, 94, 157);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(-0.1f, 20.0f, -0.8f);
        this.LegL2.func_78790_a(-5.0f, 0.0f, -5.0f, 10, 24, 10, 0.0f);
        this.setRotateAngle(this.LegL2, 0.12217305f, 0.0f, (float)Math.PI / 180);
        this.Neck3 = new ModelRenderer((ModelBase)this, 100, 26);
        this.Neck3.func_78793_a(0.0f, 0.0f, -1.0f);
        this.Neck3.func_78790_a(-4.5f, -1.5f, -4.4f, 9, 4, 7, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 42);
        this.Body1.func_78793_a(0.0f, -58.0f, 0.0f);
        this.Body1.func_78790_a(-13.0f, 0.0f, -2.0f, 26, 16, 13, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 94, 157);
        this.LegR2.func_78793_a(-0.1f, 20.0f, -0.8f);
        this.LegR2.func_78790_a(-5.0f, 0.0f, -5.0f, 10, 24, 10, 0.0f);
        this.setRotateAngle(this.LegR2, 0.12217305f, 0.0f, (float)(-Math.PI) / 180);
        this.LegL1 = new ModelRenderer((ModelBase)this, 93, 122);
        this.LegL1.field_78809_i = true;
        this.LegL1.func_78793_a(6.0f, -23.0f, 2.7f);
        this.LegL1.func_78790_a(-5.5f, 0.0f, -6.0f, 11, 20, 12, 0.0f);
        this.setRotateAngle(this.LegL1, -0.06981317f, 0.0f, (float)(-Math.PI) / 90);
        this.Chest = new ModelRenderer((ModelBase)this, 68, 73);
        this.Chest.func_78793_a(0.0f, 6.2f, -1.7f);
        this.Chest.func_78790_a(-10.0f, -3.7f, -3.5f, 20, 8, 4, 0.0f);
        this.BicepL = new ModelRenderer((ModelBase)this, 146, 78);
        this.BicepL.field_78809_i = true;
        this.BicepL.func_78793_a(6.1f, 5.7f, 0.0f);
        this.BicepL.func_78790_a(-4.5f, 0.0f, -5.0f, 9, 14, 10, 0.0f);
        this.setRotateAngle(this.BicepL, 0.06632251f, 0.0f, -0.08726646f);
        this.HeadExtra2 = new ModelRenderer((ModelBase)this, 43, 9);
        this.HeadExtra2.func_78793_a(-3.5f, -0.4f, 0.0f);
        this.HeadExtra2.func_78790_a(-4.1f, -0.9f, -1.5f, 4, 2, 3, 0.0f);
        this.setRotateAngle(this.HeadExtra2, (float)(-Math.PI) / 90, 0.0f, (float)(-Math.PI) / 90);
        this.ForeArmL = new ModelRenderer((ModelBase)this, 149, 105);
        this.ForeArmL.field_78809_i = true;
        this.ForeArmL.func_78793_a(0.0f, 13.8f, 0.0f);
        this.ForeArmL.func_78790_a(-4.5f, -0.5f, -4.2f, 10, 18, 10, 0.0f);
        this.setRotateAngle(this.ForeArmL, -0.14660765f, 0.0f, 0.05235988f);
        this.Head1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head1.func_78793_a(0.0f, -63.2f, 2.7f);
        this.Head1.func_78790_a(-5.0f, -9.0f, -6.0f, 10, 11, 10, 0.0f);
        this.Abs = new ModelRenderer((ModelBase)this, 76, 88);
        this.Abs.func_78793_a(0.0f, 0.0f, -5.7f);
        this.Abs.func_78790_a(-6.0f, -2.7f, -0.5f, 12, 11, 1, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 1, 73);
        this.Body2.func_78793_a(0.0f, 13.0f, 1.2f);
        this.Body2.func_78790_a(-11.0f, -11.9f, -5.5f, 22, 17, 14, 0.0f);
        this.LegR1 = new ModelRenderer((ModelBase)this, 93, 122);
        this.LegR1.func_78793_a(-6.0f, -23.0f, 2.7f);
        this.LegR1.func_78790_a(-5.5f, 0.0f, -6.0f, 11, 20, 12, 0.0f);
        this.setRotateAngle(this.LegR1, -0.06981317f, 0.0f, (float)Math.PI / 90);
        this.LegR3 = new ModelRenderer((ModelBase)this, 49, 157);
        this.LegR3.func_78793_a(0.0f, 6.5f, 0.9f);
        this.LegR3.func_78790_a(-6.0f, -4.8f, -5.1f, 12, 11, 10, 0.0f);
        this.HeadExtra3_1 = new ModelRenderer((ModelBase)this, 43, 15);
        this.HeadExtra3_1.field_78809_i = true;
        this.HeadExtra3_1.func_78793_a(4.0f, 0.1f, 0.0f);
        this.HeadExtra3_1.func_78790_a(0.2f, -0.8f, -2.0f, 4, 2, 4, 0.0f);
        this.setRotateAngle(this.HeadExtra3_1, -0.05235988f, 0.0f, 0.0f);
        this.Head1.func_78792_a(this.HeadExtra1);
        this.Neck1.func_78792_a(this.Neck2);
        this.Body3.func_78792_a(this.Body4);
        this.ShoulderR.func_78792_a(this.BicepR);
        this.HeadExtra1_1.func_78792_a(this.HeadExtra2_1);
        this.Body2.func_78792_a(this.Body3);
        this.HeadExtra2.func_78792_a(this.HeadExtra3);
        this.LegL2.func_78792_a(this.LegL3);
        this.BicepR.func_78792_a(this.ForeArmR);
        this.Head1.func_78792_a(this.HeadExtra1_1);
        this.LegR3.func_78792_a(this.FootR);
        this.Body1.func_78792_a(this.Neck1);
        this.LegL3.func_78792_a(this.FootL);
        this.LegL1.func_78792_a(this.LegL2);
        this.Neck2.func_78792_a(this.Neck3);
        this.LegR1.func_78792_a(this.LegR2);
        this.Body1.func_78792_a(this.Chest);
        this.ShoulderL.func_78792_a(this.BicepL);
        this.HeadExtra1.func_78792_a(this.HeadExtra2);
        this.BicepL.func_78792_a(this.ForeArmL);
        this.Body2.func_78792_a(this.Abs);
        this.Body1.func_78792_a(this.Body2);
        this.LegR2.func_78792_a(this.LegR3);
        this.HeadExtra2_1.func_78792_a(this.HeadExtra3_1);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 2.5f;
        JGRenderHelper.modelScalePositionHelper(2.5f);
        this.LegL1.func_78785_a(f5);
        this.Head1.func_78785_a(f5);
        this.ShoulderL.func_78785_a(f5);
        this.ShoulderR.func_78785_a(f5);
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
        this.Head1.field_78796_g = n4 / (r2 / (float)Math.PI);
        this.Head1.field_78795_f = n5 / (r2 / (float)Math.PI);
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
        this.LegR1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ShoulderR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ShoulderL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR1.field_78796_g = 0.0f;
        this.LegL1.field_78796_g = 0.0f;
        this.ShoulderR.field_78796_g = 0.0f;
        this.ShoulderL.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

