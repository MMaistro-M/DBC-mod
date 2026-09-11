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

public class ModelHirudegarnPost
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

    public ModelHirudegarnPost(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 256;
        this.field_78089_u = 256;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(2.0f, -30.0f, -4.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -9.0f, -12.0f, -7.0f, 14, 15, 14, -1.0f));
        ModelRenderer bone3 = new ModelRenderer((ModelBase)this);
        bone3.func_78793_a(-2.0f, 57.0f, 4.0f);
        this.Head.func_78792_a(bone3);
        bone3.field_78804_l.add(new ModelBox(bone3, 41, 127, -2.0f, -69.0f, -11.0f, 4, 4, 8, 0.0f));
        ModelRenderer globl_horns = new ModelRenderer((ModelBase)this);
        globl_horns.func_78793_a(-2.0f, -13.0f, -10.0f);
        this.Head.func_78792_a(globl_horns);
        globl_horns.field_78804_l.add(new ModelBox(globl_horns, 18, 128, 3.0f, -3.0f, 6.0f, 3, 3, 4, 0.0f));
        globl_horns.field_78804_l.add(new ModelBox(globl_horns, 19, 129, -6.0f, -3.0f, 6.0f, 3, 3, 3, 0.0f));
        globl_horns.field_78804_l.add(new ModelBox(globl_horns, 69, 64, 2.0f, 0.0f, 6.0f, 5, 4, 4, 0.0f));
        globl_horns.field_78804_l.add(new ModelBox(globl_horns, 68, 64, -7.0f, 0.0f, 6.0f, 5, 4, 4, 0.0f));
        ModelRenderer globl_horns2 = new ModelRenderer((ModelBase)this);
        globl_horns2.func_78793_a(10.0f, -17.0f, -12.0f);
        this.Head.func_78792_a(globl_horns2);
        ModelRenderer horns3 = new ModelRenderer((ModelBase)this);
        horns3.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(horns3, -0.3054f, 0.0f, 0.0f);
        globl_horns2.func_78792_a(horns3);
        horns3.field_78804_l.add(new ModelBox(horns3, 18, 128, -2.5f, 1.7456f, 2.1603f, 3, 5, 2, 0.0f));
        ModelRenderer horns4 = new ModelRenderer((ModelBase)this);
        horns4.func_78793_a(-6.5f, -6.0f, 11.5f);
        this.setRotationAngle(horns4, -0.829f, 0.0f, 0.0f);
        globl_horns2.func_78792_a(horns4);
        horns4.field_78804_l.add(new ModelBox(horns4, 16, 125, -2.5f, -1.7373f, 3.4756f, 3, 9, 2, 0.0f));
        ModelRenderer globl_horns4 = new ModelRenderer((ModelBase)this);
        globl_horns4.func_78793_a(1.0f, -17.0f, -12.0f);
        this.Head.func_78792_a(globl_horns4);
        ModelRenderer horns2 = new ModelRenderer((ModelBase)this);
        horns2.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(horns2, -0.3054f, 0.0f, 0.0f);
        globl_horns4.func_78792_a(horns2);
        horns2.field_78804_l.add(new ModelBox(horns2, 23, 128, -2.5f, 1.7456f, 2.1603f, 3, 5, 2, 0.0f));
        ModelRenderer horns7 = new ModelRenderer((ModelBase)this);
        horns7.func_78793_a(-6.5f, -6.0f, 11.5f);
        this.setRotationAngle(horns7, -0.829f, 0.0f, 0.0f);
        globl_horns4.func_78792_a(horns7);
        horns7.field_78804_l.add(new ModelBox(horns7, 20, 126, -2.5f, -1.7373f, 3.4756f, 3, 9, 2, 0.0f));
        ModelRenderer globl_horns3 = new ModelRenderer((ModelBase)this);
        globl_horns3.func_78793_a(-3.0f, -17.0f, -12.0f);
        this.Head.func_78792_a(globl_horns3);
        ModelRenderer horns6_r1 = new ModelRenderer((ModelBase)this);
        horns6_r1.func_78793_a(-6.5f, -9.0f, 11.5f);
        this.setRotationAngle(horns6_r1, -0.829f, 0.0f, 0.0f);
        globl_horns3.func_78792_a(horns6_r1);
        ModelRenderer horns5_r1 = new ModelRenderer((ModelBase)this);
        horns5_r1.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(horns5_r1, -0.3054f, 0.0f, 0.0f);
        globl_horns3.func_78792_a(horns5_r1);
        ModelRenderer horns5 = new ModelRenderer((ModelBase)this);
        horns5.func_78793_a(-6.5f, -11.0f, 14.5f);
        this.setRotationAngle(horns5, -0.3054f, 0.0f, 0.0f);
        globl_horns3.func_78792_a(horns5);
        ModelRenderer horns6 = new ModelRenderer((ModelBase)this);
        horns6.func_78793_a(-6.5f, -9.0f, 11.5f);
        this.setRotationAngle(horns6, -0.829f, 0.0f, 0.0f);
        globl_horns3.func_78792_a(horns6);
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 15.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 60, 0, -8.0f, -46.0f, -4.0f, 16, 12, 8, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(0.0f, 9.0f, 0.0f);
        this.Body.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 56, 24, -7.0f, -43.0f, -4.0f, 14, 5, 8, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 56, 64, -6.0f, -38.0f, -4.0f, 12, 7, 8, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 134, 0, -2.0f, -32.0f, -4.0f, 4, 3, 8, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 95, 71, -3.0f, -29.0f, -4.0f, 6, 3, 8, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 0, 80, -9.0f, -35.0f, -5.0f, 7, 7, 10, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 70, 79, 2.0f, -35.0f, -5.0f, 7, 7, 10, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 92, 149, -7.0f, -52.0f, 3.0f, 13, 4, 2, 0.0f));
        ModelRenderer bodyChild_7 = new ModelRenderer((ModelBase)this);
        bodyChild_7.func_78793_a(0.0f, -24.0f, 0.0f);
        this.setRotationAngle(bodyChild_7, -0.3491f, 0.0f, 0.0f);
        bone.func_78792_a(bodyChild_7);
        bodyChild_7.field_78804_l.add(new ModelBox(bodyChild_7, 210, 54, -3.0f, -10.0611f, 1.3468f, 6, 5, 17, 0.0f));
        ModelRenderer bodyChild_8 = new ModelRenderer((ModelBase)this);
        bodyChild_8.func_78793_a(0.0f, -26.0f, 0.0f);
        this.setRotationAngle(bodyChild_8, 0.5236f, 0.0f, 0.0f);
        bone.func_78792_a(bodyChild_8);
        bodyChild_8.field_78804_l.add(new ModelBox(bodyChild_8, 204, 0, -3.0f, 7.5096f, 14.4904f, 6, 5, 20, 0.0f));
        ModelRenderer bodyChildChild = new ModelRenderer((ModelBase)this);
        bodyChildChild.func_78793_a(0.0f, 0.0f, 0.0f);
        bodyChild_8.func_78792_a(bodyChildChild);
        bodyChildChild.field_78804_l.add(new ModelBox(bodyChildChild, 227, 56, -2.0f, 9.1436f, 33.9904f, 4, 2, 6, 0.0f));
        ModelRenderer bodyChild_5 = new ModelRenderer((ModelBase)this);
        bodyChild_5.func_78793_a(-4.0f, -13.0f, 0.0f);
        this.setRotationAngle(bodyChild_5, 0.1571f, -0.0349f, 0.3665f);
        this.Body.func_78792_a(bodyChild_5);
        bodyChild_5.field_78809_i = true;
        bodyChild_5.field_78804_l.add(new ModelBox(bodyChild_5, 118, 223, -16.4177f, -25.9507f, 8.6613f, 11, 33, 0, 0.0f));
        ModelRenderer bodyChild_2 = new ModelRenderer((ModelBase)this);
        bodyChild_2.func_78793_a(-21.0f, -13.0f, 0.0f);
        this.setRotationAngle(bodyChild_2, 0.1571f, -0.0349f, 0.8901f);
        this.Body.func_78792_a(bodyChild_2);
        bodyChild_2.field_78809_i = true;
        bodyChild_2.field_78804_l.add(new ModelBox(bodyChild_2, 0, 223, -16.4493f, -30.0006f, 10.3169f, 11, 33, 0, 0.0f));
        ModelRenderer bodyChild_4 = new ModelRenderer((ModelBase)this);
        bodyChild_4.func_78793_a(0.0f, -15.0f, 0.0f);
        this.setRotationAngle(bodyChild_4, 0.1571f, 0.0349f, -0.3665f);
        this.Body.func_78792_a(bodyChild_4);
        bodyChild_4.field_78804_l.add(new ModelBox(bodyChild_4, 52, 223, 6.9702f, -23.0333f, 8.2728f, 12, 33, 0, 0.0f));
        ModelRenderer bodyChild_3 = new ModelRenderer((ModelBase)this);
        bodyChild_3.func_78793_a(20.0f, -18.0f, 0.0f);
        this.setRotationAngle(bodyChild_3, 0.1571f, 0.0349f, -0.8901f);
        this.Body.func_78792_a(bodyChild_3);
        bodyChild_3.field_78804_l.add(new ModelBox(bodyChild_3, 175, 223, 1.5696f, -27.0346f, 9.7288f, 12, 33, 0, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-9.0f, -43.0f, 0.0f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.0873f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 32, 32, -6.1364f, -2.6492f, -4.0f, 7, 30, 8, 0.0f));
        ModelRenderer rightarmshoulder = new ModelRenderer((ModelBase)this);
        rightarmshoulder.func_78793_a(8.7802f, 32.4418f, -0.6f);
        this.setRotationAngle(rightarmshoulder, 0.0f, 0.0f, 0.1f);
        this.RArm.func_78792_a(rightarmshoulder);
        rightarmshoulder.field_78804_l.add(new ModelBox(rightarmshoulder, 0, 163, -24.0175f, -35.2881f, -4.0f, 15, 6, 11, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(8.0f, -43.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.0873f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 31, 0.1402f, -2.7364f, -4.0f, 7, 30, 8, 0.0f));
        ModelRenderer leftarmshoulder = new ModelRenderer((ModelBase)this);
        leftarmshoulder.func_78793_a(-7.7802f, 32.4418f, -0.5f);
        this.setRotationAngle(leftarmshoulder, 0.0f, 0.0f, -0.1f);
        this.LArm.func_78792_a(leftarmshoulder);
        leftarmshoulder.field_78804_l.add(new ModelBox(leftarmshoulder, 0, 183, 8.3714f, -35.5608f, -4.0f, 15, 6, 11, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-6.0f, -19.0f, -2.0f);
        this.setRotationAngle(this.RLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 34, 89, -4.0f, -0.8394f, -1.4501f, 6, 15, 6, 0.0f));
        ModelRenderer rightLeg3 = new ModelRenderer((ModelBase)this);
        rightLeg3.func_78793_a(-7.0f, 28.0129f, 8.8024f);
        this.setRotationAngle(rightLeg3, 0.1745f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(rightLeg3);
        rightLeg3.field_78804_l.add(new ModelBox(rightLeg3, 0, 29, 5.0f, -2.9738f, -7.771f, 2, 2, 7, 0.0f));
        rightLeg3.field_78804_l.add(new ModelBox(rightLeg3, 58, 96, 3.0f, -15.4166f, -7.637f, 6, 14, 6, 0.0f));
        rightLeg3.field_78804_l.add(new ModelBox(rightLeg3, 96, 129, 7.0f, -2.0607f, -9.7634f, 3, 1, 7, 0.0f));
        rightLeg3.field_78804_l.add(new ModelBox(rightLeg3, 96, 129, 2.0f, -2.0607f, -9.7634f, 3, 1, 7, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(7.0f, -19.0f, -2.0f);
        this.setRotationAngle(this.LLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 92, 39, -3.0f, -0.5271f, -1.2162f, 6, 15, 6, 0.0f));
        ModelRenderer leftLeg3 = new ModelRenderer((ModelBase)this);
        leftLeg3.func_78793_a(-6.0f, 28.274f, 7.8195f);
        this.setRotationAngle(leftLeg3, 0.1745f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(leftLeg3);
        leftLeg3.field_78804_l.add(new ModelBox(leftLeg3, 82, 96, 3.0f, -16.2601f, -6.6395f, 6, 15, 6, 0.0f));
        leftLeg3.field_78804_l.add(new ModelBox(leftLeg3, 96, 129, 2.0f, -1.9051f, -8.8096f, 3, 1, 7, 0.0f));
        leftLeg3.field_78804_l.add(new ModelBox(leftLeg3, 96, 129, 7.0f, -1.9051f, -8.8096f, 3, 1, 7, 0.0f));
        leftLeg3.field_78804_l.add(new ModelBox(leftLeg3, 238, 159, 5.0f, -2.8182f, -6.8171f, 2, 2, 7, 0.0f));
        leftLeg3.field_78804_l.add(new ModelBox(leftLeg3, 0, 29, 5.0f, -2.8182f, -6.8171f, 2, 2, 7, 0.0f));
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

