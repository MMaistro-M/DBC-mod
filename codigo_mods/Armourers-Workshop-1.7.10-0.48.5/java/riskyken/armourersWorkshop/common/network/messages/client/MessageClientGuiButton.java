/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.inventory.ContainerColourMixer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourMixer;
import riskyken.armourersWorkshop.utils.UtilColour;

public class MessageClientGuiButton
implements IMessage,
IMessageHandler<MessageClientGuiButton, IMessage> {
    byte buttonId;

    public MessageClientGuiButton() {
    }

    public MessageClientGuiButton(byte buttonId) {
        this.buttonId = buttonId;
    }

    public void fromBytes(ByteBuf buf) {
        this.buttonId = buf.readByte();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeByte((int)this.buttonId);
    }

    public IMessage onMessage(MessageClientGuiButton message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerArmourer) {
            TileEntityArmourer armourerBrain = ((ContainerArmourer)container).getTileEntity();
            if (message.buttonId == 14) {
                armourerBrain.loadArmourItem(player);
            }
            if (message.buttonId == 7) {
                armourerBrain.toggleGuides();
            }
            if (message.buttonId == 6) {
                armourerBrain.toggleHelper();
            }
            if (message.buttonId == 11) {
                // empty if block
            }
            if (message.buttonId == 12) {
                // empty if block
            }
        }
        if (container != null && container instanceof ContainerColourMixer) {
            TileEntityColourMixer colourMixer = ((ContainerColourMixer)container).getTileEntity();
            colourMixer.setColourFamily(UtilColour.ColourFamily.values()[message.buttonId]);
        }
        if (container instanceof IButtonPress) {
            ((IButtonPress)container).buttonPressed(message.buttonId);
        }
        return null;
    }

    public static interface IButtonPress {
        public void buttonPressed(byte var1);
    }
}

