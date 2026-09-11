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

import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketRemoveSkill
implements IMessage {
    int skillID;

    public DBUPacketRemoveSkill() {
    }

    public DBUPacketRemoveSkill(int i) {
        this.skillID = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.skillID);
    }

    public void fromBytes(ByteBuf buffer) {
        this.skillID = buffer.readInt();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketRemoveSkill> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketRemoveSkill packet) {
            int selected = packet.skillID;
            DBCAPlayer player = new DBCAPlayer((EntityPlayer)p);
            DBCASkill skill = DBCASkills.getSkill(selected);
            if (skill != null) {
                player.removeSkill(skill);
            }
        }
    }
}
