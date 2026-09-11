/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.containers.ContainerNpcInterface;
import noppes.npcs.entity.EntityNPCInterface;

public class ContainerAuction
extends ContainerNpcInterface {
    public final EntityNPCInterface npc;
    public static final int PLAYER_INV_X = 48;
    public static final int PLAYER_INV_Y = 164;
    public static final int HOTBAR_Y = 222;
    public static final int PLAYER_INV_SLOT_COUNT = 36;

    public ContainerAuction(EntityNPCInterface npc, EntityPlayer player) {
        super(player);
        this.npc = npc;
        this.player = player;
        this.addPlayerInventory();
    }

    protected void addPlayerInventory() {
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int slotIndex = col + row * 9 + 9;
                int x = 48 + col * 18;
                int y = 164 + row * 18;
                this.func_75146_a(new Slot((IInventory)this.player.field_71071_by, slotIndex, x, y));
            }
        }
        for (int col = 0; col < 9; ++col) {
            int x = 48 + col * 18;
            this.func_75146_a(new Slot((IInventory)this.player.field_71071_by, col, x, 222));
        }
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
        return null;
    }

    public ItemStack func_75144_a(int slotIndex, int mouseButton, int mode, EntityPlayer player) {
        return null;
    }

    public boolean isPlayerInventorySlot(int slotIndex) {
        return slotIndex >= 0 && slotIndex < 36;
    }

    public ItemStack getPlayerInventoryStack(int containerSlotIndex) {
        if (!this.isPlayerInventorySlot(containerSlotIndex)) {
            return null;
        }
        Slot slot = (Slot)this.field_75151_b.get(containerSlotIndex);
        return slot != null ? slot.func_75211_c() : null;
    }
}

