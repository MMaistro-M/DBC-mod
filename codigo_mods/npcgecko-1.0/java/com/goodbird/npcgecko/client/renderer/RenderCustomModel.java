/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  org.lwjgl.opengl.GL11
 */
package com.goodbird.npcgecko.client.renderer;

import com.goodbird.npcgecko.client.model.ModelCustom;
import com.goodbird.npcgecko.entity.EntityCustomModel;
import java.util.ArrayList;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderCustomModel
extends GeoEntityRenderer<EntityCustomModel> {
    public RenderCustomModel() {
        super(new ModelCustom());
    }

    @Override
    public void renderAfter(GeoModel model, EntityCustomModel animatable, float ticks, float red, float green, float blue, float alpha) {
        GeoBone bone;
        super.renderAfter(model, (Object)animatable, ticks, red, green, blue, alpha);
        if (model.getBone("held_item").isPresent() && animatable.func_70694_bm() != null) {
            bone = model.getBone("held_item").get();
            this.renderItem(animatable, animatable.func_70694_bm(), bone);
        }
        if (model.getBone("left_held_item").isPresent() && animatable.leftHeldItem != null) {
            bone = model.getBone("left_held_item").get();
            this.renderLeftHandItem(animatable, animatable.leftHeldItem, bone);
        }
    }

    @Override
    public GeoBone[] getPathFromRoot(GeoBone bone) {
        ArrayList<GeoBone> bones = new ArrayList<GeoBone>();
        while (bone != null) {
            bones.add(0, bone);
            bone = bone.parent;
        }
        return bones.toArray(new GeoBone[0]);
    }

    public void renderItem(EntityCustomModel animatable, ItemStack stack, GeoBone locator) {
        GeoBone[] bonePath;
        GL11.glPushMatrix();
        float scale = 0.5f;
        GL11.glScaled((double)scale, (double)scale, (double)scale);
        for (GeoBone b : bonePath = this.getPathFromRoot(locator)) {
            GL11.glTranslatef((float)(b.getPositionX() / (16.0f * scale)), (float)(b.getPositionY() / (16.0f * scale)), (float)(b.getPositionZ() / (16.0f * scale)));
            GL11.glTranslatef((float)(b.getPivotX() / (16.0f * scale)), (float)(b.getPivotY() / (16.0f * scale)), (float)(b.getPivotZ() / (16.0f * scale)));
            GL11.glRotated((double)((double)b.getRotationZ() / Math.PI * 180.0), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)((double)b.getRotationY() / Math.PI * 180.0), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)((double)b.getRotationX() / Math.PI * 180.0), (double)1.0, (double)0.0, (double)0.0);
            GL11.glScalef((float)b.getScaleX(), (float)b.getScaleY(), (float)b.getScaleZ());
            GL11.glTranslatef((float)(-b.getPivotX() / (16.0f * scale)), (float)(-b.getPivotY() / (16.0f * scale)), (float)(-b.getPivotZ() / (16.0f * scale)));
        }
        GL11.glRotatef((float)250.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)40.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.4f, (float)-0.55f, (float)1.7f);
        RenderManager.field_78727_a.field_78721_f.renderItem((EntityLivingBase)animatable, stack, 0, IItemRenderer.ItemRenderType.INVENTORY);
        GL11.glPopMatrix();
    }

    public void renderLeftHandItem(EntityCustomModel animatable, ItemStack stack, GeoBone locator) {
        GeoBone[] bonePath;
        GL11.glPushMatrix();
        float scale = 0.5f;
        GL11.glScaled((double)scale, (double)scale, (double)scale);
        for (GeoBone b : bonePath = this.getPathFromRoot(locator)) {
            GL11.glTranslatef((float)(b.getPositionX() / (16.0f * scale)), (float)(b.getPositionY() / (16.0f * scale)), (float)(b.getPositionZ() / (16.0f * scale)));
            GL11.glTranslatef((float)(b.getPivotX() / (16.0f * scale)), (float)(b.getPivotY() / (16.0f * scale)), (float)(b.getPivotZ() / (16.0f * scale)));
            GL11.glRotated((double)((double)b.getRotationZ() / Math.PI * 180.0), (double)0.0, (double)0.0, (double)1.0);
            GL11.glRotated((double)((double)b.getRotationY() / Math.PI * 180.0), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)((double)b.getRotationX() / Math.PI * 180.0), (double)1.0, (double)0.0, (double)0.0);
            GL11.glScalef((float)b.getScaleX(), (float)b.getScaleY(), (float)b.getScaleZ());
            GL11.glTranslatef((float)(-b.getPivotX() / (16.0f * scale)), (float)(-b.getPivotY() / (16.0f * scale)), (float)(-b.getPivotZ() / (16.0f * scale)));
        }
        GL11.glRotatef((float)250.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)40.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-1.51f, (float)-0.55f, (float)0.7f);
        RenderManager.field_78727_a.field_78721_f.renderItem((EntityLivingBase)animatable, stack, 0, IItemRenderer.ItemRenderType.INVENTORY);
        GL11.glPopMatrix();
    }

    @Override
    public Color getRenderColor(EntityCustomModel animatable, float partialTicks) {
        if (animatable.field_70737_aN > 0 || animatable.field_70725_aQ > 0) {
            if (animatable.tintData != null && animatable.tintData.isTintEnabled() && animatable.tintData.isHurtTintEnabled()) {
                int hurtTint = animatable.tintData.getHurtTint();
                int r = (int)((float)(hurtTint >> 16 & 0xFF));
                int g = (int)((float)(hurtTint >> 8 & 0xFF));
                int b = (int)((float)(hurtTint & 0xFF));
                return Color.ofRGBA(r, g, b, 255);
            }
            return Color.ofRGBA(255, 153, 153, 255);
        }
        int r = 255;
        int g = 255;
        int b = 255;
        if (animatable.tintData != null && animatable.tintData.isTintEnabled() && animatable.tintData.isGeneralTintEnabled()) {
            int hurtTint = animatable.tintData.getGeneralTint();
            int tintR = (int)((float)(hurtTint >> 16 & 0xFF));
            int tintG = (int)((float)(hurtTint >> 8 & 0xFF));
            int tintB = (int)((float)(hurtTint & 0xFF));
            double alpha = (double)animatable.tintData.getGeneralAlpha() / 100.0;
            r = (int)(alpha * (double)tintR + (double)r * (1.0 - alpha));
            g = (int)(alpha * (double)tintG + (double)g * (1.0 - alpha));
            b = (int)(alpha * (double)tintB + (double)b * (1.0 - alpha));
        }
        if (animatable.isSemiVisible) {
            return Color.ofRGBA(r, g, b, 100);
        }
        return Color.ofRGBA(r, g, b, 255);
    }

    @Override
    public boolean isBoneRenderOverriden(EntityCustomModel entity, GeoBone bone) {
        return bone.name.equals("held_item") || bone.name.equals("left_held_item");
    }
}

