/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

public enum EnumTradeSlotType {
    EMPTY,
    SELLING,
    BIDDING,
    CLAIM;


    public boolean isSelling() {
        return this == SELLING;
    }

    public boolean isBidding() {
        return this == BIDDING;
    }

    public boolean isClaim() {
        return this == CLAIM;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public static EnumTradeSlotType fromOrdinal(int ordinal) {
        if (ordinal >= 0 && ordinal < EnumTradeSlotType.values().length) {
            return EnumTradeSlotType.values()[ordinal];
        }
        return EMPTY;
    }
}

