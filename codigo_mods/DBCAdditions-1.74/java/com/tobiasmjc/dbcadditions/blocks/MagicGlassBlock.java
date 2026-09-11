/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.Facing
 *  net.minecraft.world.IBlockAccess
 */
package com.tobiasmjc.dbcadditions.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;

public class MagicGlassBlock
extends Block {
    public MagicGlassBlock() {
        super(Material.field_151592_s);
        this.func_149663_c("magicGlass");
        this.func_149672_a(field_149778_k);
        this.func_149658_d("dbcadditions:BlockMagicGlass");
        this.func_149722_s();
    }

    public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
        Block block = p_149646_1_.func_147439_a(p_149646_2_, p_149646_3_, p_149646_4_);
        if (this == Blocks.field_150359_w) {
            if (p_149646_1_.func_72805_g(p_149646_2_, p_149646_3_, p_149646_4_) != p_149646_1_.func_72805_g(p_149646_2_ - Facing.field_71586_b[p_149646_5_], p_149646_3_ - Facing.field_71587_c[p_149646_5_], p_149646_4_ - Facing.field_71585_d[p_149646_5_])) {
                return true;
            }
            if (block == this) {
                return false;
            }
        }
        return super.func_149646_a(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }
}

