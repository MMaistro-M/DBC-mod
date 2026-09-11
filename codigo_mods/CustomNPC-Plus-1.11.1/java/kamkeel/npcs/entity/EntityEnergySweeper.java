/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package kamkeel.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.EnergyController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.energy.EnergyDisplayData;
import kamkeel.npcs.entity.EntityEnergyAbility;
import kamkeel.npcs.util.CNPCDebug;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityEnergySweeper
extends EntityEnergyAbility {
    private float beamLength = 10.0f;
    private float beamWidth = 0.3f;
    private float beamHeight = 1.0f;
    private float damage = 5.0f;
    private int damageInterval = 5;
    private boolean piercing = true;
    private float currentAngle = 0.0f;
    private float prevAngle = 0.0f;
    private float sweepSpeed = 3.0f;
    private int numberOfRotations = 2;
    private int completedRotations = 0;
    private float baseYaw = 0.0f;
    private boolean lockOnTarget = false;
    private int targetEntityId = -1;
    private int maxTicks = 400;
    private long deathWorldTime = -1L;
    private transient int ticksSinceDamage = 0;
    private transient Set<Integer> hitThisTick = new HashSet<Integer>();

    public EntityEnergySweeper(World world) {
        super(world);
        this.func_70105_a(0.1f, 0.1f);
        this.field_70145_X = true;
    }

    public EntityEnergySweeper(World world, EntityLivingBase owner, EntityLivingBase target, float beamLength, float beamWidth, float beamHeight, EnergyDisplayData displayData, float sweepSpeed, int numberOfRotations, float damage, int damageInterval, boolean piercing, boolean lockOnTarget) {
        this(world);
        this.ownerEntityId = owner != null ? owner.func_145782_y() : -1;
        this.targetEntityId = target != null ? target.func_145782_y() : -1;
        this.beamLength = beamLength;
        this.beamWidth = beamWidth;
        this.beamHeight = beamHeight;
        this.displayData = displayData != null ? displayData.copy() : new EnergyDisplayData();
        this.sweepSpeed = sweepSpeed;
        this.numberOfRotations = numberOfRotations;
        this.damage = damage;
        this.damageInterval = damageInterval;
        this.piercing = piercing;
        this.lockOnTarget = lockOnTarget;
        this.maxTicks = (int)(360.0f * (float)numberOfRotations / sweepSpeed) + 10;
        if (lockOnTarget && target != null && owner != null) {
            double dx = target.field_70165_t - owner.field_70165_t;
            double dz = target.field_70161_v - owner.field_70161_v;
            this.baseYaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
        } else if (owner != null) {
            this.baseYaw = owner.field_70177_z;
        }
        if (owner != null) {
            this.func_70107_b(owner.field_70165_t, owner.field_70163_u + (double)beamHeight / 2.0, owner.field_70161_v);
        }
        this.ticksSinceDamage = damageInterval;
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void func_70071_h_() {
        boolean isClient;
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.prevAngle = this.currentAngle;
        super.func_70071_h_();
        if (this.deathWorldTime < 0L && this.field_70170_p != null) {
            this.deathWorldTime = this.field_70170_p.func_82737_E() + (long)this.maxTicks;
        }
        if (this.deathWorldTime > 0L && this.field_70170_p.func_82737_E() >= this.deathWorldTime) {
            this.func_70106_y();
            return;
        }
        if (this.field_70173_aa > 1200) {
            this.func_70106_y();
            return;
        }
        Entity owner = this.getOwnerEntity();
        if (owner != null && owner.field_70128_L) {
            this.func_70106_y();
            return;
        }
        if (owner instanceof EntityNPCInterface && ((EntityNPCInterface)owner).isKilled()) {
            this.func_70106_y();
            return;
        }
        if (owner != null) {
            this.func_70107_b(owner.field_70165_t, owner.field_70163_u + (double)this.beamHeight / 2.0, owner.field_70161_v);
        }
        if (this.completedRotations >= this.numberOfRotations) {
            this.func_70106_y();
            return;
        }
        this.currentAngle += this.sweepSpeed;
        while (this.currentAngle >= 360.0f) {
            this.currentAngle -= 360.0f;
            ++this.completedRotations;
        }
        if (!this.field_70170_p.field_72995_K) {
            this.handleDamage(owner);
        }
        if ((isClient = this.field_70170_p.field_72995_K) ? CNPCDebug.isClientEnabled("energy") : CNPCDebug.isServerEnabled("energy")) {
            CNPCDebug.log("energy", isClient, String.format("[Sweeper id=%d tick=%d] pos=(%.2f,%.2f,%.2f) angle=%.1f prevAngle=%.1f speed=%.1f rotations=%d/%d length=%.2f width=%.2f", this.func_145782_y(), this.field_70173_aa, this.field_70165_t, this.field_70163_u, this.field_70161_v, Float.valueOf(this.currentAngle), Float.valueOf(this.prevAngle), Float.valueOf(this.sweepSpeed), this.completedRotations, this.numberOfRotations, Float.valueOf(this.beamLength), Float.valueOf(this.beamWidth)));
        }
    }

    private void handleDamage(Entity owner) {
        this.hitThisTick.clear();
        ++this.ticksSinceDamage;
        if (this.ticksSinceDamage < this.damageInterval) {
            return;
        }
        this.ticksSinceDamage = 0;
        float beamYaw = this.baseYaw + this.currentAngle;
        float yawRad = (float)Math.toRadians(beamYaw);
        double startX = this.field_70165_t;
        double startY = this.field_70163_u;
        double startZ = this.field_70161_v;
        double dirX = -Math.sin(yawRad);
        double dirZ = Math.cos(yawRad);
        int samples = (int)((double)this.beamLength / 0.5);
        for (int i = 1; i <= samples; ++i) {
            double progress = (double)i / (double)samples;
            double checkX = startX + dirX * (double)this.beamLength * progress;
            double checkY = startY;
            double checkZ = startZ + dirZ * (double)this.beamLength * progress;
            AxisAlignedBB checkBox = AxisAlignedBB.func_72330_a((double)(checkX - (double)(this.beamWidth * 2.0f)), (double)(checkY - (double)this.beamHeight / 2.0), (double)(checkZ - (double)(this.beamWidth * 2.0f)), (double)(checkX + (double)(this.beamWidth * 2.0f)), (double)(checkY + (double)this.beamHeight / 2.0), (double)(checkZ + (double)(this.beamWidth * 2.0f)));
            List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, checkBox);
            for (Entity entity : entities) {
                EntityLivingBase livingEntity;
                if (!(entity instanceof EntityLivingBase) || entity == owner || this.hitThisTick.contains(entity.func_145782_y()) || this.shouldIgnoreEntity((EntityLivingBase)entity, owner) || !this.isInBeamPath(livingEntity = (EntityLivingBase)entity, startX, startY, startZ, dirX, dirZ)) continue;
                this.hitThisTick.add(entity.func_145782_y());
                this.applyDamage(livingEntity, owner);
                if (this.piercing) continue;
                return;
            }
        }
    }

    private boolean isInBeamPath(EntityLivingBase entity, double startX, double startY, double startZ, double dirX, double dirZ) {
        double entityX = entity.field_70165_t - startX;
        double entityZ = entity.field_70161_v - startZ;
        double projectionDist = entityX * dirX + entityZ * dirZ;
        if (projectionDist < 0.0 || projectionDist > (double)this.beamLength) {
            return false;
        }
        double projX = dirX * projectionDist;
        double perpX = entityX - projX;
        double projZ = dirZ * projectionDist;
        double perpZ = entityZ - projZ;
        double perpDist = Math.sqrt(perpX * perpX + perpZ * perpZ);
        if (perpDist > (double)(this.beamWidth * 3.0f + entity.field_70130_N / 2.0f)) {
            return false;
        }
        double beamTopY = startY + (double)this.beamHeight / 2.0;
        double beamBottomY = startY - (double)this.beamHeight / 2.0;
        double entityFeetY = entity.field_70163_u;
        double entityTopY = entity.field_70163_u + (double)entity.field_70131_O;
        return entityFeetY < beamTopY && entityTopY > beamBottomY;
    }

    private boolean shouldIgnoreEntity(EntityLivingBase entity, Entity owner) {
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface targetNpc = (EntityNPCInterface)entity;
            if (targetNpc.faction.isPassive) {
                return true;
            }
            if (owner instanceof EntityNPCInterface && ((EntityNPCInterface)owner).faction.id == targetNpc.faction.id) {
                return true;
            }
            if (owner instanceof EntityPlayer && targetNpc.faction.isFriendlyToPlayer((EntityPlayer)owner)) {
                return true;
            }
        }
        if (owner instanceof EntityPlayer && entity instanceof EntityPlayer) {
            Party party;
            PlayerData ownerData = PlayerData.get((EntityPlayer)owner);
            PlayerData targetData = PlayerData.get((EntityPlayer)entity);
            if (ownerData.partyUUID != null && ownerData.partyUUID.equals(targetData.partyUUID) && (party = PartyController.Instance().getParty(ownerData.partyUUID)) != null && !party.friendlyFire()) {
                return true;
            }
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void applyDamage(EntityLivingBase target, Entity owner) {
        boolean ignoreIFrames = this.isIgnoreIFrames();
        int previousHurtResistantTime = Ability.clearHurtResistanceIfNeeded(target, ignoreIFrames);
        try {
            double dz;
            double dx;
            boolean handled = false;
            if (this.sourceAbility != null && owner instanceof EntityLivingBase) {
                dx = target.field_70165_t - this.field_70165_t;
                dz = target.field_70161_v - this.field_70161_v;
                handled = AbilityController.Instance.fireOnAbilityDamage(this.sourceAbility, (EntityLivingBase)owner, target, this.damage, 0.0f, 0.2f, dx, dz, 1.0f);
            }
            if (!handled && this.customDamageData != null && owner instanceof EntityLivingBase) {
                dx = target.field_70165_t - this.field_70165_t;
                dz = target.field_70161_v - this.field_70161_v;
                handled = EnergyController.Instance.fireOnEnergyDamage(this, (EntityLivingBase)owner, target, this.damage, 0.0f, 0.2f, dx, dz, 1.0f, this.customDamageData);
            }
            if (!handled) {
                if (owner instanceof EntityNPCInterface) {
                    target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", (Entity)((EntityNPCInterface)owner)), this.damage);
                } else if (owner instanceof EntityPlayer) {
                    target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)owner)), this.damage);
                } else if (owner instanceof EntityLivingBase) {
                    target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)((EntityLivingBase)owner)), this.damage);
                } else {
                    target.func_70097_a((DamageSource)new NpcDamageSource("npc_ability", null), this.damage);
                }
            }
        }
        finally {
            Ability.restoreHurtResistanceIfNeeded(target, ignoreIFrames, previousHurtResistantTime);
        }
        target.func_70024_g(0.0, 0.2, 0.0);
        target.field_70133_I = true;
    }

    public void setupPreview(EntityLivingBase owner) {
        this.previewMode = true;
        this.previewOwner = owner;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double distance) {
        return distance < 16384.0;
    }

    public float getBeamLength() {
        return this.beamLength;
    }

    public float getBeamWidth() {
        return this.beamWidth;
    }

    public float getBeamHeight() {
        return this.beamHeight;
    }

    public float getInterpolatedAngle(float partialTicks) {
        float diff = this.currentAngle - this.prevAngle;
        if (diff > 180.0f) {
            diff -= 360.0f;
        }
        if (diff < -180.0f) {
            diff += 360.0f;
        }
        float interpAngle = this.prevAngle + diff * partialTicks;
        return this.baseYaw + interpAngle;
    }

    public float getCurrentAngle() {
        return this.currentAngle;
    }

    public float getBaseYaw() {
        return this.baseYaw;
    }

    public void setBeamLength(float length) {
        this.beamLength = length;
    }

    public void setBeamWidth(float width) {
        this.beamWidth = width;
    }

    public void setBeamHeight(float height) {
        this.beamHeight = height;
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getDamageInterval() {
        return this.damageInterval;
    }

    public void setDamageInterval(int interval) {
        this.damageInterval = interval;
    }

    public boolean isPiercing() {
        return this.piercing;
    }

    public void setPiercing(boolean piercing) {
        this.piercing = piercing;
    }

    public float getSweepSpeed() {
        return this.sweepSpeed;
    }

    public void setSweepSpeed(float speed) {
        this.sweepSpeed = speed;
    }

    public int getNumberOfRotations() {
        return this.numberOfRotations;
    }

    public void setNumberOfRotations(int rotations) {
        this.numberOfRotations = rotations;
    }

    public boolean isLockOnTarget() {
        return this.lockOnTarget;
    }

    public void setLockOnTarget(boolean lock) {
        this.lockOnTarget = lock;
    }

    @Override
    protected void writeSpawnNBT(NBTTagCompound nbt) {
        this.writeEnergyBaseNBT(nbt);
        nbt.func_74776_a("BeamLength", this.beamLength);
        nbt.func_74776_a("BeamWidth", this.beamWidth);
        nbt.func_74776_a("BeamHeight", this.beamHeight);
        nbt.func_74776_a("SweepSpeed", this.sweepSpeed);
        nbt.func_74768_a("NumRotations", this.numberOfRotations);
        nbt.func_74768_a("CompletedRotations", this.completedRotations);
        nbt.func_74768_a("MaxTicks", this.maxTicks);
        nbt.func_74768_a("TargetId", this.targetEntityId);
        nbt.func_74776_a("CurrentAngle", this.currentAngle);
        nbt.func_74776_a("BaseYaw", this.baseYaw);
        nbt.func_74772_a("DeathWorldTime", this.deathWorldTime);
        nbt.func_74776_a("Damage", this.damage);
        nbt.func_74768_a("DamageInterval", this.damageInterval);
        nbt.func_74757_a("Piercing", this.piercing);
        nbt.func_74757_a("LockOnTarget", this.lockOnTarget);
    }

    @Override
    protected void readSpawnNBT(NBTTagCompound nbt) {
        this.readEnergyBaseNBT(nbt);
        this.beamLength = EntityEnergySweeper.sanitize(nbt.func_74760_g("BeamLength"), 10.0f, 100.0f);
        this.beamWidth = EntityEnergySweeper.sanitize(nbt.func_74760_g("BeamWidth"), 0.3f, 100.0f);
        this.beamHeight = EntityEnergySweeper.sanitize(nbt.func_74760_g("BeamHeight"), 1.0f, 100.0f);
        float f = this.sweepSpeed = nbt.func_74764_b("SweepSpeed") ? nbt.func_74760_g("SweepSpeed") : 3.0f;
        if (Float.isNaN(this.sweepSpeed) || Float.isInfinite(this.sweepSpeed) || this.sweepSpeed <= 0.0f) {
            this.sweepSpeed = 3.0f;
        }
        int n = this.numberOfRotations = nbt.func_74764_b("NumRotations") ? nbt.func_74762_e("NumRotations") : 2;
        if (this.numberOfRotations <= 0) {
            this.numberOfRotations = 1;
        }
        this.completedRotations = nbt.func_74762_e("CompletedRotations");
        int n2 = this.maxTicks = nbt.func_74764_b("MaxTicks") ? nbt.func_74762_e("MaxTicks") : 400;
        if (this.maxTicks <= 0) {
            this.maxTicks = 400;
        }
        this.targetEntityId = nbt.func_74762_e("TargetId");
        this.prevAngle = this.currentAngle = nbt.func_74760_g("CurrentAngle");
        this.baseYaw = nbt.func_74760_g("BaseYaw");
        this.deathWorldTime = nbt.func_74763_f("DeathWorldTime");
        float f2 = this.damage = nbt.func_74764_b("Damage") ? nbt.func_74760_g("Damage") : 5.0f;
        if (Float.isNaN(this.damage) || Float.isInfinite(this.damage)) {
            this.damage = 5.0f;
        }
        int n3 = this.damageInterval = nbt.func_74764_b("DamageInterval") ? nbt.func_74762_e("DamageInterval") : 5;
        if (this.damageInterval <= 0) {
            this.damageInterval = 1;
        }
        this.piercing = !nbt.func_74764_b("Piercing") || nbt.func_74767_n("Piercing");
        this.lockOnTarget = nbt.func_74767_n("LockOnTarget");
    }
}

