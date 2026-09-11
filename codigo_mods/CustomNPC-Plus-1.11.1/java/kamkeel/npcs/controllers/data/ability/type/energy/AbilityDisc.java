/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Vec3
 */
package kamkeel.npcs.controllers.data.ability.type.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.ProjectileData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.enums.HitType;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergyProjectile;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityAbilityDisc;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import noppes.npcs.api.ability.type.IAbilityDisc;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityDisc
extends AbilityEnergyProjectile<EntityAbilityDisc>
implements IAbilityDisc {
    private float discRadius = 1.0f;
    private float discThickness = 0.2f;
    private boolean vertical = false;
    private boolean boomerang = false;
    private int boomerangDelay = 40;

    public AbilityDisc() {
        super(new EnergyDisplayData(0xFFFFFF, 0xFF8800, true, 0.4f, 0.5f, 10.0f), new EnergyCombatData(8.0f, 1.2f, 0.15f, false, 3.0f, 0.5f, HitType.PIERCE, 5), new EnergyHomingData(0.6f, true, 0.12f, 18.0f), new EnergyLifespanData(150.0f, 200));
        this.typeId = "ability.cnpc.disc";
        this.name = "Disc";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 30.0f;
        this.minRange = 5.0f;
        this.cooldownTicks = 0;
        this.windUpTicks = 60;
        this.lockMovement = LockMode.WINDUP;
        this.telegraphType = TelegraphType.CIRCLE;
        this.showTelegraph = true;
        this.windUpAnimationName = "Ability_Disc_Windup";
        this.activeAnimationName = "Ability_Disc_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/disc.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/disc_overlay.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    protected EntityAbilityDisc createEntity(EntityLivingBase caster, EntityLivingBase target, Vec3 spawnPos, EnergyDisplayData resolved, int index) {
        return new EntityAbilityDisc(caster.field_70170_p, caster, target, spawnPos.field_72450_a, spawnPos.field_72448_b, spawnPos.field_72449_c, this.discRadius, this.discThickness, resolved, this.combatData, this.homingData, this.lightningData, this.lifespanData, this.boomerang, this.boomerangDelay);
    }

    @Override
    protected void fireEntity(EntityAbilityDisc disc, EntityLivingBase target) {
        if (this.isPreview()) {
            disc.startPreviewFiring();
        } else {
            disc.startMoving(target);
        }
    }

    @Override
    protected void setupEntityCharging(EntityAbilityDisc disc, ProjectileData projData, int index) {
        disc.setupCharging(projData.anchor, this.windUpTicks, this.vertical);
    }

    @Override
    protected void setupEntityPreview(EntityAbilityDisc disc, EntityLivingBase caster, EnergyDisplayData resolved, ProjectileData projData, int index) {
        disc.setupPreview(caster, this.discRadius, this.discThickness, resolved, this.lightningData, projData.anchor, this.windUpTicks, this.vertical);
    }

    protected EntityAbilityDisc[] createEntityArray(int size) {
        return new EntityAbilityDisc[size];
    }

    @Override
    protected float getProjectileTelegraphRadius() {
        return this.discRadius * 1.5f;
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.single", a -> a.setName("Disc")), new AbilityVariant("ability.variant.dual", a -> {
            AbilityDisc disc = (AbilityDisc)a;
            a.setName("Dual Disc");
            disc.setProjectileCount(2);
            disc.setFireDelay(5);
            disc.projectiles[1].colorOverride = true;
            disc.projectiles[1].innerColor = 0xFFFFFF;
            disc.projectiles[1].outerColor = 0x8800FF;
            a.setWindUpAnimationName("Ability_DiscDual_Windup");
            a.setActiveAnimationName("Ability_DiscDual_Active");
        }));
    }

    @Override
    protected void writeTypeSpecificNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("discRadius", this.discRadius);
        nbt.func_74776_a("discThickness", this.discThickness);
        nbt.func_74757_a("vertical", this.vertical);
        nbt.func_74757_a("boomerang", this.boomerang);
        nbt.func_74768_a("boomerangDelay", this.boomerangDelay);
    }

    @Override
    protected void readTypeSpecificNBT(NBTTagCompound nbt) {
        this.discRadius = nbt.func_74760_g("discRadius");
        this.discThickness = nbt.func_74760_g("discThickness");
        this.vertical = nbt.func_74767_n("vertical");
        this.boomerang = nbt.func_74767_n("boomerang");
        this.boomerangDelay = nbt.func_74762_e("boomerangDelay");
    }

    @Override
    public float getSpeed() {
        return this.homingData.speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.homingData.speed = speed;
    }

    @Override
    public float getDiscRadius() {
        return this.discRadius;
    }

    @Override
    public void setDiscRadius(float discRadius) {
        this.discRadius = discRadius;
    }

    @Override
    public float getDiscThickness() {
        return this.discThickness;
    }

    @Override
    public void setDiscThickness(float discThickness) {
        this.discThickness = discThickness;
    }

    @Override
    public boolean isVertical() {
        return this.vertical;
    }

    @Override
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    @Override
    public boolean isBoomerang() {
        return this.boomerang;
    }

    @Override
    public void setBoomerang(boolean boomerang) {
        this.boomerang = boomerang;
    }

    @Override
    public int getBoomerangDelay() {
        return this.boomerangDelay;
    }

    @Override
    public void setBoomerangDelay(int boomerangDelay) {
        this.boomerangDelay = boomerangDelay;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.row(FieldDef.intField("ability.projectileCount", this::getProjectileCount, this::setProjectileCount).range(1.0f, 8.0f), FieldDef.intField("ability.fireDelay", this::getFireDelay, this::setFireDelay).range(0.0f, 200.0f).visibleWhen(() -> this.projectileCount > 1)));
        defs.add(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("stats.speed", this::getSpeed, this::setSpeed)));
        defs.add(FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback));
        defs.add(FieldDef.section("ability.section.disc"));
        defs.add(FieldDef.row(FieldDef.floatField("gui.radius", this::getDiscRadius, this::setDiscRadius).range(0.1f, 100.0f), FieldDef.floatField("gui.thickness", this::getDiscThickness, this::setDiscThickness).range(0.05f, 100.0f)));
        defs.add(FieldDef.boolField("ability.vertical", this::isVertical, this::setVertical).hover("ability.hover.vertical"));
        defs.add(FieldDef.row(FieldDef.floatField("ability.maxDistance", this::getMaxDistance, this::setMaxDistance).range(1.0f, 500.0f), FieldDef.intField("ability.lifetime", this::getMaxLifetime, this::setMaxLifetime).range(1.0f, 1200.0f)));
        defs.add(FieldDef.section("ability.section.homing"));
        defs.add(FieldDef.boolField("gui.enabled", this::isHoming, this::setHoming).hover("ability.hover.homing"));
        defs.add(FieldDef.floatField("gui.strength", this::getHomingStrength, this::setHomingStrength).visibleWhen(this::isHoming));
        defs.add(FieldDef.section("ability.section.boomerang"));
        defs.add(FieldDef.boolField("gui.enabled", this::isBoomerang, this::setBoomerang).hover("ability.hover.boomerang"));
        defs.add(FieldDef.intField("gui.delay", this::getBoomerangDelay, this::setBoomerangDelay).visibleWhen(this::isBoomerang));
        defs.add(FieldDef.section("ability.section.explosive"));
        defs.add(FieldDef.boolField("gui.enabled", this::isExplosive, this::setExplosive).hover("ability.hover.explosive"));
        defs.add(FieldDef.floatField("gui.radius", this::getExplosionRadius, this::setExplosionRadius).range(0.0f, 15.0f).visibleWhen(this::isExplosive));
        defs.add(AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects));
    }
}

