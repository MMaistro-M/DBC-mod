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

public class ModelKibito
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Ear2_r1;
    private final ModelRenderer Ear1_r1;
    private final ModelRenderer hair;
    private final ModelRenderer hair19;
    private final ModelRenderer hair19_r1;
    private final ModelRenderer hair18;
    private final ModelRenderer hair18_r1;
    private final ModelRenderer hair15;
    private final ModelRenderer hair15_r1;
    private final ModelRenderer hair13;
    private final ModelRenderer hair13_r1;
    private final ModelRenderer hair12;
    private final ModelRenderer hair11;
    private final ModelRenderer hair10;
    private final ModelRenderer hair2;
    private final ModelRenderer hair3;
    private final ModelRenderer hair15_r2;
    private final ModelRenderer hair4;
    private final ModelRenderer hair12_r1;
    private final ModelRenderer hair5;
    private final ModelRenderer hair15_r3;
    private final ModelRenderer hair6;
    private final ModelRenderer hair15_r4;
    private final ModelRenderer hair7;
    private final ModelRenderer hair15_r5;
    private final ModelRenderer hair8;
    private final ModelRenderer hair15_r6;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelKibito(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.2f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, -0.19f));
        this.Ear2_r1 = new ModelRenderer((ModelBase)this);
        this.Ear2_r1.func_78793_a(2.8f, -1.5f, -2.0f);
        this.Head.func_78792_a(this.Ear2_r1);
        this.Ear2_r1.field_78809_i = true;
        this.setRotationAngle(this.Ear2_r1, 0.0f, -0.5236f, 0.0f);
        this.Ear2_r1.field_78804_l.add(new ModelBox(this.Ear2_r1, 25, 3, 0.1f, -4.0f, -0.4f, 5, 5, 0, -0.19f));
        this.Ear1_r1 = new ModelRenderer((ModelBase)this);
        this.Ear1_r1.func_78793_a(-2.8f, -1.5f, -2.0f);
        this.Head.func_78792_a(this.Ear1_r1);
        this.setRotationAngle(this.Ear1_r1, 0.0f, 0.5236f, 0.0f);
        this.Ear1_r1.field_78804_l.add(new ModelBox(this.Ear1_r1, 25, 3, -5.1f, -4.0f, -0.4f, 5, 5, 0, -0.19f));
        this.hair = new ModelRenderer((ModelBase)this);
        this.hair.func_78793_a(0.0f, 0.1f, -1.7f);
        this.Head.func_78792_a(this.hair);
        this.hair19 = new ModelRenderer((ModelBase)this);
        this.hair19.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair19);
        this.setRotationAngle(this.hair19, -2.9671f, 0.1745f, -0.3491f);
        this.hair19_r1 = new ModelRenderer((ModelBase)this);
        this.hair19_r1.func_78793_a(2.4972f, -7.8289f, -0.6585f);
        this.hair19.func_78792_a(this.hair19_r1);
        this.setRotationAngle(this.hair19_r1, 0.0f, 0.0f, -0.0873f);
        this.hair19_r1.field_78804_l.add(new ModelBox(this.hair19_r1, 46, 2, -1.5f, -3.0f, -1.5f, 3, 6, 3, -0.1f));
        this.hair18 = new ModelRenderer((ModelBase)this);
        this.hair18.func_78793_a(4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair18);
        this.setRotationAngle(this.hair18, -2.9671f, -0.1745f, 0.5236f);
        this.hair18_r1 = new ModelRenderer((ModelBase)this);
        this.hair18_r1.func_78793_a(-2.4972f, -8.6289f, -0.6585f);
        this.hair18.func_78792_a(this.hair18_r1);
        this.setRotationAngle(this.hair18_r1, 0.0f, 0.0f, 0.3054f);
        this.hair18_r1.field_78804_l.add(new ModelBox(this.hair18_r1, 46, 2, -1.5f, -3.0f, -1.5f, 3, 6, 3, -0.1f));
        this.hair15 = new ModelRenderer((ModelBase)this);
        this.hair15.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair15);
        this.setRotationAngle(this.hair15, -2.7053f, 0.1745f, -0.5236f);
        this.hair15_r1 = new ModelRenderer((ModelBase)this);
        this.hair15_r1.func_78793_a(6.9972f, -3.4289f, -0.6585f);
        this.hair15.func_78792_a(this.hair15_r1);
        this.setRotationAngle(this.hair15_r1, -0.192f, 0.0f, -0.1309f);
        this.hair15_r1.field_78804_l.add(new ModelBox(this.hair15_r1, 47, 2, -2.0f, -3.8f, -1.4f, 3, 8, 3, -0.1f));
        this.hair13 = new ModelRenderer((ModelBase)this);
        this.hair13.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair13);
        this.setRotationAngle(this.hair13, 3.1416f, 0.0f, 0.0f);
        this.hair13_r1 = new ModelRenderer((ModelBase)this);
        this.hair13_r1.func_78793_a(3.4972f, -5.2289f, -1.8585f);
        this.hair13.func_78792_a(this.hair13_r1);
        this.setRotationAngle(this.hair13_r1, 0.0f, 0.0f, -0.0873f);
        this.hair13_r1.field_78804_l.add(new ModelBox(this.hair13_r1, 47, 2, -1.5f, -4.0f, -1.5f, 3, 8, 3, -0.1f));
        this.hair12 = new ModelRenderer((ModelBase)this);
        this.hair12.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair12);
        this.setRotationAngle(this.hair12, -2.7053f, 0.0f, 0.0f);
        this.hair12.field_78804_l.add(new ModelBox(this.hair12, 46, 2, 1.9972f, -1.2289f, -3.4585f, 4, 5, 3, -0.1f));
        this.hair11 = new ModelRenderer((ModelBase)this);
        this.hair11.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair11);
        this.setRotationAngle(this.hair11, -2.7053f, -0.2618f, 0.0f);
        this.hair11.field_78804_l.add(new ModelBox(this.hair11, 46, 2, 0.9972f, -5.6289f, -1.1585f, 3, 10, 3, -0.1f));
        this.hair10 = new ModelRenderer((ModelBase)this);
        this.hair10.func_78793_a(0.0f, -2.0f, 6.0f);
        this.hair.func_78792_a(this.hair10);
        this.setRotationAngle(this.hair10, -2.7053f, 0.3491f, 0.0f);
        this.hair10.field_78804_l.add(new ModelBox(this.hair10, 47, 2, 0.9972f, -5.6289f, -0.1585f, 3, 9, 3, -0.1f));
        this.hair2 = new ModelRenderer((ModelBase)this);
        this.hair2.func_78793_a(0.0f, -2.0f, 6.0f);
        this.hair.func_78792_a(this.hair2);
        this.setRotationAngle(this.hair2, -2.8081f, -0.448f, 0.2633f);
        this.hair2.field_78804_l.add(new ModelBox(this.hair2, 47, 2, -3.9972f, -5.6289f, -0.1585f, 3, 9, 3, -0.1f));
        this.hair3 = new ModelRenderer((ModelBase)this);
        this.hair3.func_78793_a(4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair3);
        this.setRotationAngle(this.hair3, -2.7053f, -0.1745f, 0.5236f);
        this.hair15_r2 = new ModelRenderer((ModelBase)this);
        this.hair15_r2.func_78793_a(-6.9972f, -3.4289f, -0.6585f);
        this.hair3.func_78792_a(this.hair15_r2);
        this.setRotationAngle(this.hair15_r2, -0.192f, 0.0f, 0.1309f);
        this.hair15_r2.field_78804_l.add(new ModelBox(this.hair15_r2, 47, 2, -1.0f, -3.8f, -1.4f, 3, 8, 3, -0.1f));
        this.hair4 = new ModelRenderer((ModelBase)this);
        this.hair4.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.hair.func_78792_a(this.hair4);
        this.setRotationAngle(this.hair4, -2.7053f, 0.0f, 0.0f);
        this.hair12_r1 = new ModelRenderer((ModelBase)this);
        this.hair12_r1.func_78793_a(3.9972f, -3.0289f, -1.0585f);
        this.hair4.func_78792_a(this.hair12_r1);
        this.setRotationAngle(this.hair12_r1, -0.1745f, 0.0f, 0.0f);
        this.hair12_r1.field_78804_l.add(new ModelBox(this.hair12_r1, 46, 2, -2.0f, -2.5f, -1.5f, 4, 5, 3, -0.1f));
        this.hair5 = new ModelRenderer((ModelBase)this);
        this.hair5.func_78793_a(4.5f, -0.8f, 4.0f);
        this.hair.func_78792_a(this.hair5);
        this.setRotationAngle(this.hair5, -2.7053f, -0.1745f, 0.5236f);
        this.hair15_r3 = new ModelRenderer((ModelBase)this);
        this.hair15_r3.func_78793_a(-6.9972f, -4.6289f, -0.2585f);
        this.hair5.func_78792_a(this.hair15_r3);
        this.setRotationAngle(this.hair15_r3, -0.192f, 0.0f, 0.1309f);
        this.hair15_r3.field_78804_l.add(new ModelBox(this.hair15_r3, 47, 2, -1.0f, -3.8f, -1.4f, 3, 8, 3, -0.1f));
        this.hair6 = new ModelRenderer((ModelBase)this);
        this.hair6.func_78793_a(4.9f, 1.5f, 4.0f);
        this.hair.func_78792_a(this.hair6);
        this.setRotationAngle(this.hair6, -2.7053f, -0.1745f, 0.5236f);
        this.hair15_r4 = new ModelRenderer((ModelBase)this);
        this.hair15_r4.func_78793_a(-6.9972f, -4.6289f, -0.2585f);
        this.hair6.func_78792_a(this.hair15_r4);
        this.setRotationAngle(this.hair15_r4, -0.192f, 0.0f, 0.1309f);
        this.hair15_r4.field_78804_l.add(new ModelBox(this.hair15_r4, 47, 2, -1.0f, -3.8f, -1.4f, 3, 8, 3, -0.1f));
        this.hair7 = new ModelRenderer((ModelBase)this);
        this.hair7.func_78793_a(-4.5f, -0.8f, 4.0f);
        this.hair.func_78792_a(this.hair7);
        this.setRotationAngle(this.hair7, -2.7053f, 0.1745f, -0.5236f);
        this.hair15_r5 = new ModelRenderer((ModelBase)this);
        this.hair15_r5.func_78793_a(6.9972f, -4.6289f, -0.2585f);
        this.hair7.func_78792_a(this.hair15_r5);
        this.setRotationAngle(this.hair15_r5, -0.192f, 0.0f, -0.1309f);
        this.hair15_r5.field_78804_l.add(new ModelBox(this.hair15_r5, 47, 2, -2.0f, -3.8f, -1.4f, 3, 8, 3, -0.1f));
        this.hair8 = new ModelRenderer((ModelBase)this);
        this.hair8.func_78793_a(-4.9f, 1.5f, 4.0f);
        this.hair.func_78792_a(this.hair8);
        this.setRotationAngle(this.hair8, -2.7053f, 0.1745f, -0.5236f);
        this.hair15_r6 = new ModelRenderer((ModelBase)this);
        this.hair15_r6.func_78793_a(6.9972f, -4.6289f, -0.2585f);
        this.hair8.func_78792_a(this.hair15_r6);
        this.setRotationAngle(this.hair15_r6, -0.192f, 0.0f, -0.1309f);
        this.hair15_r6.field_78804_l.add(new ModelBox(this.hair15_r6, 47, 2, -2.0f, -3.8f, -1.4f, 3, 8, 3, -0.1f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(-4.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78809_i = true;
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 8.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78809_i = true;
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

