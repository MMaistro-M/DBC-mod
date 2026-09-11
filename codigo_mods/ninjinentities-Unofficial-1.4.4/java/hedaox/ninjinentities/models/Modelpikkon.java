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

public class Modelpikkon
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer Hat;
    private final ModelRenderer Cloth;
    private final ModelRenderer LeftCloth1;
    private final ModelRenderer LeftCloth2;
    private final ModelRenderer LeftCloth3;
    private final ModelRenderer LeftCloth4;
    private final ModelRenderer RightCloth1;
    private final ModelRenderer RightCloth2;
    private final ModelRenderer RightCloth3;
    private final ModelRenderer RightCloth4;
    private final ModelRenderer bipedBody;
    private final ModelRenderer Outfit;
    private final ModelRenderer Body_r1;
    private final ModelRenderer Body_r2;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer Body_r3;
    private final ModelRenderer bipedLeftLeg;
    private final ModelRenderer Body_r4;
    private float scale = 1.0f;

    public Modelpikkon(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.scale = _scale;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedHead.field_78804_l.add(new ModelBox(this.bipedHead, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Hat = new ModelRenderer((ModelBase)this);
        this.Hat.func_78793_a(0.0f, -7.0f, 4.0f);
        this.bipedHead.func_78792_a(this.Hat);
        this.Hat.field_78804_l.add(new ModelBox(this.Hat, 0, 16, -4.0f, -5.0f, -8.0f, 8, 7, 8, 0.01f));
        this.Cloth = new ModelRenderer((ModelBase)this);
        this.Cloth.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Hat.func_78792_a(this.Cloth);
        this.setRotationAngle(this.Cloth, -0.2182f, 0.0f, 0.0f);
        this.LeftCloth1 = new ModelRenderer((ModelBase)this);
        this.LeftCloth1.func_78793_a(1.0f, 0.0f, 0.0f);
        this.Cloth.func_78792_a(this.LeftCloth1);
        this.setRotationAngle(this.LeftCloth1, 0.3341f, 0.103f, -0.288f);
        this.LeftCloth1.field_78804_l.add(new ModelBox(this.LeftCloth1, 32, 21, -1.0f, 0.0f, 0.0f, 2, 5, 0, 0.0f));
        this.LeftCloth2 = new ModelRenderer((ModelBase)this);
        this.LeftCloth2.func_78793_a(0.0f, 5.0f, 1.0f);
        this.LeftCloth1.func_78792_a(this.LeftCloth2);
        this.setRotationAngle(this.LeftCloth2, 0.3341f, 0.103f, -0.288f);
        this.LeftCloth2.field_78804_l.add(new ModelBox(this.LeftCloth2, 32, 26, -0.851f, -0.6266f, -0.9398f, 2, 5, 0, 0.0f));
        this.LeftCloth3 = new ModelRenderer((ModelBase)this);
        this.LeftCloth3.func_78793_a(0.0f, 4.0f, 0.0f);
        this.LeftCloth2.func_78792_a(this.LeftCloth3);
        this.setRotationAngle(this.LeftCloth3, -0.3084f, 0.2266f, 0.4279f);
        this.LeftCloth3.field_78804_l.add(new ModelBox(this.LeftCloth3, 36, 21, -0.6194f, 0.0653f, -0.6572f, 2, 5, 0, 0.0f));
        this.LeftCloth4 = new ModelRenderer((ModelBase)this);
        this.LeftCloth4.func_78793_a(0.0f, 5.0f, 0.0f);
        this.LeftCloth3.func_78792_a(this.LeftCloth4);
        this.setRotationAngle(this.LeftCloth4, -0.3131f, 0.1516f, 0.1724f);
        this.LeftCloth4.field_78804_l.add(new ModelBox(this.LeftCloth4, 40, 45, -0.6194f, 0.0653f, -0.6572f, 2, 5, 0, 0.0f));
        this.RightCloth1 = new ModelRenderer((ModelBase)this);
        this.RightCloth1.func_78793_a(-1.0f, 0.0f, 0.0f);
        this.Cloth.func_78792_a(this.RightCloth1);
        this.setRotationAngle(this.RightCloth1, 0.3341f, -0.103f, 0.288f);
        this.RightCloth1.field_78804_l.add(new ModelBox(this.RightCloth1, 36, 26, -1.0f, 0.0f, 0.0f, 2, 5, 0, 0.0f));
        this.RightCloth2 = new ModelRenderer((ModelBase)this);
        this.RightCloth2.func_78793_a(0.0f, 5.0f, 1.0f);
        this.RightCloth1.func_78792_a(this.RightCloth2);
        this.setRotationAngle(this.RightCloth2, 0.3341f, -0.103f, 0.288f);
        this.RightCloth2.field_78804_l.add(new ModelBox(this.RightCloth2, 40, 21, -1.149f, -0.6266f, -0.9398f, 2, 5, 0, 0.0f));
        this.RightCloth3 = new ModelRenderer((ModelBase)this);
        this.RightCloth3.func_78793_a(0.0f, 4.0f, 0.0f);
        this.RightCloth2.func_78792_a(this.RightCloth3);
        this.setRotationAngle(this.RightCloth3, -0.3084f, -0.2266f, -0.4279f);
        this.RightCloth3.field_78804_l.add(new ModelBox(this.RightCloth3, 40, 26, -1.3806f, 0.0653f, -0.6572f, 2, 5, 0, 0.0f));
        this.RightCloth4 = new ModelRenderer((ModelBase)this);
        this.RightCloth4.func_78793_a(0.0f, 5.0f, 0.0f);
        this.RightCloth3.func_78792_a(this.RightCloth4);
        this.setRotationAngle(this.RightCloth4, -0.3131f, -0.1516f, -0.1724f);
        this.RightCloth4.field_78804_l.add(new ModelBox(this.RightCloth4, 16, 47, -1.3806f, 0.0653f, -0.6572f, 2, 5, 0, 0.0f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 31, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        this.Outfit = new ModelRenderer((ModelBase)this);
        this.Outfit.func_78793_a(0.0f, 24.0f, 0.0f);
        this.bipedBody.func_78792_a(this.Outfit);
        this.Outfit.field_78804_l.add(new ModelBox(this.Outfit, 32, 69, -4.5f, -23.9f, 1.75f, 9, 8, 1, 0.0f));
        this.Outfit.field_78804_l.add(new ModelBox(this.Outfit, 52, 69, -4.5f, -18.9f, -2.25f, 9, 3, 1, 0.0f));
        this.Outfit.field_78804_l.add(new ModelBox(this.Outfit, 32, 14, -4.0f, -14.0f, -2.25f, 8, 2, 5, 0.02f));
        this.Outfit.field_78804_l.add(new ModelBox(this.Outfit, 60, 32, -4.0f, -16.0f, -2.0f, 8, 2, 4, 0.01f));
        this.Body_r1 = new ModelRenderer((ModelBase)this);
        this.Body_r1.func_78793_a(-2.0f, -16.0f, 0.0f);
        this.Outfit.func_78792_a(this.Body_r1);
        this.setRotationAngle(this.Body_r1, 0.0f, 0.0f, -0.2618f);
        this.Body_r1.field_78804_l.add(new ModelBox(this.Body_r1, 60, 38, -2.5f, -9.0f, -2.25f, 3, 8, 5, 0.02f));
        this.Body_r2 = new ModelRenderer((ModelBase)this);
        this.Body_r2.func_78793_a(2.0f, -16.0f, 0.0f);
        this.Outfit.func_78792_a(this.Body_r2);
        this.setRotationAngle(this.Body_r2, 0.0f, 0.0f, 0.2618f);
        this.Body_r2.field_78804_l.add(new ModelBox(this.Body_r2, 16, 61, -0.5f, -9.0f, -2.25f, 3, 8, 5, 0.02f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 44, 21, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 40, 53, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.25f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 44, 37, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 56, 53, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.25f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 24, 45, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 58, 0, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.25f));
        this.Body_r3 = new ModelRenderer((ModelBase)this);
        this.Body_r3.func_78793_a(1.9f, -0.1f, 0.0f);
        this.bipedRightLeg.func_78792_a(this.Body_r3);
        this.setRotationAngle(this.Body_r3, 0.0f, 0.0f, 0.0873f);
        this.Body_r3.field_78804_l.add(new ModelBox(this.Body_r3, 24, 31, -4.5f, 0.0f, -2.25f, 5, 9, 5, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(1.9f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 0, 47, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 60, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.25f));
        this.Body_r4 = new ModelRenderer((ModelBase)this);
        this.Body_r4.func_78793_a(-1.9f, -0.1f, 0.0f);
        this.bipedLeftLeg.func_78792_a(this.Body_r4);
        this.setRotationAngle(this.Body_r4, 0.0f, 0.0f, -0.0873f);
        this.Body_r4.field_78804_l.add(new ModelBox(this.Body_r4, 32, 0, -0.5f, 0.0f, -2.25f, 5, 9, 5, 0.0f));
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

