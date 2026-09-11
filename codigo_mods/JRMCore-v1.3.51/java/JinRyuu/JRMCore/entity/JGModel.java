/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.client.minigame.MiniGameAirBoxing;
import JinRyuu.JRMCore.entity.EntityNull;
import JinRyuu.JRMCore.manager.AttributeArray;
import JinRyuu.JRMCore.manager.AttributeModel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class JGModel
extends ModelBase {
    private final float SPEED_MULTIPLIER = 1.0f;
    private ArrayList<AttributeModel> AttributeModels = new ArrayList();
    public int animationID = 0;
    private Instant lastUpdate;
    private float age = 0.0f;
    private boolean animationEnded = false;
    private float jumpAge;
    private Instant jumpUpdate;
    public AttributeArray jumpPos;
    public boolean jumping;

    public JGModel(int textureWidth, int textureHeight) {
        this.field_78090_t = textureWidth;
        this.field_78089_u = textureHeight;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.updateAnimation((EntityNull)entity, f, f1, f2, f3, f4, f5);
    }

    private void updateAnimation(EntityNull entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (this.lastUpdate == null) {
            this.lastUpdate = Instant.now();
        } else {
            if (this.getMiniGameAirBoxing() != null) {
                if (!this.animationEnded) {
                    this.getMiniGameAirBoxing().gameModelAge += (float)Duration.between(this.lastUpdate, Instant.now()).toMillis() / 1000.0f * 1.0f;
                }
                this.age = this.getMiniGameAirBoxing().gameModelAge;
                this.animationID = this.getMiniGameAirBoxing().gameModelAnimationID;
                if (this.getMiniGameAirBoxing().gameModelAnimationChanged) {
                    this.jumpAge = 0.0f;
                    this.jumpPos = null;
                    this.jumping = false;
                    this.playAnimation(this.animationID);
                    this.getMiniGameAirBoxing().gameModelAnimationChanged = false;
                    this.animationEnded = false;
                }
            }
            this.lastUpdate = Instant.now();
        }
        this.updateAttributes(this.age);
    }

    public void addJump(AttributeArray array) {
        this.jumpAge = 0.0f;
        this.jumpPos = array;
        this.jumping = true;
        this.jumpUpdate = Instant.now();
    }

    public void updateJump() {
        if (this.jumping) {
            if (this.jumpUpdate != null) {
                this.jumpAge += (float)Duration.between(this.jumpUpdate, Instant.now()).toMillis() / 1000.0f * 1.0f;
            }
            this.jumpUpdate = Instant.now();
            this.jumpPos.update(this.jumpAge);
            if (this.jumpPos.hasEnded) {
                this.jumping = false;
            }
        }
    }

    public boolean isAnimationID(int animationID) {
        return this.animationID == animationID;
    }

    private void playAnimation(int animationID) {
        this.age = 0.0f;
        this.animationID = animationID;
        this.AttributeModels.clear();
        this.resetAnimation();
        this.setupAnimation();
    }

    public void resetAnimation() {
        for (AttributeModel attributeModel : this.AttributeModels) {
            if (attributeModel.value == AttributeModel.X) {
                attributeModel.model.field_78795_f = 0.0f;
                continue;
            }
            if (attributeModel.value == AttributeModel.Y) {
                attributeModel.model.field_78796_g = 0.0f;
                continue;
            }
            if (attributeModel.value != AttributeModel.Z) continue;
            attributeModel.model.field_78808_h = 0.0f;
        }
    }

    public void setupAnimation() {
    }

    public MiniGameAirBoxing getMiniGameAirBoxing() {
        if (JRMCoreGuiScreen.instance != null) {
            return JRMCoreGuiScreen.instance.miniGameAirBoxing;
        }
        return null;
    }

    public void updateAttributes(float age) {
        this.animationEnded = true;
        for (AttributeModel array : this.AttributeModels) {
            array.update(age);
            if (array.value == AttributeModel.X) {
                array.model.field_78795_f = array.attributeArray.lastValue;
            } else if (array.value == AttributeModel.Y) {
                array.model.field_78796_g = array.attributeArray.lastValue;
            } else if (array.value == AttributeModel.Z) {
                array.model.field_78808_h = array.attributeArray.lastValue;
            }
            if (array.hasEnded) continue;
            this.animationEnded = false;
        }
    }

    public void addAttribute(ModelRenderer model, AttributeArray x, AttributeArray y, AttributeArray z) {
        if (x != null) {
            this.addAttribute(new AttributeModel(model, AttributeModel.X, x));
        }
        if (y != null) {
            this.addAttribute(new AttributeModel(model, AttributeModel.Y, y));
        }
        if (z != null) {
            this.addAttribute(new AttributeModel(model, AttributeModel.Z, z));
        }
    }

    public void addAttribute(AttributeModel attributes) {
        this.AttributeModels.add(attributes);
    }

    public ArrayList<AttributeModel> getAttributes() {
        return this.AttributeModels;
    }

    public AttributeModel getAttribute(int id) {
        return this.AttributeModels.get(id);
    }

    public void setRotateAngle(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

