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
import noppes.npcs.controllers.ServerTagMapController;
import noppes.npcs.controllers.data.TagMap;

public final class CloneTagListPacket
extends AbstractPacket {
    public static String packetName = "Request|CloneTagList";
    private int tab;
    private String folderName;

    public CloneTagListPacket(int tab) {
        this.tab = tab;
        this.folderName = null;
    }

    public CloneTagListPacket(String folderName) {
        this.tab = -1;
        this.folderName = folderName;
    }

    public CloneTagListPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CloneTagList;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.tab);
        if (this.tab == -1) {
            ByteBufUtils.writeString(out, this.folderName);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        TagMap tagMap;
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.CLONER)) {
            return;
        }
        int tab = in.readInt();
        if (tab == -1) {
            String folder = ByteBufUtils.readString(in);
            tagMap = ServerTagMapController.Instance.getTagMap(folder);
        } else {
            tagMap = ServerTagMapController.Instance.getTagMap(tab);
        }
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74782_a("CloneTags", (NBTBase)tagMap.writeNBT());
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

