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
 *  net.minecraft.tileentity.TileEntity
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
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.tiles.TileColorable;
import noppes.npcs.items.ItemNpcTool;

public final class ColorSetPacket
extends AbstractPacket {
    public static String packetName = "Request|ColorSet";
    private int x;
    private int y;
    private int z;

    public ColorSetPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ColorSetPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ColorSet;
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
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeInt(this.z);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.BRUSH, player)) {
            return;
        }
        int x = in.readInt();
        int y = in.readInt();
        int z = in.readInt();
        TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
        ItemStack stack = player.func_70694_bm();
        if (tile instanceof TileColorable) {
            int color = ItemNpcTool.getColor(stack.func_77978_p());
            TileColorable colorable = (TileColorable)tile;
            colorable.setColor(color);
        }
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

