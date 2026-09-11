/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbsbroly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDBSBrolyBuff
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer HairBack1;
    public ModelRenderer Hair;
    public ModelRenderer HairBack2;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer Hair6;
    public ModelRenderer Hair7;
    public ModelRenderer Hair8;
    public ModelRenderer Hair9;
    public ModelRenderer Hair11;
    public ModelRenderer Hair12;
    public ModelRenderer Hair13;
    public ModelRenderer Hair14;
    public ModelRenderer Hair15;
    public ModelRenderer Hair16;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Chest;
    public ModelRenderer Cloth;
    public ModelRenderer ArmR2;
    public ModelRenderer ShoulderR;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmL2;
    public ModelRenderer ShoulderL;
    public ModelRenderer ArmL3;

    public ModelDBSBrolyBuff() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-5.5f, 6.2f, -2.9f, 11, 6, 6, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(-0.5f, -3.0f, 0.0f, 1, 1, 1, 0.0f);
        this.Hair9 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair9.func_78793_a(-3.6f, -3.0f, 1.5f);
        this.Hair9.func_78790_a(-1.1f, 0.0f, -0.7f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair9, 0.27314404f, 0.0f, 0.8196066f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 67, 50);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 4.2f, -0.5f);
        this.ArmL3.func_78790_a(-2.1f, 0.0f, -1.8f, 5, 8, 5, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -8.0f, -0.5f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.Cloth = new ModelRenderer((ModelBase)this, 90, 46);
        this.Cloth.func_78793_a(0.0f, 15.5f, 0.8f);
        this.Cloth.func_78790_a(-6.6f, 0.0f, -0.8f, 13, 10, 4, 0.0f);
        this.Hair6 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Hair6.func_78793_a(-1.3f, -7.4f, -1.4f);
        this.Hair6.func_78790_a(-1.1f, -6.6f, -1.9f, 3, 7, 5, 0.0f);
        this.setRotateAngle(this.Hair6, -0.31869712f, 0.07382743f, -0.22130775f);
        this.Body3 = new ModelRenderer((ModelBase)this, 0, 48);
        this.Body3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body3.func_78790_a(-6.5f, 12.0f, -3.1f, 13, 5, 7, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 66, 27);
        this.ArmR1.func_78793_a(-8.0f, -5.2f, 0.2f);
        this.ArmR1.func_78790_a(-5.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 41, 42);
        this.LegR.func_78793_a(-3.0f, 9.0f, 0.0f);
        this.LegR.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 15, 6, 0.0f);
        this.Hair12 = new ModelRenderer((ModelBase)this, 33, 1);
        this.Hair12.func_78793_a(1.9f, -6.6f, -3.6f);
        this.Hair12.func_78790_a(-0.7f, -2.8f, -0.3f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair12, 2.7317894f, -0.31869712f, -0.31869712f);
        this.Hair14 = new ModelRenderer((ModelBase)this, 52, 14);
        this.Hair14.func_78793_a(3.6f, -6.6f, -3.9f);
        this.Hair14.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair14, 2.5057693f, -0.6588618f, -0.17575465f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 67, 50);
        this.ArmR3.func_78793_a(0.0f, 4.2f, -0.5f);
        this.ArmR3.func_78790_a(-2.9f, 0.0f, -1.9f, 5, 8, 5, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 91, 26);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderL.func_78790_a(-1.1f, -3.1f, -3.6f, 7, 6, 7, 0.0f);
        this.Hair15 = new ModelRenderer((ModelBase)this, 82, 13);
        this.Hair15.func_78793_a(-1.1f, -6.0f, 2.0f);
        this.Hair15.func_78790_a(-4.7f, -3.0f, -1.6f, 4, 5, 3, 0.0f);
        this.setRotateAngle(this.Hair15, 0.0f, 0.43022367f, 0.5009095f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 52, 1);
        this.Hair1.func_78793_a(-3.3f, -7.1f, -3.4f);
        this.Hair1.func_78790_a(-1.0f, -0.4f, -3.6f, 2, 1, 4, 0.0f);
        this.setRotateAngle(this.Hair1, 0.84788096f, 0.6984808f, 0.17139134f);
        this.Hair7 = new ModelRenderer((ModelBase)this, 64, 8);
        this.Hair7.func_78793_a(1.1f, -7.8f, 1.0f);
        this.Hair7.func_78790_a(-1.3f, -3.7f, -2.4f, 4, 5, 5, 0.0f);
        this.setRotateAngle(this.Hair7, -0.3195698f, 0.0f, 0.8972738f);
        this.LegL = new ModelRenderer((ModelBase)this, 41, 42);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(3.0f, 9.0f, 0.0f);
        this.LegL.func_78790_a(-3.0f, 0.0f, -3.0f, 6, 15, 6, 0.0f);
        this.Hair13 = new ModelRenderer((ModelBase)this, 52, 14);
        this.Hair13.func_78793_a(-0.9f, -7.3f, -3.9f);
        this.Hair13.func_78790_a(-1.0f, -3.4f, -0.8f, 2, 4, 1, 0.0f);
        this.setRotateAngle(this.Hair13, 2.438225f, 0.27314404f, -0.045553092f);
        this.HairBack1 = new ModelRenderer((ModelBase)this, 101, 2);
        this.HairBack1.func_78793_a(0.0f, -2.4f, 2.9f);
        this.HairBack1.func_78790_a(-4.0f, 0.0f, -0.5f, 8, 3, 2, 0.0f);
        this.setRotateAngle(this.HairBack1, 0.31642818f, 0.0f, 0.0f);
        this.Hair16 = new ModelRenderer((ModelBase)this, 35, 7);
        this.Hair16.func_78793_a(0.9f, -7.2f, -1.4f);
        this.Hair16.func_78790_a(-1.0f, -5.1f, -1.8f, 3, 5, 5, 0.0f);
        this.setRotateAngle(this.Hair16, -0.27314404f, 0.0f, 0.045553092f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 67, 40);
        this.ArmR2.func_78793_a(-1.7f, 2.0f, 0.0f);
        this.ArmR2.func_78790_a(-2.7f, 0.4f, -2.5f, 5, 4, 5, 0.0f);
        this.HairBack2 = new ModelRenderer((ModelBase)this, 103, 9);
        this.HairBack2.func_78793_a(0.0f, 2.9f, 0.6f);
        this.HairBack2.func_78790_a(-2.9f, 0.0f, -0.5f, 6, 2, 1, 0.0f);
        this.setRotateAngle(this.HairBack2, 0.2952383f, 0.0f, 0.0f);
        this.Hair8 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair8.func_78793_a(3.6f, -3.0f, 1.5f);
        this.Hair8.func_78790_a(-1.0f, 0.0f, -0.8f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair8, 0.17453292f, 0.0f, -0.8651597f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair5.func_78793_a(4.2f, -4.1f, 0.2f);
        this.Hair5.func_78790_a(-1.0f, -2.8f, -0.8f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair5, 0.0f, 0.0f, 0.70040065f);
        this.Hair11 = new ModelRenderer((ModelBase)this, 52, 8);
        this.Hair11.func_78793_a(2.8f, -7.3f, -2.3f);
        this.Hair11.func_78790_a(-1.0f, -0.7f, -0.8f, 3, 2, 3, 0.0f);
        this.setRotateAngle(this.Hair11, 0.0f, 0.0f, 0.0991347f);
        this.Hair4 = new ModelRenderer((ModelBase)this, 82, 2);
        this.Hair4.func_78793_a(2.5f, -6.2f, 2.3f);
        this.Hair4.func_78790_a(-1.5f, -2.6f, -1.4f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.Hair4, -0.36878806f, -0.13526301f, 1.0815805f);
        this.Chest = new ModelRenderer((ModelBase)this, 35, 33);
        this.Chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chest.func_78790_a(-6.0f, 1.8f, -3.4f, 12, 5, 1, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 91, 26);
        this.ShoulderR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ShoulderR.func_78790_a(-5.9f, -3.1f, -3.6f, 7, 6, 7, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 66, 27);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(8.0f, -5.2f, 0.2f);
        this.ArmL1.func_78790_a(-1.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 67, 40);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(1.7f, 2.0f, 0.0f);
        this.ArmL2.func_78790_a(-2.3f, 0.4f, -2.5f, 5, 4, 5, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Body1.func_78793_a(0.0f, -8.0f, 0.0f);
        this.Body1.func_78790_a(-7.0f, 0.0f, -2.5f, 14, 8, 6, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 64, 0);
        this.Hair2.func_78793_a(-3.3f, -7.4f, 0.3f);
        this.Hair2.func_78790_a(-2.0f, -3.5f, -2.2f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.Hair2, 0.0f, 0.0f, -0.675966f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 39, 0);
        this.Hair3.func_78793_a(-4.2f, -4.1f, -0.2f);
        this.Hair3.func_78790_a(-0.9f, -2.8f, -0.7f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair3, 0.0f, 0.0f, -0.7005752f);
        this.Body1.func_78792_a(this.Body2);
        this.Head.func_78792_a(this.Hair);
        this.Hair.func_78792_a(this.Hair9);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.Body3.func_78792_a(this.Cloth);
        this.Hair.func_78792_a(this.Hair6);
        this.Body2.func_78792_a(this.Body3);
        this.Hair.func_78792_a(this.Hair12);
        this.Hair.func_78792_a(this.Hair14);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.ArmL1.func_78792_a(this.ShoulderL);
        this.Hair.func_78792_a(this.Hair15);
        this.Hair.func_78792_a(this.Hair1);
        this.Hair.func_78792_a(this.Hair7);
        this.Hair.func_78792_a(this.Hair13);
        this.Head.func_78792_a(this.HairBack1);
        this.Hair.func_78792_a(this.Hair16);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.HairBack1.func_78792_a(this.HairBack2);
        this.Hair.func_78792_a(this.Hair8);
        this.Hair.func_78792_a(this.Hair5);
        this.Hair.func_78792_a(this.Hair11);
        this.Hair.func_78792_a(this.Hair4);
        this.Body2.func_78792_a(this.Chest);
        this.ArmR1.func_78792_a(this.ShoulderR);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.Hair.func_78792_a(this.Hair2);
        this.Hair.func_78792_a(this.Hair3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body1.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
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
        this.Cloth.field_78795_f = 0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegR.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        this.Cloth.field_78795_f = 0.15f + this.LegL.field_78795_f * (float)(this.LegR.field_78795_f >= 0.0f ? -1 : 1) * 1.0f;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

