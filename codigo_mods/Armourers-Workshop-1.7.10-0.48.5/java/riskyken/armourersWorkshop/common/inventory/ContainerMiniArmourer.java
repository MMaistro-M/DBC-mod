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
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkinTemplate;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;

public class ContainerMiniArmourer
extends Container {
    private TileEntityMiniArmourer tileEntity;

    public ContainerMiniArmourer(InventoryPlayer invPlayer, TileEntityMiniArmourer tileEntity) {
        this.tileEntity = tileEntity;
        this.func_75146_a(new SlotSkinTemplate(tileEntity, 0, 37, 58));
        this.func_75146_a(new SlotOutput(tileEntity, 1, 119, 58));
        int hotBarY = 152;
        int playerInvY = 94;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 8 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, playerInvY + y * 18));
            }
        }
    }

    public ItemStack func_82846_b(EntityPlayer entityPlayer, int slotID) {
        return null;
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.tileEntity.func_70300_a(entityPlayer);
    }

    public TileEntityMiniArmourer getTileEntity() {
        return this.tileEntity;
    }
}

