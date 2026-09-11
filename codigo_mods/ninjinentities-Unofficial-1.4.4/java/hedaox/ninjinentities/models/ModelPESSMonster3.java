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

public class ModelPESSMonster3
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

    public ModelPESSMonster3(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -6.0f, 8, 8, 8, 0.0f));
        ModelRenderer bone3 = new ModelRenderer((ModelBase)this);
        bone3.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Head.func_78792_a(bone3);
        bone3.field_78804_l.add(new ModelBox(bone3, 0, 54, -1.0f, -34.0f, -6.0f, 2, 2, 8, 0.0f));
        bone3.field_78804_l.add(new ModelBox(bone3, 120, 53, -1.0f, -34.0f, 2.0f, 2, 10, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 64, 0, -5.0f, 0.0f, -2.0f, 10, 5, 5, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -3.5f, 5.0f, -2.0f, 7, 7, 4, 0.0f));
        ModelRenderer bodyChild_1 = new ModelRenderer((ModelBase)this);
        bodyChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_1);
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 69, 59, -1.0f, 5.0f, 2.0f, 2, 3, 2, 0.0f));
        bodyChild_1.field_78804_l.add(new ModelBox(bodyChild_1, 120, 57, -1.0f, 0.0f, 3.0f, 2, 5, 2, 0.0f));
        ModelRenderer bodyChild_3 = new ModelRenderer((ModelBase)this);
        bodyChild_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_3, 0.5236f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_3);
        bodyChild_3.field_78804_l.add(new ModelBox(bodyChild_3, 32, 48, -2.0f, 15.0f, 2.0f, 4, 4, 12, 0.0f));
        ModelRenderer bodyChild_2 = new ModelRenderer((ModelBase)this);
        bodyChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_2, -0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_2);
        bodyChild_2.field_78804_l.add(new ModelBox(bodyChild_2, 32, 48, -2.0f, 7.0f, 4.0f, 4, 4, 12, 0.0f));
        ModelRenderer bodyChild = new ModelRenderer((ModelBase)this);
        bodyChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild, -0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild);
        bodyChild.field_78804_l.add(new ModelBox(bodyChild, 64, 12, -4.5f, 1.0f, -1.9333f, 9, 4, 1, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.RArm, -0.0231f, 0.0f, 0.0791f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -5.0f, -2.0f, -2.0f, 5, 12, 5, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(0.0f, 0.0f, -0.6f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 38, 0, -5.984f, -2.8398f, -1.9984f, 6, 3, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0231f, 0.0f, -0.0791f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78809_i = true;
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 0.0f, -2.0f, -2.0f, 5, 12, 5, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(0.0f, 0.0f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78809_i = true;
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 38, 0, 0.0815f, -2.7938f, -2.0021f, 6, 3, 6, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(2.0f, 13.0f, 0.0f);
        this.setRotationAngle(bone, -0.1745f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 0, 0, -3.0f, -7.0f, -5.0f, 2, 1, 3, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78809_i = true;
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(2.0f, 13.0f, 0.0f);
        this.setRotationAngle(bone2, -0.1745f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 0, 0, -3.0f, -7.0f, -5.0f, 2, 1, 3, 0.0f));
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

