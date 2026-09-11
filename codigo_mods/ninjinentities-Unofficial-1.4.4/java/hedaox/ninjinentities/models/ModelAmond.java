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

public class ModelAmond
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

    public ModelAmond(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 26, 26, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(-3.0f, -9.0f, 0.0f);
        this.setRotationAngle(hair1, -0.0873f, 0.0f, -2.4434f);
        this.Head.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 79, 38, -1.5133f, -4.474f, -2.0714f, 2, 6, 3, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(3.0f, -9.0f, 0.0f);
        this.setRotationAngle(hair2, 0.0872f, 0.0f, 2.3562f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 79, 38, -0.4867f, -4.474f, -1.6714f, 2, 6, 3, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(3.4f, -9.0f, -2.6f);
        this.setRotationAngle(hair3, 0.1745f, -0.3491f, 2.3561f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 79, 38, -0.4867f, -4.474f, -2.0714f, 2, 7, 3, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(-3.4f, -9.0f, -2.6f);
        this.setRotationAngle(hair4, 0.1745f, 0.3491f, -2.3561f);
        this.Head.func_78792_a(hair4);
        hair4.field_78804_l.add(new ModelBox(hair4, 79, 38, -1.5133f, -4.474f, -2.0714f, 2, 7, 3, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(-2.8f, -9.0f, 2.9f);
        this.setRotationAngle(hair5, -0.0873f, 0.0f, -2.3561f);
        this.Head.func_78792_a(hair5);
        hair5.field_78804_l.add(new ModelBox(hair5, 79, 38, -1.5133f, -4.474f, -2.0714f, 2, 6, 3, 0.0f));
        ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
        hair6.func_78793_a(2.8f, -9.0f, 2.9f);
        this.setRotationAngle(hair6, -0.0873f, 0.0f, 2.3561f);
        this.Head.func_78792_a(hair6);
        hair6.field_78804_l.add(new ModelBox(hair6, 79, 38, -0.4867f, -4.474f, -2.0714f, 2, 6, 3, 0.0f));
        ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
        hair7.func_78793_a(2.8f, -9.0f, 2.9f);
        this.setRotationAngle(hair7, -0.611f, -0.6108f, 3.0543f);
        this.Head.func_78792_a(hair7);
        hair7.field_78804_l.add(new ModelBox(hair7, 79, 39, -0.4867f, -4.474f, -2.0714f, 3, 6, 2, 0.0f));
        ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
        hair8.func_78793_a(-2.8f, -9.0f, 2.9f);
        this.setRotationAngle(hair8, -0.611f, 0.5236f, -3.0543f);
        this.Head.func_78792_a(hair8);
        hair8.field_78804_l.add(new ModelBox(hair8, 79, 39, -2.5133f, -4.474f, -2.0714f, 3, 6, 2, 0.0f));
        ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
        hair9.func_78793_a(2.6f, -5.0f, -0.6f);
        this.Head.func_78792_a(hair9);
        hair9.field_78804_l.add(new ModelBox(hair9, 74, 38, -4.0133f, -5.674f, -3.0714f, 3, 3, 7, 0.0f));
        ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
        hair10.func_78793_a(3.0f, -11.0f, -1.6f);
        this.setRotationAngle(hair10, -1.2217f, 0.0f, 0.0f);
        this.Head.func_78792_a(hair10);
        hair10.field_78804_l.add(new ModelBox(hair10, 70, 21, -4.0133f, -4.674f, 3.9286f, 2, 2, 12, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 28, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 36, 42, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(-1.0f, 0.0f, -0.6f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 34, 10, -6.0f, -3.0f, -3.0f, 7, 4, 6, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 0, 0, -1.2995f, -4.985f, -1.0f, 1, 2, 1, 0.0f));
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 0, 0, -4.2845f, -4.6855f, -1.0f, 1, 2, 1, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 52, 52, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(9.0f, 0.0f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 34, 0, -1.0f, -3.0f, -3.0f, 7, 4, 6, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 0, 0, 3.2795f, -4.5857f, -1.0f, 1, 2, 1, 0.0f));
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 0, 0, 0.2945f, -4.8852f, -1.0f, 1, 2, 1, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 20, 42, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 69, -2.5f, -3.6f, -2.4f, 4, 10, 5, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 44, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 113, 2.5f, -3.6f, -2.4f, 4, 10, 5, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
        this.Head.field_78796_g = f3 / 50.92958f;
        this.Head.field_78795_f = f4 / 50.92958f;
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

