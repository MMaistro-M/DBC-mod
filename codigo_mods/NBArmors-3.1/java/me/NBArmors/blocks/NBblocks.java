/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 */
package me.NBArmors.blocks;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import me.NBArmors.blocks.CoreGeneration;
import me.NBArmors.blocks.blockCore;
import me.NBArmors.config.ConfigNB;
import me.NBArmors.tabs.NBTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NBblocks {
    public static Block blockCore;

    public static void mainRegistry() {
        NBblocks.initiliseItem();
        NBblocks.registerItem();
    }

    public static void initiliseItem() {
        if (!ConfigNB.Recipe) {
            blockCore = new blockCore(Material.field_151573_f).func_149663_c("blockCore").func_149658_d("NBArmors:blockCore").func_149711_c(10.0f).func_149647_a(NBTab.coreitems);
            GameRegistry.registerWorldGenerator((IWorldGenerator)new CoreGeneration(), (int)0);
        }
    }

    public static void registerItem() {
        if (!ConfigNB.Recipe) {
            GameRegistry.registerBlock((Block)blockCore, (String)blockCore.func_149739_a());
            System.out.println(blockCore.func_149739_a().substring(5));
        }
    }
}

