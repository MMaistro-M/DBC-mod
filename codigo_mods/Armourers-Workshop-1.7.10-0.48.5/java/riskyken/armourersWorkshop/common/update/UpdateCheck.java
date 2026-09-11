/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.update;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.utils.ModLogger;

public class UpdateCheck
implements Runnable {
    public static boolean checkForUpdates = true;
    private static final String UPDATE_URL = "http://bit.ly/2dxhJ1c";
    public static boolean updateFound = false;
    public static String remoteModVersion;

    public static void checkForUpdates() {
        if (!checkForUpdates) {
            return;
        }
        new Thread((Runnable)new UpdateCheck(), "Armourer's Workshop update thread.").start();
    }

    @Override
    public void run() {
        ModLogger.log("Starting Update Check");
        String localVersion = "1.7.10-0.48.5";
        if (localVersion.equals("@VERSION@")) {
            return;
        }
        try {
            if (localVersion.contains("-")) {
                String[] lvSplit = localVersion.split("-");
                localVersion = lvSplit[1];
            }
            String location = UPDATE_URL;
            URLConnection conn = null;
            while (location != null && !location.isEmpty()) {
                URL url = new URL(location);
                if (conn != null) {
                    ((HttpURLConnection)conn).disconnect();
                }
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
                conn.setRequestProperty("Referer", "http://1.7.10-0.48.5");
                conn.connect();
                location = conn.getHeaderField("Location");
            }
            if (conn == null) {
                throw new NullPointerException();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String data = "";
            String line = "";
            while ((line = reader.readLine()) != null) {
                data = data + line;
            }
            ((HttpURLConnection)conn).disconnect();
            reader.close();
            JsonObject json = (JsonObject)new JsonParser().parse(data);
            remoteModVersion = json.getAsJsonObject("promos").get("1.7.10-latest").getAsString();
            ModLogger.log(String.format("Latest version for Minecraft %s is %s.", "1.7.10", remoteModVersion));
            if (this.versionCompare(localVersion, remoteModVersion) < 0) {
                updateFound = true;
                ModLogger.log("Update needed. New version " + remoteModVersion + " your version " + localVersion);
            } else {
                updateFound = false;
                ModLogger.log("Mod is up to date with the latest version.");
            }
        }
        catch (Exception e) {
            ModLogger.log(Level.WARN, "Unable to read from remote version authority.");
            ModLogger.log(Level.WARN, e.toString());
            updateFound = false;
        }
    }

    private int versionCompare(String str1, String str2) {
        int i;
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        for (i = 0; i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i]); ++i) {
        }
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        return Integer.signum(vals1.length - vals2.length);
    }
}

