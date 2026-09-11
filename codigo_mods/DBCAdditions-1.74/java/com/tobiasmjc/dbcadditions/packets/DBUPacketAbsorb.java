/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package com.tobiasmjc.dbcadditions.packets;

import com.tobiasmjc.dbcadditions.data.DBCAClientData;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import com.tobiasmjc.dbcadditions.packets.DBUPackets;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketAbsorb
implements IMessage {
    String grabberName;
    int grabbedID;
    boolean absorbing;

    public DBUPacketAbsorb() {
    }

    public DBUPacketAbsorb(String grabber, int grabbed, boolean i) {
        this.grabberName = grabber;
        this.grabbedID = grabbed;
        this.absorbing = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeBoolean(this.absorbing);
        buffer.writeInt(this.grabbedID);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.grabberName);
    }

    public void fromBytes(ByteBuf buffer) {
        this.absorbing = buffer.readBoolean();
        this.grabbedID = buffer.readInt();
        this.grabberName = ByteBufUtils.readUTF8String((ByteBuf)buffer);
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketAbsorb> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketAbsorb message) {
            message.grabberName = p.func_70005_c_();
            p.field_70170_p.func_72956_a((Entity)p, "dbcadditions:bioandroid.absorb_start", 3.0f, p.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
            if (message.grabbedID == -1) {
                return;
            }
            boolean absorb = message.absorbing;
            EntityPlayer grabber = p;
            if (!absorb) {
                DBCAAbilities.stopAbsorbing(grabber);
                DBUPackets.sendToAll(message, p.field_70170_p);
                return;
            }
            Entity target = p.field_70170_p.func_73045_a(message.grabbedID);
            if (!(target instanceof EntityCreature) || p.func_70068_e(target) > 36.0) {
                return;
            }
            DBCAAbilities.startAbsorbing(grabber, message.grabbedID);
            DBUPackets.sendToAll(message, p.field_70170_p);
        }

        @Override
        public void onClientSide(DBUPacketAbsorb message) {
            boolean absorb = message.absorbing;
            EntityPlayer grabber = Minecraft.func_71410_x().field_71441_e.func_72924_a(message.grabberName);
            if (!absorb) {
                if (grabber == Minecraft.func_71410_x().field_71439_g) {
                    DBCAClientData.absorbCooldown = 1000;
                }
                if (grabber != null) {
                    DBCAAbilities.stopAbsorbing(grabber);
                }
                return;
            }
            DBCAAbilities.startAbsorbing(grabber, message.grabbedID);
        }
    }
}
