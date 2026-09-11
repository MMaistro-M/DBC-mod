/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.constants;

import net.minecraft.util.StatCollector;

public enum EnumStockReset {
    NONE("stock.reset.never"),
    MCDAILY("stock.reset.mcdaily"),
    MCWEEKLY("stock.reset.mcweekly"),
    MCCUSTOM("stock.reset.mccustom"),
    RLDAILY("stock.reset.rldaily"),
    RLWEEKLY("stock.reset.rlweekly"),
    RLCUSTOM("stock.reset.rlcustom");

    private final String langKey;

    private EnumStockReset(String langKey) {
        this.langKey = langKey;
    }

    public long getDefaultInterval() {
        switch (this) {
            case MCDAILY: {
                return 24000L;
            }
            case MCWEEKLY: {
                return 168000L;
            }
            case RLDAILY: {
                return 86400000L;
            }
            case RLWEEKLY: {
                return 604800000L;
            }
        }
        return 0L;
    }

    public boolean isRealTime() {
        return this == RLDAILY || this == RLWEEKLY || this == RLCUSTOM;
    }

    public boolean isMinecraftTime() {
        return this == MCDAILY || this == MCWEEKLY || this == MCCUSTOM;
    }

    public String getDisplayName() {
        return StatCollector.func_74838_a((String)this.langKey);
    }

    public static String[] getDisplayNames() {
        EnumStockReset[] values = EnumStockReset.values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; ++i) {
            names[i] = values[i].getDisplayName();
        }
        return names;
    }
}

