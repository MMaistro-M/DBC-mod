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

public class ModelDrWheelo
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

    public ModelDrWheelo(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.field_78090_t = 256;
        this.field_78089_u = 256;
        this.Head = new ModelRenderer((ModelBase)this);
        this.Head.func_78793_a(2.0f, 2.0f, -4.0f);
        this.Head.field_78804_l.add(new ModelBox(this.Head, 47, 50, -10.0f, -34.0f, -11.0f, 16, 17, 11, 0.0f));
        ModelRenderer bone2 = new ModelRenderer((ModelBase)this);
        bone2.func_78793_a(-2.0f, 22.0f, 4.0f);
        this.Head.func_78792_a(bone2);
        bone2.field_78804_l.add(new ModelBox(bone2, 60, 109, -7.0f, -55.0f, -14.0f, 14, 16, 1, 0.0f));
        bone2.field_78804_l.add(new ModelBox(bone2, 70, 25, -9.0f, -48.0f, -12.0f, 18, 13, 8, 0.0f));
        bone2.field_78804_l.add(new ModelBox(bone2, 0, 61, -6.0f, -35.0f, -10.0f, 12, 3, 6, 0.0f));
        this.Body = new ModelRenderer((ModelBase)this);
        this.Body.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 70, -8.0f, -48.0f, -4.0f, 16, 20, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 93, 63, -6.0f, -48.0f, 4.0f, 12, 10, 8, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 101, 0, -4.0f, -38.0f, 4.0f, 9, 5, 7, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 0, -9.0f, -60.0f, -12.0f, 18, 12, 21, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 34, 37, -7.0f, -57.0f, 9.0f, 14, 9, 4, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 57, 0, -7.0f, -64.0f, -9.0f, 14, 4, 16, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 15, 48, -3.0f, -26.0f, 3.0f, 5, 4, 9, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 0, 53, -3.0f, -44.0f, -16.0f, 5, 2, 1, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 48, 98, -7.0f, -28.0f, -3.0f, 13, 5, 6, 0.0f));
        this.Body.field_78804_l.add(new ModelBox(this.Body, 109, 88, -6.0f, -23.0f, -2.0f, 11, 3, 4, 0.0f));
        ModelRenderer bone4 = new ModelRenderer((ModelBase)this);
        bone4.func_78793_a(-0.5f, -22.0f, 28.0f);
        this.setRotationAngle(bone4, 0.6981f, 0.0f, 0.0f);
        this.Body.func_78792_a(bone4);
        bone4.field_78804_l.add(new ModelBox(bone4, 8, 41, -2.5f, -14.2846f, -12.2567f, 5, 4, 16, 0.0f));
        ModelRenderer bone5 = new ModelRenderer((ModelBase)this);
        bone5.func_78793_a(0.0f, -16.7014f, 6.0371f);
        this.setRotationAngle(bone5, 0.5236f, 0.0f, 0.0f);
        bone4.func_78792_a(bone5);
        bone5.field_78804_l.add(new ModelBox(bone5, 0, 14, -1.5f, 1.0f, 17.0f, 3, 2, 5, 0.0f));
        bone5.field_78804_l.add(new ModelBox(bone5, 42, 50, 0.5f, 1.0f, 21.0f, 1, 2, 5, 0.0f));
        bone5.field_78804_l.add(new ModelBox(bone5, 0, 33, -2.5f, 0.0f, -6.0f, 5, 4, 24, 0.0f));
        ModelRenderer spikes = new ModelRenderer((ModelBase)this);
        spikes.func_78793_a(-6.0f, -52.0f, 15.0f);
        this.Body.func_78792_a(spikes);
        spikes.field_78804_l.add(new ModelBox(spikes, 34, 17, 1.0f, -14.0f, -24.0f, 0, 2, 16, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 0, 67, 1.0f, -21.0f, -24.0f, 0, 7, 3, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 12, 50, 1.0f, -24.0f, -22.0f, 0, 3, 3, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 0, 0, 1.0f, -19.0f, -19.0f, 0, 5, 3, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 49, 49, 1.0f, -22.0f, -17.0f, 0, 3, 3, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 0, 12, 1.0f, -17.0f, -14.0f, 0, 3, 2, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 10, 0, 1.0f, -19.0f, -13.0f, 0, 2, 2, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 0, 0, 1.0f, -16.0f, -10.0f, 0, 2, 1, 0.0f));
        spikes.field_78804_l.add(new ModelBox(spikes, 4, 0, 1.0f, -17.0f, -9.0f, 0, 1, 1, 0.0f));
        ModelRenderer bone = new ModelRenderer((ModelBase)this);
        bone.func_78793_a(10.0f, 0.0f, 0.0f);
        spikes.func_78792_a(bone);
        bone.field_78804_l.add(new ModelBox(bone, 34, 19, 1.0f, -14.0f, -24.0f, 0, 2, 16, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 40, 67, 1.0f, -21.0f, -24.0f, 0, 7, 3, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 0, 58, 1.0f, -24.0f, -22.0f, 0, 3, 3, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 11, 11, 1.0f, -19.0f, -19.0f, 0, 5, 3, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 18, 50, 1.0f, -22.0f, -17.0f, 0, 3, 3, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 17, 12, 1.0f, -17.0f, -14.0f, 0, 3, 2, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 0, 15, 1.0f, -19.0f, -13.0f, 0, 2, 2, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 2, 0, 1.0f, -16.0f, -10.0f, 0, 2, 1, 0.0f));
        bone.field_78804_l.add(new ModelBox(bone, 4, 1, 1.0f, -17.0f, -9.0f, 0, 1, 1, 0.0f));
        this.RArm = new ModelRenderer((ModelBase)this);
        this.RArm.func_78793_a(-9.0f, -48.0f, -2.0f);
        this.setRotationAngle(this.RArm, 0.0f, 0.0f, 0.1309f);
        this.Body.func_78792_a(this.RArm);
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 86, 86, -6.9914f, 1.1305f, -2.0f, 6, 28, 7, 0.0f));
        this.RArm.field_78804_l.add(new ModelBox(this.RArm, 63, 110, -5.0f, -7.0f, -4.0f, 5, 10, 12, 0.0f));
        ModelRenderer right_glove = new ModelRenderer((ModelBase)this);
        right_glove.func_78793_a(-4.0513f, 3.1139f, 6.0f);
        this.setRotationAngle(right_glove, -1.5707f, 1.5272f, -1.5707f);
        this.RArm.func_78792_a(right_glove);
        right_glove.field_78804_l.add(new ModelBox(right_glove, 134, 21, -1.0f, 22.0f, -2.0f, 1, 4, 4, 0.0f));
        right_glove.field_78804_l.add(new ModelBox(right_glove, 20, 132, 0.0f, 22.0f, -3.0f, 1, 4, 6, 0.0f));
        right_glove.field_78804_l.add(new ModelBox(right_glove, 116, 116, -1.0f, 11.8861f, -3.0f, 1, 11, 6, 0.0f));
        right_glove.field_78804_l.add(new ModelBox(right_glove, 34, 46, -0.9487f, 5.0f, -2.0f, 0, 7, 4, 0.0f));
        right_glove.field_78804_l.add(new ModelBox(right_glove, 142, 16, -1.0f, 0.0f, -1.0f, 1, 5, 2, 0.0f));
        right_glove.field_78804_l.add(new ModelBox(right_glove, 0, 0, 0.0f, 16.0f, -4.0f, 1, 6, 8, 0.0f));
        ModelRenderer right_gloove_addon = new ModelRenderer((ModelBase)this);
        right_gloove_addon.func_78793_a(0.0f, 0.0f, 1.0f);
        this.setRotationAngle(right_gloove_addon, 0.0f, 0.0f, -0.3491f);
        right_glove.func_78792_a(right_gloove_addon);
        right_gloove_addon.field_78804_l.add(new ModelBox(right_gloove_addon, 16, 33, -10.0f, 24.0f, -2.0f, 1, 4, 2, 0.0f));
        right_gloove_addon.field_78804_l.add(new ModelBox(right_gloove_addon, 100, 46, -9.0f, 24.0f, -3.0f, 1, 4, 4, 0.0f));
        ModelRenderer right_glove2 = new ModelRenderer((ModelBase)this);
        right_glove2.func_78793_a(-4.6233f, 28.4135f, -3.0f);
        this.setRotationAngle(right_glove2, -1.5707f, -1.5272f, 1.5707f);
        this.RArm.func_78792_a(right_glove2);
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 124, 95, -1.0f, -3.0f, -2.0f, 1, 2, 4, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 132, 124, 0.0f, -3.0f, -3.0f, 1, 2, 6, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 127, 6, -1.0f, -12.0f, -3.0f, 1, 9, 6, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 26, 120, 0.0f, -7.0f, -4.0f, 1, 4, 8, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 52, 126, 1.0f, -7.0f, -4.0f, 1, 3, 8, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 123, 48, 1.6319f, -7.0f, -3.0f, 4, 3, 6, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 114, 133, -1.0f, -17.0f, -2.0f, 1, 5, 4, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 122, 125, 6.0f, -7.0f, -4.0f, 1, 3, 8, 0.0f));
        right_glove2.field_78804_l.add(new ModelBox(right_glove2, 72, 78, -1.0f, -22.0f, -1.0f, 1, 5, 2, 0.0f));
        ModelRenderer right_gloove_addon2 = new ModelRenderer((ModelBase)this);
        right_gloove_addon2.func_78793_a(0.0f, 0.0f, -1.0f);
        this.setRotationAngle(right_gloove_addon2, 0.0f, 0.0f, -0.3491f);
        right_glove2.func_78792_a(right_gloove_addon2);
        right_gloove_addon2.field_78804_l.add(new ModelBox(right_gloove_addon2, 0, 43, -1.0f, -1.0f, 0.0f, 1, 4, 2, 0.0f));
        right_gloove_addon2.field_78804_l.add(new ModelBox(right_gloove_addon2, 139, 87, 0.0f, -1.0f, -1.0f, 1, 4, 4, 0.0f));
        this.LArm = new ModelRenderer((ModelBase)this);
        this.LArm.func_78793_a(9.0f, -48.0f, 0.0f);
        this.setRotationAngle(this.LArm, 0.0f, 0.0f, -0.1309f);
        this.Body.func_78792_a(this.LArm);
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 0, 98, 1.0f, 0.0f, -4.0f, 6, 28, 7, 0.0f));
        this.LArm.field_78804_l.add(new ModelBox(this.LArm, 101, 46, 0.0f, -7.0f, -6.0f, 5, 10, 12, 0.0f));
        ModelRenderer left_gloove = new ModelRenderer((ModelBase)this);
        left_gloove.func_78793_a(4.6335f, 27.7738f, -4.0f);
        this.setRotationAngle(left_gloove, 3.1416f, -1.5272f, -3.1416f);
        this.LArm.func_78792_a(left_gloove);
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 80, 98, -1.0f, -2.0f, -2.0f, 1, 2, 4, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 62, 126, 0.0f, -2.0f, -3.0f, 1, 2, 6, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 100, 125, -1.0f, -11.0f, -3.0f, 1, 9, 6, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 36, 61, -1.0f, -16.0f, -2.0f, 1, 5, 4, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 72, 78, -1.0f, -21.0f, -1.0f, 1, 5, 2, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 90, 46, 0.0f, -6.0f, -4.0f, 1, 4, 8, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 82, 125, 1.0f, -6.0f, -4.0f, 1, 3, 8, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 123, 39, 1.3665f, -6.0f, -3.0f, 4, 3, 6, 0.0f));
        left_gloove.field_78804_l.add(new ModelBox(left_gloove, 122, 104, 6.0f, -6.0f, -4.0f, 1, 3, 8, 0.0f));
        ModelRenderer right_gloove_addon3 = new ModelRenderer((ModelBase)this);
        right_gloove_addon3.func_78793_a(0.0f, 0.0f, -1.0f);
        this.setRotationAngle(right_gloove_addon3, 0.0f, 0.0f, -0.3491f);
        left_gloove.func_78792_a(right_gloove_addon3);
        right_gloove_addon3.field_78804_l.add(new ModelBox(right_gloove_addon3, 0, 33, -1.8776f, -0.6835f, 0.0872f, 1, 4, 2, 0.0f));
        right_gloove_addon3.field_78804_l.add(new ModelBox(right_gloove_addon3, 10, 0, -0.9629f, -0.5756f, -0.9034f, 1, 4, 4, 0.0f));
        ModelRenderer left_glove3 = new ModelRenderer((ModelBase)this);
        left_glove3.func_78793_a(4.0257f, 26.2751f, 4.0f);
        this.setRotationAngle(left_glove3, -1.5708f, 1.5708f, -1.5708f);
        this.LArm.func_78792_a(left_glove3);
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 135, 57, -1.0f, -1.6667f, -2.0f, 1, 4, 4, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 0, 133, 0.0f, -1.6667f, -3.0f, 1, 4, 6, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 116, 95, -1.0f, -12.6667f, -3.0f, 1, 11, 6, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 26, 98, -1.0f, -18.6667f, -2.0f, 1, 6, 4, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 135, 0, 1.0f, 0.0f, -3.0f, 1, 4, 4, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 16, 43, 0.0f, 0.0f, -2.0f, 1, 4, 2, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 90, 46, -1.0f, -24.0f, -1.0f, 1, 5, 2, 0.0f));
        left_glove3.field_78804_l.add(new ModelBox(left_glove3, 74, 78, 0.0f, -8.0f, -4.0f, 1, 6, 8, 0.0f));
        ModelRenderer left_gloove_addon4 = new ModelRenderer((ModelBase)this);
        left_gloove_addon4.func_78793_a(1.7997f, -0.1305f, 33.4451f);
        this.setRotationAngle(left_gloove_addon4, 0.0436f, 0.0f, -2.9671f);
        left_glove3.func_78792_a(left_gloove_addon4);
        this.RLeg = new ModelRenderer((ModelBase)this);
        this.RLeg.func_78793_a(-7.0f, -20.0f, -1.0f);
        this.setRotationAngle(this.RLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.RLeg);
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 150, 0, -3.0f, -1.5185f, -2.3466f, 4, 10, 4, 0.0f));
        this.RLeg.field_78804_l.add(new ModelBox(this.RLeg, 0, 33, -2.0f, -4.649f, -1.3552f, 5, 4, 6, 0.0f));
        ModelRenderer rightLeg = new ModelRenderer((ModelBase)this);
        rightLeg.func_78793_a(-8.0f, 19.351f, 6.6448f);
        this.setRotationAngle(rightLeg, 0.1309f, 0.0f, 0.0f);
        this.RLeg.func_78792_a(rightLeg);
        rightLeg.field_78804_l.add(new ModelBox(rightLeg, 57, 0, 5.0f, -11.8609f, -7.878f, 4, 10, 4, 0.0f));
        rightLeg.field_78804_l.add(new ModelBox(rightLeg, 70, 129, 4.0f, -2.0f, -10.0f, 2, 2, 7, 0.0f));
        rightLeg.field_78804_l.add(new ModelBox(rightLeg, 34, 132, 6.0f, -1.8695f, -8.0086f, 2, 2, 6, 0.0f));
        rightLeg.field_78804_l.add(new ModelBox(rightLeg, 125, 68, 8.0f, -2.2611f, -9.9829f, 2, 2, 7, 0.0f));
        this.LLeg = new ModelRenderer((ModelBase)this);
        this.LLeg.func_78793_a(6.0f, -20.0f, -1.0f);
        this.setRotationAngle(this.LLeg, -0.1309f, 0.0f, 0.0f);
        this.Body.func_78792_a(this.LLeg);
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 122, 24, -1.0f, -1.5185f, -2.3466f, 4, 10, 4, 0.0f));
        this.LLeg.field_78804_l.add(new ModelBox(this.LLeg, 0, 43, -3.0f, -4.649f, -1.3552f, 5, 4, 6, 0.0f));
        ModelRenderer leftLeg2 = new ModelRenderer((ModelBase)this);
        leftLeg2.func_78793_a(-6.0f, 19.351f, 6.6448f);
        this.setRotationAngle(leftLeg2, 0.1309f, 0.0f, 0.0f);
        this.LLeg.func_78792_a(leftLeg2);
        leftLeg2.field_78804_l.add(new ModelBox(leftLeg2, 57, 0, 5.0f, -11.8609f, -7.878f, 4, 10, 4, 0.0f));
        leftLeg2.field_78804_l.add(new ModelBox(leftLeg2, 130, 115, 4.0f, -2.0f, -10.0f, 2, 2, 7, 0.0f));
        leftLeg2.field_78804_l.add(new ModelBox(leftLeg2, 132, 104, 6.0f, -1.8695f, -8.0086f, 2, 2, 6, 0.0f));
        leftLeg2.field_78804_l.add(new ModelBox(leftLeg2, 130, 95, 8.0f, -2.2611f, -9.9829f, 2, 2, 7, 0.0f));
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

