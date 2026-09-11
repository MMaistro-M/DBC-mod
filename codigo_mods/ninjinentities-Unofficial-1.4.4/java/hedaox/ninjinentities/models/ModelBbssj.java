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

public class ModelBbssj
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final ModelRenderer hair1;
    private final ModelRenderer hair2;
    private final ModelRenderer hair3;
    private final ModelRenderer hair4;
    private final ModelRenderer hair5;
    private final ModelRenderer hair6;
    private final ModelRenderer hair7;
    private final ModelRenderer hair8;
    private final ModelRenderer hair9;
    private final ModelRenderer hair10;
    private final ModelRenderer hair11;
    private final ModelRenderer hair12;
    private final ModelRenderer hair13;
    private final ModelRenderer hair14;
    private final ModelRenderer hair15;
    private final ModelRenderer belt;
    private final ModelRenderer belt2;
    private final ModelRenderer bone22;
    private final ModelRenderer bone23;
    private final ModelRenderer bone26;
    private final ModelRenderer bone27;
    private final ModelRenderer bone21;
    private final ModelRenderer bone25;
    private final ModelRenderer bone20;
    private final ModelRenderer bone24;
    private final ModelRenderer hj;
    private float scale = 1.0f;

    public ModelBbssj(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.hair1 = new ModelRenderer((ModelBase)this);
        this.hair1.func_78793_a(-1.0f, -9.0f, 0.0f);
        this.setRotationAngle(this.hair1, -0.0873f, 0.0f, -0.1745f);
        this.Head.func_78792_a(this.hair1);
        this.hair1.field_78804_l.add(new ModelBox(this.hair1, 42, 2, -0.5133f, -5.474f, -2.0714f, 4, 8, 4, 0.0f));
        this.hair2 = new ModelRenderer((ModelBase)this);
        this.hair2.func_78793_a(-3.0f, -10.0f, 0.0f);
        this.setRotationAngle(this.hair2, 0.0f, 0.0f, 0.2618f);
        this.Head.func_78792_a(this.hair2);
        this.hair2.field_78804_l.add(new ModelBox(this.hair2, 45, 2, 0.0603f, -2.658f, -3.0f, 3, 6, 3, 0.0f));
        this.hair3 = new ModelRenderer((ModelBase)this);
        this.hair3.func_78793_a(1.0f, -8.0f, 0.0f);
        this.setRotationAngle(this.hair3, 0.0f, 0.0f, -0.0873f);
        this.Head.func_78792_a(this.hair3);
        this.hair3.field_78804_l.add(new ModelBox(this.hair3, 45, 2, 0.0603f, -3.342f, -3.0f, 3, 6, 3, 0.0f));
        this.hair4 = new ModelRenderer((ModelBase)this);
        this.hair4.func_78793_a(2.0f, -7.0f, 0.0f);
        this.setRotationAngle(this.hair4, 0.0f, 0.0873f, 0.2618f);
        this.Head.func_78792_a(this.hair4);
        this.hair4.field_78804_l.add(new ModelBox(this.hair4, 45, 2, 1.0603f, -4.342f, -3.0f, 2, 7, 3, 0.0f));
        this.hair5 = new ModelRenderer((ModelBase)this);
        this.hair5.func_78793_a(-5.0f, -6.0f, -2.0f);
        this.setRotationAngle(this.hair5, 2.7925f, 0.0873f, -0.6109f);
        this.Head.func_78792_a(this.hair5);
        this.hair5.field_78804_l.add(new ModelBox(this.hair5, 45, 2, -0.0028f, -3.971f, -3.2188f, 3, 8, 3, 0.0f));
        this.hair6 = new ModelRenderer((ModelBase)this);
        this.hair6.func_78793_a(-5.0f, -7.0f, 4.0f);
        this.setRotationAngle(this.hair6, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(this.hair6);
        this.hair6.field_78804_l.add(new ModelBox(this.hair6, 45, 2, 1.8099f, -4.1257f, -3.2188f, 3, 7, 3, 0.0f));
        this.hair7 = new ModelRenderer((ModelBase)this);
        this.hair7.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.setRotationAngle(this.hair7, -0.3491f, 0.0f, 0.5236f);
        this.Head.func_78792_a(this.hair7);
        this.hair7.field_78804_l.add(new ModelBox(this.hair7, 45, 2, 1.8099f, -2.4417f, -5.0982f, 3, 6, 5, 0.0f));
        this.hair8 = new ModelRenderer((ModelBase)this);
        this.hair8.func_78793_a(-5.0f, -7.0f, 2.0f);
        this.setRotationAngle(this.hair8, 2.7925f, 0.0873f, -0.4363f);
        this.Head.func_78792_a(this.hair8);
        this.hair8.field_78804_l.add(new ModelBox(this.hair8, 45, 2, -0.0028f, -3.971f, -3.2188f, 3, 6, 3, 0.0f));
        this.hair9 = new ModelRenderer((ModelBase)this);
        this.hair9.func_78793_a(3.0f, -8.0f, 2.0f);
        this.setRotationAngle(this.hair9, 2.7925f, 0.0873f, 0.5236f);
        this.Head.func_78792_a(this.hair9);
        this.hair9.field_78804_l.add(new ModelBox(this.hair9, 45, 2, -0.0028f, -3.971f, -3.2188f, 3, 6, 3, 0.0f));
        this.hair10 = new ModelRenderer((ModelBase)this);
        this.hair10.func_78793_a(0.0f, -2.0f, 6.0f);
        this.setRotationAngle(this.hair10, -0.5236f, 0.3491f, 0.0f);
        this.Head.func_78792_a(this.hair10);
        this.hair10.field_78804_l.add(new ModelBox(this.hair10, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.hair11 = new ModelRenderer((ModelBase)this);
        this.hair11.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.setRotationAngle(this.hair11, -0.5236f, -0.2618f, 0.0f);
        this.Head.func_78792_a(this.hair11);
        this.hair11.field_78804_l.add(new ModelBox(this.hair11, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.hair12 = new ModelRenderer((ModelBase)this);
        this.hair12.func_78793_a(-3.0f, -2.0f, 3.0f);
        this.setRotationAngle(this.hair12, -0.5236f, -0.2618f, 0.0f);
        this.Head.func_78792_a(this.hair12);
        this.hair12.field_78804_l.add(new ModelBox(this.hair12, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.hair13 = new ModelRenderer((ModelBase)this);
        this.hair13.func_78793_a(-2.0f, -3.0f, 2.0f);
        this.setRotationAngle(this.hair13, -0.5236f, -0.2618f, 0.0f);
        this.Head.func_78792_a(this.hair13);
        this.hair13.field_78804_l.add(new ModelBox(this.hair13, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.hair14 = new ModelRenderer((ModelBase)this);
        this.hair14.func_78793_a(-5.0f, -2.0f, 3.0f);
        this.setRotationAngle(this.hair14, -0.5236f, -0.2618f, 0.0f);
        this.Head.func_78792_a(this.hair14);
        this.hair14.field_78804_l.add(new ModelBox(this.hair14, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.hair15 = new ModelRenderer((ModelBase)this);
        this.hair15.func_78793_a(-6.0f, -3.0f, 2.0f);
        this.setRotationAngle(this.hair15, -0.5236f, -0.2618f, 0.0f);
        this.Head.func_78792_a(this.hair15);
        this.hair15.field_78804_l.add(new ModelBox(this.hair15, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.LArm.field_78809_i = true;
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.belt = new ModelRenderer((ModelBase)this);
        this.belt.func_78793_a(-0.8f, -1.6f, -2.2f);
        this.setRotationAngle(this.belt, -0.0698f, 0.0873f, 0.0f);
        this.RLeg.func_78792_a(this.belt);
        this.belt.field_78804_l.add(new ModelBox(this.belt, 56, 20, -1.2857f, -1.145f, -0.1004f, 2, 8, 0, 0.0f));
        this.belt2 = new ModelRenderer((ModelBase)this);
        this.belt2.func_78793_a(-0.8f, -1.6f, -2.2f);
        this.setRotationAngle(this.belt2, -0.0698f, 1.2217f, 0.0f);
        this.RLeg.func_78792_a(this.belt2);
        this.belt2.field_78804_l.add(new ModelBox(this.belt2, 56, 20, -2.2857f, -1.145f, -1.1004f, 2, 8, 0, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.LLeg.field_78809_i = true;
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.hj = new ModelRenderer((ModelBase)this);
        this.hj.func_78793_a(0.0f, -0.5f, 0.0f);
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 26, 6.0f, -0.5f, 2.0f, 1, 3, 1, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 26, 4.0f, -0.5f, -3.0f, 3, 3, 1, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 26, -7.0f, -0.5f, 2.0f, 1, 3, 1, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 26, -5.0f, -0.5f, 2.0f, 10, 5, 2, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 26, -7.0f, -0.5f, -3.0f, 3, 3, 1, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 25, -7.0f, -0.5f, -2.0f, 3, 1, 4, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 25, 4.0f, -0.5f, -2.0f, 3, 1, 4, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 19, -6.0f, -1.0f, -3.5f, 1, 4, 7, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 19, 5.0f, -1.0f, -3.5f, 1, 4, 7, 0.0f));
        this.hj.field_78804_l.add(new ModelBox(this.hj, 79, 28, -5.0f, -2.0f, 4.0f, 10, 3, 1, 0.0f));
        this.bone22 = new ModelRenderer((ModelBase)this);
        this.bone22.func_78793_a(-6.5f, -5.02f, 3.5f);
        this.hj.func_78792_a(this.bone22);
        this.setRotationAngle(this.bone22, -1.0036f, -0.0436f, -0.1745f);
        this.bone22.field_78804_l.add(new ModelBox(this.bone22, 83, 12, -0.28f, -4.48f, -0.9f, 1, 5, 2, 0.0f));
        this.bone23 = new ModelRenderer((ModelBase)this);
        this.bone23.func_78793_a(6.5f, -5.0f, 3.5f);
        this.hj.func_78792_a(this.bone23);
        this.setRotationAngle(this.bone23, -1.0036f, 0.0f, 0.1745f);
        this.bone23.field_78804_l.add(new ModelBox(this.bone23, 83, 12, -0.75f, -4.5f, -0.95f, 1, 5, 2, 0.0f));
        this.bone26 = new ModelRenderer((ModelBase)this);
        this.bone26.func_78793_a(6.5f, -5.0f, 3.5f);
        this.hj.func_78792_a(this.bone26);
        this.setRotationAngle(this.bone26, -1.0036f, 0.0f, 0.1745f);
        this.bone26.field_78804_l.add(new ModelBox(this.bone26, 93, 12, -0.75f, -4.5f, 0.6143f, 1, 4, 1, 0.0f));
        this.bone27 = new ModelRenderer((ModelBase)this);
        this.bone27.func_78793_a(-5.5f, -5.0f, 3.5f);
        this.hj.func_78792_a(this.bone27);
        this.setRotationAngle(this.bone27, -1.0036f, -0.0436f, -0.2182f);
        this.bone27.field_78804_l.add(new ModelBox(this.bone27, 93, 12, -1.2f, -4.55f, 0.5f, 1, 4, 1, 0.0f));
        this.bone21 = new ModelRenderer((ModelBase)this);
        this.bone21.func_78793_a(6.5f, -7.0f, 1.5f);
        this.hj.func_78792_a(this.bone21);
        this.setRotationAngle(this.bone21, -0.6545f, 0.0f, 0.1745f);
        this.bone21.field_78804_l.add(new ModelBox(this.bone21, 99, 11, -0.4f, 0.4635f, 1.7358f, 1, 6, 2, 0.0f));
        this.bone25 = new ModelRenderer((ModelBase)this);
        this.bone25.func_78793_a(6.5f, -7.0f, 2.5f);
        this.hj.func_78792_a(this.bone25);
        this.setRotationAngle(this.bone25, -0.6545f, 0.0f, 0.1745f);
        this.bone25.field_78804_l.add(new ModelBox(this.bone25, 93, 11, -0.4f, 0.4635f, 2.7358f, 1, 6, 1, 0.0f));
        this.bone20 = new ModelRenderer((ModelBase)this);
        this.bone20.func_78793_a(-6.5f, -7.0f, 1.5f);
        this.hj.func_78792_a(this.bone20);
        this.setRotationAngle(this.bone20, -0.6545f, 0.0f, -0.1745f);
        this.bone20.field_78804_l.add(new ModelBox(this.bone20, 97, 11, -0.6f, 0.4635f, 1.7358f, 1, 6, 2, 0.0f));
        this.bone24 = new ModelRenderer((ModelBase)this);
        this.bone24.func_78793_a(-6.5f, -7.0f, 2.5f);
        this.hj.func_78792_a(this.bone24);
        this.setRotationAngle(this.bone24, -0.6545f, 0.0f, -0.1745f);
        this.bone24.field_78804_l.add(new ModelBox(this.bone24, 93, 11, -0.6f, 0.4635f, 2.7358f, 1, 6, 1, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.hj.func_78785_a(f5);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
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

