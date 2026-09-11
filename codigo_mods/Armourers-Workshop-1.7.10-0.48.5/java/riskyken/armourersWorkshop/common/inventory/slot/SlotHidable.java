/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotHidable
extends Slot {
    private boolean visible = true;
    private int xDisplayPositionNormal;
    private int yDisplayPositionNormal;

    public SlotHidable(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.xDisplayPositionNormal = xDisplayPosition;
        this.yDisplayPositionNormal = yDisplayPosition;
    }

    public void setDisplayPosition(int x, int y) {
        this.xDisplayPositionNormal = x;
        this.yDisplayPositionNormal = y;
        if (this.visible) {
            this.field_75223_e = x;
            this.field_75221_f = y;
        }
    }

    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        if (this.visible) {
            this.field_75223_e = this.xDisplayPositionNormal;
            this.field_75221_f = this.yDisplayPositionNormal;
        } else {
            this.field_75223_e = 100000;
            this.field_75221_f = 100000;
        }
    }
}

