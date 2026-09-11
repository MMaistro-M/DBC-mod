/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.api.ability.type.IAbilityShockwave;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityShockwave
extends Ability
implements IAbilityShockwave {
    private float pushRadius = 8.0f;
    private float pushStrength = 1.5f;
    private float damage = 8.0f;
    private boolean aoe = true;
    private int activeDisplayTicks = 10;

    public AbilityShockwave() {
        this.typeId = "ability.cnpc.shockwave";
        this.name = "Shockwave";
        this.targetingMode = TargetingMode.AOE_SELF;
        this.maxRange = 8.0f;
        this.lockMovement = LockMode.WINDUP_AND_ACTIVE;
        this.cooldownTicks = 0;
        this.windUpTicks = 25;
        this.telegraphType = TelegraphType.CIRCLE;
        this.windUpSound = "game.tnt.primed";
        this.activeSound = "random.explode";
        this.windUpAnimationName = "Ability_Shockwave_Windup";
        this.activeAnimationName = "Ability_Shockwave_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/shockwave.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/shockwave_overlay.png", this::getActiveColor)};
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AOE_SELF};
    }

    @Override
    public float getTelegraphRadius() {
        return this.pushRadius;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.isPreview() && !caster.field_70170_p.field_72995_K) {
            AxisAlignedBB box = caster.field_70121_D.func_72314_b((double)this.pushRadius, (double)(this.pushRadius / 2.0f), (double)this.pushRadius);
            List entities = caster.field_70170_p.func_72872_a(EntityLivingBase.class, box);
            if (this.aoe) {
                for (EntityLivingBase entity : entities) {
                    double dist;
                    if (entity == caster || entity.field_70128_L || !AbilityTargetHelper.shouldAffect(caster, (Entity)entity, TargetFilter.ENEMIES, false) || (dist = (double)caster.func_70032_d((Entity)entity)) > (double)this.pushRadius) continue;
                    this.applyShockwavePush(caster, entity, dist);
                }
            } else {
                double dist;
                if (!this.isPlayerCaster(caster) && target != null && !target.field_70128_L && (dist = (double)caster.func_70032_d((Entity)target)) <= (double)this.pushRadius && AbilityTargetHelper.shouldAffect(caster, (Entity)target, TargetFilter.ENEMIES, false)) {
                    this.applyShockwavePush(caster, target, dist);
                    return;
                }
                EntityLivingBase nearest = null;
                double nearestDist = Double.MAX_VALUE;
                for (EntityLivingBase entity : entities) {
                    double dist2;
                    if (entity == caster || entity.field_70128_L || !AbilityTargetHelper.shouldAffect(caster, (Entity)entity, TargetFilter.ENEMIES, false) || !((dist2 = (double)caster.func_70032_d((Entity)entity)) <= (double)this.pushRadius) || !(dist2 < nearestDist)) continue;
                    nearest = entity;
                    nearestDist = dist2;
                }
                if (nearest != null) {
                    this.applyShockwavePush(caster, nearest, nearestDist);
                }
            }
        }
    }

    private void applyShockwavePush(EntityLivingBase caster, EntityLivingBase entity, double dist) {
        double dx = entity.field_70165_t - caster.field_70165_t;
        double dz = entity.field_70161_v - caster.field_70161_v;
        double len = Math.sqrt(dx * dx + dz * dz);
        if (len > 0.0) {
            dx /= len;
            dz /= len;
        } else {
            double angle = Math.random() * Math.PI * 2.0;
            dx = Math.cos(angle);
            dz = Math.sin(angle);
        }
        float distFactor = 1.0f - (float)(dist / (double)this.pushRadius) * 0.5f;
        float finalPush = this.pushStrength * distFactor;
        boolean wasHit = this.applyAbilityDamageWithDirection(caster, entity, this.damage * distFactor, finalPush, dx, dz);
        if (wasHit) {
            this.applyEffects(entity);
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (tick >= this.activeDisplayTicks) {
            this.signalCompletion();
        }
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("pushRadius", this.pushRadius);
        nbt.func_74776_a("pushStrength", this.pushStrength);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74757_a("aoe", this.aoe);
        nbt.func_74768_a("activeDisplayTicks", this.activeDisplayTicks);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.pushRadius = nbt.func_74760_g("pushRadius");
        this.pushStrength = nbt.func_74760_g("pushStrength");
        this.damage = nbt.func_74760_g("damage");
        this.aoe = !nbt.func_74764_b("aoe") || nbt.func_74767_n("aoe");
        this.activeDisplayTicks = nbt.func_74764_b("activeDisplayTicks") ? nbt.func_74762_e("activeDisplayTicks") : 10;
    }

    @Override
    public float getPushRadius() {
        return this.pushRadius;
    }

    @Override
    public void setPushRadius(float pushRadius) {
        this.pushRadius = pushRadius;
    }

    @Override
    public float getPushStrength() {
        return this.pushStrength;
    }

    @Override
    public void setPushStrength(float pushStrength) {
        this.pushStrength = pushStrength;
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
    public boolean isAoe() {
        return this.aoe;
    }

    @Override
    public void setAoe(boolean aoe) {
        this.aoe = aoe;
    }

    public int getActiveDisplayTicks() {
        return this.activeDisplayTicks;
    }

    public void setActiveDisplayTicks(int activeDisplayTicks) {
        this.activeDisplayTicks = activeDisplayTicks;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.section("ability.section.push"), FieldDef.row(FieldDef.floatField("gui.radius", this::getPushRadius, this::setPushRadius), FieldDef.floatField("gui.strength", this::getPushStrength, this::setPushStrength)), FieldDef.section("ability.section.aoe"), FieldDef.boolField("gui.enabled", this::isAoe, this::setAoe).hover("ability.hover.aoe"), FieldDef.intField("ability.activeDisplayTicks", this::getActiveDisplayTicks, this::setActiveDisplayTicks).range(1.0f, 200.0f), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }
}

