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

public class ModelbbGoldoozaru
extends ModelBase {
    private final ModelRenderer Head;
    private final ModelRenderer Snout;
    private final ModelRenderer Ear;
    private final ModelRenderer Ear_r1;
    private final ModelRenderer Ear_r2;
    private final ModelRenderer hair;
    private final ModelRenderer hair12;
    private final ModelRenderer hair16;
    private final ModelRenderer bone2_r1_r1;
    private final ModelRenderer hair10;
    private final ModelRenderer hair14;
    private final ModelRenderer hair3;
    private final ModelRenderer hair18;
    private final ModelRenderer hair17;
    private final ModelRenderer hair6;
    private final ModelRenderer hair9;
    private final ModelRenderer hair5;
    private final ModelRenderer hair4;
    private final ModelRenderer hair15;
    private final ModelRenderer hair13;
    private final ModelRenderer hair11;
    private final ModelRenderer hair1;
    private final ModelRenderer hair21;
    private final ModelRenderer hair20;
    private final ModelRenderer hair19;
    private final ModelRenderer bone3_r1_r1;
    private final ModelRenderer hair23;
    private final ModelRenderer bone7_r1;
    private final ModelRenderer bone7_r1_r1;
    private final ModelRenderer hair22;
    private final ModelRenderer hair24;
    private final ModelRenderer hair25;
    private final ModelRenderer hair26;
    private final ModelRenderer bone11_r1;
    private final ModelRenderer hair2;
    private final ModelRenderer hair7;
    private final ModelRenderer hair8;
    private final ModelRenderer Body;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final ModelRenderer RArm;
    private final ModelRenderer RShoulder2;
    private final ModelRenderer RShoulder_r1;
    private final ModelRenderer LArm;
    private final ModelRenderer LShoulder2;
    private final ModelRenderer LShoulder_r1;
    private final ModelRenderer FroB;
    private final ModelRenderer ftailS1;
    private final ModelRenderer ftailS2;
    private final ModelRenderer ftailS3;
    private final ModelRenderer ftailS4;
    private final ModelRenderer ftailS5;
    private final ModelRenderer ftailS6;
    private float scale = 1.0f;

    public ModelbbGoldoozaru(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scale = _scale;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f));
        this.Snout = new ModelRenderer((ModelBase)this);
        this.Snout.func_78793_a(1.0f, -5.0f, -2.0f);
        this.Head.func_78792_a(this.Snout);
        this.Snout.field_78804_l.add(new ModelBox(this.Snout, 114, 0, -3.0f, 2.0f, -5.0f, 4, 3, 3, 0.0f));
        this.Ear = new ModelRenderer((ModelBase)this);
        this.Ear.func_78793_a(0.0f, 0.0f, -1.0f);
        this.Head.func_78792_a(this.Ear);
        this.setRotationAngle(this.Ear, 0.0f, 3.1416f, 0.0f);
        this.Ear_r1 = new ModelRenderer((ModelBase)this);
        this.Ear_r1.func_78793_a(-4.4f, -4.5f, -0.3342f);
        this.Ear_r1.field_78809_i = true;
        this.Ear.func_78792_a(this.Ear_r1);
        this.setRotationAngle(this.Ear_r1, -0.0271f, 0.2606f, -0.0902f);
        this.Ear_r1.field_78804_l.add(new ModelBox(this.Ear_r1, 0, -4, 0.0f, -2.5f, -2.0f, 0, 5, 4, 0.0f));
        this.Ear_r2 = new ModelRenderer((ModelBase)this);
        this.Ear_r2.func_78793_a(4.4f, -4.5f, -0.3342f);
        this.Ear_r2.field_78809_i = true;
        this.Ear.func_78792_a(this.Ear_r2);
        this.setRotationAngle(this.Ear_r2, -0.0271f, -0.2606f, 0.0902f);
        this.Ear_r2.field_78804_l.add(new ModelBox(this.Ear_r2, 0, -4, 0.0f, -2.5f, -2.0f, 0, 5, 4, 0.0f));
        this.hair = new ModelRenderer((ModelBase)this);
        this.hair.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(this.hair);
        this.hair12 = new ModelRenderer((ModelBase)this);
        this.hair12.func_78793_a(-0.3171f, -9.3105f, -4.52f);
        this.hair.func_78792_a(this.hair12);
        this.setRotationAngle(this.hair12, -0.7941f, 0.476f, 0.6803f);
        this.hair16 = new ModelRenderer((ModelBase)this);
        this.hair16.func_78793_a(0.3171f, 33.3105f, 2.52f);
        this.hair12.func_78792_a(this.hair16);
        this.setRotationAngle(this.hair16, 0.0f, -0.0436f, 0.0f);
        this.bone2_r1_r1 = new ModelRenderer((ModelBase)this);
        this.bone2_r1_r1.func_78793_a(0.7082f, -33.7232f, -4.6976f);
        this.hair16.func_78792_a(this.bone2_r1_r1);
        this.setRotationAngle(this.bone2_r1_r1, 0.0724f, -0.0719f, -0.0266f);
        this.bone2_r1_r1.field_78804_l.add(new ModelBox(this.bone2_r1_r1, 54, 5, -1.7001f, -0.8678f, -0.2698f, 2, 2, 3, 0.0f));
        this.hair10 = new ModelRenderer((ModelBase)this);
        this.hair10.func_78793_a(3.5f, -5.5f, 1.5f);
        this.hair.func_78792_a(this.hair10);
        this.setRotationAngle(this.hair10, -0.1745f, 0.0f, 0.7854f);
        this.hair10.field_78804_l.add(new ModelBox(this.hair10, 48, 1, -1.4388f, -0.9f, -1.4f, 3, 2, 3, 0.0f));
        this.hair14 = new ModelRenderer((ModelBase)this);
        this.hair14.func_78793_a(3.5f, -8.5f, 3.5f);
        this.hair.func_78792_a(this.hair14);
        this.setRotationAngle(this.hair14, -0.2182f, 0.1187f, 0.0f);
        this.hair14.field_78804_l.add(new ModelBox(this.hair14, 47, 5, -3.3f, 0.0f, -2.05f, 4, 5, 3, 0.0f));
        this.hair3 = new ModelRenderer((ModelBase)this);
        this.hair3.func_78793_a(5.0f, -7.0f, -2.0f);
        this.hair.func_78792_a(this.hair3);
        this.setRotationAngle(this.hair3, -0.48f, 0.0f, 0.3927f);
        this.hair3.field_78804_l.add(new ModelBox(this.hair3, 45, 3, -4.3f, -1.2f, -0.4266f, 4, 4, 3, 0.0f));
        this.hair18 = new ModelRenderer((ModelBase)this);
        this.hair18.func_78793_a(-6.0f, -1.6f, 2.5f);
        this.hair3.func_78792_a(this.hair18);
        this.setRotationAngle(this.hair18, -0.3491f, 0.0873f, -0.7854f);
        this.hair18.field_78804_l.add(new ModelBox(this.hair18, 41, 5, -3.25f, -2.1784f, -1.3769f, 4, 5, 3, 0.0f));
        this.hair17 = new ModelRenderer((ModelBase)this);
        this.hair17.func_78793_a(-6.3827f, -3.343f, 3.8474f);
        this.hair3.func_78792_a(this.hair17);
        this.setRotationAngle(this.hair17, -0.3491f, 0.3491f, -0.6545f);
        this.hair17.field_78804_l.add(new ModelBox(this.hair17, 41, 5, -2.7173f, -2.1784f, -1.3769f, 3, 5, 3, 0.0f));
        this.hair6 = new ModelRenderer((ModelBase)this);
        this.hair6.func_78793_a(5.0f, -8.0f, -3.0f);
        this.hair.func_78792_a(this.hair6);
        this.setRotationAngle(this.hair6, -0.4799f, 0.0f, 0.1745f);
        this.hair6.field_78804_l.add(new ModelBox(this.hair6, 46, 2, -4.4f, -3.0f, 1.5f, 4, 3, 3, 0.0f));
        this.hair9 = new ModelRenderer((ModelBase)this);
        this.hair9.func_78793_a(2.2f, -8.0f, 1.6f);
        this.hair.func_78792_a(this.hair9);
        this.setRotationAngle(this.hair9, -0.672f, 0.0f, 0.2182f);
        this.hair9.field_78804_l.add(new ModelBox(this.hair9, 47, 2, -2.7f, -2.4f, -2.1f, 4, 6, 3, 0.0f));
        this.hair5 = new ModelRenderer((ModelBase)this);
        this.hair5.func_78793_a(1.0f, -10.0f, -1.0f);
        this.hair.func_78792_a(this.hair5);
        this.setRotationAngle(this.hair5, -0.6545f, 0.0f, 0.0f);
        this.hair5.field_78804_l.add(new ModelBox(this.hair5, 38, 4, -1.0f, -4.366f, 0.366f, 2, 4, 2, 0.0f));
        this.hair4 = new ModelRenderer((ModelBase)this);
        this.hair4.func_78793_a(1.0f, -8.0f, -1.0f);
        this.hair.func_78792_a(this.hair4);
        this.setRotationAngle(this.hair4, -0.5236f, 0.0f, 0.0f);
        this.hair4.field_78804_l.add(new ModelBox(this.hair4, 46, 4, -1.5f, -2.0f, -0.3f, 3, 3, 3, 0.0f));
        this.hair15 = new ModelRenderer((ModelBase)this);
        this.hair15.func_78793_a(-3.0f, -8.0f, 2.5f);
        this.hair.func_78792_a(this.hair15);
        this.setRotationAngle(this.hair15, -0.2182f, -0.1833f, 0.0f);
        this.hair15.field_78804_l.add(new ModelBox(this.hair15, 47, 5, -1.0f, -2.0f, -1.4f, 4, 6, 3, 0.0f));
        this.hair13 = new ModelRenderer((ModelBase)this);
        this.hair13.func_78793_a(0.0f, -6.5f, 4.0f);
        this.hair.func_78792_a(this.hair13);
        this.setRotationAngle(this.hair13, -0.3927f, 0.0f, 0.0f);
        this.hair13.field_78804_l.add(new ModelBox(this.hair13, 39, 7, -2.0f, -1.5f, -1.31f, 4, 4, 2, 0.0f));
        this.hair11 = new ModelRenderer((ModelBase)this);
        this.hair11.func_78793_a(-3.5929f, -5.5f, 1.3044f);
        this.hair.func_78792_a(this.hair11);
        this.setRotationAngle(this.hair11, -1.4835f, 0.0f, 0.7854f);
        this.hair11.field_78804_l.add(new ModelBox(this.hair11, 48, 1, -0.9071f, -1.9f, -1.5f, 3, 3, 3, 0.0f));
        this.hair1 = new ModelRenderer((ModelBase)this);
        this.hair1.func_78793_a(-2.1f, -8.7f, -0.5f);
        this.hair.func_78792_a(this.hair1);
        this.setRotationAngle(this.hair1, -0.3054f, 0.0f, -0.3491f);
        this.hair1.field_78804_l.add(new ModelBox(this.hair1, 40, 4, -2.4f, -1.3f, -2.0f, 4, 3, 4, 0.0f));
        this.hair21 = new ModelRenderer((ModelBase)this);
        this.hair21.func_78793_a(-0.0036f, -6.8f, -3.8536f);
        this.hair.func_78792_a(this.hair21);
        this.setRotationAngle(this.hair21, 0.656f, -0.683f, -0.4463f);
        this.hair21.field_78804_l.add(new ModelBox(this.hair21, 54, 5, -0.3964f, -2.505f, -0.3964f, 2, 3, 2, 0.0f));
        this.hair20 = new ModelRenderer((ModelBase)this);
        this.hair20.func_78793_a(1.7141f, -9.4245f, -6.1921f);
        this.hair.func_78792_a(this.hair20);
        this.setRotationAngle(this.hair20, -0.7778f, -0.4431f, 1.5161f);
        this.hair19 = new ModelRenderer((ModelBase)this);
        this.hair19.func_78793_a(-0.6141f, 0.0f, 1.3921f);
        this.hair20.func_78792_a(this.hair19);
        this.setRotationAngle(this.hair19, 0.0f, 0.6109f, 0.0f);
        this.bone3_r1_r1 = new ModelRenderer((ModelBase)this);
        this.bone3_r1_r1.func_78793_a(0.2335f, -0.0225f, -0.7852f);
        this.hair19.func_78792_a(this.bone3_r1_r1);
        this.setRotationAngle(this.bone3_r1_r1, 0.0495f, -0.0713f, -0.0604f);
        this.bone3_r1_r1.field_78804_l.add(new ModelBox(this.bone3_r1_r1, 54, 5, -1.6109f, -0.8685f, -1.3108f, 2, 2, 2, 0.0f));
        this.hair23 = new ModelRenderer((ModelBase)this);
        this.hair23.func_78793_a(0.5284f, -10.1338f, -5.9196f);
        this.hair.func_78792_a(this.hair23);
        this.setRotationAngle(this.hair23, -0.8459f, 0.5578f, 0.5784f);
        this.bone7_r1 = new ModelRenderer((ModelBase)this);
        this.bone7_r1.func_78793_a(2.2785f, -0.4162f, -2.1804f);
        this.hair23.func_78792_a(this.bone7_r1);
        this.setRotationAngle(this.bone7_r1, 0.0f, 0.2182f, 0.0f);
        this.bone7_r1_r1 = new ModelRenderer((ModelBase)this);
        this.bone7_r1_r1.func_78793_a(-2.099f, -0.0159f, 1.3371f);
        this.bone7_r1.func_78792_a(this.bone7_r1_r1);
        this.setRotationAngle(this.bone7_r1_r1, 0.0752f, -0.0728f, 0.0016f);
        this.bone7_r1_r1.field_78804_l.add(new ModelBox(this.bone7_r1_r1, 54, 5, 0.1458f, -0.8747f, -2.0909f, 3, 2, 2, 0.0f));
        this.hair22 = new ModelRenderer((ModelBase)this);
        this.hair22.func_78793_a(0.9964f, -6.8f, -1.8536f);
        this.hair.func_78792_a(this.hair22);
        this.setRotationAngle(this.hair22, 0.656f, -0.683f, -0.4463f);
        this.hair22.field_78804_l.add(new ModelBox(this.hair22, 54, 5, -0.3964f, -2.505f, -0.3964f, 2, 2, 2, 0.0f));
        this.hair24 = new ModelRenderer((ModelBase)this);
        this.hair24.func_78793_a(1.5052f, -9.3102f, -2.7932f);
        this.hair.func_78792_a(this.hair24);
        this.setRotationAngle(this.hair24, 0.2284f, -0.683f, -0.4463f);
        this.hair24.field_78804_l.add(new ModelBox(this.hair24, 54, 5, -1.0f, -1.0f, -1.5f, 2, 2, 3, 0.0f));
        this.hair25 = new ModelRenderer((ModelBase)this);
        this.hair25.func_78793_a(2.871f, -9.2022f, -4.2502f);
        this.hair.func_78792_a(this.hair25);
        this.setRotationAngle(this.hair25, 0.6211f, -0.683f, -0.4463f);
        this.hair25.field_78804_l.add(new ModelBox(this.hair25, 55, 6, -0.9f, -0.8978f, -1.0998f, 2, 2, 2, 0.0f));
        this.hair26 = new ModelRenderer((ModelBase)this);
        this.hair26.func_78793_a(4.3576f, -9.2548f, -5.3957f);
        this.hair.func_78792_a(this.hair26);
        this.setRotationAngle(this.hair26, 0.6211f, -0.683f, -0.4463f);
        this.bone11_r1 = new ModelRenderer((ModelBase)this);
        this.bone11_r1.func_78793_a(-0.1348f, 1.0367f, 0.0929f);
        this.hair26.func_78792_a(this.bone11_r1);
        this.setRotationAngle(this.bone11_r1, 0.2793f, 0.0f, 0.0f);
        this.bone11_r1.field_78804_l.add(new ModelBox(this.bone11_r1, 54, 5, -1.07f, -1.1609f, -1.089f, 2, 2, 2, 0.0f));
        this.hair2 = new ModelRenderer((ModelBase)this);
        this.hair2.func_78793_a(1.0f, -10.0f, -1.0f);
        this.hair.func_78792_a(this.hair2);
        this.setRotationAngle(this.hair2, -2.0071f, 0.0f, 0.0f);
        this.hair2.field_78804_l.add(new ModelBox(this.hair2, 45, 3, -1.5f, -1.0f, -1.0f, 3, 3, 4, 0.0f));
        this.hair7 = new ModelRenderer((ModelBase)this);
        this.hair7.func_78793_a(-2.0f, -8.4422f, -2.1716f);
        this.hair.func_78792_a(this.hair7);
        this.setRotationAngle(this.hair7, -0.2926f, 0.6575f, 0.1295f);
        this.hair7.field_78804_l.add(new ModelBox(this.hair7, 38, 4, -0.41f, -1.6f, -0.7f, 2, 3, 2, 0.0f));
        this.hair8 = new ModelRenderer((ModelBase)this);
        this.hair8.func_78793_a(-1.2151f, -10.3399f, -0.7827f);
        this.hair.func_78792_a(this.hair8);
        this.setRotationAngle(this.hair8, -0.2753f, 0.5737f, 0.1595f);
        this.hair8.field_78804_l.add(new ModelBox(this.hair8, 38, 4, -1.0f, -1.5f, -1.0f, 2, 3, 2, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(-3.8f, 5.4f, 3.8f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 16, 16, -0.2f, -5.4f, -5.8f, 8, 12, 4, 0.0f));
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(1.8f, 6.6f, -3.8f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(5.8f, 6.6f, -3.8f);
        this.LLeg.field_78809_i = true;
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 16, -2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-0.2f, -3.4f, -3.8f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -4.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.RShoulder2 = new ModelRenderer((ModelBase)this);
        this.RShoulder2.func_78793_a(-15.0f, -1.0f, 0.5f);
        this.RArm.func_78792_a(this.RShoulder2);
        this.setRotationAngle(this.RShoulder2, 0.0f, 3.1416f, 0.0873f);
        this.RShoulder_r1 = new ModelRenderer((ModelBase)this);
        this.RShoulder_r1.func_78793_a(-10.6503f, -1.7769f, 0.0f);
        this.RShoulder_r1.field_78809_i = true;
        this.RShoulder2.func_78792_a(this.RShoulder_r1);
        this.setRotationAngle(this.RShoulder_r1, 0.0f, 0.0f, -0.3054f);
        this.RShoulder_r1.field_78804_l.add(new ModelBox(this.RShoulder_r1, 64, 0, -3.5f, -2.5f, -2.5f, 7, 5, 6, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(7.8f, -3.4f, -3.8f);
        this.LArm.field_78809_i = true;
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, 0.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LShoulder2 = new ModelRenderer((ModelBase)this);
        this.LShoulder2.func_78793_a(15.0f, -1.0f, 0.5f);
        this.LArm.func_78792_a(this.LShoulder2);
        this.setRotationAngle(this.LShoulder2, 0.0f, -3.1416f, -0.0873f);
        this.LShoulder_r1 = new ModelRenderer((ModelBase)this);
        this.LShoulder_r1.func_78793_a(10.6503f, -1.7769f, 0.0f);
        this.LShoulder2.func_78792_a(this.LShoulder_r1);
        this.setRotationAngle(this.LShoulder_r1, 0.0f, 0.0f, 0.3054f);
        this.LShoulder_r1.field_78804_l.add(new ModelBox(this.LShoulder_r1, 64, 0, -3.5f, -2.5f, -2.5f, 7, 5, 6, 0.0f));
        this.FroB = new ModelRenderer((ModelBase)this);
        this.FroB.func_78793_a(-2.3f, 16.0f, 2.0f);
        this.ftailS1 = new ModelRenderer((ModelBase)this);
        this.ftailS1.func_78793_a(8.0f, -4.0f, -4.0f);
        this.FroB.func_78792_a(this.ftailS1);
        this.ftailS1.field_78804_l.add(new ModelBox(this.ftailS1, 98, 2, -2.5f, -2.0f, -1.0f, 4, 4, 6, 0.0f));
        this.ftailS2 = new ModelRenderer((ModelBase)this);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.ftailS2.field_78804_l.add(new ModelBox(this.ftailS2, 98, 2, -2.5f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS3 = new ModelRenderer((ModelBase)this);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS3.field_78804_l.add(new ModelBox(this.ftailS3, 98, 2, -2.5f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS4 = new ModelRenderer((ModelBase)this);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS4.field_78804_l.add(new ModelBox(this.ftailS4, 98, 2, -2.5f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS5 = new ModelRenderer((ModelBase)this);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS5.field_78804_l.add(new ModelBox(this.ftailS5, 98, 2, -2.5f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
        this.ftailS6 = new ModelRenderer((ModelBase)this);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 98, 2, -2.5f, -2.0f, 0.0f, 4, 4, 6, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.renderHairs(0.0625f, "FR", f2);
        GL11.glPopMatrix();
        this.Head.field_78796_g = f3 / 50.92958f;
        this.Head.field_78795_f = f4 / 50.92958f;
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

    private void transRot(float f5, ModelRenderer m) {
        GL11.glTranslatef((float)(m.field_78800_c * f5), (float)(m.field_78797_d * f5), (float)(m.field_78798_e * f5));
        if (m.field_78808_h != 0.0f) {
            GL11.glRotatef((float)(m.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (m.field_78796_g != 0.0f) {
            GL11.glRotatef((float)(m.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (m.field_78795_f != 0.0f) {
            GL11.glRotatef((float)(m.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public String renderHairs(float par1, String hair, float par3) {
        if (hair.equals("FR")) {
            GL11.glPushMatrix();
            this.transRot(par1, this.Body);
            GL11.glScalef((float)0.7f, (float)0.35f, (float)1.0f);
            this.FroB.func_78785_a(par1);
            GL11.glScaled((double)0.1, (double)0.1, (double)0.1);
            float r = MathHelper.func_76126_a((float)(par3 * 0.02f)) * 0.1f;
            float r2 = MathHelper.func_76134_b((float)(par3 * 0.02f)) * 0.1f;
            float r3 = MathHelper.func_76134_b((float)(par3 * 0.14f)) * 0.1f;
            this.ftailS1.field_78796_g = 0.2f;
            this.ftailS1.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.2f - 0.2f + r;
            this.ftailS1.field_78795_f = -0.3f;
            this.ftailS2.field_78796_g = 0.2f;
            this.ftailS2.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
            this.ftailS2.field_78795_f = 0.4f;
            this.ftailS3.field_78796_g = 0.1f;
            this.ftailS3.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.1f - 0.1f + r + r3;
            this.ftailS3.field_78795_f = 0.6f;
            this.ftailS3.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.4f + 0.3f;
            this.ftailS4.field_78796_g = 0.1f;
            this.ftailS4.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.1f + r2;
            this.ftailS4.field_78795_f = 0.3f;
            this.ftailS4.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.1f - 0.2f;
            this.ftailS5.field_78796_g = 0.2f;
            this.ftailS5.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            this.ftailS5.field_78795_f = -0.2f;
            this.ftailS5.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.1f - 0.3f;
            this.ftailS6.field_78796_g = 0.2f;
            this.ftailS6.field_78796_g += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.4f - 0.2f + r2 + r3;
            this.ftailS6.field_78795_f = -0.4f;
            this.ftailS6.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.09f)) * 0.4f - 0.4f;
            GL11.glPopMatrix();
        }
        return "";
    }
}

