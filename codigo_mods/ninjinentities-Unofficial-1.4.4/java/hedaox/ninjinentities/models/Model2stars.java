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

public class Model2stars
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer bone4;
    private final ModelRenderer head_r1;
    private final ModelRenderer bipedBody;
    private final ModelRenderer Body_r1;
    private final ModelRenderer bone6;
    private final ModelRenderer Body_r2;
    private final ModelRenderer Body_r3;
    private final ModelRenderer Body_r4;
    private final ModelRenderer bone5;
    private final ModelRenderer Body_r5;
    private final ModelRenderer Body_r6;
    private final ModelRenderer Body_r7;
    private final ModelRenderer bone3;
    private final ModelRenderer Body_r8;
    private final ModelRenderer Body_r9;
    private final ModelRenderer Body_r10;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer LeftArm2;
    private final ModelRenderer RightArm_r1;
    private final ModelRenderer RightArm_r2;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer RightArm2;
    private final ModelRenderer RightArm_r3;
    private final ModelRenderer RightArm_r4;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bone;
    private final ModelRenderer RightLeg_r1;
    private final ModelRenderer RightLeg_r2;
    private final ModelRenderer RightLeg_r3;
    private final ModelRenderer bipedLeftLeg;
    private final ModelRenderer bone2;
    private final ModelRenderer RightLeg_r4;
    private final ModelRenderer RightLeg_r5;
    private final ModelRenderer RightLeg_r6;
    private float scale = 1.0f;

    public Model2stars(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.scale = _scale;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone4 = new ModelRenderer((ModelBase)this);
        this.bone4.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedHead.func_78792_a(this.bone4);
        this.bone4.field_78804_l.add(new ModelBox(this.bone4, 39, 12, -4.0f, -28.0f, -9.0f, 8, 5, 7, 0.0f));
        this.bone4.field_78804_l.add(new ModelBox(this.bone4, 58, 0, -5.0f, -28.0f, -10.0f, 4, 4, 3, -0.5f));
        this.bone4.field_78804_l.add(new ModelBox(this.bone4, 28, 36, 1.0f, -28.0f, -10.0f, 4, 4, 3, -0.5f));
        this.head_r1 = new ModelRenderer((ModelBase)this);
        this.head_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone4.func_78792_a(this.head_r1);
        this.setRotationAngle(this.head_r1, 0.3054f, 0.0f, 0.0f);
        this.head_r1.field_78804_l.add(new ModelBox(this.head_r1, 35, 0, -4.0f, -24.0f, -3.0f, 8, 3, 7, 0.0f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 0, -6.0f, -2.0f, -6.0f, 12, 8, 11, 0.0f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 36, -5.0f, 6.0f, -4.0f, 10, 5, 8, 0.0f));
        this.Body_r1 = new ModelRenderer((ModelBase)this);
        this.Body_r1.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedBody.func_78792_a(this.Body_r1);
        this.setRotationAngle(this.Body_r1, -0.2618f, 0.0f, 0.0f);
        this.Body_r1.field_78804_l.add(new ModelBox(this.Body_r1, 0, 19, -6.0f, -14.0f, -9.0f, 12, 7, 10, 0.0f));
        this.bone6 = new ModelRenderer((ModelBase)this);
        this.bone6.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bone6);
        this.bone6.field_78804_l.add(new ModelBox(this.bone6, 50, 60, 4.0f, -31.0f, -1.0f, 2, 6, 2, -0.2f));
        this.Body_r2 = new ModelRenderer((ModelBase)this);
        this.Body_r2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone6.func_78792_a(this.Body_r2);
        this.setRotationAngle(this.Body_r2, -1.4835f, 0.0f, 0.0f);
        this.Body_r2.field_78804_l.add(new ModelBox(this.Body_r2, 0, 0, 4.0f, -11.0f, -20.0f, 2, 6, 2, -0.2f));
        this.Body_r3 = new ModelRenderer((ModelBase)this);
        this.Body_r3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone6.func_78792_a(this.Body_r3);
        this.setRotationAngle(this.Body_r3, -1.0908f, 0.0f, 0.0f);
        this.Body_r3.field_78804_l.add(new ModelBox(this.Body_r3, 0, 19, 4.0f, -20.0f, -19.0f, 2, 6, 2, -0.2f));
        this.Body_r4 = new ModelRenderer((ModelBase)this);
        this.Body_r4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone6.func_78792_a(this.Body_r4);
        this.setRotationAngle(this.Body_r4, -0.6545f, 0.0f, 0.0f);
        this.Body_r4.field_78804_l.add(new ModelBox(this.Body_r4, 0, 36, 4.0f, -28.0f, -14.0f, 2, 6, 2, -0.2f));
        this.bone5 = new ModelRenderer((ModelBase)this);
        this.bone5.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bone5);
        this.bone5.field_78804_l.add(new ModelBox(this.bone5, 58, 61, -6.0f, -31.0f, -1.0f, 2, 6, 2, -0.2f));
        this.bone5.field_78804_l.add(new ModelBox(this.bone5, 28, 49, -1.0f, -28.0f, 1.0f, 2, 3, 2, -0.2f));
        this.Body_r5 = new ModelRenderer((ModelBase)this);
        this.Body_r5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone5.func_78792_a(this.Body_r5);
        this.setRotationAngle(this.Body_r5, -1.4835f, 0.0f, 0.0f);
        this.Body_r5.field_78804_l.add(new ModelBox(this.Body_r5, 0, 61, -6.0f, -11.0f, -20.0f, 2, 6, 2, -0.2f));
        this.Body_r6 = new ModelRenderer((ModelBase)this);
        this.Body_r6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone5.func_78792_a(this.Body_r6);
        this.setRotationAngle(this.Body_r6, -1.0908f, 0.0f, 0.0f);
        this.Body_r6.field_78804_l.add(new ModelBox(this.Body_r6, 8, 61, -6.0f, -20.0f, -19.0f, 2, 6, 2, -0.2f));
        this.Body_r7 = new ModelRenderer((ModelBase)this);
        this.Body_r7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone5.func_78792_a(this.Body_r7);
        this.setRotationAngle(this.Body_r7, -0.6545f, 0.0f, 0.0f);
        this.Body_r7.field_78804_l.add(new ModelBox(this.Body_r7, 16, 61, -6.0f, -28.0f, -14.0f, 2, 6, 2, -0.2f));
        this.bone3 = new ModelRenderer((ModelBase)this);
        this.bone3.func_78793_a(0.0f, 23.6f, 0.0f);
        this.bipedBody.func_78792_a(this.bone3);
        this.setRotationAngle(this.bone3, -0.0873f, 0.0f, 0.0f);
        this.Body_r8 = new ModelRenderer((ModelBase)this);
        this.Body_r8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r8);
        this.setRotationAngle(this.Body_r8, -0.2618f, 0.0f, 0.0f);
        this.Body_r8.field_78804_l.add(new ModelBox(this.Body_r8, 44, 24, -2.25f, -8.0f, 4.25f, 5, 4, 7, 0.0f));
        this.Body_r9 = new ModelRenderer((ModelBase)this);
        this.Body_r9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r9);
        this.setRotationAngle(this.Body_r9, -0.7418f, 0.0f, 0.0f);
        this.Body_r9.field_78804_l.add(new ModelBox(this.Body_r9, 36, 36, -4.0f, -10.0f, -5.0f, 8, 5, 7, 0.0f));
        this.Body_r10 = new ModelRenderer((ModelBase)this);
        this.Body_r10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone3.func_78792_a(this.Body_r10);
        this.setRotationAngle(this.Body_r10, -0.0873f, 0.0f, 0.0f);
        this.Body_r10.field_78804_l.add(new ModelBox(this.Body_r10, 30, 48, -1.25f, -5.5f, 11.25f, 3, 3, 6, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-6.0f, 2.0f, -1.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.LeftArm2 = new ModelRenderer((ModelBase)this);
        this.LeftArm2.func_78793_a(1.0f, 0.0f, 0.0f);
        this.bipedRightArm.func_78792_a(this.LeftArm2);
        this.setRotationAngle(this.LeftArm2, 0.0f, 0.0f, 0.1309f);
        this.RightArm_r1 = new ModelRenderer((ModelBase)this);
        this.RightArm_r1.func_78793_a(-3.0f, 4.0f, 1.0f);
        this.RightArm_r1.field_78809_i = true;
        this.LeftArm2.func_78792_a(this.RightArm_r1);
        this.setRotationAngle(this.RightArm_r1, -0.3491f, 0.0f, 0.0f);
        this.RightArm_r1.field_78804_l.add(new ModelBox(this.RightArm_r1, 48, 48, -1.0f, -0.6194f, -1.0866f, 3, 8, 4, -0.25f));
        this.RightArm_r2 = new ModelRenderer((ModelBase)this);
        this.RightArm_r2.func_78793_a(-1.0f, -1.0f, 1.0f);
        this.RightArm_r2.field_78809_i = true;
        this.LeftArm2.func_78792_a(this.RightArm_r2);
        this.setRotationAngle(this.RightArm_r2, 0.3927f, 0.0f, 0.0f);
        this.RightArm_r2.field_78804_l.add(new ModelBox(this.RightArm_r2, 0, 49, -3.0f, -2.0f, -3.0f, 3, 8, 4, 0.0f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(6.0f, 2.0f, -1.0f);
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.RightArm2 = new ModelRenderer((ModelBase)this);
        this.RightArm2.func_78793_a(-1.0f, 0.0f, 0.0f);
        this.bipedLeftArm.func_78792_a(this.RightArm2);
        this.setRotationAngle(this.RightArm2, 0.0f, 0.0f, -0.1309f);
        this.RightArm_r3 = new ModelRenderer((ModelBase)this);
        this.RightArm_r3.func_78793_a(3.0f, 4.0f, 1.0f);
        this.RightArm2.func_78792_a(this.RightArm_r3);
        this.setRotationAngle(this.RightArm_r3, -0.3491f, 0.0f, 0.0f);
        this.RightArm_r3.field_78804_l.add(new ModelBox(this.RightArm_r3, 48, 48, -2.0f, -0.6194f, -1.0866f, 3, 8, 4, -0.25f));
        this.RightArm_r4 = new ModelRenderer((ModelBase)this);
        this.RightArm_r4.func_78793_a(1.0f, -1.0f, 1.0f);
        this.RightArm2.func_78792_a(this.RightArm_r4);
        this.setRotationAngle(this.RightArm_r4, 0.3927f, 0.0f, 0.0f);
        this.RightArm_r4.field_78804_l.add(new ModelBox(this.RightArm_r4, 0, 49, 0.0f, -2.0f, -3.0f, 3, 8, 4, 0.0f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-4.9f, 16.0f, -2.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.setRotationAngle(this.bipedRightLeg, -0.3354f, 0.2823f, 0.0159f);
        this.bone = new ModelRenderer((ModelBase)this);
        this.bone.func_78793_a(1.9f, 7.6187f, 0.8966f);
        this.bipedRightLeg.func_78792_a(this.bone);
        this.setRotationAngle(this.bone, 0.1309f, 0.0f, 0.0f);
        this.RightLeg_r1 = new ModelRenderer((ModelBase)this);
        this.RightLeg_r1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.RightLeg_r1);
        this.setRotationAngle(this.RightLeg_r1, 0.2616f, 0.0113f, -0.0421f);
        this.RightLeg_r1.field_78804_l.add(new ModelBox(this.RightLeg_r1, 58, 56, -3.9f, -0.65f, -2.5f, 3, 1, 4, 0.0f));
        this.RightLeg_r2 = new ModelRenderer((ModelBase)this);
        this.RightLeg_r2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.RightLeg_r2);
        this.setRotationAngle(this.RightLeg_r2, 0.4363f, 0.0f, 0.0f);
        this.RightLeg_r2.field_78804_l.add(new ModelBox(this.RightLeg_r2, 62, 10, -3.4f, -4.0f, -0.5f, 2, 4, 2, 0.0f));
        this.RightLeg_r3 = new ModelRenderer((ModelBase)this);
        this.RightLeg_r3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone.func_78792_a(this.RightLeg_r3);
        this.setRotationAngle(this.RightLeg_r3, -0.3927f, 0.0f, 0.0f);
        this.RightLeg_r3.field_78804_l.add(new ModelBox(this.RightLeg_r3, 59, 35, -3.9f, -7.0f, -5.0f, 3, 5, 3, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(4.9f, 16.0f, -2.0f);
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.setRotationAngle(this.bipedLeftLeg, -0.3354f, -0.2823f, -0.0159f);
        this.bone2 = new ModelRenderer((ModelBase)this);
        this.bone2.func_78793_a(-1.9f, 7.6187f, 0.8966f);
        this.bipedLeftLeg.func_78792_a(this.bone2);
        this.setRotationAngle(this.bone2, 0.1309f, 0.0f, 0.0f);
        this.RightLeg_r4 = new ModelRenderer((ModelBase)this);
        this.RightLeg_r4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone2.func_78792_a(this.RightLeg_r4);
        this.setRotationAngle(this.RightLeg_r4, 0.2616f, -0.0113f, 0.0421f);
        this.RightLeg_r4.field_78804_l.add(new ModelBox(this.RightLeg_r4, 34, 24, 0.9f, -0.65f, -2.5f, 3, 1, 4, 0.0f));
        this.RightLeg_r5 = new ModelRenderer((ModelBase)this);
        this.RightLeg_r5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone2.func_78792_a(this.RightLeg_r5);
        this.setRotationAngle(this.RightLeg_r5, 0.4363f, 0.0f, 0.0f);
        this.RightLeg_r5.field_78804_l.add(new ModelBox(this.RightLeg_r5, 61, 24, 1.4f, -4.0f, -0.5f, 2, 4, 2, 0.0f));
        this.RightLeg_r6 = new ModelRenderer((ModelBase)this);
        this.RightLeg_r6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bone2.func_78792_a(this.RightLeg_r6);
        this.setRotationAngle(this.RightLeg_r6, -0.3927f, 0.0f, 0.0f);
        this.RightLeg_r6.field_78804_l.add(new ModelBox(this.RightLeg_r6, 38, 57, 0.9f, -7.0f, -5.0f, 3, 5, 3, 0.0f));
    }

    public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.bipedHead.func_78785_a(scaleFactor);
        this.bipedBody.func_78785_a(scaleFactor);
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

