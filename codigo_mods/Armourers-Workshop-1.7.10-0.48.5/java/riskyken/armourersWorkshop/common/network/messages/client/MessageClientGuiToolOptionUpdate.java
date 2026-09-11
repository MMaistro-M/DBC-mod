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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.network.messages.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;

public class MessageClientGuiToolOptionUpdate
implements IMessage,
IMessageHandler<MessageClientGuiToolOptionUpdate, IMessage> {
    private NBTTagCompound toolOptions;

    public MessageClientGuiToolOptionUpdate() {
    }

    public MessageClientGuiToolOptionUpdate(NBTTagCompound toolOptions) {
        this.toolOptions = toolOptions;
    }

    public void fromBytes(ByteBuf buf) {
        this.toolOptions = ByteBufUtils.readTag((ByteBuf)buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag((ByteBuf)buf, (NBTTagCompound)this.toolOptions);
    }

    public IMessage onMessage(MessageClientGuiToolOptionUpdate message, MessageContext ctx) {
        ItemStack stack;
        Item item;
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player != null && (item = (stack = player.func_71045_bC()).func_77973_b()) instanceof IConfigurableTool) {
            NBTTagCompound newOptions = message.toolOptions;
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound stackCompound = stack.func_77978_p();
            Set keySet = newOptions.func_150296_c();
            for (String key : keySet) {
                if (stackCompound.func_74764_b(key)) {
                    stackCompound.func_82580_o(key);
                }
                stackCompound.func_74782_a(key, newOptions.func_74781_a(key));
            }
        }
        return null;
    }
}

