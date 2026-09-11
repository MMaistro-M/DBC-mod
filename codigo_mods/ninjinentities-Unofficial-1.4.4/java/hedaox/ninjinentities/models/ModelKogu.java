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

public class ModelKogu
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

    public ModelKogu(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 30, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer earR = new ModelRenderer((ModelBase)this);
        earR.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earR, 0.0f, -0.5236f, 0.0f);
        earR.field_78809_i = true;
        this.Head.func_78792_a(earR);
        earR.field_78804_l.add(new ModelBox(earR, 26, 23, -4.0f, -8.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer earL = new ModelRenderer((ModelBase)this);
        earL.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(earL, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(earL);
        earL.field_78804_l.add(new ModelBox(earL, 26, 23, 4.0f, -8.0f, -1.0f, 0, 6, 6, 0.0f));
        ModelRenderer headBand = new ModelRenderer((ModelBase)this);
        headBand.func_78793_a(-5.0f, -2.0f, -3.0f);
        this.setRotationAngle(headBand, 0.0f, -0.3491f, 0.3491f);
        this.Head.func_78792_a(headBand);
        headBand.field_78804_l.add(new ModelBox(headBand, 0, 0, -0.1406f, -5.4111f, 0.0512f, 0, 5, 2, 0.0f));
        ModelRenderer headBand2 = new ModelRenderer((ModelBase)this);
        headBand2.func_78793_a(-4.0f, -1.0f, -2.0f);
        this.setRotationAngle(headBand2, 0.0873f, -0.6109f, 0.1745f);
        this.Head.func_78792_a(headBand2);
        headBand2.field_78804_l.add(new ModelBox(headBand2, 0, 0, -0.1244f, -5.8508f, 0.1619f, 0, 4, 2, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(-1.1493f, -7.2362f, -2.6078f);
        this.setRotationAngle(hair1, 1.0472f, 0.5236f, -0.5236f);
        this.Head.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 12, 3, -1.8507f, -4.0f, 0.0f, 2, 6, 2, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(-2.1493f, -6.2362f, 0.3922f);
        this.setRotationAngle(hair2, 0.8727f, 1.2217f, 0.0f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 12, 3, -2.8507f, -4.0f, 0.0f, 3, 5, 2, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(-2.1493f, -6.2362f, 2.3922f);
        this.setRotationAngle(hair3, 0.8727f, 1.9199f, 0.0f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 12, 3, -1.4396f, -3.8854f, 0.0962f, 2, 4, 1, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(-0.1493f, -6.2362f, 2.3922f);
        this.setRotationAngle(hair4, 0.8727f, 2.9671f, 0.0f);
        this.Head.func_78792_a(hair4);
        hair4.field_78804_l.add(new ModelBox(hair4, 12, 3, -1.8507f, -4.0f, 0.0f, 3, 4, 1, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(0.8507f, -7.1737f, -3.2953f);
        this.setRotationAngle(hair5, 1.3963f, -0.2618f, 0.0f);
        this.Head.func_78792_a(hair5);
        hair5.field_78804_l.add(new ModelBox(hair5, 12, 3, -1.8507f, -2.0f, 0.0f, 2, 4, 2, 0.0f));
        ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
        hair6.func_78793_a(2.8507f, -7.2362f, -2.6078f);
        this.setRotationAngle(hair6, 1.3963f, -0.7854f, 0.0f);
        this.Head.func_78792_a(hair6);
        hair6.field_78804_l.add(new ModelBox(hair6, 12, 3, -1.8507f, -3.0f, 0.0f, 2, 6, 1, 0.0f));
        ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
        hair7.func_78793_a(-0.1493f, -6.2362f, -0.6078f);
        this.setRotationAngle(hair7, -0.2618f, 0.5236f, -0.6109f);
        this.Head.func_78792_a(hair7);
        hair7.field_78804_l.add(new ModelBox(hair7, 12, 3, -2.8507f, -5.0f, 0.0f, 3, 5, 2, 0.0f));
        ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
        hair8.func_78793_a(2.1493f, -6.2362f, 2.3922f);
        this.setRotationAngle(hair8, 0.8727f, -1.9199f, 0.0f);
        this.Head.func_78792_a(hair8);
        hair8.field_78804_l.add(new ModelBox(hair8, 12, 3, -2.1493f, -4.0f, 0.0f, 2, 4, 1, 0.0f));
        ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
        hair9.func_78793_a(2.1493f, -6.2362f, 0.3922f);
        this.setRotationAngle(hair9, 0.8727f, -1.2217f, 0.0f);
        this.Head.func_78792_a(hair9);
        hair9.field_78804_l.add(new ModelBox(hair9, 12, 3, -2.1493f, -4.0f, 0.0f, 3, 5, 2, 0.0f));
        ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
        hair10.func_78793_a(0.1493f, -6.2362f, -0.6078f);
        this.setRotationAngle(hair10, -0.2618f, -0.5236f, 0.6109f);
        this.Head.func_78792_a(hair10);
        hair10.field_78804_l.add(new ModelBox(hair10, 12, 3, -1.1493f, -5.0f, 0.0f, 2, 5, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 40, 0, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        ModelRenderer bb_main = new ModelRenderer((ModelBase)this);
        bb_main.func_78793_a(-0.2f, 18.6f, 0.2f);
        this.Body.func_78792_a(bb_main);
        bb_main.field_78804_l.add(new ModelBox(bb_main, 0, 75, -4.5f, -23.6f, -2.4f, 9, 10, 5, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 54, 54, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(4.0f, 22.0f, 0.0f);
        this.RArm.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 40, 16, -9.0f, -25.0f, -3.0f, 5, 4, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 56, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(4.0f, 22.0f, 0.0f);
        this.LArm.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 0, 46, 4.0f, -25.0f, -3.0f, 5, 4, 6, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 22, 47, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 38, 47, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer sword = new ModelRenderer((ModelBase)this);
        sword.func_78793_a(-4.2f, 4.6f, -3.8f);
        this.setRotationAngle(sword, -0.4363f, -0.3491f, 0.0f);
        this.Body.func_78792_a(sword);
        ModelRenderer blade = new ModelRenderer((ModelBase)this);
        blade.func_78793_a(0.0f, 1.0f, 0.0f);
        sword.func_78792_a(blade);
        blade.field_78804_l.add(new ModelBox(blade, 33, 26, 0.0f, -1.0f, 0.0f, 0, 1, 18, 0.0f));
        ModelRenderer grip = new ModelRenderer((ModelBase)this);
        grip.func_78793_a(1.0f, 1.0f, -6.0f);
        sword.func_78792_a(grip);
        grip.field_78804_l.add(new ModelBox(grip, 41, 36, -1.5f, -1.0f, -1.0f, 1, 1, 7, 0.0f));
        ModelRenderer guard = new ModelRenderer((ModelBase)this);
        guard.func_78793_a(2.0f, 2.0f, 0.0f);
        sword.func_78792_a(guard);
        guard.field_78804_l.add(new ModelBox(guard, 57, 32, -4.0f, -3.5f, 0.0f, 4, 4, 0, 0.0f));
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

