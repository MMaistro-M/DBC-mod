/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.attribute.tracker;

import java.util.Objects;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerEquipmentTracker {
    public ItemStack heldItem;
    public ItemStack helmet;
    public ItemStack chestplate;
    public ItemStack leggings;
    public ItemStack boots;

    public void updateFrom(EntityPlayer player) {
        this.heldItem = this.copyIfNotNull(player.func_70694_bm());
        ItemStack[] armor = player.field_71071_by.field_70460_b;
        this.boots = this.copyIfNotNull(armor[0]);
        this.leggings = this.copyIfNotNull(armor[1]);
        this.chestplate = this.copyIfNotNull(armor[2]);
        this.helmet = this.copyIfNotNull(armor[3]);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerEquipmentTracker)) {
            return false;
        }
        PlayerEquipmentTracker other = (PlayerEquipmentTracker)o;
        return this.areItemStacksEquivalent(this.heldItem, other.heldItem) && this.areItemStacksEquivalent(this.boots, other.boots) && this.areItemStacksEquivalent(this.leggings, other.leggings) && this.areItemStacksEquivalent(this.chestplate, other.chestplate) && this.areItemStacksEquivalent(this.helmet, other.helmet);
    }

    public int hashCode() {
        return Objects.hash(this.itemHash(this.heldItem), this.itemHash(this.boots), this.itemHash(this.leggings), this.itemHash(this.chestplate), this.itemHash(this.helmet));
    }

    private int itemHash(ItemStack s) {
        if (s == null) {
            return 0;
        }
        NBTTagCompound root = s.field_77990_d;
        if (root == null || !root.func_74764_b("RPGCore")) {
            return 0;
        }
        NBTTagCompound tag = root.func_74775_l("RPGCore");
        return Objects.hash(s.func_77973_b(), tag);
    }

    private ItemStack copyIfNotNull(ItemStack stack) {
        return stack == null ? null : stack.func_77946_l();
    }

    private boolean areItemStacksEquivalent(ItemStack a, ItemStack b) {
        NBTTagCompound tagB;
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.func_77973_b() != b.func_77973_b()) {
            return false;
        }
        NBTTagCompound tagA = a.field_77990_d != null ? a.field_77990_d.func_74775_l("RPGCore") : null;
        if (tagA == null ^ (tagB = b.field_77990_d != null ? b.field_77990_d.func_74775_l("RPGCore") : null) == null) {
            return false;
        }
        return tagA == null || tagA.equals((Object)tagB);
    }
}

