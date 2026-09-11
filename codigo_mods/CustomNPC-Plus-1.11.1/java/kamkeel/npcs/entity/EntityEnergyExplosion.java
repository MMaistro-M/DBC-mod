/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.EnergyController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLightningData;
import kamkeel.npcs.entity.EntityEnergyAbility;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.util.AttributeAttackUtil;
import kamkeel.npcs.util.CNPCDebug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityEnergyExplosion
extends EntityEnergyAbility {
    private float maxRadius = 2.0f;
    private int durationTicks = 10;
    private float prevRenderRadius = 0.0f;
    private float renderRadius = 0.0f;
    private long renderSeed = 0L;
    private float damage = 0.0f;
    private float knockback = 0.0f;
    private float knockbackUp = 0.1f;
    private float damageFalloff = 0.5f;
    private boolean damageEnabled = false;
    private boolean hasDamaged = false;

    public EntityEnergyExplosion(World world) {
        super(world);
        this.field_70145_X = true;
        this.func_70105_a(0.1f, 0.1f);
    }

    public EntityEnergyExplosion(World world, EntityEnergyProjectile source, float radius) {
        this(world);
        if (source != null) {
            this.ownerEntityId = source.getOwnerEntityId();
            EnergyDisplayData sourceDisplay = source.getDisplayData();
            this.displayData = sourceDisplay != null ? sourceDisplay.copy() : new EnergyDisplayData();
            EnergyLightningData sourceLightning = source.getLightningData();
            this.lightningData = sourceLightning != null ? sourceLightning.copy() : new EnergyLightningData();
            this.func_70107_b(source.field_70165_t, source.field_70163_u, source.field_70161_v);
        }
        this.setExplosionRadius(radius);
        this.renderSeed = world != null ? world.field_73012_v.nextLong() : 0L;
    }

    public EntityEnergyExplosion(World world, Entity owner, double x, double y, double z, float radius) {
        this(world);
        if (owner != null) {
            this.ownerEntityId = owner.func_145782_y();
        }
        this.func_70107_b(x, y, z);
        this.setExplosionRadius(radius);
        this.renderSeed = world != null ? world.field_73012_v.nextLong() : 0L;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void func_70071_h_() {
        boolean isClient;
        this.prevRenderRadius = this.renderRadius;
        super.func_70071_h_();
        float progress = this.getLifeProgress(0.0f);
        float eased = 1.0f - (1.0f - progress) * (1.0f - progress);
        this.renderRadius = this.maxRadius * eased;
        if (this.damageEnabled && !this.hasDamaged && !this.field_70170_p.field_72995_K && this.field_70173_aa >= 1) {
            this.applyExplosionDamage();
            this.hasDamaged = true;
        }
        if ((isClient = this.field_70170_p.field_72995_K) ? CNPCDebug.isClientEnabled("energy") : CNPCDebug.isServerEnabled("energy")) {
            CNPCDebug.log("energy", isClient, String.format("[Explosion id=%d tick=%d] pos=(%.2f,%.2f,%.2f) renderRadius=%.2f maxRadius=%.2f progress=%.2f", this.func_145782_y(), this.field_70173_aa, this.field_70165_t, this.field_70163_u, this.field_70161_v, Float.valueOf(this.renderRadius), Float.valueOf(this.maxRadius), Float.valueOf(this.getLifeProgress(0.0f))));
        }
        if (this.field_70173_aa >= this.durationTicks) {
            this.func_70106_y();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void applyExplosionDamage() {
        if (this.damage <= 0.0f || this.maxRadius <= 0.0f) {
            return;
        }
        Entity owner = this.getOwnerEntity();
        double explosionRadSq = this.maxRadius * this.maxRadius;
        AxisAlignedBB explosionBox = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)this.maxRadius), (double)(this.field_70163_u - (double)this.maxRadius), (double)(this.field_70161_v - (double)this.maxRadius), (double)(this.field_70165_t + (double)this.maxRadius), (double)(this.field_70163_u + (double)this.maxRadius), (double)(this.field_70161_v + (double)this.maxRadius));
        List targets = this.field_70170_p.func_72872_a(EntityLivingBase.class, explosionBox);
        for (EntityLivingBase target : targets) {
            double kbDz;
            double kbDx;
            double len;
            double closestZ;
            double dz;
            double closestY;
            double dy;
            double closestX;
            double dx;
            double distSq;
            if (target == owner || target.field_70121_D == null || (distSq = (dx = (closestX = Math.max(target.field_70121_D.field_72340_a, Math.min(this.field_70165_t, target.field_70121_D.field_72336_d))) - this.field_70165_t) * dx + (dy = (closestY = Math.max(target.field_70121_D.field_72338_b, Math.min(this.field_70163_u, target.field_70121_D.field_72337_e))) - this.field_70163_u) * dy + (dz = (closestZ = Math.max(target.field_70121_D.field_72339_c, Math.min(this.field_70161_v, target.field_70121_D.field_72334_f))) - this.field_70161_v) * dz) > explosionRadSq) continue;
            float falloff = 1.0f;
            if (this.damageFalloff != 0.0f && distSq > 0.0) {
                float dist = (float)Math.sqrt(distSq);
                falloff = 1.0f - dist / this.maxRadius * this.damageFalloff;
            }
            float finalDmg = this.damage * falloff;
            float finalKb = this.knockback * falloff;
            boolean handled = false;
            if (this.customDamageData != null && owner instanceof EntityLivingBase) {
                double kbDirX = target.field_70165_t - this.field_70165_t;
                double kbDirZ = target.field_70161_v - this.field_70161_v;
                handled = EnergyController.Instance.fireOnEnergyDamage(this, (EntityLivingBase)owner, target, finalDmg, finalKb, this.knockbackUp, kbDirX, kbDirZ, 1.0f, this.customDamageData);
            }
            int prevHurtResist = Ability.clearHurtResistanceIfNeeded(target, this.ignoreIFrames);
            try {
                if (!handled) {
                    float dmgToApply = finalDmg;
                    if (this.magicData != null && !this.magicData.isEmpty() && owner instanceof EntityLivingBase) {
                        dmgToApply = AttributeAttackUtil.calculateAbilityDamage((EntityLivingBase)owner, target, finalDmg, this.magicData);
                    }
                    if (owner instanceof EntityNPCInterface) {
                        target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", (Entity)((EntityNPCInterface)owner)), dmgToApply);
                    } else if (owner instanceof EntityPlayer) {
                        target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)owner)), dmgToApply);
                    } else if (owner instanceof EntityLivingBase) {
                        target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)((EntityLivingBase)owner)), dmgToApply);
                    } else {
                        target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", null), dmgToApply);
                    }
                }
            }
            finally {
                Ability.restoreHurtResistanceIfNeeded(target, this.ignoreIFrames, prevHurtResist);
            }
            if (!(finalKb > 0.0f) || !((len = Math.sqrt((kbDx = target.field_70165_t - this.field_70165_t) * kbDx + (kbDz = target.field_70161_v - this.field_70161_v) * kbDz)) > 0.0)) continue;
            target.func_70024_g(kbDx / len * (double)finalKb * 0.5, (double)this.knockbackUp, kbDz / len * (double)finalKb * 0.5);
            target.field_70133_I = true;
        }
        this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "random.explode", 1.0f, 1.0f);
    }

    public void setExplosionRadius(float radius) {
        this.maxRadius = Math.max(0.5f, EntityEnergyExplosion.sanitize(radius, 2.0f, 50.0f));
        this.durationTicks = Math.max(6, Math.min(18, (int)(6.0f + this.maxRadius * 1.8f)));
        this.func_70105_a(this.maxRadius * 2.0f, this.maxRadius * 2.0f);
    }

    public float getExplosionRadius() {
        return this.maxRadius;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public float getInterpolatedRadius(float partialTicks) {
        return this.prevRenderRadius + (this.renderRadius - this.prevRenderRadius) * partialTicks;
    }

    public float getLifeProgress(float partialTicks) {
        if (this.durationTicks <= 0) {
            return 1.0f;
        }
        float age = ((float)this.field_70173_aa + partialTicks) / (float)this.durationTicks;
        return MathHelper.func_76131_a((float)age, (float)0.0f, (float)1.0f);
    }

    public long getRenderSeed() {
        return this.renderSeed;
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = Math.max(0.0f, damage);
        this.damageEnabled = this.damage > 0.0f;
    }

    public float getKnockback() {
        return this.knockback;
    }

    public void setKnockback(float knockback) {
        this.knockback = Math.max(0.0f, knockback);
    }

    public float getKnockbackUp() {
        return this.knockbackUp;
    }

    public void setKnockbackUp(float knockbackUp) {
        this.knockbackUp = knockbackUp;
    }

    public float getDamageFalloff() {
        return this.damageFalloff;
    }

    public void setDamageFalloff(float falloff) {
        this.damageFalloff = MathHelper.func_76131_a((float)falloff, (float)0.0f, (float)1.0f);
    }

    public boolean isDamageEnabled() {
        return this.damageEnabled;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        double d = Math.max(16.0, (double)this.maxRadius * 64.0);
        return distance < d * d;
    }

    public boolean func_70067_L() {
        return false;
    }

    @Override
    protected void writeSpawnNBT(NBTTagCompound nbt) {
        this.writeEnergyBaseNBT(nbt);
        nbt.func_74780_a("PosX", this.field_70165_t);
        nbt.func_74780_a("PosY", this.field_70163_u);
        nbt.func_74780_a("PosZ", this.field_70161_v);
        nbt.func_74776_a("ExplosionRadius", this.maxRadius);
        nbt.func_74768_a("ExplosionDuration", this.durationTicks);
        nbt.func_74772_a("ExplosionSeed", this.renderSeed);
        if (this.damageEnabled) {
            nbt.func_74776_a("Damage", this.damage);
            nbt.func_74776_a("Knockback", this.knockback);
            nbt.func_74776_a("KnockbackUp", this.knockbackUp);
            nbt.func_74776_a("DamageFalloff", this.damageFalloff);
        }
    }

    @Override
    protected void readSpawnNBT(NBTTagCompound nbt) {
        this.readEnergyBaseNBT(nbt);
        double x = nbt.func_74764_b("PosX") ? nbt.func_74769_h("PosX") : this.field_70165_t;
        double y = nbt.func_74764_b("PosY") ? nbt.func_74769_h("PosY") : this.field_70163_u;
        double z = nbt.func_74764_b("PosZ") ? nbt.func_74769_h("PosZ") : this.field_70161_v;
        this.func_70107_b(x, y, z);
        this.field_70169_q = x;
        this.field_70167_r = y;
        this.field_70166_s = z;
        this.field_70142_S = x;
        this.field_70137_T = y;
        this.field_70136_U = z;
        this.setExplosionRadius(nbt.func_74764_b("ExplosionRadius") ? nbt.func_74760_g("ExplosionRadius") : 2.0f);
        int n = this.durationTicks = nbt.func_74764_b("ExplosionDuration") ? nbt.func_74762_e("ExplosionDuration") : this.durationTicks;
        if (this.durationTicks <= 0) {
            this.durationTicks = 10;
        }
        this.renderSeed = nbt.func_74764_b("ExplosionSeed") ? nbt.func_74763_f("ExplosionSeed") : 0L;
        this.renderRadius = 0.0f;
        this.prevRenderRadius = 0.0f;
        if (nbt.func_74764_b("Damage")) {
            this.damage = nbt.func_74760_g("Damage");
            this.knockback = nbt.func_74760_g("Knockback");
            this.knockbackUp = nbt.func_74760_g("KnockbackUp");
            this.damageFalloff = nbt.func_74760_g("DamageFalloff");
            this.damageEnabled = this.damage > 0.0f;
        }
    }
}

