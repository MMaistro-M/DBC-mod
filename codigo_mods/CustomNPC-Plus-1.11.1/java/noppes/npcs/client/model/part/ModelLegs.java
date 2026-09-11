/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.part.legs.ModelDigitigradeLegs;
import noppes.npcs.client.model.part.legs.ModelMermaidLegs;
import noppes.npcs.client.model.part.legs.ModelMermaidLegs2;
import noppes.npcs.client.model.part.legs.ModelNagaLegs;
import noppes.npcs.client.model.util.ModelScaleRenderer;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.controllers.data.TintData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;
import org.lwjgl.opengl.GL11;

public class ModelLegs
extends ModelScaleRenderer {
    private EntityCustomNpc entity;
    public ModelScaleRenderer leg1;
    public ModelScaleRenderer leg2;
    private ModelRenderer spider;
    private ModelRenderer horse;
    private ModelNagaLegs naga;
    private ModelDigitigradeLegs digitigrade;
    private ModelMermaidLegs mermaid;
    private ModelMermaidLegs2 mermaid2;
    private ModelRenderer spiderLeg1;
    private ModelRenderer spiderLeg2;
    private ModelRenderer spiderLeg3;
    private ModelRenderer spiderLeg4;
    private ModelRenderer spiderLeg5;
    private ModelRenderer spiderLeg6;
    private ModelRenderer spiderLeg7;
    private ModelRenderer spiderLeg8;
    private ModelRenderer spiderBody;
    private ModelRenderer spiderNeck;
    private ModelRenderer backLeftLeg;
    private ModelRenderer backLeftShin;
    private ModelRenderer backLeftHoof;
    private ModelRenderer backRightLeg;
    private ModelRenderer backRightShin;
    private ModelRenderer backRightHoof;
    private ModelRenderer frontLeftLeg;
    private ModelRenderer frontLeftShin;
    private ModelRenderer frontLeftHoof;
    private ModelRenderer frontRightLeg;
    private ModelRenderer frontRightShin;
    private ModelRenderer frontRightHoof;
    private ModelMPM base;

    public ModelLegs(ModelMPM base, ModelScaleRenderer leg1, ModelScaleRenderer leg2, int textWidth, int textHeight) {
        super((ModelBase)base);
        this.base = base;
        this.leg1 = leg1;
        this.leg2 = leg2;
        if (base.isArmor) {
            return;
        }
        this.spider = new ModelRenderer((ModelBase)base);
        this.func_78792_a(this.spider);
        float var1 = 0.0f;
        int var2 = 15;
        this.spiderNeck = new ModelRenderer((ModelBase)base, 0, 0);
        this.spiderNeck.func_78787_b(64, 32);
        this.spiderNeck.func_78790_a(-3.0f, -3.0f, -3.0f, 6, 6, 6, var1);
        this.spiderNeck.func_78793_a(0.0f, (float)var2, 2.0f);
        this.spider.func_78792_a(this.spiderNeck);
        this.spiderBody = new ModelRenderer((ModelBase)base, 0, 12);
        this.spiderBody.func_78787_b(64, 32);
        this.spiderBody.func_78790_a(-5.0f, -4.0f, -6.0f, 10, 8, 12, var1);
        this.spiderBody.func_78793_a(0.0f, (float)var2, 11.0f);
        this.spider.func_78792_a(this.spiderBody);
        this.spiderLeg1 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg1.func_78787_b(64, 32);
        this.spiderLeg1.func_78790_a(-15.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg1.func_78793_a(-4.0f, (float)var2, 4.0f);
        this.spider.func_78792_a(this.spiderLeg1);
        this.spiderLeg2 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg2.func_78787_b(64, 32);
        this.spiderLeg2.func_78790_a(-1.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg2.func_78793_a(4.0f, (float)var2, 4.0f);
        this.spider.func_78792_a(this.spiderLeg2);
        this.spiderLeg3 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg3.func_78787_b(64, 32);
        this.spiderLeg3.func_78790_a(-15.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg3.func_78793_a(-4.0f, (float)var2, 3.0f);
        this.spider.func_78792_a(this.spiderLeg3);
        this.spiderLeg4 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg4.func_78787_b(64, 32);
        this.spiderLeg4.func_78790_a(-1.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg4.func_78793_a(4.0f, (float)var2, 3.0f);
        this.spider.func_78792_a(this.spiderLeg4);
        this.spiderLeg5 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg5.func_78787_b(64, 32);
        this.spiderLeg5.func_78790_a(-15.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg5.func_78793_a(-4.0f, (float)var2, 2.0f);
        this.spider.func_78792_a(this.spiderLeg5);
        this.spiderLeg6 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg6.func_78787_b(64, 32);
        this.spiderLeg6.func_78790_a(-1.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg6.func_78793_a(4.0f, (float)var2, 2.0f);
        this.spider.func_78792_a(this.spiderLeg6);
        this.spiderLeg7 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg7.func_78787_b(64, 32);
        this.spiderLeg7.func_78790_a(-15.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg7.func_78793_a(-4.0f, (float)var2, 1.0f);
        this.spider.func_78792_a(this.spiderLeg7);
        this.spiderLeg8 = new ModelRenderer((ModelBase)base, 18, 0);
        this.spiderLeg8.func_78787_b(64, 32);
        this.spiderLeg8.func_78790_a(-1.0f, -1.0f, -1.0f, 16, 2, 2, var1);
        this.spiderLeg8.func_78793_a(4.0f, (float)var2, 1.0f);
        this.spider.func_78792_a(this.spiderLeg8);
        int zOffset = 10;
        float yOffset = 7.0f;
        this.horse = new ModelRenderer((ModelBase)base);
        this.func_78792_a(this.horse);
        ModelRenderer body = new ModelRenderer((ModelBase)base, 0, 34);
        body.func_78787_b(128, 128);
        body.func_78789_a(-5.0f, -8.0f, -19.0f, 10, 10, 24);
        body.func_78793_a(0.0f, 11.0f + yOffset, 9.0f + (float)zOffset);
        this.horse.func_78792_a(body);
        this.backLeftLeg = new ModelRenderer((ModelBase)base, 78, 29);
        this.backLeftLeg.func_78787_b(128, 128);
        this.backLeftLeg.func_78789_a(-2.5f, -2.0f, -2.5f, 4, 9, 5);
        this.backLeftLeg.func_78793_a(4.0f, 9.0f + yOffset, 11.0f + (float)zOffset);
        this.horse.func_78792_a(this.backLeftLeg);
        this.backLeftShin = new ModelRenderer((ModelBase)base, 78, 43);
        this.backLeftShin.func_78787_b(128, 128);
        this.backLeftShin.func_78789_a(-2.0f, 0.0f, -1.5f, 3, 5, 3);
        this.backLeftShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.backLeftLeg.func_78792_a(this.backLeftShin);
        this.backLeftHoof = new ModelRenderer((ModelBase)base, 78, 51);
        this.backLeftHoof.func_78787_b(128, 128);
        this.backLeftHoof.func_78789_a(-2.5f, 5.1f, -2.0f, 4, 3, 4);
        this.backLeftHoof.func_78793_a(0.0f, 7.0f, 0.0f);
        this.backLeftLeg.func_78792_a(this.backLeftHoof);
        this.backRightLeg = new ModelRenderer((ModelBase)base, 96, 29);
        this.backRightLeg.func_78787_b(128, 128);
        this.backRightLeg.func_78789_a(-1.5f, -2.0f, -2.5f, 4, 9, 5);
        this.backRightLeg.func_78793_a(-4.0f, 9.0f + yOffset, 11.0f + (float)zOffset);
        this.horse.func_78792_a(this.backRightLeg);
        this.backRightShin = new ModelRenderer((ModelBase)base, 96, 43);
        this.backRightShin.func_78787_b(128, 128);
        this.backRightShin.func_78789_a(-1.0f, 0.0f, -1.5f, 3, 5, 3);
        this.backRightShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.backRightLeg.func_78792_a(this.backRightShin);
        this.backRightHoof = new ModelRenderer((ModelBase)base, 96, 51);
        this.backRightHoof.func_78787_b(128, 128);
        this.backRightHoof.func_78789_a(-1.5f, 5.1f, -2.0f, 4, 3, 4);
        this.backRightHoof.func_78793_a(0.0f, 7.0f, 0.0f);
        this.backRightLeg.func_78792_a(this.backRightHoof);
        this.frontLeftLeg = new ModelRenderer((ModelBase)base, 44, 29);
        this.frontLeftLeg.func_78787_b(128, 128);
        this.frontLeftLeg.func_78789_a(-1.9f, -1.0f, -2.1f, 3, 8, 4);
        this.frontLeftLeg.func_78793_a(4.0f, 9.0f + yOffset, -8.0f + (float)zOffset);
        this.horse.func_78792_a(this.frontLeftLeg);
        this.frontLeftShin = new ModelRenderer((ModelBase)base, 44, 41);
        this.frontLeftShin.func_78787_b(128, 128);
        this.frontLeftShin.func_78789_a(-1.9f, 0.0f, -1.6f, 3, 5, 3);
        this.frontLeftShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.frontLeftLeg.func_78792_a(this.frontLeftShin);
        this.frontLeftHoof = new ModelRenderer((ModelBase)base, 44, 51);
        this.frontLeftHoof.func_78787_b(128, 128);
        this.frontLeftHoof.func_78789_a(-2.4f, 5.1f, -2.1f, 4, 3, 4);
        this.frontLeftHoof.func_78793_a(0.0f, 7.0f, 0.0f);
        this.frontLeftLeg.func_78792_a(this.frontLeftHoof);
        this.frontRightLeg = new ModelRenderer((ModelBase)base, 60, 29);
        this.frontRightLeg.func_78787_b(128, 128);
        this.frontRightLeg.func_78789_a(-1.1f, -1.0f, -2.1f, 3, 8, 4);
        this.frontRightLeg.func_78793_a(-4.0f, 9.0f + yOffset, -8.0f + (float)zOffset);
        this.horse.func_78792_a(this.frontRightLeg);
        this.frontRightShin = new ModelRenderer((ModelBase)base, 60, 41);
        this.frontRightShin.func_78787_b(128, 128);
        this.frontRightShin.func_78789_a(-1.1f, 0.0f, -1.6f, 3, 5, 3);
        this.frontRightShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.frontRightLeg.func_78792_a(this.frontRightShin);
        this.frontRightHoof = new ModelRenderer((ModelBase)base, 60, 51);
        this.frontRightHoof.func_78787_b(128, 128);
        this.frontRightHoof.func_78789_a(-1.6f, 5.1f, -2.1f, 4, 3, 4);
        this.frontRightHoof.func_78793_a(0.0f, 7.0f, 0.0f);
        this.frontRightLeg.func_78792_a(this.frontRightHoof);
        this.naga = new ModelNagaLegs((ModelBase)base);
        this.func_78792_a(this.naga);
        this.mermaid = new ModelMermaidLegs((ModelBase)base);
        this.mermaid2 = new ModelMermaidLegs2((ModelBase)base);
        this.func_78792_a(this.mermaid);
        this.func_78792_a(this.mermaid2);
        boolean mirror = false;
        if (textHeight != textWidth) {
            mirror = true;
        }
        this.digitigrade = new ModelDigitigradeLegs(base, mirror, textWidth, textHeight);
        this.func_78792_a(this.digitigrade);
    }

    @Override
    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float limbSwing, float limbSwingAmount, float par3, float par4, float par5, float par6, Entity entity) {
        if (this.entity == null) {
            return;
        }
        ModelPartData part = this.entity.modelData.legParts;
        this.field_78798_e = 0.0f;
        this.field_78797_d = 0.0f;
        if (this.base.isArmor) {
            return;
        }
        if (part.type == 2) {
            this.field_78795_f = 0.0f;
            this.spiderBody.field_78797_d = 15.0f;
            this.spiderBody.field_78798_e = 11.0f;
            this.spiderNeck.field_78795_f = 0.0f;
            float var8 = 0.7853982f;
            this.spiderLeg1.field_78808_h = -var8;
            this.spiderLeg2.field_78808_h = var8;
            this.spiderLeg3.field_78808_h = -var8 * 0.74f;
            this.spiderLeg4.field_78808_h = var8 * 0.74f;
            this.spiderLeg5.field_78808_h = -var8 * 0.74f;
            this.spiderLeg6.field_78808_h = var8 * 0.74f;
            this.spiderLeg7.field_78808_h = -var8;
            this.spiderLeg8.field_78808_h = var8;
            float var9 = -0.0f;
            float var10 = 0.3926991f;
            this.spiderLeg1.field_78796_g = var10 * 2.0f + var9;
            this.spiderLeg2.field_78796_g = -var10 * 2.0f - var9;
            this.spiderLeg3.field_78796_g = var10 * 1.0f + var9;
            this.spiderLeg4.field_78796_g = -var10 * 1.0f - var9;
            this.spiderLeg5.field_78796_g = -var10 * 1.0f + var9;
            this.spiderLeg6.field_78796_g = var10 * 1.0f - var9;
            this.spiderLeg7.field_78796_g = -var10 * 2.0f + var9;
            this.spiderLeg8.field_78796_g = var10 * 2.0f - var9;
            float var11 = -(MathHelper.func_76134_b((float)(limbSwing * 0.6662f * 2.0f + 0.0f)) * 0.4f) * limbSwingAmount;
            float var12 = -(MathHelper.func_76134_b((float)(limbSwing * 0.6662f * 2.0f + (float)Math.PI)) * 0.4f) * limbSwingAmount;
            float var13 = -(MathHelper.func_76134_b((float)(limbSwing * 0.6662f * 2.0f + 1.5707964f)) * 0.4f) * limbSwingAmount;
            float var14 = -(MathHelper.func_76134_b((float)(limbSwing * 0.6662f * 2.0f + 4.712389f)) * 0.4f) * limbSwingAmount;
            float var15 = Math.abs(MathHelper.func_76126_a((float)(limbSwing * 0.6662f + 0.0f)) * 0.4f) * limbSwingAmount;
            float var16 = Math.abs(MathHelper.func_76126_a((float)(limbSwing * 0.6662f + (float)Math.PI)) * 0.4f) * limbSwingAmount;
            float var17 = Math.abs(MathHelper.func_76126_a((float)(limbSwing * 0.6662f + 1.5707964f)) * 0.4f) * limbSwingAmount;
            float var18 = Math.abs(MathHelper.func_76126_a((float)(limbSwing * 0.6662f + 4.712389f)) * 0.4f) * limbSwingAmount;
            this.spiderLeg1.field_78796_g += var11;
            this.spiderLeg2.field_78796_g += -var11;
            this.spiderLeg3.field_78796_g += var12;
            this.spiderLeg4.field_78796_g += -var12;
            this.spiderLeg5.field_78796_g += var13;
            this.spiderLeg6.field_78796_g += -var13;
            this.spiderLeg7.field_78796_g += var14;
            this.spiderLeg8.field_78796_g += -var14;
            this.spiderLeg1.field_78808_h += var15;
            this.spiderLeg2.field_78808_h += -var15;
            this.spiderLeg3.field_78808_h += var16;
            this.spiderLeg4.field_78808_h += -var16;
            this.spiderLeg5.field_78808_h += var17;
            this.spiderLeg6.field_78808_h += -var17;
            this.spiderLeg7.field_78808_h += var18;
            this.spiderLeg8.field_78808_h += -var18;
            if (this.base.field_78117_n) {
                this.field_78798_e = 5.0f;
                this.field_78797_d = -1.0f;
                this.spiderBody.field_78797_d = 16.0f;
                this.spiderBody.field_78798_e = 10.0f;
                this.spiderNeck.field_78795_f = -0.3926991f;
            }
            if (this.base.isSleeping(entity) || this.entity.currentAnimation == EnumAnimation.CRAWLING) {
                this.field_78797_d = 12.0f * this.entity.modelData.modelScale.legs.scaleY;
                this.field_78798_e = 15.0f * this.entity.modelData.modelScale.legs.scaleY;
                this.field_78795_f = -1.5707964f;
            }
        } else if (part.type == 3) {
            this.frontLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f)) * 0.4f * limbSwingAmount;
            this.frontRightLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f + (float)Math.PI)) * 0.4f * limbSwingAmount;
            this.backLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f + (float)Math.PI)) * 0.4f * limbSwingAmount;
            this.backRightLeg.field_78795_f = MathHelper.func_76134_b((float)(limbSwing * 0.6662f)) * 0.4f * limbSwingAmount;
        } else if (part.type == 1) {
            this.naga.isRiding = this.base.field_78093_q;
            this.naga.isSleeping = this.base.isSleeping(entity);
            this.naga.isCrawling = this.entity.currentAnimation == EnumAnimation.CRAWLING;
            this.naga.isSneaking = this.base.field_78117_n;
            this.naga.setRotationAngles(limbSwing, limbSwingAmount, par3, par4, par5, par6, entity);
        } else if (part.type == 4 || part.type == 6) {
            this.mermaid.isRiding = this.base.field_78093_q;
            this.mermaid.isSleeping = this.base.isSleeping(entity);
            this.mermaid.isCrawling = this.entity.currentAnimation == EnumAnimation.CRAWLING;
            this.mermaid.isSneaking = this.base.field_78117_n;
            this.mermaid2.isRiding = this.base.field_78093_q;
            this.mermaid2.isSleeping = this.base.isSleeping(entity);
            this.mermaid2.isCrawling = this.entity.currentAnimation == EnumAnimation.CRAWLING;
            this.mermaid2.isSneaking = this.base.field_78117_n;
            this.mermaid.setRotationAngles(limbSwing, limbSwingAmount, par3, par4, par5, par6, entity);
            this.mermaid2.setRotationAngles(limbSwing, limbSwingAmount, par3, par4, par5, par6, entity);
        } else if (part.type == 5) {
            this.digitigrade.setRotationAngles(limbSwing, limbSwingAmount, par3, par4, par5, par6, entity);
        }
    }

    @Override
    public void func_78785_a(float par1) {
        boolean showColor;
        if (!this.field_78806_j || this.field_78807_k) {
            return;
        }
        ModelPartData part = this.entity.modelData.legParts;
        if (part.type < 0) {
            return;
        }
        GL11.glPushMatrix();
        if (part.type == 4) {
            part.playerTexture = false;
        }
        if (!this.base.isArmor) {
            if (!part.playerTexture) {
                ClientProxy.bindTexture(part.getResource());
                this.base.currentlyPlayerTexture = false;
            } else if (!this.base.currentlyPlayerTexture) {
                ClientProxy.bindTexture(this.entity.textureLocation);
                this.base.currentlyPlayerTexture = true;
            }
        }
        if (part.type == 0) {
            this.leg1.setConfig(this.config, this.x, this.y, this.z);
            this.leg1.func_78785_a(par1);
            this.leg2.setConfig(this.config, -this.x, this.y, this.z);
            this.leg2.func_78785_a(par1);
        }
        if (!this.base.isArmor) {
            this.naga.field_78807_k = part.type != 1;
            this.spider.field_78807_k = part.type != 2;
            this.horse.field_78807_k = part.type != 3;
            this.mermaid.field_78807_k = part.type != 4;
            this.mermaid2.field_78807_k = part.type != 6;
            boolean bl = this.digitigrade.field_78807_k = part.type != 5;
            if (!this.horse.field_78807_k) {
                this.x = 0.0f;
                this.y *= 1.8f;
                GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
            } else if (!this.spider.field_78807_k) {
                this.x = 0.0f;
                this.y *= 2.0f;
            } else if (!this.naga.field_78807_k) {
                this.x = 0.0f;
                this.y *= 2.0f;
            } else if (!(this.mermaid.field_78807_k && this.mermaid2.field_78807_k && this.digitigrade.field_78807_k)) {
                this.x = 0.0f;
                this.y *= 2.0f;
            }
        }
        TintData tintData = this.entity.display.tintData;
        boolean bl = !this.base.isArmor && tintData.processColor(this.entity.field_70737_aN > 0 || this.entity.field_70725_aQ > 0) ? true : (showColor = false);
        if (showColor) {
            float red = (float)(this.entity.modelData.legParts.color >> 16 & 0xFF) / 255.0f;
            float green = (float)(this.entity.modelData.legParts.color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(this.entity.modelData.legParts.color & 0xFF) / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.base.alpha);
        }
        super.func_78785_a(par1);
        if (showColor) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.base.alpha);
        }
        GL11.glPopMatrix();
    }

    public void setData(EntityCustomNpc entity) {
        this.entity = entity;
    }
}

