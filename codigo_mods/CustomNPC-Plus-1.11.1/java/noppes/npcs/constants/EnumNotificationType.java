/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.constants;

import net.minecraft.util.StatCollector;

public enum EnumNotificationType {
    AUCTION_WON("auction.notify.won"),
    AUCTION_OUTBID("auction.notify.outbid"),
    AUCTION_SOLD("auction.notify.sold"),
    AUCTION_EXPIRED("auction.notify.expired"),
    CLAIM_READY("auction.notify.claim");

    private final String langKey;

    private EnumNotificationType(String langKey) {
        this.langKey = langKey;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public String getDisplayName() {
        return StatCollector.func_74838_a((String)this.langKey);
    }

    public static EnumNotificationType fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < EnumNotificationType.values().length) {
            return EnumNotificationType.values()[ordinal];
        }
        return CLAIM_READY;
    }
}

