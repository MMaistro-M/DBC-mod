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
import riskyken.armourersWorkshop.ArmourersWorkshop;

public class MessageClientGuiAdminPanel
implements IMessage,
IMessageHandler<MessageClientGuiAdminPanel, IMessage> {
    private AdminPanelCommand command;

    public MessageClientGuiAdminPanel() {
    }

    public MessageClientGuiAdminPanel(AdminPanelCommand command) {
        this.command = command;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.command.ordinal());
    }

    public void fromBytes(ByteBuf buf) {
        this.command = AdminPanelCommand.values()[buf.readInt()];
    }

    public IMessage onMessage(MessageClientGuiAdminPanel message, MessageContext ctx) {
        ArmourersWorkshop.proxy.receivedAdminPanelCommand((EntityPlayer)ctx.getServerHandler().field_147369_b, message.command);
        return null;
    }

    public static enum AdminPanelCommand {
        RECOVER_SKINS,
        RELOAD_LIBRARY,
        UPDATE_SKINS;

    }
}

