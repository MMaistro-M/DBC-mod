/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 */
package noppes.npcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.ConcurrentSlot;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.entry.AbilityToggleEntry;
import kamkeel.npcs.controllers.data.ability.data.entry.ChainedAbilityEntry;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.type.AbilityDefend;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import noppes.npcs.EventHooks;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.event.AbilityEvent;

public abstract class AbstractDataAbilities {
    protected static final int MAX_ACTIVE_PHASE_TICKS = 600;
    protected Ability currentAbility;
    protected long cooldownEndTime = 0L;
    protected int globalCooldownDuration = 0;
    protected HashMap<String, Long> perAbilityCooldownEndTimes = new HashMap();
    protected HashMap<String, Integer> perAbilityCooldownDurations = new HashMap();
    protected boolean rotationLocked = false;
    protected float lockedYaw = 0.0f;
    protected float lockedPitch = 0.0f;
    protected boolean positionLocked = false;
    protected boolean wasFlyingAtLock = false;
    protected double lockedPosX = 0.0;
    protected double lockedPosY = 0.0;
    protected double lockedPosZ = 0.0;
    protected ChainedAbility currentChain;
    protected int chainEntryIndex = -1;
    protected int chainDelayRemaining = -1;
    protected boolean interruptCooldownRolled = false;
    protected List<ConcurrentSlot> concurrentSlots = new ArrayList<ConcurrentSlot>();
    protected Map<String, AbilityToggleEntry> activeToggles = new LinkedHashMap<String, AbilityToggleEntry>();

    protected abstract EntityLivingBase getEntity();

    protected abstract EntityLivingBase getTarget();

    protected abstract long getWorldTime();

    protected void fireTickEvent(Ability ability, EntityLivingBase target) {
        EventHooks.onAbilityTick(ability, this.getEntity(), target, ability.getPhase().ordinal(), ability.getCurrentTick());
    }

    protected boolean fireExecuteEvent(Ability ability, EntityLivingBase target) {
        return EventHooks.onAbilityExecute(ability, this.getEntity(), target);
    }

    protected void fireCompleteEvent(Ability ability, EntityLivingBase target) {
        EventHooks.onAbilityComplete(ability, this.getEntity(), target);
    }

    protected void fireInterruptEvent(Ability ability, EntityLivingBase target, DamageSource source, float damage) {
        EventHooks.onAbilityInterrupt(ability, this.getEntity(), target, source, damage);
    }

    protected boolean fireToggleEvent(Ability ability, int oldState, int newState) {
        return EventHooks.onAbilityToggle(ability, this.getEntity(), oldState, newState);
    }

    protected boolean fireToggleUpdateEvent(Ability ability, int tick, int state) {
        AbilityEvent.ToggleUpdateEvent event = new AbilityEvent.ToggleUpdateEvent(this.getEntity(), ability, tick, state);
        EventHooks.onAbilityToggleUpdate(ability, event);
        return event.isEnabled();
    }

    protected void fireChainStartEvent(ChainedAbility chain, int entryIndex, EntityLivingBase target) {
        EventHooks.onChainStart(chain, this.getEntity(), entryIndex, target);
    }

    protected void fireChainNextEvent(ChainedAbility chain, int entryIndex, EntityLivingBase target) {
        EventHooks.onChainNext(chain, this.getEntity(), entryIndex, target);
    }

    protected void fireChainCompleteEvent(ChainedAbility chain, int entryIndex, EntityLivingBase target) {
        EventHooks.onChainComplete(chain, this.getEntity(), entryIndex, target);
    }

    protected void fireChainInterruptEvent(ChainedAbility chain, int entryIndex, EntityLivingBase target, DamageSource source, float damage) {
        EventHooks.onChainInterrupt(chain, this.getEntity(), entryIndex, target, source, damage);
    }

    protected abstract void spawnTelegraph(Ability var1, EntityLivingBase var2);

