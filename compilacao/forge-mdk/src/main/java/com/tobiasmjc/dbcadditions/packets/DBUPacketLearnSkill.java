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

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketLearnSkill
implements IMessage {
    int skillID;

    public DBUPacketLearnSkill() {
    }

    public DBUPacketLearnSkill(int i) {
        this.skillID = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.skillID);
    }

    public void fromBytes(ByteBuf buffer) {
        this.skillID = buffer.readInt();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketLearnSkill> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketLearnSkill packet) {
            int selected = packet.skillID;
            DBCAPlayer player = new DBCAPlayer((EntityPlayer)p);
            JGPlayerMP jgPlayer = new JGPlayerMP(p);
            jgPlayer.connectBaseNBT();
            DBCASkill skill = DBCASkills.getSkill(selected);
            int tp = JRMCoreH.getInt((EntityPlayer)p, "jrmcTpint");
            if (skill != null && skill.isEnabled() && skill.race(player.getRace(), (byte)player.DBARace) && tp >= skill.getTPCost() && !player.hasSkill(skill)) {
                player.learnSkill(skill);
                JRMCoreH.setInt(tp - skill.getTPCost(), (EntityPlayer)p, "jrmcTpint");
            }
        }
    }
}
