/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.LanguageRegistry
 */
package JinRyuu.DragonBC.common;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class DBCLocHlp {
    public static boolean isXMLLangFil(String filename) {
        return filename.endsWith(".xml");
    }

    public static String getLocFrmName(String filename) {
        return filename.substring(filename.lastIndexOf("/") + 1, filename.lastIndexOf("."));
    }

    public static String getLocStrng(String key) {
        return LanguageRegistry.instance().getStringLocalization(key);
    }
}

