/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 */
package com.goodbird.npcgecko.network;

import com.goodbird.npcgecko.constants.EnumSyncAutoAnim;
import com.goodbird.npcgecko.entity.EntityCustomModel;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

public final class CPacketSyncAutoAnim
implements IMessage,
IMessageHandler<CPacketSyncAutoAnim, IMessage> {
    public EnumSyncAutoAnim animType;
    public int entityId;

    public CPacketSyncAutoAnim() {
    }

    public CPacketSyncAutoAnim(EntityNPCInterface npc, EnumSyncAutoAnim animType) {
        this.animType = animType;
        this.entityId = npc.func_145782_y();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.animType.ordinal());
        buf.writeInt(this.entityId);
    }

    public void fromBytes(ByteBuf buf) {
        this.animType = EnumSyncAutoAnim.values()[buf.readInt()];
        this.entityId = buf.readInt();
    }

    public IMessage onMessage(CPacketSyncAutoAnim message, MessageContext ctx) {
        Entity entity = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.entityId);
        if (!(entity instanceof EntityCustomNpc)) {
            return null;
        }
        EntityCustomNpc npc = (EntityCustomNpc)entity;
        if (npc.modelData == null || !(npc.modelData.getEntity(npc) instanceof EntityCustomModel)) {
            return null;
        }
        EntityCustomModel entityCustomModel = (EntityCustomModel)npc.modelData.getEntity(npc);
        entityCustomModel.activateReceivedAnim(message.animType);
        return null;
    }
}

