/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.InventoryEntitySkin;
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkin;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ContainerEntityEquipment
extends Container {
    private int skinSlots = 0;

    public ContainerEntityEquipment(InventoryPlayer invPlayer, InventoryEntitySkin skinInventory) {
        ArrayList<ISkinType> skinTypes = skinInventory.getSkinTypes();
        for (int i = 0; i < skinTypes.size(); ++i) {
            this.func_75146_a(new SlotSkin(skinTypes.get(i), skinInventory, i, 8 + i * 18, 21));
            ++this.skinSlots;
        }
        int hotBarY = 124;
        int playerInvY = 66;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 8 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, playerInvY + y * 18));
            }
        }
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return !entityPlayer.field_70128_L;
    }

    public ItemStack func_82846_b(EntityPlayer entityPlayer, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId < this.skinSlots) {
                if (!this.func_75135_a(stack, this.skinSlots + 9, this.skinSlots + 36, false) && !this.func_75135_a(stack, this.skinSlots, this.skinSlots + 9, false)) {
                    return null;
                }
            } else if (stack.func_77973_b() instanceof ItemSkin & SkinNBTHelper.stackHasSkinData(stack)) {
                boolean slotted = false;
                for (int i = 0; i < this.skinSlots; ++i) {
                    Slot targetSlot = this.func_75139_a(i);
                    if (!targetSlot.func_75214_a(stack) || !this.func_75135_a(stack, i, i + 1, false)) continue;
                    slotted = true;
                    break;
                }
                if (!slotted) {
                    return null;
                }
            } else {
                return null;
            }
            if (stack.field_77994_a == 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
            slot.func_82870_a(entityPlayer, stack);
            return result;
        }
        return null;
    }
}

