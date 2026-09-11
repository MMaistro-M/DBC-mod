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

public class ModelHatchiyack
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

    public ModelHatchiyack(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer bone5 = new ModelRenderer((ModelBase)this);
        bone5.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Head.func_78792_a(bone5);
        bone5.field_78804_l.add(new ModelBox(bone5, 0, 0, -4.0f, -33.0f, -2.0f, 8, 1, 6, 0.0f));
        bone5.field_78804_l.add(new ModelBox(bone5, 0, 0, -4.0f, -32.0f, -4.0f, 8, 8, 8, 0.5f));
        ModelRenderer crown1 = new ModelRenderer((ModelBase)this);
        crown1.func_78793_a(0.5f, -0.9f, -4.2f);
        this.setRotationAngle(crown1, -0.6981f, 0.0f, 0.0f);
        this.Head.func_78792_a(crown1);
        crown1.field_78804_l.add(new ModelBox(crown1, 42, 19, -4.0f, -10.4088f, -3.8767f, 7, 5, 1, 0.5f));
        ModelRenderer crown2_1 = new ModelRenderer((ModelBase)this);
        crown2_1.func_78793_a(-1.9f, -0.8f, -1.0f);
        this.setRotationAngle(crown2_1, 0.0f, 0.0f, 1.3271f);
        this.Head.func_78792_a(crown2_1);
        crown2_1.field_78804_l.add(new ModelBox(crown2_1, 0, 26, -5.9409f, -6.4826f, -4.0f, 1, 3, 1, 0.5f));
        ModelRenderer crown2 = new ModelRenderer((ModelBase)this);
        crown2.func_78793_a(3.5f, -7.5f, -1.0f);
        this.setRotationAngle(crown2, 0.0f, 0.0f, -1.3273f);
        this.Head.func_78792_a(crown2);
        crown2.field_78804_l.add(new ModelBox(crown2, 0, 16, -2.059f, -6.4822f, -4.0f, 1, 3, 1, 0.5f));
        ModelRenderer crowngem = new ModelRenderer((ModelBase)this);
        crowngem.func_78793_a(3.5f, -0.9f, -4.7f);
        this.setRotationAngle(crowngem, -0.6894f, 0.0f, 0.0f);
        this.Head.func_78792_a(crowngem);
        crowngem.field_78804_l.add(new ModelBox(crowngem, 25, 16, -4.5f, -9.5433f, -5.2721f, 2, 3, 1, 0.3f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -5.0f, 0.0f, -2.0f, 10, 5, 5, 0.0f));
        ModelRenderer bodyChild_1 = new ModelRenderer((ModelBase)this);
        bodyChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_1);
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 0, 26, -3.5f, 5.0f, -2.0f, 7, 7, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.RArm, -0.0231f, 0.0f, 0.0791f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 0, 37, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone3 = new ModelRenderer((ModelBase)this);
        bone3.func_78793_a(5.0f, 22.0f, 0.0f);
        this.RArm.func_78792_a(bone3);
        bone3.field_78804_l.add(new ModelBox(bone3, 24, 0, -9.3553f, -16.7112f, -0.8085f, 1, 3, 2, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(0.0f, 0.0f, -0.6f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 26, 25, -5.0f, -2.8398f, -1.9984f, 5, 3, 5, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0231f, 0.0f, -0.0791f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 32, 0, 0.0031f, -2.079f, -1.9982f, 4, 12, 4, 0.0f));
        ModelRenderer bone4 = new ModelRenderer((ModelBase)this);
        bone4.func_78793_a(-5.0f, 22.0f, 0.0f);
        this.LArm.func_78792_a(bone4);
        bone4.field_78804_l.add(new ModelBox(bone4, 0, 0, 8.3522f, -16.6322f, -1.1934f, 1, 3, 2, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(0.0f, 0.0f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 43, 10, 0.0815f, -2.7938f, -2.0021f, 5, 3, 5, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 18, 33, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(2.0f, 13.0f, 0.0f);
        this.setRotationAngle(bone, -0.1745f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 18, 26, -3.0f, -6.0f, -3.0f, 2, 3, 1, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 34, 34, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(2.0f, 13.0f, 0.0f);
        this.setRotationAngle(bone2, -0.1745f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 24, 26, -3.0f, -6.0f, -3.0f, 2, 3, 1, 0.0f));
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

