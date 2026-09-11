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

public class ModelHop
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer tail1;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Hair;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer Hair6;
    public ModelRenderer Hair7;
    public ModelRenderer Hair8;
    public ModelRenderer Hair9;
    public ModelRenderer Hair11;
    public ModelRenderer Body2;
    public ModelRenderer Boobs;
    public ModelRenderer Body3;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;

    public ModelHop() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.tail1 = new ModelRenderer((ModelBase)this, 42, 19);
        this.tail1.func_78793_a(0.0f, 11.4f, 1.6f);
        this.tail1.func_78790_a(-0.5f, -0.3f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail1, 1.0011208f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer((ModelBase)this, 42, 23);
        this.tail4.func_78793_a(0.0f, 2.6f, 0.0f);
        this.tail4.func_78790_a(-0.5f, 0.2f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail4, 0.20507619f, 0.0f, 0.0f);
        this.Boobs = new ModelRenderer((ModelBase)this, 19, 31);
        this.Boobs.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Boobs.func_78790_a(-3.0f, 2.1f, -0.7f, 6, 3, 2, 0.0f);
        this.setRotateAngle(this.Boobs, -0.59184116f, 0.0f, 0.0f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair4.func_78793_a(1.6f, -6.2f, -0.5f);
        this.Hair4.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair4, 0.0f, -0.007853982f, 0.8237954f);
        this.ArmR = new ModelRenderer((ModelBase)this, 32, 35);
        this.ArmR.func_78793_a(-4.3f, 2.8f, 0.0f);
        this.ArmR.func_78790_a(-1.7f, -1.4f, -1.8f, 3, 11, 4, -0.1f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.06981317f);
        this.Hair7 = new ModelRenderer((ModelBase)this, 50, 8);
        this.Hair7.func_78793_a(0.2f, -6.3f, -1.3f);
        this.Hair7.func_78790_a(-0.5f, -2.3f, -0.8f, 1, 2, 2, 0.0f);
        this.setRotateAngle(this.Hair7, 0.0f, 0.0f, 0.3970624f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 1.4f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.5f, -4.0f, 8, 8, 8, -0.5f);
        this.tail2 = new ModelRenderer((ModelBase)this, 42, 19);
        this.tail2.func_78793_a(0.0f, 2.6f, 0.0f);
        this.tail2.func_78790_a(-0.5f, -0.1f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail2, -0.18203785f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer((ModelBase)this, 42, 23);
        this.tail5.func_78793_a(0.0f, 3.1f, 0.0f);
        this.tail5.func_78790_a(-0.5f, -0.1f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail5, 0.27314404f, 0.0f, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 28, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(2.4f, -5.9f, 0.0f);
        this.EarL.func_78790_a(-2.0f, -5.0f, 0.0f, 4, 5, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, 0.0f, 0.13665928f);
        this.ArmL = new ModelRenderer((ModelBase)this, 32, 35);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.3f, 2.8f, 0.0f);
        this.ArmL.func_78790_a(-1.3f, -1.4f, -1.8f, 3, 11, 4, -0.1f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.06981317f);
        this.Hair11 = new ModelRenderer((ModelBase)this, 49, 0);
        this.Hair11.func_78793_a(1.6f, -6.4f, 0.9f);
        this.Hair11.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair11, -0.27314404f, 0.091106184f, 1.4114478f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Body1.func_78793_a(0.0f, 1.2f, 0.0f);
        this.Body1.func_78790_a(-3.5f, 0.0f, -2.0f, 7, 6, 4, -0.2f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.tail3 = new ModelRenderer((ModelBase)this, 42, 19);
        this.tail3.func_78793_a(0.0f, 2.6f, 0.0f);
        this.tail3.func_78790_a(-0.5f, -0.1f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail3, -0.18203785f, 0.0f, 0.0f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair5.func_78793_a(2.4f, -6.2f, -1.3f);
        this.Hair5.func_78790_a(-1.0f, -0.2f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair5, 0.0f, 0.0f, -0.54209924f);
        this.Hair8 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair8.func_78793_a(2.7f, -4.4f, 0.7f);
        this.Hair8.func_78790_a(-1.0f, -0.2f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair8, 0.0f, 0.0f, -0.24137904f);
        this.Hair6 = new ModelRenderer((ModelBase)this, 49, 0);
        this.Hair6.func_78793_a(0.0f, -6.1f, 0.4f);
        this.Hair6.func_78790_a(-1.1f, -3.4f, -0.9f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair6, -0.46617743f, -0.05427974f, -0.82396996f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 37, 6);
        this.Hair2.func_78793_a(0.0f, -6.3f, -1.9f);
        this.Hair2.func_78790_a(-4.7f, -1.6f, -0.8f, 4, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair2, 0.0f, -0.2602286f, 0.043284167f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 30);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.0f, 5.8f, -1.6f, 6, 3, 3, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair1.func_78793_a(-0.6f, -6.8f, -1.3f);
        this.Hair1.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair1, 0.0f, 0.0f, -0.34697145f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair3.func_78793_a(-3.4f, -6.0f, -1.2f);
        this.Hair3.func_78790_a(-1.1f, 0.0f, -0.7f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair3, 0.0f, 0.0f, 0.34697145f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(-0.5f, -3.0f, 0.0f, 1, 1, 1, 0.0f);
        this.Hair9 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair9.func_78793_a(-2.7f, -4.3f, 0.8f);
        this.Hair9.func_78790_a(-1.1f, 0.0f, -0.7f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair9, 0.0f, 0.0f, 0.22828907f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 38);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 8.8f, -2.0f, 7, 2, 4, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 28, 1);
        this.EarR.func_78793_a(-2.4f, -5.9f, 0.0f);
        this.EarR.func_78790_a(-2.0f, -5.0f, 0.0f, 4, 5, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.0f, -0.13665928f);
        this.tail3.func_78792_a(this.tail4);
        this.Body1.func_78792_a(this.Boobs);
        this.Hair.func_78792_a(this.Hair4);
        this.Hair.func_78792_a(this.Hair7);
        this.tail1.func_78792_a(this.tail2);
        this.tail4.func_78792_a(this.tail5);
        this.Head.func_78792_a(this.EarL);
        this.Hair.func_78792_a(this.Hair11);
        this.tail2.func_78792_a(this.tail3);
        this.Hair.func_78792_a(this.Hair5);
        this.Hair.func_78792_a(this.Hair8);
        this.Hair.func_78792_a(this.Hair6);
        this.Hair.func_78792_a(this.Hair2);
        this.Body1.func_78792_a(this.Body2);
        this.Hair.func_78792_a(this.Hair1);
        this.Hair.func_78792_a(this.Hair3);
        this.Head.func_78792_a(this.Hair);
        this.Hair.func_78792_a(this.Hair9);
        this.Body2.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.EarR);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.ArmR.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        this.tail1.func_78785_a(f5);
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
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

