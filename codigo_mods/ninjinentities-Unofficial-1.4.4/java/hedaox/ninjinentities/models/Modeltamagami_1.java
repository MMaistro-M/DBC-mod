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

public class Modeltamagami_1
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer horns;
    private final ModelRenderer lefthorn_r1;
    private final ModelRenderer righthorn_r1;
    private final ModelRenderer eyes;
    private final ModelRenderer lefteye_r1;
    private final ModelRenderer righteye_r1;
    private final ModelRenderer ears;
    private final ModelRenderer Body;
    private final ModelRenderer chest;
    private final ModelRenderer dragonball;
    private final ModelRenderer RArm;
    private final ModelRenderer leftshoulder;
    private final ModelRenderer cube_r1;
    private final ModelRenderer leftarmbones;
    private final ModelRenderer lefthand;
    private final ModelRenderer leftfingers;
    private final ModelRenderer LArm;
    private final ModelRenderer rightshoulder;
    private final ModelRenderer cube_r2;
    private final ModelRenderer rightarmbones;
    private final ModelRenderer righthand;
    private final ModelRenderer rightfingers;
    private final ModelRenderer sword;
    private final ModelRenderer RLeg;
    private final ModelRenderer leftfoot;
    private final ModelRenderer LLeg;
    private final ModelRenderer rightfoot;
    private float scale = 1.0f;

    public Modeltamagami_1(float _scale) {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, -42.0f, -1.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -10.0f, -3.0f, 8, 9, 8, 0.0f));
        this.horns = new ModelRenderer((ModelBase)this);
        this.horns.func_78793_a(0.0f, -4.0f, 1.0f);
        this.Head.func_78792_a(this.horns);
        this.lefthorn_r1 = new ModelRenderer((ModelBase)this);
        this.lefthorn_r1.func_78793_a(-4.25f, -6.5f, 2.0f);
        this.horns.func_78792_a(this.lefthorn_r1);
        this.setRotationAngle(this.lefthorn_r1, -0.4363f, 0.0f, -0.6981f);
        this.lefthorn_r1.field_78804_l.add(new ModelBox(this.lefthorn_r1, 25, 1, -1.5f, -2.5f, -0.5f, 3, 5, 1, 0.0f));
        this.righthorn_r1 = new ModelRenderer((ModelBase)this);
        this.righthorn_r1.func_78793_a(4.25f, -6.5f, 2.0f);
        this.horns.func_78792_a(this.righthorn_r1);
        this.setRotationAngle(this.righthorn_r1, -0.4363f, 0.0f, 0.6981f);
        this.righthorn_r1.field_78804_l.add(new ModelBox(this.righthorn_r1, 25, 1, -1.5f, -2.5f, -0.5f, 3, 5, 1, 0.0f));
        this.eyes = new ModelRenderer((ModelBase)this);
        this.eyes.func_78793_a(0.0f, 0.5f, 0.0f);
        this.Head.func_78792_a(this.eyes);
        this.lefteye_r1 = new ModelRenderer((ModelBase)this);
        this.lefteye_r1.func_78793_a(-2.25f, -6.0f, -3.5f);
        this.eyes.func_78792_a(this.lefteye_r1);
        this.setRotationAngle(this.lefteye_r1, 0.0f, 0.0f, 0.7854f);
        this.lefteye_r1.field_78804_l.add(new ModelBox(this.lefteye_r1, 34, 1, -2.5f, -1.0f, -0.5f, 4, 2, 1, 0.0f));
        this.righteye_r1 = new ModelRenderer((ModelBase)this);
        this.righteye_r1.func_78793_a(2.25f, -6.0f, -3.5f);
        this.eyes.func_78792_a(this.righteye_r1);
        this.righteye_r1.field_78809_i = true;
        this.setRotationAngle(this.righteye_r1, 0.0f, 0.0f, -0.7854f);
        this.righteye_r1.field_78804_l.add(new ModelBox(this.righteye_r1, 34, 1, -1.5f, -1.0f, -0.5f, 4, 2, 1, 0.0f));
        this.ears = new ModelRenderer((ModelBase)this);
        this.ears.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(this.ears);
        this.ears.field_78804_l.add(new ModelBox(this.ears, 27, 21, -5.0f, -6.0f, -0.5f, 1, 2, 2, 0.0f));
        this.ears.field_78809_i = true;
        this.ears.field_78804_l.add(new ModelBox(this.ears, 27, 21, 4.0f, -6.0f, -0.5f, 1, 2, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 0.0f, -4.0f);
        this.chest = new ModelRenderer((ModelBase)this);
        this.chest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.chest);
        this.chest.field_78804_l.add(new ModelBox(this.chest, 0, 18, -3.0f, -43.0f, 1.0f, 6, 2, 6, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 0, 42, -7.0f, -44.0f, -1.0f, 14, 3, 10, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 1, 57, -10.0f, -41.0f, -1.0f, 20, 12, 10, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 52, 41, -7.0f, -29.0f, 0.0f, 14, 6, 8, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 83, 74, -6.0f, -23.0f, 0.5f, 12, 2, 7, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 68, 57, -7.0f, -21.0f, 0.0f, 14, 8, 8, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 93, 33, -3.0f, -15.0f, -0.5f, 6, 5, 9, 0.0f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 54, 85, -5.0f, -27.0f, 0.1f, 10, 4, 5, 0.025f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 54, 96, -5.0f, -21.0f, 0.1f, 10, 4, 5, 0.025f));
        this.chest.field_78804_l.add(new ModelBox(this.chest, 86, 85, -5.0f, -24.0f, 0.6f, 10, 4, 5, 0.025f));
        this.dragonball = new ModelRenderer((ModelBase)this);
        this.dragonball.func_78793_a(0.0f, -18.0f, 0.0f);
        this.chest.func_78792_a(this.dragonball);
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 101, 108, -3.0f, -1.0f, -3.0f, 6, 1, 6, 0.0f));
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 101, 108, -3.0f, -8.0f, -3.0f, 6, 1, 6, 0.0f));
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 114, 116, -4.0f, -7.0f, -3.0f, 1, 6, 6, 0.0f));
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 114, 116, 3.0f, -7.0f, -3.0f, 1, 6, 6, 0.0f));
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 99, 116, -3.0f, -7.0f, -4.0f, 6, 6, 1, 0.0f));
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 99, 116, -3.0f, -7.0f, 3.0f, 6, 6, 1, 0.0f));
        this.dragonball.field_78804_l.add(new ModelBox(this.dragonball, 96, 112, -0.5f, -4.5f, -0.5f, 1, 1, 1, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-14.0f, -39.0f, 0.0f);
        this.Body.func_78792_a(this.RArm);
        this.leftshoulder = new ModelRenderer((ModelBase)this);
        this.leftshoulder.func_78793_a(-3.0f, -3.0f, 0.0f);
        this.RArm.func_78792_a(this.leftshoulder);
        this.leftshoulder.field_78804_l.add(new ModelBox(this.leftshoulder, 41, 0, -1.0f, -1.0f, 0.0f, 8, 8, 8, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(-1.75f, -1.75f, 4.5f);
        this.leftshoulder.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 0.0f, -0.7854f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 67, 0, -1.5f, -2.5f, -0.5f, 3, 5, 1, 0.0f));
        this.leftarmbones = new ModelRenderer((ModelBase)this);
        this.leftarmbones.func_78793_a(15.0f, -3.0f, 0.0f);
        this.RArm.func_78792_a(this.leftarmbones);
        this.leftarmbones.field_78804_l.add(new ModelBox(this.leftarmbones, 41, 18, -18.0f, 7.0f, 1.5f, 5, 13, 5, 0.0f));
        this.leftarmbones.field_78804_l.add(new ModelBox(this.leftarmbones, 63, 18, -18.0f, 21.0f, 1.5f, 5, 14, 5, 0.0f));
        this.leftarmbones.field_78804_l.add(new ModelBox(this.leftarmbones, 85, 30, -17.0f, 18.0f, 2.5f, 3, 3, 3, 0.0f));
        this.lefthand = new ModelRenderer((ModelBase)this);
        this.lefthand.func_78793_a(15.0f, -6.0f, 0.0f);
        this.RArm.func_78792_a(this.lefthand);
        this.lefthand.field_78804_l.add(new ModelBox(this.lefthand, 81, 0, -18.0f, 38.0f, 1.5f, 5, 6, 5, 0.0f));
        this.leftfingers = new ModelRenderer((ModelBase)this);
        this.leftfingers.func_78793_a(-15.0f, 45.0f, 0.0f);
        this.lefthand.func_78792_a(this.leftfingers);
        this.leftfingers.field_78804_l.add(new ModelBox(this.leftfingers, 87, 21, -0.75f, -2.0f, -0.5f, 2, 4, 2, 0.0f));
        this.leftfingers.field_78804_l.add(new ModelBox(this.leftfingers, 87, 21, -1.0f, -1.0f, 2.0f, 2, 4, 2, 0.0f));
        this.leftfingers.field_78804_l.add(new ModelBox(this.leftfingers, 87, 21, -1.0f, -1.0f, 4.5f, 2, 4, 2, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(15.0f, -39.0f, 0.0f);
        this.Body.func_78792_a(this.LArm);
        this.rightshoulder = new ModelRenderer((ModelBase)this);
        this.rightshoulder.func_78793_a(-4.0f, -3.0f, 0.0f);
        this.LArm.func_78792_a(this.rightshoulder);
        this.rightshoulder.field_78804_l.add(new ModelBox(this.rightshoulder, 41, 0, -1.0f, -1.0f, 0.0f, 8, 8, 8, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(7.75f, -1.75f, 4.5f);
        this.rightshoulder.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, 0.0f, 0.7854f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 67, 0, -1.5f, -2.5f, -0.5f, 3, 5, 1, 0.0f));
        this.rightarmbones = new ModelRenderer((ModelBase)this);
        this.rightarmbones.func_78793_a(14.0f, -3.0f, 0.0f);
        this.LArm.func_78792_a(this.rightarmbones);
        this.rightarmbones.field_78809_i = true;
        this.rightarmbones.field_78804_l.add(new ModelBox(this.rightarmbones, 41, 18, -17.0f, 7.0f, 1.5f, 5, 13, 5, 0.0f));
        this.rightarmbones.field_78804_l.add(new ModelBox(this.rightarmbones, 63, 18, -17.0f, 21.0f, 1.5f, 5, 14, 5, 0.0f));
        this.rightarmbones.field_78804_l.add(new ModelBox(this.rightarmbones, 85, 30, -16.0f, 19.0f, 2.5f, 3, 3, 3, 0.0f));
        this.righthand = new ModelRenderer((ModelBase)this);
        this.righthand.func_78793_a(14.0f, -6.0f, 0.0f);
        this.LArm.func_78792_a(this.righthand);
        this.righthand.field_78809_i = true;
        this.righthand.field_78804_l.add(new ModelBox(this.righthand, 81, 0, -17.0f, 38.0f, 1.5f, 5, 6, 5, 0.0f));
        this.rightfingers = new ModelRenderer((ModelBase)this);
        this.rightfingers.func_78793_a(-15.0f, 45.0f, 0.0f);
        this.righthand.func_78792_a(this.rightfingers);
        this.rightfingers.field_78804_l.add(new ModelBox(this.rightfingers, 87, 21, -1.25f, -2.0f, -0.5f, 2, 4, 2, 0.0f));
        this.rightfingers.field_78804_l.add(new ModelBox(this.rightfingers, 87, 21, -1.5f, -1.0f, 2.0f, 2, 4, 2, 0.0f));
        this.rightfingers.field_78804_l.add(new ModelBox(this.rightfingers, 87, 21, -1.5f, -1.0f, 4.5f, 2, 4, 2, 0.0f));
        this.sword = new ModelRenderer((ModelBase)this);
        this.sword.func_78793_a(-16.0f, 45.0f, 11.0f);
        this.righthand.func_78792_a(this.sword);
        this.setRotationAngle(this.sword, 1.5708f, 0.0f, -1.5708f);
        this.sword.field_78804_l.add(new ModelBox(this.sword, 137, 10, -1.5f, -3.0f, -2.0f, 3, 3, 3, 0.0f));
        this.sword.field_78804_l.add(new ModelBox(this.sword, 137, 10, -7.0f, -15.0f, -2.0f, 3, 3, 3, 0.0f));
        this.sword.field_78804_l.add(new ModelBox(this.sword, 137, 10, 4.0f, -15.0f, -2.0f, 3, 3, 3, 0.0f));
        this.sword.field_78804_l.add(new ModelBox(this.sword, 137, 19, -4.0f, -14.0f, -1.5f, 8, 1, 2, 0.0f));
        this.sword.field_78804_l.add(new ModelBox(this.sword, 163, 5, -3.25f, -47.5f, 0.0f, 7, 33, 1, 0.5f));
        this.sword.field_78804_l.add(new ModelBox(this.sword, 152, 5, -1.0f, -13.0f, -1.0f, 2, 10, 1, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-6.0f, -11.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 83, -3.0f, -5.0f, 1.0f, 6, 20, 6, 0.1f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 26, 83, -3.0f, 14.0f, 1.0f, 6, 17, 6, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 44, 115, -2.0f, 12.0f, 2.0f, 4, 4, 4, 0.1f));
        this.leftfoot = new ModelRenderer((ModelBase)this);
        this.leftfoot.func_78793_a(6.0f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(this.leftfoot);
        this.leftfoot.field_78804_l.add(new ModelBox(this.leftfoot, 2, 111, -9.0f, 31.0f, -1.0f, 6, 4, 8, 0.0f));
        this.leftfoot.field_78804_l.add(new ModelBox(this.leftfoot, 24, 112, -9.0f, 33.0f, -4.0f, 6, 2, 3, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(6.0f, -11.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78809_i = true;
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 83, -3.0f, -5.0f, 1.0f, 6, 20, 6, 0.1f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 44, 115, -2.0f, 12.0f, 2.0f, 4, 4, 4, 0.1f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 26, 83, -3.0f, 14.0f, 1.0f, 6, 17, 6, 0.0f));
        this.rightfoot = new ModelRenderer((ModelBase)this);
        this.rightfoot.func_78793_a(6.0f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(this.rightfoot);
        this.rightfoot.field_78809_i = true;
        this.rightfoot.field_78804_l.add(new ModelBox(this.rightfoot, 2, 111, -9.0f, 31.0f, -1.0f, 6, 4, 8, 0.0f));
        this.rightfoot.field_78804_l.add(new ModelBox(this.rightfoot, 24, 112, -9.0f, 33.0f, -4.0f, 6, 2, 3, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        GL11.glPopMatrix();
        this.Head.field_78796_g = f3 / 35.014088f;
        this.Head.field_78795_f = f4 / 28.647888f;
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