    protected abstract void removeTelegraph(Ability var1);

    protected abstract void setAnimationData(Animation var1);

    protected abstract void clearAnimationData();

    protected abstract void playAbilitySound(String var1);

    protected abstract void captureLockedRotation();

    protected abstract void rollCooldown(Ability var1);

    protected abstract void onAbilityComplete();

    protected abstract void rollChainCooldown(ChainedAbility var1);

    protected void onPreExecute(Ability ability, EntityLivingBase target) {
    }

    protected void onPostPhaseTick(Ability ability, EntityLivingBase target) {
    }

    protected void onBurstDelayReleaseLocks() {
    }

    protected EntityLivingBase retargetForChain() {
        return null;
    }

    public void playAbilityAnimation(Animation animation) {
        if (animation == null) {
            return;
        }
        if (AnimationController.Instance == null) {
            return;
        }
        this.setAnimationData(animation);
    }

    public void playAbilityAnimation(int animation) {
        if (animation < 0) {
            return;
        }
        if (AnimationController.Instance == null) {
            return;
        }
        if (AnimationController.Instance.get(animation) == null) {
            return;
        }
        this.playAbilityAnimation((Animation)AnimationController.Instance.get(animation));
    }

    public void playAbilityAnimation(String animation) {
        if (animation.isEmpty()) {
            return;
        }
        if (AnimationController.Instance == null) {
            return;
        }
        if (AnimationController.Instance.get(animation, true) == null) {
            return;
        }
        this.playAbilityAnimation((Animation)AnimationController.Instance.get(animation, true));
    }

    protected void stopAbilityAnimation() {
        this.clearAnimationData();
    }

    public boolean isExecutingAbility() {
        return this.currentAbility != null && this.currentAbility.isExecuting();
    }

    public boolean isCurrentAbilityInvulnerable() {
        return this.currentAbility != null && this.currentAbility.isExecuting() && this.currentAbility.isInvulnerableForCurrentPhase();
    }

    public Ability getCurrentAbility() {
        return this.currentAbility;
    }

    public AbilityDefend getActiveDefend() {
        AbilityDefend defend;
        if (this.currentAbility instanceof AbilityDefend && (defend = (AbilityDefend)this.currentAbility).isDefending()) {
            return defend;
        }
        return null;
    }

    public boolean isExecutingChain() {
        return this.currentChain != null;
    }

    public ChainedAbility getCurrentChain() {
        return this.currentChain;
    }

    public int toggleAbility(String key) {
        AbilityToggleEntry existing = this.activeToggles.get(key);
        if (existing != null) {
            int maxStates;
            int currentState = existing.getState();
            if (currentState < (maxStates = existing.getAbility().getToggleStates())) {
                return this.cycleToggleState(key, existing, currentState, currentState + 1);
            }
            this.deactivateToggle(key);
            return 0;
        }
        return this.activateToggle(key, 1) ? 1 : 0;
    }

    public int getToggleState(String key) {
        AbilityToggleEntry entry = this.activeToggles.get(key);
        return entry != null ? entry.getState() : 0;
    }

    public void setToggleState(String key, int state) {
        if (state <= 0) {
            if (this.activeToggles.containsKey(key)) {
                this.deactivateToggle(key);
            }
            return;
        }
        AbilityToggleEntry existing = this.activeToggles.get(key);
        if (existing != null) {
            int currentState = existing.getState();
            if (currentState != state) {
                this.cycleToggleState(key, existing, currentState, state);
            }
        } else {
            this.activateToggle(key, state);
        }
    }

    public boolean isAbilityToggled(String key) {
        return this.activeToggles.containsKey(key);
    }

    public Set<String> getActiveToggleKeys() {
        return new LinkedHashSet<String>(this.activeToggles.keySet());
    }

