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

public class ModelGinger
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

    public ModelGinger(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f));
        ModelRenderer headChild_2 = new ModelRenderer((ModelBase)this);
        headChild_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(headChild_2, 0.0f, 0.5236f, 0.0f);
        this.Head.func_78792_a(headChild_2);
        headChild_2.field_78804_l.add(new ModelBox(headChild_2, 34, 18, 4.0f, -2.0f, -1.0f, 0, 4, 6, 0.0f));
        ModelRenderer headChild_1 = new ModelRenderer((ModelBase)this);
        headChild_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotationAngle(headChild_1, 0.0f, -0.5236f, 0.0f);
        this.Head.func_78792_a(headChild_1);
        headChild_1.field_78804_l.add(new ModelBox(headChild_1, 24, 14, -4.0f, -2.0f, -1.0f, 0, 4, 6, 0.0f));
        ModelRenderer field_78114_d2 = new ModelRenderer((ModelBase)this);
        field_78114_d2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(field_78114_d2);
        field_78114_d2.field_78804_l.add(new ModelBox(field_78114_d2, 0, 48, -4.0f, 3.0f, -4.0f, 8, 2, 8, 0.5f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 16, -4.0f, 4.0f, -2.0f, 8, 10, 4, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(0.0f, -3.0f, -3.0f);
        this.setRotationAngle(bone, 0.2618f, 0.0f, 0.0f);
        this.Body.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 46, 48, -4.0f, 9.0f, 3.0f, 8, 15, 0, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 0, -3.0f, 1.99f, -2.0f, 4, 10, 4, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(0.0f, 0.0f, -0.5f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78809_i = true;
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 18, 24, -3.0f, 0.9203f, -3.0f, 5, 4, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 16, 34, -1.0f, 1.99f, -2.0f, 4, 10, 4, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(0.0f, 0.0f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 18, 24, -2.0f, 0.9203f, -3.0f, 5, 4, 6, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 30, -2.0f, 2.0f, -2.0f, 4, 10, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(1.9f, 12.0f, 0.1f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 32, 34, -2.0f, 2.0f, -2.0f, 4, 10, 4, 0.0f));
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

