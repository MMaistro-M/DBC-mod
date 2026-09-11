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
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkill;
import com.tobiasmjc.dbcadditions.data.skills.DBCASkills;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketUpgradeSkill
implements IMessage {
    int skillID;

    public DBUPacketUpgradeSkill() {
    }

    public DBUPacketUpgradeSkill(int i) {
        this.skillID = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.skillID);
    }

    public void fromBytes(ByteBuf buffer) {
        this.skillID = buffer.readInt();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketUpgradeSkill> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketUpgradeSkill packet) {
            int selected = packet.skillID;
            DBCAPlayer player = DBCAPlayer.get((EntityPlayer)p);
            DBCASkill skill = DBCASkills.getPlayerSkill(player.Skills, selected);
            int tp = JRMCoreH.getInt((EntityPlayer)p, "jrmcTpint");
            if (skill != null && skill.isEnabled() && skill.race(player.getRace(), (byte)player.DBARace) && skill.getLevel() < skill.getMaxLevel() && tp >= skill.getTPCost() * (skill.getLevel() + 1)) {
                player.increaseSkillLevel(skill);
                JRMCoreH.setInt(tp - skill.getTPCost() * (skill.getLevel() + 1), (EntityPlayer)p, "jrmcTpint");
            }
        }
    }
}
