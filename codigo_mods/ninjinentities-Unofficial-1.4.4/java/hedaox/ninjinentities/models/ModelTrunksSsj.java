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

public class ModelTrunksSsj
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scale = 1.0f;

    public ModelTrunksSsj(float _scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(-1.0f, -9.0f, 0.0f);
        this.setRotationAngle(hair1, -0.0873f, 0.0f, -0.6981f);
        this.Head.func_78792_a(hair1);
        hair1.field_78804_l.add(new ModelBox(hair1, 42, 2, -2.5133f, -4.474f, -2.0714f, 3, 6, 3, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(1.0f, -9.0f, 0.0f);
        this.setRotationAngle(hair2, -0.0873f, 0.0f, 0.6981f);
        this.Head.func_78792_a(hair2);
        hair2.field_78804_l.add(new ModelBox(hair2, 42, 2, -0.4867f, -4.474f, -2.0714f, 3, 6, 3, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(1.8f, -9.0f, -1.6f);
        this.setRotationAngle(hair3, -0.0873f, 0.0f, 0.8726f);
        this.Head.func_78792_a(hair3);
        hair3.field_78804_l.add(new ModelBox(hair3, 42, 2, -0.4867f, -3.474f, -2.0714f, 3, 5, 3, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(-1.8f, -9.0f, -1.6f);
        this.setRotationAngle(hair4, -0.0873f, 0.0f, -0.8726f);
        this.Head.func_78792_a(hair4);
        hair4.field_78804_l.add(new ModelBox(hair4, 42, 2, -2.5133f, -3.474f, -2.0714f, 3, 5, 3, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(-1.8f, -9.0f, 2.9f);
        this.setRotationAngle(hair5, -0.0873f, 0.0f, -0.4363f);
        this.Head.func_78792_a(hair5);
        hair5.field_78804_l.add(new ModelBox(hair5, 42, 2, -2.5133f, -3.474f, -2.0714f, 3, 5, 3, 0.0f));
        ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
        hair6.func_78793_a(1.8f, -9.0f, 2.9f);
        this.setRotationAngle(hair6, -0.0873f, 0.0f, 0.4363f);
        this.Head.func_78792_a(hair6);
        hair6.field_78804_l.add(new ModelBox(hair6, 42, 2, -0.4867f, -3.474f, -2.0714f, 3, 5, 3, 0.0f));
        ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
        hair7.func_78793_a(-0.2f, -9.0f, 2.9f);
        this.setRotationAngle(hair7, -0.4364f, -0.6981f, 0.6981f);
        this.Head.func_78792_a(hair7);
        hair7.field_78804_l.add(new ModelBox(hair7, 42, 2, 0.5133f, -4.474f, -2.0714f, 2, 6, 2, 0.0f));
        ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
        hair8.func_78793_a(0.2f, -9.0f, 2.9f);
        this.setRotationAngle(hair8, -0.4364f, 0.6981f, -0.6981f);
        this.Head.func_78792_a(hair8);
        hair8.field_78804_l.add(new ModelBox(hair8, 42, 2, -2.5133f, -4.474f, -2.0714f, 2, 6, 2, 0.0f));
        ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
        hair9.func_78793_a(1.8f, -9.0f, -1.6f);
        this.setRotationAngle(hair9, -0.0873f, 0.0f, 0.8726f);
        this.Head.func_78792_a(hair9);
        hair9.field_78804_l.add(new ModelBox(hair9, 42, 2, 1.6882f, -3.1308f, -0.0337f, 3, 5, 3, 0.0f));
        ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
        hair10.func_78793_a(-1.8f, -9.0f, -1.6f);
        this.setRotationAngle(hair10, -0.0873f, 0.0f, -0.8726f);
        this.Head.func_78792_a(hair10);
        hair10.field_78804_l.add(new ModelBox(hair10, 42, 2, -4.6882f, -3.1308f, -0.0337f, 3, 5, 3, 0.0f));
        ModelRenderer hair11 = new ModelRenderer((ModelBase)this);
        hair11.func_78793_a(-2.8f, -9.0f, 1.1f);
        this.setRotationAngle(hair11, -0.0873f, 0.0f, -1.2217f);
        this.Head.func_78792_a(hair11);
        hair11.field_78804_l.add(new ModelBox(hair11, 42, 2, -4.6882f, -3.1308f, -0.0337f, 3, 5, 3, 0.0f));
        ModelRenderer hair12 = new ModelRenderer((ModelBase)this);
        hair12.func_78793_a(2.8f, -9.0f, 1.1f);
        this.setRotationAngle(hair12, -0.0873f, 0.0f, 1.2217f);
        this.Head.func_78792_a(hair12);
        hair12.field_78804_l.add(new ModelBox(hair12, 42, 2, 1.6882f, -3.1308f, -0.0337f, 3, 5, 3, 0.0f));
        ModelRenderer hair13 = new ModelRenderer((ModelBase)this);
        hair13.func_78793_a(-1.2f, -7.0f, 2.1f);
        this.setRotationAngle(hair13, -0.4364f, 0.0f, 0.0872f);
        this.Head.func_78792_a(hair13);
        hair13.field_78804_l.add(new ModelBox(hair13, 42, 2, 0.6882f, -3.1308f, -0.0337f, 4, 5, 3, 0.0f));
        ModelRenderer hair14 = new ModelRenderer((ModelBase)this);
        hair14.func_78793_a(1.2f, -7.0f, 2.1f);
        this.setRotationAngle(hair14, -0.4364f, 0.0f, -0.0872f);
        this.Head.func_78792_a(hair14);
        hair14.field_78804_l.add(new ModelBox(hair14, 42, 2, -4.6882f, -3.1308f, -0.0337f, 4, 5, 3, 0.0f));
        ModelRenderer strand1 = new ModelRenderer((ModelBase)this);
        strand1.func_78793_a(-4.0f, -8.0f, -2.0f);
        this.setRotationAngle(strand1, -0.3491f, 0.0f, 0.7854f);
        this.Head.func_78792_a(strand1);
        strand1.field_78804_l.add(new ModelBox(strand1, 38, 2, 1.6436f, -1.6388f, -3.0979f, 1, 5, 1, 0.0f));
        ModelRenderer strand2 = new ModelRenderer((ModelBase)this);
        strand2.func_78793_a(4.0f, -8.0f, -2.0f);
        this.setRotationAngle(strand2, -0.3491f, 0.0f, -0.7854f);
        this.Head.func_78792_a(strand2);
        strand2.field_78804_l.add(new ModelBox(strand2, 38, 2, -2.6436f, -1.6388f, -3.0979f, 1, 5, 1, 0.0f));
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

