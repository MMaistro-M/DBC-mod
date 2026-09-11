/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import riskyken.armourersWorkshop.common.inventory.ContainerOutfitMaker;
import riskyken.armourersWorkshop.common.tileentities.TileEntityOutfitMaker;

public class MessageClientGuiOutfitMakerUpdate
implements IMessage {
    private String name;
    private String flavour;

    public MessageClientGuiOutfitMakerUpdate() {
    }

    public MessageClientGuiOutfitMakerUpdate(String name, String flavour) {
        this.name = name;
        this.flavour = flavour;
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.name);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.flavour);
    }

    public void fromBytes(ByteBuf buf) {
        this.name = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.flavour = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public static class Handler
    implements IMessageHandler<MessageClientGuiOutfitMakerUpdate, IMessage> {
        public IMessage onMessage(MessageClientGuiOutfitMakerUpdate message, MessageContext ctx) {
            this.updateTile((EntityPlayer)ctx.getServerHandler().field_147369_b, message.name, message.flavour);
            return null;
        }

        private void updateTile(EntityPlayer player, String name, String flavour) {
            if (player.field_71070_bA != null && player.field_71070_bA instanceof ContainerOutfitMaker) {
                TileEntityOutfitMaker tileEntity = (TileEntityOutfitMaker)((Object)((ContainerOutfitMaker)player.field_71070_bA).getTileEntity());
                tileEntity.setOutfitName(name);
                tileEntity.setOutfitFlavour(flavour);
            }
        }
    }
}

