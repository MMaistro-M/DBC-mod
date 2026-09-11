/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileVariant;

public abstract class BlockTrigger
extends BlockRotated {
    protected BlockTrigger(Block block) {
        super(block);
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149748_c(IBlockAccess world, int x, int y, int z, int p_149748_5_) {
        return this.func_149709_b(world, x, y, z, p_149748_5_);
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int p_149709_5_) {
        TileVariant tile = (TileVariant)world.func_147438_o(x, y, z);
        if (tile != null) {
            return tile.powerProvided();
        }
        return 0;
    }

    public void updateSurrounding(World par1World, int par2, int par3, int par4) {
        par1World.func_147459_d(par2, par3, par4, (Block)this);
        par1World.func_147459_d(par2, par3 - 1, par4, (Block)this);
        par1World.func_147459_d(par2, par3 + 1, par4, (Block)this);
        par1World.func_147459_d(par2 - 1, par3, par4, (Block)this);
        par1World.func_147459_d(par2 + 1, par3, par4, (Block)this);
        par1World.func_147459_d(par2, par3, par4 - 1, (Block)this);
        par1World.func_147459_d(par2, par3, par4 + 1, (Block)this);
    }
}

