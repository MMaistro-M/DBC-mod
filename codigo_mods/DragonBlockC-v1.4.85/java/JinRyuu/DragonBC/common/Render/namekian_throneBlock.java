/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Render.block01dbc;
import JinRyuu.DragonBC.common.Render.namekian_throneTileEntity;
import JinRyuu.JRMCore.JRMCoreH;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class namekian_throneBlock
extends block01dbc {
    private Class TileEntity = namekian_throneTileEntity.class;

    public namekian_throneBlock() {
        super(Material.field_151576_e);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 0, 2);
        }
        if (l == 1) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 1, 2);
        }
        if (l == 2) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }
        if (l == 3) {
            p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":" + this.func_149739_a());
    }

    public int idDropped(int i, Random random, int j) {
        return 0;
    }

    public int quanityDropped(Random random) {
        return 0;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
    }

    public int func_149738_a(World par1World) {
        return 1;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
        this.sitOnBlock(world, x, (double)y + 0.99, z, player);
        return true;
    }

    public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        int l = par1World.func_72805_g(par2, par3, par4);
        this.func_149676_a(-0.5f, 0.0f, -0.5f, 1.5f, 0.8f, 1.5f);
        super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    @Override
    public TileEntity func_149915_a(World world, int i) {
        return new namekian_throneTileEntity();
    }
}

