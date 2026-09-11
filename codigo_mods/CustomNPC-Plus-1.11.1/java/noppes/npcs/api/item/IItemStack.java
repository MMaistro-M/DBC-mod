/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.INbt;

public interface IItemStack {
    public String getName();

    public int getStackSize();

    public boolean hasCustomName();

    public void setCustomName(String var1);

    public String getDisplayName();

    public String getItemName();

    public void setStackSize(int var1);

    public int getMaxStackSize();

    public int getItemDamage();

    public void setItemDamage(int var1);

    public void setTag(String var1, Object var2);

    public boolean hasTag(String var1);

    public Object getTag(String var1);

    public INbt removeTags();

    public boolean isEnchanted();

    public boolean hasEnchant(int var1);

    public void addEnchant(int var1, int var2);

    public void setAttribute(String var1, double var2);

    public double getAttribute(String var1);

    public boolean hasAttribute(String var1);

    public void setCustomAttribute(String var1, double var2);

    public boolean hasCustomAttribute(String var1);

    public float getCustomAttribute(String var1);

    public void removeCustomAttribute(String var1);

    public void setMagicAttribute(String var1, int var2, double var3);

    public boolean hasMagicAttribute(String var1, int var2);

    public float getMagicAttribute(String var1, int var2);

    public void removeMagicAttribute(String var1, int var2);

    public void setRequirement(String var1, Object var2);

    public boolean hasRequirement(String var1);

    public Object getRequirement(String var1);

    public void removeRequirement(String var1);

    public String[] getCustomAttributeKeys();

    public String[] getMagicAttributeKeys(String var1);

    public String[] getRequirementKeys();

    public String[] getLore();

    public boolean hasLore();

    public void setLore(String[] var1);

    public IItemStack copy();

    public int getMaxItemDamage();

    public boolean isWrittenBook();

    public String getBookTitle();

    public String getBookAuthor();

    public String[] getBookText();

    public boolean isBlock();

    public INbt getNbt();

    public INbt getItemNbt();

    public ItemStack getMCItemStack();

    public int itemHash();

    public NBTTagCompound getMCNbt();

    public void setMCNbt(NBTTagCompound var1);

    public boolean compare(IItemStack var1, boolean var2);

    public boolean compare(IItemStack var1, boolean var2, boolean var3);
}

