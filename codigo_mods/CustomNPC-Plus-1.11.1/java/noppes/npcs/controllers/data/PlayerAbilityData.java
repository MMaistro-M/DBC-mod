/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.DamageSource
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.controllers.data.ability.data.entry.AbilityToggleEntry;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.UpdateAnimationsPacket;
import kamkeel.npcs.network.packets.data.ability.PlayerAbilityStatePacket;
import kamkeel.npcs.network.packets.data.telegraph.TelegraphRemovePacket;
import kamkeel.npcs.network.packets.data.telegraph.TelegraphSpawnPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import noppes.npcs.AbstractDataAbilities;
import noppes.npcs.EventHooks;
import noppes.npcs.LogWriter;
import noppes.npcs.api.ability.IPlayerAbilityData;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.PlayerData;

public class PlayerAbilityData
extends AbstractDataAbilities
implements IPlayerAbilityData {
    private final PlayerData playerData;
    private List<String> unlockedAbilities = new ArrayList<String>();
    private int selectedIndex = 0;
    private boolean playingAbilityAnimation = false;
    private transient String currentAbilityKey;
    private transient EntityLivingBase currentTarget;
    private transient byte lastSyncedFlags = 0;
    private transient long lastAbilityActivationTime = -1L;
    private static final int CANCEL_WINDOW_TICKS = 10;
    private transient double lastAbilityTickX;
    private transient double lastAbilityTickY;
    private transient double lastAbilityTickZ;
    private transient boolean trackingAbilityPosition = false;
    private static final double TELEPORT_THRESHOLD_SQ = 2304.0;
    public static final String CHAIN_PREFIX = "chain:";

    public PlayerAbilityData(PlayerData playerData) {
        this.playerData = playerData;
    }

    @Override
    protected EntityLivingBase getEntity() {
        return this.playerData.player;
    }

    @Override
    protected EntityLivingBase getTarget() {
        return this.currentTarget;
    }

    @Override
    protected long getWorldTime() {
        EntityPlayer player = this.playerData.player;
        return player != null ? player.field_70170_p.func_82737_E() : 0L;
    }

    @Override
    protected void spawnTelegraph(Ability ability, EntityLivingBase target) {
        EntityPlayer player = this.playerData.player;
        List<TelegraphInstance> telegraphs = ability.createTelegraphs((EntityLivingBase)player, target);
        if (!telegraphs.isEmpty()) {
            ability.setTelegraphInstances(telegraphs);
            if (player instanceof EntityPlayerMP) {
                for (TelegraphInstance telegraph : telegraphs) {
                    TelegraphSpawnPacket.sendToTracking(telegraph, (Entity)player);
                }
            }
        }
    }

    @Override
    protected void removeTelegraph(Ability ability) {
        EntityPlayer player = this.playerData.player;
        List<TelegraphInstance> telegraphs = ability.getTelegraphInstances();
        for (TelegraphInstance telegraph : telegraphs) {
            if (!(player instanceof EntityPlayerMP)) continue;
            TelegraphRemovePacket.sendToTracking(telegraph.getInstanceId(), (Entity)player);
        }
        ability.setTelegraphInstances(null);
    }

    @Override
    protected void setAnimationData(Animation animation) {
        this.playerData.animationData.setEnabled(true);
        this.playerData.animationData.setAnimation(animation);
        this.playerData.animationData.updateClient();
        this.playingAbilityAnimation = true;
    }

    @Override
    protected void clearAnimationData() {
        this.playerData.animationData.setAnimation(null);
        this.playerData.animationData.updateClient();
        EntityPlayer player = this.playerData.player;
        if (player instanceof EntityPlayerMP) {
            NBTTagCompound data = new NBTTagCompound();
            data.func_74757_a("AllowAnimation", this.playerData.animationData.enabled());
            PacketHandler.Instance.sendToPlayer(new UpdateAnimationsPacket(data, player.func_70005_c_()), (EntityPlayerMP)player);
        }
        this.playingAbilityAnimation = false;
    }

    @Override
    protected void playAbilitySound(String sound) {
        EntityPlayer player;
        if (sound != null && !sound.isEmpty() && (player = this.playerData.player) != null) {
            player.field_70170_p.func_72956_a((Entity)player, sound, 1.0f, 1.0f);
        }
    }

    @Override
    protected void captureLockedRotation() {
        EntityPlayer player = this.playerData.player;
        this.lockedYaw = player.field_70177_z;
        this.lockedPitch = player.field_70125_A;
        this.rotationLocked = true;
    }

    @Override
    protected void rollCooldown(Ability ability) {
        if (ability.isIgnoreCooldown()) {
            return;
        }
        int duration = ability.getCooldownTicks();
        long endTime = this.getWorldTime() + (long)duration;
        if (ability.isPerAbilityCooldown()) {
            if (this.currentAbilityKey != null) {
                this.setPerAbilityCooldown(this.currentAbilityKey, endTime, duration);
            }
        } else {
            this.cooldownEndTime = endTime;
            this.globalCooldownDuration = duration;
        }
    }

    @Override
    protected void rollChainCooldown(ChainedAbility chain) {
        int duration = chain.getCooldownTicks();
        this.cooldownEndTime = this.getWorldTime() + (long)duration;
        this.globalCooldownDuration = duration;
    }

    @Override
    protected void onAbilityComplete() {
        this.currentAbility = null;
        this.currentAbilityKey = null;
        this.currentTarget = null;
        this.lastAbilityActivationTime = -1L;
        this.syncAbilityStateClear(this.playerData.player);
    }

    @Override
    protected void onInterruptComplete() {
        if (!this.interruptCooldownRolled && this.currentAbility != null) {
            this.rollCooldown(this.currentAbility);
        }
        this.interruptCooldownRolled = false;
        this.stopAbilityAnimation();
        this.currentAbility = null;
        this.currentAbilityKey = null;
        this.currentTarget = null;
        this.lastAbilityActivationTime = -1L;
        this.syncAbilityStateClear(this.playerData.player);
    }

    public void tick(EntityPlayer player) {
        boolean hasActiveAbility;
        if (player.field_70170_p.field_72995_K || player.field_70128_L) {
            return;
        }
        this.tickActiveToggles();
        boolean bl = hasActiveAbility = this.chainDelayRemaining > 0 || this.currentAbility != null && this.currentAbility.isExecuting();
        if (hasActiveAbility) {
            double dz;
            double dy;
            double dx;
            if (this.trackingAbilityPosition && (dx = player.field_70165_t - this.lastAbilityTickX) * dx + (dy = player.field_70163_u - this.lastAbilityTickY) * dy + (dz = player.field_70161_v - this.lastAbilityTickZ) * dz > 2304.0) {
                this.trackingAbilityPosition = false;
                this.resetOnTeleport();
                return;
            }
            this.lastAbilityTickX = player.field_70165_t;
            this.lastAbilityTickY = player.field_70163_u;
            this.lastAbilityTickZ = player.field_70161_v;
            this.trackingAbilityPosition = true;
            this.tickCurrentAbility();
            this.applyRotationControl(player);
            this.applyPositionLock(player);
            this.syncAbilityStateIfNeeded(player);
        } else {
            this.trackingAbilityPosition = false;
            if (this.rotationLocked || this.positionLocked) {
                this.releaseRotationControl();
                this.releaseLockedPosition();
                this.syncAbilityStateClear(player);
            }
        }
    }

    public boolean activateAbility(EntityPlayer player) {
        if (player.field_70170_p.field_72995_K) {
            return false;
        }
        if (this.unlockedAbilities.isEmpty()) {
            return false;
        }
        if (this.selectedIndex < 0 || this.selectedIndex >= this.unlockedAbilities.size()) {
            return false;
        }
        String key = this.unlockedAbilities.get(this.selectedIndex);
        return this.activateAbility(player, key);
    }

    private IAbilityAction resolveActionKey(String key) {
        if (key.startsWith(CHAIN_PREFIX)) {
            return AbilityController.Instance.resolveChainedAbility(key.substring(CHAIN_PREFIX.length()));
        }
        return AbilityController.Instance.resolveAbility(key);
    }

    public boolean activateAbility(EntityPlayer player, String key) {
        if (player.field_70170_p.field_72995_K || key == null || key.isEmpty()) {
            return false;
        }
        if (!AbilityController.Instance.canPlayerActivate(player)) {
            return false;
        }
        if (this.currentAbility != null && this.currentAbility.isExecuting()) {
            return false;
        }
        if (this.isExecutingChain()) {
            return false;
        }
        IAbilityAction action = this.resolveActionKey(key);
        if (action == null) {
            return false;
        }
        if (!action.getAllowedBy().allowsPlayer()) {
            return false;
        }
        if (!action.checkConditionsForPlayer((EntityLivingBase)player)) {
            return false;
        }
        if (!action.isAvailableFor(player)) {
            return false;
        }
        if (action.isChain()) {
            if (this.isOnCooldown()) {
                return false;
            }
            this.currentAbilityKey = key;
            this.currentTarget = null;
            this.lastAbilityActivationTime = this.getWorldTime();
            return this.startChain((ChainedAbility)action, null);
        }
        Ability ability = (Ability)action;
        if (ability.isToggleable()) {
            this.toggleAbility(key);
            return true;
        }
        if (!ability.isIgnoreCooldown() && (ability.isPerAbilityCooldown() ? this.isOnPerAbilityCooldown(key) : this.isOnCooldown())) {
            return false;
        }
        if (!AbilityController.Instance.fireOnAbilityStart(ability, (EntityLivingBase)player, null)) {
            return false;
        }
        if (EventHooks.onAbilityStart(ability, (EntityLivingBase)player, null)) {
            return false;
        }
        this.currentAbility = ability;
        this.currentAbilityKey = key;
        this.currentTarget = null;
        this.lastAbilityActivationTime = this.getWorldTime();
        ability.start(null);
        if (ability.getPhase() == AbilityPhase.ACTIVE) {
            if (ability.isRotationLockedDuringActive()) {
                this.captureLockedRotation();
            }
            if (ability.isMovementLockedDuringActive() && !ability.hasAbilityMovement()) {
                this.captureLockedPosition();
            }
            this.executeImmediate(ability, this.currentTarget);
        } else {
            if (ability.isRotationLockedDuringWindup()) {
                this.captureLockedRotation();
            }
            if (ability.isMovementLockedDuringWindup() && !ability.hasAbilityMovement()) {
                this.captureLockedPosition();
            }
            this.spawnTelegraph(ability, null);
            this.playAbilitySound(ability.getWindUpSound());
            this.playAbilityAnimation(ability.getWindUpAnimation());
        }
        this.syncToClient();
        return true;
    }

    public void syncToClient() {
        EntityPlayer player = this.playerData.player;
        if (player instanceof EntityPlayerMP) {
            SyncController.syncAbilities((EntityPlayerMP)player);
        }
    }

    public void syncCooldownToClient() {
        EntityPlayer player = this.playerData.player;
        if (player instanceof EntityPlayerMP) {
            SyncController.syncAbilityCooldowns((EntityPlayerMP)player);
        }
    }

    @Override
    protected void onToggleStateChanged(String key, boolean active, int state) {
    }

    @Override
    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    @Override
    public void setSelectedIndex(int index) {
        if (index == -1 || index >= 0 && index < this.unlockedAbilities.size()) {
            this.selectedIndex = index;
        }
    }

    @Override
    public void selectNext() {
        if (this.unlockedAbilities.isEmpty()) {
            return;
        }
        this.selectedIndex = (this.selectedIndex + 1) % this.unlockedAbilities.size();
    }

    @Override
    public void selectPrevious() {
        if (this.unlockedAbilities.isEmpty()) {
            return;
        }
        this.selectedIndex = (this.selectedIndex - 1 + this.unlockedAbilities.size()) % this.unlockedAbilities.size();
    }

    @Override
    public String getSelectedAbilityKey() {
        if (this.unlockedAbilities.isEmpty() || this.selectedIndex < 0 || this.selectedIndex >= this.unlockedAbilities.size()) {
            return null;
        }
        return this.unlockedAbilities.get(this.selectedIndex);
    }

    public List<String> getUnlockedAbilityList() {
        return this.unlockedAbilities;
    }

    @Override
    public String[] getUnlockedAbilities() {
        return this.unlockedAbilities.toArray(new String[0]);
    }

    public void setUnlockedAbilities(List<String> abilities) {
        List<String> list = this.unlockedAbilities = abilities != null ? new ArrayList<String>(abilities) : new ArrayList();
        if (this.selectedIndex >= this.unlockedAbilities.size()) {
            this.selectedIndex = Math.max(0, this.unlockedAbilities.size() - 1);
        }
    }

    @Override
    public void unlockAbility(String key) {
        if (key != null && !key.isEmpty() && !this.unlockedAbilities.contains(key)) {
            this.unlockedAbilities.add(key);
        }
    }

    @Override
    public void lockAbility(String key) {
        this.unlockedAbilities.remove(key);
        if (this.selectedIndex >= this.unlockedAbilities.size()) {
            this.selectedIndex = Math.max(0, this.unlockedAbilities.size() - 1);
        }
    }

    @Override
    public boolean hasUnlockedAbility(String key) {
        return this.unlockedAbilities.contains(key);
    }

    public boolean isOnCooldown(EntityPlayer player) {
        return player.field_70170_p.func_82737_E() < this.cooldownEndTime;
    }

    public boolean isOnCooldown(String key, EntityPlayer player) {
        Ability ability;
        if (AbilityController.Instance != null && (ability = AbilityController.Instance.resolveAbility(key)) != null && ability.isPerAbilityCooldown()) {
            return this.isOnPerAbilityCooldown(key);
        }
        return this.isOnCooldown(player);
    }

    public long getRemainingCooldown(EntityPlayer player) {
        long remaining = this.cooldownEndTime - player.field_70170_p.func_82737_E();
        return remaining > 0L ? remaining : 0L;
    }

    @Override
    public void resetCooldown(String key) {
        this.resetPerAbilityCooldown(key);
        this.cooldownEndTime = 0L;
    }

    @Override
    public void resetAllCooldowns() {
        this.cooldownEndTime = 0L;
        this.resetAllPerAbilityCooldowns();
    }

    @Override
    public boolean activateAbility() {
        return this.activateAbility(this.playerData.player);
    }

    @Override
    public boolean activateAbility(String key) {
        return this.activateAbility(this.playerData.player, key);
    }

    @Override
    public boolean isOnCooldown() {
        EntityPlayer player = this.playerData.player;
        if (player == null) {
            return false;
        }
        return this.isOnCooldown(player);
    }

    @Override
    public boolean isOnCooldown(String key) {
        EntityPlayer player = this.playerData.player;
        if (player == null) {
            return false;
        }
        return this.isOnCooldown(key, player);
    }

    @Override
    public void resetCooldown() {
        this.cooldownEndTime = 0L;
        this.resetAllPerAbilityCooldowns();
    }

    @Override
    public void interruptCurrentAbility() {
        this.interruptCurrentAbility(null, 0.0f);
    }

    public boolean tryCancelAbility() {
        AbilityPhase phase;
        boolean isInChainDelay;
        boolean hasExecutingAbility = this.currentAbility != null && this.currentAbility.isExecuting();
        boolean bl = isInChainDelay = this.currentChain != null && this.chainDelayRemaining > 0;
        if (!hasExecutingAbility && !isInChainDelay) {
            return false;
        }
        if (hasExecutingAbility && (phase = this.currentAbility.getPhase()) != AbilityPhase.WINDUP) {
            return false;
        }
        long worldTime = this.getWorldTime();
        if (this.lastAbilityActivationTime < 0L || worldTime - this.lastAbilityActivationTime > 10L) {
            this.lastAbilityActivationTime = worldTime;
            return false;
        }
        this.cancelCurrentAbility();
        return true;
    }

    public void onEntityReconstructed(boolean clearCooldowns, boolean clearToggles) {
        if (this.currentAbility != null && this.currentAbility.isExecuting()) {
            this.removeTelegraph(this.currentAbility);
            this.currentAbility.interrupt();
            this.stopAbilityAnimation();
            this.releaseRotationControl();
            this.releaseLockedPosition();
        }
        if (this.currentChain != null) {
            this.currentChain.clearInstanceScript();
        }
        this.currentChain = null;
        this.chainEntryIndex = -1;
        this.chainDelayRemaining = -1;
        this.interruptConcurrentSlots();
        this.currentAbility = null;
        this.currentAbilityKey = null;
        this.currentTarget = null;
        this.lastAbilityActivationTime = -1L;
        if (clearCooldowns) {
            this.cooldownEndTime = 0L;
            this.resetAllPerAbilityCooldowns();
        }
        this.interruptCooldownRolled = false;
        if (clearToggles) {
            this.clearActiveToggles();
        }
        this.lastSyncedFlags = 0;
        EntityPlayer player = this.playerData.player;
        if (player instanceof EntityPlayerMP) {
            PlayerAbilityStatePacket.sendToPlayer((EntityPlayerMP)player, (byte)0, 0.0f, 0.0f);
        }
        this.syncToClient();
    }

    public void resetOnDimensionChange() {
        this.trackingAbilityPosition = false;
        this.onEntityReconstructed(true, false);
    }

    public void resetOnTeleport() {
        this.onEntityReconstructed(true, false);
    }

    public void resetOnRespawn() {
        this.onEntityReconstructed(false, true);
    }

    public void resetOnLogin() {
        this.trackingAbilityPosition = false;
        if (this.currentAbility != null && this.currentAbility.isExecuting()) {
            this.removeTelegraph(this.currentAbility);
            this.currentAbility.interrupt();
            this.stopAbilityAnimation();
            this.releaseRotationControl();
            this.releaseLockedPosition();
        }
        if (this.currentChain != null) {
            this.currentChain.clearInstanceScript();
        }
        this.currentChain = null;
        this.chainEntryIndex = -1;
        this.chainDelayRemaining = -1;
        this.interruptConcurrentSlots();
        this.currentAbility = null;
        this.currentAbilityKey = null;
        this.currentTarget = null;
        this.lastAbilityActivationTime = -1L;
        this.cooldownEndTime = 0L;
        this.resetAllPerAbilityCooldowns();
        this.interruptCooldownRolled = false;
        this.lastSyncedFlags = 0;
    }

    public void resetOnDisconnect() {
        if (this.currentAbility != null && this.currentAbility.isExecuting()) {
            this.interruptCurrentAbility(null, 0.0f);
        }
        this.onEntityReconstructed(true, false);
    }

    public float onDamage(DamageSource source, float amount) {
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return amount;
        }
        if (this.currentAbility.isInvulnerableForCurrentPhase()) {
            return 0.0f;
        }
        if (this.currentAbility != null && this.currentAbility.canInterrupt(source)) {
            this.interruptCurrentAbility(source, amount);
        }
        return amount;
    }

    public boolean isMovementLocked() {
        return this.currentAbility != null && this.currentAbility.isExecuting() && this.currentAbility.isMovementLockedForCurrentPhase();
    }

    public boolean isPlayingAbilityAnimation() {
        return this.playingAbilityAnimation;
    }

    public void clearOrphanedAbilityAnimation() {
        this.stopAbilityAnimation();
    }

    private void applyRotationControl(EntityPlayer player) {
        if (!this.rotationLocked || this.currentAbility == null) {
            return;
        }
        if (this.currentAbility.isRotationLockedForCurrentPhase()) {
            player.field_70177_z = this.lockedYaw;
            player.field_70125_A = this.lockedPitch;
            player.field_70126_B = this.lockedYaw;
            player.field_70127_C = this.lockedPitch;
            if (player instanceof EntityPlayerMP) {
                player.field_70759_as = this.lockedYaw;
            }
        }
    }

    private void applyPositionLock(EntityPlayer player) {
        if (!this.positionLocked) {
            return;
        }
        player.func_70107_b(this.lockedPosX, this.lockedPosY, this.lockedPosZ);
        player.field_70169_q = this.lockedPosX;
        player.field_70167_r = this.lockedPosY;
        player.field_70166_s = this.lockedPosZ;
        player.field_70159_w = 0.0;
        player.field_70181_x = 0.0;
        player.field_70179_y = 0.0;
    }

    private byte getAbilityStateFlags() {
        byte flags = 0;
        if (this.currentAbility == null || !this.currentAbility.isExecuting()) {
            return flags;
        }
        if (this.currentAbility.isMovementLockedForCurrentPhase()) {
            flags = (byte)(flags | 1);
        }
        if (this.rotationLocked && this.currentAbility.isRotationLockedForCurrentPhase()) {
            flags = (byte)(flags | 2);
        }
        if (this.currentAbility.hasAbilityMovement() && (this.currentAbility.getPhase() == AbilityPhase.ACTIVE || this.currentAbility.getPhase() == AbilityPhase.WINDUP)) {
            flags = (byte)(flags | 4);
        }
        if (this.positionLocked) {
            flags = (byte)(flags | 8);
        }
        if (this.wasFlyingAtLock) {
            flags = (byte)(flags | 0x10);
        }
        if (this.currentAbility.getPhase() == AbilityPhase.ACTIVE) {
            flags = (byte)(flags | 0x20);
        }
        return flags;
    }

    private void syncAbilityStateIfNeeded(EntityPlayer player) {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        byte flags = this.getAbilityStateFlags();
        if (flags != this.lastSyncedFlags) {
            this.lastSyncedFlags = flags;
            PlayerAbilityStatePacket.sendToPlayer((EntityPlayerMP)player, flags, this.lockedYaw, this.lockedPitch);
        }
    }

    private void syncAbilityStateClear(EntityPlayer player) {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (this.lastSyncedFlags != 0) {
            this.lastSyncedFlags = 0;
            PlayerAbilityStatePacket.sendToPlayer((EntityPlayerMP)player, (byte)0, 0.0f, 0.0f);
        }
    }

    public void ensureLockStateClear() {
        if (this.isExecutingAbility() || this.isExecutingChain()) {
            return;
        }
        EntityPlayer player = this.playerData.player;
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (this.lastSyncedFlags != 0) {
            this.lastSyncedFlags = 0;
            PlayerAbilityStatePacket.sendToPlayer((EntityPlayerMP)player, (byte)0, 0.0f, 0.0f);
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        NBTTagList list = new NBTTagList();
        for (String key : this.unlockedAbilities) {
            list.func_74742_a((NBTBase)new NBTTagString(key));
        }
        compound.func_74782_a("PlayerAbilities", (NBTBase)list);
        compound.func_74768_a("PlayerAbilitySelected", this.selectedIndex);
        compound.func_74757_a("AbilityAnimating", this.playingAbilityAnimation);
        NBTTagList toggleList = new NBTTagList();
        for (Map.Entry entry : this.activeToggles.entrySet()) {
            NBTTagCompound toggleNbt = new NBTTagCompound();
            toggleNbt.func_74778_a("Key", (String)entry.getKey());
            toggleNbt.func_74768_a("State", ((AbilityToggleEntry)entry.getValue()).getState());
            toggleList.func_74742_a((NBTBase)toggleNbt);
        }
        compound.func_74782_a("ActiveToggles", (NBTBase)toggleList);
    }

    public void readFromNBT(NBTTagCompound compound) {
        int i;
        this.unlockedAbilities.clear();
        if (compound.func_74764_b("PlayerAbilities")) {
            NBTTagList list = compound.func_150295_c("PlayerAbilities", 8);
            for (i = 0; i < list.func_74745_c(); ++i) {
                String key = list.func_150307_f(i);
                if (key == null || key.isEmpty()) continue;
                this.unlockedAbilities.add(key);
            }
        }
        this.selectedIndex = compound.func_74762_e("PlayerAbilitySelected");
        this.validateUnlockedAbilities();
        if (this.selectedIndex >= this.unlockedAbilities.size()) {
            this.selectedIndex = Math.max(0, this.unlockedAbilities.size() - 1);
        }
        this.playingAbilityAnimation = compound.func_74767_n("AbilityAnimating");
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

    private void validateUnlockedAbilities() {
        if (AbilityController.Instance == null) {
            return;
        }
        if (this.playerData == null || !(this.playerData.player instanceof EntityPlayerMP)) {
            return;
        }
        Iterator<String> it = this.unlockedAbilities.iterator();
        while (it.hasNext()) {
            String key = it.next();
            IAbilityAction resolved = this.resolveActionKey(key);
            if (resolved == null) {
                it.remove();
                LogWriter.info("Removed invalid ability reference from player data: " + key);
                continue;
            }
            if (resolved.getAllowedBy().allowsPlayer()) continue;
            it.remove();
            LogWriter.info("Removed non-player ability from player data: " + key);
        }
    }
}

