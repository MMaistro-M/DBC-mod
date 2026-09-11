/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.controllers.data.ability.enums;

public enum UserType {
    NPC_ONLY,
    PLAYER_ONLY,
    BOTH,
    NONE;


    public boolean allowsNpc() {
        return this == NPC_ONLY || this == BOTH;
    }

    public boolean allowsPlayer() {
        return this == PLAYER_ONLY || this == BOTH;
    }

    public static UserType fromOrdinal(int ordinal) {
        UserType[] values = UserType.values();
        if (ordinal >= 0 && ordinal < values.length) {
            return values[ordinal];
        }
        return BOTH;
    }
}

