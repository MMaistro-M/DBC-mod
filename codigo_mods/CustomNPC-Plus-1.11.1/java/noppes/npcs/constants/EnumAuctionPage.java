/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

import noppes.npcs.constants.EnumGuiType;

public enum EnumAuctionPage {
    LISTINGS(EnumGuiType.PlayerAuction),
    SELL(EnumGuiType.PlayerAuctionSell),
    TRADES(EnumGuiType.PlayerAuctionTrades);

    private final EnumGuiType guiType;

    private EnumAuctionPage(EnumGuiType guiType) {
        this.guiType = guiType;
    }

    public EnumGuiType getGuiType() {
        return this.guiType;
    }

    public static EnumAuctionPage fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < EnumAuctionPage.values().length) {
            return EnumAuctionPage.values()[ordinal];
        }
        return LISTINGS;
    }
}

