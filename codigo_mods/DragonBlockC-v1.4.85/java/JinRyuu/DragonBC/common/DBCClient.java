/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClientTickHandler;
import JinRyuu.DragonBC.common.DBCKeyHandler;
import JinRyuu.DragonBC.common.Gui.DBCKiGui;
import JinRyuu.DragonBC.common.Gui.DBCSAAGui;
import JinRyuu.DragonBC.common.Gui.DBCSagaGui;
import JinRyuu.DragonBC.common.Gui.DBCWishGui;
import JinRyuu.DragonBC.common.Gui.ScouterGui;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntitiesDBC;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

@SideOnly(value=Side.CLIENT)
public class DBCClient
extends DBC {
    public static ScouterGui scouterGui;
    public static DBCSagaGui SagaSys;
    public static DBCKiGui KiGui;
    public static DBCWishGui WishGui;
    public static DBCSAAGui SAAGui;
    public static Minecraft mc;
    public static DBCPacketHandlerClient phc;

    @Override
    public void initialize() {
        super.initialize();
        FMLCommonHandler.instance().bus().register((Object)new DBCClientTickHandler());
    }

    @Override
    public void postInit() {
        super.postInit();
    }

    @Override
    public void registerRenderThings() {
        scouterGui = new ScouterGui();
        SagaSys = new DBCSagaGui();
        KiGui = new DBCKiGui(0);
        WishGui = new DBCWishGui(0);
        SAAGui = new DBCSAAGui(2);
        ItemsDBC.client();
        EntitiesDBC.client();
    }

    @Override
    public void registerKeys() {
        ClientRegistry.registerKeyBinding((KeyBinding)DBCKeyHandler.ScFunc);
        ClientRegistry.registerKeyBinding((KeyBinding)DBCKeyHandler.thirdFn);
    }

    @Override
    public void registerTicks() {
    }

    @Override
    public File getMinecraftDir() {
        return Minecraft.func_71410_x().field_71412_D;
    }

    static {
        mc = Minecraft.func_71410_x();
        phc = new DBCPacketHandlerClient();
    }
}

