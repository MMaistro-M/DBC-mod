/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.entity.data.ITintData;

public class TintData
implements ITintData {
    private boolean tintEnabled = false;
    private boolean hurtTintEnabled = true;
    private boolean generalTintEnabled = false;
    private int hurtTint = 0xFF0000;
    private int generalTint = 0;
    private int generalAlpha = 40;

    public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("TintEnabled", this.tintEnabled);
        if (this.tintEnabled) {
            nbttagcompound.func_74757_a("HurtTintEnabled", this.hurtTintEnabled);
            nbttagcompound.func_74757_a("GeneralTintEnabled", this.generalTintEnabled);
            nbttagcompound.func_74768_a("HurtTint", this.hurtTint);
            nbttagcompound.func_74768_a("GeneralTint", this.generalTint);
            nbttagcompound.func_74768_a("GeneralAlpha", this.generalAlpha);
        }
        return nbttagcompound;
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        this.tintEnabled = nbttagcompound.func_74767_n("TintEnabled");
        if (this.tintEnabled) {
            this.hurtTintEnabled = nbttagcompound.func_74767_n("HurtTintEnabled");
            this.generalTintEnabled = nbttagcompound.func_74767_n("GeneralTintEnabled");
            this.hurtTint = nbttagcompound.func_74762_e("HurtTint");
            this.generalTint = nbttagcompound.func_74762_e("GeneralTint");
            this.generalAlpha = nbttagcompound.func_74762_e("GeneralAlpha");
        }
    }

    @Override
    public boolean isHurtTintEnabled() {
        return this.hurtTintEnabled;
    }

    @Override
    public void setHurtTintEnabled(boolean hurtTintEnabled) {
        this.hurtTintEnabled = hurtTintEnabled;
    }

    @Override
    public int getHurtTint() {
        return this.hurtTint;
    }

    @Override
    public void setHurtTint(int colorHurtTint) {
        this.hurtTint = colorHurtTint;
    }

    @Override
    public int getGeneralTint() {
        return this.generalTint;
    }

    @Override
    public void setGeneralTint(int generalTint) {
        this.generalTint = generalTint;
    }

    @Override
    public boolean isTintEnabled() {
        return this.tintEnabled;
    }

    @Override
    public void setTintEnabled(boolean tintEnabled) {
        this.tintEnabled = tintEnabled;
    }

    @Override
    public boolean isGeneralTintEnabled() {
        return this.generalTintEnabled;
    }

    @Override
    public void setGeneralTintEnabled(boolean generalTintEnabled) {
        this.generalTintEnabled = generalTintEnabled;
    }

    @Override
    public int getGeneralAlpha() {
        return this.generalAlpha;
    }

    @Override
    public void setGeneralAlpha(int generalAlpha) {
        this.generalAlpha = generalAlpha;
    }

    public boolean processColor(boolean isHurt) {
        if (isHurt) {
            return this.isTintEnabled() && !this.isHurtTintEnabled() && !this.isGeneralTintEnabled();
        }
        return !this.isTintEnabled() || !this.isGeneralTintEnabled();
    }
}

