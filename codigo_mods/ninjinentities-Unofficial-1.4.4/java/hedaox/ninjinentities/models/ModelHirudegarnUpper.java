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

public class ModelHirudegarnUpper
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Body;
    private final ModelRenderer Bone;
    private final ModelRenderer globl_horns;
    private final ModelRenderer horns2_r1;
    private final ModelRenderer horns_r1;
    private final ModelRenderer horns;
    private final ModelRenderer horns2;
    private final ModelRenderer globl_horns2;
    private final ModelRenderer horns4_r1;
    private final ModelRenderer horns3_r1;
    private final ModelRenderer horns3;
    private final ModelRenderer horns4;
    private final ModelRenderer globl_horns3;
    private final ModelRenderer horns6_r1;
    private final ModelRenderer horns5_r1;
    private final ModelRenderer horns5;
    private final ModelRenderer horns6;
    private final ModelRenderer Tail;
    private final ModelRenderer Tail2;
    private final ModelRenderer bodyChildChild;
    private final ModelRenderer bone2;
    private final ModelRenderer RArm;
    private final ModelRenderer RightGauntlet;
    private final ModelRenderer right_sleeve;
    private final ModelRenderer LArm;
    private final ModelRenderer bone;
    private final ModelRenderer left_sleeve;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelHirudegarnUpper(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 256;
        this.field_78089_u = 256;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, -12.0f, -5.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 57, -5.0f, -2.0f, -8.0f, 10, 11, 13, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 15.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 66, 0, -8.0f, -23.0f, -4.0f, 16, 15, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 102, 29, -7.0f, -8.0f, -4.0f, 14, 5, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 98, 106, -6.0f, -3.0f, -4.0f, 12, 7, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 137, -2.0f, 3.0f, -4.0f, 4, 3, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 0, -8.0f, -31.0f, -8.0f, 16, 14, 17, 0.0f));
        this.Bone = new ModelRenderer((ModelBase)this);
        this.Bone.func_78793_a(2.0f, -48.0f, -4.0f);
        this.Body.func_78792_a(this.Bone);
        this.Bone.field_78804_l.add(new ModelBox(this.Bone, 4, 3, -9.0f, 14.0f, -7.0f, 14, 15, 14, 0.0f));
        this.globl_horns = new ModelRenderer((ModelBase)this);
        this.globl_horns.func_78793_a(-2.0f, -13.0f, -10.0f);
        this.Bone.func_78792_a(this.globl_horns);
        this.horns2_r1 = new ModelRenderer((ModelBase)this);
        this.horns2_r1.func_78793_a(-6.5f, -9.0f, 11.5f);
        this.setRotationAngle(this.horns2_r1, -0.829f, 0.0f, 0.0f);
        this.globl_horns.func_78792_a(this.horns2_r1);
        this.horns_r1 = new ModelRenderer((ModelBase)this);
        this.horns_r1.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(this.horns_r1, -0.3054f, 0.0f, 0.0f);
        this.globl_horns.func_78792_a(this.horns_r1);
        this.horns = new ModelRenderer((ModelBase)this);
        this.horns.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(this.horns, -0.3054f, 0.0f, 0.0f);
        this.globl_horns.func_78792_a(this.horns);
        this.horns2 = new ModelRenderer((ModelBase)this);
        this.horns2.func_78793_a(-6.5f, -9.0f, 11.5f);
        this.setRotationAngle(this.horns2, -0.829f, 0.0f, 0.0f);
        this.globl_horns.func_78792_a(this.horns2);
        this.globl_horns2 = new ModelRenderer((ModelBase)this);
        this.globl_horns2.func_78793_a(10.0f, -17.0f, -12.0f);
        this.Bone.func_78792_a(this.globl_horns2);
        this.horns4_r1 = new ModelRenderer((ModelBase)this);
        this.horns4_r1.func_78793_a(-6.5f, -6.0f, 11.5f);
        this.setRotationAngle(this.horns4_r1, -0.829f, 0.0f, 0.0f);
        this.globl_horns2.func_78792_a(this.horns4_r1);
        this.horns3_r1 = new ModelRenderer((ModelBase)this);
        this.horns3_r1.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(this.horns3_r1, -0.3054f, 0.0f, 0.0f);
        this.globl_horns2.func_78792_a(this.horns3_r1);
        this.horns3 = new ModelRenderer((ModelBase)this);
        this.horns3.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(this.horns3, -0.3054f, 0.0f, 0.0f);
        this.globl_horns2.func_78792_a(this.horns3);
        this.horns4 = new ModelRenderer((ModelBase)this);
        this.horns4.func_78793_a(-6.5f, -6.0f, 11.5f);
        this.setRotationAngle(this.horns4, -0.829f, 0.0f, 0.0f);
        this.globl_horns2.func_78792_a(this.horns4);
        this.globl_horns3 = new ModelRenderer((ModelBase)this);
        this.globl_horns3.func_78793_a(-3.0f, -17.0f, -12.0f);
        this.Bone.func_78792_a(this.globl_horns3);
        this.horns6_r1 = new ModelRenderer((ModelBase)this);
        this.horns6_r1.func_78793_a(-6.5f, -9.0f, 11.5f);
        this.setRotationAngle(this.horns6_r1, -0.829f, 0.0f, 0.0f);
        this.globl_horns3.func_78792_a(this.horns6_r1);
        this.horns5_r1 = new ModelRenderer((ModelBase)this);
        this.horns5_r1.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(this.horns5_r1, -0.3054f, 0.0f, 0.0f);
        this.globl_horns3.func_78792_a(this.horns5_r1);
        this.horns5 = new ModelRenderer((ModelBase)this);
        this.horns5.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(this.horns5, -0.3054f, 0.0f, 0.0f);
        this.globl_horns3.func_78792_a(this.horns5);
        this.horns6 = new ModelRenderer((ModelBase)this);
        this.horns6.func_78793_a(-6.5f, -9.0f, 11.5f);
        this.setRotationAngle(this.horns6, -0.829f, 0.0f, 0.0f);
        this.globl_horns3.func_78792_a(this.horns6);
        this.Tail = new ModelRenderer((ModelBase)this);
        this.Tail.func_78793_a(0.0f, -15.0f, 0.0f);
        this.setRotationAngle(this.Tail, -0.3491f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.Tail);
        this.Tail2 = new ModelRenderer((ModelBase)this);
        this.Tail2.func_78793_a(0.0f, -17.0f, 0.0f);
        this.setRotationAngle(this.Tail2, 0.5236f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.Tail2);
        this.bodyChildChild = new ModelRenderer((ModelBase)this);
        this.bodyChildChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Tail2.func_78792_a(this.bodyChildChild);
        this.bone2 = new ModelRenderer((ModelBase)this);
        this.bone2.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body.func_78792_a(this.bone2);
        this.bone2.field_78804_l.add(new ModelBox(this.bone2, 44, 221, -26.0f, -2.0f, -15.0f, 50, 1, 30, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-8.0f, -23.0f, 0.0f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.0873f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 46, 71, -8.9507f, -0.3953f, -4.0f, 7, 30, 8, 0.0f));
        this.RightGauntlet = new ModelRenderer((ModelBase)this);
        this.RightGauntlet.func_78793_a(12.2478f, 25.465f, 0.0f);
        this.RArm.func_78792_a(this.RightGauntlet);
        this.RightGauntlet.field_78804_l.add(new ModelBox(this.RightGauntlet, 0, 174, -22.3116f, -8.0156f, -5.0f, 9, 11, 10, 0.0f));
        this.right_sleeve = new ModelRenderer((ModelBase)this);
        this.right_sleeve.func_78793_a(2.8015f, 32.1397f, 0.0f);
        this.setRotationAngle(this.right_sleeve, 0.0f, 0.0f, 0.0436f);
        this.RArm.func_78792_a(this.right_sleeve);
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 106, 94, -16.6063f, -38.2224f, 7.0f, 14, 5, 0, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 106, 92, -16.6063f, -38.2224f, -7.0f, 14, 5, 0, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 106, 81, -16.6063f, -38.2224f, -7.0f, 0, 5, 14, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 36, 31, -16.6063f, -38.2224f, -7.0f, 14, 0, 14, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 38, 45, -15.6063f, -38.2224f, -6.0f, 13, 14, 12, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(8.0f, -23.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.0873f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 76, 76, 1.9583f, -0.5697f, -4.0f, 7, 30, 8, 0.0f));
        this.bone = new ModelRenderer((ModelBase)this);
        this.bone.func_78793_a(-10.7748f, 31.5293f, 0.0f);
        this.LArm.func_78792_a(this.bone);
        this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 174, 11.7331f, -14.6661f, -5.0f, 9, 11, 10, 0.0f));
        this.left_sleeve = new ModelRenderer((ModelBase)this);
        this.left_sleeve.func_78793_a(24.2061f, 29.9653f, 0.0f);
        this.setRotationAngle(this.left_sleeve, 0.0f, 0.0f, -0.0436f);
        this.LArm.func_78792_a(this.left_sleeve);
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 106, 92, -25.3937f, -37.2224f, 7.0f, 14, 5, 0, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 105, 92, -25.3937f, -37.2224f, -7.0f, 14, 5, 0, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 66, 0, -25.3937f, -37.2224f, -7.0f, 0, 5, 14, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 35, 31, -25.3937f, -37.2224f, -7.0f, 14, 0, 14, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 36, 45, -24.3937f, -37.2224f, -6.0f, 13, 14, 12, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 106, 80, -11.3048f, -37.3018f, -7.0f, 0, 5, 14, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f))) - 2.0f), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
        this.RArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f + (float)Math.PI)) * 2.0f * f1 * 0.5f;
        this.LArm.field_78795_f = MathHelper.func_76134_b((float)(f * 0.6662f)) * 2.0f * f1 * 0.5f;
        this.RArm.field_78808_h = 0.0f;
        this.LArm.field_78808_h = 0.0f;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

