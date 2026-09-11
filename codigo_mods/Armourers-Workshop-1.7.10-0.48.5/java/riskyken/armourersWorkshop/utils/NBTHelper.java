/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package riskyken.armourersWorkshop.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public final class NBTHelper {
    private static final String TAG_SLOT = "slot";

    private NBTHelper() {
    }

    public static NBTTagCompound getNBTForStack(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        return stack.func_77978_p();
    }

    public static boolean stackHasKey(ItemStack stack, String key) {
        if (stack == null) {
            return false;
        }
        if (!stack.func_77942_o()) {
            return false;
        }
        return stack.func_77978_p().func_74764_b(key);
    }

    public static void writeStackArrayToNBT(NBTTagCompound compound, String key, ItemStack[] itemStacks) {
        NBTTagList items = new NBTTagList();
        for (int i = 0; i < itemStacks.length; ++i) {
            ItemStack stack = itemStacks[i];
            if (stack == null) continue;
            NBTTagCompound item = new NBTTagCompound();
            item.func_74774_a(TAG_SLOT, (byte)i);
            stack.func_77955_b(item);
            items.func_74742_a((NBTBase)item);
        }
        compound.func_74782_a(key, (NBTBase)items);
    }

    public static void readStackArrayFromNBT(NBTTagCompound compound, String key, ItemStack[] itemStacks) {
        NBTTagList items = compound.func_150295_c(key, 10);
        for (int i = 0; i < items.func_74745_c(); ++i) {
            NBTTagCompound item = items.func_150305_b(i);
            byte slot = item.func_74771_c(TAG_SLOT);
            if (slot < 0 || slot >= itemStacks.length) continue;
            itemStacks[slot] = ItemStack.func_77949_a((NBTTagCompound)item);
        }
    }
}

