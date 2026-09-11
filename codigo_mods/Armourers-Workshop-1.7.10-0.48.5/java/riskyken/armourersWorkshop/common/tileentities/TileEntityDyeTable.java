/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.common.tileentities.AbstractTileEntityInventory;

public class TileEntityDyeTable
extends AbstractTileEntityInventory {
    private static final int INVENTORY_SIZE = 10;

    public TileEntityDyeTable() {
        super(10);
    }

    public boolean canUpdate() {
        return false;
    }

    public String func_145825_b() {
        return "dyeTable";
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
    }

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
    }
}

