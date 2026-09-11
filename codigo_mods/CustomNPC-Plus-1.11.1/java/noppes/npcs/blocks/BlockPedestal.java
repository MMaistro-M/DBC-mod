/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockTrigger;
import noppes.npcs.blocks.tiles.TileNpcContainer;
import noppes.npcs.blocks.tiles.TilePedestal;
import noppes.npcs.blocks.tiles.TileVariant;

public class BlockPedestal
extends BlockTrigger {
    public BlockPedestal() {
        super(Blocks.field_150348_b);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (par1World.field_72995_K) {
            return true;
        }
        TilePedestal tile = (TilePedestal)par1World.func_147438_o(i, j, k);
        ItemStack item = player.func_71045_bC();
        ItemStack weapon = tile.func_70301_a(0);
        if (item == null && weapon != null) {
            tile.func_70299_a(0, null);
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, weapon);
            par1World.func_147471_g(i, j, k);
            this.updateSurrounding(par1World, i, j, k);
        } else {
            if (item == null || item.func_77973_b() == null || item.func_77973_b() instanceof ItemBlock) {
                return true;
            }
            if (item != null && weapon == null) {
                tile.func_70299_a(0, item);
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                par1World.func_147471_g(i, j, k);
                this.updateSurrounding(par1World, i, j, k);
            }
        }
        return true;
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
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
        if (tile.rotation % 2 == 0) {
            this.func_149676_a(0.0f, 0.0f, 0.2f, 1.0f, 0.5f, 0.8f);
        } else {
            this.func_149676_a(0.2f, 0.0f, 0.0f, 0.8f, 0.5f, 1.0f);
        }
    }

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        super.func_149689_a(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int meta) {
        if ((meta %= 7) == 1) {
            return Blocks.field_150348_b.func_149691_a(p_149691_1_, 0);
        }
        if (meta == 2) {
            return Blocks.field_150339_S.func_149691_a(p_149691_1_, 0);
        }
        if (meta == 3) {
            return Blocks.field_150340_R.func_149691_a(p_149691_1_, 0);
        }
        if (meta == 4) {
            return Blocks.field_150484_ah.func_149691_a(p_149691_1_, 0);
        }
        return Blocks.field_150344_f.func_149691_a(p_149691_1_, 0);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TilePedestal();
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

