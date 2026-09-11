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
import riskyken.armourersWorkshop.common.inventory.WardrobeInventory;
import riskyken.armourersWorkshop.common.inventory.WardrobeInventoryContainer;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkin;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ContainerSkinWardrobe
extends Container {
    private ExPropsPlayerSkinData customEquipmentData;
    private int slotsUnlocked;
    private int indexSkinsStart = 0;
    private int indexSkinsEnd = 0;
    private int indexOutfitStart = 0;
    private int indexOutfitEnd = 0;

    public ContainerSkinWardrobe(InventoryPlayer invPlayer, ExPropsPlayerSkinData customEquipmentData) {
        int i;
        this.customEquipmentData = customEquipmentData;
        EquipmentWardrobeData ewd = customEquipmentData.getEquipmentWardrobeData();
        WardrobeInventoryContainer wardrobeInvContainer = customEquipmentData.getWardrobeInventoryContainer();
        WardrobeInventory headInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinHead);
        WardrobeInventory chestInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinChest);
        WardrobeInventory legsInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinLegs);
        WardrobeInventory feetInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinFeet);
        WardrobeInventory wingInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinWings);
        WardrobeInventory swordInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinSword);
        WardrobeInventory shieldInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinShield);
        WardrobeInventory bowInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinBow);
        WardrobeInventory pickaxeInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinPickaxe);
        WardrobeInventory axeInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinAxe);
        WardrobeInventory shovelInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinShovel);
        WardrobeInventory hoeInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinHoe);
        WardrobeInventory outfitInv = wardrobeInvContainer.getInventoryForSkinType(SkinTypeRegistry.skinOutfit);
        for (i = 0; i < 10; ++i) {
            if (i < ewd.getUnlockedSlotsForSkinType(SkinTypeRegistry.skinHead)) {
                this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinHead, headInv, i, 83 + i * 19, 27));
                ++this.indexSkinsEnd;
            }
            if (i < ewd.getUnlockedSlotsForSkinType(SkinTypeRegistry.skinChest)) {
                this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinChest, chestInv, i, 83 + i * 19, 46));
                ++this.indexSkinsEnd;
            }
            if (i < ewd.getUnlockedSlotsForSkinType(SkinTypeRegistry.skinLegs)) {
                this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinLegs, legsInv, i, 83 + i * 19, 65));
                ++this.indexSkinsEnd;
            }
            if (i < ewd.getUnlockedSlotsForSkinType(SkinTypeRegistry.skinFeet)) {
                this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinFeet, feetInv, i, 83 + i * 19, 84));
                ++this.indexSkinsEnd;
            }
            if (i >= ewd.getUnlockedSlotsForSkinType(SkinTypeRegistry.skinWings)) continue;
            this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinWings, wingInv, i, 83 + i * 19, 103));
            ++this.indexSkinsEnd;
        }
        this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinSword, swordInv, 0, 83, 122));
        this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinBow, bowInv, 0, 121, 122));
        this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinPickaxe, pickaxeInv, 0, 159, 122));
        this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinAxe, axeInv, 0, 178, 122));
        this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinShovel, shovelInv, 0, 197, 122));
        this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinHoe, hoeInv, 0, 216, 122));
        this.indexSkinsEnd += 6;
        this.indexOutfitStart = this.indexSkinsEnd;
        this.indexOutfitEnd = this.indexSkinsEnd;
        for (i = 0; i < 10; ++i) {
            if (i >= ewd.getUnlockedSlotsForSkinType(SkinTypeRegistry.skinOutfit)) continue;
            this.func_75146_a(new SlotSkin(SkinTypeRegistry.skinOutfit, outfitInv, i, 83 + i * 19, 27));
            ++this.indexOutfitEnd;
        }
        int playerInvX = 59;
        int playerInvY = 158;
        int hotBarY = playerInvY + 58;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new SlotHidable((IInventory)invPlayer, x, playerInvX + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new SlotHidable((IInventory)invPlayer, x + y * 9 + 9, playerInvX + 18 * x, playerInvY + y * 18));
            }
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return !player.field_70128_L & this.customEquipmentData.getPlayer().equals((Object)player);
    }

    public int getIndexSkinsStart() {
        return this.indexSkinsStart;
    }

    public int getIndexSkinsEnd() {
        return this.indexSkinsEnd;
    }

    public int getIndexOutfitStart() {
        return this.indexOutfitStart;
    }

    public int getIndexOutfitEnd() {
        return this.indexOutfitEnd;
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId < this.indexOutfitEnd) {
                if (!this.func_75135_a(stack, this.indexOutfitEnd + 9, this.indexOutfitEnd + 36, false) && !this.func_75135_a(stack, this.indexOutfitEnd, this.indexOutfitEnd + 9, false)) {
                    return null;
                }
            } else if (stack.func_77973_b() instanceof ItemSkin & SkinNBTHelper.stackHasSkinData(stack)) {
                boolean slotted = false;
                for (int i = 0; i < this.indexOutfitEnd; ++i) {
                    Slot targetSlot = this.func_75139_a(i);
                    if (!targetSlot.func_75214_a(stack) || !this.func_75135_a(stack, i, i + 1, false)) continue;
                    slotted = true;
                    break;
                }
                if (!slotted) {
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
}

