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

public class ModelGomahThirdEye
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer Hat;
    private final ModelRenderer HatHornRight;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    private final ModelRenderer cube_r3;
    private final ModelRenderer HatHornLeft;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer Ears;
    private final ModelRenderer EarRight_r1;
    private final ModelRenderer EarLeft_r1;
    private final ModelRenderer Body;
    private final ModelRenderer bipedBody;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftLeg;
    private float scale = 1.0f;

    public ModelGomahThirdEye(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, -10.0f, 0.0f);
        this.bipedHead.field_78804_l.add(new ModelBox(this.bipedHead, 0, 0, -5.0f, -8.0f, -4.0f, 10, 8, 8, 0.0f));
        this.Hat = new ModelRenderer((ModelBase)this);
        this.Hat.func_78793_a(0.0f, -7.0f, 0.0f);
        this.bipedHead.func_78792_a(this.Hat);
        this.HatHornRight = new ModelRenderer((ModelBase)this);
        this.HatHornRight.func_78793_a(0.0f, 1.0f, 0.0f);
        this.Hat.func_78792_a(this.HatHornRight);
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(5.95f, -4.5f, 0.0f);
        this.HatHornRight.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 0.0f, 1.0472f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 50, 0, -1.0f, -2.0f, -2.0f, 2, 2, 4, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(5.0f, -3.05f, 0.0f);
        this.HatHornRight.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.0f, 0.7854f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 44, 0, -1.5f, -2.0f, -3.0f, 3, 2, 6, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(3.0f, 0.5f, 0.0f);
        this.HatHornRight.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.0f, 0.0f, 0.5236f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 38, 0, -2.0f, -4.5f, -4.0f, 4, 4, 8, -0.025f));
        this.HatHornLeft = new ModelRenderer((ModelBase)this);
        this.HatHornLeft.func_78793_a(-8.0f, 1.0f, 0.0f);
        this.Hat.func_78792_a(this.HatHornLeft);
        this.cube_r4 = new ModelRenderer((ModelBase)this);
        this.cube_r4.func_78793_a(1.9f, -4.4f, 0.0f);
        this.HatHornLeft.func_78792_a(this.cube_r4);
        this.setRotationAngle(this.cube_r4, 0.0f, 0.0f, -1.0472f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 50, 0, -1.0f, -2.0f, -2.0f, 2, 2, 4, 0.0f));
        this.cube_r5 = new ModelRenderer((ModelBase)this);
        this.cube_r5.func_78793_a(3.0f, -3.05f, 0.0f);
        this.HatHornLeft.func_78792_a(this.cube_r5);
        this.setRotationAngle(this.cube_r5, 0.0f, 0.0f, -0.7854f);
        this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 44, 0, -1.5f, -2.0f, -3.0f, 3, 2, 6, 0.0f));
        this.cube_r6 = new ModelRenderer((ModelBase)this);
        this.cube_r6.func_78793_a(5.0f, 0.5f, 0.0f);
        this.HatHornLeft.func_78792_a(this.cube_r6);
        this.setRotationAngle(this.cube_r6, 0.0f, 0.0f, -0.5236f);
        this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 38, 0, -2.0f, -4.5f, -4.0f, 4, 4, 8, -0.025f));
        this.Ears = new ModelRenderer((ModelBase)this);
        this.Ears.func_78793_a(0.0f, -7.0f, 0.0f);
        this.bipedHead.func_78792_a(this.Ears);
        this.EarRight_r1 = new ModelRenderer((ModelBase)this);
        this.EarRight_r1.func_78793_a(6.25f, 2.5f, -3.0f);
        this.EarRight_r1.field_78809_i = true;
        this.Ears.func_78792_a(this.EarRight_r1);
        this.setRotationAngle(this.EarRight_r1, -0.0475f, -0.346f, 0.4883f);
        this.EarRight_r1.field_78804_l.add(new ModelBox(this.EarRight_r1, 30, 1, -2.0f, -2.5f, 0.0f, 4, 5, 0, 0.0f));
        this.EarLeft_r1 = new ModelRenderer((ModelBase)this);
        this.EarLeft_r1.func_78793_a(-6.25f, 2.5f, -3.0f);
        this.Ears.func_78792_a(this.EarLeft_r1);
        this.setRotationAngle(this.EarLeft_r1, -0.0475f, 0.346f, -0.4883f);
        this.EarLeft_r1.field_78804_l.add(new ModelBox(this.EarLeft_r1, 30, 1, -2.0f, -2.5f, 0.0f, 4, 5, 0, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, -15.0f, 0.0f);
        this.Body.func_78792_a(this.bipedBody);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 17, -7.0f, -19.0f, -3.0f, 14, 8, 7, 0.0f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 34, -5.5f, -11.0f, -2.5f, 11, 6, 6, 0.0f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 48, -6.0f, -5.0f, -2.5f, 12, 5, 6, 0.0f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 35, 33, -6.0f, -17.25f, -4.0f, 12, 6, 1, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-8.0f, -31.0f, 0.5f);
        this.Body.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 64, 18, -4.5f, 3.0f, -2.5f, 5, 4, 5, 0.0f));
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 63, 28, -4.5f, 7.0f, -2.5f, 5, 9, 5, 0.0f));
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 63, 5, -5.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(8.0f, -31.0f, 0.5f);
        this.bipedLeftArm.field_78809_i = true;
        this.Body.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 63, 5, -1.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f));
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 64, 18, -0.5f, 3.0f, -2.5f, 5, 4, 5, 0.0f));
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 63, 28, -0.5f, 7.0f, -2.5f, 5, 9, 5, 0.0f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-3.0f, -14.5f, 0.5f);
        this.Body.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 41, 42, -3.0f, -0.5f, -3.0f, 6, 15, 6, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(3.0f, -14.5f, 0.5f);
        this.bipedLeftLeg.field_78809_i = true;
        this.Body.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 41, 42, -3.0f, -0.5f, -3.0f, 6, 15, 6, 0.0f));
    }

    public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.bipedHead.func_78785_a(scaleFactor);
        this.Body.func_78785_a(scaleFactor);
        GL11.glPopMatrix();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
        this.bipedHead.field_78796_g = netHeadYaw / 57.295776f;
        this.bipedHead.field_78795_f = headPitch / 57.295776f;
        this.bipedRightArm.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f + (float)Math.PI)) * 2.0f * limbSwingAmount * 0.5f;
        this.bipedLeftArm.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f)) * 2.0f * limbSwingAmount * 0.5f;
        this.bipedRightLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f)) * 1.4f * limbSwingAmount;
        this.bipedLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f + (float)Math.PI)) * 1.4f * limbSwingAmount;
        this.bipedRightArm.field_78808_h = 0.0f;
        this.bipedLeftArm.field_78808_h = 0.0f;
        this.bipedRightLeg.field_78796_g = 0.0f;
        this.bipedLeftLeg.field_78796_g = 0.0f;
        this.bipedRightArm.field_78796_g = 0.0f;
        float animProgress = this.field_78095_p;
        this.bipedBody.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)animProgress) * (float)Math.PI * 2.0f)) * 0.2f;
        float animCurve = 1.0f - animProgress;
        animCurve *= animCurve;
        animCurve *= animCurve;
        animCurve = 1.0f - animCurve;
        float sinCurve = MathHelper.func_76126_a((float)(animCurve * (float)Math.PI));
        float headOffset = MathHelper.func_76126_a((float)(animProgress * (float)Math.PI)) * -(this.bipedHead.field_78795_f - 0.7f) * 0.75f;
        this.bipedRightArm.field_78795_f -= sinCurve * 1.2f + headOffset;
        this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0f;
        this.bipedRightArm.field_78808_h = MathHelper.func_76126_a((float)(animProgress * 2.1415927f)) * -0.4f;
    }
}

