/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.JYearsC;
import JinRyuu.JYearsC.JYearsCCliTickH;
import JinRyuu.JYearsC.JYearsCGui;
import JinRyuu.JYearsC.JYearsCKeyHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;

public class JYearsCClient
extends JYearsC {
    public static Minecraft mc = Minecraft.func_71410_x();
    public static JYearsCGui JYCGui;

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void postInit() {
        super.postInit();
        FMLCommonHandler.instance().bus().register((Object)new JYearsCCliTickH());
    }

    @Override
    public void registerRenderThings() {
        JYCGui = new JYearsCGui();
    }

    @Override
    public void registerKeys() {
        ClientRegistry.registerKeyBinding((KeyBinding)JYearsCKeyHandler.Calendar);
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.side.isClient() ? JYearsCClient.mc.field_71439_g : super.getPlayerEntity(ctx);
    }
}

