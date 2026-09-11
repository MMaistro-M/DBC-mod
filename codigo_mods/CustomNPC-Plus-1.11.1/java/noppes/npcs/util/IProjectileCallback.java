/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import noppes.npcs.entity.EntityProjectile;

public interface IProjectileCallback {
    public boolean onImpact(EntityProjectile var1, EntityLivingBase var2, ItemStack var3);
}

