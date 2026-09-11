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

public class ModelKahseral
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Hat;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Mic;

    public ModelKahseral() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer((ModelBase)this, 16, 16);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 33, 8);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(4.0f, -4.2f, 0.4f);
        this.EarL.func_78790_a(0.0f, -1.5f, -1.6f, 1, 3, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 33, 8);
        this.EarR.func_78793_a(-4.0f, -4.2f, 0.4f);
        this.EarR.func_78790_a(-1.0f, -1.5f, -1.6f, 1, 3, 3, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hat = new ModelRenderer((ModelBase)this, 0, 33);
        this.Hat.func_78793_a(0.0f, -6.2f, 0.0f);
        this.Hat.func_78790_a(-4.8f, -2.7f, -4.5f, 9, 3, 9, 0.0f);
        this.setRotateAngle(this.Hat, -0.08726646f, 0.0f, -0.06806784f);
        this.Mic = new ModelRenderer((ModelBase)this, 42, 11);
        this.Mic.func_78793_a(4.0f, -1.7f, -4.0f);
        this.Mic.func_78790_a(-4.0f, -0.4f, -0.1f, 4, 1, 0, 0.0f);
        this.setRotateAngle(this.Mic, 0.0f, -0.06806784f, -0.10646509f);
        this.Head.func_78792_a(this.EarL);
        this.Head.func_78792_a(this.EarR);
        this.Head.func_78792_a(this.Hat);
        this.Head.func_78792_a(this.Mic);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
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

