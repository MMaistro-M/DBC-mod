/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 */
package net.minecraft.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;

public class NPCEntityHelper {
    public static Item getDropItem(EntityLiving entity) {
        return entity.func_146068_u();
    }

    public static void setRecentlyHit(EntityLivingBase entity) {
        entity.field_70718_bc = 100;
    }
}

