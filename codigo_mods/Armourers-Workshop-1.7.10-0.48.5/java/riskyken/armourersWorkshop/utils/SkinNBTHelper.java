/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinDye;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;

public class SkinNBTHelper {
    private static final String TAG_OLD_SKIN_DATA = "armourData";
    private static final String TAG_OLD_SKIN_ID = "equpmentId";

    public static boolean stackHasSkinData(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (!stack.func_77942_o()) {
            return false;
        }
        NBTTagCompound itemCompound = stack.func_77978_p();
        return itemCompound.func_74764_b("armourersWorkshop");
    }

    public static boolean compoundHasSkinData(NBTTagCompound compound) {
        if (compound == null) {
            return false;
        }
        return compound.func_74764_b("armourersWorkshop");
    }

    public static void removeSkinDataFromStack(ItemStack stack, boolean overrideLock) {
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return;
        }
        SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
        if (skinData.lockSkin && !overrideLock) {
            return;
        }
        NBTTagCompound itemCompound = stack.func_77978_p();
        if (itemCompound.func_74764_b("armourersWorkshop")) {
            itemCompound.func_82580_o("armourersWorkshop");
        }
    }

    public static SkinPointer getSkinPointerFromStack(ItemStack stack) {
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return null;
        }
        SkinPointer skinData = new SkinPointer();
        skinData.readFromCompound(stack.func_77978_p());
        return skinData;
    }

    public static ISkinType getSkinTypeFromStack(ItemStack stack) {
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return null;
        }
        SkinPointer skinData = new SkinPointer();
        skinData.readFromCompound(stack.func_77978_p());
        return skinData.getIdentifier().getSkinType();
    }

    public static int getSkinIdFromStack(ItemStack stack) {
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return -1;
        }
        SkinPointer skinData = new SkinPointer();
        skinData.readFromCompound(stack.func_77978_p());
        return skinData.getIdentifier().getSkinLocalId();
    }

    public static boolean isSkinLockedOnStack(ItemStack stack) {
        if (!SkinNBTHelper.stackHasSkinData(stack)) {
            return false;
        }
        SkinPointer skinData = new SkinPointer();
        skinData.readFromCompound(stack.func_77978_p());
        return skinData.lockSkin;
    }

    public static void addSkinDataToStack(ItemStack stack, SkinIdentifier identifier, ISkinDye skinDye, boolean lockSkin) {
        SkinPointer skinData = new SkinPointer(identifier, skinDye, lockSkin);
        SkinNBTHelper.addSkinDataToStack(stack, skinData);
    }

    public static void addSkinDataToStack(ItemStack stack, SkinIdentifier identifier, boolean lockSkin, ISkinDye skinDye) {
        SkinPointer skinData = skinDye != null ? new SkinPointer(identifier, skinDye, lockSkin) : new SkinPointer(identifier, lockSkin);
        SkinNBTHelper.addSkinDataToStack(stack, skinData);
    }

    public static void addSkinDataToStack(ItemStack stack, SkinPointer skinPointer) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        skinPointer.writeToCompound(stack.func_77978_p());
    }

    public static boolean stackHasLegacySkinData(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (!stack.func_77942_o()) {
            return false;
        }
        NBTTagCompound itemCompound = stack.func_77978_p();
        if (!itemCompound.func_74764_b(TAG_OLD_SKIN_DATA)) {
            return false;
        }
        NBTTagCompound skinDataCompound = itemCompound.func_74775_l(TAG_OLD_SKIN_DATA);
        return skinDataCompound.func_74764_b(TAG_OLD_SKIN_ID);
    }

    public static int getLegacyIdFromStack(ItemStack stack) {
        if (stack == null) {
            return -1;
        }
        if (!stack.func_77942_o()) {
            return -1;
        }
        NBTTagCompound itemCompound = stack.func_77978_p();
        if (!itemCompound.func_74764_b(TAG_OLD_SKIN_DATA)) {
            return -1;
        }
        NBTTagCompound skinDataCompound = itemCompound.func_74775_l(TAG_OLD_SKIN_DATA);
        if (!skinDataCompound.func_74764_b(TAG_OLD_SKIN_ID)) {
            return -1;
        }
        return skinDataCompound.func_74762_e(TAG_OLD_SKIN_ID);
    }

    public static ItemStack makeEquipmentSkinStack(Skin skin, ISkinDye skinDye) {
        ItemStack stack = new ItemStack(ModItems.equipmentSkin, 1);
        stack.func_77982_d(new NBTTagCompound());
        SkinNBTHelper.addSkinDataToStack(stack, new SkinIdentifier(skin), false, skinDye);
        return stack;
    }

    public static ItemStack makeEquipmentSkinStack(Skin skin, SkinIdentifier identifier) {
        ItemStack stack = new ItemStack(ModItems.equipmentSkin, 1);
        stack.func_77982_d(new NBTTagCompound());
        SkinNBTHelper.addSkinDataToStack(stack, identifier, false, null);
        return stack;
    }

    public static ItemStack makeEquipmentSkinStack(Skin skin) {
        ItemStack stack = new ItemStack(ModItems.equipmentSkin, 1);
        stack.func_77982_d(new NBTTagCompound());
        SkinNBTHelper.addSkinDataToStack(stack, new SkinIdentifier(skin), false, null);
        return stack;
    }

    public static ItemStack makeEquipmentSkinStack(SkinPointer skinPointer) {
        ItemStack stack = new ItemStack(ModItems.equipmentSkin, 1);
        stack.func_77982_d(new NBTTagCompound());
        SkinNBTHelper.addSkinDataToStack(stack, skinPointer.getIdentifier(), false, new SkinDye(skinPointer.getSkinDye()));
        return stack;
    }

    public static ItemStack makeArmouerContainerStack(Skin skin) {
        ItemStack stack = new ItemStack(ModItems.armourContainer[skin.getSkinType().getVanillaArmourSlotId()], 1);
        stack.func_77982_d(new NBTTagCompound());
        SkinNBTHelper.addSkinDataToStack(stack, new SkinIdentifier(skin), false, null);
        return stack;
    }

    public static ItemStack makeArmouerContainerStack(SkinPointer skinPointer) {
        ItemStack stack = new ItemStack(ModItems.armourContainer[skinPointer.getIdentifier().getSkinType().getVanillaArmourSlotId()], 1);
        stack.func_77982_d(new NBTTagCompound());
        SkinNBTHelper.addSkinDataToStack(stack, skinPointer.getIdentifier(), false, new SkinDye(skinPointer.getSkinDye()));
        return stack;
    }

    public static void addSkinPointerToStack(ItemStack stack, SkinPointer skinPointer) {
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (!skinData.lockSkin && !skinData.getIdentifier().equals(skinPointer.getIdentifier()) | !skinData.skinDye.equals(skinPointer.getSkinDye())) {
                SkinNBTHelper.addSkinDataToStack(stack, skinPointer);
            }
        } else {
            SkinNBTHelper.addSkinDataToStack(stack, skinPointer);
        }
    }

    public static void removeRenderIdFromStack(ItemStack stack) {
        SkinNBTHelper.removeSkinDataFromStack(stack, false);
    }
}

