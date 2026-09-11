/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Iterator;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.LargeAbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.containers.ContainerMail;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.PlayerQuestController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.controllers.data.PlayerMailData;
import noppes.npcs.controllers.data.QuestData;

public class MailActionPacket
extends AbstractPacket {
    public static final String packetName = "Player|MailAction";
    private Action action;
    private long time;
    private String username;

    public MailActionPacket() {
    }

    private MailActionPacket(Action action, long time, String sender) {
        this.action = action;
        this.time = time;
        this.username = sender;
    }

    public static void RequestMailData() {
        MailActionPacket packet = new MailActionPacket();
        packet.action = Action.GET;
        PacketClient.sendClient(packet);
    }

    public static void OpenMail(long time, String sender) {
        PacketClient.sendClient(new MailActionPacket(Action.OPEN, time, sender));
    }

    public static void DeleteMail(long time, String sender) {
        PacketClient.sendClient(new MailActionPacket(Action.DELETE, time, sender));
    }

    public static void ReadMail(long time, String sender) {
        PacketClient.sendClient(new MailActionPacket(Action.READ, time, sender));
    }

    public static void SendMail(String receiver, NBTTagCompound mailData) {
        PacketClient.sendClient(new MailSendPacket(receiver, mailData));
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.MailBoxAction;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.action.ordinal());
        if (this.action == Action.GET) {
            return;
        }
        switch (this.action) {
            case OPEN: 
            case DELETE: 
            case READ: {
                out.writeLong(this.time);
                ByteBufUtils.writeString(out, this.username);
            }
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        Action type = Action.values()[in.readInt()];
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        EntityPlayerMP playerMP = (EntityPlayerMP)player;
        PlayerMailData data = PlayerData.get((EntityPlayer)playerMP).mailData;
        if (type == Action.GET) {
            GuiDataPacket.sendGuiData(playerMP, data.saveNBTData(new NBTTagCompound()));
            return;
        }
        long time = in.readLong();
        String user = ByteBufUtils.readString(in);
        Iterator<PlayerMail> it = data.playermail.iterator();
        block0 : switch (type) {
            case OPEN: {
                playerMP.func_71128_l();
                while (it.hasNext()) {
                    PlayerMail mail = it.next();
                    if (mail.time != time || !mail.sender.equals(user)) continue;
                    ContainerMail.staticmail = mail;
                    playerMP.openGui((Object)CustomNpcs.instance, EnumGuiType.PlayerMailman.ordinal(), playerMP.field_70170_p, 0, 0, 0);
                    break block0;
                }
                break;
            }
            case DELETE: {
                while (it.hasNext()) {
                    PlayerMail mail = it.next();
                    if (mail.time != time || !mail.sender.equals(user)) continue;
                    it.remove();
                }
                GuiDataPacket.sendGuiData(playerMP, data.saveNBTData(new NBTTagCompound()));
                break;
            }
            case READ: {
                while (it.hasNext()) {
                    PlayerMail mail = it.next();
                    if (mail.time != time || !mail.sender.equals(user)) continue;
                    mail.beenRead = true;
                    if (!mail.hasQuest()) continue;
                    PlayerQuestController.addActiveQuest(new QuestData(mail.getQuest()), (EntityPlayer)playerMP);
                }
                break;
            }
        }
    }

    private static enum Action {
        GET,
        OPEN,
        READ,
        DELETE;

    }

    public static class MailSendPacket
    extends LargeAbstractPacket {
        public static final String packetName = "Player|MailSend";
        private String username;
        private NBTTagCompound mailContent;

        public MailSendPacket() {
        }

        private MailSendPacket(String username, NBTTagCompound mailContent) {
            this.username = username;
            this.mailContent = mailContent;
        }

        @Override
        protected byte[] getData() throws IOException {
            ByteBuf buffer = Unpooled.buffer();
            ByteBufUtils.writeString(buffer, this.username);
            ByteBufUtils.writeBigNBT(buffer, this.mailContent);
            return buffer.array();
        }

        @Override
        protected void handleCompleteData(ByteBuf data, EntityPlayer player) throws IOException {
            if (!(player instanceof EntityPlayerMP)) {
                return;
            }
            EntityPlayerMP playerMP = (EntityPlayerMP)player;
            if (!(playerMP.field_71070_bA instanceof ContainerMail)) {
                return;
            }
            String mailReceiver = PlayerDataController.Instance.hasPlayer(ByteBufUtils.readString(data));
            if (mailReceiver.isEmpty()) {
                NoppesUtilServer.sendGuiError((EntityPlayer)playerMP, 0);
                return;
            }
            PlayerMail mail = new PlayerMail();
            String s = playerMP.getDisplayName();
            if (!s.equals(playerMP.func_70005_c_())) {
                s = s + " (" + playerMP.func_70005_c_() + ")";
            }
            mail.readNBT(ByteBufUtils.readBigNBT(data));
            mail.sender = s;
            mail.items = ((ContainerMail)playerMP.field_71070_bA).mail.items;
            if (mail.subject.isEmpty()) {
                NoppesUtilServer.sendGuiError((EntityPlayer)playerMP, 1);
                return;
            }
            PlayerDataController.Instance.addPlayerMessage(mailReceiver, mail);
            NBTTagCompound comp = new NBTTagCompound();
            comp.func_74778_a("username", mailReceiver);
            NoppesUtilServer.sendGuiClose(playerMP, 1, comp);
        }

        @Override
        public Enum getType() {
            return EnumPlayerPacket.MailSend;
        }

        @Override
        public PacketChannel getChannel() {
            return PacketHandler.PLAYER_PACKET;
        }
    }
}

