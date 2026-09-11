/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.roles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleInterface;
import noppes.npcs.util.ValueUtil;

public class RoleMount
extends RoleInterface {
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    private boolean storedReturnToStart;
    private float jumpStrength = 1.0f;
    private boolean allowSprint = true;
    private boolean flyingMountEnabled = false;
    private boolean hoverMode = false;
    private float flyingAscendSpeed = 0.6f;
    private float flyingDescendSpeed = 0.35f;

    public RoleMount(EntityNPCInterface npc) {
        super(npc);
        this.storedReturnToStart = npc.ais.returnToStart;
        npc.ais.returnToStart = false;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74776_a("MountOffsetX", this.offsetX);
        compound.func_74776_a("MountOffsetY", this.offsetY);
        compound.func_74776_a("MountOffsetZ", this.offsetZ);
        compound.func_74757_a("MountReturnFlag", this.storedReturnToStart);
        compound.func_74776_a("MountJumpStrength", this.jumpStrength);
        compound.func_74757_a("MountAllowSprint", this.allowSprint);
        compound.func_74757_a("MountAllowFlying", this.flyingMountEnabled);
        compound.func_74757_a("MountHoverMode", this.hoverMode);
        compound.func_74776_a("MountFlyingAscendSpeed", this.flyingAscendSpeed);
        compound.func_74776_a("MountFlyingDescendSpeed", this.flyingDescendSpeed);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.setOffsetX(compound.func_74760_g("MountOffsetX"));
        this.setOffsetY(compound.func_74760_g("MountOffsetY"));
        this.setOffsetZ(compound.func_74760_g("MountOffsetZ"));
        this.storedReturnToStart = compound.func_74764_b("MountReturnFlag") ? compound.func_74767_n("MountReturnFlag") : this.npc.ais.returnToStart;
        if (compound.func_74764_b("MountJumpStrength")) {
            this.setJumpStrength(compound.func_74760_g("MountJumpStrength"));
        } else {
            this.jumpStrength = 1.0f;
        }
        this.allowSprint = !compound.func_74764_b("MountAllowSprint") || compound.func_74767_n("MountAllowSprint");
        this.flyingMountEnabled = compound.func_74764_b("MountAllowFlying") && compound.func_74767_n("MountAllowFlying");
        boolean bl = this.hoverMode = compound.func_74764_b("MountHoverMode") && compound.func_74767_n("MountHoverMode");
        if (compound.func_74764_b("MountFlyingAscendSpeed")) {
            this.setFlyingAscendSpeed(compound.func_74760_g("MountFlyingAscendSpeed"));
        } else {
            this.flyingAscendSpeed = 0.6f;
        }
        if (compound.func_74764_b("MountFlyingDescendSpeed")) {
            this.setFlyingDescendSpeed(compound.func_74760_g("MountFlyingDescendSpeed"));
        } else if (compound.func_74764_b("MountFlyingFallSpeed")) {
            this.setFlyingDescendSpeed(compound.func_74760_g("MountFlyingFallSpeed"));
        } else {
            this.flyingDescendSpeed = 0.35f;
        }
        this.npc.ais.returnToStart = false;
    }

    @Override
    public void interact(EntityPlayer player) {
        if (player == null || this.npc.field_70170_p.field_72995_K) {
            return;
        }
        if (this.npc.field_70153_n != null && this.npc.field_70153_n != player) {
            return;
        }
        if (player.field_70154_o != null && player.field_70154_o != this.npc) {
            player.func_70078_a(null);
        }
        player.field_70143_R = 0.0f;
        this.npc.field_70143_R = 0.0f;
        player.func_70078_a((Entity)this.npc);
        this.npc.func_70661_as().func_75499_g();
        this.npc.func_70624_b(null);
        this.npc.func_70604_c(null);
    }

    @Override
    public boolean aiShouldExecute() {
        if (this.npc.ais.returnToStart) {
            this.storedReturnToStart = true;
            this.npc.ais.returnToStart = false;
        }
        return false;
    }

    public void setOffsetX(float value) {
        this.offsetX = this.clamp(value);
    }

    public void setOffsetY(float value) {
        this.offsetY = this.clamp(value);
    }

    public void setOffsetZ(float value) {
        this.offsetZ = this.clamp(value);
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public void setJumpStrength(float value) {
        this.jumpStrength = ValueUtil.clamp(value, 0.1f, 3.0f);
    }

    public float getJumpStrength() {
        return this.jumpStrength;
    }

    public void setSprintAllowed(boolean allowSprint) {
        this.allowSprint = allowSprint;
    }

    public boolean isSprintAllowed() {
        return this.allowSprint;
    }

    public void setFlyingMountEnabled(boolean enabled) {
        this.flyingMountEnabled = enabled;
    }

    public boolean isFlyingMountEnabled() {
        return this.flyingMountEnabled;
    }

    public void setHoverModeEnabled(boolean enabled) {
        this.hoverMode = enabled;
    }

    public boolean isHoverModeEnabled() {
        return this.hoverMode;
    }

    public void setFlyingAscendSpeed(float value) {
        this.flyingAscendSpeed = ValueUtil.clamp(value, 0.1f, 3.0f);
    }

    public float getFlyingAscendSpeed() {
        return this.flyingAscendSpeed;
    }

    public void setFlyingDescendSpeed(float value) {
        this.flyingDescendSpeed = ValueUtil.clamp(value, 0.05f, 3.0f);
    }

    public float getFlyingDescendSpeed() {
        return this.flyingDescendSpeed;
    }

    public void setReturnToStartPreference(boolean value) {
        this.storedReturnToStart = value;
        this.npc.ais.returnToStart = false;
    }

    public boolean getReturnToStartPreference() {
        return this.storedReturnToStart;
    }

    private float clamp(float value) {
        return ValueUtil.clamp(value, -5.0f, 5.0f);
    }

    public void resetOffsets() {
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.0f;
    }

    public void onDisable() {
        if (this.npc.field_70153_n != null) {
            this.npc.field_70153_n.func_70078_a(null);
        }
        this.npc.ais.returnToStart = this.storedReturnToStart;
    }

    @Override
    public void delete() {
        this.onDisable();
    }
}

