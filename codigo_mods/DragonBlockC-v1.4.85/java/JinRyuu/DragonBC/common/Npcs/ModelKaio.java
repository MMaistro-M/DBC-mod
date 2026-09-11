/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.JRMCore.client.JGRenderHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelKaio
extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Body1;
    public ModelRenderer ShoulderR;
    public ModelRenderer ShoulderL;
    public ModelRenderer AntennaR;
    public ModelRenderer AntennaL;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer WhiskerR;
    public ModelRenderer WhiskerL;
    public ModelRenderer Body2;
    public ModelRenderer Body3;
    public ModelRenderer Body4;
    public ModelRenderer FeetR;
    public ModelRenderer FeetL;
    public ModelRenderer ArmR;
    public ModelRenderer HandR;
    public ModelRenderer ArmL;
    public ModelRenderer HandL;

    public ModelKaio() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.HandR = new ModelRenderer((ModelBase)this, 72, 66);
        this.HandR.func_78793_a(0.1f, 8.8f, 0.0f);
        this.HandR.func_78790_a(-1.5f, -0.5f, -2.5f, 3, 2, 5, 0.0f);
        this.FeetL = new ModelRenderer((ModelBase)this, 16, 106);
        this.FeetL.field_78809_i = true;
        this.FeetL.func_78793_a(5.0f, 5.0f, 0.0f);
        this.FeetL.func_78790_a(-2.0f, 0.0f, -4.4f, 4, 3, 6, 0.0f);
        this.setRotateAngle(this.FeetL, 0.0f, -0.090757124f, 0.0f);
        this.Body3 = new ModelRenderer((ModelBase)this, 1, 66);
        this.Body3.func_78793_a(0.0f, 5.0f, 0.0f);
        this.Body3.func_78790_a(-9.5f, 0.0f, -6.0f, 19, 5, 12, 0.0f);
        this.FeetR = new ModelRenderer((ModelBase)this, 16, 106);
        this.FeetR.func_78793_a(-5.0f, 5.0f, 0.0f);
        this.FeetR.func_78790_a(-2.0f, 0.0f, -4.4f, 4, 3, 6, 0.0f);
        this.setRotateAngle(this.FeetR, 0.0f, 0.090757124f, 0.0f);
        this.Body1 = new ModelRenderer((ModelBase)this, 1, 34);
        this.Body1.func_78793_a(0.0f, 1.0f, 0.0f);
        this.Body1.func_78790_a(-7.0f, 0.0f, -4.0f, 14, 5, 8, 0.0f);
        this.AntennaR = new ModelRenderer((ModelBase)this, 53, 1);
        this.AntennaR.func_78793_a(-1.9f, -6.5f, -5.2f);
        this.AntennaR.func_78790_a(0.0f, -3.9f, -12.9f, 0, 5, 13, 0.0f);
        this.setRotateAngle(this.AntennaR, 0.0f, 0.35604715f, 0.0f);
        this.EarR = new ModelRenderer((ModelBase)this, 38, 14);
        this.EarR.func_78793_a(-4.9f, -2.0f, -1.3f);
        this.EarR.func_78790_a(-3.1f, -4.2f, 0.0f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarR, 0.0f, 0.9546951f, 0.0f);
        this.ShoulderL = new ModelRenderer((ModelBase)this, 69, 31);
        this.ShoulderL.field_78809_i = true;
        this.ShoulderL.func_78793_a(7.0f, 3.1f, 0.0f);
        this.ShoulderL.func_78790_a(0.0f, -2.5f, -4.0f, 8, 6, 7, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 8, 12);
        this.Head.func_78793_a(0.0f, 1.3f, 0.0f);
        this.Head.func_78790_a(-5.0f, -7.4f, -5.3f, 10, 8, 8, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 1, 85);
        this.Body4.func_78793_a(0.0f, 5.0f, 0.0f);
        this.Body4.func_78790_a(-10.0f, 0.0f, -6.5f, 20, 5, 13, 0.0f);
        this.ArmL = new ModelRenderer((ModelBase)this, 72, 46);
        this.ArmL.field_78809_i = true;
        this.ArmL.func_78793_a(4.5f, 0.6f, -0.5f);
        this.ArmL.func_78790_a(-2.5f, -2.6f, -3.0f, 5, 11, 6, 0.0f);
        this.EarL = new ModelRenderer((ModelBase)this, 38, 14);
        this.EarL.field_78809_i = true;
        this.EarL.func_78793_a(5.0f, -2.0f, -1.3f);
        this.EarL.func_78790_a(0.0f, -4.2f, 0.0f, 3, 5, 0, 0.0f);
        this.setRotateAngle(this.EarL, 0.0f, -0.9546951f, 0.0f);
        this.WhiskerL = new ModelRenderer((ModelBase)this, 46, 23);
        this.WhiskerL.field_78809_i = true;
        this.WhiskerL.func_78793_a(4.0f, -1.7f, -5.2f);
        this.WhiskerL.func_78790_a(0.0f, -0.5f, -0.6f, 4, 1, 0, 0.0f);
        this.setRotateAngle(this.WhiskerL, 0.0f, 0.19547687f, 0.2268928f);
        this.AntennaL = new ModelRenderer((ModelBase)this, 53, 1);
        this.AntennaL.field_78809_i = true;
        this.AntennaL.func_78793_a(1.3f, -6.5f, -4.0f);
        this.AntennaL.func_78790_a(0.0f, -3.9f, -14.8f, 0, 5, 13, 0.0f);
        this.setRotateAngle(this.AntennaL, 0.0f, -0.35604715f, 0.0f);
        this.WhiskerR = new ModelRenderer((ModelBase)this, 46, 23);
        this.WhiskerR.func_78793_a(-4.0f, -1.7f, -5.2f);
        this.WhiskerR.func_78790_a(-4.0f, -0.5f, -0.1f, 4, 1, 0, 0.0f);
        this.setRotateAngle(this.WhiskerR, 0.0f, -0.19547687f, -0.2268928f);
        this.ArmR = new ModelRenderer((ModelBase)this, 72, 46);
        this.ArmR.func_78793_a(-4.5f, 0.6f, -0.5f);
        this.ArmR.func_78790_a(-2.5f, -2.6f, -3.0f, 5, 11, 6, 0.0f);
        this.HandL = new ModelRenderer((ModelBase)this, 72, 66);
        this.HandL.field_78809_i = true;
        this.HandL.func_78793_a(0.0f, 8.8f, 0.0f);
        this.HandL.func_78790_a(-1.5f, -0.5f, -2.5f, 3, 2, 5, 0.0f);
        this.ShoulderR = new ModelRenderer((ModelBase)this, 69, 31);
        this.ShoulderR.func_78793_a(-7.0f, 3.1f, 0.0f);
        this.ShoulderR.func_78790_a(-8.0f, -2.5f, -4.0f, 8, 6, 7, 0.0f);
        this.Body2 = new ModelRenderer((ModelBase)this, 0, 49);
        this.Body2.func_78793_a(0.0f, 5.0f, 0.0f);
        this.Body2.func_78790_a(-9.0f, 0.0f, -5.0f, 18, 5, 10, 0.0f);
        this.ArmR.func_78792_a(this.HandR);
        this.Body4.func_78792_a(this.FeetL);
        this.Body2.func_78792_a(this.Body3);
        this.Body4.func_78792_a(this.FeetR);
        this.Head.func_78792_a(this.AntennaR);
        this.Head.func_78792_a(this.EarR);
        this.Body3.func_78792_a(this.Body4);
        this.ShoulderL.func_78792_a(this.ArmL);
        this.Head.func_78792_a(this.EarL);
        this.Head.func_78792_a(this.WhiskerL);
        this.Head.func_78792_a(this.AntennaL);
        this.Head.func_78792_a(this.WhiskerR);
        this.ShoulderR.func_78792_a(this.ArmR);
        this.ArmL.func_78792_a(this.HandL);
        this.Body1.func_78792_a(this.Body2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        float F = 0.6f;
        JGRenderHelper.modelScalePositionHelper(0.6f);
        float n4 = f3;
        float n5 = f4;
        float r2 = 180.0f;
        this.Head.field_78796_g = n4 / (r2 / (float)Math.PI);
        this.Head.field_78795_f = n5 / (r2 / (float)Math.PI) - 0.2f;
        this.Body1.func_78785_a(f5);
        this.ShoulderL.func_78785_a(f5);
        this.Head.func_78785_a(f5);
        this.ShoulderR.func_78785_a(f5);
        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

