/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes;

import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BeerusPlanetBiome;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.SacredWorldBiome;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.UniversalArenaBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeDBCUtils
extends BiomeGenBase {
    public static SacredWorldBiome SacredWorldBiome = new SacredWorldBiome(115);
    public static BeerusPlanetBiome BeerusPlanetBiome = new BeerusPlanetBiome(116);
    public static UniversalArenaBiome UniversalArenaBiome = new UniversalArenaBiome(117);

    public BiomeDBCUtils(int id) {
        super(id);
    }

    public static void registerAll() {
        BiomeDictionary.registerBiomeType((BiomeGenBase)SacredWorldBiome, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerBiomeType((BiomeGenBase)BeerusPlanetBiome, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.FOREST});
        BiomeDictionary.registerBiomeType((BiomeGenBase)UniversalArenaBiome, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerAllBiomes();
    }
}

