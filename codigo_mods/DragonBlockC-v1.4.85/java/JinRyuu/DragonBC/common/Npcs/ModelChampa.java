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

public class ModelChampa
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer EarL;
    public ModelRenderer EarR;
    public ModelRenderer Snout1;
    public ModelRenderer EarL_1;
    public ModelRenderer Snout2;
    public ModelRenderer SnoutSideR;
    public ModelRenderer SnoutSideL;
    public ModelRenderer Cloth1;
    public ModelRenderer tail1;
    public ModelRenderer tail2;

    public ModelChampa() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.LegR = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.3f, 0.0f, -2.0f, 4, 12, 4, 0.2f);
        this.ArmL = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 1.7f, 0.1f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 3, 12, 4, 0.0f);
        this.tail2 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail2.func_78793_a(0.0f, 2.6f, 0.0f);
        this.tail2.func_78790_a(-0.5f, -0.1f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail2, 0.4553564f, 0.0f, 0.0f);
        this.SnoutSideL = new ModelRenderer((ModelBase)this, 47, 7);
        this.SnoutSideL.field_78809_i = true;
        this.SnoutSideL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.SnoutSideL.func_78790_a(-1.7f, -2.8f, -5.0f, 2, 2, 1, 0.0f);
        this.setRotateAngle(this.SnoutSideL, 0.0f, -0.5934119f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-1.7f, 0.0f, -2.0f, 4, 12, 4, 0.2f);
        this.SnoutSideR = new ModelRenderer((ModelBase)this, 47, 7);
        this.SnoutSideR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.SnoutSideR.func_78790_a(-0.2f, -2.8f, -5.0f, 2, 2, 1, 0.0f);
        this.setRotateAngle(this.SnoutSideR, 0.0f, 0.5934119f, 0.0f);
        this.Cloth1 = new ModelRenderer((ModelBase)this, 54, 1);
        this.Cloth1.func_78793_a(0.0f, 11.2f, -2.4f);
        this.Cloth1.func_78790_a(-2.5f, 0.1f, 0.0f, 5, 8, 0, 0.0f);
        this.setRotateAngle(this.Cloth1, -0.057595864f, 0.0f, 0.0f);
        this.Snout2 = new ModelRenderer((ModelBase)this, 43, 7);
        this.Snout2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Snout2.func_78790_a(-0.5f, 1.7f, -5.2f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Snout2, -1.0927507f, 0.0f, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 32, 0);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL.func_78790_a(0.8f, -14.9f, 1.9f, 4, 10, 1, 0.0f);
        this.setRotateAngle(this.EarL, 0.10995574f, 0.0f, 0.0f);
        this.Snout1 = new ModelRenderer((ModelBase)this, 41, 10);
        this.Snout1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Snout1.func_78790_a(-1.5f, -3.0f, -5.9f, 3, 3, 2, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail1.func_78793_a(0.0f, 11.9f, 2.3f);
        this.tail1.func_78790_a(-0.5f, -0.3f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.tail1, 1.0011208f, 0.0f, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 32, 0);
        this.EarR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR.func_78790_a(-4.8f, -14.9f, 1.9f, 4, 10, 1, 0.0f);
        this.setRotateAngle(this.EarR, 0.10995574f, 0.0f, 0.0f);
        this.EarL_1 = new ModelRenderer((ModelBase)this, 0, 1);
        this.EarL_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL_1.func_78790_a(-5.1f, -11.8f, 1.4f, 1, 1, 2, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 16, 16);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.4f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -0.6f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.6f, -4.0f, 8, 8, 8, -0.2f);
        this.ArmR = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmR.func_78793_a(-5.0f, 1.7f, 0.1f);
        this.ArmR.func_78790_a(-2.0f, -2.0f, -2.0f, 3, 12, 4, 0.0f);
        this.tail1.func_78792_a(this.tail2);
        this.Snout1.func_78792_a(this.SnoutSideL);
        this.Snout1.func_78792_a(this.SnoutSideR);
        this.Body.func_78792_a(this.Cloth1);
        this.Snout1.func_78792_a(this.Snout2);
        this.Head.func_78792_a(this.EarL);
        this.Head.func_78792_a(this.Snout1);
        this.Body.func_78792_a(this.tail1);
        this.Head.func_78792_a(this.EarR);
        this.EarL.func_78792_a(this.EarL_1);
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
        this.LegR.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.ArmR.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.LegR.field_78796_g = 0.0f;
        this.LegL.field_78796_g = 0.0f;
        this.ArmR.field_78796_g = 0.0f;
        this.ArmL.field_78796_g = 0.0f;
        this.Cloth1.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

