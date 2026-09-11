/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  io.netty.channel.ChannelHandler$Sharable
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.SimpleChannelInboundHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.INetHandler
 *  net.minecraft.network.NetHandlerPlayServer
 */
package invtweaks.network.handlers;

import cpw.mods.fml.common.network.NetworkRegistry;
import invtweaks.network.packets.ITPacketClick;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

@ChannelHandler.Sharable
public class ClickMessageHandler
extends SimpleChannelInboundHandler<ITPacketClick> {
    protected void channelRead0(ChannelHandlerContext ctx, ITPacketClick msg) throws Exception {
        INetHandler handler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        if (handler instanceof NetHandlerPlayServer) {
            NetHandlerPlayServer serverHandler = (NetHandlerPlayServer)handler;
            EntityPlayerMP player = serverHandler.field_147369_b;
            player.field_71070_bA.func_75144_a(msg.slot, msg.data, msg.action, (EntityPlayer)player);
        }
    }
}

