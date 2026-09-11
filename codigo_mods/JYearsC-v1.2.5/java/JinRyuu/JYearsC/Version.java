/*
 * Decompiled with CFR 0.152.
 */
package JinRyuu.JYearsC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Version {
    public static final String MOD_ID = "jinryuuyearsc";
    public static final String MOD_NAME = "JinRyuu's Years C";
    public static final String VERSION = "1.2.5";
    public static final String REMOTE_VERSION_FILE = "http://jingames.net/public/JYearsC.txt";
    public static final String REMOTE_MASSAGE_FILE = "http://jingames.net/public/JYearsCmassage.txt";
    public static final byte UNINITIALIZED = 0;
    public static final byte CURRENT = 1;
    public static final byte OUTDATED = 2;
    public static final byte CONNECTION_ERROR = 3;
    private static final String VERSION_CHECK_INIT_LOG_MESSAGE = "version init_log_message";
    private static final String UNINITIALIZED_MESSAGE = "Version Check was Uninitialized!";
    private static final String CONNECTION_ERROR_MESSAGE = "Connection Error when trying to get latest version!";
    public static byte result = 0;
    public static String line = null;

    public static void checkVersion() {
        try {
            URL url = new URL(REMOTE_VERSION_FILE);
            InputStreamReader isr = new InputStreamReader(url.openStream());
            BufferedReader reader = new BufferedReader(isr);
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(MOD_ID) || !line.endsWith(VERSION)) continue;
                result = 1;
                reader.close();
                isr.close();
                return;
            }
            result = (byte)2;
            reader.close();
            isr.close();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            result = (byte)3;
        }
    }

    public static String getResultMessage() {
        if (result == 0) {
            return UNINITIALIZED_MESSAGE;
        }
        if (result == 1) {
            return "Your Version Is Updated";
        }
        if (result == 2) {
            return "There is a New Version Available!";
        }
        if (result == 3) {
            return CONNECTION_ERROR_MESSAGE;
        }
        return null;
    }
}

