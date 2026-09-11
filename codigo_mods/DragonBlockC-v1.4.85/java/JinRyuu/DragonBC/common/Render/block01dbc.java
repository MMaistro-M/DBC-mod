/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Render.block01dbcEntity;
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class block01dbc
extends BlockContainer {
    public block01dbc(Material par2Material) {
        super(par2Material);
    }

    public boolean sitOnBlock(World par1World, double par2, double par3, double par4, EntityPlayer par5EntityPlayer) {
        this.checkForExistingEntity(par1World, par2, par3, par4, par5EntityPlayer);
        block01dbcEntity nemb = new block01dbcEntity(par1World, par2, par3, par4);
        par1World.func_72838_d((Entity)nemb);
        par5EntityPlayer.func_70078_a((Entity)nemb);
        return true;
    }

    public boolean checkForExistingEntity(World par1World, double par2, double par3, double par4, EntityPlayer par5EntityPlayer) {
        List listEMB = par1World.func_72872_a(block01dbcEntity.class, AxisAlignedBB.func_72330_a((double)par2, (double)par3, (double)par4, (double)(par2 + 1.0), (double)(par3 + 1.0), (double)(par4 + 1.0)).func_72314_b(1.0, 1.0, 1.0));
        for (block01dbcEntity mount : listEMB) {
            if ((double)mount.getX() != par2 || (double)mount.getY() != par3 || (double)mount.getZ() != par4) continue;
            if (mount.field_70153_n == null) {
                par5EntityPlayer.func_70078_a((Entity)mount);
            }
            return true;
        }
        return false;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}

