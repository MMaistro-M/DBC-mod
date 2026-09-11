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

public class ModelSorrel
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hat1;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Hat2;
    public ModelRenderer Body2;
    public ModelRenderer Scarf;
    public ModelRenderer Body3;
    public ModelRenderer tail;
    public ModelRenderer Scarf2;
    public ModelRenderer Scarf3;
    public ModelRenderer LegR2;
    public ModelRenderer LegL2;

    public ModelSorrel() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Scarf = new ModelRenderer((ModelBase)this, 43, 8);
        this.Scarf.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Scarf.func_78790_a(-2.5f, -0.5f, -2.5f, 5, 1, 5, 0.0f);
        this.Hat2 = new ModelRenderer((ModelBase)this, 37, 48);
        this.Hat2.func_78793_a(0.0f, 0.6f, -4.0f);
        this.Hat2.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 0, 2, 0.0f);
        this.setRotateAngle(this.Hat2, 0.33056536f, 0.0f, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 23, 39);
        this.tail.func_78793_a(0.0f, 8.4f, 1.8f);
        this.tail.func_78790_a(-1.0f, -1.0f, -0.2f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.tail, -0.045553092f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, 2.6f, 0.0f);
        this.Body1.func_78790_a(-3.0f, 0.0f, -1.9f, 6, 5, 4, -0.2f);
        this.Scarf2 = new ModelRenderer((ModelBase)this, 55, 16);
        this.Scarf2.func_78793_a(1.0f, 0.2f, 2.4f);
        this.Scarf2.func_78790_a(-1.4f, 0.0f, 0.0f, 3, 7, 0, 0.0f);
        this.setRotateAngle(this.Scarf2, 0.1675516f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 27);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-2.5f, 4.8f, -1.6f, 5, 3, 3, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 0, 41);
        this.LegR2.func_78793_a(0.0f, 0.5f, 0.0f);
        this.LegR2.func_78790_a(-2.0f, -0.3f, -2.5f, 4, 4, 5, 0.0f);
        this.setRotateAngle(this.LegR2, 0.0f, 0.0f, 0.04363323f);
        this.Hat1 = new ModelRenderer((ModelBase)this, 31, 51);
        this.Hat1.func_78793_a(0.0f, -3.9f, 0.0f);
        this.Hat1.func_78790_a(-4.0f, -3.4f, -4.0f, 8, 4, 8, 0.0f);
        this.setRotateAngle(this.Hat1, -0.09128072f, 0.089186326f, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 35, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.1f, -5.7f, 0.0f);
        this.EarL.func_78790_a(-2.0f, -8.5f, 0.0f, 4, 9, 0, 0.0f);
        this.setRotateAngle(this.EarL, -0.17453292f, 0.0f, 0.08726646f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 51);
        this.LegR.func_78793_a(-1.6f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-1.4f, 3.2f, -2.0f, 3, 9, 4, -0.2f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Body3.func_78793_a(0.0f, 0.5f, 0.0f);
        this.Body3.func_78790_a(-3.0f, 7.3f, -2.0f, 6, 2, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 2.5f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.5f, -4.0f, 8, 8, 8, -0.7f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 0, 41);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.5f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, -0.3f, -2.5f, 4, 4, 5, 0.0f);
        this.setRotateAngle(this.LegL2, 0.0f, 0.0f, -0.04363323f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 51);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.8f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-1.6f, 3.2f, -2.0f, 3, 9, 4, -0.2f);
        this.Scarf3 = new ModelRenderer((ModelBase)this, 55, 23);
        this.Scarf3.func_78793_a(0.0f, 6.8f, 0.0f);
        this.Scarf3.func_78790_a(-1.4f, 0.0f, 0.0f, 3, 10, 0, 0.0f);
        this.setRotateAngle(this.Scarf3, 0.0837758f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 23, 20);
        this.ArmR.func_78793_a(-3.8f, 3.8f, 0.0f);
        this.ArmR.func_78790_a(-1.7f, -1.3f, -1.8f, 3, 11, 4, -0.3f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.06981317f);
        this.EarR = new ModelRenderer((ModelBase)this, 35, 1);
        this.EarR.func_78793_a(-3.4f, -5.7f, 0.0f);
        this.EarR.func_78790_a(-2.0f, -8.5f, 0.0f, 4, 9, 0, 0.0f);
        this.setRotateAngle(this.EarR, -0.17453292f, 0.0f, -0.08726646f);
        this.ArmL = new ModelRenderer((ModelBase)this, 38, 20);
        this.ArmL.func_78793_a(3.8f, 3.8f, 0.0f);
        this.ArmL.func_78790_a(-1.3f, -1.3f, -1.8f, 3, 11, 4, -0.3f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.06981317f);
        this.Body1.func_78792_a(this.Scarf);
        this.Hat1.func_78792_a(this.Hat2);
        this.Body3.func_78792_a(this.tail);
        this.Scarf.func_78792_a(this.Scarf2);
        this.Body1.func_78792_a(this.Body2);
        this.LegR.func_78792_a(this.LegR2);
        this.Head.func_78792_a(this.Hat1);
        this.Head.func_78792_a(this.EarL);
        this.Body2.func_78792_a(this.Body3);
        this.LegL.func_78792_a(this.LegL2);
        this.Scarf2.func_78792_a(this.Scarf3);
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
        this.Scarf2.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (0.0f > this.Scarf2.field_78795_f) {
            this.Scarf2.field_78795_f *= -1.0f;
        }
        this.Scarf2.field_78796_g = 0.0f;
        this.Scarf3.field_78795_f = -0.15f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        if (0.0f > this.Scarf3.field_78795_f) {
            this.Scarf3.field_78795_f *= -1.0f;
        }
        this.Scarf3.field_78796_g = 0.0f;
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

