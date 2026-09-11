/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbkingpiccolo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDrum
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Head_1;
    public ModelRenderer Body2;
    public ModelRenderer Chest;
    public ModelRenderer Cloth2;
    public ModelRenderer Cloth;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;

    public ModelDrum() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.LegL = new ModelRenderer((ModelBase)this, 96, 33);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(3.6f, 10.0f, 1.0f);
        this.LegL.func_78790_a(-3.5f, 0.0f, -3.5f, 7, 14, 8, 0.1f);
        this.Cloth2 = new ModelRenderer((ModelBase)this, 50, 42);
        this.Cloth2.func_78793_a(0.0f, 12.0f, -3.1f);
        this.Cloth2.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 6, 0, 0.0f);
        this.setRotateAngle(this.Cloth2, -0.05235988f, 0.0f, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 32, 1);
        this.EarR.func_78793_a(-3.5f, -4.4f, -1.0f);
        this.EarR.func_78790_a(-3.5f, -2.4f, 0.0f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.87266463f, -0.04363323f);
        this.Chest = new ModelRenderer((ModelBase)this, 47, 31);
        this.Chest.func_78793_a(0.0f, 4.2f, -1.5f);
        this.Chest.func_78790_a(-7.0f, -2.1f, -1.4f, 14, 5, 2, 0.0f);
        this.setRotateAngle(this.Chest, -0.05235988f, 0.0f, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 92, 1);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(8.8f, -2.5f, 1.7f);
        this.ArmL1.func_78790_a(-1.5f, -2.0f, -2.5f, 5, 8, 5, 0.0f);
        this.setRotateAngle(this.ArmL1, 0.0f, 0.0f, -0.20943952f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -4.4f, 0.3f);
        this.Head.func_78790_a(-4.0f, -6.0f, -4.0f, 8, 6, 8, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 96, 33);
        this.LegR.func_78793_a(-3.6f, 10.0f, 1.0f);
        this.LegR.func_78790_a(-3.5f, 0.0f, -3.5f, 7, 14, 8, 0.1f);
        this.Head_1 = new ModelRenderer((ModelBase)this, 35, 0);
        this.Head_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head_1.func_78790_a(0.0f, -7.5f, -3.1f, 0, 6, 8, 0.0f);
        this.Cloth = new ModelRenderer((ModelBase)this, 2, 51);
        this.Cloth.func_78793_a(0.0f, 12.9f, 3.0f);
        this.Cloth.func_78790_a(-8.0f, -0.9f, -2.9f, 16, 7, 6, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 90, 15);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.6f, 5.3f, -0.3f);
        this.ArmL2.func_78790_a(-2.5f, -0.5f, -2.5f, 6, 9, 6, 0.0f);
        this.setRotateAngle(this.ArmL2, -0.4098033f, 0.0f, 0.08726646f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 90, 15);
        this.ArmR2.func_78793_a(-0.6f, 5.3f, -0.3f);
        this.ArmR2.func_78790_a(-3.5f, -0.5f, -2.5f, 6, 9, 6, 0.0f);
        this.setRotateAngle(this.ArmR2, -0.4098033f, 0.0f, -0.08726646f);
        this.EarL = new ModelRenderer((ModelBase)this, 32, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.8f, -4.4f, -1.0f);
        this.EarL.func_78790_a(0.0f, -2.4f, 0.0f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.87266463f, 0.04363323f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-7.5f, 8.0f, -3.1f, 15, 7, 9, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 16);
        this.Body1.func_78793_a(0.0f, -5.0f, 0.0f);
        this.Body1.func_78790_a(-7.5f, 0.0f, -2.4f, 15, 8, 8, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 92, 1);
        this.ArmR1.func_78793_a(-8.8f, -2.5f, 1.7f);
        this.ArmR1.func_78790_a(-3.5f, -2.0f, -2.5f, 5, 8, 5, 0.0f);
        this.setRotateAngle(this.ArmR1, 0.0f, 0.0f, 0.20943952f);
        this.Body2.func_78792_a(this.Cloth2);
        this.Head.func_78792_a(this.EarR);
        this.Body1.func_78792_a(this.Chest);
        this.Head.func_78792_a(this.Head_1);
        this.Body2.func_78792_a(this.Cloth);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.Head.func_78792_a(this.EarL);
        this.Body1.func_78792_a(this.Body2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
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
        this.LegL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        this.Cloth2.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

