/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.player;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.roles.RoleTrader;

public class GetTraderData
extends AbstractPacket {
    public static final String packetName = "Player|GetTraderData";

    @Override
    public Enum getType() {
        return EnumPlayerPacket.GetTraderData;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int i;
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!(this.npc.roleInterface instanceof RoleTrader)) {
            return;
        }
        RoleTrader role = (RoleTrader)this.npc.roleInterface;
        NBTTagCompound compound = new NBTTagCompound();
        PlayerData data = PlayerData.get(player);
        compound.func_74772_a("Balance", data.tradeData.getBalance());
        compound.func_74757_a("StockEnabled", role.stock.enableStock);
        compound.func_74772_a("ResetTimeMillis", role.getResetTimeRemainingMillis());
        String playerName = player.func_70005_c_();
        int[] stock = new int[18];
        for (i = 0; i < 18; ++i) {
            stock[i] = role.getAvailableStock(i, playerName);
        }
        compound.func_74783_a("Stock", stock);
        for (i = 0; i < 18; ++i) {
            compound.func_74772_a("Cost" + i, role.getCurrencyCost(i));
        }
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

