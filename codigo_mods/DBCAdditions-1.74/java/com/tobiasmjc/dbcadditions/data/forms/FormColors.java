/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.tobiasmjc.dbcadditions.data.forms;

import net.minecraft.nbt.NBTTagCompound;

public class FormColors {
    private int[] bodyColors = new int[]{-1, -1, -1, -1};
    private int furColor = -1;
    private int tailColor = -1;
    private int kiColor = -1;
    private int hairColor = -1;
    private int eyeColor = -1;
    private int barColor = -1;
    private int tattooColor = -1;

    public FormColors setColors(int hair, int eye, int ki, int bar, int fur, int tail, int tattoo, int[] body) {
        this.hairColor = hair;
        this.eyeColor = eye;
        this.kiColor = ki;
        this.barColor = bar;
        this.furColor = fur;
        this.tailColor = tail;
        this.tattooColor = tattoo;
        this.bodyColors = body;
        return this;
    }

    public int[] getBodyColors() {
        return this.bodyColors;
    }

    public int bodyCM() {
        return this.bodyColors[0];
    }

    public int bodyC1() {
        return this.bodyColors[1];
    }

    public int bodyC2() {
        return this.bodyColors[2];
    }

    public int bodyC3() {
        return this.bodyColors[3];
    }

    public FormColors setBodyColors(int bodycm, int bodyc1, int bodyc2, int bodyc3) {
        this.bodyColors = new int[]{bodycm, bodyc1, bodyc2, bodyc3};
        return this;
    }

    public FormColors setBodyColors(int[] bodyColors) {
        this.bodyColors = bodyColors;
        return this;
    }

    public int getFurColor() {
        return this.furColor;
    }

    public FormColors setFurColor(int furColor) {
        this.furColor = furColor;
        return this;
    }

    public int getTailColor() {
        return this.tailColor;
    }

    public FormColors setTailColor(int tailColor) {
        this.tailColor = tailColor;
        return this;
    }

    public int getKiColor() {
        return this.kiColor;
    }

    public FormColors setKiColor(int kiColor) {
        this.kiColor = kiColor;
        return this;
    }

    public int getHairColor() {
        return this.hairColor;
    }

    public FormColors setHairColor(int hairColor) {
        this.hairColor = hairColor;
        return this;
    }

    public int getEyeColor() {
        return this.eyeColor;
    }

    public FormColors setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
        return this;
    }

    public int getBarColor() {
        return this.barColor;
    }

    public FormColors setBarColor(int barColor) {
        this.barColor = barColor;
        return this;
    }

    public int getTattooColor() {
        return this.tattooColor;
    }

    public void setTattooColor(int tattooColor) {
        this.tattooColor = tattooColor;
    }

    public NBTTagCompound write() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74783_a("BodyColors", this.bodyColors);
        compound.func_74768_a("FurColor", this.furColor);
        compound.func_74768_a("TailColor", this.tailColor);
        compound.func_74768_a("KiColor", this.kiColor);
        compound.func_74768_a("HairColor", this.hairColor);
        compound.func_74768_a("EyeColor", this.eyeColor);
        compound.func_74768_a("BarColor", this.barColor);
        compound.func_74768_a("TattooColor", this.tattooColor);
        return compound;
    }

    public static FormColors read(NBTTagCompound compound) {
        FormColors colors = new FormColors();
        if (compound.func_74764_b("BodyColors")) {
            colors.setBodyColors(compound.func_74759_k("BodyColors"));
        }
        colors.setFurColor(compound.func_74762_e("FurColor"));
        colors.setTailColor(compound.func_74762_e("TailColor"));
        colors.setKiColor(compound.func_74762_e("KiColor"));
        colors.setHairColor(compound.func_74762_e("HairColor"));
        colors.setEyeColor(compound.func_74762_e("EyeColor"));
        colors.setBarColor(compound.func_74762_e("BarColor"));
        colors.setTattooColor(compound.func_74762_e("TattooColor"));
        return colors;
    }
}

