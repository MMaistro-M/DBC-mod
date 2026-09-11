/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package riskyken.armourersWorkshop.common.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.common.inventory.MannequinSlotType;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

public class SlotMannequin
extends SlotHidable {
    private MannequinSlotType slotType;

    public SlotMannequin(MannequinSlotType slotType, IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
        this.slotType = slotType;
    }

    public boolean func_75214_a(ItemStack stack) {
        Item item = stack.func_77973_b();
        switch (this.slotType) {
            case HEAD: {
                if (item instanceof ItemBlock) {
                    return true;
                }
                if (item instanceof ItemArmor && ((ItemArmor)item).field_77881_a == 0) {
                    return true;
                }
                if (!(item instanceof ItemSkin) || ((ItemSkin)item).getSkinType(stack) != SkinTypeRegistry.skinHead) break;
                return true;
            }
            case CHEST: {
                if (item instanceof ItemArmor && ((ItemArmor)item).field_77881_a == 1) {
                    return true;
                }
                if (!(item instanceof ItemSkin) || ((ItemSkin)item).getSkinType(stack) != SkinTypeRegistry.skinChest) break;
                return true;
            }
            case LEGS: {
                if (item instanceof ItemArmor && ((ItemArmor)item).field_77881_a == 2) {
                    return true;
                }
                if (!(item instanceof ItemSkin) || ((ItemSkin)item).getSkinType(stack) != SkinTypeRegistry.skinLegs) break;
                return true;
            }
            case FEET: {
                if (item instanceof ItemArmor && ((ItemArmor)item).field_77881_a == 3) {
                    return true;
                }
                if (!(item instanceof ItemSkin) || ((ItemSkin)item).getSkinType(stack) != SkinTypeRegistry.skinFeet) break;
                return true;
            }
            case LEFT_HAND: {
                return true;
            }
            case RIGHT_HAND: {
                return true;
            }
            case WINGS: {
                if (!(item instanceof ItemSkin) || ((ItemSkin)item).getSkinType(stack) != SkinTypeRegistry.skinWings) break;
                return true;
            }
        }
        return false;
    }

    public IIcon func_75212_b() {
        SkinTypeRegistry str = SkinTypeRegistry.INSTANCE;
        switch (this.slotType) {
            case HEAD: {
                return SkinTypeRegistry.skinHead.getEmptySlotIcon();
            }
            case CHEST: {
                return SkinTypeRegistry.skinChest.getEmptySlotIcon();
            }
            case LEGS: {
                return SkinTypeRegistry.skinLegs.getEmptySlotIcon();
            }
            case FEET: {
                return SkinTypeRegistry.skinFeet.getEmptySlotIcon();
            }
            case LEFT_HAND: {
                return SkinTypeRegistry.skinBow.getEmptySlotIcon();
            }
            case RIGHT_HAND: {
                return SkinTypeRegistry.skinSword.getEmptySlotIcon();
            }
            case WINGS: {
                return SkinTypeRegistry.skinWings.getEmptySlotIcon();
            }
        }
        return super.func_75212_b();
    }
}

