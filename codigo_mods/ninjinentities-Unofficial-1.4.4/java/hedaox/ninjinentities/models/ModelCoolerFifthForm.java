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

public class ModelCoolerFifthForm
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer ftailS1;
    private final ModelRenderer ftailS2;
    private final ModelRenderer ftailS3;
    private final ModelRenderer ftailS4;
    private final ModelRenderer ftailS5;
    private final ModelRenderer ftailS6;
    private final ModelRenderer FroB;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelCoolerFifthForm(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 24, 24, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer headChild_3 = new ModelRenderer((ModelBase)this);
        headChild_3.func_78793_a(2.0f, -7.0f, 0.0f);
        this.Head.func_78792_a(headChild_3);
        this.setRotationAngle(headChild_3, 0.0f, 0.0873f, 0.2618f);
        ModelRenderer headChild_5 = new ModelRenderer((ModelBase)this);
        headChild_5.func_78793_a(-5.0f, -7.0f, 4.0f);
        this.Head.func_78792_a(headChild_5);
        this.setRotationAngle(headChild_5, -0.3491f, 0.0f, -0.4363f);
        ModelRenderer headChild_9 = new ModelRenderer((ModelBase)this);
        headChild_9.func_78793_a(0.0f, -2.0f, 6.0f);
        this.Head.func_78792_a(headChild_9);
        this.setRotationAngle(headChild_9, -0.5236f, 0.3491f, 0.0f);
        ModelRenderer headChild_8 = new ModelRenderer((ModelBase)this);
        headChild_8.func_78793_a(3.0f, -8.0f, 2.0f);
        this.Head.func_78792_a(headChild_8);
        this.setRotationAngle(headChild_8, 2.7925f, 0.0873f, 0.5236f);
        ModelRenderer headChild = new ModelRenderer((ModelBase)this);
        headChild.func_78793_a(-1.0f, -9.0f, 0.0f);
        this.Head.func_78792_a(headChild);
        this.setRotationAngle(headChild, -0.0873f, 0.0f, -0.1745f);
        ModelRenderer headChild_1 = new ModelRenderer((ModelBase)this);
        headChild_1.func_78793_a(-3.0f, -10.0f, 0.0f);
        this.Head.func_78792_a(headChild_1);
        this.setRotationAngle(headChild_1, 0.0f, 0.0f, 0.2618f);
        ModelRenderer headChild_4 = new ModelRenderer((ModelBase)this);
        headChild_4.func_78793_a(-5.0f, -6.0f, -2.0f);
        this.Head.func_78792_a(headChild_4);
        this.setRotationAngle(headChild_4, 2.7925f, 0.0873f, -0.6109f);
        ModelRenderer headChild_6 = new ModelRenderer((ModelBase)this);
        headChild_6.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.Head.func_78792_a(headChild_6);
        this.setRotationAngle(headChild_6, -0.3491f, 0.0f, 0.5236f);
        ModelRenderer headChild_2 = new ModelRenderer((ModelBase)this);
        headChild_2.func_78793_a(1.0f, -8.0f, 0.0f);
        this.Head.func_78792_a(headChild_2);
        this.setRotationAngle(headChild_2, 0.0f, 0.0f, -0.0873f);
        ModelRenderer headChild_7 = new ModelRenderer((ModelBase)this);
        headChild_7.func_78793_a(-5.0f, -7.0f, 2.0f);
        this.Head.func_78792_a(headChild_7);
        this.setRotationAngle(headChild_7, 2.7925f, 0.0873f, -0.4363f);
        ModelRenderer headChild_10 = new ModelRenderer((ModelBase)this);
        headChild_10.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.Head.func_78792_a(headChild_10);
        this.setRotationAngle(headChild_10, -0.5236f, -0.2618f, 0.0f);
        ModelRenderer headChild_11 = new ModelRenderer((ModelBase)this);
        headChild_11.func_78793_a(-3.0f, -7.0f, -1.0f);
        this.Head.func_78792_a(headChild_11);
        this.setRotationAngle(headChild_11, -0.2618f, 0.0f, 0.0f);
        ModelRenderer crown1 = new ModelRenderer((ModelBase)this);
        crown1.func_78793_a(0.5f, -0.9f, -4.2f);
        this.Head.func_78792_a(crown1);
        this.setRotationAngle(crown1, -0.6981f, 0.0f, 0.0f);
        crown1.field_78804_l.add(new ModelBox(crown1, 20, 16, -4.0f, -8.0f, -4.0f, 7, 4, 1, 0.5f));
        ModelRenderer crown2 = new ModelRenderer((ModelBase)this);
        crown2.func_78793_a(-3.0f, -5.4375f, -4.5f);
        this.Head.func_78792_a(crown2);
        this.setRotationAngle(crown2, 0.0f, 0.0f, -1.3273f);
        crown2.field_78804_l.add(new ModelBox(crown2, 0, 6, -0.3787f, -2.5301f, -0.5f, 1, 5, 1, 0.5f));
        ModelRenderer crown3 = new ModelRenderer((ModelBase)this);
        crown3.field_78809_i = true;
        crown3.func_78793_a(3.0f, -5.4375f, -4.5f);
        this.Head.func_78792_a(crown3);
        this.setRotationAngle(crown3, 0.0f, 0.0f, 1.3273f);
        crown3.field_78804_l.add(new ModelBox(crown3, 0, 6, -0.6213f, -2.5301f, -0.5f, 1, 5, 1, 0.5f));
        ModelRenderer crown4 = new ModelRenderer((ModelBase)this);
        crown4.func_78793_a(-6.0625f, -6.25f, -3.7375f);
        this.Head.func_78792_a(crown4);
        this.setRotationAngle(crown4, -0.8727f, 0.0f, -1.309f);
        crown4.field_78804_l.add(new ModelBox(crown4, 0, 22, -0.4396f, -1.0104f, -0.5124f, 1, 2, 1, 0.5f));
        ModelRenderer crown5 = new ModelRenderer((ModelBase)this);
        crown5.field_78809_i = true;
        crown5.func_78793_a(6.0625f, -6.25f, -3.7375f);
        this.Head.func_78792_a(crown5);
        this.setRotationAngle(crown5, -0.8727f, 0.0f, 1.309f);
        crown5.field_78804_l.add(new ModelBox(crown5, 0, 22, -0.5604f, -1.0104f, -0.5124f, 1, 2, 1, 0.5f));
        ModelRenderer crown6 = new ModelRenderer((ModelBase)this);
        crown6.func_78793_a(1.0f, -4.8f, -4.0f);
        this.Head.func_78792_a(crown6);
        this.setRotationAngle(crown6, -1.2217f, 0.0f, 0.0f);
        crown6.field_78804_l.add(new ModelBox(crown6, 0, 22, -4.0f, -8.0f, -4.0f, 1, 4, 1, 0.5f));
        ModelRenderer crown7 = new ModelRenderer((ModelBase)this);
        crown7.func_78793_a(6.0f, -4.8f, -4.0f);
        this.Head.func_78792_a(crown7);
        this.setRotationAngle(crown7, -1.2217f, 0.0f, 0.0f);
        crown7.field_78804_l.add(new ModelBox(crown7, 0, 22, -4.0f, -8.0f, -4.0f, 1, 4, 1, 0.5f));
        ModelRenderer crown8 = new ModelRenderer((ModelBase)this);
        crown8.func_78793_a(0.0625f, -5.4875f, -4.375f);
        this.Head.func_78792_a(crown8);
        this.setRotationAngle(crown8, 0.0f, 0.0f, 1.5708f);
        crown8.field_78804_l.add(new ModelBox(crown8, 0, 22, -0.5f, -2.0f, -0.5625f, 1, 4, 1, 0.5f));
        ModelRenderer crowngem = new ModelRenderer((ModelBase)this);
        crowngem.func_78793_a(3.5f, -0.9f, -4.7f);
        this.Head.func_78792_a(crowngem);
        this.setRotationAngle(crowngem, -0.6894f, 0.0f, 0.0f);
        crowngem.field_78804_l.add(new ModelBox(crowngem, 6, 0, -4.5f, -7.2085f, -4.0619f, 2, 3, 1, 0.3f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(-4.0f, -3.0f, -1.0f);
        this.Head.func_78792_a(earR);
        earR.field_78804_l.add(new ModelBox(earR, 49, 0, -1.0f, -2.0f, -1.0f, 1, 3, 2, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.field_78809_i = true;
        earL.func_78793_a(4.0f, -3.0f, -1.0f);
        this.Head.func_78792_a(earL);
        earL.field_78804_l.add(new ModelBox(earL, 49, 0, 0.0f, -2.0f, -1.0f, 1, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 32, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        ModelRenderer breasts = new ModelRenderer((ModelBase)this);
        breasts.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(breasts);
        this.setRotationAngle(breasts, -0.1745f, 0.0f, 0.0f);
        breasts.field_78804_l.add(new ModelBox(breasts, 36, 16, -3.5f, 1.0f, -1.8f, 7, 4, 1, 0.0f));
        ModelRenderer shoulderGuard1 = new ModelRenderer((ModelBase)this);
        shoulderGuard1.func_78793_a(9.8f, -0.3f, -0.3f);
        this.Body.func_78792_a(shoulderGuard1);
        this.setRotationAngle(shoulderGuard1, 0.0f, 0.0f, 0.6981f);
        shoulderGuard1.field_78804_l.add(new ModelBox(shoulderGuard1, 0, 16, -4.6428f, -0.7661f, -3.0f, 2, 5, 1, 0.0f));
        ModelRenderer shoulderGuard2 = new ModelRenderer((ModelBase)this);
        shoulderGuard2.func_78793_a(9.8f, -0.3f, -0.3f);
        this.Body.func_78792_a(shoulderGuard2);
        this.setRotationAngle(shoulderGuard2, 0.0f, 0.0f, 0.6981f);
        shoulderGuard2.field_78804_l.add(new ModelBox(shoulderGuard2, 48, 7, -4.6428f, -0.7661f, -2.0f, 2, 1, 5, 0.0f));
        ModelRenderer shoulderGuard3 = new ModelRenderer((ModelBase)this);
        shoulderGuard3.func_78793_a(9.8f, -0.3f, 4.3f);
        this.Body.func_78792_a(shoulderGuard3);
        this.setRotationAngle(shoulderGuard3, 0.0f, 0.0f, 0.6981f);
        shoulderGuard3.field_78804_l.add(new ModelBox(shoulderGuard3, 0, 16, -4.6428f, -0.7661f, -2.0f, 2, 5, 1, 0.0f));
        ModelRenderer shoulderGuard4 = new ModelRenderer((ModelBase)this);
        shoulderGuard4.field_78809_i = true;
        shoulderGuard4.func_78793_a(-9.8f, -0.3f, -0.3f);
        this.Body.func_78792_a(shoulderGuard4);
        this.setRotationAngle(shoulderGuard4, 0.0f, 0.0f, -0.6981f);
        shoulderGuard4.field_78804_l.add(new ModelBox(shoulderGuard4, 0, 16, 2.6428f, -0.7661f, -3.0f, 2, 5, 1, 0.0f));
        ModelRenderer shoulderGuard5 = new ModelRenderer((ModelBase)this);
        shoulderGuard5.field_78809_i = true;
        shoulderGuard5.func_78793_a(-9.8f, -0.3f, -0.3f);
        this.Body.func_78792_a(shoulderGuard5);
        this.setRotationAngle(shoulderGuard5, 0.0f, 0.0f, -0.6981f);
        shoulderGuard5.field_78804_l.add(new ModelBox(shoulderGuard5, 48, 7, 2.6428f, -0.7661f, -2.0f, 2, 1, 5, 0.0f));
        ModelRenderer shoulderGuard6 = new ModelRenderer((ModelBase)this);
        shoulderGuard6.field_78809_i = true;
        shoulderGuard6.func_78793_a(-9.8f, -0.3f, 4.3f);
        this.Body.func_78792_a(shoulderGuard6);
        this.setRotationAngle(shoulderGuard6, 0.0f, 0.0f, -0.6981f);
        shoulderGuard6.field_78804_l.add(new ModelBox(shoulderGuard6, 0, 16, 2.6428f, -0.7661f, -2.0f, 2, 5, 1, 0.0f));
        ModelRenderer backspike2 = new ModelRenderer((ModelBase)this);
        backspike2.func_78793_a(1.3f, 8.0f, -3.1f);
        this.Body.func_78792_a(backspike2);
        this.setRotationAngle(backspike2, -1.309f, 0.0f, 0.0f);
        backspike2.field_78804_l.add(new ModelBox(backspike2, 0, 22, -4.0f, -9.1f, -4.0f, 1, 4, 1, 0.5f));
        ModelRenderer backspike1 = new ModelRenderer((ModelBase)this);
        backspike1.func_78793_a(5.5f, 8.0f, -3.1f);
        this.Body.func_78792_a(backspike1);
        this.setRotationAngle(backspike1, -1.309f, 0.0017f, 0.0f);
        backspike1.field_78804_l.add(new ModelBox(backspike1, 0, 22, -4.0f, -9.1f, -4.0f, 1, 4, 1, 0.5f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 0, 48, -3.0031f, -2.079f, -2.0018f, 4, 12, 4, 0.0f));
        ModelRenderer wristspike2 = new ModelRenderer((ModelBase)this);
        wristspike2.func_78793_a(1.0f, 12.0f, -3.1f);
        this.RArm.func_78792_a(wristspike2);
        this.setRotationAngle(wristspike2, -1.1345f, 0.0017f, 0.0f);
        wristspike2.field_78804_l.add(new ModelBox(wristspike2, 0, 22, -2.0031f, -9.1333f, -4.0717f, 1, 4, 1, 0.5f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.field_78809_i = true;
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 48, -0.9938f, -2.158f, -1.9964f, 4, 12, 4, 0.0f));
        ModelRenderer wristspike1 = new ModelRenderer((ModelBase)this);
        wristspike1.func_78793_a(6.3f, 12.0f, -3.1f);
        this.LArm.func_78792_a(wristspike1);
        this.setRotationAngle(wristspike1, -1.1345f, 0.0017f, 0.0f);
        wristspike1.field_78804_l.add(new ModelBox(wristspike1, 0, 22, -5.9969f, -9.1335f, -4.0715f, 1, 4, 1, 0.5f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.field_78809_i = true;
        this.RLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 40, 40, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer legspike2 = new ModelRenderer((ModelBase)this);
        legspike2.func_78793_a(3.0f, 14.7f, -3.5f);
        this.RLeg.func_78792_a(legspike2);
        this.setRotationAngle(legspike2, -1.1345f, 0.0192f, 0.0f);
        legspike2.field_78804_l.add(new ModelBox(legspike2, 0, 22, -4.0f, -9.1f, -4.0f, 1, 4, 1, 0.5f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 40, 40, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer legspike1 = new ModelRenderer((ModelBase)this);
        legspike1.func_78793_a(4.0f, 14.7f, -3.5f);
        this.LLeg.func_78792_a(legspike1);
        this.setRotationAngle(legspike1, -1.1345f, 0.0017f, 0.0f);
        legspike1.field_78804_l.add(new ModelBox(legspike1, 0, 22, -4.0f, -9.1f, -4.0f, 1, 4, 1, 0.5f));
        this.FroB = new ModelRenderer((ModelBase)this);
        this.FroB.func_78793_a(0.0f, 10.0f, 2.0f);
        this.ftailS1 = new ModelRenderer((ModelBase)this);
        this.ftailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FroB.func_78792_a(this.ftailS1);
        this.ftailS1.field_78804_l.add(new ModelBox(this.ftailS1, 6, 6, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS2 = new ModelRenderer((ModelBase)this);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.ftailS2.field_78804_l.add(new ModelBox(this.ftailS2, 6, 6, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS3 = new ModelRenderer((ModelBase)this);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS3.field_78804_l.add(new ModelBox(this.ftailS3, 6, 6, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS4 = new ModelRenderer((ModelBase)this);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS4.field_78804_l.add(new ModelBox(this.ftailS4, 6, 6, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS5 = new ModelRenderer((ModelBase)this);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS5.field_78804_l.add(new ModelBox(this.ftailS5, 6, 6, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS6 = new ModelRenderer((ModelBase)this);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 26, 6, -2.0f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.renderHairs(0.0625f, "FR", f2);
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

    private void transRot(float f5, ModelRenderer m) {
        GL11.glTranslatef((float)(m.field_78800_c * f5), (float)(m.field_78797_d * f5), (float)(m.field_78798_e * f5));
        if (m.field_78808_h != 0.0f) {
            GL11.glRotatef((float)(m.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (m.field_78796_g != 0.0f) {
            GL11.glRotatef((float)(m.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (m.field_78795_f != 0.0f) {
            GL11.glRotatef((float)(m.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public String renderHairs(float par1, String hair, float par3) {
        if (hair.equals("FR")) {
            GL11.glPushMatrix();
            this.transRot(par1, this.Body);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            this.FroB.func_78785_a(par1);
            float r = MathHelper.func_76126_a((float)(par3 * 0.02f)) * 0.1f;
            float r2 = MathHelper.func_76134_b((float)(par3 * 0.02f)) * 0.1f;
            float r3 = MathHelper.func_76134_b((float)(par3 * 0.14f)) * 0.1f;
            this.ftailS1.field_78796_g = 0.2f;
            this.ftailS1.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.2f - 0.2f + r;
            this.ftailS1.field_78795_f = -0.3f;
            this.ftailS2.field_78796_g = 0.2f;
            this.ftailS2.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
            this.ftailS2.field_78795_f = 0.4f;
            this.ftailS3.field_78796_g = 0.1f;
            this.ftailS3.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.1f - 0.1f + r + r3;
            this.ftailS3.field_78795_f = 0.6f;
            this.ftailS3.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.4f + 0.3f;
            this.ftailS4.field_78796_g = 0.1f;
            this.ftailS4.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.1f + r2;
            this.ftailS4.field_78795_f = 0.3f;
            this.ftailS4.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.1f - 0.2f;
            this.ftailS5.field_78796_g = 0.2f;
            this.ftailS5.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            this.ftailS5.field_78795_f = -0.2f;
            this.ftailS5.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.1f - 0.3f;
            this.ftailS6.field_78796_g = 0.2f;
            this.ftailS6.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.2f + r2 + r3;
            this.ftailS6.field_78795_f = -0.4f;
            this.ftailS6.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.4f - 0.4f;
            GL11.glPopMatrix();
        }
        return "";
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

