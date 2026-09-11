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

public class ModelKeflaNinjin
extends ModelBase {
    private ModelRenderer Head;
    private ModelRenderer Body;
    private ModelRenderer RArm;
    private ModelRenderer LArm;
    private ModelRenderer RLeg;
    private ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelKeflaNinjin(float _scale, int _form) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.scale = _scale;
        if (_form == 0) {
            this.Head = new ModelRenderer((ModelBase)this);
            this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
            ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
            hair1.func_78793_a(-1.0f, -11.0f, 3.0f);
            this.setRotationAngle(hair1, 0.0f, 0.0f, 0.0873f);
            this.Head.func_78792_a(hair1);
            hair1.field_78804_l.add(new ModelBox(hair1, 45, 2, 0.0603f, -4.658f, -3.0f, 3, 8, 3, 0.0f));
            ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
            hair2.func_78793_a(-3.0f, -10.0f, 0.0f);
            this.setRotationAngle(hair2, 0.0f, 0.0f, -0.6981f);
            this.Head.func_78792_a(hair2);
            hair2.field_78804_l.add(new ModelBox(hair2, 45, 2, -0.9397f, -3.658f, -3.0f, 3, 7, 3, 0.0f));
            ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
            hair3.func_78793_a(0.0f, -8.0f, 0.0f);
            this.setRotationAngle(hair3, 0.0f, 0.0f, 0.1745f);
            this.Head.func_78792_a(hair3);
            hair3.field_78804_l.add(new ModelBox(hair3, 45, 2, 0.0603f, -4.342f, -3.0f, 3, 7, 3, 0.0f));
            ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
            hair4.func_78793_a(2.0f, -7.0f, 0.0f);
            this.setRotationAngle(hair4, 0.0f, 0.0873f, 0.2618f);
            this.Head.func_78792_a(hair4);
            hair4.field_78804_l.add(new ModelBox(hair4, 45, 2, 1.0603f, -4.342f, -3.0f, 2, 7, 3, 0.0f));
            ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
            hair5.func_78793_a(-5.0f, -6.0f, -2.0f);
            this.setRotationAngle(hair5, 2.7925f, 0.0873f, -1.3963f);
            this.Head.func_78792_a(hair5);
            hair5.field_78804_l.add(new ModelBox(hair5, 45, 2, -0.2474f, -2.6696f, -2.7223f, 3, 6, 3, 0.0f));
            ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
            hair6.func_78793_a(-5.0f, -7.0f, 4.0f);
            this.setRotationAngle(hair6, -0.3491f, 0.0f, -0.4363f);
            this.Head.func_78792_a(hair6);
            hair6.field_78804_l.add(new ModelBox(hair6, 45, 2, 1.8099f, -4.1257f, -3.2188f, 3, 7, 3, 0.0f));
            ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
            hair7.func_78793_a(-1.0f, -10.0f, 4.0f);
            this.setRotationAngle(hair7, -0.3491f, 0.0f, 0.5236f);
            this.Head.func_78792_a(hair7);
            hair7.field_78804_l.add(new ModelBox(hair7, 45, 2, 1.8099f, -6.4417f, -5.0982f, 3, 10, 5, 0.0f));
            ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
            hair8.func_78793_a(-5.0f, -7.0f, 2.0f);
            this.setRotationAngle(hair8, 2.7925f, 0.0873f, -0.4363f);
            this.Head.func_78792_a(hair8);
            hair8.field_78804_l.add(new ModelBox(hair8, 45, 2, -0.4847f, -3.7367f, -2.7246f, 3, 7, 3, 0.0f));
            ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
            hair9.func_78793_a(3.0f, -8.0f, 2.0f);
            this.setRotationAngle(hair9, 2.7925f, 0.0873f, 0.5236f);
            this.Head.func_78792_a(hair9);
            hair9.field_78804_l.add(new ModelBox(hair9, 45, 2, 0.3618f, -3.6765f, -2.7815f, 3, 7, 3, 0.0f));
            ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
            hair10.func_78793_a(0.0f, -2.0f, 6.0f);
            this.setRotationAngle(hair10, -0.5236f, 0.3491f, 0.0f);
            this.Head.func_78792_a(hair10);
            hair10.field_78804_l.add(new ModelBox(hair10, 38, 2, 0.9972f, -5.6289f, -4.1585f, 3, 8, 3, 0.0f));
            ModelRenderer hair11 = new ModelRenderer((ModelBase)this);
            hair11.func_78793_a(-4.0f, -2.0f, 4.0f);
            this.setRotationAngle(hair11, -0.5236f, -0.2618f, 0.0f);
            this.Head.func_78792_a(hair11);
            hair11.field_78804_l.add(new ModelBox(hair11, 38, 2, 0.9972f, -5.6289f, -4.1585f, 3, 8, 3, 0.0f));
            ModelRenderer hair12 = new ModelRenderer((ModelBase)this);
            hair12.func_78793_a(-5.0f, 0.0f, 3.0f);
            this.setRotationAngle(hair12, -0.5236f, -0.2618f, -0.7854f);
            this.Head.func_78792_a(hair12);
            hair12.field_78804_l.add(new ModelBox(hair12, 38, 2, 1.9972f, -4.6289f, -4.1585f, 2, 7, 2, 0.0f));
            ModelRenderer hair13 = new ModelRenderer((ModelBase)this);
            hair13.func_78793_a(1.0f, -6.0f, 3.0f);
            this.setRotationAngle(hair13, -0.2618f, -0.2618f, 0.7854f);
            this.Head.func_78792_a(hair13);
            hair13.field_78804_l.add(new ModelBox(hair13, 38, 2, 0.9972f, -5.6289f, -4.1585f, 2, 7, 2, 0.0f));
            ModelRenderer strand1 = new ModelRenderer((ModelBase)this);
            strand1.func_78793_a(-4.0f, -8.0f, -2.0f);
            this.setRotationAngle(strand1, -0.3491f, 0.0f, 0.7854f);
            this.Head.func_78792_a(strand1);
            strand1.field_78804_l.add(new ModelBox(strand1, 38, 2, 0.583f, -1.6421f, -2.7351f, 2, 6, 2, 0.0f));
            ModelRenderer strand2 = new ModelRenderer((ModelBase)this);
            strand2.func_78793_a(1.0f, -5.0f, -2.0f);
            this.setRotationAngle(strand2, -0.3491f, 0.0f, -0.7854f);
            this.Head.func_78792_a(strand2);
            strand2.field_78804_l.add(new ModelBox(strand2, 38, 2, 1.9972f, -1.6421f, -2.7351f, 2, 6, 2, 0.0f));
            ModelRenderer REarring = new ModelRenderer((ModelBase)this);
            REarring.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotationAngle(REarring, 0.0f, -0.5236f, 0.0f);
            this.Head.func_78792_a(REarring);
            REarring.field_78804_l.add(new ModelBox(REarring, 32, 2, -5.0f, -3.0f, 0.0f, 0, 2, 1, 0.0f));
            ModelRenderer LEarring = new ModelRenderer((ModelBase)this);
            LEarring.func_78793_a(0.0f, 0.0f, 0.0f);
            LEarring.field_78809_i = true;
            this.setRotationAngle(LEarring, 0.0f, 0.5236f, 0.0f);
            this.Head.func_78792_a(LEarring);
            LEarring.field_78804_l.add(new ModelBox(LEarring, 32, 2, 5.0f, -3.0f, 0.0f, 0, 2, 1, 0.0f));
            this.Body = new ModelRenderer((ModelBase)this);
            this.Body.func_78793_a(-3.8f, 5.4f, 3.8f);
            this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -0.2f, -5.4f, -5.8f, 8, 12, 4, 0.0f));
            this.RArm = new ModelRenderer((ModelBase)this);
            this.RArm.func_78793_a(-0.2f, -3.4f, -3.8f);
            this.Body.func_78792_a(this.RArm);
            this.RArm.field_78804_l.add(new ModelBox(this.RArm, 41, 16, -3.0f, -2.0f, -2.0f, 3, 12, 4, 0.0f));
            this.LArm = new ModelRenderer((ModelBase)this);
            this.LArm.func_78793_a(7.8f, -3.4f, -3.8f);
            this.LArm.field_78809_i = true;
            this.Body.func_78792_a(this.LArm);
            this.LArm.field_78804_l.add(new ModelBox(this.LArm, 41, 16, 0.0f, -2.0f, -2.0f, 3, 12, 4, 0.0f));
            this.RLeg = new ModelRenderer((ModelBase)this);
            this.RLeg.func_78793_a(1.8f, 6.6f, -3.8f);
            this.Body.func_78792_a(this.RLeg);
            this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
            this.LLeg = new ModelRenderer((ModelBase)this);
            this.LLeg.func_78793_a(5.8f, 6.6f, -3.8f);
            this.LLeg.field_78809_i = true;
            this.Body.func_78792_a(this.LLeg);
            this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        } else if (_form == 1) {
            this.Head = new ModelRenderer((ModelBase)this);
            this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
            this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
            ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
            hair1.func_78793_a(-1.0f, -11.0f, 3.0f);
            this.setRotationAngle(hair1, 0.0f, 0.0f, 0.2618f);
            this.Head.func_78792_a(hair1);
            hair1.field_78804_l.add(new ModelBox(hair1, 45, 2, 0.0603f, -3.658f, -3.0f, 3, 7, 3, 0.0f));
            ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
            hair2.func_78793_a(-3.0f, -10.0f, 0.0f);
            this.setRotationAngle(hair2, 0.0f, 0.0f, -0.2618f);
            this.Head.func_78792_a(hair2);
            hair2.field_78804_l.add(new ModelBox(hair2, 45, 2, 0.0603f, -3.658f, -3.0f, 3, 7, 3, 0.0f));
            ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
            hair3.func_78793_a(0.0f, -8.0f, 0.0f);
            this.setRotationAngle(hair3, 0.0f, 0.0f, 0.1745f);
            this.Head.func_78792_a(hair3);
            hair3.field_78804_l.add(new ModelBox(hair3, 45, 2, 0.0603f, -4.342f, -3.0f, 3, 7, 3, 0.0f));
            ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
            hair4.func_78793_a(2.0f, -7.0f, 0.0f);
            this.setRotationAngle(hair4, 0.0f, 0.0873f, 0.2618f);
            this.Head.func_78792_a(hair4);
            hair4.field_78804_l.add(new ModelBox(hair4, 45, 2, 1.0603f, -4.342f, -3.0f, 2, 7, 3, 0.0f));
            ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
            hair5.func_78793_a(-5.0f, -6.0f, -2.0f);
            this.setRotationAngle(hair5, 2.7925f, 0.0873f, -0.6109f);
            this.Head.func_78792_a(hair5);
            hair5.field_78804_l.add(new ModelBox(hair5, 45, 2, -0.2474f, -2.6696f, -2.7223f, 3, 6, 3, 0.0f));
            ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
            hair6.func_78793_a(-5.0f, -7.0f, 4.0f);
            this.setRotationAngle(hair6, -0.3491f, 0.0f, -0.4363f);
            this.Head.func_78792_a(hair6);
            hair6.field_78804_l.add(new ModelBox(hair6, 45, 2, 1.8099f, -4.1257f, -3.2188f, 3, 7, 3, 0.0f));
            ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
            hair7.func_78793_a(-1.0f, -10.0f, 4.0f);
            this.setRotationAngle(hair7, -0.3491f, 0.0f, 0.5236f);
            this.Head.func_78792_a(hair7);
            hair7.field_78804_l.add(new ModelBox(hair7, 45, 2, 1.8099f, -2.4417f, -5.0982f, 3, 6, 5, 0.0f));
            ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
            hair8.func_78793_a(-5.0f, -7.0f, 2.0f);
            this.setRotationAngle(hair8, 2.7925f, 0.0873f, -0.4363f);
            this.Head.func_78792_a(hair8);
            hair8.field_78804_l.add(new ModelBox(hair8, 45, 2, -0.4847f, -3.7367f, -2.7246f, 3, 7, 3, 0.0f));
            ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
            hair9.func_78793_a(3.0f, -8.0f, 2.0f);
            this.setRotationAngle(hair9, 2.7925f, 0.0873f, 0.5236f);
            this.Head.func_78792_a(hair9);
            hair9.field_78804_l.add(new ModelBox(hair9, 45, 2, 0.3618f, -3.6765f, -2.7815f, 3, 7, 3, 0.0f));
            ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
            hair10.func_78793_a(0.0f, -2.0f, 6.0f);
            this.setRotationAngle(hair10, -0.5236f, 0.3491f, 0.0f);
            this.Head.func_78792_a(hair10);
            hair10.field_78804_l.add(new ModelBox(hair10, 38, 2, 0.9972f, -5.6289f, -4.1585f, 3, 8, 3, 0.0f));
            ModelRenderer hair11 = new ModelRenderer((ModelBase)this);
            hair11.func_78793_a(-4.0f, -2.0f, 4.0f);
            this.setRotationAngle(hair11, -0.5236f, -0.2618f, 0.0f);
            this.Head.func_78792_a(hair11);
            hair11.field_78804_l.add(new ModelBox(hair11, 38, 2, 0.9972f, -5.6289f, -4.1585f, 3, 8, 3, 0.0f));
            ModelRenderer hair12 = new ModelRenderer((ModelBase)this);
            hair12.func_78793_a(-5.0f, 0.0f, 3.0f);
            this.setRotationAngle(hair12, -0.5236f, -0.2618f, -0.7854f);
            this.Head.func_78792_a(hair12);
            hair12.field_78804_l.add(new ModelBox(hair12, 38, 2, 1.9972f, -4.6289f, -4.1585f, 2, 7, 2, 0.0f));
            ModelRenderer hair13 = new ModelRenderer((ModelBase)this);
            hair13.func_78793_a(1.0f, -6.0f, 3.0f);
            this.setRotationAngle(hair13, -0.2618f, -0.2618f, 0.7854f);
            this.Head.func_78792_a(hair13);
            hair13.field_78804_l.add(new ModelBox(hair13, 38, 2, 1.9972f, -4.6289f, -4.1585f, 2, 7, 2, 0.0f));
            ModelRenderer hair14 = new ModelRenderer((ModelBase)this);
            hair14.func_78793_a(1.0f, -6.0f, 3.0f);
            this.setRotationAngle(hair14, -0.2618f, -0.2618f, 1.3963f);
            this.Head.func_78792_a(hair14);
            hair14.field_78804_l.add(new ModelBox(hair14, 38, 2, 2.256f, -4.8789f, -3.2255f, 2, 7, 2, 0.0f));
            ModelRenderer hair15 = new ModelRenderer((ModelBase)this);
            hair15.func_78793_a(-3.0f, -6.0f, 6.0f);
            this.setRotationAngle(hair15, 0.4363f, 0.0f, 1.8326f);
            this.Head.func_78792_a(hair15);
            hair15.field_78804_l.add(new ModelBox(hair15, 38, 2, 2.256f, -4.8789f, -3.2255f, 2, 7, 2, 0.0f));
            ModelRenderer strand1 = new ModelRenderer((ModelBase)this);
            strand1.func_78793_a(-4.0f, -8.0f, -2.0f);
            this.setRotationAngle(strand1, -0.3491f, 0.0f, 0.7854f);
            this.Head.func_78792_a(strand1);
            strand1.field_78804_l.add(new ModelBox(strand1, 38, 2, 0.583f, -1.6421f, -2.7351f, 2, 6, 2, 0.0f));
            ModelRenderer strand2 = new ModelRenderer((ModelBase)this);
            strand2.func_78793_a(1.0f, -5.0f, -2.0f);
            this.setRotationAngle(strand2, -0.3491f, 0.0f, -0.7854f);
            this.Head.func_78792_a(strand2);
            strand2.field_78804_l.add(new ModelBox(strand2, 38, 2, 1.9972f, -1.6421f, -2.7351f, 2, 6, 2, 0.0f));
            ModelRenderer REarring = new ModelRenderer((ModelBase)this);
            REarring.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotationAngle(REarring, 0.0f, -0.5236f, 0.0f);
            this.Head.func_78792_a(REarring);
            REarring.field_78804_l.add(new ModelBox(REarring, 32, 2, -5.0f, -3.0f, 0.0f, 0, 2, 1, 0.0f));
            ModelRenderer LEarring = new ModelRenderer((ModelBase)this);
            LEarring.func_78793_a(0.0f, 0.0f, 0.0f);
            LEarring.field_78809_i = true;
            this.setRotationAngle(LEarring, 0.0f, 0.5236f, 0.0f);
            this.Head.func_78792_a(LEarring);
            LEarring.field_78804_l.add(new ModelBox(LEarring, 32, 2, 5.0f, -3.0f, 0.0f, 0, 2, 1, 0.0f));
            this.Body = new ModelRenderer((ModelBase)this);
            this.Body.func_78793_a(-3.8f, 5.4f, 3.8f);
            this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -0.2f, -5.4f, -5.8f, 8, 12, 4, 0.0f));
            this.RArm = new ModelRenderer((ModelBase)this);
            this.RArm.func_78793_a(-0.2f, -3.4f, -3.8f);
            this.Body.func_78792_a(this.RArm);
            this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
            this.LArm = new ModelRenderer((ModelBase)this);
            this.LArm.func_78793_a(7.8f, -3.4f, -3.8f);
            this.LArm.field_78809_i = true;
            this.Body.func_78792_a(this.LArm);
            this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 0.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
            this.RLeg = new ModelRenderer((ModelBase)this);
            this.RLeg.func_78793_a(1.8f, 6.6f, -3.8f);
            this.Body.func_78792_a(this.RLeg);
            this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
            this.LLeg = new ModelRenderer((ModelBase)this);
            this.LLeg.func_78793_a(5.8f, 6.6f, -3.8f);
            this.LLeg.field_78809_i = true;
            this.Body.func_78792_a(this.LLeg);
            this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        }
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

