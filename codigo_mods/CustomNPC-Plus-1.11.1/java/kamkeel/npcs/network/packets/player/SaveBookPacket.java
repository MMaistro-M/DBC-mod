/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemEditableBook
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemWritableBook
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 */
package kamkeel.npcs.network.packets.player;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import kamkeel.npcs.network.LargeAbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumPlayerPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.blocks.tiles.TileBook;

public class SaveBookPacket
extends LargeAbstractPacket {
    public static final String packetName = "Player|SaveBook";
    private int x;
    private int y;
    private int z;
    private boolean sign;
    private NBTTagCompound compound;

    public SaveBookPacket() {
    }

    public SaveBookPacket(int x, int y, int z, boolean sign, NBTTagCompound compound) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.sign = sign;
        this.compound = compound;
    }

    @Override
    protected byte[] getData() throws IOException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeBoolean(this.sign);
        ByteBufUtils.writeBigNBT(buffer, this.compound);
        return buffer.array();
    }

    @Override
    protected void handleCompleteData(ByteBuf data, EntityPlayer player) throws IOException {
        int z;
        int y;
        int x = data.readInt();
        if (player.field_70170_p.func_72899_e(x, y = data.readInt(), z = data.readInt())) {
            TileEntity tileentity = player.field_70170_p.func_147438_o(x, y, z);
            if (!(tileentity instanceof TileBook)) {
                return;
            }
            TileBook tile = (TileBook)tileentity;
            if (tile.book.func_77973_b() == Items.field_151164_bB) {
                return;
            }
            boolean sign = data.readBoolean();
            ItemStack book = ItemStack.func_77949_a((NBTTagCompound)ByteBufUtils.readBigNBT(data));
            if (book == null) {
                return;
            }
            if (book.func_77973_b() == Items.field_151099_bA && !sign && ItemWritableBook.func_150930_a((NBTTagCompound)book.func_77978_p())) {
                tile.book.func_77983_a("pages", (NBTBase)book.func_77978_p().func_150295_c("pages", 8));
            }
            if (book.func_77973_b() == Items.field_151164_bB && sign && ItemEditableBook.func_77828_a((NBTTagCompound)book.func_77978_p())) {
                tile.book.func_77983_a("author", (NBTBase)new NBTTagString(player.func_70005_c_()));
                tile.book.func_77983_a("title", (NBTBase)new NBTTagString(book.func_77978_p().func_74779_i("title")));
                tile.book.func_77983_a("pages", (NBTBase)book.func_77978_p().func_150295_c("pages", 8));
                tile.book.func_150996_a(Items.field_151164_bB);
            }
        }
    }

    @Override
    public Enum getType() {
        return EnumPlayerPacket.SaveBook;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.PLAYER_PACKET;
    }
}

