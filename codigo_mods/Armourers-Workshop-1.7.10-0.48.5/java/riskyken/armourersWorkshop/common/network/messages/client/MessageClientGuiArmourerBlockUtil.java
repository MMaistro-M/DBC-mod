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
import riskyken.armourersWorkshop.common.inventory.ContainerArmourer;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

public class MessageClientGuiArmourerBlockUtil
implements IMessage,
IMessageHandler<MessageClientGuiArmourerBlockUtil, IMessage> {
    private String utilType;
    private ISkinPartType partType1;
    private ISkinPartType partType2;
    private boolean option1;
    private boolean option2;
    private boolean option3;

    public MessageClientGuiArmourerBlockUtil() {
    }

    public MessageClientGuiArmourerBlockUtil(String utilType, ISkinPartType part1, ISkinPartType part2, boolean option1, boolean option2, boolean option3) {
        this.utilType = utilType;
        this.partType1 = part1;
        this.partType2 = part2;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.utilType);
        buf.writeBoolean(this.partType1 != null);
        if (this.partType1 != null) {
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.partType1.getRegistryName());
        }
        buf.writeBoolean(this.partType2 != null);
        if (this.partType2 != null) {
            ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.partType2.getRegistryName());
        }
        buf.writeBoolean(this.option1);
        buf.writeBoolean(this.option2);
        buf.writeBoolean(this.option3);
    }

    public void fromBytes(ByteBuf buf) {
        String registryName;
        this.utilType = ByteBufUtils.readUTF8String((ByteBuf)buf);
        if (buf.readBoolean()) {
            registryName = ByteBufUtils.readUTF8String((ByteBuf)buf);
            this.partType1 = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(registryName);
        }
        if (buf.readBoolean()) {
            registryName = ByteBufUtils.readUTF8String((ByteBuf)buf);
            this.partType2 = SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(registryName);
        }
        this.option1 = buf.readBoolean();
        this.option2 = buf.readBoolean();
        this.option3 = buf.readBoolean();
    }

    public IMessage onMessage(MessageClientGuiArmourerBlockUtil message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().field_147369_b;
        if (player == null) {
            return null;
        }
        Container container = player.field_71070_bA;
        if (container != null && container instanceof ContainerArmourer) {
            TileEntityArmourer armourerBrain = ((ContainerArmourer)container).getTileEntity();
            boolean clearBlocks = message.option1;
            boolean clearPaint = message.option2;
            boolean clearMarkers = message.option3;
            if (message.utilType.equals("clear")) {
                if (clearBlocks) {
                    armourerBrain.clearArmourCubes(message.partType1);
                }
                if (clearPaint) {
                    armourerBrain.clearPaintData(true);
                }
                if (clearMarkers && !clearBlocks) {
                    armourerBrain.clearMarkers(message.partType1);
                }
            }
            if (message.utilType.equals("copy")) {
                armourerBrain.copySkinCubes(player, message.partType1, message.partType2, message.option1);
            }
        }
        return null;
    }
}

