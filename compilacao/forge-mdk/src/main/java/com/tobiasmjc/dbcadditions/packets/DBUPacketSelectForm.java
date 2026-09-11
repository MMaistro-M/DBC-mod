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
import com.tobiasmjc.dbcadditions.data.forms.FormItem;
import com.tobiasmjc.dbcadditions.data.forms.FormItemsDBA;
import com.tobiasmjc.dbcadditions.packets.DBUMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DBUPacketSelectForm
implements IMessage {
    int selectionID;

    public DBUPacketSelectForm() {
    }

    public DBUPacketSelectForm(int i) {
        this.selectionID = i;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.selectionID);
    }

    public void fromBytes(ByteBuf buffer) {
        this.selectionID = buffer.readInt();
    }

    public static class Handler
    extends DBUMessageHandler<DBUPacketSelectForm> {
        @Override
        public void onServerSide(EntityPlayerMP p, DBUPacketSelectForm packet) {
            int selected = packet.selectionID;
            DBCAPlayer player = DBCAPlayer.get((EntityPlayer)p);
            FormItem form = FormItemsDBA.getFormItem(selected);
            if (selected != -1 && (form == null || !form.canTransform((EntityPlayer)p))) {
                return;
            }
            player.selectedForm = selected;
            player.saveNBTData();
        }
    }
}
