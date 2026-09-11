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
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.ProjectileData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyCombatData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyHomingData;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyLifespanData;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityEnergyProjectile;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import kamkeel.npcs.entity.EntityEnergySlicer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilitySlicer
extends AbilityEnergyProjectile<EntityEnergySlicer> {
    private float sliceWidth = 3.0f;
    private float sliceThickness = 0.15f;

    public AbilitySlicer() {
        super(new EnergyDisplayData(0xFF4444, 0xFF0000, true, 0.2f, 0.6f, 0.0f), new EnergyCombatData(12.0f, 0.5f, 0.0f, false, 3.0f, 0.5f), new EnergyHomingData(0.8f, false, 0.0f, 0.0f), new EnergyLifespanData(150.0f, 100));
        this.typeId = "ability.cnpc.slicer";
        this.name = "Slicer";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 30.0f;
        this.minRange = 3.0f;
        this.cooldownTicks = 40;
        this.windUpTicks = 15;
        this.lockMovement = LockMode.WINDUP;
        this.telegraphType = TelegraphType.LINE;
        this.showTelegraph = true;
        this.windUpAnimationName = "";
        this.activeAnimationName = "";
    }

    @Override
    protected EntityEnergySlicer createEntity(EntityLivingBase caster, EntityLivingBase target, Vec3 spawnPos, EnergyDisplayData resolved, int index) {
        return new EntityEnergySlicer(caster.field_70170_p, caster, target, spawnPos.field_72450_a, spawnPos.field_72448_b, spawnPos.field_72449_c, this.sliceWidth, this.sliceThickness, resolved, this.combatData, this.homingData, this.lightningData, this.lifespanData);
    }

    @Override
    protected void fireEntity(EntityEnergySlicer slicer, EntityLivingBase target) {
        if (this.isPreview()) {
            slicer.startPreviewFiring();
        } else {
            slicer.startMoving(target);
        }
    }

    @Override
    protected void setupEntityCharging(EntityEnergySlicer slicer, ProjectileData projData, int index) {
        slicer.setupSlicerCharging(projData.anchor, this.windUpTicks, this.sliceWidth, this.sliceThickness);
    }

    @Override
    protected void setupEntityPreview(EntityEnergySlicer slicer, EntityLivingBase caster, EnergyDisplayData resolved, ProjectileData projData, int index) {
        slicer.setupPreview(caster, this.sliceWidth, this.sliceThickness, resolved, this.lightningData, projData.anchor, this.windUpTicks);
    }

    protected EntityEnergySlicer[] createEntityArray(int size) {
        return new EntityEnergySlicer[size];
    }

    @Override
    protected float getProjectileTelegraphRadius() {
        return this.sliceWidth * 0.75f;
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.single", a -> a.setName("Slicer")), new AbilityVariant("ability.variant.dual", a -> {
            AbilitySlicer slicer = (AbilitySlicer)a;
            a.setName("Double Slicer");
            slicer.setProjectileCount(2);
            slicer.setFireDelay(5);
            a.setWindUpAnimationName("");
            a.setActiveAnimationName("");
        }));
    }

    @Override
    protected void writeTypeSpecificNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("sliceWidth", this.sliceWidth);
        nbt.func_74776_a("sliceThickness", this.sliceThickness);
    }

    @Override
    protected void readTypeSpecificNBT(NBTTagCompound nbt) {
        this.sliceWidth = nbt.func_74764_b("sliceWidth") ? nbt.func_74760_g("sliceWidth") : 3.0f;
        this.sliceThickness = nbt.func_74764_b("sliceThickness") ? nbt.func_74760_g("sliceThickness") : 0.15f;
    }

    public float getSlicerSpeed() {
        return this.homingData.speed;
    }

    public void setSlicerSpeed(float speed) {
        this.homingData.speed = speed;
    }

    public float getSliceWidth() {
        return this.sliceWidth;
    }

    public void setSliceWidth(float width) {
        this.sliceWidth = Math.max(0.5f, width);
    }

    public float getSliceThickness() {
        return this.sliceThickness;
    }

    public void setSliceThickness(float thickness) {
        this.sliceThickness = Math.max(0.05f, thickness);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addTypeDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.row(FieldDef.intField("ability.projectileCount", this::getProjectileCount, this::setProjectileCount).range(1.0f, 8.0f), FieldDef.intField("ability.fireDelay", this::getFireDelay, this::setFireDelay).range(0.0f, 200.0f).visibleWhen(() -> this.projectileCount > 1)));
        defs.add(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("stats.speed", this::getSlicerSpeed, this::setSlicerSpeed)));
        defs.add(FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback));
        defs.add(FieldDef.section("ability.section.slicer"));
        defs.add(FieldDef.row(FieldDef.floatField("gui.width", this::getSliceWidth, this::setSliceWidth).range(0.5f, 100.0f), FieldDef.floatField("gui.thickness", this::getSliceThickness, this::setSliceThickness).range(0.05f, 100.0f)));
        defs.add(FieldDef.row(FieldDef.floatField("ability.maxDistance", this::getMaxDistance, this::setMaxDistance).range(1.0f, 500.0f), FieldDef.intField("ability.lifetime", this::getMaxLifetime, this::setMaxLifetime).range(1.0f, 1200.0f)));
        defs.add(FieldDef.section("ability.section.explosive"));
        defs.add(FieldDef.boolField("gui.enabled", this::isExplosive, this::setExplosive).hover("ability.hover.explosive"));
        defs.add(FieldDef.floatField("gui.radius", this::getExplosionRadius, this::setExplosionRadius).range(0.0f, 15.0f).visibleWhen(this::isExplosive));
        defs.add(AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects));
    }
}

