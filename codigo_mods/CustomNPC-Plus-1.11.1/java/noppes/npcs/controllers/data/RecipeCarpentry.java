/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.controllers.data;

import java.io.IOException;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.IRecipe;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.controllers.data.RecipeScript;

public class RecipeCarpentry
extends ShapedRecipes
implements IRecipe {
    public int id = -1;
    public String name = "";
    public Availability availability = new Availability();
    public boolean isGlobal = false;
    public boolean ignoreDamage = false;
    public boolean ignoreNBT = false;

    public RecipeCarpentry(int width, int height, ItemStack[] recipe, ItemStack result) {
        super(width, height, recipe, result);
    }

    public RecipeCarpentry(String name) {
        super(0, 0, new ItemStack[0], null);
        this.name = name;
    }

    public static RecipeCarpentry create(NBTTagCompound compound) {
        return new RecipeCarpentry(compound.func_74762_e("Width"), compound.func_74762_e("Height"), NBTTags.getItemStackArray(compound.func_150295_c("Materials", 10)), NoppesUtilServer.readItem(compound.func_74775_l("Item")));
    }

    public void readNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("Name");
        this.id = compound.func_74762_e("ID");
        this.availability.readFromNBT(compound.func_74775_l("Availability"));
        this.ignoreDamage = compound.func_74767_n("IgnoreDamage");
        this.ignoreNBT = compound.func_74767_n("IgnoreNBT");
        this.isGlobal = compound.func_74767_n("Global");
        if (!this.isGlobal && compound.func_150297_b("ScriptData", 10)) {
            RecipeScript handler = new RecipeScript();
            handler.readFromNBT(compound.func_74775_l("ScriptData"));
            this.setScriptHandler(handler);
        }
    }

    public NBTTagCompound writeNBT() {
        return this.writeNBT(true);
    }

    public NBTTagCompound writeNBT(boolean saveScripts) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74768_a("ID", this.id);
        compound.func_74768_a("Width", this.field_77576_b);
        compound.func_74768_a("Height", this.field_77577_c);
        if (this.func_77571_b() != null) {
            compound.func_74782_a("Item", (NBTBase)NoppesUtilServer.writeItem(this.func_77571_b(), new NBTTagCompound()));
        }
        compound.func_74782_a("Materials", (NBTBase)NBTTags.nbtItemStackArray(this.field_77574_d));
        compound.func_74782_a("Availability", (NBTBase)this.availability.writeToNBT(new NBTTagCompound()));
        compound.func_74778_a("Name", this.name);
        compound.func_74757_a("Global", this.isGlobal);
        compound.func_74757_a("IgnoreDamage", this.ignoreDamage);
        compound.func_74757_a("IgnoreNBT", this.ignoreNBT);
        if (saveScripts) {
            NBTTagCompound scriptData = new NBTTagCompound();
            RecipeScript handler = this.getScriptHandler();
            if (handler != null) {
                handler.writeToNBT(scriptData);
            }
            compound.func_74782_a("ScriptData", (NBTBase)scriptData);
        }
        return compound;
    }

    public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World world) {
        for (int i = 0; i <= 4 - this.field_77576_b; ++i) {
            for (int j = 0; j <= 4 - this.field_77577_c; ++j) {
                if (this.checkMatch(par1InventoryCrafting, i, j, true)) {
                    return true;
                }
                if (!this.checkMatch(par1InventoryCrafting, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    private boolean checkMatch(InventoryCrafting par1InventoryCrafting, int par2, int par3, boolean par4) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                ItemStack var10;
                int var7 = i - par2;
                int var8 = j - par3;
                ItemStack var9 = null;
                if (var7 >= 0 && var8 >= 0 && var7 < this.field_77576_b && var8 < this.field_77577_c) {
                    var9 = par4 ? this.field_77574_d[this.field_77576_b - var7 - 1 + var8 * this.field_77576_b] : this.field_77574_d[var7 + var8 * this.field_77576_b];
                }
                if ((var10 = par1InventoryCrafting.func_70463_b(i, j)) == null && var9 == null || NoppesUtilPlayer.compareItems(var9, var10, this.ignoreDamage, this.ignoreNBT)) continue;
                return false;
            }
        }
        return true;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        if (this.func_77571_b() == null) {
            return null;
        }
        return this.func_77571_b().func_77946_l();
    }

    public int func_77570_a() {
        return 16;
    }

    public static RecipeCarpentry saveRecipe(RecipeCarpentry recipe, ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
        int var9;
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        if (par2ArrayOfObj[var4] instanceof String[]) {
            String[] var7;
            String[] var8 = var7 = (String[])par2ArrayOfObj[var4++];
            var9 = var7.length;
            for (int var10 = 0; var10 < var9; ++var10) {
                String var11 = var8[var10];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        } else {
            while (par2ArrayOfObj[var4] instanceof String) {
                String var13 = (String)par2ArrayOfObj[var4++];
                ++var6;
                var5 = var13.length();
                var3 = var3 + var13;
            }
        }
        HashMap<Character, ItemStack> var14 = new HashMap<Character, ItemStack>();
        while (var4 < par2ArrayOfObj.length) {
            Character var16 = (Character)par2ArrayOfObj[var4];
            ItemStack var17 = null;
            if (par2ArrayOfObj[var4 + 1] instanceof Item) {
                var17 = new ItemStack((Item)par2ArrayOfObj[var4 + 1]);
            } else if (par2ArrayOfObj[var4 + 1] instanceof Block) {
                var17 = new ItemStack((Block)par2ArrayOfObj[var4 + 1], 1, -1);
            } else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack) {
                var17 = (ItemStack)par2ArrayOfObj[var4 + 1];
            }
            var14.put(var16, var17);
            var4 += 2;
        }
        ItemStack[] var15 = new ItemStack[var5 * var6];
        for (var9 = 0; var9 < var5 * var6; ++var9) {
            char var18 = var3.charAt(var9);
            var15[var9] = var14.containsKey(Character.valueOf(var18)) ? ((ItemStack)var14.get(Character.valueOf(var18))).func_77946_l() : null;
        }
        RecipeCarpentry newrecipe = new RecipeCarpentry(var5, var6, var15, par1ItemStack);
        newrecipe.copy(recipe);
        if (var5 == 4 || var6 == 4) {
            newrecipe.isGlobal = false;
        }
        return newrecipe;
    }

    public void copy(RecipeCarpentry recipe) {
        this.id = recipe.id;
        this.name = recipe.name;
        this.availability = recipe.availability;
        this.isGlobal = recipe.isGlobal;
        this.ignoreDamage = recipe.ignoreDamage;
        this.ignoreNBT = recipe.ignoreNBT;
    }

    public ItemStack getCraftingItem(int i) {
        if (this.field_77574_d == null || i >= this.field_77574_d.length) {
            return null;
        }
        return this.field_77574_d[i];
    }

    public void setCraftingItem(int i, ItemStack item) {
        if (i < this.field_77574_d.length) {
            this.field_77574_d[i] = item;
        }
    }

    public boolean isValid() {
        if (this.field_77574_d.length == 0 || this.func_77571_b() == null) {
            return false;
        }
        for (ItemStack item : this.field_77574_d) {
            if (item == null) continue;
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getResult() {
        return this.func_77571_b();
    }

    @Override
    public boolean isGlobal() {
        return this.isGlobal;
    }

    @Override
    public void setIsGlobal(boolean bo) {
        this.isGlobal = bo;
    }

    @Override
    public boolean getIgnoreNBT() {
        return this.ignoreNBT;
    }

    @Override
    public void setIgnoreNBT(boolean bo) {
        this.ignoreNBT = bo;
    }

    @Override
    public boolean getIgnoreDamage() {
        return this.ignoreDamage;
    }

    @Override
    public void setIgnoreDamage(boolean bo) {
        this.ignoreDamage = bo;
    }

    @Override
    public int getWidth() {
        return this.field_77576_b;
    }

    @Override
    public int getHeight() {
        return this.field_77577_c;
    }

    public void save() {
        try {
            RecipeController.Instance.saveRecipe(this.writeNBT(true));
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    @Override
    public void delete() {
        RecipeController.Instance.delete(this.id);
    }

    @Override
    public ItemStack[] getRecipe() {
        return this.field_77574_d;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public RecipeScript getScriptHandler() {
        return RecipeController.Instance.carpentryScripts.get(this.id);
    }

    public void setScriptHandler(RecipeScript handler) {
        RecipeController.Instance.carpentryScripts.put(this.id, handler);
    }

    public RecipeScript getOrCreateScriptHandler() {
        RecipeScript data = this.getScriptHandler();
        if (data == null) {
            data = new RecipeScript();
            this.setScriptHandler(data);
        }
        return data;
    }
}

