/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class EnergyBarrierData {
    public float maxHealth = 100.0f;
    public boolean useHealth = true;
    public int durationTicks = 200;
    public boolean useDuration = true;
    public float defaultMultiplier = 1.0f;
    public boolean solid = false;
    public boolean knockbackEnabled = false;
    public float knockbackStrength = 1.0f;
    public boolean absorbing = false;
    public float absorbRadius = 0.0f;
    public boolean meleeEnabled = false;
    public float meleeDamageMultiplier = 1.0f;
    public boolean reflect = false;
    public float reflectStrengthPct = 25.0f;
    public boolean targetOwner = false;
    private Map<String, Float> damageMultipliers = new HashMap<String, Float>();

    public EnergyBarrierData() {
    }

    public EnergyBarrierData(float maxHealth, boolean useHealth, int durationTicks, boolean useDuration) {
        this.maxHealth = maxHealth;
        this.useHealth = useHealth;
        this.durationTicks = durationTicks;
        this.useDuration = useDuration;
    }

    public float getMultiplier(String abilityTypeId) {
        Float mult = this.damageMultipliers.get(abilityTypeId);
        return mult != null ? mult.floatValue() : this.defaultMultiplier;
    }

    public void setMultiplier(String abilityTypeId, float multiplier) {
        this.damageMultipliers.put(abilityTypeId, Float.valueOf(multiplier));
    }

    public void removeMultiplier(String abilityTypeId) {
        this.damageMultipliers.remove(abilityTypeId);
    }

    public Map<String, Float> getDamageMultipliers() {
        return this.damageMultipliers;
    }

    public void setDamageMultipliers(Map<String, Float> multipliers) {
        this.damageMultipliers = multipliers != null ? multipliers : new HashMap();
    }

    public float getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = Math.max(1.0f, maxHealth);
    }

    public boolean isUseHealth() {
        return this.useHealth;
    }

    public void setUseHealth(boolean useHealth) {
        this.useHealth = useHealth;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(1, durationTicks);
    }

    public boolean isUseDuration() {
        return this.useDuration;
    }

    public void setUseDuration(boolean useDuration) {
        this.useDuration = useDuration;
    }

    public float getDefaultMultiplier() {
        return this.defaultMultiplier;
    }

    public void setDefaultMultiplier(float defaultMultiplier) {
        this.defaultMultiplier = defaultMultiplier;
    }

    public boolean isReflect() {
        return this.reflect;
    }

    public void setReflect(boolean reflect) {
        this.reflect = reflect;
    }

    public float getReflectStrengthPct() {
        return this.reflectStrengthPct;
    }

    public void setReflectStrengthPct(float reflectStrengthPct) {
        this.reflectStrengthPct = Math.max(0.0f, Math.min(100.0f, reflectStrengthPct));
    }

    public boolean isTargetOwner() {
        return this.targetOwner;
    }

    public void setTargetOwner(boolean targetOwner) {
        this.targetOwner = targetOwner;
    }

    public float getAbsorbRadius() {
        return this.absorbRadius;
    }

    public void setAbsorbRadius(float absorbRadius) {
        this.absorbRadius = absorbRadius;
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("barrierMaxHealth", this.maxHealth);
        nbt.func_74757_a("barrierUseHealth", this.useHealth);
        nbt.func_74768_a("barrierDuration", this.durationTicks);
        nbt.func_74757_a("barrierUseDuration", this.useDuration);
        nbt.func_74776_a("barrierDefaultMult", this.defaultMultiplier);
        nbt.func_74757_a("barrierSolid", this.solid);
        nbt.func_74757_a("barrierKnockback", this.knockbackEnabled);
        nbt.func_74776_a("barrierKnockbackStr", this.knockbackStrength);
        nbt.func_74757_a("barrierAbsorbing", this.absorbing);
        nbt.func_74776_a("barrierAbsorbRadius", this.absorbRadius);
        nbt.func_74757_a("barrierMelee", this.meleeEnabled);
        nbt.func_74776_a("barrierMeleeMult", this.meleeDamageMultiplier);
        nbt.func_74757_a("barrierReflect", this.reflect);
        nbt.func_74776_a("barrierReflectStrPct", this.reflectStrengthPct);
        nbt.func_74757_a("barrierTargetOwner", this.targetOwner);
        NBTTagCompound multNbt = new NBTTagCompound();
        for (Map.Entry<String, Float> entry : this.damageMultipliers.entrySet()) {
            multNbt.func_74776_a(entry.getKey(), entry.getValue().floatValue());
        }
        nbt.func_74782_a("barrierMultipliers", (NBTBase)multNbt);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.maxHealth = nbt.func_74764_b("barrierMaxHealth") ? nbt.func_74760_g("barrierMaxHealth") : 100.0f;
        this.useHealth = !nbt.func_74764_b("barrierUseHealth") || nbt.func_74767_n("barrierUseHealth");
        this.durationTicks = nbt.func_74764_b("barrierDuration") ? nbt.func_74762_e("barrierDuration") : 200;
        this.useDuration = !nbt.func_74764_b("barrierUseDuration") || nbt.func_74767_n("barrierUseDuration");
        this.defaultMultiplier = nbt.func_74764_b("barrierDefaultMult") ? nbt.func_74760_g("barrierDefaultMult") : 1.0f;
        this.solid = nbt.func_74764_b("barrierSolid") && nbt.func_74767_n("barrierSolid");
        this.knockbackEnabled = nbt.func_74764_b("barrierKnockback") && nbt.func_74767_n("barrierKnockback");
        this.knockbackStrength = nbt.func_74764_b("barrierKnockbackStr") ? nbt.func_74760_g("barrierKnockbackStr") : 1.0f;
        this.absorbing = nbt.func_74764_b("barrierAbsorbing") && nbt.func_74767_n("barrierAbsorbing");
        this.absorbRadius = nbt.func_74764_b("barrierAbsorbRadius") ? nbt.func_74760_g("barrierAbsorbRadius") : 0.0f;
        this.meleeEnabled = nbt.func_74764_b("barrierMelee") && nbt.func_74767_n("barrierMelee");
        this.meleeDamageMultiplier = nbt.func_74764_b("barrierMeleeMult") ? nbt.func_74760_g("barrierMeleeMult") : 1.0f;
        this.reflect = nbt.func_74764_b("barrierReflect") && nbt.func_74767_n("barrierReflect");
        this.setReflectStrengthPct(nbt.func_74764_b("barrierReflectStrPct") ? nbt.func_74760_g("barrierReflectStrPct") : 25.0f);
        this.targetOwner = nbt.func_74764_b("barrierTargetOwner") && nbt.func_74767_n("barrierTargetOwner");
        this.damageMultipliers.clear();
        if (nbt.func_74764_b("barrierMultipliers")) {
            NBTTagCompound multNbt = nbt.func_74775_l("barrierMultipliers");
            Set keys = multNbt.func_150296_c();
            for (String key : keys) {
                this.damageMultipliers.put(key, Float.valueOf(multNbt.func_74760_g(key)));
            }
        }
    }

    public EnergyBarrierData copy() {
        EnergyBarrierData copy = new EnergyBarrierData(this.maxHealth, this.useHealth, this.durationTicks, this.useDuration);
        copy.defaultMultiplier = this.defaultMultiplier;
        copy.solid = this.solid;
        copy.knockbackEnabled = this.knockbackEnabled;
        copy.knockbackStrength = this.knockbackStrength;
        copy.absorbing = this.absorbing;
        copy.absorbRadius = this.absorbRadius;
        copy.meleeEnabled = this.meleeEnabled;
        copy.meleeDamageMultiplier = this.meleeDamageMultiplier;
        copy.reflect = this.reflect;
        copy.reflectStrengthPct = this.reflectStrengthPct;
        copy.targetOwner = this.targetOwner;
        copy.damageMultipliers = new HashMap<String, Float>(this.damageMultipliers);
        return copy;
    }
}

