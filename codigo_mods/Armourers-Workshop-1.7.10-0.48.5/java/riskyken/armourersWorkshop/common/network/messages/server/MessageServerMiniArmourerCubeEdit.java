/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.gui.miniarmourer.GuiMiniArmourerBuilding;
import riskyken.armourersWorkshop.common.data.MiniCube;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class MessageServerMiniArmourerCubeEdit
implements IMessage,
IMessageHandler<MessageServerMiniArmourerCubeEdit, IMessage> {
    private ISkinPartType skinPartType;
    private MiniCube cube;
    private boolean remove;

    public MessageServerMiniArmourerCubeEdit() {
    }

    public MessageServerMiniArmourerCubeEdit(ISkinPartType skinPartType, MiniCube cube, boolean remove) {
        this.skinPartType = skinPartType;
        this.cube = cube;
        this.remove = remove;
    }

    public void fromBytes(ByteBuf buf) {
        String partName = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.skinPartType = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(partName);
        byte cubeId = buf.readByte();
        this.remove = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.skinPartType.getRegistryName());
        buf.writeBoolean(this.remove);
    }

    public IMessage onMessage(MessageServerMiniArmourerCubeEdit message, MessageContext ctx) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        Minecraft mc = Minecraft.func_71410_x();
        if (player == null) {
            return null;
        }
        GuiScreen screen = mc.field_71462_r;
        if (screen != null && screen instanceof GuiMiniArmourerBuilding) {
            ((GuiMiniArmourerBuilding)screen).tileEntity.cubeUpdateFromServer(message.skinPartType, message.cube, message.remove);
        }
        return null;
    }
}

