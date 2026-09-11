/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.constants;

import net.minecraft.util.StatCollector;

public enum EnumAuctionSort {
    NEWEST("auction.sort.newest"),
    ENDING_SOON("auction.sort.endingSoon"),
    PRICE_LOW("auction.sort.priceLow"),
    PRICE_HIGH("auction.sort.priceHigh"),
    MOST_BIDS("auction.sort.mostBids");

    private final String langKey;

    private EnumAuctionSort(String langKey) {
        this.langKey = langKey;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public String getDisplayName() {
        return StatCollector.func_74838_a((String)this.langKey);
    }

    public static String[] getDisplayNames() {
        String[] names = new String[EnumAuctionSort.values().length];
        for (int i = 0; i < EnumAuctionSort.values().length; ++i) {
            names[i] = EnumAuctionSort.values()[i].getDisplayName();
        }
        return names;
    }

    public static EnumAuctionSort fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < EnumAuctionSort.values().length) {
            return EnumAuctionSort.values()[ordinal];
        }
        return NEWEST;
    }
}

