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

public class ModelHirudegarnPre
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
    private final ModelRenderer right_sleeve;
    private final ModelRenderer left_sleeve;
    private final ModelRenderer Tail;
    private final ModelRenderer Tail2;
    private final ModelRenderer bodyChildChild;
    private final ModelRenderer RArm;
    private final ModelRenderer RightGauntlet;
    private final ModelRenderer LArm;
    private final ModelRenderer bone;
    private final ModelRenderer RLeg;
    private final ModelRenderer RLeg2;
    private final ModelRenderer LLeg;
    private final ModelRenderer LeftLeg3;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelHirudegarnPre(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 256;
        this.field_78089_u = 256;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 12.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 57, -5.0f, -52.0f, -13.0f, 10, 11, 13, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 15.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 66, 0, -8.0f, -49.0f, -4.0f, 16, 15, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 102, 29, -7.0f, -34.0f, -4.0f, 14, 5, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 98, 106, -6.0f, -29.0f, -4.0f, 12, 7, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 137, -2.0f, -23.0f, -4.0f, 4, 3, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 58, 131, -3.0f, -20.0f, -4.0f, 6, 3, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 0, -8.0f, -57.0f, -8.0f, 16, 14, 17, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 120, -9.0f, -26.0f, -5.0f, 7, 7, 10, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 117, 42, 2.0f, -26.0f, -5.0f, 7, 7, 10, 0.0f));
        this.Bone = new ModelRenderer((ModelBase)this);
        this.Bone.func_78793_a(2.0f, -48.0f, -4.0f);
        this.Body.func_78792_a(this.Bone);
        this.Bone.field_78804_l.add(new ModelBox(this.Bone, 4, 3, -9.0f, -12.0f, -7.0f, 14, 15, 14, 0.0f));
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
        this.Tail.field_78804_l.add(new ModelBox(this.Tail, 210, 54, -3.0f, -10.0611f, 1.3468f, 6, 5, 17, 0.0f));
        this.Tail2 = new ModelRenderer((ModelBase)this);
        this.Tail2.func_78793_a(0.0f, -17.0f, 0.0f);
        this.setRotationAngle(this.Tail2, 0.5236f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.Tail2);
        this.Tail2.field_78804_l.add(new ModelBox(this.Tail2, 204, 0, -3.0f, 7.5096f, 14.4904f, 6, 5, 20, 0.0f));
        this.bodyChildChild = new ModelRenderer((ModelBase)this);
        this.bodyChildChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Tail2.func_78792_a(this.bodyChildChild);
        this.bodyChildChild.field_78804_l.add(new ModelBox(this.bodyChildChild, 227, 56, -2.0f, 9.6436f, 34.8564f, 4, 2, 6, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-9.0f, -48.0f, 0.0f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.0873f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 46, 71, -8.0417f, -1.4786f, -4.0f, 7, 30, 8, 0.0f));
        this.RightGauntlet = new ModelRenderer((ModelBase)this);
        this.RightGauntlet.func_78793_a(15.4237f, 50.2827f, 0.0f);
        this.RArm.func_78792_a(this.RightGauntlet);
        this.RightGauntlet.field_78804_l.add(new ModelBox(this.RightGauntlet, 0, 174, -24.5785f, -33.9166f, -5.0f, 9, 11, 10, 0.0f));
        this.right_sleeve = new ModelRenderer((ModelBase)this);
        this.right_sleeve.func_78793_a(5.9774f, 56.9573f, 0.0f);
        this.setRotationAngle(this.right_sleeve, 0.0f, 0.0f, 0.0436f);
        this.RArm.func_78792_a(this.right_sleeve);
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 106, 94, -20.0f, -64.0f, 7.0f, 14, 5, 0, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 106, 92, -20.0f, -64.0f, -7.0f, 14, 5, 0, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 106, 81, -20.0f, -64.0f, -7.0f, 0, 5, 14, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 36, 31, -20.0f, -64.0f, -7.0f, 14, 0, 14, 0.0f));
        this.right_sleeve.field_78804_l.add(new ModelBox(this.right_sleeve, 38, 45, -19.0f, -64.0f, -6.0f, 13, 14, 12, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(8.0f, -48.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.0873f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 76, 76, 2.0455f, -1.5658f, -4.0f, 7, 30, 8, 0.0f));
        this.bone = new ModelRenderer((ModelBase)this);
        this.bone.func_78793_a(-12.9545f, 56.4342f, 0.0f);
        this.LArm.func_78792_a(this.bone);
        this.bone.field_78804_l.add(new ModelBox(this.bone, 0, 174, 14.0f, -40.5671f, -5.0f, 9, 11, 10, 0.0f));
        this.left_sleeve = new ModelRenderer((ModelBase)this);
        this.left_sleeve.func_78793_a(22.0264f, 54.8701f, 0.0f);
        this.setRotationAngle(this.left_sleeve, 0.0f, 0.0f, -0.0436f);
        this.LArm.func_78792_a(this.left_sleeve);
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 106, 92, -22.0f, -63.0f, 7.0f, 14, 5, 0, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 105, 92, -22.0f, -63.0f, -7.0f, 14, 5, 0, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 66, 0, -22.0f, -63.0f, -7.0f, 0, 5, 14, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 35, 31, -22.0f, -63.0f, -7.0f, 14, 0, 14, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 36, 45, -21.0f, -63.0f, -6.0f, 13, 14, 12, 0.0f));
        this.left_sleeve.field_78804_l.add(new ModelBox(this.left_sleeve, 106, 80, -7.9111f, -63.0794f, -7.0f, 0, 5, 14, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-7.0f, -19.0f, -2.0f);
        this.setRotationAngle(this.RLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 97, 121, -3.0f, -0.5271f, -1.2162f, 6, 15, 6, 0.0f));
        this.RLeg2 = new ModelRenderer((ModelBase)this);
        this.RLeg2.func_78793_a(-6.0f, 28.274f, 7.8195f);
        this.setRotationAngle(this.RLeg2, 0.1745f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(this.RLeg2);
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 129, 60, 3.0f, -16.2611f, -6.6831f, 6, 15, 6, 0.0f));
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 140, 120, 2.0f, -1.9051f, -8.8096f, 3, 1, 8, 0.0f));
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 26, 71, 5.0f, -2.8182f, -6.8171f, 2, 2, 7, 0.0f));
        this.RLeg2.field_78804_l.add(new ModelBox(this.RLeg2, 141, 121, 7.0f, -1.9051f, -8.8096f, 3, 1, 7, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(7.0f, -19.0f, -1.0f);
        this.setRotationAngle(this.LLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 97, 121, -3.0f, -0.3965f, -2.2076f, 6, 15, 6, 0.0f));
        this.LeftLeg3 = new ModelRenderer((ModelBase)this);
        this.LeftLeg3.func_78793_a(-6.0f, 28.4046f, 6.8281f);
        this.setRotationAngle(this.LeftLeg3, 0.1745f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(this.LeftLeg3);
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 129, 60, 3.0f, -16.2611f, -6.6831f, 6, 15, 6, 0.0f));
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 140, 120, 2.0f, -1.9051f, -8.8096f, 3, 1, 8, 0.0f));
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 26, 71, 5.0f, -2.8182f, -6.8171f, 2, 2, 7, 0.0f));
        this.LeftLeg3.field_78804_l.add(new ModelBox(this.LeftLeg3, 141, 121, 7.0f, -1.9051f, -8.8096f, 3, 1, 7, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
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

