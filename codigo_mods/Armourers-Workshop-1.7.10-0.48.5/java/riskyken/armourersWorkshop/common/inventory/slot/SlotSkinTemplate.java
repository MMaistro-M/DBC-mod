/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.inventory.slot.ISlotChanged;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.items.ItemSkinTemplate;

public class SlotSkinTemplate
extends SlotHidable {
    private final ISlotChanged callback;

    public SlotSkinTemplate(IInventory inventory, int slotIndex, int xPosition, int yPosition, ISlotChanged callback) {
        super(inventory, slotIndex, xPosition, yPosition);
        this.callback = callback;
    }

    public SlotSkinTemplate(IInventory inventory, int slotIndex, int xPosition, int yPosition) {
        this(inventory, slotIndex, xPosition, yPosition, null);
    }

    public boolean func_75214_a(ItemStack stack) {
        if (stack.func_77973_b() instanceof ItemSkinTemplate && stack.func_77960_j() == 0) {
            return true;
        }
        return stack.func_77973_b() instanceof ItemSkin;
    }

    public void func_75218_e() {
        if (this.callback != null) {
            this.callback.onSlotChanged(this.getSlotIndex());
        }
        super.func_75218_e();
    }
}

