/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 */
package com.tobiasmjc.dbcadditions.blocks;

import com.tobiasmjc.dbcadditions.blocks.MagicGlassBlock;
import com.tobiasmjc.dbcadditions.blocks.RedStoneBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class BlocksDBCAdditions {
    public static final RedStoneBlock RedStone = new RedStoneBlock();
    public static final MagicGlassBlock MagicGlass = new MagicGlassBlock();

    public static void registerAll() {
        GameRegistry.registerBlock((Block)RedStone, (String)"RedStone");
        GameRegistry.registerBlock((Block)MagicGlass, (String)"MagicGlass");
    }
}

