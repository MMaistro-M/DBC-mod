/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.profile;

import net.minecraft.nbt.NBTTagCompound;

public class ProfileInfoEntry {
    private String label;
    private String result;
    private int labelColor;
    private int resultColor;

    public ProfileInfoEntry() {
    }

    public ProfileInfoEntry(String label, int labelColor, String result, int resultColor) {
        this.label = label;
        this.result = result;
        this.labelColor = labelColor;
        this.resultColor = resultColor;
    }

    public ProfileInfoEntry(String label, int labelColor, int result, int resultColor) {
        this(label, labelColor, String.valueOf(result), resultColor);
    }

    public ProfileInfoEntry(String label, String result) {
        this(label, 0xFFFFFF, result, 0xFFFFFF);
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74778_a("Label", this.label);
        tag.func_74768_a("LabelColor", this.labelColor);
        tag.func_74778_a("Result", this.result);
        tag.func_74768_a("ResultColor", this.resultColor);
        return tag;
    }

    public static ProfileInfoEntry readFromNBT(NBTTagCompound tag) {
        return new ProfileInfoEntry(tag.func_74779_i("Label"), tag.func_74762_e("LabelColor"), tag.func_74779_i("Result"), tag.func_74762_e("ResultColor"));
    }

    public String getLabel() {
        return this.label;
    }

    public String getResult() {
        return this.result;
    }

    public int getLabelColor() {
        return this.labelColor;
    }

    public int getResultColor() {
        return this.resultColor;
    }
}

