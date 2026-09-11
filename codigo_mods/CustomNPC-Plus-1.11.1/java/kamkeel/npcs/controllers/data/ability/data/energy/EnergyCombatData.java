/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import kamkeel.npcs.controllers.data.ability.enums.HitType;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.data.IEnergyCombatData;

public class EnergyCombatData
implements IEnergyCombatData {
    public static final float MAX_EXPLOSION_RADIUS = 15.0f;
    public static final int DEFAULT_MAX_HITS = 5;
    public static final int MAX_HITS = 200;
    public float damage = 7.0f;
    public float knockback = 1.0f;
    public float knockbackUp = 0.1f;
    public boolean explosive = false;
    public float explosionRadius = 3.0f;
    public float explosionDamageFalloff = 0.5f;
    public HitType hitType = HitType.SINGLE;
    public int multiHitDelayTicks = 5;
    public int maxHits = 5;

    public EnergyCombatData() {
    }

    public EnergyCombatData(float damage, float knockback, float knockbackUp, boolean explosive, float explosionRadius, float explosionDamageFalloff, HitType hitType, int multiHitDelay) {
        this(damage, knockback, knockbackUp, explosive, explosionRadius, explosionDamageFalloff, hitType, multiHitDelay, 5);
    }

    public EnergyCombatData(float damage, float knockback, float knockbackUp, boolean explosive, float explosionRadius, float explosionDamageFalloff, HitType hitType, int multiHitDelay, int maxHits) {
        this.damage = damage;
        this.knockback = knockback;
        this.knockbackUp = knockbackUp;
        this.explosive = explosive;
        this.explosionRadius = EnergyCombatData.clampExplosionRadius(explosionRadius);
        this.explosionDamageFalloff = explosionDamageFalloff;
        this.hitType = hitType;
        this.multiHitDelayTicks = multiHitDelay;
        this.maxHits = EnergyCombatData.clampMaxHits(maxHits);
    }

    public EnergyCombatData(float damage, float knockback, float knockbackUp, boolean explosive, float explosionRadius, float explosionDamageFalloff) {
        this.damage = damage;
        this.knockback = knockback;
        this.knockbackUp = knockbackUp;
        this.explosive = explosive;
        this.explosionRadius = EnergyCombatData.clampExplosionRadius(explosionRadius);
        this.explosionDamageFalloff = explosionDamageFalloff;
    }

    @Override
    public float getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(float damage) {
        this.damage = damage;
    }

    @Override
    public float getKnockback() {
        return this.knockback;
    }

    @Override
    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    @Override
    public float getKnockbackUp() {
        return this.knockbackUp;
    }

    @Override
    public void setKnockbackUp(float knockbackUp) {
        this.knockbackUp = knockbackUp;
    }

    @Override
    public boolean isExplosive() {
        return this.explosive;
    }

    @Override
    public void setExplosive(boolean explosive) {
        this.explosive = explosive;
    }

    @Override
    public float getExplosionRadius() {
        return this.explosionRadius;
    }

    @Override
    public void setExplosionRadius(float explosionRadius) {
        this.explosionRadius = EnergyCombatData.clampExplosionRadius(explosionRadius);
    }

    @Override
    public float getExplosionDamageFalloff() {
        return this.explosionDamageFalloff;
    }

    @Override
    public void setExplosionDamageFalloff(float explosionDamageFalloff) {
        this.explosionDamageFalloff = explosionDamageFalloff;
    }

    public int getMaxHits() {
        return this.maxHits;
    }

    public void setMaxHits(int maxHits) {
        this.maxHits = EnergyCombatData.clampMaxHits(maxHits);
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74776_a("knockbackUp", this.knockbackUp);
        nbt.func_74757_a("explosive", this.explosive);
        nbt.func_74776_a("explosionRadius", this.explosionRadius);
        nbt.func_74776_a("explosionDamageFalloff", this.explosionDamageFalloff);
        nbt.func_74768_a("hitType", this.hitType.ordinal());
        nbt.func_74768_a("multiHitDelayTicks", this.multiHitDelayTicks);
        nbt.func_74768_a("maxHits", this.maxHits);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.damage = nbt.func_74764_b("damage") ? nbt.func_74760_g("damage") : 7.0f;
        this.knockback = nbt.func_74764_b("knockback") ? nbt.func_74760_g("knockback") : 1.0f;
        this.knockbackUp = nbt.func_74764_b("knockbackUp") ? nbt.func_74760_g("knockbackUp") : 0.1f;
        this.explosive = nbt.func_74764_b("explosive") && nbt.func_74767_n("explosive");
        this.explosionRadius = nbt.func_74764_b("explosionRadius") ? nbt.func_74760_g("explosionRadius") : 3.0f;
        this.explosionDamageFalloff = nbt.func_74764_b("explosionDamageFalloff") ? nbt.func_74760_g("explosionDamageFalloff") : 0.5f;
        this.hitType = HitType.fromOrdinal(nbt.func_74764_b("hitType") ? nbt.func_74762_e("hitType") : 0);
        this.multiHitDelayTicks = nbt.func_74764_b("multiHitDelayTicks") ? nbt.func_74762_e("multiHitDelayTicks") : 5;
        int n = this.maxHits = nbt.func_74764_b("maxHits") ? nbt.func_74762_e("maxHits") : 5;
        if (Float.isNaN(this.damage) || Float.isInfinite(this.damage)) {
            this.damage = 7.0f;
        }
        if (Float.isNaN(this.knockback) || Float.isInfinite(this.knockback) || this.knockback < 0.0f) {
            this.knockback = 1.0f;
        }
        if (Float.isNaN(this.explosionRadius) || Float.isInfinite(this.explosionRadius) || this.explosionRadius < 0.0f) {
            this.explosionRadius = 3.0f;
        }
        this.explosionRadius = EnergyCombatData.clampExplosionRadius(this.explosionRadius);
        if (this.multiHitDelayTicks < 1) {
            this.multiHitDelayTicks = 1;
        }
        this.maxHits = EnergyCombatData.clampMaxHits(this.maxHits);
    }

    private static float clampExplosionRadius(float explosionRadius) {
        if (Float.isNaN(explosionRadius) || Float.isInfinite(explosionRadius)) {
            return 0.0f;
        }
        return Math.max(0.0f, Math.min(15.0f, explosionRadius));
    }

    private static int clampMaxHits(int maxHits) {
        return Math.max(1, Math.min(200, maxHits));
    }

    public EnergyCombatData copy() {
        EnergyCombatData copy = new EnergyCombatData(this.damage, this.knockback, this.knockbackUp, this.explosive, this.explosionRadius, this.explosionDamageFalloff);
        copy.hitType = this.hitType;
        copy.multiHitDelayTicks = this.multiHitDelayTicks;
        copy.maxHits = this.maxHits;
        return copy;
    }
}

