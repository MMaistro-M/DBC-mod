/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryCraftResult
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.EventHooks;
import noppes.npcs.api.handler.data.IAnvilRecipe;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.RecipeAnvil;
import noppes.npcs.scripted.event.RecipeScriptEvent;

public class ContainerAnvilRepair
extends Container {
    public InventoryCrafting anvilMatrix = new InventoryCrafting((Container)this, 2, 1);
    public InventoryCraftResult anvilResult = new InventoryCraftResult();
    private final EntityPlayer player;
    private final World worldObj;
    private final int posX;
    private final int posY;
    private final int posZ;
    public int repairCost = 0;
    public int repairMaterialConsumed = 0;
    private RecipeAnvil currentRecipe;
    private SlotAnvilOutput resultSlot;
    private boolean resultCanPickup = true;

    public ContainerAnvilRepair(InventoryPlayer playerInv, World world, int x, int y, int z) {
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.player = playerInv.field_70458_d;
        this.resultSlot = new SlotAnvilOutput(this, (IInventory)this.anvilResult, 0, 133, 47);
        this.func_75146_a(this.resultSlot);
        this.func_75146_a(new Slot((IInventory)this.anvilMatrix, 0, 26, 47){

            public int func_75219_a() {
                return 1;
            }
        });
        this.func_75146_a(new Slot((IInventory)this.anvilMatrix, 1, 75, 47){

            public void func_75218_e() {
                super.func_75218_e();
                ContainerAnvilRepair.this.func_75130_a((IInventory)ContainerAnvilRepair.this.anvilMatrix);
            }
        });
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.func_75146_a(new Slot((IInventory)playerInv, col + row * 9 + 9, 8 + col * 18, 98 + row * 18));
            }
        }
        for (int col = 0; col < 9; ++col) {
            this.func_75146_a(new Slot((IInventory)playerInv, col, 8 + col * 18, 156));
        }
        this.updateRepairResult();
    }

    public void func_75130_a(IInventory inventory) {
        this.updateRepairResult();
        this.func_75142_b();
    }

    public void updateRepairResult() {
        if (!this.worldObj.field_72995_K) {
            this.repairMaterialConsumed = 0;
            ItemStack input0 = this.anvilMatrix.func_70463_b(0, 0);
            ItemStack input1 = this.anvilMatrix.func_70463_b(1, 0);
            if (input0 == null || input1 == null) {
                this.repairCost = 0;
                this.anvilResult.func_70299_a(0, null);
                return;
            }
            ItemStack calcItem = input0.func_77946_l();
            calcItem.field_77994_a = 1;
            if (calcItem.func_77984_f() && calcItem.func_77960_j() <= 0) {
                this.repairCost = 0;
                this.anvilResult.func_70299_a(0, null);
                return;
            }
            RecipeAnvil matchingRecipe = null;
            for (IAnvilRecipe recipe : RecipeController.Instance.getAnvilList()) {
                if (!recipe.matches(input0, input1)) continue;
                matchingRecipe = (RecipeAnvil)recipe;
                break;
            }
            this.currentRecipe = matchingRecipe;
            ItemStack output = null;
            boolean canPickup = true;
            RecipeScriptEvent.Pre pre = null;
            if (matchingRecipe != null) {
                int materialsUsed;
                if (!matchingRecipe.availability.isAvailable(this.player)) {
                    return;
                }
                int baseXpCost = matchingRecipe.getXpCost();
                float repairPercentage = matchingRecipe.getRepairPercentage() / 100.0f;
                int maxDamage = calcItem.func_77958_k();
                int currentDamage = calcItem.func_77960_j();
                int repairPerMaterial = (int)Math.floor((float)maxDamage * repairPercentage);
                if (repairPerMaterial < 1) {
                    repairPerMaterial = 1;
                }
                int availableMaterials = input1.field_77994_a;
                for (materialsUsed = 0; currentDamage > 0 && materialsUsed < availableMaterials; ++materialsUsed) {
                    int repairThisIteration = repairPerMaterial;
                    if (repairThisIteration <= currentDamage) continue;
                    repairThisIteration = currentDamage;
                    if ((currentDamage -= repairThisIteration) > 0) continue;
                    break;
                }
                int xpCost = baseXpCost * materialsUsed;
                calcItem.func_77964_b(currentDamage);
                ItemStack[] items = new ItemStack[]{input0, input1};
                pre = EventHooks.onRecipeScriptPre(this.player, matchingRecipe.getScriptHandler(), matchingRecipe, items);
                int scriptXp = pre.getXpCost();
                int scriptMat = pre.getMaterialUsage();
                pre.setXpCost(xpCost += scriptXp);
                pre.setMaterialUsage(materialsUsed += scriptMat);
                boolean bl = canPickup = !pre.isCanceled();
                if (this.player.field_71067_cb >= xpCost) {
                    output = calcItem;
                }
                this.repairMaterialConsumed = materialsUsed;
                this.repairCost = xpCost;
                output = EventHooks.onRecipeScriptPost(this.player, matchingRecipe.getScriptHandler(), matchingRecipe, items, output);
            } else {
                this.repairCost = 0;
            }
            this.anvilResult.func_70299_a(0, output);
            this.resultCanPickup = canPickup;
            if (this.resultSlot != null) {
                this.resultSlot.setCanPickup(canPickup);
            }
        }
    }

    public void func_75132_a(ICrafting listener) {
        super.func_75132_a(listener);
        listener.func_71112_a((Container)this, 0, this.repairCost);
        listener.func_71112_a((Container)this, 1, this.resultCanPickup ? 1 : 0);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (Object crafterObj : this.field_75149_d) {
            ((ICrafting)crafterObj).func_71112_a((Container)this, 0, this.repairCost);
            ((ICrafting)crafterObj).func_71112_a((Container)this, 1, this.resultCanPickup ? 1 : 0);
        }
    }

    public void func_75137_b(int id, int data) {
        if (id == 0) {
            this.repairCost = data;
        } else if (id == 1 && this.resultSlot != null) {
            this.resultSlot.setCanPickup(data != 0);
        }
    }

    public void func_75134_a(EntityPlayer player) {
        super.func_75134_a(player);
        if (!this.worldObj.field_72995_K) {
            for (int i = 0; i < this.anvilMatrix.func_70302_i_(); ++i) {
                ItemStack stack = this.anvilMatrix.func_70304_b(i);
                if (stack == null) continue;
                player.func_71019_a(stack, false);
            }
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return player.func_70092_e((double)this.posX + 0.5, (double)this.posY + 0.5, (double)this.posZ + 0.5) <= 64.0;
    }

    public boolean canPickupResult() {
        return this.resultCanPickup;
    }

    public ItemStack func_82846_b(EntityPlayer player, int index) {
        ItemStack copy = null;
        Slot slot = (Slot)this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            copy = stack.func_77946_l();
            if (index == 0) {
                SlotAnvilOutput resultSlot = (SlotAnvilOutput)slot;
                if (!resultSlot.canPickup()) {
                    return null;
                }
                if (player.field_71067_cb < this.repairCost) {
                    return null;
                }
                if (!this.func_75135_a(stack, 3, this.field_75151_b.size(), true)) {
                    return null;
                }
                slot.func_75220_a(stack, copy);
            } else if (index >= 1 && index <= 2 ? !this.func_75135_a(stack, 3, this.field_75151_b.size(), false) : !this.func_75135_a(stack, 1, 3, false)) {
                return null;
            }
            if (stack.field_77994_a == 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
            if (stack.field_77994_a == copy.field_77994_a) {
                return null;
            }
            slot.func_82870_a(player, stack);
        }
        if (!this.worldObj.field_72995_K && player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player).func_71120_a((Container)this);
        }
        return copy;
    }

    public boolean func_94530_a(ItemStack stack, Slot slotIn) {
        return slotIn.field_75224_c != this.anvilResult && super.func_94530_a(stack, slotIn);
    }

    public class SlotAnvilOutput
    extends Slot {
        private final ContainerAnvilRepair container;
        private boolean canPickup;

        public SlotAnvilOutput(ContainerAnvilRepair container, IInventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
            this.canPickup = true;
            this.container = container;
        }

        public void setCanPickup(boolean value) {
            this.canPickup = value;
        }

        public boolean canPickup() {
            return this.canPickup;
        }

        public boolean func_75214_a(ItemStack stack) {
            return false;
        }

        public boolean func_82869_a(EntityPlayer player) {
            return this.canPickup && player.field_71067_cb >= this.container.repairCost;
        }

        public void func_82870_a(EntityPlayer player, ItemStack stack) {
            ItemStack input0;
            ItemStack repairMat;
            if (!this.canPickup) {
                return;
            }
            if (player.field_71067_cb < this.container.repairCost) {
                this.container.updateRepairResult();
                return;
            }
            if (!player.field_71075_bZ.field_75098_d) {
                player.func_71023_q(-this.container.repairCost);
            }
            if ((repairMat = this.container.anvilMatrix.func_70463_b(1, 0)) != null) {
                if (repairMat.field_77994_a > this.container.repairMaterialConsumed) {
                    repairMat.field_77994_a -= this.container.repairMaterialConsumed;
                } else {
                    repairMat = null;
                }
                this.container.anvilMatrix.func_70299_a(1, repairMat);
            }
            if ((input0 = this.container.anvilMatrix.func_70463_b(0, 0)) != null) {
                if (input0.field_77994_a > 1) {
                    --input0.field_77994_a;
                } else {
                    input0 = null;
                }
                this.container.anvilMatrix.func_70299_a(0, input0);
            }
            this.container.repairCost = 0;
            this.container.updateRepairResult();
            this.canPickup = true;
            super.func_82870_a(player, stack);
        }
    }
}

