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
 *  net.minecraft.util.DamageSource
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.type.AbilityMovement;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import noppes.npcs.api.ability.type.IAbilityCharge;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityCharge
extends AbilityMovement
implements IAbilityCharge {
    private float chargeSpeed = 0.8f;
    private float damage = 8.0f;
    private float knockback = 3.0f;
    private float hitWidth = 1.5f;
    private transient Set<Integer> hitEntities = new HashSet<Integer>();

    public AbilityCharge() {
        this.typeId = "ability.cnpc.charge";
        this.name = "Charge";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 20.0f;
        this.minRange = 4.0f;
        this.lockMovement = LockMode.WINDUP;
        this.cooldownTicks = 0;
        this.windUpTicks = 20;
        this.telegraphType = TelegraphType.LINE;
        this.showTelegraph = true;
        this.windUpSound = "mob.zombie.wood";
        this.activeSound = "mob.zombie.attack";
        this.windUpAnimationName = "Ability_Charge_Windup";
        this.activeAnimationName = "Ability_Charge_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/charge.png")};
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
    public void onWindUpTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (!this.isPlayerCaster(caster) && target != null) {
            this.lockDirectionToTarget(caster, target);
            this.enforceLockedRotation(caster);
        }
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        this.lockDirection(caster, target);
        this.initMovement(caster, this.maxRange, this.chargeSpeed);
        this.hitEntities.clear();
        this.enforceLockedRotation(caster);
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.checkTimeout(tick)) {
            this.stopMomentum(caster);
            this.signalCompletion();
            return;
        }
        if (this.movementDirection == null) {
            this.stopMomentum(caster);
            this.signalCompletion();
            return;
        }
        if (!this.isPreview()) {
            this.enforceLockedRotation(caster);
        }
        if (this.checkStall(caster, tick)) {
            this.stopMomentum(caster);
            this.signalCompletion();
            return;
        }
        this.updatePrevPosition(caster);
        if (this.getDistanceTraveled(caster) >= (double)this.maxRange) {
            this.stopMomentum(caster);
            this.signalCompletion();
            return;
        }
        if (this.checkBlocked(caster, this.chargeSpeed)) {
            this.stopMomentum(caster);
            this.signalCompletion();
            return;
        }
        this.applyVelocityFlat(caster, this.chargeSpeed);
        if (!caster.field_70170_p.field_72995_K && !this.isPreview()) {
            AxisAlignedBB hitBox = caster.field_70121_D.func_72314_b((double)this.hitWidth, (double)this.hitWidth * 0.5, (double)this.hitWidth);
            List entities = caster.field_70170_p.func_72872_a(EntityLivingBase.class, hitBox);
            for (Entity entity : entities) {
                if (!(entity instanceof EntityLivingBase) || entity == caster || this.hitEntities.contains(entity.func_145782_y()) || !AbilityTargetHelper.shouldAffect(caster, entity, TargetFilter.ENEMIES, false)) continue;
                EntityLivingBase livingEntity = (EntityLivingBase)entity;
                this.hitEntities.add(entity.func_145782_y());
                boolean wasHit = this.applyAbilityDamageWithDirection(caster, livingEntity, this.damage, this.knockback, this.movementDirection.field_72450_a, this.movementDirection.field_72449_c);
                if (!wasHit) continue;
                this.applyEffects(livingEntity);
                caster.field_70170_p.func_72956_a((Entity)livingEntity, "random.explode", 0.5f, 1.2f);
            }
        }
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
        this.stopMomentum(caster);
        super.onComplete(caster, target);
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.stopMomentum(caster);
        super.onInterrupt(caster, source, damage);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        this.hitEntities.clear();
    }

    @Override
    public void resetForBurst() {
        super.resetForBurst();
        this.hitEntities.clear();
    }

    @Override
    public float getTelegraphLength() {
        return this.maxRange;
    }

    @Override
    public float getTelegraphWidth() {
        return this.hitWidth * 2.0f;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        float yaw;
        if (!this.isShowTelegraph() || this.getTelegraphType() == TelegraphType.NONE) {
            return null;
        }
        if (!this.isPlayerCaster(caster) && target != null) {
            double dx = target.field_70165_t - caster.field_70165_t;
            double dz = target.field_70161_v - caster.field_70161_v;
            yaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
        } else {
            yaw = caster.field_70177_z;
        }
        Telegraph telegraph = Telegraph.line(this.getTelegraphLength(), this.getTelegraphWidth());
        telegraph.setDurationTicks(this.windUpTicks);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        double groundY = AbilityCharge.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
        TelegraphInstance instance = new TelegraphInstance(telegraph, caster.field_70165_t, groundY, caster.field_70161_v, yaw);
        instance.setCasterEntityId(caster.func_145782_y());
        instance.setEntityIdToFollow(caster.func_145782_y());
        if (this.isPlayerCaster(caster)) {
            instance.setTrackFollowedEntityYaw(true);
        }
        return instance;
    }

    @Override
    public int getMaxPreviewDuration() {
        return (int)Math.ceil(this.maxRange / this.chargeSpeed) + 5;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("chargeSpeed", this.chargeSpeed);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74776_a("hitWidth", this.hitWidth);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.chargeSpeed = nbt.func_74764_b("chargeSpeed") ? Math.max(0.01f, nbt.func_74760_g("chargeSpeed")) : 0.8f;
        this.damage = nbt.func_74764_b("damage") ? nbt.func_74760_g("damage") : 8.0f;
        this.knockback = nbt.func_74764_b("knockback") ? nbt.func_74760_g("knockback") : 3.0f;
        this.hitWidth = nbt.func_74764_b("hitWidth") ? nbt.func_74760_g("hitWidth") : 1.5f;
    }

    @Override
    public float getChargeSpeed() {
        return this.chargeSpeed;
    }

    @Override
    public void setChargeSpeed(float chargeSpeed) {
        this.chargeSpeed = chargeSpeed;
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
    public float getKnockback() {
        return this.knockback;
    }

    @Override
    public void setKnockback(float knockback) {
        this.knockback = knockback;
    }

    @Override
    public float getHitWidth() {
        return this.hitWidth;
    }

    @Override
    public void setHitWidth(float hitWidth) {
        this.hitWidth = hitWidth;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("ability.chargeSpeed", this::getChargeSpeed, this::setChargeSpeed)), FieldDef.row(FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback), FieldDef.floatField("ability.hitWidth", this::getHitWidth, this::setHitWidth)), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }
}

