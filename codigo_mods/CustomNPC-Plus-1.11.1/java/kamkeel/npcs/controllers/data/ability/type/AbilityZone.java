/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityPotionEffect;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.entity.EntityAbilityZone;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import noppes.npcs.client.gui.SubGuiZonePresetSelector;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.constants.EnumPotionType;

public abstract class AbilityZone
extends Ability {
    protected int durationTicks;
    protected EntityAbilityZone.ZoneShape zoneShape = EntityAbilityZone.ZoneShape.CIRCLE;
    protected float spawnRadius = 10.0f;
    protected int zoneCount = 3;
    protected float zoneHeight = 2.0f;
    protected float particleDensity = 1.0f;
    protected float particleScale = 1.0f;
    protected float animSpeed = 1.0f;
    protected float lightningDensity = 1.0f;
    protected EnergyDisplayData colorData;
    protected boolean groundFill = true;
    protected float groundAlpha = 0.25f;
    protected boolean rings = true;
    protected int ringCount = 3;
    protected boolean border = true;
    protected float borderSpeed = 1.0f;
    protected boolean accents = true;
    protected int accentStyle = 0;
    protected boolean lightning = false;
    protected boolean particles = true;
    protected int particleMotion = 0;
    protected String particleDir = "";
    protected int particleSize = 32;
    protected boolean particleGlow = true;
    protected static final Random RANDOM = new Random();
    protected transient List<EntityAbilityZone> activeEntities = new ArrayList<EntityAbilityZone>();
    protected transient List<double[]> preCalculatedPositions = new ArrayList<double[]>();

    protected AbilityZone(int defaultDuration, EnergyDisplayData defaultColors) {
        this.durationTicks = defaultDuration;
        this.colorData = defaultColors;
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 20.0f;
        this.lockMovement = LockMode.WINDUP;
        this.cooldownTicks = 0;
        this.allowedBy = UserType.BOTH;
    }

    @Override
    public boolean allowBurst() {
        return false;
    }

    @Override
    public boolean allowFreeOnCast() {
        return true;
    }

    @Override
    public void detach() {
        this.activeEntities.clear();
        this.preCalculatedPositions.clear();
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AGGRO_TARGET};
    }

    public abstract float getZoneRadius();

    @Override
    public List<TelegraphInstance> createTelegraphs(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.showTelegraph || this.windUpTicks <= 0) {
            return new ArrayList<TelegraphInstance>();
        }
        this.preCalculatedPositions.clear();
        ArrayList<double[]> placedPositions = new ArrayList<double[]>();
        float minSeparation = this.getZoneRadius() * 2.0f;
        for (int i = 0; i < this.zoneCount; ++i) {
            double[] pos = this.findSpawnPosition(caster, placedPositions, minSeparation);
            placedPositions.add(pos);
            this.preCalculatedPositions.add(pos);
        }
        ArrayList<TelegraphInstance> telegraphs = new ArrayList<TelegraphInstance>();
        float zoneRadius = this.getZoneRadius();
        for (double[] pos : this.preCalculatedPositions) {
            Telegraph telegraph = this.zoneShape == EntityAbilityZone.ZoneShape.SQUARE ? Telegraph.square(zoneRadius) : Telegraph.circle(zoneRadius);
            telegraph.setDurationTicks(this.windUpTicks);
            telegraph.setColor(this.windUpColor);
            telegraph.setWarningColor(this.activeColor);
            telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
            telegraph.setHeightOffset(this.telegraphHeightOffset);
            double groundY = AbilityZone.findGroundLevel(caster.field_70170_p, pos[0], caster.field_70163_u, pos[1]);
            TelegraphInstance instance = new TelegraphInstance(telegraph, pos[0], groundY, pos[1], 0.0f);
            instance.setCasterEntityId(caster.func_145782_y());
            instance.setEntityIdToFollow(-1);
            telegraphs.add(instance);
        }
        return telegraphs;
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.isFreeOnCast()) {
            this.signalCompletion();
            return;
        }
        Iterator<EntityAbilityZone> it = this.activeEntities.iterator();
        while (it.hasNext()) {
            EntityAbilityZone entity = it.next();
            if (entity != null && !entity.field_70128_L) continue;
            it.remove();
        }
        if (this.activeEntities.isEmpty()) {
            this.signalCompletion();
        }
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
        this.cleanup();
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.cleanup();
    }

    @Override
    public void cleanup() {
        for (EntityAbilityZone entity : this.activeEntities) {
            if (entity == null || entity.field_70128_L) continue;
            this.killAbilityEntity(entity);
        }
        this.activeEntities.clear();
        this.preCalculatedPositions.clear();
    }

    @Override
    public int getMaxPreviewDuration() {
        return this.durationTicks + 10;
    }

    protected double[] findSpawnPosition(EntityLivingBase caster, List<double[]> placedPositions, float minSeparation) {
        double spawnX = caster.field_70165_t;
        double spawnZ = caster.field_70161_v;
        for (int attempt = 0; attempt < 15; ++attempt) {
            double angle = RANDOM.nextDouble() * Math.PI * 2.0;
            double dist = Math.sqrt(RANDOM.nextDouble()) * (double)this.spawnRadius;
            double candidateX = caster.field_70165_t + Math.cos(angle) * dist;
            double candidateZ = caster.field_70161_v + Math.sin(angle) * dist;
            boolean overlaps = false;
            for (double[] placed : placedPositions) {
                double dz;
                double dx = candidateX - placed[0];
                if (!(Math.sqrt(dx * dx + (dz = candidateZ - placed[1]) * dz) < (double)minSeparation)) continue;
                overlaps = true;
                break;
            }
            spawnX = candidateX;
            spawnZ = candidateZ;
            if (!overlaps) break;
        }
        return new double[]{spawnX, spawnZ};
    }

    protected void applyVisualToEntity(EntityAbilityZone entity) {
        entity.applyVisual(this.groundFill, this.groundAlpha, this.rings, this.ringCount, this.border, this.borderSpeed, this.accents, this.accentStyle, this.lightning, this.particles, this.particleMotion, this.particleDir, this.particleSize, this.particleGlow);
    }

    private void applyPresetDefaults(String styleName) {
        this.effects.clear();
        this.particleDir = "";
        switch (styleName) {
            case "TOXIC": {
                this.groundFill = true;
                this.groundAlpha = 0.3f;
                this.rings = true;
                this.ringCount = 3;
                this.border = false;
                this.borderSpeed = 1.0f;
                this.accents = true;
                this.accentStyle = 1;
                this.lightning = false;
                this.particles = true;
                this.particleDensity = 1.5f;
                this.particleScale = 0.8f;
                this.particleMotion = 1;
                this.particleGlow = true;
                this.particleDir = "mc:mobSpell";
                this.colorData.innerColor = 0x44DD44;
                this.colorData.outerColor = 0x116611;
                this.windUpColor = 1615125828;
                this.activeColor = -1069220028;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Poison, 100, 0));
                break;
            }
            case "INFERNO": {
                this.groundFill = true;
                this.groundAlpha = 0.35f;
                this.rings = true;
                this.ringCount = 3;
                this.border = true;
                this.borderSpeed = 2.0f;
                this.accents = true;
                this.accentStyle = 2;
                this.lightning = false;
                this.particles = true;
                this.particleDensity = 2.0f;
                this.particleScale = 1.2f;
                this.particleMotion = 0;
                this.particleGlow = true;
                this.particleDir = "mc:flame";
                this.colorData.innerColor = 0xFF6611;
                this.colorData.outerColor = 0xCC2200;
                this.windUpColor = 1627350545;
                this.activeColor = -1057012736;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Fire, 60, 0));
                break;
            }
            case "ARCANE": {
                this.groundFill = true;
                this.groundAlpha = 0.2f;
                this.rings = true;
                this.ringCount = 1;
                this.border = true;
                this.borderSpeed = 0.8f;
                this.accents = true;
                this.accentStyle = 0;
                this.lightning = false;
                this.particles = true;
                this.particleDensity = 1.0f;
                this.particleScale = 1.0f;
                this.particleMotion = 1;
                this.particleGlow = true;
                this.particleDir = "mc:portal";
                this.colorData.innerColor = 0xAA44FF;
                this.colorData.outerColor = 0x6622BB;
                this.windUpColor = 1621771519;
                this.activeColor = -1060346113;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Weakness, 100, 0));
                break;
            }
            case "ELECTRIC": {
                this.groundFill = true;
                this.groundAlpha = 0.15f;
                this.rings = false;
                this.ringCount = 1;
                this.border = false;
                this.borderSpeed = 1.0f;
                this.accents = false;
                this.accentStyle = 0;
                this.lightning = true;
                this.particles = true;
                this.particleDensity = 0.8f;
                this.particleScale = 0.6f;
                this.particleMotion = 2;
                this.particleGlow = true;
                this.particleDir = "mc:enchantmenttable";
                this.colorData.innerColor = 0x4488FF;
                this.colorData.outerColor = 0x2244BB;
                this.windUpColor = 1615104255;
                this.activeColor = -1067013377;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.MiningFatigue, 80, 1));
                break;
            }
            case "FROST": {
                this.groundFill = true;
                this.groundAlpha = 0.25f;
                this.rings = true;
                this.ringCount = 3;
                this.border = true;
                this.borderSpeed = 0.3f;
                this.accents = true;
                this.accentStyle = 0;
                this.lightning = false;
                this.particles = true;
                this.particleDensity = 1.5f;
                this.particleScale = 1.0f;
                this.particleMotion = 1;
                this.particleGlow = true;
                this.particleDir = "mc:snowshovel";
                this.colorData.innerColor = 0x88CCFF;
                this.colorData.outerColor = 0x4488CC;
                this.windUpColor = 1619578111;
                this.activeColor = -1062543873;
                this.effects.add(new AbilityPotionEffect(EnumPotionType.Slowness, 100, 1));
                break;
            }
            case "DEFAULT": {
                this.groundFill = true;
                this.groundAlpha = 0.25f;
                this.rings = true;
                this.ringCount = 3;
                this.border = true;
                this.borderSpeed = 1.0f;
                this.accents = true;
                this.accentStyle = 0;
                this.lightning = false;
                this.particles = false;
                this.particleDensity = 1.0f;
                this.particleScale = 1.0f;
                this.particleMotion = 0;
                this.particleGlow = true;
                this.colorData.innerColor = 0xCCCCCC;
                this.colorData.outerColor = 0x666666;
                this.windUpColor = 0x60CCCCCC;
                this.activeColor = -1056964609;
                break;
            }
        }
    }

    protected void writeZoneNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("durationTicks", this.durationTicks);
        nbt.func_74778_a("zoneShape", this.zoneShape.name());
        nbt.func_74776_a("spawnRadius", this.spawnRadius);
        nbt.func_74768_a("zoneCount", this.zoneCount);
        nbt.func_74776_a("zoneHeight", this.zoneHeight);
        nbt.func_74776_a("particleDensity", this.particleDensity);
        nbt.func_74776_a("particleScale", this.particleScale);
        nbt.func_74776_a("animSpeed", this.animSpeed);
        nbt.func_74776_a("lightningDensity", this.lightningDensity);
        this.colorData.writeNBT(nbt);
        nbt.func_74757_a("groundFill", this.groundFill);
        nbt.func_74776_a("groundAlpha", this.groundAlpha);
        nbt.func_74757_a("rings", this.rings);
        nbt.func_74768_a("ringCount", this.ringCount);
        nbt.func_74757_a("border", this.border);
        nbt.func_74776_a("borderSpeed", this.borderSpeed);
        nbt.func_74757_a("accents", this.accents);
        nbt.func_74768_a("accentStyle", this.accentStyle);
        nbt.func_74757_a("lightning", this.lightning);
        nbt.func_74757_a("particles", this.particles);
        nbt.func_74768_a("particleMotion", this.particleMotion);
        nbt.func_74778_a("particleDir", this.particleDir);
        nbt.func_74768_a("particleSize", this.particleSize);
        nbt.func_74757_a("particleGlow", this.particleGlow);
    }

    protected void readZoneNBT(NBTTagCompound nbt, int defaultDuration) {
        this.durationTicks = nbt.func_74762_e("durationTicks");
        try {
            this.zoneShape = EntityAbilityZone.ZoneShape.valueOf(nbt.func_74779_i("zoneShape"));
        }
        catch (Exception e) {
            this.zoneShape = EntityAbilityZone.ZoneShape.CIRCLE;
        }
        this.spawnRadius = nbt.func_74760_g("spawnRadius");
        this.zoneCount = nbt.func_74762_e("zoneCount");
        this.zoneHeight = nbt.func_74760_g("zoneHeight");
        this.particleDensity = nbt.func_74760_g("particleDensity");
        this.particleScale = nbt.func_74760_g("particleScale");
        this.animSpeed = nbt.func_74760_g("animSpeed");
        this.lightningDensity = nbt.func_74760_g("lightningDensity");
        this.colorData.readNBT(nbt);
        this.groundFill = nbt.func_74767_n("groundFill");
        this.groundAlpha = nbt.func_74760_g("groundAlpha");
        this.rings = nbt.func_74767_n("rings");
        this.ringCount = nbt.func_74762_e("ringCount");
        this.border = nbt.func_74767_n("border");
        this.borderSpeed = nbt.func_74760_g("borderSpeed");
        this.accents = nbt.func_74767_n("accents");
        this.accentStyle = nbt.func_74762_e("accentStyle");
        this.lightning = nbt.func_74767_n("lightning");
        this.particles = nbt.func_74767_n("particles");
        this.particleMotion = nbt.func_74762_e("particleMotion");
        this.particleDir = nbt.func_74779_i("particleDir");
        this.particleSize = nbt.func_74762_e("particleSize");
        this.particleGlow = nbt.func_74767_n("particleGlow");
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(1, durationTicks);
    }

    public EntityAbilityZone.ZoneShape getZoneShapeEnum() {
        return this.zoneShape;
    }

    public void setZoneShapeEnum(EntityAbilityZone.ZoneShape shape) {
        this.zoneShape = shape;
    }

    public int getZoneShapeOrdinal() {
        return this.zoneShape.ordinal();
    }

    public void setZoneShapeOrdinal(int shape) {
        EntityAbilityZone.ZoneShape[] values = EntityAbilityZone.ZoneShape.values();
        this.zoneShape = shape >= 0 && shape < values.length ? values[shape] : EntityAbilityZone.ZoneShape.CIRCLE;
    }

    public float getSpawnRadius() {
        return this.spawnRadius;
    }

    public void setSpawnRadius(float spawnRadius) {
        this.spawnRadius = Math.max(0.0f, spawnRadius);
    }

    public int getZoneCount() {
        return this.zoneCount;
    }

    public void setZoneCount(int zoneCount) {
        this.zoneCount = Math.max(1, zoneCount);
    }

    public float getZoneHeight() {
        return this.zoneHeight;
    }

    public void setZoneHeight(float zoneHeight) {
        this.zoneHeight = Math.max(0.5f, zoneHeight);
    }

    public float getParticleDensity() {
        return this.particleDensity;
    }

    public void setParticleDensity(float v) {
        this.particleDensity = Math.max(0.0f, Math.min(5.0f, v));
    }

    public float getParticleScale() {
        return this.particleScale;
    }

    public void setParticleScale(float v) {
        this.particleScale = Math.max(0.1f, Math.min(5.0f, v));
    }

    public float getAnimSpeed() {
        return this.animSpeed;
    }

    public void setAnimSpeed(float v) {
        this.animSpeed = Math.max(0.1f, Math.min(5.0f, v));
    }

    public float getLightningDensity() {
        return this.lightningDensity;
    }

    public void setLightningDensity(float v) {
        this.lightningDensity = Math.max(0.0f, Math.min(3.0f, v));
    }

    public int getInnerColor() {
        return this.colorData.innerColor;
    }

    public void setInnerColor(int color) {
        this.colorData.innerColor = color;
    }

    public int getOuterColor() {
        return this.colorData.outerColor;
    }

    public void setOuterColor(int color) {
        this.colorData.outerColor = color;
    }

    public boolean isOuterColorEnabled() {
        return this.colorData.outerColorEnabled;
    }

    public void setOuterColorEnabled(boolean enabled) {
        this.colorData.outerColorEnabled = enabled;
    }

    public float getInnerAlpha() {
        return this.colorData.innerAlpha;
    }

    public void setInnerAlpha(float alpha) {
        this.colorData.innerAlpha = alpha;
    }

    public float getOuterColorWidth() {
        return this.colorData.outerColorWidth;
    }

    public void setOuterColorWidth(float width) {
        this.colorData.outerColorWidth = width;
    }

    public float getOuterColorAlpha() {
        return this.colorData.outerColorAlpha;
    }

    public void setOuterColorAlpha(float alpha) {
        this.colorData.outerColorAlpha = alpha;
    }

    public boolean isGroundFill() {
        return this.groundFill;
    }

    public void setGroundFill(boolean v) {
        this.groundFill = v;
    }

    public float getGroundAlpha() {
        return this.groundAlpha;
    }

    public void setGroundAlpha(float v) {
        this.groundAlpha = Math.max(0.0f, Math.min(1.0f, v));
    }

    public boolean isRings() {
        return this.rings;
    }

    public void setRings(boolean v) {
        this.rings = v;
    }

    public int getRingCount() {
        return this.ringCount;
    }

    public void setRingCount(int v) {
        this.ringCount = Math.max(1, Math.min(5, v));
    }

    public boolean isBorder() {
        return this.border;
    }

    public void setBorder(boolean v) {
        this.border = v;
    }

    public float getBorderSpeed() {
        return this.borderSpeed;
    }

    public void setBorderSpeed(float v) {
        this.borderSpeed = Math.max(0.1f, Math.min(5.0f, v));
    }

    public boolean isAccents() {
        return this.accents;
    }

    public void setAccents(boolean v) {
        this.accents = v;
    }

    public int getAccentStyle() {
        return this.accentStyle;
    }

    public void setAccentStyle(int v) {
        this.accentStyle = Math.max(0, Math.min(EntityAbilityZone.AccentStyle.values().length - 1, v));
    }

    public EntityAbilityZone.AccentStyle getAccentStyleEnum() {
        return EntityAbilityZone.AccentStyle.values()[Math.max(0, Math.min(this.accentStyle, EntityAbilityZone.AccentStyle.values().length - 1))];
    }

    public void setAccentStyleEnum(EntityAbilityZone.AccentStyle s) {
        this.accentStyle = s.ordinal();
    }

    public boolean isLightning() {
        return this.lightning;
    }

    public void setLightning(boolean v) {
        this.lightning = v;
    }

    public boolean isParticles() {
        return this.particles;
    }

    public void setParticles(boolean v) {
        this.particles = v;
    }

    public int getParticleMotion() {
        return this.particleMotion;
    }

    public void setParticleMotion(int v) {
        this.particleMotion = Math.max(0, Math.min(EntityAbilityZone.ParticleMotion.values().length - 1, v));
    }

    public EntityAbilityZone.ParticleMotion getParticleMotionEnum() {
        return EntityAbilityZone.ParticleMotion.values()[Math.max(0, Math.min(this.particleMotion, EntityAbilityZone.ParticleMotion.values().length - 1))];
    }

    public void setParticleMotionEnum(EntityAbilityZone.ParticleMotion m) {
        this.particleMotion = m.ordinal();
    }

    public String getParticleDir() {
        return this.particleDir;
    }

    public void setParticleDir(String v) {
        this.particleDir = v != null ? v : "";
    }

    public int getParticleSize() {
        return this.particleSize;
    }

    public void setParticleSize(int v) {
        this.particleSize = Math.max(1, Math.min(256, v));
    }

    public boolean isParticleGlow() {
        return this.particleGlow;
    }

    public void setParticleGlow(boolean v) {
        this.particleGlow = v;
    }

    @SideOnly(value=Side.CLIENT)
    protected void addPresetFieldDef(List<FieldDef> defs) {
        defs.add(FieldDef.subGuiField("gui.applyPreset", SubGuiZonePresetSelector::new, gui -> {
            SubGuiZonePresetSelector selector = gui;
            if (selector.selectedPreset != null) {
                this.applyPresetDefaults(selector.selectedPreset);
            }
        }));
    }

    @SideOnly(value=Side.CLIENT)
    protected void addVisualFieldDefs(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.section("ability.section.ground").tab("ability.tab.visual"), FieldDef.row(FieldDef.boolField("gui.enabled", this::isGroundFill, this::setGroundFill), FieldDef.floatField("gui.alpha", this::getGroundAlpha, this::setGroundAlpha)).tab("ability.tab.visual"), FieldDef.section("ability.section.rings").tab("ability.tab.visual"), FieldDef.row(FieldDef.boolField("gui.enabled", this::isRings, this::setRings), FieldDef.intField("gui.count", this::getRingCount, this::setRingCount).range(1.0f, 5.0f)).tab("ability.tab.visual"), FieldDef.section("ability.section.border").tab("ability.tab.visual"), FieldDef.row(FieldDef.boolField("gui.enabled", this::isBorder, this::setBorder), FieldDef.floatField("gui.speed", this::getBorderSpeed, this::setBorderSpeed)).tab("ability.tab.visual"), FieldDef.section("ability.section.accents").tab("ability.tab.visual"), FieldDef.row(FieldDef.boolField("gui.enabled", this::isAccents, this::setAccents), FieldDef.enumField("gui.style", EntityAbilityZone.AccentStyle.class, this::getAccentStyleEnum, this::setAccentStyleEnum)).tab("ability.tab.visual"), FieldDef.section("ability.section.lightning").tab("ability.tab.visual"), FieldDef.row(FieldDef.boolField("gui.enabled", this::isLightning, this::setLightning), FieldDef.floatField("gui.density", this::getLightningDensity, this::setLightningDensity)).tab("ability.tab.visual"), FieldDef.section("ability.section.particles").tab("ability.tab.visual"), FieldDef.boolField("gui.enabled", this::isParticles, this::setParticles).tab("ability.tab.visual"), FieldDef.row(FieldDef.floatField("gui.density", this::getParticleDensity, this::setParticleDensity), FieldDef.floatField("gui.scale", this::getParticleScale, this::setParticleScale)).tab("ability.tab.visual").visibleWhen(this::isParticles), FieldDef.row(FieldDef.enumField("gui.motion", EntityAbilityZone.ParticleMotion.class, this::getParticleMotionEnum, this::setParticleMotionEnum), FieldDef.boolField("gui.glow", this::isParticleGlow, this::setParticleGlow)).tab("ability.tab.visual").visibleWhen(this::isParticles), FieldDef.stringField("gui.texture", this::getParticleDir, this::setParticleDir).tab("ability.tab.visual").visibleWhen(this::isParticles), FieldDef.section("ability.section.animation").tab("ability.tab.visual"), FieldDef.floatField("gui.speed", this::getAnimSpeed, this::setAnimSpeed).tab("ability.tab.visual"), FieldDef.section("ability.section.colors").tab("ability.tab.visual"), FieldDef.row(FieldDef.colorSubGui("ability.innerColor", this::getInnerColor, this::setInnerColor), FieldDef.floatField("ability.innerAlpha", this::getInnerAlpha, this::setInnerAlpha).range(0.0f, 1.0f)).tab("ability.tab.visual"), FieldDef.boolField("ability.outerEnabled", this::isOuterColorEnabled, this::setOuterColorEnabled).tab("ability.tab.visual"), FieldDef.colorSubGui("ability.outerColor", this::getOuterColor, this::setOuterColor).tab("ability.tab.visual").visibleWhen(this::isOuterColorEnabled), FieldDef.row(FieldDef.floatField("ability.outerWidth", this::getOuterColorWidth, this::setOuterColorWidth).visibleWhen(this::isOuterColorEnabled), FieldDef.floatField("ability.outerAlpha", this::getOuterColorAlpha, this::setOuterColorAlpha).range(0.0f, 1.0f).visibleWhen(this::isOuterColorEnabled)).tab("ability.tab.visual")));
    }

    @SideOnly(value=Side.CLIENT)
    public static enum ZonePreset {
        DEFAULT,
        TOXIC,
        INFERNO,
        ARCANE,
        ELECTRIC,
        FROST;


        public String toString() {
            return "ability.preset." + this.name().toLowerCase();
        }
    }
}

