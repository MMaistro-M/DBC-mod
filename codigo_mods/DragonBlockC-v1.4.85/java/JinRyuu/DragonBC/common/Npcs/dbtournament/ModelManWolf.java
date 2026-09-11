/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbtournament;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelManWolf
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Nose;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer MuttonchopsR;
    public ModelRenderer MuttonchopsL;

    public ModelManWolf() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.ArmR1 = new ModelRenderer((ModelBase)this, 34, 16);
        this.ArmR1.func_78793_a(-6.5f, 2.0f, 1.0f);
        this.ArmR1.func_78790_a(-3.5f, -2.0f, -2.5f, 5, 12, 5, 0.0f);
        this.MuttonchopsR = new ModelRenderer((ModelBase)this, 55, 8);
        this.MuttonchopsR.func_78793_a(-3.5f, -3.5f, -2.1f);
        this.MuttonchopsR.func_78790_a(-2.0f, -0.4f, 0.0f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.MuttonchopsR, 0.0f, 0.6981317f, 0.08726646f);
        this.MuttonchopsL = new ModelRenderer((ModelBase)this, 55, 8);
        this.MuttonchopsL.field_78809_i = true;
        this.MuttonchopsL.func_78793_a(3.5f, -3.5f, -2.1f);
        this.MuttonchopsL.func_78790_a(0.0f, -0.2f, 0.0f, 2, 4, 0, 0.0f);
        this.setRotateAngle(this.MuttonchopsL, 0.0f, -0.6981317f, -0.08726646f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Body1.func_78793_a(0.0f, 0.0f, 1.0f);
        this.Body1.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 12, 6, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 47, 8);
        this.EarR.func_78793_a(-3.6f, -4.0f, -1.0f);
        this.EarR.func_78790_a(-2.5f, -3.0f, 0.0f, 3, 4, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.34906584f, -0.04363323f);
        this.LegL = new ModelRenderer((ModelBase)this, 1, 40);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.6f, 12.0f, 1.0f);
        this.LegL.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 12, 5, 0.1f);
        this.EarL = new ModelRenderer((ModelBase)this, 47, 8);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.0f, -4.0f, -1.0f);
        this.EarL.func_78790_a(0.0f, -3.0f, 0.0f, 3, 4, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.34906584f, 0.04363323f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.6f);
        this.Head.func_78790_a(-4.0f, -6.0f, -4.0f, 8, 6, 8, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 34, 16);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(6.5f, 2.0f, 1.0f);
        this.ArmL1.func_78790_a(-1.5f, -2.0f, -2.5f, 5, 12, 5, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 1, 40);
        this.LegR.func_78793_a(-2.6f, 12.0f, 1.0f);
        this.LegR.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 12, 5, 0.1f);
        this.Nose = new ModelRenderer((ModelBase)this, 46, 0);
        this.Nose.func_78793_a(0.0f, -2.0f, -3.7f);
        this.Nose.func_78790_a(-2.0f, -1.0f, -2.9f, 4, 3, 3, 0.0f);
        this.Head.func_78792_a(this.MuttonchopsR);
        this.Head.func_78792_a(this.MuttonchopsL);
        this.Head.func_78792_a(this.EarR);
        this.Head.func_78792_a(this.EarL);
        this.Head.func_78792_a(this.Nose);
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
    }
}

