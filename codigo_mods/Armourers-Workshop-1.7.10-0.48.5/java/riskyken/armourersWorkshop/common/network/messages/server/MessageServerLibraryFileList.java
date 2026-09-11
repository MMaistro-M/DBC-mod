/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.inventory.Container
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.inventory.Container;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileType;
import riskyken.armourersWorkshop.utils.ModLogger;

public class MessageServerLibraryFileList
implements IMessage,
IMessageHandler<MessageServerLibraryFileList, IMessage> {
    ArrayList<LibraryFile> fileList;
    LibraryFileType listType;

    public MessageServerLibraryFileList() {
    }

    public MessageServerLibraryFileList(ArrayList<LibraryFile> fileList, LibraryFileType listType) {
        this.fileList = fileList;
        this.listType = listType;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.fileList.size());
        for (int i = 0; i < this.fileList.size(); ++i) {
            this.fileList.get(i).writeToByteBuf(buf);
        }
        buf.writeByte(this.listType.ordinal());
    }

    public void fromBytes(ByteBuf buf) {
        int size = buf.readInt();
        this.fileList = new ArrayList();
        for (int i = 0; i < size; ++i) {
            this.fileList.add(LibraryFile.readFromByteBuf(buf));
        }
        this.listType = LibraryFileType.values()[buf.readByte()];
    }

    public IMessage onMessage(MessageServerLibraryFileList message, MessageContext ctx) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        Container container = player.field_71070_bA;
        ModLogger.log("got file list type " + (Object)((Object)message.listType));
        ArmourersWorkshop.proxy.libraryManager.setFileList(message.fileList, message.listType);
        return null;
    }
}

