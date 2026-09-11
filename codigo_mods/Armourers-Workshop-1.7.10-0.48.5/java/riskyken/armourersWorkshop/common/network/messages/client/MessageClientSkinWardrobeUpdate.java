/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

public class MessageClientSkinWardrobeUpdate
implements IMessage,
IMessageHandler<MessageClientSkinWardrobeUpdate, IMessage> {
    EquipmentWardrobeData equipmentWardrobeData;

    public MessageClientSkinWardrobeUpdate() {
        this.equipmentWardrobeData = new EquipmentWardrobeData();
    }

    public MessageClientSkinWardrobeUpdate(EquipmentWardrobeData equipmentWardrobeData) {
        this.equipmentWardrobeData = equipmentWardrobeData;
    }

    public void fromBytes(ByteBuf buf) {
        this.equipmentWardrobeData.fromBytes(buf);
    }

    public void toBytes(ByteBuf buf) {
        this.equipmentWardrobeData.toBytes(buf);
    }

    public IMessage onMessage(MessageClientSkinWardrobeUpdate message, MessageContext ctx) {
        ExPropsPlayerSkinData customEquipmentData = ExPropsPlayerSkinData.get((EntityPlayer)ctx.getServerHandler().field_147369_b);
        customEquipmentData.setSkinInfo(message.equipmentWardrobeData, true);
        return null;
    }
}

