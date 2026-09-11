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
package com.tobiasmjc.dbcadditions.inventory;

import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import com.tobiasmjc.dbcadditions.inventory.CustomSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDBCAPlayer
extends Container {
    private final IInventory customInventory;

    public ContainerDBCAPlayer(IInventory customInventory, EntityPlayer player) {
        int i;
        this.customInventory = customInventory;
        this.func_75146_a(new CustomSlot(customInventory, 0, 111, 36, player));
        this.func_75146_a(new CustomSlot(customInventory, 1, 129, 36, player));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        DBCAPlayer p = DBCAPlayer.get(player);
        if (p.PotaraFusion) {
            return false;
        }
        return this.customInventory.func_70300_a(player);
    }

    public ItemStack func_82846_b(EntityPlayer player, int index) {
        return null;
    }
}