    private boolean activateToggle(String key, int state) {
        Ability ability;
        Ability ability2 = ability = AbilityController.Instance != null ? AbilityController.Instance.resolveAbility(key) : null;
        if (ability == null || !ability.isToggleable()) {
            return false;
        }
        if (state < 1 || state > ability.getToggleStates()) {
            state = 1;
        }
        if (this.fireToggleEvent(ability, 0, state)) {
            return false;
        }
        AbilityToggleEntry entry = new AbilityToggleEntry(ability, state);
        this.activeToggles.put(key, entry);
        ability.onToggle(this.getEntity(), 0, state);
        this.onToggleStateChanged(key, true, state);
        return true;
    }

    private int cycleToggleState(String key, AbilityToggleEntry entry, int oldState, int newState) {
        Ability ability = entry.getAbility();
        if (newState < 1 || newState > ability.getToggleStates()) {
            return oldState;
        }
        if (this.fireToggleEvent(ability, oldState, newState)) {
            return oldState;
        }
        entry.setState(newState);
        ability.onToggle(this.getEntity(), oldState, newState);
        this.onToggleStateChanged(key, true, newState);
        return newState;
    }

    private void deactivateToggle(String key) {
        AbilityToggleEntry entry = this.activeToggles.get(key);
        if (entry == null) {
            return;
        }
        int oldState = entry.getState();
        if (this.fireToggleEvent(entry.getAbility(), oldState, 0)) {
            return;
        }
        this.activeToggles.remove(key);
        entry.getAbility().onToggle(this.getEntity(), oldState, 0);
        this.onToggleStateChanged(key, false, 0);
    }

    protected void tickActiveToggles() {
        AbilityToggleEntry entry;
        if (this.activeToggles.isEmpty()) {
            return;
        }
        EntityLivingBase entity = this.getEntity();
        ArrayList<String> toRemove = null;
        for (Map.Entry<String, AbilityToggleEntry> mapEntry : new ArrayList<Map.Entry<String, AbilityToggleEntry>>(this.activeToggles.entrySet())) {
            boolean enabled;
            entry = mapEntry.getValue();
            entry.incrementTick();
            if (entry.getAbility().hasActiveToggle() && !entry.getAbility().onToggleTick(entity, entry.getTickCount(), entry.getState())) {
                if (toRemove == null) {
                    toRemove = new ArrayList<String>();
                }
                toRemove.add(mapEntry.getKey());
                continue;
            }
            if (entry.getTickCount() % 10 != 0 || (enabled = this.fireToggleUpdateEvent(entry.getAbility(), entry.getTickCount(), entry.getState()))) continue;
            if (toRemove == null) {
                toRemove = new ArrayList();
            }
            toRemove.add(mapEntry.getKey());
        }
        if (toRemove != null) {
            for (String key : toRemove) {
                entry = this.activeToggles.remove(key);
                if (entry == null) continue;
                int oldState = entry.getState();
                entry.getAbility().onToggle(entity, oldState, 0);
                this.onToggleStateChanged(key, false, 0);
            }
        }
    }

    protected void onToggleStateChanged(String key, boolean active, int state) {
    }

    protected void clearActiveToggles() {
        if (this.activeToggles.isEmpty()) {
            return;
        }
        EntityLivingBase entity = this.getEntity();
        for (Map.Entry<String, AbilityToggleEntry> mapEntry : this.activeToggles.entrySet()) {
            AbilityToggleEntry entry = mapEntry.getValue();
            entry.getAbility().onToggle(entity, entry.getState(), 0);
        }
        this.activeToggles.clear();
    }

    public void setToggleEntryDirect(String key, int state) {
        if (state > 0) {
            Ability ability;
            Ability ability2 = ability = AbilityController.Instance != null ? AbilityController.Instance.resolveAbility(key) : null;
            if (ability != null && ability.isToggleable()) {
                this.activeToggles.put(key, new AbilityToggleEntry(ability, state));
            }
        } else {
            this.activeToggles.remove(key);
        }
    }

