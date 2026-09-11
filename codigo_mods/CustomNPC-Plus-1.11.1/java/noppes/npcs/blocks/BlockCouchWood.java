/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockChair;
import noppes.npcs.blocks.tiles.TileCouchWood;

public class BlockCouchWood
extends BlockContainer {
    public int renderId = -1;

    public BlockCouchWood() {
        super(Material.field_151575_d);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack item = player.field_71071_by.func_70448_g();
        if (item == null) {
            return BlockChair.MountBlock(par1World, i, j, k, player);
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
        return par1;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int x, int y, int z) {
        return AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)((double)y + 0.5), (double)(z + 1));
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        TileCouchWood tile = (TileCouchWood)par1World.func_147438_o(par2, par3, par4);
        tile.rotation = l %= 4;
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
        this.updateModel(par1World, par2, par3, par4, tile);
        this.func_149695_a(par1World, par2 + 1, par3, par4, (Block)this);
        this.func_149695_a(par1World, par2 - 1, par3, par4, (Block)this);
        this.func_149695_a(par1World, par2, par3, par4 + 1, (Block)this);
        this.func_149695_a(par1World, par2, par3, par4 - 1, (Block)this);
        this.updateModel(par1World, par2, par3, par4, tile);
        par1World.func_147471_g(par2, par3, par4);
    }

    public void func_149695_a(World worldObj, int x, int y, int z, Block block) {
        if (worldObj.field_72995_K || block != this) {
            return;
        }
        TileEntity tile = worldObj.func_147438_o(x, y, z);
        if (tile == null || !(tile instanceof TileCouchWood)) {
            return;
        }
        this.updateModel(worldObj, x, y, z, (TileCouchWood)tile);
        worldObj.func_147471_g(x, y, z);
    }

    private void updateModel(World world, int x, int y, int z, TileCouchWood tile) {
        if (world.field_72995_K) {
            return;
        }
        int meta = tile.func_145832_p();
        if (tile.rotation == 0) {
            tile.hasLeft = this.compareTiles(tile, x - 1, y, z, world, meta);
            tile.hasRight = this.compareTiles(tile, x + 1, y, z, world, meta);
        } else if (tile.rotation == 2) {
            tile.hasLeft = this.compareTiles(tile, x + 1, y, z, world, meta);
            tile.hasRight = this.compareTiles(tile, x - 1, y, z, world, meta);
        } else if (tile.rotation == 1) {
            tile.hasLeft = this.compareTiles(tile, x, y, z - 1, world, meta);
            tile.hasRight = this.compareTiles(tile, x, y, z + 1, world, meta);
        } else if (tile.rotation == 3) {
            tile.hasLeft = this.compareTiles(tile, x, y, z + 1, world, meta);
            tile.hasRight = this.compareTiles(tile, x, y, z - 1, world, meta);
        }
    }

    private boolean compareTiles(TileCouchWood tile, int x, int y, int z, World world, int meta) {
        int meta2 = world.func_72805_g(x, y, z);
        if (meta2 != meta) {
            return false;
        }
        TileEntity tile2 = world.func_147438_o(x, y, z);
        if (tile2 == null || !(tile2 instanceof TileCouchWood)) {
            return false;
        }
        TileCouchWood couch = (TileCouchWood)tile2;
        return tile.rotation == couch.rotation;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return this.renderId;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int meta) {
        return Blocks.field_150344_f.func_149691_a(p_149691_1_, meta);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileCouchWood();
    }
}

