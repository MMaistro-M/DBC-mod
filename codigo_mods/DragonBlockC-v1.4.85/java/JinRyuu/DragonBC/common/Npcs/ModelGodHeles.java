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

public class ModelGodHeles
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer Hat;
    public ModelRenderer HairR;
    public ModelRenderer HairL;
    public ModelRenderer HairB;
    public ModelRenderer Neck;
    public ModelRenderer Body2;
    public ModelRenderer Boobs;
    public ModelRenderer Body3;
    public ModelRenderer Cloth1;
    public ModelRenderer LegL2;
    public ModelRenderer LegR2;

    public ModelGodHeles() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body1 = new ModelRenderer((ModelBase)this, 21, 23);
        this.Body1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body1.func_78790_a(-4.0f, 0.0f, -1.8f, 8, 5, 4, 0.0f);
        this.Cloth1 = new ModelRenderer((ModelBase)this, 24, 50);
        this.Cloth1.func_78793_a(0.0f, 11.0f, -1.6f);
        this.Cloth1.func_78790_a(-2.5f, 0.0f, -0.5f, 5, 10, 0, 0.0f);
        this.setRotateAngle(this.Cloth1, -0.04712389f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 46, 26);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-2.0f, -2.0f, -1.8f, 3, 12, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 49);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-0.9f, 0.2f, -1.8f, 3, 8, 4, 0.3f);
        this.Body3 = new ModelRenderer((ModelBase)this, 20, 40);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-4.0f, 8.0f, -2.0f, 8, 4, 4, 0.0f);
        this.LegL2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.LegL2.field_78809_i = true;
        this.LegL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegL2.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hat = new ModelRenderer((ModelBase)this, 33, 1);
        this.Hat.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hat.func_78790_a(-4.0f, -12.6f, -4.5f, 8, 7, 8, 0.0f);
        this.setRotateAngle(this.Hat, -0.08726646f, 0.0f, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 23, 33);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-2.9f, 5.0f, -1.6f, 6, 3, 3, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -0.6f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.4f);
        this.LegR2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.LegR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.LegR2.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.HairB = new ModelRenderer((ModelBase)this, 67, 17);
        this.HairB.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairB.func_78790_a(-3.0f, -5.9f, 3.0f, 6, 7, 1, 0.0f);
        this.setRotateAngle(this.HairB, 0.02443461f, 0.0f, 0.0f);
        this.Boobs = new ModelRenderer((ModelBase)this, 1, 24);
        this.Boobs.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Boobs.func_78790_a(-3.5f, 1.6f, -1.2f, 7, 3, 2, 0.0f);
        this.setRotateAngle(this.Boobs, -0.4537856f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 6, 17);
        this.Neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Neck.func_78790_a(-2.0f, -1.6f, -0.8f, 4, 2, 2, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 46, 26);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -1.8f, 3, 12, 4, 0.0f);
        this.HairR = new ModelRenderer((ModelBase)this, 67, 2);
        this.HairR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairR.func_78790_a(-4.0f, -6.2f, -3.2f, 1, 7, 7, 0.0f);
        this.setRotateAngle(this.HairR, 0.0f, 0.0f, 0.02443461f);
        this.HairL = new ModelRenderer((ModelBase)this, 67, 2);
        this.HairL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HairL.func_78790_a(3.0f, -6.2f, -3.2f, 1, 7, 7, 0.0f);
        this.setRotateAngle(this.HairL, 0.0f, 0.0f, -0.02443461f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 49);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.1f, 0.2f, -1.8f, 3, 8, 4, 0.3f);
        this.Body1.func_78792_a(this.Cloth1);
        this.Body1.func_78792_a(this.Body3);
        this.LegL.func_78792_a(this.LegL2);
        this.Head.func_78792_a(this.Hat);
        this.Body1.func_78792_a(this.Body2);
        this.LegR.func_78792_a(this.LegR2);
        this.Head.func_78792_a(this.HairB);
        this.Body1.func_78792_a(this.Boobs);
        this.Body1.func_78792_a(this.Neck);
        this.Head.func_78792_a(this.HairR);
        this.Head.func_78792_a(this.HairL);
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

