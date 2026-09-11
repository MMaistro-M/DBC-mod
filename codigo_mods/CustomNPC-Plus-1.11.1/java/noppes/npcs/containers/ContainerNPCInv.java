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
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.containers.SlotNPCArmor;
import noppes.npcs.entity.EntityNPCInterface;

public class ContainerNPCInv
extends Container {
    public ContainerNPCInv(EntityNPCInterface npc, EntityPlayer player) {
        for (int l = 0; l < 4; ++l) {
            this.func_75146_a(new SlotNPCArmor(npc.inventory, l, 9, 22 + l * 18, l));
        }
        this.func_75146_a(new Slot((IInventory)npc.inventory, 4, 81, 22));
        this.func_75146_a(new Slot((IInventory)npc.inventory, 5, 81, 40));
        this.func_75146_a(new Slot((IInventory)npc.inventory, 6, 81, 58));
        for (int c = 0; c < 4; ++c) {
            for (int r = 0; r < 9; ++r) {
                this.func_75146_a(new Slot((IInventory)npc.inventory, c * 9 + r + 7, 191 + c % 2 * 90, 16 + r * 21));
            }
        }
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int l1 = 0; l1 < 9; ++l1) {
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, l1 + i1 * 9 + 9, l1 * 18 + 8, 113 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1) {
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, j1, j1 * 18 + 8, 171));
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int i) {
        return null;
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return true;
    }
}

