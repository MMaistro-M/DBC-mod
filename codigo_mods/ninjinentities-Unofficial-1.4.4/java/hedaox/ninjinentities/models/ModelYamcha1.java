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

public class ModelYamcha1
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelYamcha1(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(-1.0f, -9.0f, 0.0f);
        this.setRotationAngle(hair1, -0.0873f, 0.0f, -0.5585f);
        this.Head.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 42, 2, -0.5133f, -3.474f, -2.0714f, 3, 6, 3, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(-3.0f, -10.0f, 0.0f);
        this.setRotationAngle(hair2, 0.0f, 0.0f, -0.9774f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 45, 2, -2.6397f, -2.658f, -3.0f, 3, 6, 3, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(1.0f, -8.0f, 0.0f);
        this.setRotationAngle(hair3, 0.0f, 0.0f, 0.6981f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 45, 2, 0.0603f, -3.342f, -3.0f, 3, 6, 3, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(2.0f, -7.0f, 0.0f);
        this.setRotationAngle(hair4, 0.0f, 0.0873f, 0.7854f);
        this.Head.func_78792_a(hair4);
        hair4.field_78804_l.add(new ModelBox(hair4, 45, 2, 1.0603f, -3.342f, -3.0f, 2, 5, 3, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(-5.0f, -6.0f, -2.0f);
        this.setRotationAngle(hair5, 2.7925f, 0.0873f, -1.4836f);
        this.Head.func_78792_a(hair5);
        hair5.field_78804_l.add(new ModelBox(hair5, 45, 2, -1.0028f, -1.971f, -3.2188f, 3, 5, 3, 0.0f));
        ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
        hair6.func_78793_a(-5.0f, -7.0f, 4.0f);
        this.setRotationAngle(hair6, -0.3491f, 0.0f, -0.7854f);
        this.Head.func_78792_a(hair6);
        hair6.field_78804_l.add(new ModelBox(hair6, 45, 2, 1.8099f, -1.1257f, -2.2188f, 3, 5, 3, 0.0f));
        ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
        hair7.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.setRotationAngle(hair7, -0.3491f, 0.0f, 0.6982f);
        this.Head.func_78792_a(hair7);
        hair7.field_78804_l.add(new ModelBox(hair7, 45, 2, 1.8099f, -1.4417f, -5.0982f, 3, 4, 5, 0.0f));
        ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
        hair8.func_78793_a(-5.0f, -7.0f, 2.0f);
        this.setRotationAngle(hair8, 2.7925f, 0.0873f, -1.309f);
        this.Head.func_78792_a(hair8);
        hair8.field_78804_l.add(new ModelBox(hair8, 45, 2, -2.0028f, -3.971f, -3.2188f, 3, 4, 3, 0.0f));
        ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
        hair9.func_78793_a(3.0f, -8.0f, 2.0f);
        this.setRotationAngle(hair9, 2.7925f, 0.0873f, 0.9599f);
        this.Head.func_78792_a(hair9);
        hair9.field_78804_l.add(new ModelBox(hair9, 45, 2, 0.9972f, -1.971f, -3.2188f, 3, 3, 3, 0.0f));
        ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
        hair10.func_78793_a(-0.7f, -4.2f, 1.3f);
        this.setRotationAngle(hair10, 2.8798f, 0.0873f, 0.0f);
        this.Head.func_78792_a(hair10);
        hair10.field_78804_l.add(new ModelBox(hair10, 38, 2, 0.9972f, -2.3391f, -4.0809f, 3, 7, 3, 0.0f));
        ModelRenderer hair11 = new ModelRenderer((ModelBase)this);
        hair11.func_78793_a(-3.5f, -6.6f, 1.6f);
        this.setRotationAngle(hair11, 2.9671f, 0.0175f, 0.0524f);
        this.Head.func_78792_a(hair11);
        hair11.field_78804_l.add(new ModelBox(hair11, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        ModelRenderer hair12 = new ModelRenderer((ModelBase)this);
        hair12.func_78793_a(-4.5f, 1.1f, 2.1f);
        this.setRotationAngle(hair12, -2.9671f, 0.0175f, -0.4712f);
        this.Head.func_78792_a(hair12);
        hair12.field_78804_l.add(new ModelBox(hair12, 38, 2, 0.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        ModelRenderer hair13 = new ModelRenderer((ModelBase)this);
        hair13.func_78793_a(4.5f, 1.1f, 2.1f);
        this.setRotationAngle(hair13, -2.9671f, -0.0175f, 0.4712f);
        this.Head.func_78792_a(hair13);
        hair13.field_78804_l.add(new ModelBox(hair13, 38, 2, -3.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        ModelRenderer hair14 = new ModelRenderer((ModelBase)this);
        hair14.func_78793_a(2.5f, 1.1f, 2.1f);
        this.setRotationAngle(hair14, -2.9671f, -0.0175f, 0.1222f);
        this.Head.func_78792_a(hair14);
        hair14.field_78804_l.add(new ModelBox(hair14, 38, 2, -3.9972f, -4.6289f, -4.1585f, 3, 7, 3, 0.0f));
        ModelRenderer hair15 = new ModelRenderer((ModelBase)this);
        hair15.func_78793_a(1.5f, 4.1f, 1.7f);
        this.setRotationAngle(hair15, -2.9671f, -0.0175f, 1.6057f);
        this.Head.func_78792_a(hair15);
        hair15.field_78804_l.add(new ModelBox(hair15, 44, 2, -3.9972f, -6.6289f, -4.1585f, 3, 9, 3, 0.0f));
        ModelRenderer hair16 = new ModelRenderer((ModelBase)this);
        hair16.func_78793_a(1.5f, 4.1f, 1.7f);
        this.setRotationAngle(hair16, 2.8798f, -0.0175f, -3.0194f);
        this.Head.func_78792_a(hair16);
        hair16.field_78804_l.add(new ModelBox(hair16, 44, 2, 4.3028f, -11.3289f, -4.1585f, 3, 9, 3, 0.0f));
        ModelRenderer hair17 = new ModelRenderer((ModelBase)this);
        hair17.func_78793_a(-1.5f, 4.1f, 1.7f);
        this.setRotationAngle(hair17, 2.8798f, 0.0175f, 3.0194f);
        this.Head.func_78792_a(hair17);
        hair17.field_78804_l.add(new ModelBox(hair17, 44, 2, -7.3028f, -11.3289f, -4.1585f, 3, 9, 3, 0.0f));
        ModelRenderer strand1 = new ModelRenderer((ModelBase)this);
        strand1.func_78793_a(-4.0f, -8.0f, -2.0f);
        this.setRotationAngle(strand1, -0.3491f, 0.0f, 0.3491f);
        this.Head.func_78792_a(strand1);
        strand1.field_78804_l.add(new ModelBox(strand1, 38, 2, 1.9972f, -1.971f, -3.2188f, 2, 4, 2, 0.0f));
        ModelRenderer strand2 = new ModelRenderer((ModelBase)this);
        strand2.func_78793_a(0.0f, -5.0f, -2.0f);
        this.setRotationAngle(strand2, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(strand2);
        strand2.field_78804_l.add(new ModelBox(strand2, 38, 2, 1.9972f, -1.971f, -3.2188f, 2, 3, 2, 0.0f));
        ModelRenderer strand3 = new ModelRenderer((ModelBase)this);
        strand3.func_78793_a(2.0f, -5.0f, -1.0f);
        this.setRotationAngle(strand3, -0.3491f, 0.0f, -0.4363f);
        this.Head.func_78792_a(strand3);
        strand3.field_78804_l.add(new ModelBox(strand3, 38, 2, 1.5441f, -0.9985f, -3.7609f, 2, 3, 2, 0.0f));
        ModelRenderer strand4 = new ModelRenderer((ModelBase)this);
        strand4.func_78793_a(-6.0f, -7.5f, -0.9f);
        this.setRotationAngle(strand4, -0.3491f, 0.0f, 0.4363f);
        this.Head.func_78792_a(strand4);
        strand4.field_78804_l.add(new ModelBox(strand4, 38, 2, 1.5441f, -0.9985f, -3.7609f, 2, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.LArm.field_78809_i = true;
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.LLeg.field_78809_i = true;
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
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

