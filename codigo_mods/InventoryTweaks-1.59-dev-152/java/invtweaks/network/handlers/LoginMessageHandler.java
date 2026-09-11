/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandler$Sharable
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.SimpleChannelInboundHandler
 */
package invtweaks.network.handlers;

import invtweaks.forge.InvTweaksMod;
import invtweaks.network.packets.ITPacketLogin;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LoginMessageHandler
extends SimpleChannelInboundHandler<ITPacketLogin> {
    protected void channelRead0(ChannelHandlerContext ctx, ITPacketLogin msg) throws Exception {
        if (msg.protocolVersion == 1) {
            InvTweaksMod.proxy.setServerHasInvTweaks(true);
        }
    }
}

