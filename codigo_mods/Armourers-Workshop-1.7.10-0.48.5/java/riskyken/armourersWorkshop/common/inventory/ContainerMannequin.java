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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.MannequinSlotType;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.inventory.slot.SlotMannequin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ContainerMannequin
extends Container {
    private TileEntityMannequin tileEntity;

    public ContainerMannequin(InventoryPlayer invPlayer, TileEntityMannequin tileEntity) {
        this.tileEntity = tileEntity;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new SlotHidable((IInventory)invPlayer, x, 8 + 18 * x, 232));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new SlotHidable((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, 174 + y * 18));
            }
        }
        for (int i = 0; i < 5; ++i) {
            for (int y = 0; y < MannequinSlotType.values().length; ++y) {
                this.func_75146_a(new SlotMannequin(MannequinSlotType.getOrdinal(y), tileEntity, y + i * 7, 5 + 19 * y, 5 + i * 19));
            }
        }
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId > 35) {
                if (!this.func_75135_a(stack, 9, 36, false) && !this.func_75135_a(stack, 0, 9, false)) {
                    return null;
                }
            } else {
                boolean slotted = false;
                for (int i = 0; i < 35; ++i) {
                    ISkinType skinType;
                    int targetSlotId = i + 36;
                    Slot targetSlot = this.func_75139_a(targetSlotId);
                    boolean handSlot = false;
                    if (i % 7 == 4) {
                        handSlot = true;
                    }
                    if (i % 7 == 5) {
                        handSlot = true;
                    }
                    if ((skinType = SkinNBTHelper.getSkinTypeFromStack(stack)) != null && skinType.getVanillaArmourSlotId() != -1 | skinType == SkinTypeRegistry.skinWings) {
                        if (handSlot || !targetSlot.func_75214_a(stack) || !this.func_75135_a(stack, targetSlotId, targetSlotId + 1, false)) continue;
                        slotted = true;
                        break;
                    }
                    if (!targetSlot.func_75214_a(stack) || !this.func_75135_a(stack, targetSlotId, targetSlotId + 1, false)) continue;
                    slotted = true;
                    break;
                }
                if (!slotted) {
                    return null;
                }
            }
            if (stack.field_77994_a == 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
            slot.func_82870_a(player, stack);
            return result;
        }
        return null;
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntity.func_70300_a(player);
    }

    public TileEntityMannequin getTileEntity() {
        return this.tileEntity;
    }
}

