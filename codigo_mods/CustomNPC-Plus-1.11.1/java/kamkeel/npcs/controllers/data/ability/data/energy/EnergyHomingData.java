/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.ability.data.IEnergyHomingData;

public class EnergyHomingData
implements IEnergyHomingData {
    public float speed = 0.5f;
    public boolean homing = true;
    public float homingStrength = 0.15f;
    public float homingRange = 20.0f;

    public EnergyHomingData() {
    }

    public EnergyHomingData(float speed, boolean homing, float homingStrength, float homingRange) {
        this.speed = speed;
        this.homing = homing;
        this.homingStrength = homingStrength;
        this.homingRange = homingRange;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = Math.max(0.01f, speed);
    }

    @Override
    public boolean isHoming() {
        return this.homing;
    }

    @Override
    public void setHoming(boolean homing) {
        this.homing = homing;
    }

    @Override
    public float getHomingStrength() {
        return this.homingStrength;
    }

    @Override
    public void setHomingStrength(float homingStrength) {
        this.homingStrength = homingStrength;
    }

    @Override
    public float getHomingRange() {
        return this.homingRange;
    }

    @Override
    public void setHomingRange(float homingRange) {
        this.homingRange = homingRange;
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74776_a("speed", this.speed);
        nbt.func_74757_a("homing", this.homing);
        nbt.func_74776_a("homingStrength", this.homingStrength);
        nbt.func_74776_a("homingRange", this.homingRange);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.speed = nbt.func_74764_b("speed") ? nbt.func_74760_g("speed") : 0.5f;
        this.homing = !nbt.func_74764_b("homing") || nbt.func_74767_n("homing");
        this.homingStrength = nbt.func_74764_b("homingStrength") ? nbt.func_74760_g("homingStrength") : 0.15f;
        float f = this.homingRange = nbt.func_74764_b("homingRange") ? nbt.func_74760_g("homingRange") : 20.0f;
        if (Float.isNaN(this.speed) || Float.isInfinite(this.speed) || this.speed <= 0.0f) {
            this.speed = 0.5f;
        }
    }

    public EnergyHomingData copy() {
        return new EnergyHomingData(this.speed, this.homing, this.homingStrength, this.homingRange);
    }
}

