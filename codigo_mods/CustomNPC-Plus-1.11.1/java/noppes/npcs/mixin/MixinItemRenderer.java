/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.AnimationMixinFunctions;
import noppes.npcs.client.ClientEventHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemRenderer.class})
public abstract class MixinItemRenderer {
    @Shadow
    private RenderBlocks field_147720_h;
    @Shadow
    private static ResourceLocation field_110930_b;

    @Inject(method={"renderItemInFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderItemInFirstPerson(float p_78440_1_, CallbackInfo callbackInfo) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        Render renderer = RenderManager.field_78727_a.func_78713_a((Entity)player);
        if (renderer instanceof RendererLivingEntity && ((RendererLivingEntity)renderer).field_77045_g instanceof ModelBiped) {
            Render playerRenderer = RenderManager.field_78727_a.func_78713_a((Entity)player);
            if (playerRenderer instanceof RendererLivingEntity) {
                ClientEventHandler.renderer = (RendererLivingEntity)playerRenderer;
            }
            ClientEventHandler.partialRenderTick = Minecraft.func_71410_x().field_71428_T.field_74281_c;
            ClientEventHandler.partialHandTicks = p_78440_1_;
            ClientEventHandler.renderingNpc = null;
            ClientEventHandler.renderingPlayer = player;
            ClientEventHandler.firstPersonAnimation = true;
            ClientEventHandler.firstPersonModel = (ModelBiped)((RendererLivingEntity)renderer).field_77045_g;
            if (AnimationMixinFunctions.mixin_renderFirstPersonAnimation(p_78440_1_, (EntityPlayer)player, ClientEventHandler.firstPersonModel, this.field_147720_h, field_110930_b)) {
                callbackInfo.cancel();
            }
            ClientEventHandler.firstPersonAnimation = false;
            ClientEventHandler.renderingPlayer = null;
        }
    }

    @Inject(method={"renderItemInFirstPerson"}, at={@At(value="TAIL")})
    public void renderItemInFirstPerson_tail(float p_78440_1_, CallbackInfo callbackInfo) {
        ClientEventHandler.firstPersonAnimation = false;
        ClientEventHandler.renderingPlayer = null;
    }
}

