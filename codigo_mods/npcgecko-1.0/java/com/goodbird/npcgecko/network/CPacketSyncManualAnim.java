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
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.goodbird.npcgecko.network;

import com.goodbird.npcgecko.entity.EntityCustomModel;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.Server;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.builder.RawAnimation;

public final class CPacketSyncManualAnim
implements IMessage,
IMessageHandler<CPacketSyncManualAnim, IMessage> {
    public AnimationBuilder builder;
    public int entityId;

    public CPacketSyncManualAnim() {
    }

    public CPacketSyncManualAnim(EntityNPCInterface npc, AnimationBuilder builder) {
        this.builder = builder;
        this.entityId = npc.func_145782_y();
    }

    public void toBytes(ByteBuf buf) {
        try {
            CPacketSyncManualAnim.writeAnimBuilder(buf, this.builder);
        }
        catch (Exception exception) {
            // empty catch block
        }
        buf.writeInt(this.entityId);
    }

    public void fromBytes(ByteBuf buf) {
        try {
            this.builder = CPacketSyncManualAnim.readAnimBuilder(buf);
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.entityId = buf.readInt();
    }

    public static void writeAnimBuilder(ByteBuf buffer, AnimationBuilder builder) throws IOException {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList animList = new NBTTagList();
        for (RawAnimation anim : builder.getRawAnimationList()) {
            NBTTagCompound animTag = new NBTTagCompound();
            animTag.func_74778_a("name", anim.animationName);
            if (anim.loopType != null) {
                animTag.func_74768_a("loop", ((ILoopType.EDefaultLoopTypes)anim.loopType).ordinal());
            } else {
                animTag.func_74768_a("loop", 1);
            }
            animList.func_74742_a((NBTBase)animTag);
        }
        compound.func_74782_a("anims", (NBTBase)animList);
        Server.writeNBT((ByteBuf)buffer, (NBTTagCompound)compound);
    }

    public static AnimationBuilder readAnimBuilder(ByteBuf buffer) throws IOException {
        AnimationBuilder builder = new AnimationBuilder();
        NBTTagCompound compound = Server.readNBT((ByteBuf)buffer);
        NBTTagList animList = compound.func_150295_c("anims", 10);
        for (int i = 0; i < animList.func_74745_c(); ++i) {
            NBTTagCompound animTag = animList.func_150305_b(i);
            builder.addAnimation(animTag.func_74779_i("name"), ILoopType.EDefaultLoopTypes.values()[animTag.func_74762_e("loop")]);
        }
        return builder;
    }

    public IMessage onMessage(CPacketSyncManualAnim message, MessageContext ctx) {
        Entity entity = Minecraft.func_71410_x().field_71441_e.func_73045_a(message.entityId);
        if (!(entity instanceof EntityCustomNpc)) {
            return null;
        }
        EntityCustomNpc npc = (EntityCustomNpc)entity;
        if (npc.modelData == null || !(npc.modelData.getEntity(npc) instanceof EntityCustomModel)) {
            return null;
        }
        EntityCustomModel entityCustomModel = (EntityCustomModel)npc.modelData.getEntity(npc);
        entityCustomModel.manualAnim = message.builder;
        return null;
    }
}

