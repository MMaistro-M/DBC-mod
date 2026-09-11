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

import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelDragon
extends ModelBase {
    public ModelRenderer bodyM;
    public ModelRenderer HeadM;
    public ModelRenderer[] body1;
    public ModelRenderer[] body2;
    public ModelRenderer[] body3;
    public ModelRenderer Head1;
    public ModelRenderer HeadSnout;
    public ModelRenderer HeadM2;
    public ModelRenderer body4;
    public ModelRenderer body5;
    public ModelRenderer hornLB;
    public ModelRenderer hornRB;
    public ModelRenderer hornL;
    public ModelRenderer hornL1;
    public ModelRenderer hornL2;
    public ModelRenderer hornR;
    public ModelRenderer hornR1;
    public ModelRenderer hornR2;
    public ModelRenderer ArmR;
    public ModelRenderer ArmL;
    public ModelRenderer ArmR1;
    public ModelRenderer FingerR1;
    public ModelRenderer FingerR2;
    public ModelRenderer FingerR3;
    public ModelRenderer FingerR0;
    public ModelRenderer ArmL1;
    public ModelRenderer FingerL1;
    public ModelRenderer FingerL2;
    public ModelRenderer FingerL3;
    public ModelRenderer FingerL0;
    public ModelRenderer[] whiskR;
    public ModelRenderer[] whiskL;
    ArrayList<ModelRenderer> models = new ArrayList();
    ArrayList<ModelRenderer> wiskerr = new ArrayList();
    ArrayList<ModelRenderer> wiskerl = new ArrayList();

    public ModelDragon() {
        int i;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.HeadM = new ModelRenderer((ModelBase)this, 40, 0);
        this.HeadM.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadM.func_78790_a(-5.0f, -5.0f, -10.0f, 10, 7, 10, 0.0f);
        this.setRotate(this.HeadM, -1.54f, 0.0f, 0.0f);
        this.HeadSnout = new ModelRenderer((ModelBase)this, 80, 0);
        this.HeadSnout.func_78793_a(0.0f, 0.0f, -10.0f);
        this.HeadSnout.func_78790_a(-4.0f, -1.0f, -12.0f, 8, 3, 12, 0.0f);
        this.Head1 = new ModelRenderer((ModelBase)this, 40, 17);
        this.Head1.func_78793_a(0.0f, 4.0f, -8.0f);
        this.Head1.func_78790_a(-4.0f, -2.0f, -14.0f, 8, 3, 16, 0.0f);
        this.HeadM2 = new ModelRenderer((ModelBase)this, 74, 15);
        this.HeadM2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HeadM2.func_78790_a(-5.0f, 2.0f, -6.0f, 10, 3, 6, 0.0f);
        this.bodyM = new ModelRenderer((ModelBase)this, 0, 0);
        this.bodyM.func_78793_a(0.0f, -80.0f, 0.0f);
        this.bodyM.func_78790_a(0.0f, 0.0f, 5.0f, 0, 0, 0, 0.0f);
        this.whiskL = new ModelRenderer[8];
        this.whiskR = new ModelRenderer[8];
        for (i = 0; i < this.whiskL.length; ++i) {
            this.whiskL[i] = new ModelRenderer((ModelBase)this, 40, 17);
            this.whiskL[i].func_78793_a(3.8f, i == 0 ? 0.5f : 0.0f, i == 0 ? -10.0f : 0.0f);
            this.whiskL[i].func_78790_a(0.0f, -0.5f, -0.5f, 4, 1, 1, 0.0f);
            if (i > 0 && i < this.whiskL.length) {
                this.whiskL[i - 1].func_78792_a(this.whiskL[i]);
            }
            this.wiskerl.add(this.whiskL[i]);
        }
        for (i = 0; i < this.whiskR.length; ++i) {
            this.whiskR[i] = new ModelRenderer((ModelBase)this, 40, 17);
            this.whiskR[i].func_78793_a(-3.8f, i == 0 ? -0.5f : 0.0f, i == 0 ? -10.0f : 0.0f);
            this.whiskR[i].func_78790_a(-4.0f, -0.5f, -0.5f, 4, 1, 1, 0.0f);
            if (i > 0 && i < this.whiskR.length) {
                this.whiskR[i - 1].func_78792_a(this.whiskR[i]);
            }
            this.wiskerr.add(this.whiskR[i]);
        }
        this.hornL2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.hornL2.func_78793_a(0.0f, -3.0f, 0.0f);
        this.hornL2.func_78790_a(-1.0f, -8.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotate(this.hornL2, 0.68294734f, 0.0f, 0.0f);
        this.FingerR1 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerR1.func_78793_a(-15.0f, -1.6f, -0.5f);
        this.FingerR1.func_78790_a(-6.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerR1, 0.0f, -0.59184116f, 0.5009095f);
        this.hornLB = new ModelRenderer((ModelBase)this, 40, 23);
        this.hornLB.func_78793_a(2.5f, -3.5f, -4.0f);
        this.hornLB.func_78790_a(-2.0f, -6.0f, -2.0f, 4, 6, 4, 0.0f);
        this.setRotate(this.hornLB, -0.8196066f, 0.27314404f, 0.0f);
        this.FingerR3 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerR3.func_78793_a(-14.0f, 1.5f, -0.5f);
        this.FingerR3.func_78790_a(-6.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerR3, 0.0f, -0.59184116f, -0.8196066f);
        this.FingerL1 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerL1.field_78809_i = true;
        this.FingerL1.func_78793_a(15.0f, -1.6f, -0.5f);
        this.FingerL1.func_78790_a(0.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerL1, 0.0f, 0.57595867f, -0.5009095f);
        this.ArmR1 = new ModelRenderer((ModelBase)this, 0, 76);
        this.ArmR1.func_78793_a(-14.0f, 0.0f, 0.0f);
        this.ArmR1.func_78790_a(-16.0f, -2.0f, -2.0f, 16, 4, 4, 0.0f);
        this.setRotate(this.ArmR1, 0.0f, -1.0471976f, 0.0f);
        this.hornR = new ModelRenderer((ModelBase)this, 106, 15);
        this.hornR.func_78793_a(0.0f, -6.0f, 0.0f);
        this.hornR.func_78790_a(-1.0f, -16.0f, -1.0f, 2, 16, 2, 0.0f);
        this.FingerL2 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerL2.field_78809_i = true;
        this.FingerL2.func_78793_a(15.0f, 0.5f, -0.5f);
        this.FingerL2.func_78790_a(0.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerL2, 0.0f, 0.59184116f, 0.0f);
        this.ArmL1 = new ModelRenderer((ModelBase)this, 0, 76);
        this.ArmL1.field_78809_i = true;
        this.ArmL1.func_78793_a(14.0f, 0.0f, 0.0f);
        this.ArmL1.func_78790_a(0.0f, -2.0f, -2.0f, 16, 4, 4, 0.0f);
        this.setRotate(this.ArmL1, 0.0f, 1.0016445f, 0.0f);
        this.hornRB = new ModelRenderer((ModelBase)this, 40, 23);
        this.hornRB.func_78793_a(-2.5f, -3.5f, -4.0f);
        this.hornRB.func_78790_a(-2.0f, -6.0f, -2.0f, 4, 6, 4, 0.0f);
        this.setRotate(this.hornRB, -0.8196066f, -0.27314404f, 0.0f);
        this.hornL1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.hornL1.func_78793_a(0.0f, -4.0f, 0.0f);
        this.hornL1.func_78790_a(-1.0f, -8.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotate(this.hornL1, -0.63739425f, 0.0f, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 0, 68);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.0f, 12.0f, 0.0f);
        this.ArmL.func_78790_a(-0.0f, -2.0f, -2.0f, 16, 4, 4, 0.0f);
        this.FingerL3 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerL3.field_78809_i = true;
        this.FingerL3.func_78793_a(14.0f, 1.6f, -0.5f);
        this.FingerL3.func_78790_a(0.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerL3, 0.0f, 0.59184116f, 0.5009095f);
        this.FingerR2 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerR2.func_78793_a(-15.0f, 0.5f, -0.5f);
        this.FingerR2.func_78790_a(-6.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerR2, 0.0f, -0.59184116f, 0.0f);
        this.hornL = new ModelRenderer((ModelBase)this, 106, 15);
        this.hornL.func_78793_a(0.0f, -6.0f, 0.0f);
        this.hornL.func_78790_a(-1.0f, -16.0f, -1.0f, 2, 16, 2, 0.0f);
        this.hornR2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.hornR2.func_78793_a(0.0f, -3.0f, 0.0f);
        this.hornR2.func_78790_a(-1.0f, -8.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotate(this.hornR2, 0.68294734f, 0.0f, 0.0f);
        this.ArmR = new ModelRenderer((ModelBase)this, 0, 68);
        this.ArmR.func_78793_a(-4.0f, 12.0f, 0.0f);
        this.ArmR.func_78790_a(-16.0f, -2.0f, -2.0f, 16, 4, 4, 0.0f);
        this.FingerL0 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerL0.field_78809_i = true;
        this.FingerL0.func_78793_a(13.0f, -1.0f, -0.5f);
        this.FingerL0.func_78790_a(0.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerL0, 0.0f, 0.59184116f, -1.548107f);
        this.hornR1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.hornR1.func_78793_a(0.0f, -4.0f, 0.0f);
        this.hornR1.func_78790_a(-1.0f, -8.0f, -1.0f, 2, 8, 2, 0.0f);
        this.setRotate(this.hornR1, -0.63739425f, 0.0f, 0.0f);
        this.FingerR0 = new ModelRenderer((ModelBase)this, 0, 84);
        this.FingerR0.func_78793_a(-13.0f, -1.0f, -0.5f);
        this.FingerR0.func_78790_a(-6.0f, -1.0f, -1.0f, 6, 2, 2, 0.0f);
        this.setRotate(this.FingerR0, 0.0f, -0.59184116f, 1.548107f);
        this.HeadM.func_78792_a(this.hornLB);
        this.HeadM.func_78792_a(this.hornRB);
        this.hornL.func_78792_a(this.hornL2);
        this.hornRB.func_78792_a(this.hornR);
        this.hornL.func_78792_a(this.hornL1);
        this.hornLB.func_78792_a(this.hornL);
        this.hornR.func_78792_a(this.hornR2);
        this.hornR.func_78792_a(this.hornR1);
        this.ArmR1.func_78792_a(this.FingerR0);
        this.ArmL1.func_78792_a(this.FingerL0);
        this.ArmL1.func_78792_a(this.FingerL3);
        this.ArmR1.func_78792_a(this.FingerR2);
        this.ArmL1.func_78792_a(this.FingerL2);
        this.ArmR1.func_78792_a(this.FingerR1);
        this.ArmR1.func_78792_a(this.FingerR3);
        this.ArmL1.func_78792_a(this.FingerL1);
        this.ArmL.func_78792_a(this.ArmL1);
        this.ArmR.func_78792_a(this.ArmR1);
        this.body1 = new ModelRenderer[12];
        this.body2 = new ModelRenderer[8];
        this.body3 = new ModelRenderer[4];
        for (i = 0; i < this.body1.length; ++i) {
            this.body1[i] = new ModelRenderer((ModelBase)this, 0, 34);
            this.body1[i].func_78793_a(0.0f, i == 0 ? 0.0f : 23.0f, 0.0f);
            this.body1[i].func_78790_a(-5.0f, 0.0f, -5.0f, 10, 24, 10, 0.0f);
            if (i > 0 && i < this.body1.length) {
                this.body1[i - 1].func_78792_a(this.body1[i]);
            }
            this.models.add(this.body1[i]);
        }
        for (i = 0; i < this.body2.length; ++i) {
            this.body2[i] = new ModelRenderer((ModelBase)this, 40, 36);
            this.body2[i].func_78793_a(0.0f, i == 0 ? 23.0f : 17.0f, 0.0f);
            this.body2[i].func_78790_a(-4.0f, 0.0f, -4.0f, 8, 18, 8, 0.0f);
            if (i > 0 && i < this.body2.length) {
                this.body2[i - 1].func_78792_a(this.body2[i]);
            }
            this.models.add(this.body2[i]);
        }
        for (i = 0; i < this.body3.length; ++i) {
            this.body3[i] = new ModelRenderer((ModelBase)this, 72, 36);
            this.body3[i].func_78793_a(0.0f, i == 0 ? 16.0f : 15.0f, 0.0f);
            this.body3[i].func_78790_a(-3.0f, 0.0f, -3.0f, 6, 16, 6, 0.0f);
            if (i > 0 && i < this.body3.length) {
                this.body3[i - 1].func_78792_a(this.body3[i]);
            }
            this.models.add(this.body3[i]);
        }
        this.body4 = new ModelRenderer((ModelBase)this, 96, 36);
        this.body4.func_78793_a(0.0f, 15.0f, 0.0f);
        this.body4.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 14, 4, 0.0f);
        this.body5 = new ModelRenderer((ModelBase)this, 112, 36);
        this.body5.func_78793_a(0.0f, 13.0f, 0.0f);
        this.body5.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 14, 2, 0.0f);
        this.models.add(this.body4);
        this.models.add(this.body5);
        this.bodyM.func_78792_a(this.body1[0]);
        this.body1[this.body1.length - 1].func_78792_a(this.body2[0]);
        this.body2[this.body2.length - 1].func_78792_a(this.body3[0]);
        this.body3[this.body3.length - 1].func_78792_a(this.body4);
        this.body4.func_78792_a(this.body5);
        this.HeadSnout.func_78792_a(this.whiskL[0]);
        this.HeadSnout.func_78792_a(this.whiskR[0]);
        this.bodyM.func_78792_a(this.HeadM);
        this.HeadM.func_78792_a(this.Head1);
        this.HeadM.func_78792_a(this.HeadSnout);
        this.HeadM.func_78792_a(this.HeadM2);
        this.body1[1].func_78792_a(this.ArmL);
        this.body1[1].func_78792_a(this.ArmR);
        this.body1[11].func_78792_a(this.ArmL);
        this.body1[11].func_78792_a(this.ArmR);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
        this.bodyM.func_78785_a(f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void setRotate(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        ModelRenderer o;
        int i;
        this.HeadM.field_78796_g = par4 / 57.295776f;
        this.HeadM.field_78795_f = par5 / 57.295776f * 1.2f;
        float r = MathHelper.func_76126_a((float)(par3 * 0.02f)) * 0.1f;
        float r2 = MathHelper.func_76134_b((float)(par3 * 0.02f)) * 0.1f;
        float r3 = MathHelper.func_76134_b((float)(par3 * 0.14f)) * 0.1f;
        this.ArmL.field_78796_g = 0.3f + r2 * 2.0f;
        this.ArmR.field_78796_g = -0.3f - r2 * 2.0f;
        this.ArmL1.field_78796_g = 0.6f + r2 * 2.0f;
        this.ArmR1.field_78796_g = -0.6f - r2 * 2.0f;
        this.body4.field_78796_g = 0.0f;
        this.body4.field_78795_f = 0.0f;
        this.body5.field_78796_g = 0.0f;
        this.body5.field_78795_f = 0.0f;
        for (i = 0; i < this.wiskerr.size(); ++i) {
            o = this.wiskerr.get(i);
            if (o == null) continue;
            if (i == 0 || i == 1) {
                o.field_78808_h = 0.2f - r2 * 1.05f;
                o.field_78795_f = -0.0f + r * 0.75f;
                continue;
            }
            if (i == 2 || i == 3) {
                o.field_78808_h = -0.45f + r2 * 1.05f;
                o.field_78795_f = -0.3f + r * 0.5f;
                continue;
            }
            if (i == 4 || i == 5 || i == 6) {
                o.field_78808_h = 0.2f + 0.1f * (float)i - r * 1.05f;
                o.field_78795_f = -0.3f + 0.1f * (float)i - r * 0.5f;
                continue;
            }
            if (i == 7 || i == 8) {
                o.field_78808_h = -0.75f - r * 1.05f;
                o.field_78795_f = -0.2f - r2 * 0.5f;
                continue;
            }
            o.field_78808_h = -0.0f;
            o.field_78795_f = -0.0f;
            o.field_78796_g = -0.0f;
        }
        for (i = 0; i < this.wiskerl.size(); ++i) {
            o = this.wiskerl.get(i);
            if (o == null) continue;
            if (i == 0 || i == 1) {
                o.field_78808_h = -0.2f - r2 * 1.05f;
                o.field_78795_f = -0.0f + r * 0.75f;
                continue;
            }
            if (i == 2 || i == 3) {
                o.field_78808_h = 0.45f - r * 1.05f;
                o.field_78795_f = -0.3f + r2 * 0.75f;
                continue;
            }
            if (i == 4 || i == 5 || i == 6) {
                o.field_78808_h = -0.2f - 0.1f * (float)i + r * 1.05f;
                o.field_78795_f = -0.3f + 0.1f * (float)i - r2 * 0.75f;
                continue;
            }
            if (i == 7 || i == 8) {
                o.field_78808_h = 0.75f + r2 * 1.05f;
                o.field_78795_f = -0.2f - r * 0.75f;
                continue;
            }
            o.field_78808_h = -0.0f;
            o.field_78795_f = -0.0f;
            o.field_78796_g = -0.0f;
        }
        this.Head1.field_78808_h = -0.0f;
        this.Head1.field_78795_f = 0.15f + r2 * 0.5f;
        this.bodyM.field_78797_d = -200.0f + r * 40.0f;
        for (i = 0; i < this.models.size(); ++i) {
            if (i == 0) {
                o = this.models.get(i);
                o.field_78808_h = 0.0f;
                o.field_78795_f = 2.0f;
                continue;
            }
            if (i == 1 || i == 2 || i == 3) {
                o = this.models.get(i);
                o.field_78808_h = -0.9f + r * 0.75f;
                o.field_78795_f = -0.5f;
                continue;
            }
            if (i == 4 || i == 5 || i == 6) {
                o = this.models.get(i);
                o.field_78808_h = 0.9f - r * 0.75f;
                o.field_78795_f = -0.7f;
                continue;
            }
            if (i == 7 || i == 8 || i == 9) {
                o = this.models.get(i);
                o.field_78808_h = -0.75f + r * 0.75f;
                o.field_78795_f = -0.3f;
                continue;
            }
            if (i == 10 || i == 11 || i == 12) {
                o = this.models.get(i);
                o.field_78808_h = -0.5f - r * 0.75f;
                o.field_78795_f = -0.25f;
                continue;
            }
            if (i == 13 || i == 14 || i == 15) {
                o = this.models.get(i);
                o.field_78808_h = 0.5f + r * 0.75f;
                o.field_78795_f = 0.75f;
                continue;
            }
            if (i == 16 || i == 17 || i == 18) {
                o = this.models.get(i);
                o.field_78808_h = 0.75f - r * 0.75f;
                o.field_78795_f = -0.5f;
                continue;
            }
            if (i == 19 || i == 20 || i == 21) {
                o = this.models.get(i);
                o.field_78808_h = -0.5f + r * 0.75f;
                o.field_78795_f = -0.75f;
                continue;
            }
            if (i == 22 || i == 23) {
                o = this.models.get(i);
                o.field_78808_h = 1.0f - r * 0.75f;
                o.field_78795_f = 0.5f;
                continue;
            }
            if (i == 24 || i == 25) {
                o = this.models.get(i);
                o.field_78808_h = -0.2f + r * 0.75f;
                o.field_78795_f = 0.1f;
                continue;
            }
            o = this.models.get(i);
            o.field_78808_h = -0.0f;
            o.field_78795_f = -0.0f;
            o.field_78796_g = -0.0f;
        }
    }
}

