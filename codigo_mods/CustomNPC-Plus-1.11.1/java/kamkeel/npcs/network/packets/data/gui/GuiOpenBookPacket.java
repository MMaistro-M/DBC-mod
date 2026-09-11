/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.player.GuiBook;

public final class GuiOpenBookPacket
extends AbstractPacket {
    public static final String packetName = "Data|GuiOpenBook";
    private int i;
    private int j;
    private int k;
    private NBTTagCompound nbtTagCompound;

    public GuiOpenBookPacket() {
    }

    public GuiOpenBookPacket(int i, int j, int k, NBTTagCompound nbtTagCompound) {
        this.i = i;
        this.j = j;
        this.k = k;
        this.nbtTagCompound = nbtTagCompound;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.GUI_BOOK;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.i);
        out.writeInt(this.j);
        out.writeInt(this.k);
        ByteBufUtils.writeNBT(out, this.nbtTagCompound);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        int x = in.readInt();
        int y = in.readInt();
        int z = in.readInt();
        NBTTagCompound nbt = ByteBufUtils.readNBT(in);
        ItemStack book = ItemStack.func_77949_a((NBTTagCompound)nbt);
        if (book != null) {
            NoppesUtil.openGUI(player, (Object)new GuiBook(player, book, x, y, z));
        }
    }
}

