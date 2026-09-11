/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;

public class DialogColorData {
    private boolean enableColorSettings = false;
    private int lineColor1 = -7522304;
    private int lineColor2 = -88773;
    private int lineColor3 = -7522304;
    private int slotColor = -432635;
    private int buttonAcceptColor = -432635;
    private int buttonRejectColor = -432635;

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74757_a("ColorSettings", this.enableColorSettings);
        if (this.enableColorSettings) {
            compound.func_74768_a("LineColor1", this.lineColor1);
            compound.func_74768_a("LineColor2", this.lineColor2);
            compound.func_74768_a("LineColor3", this.lineColor3);
            compound.func_74768_a("SlotColor", this.slotColor);
            compound.func_74768_a("ButtonAcceptColor", this.buttonAcceptColor);
            compound.func_74768_a("ButtonRejectColor", this.buttonRejectColor);
        }
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.enableColorSettings = compound.func_74767_n("ColorSettings");
        if (this.enableColorSettings) {
            this.lineColor1 = compound.func_74762_e("LineColor1");
            this.lineColor2 = compound.func_74762_e("LineColor2");
            this.lineColor3 = compound.func_74762_e("LineColor3");
            this.slotColor = compound.func_74762_e("SlotColor");
            this.buttonAcceptColor = compound.func_74762_e("ButtonAcceptColor");
            this.buttonRejectColor = compound.func_74762_e("ButtonRejectColor");
        }
    }

    public boolean getEnableColorSettings() {
        return this.enableColorSettings;
    }

    public void setEnableColorSettings(boolean enableColorSettings) {
        this.enableColorSettings = enableColorSettings;
    }

    public int getLineColor1() {
        return this.lineColor1;
    }

    public void setLineColor1(int lineColour1) {
        this.lineColor1 = lineColour1;
    }

    public int getLineColor2() {
        return this.lineColor2;
    }

    public void setLineColor2(int lineColour2) {
        this.lineColor2 = lineColour2;
    }

    public int getLineColor3() {
        return this.lineColor3;
    }

    public void setLineColor3(int lineColour3) {
        this.lineColor3 = lineColour3;
    }

    public int getSlotColor() {
        return this.slotColor;
    }

    public void setSlotColor(int slotColour) {
        this.slotColor = slotColour;
    }

    public int getButtonAcceptColor() {
        return this.buttonAcceptColor;
    }

    public void setButtonAcceptColor(int buttonAcceptColour) {
        this.buttonAcceptColor = buttonAcceptColour;
    }

    public int getButtonRejectColor() {
        return this.buttonRejectColor;
    }

    public void setButtonRejectColor(int buttonRejectColour) {
        this.buttonRejectColor = buttonRejectColour;
    }
}

