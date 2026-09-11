/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;

public class SlotOutput
extends SlotHidable {
    private final Container callback;

    public SlotOutput(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        this(inventory, slotIndex, xDisplayPosition, yDisplayPosition, null);
    }

    public SlotOutput(IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition, Container callback) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.callback = callback;
    }

    public void func_82870_a(EntityPlayer p_82870_1_, ItemStack p_82870_2_) {
        if (this.callback != null) {
            this.callback.func_75130_a(this.field_75224_c);
        }
    }

    public boolean func_75214_a(ItemStack stack) {
        return false;
    }
}

