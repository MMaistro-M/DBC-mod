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

public class ModelBrolyZBio
extends ModelBase {
    private final ModelRenderer Body;
    private final ModelRenderer Head;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelBrolyZBio(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(-3.8f, 5.4f, 3.8f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -0.2f, -5.4f, -5.8f, 8, 12, 4, 0.0f));
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer hair_1 = new ModelRenderer((ModelBase)this);
        hair_1.func_78793_a(-4.0f, -11.0f, 0.0f);
        this.setRotationAngle(hair_1, -0.7854f, 0.0f, 1.6581f);
        this.Head.func_78792_a(hair_1);
        ModelRenderer hair_18 = new ModelRenderer((ModelBase)this);
        hair_18.func_78793_a(0.0f, -5.0f, -2.5f);
        this.setRotationAngle(hair_18, -0.3491f, -0.1745f, -0.7854f);
        this.Head.func_78792_a(hair_18);
        ModelRenderer hair_3 = new ModelRenderer((ModelBase)this);
        hair_3.func_78793_a(3.0f, -7.0f, 2.0f);
        this.setRotationAngle(hair_3, -0.5236f, 0.0873f, 0.2618f);
        this.Head.func_78792_a(hair_3);
        ModelRenderer hair_19 = new ModelRenderer((ModelBase)this);
        hair_19.func_78793_a(-5.0f, -10.0f, -2.5f);
        this.setRotationAngle(hair_19, -0.3491f, -0.1745f, 0.6981f);
        this.Head.func_78792_a(hair_19);
        ModelRenderer hair_2 = new ModelRenderer((ModelBase)this);
        hair_2.func_78793_a(4.0f, -11.0f, -1.0f);
        this.setRotationAngle(hair_2, -0.6981f, 0.2618f, -1.9199f);
        this.Head.func_78792_a(hair_2);
        ModelRenderer hair_16 = new ModelRenderer((ModelBase)this);
        hair_16.func_78793_a(0.5f, 0.8f, 7.4f);
        this.setRotationAngle(hair_16, 0.3491f, 0.0f, -0.5236f);
        this.Head.func_78792_a(hair_16);
        hair_16.field_78804_l.add(new ModelBox(hair_16, 40, 3, 1.9972f, -6.6289f, -4.1585f, 3, 7, 3, 0.0f));
        ModelRenderer hair_12 = new ModelRenderer((ModelBase)this);
        hair_12.func_78793_a(7.5f, -8.2f, 2.4f);
        this.setRotationAngle(hair_12, -0.2618f, -0.2618f, 2.8798f);
        this.Head.func_78792_a(hair_12);
        hair_12.field_78804_l.add(new ModelBox(hair_12, 40, 3, 3.4302f, -6.1456f, -4.9624f, 2, 7, 3, 0.0f));
        ModelRenderer hair_17 = new ModelRenderer((ModelBase)this);
        hair_17.func_78793_a(-2.0f, -8.0f, -2.0f);
        this.setRotationAngle(hair_17, -0.3491f, -0.0873f, 0.2618f);
        this.Head.func_78792_a(hair_17);
        ModelRenderer hair_13 = new ModelRenderer((ModelBase)this);
        hair_13.func_78793_a(-7.5f, -8.2f, 2.4f);
        this.setRotationAngle(hair_13, -0.2618f, 0.2618f, -2.8798f);
        this.Head.func_78792_a(hair_13);
        hair_13.field_78804_l.add(new ModelBox(hair_13, 41, 3, -5.4302f, -6.1456f, -4.9624f, 2, 7, 3, 0.0f));
        ModelRenderer hair_9 = new ModelRenderer((ModelBase)this);
        hair_9.func_78793_a(-4.0f, -7.0f, 2.0f);
        this.setRotationAngle(hair_9, 2.7925f, -0.0873f, -1.2217f);
        this.Head.func_78792_a(hair_9);
        ModelRenderer hair_14 = new ModelRenderer((ModelBase)this);
        hair_14.func_78793_a(3.5f, -3.2f, 7.4f);
        this.setRotationAngle(hair_14, 0.3491f, 0.0f, 0.0f);
        this.Head.func_78792_a(hair_14);
        hair_14.field_78804_l.add(new ModelBox(hair_14, 40, 2, -4.9972f, -4.7495f, -4.8426f, 3, 9, 3, 0.0f));
        ModelRenderer hair_5 = new ModelRenderer((ModelBase)this);
        hair_5.func_78793_a(2.0f, -7.0f, 1.0f);
        this.setRotationAngle(hair_5, 2.7925f, -0.0873f, 1.4836f);
        this.Head.func_78792_a(hair_5);
        ModelRenderer hair_8 = new ModelRenderer((ModelBase)this);
        hair_8.func_78793_a(4.0f, -7.0f, 2.0f);
        this.setRotationAngle(hair_8, 2.7925f, 0.0873f, 1.2217f);
        this.Head.func_78792_a(hair_8);
        ModelRenderer hair_6 = new ModelRenderer((ModelBase)this);
        hair_6.func_78793_a(-1.0f, -10.0f, 4.0f);
        this.setRotationAngle(hair_6, -0.3491f, 0.0f, 0.5236f);
        this.Head.func_78792_a(hair_6);
        ModelRenderer hair_7 = new ModelRenderer((ModelBase)this);
        hair_7.func_78793_a(1.0f, -10.0f, 4.0f);
        this.setRotationAngle(hair_7, -0.3491f, 0.0f, -0.5236f);
        this.Head.func_78792_a(hair_7);
        ModelRenderer hair = new ModelRenderer((ModelBase)this);
        hair.func_78793_a(0.0f, -11.0f, 1.0f);
        this.setRotationAngle(hair, 0.5236f, 0.4363f, 0.6109f);
        this.Head.func_78792_a(hair);
        ModelRenderer hair_10 = new ModelRenderer((ModelBase)this);
        hair_10.func_78793_a(-3.0f, -5.0f, 1.0f);
        this.setRotationAngle(hair_10, -2.3562f, -0.5236f, 0.0f);
        this.Head.func_78792_a(hair_10);
        hair_10.field_78804_l.add(new ModelBox(hair_10, 40, 2, 0.9972f, -7.0431f, -2.7443f, 3, 8, 3, 0.0f));
        hair_10.field_78804_l.add(new ModelBox(hair_10, 41, 2, -0.0028f, -5.8184f, -1.5195f, 3, 8, 3, 0.0f));
        ModelRenderer hair_11 = new ModelRenderer((ModelBase)this);
        hair_11.func_78793_a(4.0f, -5.0f, 1.0f);
        this.setRotationAngle(hair_11, -2.5307f, 0.2618f, 0.0f);
        this.Head.func_78792_a(hair_11);
        hair_11.field_78804_l.add(new ModelBox(hair_11, 41, 2, -3.9972f, -7.2672f, -3.0113f, 3, 8, 3, 0.0f));
        ModelRenderer hair_15 = new ModelRenderer((ModelBase)this);
        hair_15.func_78793_a(-0.5f, 0.8f, 7.4f);
        this.setRotationAngle(hair_15, 0.3491f, 0.0f, 0.5236f);
        this.Head.func_78792_a(hair_15);
        hair_15.field_78804_l.add(new ModelBox(hair_15, 41, 3, -4.9972f, -6.6289f, -4.1585f, 3, 7, 3, 0.0f));
        ModelRenderer hair_4 = new ModelRenderer((ModelBase)this);
        hair_4.func_78793_a(-2.0f, -7.0f, 1.0f);
        this.setRotationAngle(hair_4, 2.7925f, 0.0873f, -1.4836f);
        this.Head.func_78792_a(hair_4);
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

