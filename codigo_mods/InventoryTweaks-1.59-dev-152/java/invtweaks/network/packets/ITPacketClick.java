/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package invtweaks.network.packets;

import invtweaks.network.packets.ITPacket;
import io.netty.buffer.ByteBuf;

public class ITPacketClick
implements ITPacket {
    public int slot;
    public int data;
    public int action;

    public ITPacketClick() {
    }

    public ITPacketClick(int _slot, int _data, int _action) {
        this.slot = _slot;
        this.data = _data;
        this.action = _action;
    }

    @Override
    public void readBytes(ByteBuf bytes) {
        this.slot = bytes.readInt();
        this.data = bytes.readInt();
        this.action = bytes.readInt();
    }

    @Override
    public void writeBytes(ByteBuf bytes) {
        bytes.writeInt(this.slot);
        bytes.writeInt(this.data);
        bytes.writeInt(this.action);
    }
}

