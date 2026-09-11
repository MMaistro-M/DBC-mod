/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.db;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelYamcha2
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer HairBack1;
    public ModelRenderer Hair;
    public ModelRenderer HairBack2;
    public ModelRenderer Hair8;
    public ModelRenderer Hair9;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer Hair6;
    public ModelRenderer Hair7;
    public ModelRenderer Hair11;
    public ModelRenderer Hair12;
    public ModelRenderer Hair13;
    public ModelRenderer Hair14;

    public ModelYamcha2() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hair11 = new ModelRenderer((ModelBase)this, 49, 0);
        this.Hair11.func_78793_a(2.4f, -6.6f, -2.3f);
        this.Hair11.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair11, -0.7007497f, 0.091106184f, 1.4114478f);
        this.Hair8 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair8.func_78793_a(1.5f, -0.4f, 0.0f);
        this.Hair8.func_78790_a(-1.0f, 0.0f, -0.7f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair8, 0.17453292f, 0.0f, -0.8651597f);
        this.Hair13 = new ModelRenderer((ModelBase)this, 31, 1);
        this.Hair13.func_78793_a(-0.1f, -7.6f, -3.9f);
        this.Hair13.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair13, 2.4130921f, 0.091106184f, 0.18203785f);
        this.Hair9 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair9.func_78793_a(-1.4f, -0.4f, 0.0f);
        this.Hair9.func_78790_a(-1.1f, 0.0f, -0.7f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair9, 0.27314404f, 0.0f, 0.8196066f);
        this.Hair7 = new ModelRenderer((ModelBase)this, 50, 8);
        this.Hair7.func_78793_a(0.2f, -7.0f, -1.9f);
        this.Hair7.func_78790_a(-0.5f, -2.3f, -0.8f, 1, 2, 2, 0.0f);
        this.setRotateAngle(this.Hair7, 0.0f, 0.0f, 0.3970624f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair3.func_78793_a(-2.0f, -2.8f, 2.5f);
        this.Hair3.func_78790_a(-1.1f, 0.0f, -0.7f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair3, 0.0f, 0.0f, 0.6953392f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 37, 6);
        this.Hair2.func_78793_a(-1.3f, -6.7f, -2.0f);
        this.Hair2.func_78790_a(-4.1f, -1.6f, -0.8f, 3, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair2, 0.0f, -0.47403142f, 0.043284167f);
        this.Hair6 = new ModelRenderer((ModelBase)this, 49, 0);
        this.Hair6.func_78793_a(0.0f, -6.5f, -1.5f);
        this.Hair6.func_78790_a(-1.1f, -3.4f, -0.9f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair6, -0.091106184f, -0.091106184f, -0.7285004f);
        this.Body = new ModelRenderer((ModelBase)this, 16, 16);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 30, 0);
        this.Hair4.func_78793_a(1.9f, -7.0f, 2.1f);
        this.Hair4.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair4, -0.22968534f, -0.2375393f, 0.93462384f);
        this.HairBack1 = new ModelRenderer((ModelBase)this, 1, 33);
        this.HairBack1.func_78793_a(0.0f, 0.0f, 2.7f);
        this.HairBack1.func_78790_a(-2.0f, 0.0f, -0.5f, 4, 3, 2, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 30, 0);
        this.Hair1.func_78793_a(-2.0f, -7.1f, -2.8f);
        this.Hair1.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair1, 0.22759093f, -0.091106184f, -0.3642502f);
        this.Hair12 = new ModelRenderer((ModelBase)this, 32, 1);
        this.Hair12.func_78793_a(-1.6f, -7.9f, -3.9f);
        this.Hair12.func_78790_a(-1.0f, -2.6f, -0.8f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair12, 2.5953045f, 0.13665928f, 0.18203785f);
        this.ArmR = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(-0.5f, -3.0f, 0.0f, 1, 1, 1, 0.0f);
        this.HairBack2 = new ModelRenderer((ModelBase)this, 0, 40);
        this.HairBack2.func_78793_a(0.0f, 2.8f, 0.3f);
        this.HairBack2.func_78790_a(-1.5f, 0.0f, -0.5f, 3, 3, 1, 0.0f);
        this.setRotateAngle(this.HairBack2, 0.091106184f, 0.0f, 0.0f);
        this.Hair14 = new ModelRenderer((ModelBase)this, 31, 1);
        this.Hair14.func_78793_a(2.4f, -7.8f, -3.9f);
        this.Hair14.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair14, 2.6862361f, -0.3642502f, -0.13665928f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair5.func_78793_a(2.2f, -2.8f, 2.5f);
        this.Hair5.func_78790_a(-1.0f, -0.2f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair5, 0.0f, 0.0f, -0.7876671f);
        this.Hair.func_78792_a(this.Hair11);
        this.HairBack1.func_78792_a(this.Hair8);
        this.Hair.func_78792_a(this.Hair13);
        this.HairBack1.func_78792_a(this.Hair9);
        this.Hair.func_78792_a(this.Hair7);
        this.Hair.func_78792_a(this.Hair3);
        this.Hair.func_78792_a(this.Hair2);
        this.Hair.func_78792_a(this.Hair6);
        this.Hair.func_78792_a(this.Hair4);
        this.Head.func_78792_a(this.HairBack1);
        this.Hair.func_78792_a(this.Hair1);
        this.Hair.func_78792_a(this.Hair12);
        this.Head.func_78792_a(this.Hair);
        this.HairBack1.func_78792_a(this.HairBack2);
        this.Hair.func_78792_a(this.Hair14);
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

