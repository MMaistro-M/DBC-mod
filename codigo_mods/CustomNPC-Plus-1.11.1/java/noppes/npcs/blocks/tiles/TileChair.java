/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.blocks.tiles.TileVariant;

public class TileChair
extends TileVariant {
    public boolean isPushed() {
        return this.variant == 1;
    }

    public void push() {
        this.variant = this.variant == 14 ? 1 : 14;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        if (this.isPushed()) {
            switch (this.rotation) {
                case 0: {
                    return AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c + 0.1), (double)this.field_145848_d, (double)this.field_145849_e, (double)((double)this.field_145851_c + 0.9), (double)(this.field_145848_d + 1), (double)((double)this.field_145849_e + 0.3));
                }
                case 1: {
                    return AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c + 0.7), (double)this.field_145848_d, (double)((double)this.field_145849_e + 0.1), (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)((double)this.field_145849_e + 0.9));
                }
                case 2: {
                    return AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c + 0.1), (double)this.field_145848_d, (double)((double)this.field_145849_e + 0.7), (double)((double)this.field_145851_c + 0.9), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
                }
                case 3: {
                    return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)((double)this.field_145849_e + 0.1), (double)((double)this.field_145851_c + 0.3), (double)(this.field_145848_d + 1), (double)((double)this.field_145849_e + 0.9));
                }
            }
            return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d - 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
        }
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d - 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }
}

