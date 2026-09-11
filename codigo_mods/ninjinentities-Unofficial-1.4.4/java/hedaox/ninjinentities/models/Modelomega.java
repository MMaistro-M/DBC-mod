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

public class Modelomega
extends ModelBase {
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final ModelRenderer Body;
    private final ModelRenderer Head;
    private final ModelRenderer leftarm;
    private final ModelRenderer rightarm;
    public int heldItemRight;
    private final float scale = 1.0f;

    public Modelomega(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, -2.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -2.0f, 8, 8, 8, 0.0f));
        ModelRenderer shape3 = new ModelRenderer((ModelBase)this);
        shape3.func_78793_a(3.0f, -4.9f, -0.5f);
        this.Head.func_78792_a(shape3);
        this.setRotateAngle(shape3, 0.695f, -0.5463f, -0.8657f);
        shape3.field_78804_l.add(new ModelBox(shape3, 42, 26, 0.0f, 0.0f, 0.0f, 4, 2, 1, 0.0f));
        ModelRenderer rightEars = new ModelRenderer((ModelBase)this);
        rightEars.func_78793_a(-2.0f, -4.9f, 0.5f);
        this.Head.func_78792_a(rightEars);
        this.setRotateAngle(rightEars, 2.9596f, -0.5463f, -2.3003f);
        rightEars.field_78804_l.add(new ModelBox(rightEars, 39, 26, 0.0f, 0.0f, 0.0f, 4, 2, 1, 0.0f));
        ModelRenderer faceFang3 = new ModelRenderer((ModelBase)this);
        faceFang3.func_78793_a(2.0f, -1.5f, -2.4f);
        this.Head.func_78792_a(faceFang3);
        this.setRotateAngle(faceFang3, -0.2276f, -0.5009f, 0.0f);
        faceFang3.field_78804_l.add(new ModelBox(faceFang3, 9, 1, 0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f));
        ModelRenderer faceFang2 = new ModelRenderer((ModelBase)this);
        faceFang2.func_78793_a(-2.5f, -1.5f, -2.0f);
        this.Head.func_78792_a(faceFang2);
        this.setRotateAngle(faceFang2, -0.2276f, 0.5009f, 0.0f);
        faceFang2.field_78804_l.add(new ModelBox(faceFang2, 9, 2, 0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f));
        ModelRenderer faceFang = new ModelRenderer((ModelBase)this);
        faceFang.func_78793_a(-0.5f, -1.5f, -2.5f);
        this.Head.func_78792_a(faceFang);
        this.setRotateAngle(faceFang, -0.2276f, 0.0f, 0.0f);
        faceFang.field_78804_l.add(new ModelBox(faceFang, 9, 1, 0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f));
        ModelRenderer headFang = new ModelRenderer((ModelBase)this);
        headFang.func_78793_a(0.8f, -15.5f, 3.0f);
        this.Head.func_78792_a(headFang);
        this.setRotateAngle(headFang, -0.5463f, 0.0f, 0.0f);
        headFang.field_78804_l.add(new ModelBox(headFang, 33, 0, 0.0f, 1.0f, 1.0f, 3, 8, 3, 0.0f));
        ModelRenderer headFang2 = new ModelRenderer((ModelBase)this);
        headFang2.func_78793_a(0.8f, -15.5f, 3.0f);
        this.Head.func_78792_a(headFang2);
        this.setRotateAngle(headFang2, -0.5463f, 0.0f, 0.0f);
        headFang2.field_78804_l.add(new ModelBox(headFang2, 33, 0, -4.6f, 1.0f, 1.0f, 3, 8, 3, 0.0f));
        this.leftarm = new ModelRenderer((ModelBase)this);
        this.leftarm.func_78793_a(5.2f, 1.7f, 1.0f);
        this.leftarm.field_78804_l.add(new ModelBox(this.leftarm, 40, 16, -0.6f, -2.5f, -3.7f, 5, 8, 5, 0.0f));
        ModelRenderer leftArmFang = new ModelRenderer((ModelBase)this);
        leftArmFang.func_78793_a(0.0f, 2.3f, 2.6f);
        this.leftarm.func_78792_a(leftArmFang);
        this.setRotateAngle(leftArmFang, -0.2731f, 0.0f, 0.0f);
        leftArmFang.field_78804_l.add(new ModelBox(leftArmFang, 91, 1, 0.0f, 0.5394f, -1.9259f, 2, 2, 3, 0.0f));
        ModelRenderer leftarmChild_1 = new ModelRenderer((ModelBase)this);
        leftarmChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftarm.func_78792_a(leftarmChild_1);
        leftarmChild_1.field_78804_l.add(new ModelBox(leftarmChild_1, 40, 40, -0.2f, 5.0f, -3.5f, 4, 2, 4, 0.0f));
        ModelRenderer leftarmChild = new ModelRenderer((ModelBase)this);
        leftarmChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftarm.func_78792_a(leftarmChild);
        leftarmChild.field_78804_l.add(new ModelBox(leftarmChild, 7, 40, -1.1f, -2.8f, -4.2f, 6, 6, 6, 0.0f));
        ModelRenderer leftShoulderFang = new ModelRenderer((ModelBase)this);
        leftShoulderFang.func_78793_a(1.5f, -2.5f, 0.0f);
        leftarmChild.func_78792_a(leftShoulderFang);
        this.setRotateAngle(leftShoulderFang, 0.0f, -0.1367f, -0.6374f);
        leftShoulderFang.field_78804_l.add(new ModelBox(leftShoulderFang, 87, 0, 0.7275f, 1.0f, -1.9813f, 4, 2, 2, 0.0f));
        ModelRenderer backFang7 = new ModelRenderer((ModelBase)this);
        backFang7.func_78793_a(-0.2f, -2.6408f, 5.3218f);
        leftarmChild.func_78792_a(backFang7);
        this.setRotateAngle(backFang7, 0.6541f, 0.2095f, 0.1581f);
        backFang7.field_78804_l.add(new ModelBox(backFang7, 88, 0, -0.084f, -2.1902f, -5.5525f, 2, 2, 6, 0.0f));
        ModelRenderer leftarmChild_2 = new ModelRenderer((ModelBase)this);
        leftarmChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftarm.func_78792_a(leftarmChild_2);
        leftarmChild_2.field_78804_l.add(new ModelBox(leftarmChild_2, 70, 40, -0.7f, 6.2f, -3.7f, 5, 6, 5, 0.0f));
        this.rightarm = new ModelRenderer((ModelBase)this);
        this.rightarm.field_78809_i = true;
        this.rightarm.func_78793_a(-5.2f, 1.7f, -1.0f);
        this.rightarm.field_78804_l.add(new ModelBox(this.rightarm, 40, 16, -4.4f, -2.5f, -1.7f, 5, 8, 5, 0.0f));
        ModelRenderer rightArmFang = new ModelRenderer((ModelBase)this);
        rightArmFang.func_78793_a(-2.0f, 2.3f, 3.6f);
        this.rightarm.func_78792_a(rightArmFang);
        this.setRotateAngle(rightArmFang, -0.2731f, 0.0f, 0.0f);
        rightArmFang.field_78804_l.add(new ModelBox(rightArmFang, 90, 0, 0.0f, 0.2697f, -0.9629f, 2, 2, 3, 0.0f));
        ModelRenderer rightarmChild_2 = new ModelRenderer((ModelBase)this);
        rightarmChild_2.field_78809_i = true;
        rightarmChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rightarm.func_78792_a(rightarmChild_2);
        rightarmChild_2.field_78804_l.add(new ModelBox(rightarmChild_2, 70, 40, -4.3f, 6.2f, -1.7f, 5, 6, 5, 0.0f));
        ModelRenderer rightarmChild = new ModelRenderer((ModelBase)this);
        rightarmChild.field_78809_i = true;
        rightarmChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rightarm.func_78792_a(rightarmChild);
        rightarmChild.field_78804_l.add(new ModelBox(rightarmChild, 7, 40, -4.8f, -2.8f, -2.2f, 6, 6, 6, 0.0f));
        ModelRenderer rightShoulderFang = new ModelRenderer((ModelBase)this);
        rightShoulderFang.func_78793_a(-4.5f, -5.0f, 1.0f);
        rightarmChild.func_78792_a(rightShoulderFang);
        this.setRotateAngle(rightShoulderFang, 0.0f, 0.1367f, 0.6374f);
        rightShoulderFang.field_78804_l.add(new ModelBox(rightShoulderFang, 87, 1, -0.8637f, 1.0f, -0.9907f, 4, 2, 2, 0.0f));
        ModelRenderer backFang8 = new ModelRenderer((ModelBase)this);
        backFang8.func_78793_a(0.0119f, -2.1833f, 5.7041f);
        rightarmChild.func_78792_a(backFang8);
        this.setRotateAngle(backFang8, 0.6541f, -0.2095f, -0.1581f);
        backFang8.field_78804_l.add(new ModelBox(backFang8, 88, 1, -1.208f, -1.5951f, -4.7763f, 2, 2, 6, 0.0f));
        ModelRenderer rightarmChild_1 = new ModelRenderer((ModelBase)this);
        rightarmChild_1.field_78809_i = true;
        rightarmChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.rightarm.func_78792_a(rightarmChild_1);
        rightarmChild_1.field_78804_l.add(new ModelBox(rightarmChild_1, 40, 40, -3.7f, 5.0f, -1.5f, 4, 2, 4, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(-4.0f, 0.0f, -2.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, 0.0f, 0.0f, 0.0f, 8, 12, 4, 0.1f));
        ModelRenderer backFang1 = new ModelRenderer((ModelBase)this);
        backFang1.func_78793_a(1.0f, 2.6f, 3.3f);
        this.Body.func_78792_a(backFang1);
        this.setRotateAngle(backFang1, 0.5009f, 0.0f, 0.0f);
        backFang1.field_78804_l.add(new ModelBox(backFang1, 91, 1, 0.0f, 0.0f, 0.0f, 2, 2, 4, 0.0f));
        ModelRenderer backFang3 = new ModelRenderer((ModelBase)this);
        backFang3.func_78793_a(0.0f, 6.0f, 3.3f);
        this.Body.func_78792_a(backFang3);
        this.setRotateAngle(backFang3, 0.5009f, 0.0f, 0.0f);
        backFang3.field_78804_l.add(new ModelBox(backFang3, 89, 1, 0.0f, 0.0f, 0.0f, 2, 2, 5, 0.0f));
        ModelRenderer backFang2 = new ModelRenderer((ModelBase)this);
        backFang2.func_78793_a(5.0f, 2.6f, 3.3f);
        this.Body.func_78792_a(backFang2);
        this.setRotateAngle(backFang2, 0.5009f, 0.0f, 0.0f);
        backFang2.field_78804_l.add(new ModelBox(backFang2, 88, 0, 0.0f, 0.0f, 0.0f, 2, 2, 4, 0.0f));
        ModelRenderer backFang4 = new ModelRenderer((ModelBase)this);
        backFang4.func_78793_a(6.0f, 6.0f, 3.3f);
        this.Body.func_78792_a(backFang4);
        this.setRotateAngle(backFang4, 0.5009f, 0.0f, 0.0f);
        backFang4.field_78804_l.add(new ModelBox(backFang4, 89, 0, 0.0f, 0.0f, 0.0f, 2, 2, 5, 0.0f));
        ModelRenderer backFang6 = new ModelRenderer((ModelBase)this);
        backFang6.func_78793_a(5.0f, 7.6f, 3.3f);
        this.Body.func_78792_a(backFang6);
        this.setRotateAngle(backFang6, -0.4098f, 0.0f, 0.0f);
        backFang6.field_78804_l.add(new ModelBox(backFang6, 90, 1, 0.0f, 0.0f, 0.0f, 2, 2, 4, 0.0f));
        ModelRenderer backFang5 = new ModelRenderer((ModelBase)this);
        backFang5.func_78793_a(1.0f, 7.6f, 3.3f);
        this.Body.func_78792_a(backFang5);
        this.setRotateAngle(backFang5, -0.4098f, 0.0f, 0.0f);
        backFang5.field_78804_l.add(new ModelBox(backFang5, 90, 0, 0.0f, 0.0f, 0.0f, 2, 2, 4, 0.0f));
        ModelRenderer dragonBall7 = new ModelRenderer((ModelBase)this);
        dragonBall7.func_78793_a(3.0f, 3.0f, -1.0f);
        this.Body.func_78792_a(dragonBall7);
        dragonBall7.field_78804_l.add(new ModelBox(dragonBall7, 69, 0, 0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f));
        ModelRenderer dragonBall6 = new ModelRenderer((ModelBase)this);
        dragonBall6.func_78793_a(0.0f, 4.2f, -1.0f);
        this.Body.func_78792_a(dragonBall6);
        dragonBall6.field_78804_l.add(new ModelBox(dragonBall6, 69, 0, 0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f));
        ModelRenderer dragonBall = new ModelRenderer((ModelBase)this);
        dragonBall.func_78793_a(3.0f, 0.2f, -1.0f);
        this.Body.func_78792_a(dragonBall);
        dragonBall.field_78804_l.add(new ModelBox(dragonBall, 69, 0, 0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f));
        ModelRenderer dragonBall5 = new ModelRenderer((ModelBase)this);
        dragonBall5.func_78793_a(5.0f, 4.2f, -1.0f);
        this.Body.func_78792_a(dragonBall5);
        dragonBall5.field_78804_l.add(new ModelBox(dragonBall5, 69, 0, 1.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f));
        ModelRenderer dragonBall3 = new ModelRenderer((ModelBase)this);
        dragonBall3.func_78793_a(0.0f, 1.7f, -1.0f);
        this.Body.func_78792_a(dragonBall3);
        dragonBall3.field_78804_l.add(new ModelBox(dragonBall3, 69, 0, 0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f));
        ModelRenderer dragonBall2 = new ModelRenderer((ModelBase)this);
        dragonBall2.func_78793_a(6.0f, 1.7f, -1.0f);
        this.Body.func_78792_a(dragonBall2);
        this.setRotateAngle(dragonBall2, 0.0f, 0.0f, 0.0349f);
        dragonBall2.field_78804_l.add(new ModelBox(dragonBall2, 69, 0, 0.0f, 0.0f, 0.0f, 2, 2, 1, 0.0f));
        ModelRenderer dragonBall4 = new ModelRenderer((ModelBase)this);
        dragonBall4.func_78793_a(3.0f, 2.2f, -1.0f);
        this.Body.func_78792_a(dragonBall4);
        dragonBall4.field_78804_l.add(new ModelBox(dragonBall4, 69, 0, 0.0f, 4.0f, 0.0f, 2, 2, 1, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(2.0f, 12.0f, 2.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer rightLegFang = new ModelRenderer((ModelBase)this);
        rightLegFang.func_78793_a(-1.0f, 1.8f, -2.3f);
        this.RLeg.func_78792_a(rightLegFang);
        this.setRotateAngle(rightLegFang, 0.2731f, 0.0f, 0.0f);
        rightLegFang.field_78804_l.add(new ModelBox(rightLegFang, 88, 0, 0.0f, 0.0f, -2.0f, 2, 2, 3, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.field_78809_i = true;
        this.LLeg.func_78793_a(2.0f, 12.0f, 2.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer leftLegFang = new ModelRenderer((ModelBase)this);
        leftLegFang.func_78793_a(3.0f, 1.8f, -2.3f);
        this.LLeg.func_78792_a(leftLegFang);
        this.setRotateAngle(leftLegFang, 0.2731f, 0.0f, 0.0f);
        leftLegFang.field_78804_l.add(new ModelBox(leftLegFang, 87, 0, 0.0f, 0.0f, -2.0f, 2, 2, 3, 0.0f));
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotateAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        if (this.field_78091_s) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * p_78088_7_), (float)0.0f);
            this.Head.func_78785_a(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * p_78088_7_), (float)0.0f);
            this.Body.func_78785_a(p_78088_7_);
            GL11.glPopMatrix();
        } else {
            this.Head.func_78785_a(p_78088_7_);
            this.Body.func_78785_a(p_78088_7_);
            GL11.glScaled((double)0.9, (double)0.9, (double)0.9);
            this.rightarm.func_78785_a(p_78088_7_);
            this.leftarm.func_78785_a(p_78088_7_);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void setRotateAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.Head.field_78796_g = p_78087_4_ / 57.295776f;
        this.Head.field_78795_f = p_78087_5_ / 57.295776f;
        this.rightarm.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 2.0f * p_78087_2_ * 0.5f;
        this.leftarm.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 2.0f * p_78087_2_ * 0.5f;
        this.rightarm.field_78808_h = 0.0f;
        this.leftarm.field_78808_h = 0.0f;
        this.RLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.4f * p_78087_2_;
        this.LLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.4f * p_78087_2_;
        this.RLeg.field_78796_g = 0.0f;
        this.LLeg.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.rightarm.field_78795_f = 0.0f;
            this.RLeg.field_78795_f = 0.0f;
            this.LLeg.field_78795_f = 0.0f;
            this.RLeg.field_78796_g = 0.0f;
            this.LLeg.field_78796_g = 0.0f;
        }
        if (this.heldItemRight != 0) {
            this.rightarm.field_78795_f = this.rightarm.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemRight;
        }
        this.rightarm.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            float f6 = this.field_78095_p;
            this.Body.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f6) * (float)Math.PI * 2.0f)) * 0.2f;
            this.rightarm.field_78798_e = -MathHelper.func_76126_a((float)this.Body.field_78796_g) * 5.0f;
            this.rightarm.field_78800_c = -MathHelper.func_76134_b((float)this.Body.field_78796_g) * 6.0f;
            this.leftarm.field_78798_e = -MathHelper.func_76126_a((float)this.Body.field_78796_g) * 5.0f;
            this.leftarm.field_78800_c = MathHelper.func_76134_b((float)this.Body.field_78796_g) * 5.0f;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            float f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.Head.field_78795_f - 0.7f) * 0.75f;
            this.rightarm.field_78795_f = (float)((double)this.rightarm.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.rightarm.field_78796_g += this.Body.field_78796_g * 2.0f;
            this.rightarm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * 2.1415927f)) * -0.4f;
            this.rightarm.field_82906_o = 0.03f;
            this.rightarm.field_82907_q = -0.08f;
            this.leftarm.field_82907_q = 0.11f;
        }
    }
}

