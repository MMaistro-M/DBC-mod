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
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockTrigger;
import noppes.npcs.blocks.tiles.TileNpcContainer;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.blocks.tiles.TileWeaponRack;

public class BlockWeaponRack
extends BlockTrigger {
    public BlockWeaponRack() {
        super(Blocks.field_150344_f);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (par1World.field_72995_K) {
            return true;
        }
        int meta = par1World.func_72805_g(i, j, k);
        if (meta >= 7) {
            --j;
        }
        TileWeaponRack tile = (TileWeaponRack)par1World.func_147438_o(i, j, k);
        float hit = hitX;
        if (tile.rotation == 2) {
            hit = 1.0f - hitX;
        }
        if (tile.rotation == 3) {
            hit = 1.0f - hitZ;
        }
        if (tile.rotation == 1) {
            hit = hitZ;
        }
        int selected = 2 - (int)((double)hit / 0.34);
        ItemStack item = player.func_71045_bC();
        ItemStack weapon = tile.func_70301_a(selected);
        if (item == null && weapon != null) {
            tile.func_70299_a(selected, null);
            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, weapon);
            par1World.func_147471_g(i, j, k);
            this.updateSurrounding(par1World, i, j, k);
        } else {
            if (item == null || item.func_77973_b() == null || item.func_77973_b() instanceof ItemBlock) {
                return true;
            }
            if (item != null && weapon == null) {
                tile.func_70299_a(selected, item);
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
        par3List.add(new ItemStack(par1, 1, 5));
    }

    public int func_149692_a(int par1) {
        return par1 % 7;
    }

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        if (!par1World.func_147437_c(par2, par3 + 1, par4)) {
            par1World.func_147468_f(par2, par3, par4);
        } else {
            int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
            TileVariant tile = (TileVariant)par1World.func_147438_o(par2, par3, par4);
            tile.rotation = l %= 4;
            par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
            par1World.func_147465_d(par2, par3 + 1, par4, (Block)this, par6ItemStack.func_77960_j() + 7, 2);
        }
    }

    @Override
    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity;
        int meta = world.func_72805_g(x, y, z);
        if (meta >= 7) {
            --y;
        }
        if (!((tileentity = world.func_147438_o(x, y, z)) instanceof TileVariant)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileVariant tile = (TileVariant)tileentity;
        float xStart = 0.0f;
        float zStart = 0.0f;
        float xEnd = 1.0f;
        float zEnd = 1.0f;
        if (tile.rotation == 0) {
            zStart = 0.7f;
        } else if (tile.rotation == 2) {
            zEnd = 0.3f;
        } else if (tile.rotation == 3) {
            xStart = 0.7f;
        } else if (tile.rotation == 1) {
            xEnd = 0.3f;
        }
        if (meta >= 7) {
            this.func_149676_a(xStart, -1.0f, zStart, xEnd, 0.8f, zEnd);
        } else {
            this.func_149676_a(xStart, 0.0f, zStart, xEnd, 1.8f, zEnd);
        }
    }

    public TileEntity func_149915_a(World var1, int var2) {
        if (var2 < 7) {
            return new TileWeaponRack();
        }
        return null;
    }

    public void func_149681_a(World p_149681_1_, int p_149681_2_, int p_149681_3_, int p_149681_4_, int p_149681_5_, EntityPlayer p_149681_6_) {
        if (p_149681_5_ >= 7 && p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_ - 1, p_149681_4_) == this) {
            p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_ - 1, p_149681_4_);
        } else if (p_149681_5_ < 7 && p_149681_1_.func_147439_a(p_149681_2_, p_149681_3_ + 1, p_149681_4_) == this) {
            p_149681_1_.func_147468_f(p_149681_2_, p_149681_3_ + 1, p_149681_4_);
        }
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

