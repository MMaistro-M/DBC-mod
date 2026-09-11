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
 *  net.minecraft.inventory.SlotCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S2FPacketSetSlot
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
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.EventHooks;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.RecipeCarpentry;
import noppes.npcs.scripted.event.RecipeScriptEvent;

public class ContainerCarpentryBench
extends Container {
    public InventoryCrafting craftMatrix = new InventoryCrafting((Container)this, 4, 4);
    public IInventory craftResult = new InventoryCraftResult();
    private EntityPlayer player;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    private RecipeCarpentry currentRecipe;
    private SlotCarpentryResult resultSlot;
    private boolean resultCanPickup = true;

    public ContainerCarpentryBench(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
        int var7;
        int var6;
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        this.player = par1InventoryPlayer.field_70458_d;
        this.resultSlot = new SlotCarpentryResult(this, par1InventoryPlayer.field_70458_d, this.craftMatrix, this.craftResult, 0, 133, 41);
        this.func_75146_a((Slot)this.resultSlot);
        for (var6 = 0; var6 < 4; ++var6) {
            for (var7 = 0; var7 < 4; ++var7) {
                this.func_75146_a(new Slot((IInventory)this.craftMatrix, var7 + var6 * 4, 17 + var7 * 18, 14 + var6 * 18));
            }
        }
        for (var6 = 0; var6 < 3; ++var6) {
            for (var7 = 0; var7 < 9; ++var7) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 98 + var6 * 18));
            }
        }
        for (var6 = 0; var6 < 9; ++var6) {
            this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, var6, 8 + var6 * 18, 156));
        }
        this.func_75130_a((IInventory)this.craftMatrix);
    }

    public int getMetadata() {
        return this.worldObj.func_72805_g(this.posX, this.posY, this.posZ);
    }

    public void func_75130_a(IInventory par1IInventory) {
        if (!this.worldObj.field_72995_K) {
            RecipeCarpentry recipe;
            this.currentRecipe = recipe = RecipeController.Instance.findMatchingRecipe(this.craftMatrix);
            ItemStack item = null;
            boolean canPickup = true;
            if (recipe != null && recipe.availability.isAvailable(this.player)) {
                item = recipe.func_77572_b(this.craftMatrix);
                ItemStack[] items = new ItemStack[this.craftMatrix.func_70302_i_()];
                for (int i = 0; i < items.length; ++i) {
                    items[i] = this.craftMatrix.func_70301_a(i);
                }
                RecipeScriptEvent.Pre pre = EventHooks.onRecipeScriptPre(this.player, recipe.getScriptHandler(), recipe, items);
                canPickup = !pre.isCanceled();
                item = EventHooks.onRecipeScriptPost(this.player, recipe.getScriptHandler(), recipe, items, item);
            }
            this.craftResult.func_70299_a(0, item);
            this.resultCanPickup = canPickup;
            if (this.resultSlot != null) {
                this.resultSlot.setCanPickup(canPickup);
            }
            EntityPlayerMP plmp = (EntityPlayerMP)this.player;
            plmp.field_71135_a.func_147359_a((Packet)new S2FPacketSetSlot(this.field_75152_c, 0, item));
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.worldObj.field_72995_K) {
            for (int var2 = 0; var2 < 16; ++var2) {
                ItemStack var3 = this.craftMatrix.func_70304_b(var2);
                if (var3 == null) continue;
                par1EntityPlayer.func_71019_a(var3, false);
            }
        }
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.worldObj.func_147439_a(this.posX, this.posY, this.posZ) != CustomItems.carpentyBench ? false : par1EntityPlayer.func_70092_e((double)this.posX + 0.5, (double)this.posY + 0.5, (double)this.posZ + 0.5) <= 64.0;
    }

    public boolean canPickupResult() {
        return this.resultCanPickup;
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par1) {
        ItemStack var2 = null;
        Slot var3 = (Slot)this.field_75151_b.get(par1);
        if (var3 != null && var3.func_75216_d()) {
            ItemStack var4 = var3.func_75211_c();
            var2 = var4.func_77946_l();
            if (par1 == 0) {
                SlotCarpentryResult resultSlot = (SlotCarpentryResult)var3;
                if (!resultSlot.canPickup()) {
                    return null;
                }
                if (!this.func_75135_a(var4, 17, 53, true)) {
                    return null;
                }
                var3.func_75220_a(var4, var2);
            } else if (par1 >= 17 && par1 < 44 ? !this.func_75135_a(var4, 44, 53, false) : (par1 >= 44 && par1 < 53 ? !this.func_75135_a(var4, 17, 44, false) : !this.func_75135_a(var4, 17, 53, false))) {
                return null;
            }
            if (var4.field_77994_a == 0) {
                var3.func_75215_d((ItemStack)null);
            } else {
                var3.func_75218_e();
            }
            if (var4.field_77994_a == var2.field_77994_a) {
                return null;
            }
            var3.func_82870_a(par1EntityPlayer, var4);
        }
        if (!this.worldObj.field_72995_K && par1EntityPlayer instanceof EntityPlayerMP) {
            ((EntityPlayerMP)par1EntityPlayer).func_71120_a((Container)this);
        }
        return var2;
    }

    public boolean func_94530_a(ItemStack stack, Slot slotIn) {
        return slotIn.field_75224_c != this.craftResult && super.func_94530_a(stack, slotIn);
    }

    public void func_75132_a(ICrafting listener) {
        super.func_75132_a(listener);
        listener.func_71112_a((Container)this, 0, this.resultCanPickup ? 1 : 0);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (Object crafterObj : this.field_75149_d) {
            ((ICrafting)crafterObj).func_71112_a((Container)this, 0, this.resultCanPickup ? 1 : 0);
        }
    }

    public void func_75137_b(int id, int data) {
        if (id == 0 && this.resultSlot != null) {
            this.resultSlot.setCanPickup(data != 0);
            this.resultCanPickup = data != 0;
        }
    }

    private class SlotCarpentryResult
    extends SlotCrafting {
        private final ContainerCarpentryBench container;
        private boolean canPickup;

        public SlotCarpentryResult(ContainerCarpentryBench container, EntityPlayer player, InventoryCrafting matrix, IInventory result, int index, int x, int y) {
            super(player, (IInventory)matrix, result, index, x, y);
            this.canPickup = true;
            this.container = container;
        }

        public void setCanPickup(boolean value) {
            this.canPickup = value;
        }

        public boolean canPickup() {
            return this.canPickup;
        }

        public boolean func_82869_a(EntityPlayer player) {
            return this.canPickup && super.func_82869_a(player);
        }

        public void func_82870_a(EntityPlayer player, ItemStack stack) {
            if (!this.canPickup) {
                return;
            }
            super.func_82870_a(player, stack);
        }
    }
}

