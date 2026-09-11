/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.JRMCore.i;

import JinRyuu.JRMCore.items.ItemBodysuit;
import JinRyuu.JRMCore.items.ItemHeadwear;
import JinRyuu.JRMCore.items.ItemVanity;
import JinRyuu.JRMCore.items.ItemWeight;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCustom
extends Slot {
    public SlotCustom(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    public boolean func_75214_a(ItemStack stack) {
        if (this.getSlotIndex() == 0) {
            return stack.func_77973_b() instanceof ItemWeight;
        }
        if (this.getSlotIndex() == 1) {
            return stack.func_77973_b() instanceof ItemBodysuit;
        }
        if (this.getSlotIndex() == 2) {
            return stack.func_77973_b() instanceof ItemHeadwear;
        }
        if (this.getSlotIndex() >= 3 && this.getSlotIndex() <= 10) {
            return stack.func_77973_b() instanceof ItemVanity;
        }
        return false;
    }
}

