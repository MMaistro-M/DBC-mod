/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.player.ability;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.ability.AbilityHotbarSyncPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.AbilityHotbarData;
import noppes.npcs.controllers.data.PlayerData;

public final class AbilityHotbarSavePacket
extends AbstractPacket {
    public static final String packetName = "Player|AbilityHotbarSave";
    private int slotIndex;
    private NBTTagCompound slotData;

    public AbilityHotbarSavePacket() {
    }

    public AbilityHotbarSavePacket(int slotIndex, NBTTagCompound slotData) {
        this.slotIndex = slotIndex;
        this.slotData = slotData;
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.AbilityHotbarSave;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.slotIndex);
        ByteBufUtils.writeNBT(out, this.slotData);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int slot = in.readInt();
        NBTTagCompound compound = ByteBufUtils.readNBT(in);
        if (compound == null) {
            return;
        }
        if (slot < 0 || slot >= 12) {
            return;
        }
        PlayerData data = PlayerDataController.Instance.getPlayerData(player);
        if (data == null) {
            return;
        }
        AbilityHotbarData slotData = data.hotbarData.getSlot(slot);
        if (slotData == null) {
            return;
        }
        slotData.readFromNBT(compound.func_74775_l("AbilityHotbar" + slot));
        boolean valid = true;
        if (!slotData.isEmpty() && AbilityController.Instance != null && (valid = slotData.isChainKey() ? AbilityController.Instance.canResolveChainedAbility(slotData.getResolveKey()) : AbilityController.Instance.canResolveAbility(slotData.abilityKey)) && data.abilityData != null && !(valid = data.abilityData.hasUnlockedAbility(slotData.abilityKey)) && slotData.isChainKey()) {
            valid = data.abilityData.hasUnlockedAbility("chain:" + slotData.getResolveKey());
        }
        if (!valid) {
            slotData.reset();
        }
        data.save();
        AbilityHotbarSyncPacket.sendToPlayer((EntityPlayerMP)player);
    }
}

