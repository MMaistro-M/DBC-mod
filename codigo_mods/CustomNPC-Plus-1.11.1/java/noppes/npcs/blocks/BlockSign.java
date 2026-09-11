/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileSign;
import noppes.npcs.blocks.tiles.TileVariant;

public class BlockSign
extends BlockRotated {
    public BlockSign() {
        super(Blocks.field_150344_f);
    }

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        TileSign tile = (TileSign)par1World.func_147438_o(par2, par3, par4);
        tile.rotation = l %= 4;
        tile.time = System.currentTimeMillis();
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
        if (par5EntityLivingBase instanceof EntityPlayer && par1World.field_72995_K) {
            ((EntityPlayer)par5EntityLivingBase).func_146105_b((IChatComponent)new ChatComponentTranslation("availability.editIcon", new Object[0]));
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileSign tile = (TileSign)world.func_147438_o(x, y, z);
        return tile.canEdit();
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        if (!(tileentity instanceof TileVariant)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileVariant tile = (TileVariant)tileentity;
        if (tile.rotation % 2 == 1) {
            this.func_149676_a(0.0f, 0.3f, 0.3f, 1.0f, 1.0f, 0.7f);
        } else {
            this.func_149676_a(0.3f, 0.3f, 0.0f, 0.7f, 1.0f, 1.0f);
        }
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileSign();
    }
}

