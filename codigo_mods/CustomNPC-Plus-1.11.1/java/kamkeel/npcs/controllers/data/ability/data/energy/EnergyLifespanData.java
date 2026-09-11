/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.data.IEnergyLifespanData;

public class EnergyLifespanData
implements IEnergyLifespanData {
    public float maxDistance = 150.0f;
    public int maxLifetime = 200;

    public EnergyLifespanData() {
    }

    public EnergyLifespanData(float maxDistance, int maxLifetime) {
        this.maxDistance = maxDistance;
        this.maxLifetime = maxLifetime;
    }

    @Override
    public float getMaxDistance() {
        return this.maxDistance;
    }

    @Override
    public void setMaxDistance(float maxDistance) {
        this.maxDistance = Float.isNaN(maxDistance) || maxDistance <= 0.0f ? 150.0f : maxDistance;
    }

    @Override
    public int getMaxLifetime() {
        return this.maxLifetime;
    }

    @Override
    public void setMaxLifetime(int maxLifetime) {
        this.maxLifetime = maxLifetime <= 0 ? 200 : maxLifetime;
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("maxDistance", this.maxDistance);
        nbt.func_74768_a("maxLifetime", this.maxLifetime);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.maxDistance = nbt.func_74760_g("maxDistance");
        this.maxLifetime = nbt.func_74762_e("maxLifetime");
        if (Float.isNaN(this.maxDistance) || Float.isInfinite(this.maxDistance) || this.maxDistance <= 0.0f) {
            this.maxDistance = 150.0f;
        }
        if (this.maxLifetime <= 0) {
            this.maxLifetime = 200;
        }
    }

    public EnergyLifespanData copy() {
        return new EnergyLifespanData(this.maxDistance, this.maxLifetime);
    }
}

