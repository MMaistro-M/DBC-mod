/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.init.Blocks
 */
package com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes;

import com.tobiasmjc.dbcadditions.dimensions.worldgen.biomes.BiomeDBCUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;

public class SacredWorldBiome
extends BiomeDBCUtils {
    public SacredWorldBiome(int id) {
        super(id);
        this.func_76735_a("SacredWorldOfKai");
        this.field_76752_A = Blocks.field_150349_c;
        this.field_76753_B = Blocks.field_150348_b;
        this.func_76732_a(0.8f, 0.9f);
        this.field_76750_F = 0.5f;
        this.field_76759_H = 6616400;
        this.field_76761_J.clear();
        this.field_76762_K.clear();
        this.field_76755_L.clear();
    }

    public int func_150571_c(int p_150571_1_, int p_150571_2_, int p_150571_3_) {
        return 7787630;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150558_b(int par1, int par2, int par3) {
        return 6616400;
    }

    public int func_76731_a(float p_76731_1_) {
        return super.func_76731_a(p_76731_1_);
    }
}

