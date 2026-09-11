/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.constants.EnumCompanionTalent;
import noppes.npcs.containers.ContainerNpcInterface;
import noppes.npcs.containers.InventoryNPC;
import noppes.npcs.containers.SlotCompanionArmor;
import noppes.npcs.containers.SlotCompanionWeapon;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleCompanion;

public class ContainerNPCCompanion
extends ContainerNpcInterface {
    public InventoryNPC currencyMatrix;
    public RoleCompanion role;

    public ContainerNPCCompanion(EntityNPCInterface npc, EntityPlayer player) {
        super(player);
        int l;
        this.role = (RoleCompanion)npc.roleInterface;
        for (int k = 0; k < 3; ++k) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, j1 + k * 9 + 9, 6 + j1 * 18, 87 + k * 18));
            }
        }
        for (l = 0; l < 9; ++l) {
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, l, 6 + l * 18, 145));
        }
        if (this.role.talents.containsKey((Object)EnumCompanionTalent.INVENTORY)) {
            int size = (this.role.getTalentLevel(EnumCompanionTalent.INVENTORY) + 1) * 2;
            for (int i = 0; i < size; ++i) {
                this.func_75146_a(new Slot((IInventory)this.role.inventory, i, 114 + i % 3 * 18, 8 + i / 3 * 18));
            }
        }
        if (this.role.getTalentLevel(EnumCompanionTalent.ARMOR) > 0) {
            for (l = 0; l < 4; ++l) {
                this.func_75146_a(new SlotCompanionArmor(this.role, npc.inventory, l, 6, 8 + l * 18, l));
            }
        }
        if (this.role.getTalentLevel(EnumCompanionTalent.SWORD) > 0) {
            this.func_75146_a(new SlotCompanionWeapon(this.role, npc.inventory, 4, 79, 17));
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int i) {
        return null;
    }

    public void func_75134_a(EntityPlayer entityplayer) {
        super.func_75134_a(entityplayer);
        this.role.setStats();
    }
}

