/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotInput
extends Slot {
    private final Container callback;

    public SlotInput(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        this(inventory, slotIndex, xDisplayPosition, yDisplayPosition, null);
    }

    public SlotInput(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition, Container callback) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.callback = callback;
    }

    public void func_75218_e() {
        super.func_75218_e();
        if (this.callback != null) {
            this.callback.func_75142_b();
        }
    }
}

