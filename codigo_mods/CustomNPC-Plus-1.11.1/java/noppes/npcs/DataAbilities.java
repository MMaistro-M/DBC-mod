/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.DamageSource
 */
package noppes.npcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityAction;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.controllers.data.ability.data.entry.AbilityToggleEntry;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.enums.RotationMode;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.network.packets.data.telegraph.TelegraphRemovePacket;
import kamkeel.npcs.network.packets.data.telegraph.TelegraphSpawnPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import noppes.npcs.AbstractDataAbilities;
import noppes.npcs.EventHooks;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.entity.EntityNPCInterface;

public class DataAbilities
extends AbstractDataAbilities {
    private final EntityNPCInterface npc;
    private final Random random = new Random();
    private List<AbilityAction> actionSlots = new ArrayList<AbilityAction>();
    public boolean enabled = true;
    public int minCooldown = 80;
    public int maxCooldown = 200;
    private transient EntityLivingBase lastTarget;
    private transient List<Long> recentHitTimes = new ArrayList<Long>();
    private transient float lockedYawHead = 0.0f;
    private transient float lockedRenderYawOffset = 0.0f;
    private transient boolean hitScanActive = false;
    private transient EntityLivingBase hitScanTarget = null;
    private transient float currentTrackSpeed = 0.0f;
    private transient float trackedYaw = 0.0f;
    private transient float trackedPitch = 0.0f;
    private transient boolean trackLerpedThisTick = false;
    private transient boolean preserveTrackedRotation = false;
    private static final int ROTATION_CONTROLLED_FLAG = 16;
    private static final int POSITION_LOCKED_FLAG = 32;
    private static final double DEG_TO_RAD = Math.PI / 180;
    private static final double RAD_TO_DEG = 57.29577951308232;

    public DataAbilities(EntityNPCInterface npc) {
        this.npc = npc;
    }

    @Override
    protected EntityLivingBase getEntity() {
        return this.npc;
    }

    @Override
    protected EntityLivingBase getTarget() {
        return this.lastTarget != null ? this.lastTarget : this.npc.func_70638_az();
    }

    @Override
    protected long getWorldTime() {
        return this.npc.field_70170_p.func_82737_E();
    }

    @Override
    protected void spawnTelegraph(Ability ability, EntityLivingBase target) {
        List<TelegraphInstance> telegraphs = ability.createTelegraphs((EntityLivingBase)this.npc, target);
        if (!telegraphs.isEmpty()) {
            ability.setTelegraphInstances(telegraphs);
            for (TelegraphInstance telegraph : telegraphs) {
                TelegraphSpawnPacket.sendToTracking(telegraph, (Entity)this.npc);
            }
        }
    }

    @Override
    protected void removeTelegraph(Ability ability) {
        List<TelegraphInstance> telegraphs = ability.getTelegraphInstances();
        for (TelegraphInstance telegraph : telegraphs) {
            TelegraphRemovePacket.sendToTracking(telegraph.getInstanceId(), (Entity)this.npc);
        }
        ability.setTelegraphInstances(null);
    }

    @Override
    protected void setAnimationData(Animation animation) {
        this.npc.display.animationData.setEnabled(true);
        this.npc.display.animationData.setAnimation(animation);
        this.npc.display.animationData.updateClient();
    }

    @Override
    protected void clearAnimationData() {
        this.npc.display.animationData.setAnimation(null);
        this.npc.display.animationData.updateClient();
    }

    @Override
    protected void playAbilitySound(String sound) {
        if (sound != null && !sound.isEmpty()) {
            this.npc.field_70170_p.func_72956_a((Entity)this.npc, sound, 1.0f, 1.0f);
        }
    }

    @Override
    protected void captureLockedRotation() {
        this.lockedYaw = this.npc.field_70177_z;
        this.lockedYawHead = this.npc.field_70759_as;
        this.lockedRenderYawOffset = this.npc.field_70761_aq;
        this.lockedPitch = this.npc.field_70125_A;
        this.rotationLocked = true;
        this.npc.setBoolFlag(true, 16);
    }

    @Override
    protected void rollCooldown(Ability ability) {
        int baseCooldown = this.minCooldown;
        if (this.maxCooldown > this.minCooldown) {
            baseCooldown = this.minCooldown + this.random.nextInt(this.maxCooldown - this.minCooldown + 1);
        }
        this.cooldownEndTime = this.npc.field_70170_p.func_82737_E() + (long)baseCooldown + (long)ability.getCooldownTicks();
        if (ability.isPerAbilityCooldown() && ability.getCooldownTicks() > 0) {
            long endTime = this.npc.field_70170_p.func_82737_E() + (long)ability.getCooldownTicks();
            this.setPerAbilityCooldown(ability.getName(), endTime, ability.getCooldownTicks());
        }
    }

    @Override
    protected void rollChainCooldown(ChainedAbility chain) {
        int baseCooldown = this.minCooldown;
        if (this.maxCooldown > this.minCooldown) {
            baseCooldown = this.minCooldown + this.random.nextInt(this.maxCooldown - this.minCooldown + 1);
        }
        this.cooldownEndTime = this.npc.field_70170_p.func_82737_E() + (long)baseCooldown + (long)chain.getCooldownTicks();
    }

    @Override
    protected EntityLivingBase retargetForChain() {
        return this.npc.func_70638_az();
    }

    @Override
    protected void onAbilityComplete() {
        this.currentAbility = null;
        this.lastTarget = null;
    }

    @Override
    protected void onPreExecute(Ability ability, EntityLivingBase target) {
        if (ability.isHitScanForCurrentPhase() && target != null) {
            float speed = ability.getTrackSpeed();
            if (speed <= 0.0f) {
                this.faceTarget(target);
            } else {
                this.faceTarget(target, speed);
            }
        }
    }

    @Override
    protected void onPostPhaseTick(Ability ability, EntityLivingBase target) {
        this.trackLerpedThisTick = false;
        if (ability != null && ability.isExecuting() && target != null) {
            if (ability.isHitScanForCurrentPhase()) {
                this.enableHitScan(target, ability.getTrackSpeed());
            } else {
                if (this.hitScanActive) {
                    this.releaseRotationControl();
                }
                if (ability.getRotationMode() == RotationMode.FREE) {
                    this.npc.func_70671_ap().func_75651_a((Entity)target, 30.0f, 30.0f);
                }
            }
        } else if (this.hitScanActive) {
            this.releaseRotationControl();
        }
        if (ability != null && ability.isExecuting()) {
            this.applyMovementControl();
        }
    }

    @Override
    protected void onBurstDelayReleaseLocks() {
        if (this.hitScanActive) {
            this.preserveTrackedRotation = true;
            this.releaseRotationControl();
        }
    }

    @Override
    protected void onPositionLockChanged(boolean locked) {
        this.npc.setBoolFlag(locked, 32);
    }

    @Override
    protected void onRotationLockChanged(boolean locked) {
        if (!locked) {
            this.hitScanActive = false;
            this.hitScanTarget = null;
        }
        this.npc.setBoolFlag(locked, 16);
    }

    public void tick() {
        if (this.npc.field_70170_p.field_72995_K) {
            return;
        }
        if (!this.enabled || this.npc.isKilled()) {
            if (this.currentAbility != null) {
                this.removeTelegraph(this.currentAbility);
                this.stopAbilityAnimation();
                this.releaseRotationControl();
                this.releaseLockedPosition();
                this.currentAbility = null;
                this.lastTarget = null;
            } else {
                if (this.rotationLocked || this.hitScanActive) {
                    this.releaseRotationControl();
                }
                if (this.positionLocked) {
                    this.releaseLockedPosition();
                }
            }
            if (this.currentChain != null) {
                this.currentChain.clearInstanceScript();
            }
            this.currentChain = null;
            this.chainEntryIndex = -1;
            this.chainDelayRemaining = -1;
            this.interruptConcurrentSlots();
            return;
        }
        if (!(this.currentAbility != null && this.currentAbility.isExecuting() || this.chainDelayRemaining > 0)) {
            if (this.rotationLocked || this.hitScanActive) {
                this.releaseRotationControl();
            }
            if (this.positionLocked) {
                this.releaseLockedPosition();
            }
        }
        this.tickActiveToggles();
        if (this.chainDelayRemaining > 0 || this.currentAbility != null && this.currentAbility.isExecuting()) {
            this.tickCurrentAbility();
        }
    }

    private void applyMovementControl() {
        if (this.currentAbility == null) {
            return;
        }
        if (this.currentAbility.isMovementLockedForCurrentPhase()) {
            this.npc.func_70661_as().func_75499_g();
            if (!this.currentAbility.hasAbilityMovement()) {
                this.npc.field_70159_w = 0.0;
                this.npc.field_70179_y = 0.0;
            }
        }
    }

    public boolean trySelectAndStart(EntityLivingBase target) {
        if (!this.canSelectAbility()) {
            return false;
        }
        ArrayList<IAbilityAction> eligible = new ArrayList<IAbilityAction>();
        ArrayList<Integer> weights = new ArrayList<Integer>();
        int totalWeight = 0;
        for (AbilityAction slot : this.actionSlots) {
            IAbilityAction action = slot.getAction();
            if (action == null || !this.isActionEligible(slot, action, target)) continue;
            eligible.add(action);
            weights.add(action.getWeight());
            totalWeight += action.getWeight();
        }
        if (eligible.isEmpty() || totalWeight <= 0) {
            return false;
        }
        int roll = this.random.nextInt(totalWeight);
        int cumulative = 0;
        IAbilityAction selected = null;
        for (int i = 0; i < eligible.size(); ++i) {
            if (roll >= (cumulative += ((Integer)weights.get(i)).intValue())) continue;
            selected = (IAbilityAction)eligible.get(i);
            break;
        }
        if (selected == null) {
            selected = (IAbilityAction)eligible.get(eligible.size() - 1);
        }
        this.lastTarget = target;
        if (selected.isChain()) {
            return this.startChain((ChainedAbility)selected, target);
        }
        return this.startAbility((Ability)selected, target);
    }

    public boolean canSelectAbility() {
        if (!this.enabled || this.actionSlots.isEmpty()) {
            return false;
        }
        if (this.currentAbility != null && this.currentAbility.isExecuting()) {
            return false;
        }
        if (this.isExecutingChain()) {
            return false;
        }
        return this.npc.field_70170_p.func_82737_E() >= this.cooldownEndTime;
    }

    private boolean isActionEligible(AbilityAction slot, IAbilityAction action, EntityLivingBase target) {
        Ability ab;
        float distance;
        if (!action.getAllowedBy().allowsNpc()) {
            return false;
        }
        if (!slot.isSlotEnabled()) {
            return false;
        }
        if (!action.isChain() && ((Ability)action).isExecuting()) {
            return false;
        }
        if (action.isChain() && ((ChainedAbility)action).getEntries().isEmpty()) {
            return false;
        }
        if (target != null && ((distance = this.npc.func_70032_d((Entity)target)) < action.getMinRange() || distance > action.getMaxRange())) {
            return false;
        }
        if (!action.checkConditions((EntityLivingBase)this.npc, target)) {
            return false;
        }
        return action.isChain() || !(ab = (Ability)action).isPerAbilityCooldown() || !this.isOnPerAbilityCooldown(ab.getName());
    }

    private boolean startAbility(Ability ability, EntityLivingBase target) {
        if (EventHooks.onAbilityStart(ability, (EntityLivingBase)this.npc, target)) {
            return false;
        }
        if (!AbilityController.Instance.fireOnAbilityStart(ability, (EntityLivingBase)this.npc, target)) {
            return false;
        }
        this.currentAbility = ability;
        this.lastTarget = target;
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

    public boolean onDamage(DamageSource source, float amount) {
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return false;
        }
        if (this.currentAbility.isInvulnerableForCurrentPhase()) {
            return false;
        }
        this.recordHit();
        if (this.currentAbility.canInterrupt(source)) {
            this.interruptCurrentAbility(source, amount);
            return true;
        }
        return false;
    }

    private void recordHit() {
        long currentTime = this.npc.field_70170_p.func_82737_E();
        this.recentHitTimes.add(currentTime);
        if (this.recentHitTimes.size() > 50) {
            long cutoff = currentTime - 6000L;
            this.recentHitTimes.removeIf(time -> time < cutoff);
        }
    }

    public int getRecentHitCount(int withinTicks) {
        long currentTime = this.npc.field_70170_p.func_82737_E();
        long cutoff = currentTime - (long)withinTicks;
        int count = 0;
        this.recentHitTimes.removeIf(time -> time < cutoff);
        for (Long time2 : this.recentHitTimes) {
            if (time2 < cutoff) continue;
            ++count;
        }
        return count;
    }

    public void stopCurrentAbility() {
        if (this.currentAbility != null) {
            this.removeTelegraph(this.currentAbility);
            this.stopAbilityAnimation();
            this.releaseRotationControl();
            this.releaseLockedPosition();
            this.currentAbility.interrupt();
            this.currentAbility = null;
            this.lastTarget = null;
        }
        if (this.currentChain != null) {
            this.currentChain.clearInstanceScript();
        }
        this.currentChain = null;
        this.chainEntryIndex = -1;
        this.chainDelayRemaining = -1;
    }

    public boolean forceStartAbility(Ability ability, EntityLivingBase target) {
        if (ability == null || this.npc.field_70170_p.field_72995_K) {
            return false;
        }
        if (!ability.getAllowedBy().allowsNpc()) {
            return false;
        }
        this.stopCurrentAbility();
        ability.reset();
        return this.startAbility(ability, target);
    }

    public boolean executeAbility(String key, EntityLivingBase target) {
        if (key == null || key.isEmpty() || this.npc.field_70170_p.field_72995_K) {
            return false;
        }
        Ability resolved = AbilityController.Instance.resolveAbility(key);
        if (resolved == null) {
            return false;
        }
        return this.forceStartAbility(resolved, target);
    }

    public EntityNPCInterface getNpc() {
        return this.npc;
    }

    public void reset() {
        this.stopCurrentAbility();
        this.clearActiveToggles();
        this.interruptConcurrentSlots();
        this.resetAllPerAbilityCooldowns();
        this.rollCooldownOnReset();
        for (Ability ability : this.getAbilities()) {
            ability.reset();
        }
    }

    private void rollCooldownOnReset() {
        int baseCooldown = this.minCooldown;
        if (this.maxCooldown > this.minCooldown) {
            baseCooldown = this.minCooldown + this.random.nextInt(this.maxCooldown - this.minCooldown + 1);
        }
        this.cooldownEndTime = this.npc.field_70170_p.func_82737_E() + (long)baseCooldown;
    }

    public List<AbilityAction> getAbilityActions() {
        return this.actionSlots;
    }

    public List<Ability> getAbilities() {
        ArrayList<Ability> resolved = new ArrayList<Ability>();
        for (AbilityAction slot : this.actionSlots) {
            Ability a = slot.getAbility();
            if (a == null) continue;
            resolved.add(a);
        }
        return resolved;
    }

    public void addAbility(Ability ability) {
        this.actionSlots.add(AbilityAction.inline(ability));
    }

    public void addAbilityReference(String key) {
        this.actionSlots.add(AbilityAction.abilityReference(key));
    }

    public void addChainReference(String name) {
        if (name != null && !name.isEmpty()) {
            for (AbilityAction slot : this.actionSlots) {
                if (!slot.isChainReference() || !name.equals(slot.getReferenceId())) continue;
                return;
            }
            this.actionSlots.add(AbilityAction.chainReference(name));
        }
    }

    public void removeAction(int index) {
        if (index >= 0 && index < this.actionSlots.size()) {
            this.actionSlots.remove(index);
        }
    }

    public void removeAbility(int index) {
        this.removeAction(index);
    }

    public void removeAbility(String id) {
        this.actionSlots.removeIf(slot -> {
            if (slot.isAbilityReference()) {
                return slot.getReferenceId().equals(id);
            }
            if (slot.isChainReference()) {
                return false;
            }
            Ability a = slot.getAbility();
            return a != null && a.getId().equals(id);
        });
    }

    public Ability getAbility(String id) {
        for (AbilityAction slot : this.actionSlots) {
            Ability a = slot.getAbility();
            if (a == null || !a.getId().equals(id)) continue;
            return a;
        }
        return null;
    }

    public boolean isSlotReference(int index) {
        if (index < 0 || index >= this.actionSlots.size()) {
            return false;
        }
        return this.actionSlots.get(index).isReference();
    }

    public boolean convertToInline(int index) {
        if (index < 0 || index >= this.actionSlots.size()) {
            return false;
        }
        return this.actionSlots.get(index).convertToInline();
    }

    public void clearAbilities() {
        this.actionSlots.clear();
    }

    public boolean isEmpty() {
        return this.actionSlots.isEmpty();
    }

    public boolean isAbilityControllingMovement() {
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return false;
        }
        AbilityPhase phase = this.currentAbility.getPhase();
        switch (phase) {
            case WINDUP: {
                return this.currentAbility.isMovementLockedDuringWindup();
            }
            case ACTIVE: {
                return this.currentAbility.isMovementLockedDuringActive() || this.currentAbility.hasAbilityMovement();
            }
            case DAZED: {
                return true;
            }
        }
        return false;
    }

    public boolean shouldLockLookDirection() {
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return false;
        }
        if (this.currentAbility.getPhase() != AbilityPhase.ACTIVE) {
            return false;
        }
        return this.currentAbility.isMovementLockedDuringActive();
    }

    public boolean shouldBlockAttack() {
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return false;
        }
        AbilityPhase phase = this.currentAbility.getPhase();
        return phase == AbilityPhase.WINDUP || phase == AbilityPhase.ACTIVE;
    }

    private void enableHitScan(EntityLivingBase target, float trackSpeed) {
        if (!this.hitScanActive) {
            this.hitScanActive = true;
            this.npc.setBoolFlag(true, 16);
            if (!this.preserveTrackedRotation) {
                this.trackedYaw = this.npc.field_70759_as;
                this.trackedPitch = this.npc.field_70125_A;
            }
            this.preserveTrackedRotation = false;
        }
        this.hitScanTarget = target;
        this.currentTrackSpeed = trackSpeed;
    }

    private void faceTarget(EntityLivingBase target, float trackSpeed) {
        double dx = target.field_70165_t - this.npc.field_70165_t;
        double dz = target.field_70161_v - this.npc.field_70161_v;
        double dy = target.field_70163_u + (double)target.func_70047_e() * 0.5 - (this.npc.field_70163_u + (double)this.npc.func_70047_e());
        double distXZ = Math.sqrt(dx * dx + dz * dz);
        float targetYaw = (float)(Math.atan2(-dx, dz) * 57.29577951308232);
        float targetPitch = (float)(-Math.atan2(dy, distXZ) * 57.29577951308232);
        if (trackSpeed <= 0.0f) {
            this.trackedYaw = targetYaw;
            this.trackedPitch = targetPitch;
            this.npc.field_70177_z = targetYaw;
            this.npc.field_70759_as = targetYaw;
            this.npc.field_70761_aq = targetYaw;
            this.npc.field_70125_A = targetPitch;
            this.npc.field_70126_B = targetYaw;
            this.npc.field_70758_at = targetYaw;
            this.npc.field_70760_ar = targetYaw;
            this.npc.field_70127_C = targetPitch;
        } else {
            if (!this.trackLerpedThisTick) {
                float pitchDiff;
                this.trackLerpedThisTick = true;
                double dist = Math.max(1.0, Math.sqrt(dx * dx + dy * dy + dz * dz));
                float blocksPerTick = trackSpeed / 20.0f;
                float maxAngularSpeed = (float)(Math.atan2(blocksPerTick, dist) * 57.29577951308232);
                float yawDiff = DataAbilities.wrapAngle(targetYaw - this.trackedYaw);
                float totalAngle = (float)Math.sqrt(yawDiff * yawDiff + (pitchDiff = targetPitch - this.trackedPitch) * pitchDiff);
                if (totalAngle > maxAngularSpeed) {
                    float scale = maxAngularSpeed / totalAngle;
                    yawDiff *= scale;
                    pitchDiff *= scale;
                }
                this.trackedYaw += yawDiff;
                this.trackedPitch += pitchDiff;
            }
            this.npc.field_70126_B = this.npc.field_70177_z;
            this.npc.field_70758_at = this.npc.field_70759_as;
            this.npc.field_70760_ar = this.npc.field_70761_aq;
            this.npc.field_70127_C = this.npc.field_70125_A;
            this.npc.field_70177_z = this.trackedYaw;
            this.npc.field_70759_as = this.trackedYaw;
            this.npc.field_70761_aq = this.trackedYaw;
            this.npc.field_70125_A = this.trackedPitch;
        }
    }

    private void faceTarget(EntityLivingBase target) {
        this.faceTarget(target, 0.0f);
    }

    private static float wrapAngle(float angle) {
        if ((angle %= 360.0f) >= 180.0f) {
            angle -= 360.0f;
        }
        if (angle < -180.0f) {
            angle += 360.0f;
        }
        return angle;
    }

    public void applyRotationControl() {
        if (this.npc.field_70170_p.field_72995_K) {
            if (!this.npc.getBoolFlag(16)) {
                this.rotationLocked = false;
                return;
            }
            this.npc.field_70126_B = this.npc.field_70177_z;
            this.npc.field_70758_at = this.npc.field_70759_as;
            this.npc.field_70761_aq = this.npc.field_70759_as;
            this.npc.field_70760_ar = this.npc.field_70759_as;
            this.npc.field_70127_C = this.npc.field_70125_A;
            return;
        }
        if (this.hitScanActive && this.hitScanTarget != null && !this.hitScanTarget.field_70128_L) {
            this.faceTarget(this.hitScanTarget, this.currentTrackSpeed);
        } else if (this.rotationLocked) {
            this.npc.field_70177_z = this.lockedYaw;
            this.npc.field_70759_as = this.lockedYawHead;
            this.npc.field_70761_aq = this.lockedRenderYawOffset;
            this.npc.field_70125_A = this.lockedPitch;
            this.npc.field_70126_B = this.lockedYaw;
            this.npc.field_70758_at = this.lockedYawHead;
            this.npc.field_70760_ar = this.lockedRenderYawOffset;
            this.npc.field_70127_C = this.lockedPitch;
        }
    }

    public boolean isRotationLocked() {
        return this.rotationLocked || this.hitScanActive;
    }

    public void applyLockedPosition() {
        if (this.npc.field_70170_p.field_72995_K) {
            boolean flagActive = this.npc.getBoolFlag(32);
            if (!flagActive) {
                this.positionLocked = false;
                return;
            }
            if (!this.positionLocked) {
                this.lockedPosX = this.npc.field_70165_t;
                this.lockedPosY = this.npc.field_70163_u;
                this.lockedPosZ = this.npc.field_70161_v;
                this.positionLocked = true;
            }
            this.npc.func_70107_b(this.lockedPosX, this.lockedPosY, this.lockedPosZ);
            this.npc.field_70169_q = this.lockedPosX;
            this.npc.field_70167_r = this.lockedPosY;
            this.npc.field_70166_s = this.lockedPosZ;
            this.npc.field_70159_w = 0.0;
            this.npc.field_70181_x = 0.0;
            this.npc.field_70179_y = 0.0;
            return;
        }
        if (!this.positionLocked) {
            return;
        }
        this.npc.func_70107_b(this.lockedPosX, this.lockedPosY, this.lockedPosZ);
        this.npc.field_70169_q = this.lockedPosX;
        this.npc.field_70167_r = this.lockedPosY;
        this.npc.field_70166_s = this.lockedPosZ;
        this.npc.field_70159_w = 0.0;
        this.npc.field_70181_x = 0.0;
        this.npc.field_70179_y = 0.0;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74757_a("AbilitiesEnabled", this.enabled);
        compound.func_74768_a("AbilityMinCooldown", this.minCooldown);
        compound.func_74768_a("AbilityMaxCooldown", this.maxCooldown);
        NBTTagList actionList = new NBTTagList();
        for (AbilityAction slot : this.actionSlots) {
            actionList.func_74742_a((NBTBase)slot.writeNBT(true));
        }
        compound.func_74782_a("AbilityActions", (NBTBase)actionList);
        NBTTagList toggleList = new NBTTagList();
        for (Map.Entry entry : this.activeToggles.entrySet()) {
            NBTTagCompound toggleNbt = new NBTTagCompound();
            toggleNbt.func_74778_a("Key", (String)entry.getKey());
            toggleNbt.func_74768_a("State", ((AbilityToggleEntry)entry.getValue()).getState());
            toggleList.func_74742_a((NBTBase)toggleNbt);
        }
        compound.func_74782_a("ActiveToggles", (NBTBase)toggleList);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        AbilityAction slot;
        int i;
        this.enabled = compound.func_74764_b("AbilitiesEnabled") ? compound.func_74767_n("AbilitiesEnabled") : true;
        this.minCooldown = compound.func_74762_e("AbilityMinCooldown");
        this.maxCooldown = compound.func_74762_e("AbilityMaxCooldown");
        this.actionSlots.clear();
        if (compound.func_74764_b("AbilityActions")) {
            NBTTagList actionList = compound.func_150295_c("AbilityActions", 10);
            for (i = 0; i < actionList.func_74745_c(); ++i) {
                slot = AbilityAction.fromNBT(actionList.func_150305_b(i));
                if (slot == null) continue;
                this.actionSlots.add(slot);
            }
        } else {
            if (compound.func_74764_b("Abilities")) {
                NBTTagList abilityList = compound.func_150295_c("Abilities", 10);
                for (i = 0; i < abilityList.func_74745_c(); ++i) {
                    slot = AbilityAction.fromNBT(abilityList.func_150305_b(i));
                    if (slot == null) continue;
                    this.actionSlots.add(slot);
                }
            }
            if (compound.func_74764_b("ChainedAbilities")) {
                NBTTagList chainList = compound.func_150295_c("ChainedAbilities", 8);
                for (i = 0; i < chainList.func_74745_c(); ++i) {
                    String ref = chainList.func_150307_f(i);
                    if (ref == null || ref.isEmpty()) continue;
                    this.actionSlots.add(AbilityAction.chainReference(ref));
                }
            }
        }
        this.activeToggles.clear();
        if (compound.func_74764_b("ActiveToggles")) {
            NBTTagList toggleNbt = compound.func_150295_c("ActiveToggles", 10);
            for (i = 0; i < toggleNbt.func_74745_c(); ++i) {
                NBTTagCompound entry = toggleNbt.func_150305_b(i);
                String key = entry.func_74779_i("Key");
                int state = entry.func_74764_b("State") ? entry.func_74762_e("State") : 1;
                this.setToggleEntryDirect(key, state);
            }
        }
    }
}

