/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import noppes.npcs.config.ConfigClient;

public final class ChatAlertPacket
extends AbstractPacket {
    public static final String packetName = "Data|ChatAlert";
    private Object[] objects;

    public ChatAlertPacket() {
    }

    public ChatAlertPacket(Object ... obs) {
        this.objects = obs;
    }

    public static void sendChatAlert(EntityPlayerMP playerMP, Object ... obs) {
        ChatAlertPacket packet = new ChatAlertPacket(obs);
        PacketHandler.Instance.sendToPlayer(packet, playerMP);
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.CHAT_ALERT;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.fillBuffer(out, this.objects);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        String part;
        if (!ConfigClient.ChatAlerts) {
            return;
        }
        ArrayList<String> parts = new ArrayList<String>();
        while ((part = ByteBufUtils.readString(in)) != null && !part.isEmpty()) {
            parts.add(part);
        }
        StringBuilder finalMessage = new StringBuilder();
        for (String s : parts) {
            finalMessage.append(StatCollector.func_74838_a((String)s));
        }
        String text = finalMessage.toString();
        IChatComponent comp = ColorUtil.assembleComponent(text);
        player.func_145747_a(comp);
    }
}

