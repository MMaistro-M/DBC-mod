/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.LockMode;
import kamkeel.npcs.controllers.data.ability.enums.TargetingMode;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import kamkeel.npcs.controllers.data.telegraph.TelegraphInstance;
import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.ICustomAbility;
import noppes.npcs.client.gui.builder.FieldDef;

public class CustomAbility
extends Ability
implements ICustomAbility {
    private int durationTicks = 20;
    private float tRadius = 5.0f;
    private float tInnerRadius = 0.0f;
    private float tLength = 5.0f;
    private float tWidth = 2.0f;
    private float tAngle = 45.0f;
    private int telegraphActiveTicks = 0;
    private boolean syncTelegraphWithDuration = true;

    public CustomAbility() {
        this.typeId = "ability.cnpc.custom";
        this.name = "Custom";
        this.targetingMode = TargetingMode.SELF;
        this.lockMovement = LockMode.NO;
        this.cooldownTicks = 0;
        this.windUpTicks = 0;
        this.telegraphType = TelegraphType.NONE;
        this.showTelegraph = false;
    }

    @Override
    public void onExecute(EntityLivingBase caster, EntityLivingBase target) {
        if (this.durationTicks <= 0) {
            this.signalCompletion();
        }
    }

    @Override
    public void onActiveTick(EntityLivingBase caster, EntityLivingBase target, int tick) {
        if (this.durationTicks > 0 && tick >= this.durationTicks) {
            this.signalCompletion();
        }
    }

    @Override
    public boolean hasDamage() {
        return false;
    }

    @Override
    public boolean isConcurrentCapable() {
        return true;
    }

    @Override
    public boolean isTargetingModeLocked() {
        return false;
    }

    @Override
    public boolean allowFreeOnCast() {
        return true;
    }

    @Override
    public boolean hasOwnTelegraphControls() {
        return true;
    }

    @Override
    public boolean keepTelegraphDuringActive() {
        return this.getResolvedTelegraphActiveTicks() > 0;
    }

    public int getResolvedTelegraphActiveTicks() {
        if (this.syncTelegraphWithDuration) {
            return this.durationTicks;
        }
        return this.telegraphActiveTicks;
    }

    @Override
    public TelegraphInstance createTelegraph(EntityLivingBase caster, EntityLivingBase target) {
        Telegraph telegraph;
        double z;
        double y;
        double x;
        boolean positionAtCaster;
        if (!this.isShowTelegraph() || this.getTelegraphType() == TelegraphType.NONE) {
            return null;
        }
        float yaw = caster.field_70177_z;
        TelegraphType tType = this.getTelegraphType();
        boolean bl = positionAtCaster = this.targetingMode == TargetingMode.AOE_SELF || this.targetingMode == TargetingMode.SELF || tType == TelegraphType.LINE || tType == TelegraphType.CONE;
        if (positionAtCaster) {
            x = caster.field_70165_t;
            y = CustomAbility.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
            z = caster.field_70161_v;
        } else if (target != null) {
            x = target.field_70165_t;
            y = CustomAbility.findGroundLevel(caster.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v);
            z = target.field_70161_v;
        } else {
            x = caster.field_70165_t;
            y = CustomAbility.findGroundLevel(caster.field_70170_p, caster.field_70165_t, caster.field_70163_u, caster.field_70161_v);
            z = caster.field_70161_v;
        }
        switch (tType) {
            case CIRCLE: {
                telegraph = Telegraph.circle(this.tRadius);
                break;
            }
            case RING: {
                telegraph = Telegraph.ring(this.tRadius, this.tInnerRadius);
                break;
            }
            case LINE: {
                telegraph = Telegraph.line(this.tLength, this.tWidth);
                break;
            }
            case CONE: {
                telegraph = Telegraph.cone(this.tLength, this.tAngle, this.tInnerRadius);
                break;
            }
            case POINT: {
                telegraph = new Telegraph("", TelegraphType.POINT);
                break;
            }
            case SQUARE: {
                telegraph = Telegraph.square(this.tRadius);
                break;
            }
            default: {
                return null;
            }
        }
        int activeTicks = this.getResolvedTelegraphActiveTicks();
        int totalDuration = this.windUpTicks + activeTicks;
        telegraph.setDurationTicks(Math.max(1, totalDuration));
        telegraph.setColor(this.windUpColor);
        telegraph.setWarningColor(this.activeColor);
        if (this.windUpTicks > 0) {
            telegraph.setWarningStartTick(this.windUpTicks);
        } else {
            telegraph.setWarningStartTick(0);
        }
        telegraph.setHeightOffset(this.telegraphHeightOffset);
        TelegraphInstance instance = new TelegraphInstance(telegraph, x, y, z, yaw);
        instance.setCasterEntityId(caster.func_145782_y());
        if (positionAtCaster) {
            instance.setEntityIdToFollow(caster.func_145782_y());
            if (!(tType != TelegraphType.LINE && tType != TelegraphType.CONE || this.isRotationLockedDuringWindup())) {
                if (target != null) {
                    instance.setTargetEntityId(target.func_145782_y());
                } else {
                    instance.setTrackFollowedEntityYaw(true);
                }
            }
        } else if (target != null) {
            instance.setEntityIdToFollow(target.func_145782_y());
        }
        return instance;
    }

    @Override
    public float getTelegraphRadius() {
        return this.tRadius;
    }

    @Override
    public float getTelegraphInnerRadius() {
        return this.tInnerRadius;
    }

    @Override
    public float getTelegraphLength() {
        return this.tLength;
    }

    @Override
    public float getTelegraphWidth() {
        return this.tWidth;
    }

    @Override
    public float getTelegraphAngle() {
        return this.tAngle;
    }

    @Override
    public int getDurationTicks() {
        return this.durationTicks;
    }

    @Override
    public void setDurationTicks(int ticks) {
        this.durationTicks = Math.max(0, ticks);
    }

    public float getTRadius() {
        return this.tRadius;
    }

    public void setTRadius(float radius) {
        this.tRadius = Math.max(0.0f, radius);
    }

    public float getTInnerRadius() {
        return this.tInnerRadius;
    }

    public void setTInnerRadius(float innerRadius) {
        this.tInnerRadius = Math.max(0.0f, innerRadius);
    }

    public float getTLength() {
        return this.tLength;
    }

    public void setTLength(float length) {
        this.tLength = Math.max(0.0f, length);
    }

    public float getTWidth() {
        return this.tWidth;
    }

    public void setTWidth(float width) {
        this.tWidth = Math.max(0.0f, width);
    }

    public float getTAngle() {
        return this.tAngle;
    }

    public void setTAngle(float angle) {
        this.tAngle = Math.max(0.0f, angle);
    }

    @Override
    public int getTelegraphActiveTicks() {
        return this.telegraphActiveTicks;
    }

    @Override
    public void setTelegraphActiveTicks(int ticks) {
        this.telegraphActiveTicks = Math.max(0, ticks);
    }

    @Override
    public boolean isSyncTelegraphWithDuration() {
        return this.syncTelegraphWithDuration;
    }

    @Override
    public void setSyncTelegraphWithDuration(boolean sync) {
        this.syncTelegraphWithDuration = sync;
    }

    @Override
    public int getTelegraphShapeType() {
        return this.getTelegraphType().ordinal();
    }

    @Override
    public void setTelegraphShapeType(int type) {
        TelegraphType[] values = TelegraphType.values();
        if (type >= 0 && type < values.length) {
            this.setTelegraphType(values[type]);
        }
    }

    @Override
    public void setTelegraphRadius(float radius) {
        this.tRadius = Math.max(0.0f, radius);
    }

    @Override
    public void setTelegraphInnerRadius(float innerRadius) {
        this.tInnerRadius = Math.max(0.0f, innerRadius);
    }

    @Override
    public void setTelegraphLength(float length) {
        this.tLength = Math.max(0.0f, length);
    }

    @Override
    public void setTelegraphWidth(float width) {
        this.tWidth = Math.max(0.0f, width);
    }

    @Override
    public void setTelegraphAngle(float angle) {
        this.tAngle = Math.max(0.0f, angle);
    }

    @Override
    public int getTargetingModeType() {
        return this.getTargetingMode().ordinal();
    }

    @Override
    public void setTargetingModeType(int type) {
        TargetingMode[] values = TargetingMode.values();
        if (type >= 0 && type < values.length) {
            this.setTargetingMode(values[type]);
        }
    }

    @Override
    public void writeTypeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("durationTicks", this.durationTicks);
        nbt.func_74776_a("tRadius", this.tRadius);
        nbt.func_74776_a("tInnerRadius", this.tInnerRadius);
        nbt.func_74776_a("tLength", this.tLength);
        nbt.func_74776_a("tWidth", this.tWidth);
        nbt.func_74776_a("tAngle", this.tAngle);
        nbt.func_74768_a("telegraphActiveTicks", this.telegraphActiveTicks);
        nbt.func_74757_a("syncTelegraphWithDuration", this.syncTelegraphWithDuration);
    }

    @Override
    public void readTypeNBT(NBTTagCompound nbt) {
        this.durationTicks = Math.max(0, nbt.func_74762_e("durationTicks"));
        this.tRadius = nbt.func_74764_b("tRadius") ? nbt.func_74760_g("tRadius") : 5.0f;
        this.tInnerRadius = nbt.func_74764_b("tInnerRadius") ? nbt.func_74760_g("tInnerRadius") : 0.0f;
        this.tLength = nbt.func_74764_b("tLength") ? nbt.func_74760_g("tLength") : 5.0f;
        this.tWidth = nbt.func_74764_b("tWidth") ? nbt.func_74760_g("tWidth") : 2.0f;
        this.tAngle = nbt.func_74764_b("tAngle") ? nbt.func_74760_g("tAngle") : 45.0f;
        this.telegraphActiveTicks = nbt.func_74764_b("telegraphActiveTicks") ? nbt.func_74762_e("telegraphActiveTicks") : 0;
        this.syncTelegraphWithDuration = nbt.func_74764_b("syncTelegraphWithDuration") ? nbt.func_74767_n("syncTelegraphWithDuration") : true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void getAbilityDefinitions(List<FieldDef> defs) {
        defs.add(FieldDef.enumField("ability.allowedBy", UserType.class, this::getAllowedBy, this::setAllowedBy));
        defs.add(FieldDef.enumField("ability.targetingMode", TargetingMode.class, this::getTargetingMode, this::setTargetingMode));
        defs.add(FieldDef.section("ability.section.toggle"));
        defs.add(FieldDef.boolField("ability.toggleEnabled", this::isToggleable, enabled -> this.setToggleStates(enabled != false ? Math.max(1, this.getToggleStates()) : 0)));
        defs.add(FieldDef.intField("ability.toggleStates", this::getToggleStates, this::setToggleStates).range(1.0f, 10.0f).visibleWhen(this::isToggleable));
        defs.add(FieldDef.boolField("ability.hasActiveToggle", this::hasActiveToggle, this::setHasActiveToggle).visibleWhen(this::isToggleable));
        defs.add(FieldDef.section("ability.section.execution").visibleWhen(() -> !this.isToggleable()));
        defs.add(FieldDef.intField("ability.duration", this::getDurationTicks, this::setDurationTicks).range(0.0f, 6000.0f).visibleWhen(() -> !this.isToggleable()));
        defs.add(FieldDef.section("ability.section.telegraph").tab("Effects"));
        defs.add(FieldDef.enumField("ability.telegraphShape", TelegraphType.class, this::getTelegraphType, this::setTelegraphType).tab("Effects").hover("ability.hover.telegraphShape"));
        defs.add(FieldDef.boolField("ability.showTelegraph", this::isShowTelegraph, this::setShowTelegraph).tab("Effects").visibleWhen(this::hasTelegraphShape).hover("ability.hover.showTelegraph"));
        defs.add(FieldDef.colorSubGui("ability.windUpColor", this::getWindUpColor, this::setWindUpColor).tab("Effects").visibleWhen(this::isTelegraphVisible).hover("ability.hover.windUpColor"));
        defs.add(FieldDef.colorSubGui("ability.activeColor", this::getActiveColor, this::setActiveColor).tab("Effects").visibleWhen(this::isTelegraphVisible).hover("ability.hover.activeColor"));
        defs.add(FieldDef.floatField("ability.telegraphHeight", this::getTelegraphHeightOffset, this::setTelegraphHeightOffset).tab("Effects").visibleWhen(this::isTelegraphVisible).hover("ability.hover.telegraphHeight"));
        defs.add(FieldDef.row(FieldDef.intField("ability.telegraphActiveTicks", this::getTelegraphActiveTicks, this::setTelegraphActiveTicks).range(0.0f, 6000.0f).hover("ability.hover.telegraphActiveTicks"), FieldDef.boolField("ability.syncTelegraph", this::isSyncTelegraphWithDuration, this::setSyncTelegraphWithDuration).hover("ability.hover.syncTelegraph")).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && !this.isSyncTelegraphWithDuration()));
        defs.add(FieldDef.row(FieldDef.labelField("ability.telegraphActiveTicks", () -> this.getResolvedTelegraphActiveTicks() + "t"), FieldDef.boolField("ability.syncTelegraph", this::isSyncTelegraphWithDuration, this::setSyncTelegraphWithDuration).hover("ability.hover.syncTelegraph")).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && this.isSyncTelegraphWithDuration()));
        defs.add(FieldDef.floatField("ability.telegraphRadius", this::getTRadius, this::setTRadius).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && this.needsRadius()).hover("ability.hover.telegraphRadius"));
        defs.add(FieldDef.floatField("ability.telegraphInnerRadius", this::getTInnerRadius, this::setTInnerRadius).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && this.needsInnerRadius()).hover("ability.hover.telegraphInnerRadius"));
        defs.add(FieldDef.floatField("ability.telegraphLength", this::getTLength, this::setTLength).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && this.needsLength()).hover("ability.hover.telegraphLength"));
        defs.add(FieldDef.floatField("ability.telegraphWidth", this::getTWidth, this::setTWidth).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && this.needsWidth()).hover("ability.hover.telegraphWidth"));
        defs.add(FieldDef.floatField("ability.telegraphAngle", this::getTAngle, this::setTAngle).tab("Effects").visibleWhen(() -> this.isTelegraphVisible() && this.needsAngle()).hover("ability.hover.telegraphAngle"));
    }

    private boolean hasTelegraphShape() {
        return this.getTelegraphType() != TelegraphType.NONE;
    }

    private boolean isTelegraphVisible() {
        return this.hasTelegraphShape() && this.isShowTelegraph();
    }

    private boolean needsRadius() {
        TelegraphType t = this.getTelegraphType();
        return t == TelegraphType.CIRCLE || t == TelegraphType.RING || t == TelegraphType.SQUARE;
    }

    private boolean needsInnerRadius() {
        TelegraphType t = this.getTelegraphType();
        return t == TelegraphType.RING || t == TelegraphType.CONE;
    }

    private boolean needsLength() {
        TelegraphType t = this.getTelegraphType();
        return t == TelegraphType.LINE || t == TelegraphType.CONE;
    }

    private boolean needsWidth() {
        return this.getTelegraphType() == TelegraphType.LINE;
    }

    private boolean needsAngle() {
        return this.getTelegraphType() == TelegraphType.CONE;
    }
}

