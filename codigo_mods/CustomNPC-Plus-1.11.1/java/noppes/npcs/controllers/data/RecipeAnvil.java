/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.handler.data.IAnvilRecipe;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.Availability;
import noppes.npcs.controllers.data.RecipeScript;

public class RecipeAnvil
implements IAnvilRecipe {
    public int id = -1;
    public String name = "";
    public Availability availability = new Availability();
    public boolean ignoreRepairItemNBT = false;
    public boolean ignoreRepairMaterialNBT = false;
    public boolean ignoreRepairMaterialDamage = false;
    public ItemStack itemToRepair;
    public ItemStack repairMaterial;
    public int xpCost;
    public float repairPercentage;

    public RecipeAnvil() {
    }

    public RecipeAnvil(String name, ItemStack itemToRepair, ItemStack repairMaterial, int xpCost, float repairPercentage) {
        this.name = name;
        this.itemToRepair = itemToRepair;
        this.repairMaterial = repairMaterial;
        this.xpCost = xpCost;
        this.repairPercentage = repairPercentage;
    }

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("ID");
        this.name = compound.func_74779_i("Name");
        this.availability.readFromNBT(compound.func_74775_l("Availability"));
        this.xpCost = compound.func_74762_e("XPCost");
        this.repairPercentage = compound.func_74760_g("RepairPercentage");
        this.itemToRepair = NoppesUtilServer.readItem(compound.func_74775_l("ItemToRepair"));
        this.repairMaterial = NoppesUtilServer.readItem(compound.func_74775_l("RepairMaterial"));
        this.ignoreRepairMaterialNBT = compound.func_74767_n("IgnoreRepairMatNBT");
        this.ignoreRepairItemNBT = compound.func_74767_n("IgnoreRepairItemNBT");
        this.ignoreRepairMaterialDamage = compound.func_74767_n("IgnoreRepairMatDamage");
        if (compound.func_150297_b("ScriptData", 10)) {
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
        compound.func_74778_a("Name", this.name);
        compound.func_74782_a("Availability", (NBTBase)this.availability.writeToNBT(new NBTTagCompound()));
        compound.func_74768_a("XPCost", this.xpCost);
        compound.func_74776_a("RepairPercentage", this.repairPercentage);
        if (this.itemToRepair != null) {
            compound.func_74782_a("ItemToRepair", (NBTBase)NoppesUtilServer.writeItem(this.itemToRepair, new NBTTagCompound()));
        }
        if (this.repairMaterial != null) {
            compound.func_74782_a("RepairMaterial", (NBTBase)NoppesUtilServer.writeItem(this.repairMaterial, new NBTTagCompound()));
        }
        compound.func_74757_a("IgnoreRepairMatNBT", this.ignoreRepairMaterialNBT);
        compound.func_74757_a("IgnoreRepairItemNBT", this.ignoreRepairItemNBT);
        compound.func_74757_a("IgnoreRepairMatDamage", this.ignoreRepairMaterialDamage);
        compound.func_74757_a("IsAnvil", true);
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

    @Override
    public int getXpCost() {
        return this.xpCost;
    }

    @Override
    public float getRepairPercentage() {
        return this.repairPercentage;
    }

    @Override
    public boolean matches(ItemStack inputItem, ItemStack inputRepairMaterial) {
        if (inputItem == null || inputRepairMaterial == null) {
            return false;
        }
        if (!NoppesUtilPlayer.compareItems(this.itemToRepair, inputItem, true, this.ignoreRepairItemNBT)) {
            return false;
        }
        return NoppesUtilPlayer.compareItems(this.repairMaterial, inputRepairMaterial, this.ignoreRepairMaterialDamage, this.ignoreRepairMaterialNBT);
    }

    @Override
    public ItemStack getResult(ItemStack inputItem) {
        if (inputItem == null) {
            return null;
        }
        ItemStack result = inputItem.func_77946_l();
        if (!result.func_77984_f()) {
            return result;
        }
        int maxDamage = result.func_77958_k();
        int repairAmount = (int)((float)maxDamage * this.repairPercentage);
        int currentDamage = result.func_77960_j();
        int newDamage = currentDamage - repairAmount;
        if (newDamage < 0) {
            newDamage = 0;
        }
        result.func_77964_b(newDamage);
        return result;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

    public static RecipeAnvil saveRecipe(RecipeAnvil recipe, ItemStack output, ItemStack repairMaterial) {
        if (output != null) {
            recipe.itemToRepair = output.func_77946_l();
            if (recipe.itemToRepair.func_77984_f()) {
                recipe.itemToRepair.func_77964_b(0);
            }
        } else {
            recipe.itemToRepair = null;
        }
        recipe.repairMaterial = repairMaterial == null ? null : repairMaterial.func_77946_l();
        return recipe;
    }

    public boolean isValid() {
        return this.itemToRepair != null && this.repairMaterial != null;
    }

    public void copy(RecipeAnvil recipe) {
        this.id = recipe.id;
        this.name = recipe.name;
        this.availability = recipe.availability;
        this.ignoreRepairMaterialDamage = recipe.ignoreRepairMaterialDamage;
        this.ignoreRepairItemNBT = recipe.ignoreRepairItemNBT;
        this.ignoreRepairMaterialNBT = recipe.ignoreRepairMaterialNBT;
        this.repairPercentage = recipe.repairPercentage;
        this.xpCost = recipe.xpCost;
    }

    public RecipeScript getScriptHandler() {
        return RecipeController.Instance.anvilScripts.get(this.id);
    }

    public void setScriptHandler(RecipeScript handler) {
        RecipeController.Instance.anvilScripts.put(this.id, handler);
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

