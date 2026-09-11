/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryBasic
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.containers.ContainerAuction;
import noppes.npcs.entity.EntityNPCInterface;

public class ContainerAuctionSell
extends ContainerAuction {
    private static final int SELL_SLOT_X = 59;
    private static final int SELL_SLOT_Y = 101;
    private static final int SELL_SLOT_INDEX = 36;
    private static final int MAX_STACK = 64;
    private final IInventory sellInventory = new InventoryBasic("Sell", false, 1);

    public ContainerAuctionSell(EntityNPCInterface npc, EntityPlayer player) {
        super(npc, player);
        this.func_75146_a(new Slot(this.sellInventory, 0, 59, 101){

            public boolean func_75214_a(ItemStack stack) {
                return false;
            }

            public boolean func_82869_a(EntityPlayer player) {
                return false;
            }
        });
    }

    public ItemStack getItemToSell() {
        return this.sellInventory.func_70301_a(0);
    }

    public void clearSellSlot() {
        this.sellInventory.func_70299_a(0, null);
    }

    public void addToSellSlot(int sourceSlot, boolean fullStack) {
        int toAdd;
        if (!this.isPlayerInventorySlot(sourceSlot)) {
            return;
        }
        ItemStack source = this.getPlayerInventoryStack(sourceSlot);
        if (source == null) {
            return;
        }
        ItemStack sellStack = this.sellInventory.func_70301_a(0);
        int n = toAdd = fullStack ? source.field_77994_a : 1;
        if (sellStack == null) {
            ItemStack newStack = source.func_77946_l();
            newStack.field_77994_a = Math.min(toAdd, 64);
            this.sellInventory.func_70299_a(0, newStack);
        } else if (this.itemsMatch(sellStack, source)) {
            int space = 64 - sellStack.field_77994_a;
            int add = Math.min(toAdd, space);
            if (add > 0) {
                sellStack.field_77994_a += add;
            }
        } else {
            ItemStack newStack = source.func_77946_l();
            newStack.field_77994_a = Math.min(toAdd, 64);
            this.sellInventory.func_70299_a(0, newStack);
        }
    }

    public void removeFromSellSlot(boolean removeAll) {
        ItemStack sellStack = this.sellInventory.func_70301_a(0);
        if (sellStack == null) {
            return;
        }
        if (removeAll || sellStack.field_77994_a <= 1) {
            this.sellInventory.func_70299_a(0, null);
        } else {
            --sellStack.field_77994_a;
        }
    }

    public boolean isSellSlot(int slotIndex) {
        return slotIndex == 36;
    }

    private boolean itemsMatch(ItemStack a, ItemStack b) {
        return NoppesUtilPlayer.compareItems(a, b, false, false);
    }

    public int countItemInInventory(ItemStack target) {
        if (target == null) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < 36; ++i) {
            ItemStack stack = this.getPlayerInventoryStack(i);
            if (stack == null || !this.itemsMatch(stack, target)) continue;
            total += stack.field_77994_a;
        }
        return total;
    }

    public void func_75134_a(EntityPlayer player) {
        super.func_75134_a(player);
        this.clearSellSlot();
    }
}

