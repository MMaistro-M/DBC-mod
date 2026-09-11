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

public class ModelLedgic
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer Head2;
    private final ModelRenderer HatLayer_r1;
    private final ModelRenderer HatLayer_r2;
    private final ModelRenderer bipedBody;
    private final ModelRenderer bipedLeftLeg;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer LeftArm_r1;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer RightArm_r1;
    private float scale = 1.0f;

    public ModelLedgic(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, -2.0f, 0.0f);
        this.Head2 = new ModelRenderer((ModelBase)this);
        this.Head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedHead.func_78792_a(this.Head2);
        this.Head2.field_78804_l.add(new ModelBox(this.Head2, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.HatLayer_r1 = new ModelRenderer((ModelBase)this);
        this.HatLayer_r1.func_78793_a(-1.0f, -4.75f, 1.0f);
        this.Head2.func_78792_a(this.HatLayer_r1);
        this.setRotationAngle(this.HatLayer_r1, 0.0f, 0.0f, 0.1309f);
        this.HatLayer_r1.field_78804_l.add(new ModelBox(this.HatLayer_r1, 24, 28, -6.0f, -3.0f, -5.0f, 4, 4, 8, -0.01f));
        this.HatLayer_r2 = new ModelRenderer((ModelBase)this);
        this.HatLayer_r2.func_78793_a(1.0f, -4.75f, 1.0f);
        this.Head2.func_78792_a(this.HatLayer_r2);
        this.setRotationAngle(this.HatLayer_r2, 0.0f, 0.0f, -0.1309f);
        this.HatLayer_r2.field_78804_l.add(new ModelBox(this.HatLayer_r2, 24, 16, 2.0f, -3.0f, -5.0f, 4, 4, 8, -0.01f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, -2.0f, 0.0f);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 16, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(1.9f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 16, 40, -2.0f, 0.0f, -2.0f, 4, 14, 4, 0.0f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 0, 32, -2.0f, 0.0f, -2.0f, 4, 14, 4, 0.0f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 46, 40, -1.0f, -2.0f, -2.0f, 3, 13, 4, 0.0f));
        this.LeftArm_r1 = new ModelRenderer((ModelBase)this);
        this.LeftArm_r1.func_78793_a(0.0f, 8.0f, 2.0f);
        this.bipedLeftArm.func_78792_a(this.LeftArm_r1);
        this.setRotationAngle(this.LeftArm_r1, 0.1745f, 0.0f, 0.0f);
        this.LeftArm_r1.field_78804_l.add(new ModelBox(this.LeftArm_r1, 32, 0, -1.0f, -1.0f, 0.0f, 3, 7, 0, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 32, 40, -2.0f, -2.0f, -2.0f, 3, 13, 4, 0.0f));
        this.RightArm_r1 = new ModelRenderer((ModelBase)this);
        this.RightArm_r1.func_78793_a(0.0f, 8.0f, 2.0f);
        this.bipedRightArm.func_78792_a(this.RightArm_r1);
        this.setRotationAngle(this.RightArm_r1, 0.1745f, 0.0f, 0.0f);
        this.RightArm_r1.field_78804_l.add(new ModelBox(this.RightArm_r1, 32, 7, -2.0f, -1.0f, 0.0f, 3, 7, 0, 0.0f));
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

