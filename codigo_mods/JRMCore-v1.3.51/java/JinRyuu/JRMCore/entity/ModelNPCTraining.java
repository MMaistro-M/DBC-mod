/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.entity.JGModel;
import JinRyuu.JRMCore.manager.AttributeArray;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelNPCTraining
extends JGModel {
    private final int ANIMATION_STAND = 0;
    private final int ANIMATION_LEFT_PUNCH = 1;
    private final int ANIMATION_RIGHT_PUNCH = 2;
    private final int ANIMATION_LEFT_KICK = 3;
    private final int ANIMATION_RIGHT_KICK = 4;
    private final int ANIMATION_HURT = 5;
    private final int ANIMATION_VICTORY = 6;
    private final int ANIMATION_DEFEAT = 7;
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer rightarm2 = new ModelRenderer((ModelBase)this, 40, 26);
    public ModelRenderer leftarm2;
    public ModelRenderer rightleg2;
    public ModelRenderer leftleg2;

    public ModelNPCTraining() {
        super(64, 64);
        this.rightarm2.func_78793_a(0.0f, 4.0f, 0.0f);
        this.rightarm2.func_78790_a(-3.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 32, 36);
        this.leftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leftleg2 = new ModelRenderer((ModelBase)this, 16, 46);
        this.leftleg2.func_78793_a(0.0f, 6.0f, 0.0f);
        this.leftleg2.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 6, 4, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.rightleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.rightleg2 = new ModelRenderer((ModelBase)this, 0, 26);
        this.rightleg2.func_78793_a(0.0f, 6.0f, 0.0f);
        this.rightleg2.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 16, 36);
        this.leftleg.func_78793_a(1.9f, 12.0f, 0.0f);
        this.leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leftarm2 = new ModelRenderer((ModelBase)this, 32, 46);
        this.leftarm2.func_78793_a(0.0f, 4.0f, 0.0f);
        this.leftarm2.func_78790_a(-1.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.leftarm.func_78792_a(this.leftarm2);
        this.rightarm.func_78792_a(this.rightarm2);
        this.rightleg.func_78792_a(this.rightleg2);
        this.leftleg.func_78792_a(this.leftleg2);
        this.body.func_78792_a(this.head);
        this.body.func_78792_a(this.leftarm);
        this.body.func_78792_a(this.rightarm);
        this.body.func_78792_a(this.leftleg);
        this.body.func_78792_a(this.rightleg);
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.updateJump();
        GL11.glPushMatrix();
        float legL = this.leftleg.field_78795_f;
        if (legL > 0.75f) {
            legL = 0.75f;
        }
        float legL2 = this.leftleg2.field_78795_f;
        float legR = this.rightleg.field_78795_f;
        if (legR > 0.75f) {
            legR = 0.75f;
        }
        float legR2 = this.rightleg.field_78795_f;
        float leg = legL < legR ? legL : legR;
        float leg2 = legL < legR ? legL2 : legR2;
        float F1 = leg * -0.25f + leg2 * (leg2 > 1.0f ? 0.01f : -0.08f);
        GL11.glTranslatef((float)0.0f, (float)(F1 - (this.jumping && this.jumpPos != null ? this.jumpPos.lastValue : 0.0f)), (float)0.0f);
        this.body.func_78785_a(f5);
        GL11.glPopMatrix();
    }

    @Override
    public void resetAnimation() {
        this.setRotateAngle(this.head, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.body, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.leftarm2, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.rightarm2, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.leftleg2, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.setRotateAngle(this.rightleg2, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void setupAnimation() {
        switch (this.animationID) {
            case 0: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.8f;
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, 0.0f, FRAME1, -0.8f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.1f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, 0.0f, FRAME1, -1.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, 0.0f, FRAME1, -0.8f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.1f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, 0.0f, FRAME1, -1.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, 0.0f, FRAME1, -1.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.5f), null);
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 0.0f, FRAME1, 1.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, 0.0f, FRAME1, -1.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.8f), null);
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 0.0f, FRAME1, 1.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.001f), null);
                return;
            }
            case 1: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.05f;
                float FRAME2 = 0.3f;
                this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0f, FRAME1, 0.2f, FRAME2, 0.0f), null);
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -1.6f, FRAME2, -0.8f), new AttributeArray(FRAME0, -0.1f, FRAME1, -0.2f, FRAME2, -0.1f), new AttributeArray(FRAME0, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, 0.0f, FRAME2, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.3f, FRAME2, -0.8f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.2f, FRAME2, 0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.2f, FRAME2, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, -0.5f, FRAME1, -0.7f, FRAME2, -0.5f), null);
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.8f, FRAME1, 0.6f, FRAME2, 0.8f), null);
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                return;
            }
            case 2: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.05f;
                float FRAME2 = 0.3f;
                this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0f, FRAME1, -0.2f, FRAME2, 0.0f), null);
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.3f, FRAME2, -0.8f), new AttributeArray(FRAME0, -0.1f, FRAME1, -0.2f, FRAME2, -0.1f), new AttributeArray(FRAME0, -0.1f, FRAME1, -0.1f, FRAME2, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -1.6f, FRAME2, -0.8f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.2f, FRAME2, 0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.2f, FRAME2, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, 0.0f, FRAME2, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, -0.5f, FRAME1, -0.3f, FRAME2, -0.5f), null);
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.8f, FRAME1, 1.0f, FRAME2, 0.8f), null);
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                return;
            }
            case 3: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.05f;
                float FRAME2 = 0.25f;
                float FRAME3 = 0.3f;
                this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0f, FRAME1, 0.05f, FRAME2, 0.02f, FRAME3, 0.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.4f, FRAME2, -0.3f, FRAME3, 0.0f));
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.2f, FRAME2, -0.3f, FRAME3, -0.8f), new AttributeArray(FRAME0, -0.1f), new AttributeArray(FRAME0, -0.1f, FRAME1, -1.3f, FRAME2, -1.0f, FRAME3, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.2f, FRAME2, -0.3f, FRAME3, -0.8f), new AttributeArray(FRAME0, 0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.5f, FRAME2, 0.4f, FRAME3, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -1.5f, FRAME2, -1.4f, FRAME3, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f, FRAME1, -1.3f, FRAME2, -1.2f, FRAME3, -1.0f), new AttributeArray(FRAME0, -0.5f, FRAME1, -0.3f, FRAME2, -0.4f, FRAME3, -0.5f), new AttributeArray(FRAME0, 0.0f, FRAME1, -1.3f, FRAME2, -1.0f, FRAME3, 0.0f));
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.0f, FRAME2, 0.1f, FRAME3, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f, FRAME1, 0.2f, FRAME2, 0.3f, FRAME3, -1.0f), new AttributeArray(FRAME0, 0.8f, FRAME1, 0.6f, FRAME2, 0.7f, FRAME3, 0.8f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.3f, FRAME2, 0.2f, FRAME3, 0.0f));
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.0f, FRAME2, 0.1f, FRAME3, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                return;
            }
            case 4: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.05f;
                float FRAME2 = 0.25f;
                float FRAME3 = 0.3f;
                this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0f, FRAME1, -0.5f, FRAME2, -0.48f, FRAME3, 0.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, 0.4f, FRAME2, 0.3f, FRAME3, 0.0f));
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.2f, FRAME2, -0.3f, FRAME3, -0.8f), new AttributeArray(FRAME0, -0.1f), new AttributeArray(FRAME0, -0.1f, FRAME1, -0.5f, FRAME2, -0.4f, FRAME3, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -1.5f, FRAME2, -1.4f, FRAME3, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.2f, FRAME2, -0.3f, FRAME3, -0.8f), new AttributeArray(FRAME0, 0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 1.3f, FRAME2, 1.0f, FRAME3, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.2f, FRAME2, -0.3f, FRAME3, -1.0f), new AttributeArray(FRAME0, -0.5f, FRAME1, 0.6f, FRAME2, 0.7f, FRAME3, -0.5f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.8f, FRAME2, -0.7f, FRAME3, 0.0f));
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.0f, FRAME2, 0.1f, FRAME3, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f, FRAME1, -1.3f, FRAME2, -1.2f, FRAME3, -1.0f), new AttributeArray(FRAME0, 0.8f, FRAME1, 0.3f, FRAME2, 0.4f, FRAME3, 0.8f), new AttributeArray(FRAME0, 0.0f, FRAME1, 1.3f, FRAME2, 1.0f, FRAME3, 0.0f));
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.0f, FRAME2, 0.1f, FRAME3, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                return;
            }
            case 5: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.05f;
                float FRAME2 = 0.3f;
                this.addAttribute(this.head, new AttributeArray(FRAME0, 0.0f, FRAME1, -0.1f, FRAME2, 0.0f), null, null);
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.6f, FRAME2, -0.8f), new AttributeArray(FRAME0, -0.1f), new AttributeArray(FRAME0, -0.1f, FRAME1, -0.3f, FRAME2, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.5f, FRAME2, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.6f, FRAME2, -0.8f), new AttributeArray(FRAME0, 0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.3f, FRAME2, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.5f, FRAME2, -1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, -0.5f), null);
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f), new AttributeArray(FRAME0, 0.8f), null);
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f), new AttributeArray(FRAME0, 0.001f), null);
                return;
            }
            case 6: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.3f;
                float FRAME2 = 0.5f;
                float FRAME3 = 0.7f;
                this.addAttribute(this.body, null, new AttributeArray(FRAME0, 0.0f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.06f, FRAME2, -0.06f, FRAME3, 0.0f));
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -2.7f, FRAME2, -2.7f, FRAME3, -0.1f), new AttributeArray(FRAME0, -0.1f), new AttributeArray(FRAME0, -0.1f, FRAME1, 0.2f, FRAME2, 0.2f, FRAME3, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.1f, FRAME2, -0.1f, FRAME3, -0.9f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.6f, FRAME2, -0.6f, FRAME3, -0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.2f, FRAME2, 0.2f, FRAME3, 0.1f), new AttributeArray(FRAME0, 0.1f, FRAME1, 0.2f, FRAME2, 0.2f, FRAME3, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f, FRAME2, -1.0f, FRAME3, -0.9f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f, FRAME1, 0.0f, FRAME2, 0.0f, FRAME3, 0.1f), new AttributeArray(FRAME0, 0.5f, FRAME1, 0.0f, FRAME2, 0.0f, FRAME3, 0.1f), new AttributeArray(FRAME0, 0.001f, FRAME1, 0.06f, FRAME2, 0.06f, FRAME3, -0.1f));
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.0f, FRAME2, 0.0f, FRAME3, 0.0f), new AttributeArray(FRAME0, 0.001f, FRAME1, 0.0f, FRAME2, 0.0f, FRAME3, 0.001f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f, FRAME1, -1.05f, FRAME2, -1.05f, FRAME3, 0.1f), new AttributeArray(FRAME0, 0.8f, FRAME1, 0.4f, FRAME2, 0.4f, FRAME3, 0.1f), new AttributeArray(FRAME0, 0.0f, FRAME2, 0.0f, FRAME3, 0.1f));
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 1.05f, FRAME2, 1.05f, FRAME3, 0.0f), new AttributeArray(FRAME0, 0.001f), null);
                this.addJump(new AttributeArray(FRAME0, 0.0f, FRAME1, 1.0f, FRAME2, 0.9f, FRAME3, 0.0f));
                return;
            }
            case 7: {
                float FRAME0 = 0.0f;
                float FRAME1 = 0.3f;
                float FRAME2 = 0.5f;
                float FRAME3 = 0.7f;
                this.addAttribute(this.head, new AttributeArray(FRAME0, 0.0f, FRAME1, 0.06f), new AttributeArray(FRAME0, 0.0f, FRAME1, -0.5f, FRAME1 * 2.0f, 0.0f, FRAME1 * 3.0f, 0.5f, FRAME1 * 4.0f, 0.0f), null);
                this.addAttribute(this.body, new AttributeArray(FRAME0, 0.0f, FRAME1, 0.06f), null, null);
                this.addAttribute(this.leftarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.15f), new AttributeArray(FRAME0, -0.1f), new AttributeArray(FRAME0, -0.1f));
                this.addAttribute(this.leftarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.05f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.rightarm, new AttributeArray(FRAME0, -0.8f, FRAME1, -0.15f), new AttributeArray(FRAME0, 0.1f), new AttributeArray(FRAME0, 0.1f));
                this.addAttribute(this.rightarm2, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.05f), new AttributeArray(FRAME0, 0.001f), null);
                this.addAttribute(this.leftleg, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.3f), new AttributeArray(FRAME0, 0.5f, FRAME1, 0.0f), new AttributeArray(FRAME0, 0.001f, FRAME1, -0.06f));
                this.addAttribute(this.leftleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.3f), new AttributeArray(FRAME0, 0.001f, FRAME1, 0.0f), null);
                this.addAttribute(this.rightleg, new AttributeArray(FRAME0, -1.0f, FRAME1, -0.3f), new AttributeArray(FRAME0, 0.8f, FRAME1, 0.4f), null);
                this.addAttribute(this.rightleg2, new AttributeArray(FRAME0, 1.0f, FRAME1, 0.25f), new AttributeArray(FRAME0, 0.001f), null);
                return;
            }
        }
    }
}

