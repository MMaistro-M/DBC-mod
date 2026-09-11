/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.db;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelUpa
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Feather;
    public ModelRenderer Hair;

    public ModelUpa() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.ArmL = new ModelRenderer((ModelBase)this, 17, 33);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 9.7f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -0.6f, -1.5f, 3, 7, 3, 0.2f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 8.9f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -3.8f, 8, 8, 7, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 17, 33);
        this.ArmR.func_78793_a(-5.0f, 9.7f, 0.0f);
        this.ArmR.func_78790_a(-2.0f, -0.6f, -1.4f, 3, 7, 3, 0.2f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 17.3f, -0.1f);
        this.LegL.func_78790_a(-2.0f, -0.4f, -2.0f, 4, 7, 4, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 31, 0);
        this.Hair.func_78793_a(0.0f, -0.5f, 3.0f);
        this.Hair.func_78790_a(-1.0f, 0.0f, 0.0f, 2, 10, 0, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 15);
        this.Body.func_78793_a(0.0f, 8.9f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.1f, 8, 8, 4, 0.0f);
        this.Feather = new ModelRenderer((ModelBase)this, 35, 0);
        this.Feather.func_78793_a(0.0f, -8.3f, 2.8f);
        this.Feather.func_78790_a(-1.0f, -5.6f, 0.0f, 2, 6, 0, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegR.func_78793_a(-2.0f, 17.3f, -0.1f);
        this.LegR.func_78790_a(-2.0f, -0.4f, -2.0f, 4, 7, 4, 0.0f);
        this.Head.func_78792_a(this.Hair);
        this.Head.func_78792_a(this.Feather);
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
        this.Hair.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (0.0f > this.Hair.field_78795_f) {
            this.Hair.field_78795_f *= -1.0f;
        }
        this.Hair.field_78796_g = 0.0f;
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

