/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.items;

import noppes.npcs.CustomItems;
import noppes.npcs.constants.EnumNpcToolMaterial;
import noppes.npcs.items.ItemNpcInterface;

public class ItemBullet
extends ItemNpcInterface {
    private EnumNpcToolMaterial material;

    public ItemBullet(int par1, EnumNpcToolMaterial material) {
        super(par1);
        this.material = material;
        this.func_77637_a(CustomItems.tabWeapon);
    }

    public int getBulletDamage() {
        return this.material.getDamageVsEntity();
    }
}

