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
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.miniarmourer.GuiMiniArmourerBuilding;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;

public class MessageServerMiniArmourerSkinData
implements IMessage,
IMessageHandler<MessageServerMiniArmourerSkinData, IMessage> {
    private ArrayList<SkinPart> skinParts;

    public MessageServerMiniArmourerSkinData() {
    }

    public MessageServerMiniArmourerSkinData(ArrayList<SkinPart> skinParts) {
        this.skinParts = skinParts;
    }

    public void fromBytes(ByteBuf buf) {
        int size = buf.readByte();
        this.skinParts = new ArrayList();
        for (int i = 0; i < size; ++i) {
        }
    }

    public void toBytes(ByteBuf buf) {
        buf.writeByte(this.skinParts.size());
        for (int i = 0; i < this.skinParts.size(); ++i) {
        }
    }

    public IMessage onMessage(MessageServerMiniArmourerSkinData message, MessageContext ctx) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        Minecraft mc = Minecraft.func_71410_x();
        GuiScreen screen = mc.field_71462_r;
        if (screen != null && screen instanceof GuiMiniArmourerBuilding) {
            ((GuiMiniArmourerBuilding)screen).tileEntity.setSkinParts(message.skinParts);
        }
        return null;
    }
}

