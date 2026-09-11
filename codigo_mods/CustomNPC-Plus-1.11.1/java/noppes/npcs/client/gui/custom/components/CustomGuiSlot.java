/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 */
package noppes.npcs.client.gui.custom.components;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import noppes.npcs.EventHooks;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.IItemSlot;
import noppes.npcs.containers.ContainerCustomGui;
import noppes.npcs.scripted.NpcAPI;

public class CustomGuiSlot
extends Slot {
    public boolean clientSide;
    public final int field_75222_d;
    public final IItemSlot slot;
    public final EntityPlayer player;

    public CustomGuiSlot(EntityPlayer player, IInventory inventoryIn, int index, IItemSlot slot, int xPosition, int yPosition, boolean clientSide) {
        super(inventoryIn, index, xPosition, yPosition);
        this.clientSide = clientSide;
        this.field_75222_d = index;
        this.slot = slot;
        this.player = player;
    }

    public void func_75218_e() {
        if (!this.player.field_70170_p.field_72995_K) {
            boolean changed;
            if (this.func_75211_c() != null && this.slot.getStack() != null) {
                changed = !this.func_75211_c().equals(this.slot.getStack().getMCItemStack());
            } else {
                boolean bl = changed = this.func_75211_c() != null || this.slot.getStack() != null;
            }
            if (changed) {
                this.slot.setStack(NpcAPI.Instance().getIItemStack(this.func_75211_c()));
                if (this.player.field_71070_bA instanceof ContainerCustomGui) {
                    EventHooks.onCustomGuiSlot((IPlayer)NpcAPI.Instance().getIEntity((Entity)this.player), ((ContainerCustomGui)this.player.field_71070_bA).customGui, this.getSlotIndex(), this.func_75211_c(), this.slot);
                }
            }
        }
        super.func_75218_e();
    }
}

