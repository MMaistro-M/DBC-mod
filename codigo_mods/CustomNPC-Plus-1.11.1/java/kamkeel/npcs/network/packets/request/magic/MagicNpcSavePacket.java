/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.magic;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.controllers.data.MagicData;

public final class MagicNpcSavePacket
extends AbstractPacket {
    public static final String packetName = "Request|NpcMagicGet";
    private MagicData magicData;

    public MagicNpcSavePacket() {
    }

    public MagicNpcSavePacket(MagicData magicData) {
        this.magicData = magicData;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.NpcMagicSave;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_ADVANCED_MAGIC;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        NBTTagCompound compound = new NBTTagCompound();
        if (this.magicData != null) {
            this.magicData.writeToNBT(compound);
        }
        ByteBufUtils.writeNBT(out, compound);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.WAND)) {
            return;
        }
        NBTTagCompound compound = ByteBufUtils.readNBT(in);
        this.npc.stats.magicData.readToNBT(compound);
    }
}

