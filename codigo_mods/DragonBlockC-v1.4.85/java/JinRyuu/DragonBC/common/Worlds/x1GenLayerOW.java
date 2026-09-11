/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.GenLayerVoronoiZoom
 *  net.minecraft.world.gen.layer.GenLayerZoom
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Worlds.x1GenLayerOWBiomes;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class x1GenLayerOW
extends GenLayer {
    public x1GenLayerOW(long seed) {
        super(seed);
    }

    public static GenLayer[] makeTheWorld(long seed, WorldType type) {
        x1GenLayerOWBiomes biomes = new x1GenLayerOWBiomes(1L);
        biomes = new GenLayerZoom(1000L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1001L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1002L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1003L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1004L, (GenLayer)biomes);
        biomes = new GenLayerZoom(1005L, (GenLayer)biomes);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, (GenLayer)biomes);
        biomes.func_75905_a(seed);
        genlayervoronoizoom.func_75905_a(seed);
        return new GenLayer[]{biomes, genlayervoronoizoom};
    }

    public int[] func_75904_a(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_) {
        return null;
    }
}

