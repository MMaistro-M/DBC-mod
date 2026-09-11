/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package hedaox.ninjinentities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelJanembaSuper
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelJanembaSuper(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer earleft = new ModelRenderer((ModelBase)this);
        earleft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earleft, 0.0f, -0.7695f, -0.4554f);
        this.Head.func_78792_a(earleft);
        earleft.field_78804_l.add(new ModelBox(earleft, 44, 27, 1.6084f, -3.5f, -4.5365f, 6, 3, 0, 0.0f));
        ModelRenderer earright = new ModelRenderer((ModelBase)this);
        earright.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earright, 0.0f, 0.7695f, 0.4554f);
        this.Head.func_78792_a(earright);
        earright.field_78804_l.add(new ModelBox(earright, 39, 18, -7.5084f, -3.5f, -4.5365f, 6, 3, 0, 0.0f));
        ModelRenderer hornL1 = new ModelRenderer((ModelBase)this);
        hornL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(hornL1, -0.4098f, 0.0948f, 0.403f);
        this.Head.func_78792_a(hornL1);
        hornL1.field_78804_l.add(new ModelBox(hornL1, 0, 16, -1.9f, -10.4f, -6.0f, 3, 5, 2, 0.0f));
        ModelRenderer hornL2 = new ModelRenderer((ModelBase)this);
        hornL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(hornL2, -0.5918f, 0.182f, 0.4098f);
        this.Head.func_78792_a(hornL2);
        hornL2.field_78804_l.add(new ModelBox(hornL2, 0, 0, -1.6f, -12.3f, -7.6f, 2, 4, 2, 0.0f));
        ModelRenderer hornL3 = new ModelRenderer((ModelBase)this);
        hornL3.func_78793_a(6.1375f, -13.4875f, 2.025f);
        this.setRotationAngle(hornL3, -0.5084f, 0.2276f, 0.9183f);
        this.Head.func_78792_a(hornL3);
        hornL3.field_78804_l.add(new ModelBox(hornL3, 50, 50, -0.5f, -1.5f, -1.0f, 1, 3, 2, 0.0f));
        ModelRenderer hornR1 = new ModelRenderer((ModelBase)this);
        hornR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(hornR1, -0.4098f, -0.0948f, -0.403f);
        this.Head.func_78792_a(hornR1);
        hornR1.field_78804_l.add(new ModelBox(hornR1, 0, 16, -1.1f, -10.4f, -6.0f, 3, 5, 2, 0.0f));
        ModelRenderer hornR2 = new ModelRenderer((ModelBase)this);
        hornR2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(hornR2, -0.5918f, -0.182f, -0.4098f);
        this.Head.func_78792_a(hornR2);
        hornR2.field_78804_l.add(new ModelBox(hornR2, 0, 0, -0.4f, -12.3f, -7.6f, 2, 4, 2, 0.0f));
        ModelRenderer hornR3 = new ModelRenderer((ModelBase)this);
        hornR3.func_78793_a(-6.1375f, -13.4875f, 2.025f);
        this.setRotationAngle(hornR3, -0.5084f, -0.2276f, -0.9183f);
        this.Head.func_78792_a(hornR3);
        hornR3.field_78804_l.add(new ModelBox(hornR3, 50, 50, -0.5f, -1.5f, -1.0f, 1, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 24, 26, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        ModelRenderer tail = new ModelRenderer((ModelBase)this);
        tail.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(tail, -0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(tail);
        tail.field_78804_l.add(new ModelBox(tail, 0, 16, -2.0f, 7.0f, 3.0f, 3, 3, 11, 0.0f));
        ModelRenderer neckguardleft = new ModelRenderer((ModelBase)this);
        neckguardleft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(neckguardleft, 0.0f, 0.0f, 0.5918f);
        this.Body.func_78792_a(neckguardleft);
        neckguardleft.field_78804_l.add(new ModelBox(neckguardleft, 44, 16, 3.0f, -4.5f, -3.6f, 2, 4, 7, 0.0f));
        ModelRenderer neckguardright = new ModelRenderer((ModelBase)this);
        neckguardright.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(neckguardright, 0.0f, 0.0f, -0.5918f);
        this.Body.func_78792_a(neckguardright);
        neckguardright.field_78804_l.add(new ModelBox(neckguardright, 41, 35, -4.9f, -4.5f, -3.6f, 2, 4, 7, 0.0f));
        ModelRenderer tail2 = new ModelRenderer((ModelBase)this);
        tail2.func_78793_a(-3.0f, 15.2f, 17.0f);
        this.setRotationAngle(tail2, 0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(tail2);
        tail2.field_78804_l.add(new ModelBox(tail2, 23, 7, 1.0f, -4.4717f, -7.8214f, 3, 2, 9, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.1f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 28, 42, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.1f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 38, 0, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 30, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 12, 42, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
        this.Head.field_78796_g = f3 / 57.295776f;
        this.Head.field_78795_f = f4 / 57.295776f;
        this.RArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.LArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.RArm.field_78808_h = 0.0f;
        this.LArm.field_78808_h = 0.0f;
        this.RLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 1.4f * f1;
        this.LLeg.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.RLeg.field_78796_g = 0.0f;
        this.LLeg.field_78796_g = 0.0f;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

