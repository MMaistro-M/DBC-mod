/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.telegraph;

import kamkeel.npcs.controllers.data.telegraph.TelegraphType;
import net.minecraft.nbt.NBTTagCompound;

public class Telegraph {
    private String id = "";
    private TelegraphType type = TelegraphType.CIRCLE;
    private float radius = 3.0f;
    private float innerRadius = 0.0f;
    private float length = 5.0f;
    private float width = 2.0f;
    private float angle = 45.0f;
    private int durationTicks = 40;
    private int color = -2130771968;
    private int warningColor = -1057030144;
    private int warningStartTick = 10;
    private boolean animated = true;
    private float heightOffset = 0.1f;
    private int groundSearchRange = 3;
    private boolean atTarget = true;
    private boolean followsTarget = false;
    private boolean followsCaster = false;
    private float offsetForward = 0.0f;

    public Telegraph() {
    }

    public Telegraph(String id, TelegraphType type) {
        this.id = id;
        this.type = type;
    }

    public Telegraph(Telegraph other) {
        this.id = other.id;
        this.type = other.type;
        this.radius = other.radius;
        this.innerRadius = other.innerRadius;
        this.length = other.length;
        this.width = other.width;
        this.angle = other.angle;
        this.durationTicks = other.durationTicks;
        this.color = other.color;
        this.warningColor = other.warningColor;
        this.warningStartTick = other.warningStartTick;
        this.animated = other.animated;
        this.heightOffset = other.heightOffset;
        this.groundSearchRange = other.groundSearchRange;
        this.atTarget = other.atTarget;
        this.followsTarget = other.followsTarget;
        this.followsCaster = other.followsCaster;
        this.offsetForward = other.offsetForward;
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74778_a("id", this.id);
        nbt.func_74778_a("type", this.type.name());
        nbt.func_74776_a("radius", this.radius);
        nbt.func_74776_a("innerRadius", this.innerRadius);
        nbt.func_74776_a("length", this.length);
        nbt.func_74776_a("width", this.width);
        nbt.func_74776_a("angle", this.angle);
        nbt.func_74768_a("durationTicks", this.durationTicks);
        nbt.func_74768_a("color", this.color);
        nbt.func_74768_a("warningColor", this.warningColor);
        nbt.func_74768_a("warningStartTick", this.warningStartTick);
        nbt.func_74757_a("animated", this.animated);
        nbt.func_74776_a("heightOffset", this.heightOffset);
        nbt.func_74768_a("groundSearchRange", this.groundSearchRange);
        nbt.func_74757_a("atTarget", this.atTarget);
        nbt.func_74757_a("followsTarget", this.followsTarget);
        nbt.func_74757_a("followsCaster", this.followsCaster);
        nbt.func_74776_a("offsetForward", this.offsetForward);
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        this.id = nbt.func_74779_i("id");
        if (nbt.func_74764_b("type")) {
            try {
                this.type = TelegraphType.valueOf(nbt.func_74779_i("type"));
            }
            catch (Exception e) {
                this.type = TelegraphType.CIRCLE;
            }
        }
        this.radius = nbt.func_74760_g("radius");
        this.innerRadius = nbt.func_74760_g("innerRadius");
        this.length = nbt.func_74760_g("length");
        this.width = nbt.func_74760_g("width");
        this.angle = nbt.func_74760_g("angle");
        this.durationTicks = nbt.func_74762_e("durationTicks");
        this.color = nbt.func_74762_e("color");
        this.warningColor = nbt.func_74762_e("warningColor");
        this.warningStartTick = nbt.func_74762_e("warningStartTick");
        this.animated = nbt.func_74767_n("animated");
        this.heightOffset = nbt.func_74760_g("heightOffset");
        if (nbt.func_74764_b("groundSearchRange")) {
            this.groundSearchRange = nbt.func_74762_e("groundSearchRange");
        }
        this.atTarget = nbt.func_74767_n("atTarget");
        this.followsTarget = nbt.func_74767_n("followsTarget");
        this.followsCaster = nbt.func_74767_n("followsCaster");
        this.offsetForward = nbt.func_74760_g("offsetForward");
    }

    public static Telegraph circle(float radius) {
        Telegraph t = new Telegraph("", TelegraphType.CIRCLE);
        t.radius = radius;
        return t;
    }

    public static Telegraph ring(float outerRadius, float innerRadius) {
        Telegraph t = new Telegraph("", TelegraphType.RING);
        t.radius = outerRadius;
        t.innerRadius = innerRadius;
        return t;
    }

    public static Telegraph line(float length, float width) {
        Telegraph t = new Telegraph("", TelegraphType.LINE);
        t.length = length;
        t.width = width;
        return t;
    }

    public static Telegraph cone(float length, float angle) {
        Telegraph t = new Telegraph("", TelegraphType.CONE);
        t.length = length;
        t.angle = angle;
        return t;
    }

    public static Telegraph cone(float length, float angle, float innerRadius) {
        Telegraph t = new Telegraph("", TelegraphType.CONE);
        t.length = length;
        t.angle = angle;
        t.innerRadius = innerRadius;
        return t;
    }

    public static Telegraph point() {
        return new Telegraph("", TelegraphType.POINT);
    }

    public static Telegraph square(float radius) {
        Telegraph t = new Telegraph("", TelegraphType.SQUARE);
        t.radius = radius;
        return t;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TelegraphType getType() {
        return this.type;
    }

    public void setType(TelegraphType type) {
        this.type = type;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getInnerRadius() {
        return this.innerRadius;
    }

    public void setInnerRadius(float innerRadius) {
        this.innerRadius = innerRadius;
    }

    public float getLength() {
        return this.length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getDurationTicks() {
        return this.durationTicks;
    }

    public void setDurationTicks(int durationTicks) {
        this.durationTicks = durationTicks;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getWarningColor() {
        return this.warningColor;
    }

    public void setWarningColor(int warningColor) {
        this.warningColor = warningColor;
    }

    public int getWarningStartTick() {
        return this.warningStartTick;
    }

    public void setWarningStartTick(int warningStartTick) {
        this.warningStartTick = warningStartTick;
    }

    public boolean isAnimated() {
        return this.animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public float getHeightOffset() {
        return this.heightOffset;
    }

    public void setHeightOffset(float heightOffset) {
        this.heightOffset = heightOffset;
    }

    public int getGroundSearchRange() {
        return this.groundSearchRange;
    }

    public void setGroundSearchRange(int groundSearchRange) {
        this.groundSearchRange = groundSearchRange;
    }

    public boolean isAtTarget() {
        return this.atTarget;
    }

    public void setAtTarget(boolean atTarget) {
        this.atTarget = atTarget;
    }

    public boolean isFollowsTarget() {
        return this.followsTarget;
    }

    public void setFollowsTarget(boolean followsTarget) {
        this.followsTarget = followsTarget;
    }

    public boolean isFollowsCaster() {
        return this.followsCaster;
    }

    public void setFollowsCaster(boolean followsCaster) {
        this.followsCaster = followsCaster;
    }

    public float getOffsetForward() {
        return this.offsetForward;
    }

    public void setOffsetForward(float offsetForward) {
        this.offsetForward = offsetForward;
    }
}

