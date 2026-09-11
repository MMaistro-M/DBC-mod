/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.items.ItemNpcTool;

public final class ColorBrushPacket
extends AbstractPacket {
    public static String packetName = "Request|ColorBrush";
    private int color;

    public ColorBrushPacket(int color) {
        this.color = color;
    }

    public ColorBrushPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ColorBrush;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.NPC_BUILD;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.color);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.BRUSH, player)) {
            return;
        }
        int color = in.readInt();
        ItemStack stack = player.func_70694_bm();
        ColorBrushPacket.setBrushColor(stack, color);
    }

    public static void setBrushColor(ItemStack brush, int color) {
        NBTTagCompound brushCompound = brush.func_77978_p();
        if (brushCompound == null) {
            brushCompound = new NBTTagCompound();
        }
        brushCompound.func_74768_a(ItemNpcTool.BRUSH_COLOR_TAG, color);
        brush.func_77982_d(brushCompound);
    }
}

