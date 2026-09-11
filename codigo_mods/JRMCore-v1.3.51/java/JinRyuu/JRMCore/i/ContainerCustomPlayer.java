/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package JinRyuu.JRMCore.i;

import JinRyuu.JRMCore.i.InventoryCustomPlayer;
import JinRyuu.JRMCore.i.SlotArmor;
import JinRyuu.JRMCore.i.SlotCustom;
import JinRyuu.JRMCore.items.ItemBodysuit;
import JinRyuu.JRMCore.items.ItemHeadwear;
import JinRyuu.JRMCore.items.ItemVanity;
import JinRyuu.JRMCore.items.ItemWeight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerCustomPlayer
extends Container {
    private static final int ARMOR_START = 11;
    private static final int ARMOR_END = 14;
    private static final int INV_START = 15;
    private static final int INV_END = 41;
    private static final int HOTBAR_START = 42;
    private static final int HOTBAR_END = 50;

    public ContainerCustomPlayer(EntityPlayer player, InventoryPlayer inventoryPlayer, InventoryCustomPlayer inventoryCustom) {
        int i;
        int i2;
        this.func_75146_a(new SlotCustom(inventoryCustom, 0, 80, 62));
        this.func_75146_a(new SlotCustom(inventoryCustom, 1, 80, 44));
        this.func_75146_a(new SlotCustom(inventoryCustom, 2, 80, 26));
        for (i2 = 0; i2 < 4; ++i2) {
            this.func_75146_a(new SlotCustom(inventoryCustom, i2 + 3, 136, 62 - i2 * 18));
        }
        for (i2 = 4; i2 < 8; ++i2) {
            this.func_75146_a(new SlotCustom(inventoryCustom, i2 + 3, 154, 62 - (i2 - 4) * 18));
        }
        for (i = 0; i < 4; ++i) {
            this.func_75146_a(new SlotArmor(player, (IInventory)inventoryPlayer, inventoryPlayer.func_70302_i_() - 1 - i, 8, 8 + i * 18, i));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return true;
    }

    public ItemStack func_82846_b(EntityPlayer player, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            int type;
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < 15) {
                if (!this.func_75135_a(itemstack1, 15, 51, true)) {
                    return null;
                }
                slot.func_75220_a(itemstack1, itemstack);
            } else if (itemstack1.func_77973_b() instanceof ItemWeight ? !this.func_75135_a(itemstack1, 0, 11, false) : (itemstack1.func_77973_b() instanceof ItemBodysuit ? !this.func_75135_a(itemstack1, 1, 11, false) : (itemstack1.func_77973_b() instanceof ItemHeadwear ? !this.func_75135_a(itemstack1, 2, 11, false) : (itemstack1.func_77973_b() instanceof ItemVanity ? !this.func_75135_a(itemstack1, 3, 11, false) : (itemstack1.func_77973_b() instanceof ItemArmor ? !this.func_75135_a(itemstack1, 11 + (type = ((ItemArmor)itemstack1.func_77973_b()).field_77881_a), 11 + type + 1, false) : (par2 >= 15 && par2 < 42 ? !this.func_75135_a(itemstack1, 42, 43, false) : par2 >= 42 && par2 < 51 && !this.func_75135_a(itemstack1, 15, 42, false))))))) {
                return null;
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            } else {
                slot.func_75218_e();
            }
            if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                return null;
            }
            slot.func_82870_a(player, itemstack1);
        }
        return itemstack;
    }
}

