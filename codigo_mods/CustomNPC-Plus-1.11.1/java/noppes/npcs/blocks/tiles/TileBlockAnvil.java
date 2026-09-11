/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.EnumSkyBlock
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;

public class TileBlockAnvil
extends TileEntity {
    public boolean firstTick = true;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (this.func_145830_o() && this.firstTick && !this.func_145831_w().field_72995_K) {
            this.firstTick = false;
            this.func_145831_w().func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_145831_w().func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

