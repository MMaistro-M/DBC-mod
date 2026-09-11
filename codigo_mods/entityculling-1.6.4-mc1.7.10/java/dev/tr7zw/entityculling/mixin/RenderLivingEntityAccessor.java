/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.EntityLivingBase
 */
package dev.tr7zw.entityculling.mixin;

import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value={RendererLivingEntity.class})
public interface RenderLivingEntityAccessor {
    @Invoker
    public void callPassSpecialRender(EntityLivingBase var1, double var2, double var4, double var6);
}