    public boolean isOnCooldown() {
        return this.getWorldTime() < this.cooldownEndTime;
    }

    public long getRemainingCooldown() {
        long remaining = this.cooldownEndTime - this.getWorldTime();
        return remaining > 0L ? remaining : 0L;
    }

    public void resetCooldown() {
        this.cooldownEndTime = 0L;
    }

    public void setCooldownEndTime(long endTime) {
        this.cooldownEndTime = endTime;
    }

    public long getCooldownEndTime() {
        return this.cooldownEndTime;
    }

    public int getGlobalCooldownDurationValue() {
        return this.globalCooldownDuration;
    }

    public HashMap<String, Long> getPerAbilityCooldownEndTimes() {
        return this.perAbilityCooldownEndTimes;
    }

    public HashMap<String, Integer> getPerAbilityCooldownDurations() {
        return this.perAbilityCooldownDurations;
    }

    public void applyCooldownSync(long globalEndTime, int globalDuration, HashMap<String, Long> perEndTimes, HashMap<String, Integer> perDurations) {
        this.cooldownEndTime = globalEndTime;
        this.globalCooldownDuration = globalDuration;
        this.perAbilityCooldownEndTimes = perEndTimes;
        this.perAbilityCooldownDurations = perDurations;
    }

    public boolean isOnPerAbilityCooldown(String key) {
        Long endTime = this.perAbilityCooldownEndTimes.get(key);
        return endTime != null && this.getWorldTime() < endTime;
    }

    public void setPerAbilityCooldown(String key, long endTime, int duration) {
        this.perAbilityCooldownEndTimes.put(key, endTime);
        this.perAbilityCooldownDurations.put(key, duration);
    }

    public void resetPerAbilityCooldown(String key) {
        this.perAbilityCooldownEndTimes.remove(key);
        this.perAbilityCooldownDurations.remove(key);
    }

    public void resetAllPerAbilityCooldowns() {
        this.perAbilityCooldownEndTimes.clear();
        this.perAbilityCooldownDurations.clear();
    }

    public float getGlobalCooldownProgress() {
        if (this.globalCooldownDuration <= 0) {
            return 0.0f;
        }
        long remaining = this.cooldownEndTime - this.getWorldTime();
        if (remaining <= 0L) {
            return 0.0f;
        }
        return Math.min(1.0f, (float)remaining / (float)this.globalCooldownDuration);
    }

    public float getPerAbilityCooldownProgress(String key) {
        Long endTime = this.perAbilityCooldownEndTimes.get(key);
        Integer duration = this.perAbilityCooldownDurations.get(key);
        if (endTime == null || duration == null || duration <= 0) {
            return 0.0f;
        }
        long remaining = endTime - this.getWorldTime();
        if (remaining <= 0L) {
            return 0.0f;
        }
        return Math.min(1.0f, (float)remaining / (float)duration.intValue());
    }

    protected void captureLockedPosition() {
        EntityLivingBase entity = this.getEntity();
        this.lockedPosX = entity.field_70165_t;
        this.lockedPosY = entity.field_70163_u;
        this.lockedPosZ = entity.field_70161_v;
        this.positionLocked = true;
        this.wasFlyingAtLock = entity instanceof EntityPlayer && AbilityController.Instance != null && AbilityController.Instance.isPlayerFlying((EntityPlayer)entity);
        this.onPositionLockChanged(true);
    }

    protected void releaseLockedPosition() {
        this.positionLocked = false;
        this.wasFlyingAtLock = false;
        this.onPositionLockChanged(false);
    }

    public boolean wasFlyingAtLock() {
        return this.wasFlyingAtLock;
    }

    protected void onPositionLockChanged(boolean locked) {
    }

    protected void releaseRotationControl() {
        this.rotationLocked = false;
        this.onRotationLockChanged(false);
    }

    protected void onRotationLockChanged(boolean locked) {
    }

