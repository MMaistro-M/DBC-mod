/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
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
import noppes.npcs.api.ability.type.IAbilityHeavyHit;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityHeavyHit
extends Ability
implements IAbilityHeavyHit {
    private float damage = 8.0f;
    private float knockback = 2.0f;
    private float hitLength = 4.0f;
    private float hitWidth = 3.0f;
    private int hitDelayTicks = 0;
    private int activeDisplayTicks = 10;

    public AbilityHeavyHit() {
        this.typeId = "ability.cnpc.heavy_hit";
        this.name = "Heavy Hit";
        this.targetingMode = TargetingMode.AOE_SELF;
        this.maxRange = 5.0f;
        this.minRange = 0.0f;
        this.lockMovement = LockMode.WINDUP;
        this.cooldownTicks = 0;
        this.windUpTicks = 30;
        this.telegraphType = TelegraphType.LINE;
        this.showTelegraph = true;
        this.windUpSound = "random.anvil_use";
        this.activeSound = "random.anvil_land";
        this.windUpAnimationName = "Ability_HeavyHit_Windup";
        this.activeAnimationName = "Ability_HeavyHit_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/heavy_hit.png"), new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/heavy_hit_overlay.png", this::getActiveColor)};
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
    public float getTelegraphLength() {
        return this.hitLength;
    }

    @Override
    public float getTelegraphWidth() {
        return this.hitWidth * 2.0f;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (caster.field_70170_p.field_72995_K && !this.isPreview()) {
            this.signalCompletion();
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.isPreview()) {
            if (tick >= this.activeDisplayTicks + this.hitDelayTicks) {
                this.signalCompletion();
            }
            return;
        }
        if (tick >= this.activeDisplayTicks + this.hitDelayTicks) {
            this.signalCompletion();
            return;
        }
        if (tick == this.hitDelayTicks && !caster.field_70170_p.field_72995_K) {
            double forwardZ;
            float yawRad = (float)Math.toRadians(caster.field_70177_z);
            double forwardX = -Math.sin(yawRad);
            double rightX = forwardZ = Math.cos(yawRad);
            double rightZ = -forwardX;
            float searchDist = Math.max(this.hitLength, this.hitWidth) + 1.0f;
            List entities = caster.field_70170_p.func_72839_b((Entity)caster, caster.field_70121_D.func_72314_b((double)searchDist, 2.0, (double)searchDist));
            boolean anyHit = false;
            for (Entity entity : entities) {
                boolean wasHit;
                double sideDist;
                double dz;
                double dx;
                double forwardDist;
                EntityLivingBase livingTarget;
                if (!(entity instanceof EntityLivingBase) || entity == caster || !AbilityTargetHelper.shouldAffect(caster, (Entity)(livingTarget = (EntityLivingBase)entity), TargetFilter.ENEMIES, false) || (forwardDist = (dx = livingTarget.field_70165_t - caster.field_70165_t) * forwardX + (dz = livingTarget.field_70161_v - caster.field_70161_v) * forwardZ) < 0.0 || forwardDist > (double)this.hitLength || Math.abs(sideDist = dx * rightX + dz * rightZ) > (double)this.hitWidth || !AbilityHeavyHit.hasLineOfSight(caster.field_70170_p, caster, livingTarget) || AbilityHeavyHit.isBlockedByBarrier(caster.field_70170_p, caster, livingTarget) || !(wasHit = this.applyAbilityDamage(caster, livingTarget, this.damage, this.knockback))) continue;
                this.applyEffects(livingTarget);
                anyHit = true;
            }
            if (!anyHit) {
                caster.field_70170_p.func_72956_a((Entity)caster, "random.anvil_land", 0.5f, 1.2f);
            }
        }
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("knockback", this.knockback);
        nbt.func_74776_a("hitLength", this.hitLength);
        nbt.func_74776_a("hitWidth", this.hitWidth);
        nbt.func_74768_a("activeDisplayTicks", this.activeDisplayTicks);
        nbt.func_74768_a("hitDelayTicks", this.hitDelayTicks);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.damage = nbt.func_74760_g("damage");
        this.knockback = nbt.func_74760_g("knockback");
        this.hitLength = nbt.func_74760_g("hitLength");
        this.hitWidth = nbt.func_74760_g("hitWidth");
        this.hitDelayTicks = nbt.func_74762_e("hitDelayTicks");
        this.activeDisplayTicks = nbt.func_74762_e("activeDisplayTicks");
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
    public float getHitLength() {
        return this.hitLength;
    }

    @Override
    public void setHitLength(float hitLength) {
        this.hitLength = hitLength;
    }

    @Override
    public float getHitWidth() {
        return this.hitWidth;
    }

    @Override
    public void setHitWidth(float hitWidth) {
        this.hitWidth = hitWidth;
    }

    public int getActiveDisplayTicks() {
        return this.activeDisplayTicks;
    }

    public void setActiveDisplayTicks(int activeDisplayTicks) {
        this.activeDisplayTicks = activeDisplayTicks;
    }

    public int getHitDelayTicks() {
        return this.hitDelayTicks;
    }

    public void setHitDelayTicks(int hitDelayTicks) {
        this.hitDelayTicks = hitDelayTicks;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("ability.knockback", this::getKnockback, this::setKnockback)), FieldDef.section("ability.section.hitZone"), FieldDef.row(FieldDef.floatField("ability.hitLength", this::getHitLength, this::setHitLength), FieldDef.floatField("ability.hitWidth", this::getHitWidth, this::setHitWidth)), FieldDef.section("ability.section.timing"), FieldDef.row(FieldDef.intField("ability.hitDelayTicks", this::getHitDelayTicks, this::setHitDelayTicks).min(0.0f), FieldDef.intField("ability.activeDisplayTicks", this::getActiveDisplayTicks, this::setActiveDisplayTicks).range(1.0f, 200.0f)), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }
}

