/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreGuiSCM;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.i.ContainerCustomPlayer;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.i.GuiCustomPlayerInventory;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class JRMCoreGuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
        if (guiId == mod_JRMCore.GUI_CUSTOM_INV) {
            return new ContainerCustomPlayer(player, player.field_71071_by, ExtendedPlayer.get((EntityPlayer)player).inventory);
        }
        return null;
    }

    public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
        if (guiId == mod_JRMCore.GUI_CUSTOM_INV) {
            return new GuiCustomPlayerInventory(player, player.field_71071_by, ExtendedPlayer.get((EntityPlayer)player).inventory);
        }
        if (guiId >= 0) {
            return new JRMCoreGuiScreen(guiId);
        }
        if (guiId == -1) {
            return new JRMCoreGuiSCM();
        }
        return null;
    }
}

