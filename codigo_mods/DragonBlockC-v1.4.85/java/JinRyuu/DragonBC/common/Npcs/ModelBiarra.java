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

public class ModelBiarra
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR1;
    public ModelRenderer LegL1;
    public ModelRenderer EarL;
    public ModelRenderer EarR;
    public ModelRenderer Head2;
    public ModelRenderer Body2;
    public ModelRenderer ChestJewelL;
    public ModelRenderer ChestJewelR;
    public ModelRenderer Body3;
    public ModelRenderer Body3_1;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmJewelR;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;
    public ModelRenderer ArmJewelL;
    public ModelRenderer LegR2;
    public ModelRenderer LegR3;
    public ModelRenderer LegL2;
    public ModelRenderer LegL3;

    public ModelBiarra() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 48);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-5.5f, 15.0f, -2.9f, 11, 6, 7, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 101, 2);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(7.0f, -11.5f, 0.5f);
        this.ArmL1.func_78790_a(0.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.ArmL1, 0.04363323f, 0.0f, 0.0f);
        this.ChestJewelR = new ModelRenderer((ModelBase)this, 38, 0);
        this.ChestJewelR.func_78793_a(-5.6f, 8.8f, -3.3f);
        this.ChestJewelR.func_78790_a(-1.0f, -0.9f, -1.0f, 2, 2, 1, 0.0f);
        this.ArmJewelL = new ModelRenderer((ModelBase)this, 29, 0);
        this.ArmJewelL.field_78809_i = true;
        this.ArmJewelL.func_78793_a(2.8f, 4.7f, 0.4f);
        this.ArmJewelL.func_78790_a(0.0f, -0.9f, -1.0f, 1, 2, 2, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 66, 17);
        this.LegL2.func_78793_a(0.0f, 7.0f, 0.0f);
        this.LegL2.func_78790_a(-3.0f, 0.0f, -2.6f, 6, 9, 6, 0.0f);
        this.setRotateAngle(this.LegL2, 0.04363323f, 0.0f, 0.02617994f);
        this.ChestJewelL = new ModelRenderer((ModelBase)this, 38, 0);
        this.ChestJewelL.func_78793_a(5.6f, 8.8f, -3.3f);
        this.ChestJewelL.func_78790_a(-1.0f, -0.9f, -1.0f, 2, 2, 1, 0.0f);
        this.LegR1 = new ModelRenderer((ModelBase)this, 67, 3);
        this.LegR1.func_78793_a(-3.2f, 6.5f, 0.0f);
        this.LegR1.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 7, 6, 0.0f);
        this.setRotateAngle(this.LegR1, -0.04363323f, 0.0f, 0.05235988f);
        this.LegR3 = new ModelRenderer((ModelBase)this, 61, 35);
        this.LegR3.func_78793_a(0.0f, 8.9f, 0.0f);
        this.LegR3.func_78790_a(-2.5f, 0.0f, -6.0f, 5, 2, 10, 0.0f);
        this.setRotateAngle(this.LegR3, 0.0f, 0.0f, -0.02617994f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 102, 15);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(3.0f, 2.9f, 0.0f);
        this.ArmL2.func_78790_a(-2.2f, -0.1f, -2.5f, 5, 8, 5, 0.0f);
        this.setRotateAngle(this.ArmL2, 0.0f, 0.0f, -0.06981317f);
        this.LegL3 = new ModelRenderer((ModelBase)this, 61, 35);
        this.LegL3.func_78793_a(0.0f, 8.9f, 0.0f);
        this.LegL3.func_78790_a(-2.5f, 0.0f, -6.0f, 5, 2, 10, 0.0f);
        this.setRotateAngle(this.LegL3, 0.0f, 0.0f, 0.02617994f);
        this.LegL1 = new ModelRenderer((ModelBase)this, 67, 3);
        this.LegL1.field_78809_i = true;
        this.LegL1.func_78793_a(3.2f, 6.5f, 0.0f);
        this.LegL1.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 7, 6, 0.0f);
        this.setRotateAngle(this.LegL1, -0.04363323f, 0.0f, -0.05235988f);
        this.Body3_1 = new ModelRenderer((ModelBase)this, 32, 48);
        this.Body3_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3_1.func_78790_a(-4.0f, 11.8f, -3.6f, 8, 4, 1, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 13);
        this.Body1.func_78793_a(0.0f, -14.5f, 0.0f);
        this.Body1.func_78790_a(-7.0f, 0.0f, -3.8f, 14, 12, 9, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 45, 4);
        this.Head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head2.func_78790_a(-1.0f, -5.4f, -2.8f, 2, 1, 7, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 102, 15);
        this.ArmR2.func_78793_a(-3.0f, 2.9f, 0.0f);
        this.ArmR2.func_78790_a(-2.7f, -0.1f, -2.5f, 5, 8, 5, 0.0f);
        this.setRotateAngle(this.ArmR2, 0.0f, 0.0f, 0.06981317f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 101, 29);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 7.3f, -0.5f);
        this.ArmL3.func_78790_a(-2.7f, 0.0f, -2.4f, 6, 13, 6, 0.0f);
        this.setRotateAngle(this.ArmL3, -0.2617994f, 0.0f, (float)Math.PI / 90);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -14.5f, -0.3f);
        this.Head.func_78790_a(-3.5f, -5.0f, -2.3f, 7, 5, 6, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 29, 5);
        this.EarR.func_78793_a(-3.5f, -3.0f, 0.4f);
        this.EarR.func_78790_a(-1.0f, -0.9f, -1.0f, 1, 2, 2, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 66, 17);
        this.LegR2.func_78793_a(0.0f, 7.0f, 0.0f);
        this.LegR2.func_78790_a(-3.0f, 0.0f, -2.6f, 6, 9, 6, 0.0f);
        this.setRotateAngle(this.LegR2, 0.04363323f, 0.0f, -0.02617994f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 101, 29);
        this.ArmR3.func_78793_a(0.0f, 7.3f, -0.5f);
        this.ArmR3.func_78790_a(-3.2f, 0.0f, -2.4f, 6, 13, 6, 0.0f);
        this.setRotateAngle(this.ArmR3, -0.2617994f, 0.0f, (float)(-Math.PI) / 90);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 36);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-6.0f, 12.0f, -3.0f, 12, 3, 7, 0.0f);
        this.ArmJewelR = new ModelRenderer((ModelBase)this, 29, 0);
        this.ArmJewelR.func_78793_a(-2.8f, 4.7f, 0.4f);
        this.ArmJewelR.func_78790_a(-1.0f, -0.9f, -1.0f, 1, 2, 2, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 101, 2);
        this.ArmR1.func_78793_a(-7.0f, -11.5f, 0.5f);
        this.ArmR1.func_78790_a(-6.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.ArmR1, 0.04363323f, 0.0f, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 29, 5);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.5f, -3.0f, 0.4f);
        this.EarL.func_78790_a(0.0f, -0.9f, -1.0f, 1, 2, 2, 0.0f);
        this.Body2.func_78792_a(this.Body3);
        this.Body1.func_78792_a(this.ChestJewelR);
        this.ArmL3.func_78792_a(this.ArmJewelL);
        this.LegL1.func_78792_a(this.LegL2);
        this.Body1.func_78792_a(this.ChestJewelL);
        this.LegR2.func_78792_a(this.LegR3);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.LegL2.func_78792_a(this.LegL3);
        this.Body3.func_78792_a(this.Body3_1);
        this.Head.func_78792_a(this.Head2);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.Head.func_78792_a(this.EarR);
        this.LegR1.func_78792_a(this.LegR2);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Body1.func_78792_a(this.Body2);
        this.ArmR3.func_78792_a(this.ArmJewelR);
        this.Head.func_78792_a(this.EarL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL1.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegR1.func_78785_a(f5);
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
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

