/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;

public class ModContainer
extends Container {
    private final EntityPlayer player;
    private int playerInvStartIndex;
    private int playerInvEndIndex;

    public ModContainer(EntityPlayer player) {
        this.player = player;
    }

    protected void addPlayerSlots(int posX, int posY) {
        this.playerInvStartIndex = this.field_75151_b.size();
        int playerInvY = posY;
        int hotBarY = playerInvY + 58;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new SlotHidable((IInventory)this.player.field_71071_by, x, posX + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new SlotHidable((IInventory)this.player.field_71071_by, x + y * 9 + 9, posX + 18 * x, playerInvY + y * 18));
            }
        }
        this.playerInvEndIndex = this.field_75151_b.size();
    }

    public EntityPlayer getPlayer() {
        return this.player;
    }

    public int getPlayerInvStartIndex() {
        return this.playerInvStartIndex;
    }

    public int getPlayerInvEndIndex() {
        return this.playerInvEndIndex;
    }

    public boolean isSlotPlayerInv(int index) {
        return index >= this.playerInvStartIndex & index < this.playerInvEndIndex;
    }

    protected boolean canSlotHoldItem(int slotIndex, ItemStack itemStack) {
        Slot slot = this.func_75139_a(slotIndex);
        return this.canSlotHoldItem(slot, itemStack);
    }

    protected boolean canSlotHoldItem(Slot slot, ItemStack itemStack) {
        return slot.func_75214_a(itemStack);
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        if (!this.isSlotPlayerInv(index)) {
            Slot slot = this.func_75139_a(index);
            if (slot.func_75216_d()) {
                ItemStack stack = slot.func_75211_c();
                ItemStack result = stack.func_77946_l();
                if (!this.func_75135_a(stack, this.playerInvStartIndex + 9, this.playerInvEndIndex, false) && !this.func_75135_a(stack, this.playerInvStartIndex, this.playerInvStartIndex + 9, false)) {
                    return null;
                }
                if (stack.field_77994_a == 0) {
                    slot.func_75215_d(null);
                } else {
                    slot.func_75218_e();
                }
                slot.func_82870_a(playerIn, stack);
                return result;
            }
            return null;
        }
        return this.transferStackFromPlayer(playerIn, index);
    }

    protected ItemStack transferStackFromPlayer(EntityPlayer playerIn, int index) {
        return null;
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        return !playerIn.field_70128_L;
    }
}

