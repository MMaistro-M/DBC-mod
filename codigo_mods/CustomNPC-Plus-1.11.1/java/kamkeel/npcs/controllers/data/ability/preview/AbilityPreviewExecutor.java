/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package kamkeel.npcs.controllers.data.ability.preview;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.entry.ChainedAbilityEntry;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.gui.GuiAbilityInterface;
import kamkeel.npcs.controllers.data.ability.preview.PreviewEntityHandler;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;

@SideOnly(value=Side.CLIENT)
public class AbilityPreviewExecutor
implements PreviewEntityHandler {
    private Ability previewAbility;
    private EntityNPCInterface previewNpc;
    private ExecutorPhase executorPhase = ExecutorPhase.IDLE;
    private int currentTick = 0;
    private boolean playing = false;
    private boolean paused = false;
    private static final int EXTENDED_DURATION = 100;
    private int maxPreviewDuration = 200;
    private EntityNPCInterface fakeTarget;
    private static final double TARGET_DISTANCE = 5.0;
    private double npcStartX;
    private double npcStartY;
    private double npcStartZ;
    private boolean hasStartPosition = false;
    private TelegraphInstance previewTelegraph;
    private List<Entity> previewEntities = new ArrayList<Entity>();
    private ChainedAbility previewChain;
    private List<Ability> chainAbilities;
    private List<Integer> chainDelays;
    private int chainIndex = -1;
    private int chainDelayRemaining = 0;
    private boolean chainWindUpAll = true;
    private GuiAbilityInterface parentGui;

    public void setParentGui(GuiAbilityInterface gui) {
        this.parentGui = gui;
    }

    public void startPreview(Ability ability, EntityNPCInterface npc) {
        this.stop();
        this.previewNpc = npc;
        this.playing = true;
        this.paused = false;
        this.npcStartX = npc.field_70165_t;
        this.npcStartY = npc.field_70163_u;
        this.npcStartZ = npc.field_70161_v;
        this.hasStartPosition = true;
        this.createFakeTarget(npc);
        if (this.parentGui != null) {
            this.parentGui.startTrackingMovement();
        }
        this.beginAbilityPreview(ability);
    }

    public void startChainPreview(ChainedAbility chain, EntityNPCInterface npc) {
        this.stop();
        this.previewNpc = npc;
        this.playing = true;
        this.paused = false;
        this.previewChain = chain;
        this.chainWindUpAll = chain.isWindUpAll();
        this.npcStartX = npc.field_70165_t;
        this.npcStartY = npc.field_70163_u;
        this.npcStartZ = npc.field_70161_v;
        this.hasStartPosition = true;
        this.chainAbilities = new ArrayList<Ability>();
        this.chainDelays = new ArrayList<Integer>();
        for (ChainedAbilityEntry entry : chain.getEntries()) {
            Ability copy;
            Ability resolved = entry.resolve();
            if (resolved == null || AbilityController.Instance == null || (copy = AbilityController.Instance.fromNBT(resolved.writeNBT(true))) == null) continue;
            this.chainAbilities.add(copy);
            this.chainDelays.add(entry.getDelayTicks());
        }
        if (this.chainAbilities.isEmpty()) {
            this.playing = false;
            return;
        }
        this.createFakeTarget(npc);
        if (this.parentGui != null) {
            this.parentGui.startTrackingMovement();
        }
        this.chainIndex = 0;
        this.beginAbilityPreview(this.chainAbilities.get(0));
    }

    private void beginAbilityPreview(Ability ability) {
        boolean skipWindup;
        if (this.previewAbility != null) {
            this.previewAbility.cleanup();
            this.previewAbility.setPreviewMode(false);
            this.previewAbility.setPreviewEntityHandler(null);
            this.previewAbility.reset();
        }
        this.previewAbility = ability;
        this.currentTick = 0;
        this.maxPreviewDuration = ability.getMaxPreviewDuration();
        ability.setPreviewMode(true);
        ability.setPreviewEntityHandler(this);
        ability.start((EntityLivingBase)this.fakeTarget);
        boolean bl = skipWindup = this.previewChain != null && !this.chainWindUpAll && this.chainIndex > 0;
        if (ability.getWindUpTicks() <= 0 || skipWindup) {
            this.executorPhase = ExecutorPhase.ACTIVE;
            this.transitionToActive();
        } else {
            this.executorPhase = ExecutorPhase.WINDUP;
            this.previewTelegraph = ability.createTelegraph((EntityLivingBase)this.previewNpc, (EntityLivingBase)this.fakeTarget);
            Animation windUpAnim = ability.getWindUpAnimation();
            if (windUpAnim != null) {
                AnimationData data = this.previewNpc.display.animationData;
                data.setEnabled(true);
                data.setAnimation(windUpAnim);
                data.animation.paused = false;
            }
        }
    }

    private void createFakeTarget(EntityNPCInterface npc) {
        if (npc.field_70170_p == null) {
            return;
        }
        try {
            this.fakeTarget = new EntityCustomNpc(npc.field_70170_p);
            float facingYaw = GuiAbilityInterface.NPC_FACING_YAW;
            double yawRad = Math.toRadians(facingYaw);
            double targetX = npc.field_70165_t - Math.sin(yawRad) * 5.0;
            double targetY = npc.field_70163_u;
            double targetZ = npc.field_70161_v + Math.cos(yawRad) * 5.0;
            this.fakeTarget.func_70107_b(targetX, targetY, targetZ);
            this.fakeTarget.field_70169_q = targetX;
            this.fakeTarget.field_70167_r = targetY;
            this.fakeTarget.field_70166_s = targetZ;
        }
        catch (Exception e) {
            this.fakeTarget = null;
        }
    }

    public EntityLivingBase getFakeTarget() {
        return this.fakeTarget;
    }

    public void play() {
        if (this.previewAbility == null || this.executorPhase == ExecutorPhase.IDLE) {
            return;
        }
        this.playing = true;
        this.paused = false;
        if (this.previewNpc != null) {
            AnimationData data = this.previewNpc.display.animationData;
            if (data.animation != null) {
                data.animation.paused = false;
            }
        }
    }

    public void pause() {
        this.paused = true;
        this.syncPrevPositions();
        if (this.previewNpc != null) {
            AnimationData data = this.previewNpc.display.animationData;
            if (data.animation != null) {
                data.animation.paused = true;
            }
        }
    }

    private void syncPrevPositions() {
        if (this.previewNpc != null) {
            this.previewNpc.field_70169_q = this.previewNpc.field_70165_t;
            this.previewNpc.field_70167_r = this.previewNpc.field_70163_u;
            this.previewNpc.field_70166_s = this.previewNpc.field_70161_v;
        }
        for (Entity entity : this.previewEntities) {
            if (entity == null || entity.field_70128_L) continue;
            entity.field_70169_q = entity.field_70165_t;
            entity.field_70167_r = entity.field_70163_u;
            entity.field_70166_s = entity.field_70161_v;
        }
    }

    public void stop() {
        if (this.previewAbility != null) {
            this.previewAbility.cleanup();
            this.previewAbility.setPreviewMode(false);
            this.previewAbility.setPreviewEntityHandler(null);
            this.previewAbility.reset();
        }
        this.playing = false;
        this.paused = false;
        this.executorPhase = ExecutorPhase.IDLE;
        this.currentTick = 0;
        this.previewChain = null;
        this.chainAbilities = null;
        this.chainDelays = null;
        this.chainIndex = -1;
        this.chainDelayRemaining = 0;
        this.previewTelegraph = null;
        if (this.parentGui != null) {
            for (Entity entity : this.previewEntities) {
                this.parentGui.removePreviewEntity(entity);
            }
            this.parentGui.stopTrackingMovement();
            this.parentGui.setPreviewTelegraph(null);
        }
        this.previewEntities.clear();
        if (this.previewNpc != null && this.hasStartPosition) {
            this.previewNpc.field_70165_t = this.npcStartX;
            this.previewNpc.field_70163_u = this.npcStartY;
            this.previewNpc.field_70161_v = this.npcStartZ;
            this.previewNpc.field_70169_q = this.npcStartX;
            this.previewNpc.field_70167_r = this.npcStartY;
            this.previewNpc.field_70166_s = this.npcStartZ;
            this.previewNpc.field_70159_w = 0.0;
            this.previewNpc.field_70181_x = 0.0;
            this.previewNpc.field_70179_y = 0.0;
        }
        this.fakeTarget = null;
        if (this.previewNpc != null) {
            AnimationData data = this.previewNpc.display.animationData;
            data.setAnimation(new Animation());
            data.animation.paused = false;
        }
    }

    public void tick() {
        if (!this.playing || this.previewAbility == null || this.previewNpc == null) {
            return;
        }
        if (this.paused) {
            this.syncPrevPositions();
            return;
        }
        this.previewNpc.field_70169_q = this.previewNpc.field_70165_t;
        this.previewNpc.field_70167_r = this.previewNpc.field_70163_u;
        this.previewNpc.field_70166_s = this.previewNpc.field_70161_v;
        ++this.currentTick;
        if (this.previewTelegraph != null) {
            this.previewTelegraph.tick(null);
        }
        this.tickEntities();
        switch (this.executorPhase) {
            case WINDUP: {
                this.tickWindup();
                break;
            }
            case ACTIVE: {
                this.tickActive();
                break;
            }
            case EXTENDED: {
                this.tickExtended();
                break;
            }
            case CHAIN_DELAY: {
                this.tickChainDelay();
                break;
            }
        }
    }

    private void tickWindup() {
        this.previewAbility.onWindUpTick((EntityLivingBase)this.previewNpc, (EntityLivingBase)this.fakeTarget, this.currentTick);
        if (this.currentTick >= this.previewAbility.getWindUpTicks()) {
            this.transitionToActive();
        }
    }

    private void tickActive() {
        this.previewAbility.onActiveTick((EntityLivingBase)this.previewNpc, (EntityLivingBase)this.fakeTarget, this.currentTick);
        this.simulatePhysics();
        if (this.previewAbility.getPhase() == AbilityPhase.IDLE) {
            if (this.hasNextChainEntry()) {
                this.transitionToChainDelay();
            } else {
                this.transitionToExtended();
            }
            return;
        }
        if (this.currentTick >= this.maxPreviewDuration) {
            if (this.hasNextChainEntry()) {
                this.transitionToChainDelay();
            } else {
                this.transitionToExtended();
            }
        }
    }

    private void tickExtended() {
        if (this.currentTick >= 100) {
            this.playing = false;
        }
    }

    private boolean hasNextChainEntry() {
        return this.previewChain != null && this.chainAbilities != null && this.chainIndex < this.chainAbilities.size() - 1;
    }

    private void transitionToChainDelay() {
        this.executorPhase = ExecutorPhase.CHAIN_DELAY;
        this.currentTick = 0;
        int nextIndex = this.chainIndex + 1;
        int n = this.chainDelayRemaining = nextIndex < this.chainDelays.size() ? this.chainDelays.get(nextIndex) : 0;
        if (this.previewNpc != null) {
            AnimationData data = this.previewNpc.display.animationData;
            data.setAnimation(new Animation());
        }
    }

    private void tickChainDelay() {
        if (this.chainDelayRemaining > 0) {
            --this.chainDelayRemaining;
            return;
        }
        this.advanceChainEntry();
    }

    private void advanceChainEntry() {
        ++this.chainIndex;
        if (this.chainIndex >= this.chainAbilities.size()) {
            this.transitionToExtended();
            return;
        }
        this.beginAbilityPreview(this.chainAbilities.get(this.chainIndex));
    }

    private void transitionToActive() {
        this.executorPhase = ExecutorPhase.ACTIVE;
        this.currentTick = 0;
        this.previewTelegraph = null;
        this.previewAbility.onExecute((EntityLivingBase)this.previewNpc, (EntityLivingBase)this.fakeTarget);
        Animation activeAnim = this.previewAbility.getActiveAnimation();
        if (activeAnim != null && this.previewNpc != null) {
            AnimationData data = this.previewNpc.display.animationData;
            data.setAnimation(activeAnim);
            data.animation.paused = false;
        }
    }

    private void transitionToExtended() {
        this.executorPhase = ExecutorPhase.EXTENDED;
        this.currentTick = 0;
        if (this.previewNpc != null) {
            AnimationData data = this.previewNpc.display.animationData;
            data.setAnimation(new Animation());
        }
    }

    private void simulatePhysics() {
        if (!this.previewAbility.hasAbilityMovement()) {
            return;
        }
        if (this.previewNpc == null) {
            return;
        }
        this.previewNpc.field_70169_q = this.previewNpc.field_70165_t;
        this.previewNpc.field_70167_r = this.previewNpc.field_70163_u;
        this.previewNpc.field_70166_s = this.previewNpc.field_70161_v;
        this.previewNpc.field_70165_t += this.previewNpc.field_70159_w;
        this.previewNpc.field_70163_u += this.previewNpc.field_70181_x;
        this.previewNpc.field_70161_v += this.previewNpc.field_70179_y;
        this.previewNpc.field_70181_x -= 0.08;
        this.previewNpc.field_70181_x *= 0.98;
        this.previewNpc.field_70159_w *= 0.91;
        this.previewNpc.field_70179_y *= 0.91;
        if (this.previewNpc.field_70163_u < this.npcStartY && this.previewNpc.field_70181_x < 0.0) {
            this.previewNpc.field_70163_u = this.npcStartY;
            this.previewNpc.field_70181_x = 0.0;
            this.previewNpc.field_70122_E = true;
        }
        this.previewNpc.field_70143_R = 0.0f;
    }

    private void tickEntities() {
        Iterator<Entity> iter = this.previewEntities.iterator();
        while (iter.hasNext()) {
            Entity entity = iter.next();
            if (entity == null || entity.field_70128_L) {
                if (this.parentGui != null) {
                    this.parentGui.removePreviewEntity(entity);
                }
                iter.remove();
                continue;
            }
            entity.func_70071_h_();
        }
    }

    @Override
    public void onEntitySpawned(Entity entity) {
        if (entity == null) {
            return;
        }
        this.previewEntities.add(entity);
        if (this.parentGui != null) {
            this.parentGui.addPreviewEntity(entity);
        }
    }

    @Override
    public void onEntityRemoved(Entity entity) {
        if (entity == null) {
            return;
        }
        this.previewEntities.remove(entity);
        if (this.parentGui != null) {
            this.parentGui.removePreviewEntity(entity);
        }
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public boolean isPaused() {
        return this.paused;
    }

    public boolean isActive() {
        return this.playing || this.paused;
    }

    public boolean isChainPreview() {
        return this.previewChain != null;
    }

    public AbilityPhase getPhase() {
        switch (this.executorPhase) {
            case WINDUP: {
                return AbilityPhase.WINDUP;
            }
            case ACTIVE: {
                return AbilityPhase.ACTIVE;
            }
        }
        return AbilityPhase.IDLE;
    }

    public int getCurrentTick() {
        return this.currentTick;
    }

    public TelegraphInstance getTelegraph() {
        return this.previewTelegraph;
    }

    public List<Entity> getPreviewEntities() {
        return this.previewEntities;
    }

    public Ability getPreviewAbility() {
        return this.previewAbility;
    }

    public EntityNPCInterface getPreviewNpc() {
        return this.previewNpc;
    }

    public String getStatusString() {
        if (!this.playing && !this.paused) {
            return "Stopped";
        }
        String chainPrefix = "";
        if (this.previewChain != null && this.chainAbilities != null) {
            chainPrefix = "Chain " + (this.chainIndex + 1) + "/" + this.chainAbilities.size() + " - ";
        }
        if (this.paused) {
            return chainPrefix + "Paused";
        }
        if (this.executorPhase == ExecutorPhase.WINDUP) {
            return chainPrefix + "Windup: " + this.currentTick + "/" + (this.previewAbility != null ? this.previewAbility.getWindUpTicks() : 0);
        }
        if (this.executorPhase == ExecutorPhase.ACTIVE) {
            return chainPrefix + "Active: " + this.currentTick + "/" + this.maxPreviewDuration;
        }
        if (this.executorPhase == ExecutorPhase.EXTENDED) {
            return chainPrefix + "Extended: " + this.currentTick + "/" + 100;
        }
        if (this.executorPhase == ExecutorPhase.CHAIN_DELAY) {
            return chainPrefix + "Delay: " + this.chainDelayRemaining;
        }
        return "";
    }

    private static enum ExecutorPhase {
        IDLE,
        WINDUP,
        ACTIVE,
        EXTENDED,
        CHAIN_DELAY;

    }
}

