/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.api.handler;

import java.util.List;
import net.minecraft.item.ItemStack;
import noppes.npcs.api.handler.data.IAnvilRecipe;
import noppes.npcs.api.handler.data.IRecipe;

public interface IRecipeHandler {
    public List<IRecipe> getGlobalList();

    public List<IRecipe> getCarpentryList();

    public List<IAnvilRecipe> getAnvilList();

    public void addRecipe(String var1, boolean var2, ItemStack var3, Object ... var4);

    public void addRecipe(String var1, boolean var2, ItemStack var3, int var4, int var5, ItemStack ... var6);

    public IRecipe delete(int var1);

    public IAnvilRecipe deleteAnvil(int var1);

    public void addAnvilRecipe(String var1, boolean var2, ItemStack var3, ItemStack var4, int var5, float var6);
}

