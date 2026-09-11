/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package riskyken.armourersWorkshop.common.skin.cubes;

import net.minecraft.block.Block;

public interface ICube {
    public boolean isGlowing();

    public boolean needsPostRender();

    public byte getId();

    public Block getMinecraftBlock();
}

