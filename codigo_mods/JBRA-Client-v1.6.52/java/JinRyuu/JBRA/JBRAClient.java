/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.player.EntityPlayer
 */
package JinRyuu.JBRA;

import JinRyuu.JBRA.JBRA;
import JinRyuu.JBRA.JBRACliTicH;
import JinRyuu.JBRA.RenderPlayerJBRA;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;

public class JBRAClient
extends JBRA {
    public static Minecraft mc = Minecraft.func_71410_x();

    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, (Render)new RenderPlayerJBRA());
    }

    @Override
    public void registerTicks() {
        FMLCommonHandler.instance().bus().register((Object)new JBRACliTicH());
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}

