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
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkinTemplate;
import riskyken.armourersWorkshop.common.items.ItemArmourContainerItem;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.items.ItemSkinTemplate;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

public class ContainerArmourer
extends Container {
    private TileEntityArmourer armourerBrain;

    public ContainerArmourer(InventoryPlayer invPlayer, TileEntityArmourer armourerBrain) {
        this.armourerBrain = armourerBrain;
        this.func_75146_a(new SlotSkinTemplate(armourerBrain, 0, 64, 21));
        this.func_75146_a(new SlotOutput(armourerBrain, 1, 147, 21));
        int playerInvY = 142;
        int hotBarY = playerInvY + 58;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new SlotHidable((IInventory)invPlayer, x, 8 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new SlotHidable((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, playerInvY + y * 18));
            }
        }
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotID) {
        Slot slot = this.func_75139_a(slotID);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotID < 2) {
                if (!this.func_75135_a(stack, 11, 38, false) && !this.func_75135_a(stack, 2, 11, false)) {
                    return null;
                }
            } else if (stack.func_77973_b() instanceof ItemSkinTemplate & stack.func_77960_j() == 0 | stack.func_77973_b() instanceof ItemSkin | stack.func_77973_b() instanceof ItemArmourContainerItem) {
                if (!this.func_75135_a(stack, 0, 1, false)) {
                    return null;
                }
            } else {
                return null;
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
        return this.armourerBrain.func_70300_a(player);
    }

    public TileEntityArmourer getTileEntity() {
        return this.armourerBrain;
    }
}

