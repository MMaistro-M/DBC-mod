/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileShelf;
import noppes.npcs.blocks.tiles.TileVariant;

public class BlockShelf
extends BlockRotated {
    public BlockShelf() {
        super(Blocks.field_150344_f);
    }

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        super.func_149689_a(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
    }

    @Override
    public AxisAlignedBB func_149668_a(World p_149668_1_, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)p_149668_1_, x, y, z);
        return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B), (double)((float)y + 0.9f), (double)((double)z + this.field_149754_D), (double)((double)x + this.field_149755_E), (double)(y + 1), (double)((double)z + this.field_149757_G));
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
        float xStart = 0.0f;
        float zStart = 0.0f;
        float xEnd = 1.0f;
        float zEnd = 1.0f;
        if (tile.rotation == 0) {
            zStart = 0.3f;
        } else if (tile.rotation == 2) {
            zEnd = 0.7f;
        } else if (tile.rotation == 3) {
            xStart = 0.3f;
        } else if (tile.rotation == 1) {
            xEnd = 0.7f;
        }
        this.func_149676_a(xStart, 0.44f, zStart, xEnd, 1.0f, zEnd);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileShelf();
    }
}

