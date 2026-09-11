/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import kamkeel.npcs.controllers.SyncController;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcs;
import noppes.npcs.api.handler.IRecipeHandler;
import noppes.npcs.api.handler.data.IAnvilRecipe;
import noppes.npcs.api.handler.data.IRecipe;
import noppes.npcs.controllers.data.RecipeAnvil;
import noppes.npcs.controllers.data.RecipeCarpentry;
import noppes.npcs.controllers.data.RecipeScript;
import noppes.npcs.controllers.data.RecipesDefault;

public class RecipeController
implements IRecipeHandler {
    private static Collection<RecipeCarpentry> prevRecipes;
    public HashMap<Integer, RecipeCarpentry> globalRecipes = new HashMap();
    public HashMap<Integer, RecipeCarpentry> carpentryRecipes = new HashMap();
    public HashMap<Integer, RecipeAnvil> anvilRecipes = new HashMap();
    public HashMap<Integer, RecipeScript> carpentryScripts = new HashMap();
    public HashMap<Integer, RecipeScript> anvilScripts = new HashMap();
    public static RecipeController Instance;
    public static final int version = 1;
    public int nextId = 1;
    public int nextAnvilId = 1;
    public static HashMap<Integer, RecipeCarpentry> syncRecipes;
    public static HashMap<Integer, RecipeAnvil> syncAnvilRecipes;

    public RecipeController() {
        Instance = this;
    }

    public void load() {
        this.loadCategories();
        RecipeController.reloadGlobalRecipes(this.globalRecipes);
    }

    public static void reloadGlobalRecipes(HashMap<Integer, RecipeCarpentry> globalRecipes) {
        List list = CraftingManager.func_77594_a().func_77592_b();
        if (prevRecipes != null) {
            list.removeAll(prevRecipes);
        }
        prevRecipes = new HashSet<RecipeCarpentry>();
        for (RecipeCarpentry recipe : globalRecipes.values()) {
            if (!recipe.isValid()) continue;
            prevRecipes.add(recipe);
        }
        list.addAll(prevRecipes);
    }

    private void loadCategories() {
        File saveDir = CustomNpcs.getWorldSaveDirectory();
        try {
            File file = new File(saveDir, "recipes.dat");
            if (file.exists()) {
                this.loadCategories(file);
            } else {
                this.globalRecipes.clear();
                this.carpentryRecipes.clear();
                this.anvilRecipes.clear();
                this.loadDefaultRecipes(-1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                File file = new File(saveDir, "recipes.dat_old");
                if (file.exists()) {
                    this.loadCategories(file);
                }
            }
            catch (Exception ee) {
                e.printStackTrace();
            }
        }
    }

    private void loadDefaultRecipes(int i) {
        if (i == 1) {
            return;
        }
        RecipesDefault.loadDefaultRecipes(i);
        this.saveCategories();
    }

    private void loadCategories(File file) throws Exception {
        NBTTagCompound nbttagcompound1;
        try (FileInputStream fis = new FileInputStream(file);){
            nbttagcompound1 = CompressedStreamTools.func_74796_a((InputStream)fis);
        }
        this.nextId = nbttagcompound1.func_74762_e("LastId");
        this.nextAnvilId = nbttagcompound1.func_74762_e("LastAnvilId");
        NBTTagList list = nbttagcompound1.func_150295_c("Data", 10);
        HashMap<Integer, RecipeCarpentry> globalRecipes = new HashMap<Integer, RecipeCarpentry>();
        HashMap<Integer, RecipeCarpentry> carpentryRecipes = new HashMap<Integer, RecipeCarpentry>();
        HashMap<Integer, RecipeAnvil> anvilRecipes = new HashMap<Integer, RecipeAnvil>();
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound compound = list.func_150305_b(i);
                if (compound.func_74764_b("IsAnvil")) {
                    RecipeAnvil anvil = new RecipeAnvil();
                    anvil.readNBT(compound);
                    anvilRecipes.put(anvil.id, anvil);
                    if (anvil.id <= this.nextAnvilId) continue;
                    this.nextAnvilId = anvil.id;
                    continue;
                }
                RecipeCarpentry recipe = RecipeCarpentry.create(compound);
                recipe.readNBT(compound);
                if (recipe.isGlobal) {
                    globalRecipes.put(recipe.id, recipe);
                } else {
                    carpentryRecipes.put(recipe.id, recipe);
                }
                if (recipe.id <= this.nextId) continue;
                this.nextId = recipe.id;
            }
        }
        this.carpentryRecipes = carpentryRecipes;
        this.globalRecipes = globalRecipes;
        this.anvilRecipes = anvilRecipes;
        this.loadDefaultRecipes(nbttagcompound1.func_74762_e("Version"));
    }

    private void saveCategories() {
        try {
            File saveDir = CustomNpcs.getWorldSaveDirectory();
            NBTTagList list = new NBTTagList();
            for (RecipeCarpentry recipeCarpentry : this.globalRecipes.values()) {
                list.func_74742_a((NBTBase)recipeCarpentry.writeNBT(true));
            }
            for (RecipeCarpentry recipeCarpentry : this.carpentryRecipes.values()) {
                list.func_74742_a((NBTBase)recipeCarpentry.writeNBT(true));
            }
            for (RecipeAnvil recipeAnvil : this.anvilRecipes.values()) {
                list.func_74742_a((NBTBase)recipeAnvil.writeNBT(true));
            }
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74782_a("Data", (NBTBase)list);
            nbttagcompound.func_74768_a("LastId", this.nextId);
            nbttagcompound.func_74768_a("LastAnvilId", this.nextAnvilId);
            nbttagcompound.func_74768_a("Version", 1);
            File file = new File(saveDir, "recipes.dat_new");
            File file1 = new File(saveDir, "recipes.dat_old");
            File file2 = new File(saveDir, "recipes.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)nbttagcompound, (OutputStream)new FileOutputStream(file));
            if (file1.exists()) {
                file1.delete();
            }
            file2.renameTo(file1);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            if (file.exists()) {
                file.delete();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecipeCarpentry findMatchingRecipe(InventoryCrafting par1InventoryCrafting) {
        for (RecipeCarpentry recipe : this.carpentryRecipes.values()) {
            if (!recipe.isValid() || !recipe.func_77569_a(par1InventoryCrafting, null)) continue;
            return recipe;
        }
        return null;
    }

    public RecipeCarpentry getRecipe(int id) {
        if (this.globalRecipes.containsKey(id)) {
            return this.globalRecipes.get(id);
        }
        if (this.carpentryRecipes.containsKey(id)) {
            return this.carpentryRecipes.get(id);
        }
        return null;
    }

    public RecipeAnvil getAnvilRecipe(int id) {
        if (this.anvilRecipes.containsKey(id)) {
            return this.anvilRecipes.get(id);
        }
        return null;
    }

    public RecipeCarpentry saveRecipe(NBTTagCompound compound) throws IOException {
        RecipeCarpentry recipe = RecipeCarpentry.create(compound);
        recipe.readNBT(compound);
        RecipeCarpentry current = this.getRecipe(recipe.id);
        if (current != null && !current.name.equals(recipe.name)) {
            while (this.containsRecipeName(recipe.name)) {
                recipe.name = recipe.name + "_";
            }
        }
        if (recipe.id == -1) {
            recipe.id = this.getUniqueId();
            while (this.containsRecipeName(recipe.name)) {
                recipe.name = recipe.name + "_";
            }
        }
        if (recipe.isGlobal) {
            this.carpentryRecipes.remove(recipe.id);
            this.globalRecipes.put(recipe.id, recipe);
        } else {
            this.globalRecipes.remove(recipe.id);
            this.carpentryRecipes.put(recipe.id, recipe);
        }
        this.saveCategories();
        RecipeController.reloadGlobalRecipes(this.globalRecipes);
        return recipe;
    }

    public RecipeAnvil saveAnvilRecipe(NBTTagCompound compound) throws IOException {
        RecipeAnvil recipe = new RecipeAnvil();
        recipe.readNBT(compound);
        RecipeAnvil current = this.getAnvilRecipe(recipe.id);
        if (current != null && !current.name.equals(recipe.name)) {
            while (this.containsAnvilRecipeName(recipe.name)) {
                recipe.name = recipe.name + "_";
            }
        }
        if (recipe.id == -1) {
            recipe.id = this.getUniqueAnvilId();
            while (this.containsAnvilRecipeName(recipe.name)) {
                recipe.name = recipe.name + "_";
            }
        }
        this.anvilRecipes.put(recipe.id, recipe);
        this.saveCategories();
        return recipe;
    }

    private int getUniqueId() {
        return this.nextId++;
    }

    private int getUniqueAnvilId() {
        return this.nextAnvilId++;
    }

    private boolean containsRecipeName(String name) {
        name = name.toLowerCase();
        for (RecipeCarpentry recipe : this.globalRecipes.values()) {
            if (!recipe.name.toLowerCase().equals(name)) continue;
            return true;
        }
        for (RecipeCarpentry recipe : this.carpentryRecipes.values()) {
            if (!recipe.name.toLowerCase().equals(name)) continue;
            return true;
        }
        return false;
    }

    private boolean containsAnvilRecipeName(String name) {
        name = name.toLowerCase();
        for (RecipeAnvil recipe : this.anvilRecipes.values()) {
            if (!recipe.name.toLowerCase().equals(name)) continue;
            return true;
        }
        return false;
    }

    @Override
    public RecipeCarpentry delete(int id) {
        RecipeCarpentry carpentry;
        RecipeCarpentry recipe = this.getRecipe(id);
        if (recipe == null) {
            return null;
        }
        RecipeCarpentry globalRecipe = this.globalRecipes.remove(recipe.id);
        if (globalRecipe != null) {
            SyncController.syncAllWorkbenchRecipes();
        }
        if ((carpentry = this.carpentryRecipes.remove(recipe.id)) != null) {
            this.carpentryScripts.remove(recipe.id);
            SyncController.syncAllCarpentryRecipes();
        }
        this.saveCategories();
        RecipeController.reloadGlobalRecipes(this.globalRecipes);
        return recipe;
    }

    @Override
    public RecipeAnvil deleteAnvil(int id) {
        RecipeAnvil recipe = this.getAnvilRecipe(id);
        if (recipe == null) {
            return null;
        }
        RecipeAnvil anvilRecipe = this.anvilRecipes.remove(recipe.id);
        if (anvilRecipe != null) {
            SyncController.syncAllAnvilRecipes();
        }
        this.saveCategories();
        return recipe;
    }

    public void addRecipe(RecipeCarpentry recipeCarpentry) {
        recipeCarpentry.id = this.getUniqueId();
        if (!recipeCarpentry.isGlobal) {
            RecipeController.Instance.carpentryRecipes.put(recipeCarpentry.id, recipeCarpentry);
        } else {
            RecipeController.Instance.globalRecipes.put(recipeCarpentry.id, recipeCarpentry);
        }
    }

    public void addAnvilRecipe(RecipeAnvil recipeAnvil) {
        recipeAnvil.id = this.getUniqueAnvilId();
        this.anvilRecipes.put(recipeAnvil.id, recipeAnvil);
    }

    @Override
    public List<IRecipe> getGlobalList() {
        return new ArrayList<IRecipe>(this.globalRecipes.values());
    }

    @Override
    public List<IRecipe> getCarpentryList() {
        return new ArrayList<IRecipe>(this.carpentryRecipes.values());
    }

    @Override
    public List<IAnvilRecipe> getAnvilList() {
        ArrayList<IAnvilRecipe> list = new ArrayList<IAnvilRecipe>();
        list.addAll(this.anvilRecipes.values());
        return list;
    }

    @Override
    public void addRecipe(String name, boolean global, ItemStack result, Object ... objects) {
        RecipeCarpentry recipe = new RecipeCarpentry(name);
        recipe.isGlobal = global;
        recipe = RecipeCarpentry.saveRecipe(recipe, result, objects);
        try {
            this.saveRecipe(recipe.writeNBT(true));
        }
        catch (Exception var7) {
            var7.printStackTrace();
        }
    }

    @Override
    public void addRecipe(String name, boolean global, ItemStack result, int width, int height, ItemStack ... objects) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (ItemStack item : objects) {
            if (item.field_77994_a <= 0) continue;
            list.add(item);
        }
        RecipeCarpentry recipe = new RecipeCarpentry(width, height, list.toArray(new ItemStack[0]), result);
        recipe.isGlobal = global;
        recipe.name = name;
        try {
            this.saveRecipe(recipe.writeNBT(true));
        }
        catch (IOException var12) {
            var12.printStackTrace();
        }
    }

    @Override
    public void addAnvilRecipe(String name, boolean global, ItemStack itemToRepair, ItemStack repairMaterial, int xpCost, float repairPercentage) {
        RecipeAnvil recipe = new RecipeAnvil(name, itemToRepair, repairMaterial, xpCost, repairPercentage);
        try {
            this.saveAnvilRecipe(recipe.writeNBT(true));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        syncRecipes = new HashMap();
        syncAnvilRecipes = new HashMap();
    }
}

