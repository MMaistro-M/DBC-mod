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

public class ModelPuar
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer EarR2;
    public ModelRenderer EarL2;
    public ModelRenderer Body2;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;

    public ModelPuar() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.EarL2 = new ModelRenderer((ModelBase)this, 31, 7);
        this.EarL2.field_78809_i = true;
        this.EarL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL2.func_78790_a(0.4f, -9.3f, 0.0f, 3, 4, 0, 0.0f);
        this.setRotateAngle(this.EarL2, -0.006981317f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail4.func_78793_a(0.0f, 0.2f, 2.6f);
        this.tail4.func_78790_a(-1.0f, -1.1f, 0.0f, 2, 2, 3, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 31, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL.func_78790_a(0.4f, -10.5f, 0.1f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.10995574f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail3.func_78793_a(0.0f, 0.0f, 2.6f);
        this.tail3.func_78790_a(-1.0f, -0.9f, -0.1f, 2, 2, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 4, 37);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.4f, 22.0f, 0.3f);
        this.LegL.func_78790_a(-1.5f, 0.0f, -4.3f, 3, 2, 6, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail1.func_78793_a(0.0f, 5.0f, 3.3f);
        this.tail1.func_78790_a(-1.0f, -0.8f, -0.8f, 2, 2, 3, 0.0f);
        this.tail2 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail2.func_78793_a(0.0f, 0.1f, 2.2f);
        this.tail2.func_78790_a(-1.0f, -0.9f, -0.2f, 2, 2, 3, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 31, 1);
        this.EarR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR.func_78790_a(-3.4f, -10.5f, -0.1f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.10995574f, 0.0f, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 4, 37);
        this.LegR.func_78793_a(-2.4f, 22.0f, 0.3f);
        this.LegR.func_78790_a(-1.5f, 0.0f, -4.3f, 3, 2, 6, 0.0f);
        this.tail5 = new ModelRenderer((ModelBase)this, 52, 0);
        this.tail5.func_78793_a(0.0f, -0.1f, 2.9f);
        this.tail5.func_78790_a(-1.0f, -1.0f, -0.1f, 2, 2, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 15.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -5.7f, -4.1f, 8, 6, 7, -0.2f);
        this.Body1 = new ModelRenderer((ModelBase)this, 3, 15);
        this.Body1.func_78793_a(0.0f, 14.7f, 0.0f);
        this.Body1.func_78790_a(-3.5f, 0.3f, -2.5f, 7, 3, 5, 0.0f);
        this.EarR2 = new ModelRenderer((ModelBase)this, 31, 7);
        this.EarR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR2.func_78790_a(-3.4f, -9.3f, -0.2f, 3, 4, 0, 0.0f);
        this.setRotateAngle(this.EarR2, -0.006981317f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 1, 24);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-4.0f, 3.3f, -3.0f, 8, 4, 6, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 33, 17);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(3.8f, 15.9f, 0.0f);
        this.ArmL.func_78790_a(-0.6f, -0.6f, -1.5f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.21327923f);
        this.ArmR = new ModelRenderer((ModelBase)this, 33, 17);
        this.ArmR.func_78793_a(-3.8f, 15.9f, 0.0f);
        this.ArmR.func_78790_a(-1.5f, -0.6f, -1.4f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.1972222f);
        this.EarL.func_78792_a(this.EarL2);
        this.tail3.func_78792_a(this.tail4);
        this.Head.func_78792_a(this.EarL);
        this.tail2.func_78792_a(this.tail3);
        this.Body1.func_78792_a(this.tail1);
        this.tail1.func_78792_a(this.tail2);
        this.Head.func_78792_a(this.EarR);
        this.tail4.func_78792_a(this.tail5);
        this.EarR.func_78792_a(this.EarR2);
        this.Body1.func_78792_a(this.Body2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
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
        this.tail1.field_78795_f = 0.2f;
        this.tail1.field_78795_f += r4 / 2.0f;
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
    }
}

