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

public class ModelGanos2
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hair1;
    public ModelRenderer Beak1;
    public ModelRenderer Hat2;
    public ModelRenderer Hair2;
    public ModelRenderer Beak2;
    public ModelRenderer Beak3;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Chest;
    public ModelRenderer FeathersR;
    public ModelRenderer FeathersL;
    public ModelRenderer LegR2;
    public ModelRenderer LegL2;

    public ModelGanos2() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Hair2 = new ModelRenderer((ModelBase)this, 96, 32);
        this.Hair2.field_78809_i = true;
        this.Hair2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair2.func_78790_a(-6.0f, -3.8f, -0.1f, 12, 10, 0, 0.0f);
        this.Beak1 = new ModelRenderer((ModelBase)this, 33, 8);
        this.Beak1.func_78793_a(0.0f, -3.4f, -3.4f);
        this.Beak1.func_78790_a(-2.5f, 0.9f, -1.9f, 5, 2, 2, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 66, 42);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.5f, 7.0f, -2.0f, 5, 9, 6, 0.0f);
        this.FeathersL = new ModelRenderer((ModelBase)this, 72, 21);
        this.FeathersL.func_78793_a(3.3f, 4.6f, 2.2f);
        this.FeathersL.func_78790_a(0.0f, -4.8f, -0.3f, 0, 7, 5, 0.0f);
        this.setRotateAngle(this.FeathersL, 0.0f, 0.6981317f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 41, 42);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(3.0f, 8.0f, 0.0f);
        this.LegL.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 7, 6, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 66, 42);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.5f, 7.0f, -2.0f, 5, 9, 6, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, -6.7f, 0.0f);
        this.Body1.func_78790_a(-6.0f, 0.0f, -3.3f, 12, 8, 7, 0.0f);
        this.Beak2 = new ModelRenderer((ModelBase)this, 48, 8);
        this.Beak2.func_78793_a(0.0f, 0.9f, -1.1f);
        this.Beak2.func_78790_a(-2.0f, -1.1f, -2.4f, 4, 3, 4, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this, 40, 26);
        this.Chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest.func_78790_a(-5.5f, 0.9f, -4.1f, 11, 5, 1, 0.0f);
        this.FeathersR = new ModelRenderer((ModelBase)this, 72, 21);
        this.FeathersR.func_78793_a(-3.5f, 4.6f, 2.2f);
        this.FeathersR.func_78790_a(0.0f, -4.8f, -0.3f, 0, 7, 5, 0.0f);
        this.setRotateAngle(this.FeathersR, 0.0f, -0.6981317f, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 48);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-5.5f, 10.7f, -3.0f, 11, 4, 6, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 72, 3);
        this.ArmR.func_78793_a(-6.0f, -5.0f, 0.0f);
        this.ArmR.func_78790_a(-5.0f, -2.0f, -3.0f, 5, 16, 5, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 41, 42);
        this.LegR.func_78793_a(-3.0f, 8.0f, 0.0f);
        this.LegR.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 7, 6, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-5.0f, 6.8f, -3.1f, 10, 4, 6, 0.0f);
        this.Hat2 = new ModelRenderer((ModelBase)this, 41, 16);
        this.Hat2.func_78793_a(0.0f, -6.3f, -0.5f);
        this.Hat2.func_78790_a(-3.5f, -1.0f, -3.0f, 7, 1, 7, 0.0f);
        this.setRotateAngle(this.Hat2, 0.14014994f, 0.0f, 0.0f);
        this.Beak3 = new ModelRenderer((ModelBase)this, 48, 1);
        this.Beak3.func_78793_a(0.0f, 0.1f, 0.0f);
        this.Beak3.func_78790_a(-1.0f, -1.6f, -1.7f, 2, 2, 4, 0.0f);
        this.setRotateAngle(this.Beak3, 0.4098033f, 0.0f, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 96, 2);
        this.Hair1.func_78793_a(0.0f, -4.2f, 3.6f);
        this.Hair1.func_78790_a(-7.5f, -7.3f, 0.0f, 15, 26, 0, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -6.5f, 0.3f);
        this.Head.func_78790_a(-4.0f, -7.0f, -3.5f, 8, 7, 7, -0.2f);
        this.ArmL = new ModelRenderer((ModelBase)this, 72, 3);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(6.0f, -5.0f, 0.0f);
        this.ArmL.func_78790_a(0.0f, -2.0f, -3.0f, 5, 16, 5, 0.0f);
        this.Hair1.func_78792_a(this.Hair2);
        this.Head.func_78792_a(this.Beak1);
        this.LegL.func_78792_a(this.LegL2);
        this.ArmL.func_78792_a(this.FeathersL);
        this.LegR.func_78792_a(this.LegR2);
        this.Beak1.func_78792_a(this.Beak2);
        this.Body2.func_78792_a(this.Chest);
        this.ArmR.func_78792_a(this.FeathersR);
        this.Body2.func_78792_a(this.Body3);
        this.Body1.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.Hat2);
        this.Beak2.func_78792_a(this.Beak3);
        this.Head.func_78792_a(this.Hair1);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
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
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
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

