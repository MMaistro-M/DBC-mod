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

public class ModelPESSMonster2
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

    public ModelPESSMonster2(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -6.0f, -30.0f, -8.0f, 12, 8, 13, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 19, 60, -3.0f, -31.0f, -10.0f, 2, 3, 1, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 33, 46, 1.0f, -31.0f, -10.0f, 2, 3, 1, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 37, 8, -4.0f, -32.0f, -9.0f, 3, 3, 2, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 7, 1.0f, -32.0f, -9.0f, 3, 3, 2, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 60, -3.5f, -33.0f, -8.0f, 3, 3, 13, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 62, 14, 0.5f, -33.0f, -8.0f, 3, 3, 13, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 29, 47, -5.0f, -32.0f, -8.0f, 4, 2, 13, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 50, 50, 1.0f, -32.0f, -8.0f, 4, 2, 13, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 46, -1.0f, -27.0f, -9.0f, 2, 1, 2, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 47, 8, 1.0f, -26.0f, -9.0f, 1, 1, 2, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 21, -3.0f, -25.0f, -9.0f, 1, 5, 2, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 37, 0, 2.0f, -25.0f, -9.0f, 1, 5, 2, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -3.0f, -20.0f, -8.0f, 1, 3, 1, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 8, 0, 2.0f, -20.0f, -8.0f, 1, 3, 1, 0.0f));
        this.Head.field_78804_l.add(new ModelBox(this.Head, 50, 57, -2.0f, -26.0f, -9.0f, 1, 1, 2, 0.0f));
        ModelRenderer turns_left = new ModelRenderer((ModelBase)this);
        turns_left.func_78793_a(5.5f, -25.0f, 0.0f);
        this.setRotationAngle(turns_left, 0.0f, 0.0f, 1.1345f);
        this.Head.func_78792_a(turns_left);
        turns_left.field_78804_l.add(new ModelBox(turns_left, 40, 31, -4.3202f, -2.5662f, -5.0f, 8, 6, 10, 0.0f));
        ModelRenderer turns_right = new ModelRenderer((ModelBase)this);
        turns_right.func_78793_a(-5.5f, -25.0f, 0.0f);
        this.setRotationAngle(turns_right, 0.0f, 0.0f, -1.1345f);
        this.Head.func_78792_a(turns_right);
        turns_right.field_78804_l.add(new ModelBox(turns_right, 41, 11, -3.5f, -2.5662f, -5.0f, 7, 6, 10, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 88, 68, -5.0f, -22.0f, 6.0f, 10, 11, 3, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 21, -8.0f, -22.0f, -3.0f, 16, 11, 9, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 84, 53, 3.0f, -18.0f, -6.0f, 6, 8, 7, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 77, 84, -9.0f, -18.0f, -6.0f, 6, 8, 7, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 41, -6.0f, -21.0f, -7.0f, 12, 10, 9, 0.0f));
        ModelRenderer tail = new ModelRenderer((ModelBase)this);
        tail.func_78793_a(0.0f, -24.0f, 0.0f);
        this.setRotationAngle(tail, -0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(tail);
        tail.field_78804_l.add(new ModelBox(tail, 1, 114, -2.0f, 8.1953f, 5.5635f, 3, 2, 11, 0.0f));
        ModelRenderer tail2 = new ModelRenderer((ModelBase)this);
        tail2.func_78793_a(-3.0f, -8.8f, 17.0f);
        this.setRotationAngle(tail2, 0.1745f, 0.0f, 0.0f);
        this.Body.func_78792_a(tail2);
        tail2.field_78804_l.add(new ModelBox(tail2, 0, 117, 1.0f, -2.966f, -5.0406f, 3, 2, 9, 0.0f));
        ModelRenderer backpack = new ModelRenderer((ModelBase)this);
        backpack.func_78793_a(0.0f, -27.5f, 7.5f);
        this.setRotationAngle(backpack, 0.2618f, 0.0f, 0.0f);
        this.Body.func_78792_a(backpack);
        backpack.field_78804_l.add(new ModelBox(backpack, 0, 89, -5.0f, -3.2992f, -2.9746f, 10, 9, 3, 0.0f));
        backpack.field_78804_l.add(new ModelBox(backpack, 71, 53, -4.0f, -4.5442f, -2.8359f, 8, 2, 2, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-7.0f, -21.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        ModelRenderer right_arm5 = new ModelRenderer((ModelBase)this);
        right_arm5.func_78793_a(0.5f, 0.0f, -0.5f);
        this.setRotationAngle(right_arm5, 0.0f, 0.0f, -0.3491f);
        this.RArm.func_78792_a(right_arm5);
        right_arm5.field_78804_l.add(new ModelBox(right_arm5, 71, 40, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer right_arm6 = new ModelRenderer((ModelBase)this);
        right_arm6.func_78793_a(-3.5f, 3.0f, -0.5f);
        this.setRotationAngle(right_arm6, 0.0f, 0.0f, -0.9599f);
        this.RArm.func_78792_a(right_arm6);
        right_arm6.field_78804_l.add(new ModelBox(right_arm6, 65, 0, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer right_arm7 = new ModelRenderer((ModelBase)this);
        right_arm7.func_78793_a(-5.5f, 7.0f, -1.5f);
        this.setRotationAngle(right_arm7, 0.0f, -0.2618f, -0.9599f);
        this.RArm.func_78792_a(right_arm7);
        right_arm7.field_78804_l.add(new ModelBox(right_arm7, 60, 65, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer right_arm8 = new ModelRenderer((ModelBase)this);
        right_arm8.func_78793_a(-7.5f, 11.0f, -4.5f);
        this.setRotationAngle(right_arm8, 0.0f, -0.6981f, -0.9599f);
        this.RArm.func_78792_a(right_arm8);
        right_arm8.field_78804_l.add(new ModelBox(right_arm8, 32, 65, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer right_hand_global = new ModelRenderer((ModelBase)this);
        right_hand_global.func_78793_a(-9.0f, 11.5f, -7.0f);
        this.setRotationAngle(right_hand_global, -1.7453f, 2.4435f, 2.3562f);
        this.RArm.func_78792_a(right_hand_global);
        right_hand_global.field_78804_l.add(new ModelBox(right_hand_global, 59, 91, -2.5f, -2.5f, -3.5f, 4, 3, 4, 0.0f));
        ModelRenderer right_hand8 = new ModelRenderer((ModelBase)this);
        right_hand8.func_78793_a(5.75f, -1.0f, -1.5f);
        this.setRotationAngle(right_hand8, 0.0f, 0.0f, -0.4363f);
        right_hand_global.func_78792_a(right_hand8);
        right_hand8.field_78804_l.add(new ModelBox(right_hand8, 0, 0, -4.0326f, -3.2254f, -2.0f, 2, 3, 4, 0.0f));
        ModelRenderer right_hand9 = new ModelRenderer((ModelBase)this);
        right_hand9.func_78793_a(5.75f, -1.0f, -2.5f);
        this.setRotationAngle(right_hand9, 0.0f, 0.0f, -0.8727f);
        right_hand_global.func_78792_a(right_hand9);
        right_hand9.field_78804_l.add(new ModelBox(right_hand9, 50, 0, -1.4059f, -3.9042f, -1.0f, 5, 3, 4, 0.0f));
        right_hand9.field_78804_l.add(new ModelBox(right_hand9, 41, 27, 3.5867f, -2.1772f, 0.0f, 3, 1, 2, 0.0f));
        ModelRenderer right_hand10 = new ModelRenderer((ModelBase)this);
        right_hand10.func_78793_a(-2.5f, -2.1732f, -3.2918f);
        this.setRotationAngle(right_hand10, 1.309f, 0.9599f, 0.0f);
        right_hand_global.func_78792_a(right_hand10);
        right_hand10.field_78804_l.add(new ModelBox(right_hand10, 21, 76, -2.1499f, -1.7907f, -2.4434f, 4, 4, 3, 0.0f));
        ModelRenderer right_hand11 = new ModelRenderer((ModelBase)this);
        right_hand11.func_78793_a(-0.5f, -7.1695f, -4.2383f);
        this.setRotationAngle(right_hand11, 0.8727f, 0.9599f, 0.0f);
        right_hand_global.func_78792_a(right_hand11);
        right_hand11.field_78804_l.add(new ModelBox(right_hand11, 53, 65, -4.0724f, -2.2178f, -7.5028f, 4, 4, 3, 0.0f));
        right_hand11.field_78804_l.add(new ModelBox(right_hand11, 0, 41, -3.3357f, -6.0974f, -7.1243f, 2, 4, 1, 0.0f));
        ModelRenderer right_hand1 = new ModelRenderer((ModelBase)this);
        right_hand1.func_78793_a(-2.5f, -2.5f, 3.0f);
        this.setRotationAngle(right_hand1, 0.3491f, -0.9599f, 0.0f);
        right_hand_global.func_78792_a(right_hand1);
        right_hand1.field_78804_l.add(new ModelBox(right_hand1, 43, 91, -4.6552f, -0.7554f, -3.6043f, 4, 3, 4, 0.0f));
        ModelRenderer right_hand13 = new ModelRenderer((ModelBase)this);
        right_hand13.func_78793_a(-4.664f, -4.5614f, 5.3813f);
        this.setRotationAngle(right_hand13, 0.8727f, -0.9599f, 0.0f);
        right_hand_global.func_78792_a(right_hand13);
        right_hand13.field_78804_l.add(new ModelBox(right_hand13, 86, 0, -5.3645f, -1.9374f, -4.3762f, 4, 3, 4, 0.0f));
        right_hand13.field_78804_l.add(new ModelBox(right_hand13, 50, 27, -3.8629f, -1.3565f, -0.529f, 2, 1, 3, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(8.0f, -21.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        ModelRenderer left_arm1 = new ModelRenderer((ModelBase)this);
        left_arm1.func_78793_a(-1.5f, 0.0f, -0.5f);
        this.setRotationAngle(left_arm1, 0.0f, 0.0f, 0.3491f);
        this.LArm.func_78792_a(left_arm1);
        left_arm1.field_78804_l.add(new ModelBox(left_arm1, 81, 13, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer left_arm2 = new ModelRenderer((ModelBase)this);
        left_arm2.func_78793_a(2.5f, 3.0f, -0.5f);
        this.setRotationAngle(left_arm2, 0.0f, 0.0f, 0.9599f);
        this.LArm.func_78792_a(left_arm2);
        left_arm2.field_78804_l.add(new ModelBox(left_arm2, 56, 78, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer left_arm3 = new ModelRenderer((ModelBase)this);
        left_arm3.func_78793_a(4.5f, 7.0f, -1.5f);
        this.setRotationAngle(left_arm3, 0.0f, 0.2618f, 0.9599f);
        this.LArm.func_78792_a(left_arm3);
        left_arm3.field_78804_l.add(new ModelBox(left_arm3, 28, 78, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer left_arm4 = new ModelRenderer((ModelBase)this);
        left_arm4.func_78793_a(6.5f, 11.0f, -4.5f);
        this.setRotationAngle(left_arm4, 0.0f, 0.6981f, 0.9599f);
        this.LArm.func_78792_a(left_arm4);
        left_arm4.field_78804_l.add(new ModelBox(left_arm4, 0, 76, -3.5f, -3.0f, -3.5f, 7, 6, 7, 0.0f));
        ModelRenderer left_hand_global = new ModelRenderer((ModelBase)this);
        left_hand_global.func_78793_a(8.0f, 11.5f, -7.0f);
        this.setRotationAngle(left_hand_global, -1.7453f, -2.4435f, -2.3562f);
        this.LArm.func_78792_a(left_hand_global);
        left_hand_global.field_78804_l.add(new ModelBox(left_hand_global, 50, 98, -2.0f, -2.5f, -3.5f, 4, 3, 4, 0.0f));
        ModelRenderer left_hand2 = new ModelRenderer((ModelBase)this);
        left_hand2.func_78793_a(-5.75f, -1.0f, -1.5f);
        this.setRotationAngle(left_hand2, 0.0f, 0.0f, 0.4363f);
        left_hand_global.func_78792_a(left_hand2);
        left_hand2.field_78804_l.add(new ModelBox(left_hand2, 50, 50, 1.6563f, -3.2254f, -2.0f, 2, 3, 4, 0.0f));
        ModelRenderer left_hand3 = new ModelRenderer((ModelBase)this);
        left_hand3.func_78793_a(-5.75f, -1.0f, -2.5f);
        this.setRotationAngle(left_hand3, 0.0f, 0.0f, 0.8727f);
        left_hand_global.func_78792_a(left_hand3);
        left_hand3.field_78804_l.add(new ModelBox(left_hand3, 66, 30, -3.9729f, -3.9042f, -1.0f, 5, 3, 4, 0.0f));
        left_hand3.field_78804_l.add(new ModelBox(left_hand3, 50, 47, -7.25f, -2.1772f, 0.0f, 3, 1, 2, 0.0f));
        ModelRenderer left_hand4 = new ModelRenderer((ModelBase)this);
        left_hand4.func_78793_a(2.5f, -2.1732f, -3.2918f);
        this.setRotationAngle(left_hand4, 1.309f, -0.9599f, 0.0f);
        left_hand_global.func_78792_a(left_hand4);
        left_hand4.field_78804_l.add(new ModelBox(left_hand4, 66, 98, -1.8501f, -1.7907f, -2.4434f, 4, 4, 3, 0.0f));
        ModelRenderer left_hand5 = new ModelRenderer((ModelBase)this);
        left_hand5.func_78793_a(0.5f, -7.1695f, -4.2383f);
        this.setRotationAngle(left_hand5, 0.8727f, -0.9599f, 0.0f);
        left_hand_global.func_78792_a(left_hand5);
        left_hand5.field_78804_l.add(new ModelBox(left_hand5, 49, 78, 0.0724f, -2.2178f, -7.5028f, 4, 4, 3, 0.0f));
        left_hand5.field_78804_l.add(new ModelBox(left_hand5, 33, 41, 0.5f, -6.0974f, -7.1243f, 2, 4, 1, 0.0f));
        ModelRenderer left_hand6 = new ModelRenderer((ModelBase)this);
        left_hand6.func_78793_a(2.5f, -2.5f, 3.0f);
        this.setRotationAngle(left_hand6, 0.3491f, 0.9599f, 0.0f);
        left_hand_global.func_78792_a(left_hand6);
        left_hand6.field_78804_l.add(new ModelBox(left_hand6, 96, 82, 0.6552f, -0.7554f, -3.6043f, 4, 3, 4, 0.0f));
        ModelRenderer left_hand7 = new ModelRenderer((ModelBase)this);
        left_hand7.func_78793_a(4.664f, -4.5614f, 5.3813f);
        this.setRotationAngle(left_hand7, 0.8727f, 0.9599f, 0.0f);
        left_hand_global.func_78792_a(left_hand7);
        left_hand7.field_78804_l.add(new ModelBox(left_hand7, 92, 39, 1.3645f, -1.9374f, -4.3762f, 4, 3, 4, 0.0f));
        left_hand7.field_78804_l.add(new ModelBox(left_hand7, 54, 7, 2.7261f, -0.864f, -1.4211f, 2, 1, 3, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-5.0f, -14.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 19, 62, -1.0f, 13.0f, -4.0f, 3, 1, 7, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 60, -1.0f, 9.0f, -1.0f, 3, 4, 3, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 87, 26, -2.0f, 3.0f, -3.0f, 5, 6, 7, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(6.0f, -14.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 37, 0, -3.0f, 13.0f, -4.0f, 3, 1, 7, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 80, 99, -3.0f, 9.0f, -1.0f, 3, 4, 3, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 26, 91, -4.0f, 3.0f, -3.0f, 5, 6, 7, 0.0f));
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

