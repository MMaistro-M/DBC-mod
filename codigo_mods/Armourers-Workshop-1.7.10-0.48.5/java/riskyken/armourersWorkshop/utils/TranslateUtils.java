/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.StatCollector
 */
package riskyken.armourersWorkshop.utils;

import net.minecraft.util.StatCollector;

public final class TranslateUtils {
    public static String translate(String unlocalizedText) {
        String localizedText = StatCollector.func_74838_a((String)unlocalizedText);
        return localizedText.replace("&", "\u00a7");
    }

    public static String translate(String unlocalizedText, Object ... args) {
        String localizedText = StatCollector.func_74837_a((String)unlocalizedText, (Object[])args);
        return localizedText.replace("&", "\u00a7");
    }
}

