/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.constants;

import net.minecraft.util.StatCollector;

public enum EnumClaimType {
    ITEM("auction.claim.item"),
    CURRENCY("auction.claim.currency"),
    REFUND("auction.claim.refund");

    private final String langKey;

    private EnumClaimType(String langKey) {
        this.langKey = langKey;
    }

    public String getLangKey() {
        return this.langKey;
    }

    public String getDisplayName() {
        return StatCollector.func_74838_a((String)this.langKey);
    }

    public boolean isItem() {
        return this == ITEM;
    }

    public boolean isCurrency() {
        return this == CURRENCY || this == REFUND;
    }

    public static EnumClaimType fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < EnumClaimType.values().length) {
            return EnumClaimType.values()[ordinal];
        }
        return ITEM;
    }
}

