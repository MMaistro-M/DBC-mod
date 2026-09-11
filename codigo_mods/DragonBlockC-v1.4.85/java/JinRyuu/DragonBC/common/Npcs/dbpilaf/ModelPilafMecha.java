/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Npcs.dbpilaf;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPilafMecha
extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmL1;
    public ModelRenderer Leg1L;
    public ModelRenderer Leg1R;
    public ModelRenderer BodyBack;
    public ModelRenderer BodyFront;
    public ModelRenderer BodyR;
    public ModelRenderer BodyL;
    public ModelRenderer BodyBottom;
    public ModelRenderer BodyTop;
    public ModelRenderer Head1;
    public ModelRenderer Back;
    public ModelRenderer PlateR;
    public ModelRenderer PlateL;
    public ModelRenderer Head2;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmR4;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;
    public ModelRenderer ArmL4;
    public ModelRenderer Leg2L;
    public ModelRenderer Leg3L;
    public ModelRenderer KneeL;
    public ModelRenderer FootL;
    public ModelRenderer Leg2R;
    public ModelRenderer Leg3R;
    public ModelRenderer KneeR;
    public ModelRenderer FootR;

    public ModelPilafMecha() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head2 = new ModelRenderer((ModelBase)this, 78, 26);
        this.Head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head2.func_78790_a(-4.0f, -10.5f, -10.0f, 8, 12, 11, 0.0f);
        this.FootR = new ModelRenderer((ModelBase)this, 1, 91);
        this.FootR.func_78793_a(0.0f, 9.0f, 0.0f);
        this.FootR.func_78790_a(-2.0f, -0.1f, -6.0f, 4, 2, 11, 0.0f);
        this.Back = new ModelRenderer((ModelBase)this, 82, 62);
        this.Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back.func_78790_a(-4.0f, -8.5f, 4.7f, 8, 11, 6, 0.0f);
        this.BodyL = new ModelRenderer((ModelBase)this, 44, 29);
        this.BodyL.field_78809_i = true;
        this.BodyL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyL.func_78790_a(7.5f, -6.5f, -6.0f, 2, 10, 11, 0.0f);
        this.KneeR = new ModelRenderer((ModelBase)this, 20, 79);
        this.KneeR.func_78793_a(0.1f, 1.6f, 0.0f);
        this.KneeR.func_78790_a(-1.0f, -2.7f, -3.0f, 2, 3, 2, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 43, 71);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-0.6f, -0.4f, -1.2f, 2, 7, 2, 0.0f);
        this.setRotateAngle(this.ArmL2, 0.0f, 0.0f, -0.6981317f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 43, 71);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-1.3f, -0.4f, -1.1f, 2, 7, 2, 0.0f);
        this.setRotateAngle(this.ArmR2, 0.0f, 0.0f, 0.6981317f);
        this.BodyR = new ModelRenderer((ModelBase)this, 44, 29);
        this.BodyR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyR.func_78790_a(-9.5f, -6.5f, -6.0f, 2, 10, 11, 0.0f);
        this.Leg2L = new ModelRenderer((ModelBase)this, 20, 68);
        this.Leg2L.field_78809_i = true;
        this.Leg2L.func_78793_a(0.0f, 1.6f, 0.0f);
        this.Leg2L.func_78790_a(-1.0f, -0.1f, -1.0f, 2, 6, 2, 0.0f);
        this.setRotateAngle(this.Leg2L, -0.31415927f, -0.06981317f, -0.6370452f);
        this.PlateR = new ModelRenderer((ModelBase)this, 44, 52);
        this.PlateR.func_78793_a(-10.0f, -5.0f, 0.0f);
        this.PlateR.func_78790_a(-1.0f, -0.4f, -3.5f, 1, 8, 7, 0.0f);
        this.setRotateAngle(this.PlateR, 0.0f, 0.0f, 1.7453293f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 44, 82);
        this.ArmR3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR3.func_78790_a(-2.5f, 5.8f, 1.5f, 4, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmR3, -0.4553564f, 0.0f, 0.0f);
        this.Leg1R = new ModelRenderer((ModelBase)this, 3, 68);
        this.Leg1R.func_78793_a(-5.5f, 7.4f, 1.5f);
        this.Leg1R.func_78790_a(-1.5f, -1.5f, -1.5f, 3, 3, 3, 0.0f);
        this.FootL = new ModelRenderer((ModelBase)this, 1, 91);
        this.FootL.field_78809_i = true;
        this.FootL.func_78793_a(0.0f, 9.0f, 0.0f);
        this.FootL.func_78790_a(-2.0f, -0.1f, -6.0f, 4, 2, 11, 0.0f);
        this.PlateL = new ModelRenderer((ModelBase)this, 44, 52);
        this.PlateL.field_78809_i = true;
        this.PlateL.func_78793_a(10.0f, -5.0f, 0.0f);
        this.PlateL.func_78790_a(0.0f, -0.4f, -3.5f, 1, 8, 7, 0.0f);
        this.setRotateAngle(this.PlateL, 0.0f, 0.0f, -1.7453293f);
        this.BodyTop = new ModelRenderer((ModelBase)this, 0, 34);
        this.BodyTop.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyTop.func_78790_a(-5.5f, -9.6f, -5.0f, 11, 2, 10, 0.0f);
        this.Leg3L = new ModelRenderer((ModelBase)this, 1, 76);
        this.Leg3L.field_78809_i = true;
        this.Leg3L.func_78793_a(0.0f, 5.3f, -0.1f);
        this.Leg3L.func_78790_a(-2.0f, -0.1f, -2.0f, 4, 9, 4, 0.0f);
        this.setRotateAngle(this.Leg3L, 0.31415927f, -0.14486232f, 0.6161012f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.ArmR1.func_78793_a(-9.0f, 1.0f, 0.0f);
        this.ArmR1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Body.func_78793_a(0.0f, 4.0f, 0.0f);
        this.Body.func_78790_a(-7.5f, -8.0f, -7.0f, 15, 13, 13, 0.0f);
        this.Head1 = new ModelRenderer((ModelBase)this, 72, 0);
        this.Head1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head1.func_78790_a(-5.0f, -10.0f, -9.6f, 10, 12, 12, 0.0f);
        this.ArmR4 = new ModelRenderer((ModelBase)this, 44, 93);
        this.ArmR4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR4.func_78790_a(-2.4f, 12.6f, 1.0f, 3, 4, 4, 0.0f);
        this.setRotateAngle(this.ArmR4, -0.4553564f, -0.1829105f, 0.0f);
        this.BodyFront = new ModelRenderer((ModelBase)this, 1, 51);
        this.BodyFront.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyFront.func_78790_a(-6.0f, -6.5f, -9.0f, 12, 10, 2, 0.0f);
        this.setRotateAngle(this.BodyFront, 0.0f, -0.014486233f, 0.0f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 44, 82);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL3.func_78790_a(-1.4f, 5.8f, 1.5f, 4, 7, 3, 0.0f);
        this.setRotateAngle(this.ArmL3, -0.4553564f, 0.0f, 0.0f);
        this.ArmL4 = new ModelRenderer((ModelBase)this, 44, 93);
        this.ArmL4.field_78809_i = true;
        this.ArmL4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL4.func_78790_a(-0.6f, 12.6f, 1.0f, 3, 4, 4, 0.0f);
        this.setRotateAngle(this.ArmL4, -0.4553564f, 0.16475908f, 0.0f);
        this.Leg2R = new ModelRenderer((ModelBase)this, 20, 68);
        this.Leg2R.func_78793_a(0.0f, 1.6f, 0.0f);
        this.Leg2R.func_78790_a(-1.0f, -0.1f, -1.0f, 2, 6, 2, 0.0f);
        this.setRotateAngle(this.Leg2R, -0.31415927f, 0.06981317f, 0.6370452f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.ArmL1.func_78793_a(9.0f, 1.0f, 0.0f);
        this.ArmL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.Leg3R = new ModelRenderer((ModelBase)this, 1, 76);
        this.Leg3R.func_78793_a(0.0f, 5.3f, -0.1f);
        this.Leg3R.func_78790_a(-2.0f, -0.1f, -2.0f, 4, 9, 4, 0.0f);
        this.setRotateAngle(this.Leg3R, 0.31415927f, 0.14486232f, -0.6161012f);
        this.BodyBottom = new ModelRenderer((ModelBase)this, 0, 34);
        this.BodyBottom.func_78793_a(0.0f, -2.5f, 0.0f);
        this.BodyBottom.func_78790_a(-5.5f, 7.5f, -5.0f, 11, 2, 10, 0.0f);
        this.Leg1L = new ModelRenderer((ModelBase)this, 3, 68);
        this.Leg1L.func_78793_a(5.5f, 7.4f, 1.5f);
        this.Leg1L.func_78790_a(-1.5f, -1.5f, -1.5f, 3, 3, 3, 0.0f);
        this.KneeL = new ModelRenderer((ModelBase)this, 20, 79);
        this.KneeL.field_78809_i = true;
        this.KneeL.func_78793_a(0.1f, 1.6f, 0.0f);
        this.KneeL.func_78790_a(-1.0f, -2.7f, -3.0f, 2, 3, 2, 0.0f);
        this.BodyBack = new ModelRenderer((ModelBase)this, 1, 51);
        this.BodyBack.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyBack.func_78790_a(-6.0f, -6.5f, 6.0f, 12, 10, 2, 0.0f);
        this.Head1.func_78792_a(this.Head2);
        this.Leg3R.func_78792_a(this.FootR);
        this.BodyBack.func_78792_a(this.Back);
        this.Body.func_78792_a(this.BodyL);
        this.Leg3R.func_78792_a(this.KneeR);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.Body.func_78792_a(this.BodyR);
        this.Leg1L.func_78792_a(this.Leg2L);
        this.BodyR.func_78792_a(this.PlateR);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Leg3L.func_78792_a(this.FootL);
        this.BodyL.func_78792_a(this.PlateL);
        this.Body.func_78792_a(this.BodyTop);
        this.Leg2L.func_78792_a(this.Leg3L);
        this.Body.func_78792_a(this.Head1);
        this.ArmR2.func_78792_a(this.ArmR4);
        this.Body.func_78792_a(this.BodyFront);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.ArmL2.func_78792_a(this.ArmL4);
        this.Leg1R.func_78792_a(this.Leg2R);
        this.Leg2R.func_78792_a(this.Leg3R);
        this.Body.func_78792_a(this.BodyBottom);
        this.Leg3L.func_78792_a(this.KneeL);
        this.Body.func_78792_a(this.BodyBack);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.Leg1L.func_78785_a(f5);
        this.Leg1R.func_78785_a(f5);
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
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
        r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
        this.Leg1R.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.Leg1L.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmR1.field_78795_f = -0.0f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.ArmL1.field_78795_f = -0.0f - MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.Leg1R.field_78796_g = 0.0f;
        this.Leg1L.field_78796_g = 0.0f;
        this.ArmR1.field_78796_g = 0.0f;
        this.ArmL1.field_78796_g = 0.0f;
    }
}

