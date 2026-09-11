/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.enchants;

import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.items.ItemGun;
import noppes.npcs.items.ItemStaff;

public class EnchantDamage
extends EnchantInterface {
    public EnchantDamage() {
        super(10, ItemStaff.class, ItemGun.class);
        this.func_77322_b("damage");
    }

    public int func_77321_a(int par1) {
        return 1 + (par1 - 1) * 10;
    }

    public int func_77317_b(int par1) {
        return this.func_77321_a(par1) + 15;
    }

    public int func_77325_b() {
        return 5;
    }
}

