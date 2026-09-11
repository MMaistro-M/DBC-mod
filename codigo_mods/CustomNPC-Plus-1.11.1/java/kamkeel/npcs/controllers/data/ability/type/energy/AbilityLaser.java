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
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ProjectileData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import kamkeel.npcs.controllers.data.ability.enums.HitType;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.RotationMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergyProjectile;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityAbilityLaser;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import noppes.npcs.api.ability.type.IAbilityLaser;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityLaser
extends AbilityEnergyProjectile<EntityAbilityLaser>
implements IAbilityLaser {
    private float laserWidth = 0.3f;
    private float expansionSpeed = 3.0f;
    private float maxLength = 32.0f;

    public AbilityLaser() {
        super(new EnergyDisplayData(0xFFFFFF, 0xFF0000, true, 0.4f, 0.5f, 4.0f), new EnergyCombatData(4.0f, 0.5f, 0.05f, false, 2.0f, 0.5f, HitType.MULTI, 5), new EnergyHomingData(), new EnergyLifespanData(150.0f, 100));
        this.typeId = "ability.cnpc.laser_shot";
        this.name = "Laser";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 30.0f;
        this.minRange = 3.0f;
        this.cooldownTicks = 0;
        this.windUpTicks = 15;
        this.lockMovement = LockMode.WINDUP_AND_ACTIVE;
        this.rotationMode = RotationMode.TRACK;
        this.rotationPhase = LockMode.WINDUP_AND_ACTIVE;
        this.trackSpeed = 4.0f;
        this.telegraphType = TelegraphType.LINE;
        this.showTelegraph = true;
        this.windUpAnimationName = "Ability_Laser_Windup";
        this.activeAnimationName = "Ability_Laser_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/laser_shot.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/laser_shot_overlay.png", () -> this.isOuterColorEnabled() ? this.getOuterColor() : this.getInnerColor())};
    }

    @Override
    protected AnchorPoint getDefaultAnchor(int index) {
        return AnchorPoint.FRONT;
    }

    @Override
    public boolean isTargetingModeLocked() {
        return false;
    }

    @Override
    public int getMaxPreviewDuration() {
        int lifetime = this.lifespanData.maxLifetime;
        return lifetime > 0 ? Math.min(lifetime, 60) : 60;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.showTelegraph || this.telegraphType == TelegraphType.NONE || this.isPlayerCaster(caster)) {
            return null;
        }
        Telegraph telegraph = Telegraph.line(this.getTelegraphLength(), this.getTelegraphWidth());
        telegraph.setDurationTicks(this.windUpTicks);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        double groundY = AbilityLaser.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
        TelegraphInstance instance = new TelegraphInstance(telegraph, caster.field_70165_t, groundY, caster.field_70161_v, caster.field_70177_z);
        instance.setCasterEntityId(caster.func_145782_y());
        instance.setEntityIdToFollow(caster.func_145782_y());
        if (target != null) {
            instance.setTargetEntityId(target.func_145782_y());
        }
        return instance;
    }

    @Override
    public float getTelegraphLength() {
        return this.maxLength;
    }

    @Override
    public float getTelegraphWidth() {
        return this.laserWidth * 2.0f;
    }

    @Override
    protected EntityAbilityLaser createEntity(EntityLivingBase caster, EntityLivingBase target, Vec3 spawnPos, EnergyDisplayData resolved, int index) {
        EntityAbilityLaser laser = new EntityAbilityLaser(caster.field_70170_p, caster, target, spawnPos.field_72450_a, spawnPos.field_72448_b, spawnPos.field_72449_c, this.laserWidth, resolved, this.combatData, this.lightningData, this.lifespanData, this.expansionSpeed, this.maxLength);
        return laser;
    }

    @Override
    protected void fireEntity(EntityAbilityLaser laser, EntityLivingBase target) {
        laser.startMoving(target);
    }

    @Override
    protected void setupEntityCharging(EntityAbilityLaser laser, ProjectileData projData, int index) {
        laser.setupCharging(projData.anchor, this.windUpTicks);
    }

    @Override
    protected void setupEntityPreview(EntityAbilityLaser laser, EntityLivingBase caster, EnergyDisplayData resolved, ProjectileData projData, int index) {
        laser.setupPreview(caster, this.laserWidth, resolved, this.lightningData, projData.anchor, this.windUpTicks, this.expansionSpeed, this.maxLength);
    }

    protected EntityAbilityLaser[] createEntityArray(int size) {
        return new EntityAbilityLaser[size];
    }

    @Override
    protected float getProjectileTelegraphRadius() {
        return this.laserWidth * 2.0f;
    }

    @Override
    protected void writeTypeSpecificNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("laserWidth", this.laserWidth);
        nbt.func_74776_a("expansionSpeed", this.expansionSpeed);
        nbt.func_74776_a("maxLength", this.maxLength);
    }

    @Override
    protected void readTypeSpecificNBT(NBTTagCompound nbt) {
        this.laserWidth = nbt.func_74760_g("laserWidth");
        this.expansionSpeed = nbt.func_74760_g("expansionSpeed");
        this.maxLength = nbt.func_74764_b("maxLength") ? nbt.func_74760_g("maxLength") : 32.0f;
    }

    @Override
    public float getLaserWidth() {
        return this.laserWidth;
    }

    @Override
    public void setLaserWidth(float laserWidth) {
        this.laserWidth = laserWidth;
    }

    @Override
    public float getExpansionSpeed() {
        return this.expansionSpeed;
    }

    @Override
    public void setExpansionSpeed(float expansionSpeed) {
        this.expansionSpeed = expansionSpeed;
    }

    @Override
    public float getMaxLength() {
        return this.maxLength;
    }

    @Override
    public void setMaxLength(float maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage));
        defs.add(FieldDef.row(FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback), FieldDef.floatField("ability.knockbackUp", this::getKnockbackUp, this::setKnockbackUp)));
        defs.add(FieldDef.section("ability.section.beam"));
        defs.add(FieldDef.row(FieldDef.floatField("ability.laserWidth", this::getLaserWidth, this::setLaserWidth).range(0.1f, 100.0f), FieldDef.floatField("ability.expansionSpeed", this::getExpansionSpeed, this::setExpansionSpeed).range(0.1f, 50.0f)));
        defs.add(FieldDef.row(FieldDef.floatField("ability.maxLength", this::getMaxLength, this::setMaxLength).range(1.0f, 500.0f), FieldDef.intField("ability.lifetime", this::getMaxLifetime, this::setMaxLifetime).range(1.0f, 1200.0f)));
        defs.add(FieldDef.section("ability.section.explosive"));
        defs.add(FieldDef.boolField("gui.enabled", this::isExplosive, this::setExplosive).hover("ability.hover.explosive"));
        defs.add(FieldDef.floatField("gui.radius", this::getExplosionRadius, this::setExplosionRadius).range(0.0f, 15.0f).visibleWhen(this::isExplosive));
        defs.add(AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects));
    }
}

