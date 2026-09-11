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
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelDino02
extends ModelBase {
    public ModelRenderer Body;
    public ModelRenderer Neck;
    public ModelRenderer LeftLeg;
    public ModelRenderer RightLeg;
    public ModelRenderer Tail;
    public ModelRenderer Neck2;
    public ModelRenderer Neck3;
    public ModelRenderer Neck4;
    public ModelRenderer Neck5;
    public ModelRenderer Head;
    public ModelRenderer L;
    public ModelRenderer R;
    public ModelRenderer T;
    public ModelRenderer Mouth;
    public ModelRenderer T2;
    public ModelRenderer LeftLeg2;
    public ModelRenderer LeftLeg3;
    public ModelRenderer lf1;
    public ModelRenderer lf2;
    public ModelRenderer lf3;
    public ModelRenderer lf4;
    public ModelRenderer RightLeg2;
    public ModelRenderer RightLeg3;
    public ModelRenderer rf1;
    public ModelRenderer rf2;
    public ModelRenderer rf3;
    public ModelRenderer lf4_1;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer Tail5;
    public ModelRenderer Tail6;

    public ModelDino02() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.rf3 = new ModelRenderer((ModelBase)this, 67, 76);
        this.rf3.func_78793_a(-1.1f, 10.0f, 0.8f);
        this.rf3.func_78790_a(-0.5f, 0.0f, -0.8f, 1, 5, 1, 0.0f);
        this.setRotation(this.rf3, -1.0471976f, 0.4098033f, 0.0f);
        this.lf3 = new ModelRenderer((ModelBase)this, 67, 76);
        this.lf3.field_78809_i = true;
        this.lf3.func_78793_a(-1.1f, 10.0f, 0.8f);
        this.lf3.func_78790_a(-0.5f, 0.0f, -0.8f, 1, 5, 1, 0.0f);
        this.setRotation(this.lf3, -1.0471976f, 0.4098033f, 0.0f);
        this.lf4 = new ModelRenderer((ModelBase)this, 67, 76);
        this.lf4.field_78809_i = true;
        this.lf4.func_78793_a(0.0f, 9.9f, 3.4f);
        this.lf4.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 4, 1, 0.0f);
        this.setRotation(this.lf4, 1.3203416f, 0.0f, 0.0f);
        this.R = new ModelRenderer((ModelBase)this, 35, 0);
        this.R.func_78793_a(0.0f, 0.0f, -5.7f);
        this.R.func_78790_a(-4.0f, -2.5f, 0.0f, 4, 5, 6, 0.0f);
        this.setRotation(this.R, 0.0f, -0.59184116f, 0.0f);
        this.RightLeg2 = new ModelRenderer((ModelBase)this, 73, 54);
        this.RightLeg2.func_78793_a(-3.0f, 10.1f, -2.8f);
        this.RightLeg2.func_78790_a(-3.0f, 0.0f, -4.4f, 6, 11, 5, 0.0f);
        this.setRotation(this.RightLeg2, 1.5934856f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer((ModelBase)this, 0, 89);
        this.Tail2.func_78793_a(0.5f, 0.5f, 10.9f);
        this.Tail2.func_78790_a(-5.0f, -5.0f, 0.0f, 9, 9, 13, 0.0f);
        this.setRotation(this.Tail2, -0.27314404f, 0.0f, 0.0f);
        this.LeftLeg = new ModelRenderer((ModelBase)this, 73, 32);
        this.LeftLeg.field_78809_i = true;
        this.LeftLeg.func_78793_a(7.9f, 7.8f, 8.5f);
        this.LeftLeg.func_78790_a(0.4f, -0.8f, -3.5f, 7, 11, 7, 0.0f);
        this.setRotation(this.LeftLeg, -0.5009095f, 0.0f, 0.0f);
        this.lf2 = new ModelRenderer((ModelBase)this, 67, 76);
        this.lf2.field_78809_i = true;
        this.lf2.func_78793_a(0.0f, 10.0f, 0.8f);
        this.lf2.func_78790_a(-0.5f, 0.0f, -0.8f, 1, 5, 1, 0.0f);
        this.setRotation(this.lf2, -1.0471976f, 0.0f, 0.091106184f);
        this.lf4_1 = new ModelRenderer((ModelBase)this, 67, 76);
        this.lf4_1.func_78793_a(0.0f, 9.9f, 3.4f);
        this.lf4_1.func_78790_a(-1.0f, 0.0f, -0.5f, 2, 4, 1, 0.0f);
        this.setRotation(this.lf4_1, 1.3203416f, 0.0f, 0.0f);
        this.Tail6 = new ModelRenderer((ModelBase)this, 81, 101);
        this.Tail6.func_78793_a(-0.5f, 0.0f, 9.7f);
        this.Tail6.func_78790_a(-3.0f, -3.0f, 0.0f, 6, 6, 13, 0.0f);
        this.RightLeg = new ModelRenderer((ModelBase)this, 73, 32);
        this.RightLeg.func_78793_a(-7.9f, 7.8f, 8.5f);
        this.RightLeg.func_78790_a(-6.5f, -0.8f, -3.5f, 7, 11, 7, 0.0f);
        this.setRotation(this.RightLeg, -0.5009095f, 0.0f, 0.0f);
        this.T2 = new ModelRenderer((ModelBase)this, 59, 0);
        this.T2.func_78793_a(0.0f, -5.0f, 0.1f);
        this.T2.func_78790_a(-1.0f, -6.0f, -2.5f, 2, 6, 3, 0.0f);
        this.setRotation(this.T2, -0.31869712f, 0.0f, 0.0f);
        this.Tail3 = new ModelRenderer((ModelBase)this, 34, 101);
        this.Tail3.func_78793_a(-0.5f, -1.0f, 9.7f);
        this.Tail3.func_78790_a(-4.0f, -4.0f, 0.0f, 8, 8, 13, 0.0f);
        this.setRotation(this.Tail3, -0.27314404f, 0.0f, 0.0f);
        this.RightLeg3 = new ModelRenderer((ModelBase)this, 73, 71);
        this.RightLeg3.func_78793_a(0.0f, 9.8f, -0.5f);
        this.RightLeg3.func_78790_a(-2.0f, -0.7f, 0.0f, 4, 11, 4, 0.0f);
        this.setRotation(this.RightLeg3, -1.2747885f, 0.0f, 0.0f);
        this.Tail5 = new ModelRenderer((ModelBase)this, 34, 101);
        this.Tail5.func_78793_a(-0.5f, 0.0f, 9.7f);
        this.Tail5.func_78790_a(-4.0f, -4.0f, 0.0f, 8, 8, 13, 0.0f);
        this.setRotation(this.Tail5, 0.27314404f, 0.0f, 0.0f);
        this.rf2 = new ModelRenderer((ModelBase)this, 67, 76);
        this.rf2.func_78793_a(0.0f, 10.0f, 0.8f);
        this.rf2.func_78790_a(-0.5f, 0.0f, -0.8f, 1, 5, 1, 0.0f);
        this.setRotation(this.rf2, -1.0471976f, 0.0f, 0.091106184f);
        this.Head = new ModelRenderer((ModelBase)this, 0, 0);
        this.Head.func_78793_a(0.0f, 0.0f, -7.0f);
        this.Head.func_78790_a(-4.0f, -3.5f, -8.0f, 8, 7, 8, 0.0f);
        this.setRotation(this.Head, 0.31869712f, 0.0f, 0.0f);
        this.T = new ModelRenderer((ModelBase)this, 59, 11);
        this.T.func_78793_a(0.0f, -1.9f, -5.4f);
        this.T.func_78790_a(-1.5f, -6.0f, -2.5f, 3, 6, 5, 0.0f);
        this.setRotation(this.T, -0.5009095f, 0.0f, 0.0f);
        this.Neck4 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Neck4.func_78793_a(0.0f, 0.0f, -7.0f);
        this.Neck4.func_78790_a(-4.0f, -3.0f, -8.0f, 8, 6, 8, 0.0f);
        this.Tail4 = new ModelRenderer((ModelBase)this, 34, 101);
        this.Tail4.func_78793_a(-0.5f, 0.0f, 9.7f);
        this.Tail4.func_78790_a(-4.0f, -4.0f, 0.0f, 8, 8, 13, 0.0f);
        this.rf1 = new ModelRenderer((ModelBase)this, 67, 76);
        this.rf1.func_78793_a(1.1f, 10.0f, 0.8f);
        this.rf1.func_78790_a(-0.5f, 0.0f, -0.8f, 1, 5, 1, 0.0f);
        this.setRotation(this.rf1, -1.0471976f, -0.4098033f, 0.0f);
        this.lf1 = new ModelRenderer((ModelBase)this, 67, 76);
        this.lf1.field_78809_i = true;
        this.lf1.func_78793_a(1.1f, 10.0f, 0.8f);
        this.lf1.func_78790_a(-0.5f, 0.0f, -0.8f, 1, 5, 1, 0.0f);
        this.setRotation(this.lf1, -1.0471976f, -0.4098033f, 0.0f);
        this.Neck5 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Neck5.func_78793_a(0.0f, -0.5f, -6.2f);
        this.Neck5.func_78790_a(-4.0f, -3.0f, -8.0f, 8, 6, 8, 0.0f);
        this.setRotation(this.Neck5, 0.67945665f, 0.0f, 0.0f);
        this.Neck2 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Neck2.func_78793_a(0.0f, 0.0f, -7.0f);
        this.Neck2.func_78790_a(-4.0f, -3.0f, -8.0f, 8, 6, 8, 0.0f);
        this.setRotation(this.Neck2, -0.22759093f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 0, 30);
        this.Body.func_78793_a(0.0f, -7.0f, 0.0f);
        this.Body.func_78790_a(-8.0f, -2.0f, -1.8f, 16, 13, 19, 0.0f);
        this.Tail = new ModelRenderer((ModelBase)this, 0, 65);
        this.Tail.func_78793_a(0.0f, 4.1f, 13.6f);
        this.Tail.func_78790_a(-5.0f, -5.0f, 0.0f, 10, 10, 13, 0.0f);
        this.LeftLeg3 = new ModelRenderer((ModelBase)this, 73, 71);
        this.LeftLeg3.field_78809_i = true;
        this.LeftLeg3.func_78793_a(0.0f, 9.8f, -0.5f);
        this.LeftLeg3.func_78790_a(-2.0f, -0.7f, 0.0f, 4, 11, 4, 0.0f);
        this.setRotation(this.LeftLeg3, -1.2747885f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 80, 0);
        this.Neck.func_78793_a(0.0f, 1.6f, 1.8f);
        this.Neck.func_78790_a(-4.0f, -3.0f, -8.0f, 8, 6, 8, 0.0f);
        this.setRotation(this.Neck, 0.18203785f, 0.0f, 0.0f);
        this.Mouth = new ModelRenderer((ModelBase)this, 0, 16);
        this.Mouth.func_78793_a(0.0f, 1.2f, -7.5f);
        this.Mouth.func_78790_a(-2.5f, -2.0f, -7.0f, 5, 4, 7, 0.0f);
        this.Neck3 = new ModelRenderer((ModelBase)this, 80, 0);
        this.Neck3.func_78793_a(0.0f, 0.5f, -6.4f);
        this.Neck3.func_78790_a(-4.0f, -3.0f, -8.0f, 8, 6, 8, 0.0f);
        this.setRotation(this.Neck3, -0.7285004f, 0.0f, 0.0f);
        this.LeftLeg2 = new ModelRenderer((ModelBase)this, 73, 54);
        this.LeftLeg2.field_78809_i = true;
        this.LeftLeg2.func_78793_a(4.0f, 10.1f, -2.8f);
        this.LeftLeg2.func_78790_a(-3.0f, 0.0f, -4.4f, 6, 11, 5, 0.0f);
        this.setRotation(this.LeftLeg2, 1.5934856f, 0.0f, 0.0f);
        this.L = new ModelRenderer((ModelBase)this, 35, 0);
        this.L.field_78809_i = true;
        this.L.func_78793_a(0.0f, 0.0f, -5.0f);
        this.L.func_78790_a(0.0f, -2.5f, 0.0f, 4, 5, 6, 0.0f);
        this.setRotation(this.L, 0.0f, 0.59184116f, 0.0f);
        this.RightLeg3.func_78792_a(this.rf3);
        this.LeftLeg3.func_78792_a(this.lf3);
        this.LeftLeg3.func_78792_a(this.lf4);
        this.Head.func_78792_a(this.R);
        this.RightLeg.func_78792_a(this.RightLeg2);
        this.Tail.func_78792_a(this.Tail2);
        this.Body.func_78792_a(this.LeftLeg);
        this.LeftLeg3.func_78792_a(this.lf2);
        this.RightLeg3.func_78792_a(this.lf4_1);
        this.Tail5.func_78792_a(this.Tail6);
        this.Body.func_78792_a(this.RightLeg);
        this.T.func_78792_a(this.T2);
        this.Tail2.func_78792_a(this.Tail3);
        this.RightLeg2.func_78792_a(this.RightLeg3);
        this.Tail4.func_78792_a(this.Tail5);
        this.RightLeg3.func_78792_a(this.rf2);
        this.Neck5.func_78792_a(this.Head);
        this.Head.func_78792_a(this.T);
        this.Neck3.func_78792_a(this.Neck4);
        this.Tail3.func_78792_a(this.Tail4);
        this.RightLeg3.func_78792_a(this.rf1);
        this.LeftLeg3.func_78792_a(this.lf1);
        this.Neck4.func_78792_a(this.Neck5);
        this.Neck.func_78792_a(this.Neck2);
        this.Body.func_78792_a(this.Tail);
        this.LeftLeg2.func_78792_a(this.LeftLeg3);
        this.Body.func_78792_a(this.Neck);
        this.Head.func_78792_a(this.Mouth);
        this.Neck2.func_78792_a(this.Neck3);
        this.LeftLeg.func_78792_a(this.LeftLeg2);
        this.Head.func_78792_a(this.L);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        float r2 = 180.0f;
        float r = 360.0f;
        float n4 = (par4 + r2) % r;
        float f = n4 = n4 > 0.0f ? n4 - r2 : n4 + r2;
        n4 = n4 > r2 ? r2 : (n4 < -r2 ? -r2 : n4);
        float n5 = par5;
        float p = 5.0f;
        this.Head.field_78796_g = n4 / (r2 / (float)Math.PI) / p;
        this.Head.field_78795_f = n5 / (r2 / (float)Math.PI) / p;
        this.Neck.field_78796_g = n4 / (r2 / (float)Math.PI) / p;
        this.Neck.field_78795_f = n5 / (r2 / (float)Math.PI) / p;
        this.Neck2.field_78796_g = n4 / (r2 / (float)Math.PI) / p;
        this.Neck2.field_78795_f = n5 / (r2 / (float)Math.PI) / p;
        this.Neck3.field_78796_g = n4 / (r2 / (float)Math.PI) / p;
        this.Neck3.field_78795_f = n5 / (r2 / (float)Math.PI) / p;
        this.Neck4.field_78796_g = n4 / (r2 / (float)Math.PI) / p;
        this.Neck4.field_78795_f = n5 / (r2 / (float)Math.PI) / p;
        this.Neck5.field_78796_g = n4 / (r2 / (float)Math.PI) / p;
        this.Neck5.field_78795_f = n5 / (r2 / (float)Math.PI) / p;
        this.RightLeg.field_78795_f = -0.5f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.2f * par2;
        this.LeftLeg.field_78795_f = -0.5f + MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
        this.RightLeg.field_78796_g = 0.0f;
        this.LeftLeg.field_78796_g = 0.0f;
        float ex = par7Entity.field_70173_aa;
        float r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
        float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 4.0f - 0.2f;
        this.Tail.field_78796_g = 0.2f;
        this.Tail.field_78796_g += r4;
        this.Tail2.field_78796_g = 0.2f;
        this.Tail2.field_78796_g += r4;
        this.Tail3.field_78796_g = 0.2f;
        this.Tail3.field_78796_g += r4;
        this.Tail4.field_78796_g = 0.2f;
        this.Tail4.field_78796_g += r4;
        this.Tail5.field_78796_g = 0.2f;
        this.Tail5.field_78796_g += r4;
        this.Tail6.field_78796_g = 0.2f;
        this.Tail6.field_78796_g += r4;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    }
}

