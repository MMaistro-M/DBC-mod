/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.utils;

import cpw.mods.fml.common.FMLLog;
import org.apache.logging.log4j.Level;

public class ModLogger {
    public static void log(Object object) {
        FMLLog.log((String)"Armourer's Workshop", (Level)Level.INFO, (String)String.valueOf(object), (Object[])new Object[0]);
    }

    public static void log(Level logLevel, Object object) {
        FMLLog.log((String)"Armourer's Workshop", (Level)logLevel, (String)String.valueOf(object), (Object[])new Object[0]);
    }
}

