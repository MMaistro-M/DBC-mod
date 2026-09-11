/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class x0GenLayerDBCNamekBiomes
extends GenLayer {
    protected BiomeGenBase[] allowedBiomes = new BiomeGenBase[]{BiomeGenBaseDBC.Namek};

    public x0GenLayerDBCNamekBiomes(long seed) {
        super(seed);
    }

    public x0GenLayerDBCNamekBiomes(long seed, GenLayer genlayer) {
        super(seed);
        this.field_75909_a = genlayer;
    }

    public int[] func_75904_a(int x, int z, int width, int depth) {
        int[] dest = IntCache.func_76445_a((int)(width * depth));
        for (int dz = 0; dz < depth; ++dz) {
            for (int dx = 0; dx < width; ++dx) {
                this.func_75903_a(dx + x, dz + z);
                dest[dx + dz * width] = this.allowedBiomes[this.func_75902_a((int)this.allowedBiomes.length)].field_76756_M;
            }
        }
        return dest;
    }
}

