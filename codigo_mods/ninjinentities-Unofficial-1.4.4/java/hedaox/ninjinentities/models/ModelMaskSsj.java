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

public class ModelMaskSsj
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;

    public ModelMaskSsj(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer hair1 = new ModelRenderer((ModelBase)this);
        hair1.func_78793_a(-1.0f, -9.0f, 0.0f);
        this.Head.func_78792_a(hair1);
        this.setRotationAngle(hair1, -0.0873f, 0.0f, 0.6109f);
        hair1.field_78804_l.add(new ModelBox(hair1, 42, 2, -1.6854f, -5.7188f, -2.0928f, 4, 8, 4, 0.0f));
        ModelRenderer hair2 = new ModelRenderer((ModelBase)this);
        hair2.func_78793_a(-3.0f, -10.0f, 0.0f);
        this.Head.func_78792_a(hair2);
        this.setRotationAngle(hair2, 0.0f, 0.0f, 0.2618f);
        hair2.field_78804_l.add(new ModelBox(hair2, 45, 2, -0.0173f, -2.9478f, -3.0f, 3, 6, 3, 0.0f));
        ModelRenderer hair3 = new ModelRenderer((ModelBase)this);
        hair3.func_78793_a(1.0f, -8.0f, 0.0f);
        this.Head.func_78792_a(hair3);
        this.setRotationAngle(hair3, 0.0f, 0.0f, -0.0873f);
        hair3.field_78804_l.add(new ModelBox(hair3, 45, 2, 0.0865f, -3.6409f, -3.0f, 3, 6, 3, 0.0f));
        ModelRenderer hair4 = new ModelRenderer((ModelBase)this);
        hair4.func_78793_a(2.0f, -7.0f, 0.0f);
        this.Head.func_78792_a(hair4);
        this.setRotationAngle(hair4, 0.0f, 0.0873f, 0.2618f);
        hair4.field_78804_l.add(new ModelBox(hair4, 45, 2, 0.9829f, -3.6318f, -3.0068f, 2, 6, 3, 0.0f));
        ModelRenderer hair5 = new ModelRenderer((ModelBase)this);
        hair5.func_78793_a(-5.0f, -6.0f, -2.0f);
        this.Head.func_78792_a(hair5);
        this.setRotationAngle(hair5, 2.7925f, 0.0873f, -0.6109f);
        hair5.field_78804_l.add(new ModelBox(hair5, 45, 2, 0.1686f, -3.735f, -3.1488f, 3, 6, 3, 0.0f));
        ModelRenderer hair6 = new ModelRenderer((ModelBase)this);
        hair6.func_78793_a(-5.0f, -7.0f, 4.0f);
        this.Head.func_78792_a(hair6);
        this.setRotationAngle(hair6, -0.3491f, 0.0f, -0.4363f);
        hair6.field_78804_l.add(new ModelBox(hair6, 45, 2, 1.9367f, -3.3812f, -3.3118f, 3, 6, 3, 0.0f));
        ModelRenderer hair7 = new ModelRenderer((ModelBase)this);
        hair7.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.Head.func_78792_a(hair7);
        this.setRotationAngle(hair7, -0.3491f, 0.0f, 0.5236f);
        hair7.field_78804_l.add(new ModelBox(hair7, 45, 2, 1.6599f, -2.6858f, -5.1871f, 3, 6, 5, 0.0f));
        ModelRenderer hair8 = new ModelRenderer((ModelBase)this);
        hair8.func_78793_a(-5.0f, -7.0f, 2.0f);
        this.Head.func_78792_a(hair8);
        this.setRotationAngle(hair8, 2.7925f, 0.0873f, -0.4363f);
        hair8.field_78804_l.add(new ModelBox(hair8, 45, 2, 0.1235f, -3.7117f, -3.1362f, 3, 6, 3, 0.0f));
        ModelRenderer hair9 = new ModelRenderer((ModelBase)this);
        hair9.func_78793_a(3.0f, -8.0f, 2.0f);
        this.Head.func_78792_a(hair9);
        this.setRotationAngle(hair9, 2.7925f, 0.0873f, 0.5236f);
        hair9.field_78804_l.add(new ModelBox(hair9, 45, 2, -0.1522f, -3.7313f, -3.1176f, 3, 6, 3, 0.0f));
        ModelRenderer hair10 = new ModelRenderer((ModelBase)this);
        hair10.func_78793_a(0.0f, -2.0f, 6.0f);
        this.Head.func_78792_a(hair10);
        this.setRotationAngle(hair10, -0.5236f, 0.3491f, 0.0f);
        hair10.field_78804_l.add(new ModelBox(hair10, 38, 2, 0.9972f, -4.9887f, -4.3085f, 3, 7, 3, 0.0f));
        ModelRenderer hair11 = new ModelRenderer((ModelBase)this);
        hair11.func_78793_a(-4.0f, -2.0f, 4.0f);
        this.Head.func_78792_a(hair11);
        this.setRotationAngle(hair11, -0.5236f, -0.2618f, 0.0f);
        hair11.field_78804_l.add(new ModelBox(hair11, 38, 2, 1.1972f, -5.1887f, -4.3085f, 3, 7, 3, 0.0f));
        ModelRenderer strand1 = new ModelRenderer((ModelBase)this);
        strand1.func_78793_a(-4.4864f, -8.2207f, -2.0684f);
        this.Head.func_78792_a(strand1);
        this.setRotationAngle(strand1, -0.3491f, 0.0f, 0.7854f);
        strand1.field_78804_l.add(new ModelBox(strand1, 38, 2, 1.7851f, -3.1703f, -3.2914f, 2, 5, 2, 0.0f));
        ModelRenderer strand2 = new ModelRenderer((ModelBase)this);
        strand2.func_78793_a(0.0f, -5.0f, -2.0f);
        this.Head.func_78792_a(strand2);
        this.setRotationAngle(strand2, -0.3491f, 0.0f, -0.4363f);
        strand2.field_78804_l.add(new ModelBox(strand2, 38, 2, 1.924f, -2.3265f, -3.3118f, 2, 4, 2, 0.0f));
        ModelRenderer strand3 = new ModelRenderer((ModelBase)this);
        strand3.func_78793_a(2.0f, -5.4f, -1.0f);
        this.Head.func_78792_a(strand3);
        this.setRotationAngle(strand3, -0.3491f, 0.0f, -0.4363f);
        strand3.field_78804_l.add(new ModelBox(strand3, 38, 2, 1.4709f, -1.254f, -3.6539f, 2, 3, 2, 0.0f));
        ModelRenderer strand4 = new ModelRenderer((ModelBase)this);
        strand4.func_78793_a(-6.0f, -7.5f, -0.9f);
        this.Head.func_78792_a(strand4);
        this.setRotationAngle(strand4, -0.3491f, 0.0f, 0.4363f);
        strand4.field_78804_l.add(new ModelBox(strand4, 38, 2, 1.4173f, -1.254f, -3.8539f, 2, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.2f, 5.4f, -0.2f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -4.2f, -5.4f, -1.8f, 8, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(-2.2f, 6.6f, 0.2f);
        this.LLeg.field_78809_i = true;
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, 2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.2f, -3.4f, 0.2f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(4.8f, -3.4f, 0.2f);
        this.LArm.field_78809_i = true;
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        float scale = 1.0f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(scale / 0.45f, 1.88f)))), (float)0.0f);
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

