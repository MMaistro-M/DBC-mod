/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.data.effect.AbilityCustomEffect;
import kamkeel.npcs.controllers.data.ability.data.entry.AbilityEffectActionEntry;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import noppes.npcs.api.ability.type.IAbilityEffect;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityEffect
extends Ability
implements IAbilityEffect {
    private int durationTicks = 60;
    private float healAmount = 10.0f;
    private float healPercent = 0.0f;
    private boolean includeSelf = true;
    private float radius = 8.0f;
    private boolean instantHeal = true;
    private TargetFilter targetFilter = TargetFilter.ALLIES;
    private List<AbilityCustomEffect> customEffects = new ArrayList<AbilityCustomEffect>();
    private List<AbilityEffectActionEntry> effectActions = new ArrayList<AbilityEffectActionEntry>();
    private transient List<EntityLivingBase> affectedEntities;

    private List<EntityLivingBase> getAffectedEntities() {
        if (this.affectedEntities == null) {
            this.affectedEntities = new ArrayList<EntityLivingBase>();
        }
        return this.affectedEntities;
    }

    public AbilityEffect() {
        this.typeId = "ability.cnpc.effect";
        this.name = "Effect";
        this.targetingMode = TargetingMode.SELF;
        this.lockMovement = LockMode.WINDUP;
        this.cooldownTicks = 0;
        this.windUpTicks = 30;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/effect.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/effect_overlay.png", this::getActiveColor)};
    }

    @Override
    public boolean hasDamage() {
        return false;
    }

    @Override
    public boolean allowBurst() {
        return true;
    }

    @Override
    public boolean isTargetingModeLocked() {
        return false;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.SELF, TargetingMode.AOE_SELF};
    }

    @Override
    public boolean isConcurrentCapable() {
        return true;
    }

    @Override
    public float getTelegraphRadius() {
        return this.targetingMode == TargetingMode.AOE_SELF && this.radius > 0.0f ? this.radius : 0.0f;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (caster.field_70170_p.field_72995_K && !this.isPreview()) {
            return;
        }
        if (!this.isPreview()) {
            this.getAffectedEntities().clear();
            if (this.targetingMode == TargetingMode.AOE_SELF && this.radius > 0.0f) {
                this.findEntitiesInRadius(caster, caster.field_70170_p);
            } else if (this.includeSelf) {
                this.getAffectedEntities().add(caster);
            }
            if (this.instantHeal) {
                for (EntityLivingBase entity : this.getAffectedEntities()) {
                    this.healEntity(caster, entity);
                    this.applyAllEffects(caster, entity);
                    this.spawnHealParticles(caster.field_70170_p, entity);
                }
            }
        }
        if (this.instantHeal) {
            this.signalCompletion();
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (caster.field_70170_p.field_72995_K && !this.isPreview() || this.instantHeal) {
            return;
        }
        if (tick % 10 == 0) {
            float tickHealFixed = this.healAmount / (float)this.durationTicks * 10.0f;
            for (EntityLivingBase entity : this.getAffectedEntities()) {
                if (entity.field_70128_L) continue;
                float totalTickHeal = tickHealFixed;
                if (this.healPercent > 0.0f) {
                    totalTickHeal += entity.func_110138_aP() * this.healPercent / (float)this.durationTicks * 10.0f;
                }
                if (totalTickHeal > 0.0f && !AbilityController.Instance.fireOnAbilityHeal(this, caster, entity, totalTickHeal)) {
                    entity.func_70691_i(totalTickHeal);
                }
                if (tick % 20 != 0) continue;
                this.applyAllEffects(caster, entity);
                this.spawnHealParticles(caster.field_70170_p, entity);
            }
        }
        if (tick >= this.durationTicks) {
            this.signalCompletion();
            return;
        }
        boolean allDead = true;
        for (EntityLivingBase entity : this.getAffectedEntities()) {
            if (entity.field_70128_L) continue;
            allDead = false;
            break;
        }
        if (!this.getAffectedEntities().isEmpty() && allDead) {
            this.signalCompletion();
        }
    }

    private void applyAllEffects(EntityLivingBase caster, EntityLivingBase entity) {
        this.applyEffects(entity);
        this.applyCustomEffects(entity);
        this.applyEffectActions(caster, entity);
    }

    private void applyCustomEffects(EntityLivingBase entity) {
        for (AbilityCustomEffect ce : this.customEffects) {
            if (!ce.isValid()) continue;
            ce.apply(entity);
        }
    }

    private void applyEffectActions(EntityLivingBase caster, EntityLivingBase entity) {
        for (AbilityEffectActionEntry ea : this.effectActions) {
            if (!ea.isValid()) continue;
            ea.apply(caster, entity);
        }
    }

    private void findEntitiesInRadius(EntityLivingBase caster, World world) {
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(caster.field_70165_t - (double)this.radius), (double)(caster.field_70163_u - 2.0), (double)(caster.field_70161_v - (double)this.radius), (double)(caster.field_70165_t + (double)this.radius), (double)(caster.field_70163_u + 3.0), (double)(caster.field_70161_v + (double)this.radius));
        List entities = world.func_72872_a(EntityLivingBase.class, aabb);
        for (Entity entity : entities) {
            EntityLivingBase living;
            float dist;
            if (!(entity instanceof EntityLivingBase) || (dist = caster.func_70032_d((Entity)(living = (EntityLivingBase)entity))) > this.radius || !AbilityTargetHelper.shouldAffect(caster, (Entity)living, this.targetFilter, this.includeSelf)) continue;
            this.getAffectedEntities().add(living);
        }
    }

    private void healEntity(EntityLivingBase caster, EntityLivingBase entity) {
        float totalHeal = this.healAmount;
        if (this.healPercent > 0.0f) {
            totalHeal += entity.func_110138_aP() * this.healPercent;
        }
        if (totalHeal > 0.0f && !AbilityController.Instance.fireOnAbilityHeal(this, caster, entity, totalHeal)) {
            entity.func_70691_i(totalHeal);
        }
    }

    private void spawnHealParticles(World world, EntityLivingBase entity) {
        for (int i = 0; i < 10; ++i) {
            double offsetX = (world.field_73012_v.nextDouble() - 0.5) * (double)entity.field_70130_N;
            double offsetY = world.field_73012_v.nextDouble() * (double)entity.field_70131_O;
            double offsetZ = (world.field_73012_v.nextDouble() - 0.5) * (double)entity.field_70130_N;
            world.func_72869_a("happyVillager", entity.field_70165_t + offsetX, entity.field_70163_u + offsetY, entity.field_70161_v + offsetZ, 0.0, 0.1, 0.0);
        }
    }

    @Override
    public void resetForBurst() {
        this.getAffectedEntities().clear();
    }

    @Override
    public void cleanup() {
        this.getAffectedEntities().clear();
    }

    @Override
    public List<AbilityVariant> getVariants() {
        return Arrays.asList(new AbilityVariant("ability.variant.selfHeal", a -> {
            AbilityEffect e = (AbilityEffect)a;
            a.setName("Self Heal");
            e.setTargetingMode(TargetingMode.SELF);
            e.setIncludeSelf(true);
            e.setInstantHeal(true);
        }), new AbilityVariant("ability.variant.healingAura", a -> {
            AbilityEffect e = (AbilityEffect)a;
            a.setName("Healing Aura");
            e.setTargetingMode(TargetingMode.AOE_SELF);
            e.setTargetFilter(TargetFilter.ALLIES);
            e.setIncludeSelf(true);
            e.setRadius(8.0f);
            e.setBurstEnabled(true);
            e.setBurstAmount(5);
            e.setBurstDelay(20);
        }), new AbilityVariant("ability.variant.poisonDebuff", a -> {
            AbilityEffect e = (AbilityEffect)a;
            a.setName("Poison Debuff");
            e.setTargetingMode(TargetingMode.AOE_SELF);
            e.setTargetFilter(TargetFilter.ENEMIES);
            e.setIncludeSelf(false);
            e.setHealAmount(0.0f);
            e.setHealPercent(0.0f);
            e.setRadius(6.0f);
            e.setBurstEnabled(true);
            e.setBurstAmount(3);
            e.setBurstDelay(40);
        }));
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("durationTicks", this.durationTicks);
        nbt.func_74776_a("healAmount", this.healAmount);
        nbt.func_74776_a("healPercent", this.healPercent);
        nbt.func_74757_a("includeSelf", this.includeSelf);
        nbt.func_74776_a("radius", this.radius);
        nbt.func_74757_a("instantHeal", this.instantHeal);
        nbt.func_74778_a("targetFilter", this.targetFilter.name());
        NBTTagList ceList = new NBTTagList();
        for (AbilityCustomEffect ce : this.customEffects) {
            ceList.func_74742_a((NBTBase)ce.writeNBT());
        }
        nbt.func_74782_a("customEffects", (NBTBase)ceList);
        NBTTagList eaList = new NBTTagList();
        for (AbilityEffectActionEntry ea : this.effectActions) {
            eaList.func_74742_a((NBTBase)ea.writeNBT());
        }
        nbt.func_74782_a("effectActions", (NBTBase)eaList);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        int i;
        this.durationTicks = nbt.func_74762_e("durationTicks");
        this.healAmount = nbt.func_74760_g("healAmount");
        this.healPercent = nbt.func_74760_g("healPercent");
        this.includeSelf = nbt.func_74767_n("includeSelf");
        this.radius = nbt.func_74760_g("radius");
        this.instantHeal = nbt.func_74767_n("instantHeal");
        this.targetFilter = TargetFilter.fromString(nbt.func_74779_i("targetFilter"));
        this.customEffects.clear();
        if (nbt.func_74764_b("customEffects")) {
            NBTTagList ceList = nbt.func_150295_c("customEffects", 10);
            for (i = 0; i < ceList.func_74745_c(); ++i) {
                AbilityCustomEffect ce = AbilityCustomEffect.fromNBT(ceList.func_150305_b(i));
                if (!ce.isValid()) continue;
                this.customEffects.add(ce);
            }
        }
        this.effectActions.clear();
        if (nbt.func_74764_b("effectActions")) {
            NBTTagList eaList = nbt.func_150295_c("effectActions", 10);
            for (i = 0; i < eaList.func_74745_c(); ++i) {
                AbilityEffectActionEntry ea = AbilityEffectActionEntry.fromNBT(eaList.func_150305_b(i));
                this.effectActions.add(ea);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.boolField("ability.instantHeal", this::isInstantHeal, this::setInstantHeal).hover("ability.hover.instant"), FieldDef.intField("ability.duration", this::getDurationTicks, this::setDurationTicks).range(1.0f, 1000.0f).visibleWhen(() -> !this.isInstantHeal()), FieldDef.section("ability.section.healing"), FieldDef.row(FieldDef.floatField("ability.healAmount", this::getHealAmount, this::setHealAmount), FieldDef.floatField("ability.healPercent", this::getHealPercent, this::setHealPercent)), FieldDef.section("ability.section.targeting").tab("Target").visibleWhen(() -> this.targetingMode == TargetingMode.AOE_SELF), FieldDef.enumField("ability.targetFilter", TargetFilter.class, this::getTargetFilter, this::setTargetFilter).tab("Target").visibleWhen(() -> this.targetingMode == TargetingMode.AOE_SELF), FieldDef.row(FieldDef.boolField("ability.includeSelf", this::isIncludeSelf, this::setIncludeSelf).visibleWhen(() -> this.targetingMode == TargetingMode.AOE_SELF), FieldDef.floatField("gui.radius", this::getRadius, this::setRadius).visibleWhen(() -> this.targetingMode == TargetingMode.AOE_SELF)).tab("Target"), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects), AbilityFieldDefs.customEffectsListField("ability.customEffects", this::getCustomEffects, this::setCustomEffects), AbilityFieldDefs.effectActionsListField("ability.effectActions", this::getEffectActionEntries, this::setEffectActionEntries).visibleWhen(() -> AbilityController.Instance.hasEffectActions())));
    }

    @Override
    public int getDurationTicks() {
        return this.durationTicks;
    }

    @Override
    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(1, durationTicks);
    }

    @Override
    public float getHealAmount() {
        return this.healAmount;
    }

    @Override
    public void setHealAmount(float healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public float getHealPercent() {
        return this.healPercent;
    }

    @Override
    public void setHealPercent(float healPercent) {
        this.healPercent = healPercent;
    }

    @Override
    public boolean isIncludeSelf() {
        return this.includeSelf;
    }

    @Override
    public void setIncludeSelf(boolean includeSelf) {
        this.includeSelf = includeSelf;
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
    public boolean isInstantHeal() {
        return this.instantHeal;
    }

    @Override
    public void setInstantHeal(boolean instantHeal) {
        this.instantHeal = instantHeal;
    }

    public TargetFilter getTargetFilter() {
        return this.targetFilter;
    }

    public void setTargetFilter(TargetFilter targetFilter) {
        this.targetFilter = targetFilter;
    }

    @Override
    public int getTargetFilterType() {
        return this.targetFilter.ordinal();
    }

    @Override
    public void setTargetFilterType(int filter) {
        TargetFilter[] values = TargetFilter.values();
        if (filter >= 0 && filter < values.length) {
            this.targetFilter = values[filter];
        }
    }

    public List<AbilityCustomEffect> getCustomEffects() {
        return this.customEffects;
    }

    public void setCustomEffects(List<AbilityCustomEffect> customEffects) {
        this.customEffects = customEffects != null ? customEffects : new ArrayList();
    }

    @Override
    public int getCustomEffectCount() {
        return this.customEffects.size();
    }

    public List<AbilityEffectActionEntry> getEffectActionEntries() {
        return this.effectActions;
    }

    public void setEffectActionEntries(List<AbilityEffectActionEntry> effectActions) {
        this.effectActions = effectActions != null ? effectActions : new ArrayList();
    }

    @Override
    public int getEffectActionCount() {
        return this.effectActions.size();
    }
}

