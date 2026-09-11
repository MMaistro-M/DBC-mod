/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.scripted;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;

public class ScriptContainer
implements IContainer {
    private IInventory inventory;
    private Container container;

    public ScriptContainer(IInventory inventory) {
        this.inventory = inventory;
    }

    public ScriptContainer(Container container) {
        this.container = container;
    }

    @Override
    public int getSize() {
        return this.inventory != null ? this.inventory.func_70302_i_() : this.container.field_75151_b.size();
    }

    @Override
    public IItemStack getSlot(int slot) {
        if (slot >= 0 && slot < this.getSize()) {
            return this.inventory != null ? NpcAPI.Instance().getIItemStack(this.inventory.func_70301_a(slot)) : NpcAPI.Instance().getIItemStack(this.container.func_75139_a(slot).func_75211_c());
        }
        throw new CustomNPCsException("Slot is out of range " + slot, new Object[0]);
    }

    @Override
    public void setSlot(int slot, IItemStack item) {
        if (slot >= 0 && slot < this.getSize()) {
            ItemStack itemstack;
            ItemStack itemStack = itemstack = item == null ? new ItemStack(Block.func_149729_e((int)0)) : item.getMCItemStack();
            if (this.inventory != null) {
                this.inventory.func_70299_a(slot, itemstack);
            } else {
                this.container.func_75141_a(slot, itemstack);
                this.container.func_75142_b();
            }
        } else {
            throw new CustomNPCsException("Slot is out of range " + slot, new Object[0]);
        }
    }

    @Override
    public int count(IItemStack item, boolean ignoreDamage, boolean ignoreNBT) {
        int count = 0;
        for (int i = 0; i < this.getSize(); ++i) {
            IItemStack toCompare = this.getSlot(i);
            if (!NoppesUtilPlayer.compareItems(item.getMCItemStack(), toCompare.getMCItemStack(), ignoreDamage, ignoreNBT)) continue;
            count += toCompare.getStackSize();
        }
        return count;
    }

    @Override
    public IInventory getMCInventory() {
        return this.inventory;
    }

    @Override
    public Container getMCContainer() {
        return this.container;
    }

    @Override
    public IItemStack[] getItems() {
        IItemStack[] items = new IItemStack[this.getSize()];
        for (int i = 0; i < this.getSize(); ++i) {
            items[i] = this.getSlot(i);
        }
        return items;
    }

    @Override
    public boolean isCustomGUI() {
        return this.container instanceof ContainerCustomGui;
    }

    @Override
    public void detectAndSendChanges() {
        this.container.func_75142_b();
    }

    @Override
    public boolean isPlayerNotUsingContainer(IPlayer player) {
        return this.container.func_75129_b((EntityPlayer)((EntityPlayerMP)player.getMCEntity()));
    }
}

