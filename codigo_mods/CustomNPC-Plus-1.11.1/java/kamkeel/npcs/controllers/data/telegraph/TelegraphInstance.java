/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package kamkeel.npcs.controllers.data.telegraph;

import java.util.UUID;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.telegraph.Telegraph;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class TelegraphInstance {
    private String instanceId = UUID.randomUUID().toString().substring(0, 8);
    private Telegraph telegraph;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private double prevX;
    private double prevY;
    private double prevZ;
    private float prevYaw;
    private int entityIdToFollow = -1;
    private int casterEntityId = -1;
    private int targetEntityId = -1;
    private boolean trackFollowedEntityYaw = false;
    private int remainingTicks;
    private int totalTicks;
    private boolean isWarning = false;
    private transient World world;
    private transient int dimensionId;

    public TelegraphInstance() {
    }

    public TelegraphInstance(Telegraph telegraph, double x, double y, double z, float yaw) {
        this();
        this.telegraph = new Telegraph(telegraph);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.prevYaw = yaw;
        this.remainingTicks = telegraph.getDurationTicks();
        this.totalTicks = telegraph.getDurationTicks();
    }

    public boolean tick(World world) {
        Entity target;
        Entity entity;
        if (this.remainingTicks <= 0) {
            return false;
        }
        this.prevX = this.x;
        this.prevY = this.y;
        this.prevZ = this.z;
        this.prevYaw = this.yaw;
        --this.remainingTicks;
        if (this.entityIdToFollow >= 0 && world != null && (entity = world.func_73045_a(this.entityIdToFollow)) != null) {
            this.x = entity.field_70165_t;
            this.z = entity.field_70161_v;
            int searchRange = this.telegraph != null ? this.telegraph.getGroundSearchRange() : 3;
            this.y = Ability.findGroundLevel(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, searchRange);
            if (this.trackFollowedEntityYaw && this.targetEntityId < 0) {
                this.yaw = entity.field_70177_z;
            }
        }
        if (this.targetEntityId >= 0 && world != null && (target = world.func_73045_a(this.targetEntityId)) != null) {
            double dx = target.field_70165_t - this.x;
            double dz = target.field_70161_v - this.z;
            this.yaw = (float)(Math.atan2(-dx, dz) * 180.0 / Math.PI);
        }
        if (!this.isWarning && this.telegraph != null && this.remainingTicks <= this.telegraph.getWarningStartTick()) {
            this.isWarning = true;
        }
        return true;
    }

    public float getProgress() {
        if (this.totalTicks <= 0) {
            return 1.0f;
        }
        return 1.0f - (float)this.remainingTicks / (float)this.totalTicks;
    }

    public int getCurrentColor() {
        if (this.telegraph == null) {
            return -2130771968;
        }
        return this.isWarning ? this.telegraph.getWarningColor() : this.telegraph.getColor();
    }

    public int getAnimatedColor(float partialTicks) {
        int baseColor = this.getCurrentColor();
        if (this.telegraph == null || !this.telegraph.isAnimated()) {
            return baseColor;
        }
        float time = (float)(this.totalTicks - this.remainingTicks) + partialTicks;
        float breathPhase = (float)Math.sin((double)time * 0.08);
        float pulse = 0.5f + breathPhase * breathPhase * 0.5f * Math.signum(breathPhase);
        int alpha = baseColor >> 24 & 0xFF;
        int r = baseColor >> 16 & 0xFF;
        int g = baseColor >> 8 & 0xFF;
        int b = baseColor & 0xFF;
        alpha = (int)((float)alpha * pulse);
        return alpha << 24 | r << 16 | g << 8 | b;
    }

    public double getInterpolatedX(float partialTicks) {
        return this.prevX + (this.x - this.prevX) * (double)partialTicks;
    }

    public double getInterpolatedY(float partialTicks) {
        return this.prevY + (this.y - this.prevY) * (double)partialTicks;
    }

    public double getInterpolatedZ(float partialTicks) {
        return this.prevZ + (this.z - this.prevZ) * (double)partialTicks;
    }

    public float getInterpolatedYaw(float partialTicks) {
        float diff;
        for (diff = this.yaw - this.prevYaw; diff > 180.0f; diff -= 360.0f) {
        }
        while (diff < -180.0f) {
            diff += 360.0f;
        }
        return this.prevYaw + diff * partialTicks;
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74778_a("instanceId", this.instanceId);
        if (this.telegraph != null) {
            nbt.func_74782_a("telegraph", (NBTBase)this.telegraph.writeNBT());
        }
        nbt.func_74780_a("x", this.x);
        nbt.func_74780_a("y", this.y);
        nbt.func_74780_a("z", this.z);
        nbt.func_74776_a("yaw", this.yaw);
        nbt.func_74768_a("entityIdToFollow", this.entityIdToFollow);
        nbt.func_74768_a("casterEntityId", this.casterEntityId);
        nbt.func_74768_a("targetEntityId", this.targetEntityId);
        nbt.func_74757_a("trackFollowedYaw", this.trackFollowedEntityYaw);
        nbt.func_74768_a("remainingTicks", this.remainingTicks);
        nbt.func_74768_a("totalTicks", this.totalTicks);
        nbt.func_74757_a("isWarning", this.isWarning);
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        this.instanceId = nbt.func_74779_i("instanceId");
        if (nbt.func_74764_b("telegraph")) {
            this.telegraph = new Telegraph();
            this.telegraph.readNBT(nbt.func_74775_l("telegraph"));
        }
        this.x = nbt.func_74769_h("x");
        this.y = nbt.func_74769_h("y");
        this.z = nbt.func_74769_h("z");
        this.yaw = nbt.func_74760_g("yaw");
        this.entityIdToFollow = nbt.func_74762_e("entityIdToFollow");
        this.casterEntityId = nbt.func_74762_e("casterEntityId");
        this.targetEntityId = nbt.func_74762_e("targetEntityId");
        this.trackFollowedEntityYaw = nbt.func_74764_b("trackFollowedYaw") && nbt.func_74767_n("trackFollowedYaw");
        this.remainingTicks = nbt.func_74762_e("remainingTicks");
        this.totalTicks = nbt.func_74762_e("totalTicks");
        this.isWarning = nbt.func_74767_n("isWarning");
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Telegraph getTelegraph() {
        return this.telegraph;
    }

    public void setTelegraph(Telegraph telegraph) {
        this.telegraph = telegraph;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public int getEntityIdToFollow() {
        return this.entityIdToFollow;
    }

    public void setEntityIdToFollow(int entityIdToFollow) {
        this.entityIdToFollow = entityIdToFollow;
    }

    public void lockPosition() {
        this.entityIdToFollow = -1;
        this.targetEntityId = -1;
    }

    public int getCasterEntityId() {
        return this.casterEntityId;
    }

    public void setCasterEntityId(int casterEntityId) {
        this.casterEntityId = casterEntityId;
    }

    public int getTargetEntityId() {
        return this.targetEntityId;
    }

    public void setTargetEntityId(int targetEntityId) {
        this.targetEntityId = targetEntityId;
    }

    public boolean isTrackFollowedEntityYaw() {
        return this.trackFollowedEntityYaw;
    }

    public void setTrackFollowedEntityYaw(boolean track) {
        this.trackFollowedEntityYaw = track;
    }

    public int getRemainingTicks() {
        return this.remainingTicks;
    }

    public void setRemainingTicks(int remainingTicks) {
        this.remainingTicks = remainingTicks;
    }

    public int getTotalTicks() {
        return this.totalTicks;
    }

    public void setTotalTicks(int totalTicks) {
        this.totalTicks = totalTicks;
    }

    public boolean isWarning() {
        return this.isWarning;
    }

    public void setWarning(boolean warning) {
        this.isWarning = warning;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
        if (world != null) {
            this.dimensionId = world.field_73011_w.field_76574_g;
        }
    }

    public int getDimensionId() {
        return this.dimensionId;
    }

    public void setDimensionId(int dimensionId) {
        this.dimensionId = dimensionId;
    }
}

