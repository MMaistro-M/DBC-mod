/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.settings.KeyBinding
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyC;
import JinRyuu.FamilyC.FamilyCCliTicH;
import JinRyuu.FamilyC.FamilyCGui;
import JinRyuu.FamilyC.FamilyCKeyHandler;
import JinRyuu.FamilyC.RenderJFC;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.settings.KeyBinding;

public class FamilyCClient
extends FamilyC {
    public static Minecraft mc = Minecraft.func_71410_x();
    public static FamilyCGui JFCGui;

    @Override
    public void registerRenderThings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityNPC.class, (Render)new RenderJFC());
        JFCGui = new FamilyCGui();
    }

    @Override
    public void registerKeys() {
        ClientRegistry.registerKeyBinding((KeyBinding)FamilyCKeyHandler.Interact);
    }

    @Override
    public void registerTicks() {
        super.registerTicks();
        FMLCommonHandler.instance().bus().register((Object)new FamilyCCliTicH());
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}

