/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.api.handler.data;

import net.minecraft.item.ItemStack;

public interface IAnvilRecipe {
    public String getName();

    public int getXpCost();

    public float getRepairPercentage();

    public boolean matches(ItemStack var1, ItemStack var2);

    public ItemStack getResult(ItemStack var1);

    public int getID();
}

