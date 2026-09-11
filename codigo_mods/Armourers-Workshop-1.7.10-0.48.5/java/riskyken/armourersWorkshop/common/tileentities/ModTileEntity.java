/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package riskyken.armourersWorkshop.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public abstract class ModTileEntity
extends TileEntity {
    public void syncWithClients() {
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void dirtySync() {
        this.func_70296_d();
        this.syncWithClients();
    }

    public NBTTagCompound getUpdateTag() {
        return null;
    }
}

