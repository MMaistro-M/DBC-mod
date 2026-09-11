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
 *  net.minecraft.nbt.NBTTagString
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
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.controllers.ServerCloneController;

public final class CloneRemovePacket
extends AbstractPacket {
    public static String packetName = "Request|CloneRemove";
    private int tab;
    private String folderName;
    private String name;

    public CloneRemovePacket() {
    }

    public CloneRemovePacket(int tab, String name) {
        this.tab = tab;
        this.folderName = null;
        this.name = name;
    }

    public CloneRemovePacket(String folderName, String name) {
        this.tab = -1;
        this.folderName = folderName;
        this.name = name;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CloneRemove;
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
        out.writeInt(this.tab);
        if (this.tab == -1) {
            ByteBufUtils.writeString(out, this.folderName);
        }
        ByteBufUtils.writeString(out, this.name);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.CLONER)) {
            return;
        }
        int tab = in.readInt();
        String folder = null;
        if (tab == -1) {
            folder = ByteBufUtils.readString(in);
        }
        String cloneName = ByteBufUtils.readString(in);
        NBTTagList list = new NBTTagList();
        NBTTagList listDate = new NBTTagList();
        if (folder != null) {
            ServerCloneController.Instance.removeClone(cloneName, folder);
            for (String name : ServerCloneController.Instance.getClones(folder)) {
                list.func_74742_a((NBTBase)new NBTTagString(name));
            }
            for (String name : ServerCloneController.Instance.getClonesDate(folder)) {
                listDate.func_74742_a((NBTBase)new NBTTagString(name));
            }
        } else {
            ServerCloneController.Instance.removeClone(cloneName, tab);
            for (String name : ServerCloneController.Instance.getClones(tab)) {
                list.func_74742_a((NBTBase)new NBTTagString(name));
            }
            for (String name : ServerCloneController.Instance.getClonesDate(tab)) {
                listDate.func_74742_a((NBTBase)new NBTTagString(name));
            }
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74782_a("List", (NBTBase)list);
        compound.func_74782_a("ListDate", (NBTBase)listDate);
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

