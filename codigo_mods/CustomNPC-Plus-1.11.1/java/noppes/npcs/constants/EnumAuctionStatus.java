/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.constants;

import net.minecraft.util.StatCollector;

public enum EnumAuctionStatus {
    ACTIVE("auction.status.active"),
    ENDED("auction.status.ended"),
    CANCELLED("auction.status.cancelled"),
    CLAIMED("auction.status.claimed");

    private final String langKey;

    private EnumAuctionStatus(String langKey) {
        this.langKey = langKey;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public String getDisplayName() {
        return StatCollector.func_74838_a((String)this.langKey);
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isEnded() {
        return this == ENDED || this == CANCELLED || this == CLAIMED;
    }

    public boolean canBid() {
        return this == ACTIVE;
    }

    public boolean canCancel() {
        return this == ACTIVE;
    }

    public static EnumAuctionStatus fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < EnumAuctionStatus.values().length) {
            return EnumAuctionStatus.values()[ordinal];
        }
        return ACTIVE;
    }
}

