/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelLaunch
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer HairBack1;
    public ModelRenderer Hair;
    public ModelRenderer Ribbon;
    public ModelRenderer HairBack2;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer Hair6;
    public ModelRenderer Hair7;
    public ModelRenderer HairFront;
    public ModelRenderer Body2;
    public ModelRenderer Boobs;
    public ModelRenderer Body3;

    public ModelLaunch() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 30);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-3.0f, 5.8f, -1.6f, 6, 3, 3, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, -0.1f);
        this.Hair6 = new ModelRenderer((ModelBase)this, 49, 7);
        this.Hair6.func_78793_a(0.0f, -6.6f, -0.2f);
        this.Hair6.func_78790_a(-1.1f, -3.4f, -0.9f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair6, -0.091106184f, -0.091106184f, -0.7285004f);
        this.HairBack2 = new ModelRenderer((ModelBase)this, 47, 33);
        this.HairBack2.func_78793_a(0.0f, 3.9f, 0.3f);
        this.HairBack2.func_78790_a(-2.9f, 0.0f, -0.5f, 6, 3, 1, 0.0f);
        this.setRotateAngle(this.HairBack2, 0.091106184f, 0.0f, 0.0f);
        this.HairFront = new ModelRenderer((ModelBase)this, 41, 15);
        this.HairFront.func_78793_a(0.2f, -6.7f, -3.6f);
        this.HairFront.func_78790_a(-6.1f, -1.0f, 0.0f, 11, 7, 0, 0.0f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair3.func_78793_a(-2.5f, -2.0f, 2.0f);
        this.Hair3.func_78790_a(-1.1f, 0.0f, -0.7f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair3, 0.0f, 0.0f, 0.6953392f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(-0.5f, -3.0f, 0.0f, 1, 1, 1, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 37, 6);
        this.Hair2.func_78793_a(-1.1f, -6.2f, 1.7f);
        this.Hair2.func_78790_a(-3.8f, -1.6f, -0.8f, 3, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair2, 0.0f, -0.47403142f, 0.043284167f);
        this.Boobs = new ModelRenderer((ModelBase)this, 19, 31);
        this.Boobs.func_78793_a(0.0f, -0.5f, 0.0f);
        this.Boobs.func_78790_a(-3.0f, 1.8f, -0.4f, 6, 3, 2, 0.0f);
        this.setRotateAngle(this.Boobs, -0.59864795f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 18, 47);
        this.ArmR.func_78793_a(-4.3f, 3.0f, 0.0f);
        this.ArmR.func_78790_a(-1.9f, -1.5f, -1.8f, 3, 11, 4, -0.1f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 38);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-3.5f, 8.8f, -2.0f, 7, 2, 4, 0.0f);
        this.Ribbon = new ModelRenderer((ModelBase)this, 24, 18);
        this.Ribbon.func_78793_a(3.5f, -6.0f, -2.1f);
        this.Ribbon.func_78790_a(0.0f, -2.8f, 0.0f, 6, 7, 0, 0.0f);
        this.setRotateAngle(this.Ribbon, 0.0f, -0.4098033f, 0.0f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair4.func_78793_a(0.3f, -5.6f, 1.1f);
        this.Hair4.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.Hair4, -0.22968534f, -0.2375393f, -0.091106184f);
        this.Hair7 = new ModelRenderer((ModelBase)this, 49, 0);
        this.Hair7.func_78793_a(1.3f, -6.0f, -0.2f);
        this.Hair7.func_78790_a(-1.0f, -2.6f, -0.8f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair7, 0.0f, 0.0f, 1.1383038f);
        this.HairBack1 = new ModelRenderer((ModelBase)this, 39, 26);
        this.HairBack1.func_78793_a(0.0f, -0.4f, 2.3f);
        this.HairBack1.func_78790_a(-4.5f, 0.0f, -0.5f, 9, 4, 2, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 46);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, -0.1f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 18);
        this.Body.func_78793_a(0.0f, 1.3f, 0.0f);
        this.Body.func_78790_a(-3.5f, 0.0f, -2.0f, 7, 6, 4, -0.2f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair1.func_78793_a(-2.0f, -6.8f, -1.5f);
        this.Hair1.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair1, -0.22759093f, -0.091106184f, -1.2292354f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 1.5f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.5f, -4.0f, 8, 8, 8, -0.5f);
        this.ArmL = new ModelRenderer((ModelBase)this, 18, 47);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.3f, 3.0f, 0.0f);
        this.ArmL.func_78790_a(-1.1f, -1.5f, -1.8f, 3, 11, 4, -0.1f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair5.func_78793_a(2.4f, -2.1f, 2.0f);
        this.Hair5.func_78790_a(-1.0f, -0.2f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair5, 0.0f, 0.011913514f, -0.7876671f);
        this.Body.func_78792_a(this.Body2);
        this.Hair.func_78792_a(this.Hair6);
        this.HairBack1.func_78792_a(this.HairBack2);
        this.Hair.func_78792_a(this.HairFront);
        this.Hair.func_78792_a(this.Hair3);
        this.Head.func_78792_a(this.Hair);
        this.Hair.func_78792_a(this.Hair2);
        this.Body.func_78792_a(this.Boobs);
        this.Body2.func_78792_a(this.Body3);
        this.Head.func_78792_a(this.Ribbon);
        this.Hair.func_78792_a(this.Hair4);
        this.Hair.func_78792_a(this.Hair7);
        this.Head.func_78792_a(this.HairBack1);
        this.Hair.func_78792_a(this.Hair1);
        this.Hair.func_78792_a(this.Hair5);
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
    }
}

