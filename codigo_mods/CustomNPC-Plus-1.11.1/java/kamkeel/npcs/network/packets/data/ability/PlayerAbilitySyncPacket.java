/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public final class PlayerAbilitySyncPacket
extends AbstractPacket {
    public static final String packetName = "Data|PlayerAbilitySync";
    private NBTTagCompound abilityNBT;

    public PlayerAbilitySyncPacket() {
    }

    public PlayerAbilitySyncPacket(NBTTagCompound abilityNBT) {
        this.abilityNBT = abilityNBT;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.PLAYER_ABILITY_SYNC;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, this.abilityNBT);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        if (nbt == null) {
            return;
        }
        PlayerData data = ClientCacheHandler.playerData;
        if (data != null) {
            data.abilityData.readFromNBT(nbt);
        }
    }

    public static void sendToPlayer(EntityPlayerMP player) {
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (data == null) {
            return;
        }
        NBTTagCompound nbt = new NBTTagCompound();
        data.abilityData.writeToNBT(nbt);
        PacketHandler.Instance.sendToPlayer(new PlayerAbilitySyncPacket(nbt), player);
    }
}

