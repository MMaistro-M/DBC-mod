/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.tobiasmjc.dbcadditions.data;

import net.minecraft.nbt.NBTTagCompound;

public class FormMasteryData {
    public double maxLevel = 50.0;
    public double instantTransformLevel = -1.0;
    public double attributeMultiplier = 0.01;
    public double kiDrainMultiplier = 0.005;
    public double healthDrainMultiplier = 0.005;

    public void loadData(double maxLevel, double instantTransformLevel, double attributeMultiplier, double kiDrainMultiplier, double healthDrainMultiplier) {
        this.maxLevel = maxLevel;
        this.instantTransformLevel = instantTransformLevel;
        this.attributeMultiplier = attributeMultiplier;
        this.kiDrainMultiplier = kiDrainMultiplier;
        this.healthDrainMultiplier = healthDrainMultiplier;
    }

    public FormMasteryData setInstantTransformLevel(int level) {
        this.instantTransformLevel = level;
        return this;
    }

    public NBTTagCompound write() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74780_a("MaxLevel", this.maxLevel);
        compound.func_74780_a("InstantTransform", this.instantTransformLevel);
        compound.func_74780_a("AttributeMultiplier", this.attributeMultiplier);
        compound.func_74780_a("KiDrainMultiplier", this.kiDrainMultiplier);
        compound.func_74780_a("HealthDrainMultiplier", this.healthDrainMultiplier);
        return compound;
    }

    public static FormMasteryData read(NBTTagCompound compound) {
        double maxLevel = compound.func_74769_h("MaxLevel");
        double instantTransformLevel = compound.func_74769_h("InstantTransform");
        double attributeMultiplier = compound.func_74769_h("AttributeMultiplier");
        double kiDrainMultiplier = compound.func_74769_h("KiDrainMultiplier");
        double healthDrainMultiplier = compound.func_74769_h("HealthDrainMultiplier");
        FormMasteryData data = new FormMasteryData();
        data.loadData(maxLevel, instantTransformLevel, attributeMultiplier, kiDrainMultiplier, healthDrainMultiplier);
        return data;
    }
}

