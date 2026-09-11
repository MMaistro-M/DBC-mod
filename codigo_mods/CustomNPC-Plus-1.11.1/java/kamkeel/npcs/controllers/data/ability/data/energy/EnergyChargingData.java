/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.energy;

import net.minecraft.nbt.NBTTagCompound;

public class EnergyChargingData {
    public int chargeDuration = 60;
    public int chargeDelay = 0;
    public int maxCharge = 100;
    public boolean hasCharge = true;
    public boolean chargeWindUpSync = true;

    public EnergyChargingData() {
    }

    public EnergyChargingData(int chargeDuration, int chargeDelay, int maxCharge, boolean hasCharge, boolean chargeWindUpSync) {
        this.chargeDuration = chargeDuration;
        this.chargeDelay = chargeDelay;
        this.maxCharge = maxCharge;
        this.hasCharge = hasCharge;
        this.chargeWindUpSync = chargeWindUpSync;
    }

    public boolean hasCharge() {
        return this.hasCharge;
    }

    public void setHasCharge(boolean hasCharge) {
        this.hasCharge = hasCharge;
    }

    public boolean isChargeWindUpSync() {
        return this.chargeWindUpSync;
    }

    public void setChargeWindUpSync(boolean chargeWindUpSync) {
        this.chargeWindUpSync = chargeWindUpSync;
    }

    public int getChargeDuration() {
        return this.chargeDuration;
    }

    public void setChargeDuration(int chargeDuration) {
        this.chargeDuration = chargeDuration;
    }

    public int getChargeDelay() {
        return this.chargeDelay;
    }

    public void setChargeDelay(int chargeDelay) {
        this.chargeDelay = chargeDelay;
    }

    public int getMaxCharge() {
        return this.maxCharge;
    }

    public void setMaxCharge(int maxCharge) {
        this.maxCharge = Math.max(0, maxCharge);
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("chargeDuration", this.chargeDuration);
        nbt.func_74768_a("chargeDelay", this.chargeDelay);
        nbt.func_74768_a("maxCharge", this.maxCharge);
        nbt.func_74757_a("hasCharge", this.hasCharge);
        nbt.func_74757_a("chargeWindUpSync", this.chargeWindUpSync);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.chargeDuration = nbt.func_74764_b("chargeDuration") ? nbt.func_74762_e("chargeDuration") : 60;
        this.chargeDelay = nbt.func_74764_b("chargeDelay") ? nbt.func_74762_e("chargeDelay") : 0;
        this.maxCharge = nbt.func_74764_b("maxCharge") ? nbt.func_74762_e("maxCharge") : 100;
        this.hasCharge = !nbt.func_74764_b("hasCharge") || nbt.func_74767_n("hasCharge");
        this.chargeWindUpSync = !nbt.func_74764_b("chargeWindUpSync") || nbt.func_74767_n("chargeWindUpSync");
    }

    public EnergyChargingData copy() {
        return new EnergyChargingData(this.chargeDuration, this.chargeDelay, this.maxCharge, this.hasCharge, this.chargeWindUpSync);
    }
}

