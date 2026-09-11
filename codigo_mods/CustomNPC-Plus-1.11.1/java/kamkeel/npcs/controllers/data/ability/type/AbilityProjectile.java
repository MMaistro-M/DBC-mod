/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.api.ability.type.IAbilityProjectile;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityProjectile
extends Ability
implements IAbilityProjectile {
    private float damage = 6.0f;
    private float speed = 1.5f;
    private float knockback = 0.5f;
    private String projectileType = "fireball";
    private boolean explosive = false;
    private float explosionRadius = 0.0f;
    private boolean homing = false;
    private float homingStrength = 0.1f;

    public AbilityProjectile() {
        this.typeId = "ability.cnpc.projectile";
        this.name = "Projectile";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.minRange = 5.0f;
        this.maxRange = 20.0f;
        this.cooldownTicks = 0;
        this.windUpTicks = 15;
        this.lockMovement = LockMode.NO;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
    }

    @Override
    public boolean allowBurst() {
        return false;
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AGGRO_TARGET};
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (caster.field_70170_p.field_72995_K && !this.isPreview()) {
            this.signalCompletion();
            return;
        }
        if (this.isPlayerCaster(caster)) {
            this.executePlayerProjectile(caster, caster.field_70170_p);
        } else if (target != null) {
            this.executeNpcProjectile(caster, target, caster.field_70170_p);
        }
        this.signalCompletion();
    }

    private void executeNpcProjectile(EntityLivingBase caster, EntityLivingBase target, World world) {
        double dz;
        if (this.isPreview()) {
            return;
        }
        double dx = target.field_70165_t - caster.field_70165_t;
        double dy = target.field_70163_u + (double)(target.field_70131_O / 2.0f) - (caster.field_70163_u + (double)caster.func_70047_e());
        double len = Math.sqrt(dx * dx + dy * dy + (dz = target.field_70161_v - caster.field_70161_v) * dz);
        if (len > 0.0) {
            dx /= len;
            dy /= len;
            dz /= len;
        }
        this.applyAbilityDamageWithDirection(caster, target, this.damage, this.knockback, dx, dz);
        world.func_72956_a((Entity)caster, "random.bow", 1.0f, 0.8f);
        if (this.explosive && this.explosionRadius > 0.0f) {
            this.applyExplosionDamage(caster, target, world, target.field_70165_t, target.field_70163_u, target.field_70161_v);
        }
        this.spawnProjectileParticles(world, caster, target);
    }

    private void executePlayerProjectile(EntityLivingBase caster, World world) {
        if (this.isPreview()) {
            return;
        }
        Vec3 look = caster.func_70040_Z();
        double eyeX = caster.field_70165_t;
        double eyeY = caster.field_70163_u + (double)caster.func_70047_e();
        double eyeZ = caster.field_70161_v;
        EntityLivingBase hitTarget = null;
        double closestDist = this.maxRange;
        List candidates = world.func_72839_b((Entity)caster, caster.field_70121_D.func_72314_b((double)this.maxRange, (double)this.maxRange, (double)this.maxRange));
        for (Entity entity : candidates) {
            double dot;
            double dz;
            double dy;
            double dx;
            double dist;
            if (!(entity instanceof EntityLivingBase) || entity.field_70128_L || (dist = Math.sqrt((dx = entity.field_70165_t - eyeX) * dx + (dy = entity.field_70163_u + (double)(entity.field_70131_O / 2.0f) - eyeY) * dy + (dz = entity.field_70161_v - eyeZ) * dz)) > (double)this.maxRange || dist < 0.5 || (dot = (dx * look.field_72450_a + dy * look.field_72448_b + dz * look.field_72449_c) / dist) < 0.95 || !(dist < closestDist)) continue;
            closestDist = dist;
            hitTarget = (EntityLivingBase)entity;
        }
        world.func_72956_a((Entity)caster, "random.bow", 1.0f, 0.8f);
        if (hitTarget != null) {
            this.applyAbilityDamageWithDirection(caster, hitTarget, this.damage, this.knockback, look.field_72450_a, look.field_72449_c);
            if (this.explosive && this.explosionRadius > 0.0f) {
                this.applyExplosionDamage(caster, hitTarget, world, hitTarget.field_70165_t, hitTarget.field_70163_u, hitTarget.field_70161_v);
            }
            this.spawnProjectileParticles(world, caster, hitTarget);
        } else {
            this.spawnProjectileParticlesInDirection(world, caster, look);
        }
    }

    private void applyExplosionDamage(EntityLivingBase caster, EntityLivingBase primaryTarget, World world, double x, double y, double z) {
        List entities = world.func_72839_b((Entity)primaryTarget, primaryTarget.field_70121_D.func_72314_b((double)this.explosionRadius, (double)this.explosionRadius, (double)this.explosionRadius));
        for (Entity entity : entities) {
            EntityLivingBase living;
            float dist;
            if (!(entity instanceof EntityLivingBase) || entity == caster || !((dist = primaryTarget.func_70032_d((Entity)(living = (EntityLivingBase)entity))) < this.explosionRadius)) continue;
            float falloff = 1.0f - dist / this.explosionRadius;
            this.applyAbilityDamage(caster, living, this.damage * falloff * 0.5f, 0.0f);
        }
        world.func_72956_a((Entity)primaryTarget, "random.explode", 0.5f, 1.0f);
        for (int i = 0; i < 10; ++i) {
            world.func_72869_a("explode", x + (world.field_73012_v.nextDouble() - 0.5) * (double)this.explosionRadius, y + world.field_73012_v.nextDouble() * 2.0, z + (world.field_73012_v.nextDouble() - 0.5) * (double)this.explosionRadius, 0.0, 0.1, 0.0);
        }
    }

    private void spawnProjectileParticles(World world, EntityLivingBase caster, EntityLivingBase target) {
        double startX = caster.field_70165_t;
        double startY = caster.field_70163_u + (double)caster.func_70047_e();
        double startZ = caster.field_70161_v;
        double endX = target.field_70165_t;
        double endY = target.field_70163_u + (double)(target.field_70131_O / 2.0f);
        double endZ = target.field_70161_v;
        double dx = endX - startX;
        double dy = endY - startY;
        double dz = endZ - startZ;
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
        String particle = "flame";
        if (this.projectileType.equals("arrow")) {
            particle = "crit";
        } else if (this.projectileType.equals("magic")) {
            particle = "witchMagic";
        }
        int steps = (int)Math.max(5.0, dist * 2.0);
        for (int i = 0; i < steps; ++i) {
            double progress = (double)i / (double)steps;
            double px = startX + dx * progress;
            double py = startY + dy * progress;
            double pz = startZ + dz * progress;
            world.func_72869_a(particle, px, py, pz, 0.0, 0.0, 0.0);
        }
    }

    private void spawnProjectileParticlesInDirection(World world, EntityLivingBase caster, Vec3 look) {
        double startX = caster.field_70165_t;
        double startY = caster.field_70163_u + (double)caster.func_70047_e();
        double startZ = caster.field_70161_v;
        String particle = "flame";
        if (this.projectileType.equals("arrow")) {
            particle = "crit";
        } else if (this.projectileType.equals("magic")) {
            particle = "witchMagic";
        }
        int steps = (int)Math.max(5.0f, this.maxRange * 2.0f);
        for (int i = 0; i < steps; ++i) {
            double progress = (double)i / (double)steps * (double)this.maxRange;
            double px = startX + look.field_72450_a * progress;
            double py = startY + look.field_72448_b * progress;
            double pz = startZ + look.field_72449_c * progress;
            world.func_72869_a(particle, px, py, pz, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
    }

    @Override
    public float getTelegraphRadius() {
        return 0.0f;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("speed", this.speed);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74778_a("projectileType", this.projectileType);
        nbt.func_74757_a("explosive", this.explosive);
        nbt.func_74776_a("explosionRadius", this.explosionRadius);
        nbt.func_74757_a("homing", this.homing);
        nbt.func_74776_a("homingStrength", this.homingStrength);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.damage = nbt.func_74760_g("damage");
        this.speed = nbt.func_74760_g("speed");
        this.knockback = nbt.func_74760_g("knockback");
        this.projectileType = nbt.func_74779_i("projectileType");
        this.explosive = nbt.func_74767_n("explosive");
        this.explosionRadius = nbt.func_74760_g("explosionRadius");
        this.homing = nbt.func_74767_n("homing");
        this.homingStrength = nbt.func_74760_g("homingStrength");
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
    public float getDisplayDamage() {
        return this.damage;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
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
    public String getProjectileType() {
        return this.projectileType;
    }

    @Override
    public void setProjectileType(String projectileType) {
        this.projectileType = projectileType;
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
        this.explosionRadius = explosionRadius;
    }

    @Override
    public boolean isHoming() {
        return this.homing;
    }

    @Override
    public void setHoming(boolean homing) {
        this.homing = homing;
    }

    @Override
    public float getHomingStrength() {
        return this.homingStrength;
    }

    @Override
    public void setHomingStrength(float homingStrength) {
        this.homingStrength = homingStrength;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("stats.speed", this::getSpeed, this::setSpeed)), FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback), FieldDef.stringEnumField("ability.projectileType", new String[]{"fireball", "arrow", "magic"}, this::getProjectileType, this::setProjectileType), FieldDef.section("ability.section.homing"), FieldDef.boolField("gui.enabled", this::isHoming, this::setHoming).hover("ability.hover.homing"), FieldDef.floatField("gui.strength", this::getHomingStrength, this::setHomingStrength).visibleWhen(this::isHoming), FieldDef.section("ability.section.explosive"), FieldDef.boolField("gui.enabled", this::isExplosive, this::setExplosive).hover("ability.hover.explosive"), FieldDef.floatField("gui.radius", this::getExplosionRadius, this::setExplosionRadius).visibleWhen(this::isExplosive), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }
}

