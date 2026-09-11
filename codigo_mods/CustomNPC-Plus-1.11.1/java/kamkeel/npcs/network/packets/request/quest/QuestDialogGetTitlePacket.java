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
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.data.Dialog;

public final class QuestDialogGetTitlePacket
extends AbstractPacket {
    public static String packetName = "Request|QuestDialogGetTitle";
    private int dialogOne;
    private int dialogTwo;
    private int dialogThree;

    public QuestDialogGetTitlePacket() {
    }

    public QuestDialogGetTitlePacket(int dialogOne, int dialogTwo, int dialogThree) {
        this.dialogOne = dialogOne;
        this.dialogTwo = dialogTwo;
        this.dialogThree = dialogThree;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.QuestDialogGetTitle;
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
        out.writeInt(this.dialogOne);
        out.writeInt(this.dialogTwo);
        out.writeInt(this.dialogThree);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        Dialog quest = DialogController.Instance.dialogs.get(in.readInt());
        Dialog quest2 = DialogController.Instance.dialogs.get(in.readInt());
        Dialog quest3 = DialogController.Instance.dialogs.get(in.readInt());
        NBTTagCompound compound = new NBTTagCompound();
        if (quest != null) {
            compound.func_74778_a("1", quest.title);
        }
        if (quest2 != null) {
            compound.func_74778_a("2", quest2.title);
        }
        if (quest3 != null) {
            compound.func_74778_a("3", quest3.title);
        }
        GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
    }
}

