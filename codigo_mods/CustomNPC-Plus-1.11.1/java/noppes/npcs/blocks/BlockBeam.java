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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileBeam;
import noppes.npcs.blocks.tiles.TileVariant;

public class BlockBeam
extends BlockRotated {
    public BlockBeam() {
        super(Blocks.field_150344_f);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        if (!(tileentity instanceof TileVariant)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileVariant tile = (TileVariant)tileentity;
        if (tile.rotation == 0) {
            this.func_149676_a(0.33f, 0.33f, 0.25f, 0.67f, 0.67f, 1.0f);
        } else if (tile.rotation == 2) {
            this.func_149676_a(0.33f, 0.33f, 0.0f, 0.67f, 0.67f, 0.75f);
        } else if (tile.rotation == 3) {
            this.func_149676_a(0.25f, 0.33f, 0.33f, 1.0f, 0.67f, 0.67f);
        } else if (tile.rotation == 1) {
            this.func_149676_a(0.0f, 0.33f, 0.33f, 0.75f, 0.67f, 0.67f);
        }
    }

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        super.func_149689_a(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileBeam();
    }
}

