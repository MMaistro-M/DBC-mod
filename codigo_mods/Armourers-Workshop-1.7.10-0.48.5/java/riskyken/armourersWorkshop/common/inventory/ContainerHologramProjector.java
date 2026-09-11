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
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkinTemplate;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;

public class ContainerHologramProjector
extends Container {
    private final TileEntityHologramProjector tileEntity;

    public ContainerHologramProjector(InventoryPlayer invPlayer, TileEntityHologramProjector tileEntity) {
        this.tileEntity = tileEntity;
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
        this.func_75146_a(new SlotSkinTemplate(tileEntity, 0, 8, 110));
    }

    public boolean func_75145_c(EntityPlayer entityplayer) {
        return this.tileEntity.func_70300_a(entityplayer);
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId > 35 ? !this.func_75135_a(stack, 9, 36, false) && !this.func_75135_a(stack, 0, 9, false) : !this.func_75135_a(stack, 36, 37, false)) {
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

    public TileEntityHologramProjector getTileEntity() {
        return this.tileEntity;
    }
}

