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

public class ModelDore
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

    public ModelDore(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer headGear = new ModelRenderer((ModelBase)this);
        headGear.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(headGear);
        headGear.field_78804_l.add(new ModelBox(headGear, 32, 44, -4.0f, -8.0f, -4.0f, 8, 7, 8, 0.5f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 28, 28, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(5.0f, 2.0f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.Body.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 27, 11, -1.0f, -3.0f, -2.0f, 7, 4, 5, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 46, 0, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 16, 40, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 32, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 48, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(1.0f, 0.0f, 3.0f);
        this.setRotationAngle(hair1, 0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 5, 18, -5.0f, -1.1496f, -0.4111f, 8, 11, 2, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(3.1875f, 0.9375f, 3.0f);
        this.setRotationAngle(hair2, 0.3491f, -0.2618f, 0.7854f);
        this.Body.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 7, 24, -3.0f, -1.1496f, -0.4111f, 6, 3, 2, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(3.1875f, 5.9375f, 5.0f);
        this.setRotationAngle(hair3, 0.3491f, -0.2618f, 0.7854f);
        this.Body.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 7, 22, -3.0f, -1.1496f, -0.4111f, 6, 7, 2, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(-3.1875f, 0.9375f, 3.0f);
        this.setRotationAngle(hair4, 0.3491f, 0.2618f, -0.7854f);
        this.Body.func_78792_a(hair4);
        hair4.field_78804_l.add(new ModelBox(hair4, 7, 24, -3.0f, -1.1496f, -0.4111f, 6, 3, 2, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(-3.1875f, 5.9375f, 5.0f);
        this.setRotationAngle(hair5, 0.3491f, 0.2618f, -0.7854f);
        this.Body.func_78792_a(hair5);
        hair5.field_78804_l.add(new ModelBox(hair5, 7, 21, -3.0f, -1.1496f, -0.4111f, 6, 7, 2, 0.0f));
        ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
        hair6.func_78793_a(-0.1875f, 0.9375f, 4.0f);
        this.setRotationAngle(hair6, 0.0f, 0.6109f, -1.5708f);
        this.Body.func_78792_a(hair6);
        hair6.field_78804_l.add(new ModelBox(hair6, 5, 21, -3.0f, -2.1496f, -2.4111f, 6, 6, 4, 0.0f));
        ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
        hair7.func_78793_a(-0.1875f, 5.9375f, 6.0f);
        this.setRotationAngle(hair7, 0.0f, 0.6109f, -1.5708f);
        this.Body.func_78792_a(hair7);
        hair7.field_78804_l.add(new ModelBox(hair7, 6, 22, -3.0f, -2.1496f, -1.4111f, 6, 6, 3, 0.0f));
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

