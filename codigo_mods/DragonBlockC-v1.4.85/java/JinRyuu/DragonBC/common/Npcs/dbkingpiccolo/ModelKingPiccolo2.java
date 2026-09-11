/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbkingpiccolo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelKingPiccolo2
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Head2;
    public ModelRenderer TentacleL;
    public ModelRenderer TentacleR;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer FeetL;
    public ModelRenderer FeetR;

    public ModelKingPiccolo2() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.TentacleL = new ModelRenderer((ModelBase)this, 25, -1);
        this.TentacleL.field_78809_i = true;
        this.TentacleL.func_78793_a(1.5f, -5.5f, -4.0f);
        this.TentacleL.func_78790_a(0.0f, -2.0f, -4.0f, 0, 4, 4, 0.0f);
        this.setRotateAngle(this.TentacleL, 0.0f, -0.6981317f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.TentacleR = new ModelRenderer((ModelBase)this, 25, -1);
        this.TentacleR.func_78793_a(-1.5f, -5.5f, -4.0f);
        this.TentacleR.func_78790_a(0.0f, -2.0f, -4.0f, 0, 4, 4, 0.0f);
        this.setRotateAngle(this.TentacleR, 0.0f, 0.6981317f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 1, 41);
        this.Body2.func_78793_a(0.0f, 9.5f, 0.0f);
        this.Body2.func_78790_a(-4.5f, -0.5f, -2.5f, 9, 2, 5, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 40, 40);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.5f, 8.0f, 0.0f);
        this.LegL.func_78790_a(-2.5f, 0.0f, -3.0f, 5, 14, 6, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 40, 40);
        this.LegR.func_78793_a(-2.5f, 8.0f, 0.0f);
        this.LegR.func_78790_a(-2.5f, 0.0f, -3.0f, 5, 14, 6, 0.0f);
        this.FeetR = new ModelRenderer((ModelBase)this, 64, 41);
        this.FeetR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetR.func_78790_a(-2.0f, 14.0f, -3.3f, 4, 2, 5, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 34, 1);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(3.8f, -4.5f, -1.0f);
        this.EarL.func_78790_a(0.0f, -3.5f, 0.0f, 3, 6, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.87266463f, 0.04363323f);
        this.Body3 = new ModelRenderer((ModelBase)this, 1, 50);
        this.Body3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.Body3.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 4, 6, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 41, 19);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.8f, -5.1f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.5f, 5, 15, 5, -0.1f);
        this.Head2 = new ModelRenderer((ModelBase)this, 33, 1);
        this.Head2.func_78793_a(0.0f, -5.6f, 1.7f);
        this.Head2.func_78790_a(-3.5f, -3.0f, -5.0f, 7, 8, 8, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 34, 1);
        this.EarR.func_78793_a(-3.5f, -4.5f, -1.0f);
        this.EarR.func_78790_a(-3.5f, -3.5f, 0.0f, 3, 6, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.87266463f, -0.04363323f);
        this.ArmR = new ModelRenderer((ModelBase)this, 41, 19);
        this.ArmR.func_78793_a(-5.8f, -5.1f, 0.0f);
        this.ArmR.func_78790_a(-4.0f, -2.0f, -2.5f, 5, 15, 5, -0.1f);
        this.FeetL = new ModelRenderer((ModelBase)this, 64, 41);
        this.FeetL.field_78809_i = true;
        this.FeetL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FeetL.func_78790_a(-2.0f, 14.0f, -3.3f, 4, 2, 5, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 24);
        this.Body.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Body.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 9, 6, 0.0f);
        this.Head.func_78792_a(this.TentacleL);
        this.Head.func_78792_a(this.TentacleR);
        this.Body.func_78792_a(this.Body2);
        this.LegR.func_78792_a(this.FeetR);
        this.Head.func_78792_a(this.EarL);
        this.Body.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.Head2);
        this.Head.func_78792_a(this.EarR);
        this.LegL.func_78792_a(this.FeetL);
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

