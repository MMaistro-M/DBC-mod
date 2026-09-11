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

public class ModelVegetaSSJ4
extends ModelBase {
    private final ModelRenderer FroB;
    private final ModelRenderer ftailS1;
    private final ModelRenderer ftailS2;
    private final ModelRenderer ftailS3;
    private final ModelRenderer ftailS4;
    private final ModelRenderer ftailS5;
    private final ModelRenderer ftailS6;
    private final ModelRenderer Head;
    private final ModelRenderer RArm;
    private final ModelRenderer LArm;
    private final ModelRenderer Body;
    private final ModelRenderer RLeg;
    private final ModelRenderer LLeg;
    private final float scale = 1.0f;

    public ModelVegetaSSJ4(float _scale) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.FroB = new ModelRenderer((ModelBase)this);
        this.FroB.func_78793_a(0.0f, 10.0f, 2.0f);
        this.ftailS1 = new ModelRenderer((ModelBase)this);
        this.ftailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.FroB.func_78792_a(this.ftailS1);
        this.ftailS1.field_78804_l.add(new ModelBox(this.ftailS1, 94, 7, -1.5f, -2.0f, 0.0f, 3, 4, 6, 0.0f));
        this.ftailS2 = new ModelRenderer((ModelBase)this);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.ftailS2.field_78804_l.add(new ModelBox(this.ftailS2, 94, 7, -1.5f, -2.0f, 0.0f, 3, 4, 6, 0.0f));
        this.ftailS3 = new ModelRenderer((ModelBase)this);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS3.field_78804_l.add(new ModelBox(this.ftailS3, 94, 7, -1.5f, -2.0f, 0.0f, 3, 4, 6, 0.0f));
        this.ftailS4 = new ModelRenderer((ModelBase)this);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS4.field_78804_l.add(new ModelBox(this.ftailS4, 94, 7, -1.5f, -2.0f, 0.0f, 3, 4, 6, 0.0f));
        this.ftailS5 = new ModelRenderer((ModelBase)this);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS5.field_78804_l.add(new ModelBox(this.ftailS5, 94, 7, -1.5f, -2.0f, 0.0f, 3, 4, 6, 0.0f));
        this.ftailS6 = new ModelRenderer((ModelBase)this);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 5.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS6.field_78804_l.add(new ModelBox(this.ftailS6, 94, 7, -1.5f, -2.0f, 0.0f, 3, 4, 6, 0.0f));
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(0.0f, 0.1f, 0.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 0, 0, -4.0f, -8.0f, -4.0f, 8, 8, 8, -0.1f));
        ModelRenderer bipedHeadsvChild_9 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_9);
        this.setRotationAngle(bipedHeadsvChild_9, -0.4363f, 0.0f, -0.4014f);
        bipedHeadsvChild_9.field_78804_l.add(new ModelBox(bipedHeadsvChild_9, 32, 0, -1.0f, -10.0f, -6.2f, 4, 6, 4, -0.1f));
        ModelRenderer bipedHeadsvChild_11 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_11);
        this.setRotationAngle(bipedHeadsvChild_11, -0.6109f, 0.0f, -0.2443f);
        bipedHeadsvChild_11.field_78804_l.add(new ModelBox(bipedHeadsvChild_11, 32, 0, -0.5f, -14.0f, -6.0f, 3, 3, 3, -0.1f));
        ModelRenderer SSJ3Parte2 = new ModelRenderer((ModelBase)this);
        SSJ3Parte2.func_78793_a(0.0f, -9.3f, -5.2f);
        this.Head.func_78792_a(SSJ3Parte2);
        SSJ3Parte2.field_78804_l.add(new ModelBox(SSJ3Parte2, 0, 0, 0.0f, 0.0f, 0.0f, 0, 0, 0, -0.1f));
        ModelRenderer bipedHeadssj3lChild_6 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_6.func_78793_a(0.1745f, 4.5618f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_6);
        bipedHeadssj3lChild_6.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_6, 32, 0, -0.9f, 1.2667f, 4.0f, 4, 5, 4, -0.1f));
        ModelRenderer bipedHeadssj3lChild_4 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_4.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_4);
        this.setRotationAngle(bipedHeadssj3lChild_4, 0.1571f, -0.2618f, 0.0f);
        bipedHeadssj3lChild_4.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_4, 32, 0, -2.4667f, 2.0f, 4.0f, 4, 6, 4, -0.1f));
        ModelRenderer bipedHeadssj3lChild_3 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_3.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_3);
        this.setRotationAngle(bipedHeadssj3lChild_3, 0.2269f, 0.0f, -0.2094f);
        bipedHeadssj3lChild_3.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_3, 32, 0, 2.17f, 1.73f, 3.0f, 3, 5, 3, -0.1f));
        ModelRenderer bipedHeadssj3lChild_1 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_1.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_1);
        this.setRotationAngle(bipedHeadssj3lChild_1, 0.1396f, 0.0f, 0.0873f);
        bipedHeadssj3lChild_1.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_1, 32, 0, -5.4667f, 4.2f, 3.0f, 3, 5, 3, -0.1f));
        ModelRenderer bipedHeadssj3lChild_8 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_8.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_8);
        this.setRotationAngle(bipedHeadssj3lChild_8, 0.2793f, 0.0f, 0.0f);
        bipedHeadssj3lChild_8.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_8, 32, 0, -2.0f, 1.0f, 4.0f, 4, 6, 4, -0.1f));
        ModelRenderer bipedHeadssj3lChild_10 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_10.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_10);
        this.setRotationAngle(bipedHeadssj3lChild_10, -0.9425f, 0.1222f, 0.0f);
        bipedHeadssj3lChild_10.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_10, 32, 0, -5.7f, -2.1f, 2.0f, 2, 5, 3, -0.1f));
        ModelRenderer bipedHeadssj3lChild_7 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_7.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_7);
        this.setRotationAngle(bipedHeadssj3lChild_7, 0.1745f, 0.0f, 0.0f);
        bipedHeadssj3lChild_7.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_7, 33, 1, -1.9333f, 6.0f, 5.0f, 4, 6, 3, -0.1f));
        ModelRenderer bipedHeadssj3lChild = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild.func_78793_a(0.1396f, 4.3f, 2.1127f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild);
        this.setRotationAngle(bipedHeadssj3lChild, 0.1396f, 0.0f, -0.0873f);
        bipedHeadssj3lChild.field_78804_l.add(new ModelBox(bipedHeadssj3lChild, 32, 0, 2.5333f, 4.2f, 3.0f, 2, 5, 3, -0.1f));
        ModelRenderer bipedHeadssj3lChild_5 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_5.func_78793_a(0.1047f, 4.5618f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_5);
        this.setRotationAngle(bipedHeadssj3lChild_5, 0.1047f, 0.2618f, 0.0f);
        bipedHeadssj3lChild_5.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_5, 32, 0, -1.0f, 4.9667f, 4.0f, 4, 4, 4, -0.1f));
        ModelRenderer bipedHeadssj3lChild_9 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_9.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_9);
        this.setRotationAngle(bipedHeadssj3lChild_9, -0.9425f, -0.1222f, 0.0f);
        bipedHeadssj3lChild_9.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_9, 32, 0, 3.7f, -2.1f, 2.0f, 2, 5, 3, -0.1f));
        ModelRenderer bipedHeadssj3lChild_2 = new ModelRenderer((ModelBase)this);
        bipedHeadssj3lChild_2.func_78793_a(0.0f, 4.3f, 2.2f);
        SSJ3Parte2.func_78792_a(bipedHeadssj3lChild_2);
        this.setRotationAngle(bipedHeadssj3lChild_2, 0.2269f, 0.0f, 0.2094f);
        bipedHeadssj3lChild_2.field_78804_l.add(new ModelBox(bipedHeadssj3lChild_2, 32, 0, -5.17f, 1.73f, 3.0f, 3, 5, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_7 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_7);
        this.setRotationAngle(bipedHeadsvChild_7, -0.5934f, 0.0f, 0.0f);
        bipedHeadsvChild_7.field_78804_l.add(new ModelBox(bipedHeadsvChild_7, 32, 0, 0.0f, -14.0f, -7.0f, 2, 3, 2, -0.1f));
        ModelRenderer bipedHeadsvChild_5 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_5);
        this.setRotationAngle(bipedHeadsvChild_5, 0.0f, -0.3491f, -0.925f);
        bipedHeadsvChild_5.field_78804_l.add(new ModelBox(bipedHeadsvChild_5, 32, 0, 7.0f, -2.0f, -1.5f, 3, 2, 2, -0.1f));
        ModelRenderer bipedHeadsvChild_15 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_15);
        this.setRotationAngle(bipedHeadsvChild_15, -0.576f, 0.0f, 0.1396f);
        bipedHeadsvChild_15.field_78804_l.add(new ModelBox(bipedHeadsvChild_15, 32, 0, -1.0f, -12.0f, -6.0f, 3, 3, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_19 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_19);
        this.setRotationAngle(bipedHeadsvChild_19, -0.1396f, 0.0f, 0.0f);
        bipedHeadsvChild_19.field_78804_l.add(new ModelBox(bipedHeadsvChild_19, 32, 0, -2.0f, -13.0f, -1.0f, 3, 5, 4, -0.1f));
        ModelRenderer bipedHeadsvChild_17 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_17);
        this.setRotationAngle(bipedHeadsvChild_17, -0.2443f, 0.2618f, 0.0175f);
        bipedHeadsvChild_17.field_78804_l.add(new ModelBox(bipedHeadsvChild_17, 32, 0, -1.0f, -10.0f, -1.0f, 4, 5, 4, -0.1f));
        ModelRenderer bipedHeadsvChild_6 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_6);
        this.setRotationAngle(bipedHeadsvChild_6, -0.4363f, 0.0f, 0.0f);
        bipedHeadsvChild_6.field_78804_l.add(new ModelBox(bipedHeadsvChild_6, 32, 0, -0.5f, -12.0f, -6.0f, 3, 3, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_3 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_3);
        this.setRotationAngle(bipedHeadsvChild_3, 0.0f, -0.1745f, -0.6109f);
        bipedHeadsvChild_3.field_78804_l.add(new ModelBox(bipedHeadsvChild_3, 32, 0, 4.0f, -4.0f, -1.0f, 4, 3, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_10 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_10);
        this.setRotationAngle(bipedHeadsvChild_10, -0.5411f, 0.0f, -0.3665f);
        bipedHeadsvChild_10.field_78804_l.add(new ModelBox(bipedHeadsvChild_10, 32, 0, -0.5f, -12.0f, -6.0f, 5, 4, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_16 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_16);
        this.setRotationAngle(bipedHeadsvChild_16, -0.2793f, 0.0f, 0.0f);
        bipedHeadsvChild_16.field_78804_l.add(new ModelBox(bipedHeadsvChild_16, 32, 0, -2.0f, -9.0f, -1.0f, 4, 5, 4, -0.1f));
        ModelRenderer bipedHeadsvChild_12 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_12);
        this.setRotationAngle(bipedHeadsvChild_12, -0.6981f, 0.0f, -0.1222f);
        bipedHeadsvChild_12.field_78804_l.add(new ModelBox(bipedHeadsvChild_12, 32, 0, 0.0f, -15.4f, -7.0f, 2, 5, 2, -0.1f));
        ModelRenderer bipedHeadsvChild = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild);
        this.setRotationAngle(bipedHeadsvChild, -0.3142f, 0.0f, 0.0f);
        bipedHeadsvChild.field_78804_l.add(new ModelBox(bipedHeadsvChild, 32, 0, -1.0f, -10.0f, -6.05f, 4, 4, 4, -0.1f));
        ModelRenderer bipedHeadsvChild_14 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_14.func_78793_a(-0.5411f, 0.0f, 0.2455f);
        this.Head.func_78792_a(bipedHeadsvChild_14);
        this.setRotationAngle(bipedHeadsvChild_14, -0.5411f, 0.0f, 0.2455f);
        bipedHeadsvChild_14.field_78804_l.add(new ModelBox(bipedHeadsvChild_14, 32, 0, -0.5f, -10.0f, -6.0f, 3, 3, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_8 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_8.func_78793_a(0.0f, 0.3491f, 0.8901f);
        this.Head.func_78792_a(bipedHeadsvChild_8);
        this.setRotationAngle(bipedHeadsvChild_8, 0.0f, 0.3491f, 0.8901f);
        bipedHeadsvChild_8.field_78804_l.add(new ModelBox(bipedHeadsvChild_8, 32, 0, -10.0f, -2.1667f, -1.5f, 3, 2, 2, -0.1f));
        ModelRenderer bipedHeadsvChild_18 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_18.func_78793_a(-0.2443f, -0.2618f, 0.0175f);
        this.Head.func_78792_a(bipedHeadsvChild_18);
        this.setRotationAngle(bipedHeadsvChild_18, -0.2443f, -0.2618f, 0.0175f);
        bipedHeadsvChild_18.field_78804_l.add(new ModelBox(bipedHeadsvChild_18, 32, 0, -4.0f, -11.0f, -1.0f, 4, 6, 4, -0.1f));
        ModelRenderer bipedHeadsvChild_13 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_13.func_78793_a(-0.3665f, 0.0f, 0.4363f);
        this.Head.func_78792_a(bipedHeadsvChild_13);
        this.setRotationAngle(bipedHeadsvChild_13, -0.3665f, 0.0f, 0.4363f);
        bipedHeadsvChild_13.field_78804_l.add(new ModelBox(bipedHeadsvChild_13, 32, 0, -1.5f, -9.0f, -5.5f, 3, 5, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_20 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_20.func_78793_a(-0.1222f, 0.1745f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_20);
        this.setRotationAngle(bipedHeadsvChild_20, -0.1222f, 0.1745f, 0.0f);
        bipedHeadsvChild_20.field_78804_l.add(new ModelBox(bipedHeadsvChild_20, 32, 0, -1.0f, -14.0f, 0.0f, 3, 5, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_21 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_21.func_78793_a(-0.2443f, -0.2618f, 0.0175f);
        this.Head.func_78792_a(bipedHeadsvChild_21);
        this.setRotationAngle(bipedHeadsvChild_21, -0.2443f, -0.2618f, 0.0175f);
        bipedHeadsvChild_21.field_78804_l.add(new ModelBox(bipedHeadsvChild_21, 32, 0, -2.8667f, -13.2f, -0.6667f, 3, 4, 3, -0.1f));
        ModelRenderer bipedHeadsvChild_4 = new ModelRenderer((ModelBase)this);
        bipedHeadsvChild_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Head.func_78792_a(bipedHeadsvChild_4);
        this.setRotationAngle(bipedHeadsvChild_4, 0.0f, 0.1745f, 0.6109f);
        bipedHeadsvChild_4.field_78804_l.add(new ModelBox(bipedHeadsvChild_4, 32, 0, -8.0f, -4.0f, -1.0f, 4, 3, 3, -0.1f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 40, 16, -3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.LArm.field_78809_i = true;
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 40, 16, -1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f));
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
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scale, (float)this.scale, (float)this.scale);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scale / 0.45f, 1.88f)))), (float)0.0f);
        this.Head.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.RArm.func_78785_a(f5);
        this.LArm.func_78785_a(f5);
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
            GL11.glScalef((float)1.0f, (float)0.35f, (float)1.0f);
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

