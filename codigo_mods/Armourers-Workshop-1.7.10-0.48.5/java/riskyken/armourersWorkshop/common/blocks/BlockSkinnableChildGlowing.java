/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnableChild;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnableChild;

public class BlockSkinnableChildGlowing
extends BlockSkinnableChild {
    public BlockSkinnableChildGlowing() {
        super("skinnableChildGlowing");
        this.func_149715_a(1.0f);
    }

    @Override
    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return new TileEntitySkinnableChild();
    }
}

