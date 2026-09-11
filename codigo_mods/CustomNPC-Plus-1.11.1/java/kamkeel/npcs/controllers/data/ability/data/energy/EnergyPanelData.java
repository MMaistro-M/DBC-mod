/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyPanelData {
    public float panelWidth = 3.0f;
    public float panelHeight = 3.0f;
    public float heightOffset = 0.0f;
    public float offsetX = 0.0f;
    public float offsetZ = 0.0f;
    public boolean launching = false;
    public float launchSpeed = 0.5f;
    public float launchDamage = 8.0f;
    public float launchKnockback = 2.0f;

    public EnergyPanelData() {
    }

    public EnergyPanelData(float panelWidth, float panelHeight, float heightOffset) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.heightOffset = heightOffset;
    }

    public float getPanelWidth() {
        return this.panelWidth;
    }

    public void setPanelWidth(float panelWidth) {
        this.panelWidth = Math.max(0.5f, panelWidth);
    }

    public float getPanelHeight() {
        return this.panelHeight;
    }

    public void setPanelHeight(float panelHeight) {
        this.panelHeight = Math.max(0.5f, panelHeight);
    }

    public float getHeightOffset() {
        return this.heightOffset;
    }

    public void setHeightOffset(float heightOffset) {
        this.heightOffset = heightOffset;
    }

    public boolean isLaunching() {
        return this.launching;
    }

    public void setLaunching(boolean launching) {
        this.launching = launching;
    }

    public float getLaunchSpeed() {
        return this.launchSpeed;
    }

    public void setLaunchSpeed(float launchSpeed) {
        this.launchSpeed = Math.max(0.1f, launchSpeed);
    }

    public float getLaunchDamage() {
        return this.launchDamage;
    }

    public void setLaunchDamage(float launchDamage) {
        this.launchDamage = Math.max(0.0f, launchDamage);
    }

    public float getLaunchKnockback() {
        return this.launchKnockback;
    }

    public void setLaunchKnockback(float launchKnockback) {
        this.launchKnockback = Math.max(0.0f, launchKnockback);
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("panelWidth", this.panelWidth);
        nbt.func_74776_a("panelHeight", this.panelHeight);
        nbt.func_74776_a("panelHeightOffset", this.heightOffset);
        nbt.func_74776_a("panelOffsetX", this.offsetX);
        nbt.func_74776_a("panelOffsetZ", this.offsetZ);
        nbt.func_74757_a("panelLaunching", this.launching);
        nbt.func_74776_a("panelLaunchSpeed", this.launchSpeed);
        nbt.func_74776_a("panelLaunchDamage", this.launchDamage);
        nbt.func_74776_a("panelLaunchKnockback", this.launchKnockback);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.panelWidth = nbt.func_74764_b("panelWidth") ? nbt.func_74760_g("panelWidth") : 3.0f;
        this.panelHeight = nbt.func_74764_b("panelHeight") ? nbt.func_74760_g("panelHeight") : 3.0f;
        this.heightOffset = nbt.func_74764_b("panelHeightOffset") ? nbt.func_74760_g("panelHeightOffset") : 0.0f;
        this.offsetX = nbt.func_74764_b("panelOffsetX") ? nbt.func_74760_g("panelOffsetX") : 0.0f;
        this.offsetZ = nbt.func_74764_b("panelOffsetZ") ? nbt.func_74760_g("panelOffsetZ") : 0.0f;
        this.launching = nbt.func_74764_b("panelLaunching") && nbt.func_74767_n("panelLaunching");
        this.launchSpeed = nbt.func_74764_b("panelLaunchSpeed") ? nbt.func_74760_g("panelLaunchSpeed") : 0.5f;
        this.launchDamage = nbt.func_74764_b("panelLaunchDamage") ? nbt.func_74760_g("panelLaunchDamage") : 8.0f;
        this.launchKnockback = nbt.func_74764_b("panelLaunchKnockback") ? nbt.func_74760_g("panelLaunchKnockback") : 2.0f;
    }

    public EnergyPanelData copy() {
        EnergyPanelData copy = new EnergyPanelData(this.panelWidth, this.panelHeight, this.heightOffset);
        copy.offsetX = this.offsetX;
        copy.offsetZ = this.offsetZ;
        copy.launching = this.launching;
        copy.launchSpeed = this.launchSpeed;
        copy.launchDamage = this.launchDamage;
        copy.launchKnockback = this.launchKnockback;
        return copy;
    }
}

