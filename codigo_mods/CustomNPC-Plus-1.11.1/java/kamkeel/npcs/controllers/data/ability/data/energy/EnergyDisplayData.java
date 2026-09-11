/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.data.IEnergyDisplayData;

public class EnergyDisplayData
implements IEnergyDisplayData {
    public int innerColor = 0xFFFFFF;
    public int outerColor = 0x8888FF;
    public boolean outerColorEnabled = true;
    public float outerColorWidth = 0.4f;
    public float outerColorAlpha = 0.5f;
    public float innerAlpha = 1.0f;
    public float rotationSpeed = 4.0f;

    public EnergyDisplayData() {
    }

    public EnergyDisplayData(int innerColor, int outerColor) {
        this.innerColor = innerColor;
        this.outerColor = outerColor;
    }

    public EnergyDisplayData(int innerColor, int outerColor, boolean outerColorEnabled, float outerColorWidth, float outerColorAlpha, float rotationSpeed) {
        this.innerColor = innerColor;
        this.outerColor = outerColor;
        this.outerColorEnabled = outerColorEnabled;
        this.outerColorWidth = outerColorWidth;
        this.outerColorAlpha = outerColorAlpha;
        this.rotationSpeed = rotationSpeed;
    }

    public EnergyDisplayData(int innerColor, int outerColor, boolean outerColorEnabled, float outerColorWidth, float outerColorAlpha, float innerAlpha, float rotationSpeed) {
        this(innerColor, outerColor, outerColorEnabled, outerColorWidth, outerColorAlpha, rotationSpeed);
        this.innerAlpha = innerAlpha;
    }

    @Override
    public int getInnerColor() {
        return this.innerColor;
    }

    @Override
    public void setInnerColor(int innerColor) {
        this.innerColor = innerColor;
    }

    @Override
    public int getOuterColor() {
        return this.outerColor;
    }

    @Override
    public void setOuterColor(int outerColor) {
        this.outerColor = outerColor;
    }

    @Override
    public boolean isOuterColorEnabled() {
        return this.outerColorEnabled;
    }

    @Override
    public void setOuterColorEnabled(boolean outerColorEnabled) {
        this.outerColorEnabled = outerColorEnabled;
    }

    @Override
    public float getOuterColorWidth() {
        return this.outerColorWidth;
    }

    @Override
    public void setOuterColorWidth(float outerColorWidth) {
        this.outerColorWidth = outerColorWidth;
    }

    @Override
    public float getOuterColorAlpha() {
        return this.outerColorAlpha;
    }

    @Override
    public void setOuterColorAlpha(float outerColorAlpha) {
        this.outerColorAlpha = outerColorAlpha;
    }

    @Override
    public float getInnerAlpha() {
        return this.innerAlpha;
    }

    @Override
    public void setInnerAlpha(float innerAlpha) {
        this.innerAlpha = innerAlpha;
    }

    @Override
    public float getRotationSpeed() {
        return this.rotationSpeed;
    }

    @Override
    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("innerColor", this.innerColor);
        nbt.func_74768_a("outerColor", this.outerColor);
        nbt.func_74757_a("outerColorEnabled", this.outerColorEnabled);
        nbt.func_74776_a("outerColorWidth", this.outerColorWidth);
        nbt.func_74776_a("outerColorAlpha", this.outerColorAlpha);
        nbt.func_74776_a("innerAlpha", this.innerAlpha);
        nbt.func_74776_a("rotationSpeed", this.rotationSpeed);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.innerColor = nbt.func_74764_b("innerColor") ? nbt.func_74762_e("innerColor") : 0xFFFFFF;
        this.outerColor = nbt.func_74764_b("outerColor") ? nbt.func_74762_e("outerColor") : 0x8888FF;
        this.outerColorEnabled = !nbt.func_74764_b("outerColorEnabled") || nbt.func_74767_n("outerColorEnabled");
        this.outerColorWidth = nbt.func_74764_b("outerColorWidth") ? nbt.func_74760_g("outerColorWidth") : 0.4f;
        this.outerColorAlpha = nbt.func_74764_b("outerColorAlpha") ? nbt.func_74760_g("outerColorAlpha") : 0.5f;
        this.innerAlpha = nbt.func_74764_b("innerAlpha") ? nbt.func_74760_g("innerAlpha") : 1.0f;
        this.rotationSpeed = nbt.func_74764_b("rotationSpeed") ? nbt.func_74760_g("rotationSpeed") : 4.0f;
    }

    public EnergyDisplayData copy() {
        EnergyDisplayData copy = new EnergyDisplayData(this.innerColor, this.outerColor, this.outerColorEnabled, this.outerColorWidth, this.outerColorAlpha, this.innerAlpha, this.rotationSpeed);
        return copy;
    }
}

