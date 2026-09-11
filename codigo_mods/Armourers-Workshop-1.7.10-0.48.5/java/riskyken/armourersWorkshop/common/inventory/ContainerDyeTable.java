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
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.inventory.ModInventory;
import riskyken.armourersWorkshop.common.inventory.slot.SlotDyeBottle;
import riskyken.armourersWorkshop.common.inventory.slot.SlotDyeableSkin;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityDyeTable;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.UtilPlayer;

public class ContainerDyeTable
extends Container {
    private final InventoryPlayer invPlayer;
    private final TileEntityDyeTable tileEntity;
    private boolean instanced;
    private IInventory inventory;

    public ContainerDyeTable(InventoryPlayer invPlayer, TileEntityDyeTable tileEntity) {
        this.invPlayer = invPlayer;
        this.tileEntity = tileEntity;
        this.instanced = ConfigHandler.instancedDyeTable;
        this.inventory = this.instanced ? new ModInventory("fakeInventory", 10) : tileEntity;
        int playerInvY = 108;
        int hotBarY = playerInvY + 58;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 8 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 8 + 18 * x, playerInvY + y * 18));
            }
        }
        this.func_75146_a(new SlotDyeableSkin(this.inventory, 0, 26, 23, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 1, 68, 36, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 2, 90, 36, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 3, 112, 36, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 4, 134, 36, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 5, 68, 58, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 6, 90, 58, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 7, 112, 58, this));
        this.func_75146_a(new SlotDyeBottle(this.inventory, 8, 134, 58, this));
        this.func_75146_a(new SlotOutput(this.inventory, 9, 26, 69, this));
        ItemStack stack = this.func_75139_a(36).func_75211_c();
        if (stack != null) {
            this.updateLockedSlots(stack);
        }
    }

    public void skinAdded(ItemStack stack) {
        this.updateLockedSlots(stack);
        if (this.tileEntity.func_145831_w().field_72995_K) {
            return;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        ISkinDye dye = skinPointer.getSkinDye();
        this.skinRemoved();
        this.updateLockedSlots(stack);
        this.func_75141_a(45, stack.func_77946_l());
        this.putDyesInSlots();
        this.func_75142_b();
    }

    public void func_75134_a(EntityPlayer entityPlayer) {
        Slot slot;
        super.func_75134_a(entityPlayer);
        if (!this.tileEntity.func_145831_w().field_72995_K & this.instanced && (slot = this.func_75139_a(45)).func_75216_d()) {
            entityPlayer.func_71019_a(slot.func_75211_c(), false);
        }
    }

    public void skinRemoved() {
        if (!this.tileEntity.func_145831_w().field_72995_K) {
            for (int i = 0; i < 8; ++i) {
                SlotDyeBottle slot = (SlotDyeBottle)this.func_75139_a(37 + i);
                if (!slot.isLocked()) {
                    UtilPlayer.giveItem(this.invPlayer.field_70458_d, this.func_75139_a(37 + i).func_75211_c());
                } else {
                    slot.setLocked(false);
                }
                this.func_75141_a(37 + i, null);
            }
            this.func_75141_a(45, null);
            this.func_75142_b();
        }
        this.unlockedSlots();
    }

    private void updateLockedSlots(ItemStack stack) {
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        ISkinDye dye = skinPointer.getSkinDye();
        for (int i = 0; i < 8; ++i) {
            if (dye.haveDyeInSlot(i)) {
                ((SlotDyeBottle)this.func_75139_a(37 + i)).setLocked(true);
                continue;
            }
            ((SlotDyeBottle)this.func_75139_a(37 + i)).setLocked(false);
        }
    }

    private void unlockedSlots() {
        for (int i = 0; i < 8; ++i) {
            ((SlotDyeBottle)this.func_75139_a(37 + i)).setLocked(false);
        }
    }

    private void putDyesInSlots() {
        if (this.tileEntity.func_145831_w().field_72995_K) {
            return;
        }
        ItemStack outStack = this.func_75139_a(45).func_75211_c();
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(outStack);
        ISkinDye dye = skinPointer.getSkinDye();
        for (int i = 0; i < 8; ++i) {
            if (dye.haveDyeInSlot(i)) {
                byte[] rgbt = dye.getDyeColour(i);
                ItemStack bottle = new ItemStack(ModItems.dyeBottle, 1, 1);
                PaintingHelper.setToolPaintColour(bottle, rgbt);
                PaintingHelper.setToolPaint(bottle, PaintType.getPaintTypeFormSKey(rgbt[3]));
                if (dye.hasName(i)) {
                    bottle.func_151001_c(dye.getDyeName(i));
                }
                this.func_75141_a(37 + i, bottle);
                continue;
            }
            this.func_75141_a(37 + i, null);
        }
    }

    public void func_75130_a(IInventory inventory) {
        for (int i = 0; i < 9; ++i) {
            this.func_75141_a(37 + i, null);
        }
        this.func_75141_a(36, null);
        super.func_75130_a(inventory);
    }

    public void dyeAdded(ItemStack dyeStack, int slotId) {
        ItemStack skinStack = this.func_75139_a(45).func_75211_c();
        if (skinStack == null) {
            return;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(skinStack);
        ISkinDye skinDye = skinPointer.getSkinDye();
        byte[] rgbt = PaintingHelper.getToolPaintData(dyeStack);
        String name = null;
        if (dyeStack.func_82837_s()) {
            name = dyeStack.func_82833_r();
        }
        skinDye.addDye(slotId, rgbt, name);
        SkinNBTHelper.addSkinDataToStack(skinStack, skinPointer);
    }

    public void dyeRemoved(int slotId) {
        ItemStack skinStack = this.func_75139_a(45).func_75211_c();
        if (skinStack == null) {
            return;
        }
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(skinStack);
        ISkinDye skinDye = skinPointer.getSkinDye();
        skinDye.removeDye(slotId);
        SkinNBTHelper.addSkinDataToStack(skinStack, skinPointer);
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntity.func_70300_a(player);
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId > 35) {
                if (!this.func_75135_a(stack, 9, 36, false) && !this.func_75135_a(stack, 0, 9, false)) {
                    return null;
                }
            } else {
                SkinPointer sp = SkinNBTHelper.getSkinPointerFromStack(stack);
                if (sp != null && sp.lockSkin | stack.func_77973_b() == ModItems.equipmentSkin) {
                    if (!this.func_75135_a(stack, 36, 37, false)) {
                        return null;
                    }
                } else if (stack.func_77973_b() == ModItems.dyeBottle && this.func_75139_a(36).func_75216_d() & PaintingHelper.getToolHasPaint(stack)) {
                    if (!this.func_75135_a(stack, 37, 45, false)) {
                        return null;
                    }
                } else {
                    return null;
                }
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

