/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 */
package com.tobiasmjc.dbcadditions.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class LivingUtils {
    public static void knockback(EntityLivingBase targetEntity, Entity attacker, int knockbackStrength) {
        float var25;
        if (knockbackStrength > 0 && (var25 = MathHelper.func_76133_a((double)(attacker.field_70159_w * attacker.field_70159_w + attacker.field_70179_y * attacker.field_70179_y))) > 0.0f) {
            targetEntity.func_70024_g(attacker.field_70159_w * (double)knockbackStrength * (double)0.6f / (double)var25, 0.1, attacker.field_70179_y * (double)knockbackStrength * (double)0.6f / (double)var25);
        }
    }
}

