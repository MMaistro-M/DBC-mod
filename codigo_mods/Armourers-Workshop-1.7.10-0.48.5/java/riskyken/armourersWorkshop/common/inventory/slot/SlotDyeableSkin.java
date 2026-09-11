/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.inventory.ContainerDyeTable;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class SlotDyeableSkin
extends Slot {
    private final ContainerDyeTable container;

    public SlotDyeableSkin(IInventory inventory, int slotIndex, int xPosition, int yPosition, ContainerDyeTable container) {
        super(inventory, slotIndex, xPosition, yPosition);
        this.container = container;
    }

    public boolean func_75214_a(ItemStack stack) {
        SkinPointer sp = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (sp != null) {
            if (stack.func_77973_b() == ModItems.equipmentSkin) {
                return true;
            }
            if (sp.lockSkin) {
                return true;
            }
        }
        return false;
    }

    public boolean func_82869_a(EntityPlayer player) {
        return false;
    }

    public void func_75218_e() {
        ItemStack stack = this.func_75211_c();
        if (stack == null) {
            this.container.skinRemoved();
        } else {
            SkinPointer sp = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (sp != null) {
                if (stack.func_77973_b() == ModItems.equipmentSkin) {
                    this.container.skinAdded(stack);
                } else if (sp.lockSkin) {
                    this.container.skinAdded(stack);
                }
            }
        }
        super.func_75218_e();
    }
}

