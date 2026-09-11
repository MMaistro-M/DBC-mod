/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package invtweaks.network.packets;

import invtweaks.network.packets.ITPacket;
import io.netty.buffer.ByteBuf;

public class ITPacketLogin
implements ITPacket {
    public byte protocolVersion = 1;

    @Override
    public void readBytes(ByteBuf bytes) {
        this.protocolVersion = bytes.readByte();
    }

    @Override
    public void writeBytes(ByteBuf bytes) {
        bytes.writeByte((int)this.protocolVersion);
    }
}

