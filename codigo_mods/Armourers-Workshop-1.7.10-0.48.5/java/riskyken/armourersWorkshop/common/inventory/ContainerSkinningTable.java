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
import riskyken.armourersWorkshop.common.crafting.ItemSkinningRecipes;
import riskyken.armourersWorkshop.common.inventory.slot.SlotInput;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinningTable;

public class ContainerSkinningTable
extends Container {
    private final TileEntitySkinningTable tileEntity;
    private final IInventory craftingInventory;
    private final IInventory outputInventory;

    public ContainerSkinningTable(InventoryPlayer invPlayer, TileEntitySkinningTable tileEntity) {
        this.tileEntity = tileEntity;
        this.craftingInventory = tileEntity.getCraftingInventory();
        this.outputInventory = tileEntity.getOutputInventory();
        int hotBarY = 152;
        int playerInvY = 94;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 8 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, playerInvY + y * 18));
            }
        }
        this.func_75146_a(new SlotInput(this.craftingInventory, 0, 37, 22, this));
        this.func_75146_a(new SlotInput(this.craftingInventory, 1, 37, 58, this));
        this.func_75146_a(new SlotOutput(this.outputInventory, 0, 119, 40, this));
    }

    public void func_75130_a(IInventory p_75130_1_) {
        ItemSkinningRecipes.onCraft(this.craftingInventory);
        super.func_75130_a(p_75130_1_);
    }

    public boolean func_75145_c(EntityPlayer player) {
        return player.func_70092_e((double)this.tileEntity.field_145851_c + 0.5, (double)this.tileEntity.field_145848_d + 0.5, (double)this.tileEntity.field_145849_e + 0.5) <= 64.0;
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
                for (int i = 36; i < 38; ++i) {
                    Slot targetSlot = this.func_75139_a(i);
                    if (!this.func_75135_a(stack, i, i + 1, false)) continue;
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
}

