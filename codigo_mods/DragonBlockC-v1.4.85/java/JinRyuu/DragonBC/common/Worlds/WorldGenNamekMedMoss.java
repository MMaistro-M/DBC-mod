/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNamekMedMoss
extends WorldGenerator {
    public WorldGenNamekMedMoss(boolean doBlockNotify) {
        super(doBlockNotify);
    }

    public boolean func_76484_a(World world, Random rand, int i, int j, int k) {
        if (world.func_147437_c(i, j, k) && world.func_147439_a(i, j - 1, k) == BlocksDBC.BlockNamekGrass) {
            this.func_150515_a(world, i + 0, j + 0, k + 0, BlocksDBC.BlockWildMedMoss);
        }
        return true;
    }
}

