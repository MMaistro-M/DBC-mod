/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

import java.util.ArrayList;

public enum EnumPartyObjectives {
    Shared("party.shared"),
    All("party.all"),
    Leader("party.leader");

    public final String name;

    private EnumPartyObjectives(String name) {
        this.name = name;
    }

    public static String[] names() {
        ArrayList<String> list = new ArrayList<String>();
        for (EnumPartyObjectives e : EnumPartyObjectives.values()) {
            list.add(e.name);
        }
        return list.toArray(new String[list.size()]);
    }
}

