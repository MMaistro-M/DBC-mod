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
import kamkeel.npcs.controllers.AbilityController;
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
import noppes.npcs.controllers.data.AbilityHotbarData;
import noppes.npcs.controllers.data.PlayerAbilityHotbarData;
import noppes.npcs.controllers.data.PlayerData;

public final class AbilityHotbarSyncPacket
extends AbstractPacket {
    public static final String packetName = "Data|AbilityHotbarSync";
    private NBTTagCompound hotbarNBT;

    public AbilityHotbarSyncPacket() {
    }

    public AbilityHotbarSyncPacket(NBTTagCompound hotbarNBT) {
        this.hotbarNBT = hotbarNBT;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.ABILITY_HOTBAR_SYNC;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, this.hotbarNBT);
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
            data.hotbarData.readFromNBT(nbt);
        }
    }

    public static void sendToPlayer(EntityPlayerMP player) {
        PlayerData data = PlayerDataController.Instance.getPlayerData((EntityPlayer)player);
        if (data == null) {
            return;
        }
        PlayerAbilityHotbarData hotbarData = data.hotbarData;
        boolean anyCleared = false;
        if (AbilityController.Instance != null) {
            for (int i = 0; i < hotbarData.slots.length; ++i) {
                AbilityHotbarData slot = hotbarData.slots[i];
                if (slot.isEmpty()) continue;
                boolean valid = slot.isChainKey() ? AbilityController.Instance.canResolveChainedAbility(slot.getResolveKey()) : AbilityController.Instance.canResolveAbility(slot.abilityKey);
                if (valid && data.abilityData != null && !(valid = data.abilityData.hasUnlockedAbility(slot.abilityKey)) && slot.isChainKey()) {
                    valid = data.abilityData.hasUnlockedAbility("chain:" + slot.getResolveKey());
                }
                if (valid) continue;
                slot.reset();
                anyCleared = true;
            }
        }
        NBTTagCompound nbt = new NBTTagCompound();
        hotbarData.writeToNBT(nbt);
        PacketHandler.Instance.sendToPlayer(new AbilityHotbarSyncPacket(nbt), player);
        if (anyCleared) {
            data.save();
        }
    }
}

