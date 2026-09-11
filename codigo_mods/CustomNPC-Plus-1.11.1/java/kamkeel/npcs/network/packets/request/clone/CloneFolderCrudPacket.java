/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
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
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.data.CloneFolder;

public final class CloneFolderCrudPacket
extends AbstractPacket {
    public static String packetName = "Request|CloneFolderCrud";
    public static final byte ACTION_CREATE = 0;
    public static final byte ACTION_RENAME = 1;
    public static final byte ACTION_DELETE = 2;
    private byte action;
    private String name;
    private String newName;

    public CloneFolderCrudPacket() {
    }

    public CloneFolderCrudPacket(byte action, String name) {
        this.action = action;
        this.name = name;
        this.newName = "";
    }

    public CloneFolderCrudPacket(byte action, String name, String newName) {
        this.action = action;
        this.name = name;
        this.newName = newName;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CloneFolderCrud;
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
        out.writeByte((int)this.action);
        ByteBufUtils.writeString(out, this.name);
        if (this.action == 1) {
            ByteBufUtils.writeString(out, this.newName);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.CLONER)) {
            return;
        }
        byte action = in.readByte();
        String name = ByteBufUtils.readString(in);
        boolean success = false;
        switch (action) {
            case 0: {
                if (!CloneFolder.isValidName(name)) break;
                success = ServerCloneController.Instance.createFolder(name) != null;
                break;
            }
            case 1: {
                String newName = ByteBufUtils.readString(in);
                if (!CloneFolder.isValidName(newName)) break;
                success = ServerCloneController.Instance.renameFolder(name, newName);
                break;
            }
            case 2: {
                success = ServerCloneController.Instance.deleteFolder(name);
            }
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("Success", success);
        NBTTagList folderList = new NBTTagList();
        for (CloneFolder folder : ServerCloneController.Instance.getFolderList()) {
            folderList.func_74742_a((NBTBase)folder.writeNBT(new NBTTagCompound()));
        }
        compound.func_74782_a("CloneFolders", (NBTBase)folderList);
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

