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

public class ModelSansho
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

    public ModelSansho(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 2, 0, 4.0f, -1.0f, -4.0f, 0, 7, 1, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -1.0f, -4.0f, 0, 7, 1, 0.0f));
        ModelRenderer headChild_6 = new ModelRenderer((ModelBase)this);
        headChild_6.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.setRotationAngle(headChild_6, -0.3491f, 0.0f, 0.6982f);
        this.Head.func_78792_a(headChild_6);
        ModelRenderer headChild_14 = new ModelRenderer((ModelBase)this);
        headChild_14.func_78793_a(-6.0f, -7.5f, -0.9f);
        this.setRotationAngle(headChild_14, -0.3491f, 0.0f, 0.4363f);
        this.Head.func_78792_a(headChild_14);
        ModelRenderer headChild_3 = new ModelRenderer((ModelBase)this);
        headChild_3.func_78793_a(2.0f, -7.0f, 0.0f);
        this.setRotationAngle(headChild_3, 0.0f, 0.0873f, 0.7854f);
        this.Head.func_78792_a(headChild_3);
        ModelRenderer headChild = new ModelRenderer((ModelBase)this);
        headChild.func_78793_a(-1.0f, -8.7f, 0.0f);
        this.setRotationAngle(headChild, -0.0873f, 0.0f, -0.6108f);
        this.Head.func_78792_a(headChild);
        ModelRenderer headChild_5 = new ModelRenderer((ModelBase)this);
        headChild_5.func_78793_a(-5.0f, -7.0f, 4.0f);
        this.setRotationAngle(headChild_5, -0.3491f, 0.0f, -0.7854f);
        this.Head.func_78792_a(headChild_5);
        ModelRenderer headChild_7 = new ModelRenderer((ModelBase)this);
        headChild_7.func_78793_a(-5.0f, -7.0f, 2.0f);
        this.setRotationAngle(headChild_7, 2.7925f, 0.0873f, -1.309f);
        this.Head.func_78792_a(headChild_7);
        ModelRenderer headChild_4 = new ModelRenderer((ModelBase)this);
        headChild_4.func_78793_a(-5.0f, -6.0f, -2.0f);
        this.setRotationAngle(headChild_4, 2.7925f, 0.0873f, -1.1345f);
        this.Head.func_78792_a(headChild_4);
        ModelRenderer headChild_13 = new ModelRenderer((ModelBase)this);
        headChild_13.func_78793_a(2.0f, -5.0f, -1.0f);
        this.setRotationAngle(headChild_13, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(headChild_13);
        ModelRenderer headChild_12 = new ModelRenderer((ModelBase)this);
        headChild_12.func_78793_a(0.0f, -5.0f, -2.0f);
        this.setRotationAngle(headChild_12, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(headChild_12);
        ModelRenderer headChild_2 = new ModelRenderer((ModelBase)this);
        headChild_2.func_78793_a(1.0f, -8.0f, 0.0f);
        this.setRotationAngle(headChild_2, 0.0f, 0.0f, 0.6981f);
        this.Head.func_78792_a(headChild_2);
        ModelRenderer headChild_9 = new ModelRenderer((ModelBase)this);
        headChild_9.func_78793_a(0.0f, -2.0f, 6.0f);
        this.setRotationAngle(headChild_9, -0.5236f, 0.3491f, 0.0f);
        this.Head.func_78792_a(headChild_9);
        ModelRenderer headChild_10 = new ModelRenderer((ModelBase)this);
        headChild_10.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.setRotationAngle(headChild_10, -0.5236f, -0.2618f, 0.0f);
        this.Head.func_78792_a(headChild_10);
        ModelRenderer headChild_1 = new ModelRenderer((ModelBase)this);
        headChild_1.func_78793_a(-3.0f, -10.0f, 0.0f);
        this.setRotationAngle(headChild_1, 0.0f, 0.0f, -0.6981f);
        this.Head.func_78792_a(headChild_1);
        ModelRenderer headChild_8 = new ModelRenderer((ModelBase)this);
        headChild_8.func_78793_a(3.0f, -8.0f, 2.0f);
        this.setRotationAngle(headChild_8, 2.7925f, 0.0873f, 0.9599f);
        this.Head.func_78792_a(headChild_8);
        ModelRenderer headChild_11 = new ModelRenderer((ModelBase)this);
        headChild_11.func_78793_a(-4.0f, -8.0f, -2.0f);
        this.setRotationAngle(headChild_11, -0.3491f, 0.0f, 0.3491f);
        this.Head.func_78792_a(headChild_11);
        ModelRenderer headChild_15 = new ModelRenderer((ModelBase)this);
        headChild_15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(headChild_15, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(headChild_15);
        headChild_15.field_78804_l.add(new ModelBox(headChild_15, 12, 26, 4.0f, -6.0f, -1.0f, 0, 4, 6, 0.0f));
        ModelRenderer headChild_16 = new ModelRenderer((ModelBase)this);
        headChild_16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(headChild_16, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(headChild_16);
        headChild_16.field_78804_l.add(new ModelBox(headChild_16, 24, 14, -4.0f, -6.0f, -1.0f, 0, 4, 6, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        ModelRenderer cape = new ModelRenderer((ModelBase)this);
        cape.func_78793_a(0.0f, -1.0f, 0.0f);
        this.setRotationAngle(cape, 0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(cape);
        cape.field_78804_l.add(new ModelBox(cape, 24, 24, -4.2f, -5.0f, 3.0f, 8, 23, 0, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(4.8f, -3.4f, -0.3f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.Body.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 26, 10, -1.0f, -3.0f, -3.0f, 7, 4, 6, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 24, 4, 4.2745f, -5.4858f, -2.0f, 1, 3, 1, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 0, 16, 1.2895f, -5.7853f, 0.0f, 1, 3, 1, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(-5.2f, -3.4f, -0.4f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.Body.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 32, 0, -6.0f, -3.0f, -3.0f, 7, 4, 6, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 4, 0, -5.2795f, -5.5857f, -2.0f, 1, 3, 1, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 4, 4, -2.2945f, -5.8852f, 0.0f, 1, 3, 1, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.0873f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 40, -3.9128f, -1.0038f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.0873f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 12, 44, 7.9128f, -1.0038f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 32, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 32, 0, -2.5f, 0.4f, -2.4f, 4, 5, 5, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bone, 0.0f, 0.0f, 0.0873f);
        this.RLeg.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 24, 2, -3.091f, 8.9166f, 0.0f, 2, 1, 1, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 24, 0, -3.2653f, 6.9243f, 0.0f, 2, 1, 1, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 40, 20, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 32, 0, 2.5f, 0.4f, -2.4f, 4, 5, 5, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(2.0f, 12.0f, 0.0f);
        this.setRotationAngle(bone2, 0.0f, 0.0f, -0.0873f);
        this.LLeg.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 20, 18, 4.0f, -3.0f, 0.0f, 2, 1, 1, 0.0f));
        ModelRenderer bone3 = new ModelRenderer((ModelBase)this);
        bone3.func_78793_a(2.0f, 10.0f, 0.0f);
        this.setRotationAngle(bone3, 0.0f, 0.0f, -0.0873f);
        this.LLeg.func_78792_a(bone3);
        bone3.field_78804_l.add(new ModelBox(bone3, 20, 16, 4.0f, -3.0f, 0.0f, 2, 1, 1, 0.0f));
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

