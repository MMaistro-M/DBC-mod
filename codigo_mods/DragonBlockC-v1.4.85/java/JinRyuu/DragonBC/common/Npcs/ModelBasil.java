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

public class ModelBasil
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Tail1;
    public ModelRenderer Snout;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer EarR2;
    public ModelRenderer EarL2;
    public ModelRenderer Body2;
    public ModelRenderer Chest;
    public ModelRenderer Scarf;
    public ModelRenderer Fur;
    public ModelRenderer Body3;
    public ModelRenderer Back;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmL2;
    public ModelRenderer LegL2;
    public ModelRenderer LegR2;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer Tail5;

    public ModelBasil() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Back = new ModelRenderer((ModelBase)this, 44, 48);
        this.Back.func_78793_a(0.0f, 1.6f, 3.0f);
        this.Back.func_78790_a(-5.0f, 0.4f, 0.0f, 10, 12, 0, 0.0f);
        this.setRotateAngle(this.Back, -0.091106184f, 0.0f, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 0, 53);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.0f, 6.0f, -0.6f, 4, 7, 4, 0.0f);
        this.Tail5 = new ModelRenderer((ModelBase)this, 54, 38);
        this.Tail5.func_78793_a(0.0f, 0.0f, 2.9f);
        this.Tail5.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 3, 0.0f);
        this.setRotateAngle(this.Tail5, 0.13665928f, 0.0f, 0.0f);
        this.Tail1 = new ModelRenderer((ModelBase)this, 54, 9);
        this.Tail1.func_78793_a(0.0f, 8.2f, 1.8f);
        this.Tail1.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 3, 0.0f);
        this.setRotateAngle(this.Tail1, -0.4098033f, 0.0f, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 27, 2);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.1f, -6.1f, 0.0f);
        this.EarL.func_78790_a(-2.0f, -4.0f, 0.0f, 4, 6, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.27925268f, 0.6981317f);
        this.Body3 = new ModelRenderer((ModelBase)this, 19, 40);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 10.7f, -2.0f, 8, 3, 4, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 0, 18);
        this.ArmR.func_78793_a(-5.0f, -1.0f, 0.5f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.6f, 4, 5, 5, 0.0f);
        this.Chest = new ModelRenderer((ModelBase)this, 33, 9);
        this.Chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest.func_78790_a(-4.0f, 1.0f, -2.5f, 8, 4, 1, 0.0f);
        this.Tail2 = new ModelRenderer((ModelBase)this, 48, 14);
        this.Tail2.func_78793_a(0.0f, 0.0f, 2.3f);
        this.Tail2.func_78790_a(-1.5f, -1.5f, 0.0f, 3, 3, 5, 0.0f);
        this.setRotateAngle(this.Tail2, -0.59184116f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer((ModelBase)this, 44, 22);
        this.Tail3.func_78793_a(0.0f, 0.0f, 4.4f);
        this.Tail3.func_78790_a(-2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f);
        this.setRotateAngle(this.Tail3, 0.4553564f, 0.0f, 0.0f);
        this.Tail4 = new ModelRenderer((ModelBase)this, 52, 32);
        this.Tail4.func_78793_a(0.0f, 0.0f, 5.8f);
        this.Tail4.func_78790_a(-1.5f, -1.5f, 0.0f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.Tail4, 0.27314404f, 0.0f, 0.0f);
        this.EarR2 = new ModelRenderer((ModelBase)this, 49, 2);
        this.EarR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR2.func_78790_a(-1.3f, -3.2f, 0.05f, 3, 4, 0, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 43);
        this.LegR.func_78793_a(-2.3f, 10.6f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 0, 18);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, -1.0f, 0.5f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.5f, 4, 5, 5, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 0, 53);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, 6.0f, -0.6f, 4, 7, 4, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 27, 2);
        this.EarR.func_78793_a(-3.0f, -6.1f, 0.0f);
        this.EarR.func_78790_a(-2.0f, -4.0f, 0.0f, 4, 6, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.27925268f, -0.6981317f);
        this.Scarf = new ModelRenderer((ModelBase)this, 17, 53);
        this.Scarf.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Scarf.func_78790_a(-4.5f, -1.0f, -3.8f, 9, 3, 8, 0.0f);
        this.setRotateAngle(this.Scarf, 0.16231562f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.2f, -4.0f, 8, 7, 8, 0.0f);
        this.EarL2 = new ModelRenderer((ModelBase)this, 49, 2);
        this.EarL2.field_78809_i = true;
        this.EarL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL2.func_78790_a(-1.7f, -3.2f, 0.05f, 3, 4, 0, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 20, 29);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.5f, 4.7f, -2.0f, 7, 6, 4, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 0, 28);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-0.6f, 2.8f, -2.0f, 3, 9, 4, 0.0f);
        this.Snout = new ModelRenderer((ModelBase)this, 36, 1);
        this.Snout.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Snout.func_78790_a(-2.0f, -3.9f, -6.0f, 4, 3, 2, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 20, 16);
        this.Body1.func_78793_a(0.0f, -3.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -1.4f, 8, 8, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 43);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.3f, 10.6f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.Fur = new ModelRenderer((ModelBase)this, 42, 14);
        this.Fur.func_78793_a(0.2f, 0.9f, -3.0f);
        this.Fur.func_78790_a(-1.6f, -1.2f, -0.9f, 3, 3, 2, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 0, 28);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-2.6f, 2.8f, -2.0f, 3, 9, 4, 0.0f);
        this.Scarf.func_78792_a(this.Back);
        this.LegR.func_78792_a(this.LegR2);
        this.Tail4.func_78792_a(this.Tail5);
        this.Head.func_78792_a(this.EarL);
        this.Body2.func_78792_a(this.Body3);
        this.Body1.func_78792_a(this.Chest);
        this.Tail1.func_78792_a(this.Tail2);
        this.Tail2.func_78792_a(this.Tail3);
        this.Tail3.func_78792_a(this.Tail4);
        this.EarR.func_78792_a(this.EarR2);
        this.LegL.func_78792_a(this.LegL2);
        this.Head.func_78792_a(this.EarR);
        this.Body1.func_78792_a(this.Scarf);
        this.EarL.func_78792_a(this.EarL2);
        this.Body1.func_78792_a(this.Body2);
        this.ArmL.func_78792_a(this.ArmL2);
        this.Head.func_78792_a(this.Snout);
        this.Body1.func_78792_a(this.Fur);
        this.ArmR.func_78792_a(this.ArmR2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        this.Tail1.func_78785_a(f5);
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
        this.Tail1.field_78795_f = 0.2f;
        this.Tail1.field_78795_f += r4 / 2.0f;
        this.Tail2.field_78795_f = 0.2f;
        this.Tail2.field_78795_f += r4 / 2.0f;
        this.Tail3.field_78795_f = 0.2f;
        this.Tail3.field_78795_f += r4 / 2.0f;
        this.Tail4.field_78795_f = 0.2f;
        this.Tail4.field_78795_f += r4 / 2.0f;
        this.Tail5.field_78796_g = 0.2f;
        this.Tail5.field_78796_g += r4;
        this.Back.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (0.0f > this.Back.field_78795_f) {
            this.Back.field_78795_f *= -1.0f;
        }
        this.Back.field_78796_g = 0.0f;
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

