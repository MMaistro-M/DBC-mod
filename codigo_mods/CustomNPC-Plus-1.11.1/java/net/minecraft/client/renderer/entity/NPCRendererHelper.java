/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class NPCRendererHelper {
    public static ModelBase getMainModel(RendererLivingEntity render) {
        return render.field_77045_g;
    }

    public static String getTexture(RendererLivingEntity render, Entity entity) {
        ResourceLocation location = render.func_110775_a(entity);
        return location.toString();
    }

    public static int shouldRenderPass(EntityLivingBase entity, int par2, float par3, RendererLivingEntity renderEntity) {
        return renderEntity.func_77032_a(entity, par2, par3);
    }

    public static void preRenderCallback(EntityLivingBase entity, float f, RendererLivingEntity renderEntity) {
        renderEntity.func_77041_b(entity, f);
    }

    public static ModelBase getPassModel(RendererLivingEntity render) {
        return render.field_77046_h;
    }

    public static float handleRotationFloat(EntityLivingBase entity, float par2, RendererLivingEntity renderEntity) {
        return renderEntity.func_77044_a(entity, par2);
    }

    public static void renderEquippedItems(EntityLivingBase entity, float f, RendererLivingEntity renderEntity) {
        renderEntity.func_77029_c(entity, f);
    }
}

