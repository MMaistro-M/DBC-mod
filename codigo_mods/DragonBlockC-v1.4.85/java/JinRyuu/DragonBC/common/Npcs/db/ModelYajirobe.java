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

public class ModelYajirobe
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer LegR;
    public ModelRenderer LegL;
    public ModelRenderer Hair;
    public ModelRenderer Hair1;
    public ModelRenderer Hair2;
    public ModelRenderer Hair3;
    public ModelRenderer Hair4;
    public ModelRenderer Hair5;
    public ModelRenderer Hair6;
    public ModelRenderer Hair7;
    public ModelRenderer Hair8;
    public ModelRenderer Hair9;
    public ModelRenderer Hair10;
    public ModelRenderer Hair11;
    public ModelRenderer Hair12;
    public ModelRenderer Hair13;
    public ModelRenderer Hair14;
    public ModelRenderer Hair15;
    public ModelRenderer Hair16;
    public ModelRenderer Hair17;
    public ModelRenderer Hair18;
    public ModelRenderer Hair19;
    public ModelRenderer Hair20;
    public ModelRenderer Hair21;
    public ModelRenderer Body2;
    public ModelRenderer Sword;
    public ModelRenderer Sheet;
    public ModelRenderer Guard;
    public ModelRenderer Handle;
    public ModelRenderer Sheet2;

    public ModelYajirobe() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Hair4 = new ModelRenderer((ModelBase)this, 29, 1);
        this.Hair4.func_78793_a(-3.4f, -6.0f, -3.5f);
        this.Hair4.func_78790_a(-0.4f, 0.2f, -0.5f, 1, 2, 1, 0.0f);
        this.setRotateAngle(this.Hair4, -0.3642502f, 0.5009095f, 0.22759093f);
        this.ArmL = new ModelRenderer((ModelBase)this, 23, 45);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(6.0f, 4.0f, 1.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.5f, 4, 11, 5, 0.0f);
        this.Sheet = new ModelRenderer((ModelBase)this, 46, 48);
        this.Sheet.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Sheet.func_78790_a(-0.5f, -0.5f, -1.0f, 1, 8, 2, 0.0f);
        this.Hair19 = new ModelRenderer((ModelBase)this, 34, 1);
        this.Hair19.func_78793_a(-3.8f, -1.5f, -1.7f);
        this.Hair19.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair19, -0.5462881f, 0.7679449f, 0.0f);
        this.Hair11 = new ModelRenderer((ModelBase)this, 47, 20);
        this.Hair11.field_78809_i = true;
        this.Hair11.func_78793_a(2.1f, -4.0f, -1.0f);
        this.Hair11.func_78790_a(-1.0f, -0.2f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.Hair11, 0.0f, 0.0f, -0.7853982f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 16);
        this.Body.func_78793_a(0.0f, 2.0f, 1.0f);
        this.Body.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 5, 6, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 0, 0);
        this.Hair.func_78793_a(0.0f, -0.9f, 0.0f);
        this.Hair.func_78790_a(-0.5f, -3.0f, 0.0f, 1, 1, 1, 0.0f);
        this.Hair21 = new ModelRenderer((ModelBase)this, 48, 6);
        this.Hair21.func_78793_a(0.0f, 1.3f, -0.4f);
        this.Hair21.func_78790_a(-3.0f, -1.0f, -0.6f, 6, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair21, -0.31869712f, 0.0f, 0.0f);
        this.Hair18 = new ModelRenderer((ModelBase)this, 34, 1);
        this.Hair18.field_78809_i = true;
        this.Hair18.func_78793_a(3.8f, -1.5f, -1.7f);
        this.Hair18.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair18, -0.5462881f, -0.7679449f, 0.0f);
        this.Hair9 = new ModelRenderer((ModelBase)this, 49, 12);
        this.Hair9.field_78809_i = true;
        this.Hair9.func_78793_a(1.7f, -6.3f, 2.1f);
        this.Hair9.func_78790_a(-1.1f, -3.4f, -0.9f, 3, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair9, -1.2217305f, 0.9075712f, -0.090757124f);
        this.Sheet2 = new ModelRenderer((ModelBase)this, 53, 48);
        this.Sheet2.func_78793_a(0.0f, 7.8f, 0.0f);
        this.Sheet2.func_78790_a(-0.5f, -0.5f, -1.0f, 1, 7, 2, 0.0f);
        this.setRotateAngle(this.Sheet2, 0.073303826f, 0.0f, 0.0f);
        this.Hair14 = new ModelRenderer((ModelBase)this, 47, 29);
        this.Hair14.func_78793_a(-2.7f, -1.9f, 1.5f);
        this.Hair14.func_78790_a(-2.5f, 0.0f, -3.0f, 3, 3, 5, 0.0f);
        this.setRotateAngle(this.Hair14, 0.27314404f, 0.0f, 0.8196066f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 28);
        this.Body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body2.func_78790_a(-5.0f, 5.0f, -3.5f, 10, 6, 7, 0.0f);
        this.Guard = new ModelRenderer((ModelBase)this, 50, 58);
        this.Guard.func_78793_a(0.0f, -0.6f, 0.0f);
        this.Guard.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 0, 3, 0.0f);
        this.Hair3 = new ModelRenderer((ModelBase)this, 29, 1);
        this.Hair3.func_78793_a(-1.9f, -5.9f, -3.7f);
        this.Hair3.func_78790_a(-0.4f, 0.0f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair3, -0.5462881f, 0.18203785f, 0.18203785f);
        this.Hair16 = new ModelRenderer((ModelBase)this, 49, 38);
        this.Hair16.func_78793_a(-2.5f, -4.0f, 2.2f);
        this.Hair16.func_78790_a(-2.0f, -1.0f, -1.0f, 4, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair16, 0.7679449f, -0.9599311f, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 1, 45);
        this.LegR.func_78793_a(-2.6f, 13.0f, 1.0f);
        this.LegR.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 11, 5, 0.1f);
        this.Sword = new ModelRenderer((ModelBase)this, 0, 0);
        this.Sword.func_78793_a(5.8f, 7.0f, -4.4f);
        this.Sword.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.setRotateAngle(this.Sword, 1.3185962f, -0.07853982f, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 34, 1);
        this.Hair1.func_78793_a(2.8f, -5.7f, -3.7f);
        this.Hair1.func_78790_a(-1.0f, -0.3f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair1, -0.3642502f, -0.4553564f, -0.045553092f);
        this.Hair6 = new ModelRenderer((ModelBase)this, 33, 14);
        this.Hair6.func_78793_a(0.2f, -6.3f, -1.9f);
        this.Hair6.func_78790_a(-0.5f, -2.3f, -0.8f, 1, 2, 2, 0.0f);
        this.setRotateAngle(this.Hair6, 0.0f, 0.0f, 0.3970624f);
        this.Hair17 = new ModelRenderer((ModelBase)this, 49, 38);
        this.Hair17.func_78793_a(0.0f, -3.6f, 3.1f);
        this.Hair17.func_78790_a(-2.0f, -1.0f, -1.0f, 4, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair17, 0.7285004f, 0.0f, 0.0f);
        this.Hair7 = new ModelRenderer((ModelBase)this, 40, 14);
        this.Hair7.func_78793_a(-0.7f, -6.6f, 0.1f);
        this.Hair7.func_78790_a(-1.0f, -2.6f, -0.8f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair7, 0.22759093f, -0.091106184f, -0.3642502f);
        this.Hair13 = new ModelRenderer((ModelBase)this, 47, 29);
        this.Hair13.field_78809_i = true;
        this.Hair13.func_78793_a(3.2f, -1.9f, 1.5f);
        this.Hair13.func_78790_a(-0.5f, -0.2f, -3.0f, 3, 3, 5, 0.0f);
        this.setRotateAngle(this.Hair13, 0.17453292f, 0.0f, -0.8196066f);
        this.Hair5 = new ModelRenderer((ModelBase)this, 33, 6);
        this.Hair5.func_78793_a(1.8f, -6.1f, -2.6f);
        this.Hair5.func_78790_a(-1.1f, -3.4f, -0.9f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair5, -0.091106184f, 0.0f, 1.0471976f);
        this.Hair10 = new ModelRenderer((ModelBase)this, 49, 12);
        this.Hair10.func_78793_a(-1.4f, -6.1f, 1.5f);
        this.Hair10.func_78790_a(-1.1f, -3.9f, -0.9f, 3, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair10, -1.2217305f, -1.0471976f, 0.091106184f);
        this.Hair20 = new ModelRenderer((ModelBase)this, 42, 0);
        this.Hair20.func_78793_a(0.0f, -0.6f, 3.1f);
        this.Hair20.func_78790_a(-4.5f, -1.0f, -0.6f, 9, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair20, 0.7285004f, 0.0f, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 1, 45);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.6f, 13.0f, 1.0f);
        this.LegL.func_78790_a(-2.5f, 0.0f, -2.5f, 5, 11, 5, 0.1f);
        this.Handle = new ModelRenderer((ModelBase)this, 60, 49);
        this.Handle.func_78793_a(0.0f, -0.5f, 0.0f);
        this.Handle.func_78790_a(-0.5f, -5.0f, -0.5f, 1, 5, 1, 0.0f);
        this.Hair15 = new ModelRenderer((ModelBase)this, 49, 38);
        this.Hair15.field_78809_i = true;
        this.Hair15.func_78793_a(2.6f, -4.0f, 2.2f);
        this.Hair15.func_78790_a(-2.0f, -1.0f, -1.0f, 4, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair15, 0.7679449f, 0.9599311f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 23, 45);
        this.ArmR.func_78793_a(-6.0f, 4.0f, 1.0f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.5f, 4, 11, 5, 0.0f);
        this.Hair8 = new ModelRenderer((ModelBase)this, 49, 12);
        this.Hair8.func_78793_a(-2.0f, -6.3f, -2.3f);
        this.Hair8.func_78790_a(-2.2f, -3.4f, -0.9f, 3, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair8, 0.27314404f, -0.090757124f, -1.0471976f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 2.0f, 0.6f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.Hair12 = new ModelRenderer((ModelBase)this, 47, 20);
        this.Hair12.func_78793_a(-2.1f, -4.0f, -1.0f);
        this.Hair12.func_78790_a(-3.0f, -0.4f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.Hair12, 0.0f, 0.0f, 0.7853982f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 34, 1);
        this.Hair2.func_78793_a(0.2f, -5.7f, -3.8f);
        this.Hair2.func_78790_a(-1.0f, -0.1f, -0.5f, 2, 3, 1, 0.0f);
        this.setRotateAngle(this.Hair2, -0.5009095f, -0.091106184f, 0.13665928f);
        this.Hair.func_78792_a(this.Hair4);
        this.Sword.func_78792_a(this.Sheet);
        this.Hair.func_78792_a(this.Hair19);
        this.Hair.func_78792_a(this.Hair11);
        this.Head.func_78792_a(this.Hair);
        this.Hair20.func_78792_a(this.Hair21);
        this.Hair.func_78792_a(this.Hair18);
        this.Hair.func_78792_a(this.Hair9);
        this.Sheet.func_78792_a(this.Sheet2);
        this.Hair.func_78792_a(this.Hair14);
        this.Body.func_78792_a(this.Body2);
        this.Sword.func_78792_a(this.Guard);
        this.Hair.func_78792_a(this.Hair3);
        this.Hair.func_78792_a(this.Hair16);
        this.Body2.func_78792_a(this.Sword);
        this.Hair.func_78792_a(this.Hair1);
        this.Hair.func_78792_a(this.Hair6);
        this.Hair.func_78792_a(this.Hair17);
        this.Hair.func_78792_a(this.Hair7);
        this.Hair.func_78792_a(this.Hair13);
        this.Hair.func_78792_a(this.Hair5);
        this.Hair.func_78792_a(this.Hair10);
        this.Hair.func_78792_a(this.Hair20);
        this.Sword.func_78792_a(this.Handle);
        this.Hair.func_78792_a(this.Hair15);
        this.Hair.func_78792_a(this.Hair8);
        this.Hair.func_78792_a(this.Hair12);
        this.Hair.func_78792_a(this.Hair2);
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

