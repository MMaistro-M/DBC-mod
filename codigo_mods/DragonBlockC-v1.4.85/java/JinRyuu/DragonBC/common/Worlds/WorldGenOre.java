/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Worlds;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenOre
extends WorldGenerator {
    private Block field_150519_a;
    private int numberOfBlocks;
    private Block field_150518_c;
    private static final String __OBFID = "CL_00000426";
    private int mineableBlockMeta;

    public WorldGenOre(Block blockToPlace, int howMeny, Block replaceableBlock) {
        this.field_150519_a = blockToPlace;
        this.numberOfBlocks = howMeny;
        this.field_150518_c = replaceableBlock;
    }

    public WorldGenOre(Block block, int meta, int number, Block target) {
        this(block, number, target);
        this.mineableBlockMeta = meta;
    }

    public boolean func_76484_a(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_) {
        float f = p_76484_2_.nextFloat() * (float)Math.PI;
        double d0 = (float)(p_76484_3_ + 8) + MathHelper.func_76126_a((float)f) * (float)this.numberOfBlocks / 8.0f;
        double d1 = (float)(p_76484_3_ + 8) - MathHelper.func_76126_a((float)f) * (float)this.numberOfBlocks / 8.0f;
        double d2 = (float)(p_76484_5_ + 8) + MathHelper.func_76134_b((float)f) * (float)this.numberOfBlocks / 8.0f;
        double d3 = (float)(p_76484_5_ + 8) - MathHelper.func_76134_b((float)f) * (float)this.numberOfBlocks / 8.0f;
        double d4 = p_76484_4_ + p_76484_2_.nextInt(3) - 2;
        double d5 = p_76484_4_ + p_76484_2_.nextInt(3) - 2;
        for (int l = 0; l <= this.numberOfBlocks; ++l) {
            double d6 = d0 + (d1 - d0) * (double)l / (double)this.numberOfBlocks;
            double d7 = d4 + (d5 - d4) * (double)l / (double)this.numberOfBlocks;
            double d8 = d2 + (d3 - d2) * (double)l / (double)this.numberOfBlocks;
            double d9 = p_76484_2_.nextDouble() * (double)this.numberOfBlocks / 16.0;
            double d10 = (double)(MathHelper.func_76126_a((float)((float)l * (float)Math.PI / (float)this.numberOfBlocks)) + 1.0f) * d9 + 1.0;
            double d11 = (double)(MathHelper.func_76126_a((float)((float)l * (float)Math.PI / (float)this.numberOfBlocks)) + 1.0f) * d9 + 1.0;
            int i1 = MathHelper.func_76128_c((double)(d6 - d10 / 2.0));
            int j1 = MathHelper.func_76128_c((double)(d7 - d11 / 2.0));
            int k1 = MathHelper.func_76128_c((double)(d8 - d10 / 2.0));
            int l1 = MathHelper.func_76128_c((double)(d6 + d10 / 2.0));
            int i2 = MathHelper.func_76128_c((double)(d7 + d11 / 2.0));
            int j2 = MathHelper.func_76128_c((double)(d8 + d10 / 2.0));
            for (int k2 = i1; k2 <= l1; ++k2) {
                double d12 = ((double)k2 + 0.5 - d6) / (d10 / 2.0);
                if (!(d12 * d12 < 1.0)) continue;
                for (int l2 = j1; l2 <= i2; ++l2) {
                    double d13 = ((double)l2 + 0.5 - d7) / (d11 / 2.0);
                    if (!(d12 * d12 + d13 * d13 < 1.0)) continue;
                    for (int i3 = k1; i3 <= j2; ++i3) {
                        double d14 = ((double)i3 + 0.5 - d8) / (d10 / 2.0);
                        if (!(d12 * d12 + d13 * d13 + d14 * d14 < 1.0) || !p_76484_1_.func_147439_a(k2, l2, i3).isReplaceableOreGen(p_76484_1_, k2, l2, i3, this.field_150518_c)) continue;
                        p_76484_1_.func_147465_d(k2, l2, i3, this.field_150519_a, this.mineableBlockMeta, 2);
                    }
                }
            }
        }
        return true;
    }
}

