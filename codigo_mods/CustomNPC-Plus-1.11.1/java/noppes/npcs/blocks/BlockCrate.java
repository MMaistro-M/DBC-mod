/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileCrate;
import noppes.npcs.blocks.tiles.TileNpcContainer;
import noppes.npcs.constants.EnumGuiType;

public class BlockCrate
extends BlockRotated {
    public BlockCrate() {
        super(Blocks.field_150344_f);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return true;
        }
        par1World.func_72908_a((double)i, (double)j + 0.5, (double)k, "random.chestopen", 0.5f, par1World.field_73012_v.nextFloat() * 0.1f + 0.9f);
        player.openGui((Object)CustomNpcs.instance, EnumGuiType.Crate.ordinal(), par1World, i, j, k);
        return true;
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

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        super.func_149689_a(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileCrate();
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileNpcContainer tile = (TileNpcContainer)world.func_147438_o(x, y, z);
        if (tile == null) {
            return;
        }
        tile.dropItems(world, x, y, z);
        world.func_147453_f(x, y, z, block);
        super.func_149749_a(world, x, y, z, block, p_149749_6_);
    }
}

