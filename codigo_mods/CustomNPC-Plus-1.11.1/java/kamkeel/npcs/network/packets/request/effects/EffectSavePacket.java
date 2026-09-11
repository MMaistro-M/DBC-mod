/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.effects;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;

public class EffectSavePacket
extends AbstractPacket {
    public static final String packetName = "NPC|EffectSave";
    private String prevName;
    private NBTTagCompound effectCompound;

    public EffectSavePacket(NBTTagCompound compound, String prev) {
        this.effectCompound = compound;
        this.prevName = prev;
    }

    public EffectSavePacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.EffectSave;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.GLOBAL_EFFECT;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.prevName);
        ByteBufUtils.writeNBT(out, this.effectCompound);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        String prevName = ByteBufUtils.readString(in);
        CustomEffect effect = new CustomEffect();
        effect.readFromNBT(ByteBufUtils.readNBT(in));
        CustomEffectController.getInstance().saveEffect(effect);
        if (!prevName.isEmpty() && !prevName.equals(effect.name)) {
            CustomEffectController.getInstance().deleteEffectFile(prevName);
        }
        NoppesUtilServer.sendCustomEffectDataAll((EntityPlayerMP)player);
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, effect.writeToNBT(false));
    }
}

