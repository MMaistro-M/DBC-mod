/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class MyWoodStairs
extends BlockStairs {
    protected MyWoodStairs(Block sakuraStairs, int meta) {
        super(ModdedBlocks.SakuraPlank, meta);
        this.field_149783_u = true;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 40;
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }
}

