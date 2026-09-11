/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.scripted.item;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import noppes.npcs.api.item.IItemArmor;
import noppes.npcs.scripted.item.ScriptItemStack;

public class ScriptItemArmor
extends ScriptItemStack
implements IItemArmor {
    protected ItemArmor armor;

    public ScriptItemArmor(ItemStack item) {
        super(item);
        this.armor = (ItemArmor)item.func_77973_b();
    }

    @Override
    public int getType() {
        return 3;
    }

    @Override
    public int getArmorSlot() {
        return this.armor.field_77881_a;
    }

    @Override
    public String getArmorMaterial() {
        return this.armor.func_82812_d().toString();
    }
}

