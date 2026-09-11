/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package kamkeel.npcs.controllers.data.ability.extender;

import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import net.minecraft.entity.EntityLivingBase;

public interface IAbilityExtender {
    default public boolean onAbilityStart(Ability ability, EntityLivingBase caster, EntityLivingBase target) {
        return true;
    }

    default public boolean onAbilityTick(Ability ability, EntityLivingBase caster, EntityLivingBase target, AbilityPhase phase, int tick) {
        return true;
    }

    default public void onAbilityComplete(Ability ability, EntityLivingBase caster, EntityLivingBase target, boolean interrupted) {
    }

    default public boolean onAbilityDamage(Ability ability, EntityLivingBase caster, EntityLivingBase target, float damage, float knockback, float knockbackUp, double knockbackDirX, double knockbackDirZ, float damageMultiplier) {
        return false;
    }

    default public float modifyProjectileDamage(Ability ability, EntityLivingBase caster, float baseDamage) {
        return baseDamage;
    }

    default public float modifyBarrierHealth(Ability ability, EntityLivingBase caster, float baseHealth) {
        return baseHealth;
    }

    default public boolean onAbilityHeal(Ability ability, EntityLivingBase caster, EntityLivingBase target, float healAmount) {
        return false;
    }

    default public Boolean onCheckCondition(AbilityCondition condition, EntityLivingBase caster, EntityLivingBase target) {
        return null;
    }

    default public Boolean onCheckConditionForPlayer(AbilityCondition condition, EntityLivingBase player) {
        return null;
    }
}

