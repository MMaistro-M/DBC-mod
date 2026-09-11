/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketOpenGui
implements IMessage {
    int id;

    public DBUPacketOpenGui() {
    }

    public DBUPacketOpenGui(int i) {
        this.id = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.id);
    }

    public void fromBytes(ByteBuf buffer) {
        this.id = buffer.readInt();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketOpenGui> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketOpenGui message) {
            p.openGui((Object)"dbcadditions", message.id, p.field_70170_p, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v);
        }
    }
}

