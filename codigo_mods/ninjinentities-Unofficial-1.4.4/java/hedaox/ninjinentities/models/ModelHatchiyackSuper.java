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

public class ModelHatchiyackSuper
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

    public ModelHatchiyackSuper(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer crown1 = new ModelRenderer((ModelBase)this);
        crown1.func_78793_a(0.5f, -0.9f, -4.2f);
        this.setRotationAngle(crown1, -0.6981f, 0.0f, 0.0f);
        this.Head.func_78792_a(crown1);
        crown1.field_78804_l.add(new ModelBox(crown1, 48, 9, -4.0f, -10.4088f, -3.8767f, 7, 5, 1, 0.5f));
        ModelRenderer crown2_1 = new ModelRenderer((ModelBase)this);
        crown2_1.func_78793_a(-1.9f, -0.8f, -1.0f);
        this.setRotationAngle(crown2_1, 0.0f, 0.0f, 1.3271f);
        this.Head.func_78792_a(crown2_1);
        crown2_1.field_78804_l.add(new ModelBox(crown2_1, 20, 34, -5.9409f, -6.4826f, -4.0f, 1, 3, 1, 0.5f));
        ModelRenderer crown2 = new ModelRenderer((ModelBase)this);
        crown2.func_78793_a(3.5f, -7.5f, -1.0f);
        this.setRotationAngle(crown2, 0.0f, 0.0f, -1.3273f);
        this.Head.func_78792_a(crown2);
        crown2.field_78804_l.add(new ModelBox(crown2, 20, 30, -2.059f, -6.4822f, -4.0f, 1, 3, 1, 0.5f));
        ModelRenderer crowngem = new ModelRenderer((ModelBase)this);
        crowngem.func_78793_a(3.5f, -0.9f, -4.7f);
        this.setRotationAngle(crowngem, -0.6894f, 0.0f, 0.0f);
        this.Head.func_78792_a(crowngem);
        crowngem.field_78804_l.add(new ModelBox(crowngem, 32, 54, -4.5f, -9.5433f, -5.2721f, 2, 3, 1, 0.3f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -5.0f, 0.0f, -2.0f, 10, 5, 5, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 71, -4.0f, 0.0f, -3.0f, 8, 5, 5, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 26, -3.0f, 5.0f, -3.0f, 1, 3, 1, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, 2.0f, 5.0f, -3.0f, 1, 3, 1, 0.0f));
        ModelRenderer bodyChild_1 = new ModelRenderer((ModelBase)this);
        bodyChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_1);
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 46, 19, -3.5f, 5.0f, -2.0f, 7, 7, 4, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 55, 38, 3.0f, 8.0f, -2.0f, 1, 2, 4, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 54, 0, -4.0f, 8.0f, -2.0f, 1, 2, 4, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 46, 52, -4.0f, 8.0f, -3.0f, 8, 2, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 24, 4, -3.0f, 10.0f, -3.0f, 6, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 52, 30, -2.0f, 11.0f, -3.0f, 4, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 0, 6, -1.0f, 5.0f, -3.0f, 2, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 12, 43, -1.0f, 1.0f, -4.0f, 2, 3, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 24, 2, -3.0f, 5.0f, 2.0f, 6, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 28, 52, -2.0f, 6.0f, 2.0f, 4, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 20, 26, -2.0f, 11.0f, 2.0f, 4, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 24, 0, -3.0f, 10.0f, 2.0f, 6, 1, 1, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 50, 35, -4.0f, 8.0f, 2.0f, 8, 2, 1, 0.0f));
        ModelRenderer bodyChild_3 = new ModelRenderer((ModelBase)this);
        bodyChild_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_3, 0.5236f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_3);
        ModelRenderer bodyChild_2 = new ModelRenderer((ModelBase)this);
        bodyChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_2, -0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_2);
        ModelRenderer bodyChild = new ModelRenderer((ModelBase)this);
        bodyChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild, -0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild);
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.RArm, -0.0231f, 0.0f, 0.0791f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 20, 35, -5.0f, -2.0f, -2.0f, 5, 12, 5, 0.0f));
        ModelRenderer bone3 = new ModelRenderer((ModelBase)this);
        bone3.func_78793_a(5.0f, 22.0f, 0.0f);
        this.RArm.func_78792_a(bone3);
        bone3.field_78804_l.add(new ModelBox(bone3, 50, 62, -10.3522f, -16.6322f, -0.8067f, 1, 3, 2, 0.0f));
        bone3.field_78804_l.add(new ModelBox(bone3, 40, 40, -11.1487f, -17.7562f, -3.0406f, 4, 5, 7, 0.0f));
        bone3.field_78804_l.add(new ModelBox(bone3, 52, 32, -10.6683f, -18.6186f, -0.8988f, 1, 1, 2, 0.0f));
        bone3.field_78804_l.add(new ModelBox(bone3, 40, 40, -11.3491f, -16.5532f, -0.8048f, 1, 3, 2, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(0.0f, 0.0f, -0.6f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 32, 0, -8.0f, -2.8398f, -1.9984f, 8, 3, 6, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 30, 19, -7.0f, -3.595f, -1.0654f, 6, 1, 3, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0231f, 0.0f, -0.0791f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 26, 0.0f, -2.0f, -2.0f, 5, 12, 5, 0.0f));
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 59, 4.3491f, 5.4468f, -1.1952f, 1, 3, 3, 0.0f));
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 32, 52, 2.0f, 4.1648f, -2.9576f, 4, 5, 6, 0.0f));
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 15, 26, 4.6652f, 3.4604f, -1.1031f, 1, 1, 3, 0.0f));
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 0, 5.346f, 5.5258f, -1.197f, 1, 3, 3, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(0.0f, 0.0f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 26, 10, 0.0815f, -2.7938f, -2.0021f, 8, 3, 6, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 35, 35, 1.0f, -3.4169f, -0.9364f, 6, 1, 3, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 43, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 54, 15, -1.0f, 7.0f, -3.0f, 2, 3, 1, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 8, 59, -3.0f, 4.0f, -1.0f, 1, 3, 2, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(2.0f, 13.0f, 0.0f);
        this.setRotationAngle(bone, -0.1745f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(bone);
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 16, 52, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 60, 0, -1.0f, 7.0f, -3.0f, 2, 3, 1, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 60, 30, 2.0f, 4.0f, -1.0f, 1, 3, 2, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(2.0f, 13.0f, 0.0f);
        this.setRotationAngle(bone2, -0.1745f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(bone2);
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