    protected void tickCurrentAbility() {
        if (this.chainDelayRemaining > 0) {
            --this.chainDelayRemaining;
            if (this.chainDelayRemaining <= 0) {
                EntityLivingBase chainTarget = this.getTarget();
                if (this.currentChain != null && chainTarget != null && chainTarget.field_70128_L && (chainTarget = this.retargetForChain()) == null) {
                    this.completeChain();
                    return;
                }
                if (this.startChainEntry(chainTarget)) {
                    this.fireChainNextEvent(this.currentChain, this.chainEntryIndex, chainTarget);
                    this.launchConsecutiveConcurrentEntries(chainTarget);
                }
            }
            this.tickConcurrentSlots();
            return;
        }
        EntityLivingBase entity = this.getEntity();
        EntityLivingBase target = this.getTarget();
        AbilityPhase oldPhase = this.currentAbility.getPhase();
        boolean phaseChanged = this.currentAbility.tick();
        this.fireTickEvent(this.currentAbility, target);
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return;
        }
        if (!AbilityController.Instance.fireOnAbilityTick(this.currentAbility, entity, target, this.currentAbility.getPhase(), this.currentAbility.getCurrentTick())) {
            this.interruptCurrentAbility(null, 0.0f);
            return;
        }
        switch (this.currentAbility.getPhase()) {
            case WINDUP: {
                if (phaseChanged && oldPhase == AbilityPhase.BURST_DELAY) {
                    if (this.currentAbility.isRotationLockedDuringWindup()) {
                        this.captureLockedRotation();
                    }
                    if (this.currentAbility.isMovementLockedDuringWindup() && !this.currentAbility.hasAbilityMovement()) {
                        this.captureLockedPosition();
                    }
                    this.spawnTelegraph(this.currentAbility, target);
                    this.playAbilitySound(this.currentAbility.getWindUpSound());
                    this.playAbilityAnimation(this.currentAbility.getWindUpAnimation());
                }
                this.currentAbility.onWindUpTick(entity, target, this.currentAbility.getCurrentTick());
                break;
            }
            case ACTIVE: {
                if (phaseChanged && (oldPhase == AbilityPhase.WINDUP || oldPhase == AbilityPhase.BURST_DELAY)) {
                    for (TelegraphInstance telegraph : this.currentAbility.getTelegraphInstances()) {
                        telegraph.lockPosition();
                    }
                    if (!this.currentAbility.keepTelegraphDuringActive()) {
                        this.removeTelegraph(this.currentAbility);
                    }
                    if (this.currentAbility.isRotationLockedDuringActive()) {
                        if (!this.rotationLocked) {
                            this.captureLockedRotation();
                        }
                    } else if (this.rotationLocked) {
                        this.releaseRotationControl();
                    }
                    if (this.currentAbility.isMovementLockedDuringActive() && !this.currentAbility.hasAbilityMovement()) {
                        if (!this.positionLocked) {
                            this.captureLockedPosition();
                        }
                    } else if (this.positionLocked) {
                        this.releaseLockedPosition();
                    }
                    if (!this.currentAbility.keepTelegraphDuringActive()) {
                        this.playAbilitySound(this.currentAbility.getActiveSound());
                    }
                    this.playAbilityAnimation(this.currentAbility.getActiveAnimation());
                    this.onPreExecute(this.currentAbility, target);
                    if (this.fireExecuteEvent(this.currentAbility, target)) {
                        this.currentAbility.interrupt();
                        this.handleAbilityCompletion(target);
                        return;
                    }
                    this.currentAbility.onExecute(entity, target);
                    if (this.currentAbility.getPhase() == AbilityPhase.IDLE) {
                        this.handleAbilityCompletion(target);
                        return;
                    }
                }
                this.currentAbility.onActiveTick(entity, target, this.currentAbility.getCurrentTick());
                if (this.currentAbility instanceof AbilityDefend) {
                    Animation activeAnim;
                    AbilityDefend defend = (AbilityDefend)this.currentAbility;
                    Animation defendAnim = defend.consumeDefendAnimation();
                    if (defendAnim != null) {
                        this.playAbilityAnimation(defendAnim);
                        this.playAbilitySound(this.currentAbility.getActiveSound());
                        defend.scheduleReturnToActive(this.currentAbility.getCurrentTick(), defendAnim);
                    }
                    if (defend.shouldReturnToActiveAnimation(this.currentAbility.getCurrentTick()) && (activeAnim = this.currentAbility.getActiveAnimation()) != null) {
                        this.playAbilityAnimation(activeAnim);
                    }
                }
                if (this.currentAbility.getPhase() == AbilityPhase.IDLE) {
                    this.handleAbilityCompletion(target);
                    return;
                }
                if (this.currentAbility.isBurstEnabled() && this.currentAbility.isBurstOverlap() && this.currentAbility.getBurstIndex() < this.currentAbility.getBurstAmount() && this.currentAbility.getPhase() == AbilityPhase.ACTIVE && this.currentAbility.isReadyForBurstCompletion(this.currentAbility.getCurrentTick())) {
                    this.currentAbility.signalCompletion();
                }
                if (this.currentAbility.getPhase() == AbilityPhase.BURST_DELAY) {
                    if (this.rotationLocked) {
                        this.releaseRotationControl();
                    }
                    if (this.positionLocked) {
                        this.releaseLockedPosition();
                    }
                    this.onBurstDelayReleaseLocks();
                }
                if (this.currentAbility == null || this.currentAbility.getPhase() != AbilityPhase.ACTIVE || this.currentAbility.getCurrentTick() <= 600) break;
                this.currentAbility.cancel();
                this.handleAbilityCompletion(target);
                return;
            }
            case BURST_DELAY: {
                if (this.rotationLocked) {
                    this.releaseRotationControl();
                }
                if (!this.positionLocked) break;
                this.releaseLockedPosition();
                break;
            }
            case DAZED: {
                break;
            }
            case IDLE: {
                this.handleAbilityCompletion(target);
            }
        }
        this.onPostPhaseTick(this.currentAbility, target);
        this.tickConcurrentSlots();
    }

    protected void handleAbilityCompletion(EntityLivingBase target) {
        if (this.currentAbility == null) {
            return;
        }
        this.removeTelegraph(this.currentAbility);
        AbilityController.Instance.fireOnAbilityComplete(this.currentAbility, this.getEntity(), target, false);
        this.currentAbility.onComplete(this.getEntity(), target);
        this.fireCompleteEvent(this.currentAbility, target);
        this.releaseRotationControl();
        this.releaseLockedPosition();
        this.stopAbilityAnimation();
        if (this.currentChain != null) {
            ChainedAbilityEntry completedEntry = this.currentChain.getEntries().get(this.chainEntryIndex);
            int delay = completedEntry.getDelayTicks();
            ++this.chainEntryIndex;
            this.launchConsecutiveConcurrentEntries(target);
            if (this.chainEntryIndex < this.currentChain.getEntries().size()) {
                if (target != null && target.field_70128_L && (target = this.retargetForChain()) == null) {
                    this.completeChain();
                    return;
                }
                this.chainDelayRemaining = Math.max(1, delay);
                this.currentAbility = null;
                return;
            }
            this.completeChain();
            return;
        }
        if (this.interruptCooldownRolled) {
            this.interruptCooldownRolled = false;
        } else {
            this.rollCooldown(this.currentAbility);
        }
        this.onAbilityComplete();
    }

    protected void executeImmediate(Ability ability, EntityLivingBase target) {
        if (ability.keepTelegraphDuringActive()) {
            this.spawnTelegraph(ability, target);
        }
        this.playAbilitySound(ability.getActiveSound());
        this.playAbilityAnimation(ability.getActiveAnimation());
        if (this.fireExecuteEvent(ability, target)) {
            ability.interrupt();
            this.handleAbilityCompletion(target);
            return;
        }
        ability.onExecute(this.getEntity(), target);
        if (ability.getPhase() == AbilityPhase.IDLE) {
            this.handleAbilityCompletion(target);
        }
    }

    public void interruptCurrentAbility(DamageSource source, float damage) {
        if (this.currentAbility != null && this.currentAbility.isExecuting()) {
            this.removeTelegraph(this.currentAbility);
            AbilityController.Instance.fireOnAbilityComplete(this.currentAbility, this.getEntity(), this.getTarget(), true);
            this.fireInterruptEvent(this.currentAbility, this.getTarget(), source, damage);
            this.currentAbility.onInterrupt(this.getEntity(), source, damage);
            this.currentAbility.interrupt();
            this.stopAbilityAnimation();
            this.playAbilityAnimation(this.currentAbility.getDazedAnimation());
            this.releaseRotationControl();
            this.releaseLockedPosition();
            if (this.currentChain != null) {
                this.rollChainCooldown(this.currentChain);
                this.interruptCooldownRolled = true;
                this.fireChainInterruptEvent(this.currentChain, this.chainEntryIndex, this.getTarget(), source, damage);
                this.currentChain.clearInstanceScript();
                this.currentChain = null;
                this.chainEntryIndex = -1;
                this.chainDelayRemaining = -1;
            }
            this.interruptConcurrentSlots();
            this.onInterruptComplete();
        }
    }

    protected void onInterruptComplete() {
    }

    public void cancelCurrentAbility() {
        boolean isInChainDelay;
        boolean hasExecutingAbility = this.currentAbility != null && this.currentAbility.isExecuting();
        boolean bl = isInChainDelay = this.currentChain != null && this.chainDelayRemaining > 0;
        if (!hasExecutingAbility && !isInChainDelay) {
            return;
        }
        if (hasExecutingAbility) {
            AbilityPhase phase = this.currentAbility.getPhase();
            if (phase != AbilityPhase.WINDUP) {
                return;
            }
            this.removeTelegraph(this.currentAbility);
            AbilityController.Instance.fireOnAbilityComplete(this.currentAbility, this.getEntity(), this.getTarget(), true);
            this.fireInterruptEvent(this.currentAbility, this.getTarget(), null, 0.0f);
            this.currentAbility.onInterrupt(this.getEntity(), null, 0.0f);
            this.currentAbility.cancel();
            this.stopAbilityAnimation();
            this.releaseRotationControl();
            this.releaseLockedPosition();
        }
        if (this.currentChain != null) {
            this.rollChainCooldown(this.currentChain);
            this.currentChain.clearInstanceScript();
            this.currentChain = null;
            this.chainEntryIndex = -1;
            this.chainDelayRemaining = -1;
        } else if (this.currentAbility != null) {
            this.rollCooldown(this.currentAbility);
        }
        this.interruptConcurrentSlots();
        this.onAbilityComplete();
    }

    public void completeCurrentAbility() {
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return;
        }
        if (this.currentAbility.getPhase() != AbilityPhase.ACTIVE) {
            return;
        }
        if (!this.currentAbility.signalCompletion()) {
            return;
        }
        if (this.currentAbility.getPhase() == AbilityPhase.IDLE) {
            this.handleAbilityCompletion(this.getTarget());
        }
    }

    protected boolean startChain(ChainedAbility chain, EntityLivingBase target) {
        if (chain == null || chain.getEntries().isEmpty()) {
            return false;
        }
        this.currentChain = chain;
        this.chainEntryIndex = 0;
        this.chainDelayRemaining = -1;
        boolean started = this.startChainEntry(target);
        if (started) {
            this.fireChainStartEvent(this.currentChain, 0, target);
            this.launchConsecutiveConcurrentEntries(target);
        }
        return started;
    }

    protected boolean startChainEntry(EntityLivingBase target) {
        if (this.currentChain == null || this.chainEntryIndex < 0 || this.chainEntryIndex >= this.currentChain.getEntries().size()) {
            this.completeChain();
            return false;
        }
        ChainedAbilityEntry entry = this.currentChain.getEntries().get(this.chainEntryIndex);
        Ability ability = entry.resolve();
        if (ability == null) {
            this.completeChain();
            return false;
        }
        if (!ability.getAllowedBy().allowsNpc() && this.getEntity() instanceof EntityNPCInterface) {
            this.completeChain();
            return false;
        }
        if (!ability.getAllowedBy().allowsPlayer() && this.getEntity() instanceof EntityPlayer) {
            this.completeChain();
            return false;
        }
        if (!this.currentChain.isWindUpAll() && this.chainEntryIndex > 0) {
            ability.setWindUpTicks(0);
        }
        this.currentAbility = ability;
        ability.start(target);
        if (ability.getPhase() == AbilityPhase.ACTIVE) {
            if (ability.isRotationLockedDuringActive()) {
                this.captureLockedRotation();
            }
            if (ability.isMovementLockedDuringActive() && !ability.hasAbilityMovement()) {
                this.captureLockedPosition();
            }
            this.executeImmediate(ability, target);
        } else {
            if (ability.isRotationLockedDuringWindup()) {
                this.captureLockedRotation();
            }
            if (ability.isMovementLockedDuringWindup() && !ability.hasAbilityMovement()) {
                this.captureLockedPosition();
            }
            this.spawnTelegraph(ability, target);
            this.playAbilitySound(ability.getWindUpSound());
            this.playAbilityAnimation(ability.getWindUpAnimation());
        }
        return true;
    }

    protected void tickConcurrentSlots() {
        if (this.concurrentSlots.isEmpty()) {
            return;
        }
        EntityLivingBase entity = this.getEntity();
        EntityLivingBase target = this.getTarget();
        Iterator<ConcurrentSlot> it = this.concurrentSlots.iterator();
        while (it.hasNext()) {
            ConcurrentSlot slot = it.next();
            slot.tick(entity, target);
            if (!slot.isCompleted()) continue;
            it.remove();
        }
    }

    protected void launchConsecutiveConcurrentEntries(EntityLivingBase target) {
        ChainedAbilityEntry nextEntry;
        Ability resolved;
        if (this.currentChain == null) {
            return;
        }
        List<ChainedAbilityEntry> entries = this.currentChain.getEntries();
        while (this.chainEntryIndex + 1 < entries.size() && (resolved = (nextEntry = entries.get(this.chainEntryIndex + 1)).resolve()) != null && resolved.isConcurrentCapable() && nextEntry.isConcurrentEnabled() && nextEntry.getDelayTicks() <= 0) {
            ++this.chainEntryIndex;
            Ability concurrentCopy = AbilityController.Instance.fromNBT(resolved.writeNBT(true));
            if (concurrentCopy == null) continue;
            ConcurrentSlot slot = new ConcurrentSlot(concurrentCopy);
            this.concurrentSlots.add(slot);
            slot.start(this.getEntity(), target);
        }
    }

    protected void interruptConcurrentSlots() {
        for (ConcurrentSlot slot : this.concurrentSlots) {
            slot.interrupt();
        }
        this.concurrentSlots.clear();
    }

    protected void completeChain() {
        if (this.currentChain != null) {
            this.rollChainCooldown(this.currentChain);
            this.fireChainCompleteEvent(this.currentChain, this.chainEntryIndex, this.getTarget());
            this.currentChain.clearInstanceScript();
        }
        this.currentChain = null;
        this.chainEntryIndex = -1;
        this.chainDelayRemaining = -1;
        this.currentAbility = null;
        this.onAbilityComplete();
    }
}

