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

public class ModelGeneral3
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer FutreGohan;
    private final ModelRenderer FutreGohan12;
    private final ModelRenderer FutreGohan11;
    private final ModelRenderer FutreGohan10;
    private final ModelRenderer FutreGohan9;
    private final ModelRenderer FutreGohan8;
    private final ModelRenderer FutreGohan7;
    private final ModelRenderer FutreGohan6;
    private final ModelRenderer FutreGohan5;
    private final ModelRenderer FutreGohan3;
    private final ModelRenderer FutreGohan4;
    private final ModelRenderer FutreGohan2;
    private final ModelRenderer Strand4_r1;
    private final ModelRenderer FroB;
    private final ModelRenderer ftailS1;
    private final ModelRenderer ftailS2;
    private final ModelRenderer ftailS3;
    private final ModelRenderer ftailS4;
    private final ModelRenderer ftailS5;
    private final ModelRenderer ftailS6;
    private final ModelRenderer bipedBody;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftLeg;
    private float scale = 1.0f;

    public ModelGeneral3(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedHead.field_78804_l.add(new ModelBox(this.bipedHead, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.FutreGohan = new ModelRenderer((ModelBase)this);
        this.FutreGohan.func_78793_a(0.0f, -8.0f, 0.0f);
        this.bipedHead.func_78792_a(this.FutreGohan);
        this.FutreGohan12 = new ModelRenderer((ModelBase)this);
        this.FutreGohan12.func_78793_a(0.0f, 8.0f, 0.0f);
        this.FutreGohan.func_78792_a(this.FutreGohan12);
        this.setRotationAngle(this.FutreGohan12, 0.1745f, 0.0f, -0.4363f);
        this.FutreGohan12.field_78804_l.add(new ModelBox(this.FutreGohan12, 33, 0, -0.4562f, -9.7503f, -0.044f, 4, 4, 4, 0.0f));
        this.FutreGohan11 = new ModelRenderer((ModelBase)this);
        this.FutreGohan11.func_78793_a(3.6f, 2.5f, -1.1f);
        this.FutreGohan.func_78792_a(this.FutreGohan11);
        this.setRotationAngle(this.FutreGohan11, 0.139f, -0.1059f, -0.5746f);
        this.FutreGohan11.field_78804_l.add(new ModelBox(this.FutreGohan11, 33, 9, -7.6042f, -4.6016f, 1.6979f, 4, 3, 3, 0.0f));
        this.FutreGohan10 = new ModelRenderer((ModelBase)this);
        this.FutreGohan10.func_78793_a(0.0f, 8.0f, 0.0f);
        this.FutreGohan.func_78792_a(this.FutreGohan10);
        this.setRotationAngle(this.FutreGohan10, 0.3491f, 0.0f, -0.6109f);
        this.FutreGohan10.field_78804_l.add(new ModelBox(this.FutreGohan10, 35, 6, 1.4915f, -10.6766f, 1.8823f, 3, 3, 3, 0.0f));
        this.FutreGohan9 = new ModelRenderer((ModelBase)this);
        this.FutreGohan9.func_78793_a(0.0f, 8.0f, 0.0f);
        this.FutreGohan.func_78792_a(this.FutreGohan9);
        this.setRotationAngle(this.FutreGohan9, 0.5236f, 0.0f, -0.7854f);
        this.FutreGohan9.field_78804_l.add(new ModelBox(this.FutreGohan9, 33, 8, 3.6243f, -11.1326f, 3.7879f, 2, 3, 2, 0.0f));
        this.FutreGohan8 = new ModelRenderer((ModelBase)this);
        this.FutreGohan8.func_78793_a(3.6f, 2.5f, -1.1f);
        this.FutreGohan.func_78792_a(this.FutreGohan8);
        this.setRotationAngle(this.FutreGohan8, 0.2811f, -0.2098f, -0.6843f);
        this.FutreGohan8.field_78804_l.add(new ModelBox(this.FutreGohan8, 39, 11, -8.3987f, -4.5511f, 3.1329f, 3, 2, 2, 0.0f));
        this.FutreGohan7 = new ModelRenderer((ModelBase)this);
        this.FutreGohan7.func_78793_a(-3.846f, 1.1409f, -0.9554f);
        this.FutreGohan.func_78792_a(this.FutreGohan7);
        this.setRotationAngle(this.FutreGohan7, 0.2783f, -0.2681f, -0.2961f);
        this.FutreGohan7.field_78804_l.add(new ModelBox(this.FutreGohan7, 33, 0, -3.0973f, -0.7019f, 0.9995f, 5, 2, 2, 0.0f));
        this.FutreGohan6 = new ModelRenderer((ModelBase)this);
        this.FutreGohan6.func_78793_a(-0.1524f, 1.4604f, -3.5f);
        this.FutreGohan.func_78792_a(this.FutreGohan6);
        this.setRotationAngle(this.FutreGohan6, 0.0f, 0.0f, -0.3927f);
        this.FutreGohan6.field_78804_l.add(new ModelBox(this.FutreGohan6, 39, 0, -1.0f, -1.3f, -1.5f, 2, 3, 3, 0.0f));
        this.FutreGohan5 = new ModelRenderer((ModelBase)this);
        this.FutreGohan5.func_78793_a(-1.4631f, 0.2931f, -4.0f);
        this.FutreGohan.func_78792_a(this.FutreGohan5);
        this.setRotationAngle(this.FutreGohan5, 0.0f, 0.0f, 0.4363f);
        this.FutreGohan5.field_78804_l.add(new ModelBox(this.FutreGohan5, 34, 3, -0.4031f, -0.123f, -1.0f, 1, 3, 2, 0.0f));
        this.FutreGohan3 = new ModelRenderer((ModelBase)this);
        this.FutreGohan3.func_78793_a(0.0f, 7.5f, 0.0f);
        this.FutreGohan.func_78792_a(this.FutreGohan3);
        this.setRotationAngle(this.FutreGohan3, 0.0f, -0.384f, 0.5271f);
        this.FutreGohan3.field_78804_l.add(new ModelBox(this.FutreGohan3, 33, 0, -11.0f, -5.2f, 1.0f, 6, 2, 2, 0.0f));
        this.FutreGohan4 = new ModelRenderer((ModelBase)this);
        this.FutreGohan4.func_78793_a(1.0861f, 1.4797f, -4.0f);
        this.FutreGohan.func_78792_a(this.FutreGohan4);
        this.setRotationAngle(this.FutreGohan4, 0.0f, 0.0f, -0.576f);
        this.FutreGohan4.field_78804_l.add(new ModelBox(this.FutreGohan4, 34, 0, 0.0f, -1.5f, -1.0f, 1, 3, 2, 0.0f));
        this.FutreGohan2 = new ModelRenderer((ModelBase)this);
        this.FutreGohan2.func_78793_a(-2.6163f, 0.4502f, -3.8265f);
        this.FutreGohan.func_78792_a(this.FutreGohan2);
        this.setRotationAngle(this.FutreGohan2, -0.4651f, 0.2457f, 0.3449f);
        this.Strand4_r1 = new ModelRenderer((ModelBase)this);
        this.Strand4_r1.func_78793_a(-1.5142f, 1.4614f, 0.7725f);
        this.FutreGohan2.func_78792_a(this.Strand4_r1);
        this.setRotationAngle(this.Strand4_r1, 0.3046f, -0.3684f, 0.2431f);
        this.Strand4_r1.field_78804_l.add(new ModelBox(this.Strand4_r1, 33, 1, -0.5926f, -2.4717f, -0.8594f, 2, 3, 2, 0.0f));
        this.FroB = new ModelRenderer((ModelBase)this);
        this.FroB.func_78793_a(0.0f, 10.0f, 2.0f);
        this.ftailS1 = new ModelRenderer((ModelBase)this);
        this.ftailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FroB.func_78792_a(this.ftailS1);
        this.ftailS1.field_78804_l.add(new ModelBox(this.ftailS1, 110, 0, -1.5f, -2.0f, 0.0f, 3, 3, 6, 0.0f));
        this.ftailS2 = new ModelRenderer((ModelBase)this);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.ftailS2.field_78804_l.add(new ModelBox(this.ftailS2, 110, 0, -1.5f, -2.0f, 0.0f, 3, 3, 6, 0.0f));
        this.ftailS3 = new ModelRenderer((ModelBase)this);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS3.field_78804_l.add(new ModelBox(this.ftailS3, 110, 0, -1.5f, -2.0f, 0.0f, 3, 3, 6, 0.0f));
        this.ftailS4 = new ModelRenderer((ModelBase)this);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS4.field_78804_l.add(new ModelBox(this.ftailS4, 110, 0, -1.5f, -2.0f, 0.0f, 3, 3, 6, 0.0f));
        this.ftailS5 = new ModelRenderer((ModelBase)this);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS5.field_78804_l.add(new ModelBox(this.ftailS5, 110, 0, -1.5f, -2.0f, 0.0f, 3, 3, 6, 0.0f));
        this.ftailS6 = new ModelRenderer((ModelBase)this);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 110, 0, -1.5f, -2.0f, 0.0f, 3, 3, 6, 0.0f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 16, 16, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-5.0f, 3.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 40, 16, -3.0f, -3.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(5.0f, 3.0f, 0.0f);
        this.bipedLeftArm.field_78809_i = true;
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 40, 32, -1.0f, -3.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 0, 16, -1.9f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg.field_78809_i = true;
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 0, 32, -2.1f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.func_78087_a(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.bipedHead.func_78785_a(scaleFactor);
        this.bipedBody.func_78785_a(scaleFactor);
        this.renderHairs(0.0625f, "FR", limbSwingAmount);
        GL11.glPopMatrix();
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
            this.transRot(par1, this.bipedBody);
            GL11.glScalef((float)1.0f, (float)0.35f, (float)1.0f);
            this.FroB.func_78785_a(par1);
            GL11.glScaled((double)0.1, (double)0.1, (double)0.1);
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

