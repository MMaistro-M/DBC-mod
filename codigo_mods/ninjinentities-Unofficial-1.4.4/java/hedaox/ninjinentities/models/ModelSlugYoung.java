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

public class ModelSlugYoung
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

    public ModelSlugYoung(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 20, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer helmet = new ModelRenderer((ModelBase)this);
        helmet.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(helmet);
        helmet.field_78804_l.add(new ModelBox(helmet, 24, 12, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f));
        helmet.field_78804_l.add(new ModelBox(helmet, 32, 28, -1.0f, -9.0f, -5.0f, 2, 2, 1, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 16, 56, 4.0f, -5.0f, -3.0f, 1, 3, 4, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 48, 12, -5.0f, -5.0f, -3.0f, 1, 3, 4, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 26, 56, 4.0f, -2.0f, -4.0f, 1, 1, 4, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 55, 40, -5.0f, -2.0f, -4.0f, 1, 1, 4, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 28, 3, 4.0f, -1.0f, -5.0f, 1, 1, 2, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 28, 0, -5.0f, -1.0f, -5.0f, 1, 1, 2, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 0, 23, 4.0f, -6.0f, -2.0f, 1, 1, 2, 0.0f));
        helmet.field_78804_l.add(new ModelBox(helmet, 0, 20, -5.0f, -6.0f, -2.0f, 1, 1, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 36, -4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f));
        ModelRenderer ba = new ModelRenderer((ModelBase)this);
        ba.func_78793_a(3.5f, 18.4f, -3.2f);
        this.Body.func_78792_a(ba);
        ba.field_78804_l.add(new ModelBox(ba, 27, 31, -8.0f, -7.0f, 0.8f, 9, 10, 5, 0.0f));
        ModelRenderer bodyChild_6 = new ModelRenderer((ModelBase)this);
        bodyChild_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(bodyChild_6, 0.1571f, 0.0f, 0.0f);
        this.Body.func_78792_a(bodyChild_6);
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 55, 24, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 52, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 44, 46, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 54, 0, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
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

