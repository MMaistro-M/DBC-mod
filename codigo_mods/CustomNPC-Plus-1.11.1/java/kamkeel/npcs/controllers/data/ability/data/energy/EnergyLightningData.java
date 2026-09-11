/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.data.IEnergyLightningData;

public class EnergyLightningData
implements IEnergyLightningData {
    public boolean lightningEffect = false;
    public float lightningDensity = 0.15f;
    public float lightningRadius = 0.5f;
    public int lightningFadeTime = 6;

    public EnergyLightningData() {
    }

    public EnergyLightningData(boolean lightningEffect, float lightningDensity, float lightningRadius, int lightningFadeTime) {
        this.lightningEffect = lightningEffect;
        this.lightningDensity = lightningDensity;
        this.lightningRadius = lightningRadius;
        this.lightningFadeTime = lightningFadeTime;
    }

    @Override
    public boolean isLightningEffect() {
        return this.lightningEffect;
    }

    @Override
    public void setLightningEffect(boolean lightningEffect) {
        this.lightningEffect = lightningEffect;
    }

    @Override
    public float getLightningDensity() {
        return this.lightningDensity;
    }

    @Override
    public void setLightningDensity(float lightningDensity) {
        this.lightningDensity = lightningDensity;
    }

    @Override
    public float getLightningRadius() {
        return this.lightningRadius;
    }

    @Override
    public void setLightningRadius(float lightningRadius) {
        this.lightningRadius = lightningRadius;
    }

    @Override
    public int getLightningFadeTime() {
        return this.lightningFadeTime;
    }

    @Override
    public void setLightningFadeTime(int lightningFadeTime) {
        this.lightningFadeTime = Math.max(1, lightningFadeTime);
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74757_a("lightningEffect", this.lightningEffect);
        nbt.func_74776_a("lightningDensity", this.lightningDensity);
        nbt.func_74776_a("lightningRadius", this.lightningRadius);
        nbt.func_74768_a("lightningFadeTime", this.lightningFadeTime);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.lightningEffect = nbt.func_74764_b("lightningEffect") && nbt.func_74767_n("lightningEffect");
        this.lightningDensity = nbt.func_74764_b("lightningDensity") ? nbt.func_74760_g("lightningDensity") : 0.15f;
        this.lightningRadius = nbt.func_74764_b("lightningRadius") ? nbt.func_74760_g("lightningRadius") : 0.5f;
        int n = this.lightningFadeTime = nbt.func_74764_b("lightningFadeTime") ? nbt.func_74762_e("lightningFadeTime") : 6;
        if (this.lightningFadeTime <= 0) {
            this.lightningFadeTime = 1;
        }
    }

    public EnergyLightningData copy() {
        return new EnergyLightningData(this.lightningEffect, this.lightningDensity, this.lightningRadius, this.lightningFadeTime);
    }
}

