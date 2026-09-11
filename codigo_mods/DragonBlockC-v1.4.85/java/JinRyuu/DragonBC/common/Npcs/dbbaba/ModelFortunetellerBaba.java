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

public class ModelFortunetellerBaba
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR;
    public ModelRenderer Base1;
    public ModelRenderer Hat1;
    public ModelRenderer Hair;
    public ModelRenderer Hat2;
    public ModelRenderer Hat3;
    public ModelRenderer Hat4;
    public ModelRenderer Body2;
    public ModelRenderer BodyR;
    public ModelRenderer BodyL;
    public ModelRenderer Body3;
    public ModelRenderer ArmL1;
    public ModelRenderer ArmL2;
    public ModelRenderer ArmL3;
    public ModelRenderer ArmR1;
    public ModelRenderer ArmR2;
    public ModelRenderer ArmR3;
    public ModelRenderer Base1Front;
    public ModelRenderer Base1Back;
    public ModelRenderer Base1Side_Ball;
    public ModelRenderer Base1Side_Jobb;
    public ModelRenderer Base1Top;
    public ModelRenderer Base1Bottom;
    public ModelRenderer Base1Front2;
    public ModelRenderer Base1Back2;
    public ModelRenderer Base1Side_Ball2;
    public ModelRenderer Base1Side_Jobb2;
    public ModelRenderer Base1Bottom2;

    public ModelFortunetellerBaba() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Hat3 = new ModelRenderer((ModelBase)this, 26, 57);
        this.Hat3.func_78793_a(0.0f, -2.5f, 0.4f);
        this.Hat3.func_78790_a(-1.5f, -1.5f, -1.6f, 3, 3, 3, 0.0f);
        this.setRotateAngle(this.Hat3, -0.13665928f, 0.0f, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 46, 30);
        this.ArmL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmL1.func_78790_a(-0.5f, 0.0f, -1.6f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.ArmL1, -0.59184116f, 0.091106184f, 0.0f);
        this.Base1Back = new ModelRenderer((ModelBase)this, 111, 17);
        this.Base1Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Back.func_78790_a(3.0f, -3.0f, -3.0f, 2, 6, 6, 0.0f);
        this.Hat1 = new ModelRenderer((ModelBase)this, 49, 50);
        this.Hat1.func_78793_a(0.0f, -4.0f, 0.0f);
        this.Hat1.func_78790_a(-9.0f, 0.0f, -7.0f, 18, 0, 14, 0.0f);
        this.Base1Side_Jobb = new ModelRenderer((ModelBase)this, 81, 20);
        this.Base1Side_Jobb.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Side_Jobb.func_78790_a(-3.0f, -3.0f, 3.0f, 6, 6, 2, 0.0f);
        this.Base1Top = new ModelRenderer((ModelBase)this, 103, 30);
        this.Base1Top.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Top.func_78790_a(-3.0f, -5.0f, -3.0f, 6, 2, 6, 0.0f);
        this.Base1Front = new ModelRenderer((ModelBase)this, 111, 17);
        this.Base1Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Front.func_78790_a(-5.0f, -3.0f, -3.0f, 2, 6, 6, 0.0f);
        this.Base1 = new ModelRenderer((ModelBase)this, 96, 0);
        this.Base1.func_78793_a(0.0f, 9.4f, 0.0f);
        this.Base1.func_78790_a(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        this.BodyR = new ModelRenderer((ModelBase)this, 0, 41);
        this.BodyR.func_78793_a(-2.6f, 4.7f, -0.4f);
        this.BodyR.func_78790_a(-2.5f, -1.5f, -1.5f, 5, 3, 4, 0.0f);
        this.setRotateAngle(this.BodyR, 0.27314404f, 0.0f, -0.18325958f);
        this.Body3 = new ModelRenderer((ModelBase)this, 31, 19);
        this.Body3.func_78793_a(0.0f, 2.7f, 3.2f);
        this.Body3.func_78790_a(-3.5f, -1.9f, -1.6f, 7, 3, 4, 0.0f);
        this.setRotateAngle(this.Body3, -0.95609134f, 0.0f, 0.0f);
        this.Base1Side_Ball2 = new ModelRenderer((ModelBase)this, 81, 29);
        this.Base1Side_Ball2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Side_Ball2.func_78790_a(-2.0f, -2.0f, -5.5f, 4, 4, 2, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 0, 0);
        this.ArmR.func_78793_a(-3.5f, -0.5f, 0.0f);
        this.ArmR.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.Base1Side_Jobb2 = new ModelRenderer((ModelBase)this, 81, 29);
        this.Base1Side_Jobb2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Side_Jobb2.func_78790_a(-2.0f, -2.0f, 3.5f, 4, 4, 2, 0.0f);
        this.Hair = new ModelRenderer((ModelBase)this, 33, 1);
        this.Hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hair.func_78790_a(-3.5f, -3.99f, -3.5f, 7, 4, 7, 0.0f);
        this.Base1Bottom = new ModelRenderer((ModelBase)this, 103, 30);
        this.Base1Bottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Bottom.func_78790_a(-3.0f, 3.0f, -3.0f, 6, 2, 6, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 1, 18);
        this.Body.func_78793_a(0.0f, -1.6f, 0.0f);
        this.Body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 3, 5, 0.0f);
        this.Hat4 = new ModelRenderer((ModelBase)this, 16, 58);
        this.Hat4.func_78793_a(0.0f, -2.5f, 0.0f);
        this.Hat4.func_78790_a(-1.0f, -1.5f, -1.0f, 2, 3, 2, 0.0f);
        this.setRotateAngle(this.Hat4, -0.13665928f, 0.0f, 0.0f);
        this.ArmR3 = new ModelRenderer((ModelBase)this, 47, 47);
        this.ArmR3.func_78793_a(0.0f, 2.9f, 0.0f);
        this.ArmR3.func_78790_a(-0.5f, 0.0f, -1.0f, 1, 1, 2, 0.0f);
        this.Base1Back2 = new ModelRenderer((ModelBase)this, 98, 19);
        this.Base1Back2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Back2.func_78790_a(3.5f, -2.0f, -2.0f, 2, 4, 4, 0.0f);
        this.Hat2 = new ModelRenderer((ModelBase)this, 40, 55);
        this.Hat2.func_78793_a(0.0f, -2.8f, 0.0f);
        this.Hat2.func_78790_a(-2.2f, -1.5f, -2.1f, 4, 3, 5, 0.0f);
        this.setRotateAngle(this.Hat2, -0.13665928f, 0.0f, 0.0f);
        this.ArmL2 = new ModelRenderer((ModelBase)this, 46, 39);
        this.ArmL2.func_78793_a(0.3f, 3.1f, -0.6f);
        this.ArmL2.func_78790_a(-1.0f, -0.1f, -1.5f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.ArmL2, -0.9599311f, 1.5707964f, 0.34906584f);
        this.ArmR2 = new ModelRenderer((ModelBase)this, 46, 39);
        this.ArmR2.func_78793_a(-0.4f, 3.1f, -0.6f);
        this.ArmR2.func_78790_a(-1.0f, -0.1f, -1.5f, 2, 3, 3, 0.0f);
        this.setRotateAngle(this.ArmR2, -0.9599311f, -1.5707964f, -0.34906584f);
        this.Base1Front2 = new ModelRenderer((ModelBase)this, 98, 19);
        this.Base1Front2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Front2.func_78790_a(-5.5f, -2.0f, -2.0f, 2, 4, 4, 0.0f);
        this.setRotateAngle(this.Base1Front2, 0.0f, -0.008901179f, 0.0f);
        this.Base1Bottom2 = new ModelRenderer((ModelBase)this, 107, 39);
        this.Base1Bottom2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Bottom2.func_78790_a(-2.0f, 3.5f, -2.0f, 4, 2, 4, 0.0f);
        this.Base1Side_Ball = new ModelRenderer((ModelBase)this, 81, 20);
        this.Base1Side_Ball.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Base1Side_Ball.func_78790_a(-3.0f, -3.0f, -5.0f, 6, 6, 2, 0.0f);
        this.ArmL3 = new ModelRenderer((ModelBase)this, 47, 47);
        this.ArmL3.func_78793_a(0.0f, 2.9f, 0.0f);
        this.ArmL3.func_78790_a(-0.5f, 0.0f, -1.0f, 1, 1, 2, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 1, 29);
        this.Body2.func_78793_a(0.0f, 2.2f, 0.0f);
        this.Body2.func_78790_a(-4.5f, 0.0f, -2.0f, 9, 4, 6, 0.0f);
        this.setRotateAngle(this.Body2, -0.22759093f, 0.0f, 0.0f);
        this.BodyL = new ModelRenderer((ModelBase)this, 20, 41);
        this.BodyL.func_78793_a(2.6f, 4.7f, -0.4f);
        this.BodyL.func_78790_a(-2.5f, -1.5f, -1.5f, 5, 3, 4, 0.0f);
        this.setRotateAngle(this.BodyL, 0.27314404f, 0.0f, 0.18325958f);
        this.ArmL = new ModelRenderer((ModelBase)this, 0, 0);
        this.ArmL.func_78793_a(3.5f, -0.5f, 0.0f);
        this.ArmL.func_78790_a(-0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, -0.7f, 0.0f);
        this.Head.func_78790_a(-4.0f, -7.2f, -4.0f, 8, 8, 8, -0.8f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 46, 30);
        this.ArmR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ArmR1.func_78790_a(-1.5f, 0.0f, -1.6f, 2, 4, 3, 0.0f);
        this.setRotateAngle(this.ArmR1, -0.59184116f, -0.091106184f, 0.0f);
        this.Hat2.func_78792_a(this.Hat3);
        this.ArmL.func_78792_a(this.ArmL1);
        this.Base1.func_78792_a(this.Base1Back);
        this.Head.func_78792_a(this.Hat1);
        this.Base1.func_78792_a(this.Base1Side_Jobb);
        this.Base1.func_78792_a(this.Base1Top);
        this.Base1.func_78792_a(this.Base1Front);
        this.Body2.func_78792_a(this.BodyR);
        this.Body2.func_78792_a(this.Body3);
        this.Base1Side_Ball.func_78792_a(this.Base1Side_Ball2);
        this.Base1Side_Jobb.func_78792_a(this.Base1Side_Jobb2);
        this.Head.func_78792_a(this.Hair);
        this.Base1.func_78792_a(this.Base1Bottom);
        this.Hat3.func_78792_a(this.Hat4);
        this.ArmR2.func_78792_a(this.ArmR3);
        this.Base1Back.func_78792_a(this.Base1Back2);
        this.Hat1.func_78792_a(this.Hat2);
        this.ArmL1.func_78792_a(this.ArmL2);
        this.ArmR1.func_78792_a(this.ArmR2);
        this.Base1Front.func_78792_a(this.Base1Front2);
        this.Base1Bottom.func_78792_a(this.Base1Bottom2);
        this.Base1.func_78792_a(this.Base1Side_Ball);
        this.ArmL2.func_78792_a(this.ArmL3);
        this.Body.func_78792_a(this.Body2);
        this.Body2.func_78792_a(this.BodyL);
        this.ArmR.func_78792_a(this.ArmR1);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.ArmR.func_78785_a(f5);
        this.ArmL.func_78785_a(f5);
        this.Base1.func_78785_a(f5);
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
    }
}

