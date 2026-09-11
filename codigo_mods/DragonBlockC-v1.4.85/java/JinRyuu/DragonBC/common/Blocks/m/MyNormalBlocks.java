/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MyNormalBlocks
extends Block {
    protected MyNormalBlocks(String unlocalizedName, Material material) {
        super(material);
        this.func_149663_c(unlocalizedName);
        this.func_149658_d(JRMCoreH.tjdbcAssts + ":" + unlocalizedName);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.func_149672_a(field_149769_e);
    }
}

