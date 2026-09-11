/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class RedStoneBlock
extends Block {
    public RedStoneBlock() {
        super(Material.field_151576_e);
        this.func_149663_c("redStone");
        this.func_149658_d("dbcadditions:BlockRedStone");
        this.func_149722_s();
    }

    public void func_149664_b(World world, int x, int y, int z, int metadata) {
        super.func_149664_b(world, x, y, z, metadata);
    }
}

