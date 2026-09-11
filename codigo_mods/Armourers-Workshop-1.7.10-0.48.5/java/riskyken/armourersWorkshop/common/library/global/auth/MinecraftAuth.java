/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.Session
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.library.global.auth;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import net.minecraft.util.Session;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.common.library.global.GlobalSkinLibraryUtils;
import riskyken.armourersWorkshop.utils.ModLogger;

public class MinecraftAuth {
    private static final String JOIN_URL = "https://sessionserver.mojang.com/session/minecraft/join";
    private static long lastAuthTime;

    public static boolean checkAndRefeshAuth(Session session, String serverId) {
        if (lastAuthTime + 30000L > System.currentTimeMillis()) {
            ModLogger.log("skipping mc auth");
            return true;
        }
        ModLogger.log(Level.INFO, "MC Auth start");
        Object conn = null;
        String data = "{\"accessToken\":\"" + session.func_148254_d() + "\", \"serverId\":\"" + serverId + "\", \"selectedProfile\":\"" + session.func_148255_b() + "\"}";
        try {
            String result = GlobalSkinLibraryUtils.performPostRequest(new URL(JOIN_URL), data, "application/json");
            lastAuthTime = System.currentTimeMillis();
            return true;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

