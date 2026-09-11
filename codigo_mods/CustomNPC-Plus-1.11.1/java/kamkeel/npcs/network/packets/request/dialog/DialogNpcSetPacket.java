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
package kamkeel.npcs.network.packets.request.dialog;

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
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.data.DialogOption;

public final class DialogNpcSetPacket
extends AbstractPacket {
    public static String packetName = "Request|DialogNpcSet";
    private int slot;
    private int dialog;

    public DialogNpcSetPacket(int slot, int dialog) {
        this.slot = slot;
        this.dialog = dialog;
    }

    public DialogNpcSetPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.DialogNpcSet;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_ADVANCED_DIALOG;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.slot);
        out.writeInt(this.dialog);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int d;
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        int s = in.readInt();
        DialogOption option = NoppesUtilServer.setNpcDialog(s, d = in.readInt(), player);
        if (option != null && option.hasDialog()) {
            NBTTagCompound compound = option.writeNBT();
            compound.func_74768_a("Position", s);
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
        }
    }
}

