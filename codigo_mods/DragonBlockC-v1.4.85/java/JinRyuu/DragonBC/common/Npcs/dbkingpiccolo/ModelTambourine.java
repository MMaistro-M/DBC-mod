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

public class ModelTambourine
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Head2;
    public ModelRenderer EarL;
    public ModelRenderer EarR;
    public ModelRenderer Cloth;
    public ModelRenderer WingL;
    public ModelRenderer WingR;
    public ModelRenderer Cloth2;

    public ModelTambourine() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Cloth2 = new ModelRenderer((ModelBase)this, 41, 0);
        this.Cloth2.func_78793_a(0.0f, 8.5f, -2.1f);
        this.Cloth2.func_78790_a(-3.5f, 0.0f, 0.0f, 7, 6, 0, 0.0f);
        this.setRotateAngle(this.Cloth2, -0.05235988f, 0.0f, 0.0f);
        this.Cloth = new ModelRenderer((ModelBase)this, 55, 3);
        this.Cloth.func_78793_a(0.0f, 9.0f, 0.4f);
        this.Cloth.func_78790_a(-4.5f, 0.0f, -2.3f, 9, 6, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 27, 17);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.WingR = new ModelRenderer((ModelBase)this, 29, 39);
        this.WingR.func_78793_a(-3.0f, 3.0f, 2.6f);
        this.WingR.func_78790_a(-19.0f, -14.0f, 0.0f, 21, 22, 0, 0.0f);
        this.setRotateAngle(this.WingR, 0.0f, 0.17453292f, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 32, 1);
        this.EarR.func_78793_a(-3.5f, -4.4f, -1.0f);
        this.EarR.func_78790_a(-3.5f, -2.4f, 0.0f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.87266463f, -0.04363323f);
        this.Head2 = new ModelRenderer((ModelBase)this, 35, -1);
        this.Head2.func_78793_a(0.0f, -4.8f, 0.9f);
        this.Head2.func_78790_a(0.0f, -4.0f, -4.0f, 0, 8, 8, 0.0f);
        this.WingL = new ModelRenderer((ModelBase)this, 29, 39);
        this.WingL.field_78809_i = true;
        this.WingL.func_78793_a(1.0f, 3.0f, 2.6f);
        this.WingL.func_78790_a(0.0f, -14.0f, 0.0f, 21, 22, 0, 0.0f);
        this.setRotateAngle(this.WingL, 0.0f, -0.17453292f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 27, 17);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 2, 39);
        this.LegR.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 2, 39);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 32, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.8f, -4.4f, -1.0f);
        this.EarL.func_78790_a(0.0f, -2.4f, 0.0f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.87266463f, 0.04363323f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 19);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.Body.func_78792_a(this.Cloth2);
        this.Body.func_78792_a(this.Cloth);
        this.Body.func_78792_a(this.WingR);
        this.Head.func_78792_a(this.EarR);
        this.Head.func_78792_a(this.Head2);
        this.Body.func_78792_a(this.WingL);
        this.Head.func_78792_a(this.EarL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
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
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
        this.Cloth2.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

