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

public class ModelNarirama
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR1;
    public ModelRenderer LegL1;
    public ModelRenderer EarL;
    public ModelRenderer EarR1;
    public ModelRenderer Eye;
    public ModelRenderer EarR2;
    public ModelRenderer EarR2_1;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Chest1;
    public ModelRenderer Body4;
    public ModelRenderer Chest2;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;
    public ModelRenderer LegR2;
    public ModelRenderer LegR3;
    public ModelRenderer LegL2;
    public ModelRenderer LegL3;

    public ModelNarirama() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.LegR1 = new ModelRenderer((ModelBase)this, 67, 3);
        this.LegR1.func_78793_a(-3.0f, 5.5f, 0.0f);
        this.LegR1.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 8, 5, 0.0f);
        this.LegL1 = new ModelRenderer((ModelBase)this, 67, 3);
        this.LegL1.field_78809_i = true;
        this.LegL1.func_78793_a(3.0f, 5.5f, 0.0f);
        this.LegL1.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 8, 5, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-5.5f, 10.0f, -3.0f, 11, 2, 6, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 101, 2);
        this.ArmR1.func_78793_a(-7.0f, -11.0f, 0.0f);
        this.ArmR1.func_78790_a(-6.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.Chest2 = new ModelRenderer((ModelBase)this, 43, 23);
        this.Chest2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest2.func_78790_a(-2.5f, 2.8f, -5.2f, 5, 5, 1, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 101, 2);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(7.0f, -11.0f, 0.0f);
        this.ArmL1.func_78790_a(0.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.Eye = new ModelRenderer((ModelBase)this, 27, 0);
        this.Eye.func_78793_a(0.0f, -2.0f, -2.2f);
        this.Eye.func_78790_a(-2.5f, -1.1f, -0.8f, 5, 3, 1, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 102, 15);
        this.ArmR2.func_78793_a(-2.8f, 2.9f, 0.0f);
        this.ArmR2.func_78790_a(-2.7f, -0.1f, -2.5f, 5, 8, 5, 0.0f);
        this.EarR2 = new ModelRenderer((ModelBase)this, 50, 6);
        this.EarR2.func_78793_a(0.0f, 0.5f, 0.0f);
        this.EarR2.func_78790_a(0.0f, -2.6f, 0.0f, 1, 2, 0, 0.0f);
        this.setRotateAngle(this.EarR2, 0.0f, 0.0f, -1.0168288f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 101, 29);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 7.3f, -0.5f);
        this.ArmL3.func_78790_a(-2.7f, 0.0f, -2.4f, 6, 10, 6, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 66, 17);
        this.LegR2.func_78793_a(0.0f, 8.0f, 0.0f);
        this.LegR2.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 10, 6, 0.0f);
        this.EarR1 = new ModelRenderer((ModelBase)this, 38, 4);
        this.EarR1.func_78793_a(-3.0f, -3.8f, 0.4f);
        this.EarR1.func_78790_a(-1.0f, -1.5f, -1.6f, 2, 3, 3, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 0, 50);
        this.Body4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body4.func_78790_a(-5.5f, 16.0f, -3.0f, 11, 4, 6, 0.0f);
        this.LegR3 = new ModelRenderer((ModelBase)this, 61, 35);
        this.LegR3.func_78793_a(0.0f, 8.5f, 0.0f);
        this.LegR3.func_78790_a(-2.5f, 0.0f, -6.0f, 5, 2, 11, 0.0f);
        this.EarR2_1 = new ModelRenderer((ModelBase)this, 49, 4);
        this.EarR2_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR2_1.func_78790_a(-0.5f, -4.6f, 0.0f, 2, 2, 0, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 13);
        this.Body1.func_78793_a(0.0f, -14.5f, 0.0f);
        this.Body1.func_78790_a(-7.0f, 0.0f, -3.3f, 14, 10, 7, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 66, 17);
        this.LegL2.func_78793_a(0.0f, 8.0f, 0.0f);
        this.LegL2.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 10, 6, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 29, 5);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.0f, -4.2f, 0.4f);
        this.EarL.func_78790_a(-1.0f, -1.0f, -1.0f, 2, 2, 2, 0.0f);
        this.Chest1 = new ModelRenderer((ModelBase)this, 43, 14);
        this.Chest1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest1.func_78790_a(-3.0f, 1.8f, -4.3f, 6, 7, 1, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 102, 15);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(2.8f, 2.9f, 0.0f);
        this.ArmL2.func_78790_a(-2.2f, -0.1f, -2.5f, 5, 8, 5, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -14.5f, -0.3f);
        this.Head.func_78790_a(-3.5f, -5.0f, -2.3f, 7, 5, 6, 0.0f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 101, 29);
        this.ArmR3.func_78793_a(0.0f, 7.3f, -0.5f);
        this.ArmR3.func_78790_a(-3.2f, 0.0f, -2.4f, 6, 10, 6, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 40);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.5f, 12.0f, -2.8f, 9, 4, 5, 0.0f);
        this.LegL3 = new ModelRenderer((ModelBase)this, 61, 35);
        this.LegL3.func_78793_a(0.0f, 8.5f, 0.0f);
        this.LegL3.func_78790_a(-2.5f, 0.0f, -6.0f, 5, 2, 11, 0.0f);
        this.Body1.func_78792_a(this.Body2);
        this.Chest1.func_78792_a(this.Chest2);
        this.Head.func_78792_a(this.Eye);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.EarR1.func_78792_a(this.EarR2);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.LegR1.func_78792_a(this.LegR2);
        this.Head.func_78792_a(this.EarR1);
        this.Body3.func_78792_a(this.Body4);
        this.LegR2.func_78792_a(this.LegR3);
        this.EarR2.func_78792_a(this.EarR2_1);
        this.LegL1.func_78792_a(this.LegL2);
        this.Head.func_78792_a(this.EarL);
        this.Body3.func_78792_a(this.Chest1);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Body2.func_78792_a(this.Body3);
        this.LegL2.func_78792_a(this.LegL3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegR1.func_78785_a(f5);
        this.LegL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
        this.Head.func_78785_a(f5);
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
        this.LegR1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegR1.field_78796_g = 0.0f;
        this.LegL1.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

