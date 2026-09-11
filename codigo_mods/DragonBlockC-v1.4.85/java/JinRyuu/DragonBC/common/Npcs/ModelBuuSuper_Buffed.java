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

public class ModelBuuSuper_Buffed
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Head2;
    public ModelRenderer Head3;
    public ModelRenderer Head4;
    public ModelRenderer Hips;
    public ModelRenderer Torso;
    public ModelRenderer ChestR;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;
    public ModelRenderer FootR;
    public ModelRenderer FootL;

    public ModelBuuSuper_Buffed() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.ArmR2 = new ModelRenderer((ModelBase)this, 23, 49);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-3.4f, 2.4f, -2.2f, 4, 10, 5, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 29);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 8.6f, 0.5f);
        this.LegL.func_78790_a(-1.9f, 2.7f, -3.0f, 5, 7, 6, 0.1f);
        this.Torso = new ModelRenderer((ModelBase)this, 28, 10);
        this.Torso.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Torso.func_78790_a(-5.5f, 0.3f, -2.5f, 11, 6, 7, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -2.8f, 0.8f);
        this.Head.func_78790_a(-4.0f, -7.5f, -4.0f, 8, 8, 8, -0.6f);
        this.ArmR = new ModelRenderer((ModelBase)this, 42, 52);
        this.ArmR.func_78793_a(-6.1f, -1.4f, 0.9f);
        this.ArmR.func_78790_a(-3.8f, -2.3f, -2.7f, 5, 5, 6, -0.1f);
        this.Head4 = new ModelRenderer((ModelBase)this, 36, 0);
        this.Head4.func_78793_a(0.0f, -0.1f, 2.4f);
        this.Head4.func_78790_a(-0.5f, -0.5f, -0.3f, 1, 1, 3, 0.0f);
        this.setRotateAngle(this.Head4, -0.63739425f, 0.0f, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 42, 52);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(6.2f, -1.4f, 0.9f);
        this.ArmL.func_78790_a(-1.2f, -2.3f, -2.7f, 5, 5, 6, -0.1f);
        this.ChestR = new ModelRenderer((ModelBase)this, 0, 17);
        this.ChestR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ChestR.func_78790_a(-5.0f, 1.4f, -2.9f, 10, 4, 3, 0.0f);
        this.setRotateAngle(this.ChestR, -0.06492625f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 27, 24);
        this.Body.func_78793_a(0.0f, -3.3f, 0.0f);
        this.Body.func_78790_a(-4.0f, 2.9f, -2.8f, 8, 7, 6, 0.0f);
        this.setRotateAngle(this.Body, 0.0f, 0.0f, 0.006981317f);
        this.Hips = new ModelRenderer((ModelBase)this, 23, 38);
        this.Hips.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hips.func_78790_a(-4.5f, 9.3f, -2.5f, 9, 3, 6, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 29);
        this.LegR.func_78793_a(-1.9f, 8.6f, 0.5f);
        this.LegR.func_78790_a(-3.2f, 2.7f, -3.0f, 5, 7, 6, 0.1f);
        this.FootR = new ModelRenderer((ModelBase)this, 0, 43);
        this.FootR.field_78809_i = true;
        this.FootR.func_78793_a(0.3f, 0.0f, 0.0f);
        this.FootR.func_78790_a(-3.3f, 0.4f, -3.0f, 5, 15, 6, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 25, 0);
        this.Head2.func_78793_a(0.0f, -6.6f, -1.7f);
        this.Head2.func_78790_a(-1.0f, -3.4f, -1.3f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Head2, -0.5677556f, 0.0f, 0.0f);
        this.Head3 = new ModelRenderer((ModelBase)this, 36, 0);
        this.Head3.func_78793_a(0.0f, -3.5f, 0.1f);
        this.Head3.func_78790_a(-0.5f, -1.0f, -0.6f, 1, 2, 3, 0.0f);
        this.setRotateAngle(this.Head3, 1.1383038f, 0.0f, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 23, 49);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-0.8f, 2.4f, -2.2f, 4, 10, 5, 0.0f);
        this.FootL = new ModelRenderer((ModelBase)this, 0, 43);
        this.FootL.func_78793_a(0.3f, 0.0f, 0.0f);
        this.FootL.func_78790_a(-2.4f, 0.4f, -3.0f, 5, 15, 6, 0.0f);
        this.ArmR.func_78792_a(this.ArmR2);
        this.Body.func_78792_a(this.Torso);
        this.Head3.func_78792_a(this.Head4);
        this.Torso.func_78792_a(this.ChestR);
        this.Body.func_78792_a(this.Hips);
        this.LegR.func_78792_a(this.FootR);
        this.Head.func_78792_a(this.Head2);
        this.Head2.func_78792_a(this.Head3);
        this.ArmL.func_78792_a(this.ArmL2);
        this.LegL.func_78792_a(this.FootL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body.func_78785_a(f5);
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

