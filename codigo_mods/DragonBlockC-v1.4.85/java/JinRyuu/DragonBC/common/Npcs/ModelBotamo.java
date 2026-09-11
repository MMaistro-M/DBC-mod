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

public class ModelBotamo
extends ModelBase {
    public ModelRenderer BodyBelly;
    public ModelRenderer LegR1;
    public ModelRenderer LegL1;
    public ModelRenderer Head;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer BodyBottom;
    public ModelRenderer BodyTorso;
    public ModelRenderer BodyTop;
    public ModelRenderer LegR2;
    public ModelRenderer LegR3;
    public ModelRenderer LegL2;
    public ModelRenderer LegL3;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;

    public ModelBotamo() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.LegR1 = new ModelRenderer((ModelBase)this, 77, 35);
        this.LegR1.func_78793_a(-3.1f, 14.1f, 0.0f);
        this.LegR1.func_78790_a(-1.4f, -0.6f, -1.5f, 3, 4, 3, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 71, 0);
        this.Head.func_78793_a(0.0f, -5.6f, 0.0f);
        this.Head.func_78790_a(-3.5f, -4.3f, -4.1f, 7, 5, 6, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 95, 18);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-1.7f, 0.7f, -2.6f, 3, 6, 4, 0.0f);
        this.setRotateAngle(this.ArmL2, 0.0f, 0.0f, -0.18203785f);
        this.BodyBelly = new ModelRenderer((ModelBase)this, 10, 27);
        this.BodyBelly.func_78793_a(0.0f, -5.6f, 0.0f);
        this.BodyBelly.func_78790_a(-8.5f, 7.5f, -8.0f, 17, 10, 14, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 75, 43);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, 3.2f, -2.0f, 4, 5, 4, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 68, 0);
        this.EarR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarR.func_78790_a(-4.3f, -4.3f, -1.3f, 1, 2, 2, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 94, 10);
        this.ArmR1.func_78793_a(-8.2f, -3.7f, 1.8f);
        this.ArmR1.func_78790_a(-1.7f, -1.6f, -2.6f, 4, 3, 4, 0.0f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 94, 29);
        this.ArmR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR3.func_78790_a(-2.9f, 6.4f, -2.6f, 4, 8, 4, 0.0f);
        this.setRotateAngle(this.ArmR3, 0.0f, 0.0f, -0.091106184f);
        this.EarL = new ModelRenderer((ModelBase)this, 68, 0);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.EarL.func_78790_a(3.3f, -4.3f, -1.3f, 1, 2, 2, 0.0f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 75, 43);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.0f, 3.2f, -2.0f, 4, 5, 4, 0.0f);
        this.LegL1 = new ModelRenderer((ModelBase)this, 77, 35);
        this.LegL1.field_78809_i = true;
        this.LegL1.func_78793_a(3.3f, 14.2f, 0.0f);
        this.LegL1.func_78790_a(-1.5f, -0.6f, -1.5f, 3, 4, 3, 0.0f);
        this.BodyBottom = new ModelRenderer((ModelBase)this, 19, 52);
        this.BodyBottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyBottom.func_78790_a(-6.0f, 17.4f, -5.7f, 12, 2, 10, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 95, 18);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-1.7f, 0.7f, -2.6f, 3, 6, 4, 0.0f);
        this.setRotateAngle(this.ArmR2, 0.0f, 0.0f, 0.18203785f);
        this.BodyTorso = new ModelRenderer((ModelBase)this, 17, 11);
        this.BodyTorso.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyTorso.func_78790_a(-7.0f, 2.9f, -4.9f, 14, 5, 10, 0.0f);
        this.BodyTop = new ModelRenderer((ModelBase)this, 22, 0);
        this.BodyTop.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyTop.func_78790_a(-6.0f, 0.2f, -2.6f, 12, 3, 7, 0.0f);
        this.LegL3 = new ModelRenderer((ModelBase)this, 72, 53);
        this.LegL3.field_78809_i = true;
        this.LegL3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL3.func_78790_a(-2.5f, 8.0f, -3.9f, 5, 2, 6, 0.0f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 94, 29);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL3.func_78790_a(-1.4f, 6.4f, -2.6f, 4, 8, 4, 0.0f);
        this.setRotateAngle(this.ArmL3, 0.0f, 0.0f, 0.091106184f);
        this.LegR3 = new ModelRenderer((ModelBase)this, 72, 53);
        this.LegR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR3.func_78790_a(-2.5f, 8.0f, -3.9f, 5, 2, 6, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 94, 10);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(8.5f, -3.7f, 1.8f);
        this.ArmL1.func_78790_a(-2.7f, -1.6f, -2.6f, 4, 3, 4, 0.0f);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.LegL1.func_78792_a(this.LegL2);
        this.Head.func_78792_a(this.EarR);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Head.func_78792_a(this.EarL);
        this.LegR1.func_78792_a(this.LegR2);
        this.BodyBelly.func_78792_a(this.BodyBottom);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.BodyBottom.func_78792_a(this.BodyTorso);
        this.BodyTorso.func_78792_a(this.BodyTop);
        this.LegL2.func_78792_a(this.LegL3);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.LegR2.func_78792_a(this.LegR3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL1.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.BodyBelly.func_78785_a(f5);
        this.LegR1.func_78785_a(f5);
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
        this.LegR1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LegL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.LegR1.field_78796_g = 0.0f;
        this.LegL1.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

