/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BiomeDBCUtils;

public class UniversalArenaBiome
extends BiomeDBCUtils {
    public UniversalArenaBiome(int id) {
        super(id);
        this.func_76735_a("UniversalArena");
        this.field_76752_A = BlocksDBC.BlockNamekGrass;
        this.field_76753_B = BlocksDBC.BlockNamekDirt;
        this.func_76732_a(0.8f, 0.9f);
        this.field_76750_F = 0.5f;
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
    }
}

