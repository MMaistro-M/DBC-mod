/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S12PacketEntityVelocity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
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
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import noppes.npcs.api.ability.type.IAbilitySlam;
import noppes.npcs.client.gui.builder.FieldDef;
import noppes.npcs.entity.EntityNPCInterface;

public class AbilitySlam
extends Ability
implements IAbilitySlam {
    private float damage = 10.0f;
    private float radius = 5.0f;
    private float knockbackStrength = 1.5f;
    private float leapSpeed = 1.0f;
    private float leapHeight = 4.0f;
    private static final int PLAYER_LANDING_GRACE_TICKS = 10;
    private static final int NPC_LANDING_GRACE_TICKS = 3;
    private transient double targetX;
    private transient double targetY;
    private transient double targetZ;
    private transient double startY;
    private transient boolean hasLaunched = false;
    private transient boolean hasLanded = false;
    private transient boolean hasRisen = false;
    private transient int airTicks = 0;
    private transient int maxAirTicks = 60;
    private transient boolean airSlam = false;
    private transient double airSlamStartY = 0.0;
    private transient boolean wasFlying = false;
    private transient double peakY = 0.0;

    public AbilitySlam() {
        this.typeId = "ability.cnpc.slam";
        this.name = "Slam";
        this.targetingMode = TargetingMode.AOE_SELF;
        this.windUpTicks = 30;
        this.cooldownTicks = 0;
        this.lockMovement = LockMode.WINDUP;
        this.minRange = 2.0f;
        this.maxRange = 15.0f;
        this.telegraphType = TelegraphType.CIRCLE;
        this.windUpSound = "mob.irongolem.throw";
        this.activeSound = "";
        this.windUpAnimationName = "Ability_Slam_Windup";
        this.activeAnimationName = "Ability_Slam_Active";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/slam.png")};
    }

    @Override
    public boolean isTargetingModeLocked() {
        return false;
    }

    @Override
    public TargetingMode[] getAllowedTargetingModes() {
        return new TargetingMode[]{TargetingMode.AOE_SELF, TargetingMode.AOE_TARGET};
    }

    @Override
    public boolean hasAbilityMovement() {
        return true;
    }

    @Override
    public void onWindUpTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.isPlayerCaster(caster)) {
            if (this.targetingMode == TargetingMode.AOE_SELF) {
                this.targetX = caster.field_70165_t;
                this.targetY = caster.field_70163_u;
                this.targetZ = caster.field_70161_v;
            } else {
                float yawRad = (float)Math.toRadians(caster.field_70177_z);
                double dirX = -Math.sin(yawRad);
                double dirZ = Math.cos(yawRad);
                double launchDist = Math.max(4.0, (double)this.maxRange * 0.5);
                this.targetX = caster.field_70165_t + dirX * launchDist;
                this.targetY = caster.field_70163_u;
                this.targetZ = caster.field_70161_v + dirZ * launchDist;
            }
        } else if (this.targetingMode == TargetingMode.AOE_SELF) {
            this.targetX = caster.field_70165_t;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v;
        } else if (this.targetingMode == TargetingMode.AOE_TARGET && target != null && !target.field_70128_L) {
            this.targetX = target.field_70165_t;
            this.targetY = AbilitySlam.findGroundLevel(caster.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, 64);
            this.targetZ = target.field_70161_v;
        }
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        this.hasLaunched = false;
        this.hasLanded = false;
        this.hasRisen = false;
        this.airTicks = 0;
        this.startY = caster.field_70163_u;
        this.peakY = caster.field_70163_u;
        caster.field_70143_R = 0.0f;
        if (this.isPlayerCaster(caster)) {
            this.executePlayerSlam(caster);
        } else {
            this.executeNpcSlam(caster, target);
        }
    }

    private void executeNpcSlam(EntityLivingBase caster, EntityLivingBase target) {
        if (this.targetingMode == TargetingMode.AOE_SELF) {
            this.targetX = caster.field_70165_t;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v;
        } else if (this.targetingMode == TargetingMode.AOE_TARGET && target != null && !target.field_70128_L) {
            this.targetX = target.field_70165_t;
            this.targetY = AbilitySlam.findGroundLevel(caster.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, 64);
            this.targetZ = target.field_70161_v;
        } else {
            this.targetX = caster.field_70165_t;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v;
        }
        this.launchTowardTarget(caster);
    }

    private void executePlayerSlam(EntityLivingBase caster) {
        boolean isAirborne = !caster.field_70122_E;
        boolean highEnoughForAirSlam = false;
        if (isAirborne) {
            double groundY = AbilitySlam.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v, 64);
            boolean bl = highEnoughForAirSlam = caster.field_70163_u - groundY >= 3.0;
        }
        if (isAirborne && highEnoughForAirSlam) {
            this.airSlam = true;
            this.airSlamStartY = caster.field_70163_u;
            this.targetX = caster.field_70165_t;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v;
            if (caster instanceof EntityPlayerMP) {
                EntityPlayerMP mp = (EntityPlayerMP)caster;
                this.wasFlying = mp.field_71075_bZ.field_75100_b;
                if (this.wasFlying) {
                    mp.field_71075_bZ.field_75100_b = false;
                    mp.func_71016_p();
                }
            }
            this.hasLaunched = true;
            this.hasRisen = true;
            if (this.isMovementLockedDuringActive()) {
                caster.field_70159_w *= 0.25;
                caster.field_70179_y *= 0.25;
            }
            caster.field_70181_x = Math.min(caster.field_70181_x, -0.2);
            if (!this.isPreview()) {
                if (caster instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)caster).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)caster));
                } else {
                    caster.field_70133_I = true;
                }
                caster.field_70170_p.func_72956_a((Entity)caster, "mob.irongolem.throw", 0.8f, 0.8f);
            }
        } else {
            this.airSlam = false;
            this.airSlamStartY = 0.0;
            this.wasFlying = false;
            float yawRad = (float)Math.toRadians(caster.field_70177_z);
            double dirX = -Math.sin(yawRad);
            double dirZ = Math.cos(yawRad);
            double launchDist = Math.max(4.0, (double)this.maxRange * 0.5);
            this.targetX = caster.field_70165_t + dirX * launchDist;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v + dirZ * launchDist;
            this.launchTowardTarget(caster);
        }
    }

    private static double calculateLaunchVelocity(double targetHeight) {
        double lo = 0.0;
        double hi = 10.0;
        for (int iter = 0; iter < 30; ++iter) {
            double mid = (lo + hi) / 2.0;
            double maxY = AbilitySlam.simulateMaxHeight(mid);
            if (maxY < targetHeight) {
                lo = mid;
                continue;
            }
            hi = mid;
        }
        return (lo + hi) / 2.0;
    }

    private static double simulateMaxHeight(double vy) {
        double y = 0.0;
        double maxY = 0.0;
        for (int t = 0; t < 200 && vy > 0.0; ++t) {
            y += vy;
            vy -= 0.08;
            vy *= 0.98;
            if (!(y > maxY)) continue;
            maxY = y;
        }
        return maxY;
    }

    private void launchTowardTarget(EntityLivingBase caster) {
        float targetYaw;
        double dx = this.targetX - caster.field_70165_t;
        double dy = this.targetY - caster.field_70163_u;
        double dz = this.targetZ - caster.field_70161_v;
        double horizontalDist = Math.sqrt(dx * dx + dz * dz);
        double arcHeight = Math.max(1.0, (double)this.leapHeight);
        double vy = AbilitySlam.calculateLaunchVelocity(arcHeight);
        if (horizontalDist < 0.5) {
            caster.field_70159_w = 0.0;
            caster.field_70179_y = 0.0;
            caster.field_70181_x = vy;
            this.hasLaunched = true;
            if (!this.isPreview() && caster instanceof EntityNPCInterface) {
                ((EntityNPCInterface)caster).setNpcJumpingState(true);
            }
            if (!this.isPreview()) {
                if (caster instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)caster).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)caster));
                } else {
                    caster.field_70133_I = true;
                }
                caster.field_70170_p.func_72956_a((Entity)caster, "mob.irongolem.throw", 0.8f, 0.8f);
            }
            return;
        }
        if (horizontalDist > (double)this.maxRange) {
            double scale = (double)this.maxRange / horizontalDist;
            horizontalDist = this.maxRange;
            this.targetX = caster.field_70165_t + (dx *= scale);
            this.targetZ = caster.field_70161_v + (dz *= scale);
        }
        double speedFactor = Math.max(0.1, (double)this.leapSpeed);
        int flightTicks = (int)Math.max(10.0, Math.min(horizontalDist * 1.5 / speedFactor, 40.0));
        double drag = 0.91;
        double dragPowN = Math.pow(drag, flightTicks);
        double vHorizontal = horizontalDist * (1.0 - drag) / (1.0 - dragPowN);
        if (dy > 0.0) {
            vy += dy / (double)flightTicks * 1.5;
        } else if (dy < 0.0) {
            vy += dy / (double)flightTicks * 0.3;
        }
        double dirX = dx / horizontalDist;
        double dirZ = dz / horizontalDist;
        caster.field_70159_w = dirX * vHorizontal;
        caster.field_70179_y = dirZ * vHorizontal;
        caster.field_70181_x = vy;
        this.hasLaunched = true;
        if (!this.isPreview() && caster instanceof EntityNPCInterface) {
            ((EntityNPCInterface)caster).setNpcJumpingState(true);
        }
        if (!this.isPreview()) {
            if (caster instanceof EntityPlayerMP) {
                ((EntityPlayerMP)caster).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)caster));
            } else {
                caster.field_70133_I = true;
            }
        }
        caster.field_70177_z = targetYaw = (float)(Math.atan2(-dx, dz) * 180.0 / Math.PI);
        caster.field_70759_as = targetYaw;
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        double dz;
        double dx;
        int graceTicks;
        double normZ;
        double normX;
        double moveZ;
        double moveX;
        double hSpeed;
        if (!this.hasLaunched) {
            return;
        }
        if (this.hasLanded) {
            return;
        }
        boolean playerCaster = this.isPlayerCaster(caster);
        boolean activeMovementLocked = this.isMovementLockedDuringActive();
        boolean forceHorizontalControl = !playerCaster || activeMovementLocked;
        ++this.airTicks;
        if (!this.isPreview()) {
            caster.field_70143_R = 0.0f;
        }
        if (caster.field_70163_u > this.peakY) {
            this.peakY = caster.field_70163_u;
        }
        if (this.airSlam && !this.isPreview() && !this.hasLanded) {
            if (forceHorizontalControl) {
                caster.field_70159_w *= 0.2;
                caster.field_70179_y *= 0.2;
            }
            double nextMotionY = caster.field_70181_x - 0.18;
            caster.field_70181_x = Math.max(-1.6, nextMotionY);
            if (caster instanceof EntityPlayerMP) {
                if (forceHorizontalControl) {
                    ((EntityPlayerMP)caster).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)caster));
                }
            } else {
                caster.field_70133_I = true;
            }
        }
        if (!this.isPreview() && caster instanceof EntityNPCInterface) {
            ((EntityNPCInterface)caster).func_70661_as().func_75499_g();
        }
        if (!this.isPreview() && !caster.field_70170_p.field_72995_K && (hSpeed = Math.sqrt((moveX = caster.field_70159_w) * moveX + (moveZ = caster.field_70179_y) * moveZ)) > 0.01 && AbilitySlam.isMovementBlockedByBarrier(caster, normX = moveX / hSpeed, normZ = moveZ / hSpeed, hSpeed)) {
            caster.field_70159_w = 0.0;
            caster.field_70179_y = 0.0;
            if (caster instanceof EntityPlayerMP) {
                ((EntityPlayerMP)caster).field_71135_a.func_147359_a((Packet)new S12PacketEntityVelocity((Entity)caster));
            } else {
                caster.field_70133_I = true;
            }
        }
        if (!this.hasRisen && caster.field_70163_u > this.startY + 0.5) {
            this.hasRisen = true;
        }
        int n = graceTicks = this.isPlayerCaster(caster) ? 10 : 3;
        if (this.airTicks > graceTicks) {
            if (this.hasRisen && (caster.field_70122_E || caster.field_70123_F)) {
                this.onLanding(caster, caster.field_70170_p);
                return;
            }
            if (!this.hasRisen && this.airTicks > graceTicks * 3) {
                this.onLanding(caster, caster.field_70170_p);
                return;
            }
        }
        if (this.airTicks >= this.maxAirTicks) {
            this.onLanding(caster, caster.field_70170_p);
            return;
        }
        if (!this.isPreview() && forceHorizontalControl && (dx = this.targetX - caster.field_70165_t) * dx + (dz = this.targetZ - caster.field_70161_v) * dz > 0.25) {
            float targetYaw;
            caster.field_70177_z = targetYaw = (float)(Math.atan2(-dx, dz) * 180.0 / Math.PI);
            caster.field_70759_as = targetYaw;
            if (playerCaster) {
                caster.field_70133_I = true;
            }
        }
    }

    private void onLanding(EntityLivingBase caster, World world) {
        float heightFactor;
        this.hasLanded = true;
        if (!this.isPreview() && caster instanceof EntityNPCInterface) {
            ((EntityNPCInterface)caster).setNpcJumpingState(false);
        }
        this.restoreFlightIfNeeded(caster);
        if (!this.isPlayerCaster(caster) || this.isMovementLockedDuringActive()) {
            caster.field_70159_w = 0.0;
            caster.field_70179_y = 0.0;
        }
        if (!this.isPreview()) {
            caster.field_70143_R = 0.0f;
            caster.field_70133_I = true;
        }
        if (world.field_72995_K && !this.isPreview()) {
            return;
        }
        if (this.isPreview()) {
            return;
        }
        world.func_72956_a((Entity)caster, "random.explode", 1.0f, 1.0f);
        float effectiveDamage = this.damage;
        float heightMultiplier = 1.0f;
        if (this.airSlam) {
            double fallDistance = this.airSlamStartY - caster.field_70163_u;
            if (fallDistance < 0.0) {
                fallDistance = 0.0;
            }
            heightFactor = (float)Math.min(1.0, fallDistance / Math.max(1.0, (double)this.leapHeight));
            heightMultiplier = Math.max(0.25f, heightFactor);
            effectiveDamage = this.damage * heightMultiplier;
        } else {
            double arcHeight = this.peakY - this.startY;
            if (arcHeight > 0.0) {
                heightFactor = (float)Math.min(1.0, arcHeight / Math.max(1.0, (double)this.leapHeight));
                heightMultiplier = Math.max(0.25f, heightFactor);
                effectiveDamage = this.damage * heightMultiplier;
            }
        }
        this.setDamageMultiplier(heightMultiplier);
        List entities = world.func_72839_b((Entity)caster, caster.field_70121_D.func_72314_b((double)this.radius, (double)(this.radius / 2.0f), (double)this.radius));
        for (Entity entity : entities) {
            boolean wasHit;
            double dz;
            double dx;
            EntityLivingBase livingTarget;
            if (!(entity instanceof EntityLivingBase) || entity == caster || !AbilityTargetHelper.shouldAffect(caster, (Entity)(livingTarget = (EntityLivingBase)entity), TargetFilter.ENEMIES, false) || !((dx = livingTarget.field_70165_t - caster.field_70165_t) * dx + (dz = livingTarget.field_70161_v - caster.field_70161_v) * dz <= (double)(this.radius * this.radius)) || !(wasHit = this.applyAbilityDamage(caster, livingTarget, effectiveDamage, this.knockbackStrength))) continue;
            this.applyEffects(livingTarget);
        }
        this.setDamageMultiplier(1.0f);
        this.spawnSlamParticles(world, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
        this.signalCompletion();
    }

    private void spawnSlamParticles(World world, double x, double y, double z) {
        int blockId;
        int i;
        for (i = 0; i < 20; ++i) {
            double angle = (double)i / 20.0 * Math.PI * 2.0;
            double px = x + Math.cos(angle) * (double)this.radius * 0.8;
            double pz = z + Math.sin(angle) * (double)this.radius * 0.8;
            world.func_72869_a("explode", px, y + 0.5, pz, 0.0, 0.1, 0.0);
        }
        for (i = 0; i < 10; ++i) {
            world.func_72869_a("smoke", x, y + 0.2, z, (world.field_73012_v.nextDouble() - 0.5) * 0.3, world.field_73012_v.nextDouble() * 0.2, (world.field_73012_v.nextDouble() - 0.5) * 0.3);
        }
        Block groundBlock = world.func_147439_a((int)x, (int)y - 1, (int)z);
        if (groundBlock != null && groundBlock.func_149688_o().func_76220_a() && (blockId = Block.func_149682_b((Block)groundBlock)) > 0) {
            for (int i2 = 0; i2 < 15; ++i2) {
                double angle = world.field_73012_v.nextDouble() * Math.PI * 2.0;
                double dist = world.field_73012_v.nextDouble() * (double)this.radius * 0.6;
                double px = x + Math.cos(angle) * dist;
                double pz = z + Math.sin(angle) * dist;
                world.func_72869_a("blockcrack_" + blockId + "_0", px, y + 0.1, pz, 0.0, 0.2, 0.0);
            }
        }
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.isPreview() && caster instanceof EntityNPCInterface) {
            ((EntityNPCInterface)caster).setNpcJumpingState(false);
        }
        this.restoreFlightIfNeeded(caster);
        this.hasLaunched = false;
        this.hasLanded = false;
        this.hasRisen = false;
        this.airTicks = 0;
        this.airSlam = false;
        this.airSlamStartY = 0.0;
        this.peakY = 0.0;
        this.wasFlying = false;
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        if (!this.isPreview() && caster instanceof EntityNPCInterface) {
            ((EntityNPCInterface)caster).setNpcJumpingState(false);
        }
        this.restoreFlightIfNeeded(caster);
        this.hasLaunched = false;
        this.hasLanded = false;
        this.hasRisen = false;
        this.airTicks = 0;
        this.airSlam = false;
        this.airSlamStartY = 0.0;
        this.peakY = 0.0;
        this.wasFlying = false;
    }

    @Override
    public void cleanup() {
        this.hasLaunched = false;
        this.hasLanded = false;
        this.hasRisen = false;
        this.airTicks = 0;
        this.airSlam = false;
        this.airSlamStartY = 0.0;
        this.peakY = 0.0;
        this.wasFlying = false;
    }

    @Override
    public void reset() {
        super.reset();
        this.hasLaunched = false;
        this.hasLanded = false;
        this.hasRisen = false;
        this.airTicks = 0;
        this.airSlam = false;
        this.airSlamStartY = 0.0;
        this.peakY = 0.0;
        this.wasFlying = false;
    }

    private void restoreFlightIfNeeded(EntityLivingBase caster) {
        if (this.airSlam && this.wasFlying && caster instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP)caster;
            if (!mp.field_71075_bZ.field_75100_b) {
                mp.field_71075_bZ.field_75100_b = true;
                mp.func_71016_p();
            }
        }
    }

    @Override
    public int getMaxPreviewDuration() {
        return 60;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        if (!this.isShowTelegraph() || this.getTelegraphType() == TelegraphType.NONE) {
            return null;
        }
        if (this.targetingMode == TargetingMode.AOE_SELF) {
            this.targetX = caster.field_70165_t;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v;
        } else if (this.targetingMode == TargetingMode.AOE_TARGET && target != null) {
            this.targetX = target.field_70165_t;
            this.targetY = target.field_70163_u;
            this.targetZ = target.field_70161_v;
        } else {
            this.targetX = caster.field_70165_t;
            this.targetY = caster.field_70163_u;
            this.targetZ = caster.field_70161_v;
        }
        double groundY = AbilitySlam.findGroundLevel(caster.field_70170_p, this.targetX, this.targetY, this.targetZ, 64);
        Telegraph telegraph = Telegraph.circle(this.radius);
        telegraph.setDurationTicks(this.windUpTicks);
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        telegraph.setWarningStartTick(Math.max(5, this.windUpTicks / 4));
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        telegraph.setGroundSearchRange(64);
        TelegraphInstance instance = new TelegraphInstance(telegraph, this.targetX, groundY, this.targetZ, caster.field_70177_z);
        instance.setCasterEntityId(caster.func_145782_y());
        if (!this.isPlayerCaster(caster) && this.targetingMode == TargetingMode.AOE_TARGET && target != null) {
            instance.setEntityIdToFollow(target.func_145782_y());
        }
        return instance;
    }

    @Override
    public float getTelegraphRadius() {
        return this.radius;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("radius", this.radius);
        nbt.func_74776_a("knockback", this.knockbackStrength);
        nbt.func_74776_a("leapSpeed", this.leapSpeed);
        nbt.func_74776_a("leapHeight", this.leapHeight);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.damage = nbt.func_74760_g("damage");
        this.radius = nbt.func_74760_g("radius");
        this.knockbackStrength = nbt.func_74760_g("knockback");
        this.leapSpeed = nbt.func_74760_g("leapSpeed");
        this.leapHeight = nbt.func_74760_g("leapHeight");
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
    public float getRadius() {
        return this.radius;
    }

    @Override
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public float getKnockbackStrength() {
        return this.knockbackStrength;
    }

    @Override
    public void setKnockbackStrength(float knockbackStrength) {
        this.knockbackStrength = knockbackStrength;
    }

    @Override
    public float getLeapSpeed() {
        return this.leapSpeed;
    }

    @Override
    public void setLeapSpeed(float leapSpeed) {
        this.leapSpeed = leapSpeed;
    }

    @Override
    public float getLeapHeight() {
        return this.leapHeight;
    }

    @Override
    public void setLeapHeight(float leapHeight) {
        this.leapHeight = leapHeight;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.row(FieldDef.floatField("gui.radius", this::getRadius, this::setRadius), FieldDef.floatField("ability.knockback", this::getKnockbackStrength, this::setKnockbackStrength)), FieldDef.section("ability.section.leap"), FieldDef.row(FieldDef.floatField("gui.speed", this::getLeapSpeed, this::setLeapSpeed), FieldDef.floatField("gui.height", this::getLeapHeight, this::setLeapHeight)), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }
}

