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
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.AbilityZone;
import kamkeel.npcs.entity.EntityAbilityZone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.type.IAbilityTrap;
import noppes.npcs.client.gui.SubGuiTrapPresetSelector;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.constants.EnumPotionType;

public class AbilityTrap
extends AbilityZone
implements IAbilityTrap {
    private float triggerRadius = 2.0f;
    private int armTime = 20;
    private int maxTriggers = 1;
    private int triggerCooldown = 20;
    private float damage = 6.0f;
    private float damageRadius = 0.0f;
    private float knockback = 0.5f;
    private boolean visible = true;

    public AbilityTrap() {
        super(300, new EnergyDisplayData(0xFF6600, 0xFF0000, true, 1.0f, 0.5f, 1.5f));
        this.typeId = "ability.cnpc.trap";
        this.name = "Trap";
        this.windUpTicks = 30;
        this.windUpAnimationName = "Ability_Zone_Windup";
        this.activeAnimationName = "Ability_Zone_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/trap.png", this::getActiveColor)};
    }

    @Override
    public boolean isConcurrentCapable() {
        return true;
    }

    @Override
    public float getZoneRadius() {
        return this.triggerRadius;
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
            float minSeparation = this.triggerRadius * 2.0f;
            for (int i = 0; i < this.zoneCount; ++i) {
                double[] pos = this.findSpawnPosition(caster, placedPositions, minSeparation);
                placedPositions.add(pos);
                positions.add(pos);
            }
        }
        for (int i = 0; i < this.zoneCount; ++i) {
            double[] pos = (double[])positions.get(i);
            EntityAbilityZone entity = EntityAbilityZone.createTrap(caster.field_70170_p, caster, pos[0], caster.field_70163_u, pos[1], this.zoneShape, this.triggerRadius, this.armTime, this.maxTriggers, this.triggerCooldown, this.damage, this.damageRadius, this.knockback, this.durationTicks, this.isIgnoreIFrames(), this.colorData.innerColor, this.colorData.outerColor, this.colorData.outerColorEnabled, this.zoneHeight, this.particleDensity, this.particleScale, this.animSpeed, this.lightningDensity, this.visible, this.getEffects());
            entity.setSourceAbility(this);
            this.applyVisualToEntity(entity);
            if (this.isPreview()) {
                entity.setupPreview(caster);
            }
            this.spawnAbilityEntity(entity);
            this.activeEntities.add(entity);
        }
    }

    private void applyTrapPresetDefaults(String preset) {
        this.effects.clear();
        this.particleDir = "";
        this.groundFill = true;
        this.rings = false;
        this.ringCount = 1;
        this.border = false;
        this.borderSpeed = 1.0f;
        this.accents = false;
        this.accentStyle = 0;
        this.lightning = false;
        this.particleGlow = false;
        switch (preset) {
            case "VENOM": {
                this.groundAlpha = 0.04f;
                this.particles = true;
                this.particleDensity = 0.3f;
                this.particleScale = 0.5f;
                this.particleMotion = 1;
                this.particleDir = "mc:mobSpell";
                this.colorData.innerColor = 0x44DD44;
                this.colorData.outerColor = 0x116611;
                this.windUpColor = 1615125828;
                this.activeColor = -1069220028;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Poison, 100, 0));
                break;
            }
            case "EXPLOSIVE": {
                this.groundAlpha = 0.04f;
                this.particles = true;
                this.particleDensity = 0.3f;
                this.particleScale = 0.6f;
                this.particleMotion = 0;
                this.particleDir = "mc:smoke";
                this.colorData.innerColor = 0xFF6611;
                this.colorData.outerColor = 0xCC2200;
                this.windUpColor = 1627350545;
                this.activeColor = -1057012736;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Fire, 60, 0));
                break;
            }
            case "CURSED": {
                this.groundAlpha = 0.04f;
                this.particles = true;
                this.particleDensity = 0.3f;
                this.particleScale = 0.5f;
                this.particleMotion = 1;
                this.particleDir = "mc:portal";
                this.colorData.innerColor = 0xAA44FF;
                this.colorData.outerColor = 0x6622BB;
                this.windUpColor = 1621771519;
                this.activeColor = -1060346113;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Weakness, 100, 0));
                break;
            }
            case "SHOCK": {
                this.groundAlpha = 0.04f;
                this.particles = true;
                this.particleDensity = 0.3f;
                this.particleScale = 0.4f;
                this.particleMotion = 2;
                this.particleDir = "mc:enchantmenttable";
                this.colorData.innerColor = 0x4488FF;
                this.colorData.outerColor = 0x2244BB;
                this.windUpColor = 1615104255;
                this.activeColor = -1067013377;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.MiningFatigue, 80, 1));
                break;
            }
            case "SNARE": {
                this.groundAlpha = 0.04f;
                this.particles = true;
                this.particleDensity = 0.3f;
                this.particleScale = 0.5f;
                this.particleMotion = 1;
                this.particleDir = "mc:snowshovel";
                this.colorData.innerColor = 0x88CCFF;
                this.colorData.outerColor = 0x4488CC;
                this.windUpColor = 1619578111;
                this.activeColor = -1062543873;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Slowness, 100, 1));
                break;
            }
            default: {
                this.groundAlpha = 0.03f;
                this.particles = false;
                this.particleDensity = 0.0f;
                this.particleScale = 1.0f;
                this.particleMotion = 0;
                this.colorData.innerColor = 0x888888;
                this.colorData.outerColor = 0x444444;
                this.windUpColor = 0x60888888;
                this.activeColor = -1062557014;
            }
        }
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        this.writeZoneNBT(nbt);
        nbt.func_74776_a("triggerRadius", this.triggerRadius);
        nbt.func_74768_a("armTime", this.armTime);
        nbt.func_74768_a("maxTriggers", this.maxTriggers);
        nbt.func_74768_a("triggerCooldown", this.triggerCooldown);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("damageRadius", this.damageRadius);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74757_a("visible", this.visible);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.readZoneNBT(nbt, 300);
        this.triggerRadius = nbt.func_74760_g("triggerRadius");
        this.armTime = nbt.func_74762_e("armTime");
        this.maxTriggers = nbt.func_74762_e("maxTriggers");
        this.triggerCooldown = nbt.func_74762_e("triggerCooldown");
        this.damage = nbt.func_74760_g("damage");
        this.damageRadius = nbt.func_74760_g("damageRadius");
        this.knockback = nbt.func_74760_g("knockback");
        this.visible = nbt.func_74767_n("visible");
    }

    @Override
    public float getTriggerRadius() {
        return this.triggerRadius;
    }

    @Override
    public void setTriggerRadius(float triggerRadius) {
        this.triggerRadius = triggerRadius;
    }

    @Override
    public int getArmTime() {
        return this.armTime;
    }

    @Override
    public void setArmTime(int armTime) {
        this.armTime = armTime;
    }

    @Override
    public int getMaxTriggers() {
        return this.maxTriggers;
    }

    @Override
    public void setMaxTriggers(int maxTriggers) {
        this.maxTriggers = maxTriggers;
    }

    @Override
    public int getTriggerCooldown() {
        return this.triggerCooldown;
    }

    @Override
    public void setTriggerCooldown(int triggerCooldown) {
        this.triggerCooldown = triggerCooldown;
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
    public float getDamageRadius() {
        return this.damageRadius;
    }

    @Override
    public void setDamageRadius(float damageRadius) {
        this.damageRadius = damageRadius;
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
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    protected void addPresetFieldDef(List<FieldDef> defs) {
        defs.add(FieldDef.subGuiField("gui.applyPreset", SubGuiTrapPresetSelector::new, gui -> {
            SubGuiTrapPresetSelector selector = gui;
            if (selector.selectedPreset != null) {
                this.applyTrapPresetDefaults(selector.selectedPreset);
            }
        }));
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        this.addPresetFieldDef(defs);
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.intField("ability.duration", this::getDurationTicks, this::setDurationTicks).range(1.0f, 2000.0f), FieldDef.enumField("ability.zoneShape", EntityAbilityZone.ZoneShape.class, this::getZoneShapeEnum, this::setZoneShapeEnum)), FieldDef.section("ability.section.zone"), FieldDef.row(FieldDef.floatField("gui.radius", this::getSpawnRadius, this::setSpawnRadius), FieldDef.intField("gui.count", this::getZoneCount, this::setZoneCount).range(1.0f, 20.0f)), FieldDef.row(FieldDef.floatField("gui.height", this::getZoneHeight, this::setZoneHeight), FieldDef.boolField("ability.visible", this::isVisible, this::setVisible)), FieldDef.section("ability.section.trigger"), FieldDef.row(FieldDef.floatField("gui.radius", this::getTriggerRadius, this::setTriggerRadius), FieldDef.intField("ability.armTime", this::getArmTime, this::setArmTime)), FieldDef.row(FieldDef.intField("gui.max", this::getMaxTriggers, this::setMaxTriggers), FieldDef.intField("gui.cooldown", this::getTriggerCooldown, this::setTriggerCooldown)), FieldDef.section("ability.section.damage"), FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("gui.radius", this::getDamageRadius, this::setDamageRadius)), FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
        this.addVisualFieldDefs(defs);
    }

    @SideOnly(value=Side.CLIENT)
    public static enum TrapPreset {
        HIDDEN,
        VENOM,
        EXPLOSIVE,
        CURSED,
        SHOCK,
        SNARE;


        public String toString() {
            return "ability.preset." + this.name().toLowerCase();
        }
    }
}

