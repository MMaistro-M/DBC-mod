/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.EventHooks;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.client.gui.custom.components.CustomGuiSlot;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.gui.ScriptGui;

public class ContainerCustomGui
extends Container {
    public ScriptGui customGui;
    public IInventory guiInventory;
    int slotCount = 0;
    public int playerInvX;
    public int playerInvY;

    public ContainerCustomGui(IInventory inventory) {
        this.guiInventory = inventory;
    }

    public void setGui(ScriptGui gui, EntityPlayer player) {
        this.customGui = gui;
        this.slotCount = 0;
        this.field_75151_b.clear();
        if (this.customGui.getShowPlayerInv()) {
            this.addPlayerInventory(player, this.customGui.getPlayerInvX(), this.customGui.getPlayerInvY());
        }
        for (IItemSlot slot : this.customGui.getSlots()) {
            if (slot.hasStack()) {
                this.addSlot(player, slot.getPosX(), slot.getPosY(), slot, slot.getStack().getMCItemStack(), player.field_70170_p.field_72995_K);
                continue;
            }
            this.addSlot(player, slot.getPosX(), slot.getPosY(), slot, player.field_70170_p.field_72995_K);
        }
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (index < this.guiInventory.func_70302_i_() ? !this.func_75135_a(itemstack1, this.guiInventory.func_70302_i_(), this.field_75151_b.size(), true) : !this.func_75135_a(itemstack1, 0, this.guiInventory.func_70302_i_(), false)) {
                return null;
            }
            if (itemstack1.field_77994_a <= 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
        }
        return itemstack;
    }

    void addSlot(EntityPlayer player, int x, int y, IItemSlot slot, boolean clientSide) {
        this.func_75146_a(new CustomGuiSlot(player, this.guiInventory, this.slotCount++, slot, x, y, clientSide));
    }

    void addSlot(EntityPlayer player, int x, int y, IItemSlot slot, ItemStack itemStack, boolean clientSide) {
        this.guiInventory.func_70299_a(this.slotCount, itemStack);
        this.func_75146_a(new CustomGuiSlot(player, this.guiInventory, this.slotCount++, slot, x, y, clientSide));
    }

    void addPlayerInventory(EntityPlayer player, int x, int y) {
        int row;
        this.playerInvX = x;
        this.playerInvY = y;
        for (row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, col + row * 9 + 9, x + col * 18, y + row * 18));
            }
        }
        for (row = 0; row < 9; ++row) {
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, row, x + row * 18, y + 58));
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack func_75144_a(int slotId, int dragType, int clickTypeIn, EntityPlayer player) {
        if (slotId < 0) {
            return super.func_75144_a(slotId, dragType, clickTypeIn, player);
        }
        if (!player.field_70170_p.field_72995_K) {
            IItemSlot slot = null;
            if (this.func_75139_a(slotId) instanceof CustomGuiSlot) {
                slot = ((CustomGuiSlot)this.func_75139_a((int)slotId)).slot;
            }
            if (!EventHooks.onCustomGuiSlotClicked((IPlayer)NpcAPI.Instance().getIEntity((Entity)player), ((ContainerCustomGui)player.field_71070_bA).customGui, slotId, slot, dragType, clickTypeIn)) {
                ItemStack item = super.func_75144_a(slotId, dragType, clickTypeIn, player);
                EntityPlayerMP p = (EntityPlayerMP)player;
                p.func_71120_a((Container)this);
                return item;
            }
        }
        return null;
    }

    public boolean func_94531_b(Slot p_94531_1_) {
        return true;
    }
}

