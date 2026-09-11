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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.type.AbilityMovement;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import noppes.npcs.api.ability.type.IAbilityDash;
import noppes.npcs.client.gui.builder.FieldDef;

public class AbilityDash
extends AbilityMovement
implements IAbilityDash {
    private static final Random RANDOM = new Random();
    private static final DashDirection[] AGGRESSIVE_DIRECTIONS = new DashDirection[]{DashDirection.FORWARD, DashDirection.DIAGONAL_FORWARD_LEFT, DashDirection.DIAGONAL_FORWARD_RIGHT};
    private static final DashDirection[] DEFENSIVE_DIRECTIONS = new DashDirection[]{DashDirection.LEFT, DashDirection.RIGHT, DashDirection.BACK, DashDirection.DIAGONAL_BACK_LEFT, DashDirection.DIAGONAL_BACK_RIGHT};
    private DashMode dashMode = DashMode.DEFENSIVE;
    private DashDirection dashDirection = DashDirection.FORWARD;
    private float dashDistance = 4.0f;
    private float dashSpeed = 0.5f;
    private float dashAngle = 0.0f;
    private transient DashDirection chosenDirection;
    private transient double preDashMotionX;
    private transient double preDashMotionZ;

    public AbilityDash() {
        this.typeId = "ability.cnpc.dash";
        this.name = "Dash";
        this.targetingMode = TargetingMode.AGGRO_TARGET;
        this.maxRange = 20.0f;
        this.minRange = 0.0f;
        this.lockMovement = LockMode.NO;
        this.cooldownTicks = 0;
        this.windUpTicks = 5;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
        this.windUpSound = "mob.bat.takeoff";
        this.defaultIconLayers = new Ability.DefaultIconLayer[]{new Ability.DefaultIconLayer("customnpcs:textures/gui/ability/dash.png")};
    }

    @Override
    public boolean hasDamage() {
        return false;
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
        this.preDashMotionX = caster.field_70159_w;
        this.preDashMotionZ = caster.field_70179_y;
        this.initMovement(caster, this.dashDistance, this.dashSpeed);
        if (this.dashMode == DashMode.DIRECTIONAL) {
            this.chosenDirection = this.dashDirection;
            float baseYaw = this.getBaseYaw(caster, target);
            float angleOffset = this.chosenDirection == DashDirection.CUSTOM ? this.dashAngle : this.chosenDirection.getAngleOffset();
            float dashYaw = baseYaw + angleOffset;
            this.setDirectionFromYaw(dashYaw);
        } else {
            DashDirection[] directions = this.dashMode == DashMode.AGGRESSIVE ? AGGRESSIVE_DIRECTIONS : DEFENSIVE_DIRECTIONS;
            this.chosenDirection = directions[RANDOM.nextInt(directions.length)];
            float baseYaw = this.getBaseYaw(caster, target);
            float dashYaw = baseYaw + this.chosenDirection.getAngleOffset();
            this.setDirectionFromYaw(dashYaw);
        }
        caster.field_70181_x = 0.2;
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.checkTimeout(tick)) {
            this.finishDash(caster);
            this.signalCompletion();
            return;
        }
        if (this.movementDirection == null) {
            this.finishDash(caster);
            this.signalCompletion();
            return;
        }
        if (this.checkStall(caster, tick)) {
            this.finishDash(caster);
            this.signalCompletion();
            return;
        }
        this.updatePrevPosition(caster);
        if (this.getDistanceTraveledSq(caster) >= (double)this.dashDistance * (double)this.dashDistance) {
            this.finishDash(caster);
            this.signalCompletion();
            return;
        }
        if (this.checkBlocked(caster, this.dashSpeed)) {
            this.finishDash(caster);
            this.signalCompletion();
            return;
        }
        if (this.dashMode == DashMode.DIRECTIONAL) {
            this.applyHorizontalMomentum(caster, this.preDashMotionX + this.movementDirection.field_72450_a * (double)this.dashSpeed, this.preDashMotionZ + this.movementDirection.field_72449_c * (double)this.dashSpeed);
        } else {
            this.applyVelocity(caster, this.dashSpeed);
        }
        if (!this.isPreview()) {
            caster.field_70170_p.func_72869_a("smoke", caster.field_70165_t, caster.field_70163_u + 0.5, caster.field_70161_v, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onComplete(EntityLivingBase caster, EntityLivingBase target) {
        this.finishDash(caster);
    }

    @Override
    public void onInterrupt(EntityLivingBase caster, DamageSource source, float damage) {
        this.finishDash(caster);
    }

    @Override
    public void cleanup() {
        super.cleanup();
        this.chosenDirection = null;
        this.preDashMotionX = 0.0;
        this.preDashMotionZ = 0.0;
    }

    @Override
    public void resetForBurst() {
        super.resetForBurst();
        this.chosenDirection = null;
        this.preDashMotionX = 0.0;
        this.preDashMotionZ = 0.0;
    }

    @Override
    public int getMaxPreviewDuration() {
        return (int)Math.ceil(this.dashDistance / this.dashSpeed) + 5;
    }

    @Override
    public float getTelegraphRadius() {
        return 0.0f;
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74778_a("dashMode", this.dashMode.name());
        nbt.func_74778_a("dashDirection", this.dashDirection.name());
        nbt.func_74776_a("dashDistance", this.dashDistance);
        nbt.func_74776_a("dashSpeed", this.dashSpeed);
        nbt.func_74776_a("dashAngle", this.dashAngle);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        try {
            this.dashMode = DashMode.valueOf(nbt.func_74779_i("dashMode"));
        }
        catch (Exception e) {
            this.dashMode = DashMode.DEFENSIVE;
        }
        try {
            this.dashDirection = DashDirection.valueOf(nbt.func_74779_i("dashDirection"));
        }
        catch (Exception e) {
            this.dashDirection = DashDirection.FORWARD;
        }
        this.dashDistance = nbt.func_74764_b("dashDistance") ? nbt.func_74760_g("dashDistance") : 4.0f;
        this.dashSpeed = nbt.func_74764_b("dashSpeed") ? Math.max(0.01f, nbt.func_74760_g("dashSpeed")) : 0.5f;
        this.dashAngle = nbt.func_74764_b("dashAngle") ? nbt.func_74760_g("dashAngle") : 0.0f;
    }

    public DashMode getDashModeEnum() {
        return this.dashMode;
    }

    public void setDashModeEnum(DashMode dashMode) {
        this.dashMode = dashMode;
    }

    @Override
    public int getDashMode() {
        return this.dashMode.ordinal();
    }

    @Override
    public void setDashMode(int mode) {
        DashMode[] values = DashMode.values();
        this.dashMode = mode >= 0 && mode < values.length ? values[mode] : DashMode.AGGRESSIVE;
    }

    @Override
    public float getDashDistance() {
        return this.dashDistance;
    }

    @Override
    public void setDashDistance(float dashDistance) {
        this.dashDistance = dashDistance;
    }

    @Override
    public float getDashSpeed() {
        return this.dashSpeed;
    }

    @Override
    public void setDashSpeed(float dashSpeed) {
        this.dashSpeed = dashSpeed;
    }

    @Override
    public float getDashAngle() {
        return this.dashAngle;
    }

    @Override
    public void setDashAngle(float dashAngle) {
        this.dashAngle = dashAngle;
    }

    public DashDirection getDashDirectionEnum() {
        return this.dashDirection;
    }

    public void setDashDirectionEnum(DashDirection dashDirection) {
        this.dashDirection = dashDirection;
    }

    @Override
    public int getDashDirection() {
        return this.dashDirection.ordinal();
    }

    @Override
    public void setDashDirection(int mode) {
        DashDirection[] values = DashDirection.values();
        this.dashDirection = mode >= 0 && mode < values.length ? values[mode] : DashDirection.FORWARD;
    }

    public DashDirection getChosenDirection() {
        return this.chosenDirection;
    }

    private void finishDash(EntityLivingBase caster) {
        if (this.dashMode == DashMode.DIRECTIONAL) {
            this.applyHorizontalMomentum(caster, this.preDashMotionX, this.preDashMotionZ);
        } else {
            this.stopMomentum(caster);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.addAll(Arrays.asList(FieldDef.enumField("ability.dashMode", DashMode.class, this::getDashModeEnum, this::setDashModeEnum).hover("ability.hover.dashMode"), FieldDef.row(FieldDef.floatField("ability.dashDistance", this::getDashDistance, this::setDashDistance), FieldDef.floatField("ability.dashSpeed", this::getDashSpeed, this::setDashSpeed)), FieldDef.row(FieldDef.enumField("ability.dashDirection", DashDirection.class, this::getDashDirectionEnum, this::setDashDirectionEnum), FieldDef.floatField("ability.dashAngle", this::getDashAngle, this::setDashAngle).min(Float.NEGATIVE_INFINITY).visibleWhen(() -> this.getDashDirectionEnum() == DashDirection.CUSTOM)).visibleWhen(() -> this.getDashModeEnum() == DashMode.DIRECTIONAL)));
    }

    public static enum DashDirection {
        FORWARD(0.0f),
        DIAGONAL_FORWARD_LEFT(45.0f),
        DIAGONAL_FORWARD_RIGHT(-45.0f),
        LEFT(90.0f),
        RIGHT(-90.0f),
        DIAGONAL_BACK_LEFT(135.0f),
        DIAGONAL_BACK_RIGHT(-135.0f),
        BACK(180.0f),
        CUSTOM(0.0f);

        private final float angleOffset;

        private DashDirection(float angleOffset) {
            this.angleOffset = angleOffset;
        }

        public float getAngleOffset() {
            return this.angleOffset;
        }

        public String toString() {
            switch (this) {
                case FORWARD: {
                    return "ability.dash.forward";
                }
                case DIAGONAL_FORWARD_LEFT: {
                    return "ability.dash.diagonalForwardLeft";
                }
                case DIAGONAL_FORWARD_RIGHT: {
                    return "ability.dash.diagonalForwardRight";
                }
                case LEFT: {
                    return "ability.dash.left";
                }
                case RIGHT: {
                    return "ability.dash.right";
                }
                case DIAGONAL_BACK_LEFT: {
                    return "ability.dash.diagonalBackLeft";
                }
                case DIAGONAL_BACK_RIGHT: {
                    return "ability.dash.diagonalBackRight";
                }
                case BACK: {
                    return "ability.dash.back";
                }
                case CUSTOM: {
                    return "ability.dash.custom";
                }
            }
            return this.name();
        }

        public static DashDirection fromOrdinal(int ordinal) {
            DashDirection[] values = DashDirection.values();
            if (ordinal >= 0 && ordinal < values.length) {
                return values[ordinal];
            }
            return FORWARD;
        }
    }

    public static enum DashMode {
        AGGRESSIVE,
        DEFENSIVE,
        DIRECTIONAL;


        public String toString() {
            switch (this) {
                case AGGRESSIVE: {
                    return "ability.dash.aggressive";
                }
                case DEFENSIVE: {
                    return "ability.dash.defensive";
                }
                case DIRECTIONAL: {
                    return "ability.dash.directional";
                }
            }
            return this.name();
        }
    }
}

