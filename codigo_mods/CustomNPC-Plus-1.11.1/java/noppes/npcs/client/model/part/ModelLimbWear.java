/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelScaleRenderer;

public class ModelLimbWear
extends ModelScaleRenderer {
    public ModelLimbWear(ModelBase base, String limb, String side, String type) {
        super(base);
        float thick = 0.55f;
        block4 : switch (limb) {
            case "arm": {
                block12 : switch (type) {
                    case "Steve": {
                        switch (side) {
                            case "right": {
                                this.rightArm(base, thick);
                                break;
                            }
                            case "left": {
                                this.leftArm(base, thick);
                            }
                        }
                        break;
                    }
                    case "Alex": {
                        switch (side) {
                            case "right": {
                                this.rightArmAlex(base, thick);
                                break block12;
                            }
                            case "left": {
                                this.leftArmAlex(base, thick);
                            }
                        }
                    }
                }
                break;
            }
            case "leg": {
                switch (side) {
                    case "right": {
                        this.rightLeg(base, thick);
                        break block4;
                    }
                    case "left": {
                        this.leftLeg(base, thick);
                    }
                }
            }
        }
    }

    public void rightArmAlex(ModelBase base, float thick) {
        float x = 0.95f;
        float y = -2.0f;
        Model2DRenderer front = new Model2DRenderer(base, 44.0f, 36.0f, 3, 12, 64.0f, 64.0f);
        front.func_78793_a(-3.39375f + x, 12.535f + y, -2.58f);
        front.setScale(0.96f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 51.0f, 36.0f, 3, 12, 64.0f, 64.0f);
        back.func_78793_a(0.44f + x, 12.535f + y, 2.58f);
        back.setScale(0.96f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 47.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        right.func_78793_a(0.45f + x, 12.55f + y, -2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 40.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        left.func_78793_a(-3.405f + x, 12.55f + y, 2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 44.0f, 32.0f, 3, 4, 64.0f, 64.0f);
        top.func_78793_a(-3.39375f + x, -0.6f + y, -2.55f);
        top.setScale(0.32f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 47.0f, 32.0f, 3, 4, 64.0f, 64.0f);
        bottom.func_78793_a(-3.39375f + x, 12.0f + y, -2.545f);
        bottom.setScale(0.32f);
        bottom.setThickness(thick);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    public void leftArmAlex(ModelBase base, float thick) {
        float x = 1.95f;
        float y = -2.0f;
        Model2DRenderer front = new Model2DRenderer(base, 52.0f, 52.0f, 3, 12, 64.0f, 64.0f);
        front.func_78793_a(-3.39375f + x, 12.535f + y, -2.58f);
        front.setScale(0.96f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 59.0f, 52.0f, 3, 12, 64.0f, 64.0f);
        back.func_78793_a(0.44f + x, 12.535f + y, 2.58f);
        back.setScale(0.96f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 55.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        right.func_78793_a(0.45f + x, 12.55f + y, -2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 48.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        left.func_78793_a(-3.405f + x, 12.55f + y, 2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 52.0f, 48.0f, 3, 4, 64.0f, 64.0f);
        top.func_78793_a(-3.39375f + x, -0.6f + y, -2.55f);
        top.setScale(0.32f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 55.0f, 48.0f, 3, 4, 64.0f, 64.0f);
        bottom.func_78793_a(-3.39375f + x, 12.0f + y, -2.545f);
        bottom.setScale(0.32f);
        bottom.setThickness(thick);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    public void rightArm(ModelBase base, float thick) {
        float x = 0.95f;
        float y = -2.0f;
        Model2DRenderer front = new Model2DRenderer(base, 44.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        front.func_78793_a(-4.52f + x, 12.535f + y, -2.58f);
        front.setScale(0.96f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 52.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        back.func_78793_a(0.6f + x, 12.535f + y, 2.58f);
        back.setScale(0.96f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 48.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        right.func_78793_a(0.62f + x, 12.55f + y, -2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 40.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        left.func_78793_a(-4.56f + x, 12.55f + y, 2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 44.0f, 32.0f, 4, 4, 64.0f, 64.0f);
        top.func_78793_a(-4.525f + x, -0.6f + y, -2.55f);
        top.setScale(0.32f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 48.0f, 32.0f, 4, 4, 64.0f, 64.0f);
        bottom.func_78793_a(-4.5f + x, 12.0f + y, -2.525f);
        bottom.setScale(0.32f);
        bottom.setThickness(thick);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    public void leftArm(ModelBase base, float thick) {
        float x = 2.95f;
        float y = -2.0f;
        Model2DRenderer front = new Model2DRenderer(base, 52.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        front.func_78793_a(-4.52f + x, 12.535f + y, -2.58f);
        front.setScale(0.96f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 60.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        back.func_78793_a(0.6f + x, 12.535f + y, 2.58f);
        back.setScale(0.96f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 56.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        right.func_78793_a(0.62f + x, 12.55f + y, -2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 48.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        left.func_78793_a(-4.56f + x, 12.55f + y, 2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 52.0f, 48.0f, 4, 4, 64.0f, 64.0f);
        top.func_78793_a(-4.525f + x, -0.6f + y, -2.55f);
        top.setScale(0.32f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 56.0f, 48.0f, 4, 4, 64.0f, 64.0f);
        bottom.func_78793_a(-4.56f + x, 12.0f + y, -2.55f);
        bottom.setScale(0.32f);
        bottom.setThickness(thick);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    public void rightLeg(ModelBase base, float thick) {
        float x = 1.8f;
        float y = 0.0f;
        Model2DRenderer front = new Model2DRenderer(base, 4.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        front.func_78793_a(-4.52f + x, 12.535f + y, -2.58f);
        front.setScale(0.96f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 12.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        back.func_78793_a(0.6f + x, 12.535f + y, 2.58f);
        back.setScale(0.96f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 8.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        right.func_78793_a(0.62f + x, 12.55f + y, -2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 0.0f, 36.0f, 4, 12, 64.0f, 64.0f);
        left.func_78793_a(-4.53f + x, 12.55f + y, 2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 4.0f, 32.0f, 4, 4, 64.0f, 64.0f);
        top.func_78793_a(-4.525f + x, -0.6f + y, -2.55f);
        top.setScale(0.32f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 8.0f, 32.0f, 4, 4, 64.0f, 64.0f);
        bottom.func_78793_a(-4.5f + x, 12.11f + y, -2.525f);
        bottom.setScale(0.32f);
        bottom.setThickness(thick);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    public void leftLeg(ModelBase base, float thick) {
        float x = 2.05f;
        float y = 0.0f;
        Model2DRenderer front = new Model2DRenderer(base, 4.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        front.func_78793_a(-4.52f + x, 12.535f + y, -2.58f);
        front.setScale(0.96f, 0.8175f);
        front.setThickness(thick);
        this.setRotation(front, 0.0f, 0.0f, 0.0f);
        this.func_78792_a(front);
        Model2DRenderer back = new Model2DRenderer(base, 12.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        back.func_78793_a(0.6f + x, 12.535f + y, 2.58f);
        back.setScale(0.96f, 0.8175f);
        back.setThickness(thick);
        this.setRotation(back, 0.0f, (float)Math.PI, 0.0f);
        this.func_78792_a(back);
        Model2DRenderer right = new Model2DRenderer(base, 8.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        right.func_78793_a(0.62f + x, 12.55f + y, -2.55f);
        right.setScale(0.96f, 0.82f);
        right.setThickness(thick);
        this.setRotation(right, 0.0f, -1.5707964f, 0.0f);
        this.func_78792_a(right);
        Model2DRenderer left = new Model2DRenderer(base, 0.0f, 52.0f, 4, 12, 64.0f, 64.0f);
        left.func_78793_a(-4.53f + x, 12.55f + y, 2.55f);
        left.setScale(0.96f, 0.82f);
        left.setThickness(thick);
        this.setRotation(left, 0.0f, 1.5707964f, 0.0f);
        this.func_78792_a(left);
        Model2DRenderer top = new Model2DRenderer(base, 4.0f, 48.0f, 4, 4, 64.0f, 64.0f);
        top.func_78793_a(-4.525f + x, -0.6f + y, -2.55f);
        top.setScale(0.32f);
        top.setThickness(thick);
        this.setRotation(top, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(top);
        Model2DRenderer bottom = new Model2DRenderer(base, 8.0f, 48.0f, 4, 4, 64.0f, 64.0f);
        bottom.func_78793_a(-4.5f + x, 12.11f + y, -2.525f);
        bottom.setScale(0.32f);
        bottom.setThickness(thick);
        this.setRotation(bottom, -1.5707964f, 0.0f, 0.0f);
        this.func_78792_a(bottom);
    }

    @Override
    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

