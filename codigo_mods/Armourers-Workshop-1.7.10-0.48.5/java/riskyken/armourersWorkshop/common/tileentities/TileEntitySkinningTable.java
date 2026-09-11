/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package riskyken.armourersWorkshop.common.tileentities;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import riskyken.armourersWorkshop.common.crafting.ItemSkinningRecipes;
import riskyken.armourersWorkshop.common.inventory.IInventorySlotUpdate;
import riskyken.armourersWorkshop.common.inventory.ModInventory;

public class TileEntitySkinningTable
extends TileEntity
implements IInventorySlotUpdate {
    private final ModInventory craftingInventory = new ModInventory("skinningTablecrafting", 2, this, this);
    private final ModInventory outputInventory = new ModInventory("skinningTableOutput", 1, this, this);

    public boolean canUpdate() {
        return false;
    }

    public ModInventory getCraftingInventory() {
        return this.craftingInventory;
    }

    public ModInventory getOutputInventory() {
        return this.outputInventory;
    }

    @Override
    public void setInventorySlotContents(IInventory inventory, int slotId, ItemStack stack) {
        if (inventory == this.craftingInventory) {
            this.checkForValidRecipe();
        }
    }

    private void checkForValidRecipe() {
        ItemStack stack = ItemSkinningRecipes.getRecipeOutput(this.craftingInventory);
        this.outputInventory.func_70299_a(0, stack);
        this.func_70296_d();
    }

    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        this.craftingInventory.saveItemsToNBT(compound);
        this.outputInventory.saveItemsToNBT(compound);
    }

    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.craftingInventory.loadItemsFromNBT(compound);
        this.outputInventory.loadItemsFromNBT(compound);
    }
}

