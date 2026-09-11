/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.Item
 */
package me.NBArmors.blocks;

import java.util.Random;
import me.NBArmors.items.CItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class blockCore
extends Block {
    public blockCore(Material material) {
        super(material);
        this.func_149711_c(0.4f);
        this.setHarvestLevel("pickaxe", 3);
        this.func_149715_a(3.4f);
        this.func_149713_g(128);
        this.func_149672_a(field_149769_e);
    }

    public Item func_149650_a(int metadata, Random rand, int fortune) {
        return CItems.Core;
    }

    public int func_149745_a(Random rand) {
        return 1 + rand.nextInt(4);
    }
}

