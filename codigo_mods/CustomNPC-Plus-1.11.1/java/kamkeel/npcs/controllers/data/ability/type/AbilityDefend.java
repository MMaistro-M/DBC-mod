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
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import noppes.npcs.api.ability.type.IAbilityDefend;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.controllers.data.Animation;

public abstract class AbilityDefend
extends Ability
implements IAbilityDefend {
    protected int durationTicks = 60;
    protected int maxHitAmount = 3;
    protected transient EntityLivingBase caster;
    protected transient int hitCount;
    protected transient EntityLivingBase lastAttacker;
    protected transient float lastDamageTaken;
    protected transient Animation pendingDefendAnimation;
    protected transient int defendAnimEndTick = -1;
    protected transient long lastDefendTick = -1L;
    protected transient boolean pendingCompletion;

    protected AbilityDefend() {
        this.targetingMode = TargetingMode.SELF;
        this.lockMovement = LockMode.ACTIVE;
        this.interruptible = false;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
        this.windUpTicks = 0;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        this.caster = caster;
        this.hitCount = 0;
        this.lastAttacker = null;
        this.lastDamageTaken = 0.0f;
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.pendingCompletion || tick >= this.durationTicks) {
            this.signalCompletion();
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.caster = null;
        this.hitCount = 0;
        this.lastAttacker = null;
        this.lastDamageTaken = 0.0f;
        this.pendingDefendAnimation = null;
        this.defendAnimEndTick = -1;
        this.lastDefendTick = -1L;
        this.pendingCompletion = false;
    }

    public final float onDefend(EntityLivingBase attacker, DamageSource source, float amount) {
        boolean firstCallThisTick;
        if (!this.isDefending()) {
            return amount;
        }
        if (attacker == null || source == null) {
            return amount;
        }
        if (!this.isValidDamageSource(source)) {
            return amount;
        }
        long currentTick = this.caster != null && this.caster.field_70170_p != null ? this.caster.field_70170_p.func_82737_E() : -1L;
        boolean bl = firstCallThisTick = currentTick < 0L || currentTick != this.lastDefendTick;
        if (this.pendingCompletion && firstCallThisTick) {
            return amount;
        }
        if (firstCallThisTick) {
            this.lastDefendTick = currentTick;
            this.lastAttacker = attacker;
            this.lastDamageTaken = amount;
            ++this.hitCount;
            this.pendingDefendAnimation = this.getDefendAnimation();
            if (this.maxHitAmount > 0 && this.hitCount >= this.maxHitAmount) {
                this.pendingCompletion = true;
            }
        }
        return this.performDefend(attacker, amount);
    }

    protected abstract float performDefend(EntityLivingBase var1, float var2);

    protected boolean isValidDamageSource(DamageSource source) {
        return !source.func_82725_o() && !source.func_76347_k() && !source.func_94541_c() && !source.func_76352_a();
    }

    protected abstract void writeSubTypeNBT(NBTTagCompound var1);

    protected abstract void readSubTypeNBT(NBTTagCompound var1);

    @SideOnly(value=Side.CLIENT)
    protected abstract void getTypeDefinitions(List<FieldDef> var1);

    protected Animation getDefendAnimation() {
        return null;
    }

    public Animation consumeDefendAnimation() {
        Animation anim = this.pendingDefendAnimation;
        this.pendingDefendAnimation = null;
        return anim;
    }

    public void scheduleReturnToActive(int currentTick, Animation defendAnim) {
        if (defendAnim != null) {
            this.defendAnimEndTick = currentTick + (int)defendAnim.getTotalTime();
        }
    }

    public boolean shouldReturnToActiveAnimation(int currentTick) {
        if (this.defendAnimEndTick >= 0 && currentTick >= this.defendAnimEndTick) {
            this.defendAnimEndTick = -1;
            return true;
        }
        return false;
    }

    @Override
    public boolean isDefending() {
        return this.isExecuting() && this.getPhase() == AbilityPhase.ACTIVE;
    }

    @Override
    public boolean hasDamage() {
        return false;
    }

    @Override
    public boolean allowBurst() {
        return false;
    }

    @Override
    public boolean isTargetingModeLocked() {
        return true;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.SELF};
    }

    @Override
    public float getTelegraphRadius() {
        return 0.0f;
    }

    @Override
    public boolean keepTelegraphDuringActive() {
        return true;
    }

    @Override
    public final void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("durationTicks", this.durationTicks);
        nbt.func_74768_a("maxHitAmount", this.maxHitAmount);
        this.writeSubTypeNBT(nbt);
    }

    @Override
    public final void readTypeNBT(NBTTagCompound nbt) {
        this.durationTicks = nbt.func_74764_b("durationTicks") ? nbt.func_74762_e("durationTicks") : 60;
        this.maxHitAmount = nbt.func_74764_b("maxHitAmount") ? nbt.func_74762_e("maxHitAmount") : 3;
        this.readSubTypeNBT(nbt);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public final void getAbilityDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.intField("ability.duration", this::getDurationTicks, this::setDurationTicks).range(1.0f, 6000.0f));
        defs.add(FieldDef.intField("ability.maxHitAmount", this::getMaxHitAmount, this::setMaxHitAmount).range(0.0f, 100.0f).hover("ability.hover.maxHitAmount"));
        this.getTypeDefinitions(defs);
    }

    @Override
    public int getDurationTicks() {
        return this.durationTicks;
    }

    @Override
    public void setDurationTicks(int ticks) {
        this.durationTicks = Math.max(1, ticks);
    }

    @Override
    public int getMaxHitAmount() {
        return this.maxHitAmount;
    }

    @Override
    public void setMaxHitAmount(int amount) {
        this.maxHitAmount = Math.max(0, amount);
    }

    public int getHitCount() {
        return this.hitCount;
    }
}

