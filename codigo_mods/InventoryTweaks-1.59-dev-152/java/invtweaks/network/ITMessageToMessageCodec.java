/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 */
package invtweaks.network;

import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import invtweaks.network.packets.ITPacket;
import invtweaks.network.packets.ITPacketClick;
import invtweaks.network.packets.ITPacketLogin;
import invtweaks.network.packets.ITPacketSortComplete;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ITMessageToMessageCodec
extends FMLIndexedMessageToMessageCodec<ITPacket> {
    public ITMessageToMessageCodec() {
        this.addDiscriminator(0, ITPacketLogin.class);
        this.addDiscriminator(1, ITPacketClick.class);
        this.addDiscriminator(2, ITPacketSortComplete.class);
    }

    public void encodeInto(ChannelHandlerContext ctx, ITPacket source, ByteBuf target) throws Exception {
        source.writeBytes(target);
    }

    public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, ITPacket target) {
        target.readBytes(source);
    }
}

