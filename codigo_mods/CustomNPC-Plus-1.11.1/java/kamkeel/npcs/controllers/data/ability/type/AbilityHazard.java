/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.AbilityZone;
import kamkeel.npcs.entity.EntityAbilityZone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.type.IAbilityHazard;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityHazard
extends AbilityZone
implements IAbilityHazard {
    private float radius = 2.0f;
    private float damagePerSecond = 1.0f;
    private int damageInterval = 20;
    private boolean affectsCaster = false;

    public AbilityHazard() {
        super(300, new EnergyDisplayData(52224, 26112, true, 1.0f, 0.5f, 1.5f));
        this.typeId = "ability.cnpc.hazard";
        this.name = "Hazard";
        this.windUpTicks = 30;
        this.windUpAnimationName = "Ability_Zone_Windup";
        this.activeAnimationName = "Ability_Zone_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/hazard.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/hazard_overlay.png", this::getActiveColor)};
    }

    @Override
    public boolean isConcurrentCapable() {
        return true;
    }

    @Override
    public float getZoneRadius() {
        return this.radius;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        ArrayList<double[]> positions;
        this.activeEntities.clear();
        if (!this.preCalculatedPositions.isEmpty() && this.preCalculatedPositions.size() == this.zoneCount) {
            positions = new ArrayList(this.preCalculatedPositions);
            this.preCalculatedPositions.clear();
        } else {
            positions = new ArrayList<double[]>();
            ArrayList<double[]> placedPositions = new ArrayList<double[]>();
            float minSeparation = this.radius * 2.0f;
            for (int i = 0; i < this.zoneCount; ++i) {
                double[] pos = this.findSpawnPosition(caster, placedPositions, minSeparation);
                placedPositions.add(pos);
                positions.add(pos);
            }
        }
        for (int i = 0; i < this.zoneCount; ++i) {
            double[] pos = (double[])positions.get(i);
            EntityAbilityZone entity = EntityAbilityZone.createHazard(caster.field_70170_p, caster, pos[0], caster.field_70163_u, pos[1], this.zoneShape, this.radius, this.damagePerSecond, this.damageInterval, this.isIgnoreIFrames(), this.affectsCaster, this.durationTicks, this.colorData.innerColor, this.colorData.outerColor, this.colorData.outerColorEnabled, this.zoneHeight, this.particleDensity, this.particleScale, this.animSpeed, this.lightningDensity, this.getEffects());
            entity.setSourceAbility(this);
            this.applyVisualToEntity(entity);
            if (this.isPreview()) {
                entity.setupPreview(caster);
            }
            this.spawnAbilityEntity(entity);
            this.activeEntities.add(entity);
        }
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        this.writeZoneNBT(nbt);
        nbt.func_74776_a("radius", this.radius);
        nbt.func_74776_a("damagePerSecond", this.damagePerSecond);
        nbt.func_74768_a("damageInterval", this.damageInterval);
        nbt.func_74757_a("affectsCaster", this.affectsCaster);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.readZoneNBT(nbt, 300);
        this.radius = nbt.func_74760_g("radius");
        this.damagePerSecond = nbt.func_74760_g("damagePerSecond");
        this.damageInterval = nbt.func_74762_e("damageInterval");
        this.affectsCaster = nbt.func_74767_n("affectsCaster");
    }

    @Override
    public float getRadius() {
        return this.radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public float getDamagePerSecond() {
        return this.damagePerSecond;
    }

    @Override
    public void setDamagePerSecond(float damagePerSecond) {
        this.damagePerSecond = damagePerSecond;
    }

    @Override
    public float getDisplayDamage() {
        return this.damagePerSecond;
    }

    @Override
    public boolean isDisplayDamageDPS() {
        return true;
    }

    @Override
    public int getDamageInterval() {
        return this.damageInterval;
    }

    @Override
    public void setDamageInterval(int damageInterval) {
        this.damageInterval = damageInterval;
    }

    @Override
    public boolean isAffectsCaster() {
        return this.affectsCaster;
    }

    @Override
    public void setAffectsCaster(boolean affectsCaster) {
        this.affectsCaster = affectsCaster;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        this.addPresetFieldDef(defs);
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.intField("ability.duration", this::getDurationTicks, this::setDurationTicks).range(1.0f, 2000.0f), FieldDef.enumField("ability.zoneShape", EntityAbilityZone.ZoneShape.class, this::getZoneShapeEnum, this::setZoneShapeEnum)), FieldDef.section("ability.section.zone"), FieldDef.row(FieldDef.floatField("gui.radius", this::getSpawnRadius, this::setSpawnRadius), FieldDef.intField("gui.count", this::getZoneCount, this::setZoneCount).range(1.0f, 20.0f)), FieldDef.floatField("gui.height", this::getZoneHeight, this::setZoneHeight), FieldDef.section("ability.section.area"), FieldDef.floatField("gui.radius", this::getRadius, this::setRadius), FieldDef.section("ability.section.damage"), FieldDef.row(FieldDef.floatField("gui.dps", this::getDamagePerSecond, this::setDamagePerSecond), FieldDef.intField("gui.interval", this::getDamageInterval, this::setDamageInterval)), FieldDef.boolField("ability.affectsCaster", this::isAffectsCaster, this::setAffectsCaster).hover("ability.hover.affectsCaster"), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
        this.addVisualFieldDefs(defs);
    }
}

