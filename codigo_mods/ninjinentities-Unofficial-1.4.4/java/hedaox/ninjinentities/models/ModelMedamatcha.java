/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package hedaox.ninjinentities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelMedamatcha
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer HeadChild_2;
    private final ModelRenderer HeadChild_1;
    private final ModelRenderer Body;
    private final ModelRenderer Kilt;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelMedamatcha(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, 0.0f, -4.0f, 8, 8, 8, 0.0f));
        this.HeadChild_2 = new ModelRenderer((ModelBase)this);
        this.HeadChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(this.HeadChild_2, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(this.HeadChild_2);
        this.HeadChild_2.field_78804_l.add(new ModelBox(this.HeadChild_2, 12, 21, 4.0f, 1.0f, -1.0f, 0, 4, 6, 0.0f));
        this.HeadChild_1 = new ModelRenderer((ModelBase)this);
        this.HeadChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(this.HeadChild_1, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(this.HeadChild_1);
        this.HeadChild_1.field_78804_l.add(new ModelBox(this.HeadChild_1, 28, 30, -4.0f, 1.0f, -1.0f, 0, 4, 6, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 24, 24, -4.2f, 2.6f, -1.8f, 8, 8, 4, 0.0f));
        this.Kilt = new ModelRenderer((ModelBase)this);
        this.Kilt.func_78793_a(3.3f, 13.0f, -3.0f);
        this.Body.func_78792_a(this.Kilt);
        this.Kilt.field_78804_l.add(new ModelBox(this.Kilt, 0, 48, -8.0f, -3.0f, 0.8f, 9, 6, 5, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 32, 0, -4.0f, 6.0f, -2.0f, 4, 8, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 12, 35, 8.0f, 6.0f, -2.0f, 4, 8, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 27, -2.0f, 4.0f, -2.0f, 4, 8, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 28, 12, 2.0f, 4.0f, -2.0f, 4, 8, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float HeadPitch, float scaleFactor, Entity entity) {
        this.Head.field_78796_g = netHeadYaw / 57.295776f;
        this.Head.field_78795_f = HeadPitch / 57.295776f;
        this.RArm.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f + (float)Math.PI)) * 2.0f * limbSwingAmount * 0.5f;
        this.LArm.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f)) * 2.0f * limbSwingAmount * 0.5f;
        this.RLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f)) * 1.4f * limbSwingAmount;
        this.LLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f + (float)Math.PI)) * 1.4f * limbSwingAmount;
        this.RArm.field_78808_h = 0.0f;
        this.LArm.field_78808_h = 0.0f;
        this.RLeg.field_78796_g = 0.0f;
        this.LLeg.field_78796_g = 0.0f;
        this.RArm.field_78796_g = 0.0f;
        float animProgress = this.field_78095_p;
        this.Body.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)animProgress) * (float)Math.PI * 2.0f)) * 0.2f;
        float animCurve = 1.0f - animProgress;
        animCurve *= animCurve;
        animCurve *= animCurve;
        animCurve = 1.0f - animCurve;
        float sinCurve = MathHelper.func_76126_a((float)(animCurve * (float)Math.PI));
        float HeadOffset = MathHelper.func_76126_a((float)(animProgress * (float)Math.PI)) * -(this.Head.field_78795_f - 0.7f) * 0.75f;
        this.RArm.field_78795_f -= sinCurve * 1.2f + HeadOffset;
        this.RArm.field_78796_g += this.Body.field_78796_g * 2.0f;
        this.RArm.field_78808_h = MathHelper.func_76126_a((float)(animProgress * 2.1415927f)) * -0.4f;
    }
}

