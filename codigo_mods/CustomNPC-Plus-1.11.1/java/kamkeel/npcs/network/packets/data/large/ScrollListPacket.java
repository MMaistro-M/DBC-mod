/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.data.large;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.List;
import kamkeel.npcs.network.LargeAbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.constants.EnumScrollData;

public final class ScrollListPacket
extends LargeAbstractPacket {
    public static final String packetName = "Large|ScrollList";
    private List<String> data;
    private EnumScrollData enumScrollData;

    public ScrollListPacket() {
    }

    public ScrollListPacket(List<String> data, EnumScrollData enumScrollData) {
        this.data = data;
        this.enumScrollData = enumScrollData;
    }

    public static void sendList(EntityPlayerMP player, List<String> list, EnumScrollData enumScrollData) {
        ScrollListPacket packet = new ScrollListPacket(list, enumScrollData);
        PacketHandler.Instance.sendToPlayer(packet, player);
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.SCROLL_LIST;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    protected byte[] getData() throws IOException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(this.enumScrollData.ordinal());
        buffer.writeInt(this.data.size());
        for (String entry : this.data) {
            ByteBufUtils.writeString(buffer, entry);
        }
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        return bytes;
    }

    @Override
    protected void handleCompleteData(ByteBuf data, EntityPlayer player) throws IOException {
        NoppesUtil.setScrollList(data);
    }
}

