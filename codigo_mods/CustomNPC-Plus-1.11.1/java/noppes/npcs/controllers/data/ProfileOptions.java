/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.IProfileOptions;
import noppes.npcs.constants.EnumProfileSync;

public class ProfileOptions
implements IProfileOptions {
    public boolean enableOptions = false;
    public EnumProfileSync cooldownControl = EnumProfileSync.Individual;
    public EnumProfileSync completeControl = EnumProfileSync.Individual;

    public void readFromNBT(NBTTagCompound compound) {
        this.enableOptions = compound.func_74767_n("EnableProfiles");
        if (this.enableOptions) {
            this.cooldownControl = EnumProfileSync.values()[compound.func_74762_e("CooldownControl")];
            this.completeControl = EnumProfileSync.values()[compound.func_74762_e("CompleteControl")];
        } else {
            if (compound.func_74764_b("CooldownControl")) {
                compound.func_82580_o("CooldownControl");
            }
            if (compound.func_74764_b("CompleteControl")) {
                compound.func_82580_o("CompleteControl");
            }
            this.cooldownControl = EnumProfileSync.Individual;
            this.completeControl = EnumProfileSync.Individual;
        }
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74757_a("EnableProfiles", this.enableOptions);
        if (this.enableOptions) {
            compound.func_74768_a("CooldownControl", this.cooldownControl.ordinal());
            compound.func_74768_a("CompleteControl", this.completeControl.ordinal());
        } else {
            if (compound.func_74764_b("CooldownControl")) {
                compound.func_82580_o("CooldownControl");
            }
            if (compound.func_74764_b("CompleteControl")) {
                compound.func_82580_o("CompleteControl");
            }
        }
        return compound;
    }

    @Override
    public boolean hasProfileOptions() {
        return this.enableOptions;
    }

    @Override
    public void setProfileOptions(boolean enable) {
        this.enableOptions = enable;
    }

    @Override
    public void setCooldownControl(int profileType) {
        if (profileType < 0 || profileType >= EnumProfileSync.values().length) {
            return;
        }
        this.cooldownControl = EnumProfileSync.values()[profileType];
    }

    @Override
    public int getCooldownControl() {
        return this.cooldownControl.ordinal();
    }

    @Override
    public void setCompleteControl(int profileType) {
        if (profileType < 0 || profileType >= EnumProfileSync.values().length) {
            return;
        }
        this.completeControl = EnumProfileSync.values()[profileType];
    }

    @Override
    public int getCompleteControl() {
        return this.completeControl.ordinal();
    }
}

