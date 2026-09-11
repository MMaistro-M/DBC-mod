/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.library.LibraryFile;

public class MessageClientGuiSkinLibraryCommand
implements IMessage,
IMessageHandler<MessageClientGuiSkinLibraryCommand, IMessage> {
    private SkinLibraryCommand command;
    private LibraryFile file;
    private boolean publicList;

    public void delete(LibraryFile file, boolean publicList) {
        this.publicList = publicList;
        this.command = SkinLibraryCommand.DELETE;
        this.file = file;
    }

    public void newFolder(LibraryFile folder, boolean publicList) {
        this.publicList = publicList;
        this.command = SkinLibraryCommand.NEW_FOLDER;
        this.file = folder;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeByte(this.command.ordinal());
        buf.writeBoolean(this.publicList);
        switch (this.command) {
            case DELETE: {
                this.file.writeToByteBuf(buf);
                break;
            }
            case NEW_FOLDER: {
                this.file.writeToByteBuf(buf);
            }
        }
    }

    public void fromBytes(ByteBuf buf) {
        this.command = SkinLibraryCommand.values()[buf.readByte()];
        this.publicList = buf.readBoolean();
        switch (this.command) {
            case DELETE: {
                this.file = LibraryFile.readFromByteBuf(buf);
                break;
            }
            case NEW_FOLDER: {
                this.file = LibraryFile.readFromByteBuf(buf);
            }
        }
    }

    public IMessage onMessage(MessageClientGuiSkinLibraryCommand message, MessageContext ctx) {
        ArmourersWorkshop.proxy.skinLibraryCommand(ctx.getServerHandler().field_147369_b, message.command, message.file, message.publicList);
        return null;
    }

    public static enum SkinLibraryCommand {
        DELETE,
        NEW_FOLDER;

    }
}

