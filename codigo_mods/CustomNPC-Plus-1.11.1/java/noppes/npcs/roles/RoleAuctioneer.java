/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.roles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;

public class RoleAuctioneer
extends RoleInterface {
    public RoleAuctioneer(EntityNPCInterface npc) {
        super(npc);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
    }

    @Override
    public void interact(EntityPlayer player) {
        if (!ConfigMarket.AuctionEnabled) {
            return;
        }
        NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerAuction, this.npc);
    }

    @Override
    public void delete() {
    }
}

