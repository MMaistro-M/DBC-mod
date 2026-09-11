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

public class ModelKefla
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer Hair;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer HairFrontL1;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer HairBand;
    public ModelRenderer HairFrontR1;
    public ModelRenderer HairFrontL2;
    public ModelRenderer Ponytail1;
    public ModelRenderer Ponytail2;
    public ModelRenderer Ponytail3;
    public ModelRenderer Ponytail4;
    public ModelRenderer HairFrontR2;
    public ModelRenderer Body2;
    public ModelRenderer Boobs;
    public ModelRenderer Body3;

    public ModelKefla() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 1.4f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.5f, -4.0f, 8, 8, 8, -0.5f);
        this.ArmR = new ModelRenderer((ModelBase)this, 32, 29);
        this.ArmR.func_78793_a(-4.3f, 2.5f, 0.0f);
        this.ArmR.func_78790_a(-2.8f, -1.3f, -1.8f, 4, 12, 4, -0.3f);
        this.setRotateAngle(this.ArmR, 0.0f, 0.0f, 0.06981317f);
        this.ArmL = new ModelRenderer((ModelBase)this, 32, 29);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.4f, 2.5f, 0.0f);
        this.ArmL.func_78790_a(-1.3f, -1.3f, -1.8f, 4, 12, 4, -0.3f);
        this.setRotateAngle(this.ArmL, 0.0f, 0.0f, -0.06981317f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 33, 10);
        this.Hair5.func_78793_a(2.9f, -5.8f, -2.3f);
        this.Hair5.func_78790_a(-0.7f, -1.0f, -1.0f, 5, 2, 2, 0.0f);
        this.setRotateAngle(this.Hair5, 0.0f, -0.27314404f, 0.3642502f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, 1.2f, 0.0f);
        this.Body1.func_78790_a(-3.5f, 0.0f, -2.0f, 7, 5, 4, -0.2f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 45, 12);
        this.Hair4.func_78793_a(-2.6f, -6.2f, -0.8f);
        this.Hair4.func_78790_a(-4.7f, -1.6f, -1.4f, 5, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair4, 0.0f, -0.13665928f, -0.31869712f);
        this.Ponytail4 = new ModelRenderer((ModelBase)this, 40, 18);
        this.Ponytail4.func_78793_a(-0.1f, 0.7f, 0.8f);
        this.Ponytail4.func_78790_a(-0.5f, -1.5f, 0.0f, 1, 3, 5, 0.0f);
        this.setRotateAngle(this.Ponytail4, -0.8651597f, 0.0f, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 36, 0);
        this.Hair1.func_78793_a(-0.4f, -7.1f, 0.3f);
        this.Hair1.func_78790_a(-1.0f, -6.2f, -1.1f, 2, 7, 2, 0.0f);
        this.setRotateAngle(this.Hair1, 0.4098033f, 0.0f, -0.27314404f);
        this.Ponytail2 = new ModelRenderer((ModelBase)this, 46, 19);
        this.Ponytail2.func_78793_a(1.4f, 0.0f, 0.8f);
        this.Ponytail2.func_78790_a(-0.9f, -1.5f, 0.0f, 2, 3, 7, 0.0f);
        this.setRotateAngle(this.Ponytail2, -0.18203785f, 0.091106184f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 27);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.0f, 4.3f, -1.6f, 6, 4, 3, 0.0f);
        this.Boobs = new ModelRenderer((ModelBase)this, 19, 27);
        this.Boobs.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Boobs.func_78790_a(-3.0f, 1.8f, -0.7f, 6, 3, 2, 0.0f);
        this.setRotateAngle(this.Boobs, -0.59184116f, 0.0f, 0.0f);
        this.HairFrontR2 = new ModelRenderer((ModelBase)this, 56, 5);
        this.HairFrontR2.func_78793_a(0.4f, 0.0f, -1.7f);
        this.HairFrontR2.func_78790_a(-1.0f, -0.3f, -0.5f, 2, 4, 1, 0.0f);
        this.setRotateAngle(this.HairFrontR2, -0.8196066f, 0.0f, 0.0f);
        this.Ponytail1 = new ModelRenderer((ModelBase)this, 46, 19);
        this.Ponytail1.func_78793_a(-0.1f, 0.0f, 0.8f);
        this.Ponytail1.func_78790_a(-0.9f, -1.5f, 0.0f, 2, 3, 7, 0.0f);
        this.setRotateAngle(this.Ponytail1, 0.31869712f, 0.0f, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 35);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 7.9f, -1.9f, 7, 3, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegR.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.HairFrontR1 = new ModelRenderer((ModelBase)this, 56, 1);
        this.HairFrontR1.func_78793_a(-1.5f, -6.0f, -3.8f);
        this.HairFrontR1.func_78790_a(-1.0f, -0.3f, -1.7f, 2, 1, 2, 0.0f);
        this.setRotateAngle(this.HairFrontR1, 0.4098033f, 0.8651597f, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(0.0f, -1.2f, 0.0f, 1, 1, 1, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 45, 0);
        this.Hair2.func_78793_a(-1.1f, -6.5f, -2.3f);
        this.Hair2.func_78790_a(-1.1f, -6.5f, -1.5f, 2, 7, 3, 0.0f);
        this.setRotateAngle(this.Hair2, 0.91053826f, 0.91053826f, 0.0f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 25, 0);
        this.Hair3.func_78793_a(0.7f, -5.0f, -1.0f);
        this.Hair3.func_78790_a(-1.9f, -6.5f, -2.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.Hair3, 0.18203785f, -0.18203785f, 0.5009095f);
        this.HairFrontL1 = new ModelRenderer((ModelBase)this, 56, 1);
        this.HairFrontL1.func_78793_a(1.4f, -6.1f, -3.4f);
        this.HairFrontL1.func_78790_a(-1.0f, -0.3f, -2.0f, 2, 1, 2, 0.0f);
        this.setRotateAngle(this.HairFrontL1, 0.22759093f, -0.5462881f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.HairBand = new ModelRenderer((ModelBase)this, 34, 15);
        this.HairBand.func_78793_a(-0.2f, -6.4f, 3.0f);
        this.HairBand.func_78790_a(-1.5f, -1.0f, -1.0f, 3, 2, 2, 0.0f);
        this.setRotateAngle(this.HairBand, 0.5462881f, 0.0f, 0.0f);
        this.HairFrontL2 = new ModelRenderer((ModelBase)this, 56, 5);
        this.HairFrontL2.func_78793_a(0.1f, 0.1f, -1.7f);
        this.HairFrontL2.func_78790_a(-1.0f, -0.3f, -0.5f, 2, 5, 1, 0.0f);
        this.setRotateAngle(this.HairFrontL2, -0.5009095f, 0.0f, 0.0f);
        this.Ponytail3 = new ModelRenderer((ModelBase)this, 40, 18);
        this.Ponytail3.func_78793_a(-0.8f, 0.0f, 0.8f);
        this.Ponytail3.func_78790_a(-0.9f, -1.0f, 0.0f, 1, 2, 5, 0.0f);
        this.setRotateAngle(this.Ponytail3, 0.5462881f, -0.27314404f, -0.009075712f);
        this.Hair.func_78792_a(this.Hair5);
        this.Hair.func_78792_a(this.Hair4);
        this.HairBand.func_78792_a(this.Ponytail4);
        this.Hair.func_78792_a(this.Hair1);
        this.HairBand.func_78792_a(this.Ponytail2);
        this.Body1.func_78792_a(this.Body2);
        this.Body1.func_78792_a(this.Boobs);
        this.HairFrontR1.func_78792_a(this.HairFrontR2);
        this.HairBand.func_78792_a(this.Ponytail1);
        this.Body2.func_78792_a(this.Body3);
        this.Hair.func_78792_a(this.HairFrontR1);
        this.Head.func_78792_a(this.Hair);
        this.Hair.func_78792_a(this.Hair2);
        this.Hair.func_78792_a(this.Hair3);
        this.Hair.func_78792_a(this.HairFrontL1);
        this.Hair.func_78792_a(this.HairBand);
        this.HairFrontL1.func_78792_a(this.HairFrontL2);
        this.HairBand.func_78792_a(this.Ponytail3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.Body1.func_78785_a(f5);
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

