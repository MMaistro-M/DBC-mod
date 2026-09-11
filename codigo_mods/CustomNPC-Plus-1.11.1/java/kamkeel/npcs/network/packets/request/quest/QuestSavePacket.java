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
package kamkeel.npcs.network.packets.request.quest;

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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.data.Quest;

public final class QuestSavePacket
extends AbstractPacket {
    public static String packetName = "Request|QuestSave";
    private int categoryId;
    private NBTTagCompound questNBT;
    private boolean sendGroup;

    public QuestSavePacket(int categoryId, NBTTagCompound questNBT, boolean sendGroup) {
        this.categoryId = categoryId;
        this.questNBT = questNBT;
        this.sendGroup = sendGroup;
    }

    public QuestSavePacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.QuestSave;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.GLOBAL_QUEST;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.categoryId);
        ByteBufUtils.writeNBT(out, this.questNBT);
        out.writeBoolean(this.sendGroup);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        int cat = in.readInt();
        NBTTagCompound compound = ByteBufUtils.readNBT(in);
        boolean sendGroup = in.readBoolean();
        Quest quest = new Quest();
        quest.readNBT(compound);
        QuestController.Instance.saveQuest(cat, quest);
        if (quest.category != null) {
            if (sendGroup) {
                NoppesUtilServer.sendQuestGroup((EntityPlayerMP)player, quest.category);
            } else {
                NoppesUtilServer.sendQuestData((EntityPlayerMP)player, quest.category);
            }
        }
    }
}

