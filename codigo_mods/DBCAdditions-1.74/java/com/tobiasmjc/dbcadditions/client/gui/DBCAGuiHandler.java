/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.client.gui;

import com.tobiasmjc.dbcadditions.client.gui.DBAGuiFormWheel;
import com.tobiasmjc.dbcadditions.client.gui.DBCATalk;
import com.tobiasmjc.dbcadditions.client.gui.GuiContainerDBCA;
import com.tobiasmjc.dbcadditions.data.DBCAPlayerEProperties;
import com.tobiasmjc.dbcadditions.inventory.ContainerDBCAPlayer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class DBCAGuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 2) {
            return new ContainerDBCAPlayer(DBCAPlayerEProperties.get((EntityPlayer)player).inventory, player);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == 1) {
            return new DBAGuiFormWheel();
        }
        if (ID == 2) {
            return new GuiContainerDBCA(player, player.field_71071_by, DBCAPlayerEProperties.get((EntityPlayer)player).inventory);
        }
        if (ID >= 3) {
            return new DBCATalk(ID, world, x, y, z);
        }
        return null;
    }
}

