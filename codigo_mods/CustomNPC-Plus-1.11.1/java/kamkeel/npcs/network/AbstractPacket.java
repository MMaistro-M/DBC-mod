/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.network;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import kamkeel.npcs.network.PacketChannel;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.entity.EntityNPCInterface;

public abstract class AbstractPacket {
    public EntityNPCInterface npc;

    public FMLProxyPacket generatePacket() {
        PacketChannel packetChannel = this.getChannel();
        ByteBuf buf = Unpooled.buffer();
        try {
            buf.writeInt(packetChannel.getChannelType().ordinal());
            buf.writeInt(this.getType().ordinal());
            this.sendData(buf);
            return new FMLProxyPacket(buf, packetChannel.getChannelName());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<FMLProxyPacket> generatePackets() {
        FMLProxyPacket single = this.generatePacket();
        if (single == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(single);
    }

    public abstract Enum getType();

    public abstract PacketChannel getChannel();

    public CustomNpcsPermissions.Permission getPermission() {
        return null;
    }

    public boolean needsNPC() {
        return false;
    }

    public void setNPC(EntityNPCInterface npc) {
        this.npc = npc;
    }

    public abstract void sendData(ByteBuf var1) throws IOException;

    public abstract void receiveData(ByteBuf var1, EntityPlayer var2) throws IOException;
}

