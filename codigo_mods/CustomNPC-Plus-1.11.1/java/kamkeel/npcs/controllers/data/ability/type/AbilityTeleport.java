/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.ability.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetFilter;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.gui.AbilityFieldDefs;
import kamkeel.npcs.controllers.data.ability.util.AbilityTargetHelper;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.api.ability.type.IAbilityTeleport;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityTeleport
extends Ability
implements IAbilityTeleport {
    private static final Random RANDOM = new Random();
    private TeleportMode mode = TeleportMode.BLINK;
    private int blinkCount = 3;
    private int blinkDelayTicks = 10;
    private float blinkRadius = 8.0f;
    private float behindDistance = 2.0f;
    private boolean requireLineOfSight = true;
    private boolean damageAtStart = false;
    private boolean damageAtEnd = false;
    private float damage = 5.0f;
    private float damageRadius = 2.0f;
    private transient int currentBlink = 0;
    private transient int ticksSinceLastBlink = 0;

    public AbilityTeleport() {
        this.typeId = "ability.cnpc.teleport";
        this.name = "Teleport";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.allowedBy = UserType.NPC_ONLY;
        this.maxRange = 30.0f;
        this.minRange = 5.0f;
        this.cooldownTicks = 0;
        this.windUpTicks = 10;
        this.lockMovement = LockMode.NO;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
        this.windUpSound = "mob.endermen.portal";
        this.activeSound = "mob.endermen.portal";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/teleport.png")};
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
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        this.currentBlink = 0;
        this.ticksSinceLastBlink = this.blinkDelayTicks;
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        int blinkLimit;
        int n = blinkLimit = this.mode == TeleportMode.BLINK ? this.blinkCount : 1;
        if (this.currentBlink >= blinkLimit) {
            this.signalCompletion();
            return;
        }
        ++this.ticksSinceLastBlink;
        if (this.ticksSinceLastBlink >= this.blinkDelayTicks) {
            this.performBlink(caster, target, caster.field_70170_p);
            ++this.currentBlink;
            this.ticksSinceLastBlink = 0;
            if (this.currentBlink >= blinkLimit) {
                this.signalCompletion();
            }
        }
    }

    private void performBlink(EntityLivingBase caster, EntityLivingBase target, World world) {
        if (world.field_72995_K && !this.isPreview()) {
            return;
        }
        double oldX = caster.field_70165_t;
        double oldY = caster.field_70163_u;
        double oldZ = caster.field_70161_v;
        Vec3 destination = this.calculateDestination(caster, target, world);
        if (destination == null) {
            return;
        }
        if (!this.isPreview()) {
            boolean mustHaveLOS;
            boolean bl = mustHaveLOS = this.mode == TeleportMode.BEHIND || this.requireLineOfSight;
            if (mustHaveLOS && !this.hasLineOfSight(world, oldX, oldY + (double)caster.func_70047_e(), oldZ, destination.field_72450_a, destination.field_72448_b + (double)caster.func_70047_e(), destination.field_72449_c) && (destination = this.findValidPositionAlongLine(world, caster, oldX, oldY, oldZ, destination.field_72450_a, destination.field_72448_b, destination.field_72449_c)) == null) {
                return;
            }
            destination = this.findSafeDestination(world, oldX, oldY, oldZ, destination.field_72450_a, destination.field_72448_b, destination.field_72449_c);
            if (destination == null) {
                return;
            }
            if (this.damageAtStart) {
                this.dealDamageAt(caster, world, oldX, oldY, oldZ);
            }
            this.spawnTeleportParticles(world, oldX, oldY, oldZ);
        }
        if (this.isPreview()) {
            caster.func_70107_b(destination.field_72450_a, destination.field_72448_b, destination.field_72449_c);
            caster.field_70169_q = destination.field_72450_a;
            caster.field_70167_r = destination.field_72448_b;
            caster.field_70166_s = destination.field_72449_c;
        } else {
            caster.func_70634_a(destination.field_72450_a, destination.field_72448_b, destination.field_72449_c);
        }
        caster.field_70143_R = 0.0f;
        if (!this.isPreview()) {
            this.spawnTeleportParticles(world, destination.field_72450_a, destination.field_72448_b, destination.field_72449_c);
            if (this.damageAtEnd) {
                this.dealDamageAt(caster, world, destination.field_72450_a, destination.field_72448_b, destination.field_72449_c);
            }
        }
        if (target != null) {
            float newYaw;
            double dx = target.field_70165_t - destination.field_72450_a;
            double dz = target.field_70161_v - destination.field_72449_c;
            caster.field_70177_z = newYaw = (float)Math.toDegrees(Math.atan2(-dx, dz));
            caster.field_70759_as = newYaw;
        }
    }

    private void spawnTeleportParticles(World world, double x, double y, double z) {
        for (int i = 0; i < 20; ++i) {
            world.func_72869_a("portal", x + (RANDOM.nextDouble() - 0.5) * 2.0, y + RANDOM.nextDouble() * 2.0, z + (RANDOM.nextDouble() - 0.5) * 2.0, (RANDOM.nextDouble() - 0.5) * 0.5, RANDOM.nextDouble() * 0.5, (RANDOM.nextDouble() - 0.5) * 0.5);
        }
    }

    private Vec3 calculateDestination(EntityLivingBase caster, EntityLivingBase target, World world) {
        if (target != null) {
            switch (this.mode) {
                case BEHIND: {
                    double yaw = Math.toRadians(target.field_70177_z);
                    double newX = target.field_70165_t + Math.sin(yaw) * (double)this.behindDistance;
                    double newZ = target.field_70161_v - Math.cos(yaw) * (double)this.behindDistance;
                    double newY = target.field_70163_u;
                    return Vec3.func_72443_a((double)newX, (double)newY, (double)newZ);
                }
            }
            double angle = RANDOM.nextDouble() * Math.PI * 2.0;
            double dist = Math.min(this.blinkRadius, this.maxRange);
            double offset = RANDOM.nextDouble() * dist;
            double newX = target.field_70165_t + Math.cos(angle) * offset;
            double newZ = target.field_70161_v + Math.sin(angle) * offset;
            double newY = target.field_70163_u;
            return Vec3.func_72443_a((double)newX, (double)newY, (double)newZ);
        }
        return null;
    }

    private Vec3 findSafeDestination(World world, double oldX, double oldY, double oldZ, double destX, double destY, double destZ) {
        int oldBlockX = (int)Math.floor(oldX);
        int oldBlockY = (int)Math.floor(oldY);
        int oldBlockZ = (int)Math.floor(oldZ);
        int destBlockX = (int)Math.floor(destX);
        int destBlockY = (int)Math.floor(destY);
        int destBlockZ = (int)Math.floor(destZ);
        if (oldBlockX == destBlockX && oldBlockY == destBlockY && oldBlockZ == destBlockZ) {
            return null;
        }
        double safeY = this.findSafeYNear(world, destX, destY, destZ);
        Vec3 safe = Vec3.func_72443_a((double)destX, (double)safeY, (double)destZ);
        if (!this.isSafeLocation(world, (int)Math.floor(safe.field_72450_a), (int)Math.floor(safe.field_72448_b), (int)Math.floor(safe.field_72449_c))) {
            return null;
        }
        return safe;
    }

    private double findSafeYNear(World world, double x, double baseY, double z) {
        int[] offsets;
        for (int offset : offsets = new int[]{0, 1, -1, 2, -2}) {
            double y = baseY + (double)offset;
            if (!this.isSafeLocation(world, (int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z))) continue;
            return y;
        }
        return this.findSafeY(world, x, baseY, z);
    }

    private boolean hasLineOfSight(World world, double x1, double y1, double z1, double x2, double y2, double z2) {
        double step;
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (distance < 0.1) {
            return true;
        }
        dx /= distance;
        dy /= distance;
        dz /= distance;
        for (double d = step = 0.5; d < distance; d += step) {
            Block block;
            double checkX = x1 + dx * d;
            double checkY = y1 + dy * d;
            double checkZ = z1 + dz * d;
            int blockX = MathHelper.func_76128_c((double)checkX);
            int blockY = MathHelper.func_76128_c((double)checkY);
            int blockZ = MathHelper.func_76128_c((double)checkZ);
            if (blockY < 0 || blockY >= 256 || (block = world.func_147439_a(blockX, blockY, blockZ)) == null || !block.func_149688_o().func_76220_a() || !block.func_149662_c()) continue;
            return false;
        }
        return true;
    }

    private Vec3 findValidPositionAlongLine(World world, EntityLivingBase caster, double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        if (distance < 1.0) {
            return null;
        }
        dx /= distance;
        dy /= distance;
        dz /= distance;
        Vec3 lastValid = null;
        double step = 0.5;
        for (double d = 1.0; d < distance; d += step) {
            int blockZ;
            int blockY;
            double checkX = x1 + dx * d;
            double checkY = y1 + dy * d;
            double checkZ = z1 + dz * d;
            if (!this.hasLineOfSight(world, x1, y1 + (double)caster.func_70047_e(), z1, checkX, checkY + (double)caster.func_70047_e(), checkZ)) break;
            int blockX = MathHelper.func_76128_c((double)checkX);
            if (!this.isSafeLocation(world, blockX, blockY = MathHelper.func_76128_c((double)checkY), blockZ = MathHelper.func_76128_c((double)checkZ))) continue;
            lastValid = Vec3.func_72443_a((double)checkX, (double)checkY, (double)checkZ);
        }
        return lastValid;
    }

    private double findSafeY(World world, double x, double baseY, double z) {
        int blockX = MathHelper.func_76128_c((double)x);
        int blockZ = MathHelper.func_76128_c((double)z);
        int blockY = MathHelper.func_76128_c((double)baseY);
        for (int offset = 0; offset <= 5; ++offset) {
            if (this.isSafeLocation(world, blockX, blockY + offset, blockZ)) {
                return blockY + offset;
            }
            if (offset <= 0 || !this.isSafeLocation(world, blockX, blockY - offset, blockZ)) continue;
            return blockY - offset;
        }
        return baseY;
    }

    private boolean isSafeLocation(World world, int x, int y, int z) {
        if (y < 1 || y >= 255) {
            return false;
        }
        Block groundBlock = world.func_147439_a(x, y - 1, z);
        Block feetBlock = world.func_147439_a(x, y, z);
        Block headBlock = world.func_147439_a(x, y + 1, z);
        if (groundBlock == null || feetBlock == null || headBlock == null) {
            return false;
        }
        return groundBlock.func_149688_o().func_76220_a() && !feetBlock.func_149688_o().func_76220_a() && !headBlock.func_149688_o().func_76220_a();
    }

    private void dealDamageAt(EntityLivingBase caster, World world, double x, double y, double z) {
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(x - (double)this.damageRadius), (double)(y - 1.0), (double)(z - (double)this.damageRadius), (double)(x + (double)this.damageRadius), (double)(y + 2.0), (double)(z + (double)this.damageRadius));
        List entities = world.func_72872_a(EntityLivingBase.class, aabb);
        for (Entity entity : entities) {
            boolean wasHit;
            if (!(entity instanceof EntityLivingBase) || entity == caster || !AbilityTargetHelper.shouldAffect(caster, entity, TargetFilter.ENEMIES, false)) continue;
            EntityLivingBase living = (EntityLivingBase)entity;
            double dist = Math.sqrt(Math.pow(living.field_70165_t - x, 2.0) + Math.pow(living.field_70161_v - z, 2.0));
            if (!(dist <= (double)this.damageRadius) || !(wasHit = this.applyAbilityDamage(caster, living, this.damage, 0.0f))) continue;
            this.applyEffects(living);
        }
    }

    @Override
    public void reset() {
        super.reset();
        this.currentBlink = 0;
        this.ticksSinceLastBlink = 0;
    }

    @Override
    public float getTelegraphRadius() {
        return 0.0f;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74778_a("mode", this.mode.name());
        nbt.func_74768_a("blinkCount", this.blinkCount);
        nbt.func_74768_a("blinkDelayTicks", this.blinkDelayTicks);
        nbt.func_74776_a("blinkRadius", this.blinkRadius);
        nbt.func_74776_a("behindDistance", this.behindDistance);
        nbt.func_74757_a("requireLineOfSight", this.requireLineOfSight);
        nbt.func_74757_a("damageAtStart", this.damageAtStart);
        nbt.func_74757_a("damageAtEnd", this.damageAtEnd);
        nbt.func_74776_a("damage", this.damage);
        nbt.func_74776_a("damageRadius", this.damageRadius);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        try {
            this.mode = TeleportMode.valueOf(nbt.func_74779_i("mode"));
        }
        catch (Exception e) {
            this.mode = TeleportMode.BLINK;
        }
        this.blinkCount = nbt.func_74764_b("blinkCount") ? nbt.func_74762_e("blinkCount") : 3;
        this.blinkDelayTicks = nbt.func_74764_b("blinkDelayTicks") ? nbt.func_74762_e("blinkDelayTicks") : 10;
        this.blinkRadius = nbt.func_74760_g("blinkRadius");
        this.behindDistance = nbt.func_74760_g("behindDistance");
        this.requireLineOfSight = nbt.func_74767_n("requireLineOfSight");
        this.damageAtStart = nbt.func_74767_n("damageAtStart");
        this.damageAtEnd = nbt.func_74767_n("damageAtEnd");
        this.damage = nbt.func_74760_g("damage");
        this.damageRadius = nbt.func_74760_g("damageRadius");
    }

    public TeleportMode getModeEnum() {
        return this.mode;
    }

    public void setModeEnum(TeleportMode mode) {
        this.mode = mode;
    }

    @Override
    public int getMode() {
        return this.mode.ordinal();
    }

    @Override
    public void setMode(int mode) {
        TeleportMode[] values = TeleportMode.values();
        this.mode = mode >= 0 && mode < values.length ? values[mode] : TeleportMode.BLINK;
    }

    @Override
    public int getBlinkCount() {
        return this.blinkCount;
    }

    @Override
    public void setBlinkCount(int blinkCount) {
        this.blinkCount = blinkCount;
    }

    @Override
    public int getBlinkDelayTicks() {
        return this.blinkDelayTicks;
    }

    @Override
    public void setBlinkDelayTicks(int blinkDelayTicks) {
        this.blinkDelayTicks = blinkDelayTicks;
    }

    @Override
    public float getBlinkRadius() {
        return this.blinkRadius;
    }

    @Override
    public void setBlinkRadius(float blinkRadius) {
        this.blinkRadius = blinkRadius;
    }

    @Override
    public float getBehindDistance() {
        return this.behindDistance;
    }

    @Override
    public void setBehindDistance(float behindDistance) {
        this.behindDistance = behindDistance;
    }

    @Override
    public boolean isRequireLineOfSight() {
        return this.requireLineOfSight;
    }

    @Override
    public void setRequireLineOfSight(boolean requireLineOfSight) {
        this.requireLineOfSight = requireLineOfSight;
    }

    @Override
    public boolean isDamageAtStart() {
        return this.damageAtStart;
    }

    @Override
    public void setDamageAtStart(boolean damageAtStart) {
        this.damageAtStart = damageAtStart;
    }

    @Override
    public boolean isDamageAtEnd() {
        return this.damageAtEnd;
    }

    @Override
    public void setDamageAtEnd(boolean damageAtEnd) {
        this.damageAtEnd = damageAtEnd;
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
    public float getDamageRadius() {
        return this.damageRadius;
    }

    @Override
    public void setDamageRadius(float damageRadius) {
        this.damageRadius = damageRadius;
    }

    @Override
    public int getMaxPreviewDuration() {
        int blinkLimit = this.mode == TeleportMode.BLINK ? this.blinkCount : 1;
        return blinkLimit * this.blinkDelayTicks + 10;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.enumField("ability.mode", TeleportMode.class, this::getModeEnum, this::setModeEnum), FieldDef.floatField("ability.blinkRadius", this::getBlinkRadius, this::setBlinkRadius).visibleWhen(() -> this.getModeEnum() == TeleportMode.BLINK || this.getModeEnum() == TeleportMode.SINGLE), FieldDef.row(FieldDef.intField("ability.blinkCount", this::getBlinkCount, this::setBlinkCount).visibleWhen(() -> this.getModeEnum() == TeleportMode.BLINK), FieldDef.intField("ability.blinkDelay", this::getBlinkDelayTicks, this::setBlinkDelayTicks).visibleWhen(() -> this.getModeEnum() == TeleportMode.BLINK)), FieldDef.floatField("ability.behindDistance", this::getBehindDistance, this::setBehindDistance).visibleWhen(() -> this.getModeEnum() == TeleportMode.BEHIND), FieldDef.boolField("ability.lineOfSight", this::isRequireLineOfSight, this::setRequireLineOfSight).hover("ability.hover.lineOfSight").visibleWhen(() -> this.getModeEnum() == TeleportMode.BLINK || this.getModeEnum() == TeleportMode.SINGLE), FieldDef.section("ability.section.damage"), FieldDef.row(FieldDef.floatField("enchantment.damage", this::getDamage, this::setDamage), FieldDef.floatField("gui.radius", this::getDamageRadius, this::setDamageRadius)), FieldDef.row(FieldDef.boolField("ability.damageAtStart", this::isDamageAtStart, this::setDamageAtStart).hover("ability.hover.dmgAtStart"), FieldDef.boolField("ability.damageAtEnd", this::isDamageAtEnd, this::setDamageAtEnd).hover("ability.hover.dmgAtEnd")), AbilityFieldDefs.effectsListField("ability.effects", this::getEffects, this::setEffects)));
    }

    public static enum TeleportMode {
        BLINK,
        BEHIND,
        SINGLE;


        public String toString() {
            switch (this) {
                case BLINK: {
                    return "ability.teleport.blink";
                }
                case BEHIND: {
                    return "ability.teleport.behind";
                }
                case SINGLE: {
                    return "ability.teleport.single";
                }
            }
            return this.name();
        }
    }
}

