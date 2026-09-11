/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.enchants;

import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.items.ItemGun;
import noppes.npcs.items.ItemStaff;

public class EnchantInfinite
extends EnchantInterface {
    public EnchantInfinite() {
        super(3, ItemStaff.class, ItemGun.class);
        this.func_77322_b("infinite");
    }

    public int func_77321_a(int par1) {
        return 20;
    }

    public int func_77317_b(int par1) {
        return 50;
    }

    public int func_77325_b() {
        return 1;
    }
}

