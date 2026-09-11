/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumConfigOperation;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.config.ConfigClient;

public final class ConfigCommandPacket
extends AbstractPacket {
    public static final String packetName = "Data|ConfigCommand";
    private EnumConfigOperation configOperation;
    private Object[] objects;

    public ConfigCommandPacket() {
    }

    public ConfigCommandPacket(EnumConfigOperation operation, Object ... obs) {
        this.configOperation = operation;
        this.objects = obs;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.CONFIG_COMMAND;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.configOperation.ordinal());
        ByteBufUtils.fillBuffer(out, this.objects);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        EnumConfigOperation configType = EnumConfigOperation.values()[in.readInt()];
        if (configType == EnumConfigOperation.FONT) {
            String font = ByteBufUtils.readString(in);
            if (font == null) {
                return;
            }
            int size = in.readInt();
            if (!font.isEmpty()) {
                ConfigClient.FontType = font;
                ConfigClient.FontSize = size;
                ClientProxy.Font = new ClientProxy.FontContainer(ConfigClient.FontType, ConfigClient.FontSize);
                ConfigClient.FontTypeProperty.set(ConfigClient.FontType);
                ConfigClient.FontSizeProperty.set(ConfigClient.FontSize);
                if (ConfigClient.config.hasChanged()) {
                    ConfigClient.config.save();
                }
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("Font set to %s", new Object[]{ClientProxy.Font.getName()}));
            } else {
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("Current font is %s", new Object[]{ClientProxy.Font.getName()}));
            }
        }
    }
}

