/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.inventory.ContainerMiniArmourerBuilding;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;

public class MessageClientGuiSetArmourerSkinType
implements IMessage,
IMessageHandler<MessageClientGuiSetArmourerSkinType, IMessage> {
    private ISkinType skinType = null;

    public MessageClientGuiSetArmourerSkinType() {
    }

    public MessageClientGuiSetArmourerSkinType(ISkinType skinType) {
        this.skinType = skinType;
    }

    public void fromBytes(ByteBuf buf) {
        String registryName = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(registryName);
    }

    public void toBytes(ByteBuf buf) {
        String registryName = "";
        if (this.skinType != null) {
            registryName = this.skinType.getRegistryName();
        }
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)registryName);
    }

    public IMessage onMessage(MessageClientGuiSetArmourerSkinType message, MessageContext ctx) {
        AbstractTileEntityInventory te;
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerArmourer) {
            te = ((ContainerArmourer)container).getTileEntity();
            ((TileEntityArmourer)te).setSkinType(message.skinType);
        }
        if (container != null && container instanceof ContainerMiniArmourerBuilding) {
            te = ((ContainerMiniArmourerBuilding)container).getTileEntity();
            ((TileEntityMiniArmourer)te).setSkinType(message.skinType);
        }
        return null;
    }
}

