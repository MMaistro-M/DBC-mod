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

public class ModelGodArak
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer Hair;
    public ModelRenderer WhiskersR1;
    public ModelRenderer WhiskersR1_1;
    public ModelRenderer WhiskersR2;
    public ModelRenderer WhiskersR3;
    public ModelRenderer WhiskersR4;
    public ModelRenderer WhiskersR2_1;
    public ModelRenderer WhiskersR3_1;
    public ModelRenderer WhiskersR4_1;
    public ModelRenderer Neck;
    public ModelRenderer Body2;
    public ModelRenderer Cloth1;
    public ModelRenderer Body3;
    public ModelRenderer LegR2;
    public ModelRenderer LegL2;
    public ModelRenderer ArmRingR1;
    public ModelRenderer ArmRingR2;
    public ModelRenderer ArmRingL1;
    public ModelRenderer ArmRingL2;

    public ModelGodArak() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.WhiskersR1_1 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR1_1.func_78793_a(3.8f, -3.1f, -0.7f);
        this.WhiskersR1_1.func_78790_a(-0.6f, 0.0f, -0.2f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR1_1, 0.27314404f, -0.4553564f, -1.1383038f);
        this.ArmR = new ModelRenderer((ModelBase)this, 50, 16);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-2.0f, -2.0f, -1.8f, 3, 12, 4, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 23, 29);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.5f, 5.0f, -3.1f, 7, 3, 5, 0.0f);
        this.ArmRingL2 = new ModelRenderer((ModelBase)this, 25, 51);
        this.ArmRingL2.field_78809_i = true;
        this.ArmRingL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingL2.func_78790_a(-0.4f, 7.4f, -2.3f, 4, 1, 5, 0.0f);
        this.setRotateAngle(this.ArmRingL2, 0.0f, 0.0f, 0.10471976f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-1.8f, 1.4f, -2.0f, 4, 6, 4, 0.3f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 45);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(-3.5f, -11.8f, -2.6f, 7, 5, 6, 0.0f);
        this.setRotateAngle(this.Hair, (float)(-Math.PI) / 90, 0.0f, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 20, 18);
        this.Body1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 5, 4, 0.0f);
        this.WhiskersR3 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR3.func_78793_a(0.8f, 0.0f, -0.2f);
        this.WhiskersR3.func_78790_a(-0.6f, 0.0f, -0.2f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR3, 0.0f, 0.091106184f, -0.13665928f);
        this.ArmRingL1 = new ModelRenderer((ModelBase)this, 25, 51);
        this.ArmRingL1.field_78809_i = true;
        this.ArmRingL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingL1.func_78790_a(-1.3f, 6.4f, -2.3f, 4, 1, 5, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 21, 38);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 8.0f, -2.9f, 7, 4, 5, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 33);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.3f, 1.4f, -2.0f, 4, 6, 4, 0.3f);
        this.Neck = new ModelRenderer((ModelBase)this, 42, 6);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -1.6f, -0.8f, 4, 2, 2, 0.0f);
        this.WhiskersR1 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR1.func_78793_a(-3.8f, -3.1f, -0.7f);
        this.WhiskersR1.func_78790_a(-0.6f, 0.0f, -0.2f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR1, 0.27314404f, 0.4553564f, 1.1383038f);
        this.Cloth1 = new ModelRenderer((ModelBase)this, 47, 51);
        this.Cloth1.func_78793_a(0.0f, 9.9f, -2.5f);
        this.Cloth1.func_78790_a(-2.5f, 0.0f, -0.5f, 5, 8, 0, 0.0f);
        this.setRotateAngle(this.Cloth1, -0.057595864f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -1.2f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.0f, -4.3f, 8, 7, 8, 0.0f);
        this.WhiskersR3_1 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR3_1.func_78793_a(-0.9f, 0.0f, -0.2f);
        this.WhiskersR3_1.func_78790_a(-0.6f, 0.0f, -0.2f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR3_1, 0.0f, 0.098087505f, 0.12967797f);
        this.ArmL = new ModelRenderer((ModelBase)this, 50, 16);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -1.8f, 3, 12, 4, 0.0f);
        this.WhiskersR2 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR2.func_78793_a(1.2f, 0.5f, 0.1f);
        this.WhiskersR2.func_78790_a(-0.7f, -0.1f, -0.3f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR2, 0.0f, 0.045553092f, -0.091106184f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.WhiskersR4 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR4.func_78793_a(1.0f, 0.0f, -0.1f);
        this.WhiskersR4.func_78790_a(-0.6f, 0.1f, -0.2f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR4, 0.0f, 0.0f, -0.18203785f);
        this.WhiskersR4_1 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR4_1.func_78793_a(-0.8f, 0.0f, -0.1f);
        this.WhiskersR4_1.func_78790_a(-0.6f, 0.1f, -0.2f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR4_1, 0.0f, 0.0f, 0.12618731f);
        this.WhiskersR2_1 = new ModelRenderer((ModelBase)this, 31, 1);
        this.WhiskersR2_1.func_78793_a(-0.9f, 0.3f, 0.1f);
        this.WhiskersR2_1.func_78790_a(-0.7f, -0.1f, -0.3f, 1, 3, 0, 0.0f);
        this.setRotateAngle(this.WhiskersR2_1, 0.0f, -0.026005406f, 0.06667158f);
        this.ArmRingR2 = new ModelRenderer((ModelBase)this, 25, 51);
        this.ArmRingR2.field_78809_i = true;
        this.ArmRingR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingR2.func_78790_a(-3.6f, 7.4f, -2.3f, 4, 1, 5, 0.0f);
        this.setRotateAngle(this.ArmRingR2, 0.0f, 0.0f, -0.10471976f);
        this.ArmRingR1 = new ModelRenderer((ModelBase)this, 25, 51);
        this.ArmRingR1.field_78809_i = true;
        this.ArmRingR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmRingR1.func_78790_a(-2.7f, 6.4f, -2.3f, 4, 1, 5, 0.0f);
        this.Head.func_78792_a(this.WhiskersR1_1);
        this.Body1.func_78792_a(this.Body2);
        this.ArmRingL1.func_78792_a(this.ArmRingL2);
        this.Head.func_78792_a(this.Hair);
        this.LegL.func_78792_a(this.LegL2);
        this.WhiskersR2.func_78792_a(this.WhiskersR3);
        this.ArmL.func_78792_a(this.ArmRingL1);
        this.Body2.func_78792_a(this.Body3);
        this.Body1.func_78792_a(this.Neck);
        this.Head.func_78792_a(this.WhiskersR1);
        this.Body1.func_78792_a(this.Cloth1);
        this.WhiskersR2_1.func_78792_a(this.WhiskersR3_1);
        this.WhiskersR1.func_78792_a(this.WhiskersR2);
        this.LegR.func_78792_a(this.LegR2);
        this.WhiskersR3.func_78792_a(this.WhiskersR4);
        this.WhiskersR3_1.func_78792_a(this.WhiskersR4_1);
        this.WhiskersR1_1.func_78792_a(this.WhiskersR2_1);
        this.ArmRingR1.func_78792_a(this.ArmRingR2);
        this.ArmR.func_78792_a(this.ArmRingR1);
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
        this.Cloth1.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

