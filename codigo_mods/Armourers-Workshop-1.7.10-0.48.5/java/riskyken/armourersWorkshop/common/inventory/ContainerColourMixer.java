/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.painting.IPaintingTool;
import riskyken.armourersWorkshop.common.inventory.slot.SlotColourTool;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourMixer;
import riskyken.armourersWorkshop.utils.UtilColour;

public class ContainerColourMixer
extends Container {
    private TileEntityColourMixer tileEntityColourMixer;
    private UtilColour.ColourFamily lastColourFamily;

    public ContainerColourMixer(InventoryPlayer invPlayer, TileEntityColourMixer tileEntityColourMixer) {
        this.tileEntityColourMixer = tileEntityColourMixer;
        this.func_75146_a(new SlotColourTool(tileEntityColourMixer, 0, 144, 32));
        this.func_75146_a(new SlotOutput(tileEntityColourMixer, 1, 144, 73));
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 48 + 18 * x, 216));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 48 + 18 * x, 158 + y * 18));
            }
        }
    }

    public ItemStack func_82846_b(EntityPlayer player, int slotID) {
        Slot slot = this.func_75139_a(slotID);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotID < 2) {
                if (!this.func_75135_a(stack, 11, 38, false) && !this.func_75135_a(stack, 2, 11, false)) {
                    return null;
                }
            } else if (stack.func_77973_b() instanceof IPaintingTool) {
                if (!this.func_75135_a(stack, 0, 1, false)) {
                    return null;
                }
            } else {
                return null;
            }
            if (stack.field_77994_a == 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
            slot.func_82870_a(player, stack);
            return result;
        }
        return null;
    }

    public void func_75132_a(ICrafting crafter) {
        super.func_75132_a(crafter);
        crafter.func_71112_a((Container)this, 0, this.tileEntityColourMixer.getColourFamily().ordinal());
        this.lastColourFamily = this.tileEntityColourMixer.getColourFamily();
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); ++i) {
            ICrafting crafter = (ICrafting)this.field_75149_d.get(i);
            if (this.lastColourFamily == this.tileEntityColourMixer.getColourFamily()) continue;
            crafter.func_71112_a((Container)this, 0, this.tileEntityColourMixer.getColourFamily().ordinal());
        }
        this.lastColourFamily = this.tileEntityColourMixer.getColourFamily();
    }

    public void func_75137_b(int id, int data) {
        if (id == 0) {
            this.tileEntityColourMixer.setColourFamily(UtilColour.ColourFamily.values()[data]);
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntityColourMixer.func_70300_a(player);
    }

    public TileEntityColourMixer getTileEntity() {
        return this.tileEntityColourMixer;
    }
}

