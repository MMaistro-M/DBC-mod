/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.enchants;

import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.items.ItemGun;
import noppes.npcs.items.ItemStaff;

public class EnchantPoison
extends EnchantInterface {
    public EnchantPoison() {
        super(6, ItemStaff.class, ItemGun.class);
        this.func_77322_b("poison");
    }

    public int func_77321_a(int par1) {
        return 12 + (par1 - 1) * 20;
    }

    public int func_77317_b(int par1) {
        return this.func_77321_a(par1) + 25;
    }

    public int func_77325_b() {
        return 2;
    }
}

