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

public class Modellierde
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer bipedBody;
    private final ModelRenderer bipedRightArm;
    private final ModelRenderer bipedLeftArm;
    private final ModelRenderer bipedRightLeg;
    private final ModelRenderer bipedLeftLeg;
    private float scale = 1.0f;

    public Modellierde(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.scale = _scale;
        this.bipedHead = new ModelRenderer((ModelBase)this);
        this.bipedHead.func_78793_a(0.0f, 1.0f, -1.0f);
        this.bipedHead.field_78804_l.add(new ModelBox(this.bipedHead, 56, 18, -4.0f, -10.0f, -3.0f, 8, 10, 7, -0.5f));
        this.bipedBody = new ModelRenderer((ModelBase)this);
        this.bipedBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 0, -5.0f, 0.0f, -3.0f, 10, 6, 6, 0.0f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 32, 0, -4.0f, 9.0f, -2.0f, 8, 3, 4, 0.25f));
        this.bipedBody.field_78804_l.add(new ModelBox(this.bipedBody, 0, 12, -4.0f, 4.0f, -2.0f, 8, 8, 4, 0.0f));
        this.bipedRightArm = new ModelRenderer((ModelBase)this);
        this.bipedRightArm.func_78793_a(-6.0f, 2.25f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 24, 26, -3.5f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 48, 7, -4.0f, -3.0f, -2.5f, 5, 6, 5, 0.0f));
        this.bipedRightArm.field_78804_l.add(new ModelBox(this.bipedRightArm, 32, 42, 0.0f, -6.0f, -3.5f, 1, 9, 7, 0.01f));
        this.bipedLeftArm = new ModelRenderer((ModelBase)this);
        this.bipedLeftArm.func_78793_a(6.0f, 2.25f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 0, 28, -0.5f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 48, 42, -1.0f, -3.0f, -2.5f, 5, 6, 5, 0.0f));
        this.bipedLeftArm.field_78804_l.add(new ModelBox(this.bipedLeftArm, 0, 44, -1.0f, -6.0f, -3.5f, 1, 9, 7, 0.01f));
        this.bipedRightLeg = new ModelRenderer((ModelBase)this);
        this.bipedRightLeg.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.bipedRightLeg.field_78804_l.add(new ModelBox(this.bipedRightLeg, 40, 26, -2.1f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this);
        this.bipedLeftLeg.func_78793_a(1.9f, 12.0f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.bipedLeftLeg.field_78804_l.add(new ModelBox(this.bipedLeftLeg, 16, 42, -1.9f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
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

