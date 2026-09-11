/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 */
package invtweaks.network.packets;

import io.netty.buffer.ByteBuf;

public interface ITPacket {
    public void readBytes(ByteBuf var1);

    public void writeBytes(ByteBuf var1);
}

