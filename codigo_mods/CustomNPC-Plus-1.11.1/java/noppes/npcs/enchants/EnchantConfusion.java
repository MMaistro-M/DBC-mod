/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.enchants;

import noppes.npcs.enchants.EnchantInterface;
import noppes.npcs.items.ItemGun;
import noppes.npcs.items.ItemStaff;

public class EnchantConfusion
extends EnchantInterface {
    public EnchantConfusion() {
        super(3, ItemStaff.class, ItemGun.class);
        this.func_77322_b("confusion");
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

