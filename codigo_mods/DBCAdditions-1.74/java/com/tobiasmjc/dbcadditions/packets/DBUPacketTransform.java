/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.data.DBCAClientData;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketTransform
implements IMessage {
    int formID;

    public DBUPacketTransform() {
    }

    public DBUPacketTransform(int i) {
        this.formID = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.formID);
    }

    public void fromBytes(ByteBuf buffer) {
        this.formID = buffer.readInt();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketTransform> {
        @Override
        public void onServerSide(EntityPlayerMP player, DBUPacketTransform message) {
            DBCAPlayer dbaPlayer = new DBCAPlayer((EntityPlayer)player);
            dbaPlayer.descend();
        }

        @Override
        public void onClientSide(DBUPacketTransform message) {
            int form;
            DBCAClientData.currentDBAForm = form = message.formID;
        }
    }
}

