/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.undo.UndoManager;

public class MessageClientKeyPress
implements IMessage,
IMessageHandler<MessageClientKeyPress, IMessage> {
    byte keyId;

    public MessageClientKeyPress() {
    }

    public MessageClientKeyPress(byte keyId) {
        this.keyId = keyId;
    }

    public void fromBytes(ByteBuf buf) {
        this.keyId = buf.readByte();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeByte((int)this.keyId);
    }

    public IMessage onMessage(MessageClientKeyPress message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        switch (message.keyId) {
            case 0: {
                FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)5, (World)player.field_70170_p, (int)0, (int)0, (int)0);
                break;
            }
            case 1: {
                UndoManager.undoPressed((EntityPlayer)player);
                break;
            }
        }
        return null;
    }
}

