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

public class ModelKaioshin
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer ear2;
    private final ModelRenderer ear1;
    private final ModelRenderer hair;
    private final ModelRenderer hair1;
    private final ModelRenderer hair2_r1;
    private final ModelRenderer hair1_r1;
    private final ModelRenderer hair2;
    private final ModelRenderer hair1_r2;
    private final ModelRenderer hair2_1;
    private final ModelRenderer hair2_r2;
    private final ModelRenderer hair2_r3;
    private final ModelRenderer hair3;
    private final ModelRenderer hair1_r3;
    private final ModelRenderer hair2_2;
    private final ModelRenderer hair2_r4;
    private final ModelRenderer hair2_r5;
    private final ModelRenderer hair4;
    private final ModelRenderer hair5;
    private final ModelRenderer hair2_r6;
    private final ModelRenderer hair1_r4;
    private final ModelRenderer hair6;
    private final ModelRenderer hair2_r7;
    private final ModelRenderer hair1_r5;
    private final ModelRenderer hair7;
    private final ModelRenderer hair8;
    private final ModelRenderer hair2_r8;
    private final ModelRenderer hair9;
    private final ModelRenderer hair2_r9;
    private final ModelRenderer hair10;
    private final ModelRenderer hair2_r10;
    private final ModelRenderer hair11;
    private final ModelRenderer hair2_r11;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelKaioshin(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.2f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, -0.19f));
        this.ear2 = new ModelRenderer((ModelBase)this);
        this.ear2.func_78793_a(2.8f, -1.5f, -2.0f);
        this.Head.func_78792_a(this.ear2);
        this.ear2.field_78809_i = true;
        this.setRotationAngle(this.ear2, 0.0f, -0.5236f, 0.0f);
        this.ear2.field_78804_l.add(new ModelBox(this.ear2, 25, 3, 0.1f, -5.5f, -0.4f, 5, 5, 0, -0.19f));
        this.ear1 = new ModelRenderer((ModelBase)this);
        this.ear1.func_78793_a(-2.8f, -1.5f, -2.0f);
        this.Head.func_78792_a(this.ear1);
        this.setRotationAngle(this.ear1, 0.0f, 0.5236f, 0.0f);
        this.ear1.field_78804_l.add(new ModelBox(this.ear1, 25, 3, -5.1f, -5.5f, -0.4f, 5, 5, 0, -0.19f));
        this.hair = new ModelRenderer((ModelBase)this);
        this.hair.func_78793_a(0.3f, 0.0f, 0.0f);
        this.Head.func_78792_a(this.hair);
        this.hair1 = new ModelRenderer((ModelBase)this);
        this.hair1.func_78793_a(-0.4f, -8.1f, -2.6f);
        this.hair.func_78792_a(this.hair1);
        this.setRotationAngle(this.hair1, 0.1115f, 0.3033f, -0.0092f);
        this.hair2_r1 = new ModelRenderer((ModelBase)this);
        this.hair2_r1.func_78793_a(-0.3699f, 1.079f, -1.7304f);
        this.hair1.func_78792_a(this.hair2_r1);
        this.setRotationAngle(this.hair2_r1, 0.9516f, 0.2101f, 0.0863f);
        this.hair2_r1.field_78804_l.add(new ModelBox(this.hair2_r1, 45, -1, -0.7301f, -1.379f, -2.8347f, 2, 2, 3, -0.1f));
        this.hair1_r1 = new ModelRenderer((ModelBase)this);
        this.hair1_r1.func_78793_a(0.0f, 1.6f, -1.1f);
        this.hair1.func_78792_a(this.hair1_r1);
        this.setRotationAngle(this.hair1_r1, 0.428f, 0.2101f, 0.0863f);
        this.hair1_r1.field_78804_l.add(new ModelBox(this.hair1_r1, 46, -1, -1.0f, -2.0f, -1.0f, 2, 2, 2, 0.0f));
        this.hair2 = new ModelRenderer((ModelBase)this);
        this.hair2.func_78793_a(-0.3f, -7.7f, -2.8f);
        this.hair.func_78792_a(this.hair2);
        this.setRotationAngle(this.hair2, -0.4008f, 0.73f, 0.3088f);
        this.hair1_r2 = new ModelRenderer((ModelBase)this);
        this.hair1_r2.func_78793_a(-0.3699f, 0.179f, -2.8304f);
        this.hair2.func_78792_a(this.hair1_r2);
        this.setRotationAngle(this.hair1_r2, 0.428f, 0.2101f, 0.0863f);
        this.hair1_r2.field_78804_l.add(new ModelBox(this.hair1_r2, 44, -1, -0.8808f, 0.0039f, -0.9415f, 2, 2, 4, 0.0f));
        this.hair2_1 = new ModelRenderer((ModelBase)this);
        this.hair2_1.func_78793_a(-0.5699f, 0.579f, -3.7304f);
        this.hair2.func_78792_a(this.hair2_1);
        this.setRotationAngle(this.hair2_1, -0.1571f, 0.0f, 0.0f);
        this.hair2_r2 = new ModelRenderer((ModelBase)this);
        this.hair2_r2.func_78793_a(-0.3f, 2.7f, -0.7f);
        this.hair2_1.func_78792_a(this.hair2_r2);
        this.setRotationAngle(this.hair2_r2, 1.545f, 0.2101f, 0.0863f);
        this.hair2_r2.field_78804_l.add(new ModelBox(this.hair2_r2, 45, -1, -0.9628f, -0.4241f, -2.9338f, 2, 2, 3, -0.15f));
        this.hair2_r3 = new ModelRenderer((ModelBase)this);
        this.hair2_r3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hair2_1.func_78792_a(this.hair2_r3);
        this.setRotationAngle(this.hair2_r3, 1.161f, 0.2101f, 0.0863f);
        this.hair2_r3.field_78804_l.add(new ModelBox(this.hair2_r3, 45, -1, -0.8901f, 0.0307f, -2.9311f, 2, 2, 3, -0.05f));
        this.hair3 = new ModelRenderer((ModelBase)this);
        this.hair3.func_78793_a(-0.6f, -7.2f, -2.0f);
        this.hair.func_78792_a(this.hair3);
        this.setRotationAngle(this.hair3, -1.6205f, 0.8413f, -0.1285f);
        this.hair1_r3 = new ModelRenderer((ModelBase)this);
        this.hair1_r3.func_78793_a(-0.3699f, 0.179f, -2.8304f);
        this.hair3.func_78792_a(this.hair1_r3);
        this.setRotationAngle(this.hair1_r3, 0.428f, 0.2101f, 0.0863f);
        this.hair1_r3.field_78804_l.add(new ModelBox(this.hair1_r3, 44, -1, -0.8808f, 0.0039f, -0.9415f, 2, 2, 4, 0.0f));
        this.hair2_2 = new ModelRenderer((ModelBase)this);
        this.hair2_2.func_78793_a(-0.5699f, 0.579f, -3.7304f);
        this.hair3.func_78792_a(this.hair2_2);
        this.setRotationAngle(this.hair2_2, 0.0698f, 0.0f, 0.0f);
        this.hair2_r4 = new ModelRenderer((ModelBase)this);
        this.hair2_r4.func_78793_a(-0.3f, 2.7f, -0.7f);
        this.hair2_2.func_78792_a(this.hair2_r4);
        this.setRotationAngle(this.hair2_r4, 1.545f, 0.2101f, 0.0863f);
        this.hair2_r4.field_78804_l.add(new ModelBox(this.hair2_r4, 45, -1, -0.9628f, -0.4241f, -2.9338f, 2, 2, 3, -0.1f));
        this.hair2_r5 = new ModelRenderer((ModelBase)this);
        this.hair2_r5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hair2_2.func_78792_a(this.hair2_r5);
        this.setRotationAngle(this.hair2_r5, 1.161f, 0.2101f, 0.0863f);
        this.hair2_r5.field_78804_l.add(new ModelBox(this.hair2_r5, 45, -1, -0.8901f, 0.0307f, -2.9311f, 2, 2, 3, -0.05f));
        this.hair4 = new ModelRenderer((ModelBase)this);
        this.hair4.func_78793_a(-3.2f, -0.8f, -2.0f);
        this.hair.func_78792_a(this.hair4);
        this.setRotationAngle(this.hair4, -0.2094f, 0.0f, 0.4014f);
        this.hair5 = new ModelRenderer((ModelBase)this);
        this.hair5.func_78793_a(0.2f, -7.8f, -0.8f);
        this.hair4.func_78792_a(this.hair5);
        this.setRotationAngle(this.hair5, 1.3263f, 0.0309f, 2.1911f);
        this.hair2_r6 = new ModelRenderer((ModelBase)this);
        this.hair2_r6.func_78793_a(-0.3699f, 1.079f, -1.7304f);
        this.hair5.func_78792_a(this.hair2_r6);
        this.setRotationAngle(this.hair2_r6, 0.9226f, 0.044f, -0.1403f);
        this.hair2_r6.field_78804_l.add(new ModelBox(this.hair2_r6, 44, -2, -0.7301f, -1.379f, -3.8347f, 2, 2, 4, -0.01f));
        this.hair1_r4 = new ModelRenderer((ModelBase)this);
        this.hair1_r4.func_78793_a(0.0f, 1.6f, -1.1f);
        this.hair5.func_78792_a(this.hair1_r4);
        this.setRotationAngle(this.hair1_r4, 0.428f, 0.2101f, 0.0863f);
        this.hair1_r4.field_78804_l.add(new ModelBox(this.hair1_r4, 45, -1, -1.0f, -2.0f, -1.0f, 2, 2, 3, 0.0f));
        this.hair6 = new ModelRenderer((ModelBase)this);
        this.hair6.func_78793_a(0.7f, -7.0f, 0.7f);
        this.hair4.func_78792_a(this.hair6);
        this.setRotationAngle(this.hair6, 1.3905f, -0.0414f, 2.5833f);
        this.hair2_r7 = new ModelRenderer((ModelBase)this);
        this.hair2_r7.func_78793_a(-0.3699f, 0.079f, -2.6304f);
        this.hair6.func_78792_a(this.hair2_r7);
        this.setRotationAngle(this.hair2_r7, 1.039f, 0.072f, -0.1591f);
        this.hair2_r7.field_78804_l.add(new ModelBox(this.hair2_r7, 45, 0, -0.8336f, -0.0693f, -5.1247f, 2, 2, 5, -0.01f));
        this.hair1_r5 = new ModelRenderer((ModelBase)this);
        this.hair1_r5.func_78793_a(0.0f, 1.6f, -1.1f);
        this.hair6.func_78792_a(this.hair1_r5);
        this.setRotationAngle(this.hair1_r5, 0.428f, 0.2101f, 0.0863f);
        this.hair1_r5.field_78804_l.add(new ModelBox(this.hair1_r5, 46, -1, -1.0f, -2.0f, -1.0f, 2, 2, 2, 0.0f));
        this.hair7 = new ModelRenderer((ModelBase)this);
        this.hair7.func_78793_a(-1.2f, -7.6f, 1.7f);
        this.hair.func_78792_a(this.hair7);
        this.setRotationAngle(this.hair7, 0.4445f, 0.0984f, 3.0798f);
        this.hair8 = new ModelRenderer((ModelBase)this);
        this.hair8.func_78793_a(-0.9032f, -2.6017f, 0.3049f);
        this.hair7.func_78792_a(this.hair8);
        this.setRotationAngle(this.hair8, 1.3551f, -0.0112f, -0.0511f);
        this.hair2_r8 = new ModelRenderer((ModelBase)this);
        this.hair2_r8.func_78793_a(-0.5699f, 0.879f, -1.7304f);
        this.hair8.func_78792_a(this.hair2_r8);
        this.setRotationAngle(this.hair2_r8, 0.9382f, -0.008f, -0.0363f);
        this.hair2_r8.field_78804_l.add(new ModelBox(this.hair2_r8, 44, -2, -0.7301f, -1.379f, -3.8347f, 2, 2, 4, -0.01f));
        this.hair9 = new ModelRenderer((ModelBase)this);
        this.hair9.func_78793_a(-0.7032f, -3.5017f, 0.9049f);
        this.hair7.func_78792_a(this.hair9);
        this.setRotationAngle(this.hair9, 1.3921f, 0.0f, 0.0f);
        this.hair2_r9 = new ModelRenderer((ModelBase)this);
        this.hair2_r9.func_78793_a(-0.2699f, 0.079f, -2.6304f);
        this.hair9.func_78792_a(this.hair2_r9);
        this.setRotationAngle(this.hair2_r9, 1.0798f, 0.1486f, 0.1199f);
        this.hair2_r9.field_78804_l.add(new ModelBox(this.hair2_r9, 45, -1, -0.8336f, -0.0693f, -3.1247f, 2, 2, 3, -0.01f));
        this.hair10 = new ModelRenderer((ModelBase)this);
        this.hair10.func_78793_a(0.1968f, -6.2017f, 1.1049f);
        this.hair7.func_78792_a(this.hair10);
        this.setRotationAngle(this.hair10, 1.356f, 0.0224f, 0.1023f);
        this.hair2_r10 = new ModelRenderer((ModelBase)this);
        this.hair2_r10.func_78793_a(-0.8699f, 1.079f, -1.7304f);
        this.hair10.func_78792_a(this.hair2_r10);
        this.setRotationAngle(this.hair2_r10, 0.9516f, 0.0f, 0.0863f);
        this.hair2_r10.field_78804_l.add(new ModelBox(this.hair2_r10, 44, -2, -0.7301f, -1.379f, -3.8347f, 2, 2, 4, -0.01f));
        this.hair11 = new ModelRenderer((ModelBase)this);
        this.hair11.func_78793_a(-0.5032f, -6.7017f, 2.3049f);
        this.hair7.func_78792_a(this.hair11);
        this.setRotationAngle(this.hair11, 1.3925f, 0.0f, -0.0687f);
        this.hair2_r11 = new ModelRenderer((ModelBase)this);
        this.hair2_r11.func_78793_a(-0.4699f, 0.079f, -2.6304f);
        this.hair11.func_78792_a(this.hair2_r11);
        this.setRotationAngle(this.hair2_r11, 1.077f, 0.1794f, 0.1032f);
        this.hair2_r11.field_78804_l.add(new ModelBox(this.hair2_r11, 45, -1, -0.8336f, -0.0693f, -3.1247f, 2, 2, 3, -0.01f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(3.8f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 32, 48, 0.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(1.8f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 16, 48, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
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

