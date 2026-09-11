/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.tobiasmjc.dbcadditions.inventory;

import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.items.ItemPotara;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CustomSlot
extends Slot {
    IInventory inv;
    private EntityPlayer player;

    public CustomSlot(IInventory inventory, int slotIndex, int x, int y, EntityPlayer p) {
        super(inventory, slotIndex, x, y);
        this.inv = inventory;
        this.player = p;
    }

    public int func_75219_a() {
        return 1;
    }

    public boolean func_82869_a(EntityPlayer p_82869_1_) {
        DBCAPlayer p = DBCAPlayer.get(p_82869_1_);
        if (p.PotaraFusion) {
            return false;
        }
        return super.func_82869_a(p_82869_1_);
    }

    public boolean func_75214_a(ItemStack stack) {
        DBCAPlayer p = DBCAPlayer.get(this.player);
        if (p.PotaraFusion) {
            return false;
        }
        if (this.getSlotIndex() == 0 || this.getSlotIndex() == 1) {
            return stack.func_77973_b() instanceof ItemPotara;
        }
        return false;
    }
}

