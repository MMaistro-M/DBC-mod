/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.world.storage.SaveHandler
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.DBCCommonTickHandler;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import java.io.File;
import net.minecraft.world.storage.SaveHandler;

public class DBC {
    public static SaveHandler saveHandler;
    public static DBCPacketHandlerServer phs;

    public void initialize() {
        FMLCommonHandler.instance().bus().register((Object)new DBCCommonTickHandler());
    }

    public void postInit() {
    }

    public void registerRenderThings() {
    }

    public void registerTicks() {
    }

    public void registerKeys() {
    }

    public String getMinecraftVersion() {
        return Loader.instance().getMinecraftModContainer().getVersion();
    }

    public File getMinecraftDir() {
        return new File(".");
    }

    public void registerDBCRender() {
    }

    static {
        phs = new DBCPacketHandlerServer();
    }
}

