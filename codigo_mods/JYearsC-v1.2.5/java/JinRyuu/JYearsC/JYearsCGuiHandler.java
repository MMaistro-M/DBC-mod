/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package JinRyuu.JYearsC;

import JinRyuu.JYearsC.JYearsCCalGui;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class JYearsCGuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        JYearsCCalGui Gui2 = null;
        if (id == 0) {
            Gui2 = new JYearsCCalGui(0);
        }
        if (id == 1) {
            Gui2 = new JYearsCCalGui(1);
        }
        if (id == 2) {
            Gui2 = new JYearsCCalGui(2);
        }
        return Gui2;
    }
}

