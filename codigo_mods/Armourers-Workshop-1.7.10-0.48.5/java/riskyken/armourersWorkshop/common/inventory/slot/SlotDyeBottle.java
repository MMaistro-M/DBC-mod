/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.inventory.ContainerDyeTable;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class SlotDyeBottle
extends Slot {
    private final ContainerDyeTable container;
    private boolean locked;

    public SlotDyeBottle(IInventory inventory, int slotIndex, int xPosition, int yPosition, ContainerDyeTable container) {
        super(inventory, slotIndex, xPosition, yPosition);
        this.container = container;
        this.locked = false;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public boolean func_75214_a(ItemStack stack) {
        ItemStack skinStack = this.field_75224_c.func_70301_a(0);
        return skinStack != null && SkinNBTHelper.stackHasSkinData(skinStack) && stack.func_77973_b() == ModItems.dyeBottle && PaintingHelper.getToolHasPaint(stack);
    }

    public boolean func_82869_a(EntityPlayer player) {
        if (!ConfigHandler.lockDyesOnSkins) {
            return true;
        }
        return !this.locked;
    }

    public void func_75218_e() {
        ItemStack stack = this.func_75211_c();
        if (stack == null) {
            this.container.dyeRemoved(this.getSlotIndex() - 1);
        } else if (stack.func_77973_b() == ModItems.dyeBottle) {
            this.container.dyeAdded(stack, this.getSlotIndex() - 1);
        }
        super.func_75218_e();
    }

    public IIcon func_75212_b() {
        return ClientProxy.dyeBottleSlotIcon;
    }
}

