/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileChair;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.entity.EntityChairMount;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.items.ItemNpcTool;

public class BlockChair
extends BlockRotated {
    public BlockChair() {
        super(Blocks.field_150344_f);
        this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
    }

    @Override
    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        super.func_149689_a(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
        par1World.func_72921_c(par2, par3, par4, par6ItemStack.func_77960_j(), 2);
    }

    @Override
    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te instanceof TileChair && ((TileChair)te).isPushed()) {
            int rotation = ((TileVariant)te).rotation;
            switch (rotation) {
                case 0: {
                    return AxisAlignedBB.func_72330_a((double)((double)x + 0.1), (double)y, (double)z, (double)((double)x + 0.9), (double)(y + 1), (double)((double)z + 0.3));
                }
                case 1: {
                    return AxisAlignedBB.func_72330_a((double)((double)x + 0.7), (double)y, (double)((double)z + 0.1), (double)((double)x + 1.0), (double)(y + 1), (double)((double)z + 0.9));
                }
                case 2: {
                    return AxisAlignedBB.func_72330_a((double)((double)x + 0.1), (double)y, (double)((double)z + 0.7), (double)((double)x + 0.9), (double)(y + 1), (double)((double)z + 1.0));
                }
                case 3: {
                    return AxisAlignedBB.func_72330_a((double)x, (double)y, (double)((double)z + 0.1), (double)((double)x + 0.3), (double)(y + 1), (double)((double)z + 0.9));
                }
            }
            return AxisAlignedBB.func_72330_a((double)((double)x + 0.1), (double)y, (double)((double)z + 0.1), (double)((double)x + 0.9), (double)(y + 1), (double)((double)z + 0.9));
        }
        return AxisAlignedBB.func_72330_a((double)((double)x + 0.1), (double)y, (double)((double)z + 0.1), (double)((double)x + 0.9), (double)((double)y + 0.5), (double)((double)z + 0.9));
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        TileEntity tileentity = world.func_147438_o(x, y, z);
        if (!(tileentity instanceof TileChair)) {
            super.func_149719_a(world, x, y, z);
            return;
        }
        TileChair tile = (TileChair)tileentity;
        if (tile.isPushed()) {
            switch (tile.rotation) {
                case 0: {
                    this.func_149676_a(0.1f, 0.0f, 0.0f, 0.9f, 1.0f, 0.3f);
                    break;
                }
                case 1: {
                    this.func_149676_a(0.7f, 0.0f, 0.1f, 1.0f, 1.0f, 0.9f);
                    break;
                }
                case 2: {
                    this.func_149676_a(0.1f, 0.0f, 0.7f, 0.9f, 1.0f, 1.0f);
                    break;
                }
                case 3: {
                    this.func_149676_a(0.0f, 0.0f, 0.1f, 0.3f, 1.0f, 0.9f);
                    break;
                }
                default: {
                    this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
                    break;
                }
            }
        } else {
            this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
        }
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

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileChair();
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        ItemStack item = player.field_71071_by.func_70448_g();
        if (item != null && item.func_77973_b() != null && item.func_77973_b() instanceof ItemNpcTool) {
            return false;
        }
        return BlockChair.MountBlock(world, x, y, z, player);
    }

    public static boolean MountBlock(World world, int x, int y, int z, EntityPlayer player) {
        if (world.field_72995_K) {
            return true;
        }
        if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ItemNpcTool) {
            return false;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te instanceof TileChair && ((TileChair)te).isPushed()) {
            return false;
        }
        List list = world.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)));
        for (Entity entity : list) {
            if (!(entity instanceof EntityChairMount) && !(entity instanceof EntityCustomNpc)) continue;
            return false;
        }
        EntityChairMount mount = new EntityChairMount(world);
        mount.func_70107_b((float)x + 0.5f, y, (float)z + 0.5f);
        world.func_72838_d((Entity)mount);
        player.func_70078_a((Entity)mount);
        return true;
    }
}

