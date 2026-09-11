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

public class ModelZamasu
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hair1;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer earR2;
    public ModelRenderer earL2;
    public ModelRenderer Body2;
    public ModelRenderer ClothF;
    public ModelRenderer Body3;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;

    public ModelZamasu() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.earR2 = new ModelRenderer((ModelBase)this, 30, 1);
        this.earR2.func_78793_a(-0.5f, 1.2f, 0.0f);
        this.earR2.func_78790_a(-0.4f, -0.4f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.earR2, 0.0f, -0.34906584f, -0.34906584f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 45);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 11.0f, 0.0f);
        this.LegL.func_78790_a(-1.9f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 3, 18);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(-1.0f, -0.9f, -1.8f, 6, 4, 4, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 46, 6);
        this.Hair2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair2.func_78790_a(-1.3f, -11.2f, -5.0f, 2, 2, 6, 0.0f);
        this.earL2 = new ModelRenderer((ModelBase)this, 30, 1);
        this.earL2.field_78809_i = true;
        this.earL2.func_78793_a(0.3f, 1.2f, 0.0f);
        this.earL2.func_78790_a(-0.5f, -0.4f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.earL2, 0.0f, 0.34906584f, 0.34906584f);
        this.ClothF = new ModelRenderer((ModelBase)this, 25, 51);
        this.ClothF.func_78793_a(0.0f, 9.0f, -2.1f);
        this.ClothF.func_78790_a(-2.0f, 0.0f, 0.1f, 4, 11, 0, 0.0f);
        this.setRotateAngle(this.ClothF, -0.061086524f, 0.0f, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 33, 0);
        this.Hair1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair1.func_78790_a(-1.4f, -9.7f, -4.0f, 3, 3, 6, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 20, 43);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -1.1f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.3f, -4.0f, 8, 8, 8, -0.5f);
        this.Body2 = new ModelRenderer((ModelBase)this, 23, 36);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.5f, 7.0f, -1.7f, 7, 2, 3, 0.0f);
        this.earR = new ModelRenderer((ModelBase)this, 0, 1);
        this.earR.func_78793_a(-3.2f, -1.9f, -1.5f);
        this.earR.func_78790_a(-4.2f, -2.1f, 0.0f, 4, 3, 0, 0.0f);
        this.setRotateAngle(this.earR, 0.1134464f, 0.5235988f, 0.34906584f);
        this.ArmR = new ModelRenderer((ModelBase)this, 0, 27);
        this.ArmR.func_78793_a(-5.0f, -0.1f, 0.0f);
        this.ArmR.func_78790_a(-1.9f, -0.8f, -1.8f, 3, 12, 4, -0.1f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 46, 0);
        this.Hair3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair3.func_78790_a(-0.7f, -12.0f, -1.7f, 1, 1, 2, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 3, 18);
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-5.0f, -0.9f, -1.8f, 6, 4, 4, 0.0f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 46, 6);
        this.Hair4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair4.func_78790_a(-1.5f, -10.6f, -0.6f, 2, 3, 6, 0.0f);
        this.setRotateAngle(this.Hair4, 0.8651597f, 0.0f, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 20, 23);
        this.Body1.func_78793_a(0.0f, -1.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -1.8f, 8, 7, 4, 0.0f);
        this.earL = new ModelRenderer((ModelBase)this, 0, 4);
        this.earL.func_78793_a(3.2f, -1.9f, -1.5f);
        this.earL.func_78790_a(0.2f, -2.1f, 0.0f, 4, 3, 0, 0.0f);
        this.setRotateAngle(this.earL, 0.1134464f, -0.5235988f, -0.34906584f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 45);
        this.LegR.func_78793_a(-1.9f, 11.0f, 0.0f);
        this.LegR.func_78790_a(-2.1f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 46, 0);
        this.Hair5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair5.func_78790_a(-1.3f, -9.2f, -1.8f, 1, 2, 2, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 0, 27);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, -0.1f, 0.0f);
        this.ArmL.func_78790_a(-1.1f, -0.8f, -1.8f, 3, 12, 4, -0.1f);
        this.earR.func_78792_a(this.earR2);
        this.ArmL.func_78792_a(this.ShoulderL);
        this.Hair1.func_78792_a(this.Hair2);
        this.earL.func_78792_a(this.earL2);
        this.Body1.func_78792_a(this.ClothF);
        this.Head.func_78792_a(this.Hair1);
        this.Body2.func_78792_a(this.Body3);
        this.Body1.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.earR);
        this.Hair1.func_78792_a(this.Hair3);
        this.ArmR.func_78792_a(this.ShoulderR);
        this.Hair1.func_78792_a(this.Hair4);
        this.Head.func_78792_a(this.earL);
        this.Hair4.func_78792_a(this.Hair5);
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
        this.ClothF.field_78795_f = -0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

