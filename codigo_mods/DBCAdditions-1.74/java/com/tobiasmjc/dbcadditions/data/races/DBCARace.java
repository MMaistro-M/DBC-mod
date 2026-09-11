/*
 * Decompiled with CFR 0.152.
 */
package com.tobiasmjc.dbcadditions.data.races;

public class DBCARace {
    private static byte RACE_ID = 1;
    public final byte ID;
    private String name;
    private String hairType;
    private double baseMultiplier = 1.0;
    private int[] customSkinLimits;
    private int[] mindCosts;
    private int[] tpCosts;
    private int colorPresetLimit;
    private int[] ultimateFormColors;
    private int colorMinRacial;
    public byte MaxRacial = (byte)5;

    private DBCARace(byte id, String name) {
        this.ID = id;
        this.name = name;
        this.hairType = "H";
        this.customSkinLimits = new int[]{1, 1, 1, 1, 1, 1};
        this.colorPresetLimit = 1;
        this.colorMinRacial = -1;
        this.ultimateFormColors = new int[]{-1, -1, -1, -1};
    }

    public DBCARace(String name) {
        byte by = RACE_ID;
        RACE_ID = (byte)(by + 1);
        this.ID = by;
        this.name = name;
        this.hairType = "H";
        this.customSkinLimits = new int[]{1, 1, 1, 1, 1, 1};
        this.colorPresetLimit = 1;
        this.colorMinRacial = -1;
        this.ultimateFormColors = new int[]{-1, -1, -1, -1};
    }

    public DBCARace copy() {
        return new DBCARace(this.ID, this.name).setHairType(this.hairType).setSkinLimits(this.customSkinLimits).setColorMinRacial(this.colorMinRacial).setUltimateFormColors(this.ultimateFormColors).setTPCosts(this.tpCosts).setMindCosts(this.mindCosts).setBaseMultiplier(this.baseMultiplier);
    }

    public String getName() {
        return this.name;
    }

    public String getHairType() {
        return this.hairType;
    }

    public int[] getTPCosts() {
        return this.tpCosts;
    }

    public int[] getMindCosts() {
        return this.mindCosts;
    }

    public double getBaseMultiplier() {
        return this.baseMultiplier;
    }

    public int[] getSkinLimits() {
        return this.customSkinLimits;
    }

    public int getColorPresetLimit() {
        return this.colorPresetLimit;
    }

    public int[] getUltimateFormColors() {
        return this.ultimateFormColors;
    }

    public int getColorMinRacial() {
        return this.colorMinRacial;
    }

    public DBCARace setColorMinRacial(int min) {
        this.colorMinRacial = min;
        return this;
    }

    public DBCARace setUltimateFormColors(int[] col) {
        this.ultimateFormColors = col;
        return this;
    }

    public DBCARace setSkinLimits(int[] limits) {
        this.customSkinLimits = limits;
        return this;
    }

    public DBCARace setMindCosts(int[] mcost) {
        this.mindCosts = mcost;
        return this;
    }

    public DBCARace setTPCosts(int[] tpcosts) {
        this.tpCosts = tpcosts;
        return this;
    }

    public DBCARace setHairType(String type) {
        this.hairType = type;
        return this;
    }

    public DBCARace setBaseMultiplier(double multiplier) {
        this.baseMultiplier = multiplier;
        return this;
    }
}

