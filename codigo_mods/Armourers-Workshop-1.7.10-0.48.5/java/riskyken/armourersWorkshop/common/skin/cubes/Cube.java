/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package riskyken.armourersWorkshop.common.skin.cubes;

import net.minecraft.block.Block;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;
import riskyken.armourersWorkshop.common.skin.cubes.ICube;

public class Cube
implements ICube {
    protected final byte id = CubeRegistry.INSTANCE.getTotalCubes();

    @Override
    public boolean isGlowing() {
        return false;
    }

    @Override
    public boolean needsPostRender() {
        return false;
    }

    @Override
    public byte getId() {
        return this.id;
    }

    @Override
    public Block getMinecraftBlock() {
        return ModBlocks.colourable;
    }
}

