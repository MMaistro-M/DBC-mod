/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.tobiasmjc.dbcadditions.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUMessageHandler<T extends IMessage>
implements IMessageHandler<T, IMessage> {
    public void onClientSide(T message) {
    }

    public void onServerSide(EntityPlayerMP player, T message) {
    }

    public IMessage onMessage(T message, MessageContext ctx) {
        if (ctx.side.isClient()) {
            this.onClientSide(message);
        } else if (ctx.side.isServer()) {
            this.onServerSide(ctx.getServerHandler().field_147369_b, message);
        }
        return null;
    }
}

