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
            message.grabberName = p.getCommandSenderName();
            p.worldObj.playSoundAtEntity((Entity)p, "dbcadditions:bioandroid.absorb_start", 3.0f, p.worldObj.rand.nextFloat() * 0.1f + 0.9f);
            if (message.grabbedID == -1) {
                return;
            }
            boolean absorb = message.absorbing;
            EntityPlayer grabber = p;
            if (!absorb) {
                DBCAAbilities.stopAbsorbing(grabber);
                DBUPackets.sendToAll(message, p.worldObj);
                return;
            }
            Entity target = p.worldObj.getEntityByID(message.grabbedID);
            if (!(target instanceof EntityCreature) || p.getDistanceSqToEntity(target) > 36.0) {
                return;
            }
            DBCAAbilities.startAbsorbing(grabber, message.grabbedID);
            DBUPackets.sendToAll(message, p.worldObj);
        }

        @Override
        public void onClientSide(DBUPacketAbsorb message) {
            boolean absorb = message.absorbing;
            EntityPlayer grabber = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(message.grabberName);
            if (!absorb) {
                if (grabber == Minecraft.getMinecraft().thePlayer) {
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
