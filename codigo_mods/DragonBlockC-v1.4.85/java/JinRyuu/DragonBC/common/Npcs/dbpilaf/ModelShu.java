/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbpilaf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelShu
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer tail1;
    public ModelRenderer Head_1;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Sword;
    public ModelRenderer Sword2;
    public ModelRenderer LegL_1;
    public ModelRenderer LegR_1;
    public ModelRenderer tail2;
    public ModelRenderer tail3;

    public ModelShu() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.LegR = new ModelRenderer((ModelBase)this, 1, 42);
        this.LegR.func_78793_a(-1.9f, 17.0f, 0.0f);
        this.LegR.func_78790_a(-2.1f, 0.0f, -2.4f, 4, 5, 5, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 31, 1);
        this.EarR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR.func_78790_a(-3.4f, -10.4f, -0.1f, 3, 4, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.10995574f, 0.0f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 1, 42);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 17.0f, 0.0f);
        this.LegL.func_78790_a(-1.9f, 0.0f, -2.4f, 4, 5, 5, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 31, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL.func_78790_a(0.4f, -10.4f, 0.1f, 3, 4, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.10995574f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 42, 1);
        this.tail1.func_78793_a(0.0f, 16.1f, 2.7f);
        this.tail1.func_78790_a(-1.0f, -1.0f, -0.4f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.tail1, -0.01379388f, 0.0f, 0.0f);
        this.Sword = new ModelRenderer((ModelBase)this, 27, 16);
        this.Sword.func_78793_a(-0.7f, 2.6f, 3.5f);
        this.Sword.func_78790_a(-8.8f, -1.0f, 0.2f, 18, 2, 0, 0.0f);
        this.setRotateAngle(this.Sword, 0.0f, 0.098262034f, 0.63739425f);
        this.LegL_1 = new ModelRenderer((ModelBase)this, 2, 53);
        this.LegL_1.field_78809_i = true;
        this.LegL_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL_1.func_78790_a(-1.5f, 5.0f, -2.0f, 3, 2, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 1, 31);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.5f, 9.0f, 0.0f);
        this.ArmL.func_78790_a(-0.6f, -0.6f, -1.5f, 3, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.04363323f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 8.3f, 0.0f);
        this.Head.func_78790_a(-4.0f, -6.8f, -4.1f, 8, 7, 7, -0.2f);
        this.ArmR = new ModelRenderer((ModelBase)this, 1, 31);
        this.ArmR.func_78793_a(-4.6f, 9.0f, 0.0f);
        this.ArmR.func_78790_a(-2.3f, -0.6f, -1.4f, 3, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.04363323f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 15);
        this.Body.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.3f, -3.0f, 8, 9, 6, 0.0f);
        this.tail2 = new ModelRenderer((ModelBase)this, 48, 2);
        this.tail2.func_78793_a(0.0f, 0.1f, 1.4f);
        this.tail2.func_78790_a(-1.5f, -1.4f, -0.5f, 3, 3, 5, 0.0f);
        this.setRotateAngle(this.tail2, 0.06021386f, 0.0f, 0.0f);
        this.Sword2 = new ModelRenderer((ModelBase)this, 31, 17);
        this.Sword2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Sword2.func_78790_a(-3.9f, -2.0f, -0.7f, 0, 4, 2, 0.0f);
        this.tail3 = new ModelRenderer((ModelBase)this, 53, 10);
        this.tail3.func_78793_a(0.0f, 0.0f, 4.2f);
        this.tail3.func_78790_a(-1.0f, -0.9f, -0.6f, 2, 2, 3, 0.0f);
        this.Head_1 = new ModelRenderer((ModelBase)this, 32, 7);
        this.Head_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head_1.func_78790_a(-1.5f, -2.9f, -5.9f, 3, 3, 2, 0.0f);
        this.LegR_1 = new ModelRenderer((ModelBase)this, 2, 53);
        this.LegR_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR_1.func_78790_a(-1.5f, 5.0f, -2.0f, 3, 2, 4, 0.0f);
        this.Head.func_78792_a(this.EarR);
        this.Head.func_78792_a(this.EarL);
        this.Body.func_78792_a(this.Sword);
        this.LegL.func_78792_a(this.LegL_1);
        this.tail1.func_78792_a(this.tail2);
        this.Sword.func_78792_a(this.Sword2);
        this.tail2.func_78792_a(this.tail3);
        this.Head.func_78792_a(this.Head_1);
        this.LegR.func_78792_a(this.LegR_1);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
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

