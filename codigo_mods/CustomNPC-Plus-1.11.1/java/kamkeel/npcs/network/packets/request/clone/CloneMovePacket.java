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
package kamkeel.npcs.network.packets.request.clone;

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
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.controllers.ServerCloneController;

public final class CloneMovePacket
extends AbstractPacket {
    public static String packetName = "Request|CloneMove";
    private String cloneName;
    private int fromTab;
    private String fromFolder;
    private int toTab;
    private String toFolder;

    public CloneMovePacket() {
    }

    public CloneMovePacket(String cloneName, int fromTab, String fromFolder, int toTab, String toFolder) {
        this.cloneName = cloneName;
        this.fromTab = fromTab;
        this.fromFolder = fromFolder;
        this.toTab = toTab;
        this.toFolder = toFolder;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CloneMove;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_CLONE;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeString(out, this.cloneName);
        out.writeInt(this.fromTab);
        ByteBufUtils.writeString(out, this.fromFolder != null ? this.fromFolder : "");
        out.writeInt(this.toTab);
        ByteBufUtils.writeString(out, this.toFolder != null ? this.toFolder : "");
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.CLONER)) {
            return;
        }
        String cloneName = ByteBufUtils.readString(in);
        int fromTab = in.readInt();
        String fromFolder = ByteBufUtils.readString(in);
        if (fromFolder.isEmpty()) {
            fromFolder = null;
        }
        int toTab = in.readInt();
        String toFolder = ByteBufUtils.readString(in);
        if (toFolder.isEmpty()) {
            toFolder = null;
        }
        boolean success = false;
        success = fromFolder != null && toFolder != null ? ServerCloneController.Instance.moveClone(cloneName, fromFolder, toFolder) : (fromFolder != null ? ServerCloneController.Instance.moveClone(cloneName, fromFolder, toTab) : (toFolder != null ? ServerCloneController.Instance.moveClone(cloneName, fromTab, toFolder) : ServerCloneController.Instance.moveClone(cloneName, fromTab, toTab)));
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("MoveSuccess", success);
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

