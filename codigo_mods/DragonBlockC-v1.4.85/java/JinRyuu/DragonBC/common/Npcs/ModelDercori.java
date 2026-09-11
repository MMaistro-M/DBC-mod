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

public class ModelDercori
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmL1;
    public ModelRenderer ArmR1;
    public ModelRenderer Hair;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Hair2;
    public ModelRenderer HairFrontL1;
    public ModelRenderer HairFrontR1;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer HairFrontL2;
    public ModelRenderer HairFrontR2;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer Body5;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmR2;

    public ModelDercori() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Hair3 = new ModelRenderer((ModelBase)this, 37, 9);
        this.Hair3.func_78793_a(1.2f, 0.6f, -0.2f);
        this.Hair3.func_78790_a(-1.2f, -6.9f, -1.7f, 2, 6, 3, 0.0f);
        this.setRotateAngle(this.Hair3, -0.5009095f, 0.13665928f, 0.18203785f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 64, 19);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-3.0f, 6.7f, -2.0f, 4, 7, 4, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 64, 6);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(4.7f, -2.0f, 0.0f);
        this.ArmL1.func_78790_a(-1.1f, -1.0f, -2.0f, 4, 8, 4, -0.3f);
        this.setRotateAngle(this.ArmL1, 0.0f, 0.0f, -0.0418879f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 37, 0);
        this.Hair2.func_78793_a(-0.4f, -3.0f, 1.6f);
        this.Hair2.func_78790_a(-1.2f, -6.5f, -1.5f, 2, 6, 3, 0.0f);
        this.setRotateAngle(this.Hair2, -0.045553092f, -0.13665928f, -0.091106184f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 64, 6);
        this.ArmR1.func_78793_a(-4.7f, -2.0f, 0.0f);
        this.ArmR1.func_78790_a(-2.9f, -1.0f, -2.0f, 4, 8, 4, -0.3f);
        this.setRotateAngle(this.ArmR1, 0.0f, 0.0f, 0.0418879f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 48, 11);
        this.Hair4.func_78793_a(-0.7f, -2.0f, 0.0f);
        this.Hair4.func_78790_a(-1.2f, -6.0f, -0.9f, 2, 6, 2, 0.0f);
        this.setRotateAngle(this.Hair4, -1.0016445f, 0.0f, 0.0f);
        this.Body5 = new ModelRenderer((ModelBase)this, 32, 47);
        this.Body5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body5.func_78790_a(-5.0f, 19.0f, -2.6f, 10, 8, 6, 0.0f);
        this.HairFrontR2 = new ModelRenderer((ModelBase)this, 54, 5);
        this.HairFrontR2.func_78793_a(0.1f, 0.1f, -0.8f);
        this.HairFrontR2.func_78790_a(-3.3f, -0.6f, -0.1f, 4, 8, 0, 0.0f);
        this.setRotateAngle(this.HairFrontR2, 0.0f, 0.0f, 0.17627825f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Body1.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 6, 4, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 0, 50);
        this.Body4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body4.func_78790_a(-4.5f, 13.0f, -2.3f, 9, 6, 5, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 64, 19);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-1.0f, 6.7f, -2.0f, 4, 7, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -2.8f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.2f);
        this.Hair = new ModelRenderer((ModelBase)this, 26, 0);
        this.Hair.func_78793_a(0.0f, -6.2f, -0.4f);
        this.Hair.func_78790_a(-1.5f, -4.1f, 1.0f, 3, 2, 2, 0.0f);
        this.setRotateAngle(this.Hair, -0.7285004f, 0.045553092f, 0.0f);
        this.HairFrontL1 = new ModelRenderer((ModelBase)this, 54, 1);
        this.HairFrontL1.func_78793_a(1.5f, 1.5f, -3.0f);
        this.HairFrontL1.func_78790_a(-1.0f, -0.3f, -0.8f, 2, 1, 1, 0.0f);
        this.setRotateAngle(this.HairFrontL1, 0.6981317f, -0.2617994f, -0.08726646f);
        this.HairFrontL2 = new ModelRenderer((ModelBase)this, 54, 5);
        this.HairFrontL2.field_78809_i = true;
        this.HairFrontL2.func_78793_a(0.1f, 0.1f, -0.8f);
        this.HairFrontL2.func_78790_a(-0.8f, -0.6f, -0.1f, 4, 8, 0, 0.0f);
        this.setRotateAngle(this.HairFrontL2, 0.0f, 0.0f, -0.25307274f);
        this.HairFrontR1 = new ModelRenderer((ModelBase)this, 54, 1);
        this.HairFrontR1.func_78793_a(-1.5f, 1.5f, -3.0f);
        this.HairFrontR1.func_78790_a(-1.0f, -0.3f, -0.8f, 2, 1, 1, 0.0f);
        this.setRotateAngle(this.HairFrontR1, 0.6981317f, 0.18325958f, 0.08726646f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.5f, 6.0f, -1.6f, 7, 3, 3, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 40);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 4, 4, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 0, 0);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.5f, -3.0f, -1.5f);
        this.EarL.func_78790_a(0.0f, -3.0f, 0.0f, 4, 5, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.7853982f, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 0, 0);
        this.EarR.func_78793_a(-3.5f, -3.0f, -1.5f);
        this.EarR.func_78790_a(-4.0f, -3.0f, 0.0f, 4, 5, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.7853982f, 0.0f);
        this.Hair2.func_78792_a(this.Hair3);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.Hair.func_78792_a(this.Hair2);
        this.Hair3.func_78792_a(this.Hair4);
        this.Body4.func_78792_a(this.Body5);
        this.HairFrontR1.func_78792_a(this.HairFrontR2);
        this.Body3.func_78792_a(this.Body4);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.Head.func_78792_a(this.Hair);
        this.Hair.func_78792_a(this.HairFrontL1);
        this.HairFrontL1.func_78792_a(this.HairFrontL2);
        this.Hair.func_78792_a(this.HairFrontR1);
        this.Body1.func_78792_a(this.Body2);
        this.Body2.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.EarL);
        this.Head.func_78792_a(this.EarR);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.ArmL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.Head.func_78785_a(f5);
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
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

