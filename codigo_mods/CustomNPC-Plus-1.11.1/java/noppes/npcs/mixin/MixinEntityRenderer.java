/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.client.ClientEventHandler;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityRenderer.class})
public abstract class MixinEntityRenderer {
    @Inject(method={"renderWorld"}, at={@At(value="RETURN")})
    private void renderFirstPersonOverlays(float p_78476_1_, long p_78476_2_, CallbackInfo callbackInfo) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (ClientEventHandler.hasOverlays((EntityPlayer)player)) {
            GL11.glPushMatrix();
            ClientEventHandler.renderCNPCSelf.renderHand(p_78476_1_, 0);
            GL11.glPopMatrix();
        }
    }
}

