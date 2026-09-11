/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs.dbpilaf;

import JinRyuu.JRMCore.client.JGRenderHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelShuMecha
extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer ArmL1;
    public ModelRenderer ArmR1;
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
    public ModelRenderer Tail1;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer Bottom;
    public ModelRenderer PlateR;
    public ModelRenderer PlateL;
    public ModelRenderer Bottom_1;
    public ModelRenderer Head2;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;
    public ModelRenderer ArmGunR1;
    public ModelRenderer ArmGunR2;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer ArmGunR1_1;
    public ModelRenderer ArmGunR2_1;
    public ModelRenderer Leg2L;
    public ModelRenderer Leg3L;
    public ModelRenderer KneeL;
    public ModelRenderer FootL;
    public ModelRenderer Leg2R;
    public ModelRenderer Leg3R;
    public ModelRenderer KneeR;
    public ModelRenderer FootR;

    public ModelShuMecha() {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.Head1 = new ModelRenderer((ModelBase)this, 72, 0);
        this.Head1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head1.func_78790_a(-6.0f, -4.6f, -10.0f, 12, 15, 14, 0.0f);
        this.Leg3L = new ModelRenderer((ModelBase)this, 1, 87);
        this.Leg3L.field_78809_i = true;
        this.Leg3L.func_78793_a(0.0f, 10.7f, -0.1f);
        this.Leg3L.func_78790_a(-3.0f, -0.1f, -2.5f, 6, 18, 5, 0.0f);
        this.setRotateAngle(this.Leg3L, 0.31415927f, 0.0f, 0.0f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 46, 101);
        this.ArmR3.func_78793_a(0.0f, 6.5f, 0.0f);
        this.ArmR3.func_78790_a(-2.5f, 0.0f, -2.0f, 4, 7, 5, 0.0f);
        this.setRotateAngle(this.ArmR3, -1.2217305f, 0.0f, 0.0f);
        this.KneeR = new ModelRenderer((ModelBase)this, 25, 91);
        this.KneeR.func_78793_a(0.0f, 1.6f, 0.0f);
        this.KneeR.func_78790_a(-1.5f, -2.7f, -3.0f, 3, 4, 1, 0.0f);
        this.Bottom_1 = new ModelRenderer((ModelBase)this, 115, 47);
        this.Bottom_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Bottom_1.func_78790_a(-3.0f, 9.3f, -8.1f, 6, 6, 14, 0.0f);
        this.Bottom = new ModelRenderer((ModelBase)this, 152, 29);
        this.Bottom.func_78793_a(0.0f, -9.3f, 0.0f);
        this.Bottom.func_78790_a(-2.0f, -6.2f, -2.0f, 4, 7, 4, 0.0f);
        this.setRotateAngle(this.Bottom, 0.3642502f, 0.0f, 0.0f);
        this.BodyTop = new ModelRenderer((ModelBase)this, 0, 34);
        this.BodyTop.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyTop.func_78790_a(-7.0f, -9.6f, -6.0f, 14, 2, 13, 0.0f);
        this.ArmGunR1_1 = new ModelRenderer((ModelBase)this, 46, 114);
        this.ArmGunR1_1.func_78793_a(-0.5f, 7.0f, -0.7f);
        this.ArmGunR1_1.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 3, 2, 0.0f);
        this.BodyBack = new ModelRenderer((ModelBase)this, 0, 54);
        this.BodyBack.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyBack.func_78790_a(-7.0f, -6.5f, 8.3f, 14, 13, 2, 0.0f);
        this.BodyR = new ModelRenderer((ModelBase)this, 44, 42);
        this.BodyR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyR.func_78790_a(-10.5f, -6.5f, -6.5f, 2, 13, 14, 0.0f);
        this.KneeL = new ModelRenderer((ModelBase)this, 25, 91);
        this.KneeL.field_78809_i = true;
        this.KneeL.func_78793_a(0.0f, 1.6f, 0.0f);
        this.KneeL.func_78790_a(-1.5f, -2.7f, -3.0f, 3, 4, 1, 0.0f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 46, 101);
        this.ArmL3.field_78809_i = true;
        this.ArmL3.func_78793_a(0.0f, 6.5f, 0.0f);
        this.ArmL3.func_78790_a(-1.4f, 0.0f, -2.0f, 4, 7, 5, 0.0f);
        this.setRotateAngle(this.ArmL3, -1.2217305f, 0.0f, 0.0f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 46, 91);
        this.ArmR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR2.func_78790_a(-1.3f, -0.4f, -1.1f, 2, 7, 2, 0.0f);
        this.setRotateAngle(this.ArmR2, 0.0f, 0.0f, 1.0471976f);
        this.ArmGunR1 = new ModelRenderer((ModelBase)this, 46, 114);
        this.ArmGunR1.func_78793_a(0.5f, 7.0f, -0.7f);
        this.ArmGunR1.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 3, 2, 0.0f);
        this.FootL = new ModelRenderer((ModelBase)this, 1, 113);
        this.FootL.field_78809_i = true;
        this.FootL.func_78793_a(0.0f, 17.0f, 0.0f);
        this.FootL.func_78790_a(-2.0f, 0.0f, -6.0f, 4, 2, 11, 0.0f);
        this.Tail3 = new ModelRenderer((ModelBase)this, 141, 29);
        this.Tail3.func_78793_a(0.0f, -8.6f, 0.0f);
        this.Tail3.func_78790_a(-1.0f, -9.0f, -1.0f, 2, 9, 2, 0.0f);
        this.setRotateAngle(this.Tail3, 0.5235988f, 0.0f, 0.0f);
        this.Leg2R = new ModelRenderer((ModelBase)this, 26, 73);
        this.Leg2R.func_78793_a(0.0f, 4.6f, 0.0f);
        this.Leg2R.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 11, 3, 0.0f);
        this.setRotateAngle(this.Leg2R, -0.31415927f, 0.0f, 0.0f);
        this.Leg3R = new ModelRenderer((ModelBase)this, 1, 87);
        this.Leg3R.func_78793_a(0.0f, 10.7f, -0.1f);
        this.Leg3R.func_78790_a(-3.0f, -0.1f, -2.5f, 6, 18, 5, 0.0f);
        this.setRotateAngle(this.Leg3R, 0.31415927f, 0.0f, 0.0f);
        this.Leg2L = new ModelRenderer((ModelBase)this, 26, 73);
        this.Leg2L.field_78809_i = true;
        this.Leg2L.func_78793_a(0.0f, 4.6f, 0.0f);
        this.Leg2L.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 11, 3, 0.0f);
        this.setRotateAngle(this.Leg2L, -0.31415927f, 0.0f, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this, 78, 30);
        this.Head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head2.func_78790_a(-5.0f, -3.0f, -10.7f, 10, 14, 13, 0.0f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.ArmR1.func_78793_a(-10.0f, -18.0f, 0.0f);
        this.ArmR1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Body.func_78793_a(0.0f, -17.0f, 0.0f);
        this.Body.func_78790_a(-9.0f, -8.0f, -7.4f, 18, 16, 16, 0.0f);
        this.Tail2 = new ModelRenderer((ModelBase)this, 127, 28);
        this.Tail2.func_78793_a(0.0f, -8.7f, 0.0f);
        this.Tail2.func_78790_a(-1.5f, -9.0f, -1.5f, 3, 9, 3, 0.0f);
        this.setRotateAngle(this.Tail2, 0.6981317f, 0.0f, 0.0f);
        this.PlateR = new ModelRenderer((ModelBase)this, 44, 72);
        this.PlateR.func_78793_a(-10.5f, -5.0f, 0.5f);
        this.PlateR.func_78790_a(-1.0f, -0.4f, -3.5f, 1, 10, 7, 0.0f);
        this.setRotateAngle(this.PlateR, 0.0f, 0.0f, 1.7453293f);
        this.BodyBottom = new ModelRenderer((ModelBase)this, 0, 34);
        this.BodyBottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyBottom.func_78790_a(-7.0f, 7.8f, -6.0f, 14, 2, 13, 0.0f);
        this.ArmGunR2_1 = new ModelRenderer((ModelBase)this, 46, 114);
        this.ArmGunR2_1.func_78793_a(-0.5f, 7.0f, 1.7f);
        this.ArmGunR2_1.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 3, 2, 0.0f);
        this.Back = new ModelRenderer((ModelBase)this, 82, 62);
        this.Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Back.func_78790_a(-4.5f, -5.4f, 6.5f, 9, 14, 6, 0.0f);
        this.Leg1L = new ModelRenderer((ModelBase)this, 1, 71);
        this.Leg1L.func_78793_a(10.7f, -9.5f, 1.5f);
        this.Leg1L.func_78790_a(-3.0f, -2.0f, -3.0f, 5, 7, 6, 0.0f);
        this.Tail1 = new ModelRenderer((ModelBase)this, 127, 28);
        this.Tail1.func_78793_a(0.0f, -4.6f, 11.0f);
        this.Tail1.func_78790_a(-1.5f, -9.0f, -1.5f, 3, 9, 3, 0.0f);
        this.setRotateAngle(this.Tail1, -0.3642502f, 0.0f, 0.0f);
        this.FootR = new ModelRenderer((ModelBase)this, 1, 113);
        this.FootR.func_78793_a(0.0f, 17.0f, 0.0f);
        this.FootR.func_78790_a(-2.0f, 0.0f, -6.0f, 4, 2, 11, 0.0f);
        this.BodyFront = new ModelRenderer((ModelBase)this, 0, 54);
        this.BodyFront.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyFront.func_78790_a(-7.0f, -6.5f, -8.9f, 14, 13, 2, 0.0f);
        this.Leg1R = new ModelRenderer((ModelBase)this, 1, 71);
        this.Leg1R.func_78793_a(-10.7f, -9.5f, 1.5f);
        this.Leg1R.func_78790_a(-2.0f, -2.0f, -3.0f, 5, 7, 6, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 46, 91);
        this.ArmL2.field_78809_i = true;
        this.ArmL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL2.func_78790_a(-0.6f, -0.4f, -1.2f, 2, 7, 2, 0.0f);
        this.setRotateAngle(this.ArmL2, 0.0f, 0.0f, -1.0471976f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.ArmL1.func_78793_a(10.0f, -18.0f, 0.0f);
        this.ArmL1.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.PlateL = new ModelRenderer((ModelBase)this, 44, 72);
        this.PlateL.field_78809_i = true;
        this.PlateL.func_78793_a(10.5f, -5.0f, 0.5f);
        this.PlateL.func_78790_a(0.0f, -0.4f, -3.5f, 1, 10, 7, 0.0f);
        this.setRotateAngle(this.PlateL, 0.0f, 0.0f, -1.7453293f);
        this.ArmGunR2 = new ModelRenderer((ModelBase)this, 46, 114);
        this.ArmGunR2.func_78793_a(0.5f, 7.0f, 1.7f);
        this.ArmGunR2.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 3, 2, 0.0f);
        this.Tail4 = new ModelRenderer((ModelBase)this, 141, 29);
        this.Tail4.func_78793_a(0.0f, -8.6f, 0.0f);
        this.Tail4.func_78790_a(-1.0f, -9.0f, -1.0f, 2, 9, 2, 0.0f);
        this.setRotateAngle(this.Tail4, 0.5235988f, 0.0f, 0.0f);
        this.BodyL = new ModelRenderer((ModelBase)this, 44, 42);
        this.BodyL.field_78809_i = true;
        this.BodyL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.BodyL.func_78790_a(8.5f, -6.5f, -6.5f, 2, 13, 14, 0.0f);
        this.Body.func_78792_a(this.Head1);
        this.Leg2L.func_78792_a(this.Leg3L);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Leg3R.func_78792_a(this.KneeR);
        this.BodyBottom.func_78792_a(this.Bottom_1);
        this.Tail4.func_78792_a(this.Bottom);
        this.Body.func_78792_a(this.BodyTop);
        this.ArmR3.func_78792_a(this.ArmGunR1_1);
        this.Body.func_78792_a(this.BodyBack);
        this.Body.func_78792_a(this.BodyR);
        this.Leg3L.func_78792_a(this.KneeL);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.ArmL3.func_78792_a(this.ArmGunR1);
        this.Leg3L.func_78792_a(this.FootL);
        this.Tail2.func_78792_a(this.Tail3);
        this.Leg1R.func_78792_a(this.Leg2R);
        this.Leg2R.func_78792_a(this.Leg3R);
        this.Leg1L.func_78792_a(this.Leg2L);
        this.Head1.func_78792_a(this.Head2);
        this.Tail1.func_78792_a(this.Tail2);
        this.BodyR.func_78792_a(this.PlateR);
        this.Body.func_78792_a(this.BodyBottom);
        this.ArmR3.func_78792_a(this.ArmGunR2_1);
        this.BodyBack.func_78792_a(this.Back);
        this.Back.func_78792_a(this.Tail1);
        this.Leg3R.func_78792_a(this.FootR);
        this.Body.func_78792_a(this.BodyFront);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.BodyL.func_78792_a(this.PlateL);
        this.ArmL3.func_78792_a(this.ArmGunR2);
        this.Tail3.func_78792_a(this.Tail4);
        this.Body.func_78792_a(this.BodyL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float F = 1.4f;
        JGRenderHelper.modelScalePositionHelper(1.4f);
        this.Body.func_78785_a(f5);
        this.ArmR1.func_78785_a(f5);
        this.ArmL1.func_78785_a(f5);
        this.Leg1L.func_78785_a(f5);
        this.Leg1R.func_78785_a(f5);
        GL11.glPopMatrix();
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

