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
import riskyken.armourersWorkshop.common.inventory.ModInventory;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;

public class ContainerSkinnable
extends Container {
    private final TileEntitySkinnable tileEntity;
    private int size;

    public ContainerSkinnable(InventoryPlayer invPlayer, TileEntitySkinnable tileEntity, Skin skin) {
        this.tileEntity = tileEntity;
        boolean ender = SkinProperties.PROP_BLOCK_ENDER_INVENTORY.getValue(skin.getProperties());
        int width = SkinProperties.PROP_BLOCK_INVENTORY_WIDTH.getValue(skin.getProperties());
        int height = SkinProperties.PROP_BLOCK_INVENTORY_HEIGHT.getValue(skin.getProperties());
        ModInventory inventory = tileEntity.getInventory();
        if (ender) {
            width = 9;
            height = 3;
            inventory = invPlayer.field_70458_d.func_71005_bN();
        }
        this.size = width * height;
        int playerInvY = height * 18 + 41;
        int hotBarY = playerInvY + 58;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 8 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, playerInvY + y * 18));
            }
        }
        int guiWidth = 176;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                this.func_75146_a(new Slot((IInventory)inventory, x + y * width, guiWidth / 2 - width * 18 / 2 + 1 + 18 * x, 21 + y * 18));
            }
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return player.func_70092_e((double)this.tileEntity.field_145851_c + 0.5, (double)this.tileEntity.field_145848_d + 0.5, (double)this.tileEntity.field_145849_e + 0.5) <= 64.0;
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId > 35 ? !this.func_75135_a(stack, 9, 36, false) && !this.func_75135_a(stack, 0, 9, false) : !this.func_75135_a(stack, 36, 36 + this.size, false)) {
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
}

