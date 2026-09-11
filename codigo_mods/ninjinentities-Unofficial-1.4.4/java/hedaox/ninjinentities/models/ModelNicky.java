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

public class ModelNicky
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

    public ModelNicky(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 86, -4.0f, -10.0f, -4.0f, 8, 10, 8, 0.5f));
        ModelRenderer headChild_15 = new ModelRenderer((ModelBase)this);
        headChild_15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(headChild_15, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(headChild_15);
        headChild_15.field_78804_l.add(new ModelBox(headChild_15, 12, 39, 4.0f, -6.0f, -1.0f, 0, 4, 6, 0.0f));
        ModelRenderer headChild_16 = new ModelRenderer((ModelBase)this);
        headChild_16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(headChild_16, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(headChild_16);
        headChild_16.field_78804_l.add(new ModelBox(headChild_16, 32, 16, -4.0f, -6.0f, -1.0f, 0, 4, 6, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(0.0f, -8.0f, -4.0f);
        this.setRotationAngle(hair1, -0.1745f, 0.0f, 0.0f);
        this.Head.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 51, 83, -3.0f, -4.0f, 0.0f, 6, 4, 8, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(-2.6875f, -7.0f, -4.0f);
        this.setRotationAngle(hair2, -0.1745f, 0.0f, -0.733f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 51, 83, -2.0f, -3.0f, 0.0f, 5, 3, 8, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(2.6875f, -7.0f, -4.0f);
        this.setRotationAngle(hair3, -0.1745f, 0.0f, 0.733f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 51, 83, -3.0f, -3.0f, 0.0f, 5, 3, 8, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 29, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 27, 27, -4.7f, -6.0f, -2.2f, 9, 11, 5, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 27, 11, -4.7f, 4.6875f, -2.2f, 9, 5, 5, 0.0f));
        ModelRenderer bone4 = new ModelRenderer((ModelBase)this);
        bone4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bone4, 0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(bone4);
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 16, 53, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 44, 44, -4.7512f, 0.8794f, -2.4f, 5, 8, 5, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(-1.0f, 0.0f, -0.6f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 18, 43, -6.0f, -3.0f, -3.0f, 7, 4, 6, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 3, 3, -5.2795f, -5.5857f, -2.0f, 1, 3, 1, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 0, 0, -2.2945f, -5.8852f, 0.0f, 1, 3, 1, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 55, 6, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(9.0f, 0.0f, -0.5f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 32, 0, -1.0f, -3.0f, -3.0f, 7, 4, 6, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 4, 16, 4.2745f, -5.4858f, -2.0f, 1, 3, 1, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 0, 16, 1.2895f, -5.7853f, 0.0f, 1, 3, 1, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 45, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(1.0f, 0.0f, 0.0f);
        this.setRotationAngle(bone, 0.0f, 0.0f, 0.2618f);
        this.RLeg.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 0, 0, -3.0f, 6.2805f, -1.0f, 2, 1, 1, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 32, 53, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer bone5 = new ModelRenderer((ModelBase)this);
        bone5.func_78793_a(-2.0f, 1.0f, 0.0f);
        this.setRotationAngle(bone5, 0.0f, 0.0f, -0.1745f);
        this.LLeg.func_78792_a(bone5);
        bone5.field_78804_l.add(new ModelBox(bone5, 0, 0, 6.0f, 6.0f, -1.0f, 3, 1, 1, 0.0f));
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

