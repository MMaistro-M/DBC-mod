/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package dev.tr7zw.entityculling.mixin;

import dev.tr7zw.entityculling.EntityCullingModBase;
import dev.tr7zw.entityculling.access.Cullable;
import dev.tr7zw.entityculling.mixin.RenderLivingEntityAccessor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={RenderManager.class})
public abstract class WorldRendererMixin {
    @Shadow
    public abstract Render func_78713_a(Entity var1);

    @Inject(at={@At(value="HEAD")}, method={"func_147939_a"}, cancellable=true)
    public void doRenderEntity(Entity entity, double p_doRenderEntity_2_, double d1, double d2, float tickDelta, float p_doRenderEntity_9_, boolean p_doRenderEntity_10_, CallbackInfoReturnable<Boolean> info) {
        Cullable cullable = (Cullable)entity;
        if (!cullable.isForcedVisible() && cullable.isCulled()) {
            if (EntityCullingModBase.instance.config.renderNametagsThroughWalls && entity instanceof EntityLivingBase && this.func_78713_a(entity) instanceof RenderLivingEntityAccessor) {
                RenderLivingEntityAccessor livingEntity = (RenderLivingEntityAccessor)this.func_78713_a(entity);
                livingEntity.callPassSpecialRender((EntityLivingBase)entity, p_doRenderEntity_2_, d1, d2);
            }
            ++EntityCullingModBase.instance.skippedEntities;
            info.cancel();
            return;
        }
        ++EntityCullingModBase.instance.renderedEntities;
        cullable.setOutOfCamera(false);
    }
}

