/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.blocks.tiles.TileColorable;

public class TileShortLamp
extends TileColorable {
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)((float)this.field_145851_c + 0.25f), (double)this.field_145848_d, (double)((float)this.field_145849_e + 0.25f), (double)((float)this.field_145851_c + 0.75f), (double)(this.field_145848_d + 1), (double)((float)this.field_145849_e + 0.75f));
    }
}

