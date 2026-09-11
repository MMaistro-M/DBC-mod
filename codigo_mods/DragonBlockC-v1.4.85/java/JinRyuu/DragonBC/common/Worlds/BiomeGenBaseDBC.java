/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.feature.WorldGenTallGrass
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.common.BiomeManager
 *  net.minecraftforge.common.BiomeManager$BiomeEntry
 *  net.minecraftforge.common.BiomeManager$BiomeType
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Worlds.BiomeGenStony;
import JinRyuu.DragonBC.common.Worlds.Dimension.NullRealm.BiomeNullRealm;
import JinRyuu.DragonBC.common.Worlds.x0BiomeGenNamek;
import JinRyuu.DragonBC.common.Worlds.x1BiomeGenOW;
import JinRyuu.DragonBC.common.Worlds.x1BiomeGenOW2;
import JinRyuu.DragonBC.common.Worlds.x1BiomeGenOW3;
import JinRyuu.DragonBC.common.Worlds.x1BiomeGenOW4;
import JinRyuu.DragonBC.common.Worlds.x2BiomeGenTC;
import JinRyuu.DragonBC.common.Worlds.x3BiomeGenVegata;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public abstract class BiomeGenBaseDBC
extends BiomeGenBase {
    public static BiomeGenBase Namek;
    public static BiomeGenBase Vegeta;
    public static BiomeGenBase Stony;
    public static BiomeGenBase OtherW;
    public static BiomeGenBase OtherW2;
    public static BiomeGenBase OtherW3;
    public static BiomeGenBase OtherW4;
    public static BiomeGenBase TC;
    public static BiomeGenBase NR;

    public BiomeGenBaseDBC(int biomeId) {
        super(biomeId);
    }

    public WorldGenerator func_76730_b(Random random) {
        if (random.nextInt(4) == 0) {
            return new WorldGenTallGrass((Block)Blocks.field_150329_H, 2);
        }
        return new WorldGenTallGrass((Block)Blocks.field_150329_H, 1);
    }

    public static void init() {
        BiomeDictionary.registerBiomeType((BiomeGenBase)Namek, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerBiomeType((BiomeGenBase)Vegeta, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerBiomeType((BiomeGenBase)Stony, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY});
        BiomeDictionary.registerBiomeType((BiomeGenBase)OtherW, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerBiomeType((BiomeGenBase)OtherW2, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerBiomeType((BiomeGenBase)OtherW4, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.PLAINS});
        BiomeDictionary.registerBiomeType((BiomeGenBase)TC, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY});
        BiomeDictionary.registerBiomeType((BiomeGenBase)NR, (BiomeDictionary.Type[])new BiomeDictionary.Type[]{BiomeDictionary.Type.HOT, BiomeDictionary.Type.DRY});
        BiomeDictionary.registerAllBiomes();
        BiomeManager.addBiome((BiomeManager.BiomeType)BiomeManager.BiomeType.WARM, (BiomeManager.BiomeEntry)new BiomeManager.BiomeEntry(Stony, DBCConfig.cnfDrt));
    }

    static {
        int i = 100;
        Namek = new x0BiomeGenNamek(i++);
        ++i;
        int n = ++i;
        Vegeta = new x3BiomeGenVegata(n);
        int n2 = ++i;
        Stony = new BiomeGenStony(n2);
        int n3 = ++i;
        OtherW = new x1BiomeGenOW(n3);
        int n4 = ++i;
        TC = new x2BiomeGenTC(n4);
        int n5 = ++i;
        OtherW2 = new x1BiomeGenOW2(n5);
        int n6 = ++i;
        OtherW3 = new x1BiomeGenOW3(n6);
        int n7 = ++i;
        OtherW4 = new x1BiomeGenOW4(n7);
        int n8 = ++i;
        ++i;
        NR = new BiomeNullRealm(n8);
    }
}

