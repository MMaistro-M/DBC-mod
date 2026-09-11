/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.constants;

import java.util.ArrayList;

public enum EnumTextureType {
    BASE("texture.base"),
    ITEM("texture.item");

    private String lang;

    private EnumTextureType(String langName) {
        this.lang = langName;
    }

    public String getLang() {
        return this.lang;
    }

    public static String[] names() {
        ArrayList<String> list = new ArrayList<String>();
        for (EnumTextureType e : EnumTextureType.values()) {
            list.add(e.lang);
        }
        return list.toArray(new String[list.size()]);
    }
}

