/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenDragonBallStone
extends WorldGenerator {
    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        int var9;
        int var8;
        int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
        if (par1World.func_147437_c(var7, var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4), var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8)) && par1World.func_147439_a(var7, var8 - 1, var9) == Blocks.field_150349_c && BlocksDBC.BlockDragonBallStone.func_149742_c(par1World, var7, var8, var9)) {
            par1World.func_147465_d(var7, var8, var9, BlocksDBC.BlockDragonBallStone, 10, 4);
        }
        return true;
    }
}

