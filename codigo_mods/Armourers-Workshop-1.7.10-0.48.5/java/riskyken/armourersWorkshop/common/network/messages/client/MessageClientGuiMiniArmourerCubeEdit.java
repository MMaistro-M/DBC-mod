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
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.common.data.MiniCube;
import riskyken.armourersWorkshop.common.inventory.ContainerMiniArmourerBuilding;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class MessageClientGuiMiniArmourerCubeEdit
implements IMessage,
IMessageHandler<MessageClientGuiMiniArmourerCubeEdit, IMessage> {
    private ISkinPartType skinPartType;
    private MiniCube cube;
    private boolean remove;

    public MessageClientGuiMiniArmourerCubeEdit() {
    }

    public MessageClientGuiMiniArmourerCubeEdit(ISkinPartType skinPartType, MiniCube cube, boolean remove) {
        this.skinPartType = skinPartType;
        this.cube = cube;
        this.remove = remove;
    }

    public void fromBytes(ByteBuf buf) {
        String partName = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.skinPartType = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(partName);
        byte cubeId = buf.readByte();
        this.cube = new MiniCube(buf);
        this.remove = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.skinPartType.getRegistryName());
        this.cube.writeToBuf(buf);
        buf.writeBoolean(this.remove);
    }

    public IMessage onMessage(MessageClientGuiMiniArmourerCubeEdit message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerMiniArmourerBuilding) {
            ((ContainerMiniArmourerBuilding)container).updateFromClientCubeEdit(message.skinPartType, message.cube, message.remove);
        }
        return null;
    }
}

