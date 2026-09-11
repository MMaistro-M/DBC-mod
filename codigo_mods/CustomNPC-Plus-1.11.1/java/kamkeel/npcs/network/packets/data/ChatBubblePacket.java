/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.RenderChatMessages;
import noppes.npcs.entity.EntityNPCInterface;

public final class ChatBubblePacket
extends AbstractPacket {
    public static final String packetName = "Data|ChatBubble";
    private int entityId;
    private String text;
    private boolean hideText;

    public ChatBubblePacket() {
    }

    public ChatBubblePacket(int entityId, String text, boolean hideText) {
        this.entityId = entityId;
        this.text = text;
        this.hideText = hideText;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.CHATBUBBLE;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.entityId);
        ByteBufUtils.writeString(out, this.text);
        out.writeBoolean(this.hideText);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        Entity entity = Minecraft.func_71410_x().field_71441_e.func_73045_a(in.readInt());
        if (!(entity instanceof EntityNPCInterface)) {
            return;
        }
        EntityNPCInterface npc = (EntityNPCInterface)entity;
        if (npc.messages == null) {
            npc.messages = new RenderChatMessages();
        }
        String text = NoppesStringUtils.formatText(ByteBufUtils.readString(in), new Object[]{player, npc});
        npc.messages.addMessage(text, npc);
        if (in.readBoolean()) {
            player.func_145747_a((IChatComponent)new ChatComponentTranslation(npc.func_70005_c_() + ": " + text, new Object[0]));
        }
    }
}

