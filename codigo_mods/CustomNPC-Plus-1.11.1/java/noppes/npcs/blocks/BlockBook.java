/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.gui.GuiOpenBookPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileBook;

public class BlockBook
extends BlockRotated {
    public BlockBook() {
        super(Blocks.field_150344_f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.2f, 1.0f);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return true;
        }
        TileEntity tile = par1World.func_147438_o(i, j, k);
        if (!(tile instanceof TileBook)) {
            return false;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.wand && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.EDIT_BOOK)) {
            ((TileBook)tile).book.func_150996_a(Items.field_151099_bA);
        }
        PacketHandler.Instance.sendToPlayer(new GuiOpenBookPacket(i, j, k, ((TileBook)tile).book.func_77955_b(new NBTTagCompound())), (EntityPlayerMP)player);
        return true;
    }

    public String func_149739_a() {
        return "item.book";
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileBook();
    }
}

