/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbbaba;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDevil
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer LegL;
    public ModelRenderer LegR;
    public ModelRenderer tail1;
    public ModelRenderer HornL;
    public ModelRenderer HornR;
    public ModelRenderer HornL2;
    public ModelRenderer HornL3;
    public ModelRenderer HornR2;
    public ModelRenderer HornR3;
    public ModelRenderer WingR;
    public ModelRenderer WingL;
    public ModelRenderer tail2;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;

    public ModelDevil() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.WingL = new ModelRenderer((ModelBase)this, 0, 34);
        this.WingL.field_78809_i = true;
        this.WingL.func_78793_a(2.9f, 2.0f, 2.0f);
        this.WingL.func_78790_a(0.0f, -9.0f, 0.0f, 19, 19, 0, 0.0f);
        this.setRotateAngle(this.WingL, 0.0f, -0.17453292f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.HornL2 = new ModelRenderer((ModelBase)this, 42, 8);
        this.HornL2.field_78809_i = true;
        this.HornL2.func_78793_a(0.0f, -3.0f, 0.0f);
        this.HornL2.func_78790_a(-1.0f, -2.8f, -1.0f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.HornL2, -0.12217305f, 0.0f, 0.12217305f);
        this.tail2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.tail2.func_78793_a(0.0f, 0.0f, 4.0f);
        this.tail2.func_78790_a(-0.5f, -0.0f, 0.0f, 1, 0, 4, 0.0f);
        this.LegR = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegR.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.LegR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.tail5 = new ModelRenderer((ModelBase)this, 32, 5);
        this.tail5.func_78793_a(0.0f, 0.0f, 4.0f);
        this.tail5.func_78790_a(-1.5f, 0.0f, 0.0f, 3, 0, 3, 0.0f);
        this.HornR3 = new ModelRenderer((ModelBase)this, 51, 9);
        this.HornR3.func_78793_a(0.0f, -3.2f, 0.0f);
        this.HornR3.func_78790_a(-0.6f, -2.6f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.HornR3, -0.08726646f, 0.0f, -0.08726646f);
        this.HornR = new ModelRenderer((ModelBase)this, 42, 1);
        this.HornR.func_78793_a(-2.1f, -7.3f, -1.3f);
        this.HornR.func_78790_a(-1.5f, -3.0f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.HornR, -0.43633232f, 0.0f, -0.04363323f);
        this.ArmL = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(5.0f, 2.0f, 0.0f);
        this.ArmL.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.tail3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.tail3.func_78793_a(0.0f, 0.0f, 4.0f);
        this.tail3.func_78790_a(-0.5f, 0.0f, 0.0f, 1, 0, 4, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 16, 16);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.tail1.func_78793_a(0.0f, 10.4f, 1.9f);
        this.tail1.func_78790_a(-0.5f, 0.0f, 0.0f, 1, 0, 4, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 40, 16);
        this.ArmR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.ArmR.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        this.tail4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.tail4.func_78793_a(0.0f, 0.0f, 4.0f);
        this.tail4.func_78790_a(-0.5f, 0.0f, 0.0f, 1, 0, 4, 0.0f);
        this.LegL = new ModelRenderer((ModelBase)this, 0, 16);
        this.LegL.field_78809_i = true;
        this.LegL.func_78793_a(2.0f, 12.0f, 0.0f);
        this.LegL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.HornR2 = new ModelRenderer((ModelBase)this, 42, 8);
        this.HornR2.func_78793_a(0.0f, -3.0f, 0.0f);
        this.HornR2.func_78790_a(-1.0f, -2.8f, -1.0f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.HornR2, -0.12217305f, 0.0f, -0.13665928f);
        this.HornL = new ModelRenderer((ModelBase)this, 42, 1);
        this.HornL.field_78809_i = true;
        this.HornL.func_78793_a(2.1f, -7.3f, -1.3f);
        this.HornL.func_78790_a(-1.5f, -3.0f, -1.5f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.HornL, -0.43633232f, 0.0f, 0.08726646f);
        this.WingR = new ModelRenderer((ModelBase)this, 0, 34);
        this.WingR.func_78793_a(-2.9f, 2.0f, 2.0f);
        this.WingR.func_78790_a(-19.0f, -9.0f, 0.0f, 19, 19, 0, 0.0f);
        this.setRotateAngle(this.WingR, 0.0f, 0.17453292f, 0.0f);
        this.HornL3 = new ModelRenderer((ModelBase)this, 51, 9);
        this.HornL3.field_78809_i = true;
        this.HornL3.func_78793_a(0.0f, -3.2f, 0.0f);
        this.HornL3.func_78790_a(-0.6f, -2.6f, -0.5f, 1, 3, 1, 0.0f);
        this.setRotateAngle(this.HornL3, -0.08726646f, 0.0f, 0.08726646f);
        this.Body.func_78792_a(this.WingL);
        this.HornL.func_78792_a(this.HornL2);
        this.tail1.func_78792_a(this.tail2);
        this.tail4.func_78792_a(this.tail5);
        this.HornR2.func_78792_a(this.HornR3);
        this.Head.func_78792_a(this.HornR);
        this.tail2.func_78792_a(this.tail3);
        this.tail3.func_78792_a(this.tail4);
        this.HornR.func_78792_a(this.HornR2);
        this.Head.func_78792_a(this.HornL);
        this.Body.func_78792_a(this.WingR);
        this.HornL2.func_78792_a(this.HornL3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.LegL.func_78785_a(f5);
        this.LegR.func_78785_a(f5);
        this.tail1.func_78785_a(f5);
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
        this.tail1.field_78795_f = 0.2f;
        this.tail1.field_78795_f += r4 / 2.0f;
        this.tail2.field_78795_f = 0.2f;
        this.tail2.field_78795_f += r4 / 2.0f;
        this.tail3.field_78795_f = 0.2f;
        this.tail3.field_78795_f += r4 / 2.0f;
        this.tail4.field_78795_f = 0.2f;
        this.tail4.field_78795_f += r4 / 2.0f;
        this.tail5.field_78796_g = 0.2f;
        this.tail5.field_78796_g += r4;
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

