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
import kamkeel.npcs.controllers.data.ability.enums.RotationMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergyProjectile;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.util.AnchorPointHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import noppes.npcs.api.ability.type.IAbilityEnergyBeam;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityBeam
extends AbilityEnergyProjectile<EntityAbilityBeam>
implements IAbilityEnergyBeam {
    private float beamWidth = 0.4f;
    private float headSize = 0.6f;
    private boolean freeAim = false;

    public AbilityBeam() {
        super(new EnergyDisplayData(0xFFFFFF, 43775, true, 0.4f, 0.5f, 10.0f), new EnergyCombatData(10.0f, 1.5f, 0.2f, false, 4.0f, 0.5f, HitType.SINGLE, 5), new EnergyHomingData(0.4f, true, 0.1f, 15.0f), new EnergyLifespanData(150.0f, 200));
        this.typeId = "ability.cnpc.beam";
        this.name = "Beam";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 20.0f;
        this.minRange = 5.0f;
        this.cooldownTicks = 0;
        this.windUpTicks = 40;
        this.lockMovement = LockMode.WINDUP_AND_ACTIVE;
        this.rotationMode = RotationMode.LOCKED;
        this.rotationPhase = LockMode.ACTIVE;
        this.telegraphType = TelegraphType.CIRCLE;
        this.showTelegraph = true;
        this.windUpAnimationName = "Ability_Beam_Windup";
        this.activeAnimationName = "Ability_Beam_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/beam.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    protected EntityAbilityBeam createEntity(EntityLivingBase caster, EntityLivingBase target, Vec3 spawnPos, EnergyDisplayData resolved, int index) {
        EntityAbilityBeam beam = new EntityAbilityBeam(caster.field_70170_p, caster, target, spawnPos.field_72450_a, spawnPos.field_72448_b, spawnPos.field_72449_c, this.beamWidth, this.headSize, resolved, this.combatData, this.homingData, this.lightningData, this.lifespanData, this.lockMovement.locksActive());
        beam.setFreeAim(this.freeAim);
        return beam;
    }

    @Override
    protected void fireEntity(EntityAbilityBeam beam, EntityLivingBase target) {
        if (this.isPreview()) {
            beam.startPreviewFiring();
        } else {
            beam.startFiring(target);
        }
    }

    @Override
    protected void setupEntityCharging(EntityAbilityBeam beam, ProjectileData projData, int index) {
        beam.setupCharging(projData.anchor, this.windUpTicks, 1.0f);
    }

    @Override
    protected void setupEntityPreview(EntityAbilityBeam beam, EntityLivingBase caster, EnergyDisplayData resolved, ProjectileData projData, int index) {
        beam.setupPreview(caster, this.beamWidth, this.headSize, resolved, this.lightningData, projData.anchor, this.windUpTicks, 1.0f);
    }

    protected EntityAbilityBeam[] createEntityArray(int size) {
        return new EntityAbilityBeam[size];
    }

    @Override
    public void detach() {
        if (this.entities != null) {
            for (EntityAbilityBeam beam : (EntityAbilityBeam[])this.entities) {
                if (beam == null || beam.field_70128_L) continue;
                beam.setAttachedToOwner(false);
            }
        }
        super.detach();
    }

    @Override
    protected float getProjectileTelegraphRadius() {
        return this.headSize * 2.0f;
    }

    @Override
    protected Vec3 getSpawnPosition(EntityLivingBase caster, int index) {
        return AnchorPointHelper.calculateAnchorPosition(caster, this.projectiles[index].anchor, 1.0f);
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.single", a -> a.setName("Beam")), new AbilityVariant("ability.variant.dual", a -> {
            AbilityBeam beam = (AbilityBeam)a;
            a.setName("Dual Beam");
            beam.setProjectileCount(2);
            beam.setFireDelay(0);
            beam.projectiles[1].colorOverride = true;
            beam.projectiles[1].innerColor = 0xFFFFFF;
            beam.projectiles[1].outerColor = 0xFF0000;
            a.setWindUpTicks(80);
            a.setWindUpAnimationName("Ability_BeamDual_Windup");
            a.setActiveAnimationName("Ability_BeamDual_Active");
        }));
    }

    @Override
    protected void writeTypeSpecificNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("beamWidth", this.beamWidth);
        nbt.func_74776_a("headSize", this.headSize);
        nbt.func_74757_a("freeAim", this.freeAim);
    }

    @Override
    protected void readTypeSpecificNBT(NBTTagCompound nbt) {
        this.beamWidth = nbt.func_74760_g("beamWidth");
        this.headSize = nbt.func_74760_g("headSize");
        this.freeAim = nbt.func_74767_n("freeAim");
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
    public float getBeamWidth() {
        return this.beamWidth;
    }

    @Override
    public void setBeamWidth(float beamWidth) {
        this.beamWidth = beamWidth;
    }

    @Override
    public float getHeadSize() {
        return this.headSize;
    }

    @Override
    public void setHeadSize(float headSize) {
        this.headSize = headSize;
    }

    public boolean isFreeAim() {
        return this.freeAim;
    }

    public void setFreeAim(boolean freeAim) {
        this.freeAim = freeAim;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.row(FieldDef.intField("ability.projectileCount", this::getProjectileCount, this::setProjectileCount).range(1.0f, 8.0f), FieldDef.intField("ability.fireDelay", this::getFireDelay, this::setFireDelay).range(0.0f, 200.0f).visibleWhen(() -> this.projectileCount > 1)));
        defs.add(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("stats.speed", this::getSpeed, this::setSpeed)));
        defs.add(FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback));
        defs.add(FieldDef.section("ability.section.beam"));
        defs.add(FieldDef.boolField("ability.freeAim", this::isFreeAim, this::setFreeAim).hover("ability.hover.freeAim").enabledWhen(() -> this.lockMovement.locksActive() && this.rotationMode == RotationMode.FREE && !this.freeOnCast));
        defs.add(FieldDef.row(FieldDef.floatField("ability.beamWidth", this::getBeamWidth, this::setBeamWidth).range(0.1f, 100.0f), FieldDef.floatField("ability.headSize", this::getHeadSize, this::setHeadSize).range(0.1f, 100.0f)));
        defs.add(FieldDef.row(FieldDef.floatField("ability.maxDistance", this::getMaxDistance, this::setMaxDistance).range(1.0f, 500.0f), FieldDef.intField("ability.lifetime", this::getMaxLifetime, this::setMaxLifetime).range(1.0f, 1200.0f)));
        defs.add(FieldDef.section("ability.section.homing"));
        defs.add(FieldDef.boolField("gui.enabled", this::isHoming, this::setHoming).hover("ability.hover.homing"));
        defs.add(FieldDef.row(FieldDef.floatField("gui.strength", this::getHomingStrength, this::setHomingStrength).visibleWhen(this::isHoming), FieldDef.floatField("gui.range", this::getHomingRange, this::setHomingRange).visibleWhen(this::isHoming)));
        defs.add(FieldDef.section("ability.section.explosive"));
        defs.add(FieldDef.boolField("gui.enabled", this::isExplosive, this::setExplosive).hover("ability.hover.explosive"));
        defs.add(FieldDef.floatField("gui.radius", this::getExplosionRadius, this::setExplosionRadius).range(0.0f, 15.0f).visibleWhen(this::isExplosive));
        defs.add(AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects));
    }
}

