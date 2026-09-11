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

public final class ClonePreSavePacket
extends AbstractPacket {
    public static String packetName = "Request|ClonePreSave";
    private String name;
    private int tab;
    private String folderName;

    public ClonePreSavePacket() {
    }

    public ClonePreSavePacket(String name, int tab) {
        this.name = name;
        this.tab = tab;
        this.folderName = null;
    }

    public ClonePreSavePacket(String name, String folderName) {
        this.name = name;
        this.tab = -1;
        this.folderName = folderName;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ClonePreSave;
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
        ByteBufUtils.writeString(out, this.name);
        out.writeInt(this.tab);
        if (this.tab == -1) {
            ByteBufUtils.writeString(out, this.folderName);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        String folder;
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.CLONER)) {
            return;
        }
        String name = ByteBufUtils.readString(in);
        int tab = in.readInt();
        boolean bo = tab == -1 ? ServerCloneController.Instance.getCloneData(null, name, folder = ByteBufUtils.readString(in)) != null : ServerCloneController.Instance.getCloneData(null, name, tab) != null;
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("NameExists", bo);
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

