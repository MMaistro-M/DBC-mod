/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package riskyken.armourersWorkshop.common.skin.cubes;

import net.minecraft.block.Block;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.skin.cubes.Cube;

public class CubeGlowing
extends Cube {
    @Override
    public boolean isGlowing() {
        return true;
    }

    @Override
    public Block getMinecraftBlock() {
        return ModBlocks.colourableGlowing;
    }
}

