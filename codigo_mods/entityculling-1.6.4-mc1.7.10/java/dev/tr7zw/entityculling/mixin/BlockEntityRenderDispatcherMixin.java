/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntity
 */
package dev.tr7zw.entityculling.mixin;

import dev.tr7zw.entityculling.EntityCullingModBase;
import dev.tr7zw.entityculling.access.Cullable;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntityRendererDispatcher.class})
public class BlockEntityRenderDispatcherMixin {
    @Inject(method={"renderTileEntityAt"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderTileEntityAt(TileEntity blockEntity, double p_147549_2_, double p_147549_4_, double p_147549_6_, float p_147549_8_, CallbackInfo info) {
        if (!((Cullable)blockEntity).isForcedVisible() && ((Cullable)blockEntity).isCulled()) {
            ++EntityCullingModBase.instance.skippedBlockEntities;
            info.cancel();
            return;
        }
        ++EntityCullingModBase.instance.renderedBlockEntities;
    }
}

