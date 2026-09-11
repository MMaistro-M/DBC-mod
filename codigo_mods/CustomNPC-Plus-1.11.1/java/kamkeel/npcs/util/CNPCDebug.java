/*
 * Decompiled with CFR 0.152.
 */
package kamkeel.npcs.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class CNPCDebug {
    private static final String DIR = "logs/cnpc_debug";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final Map<String, Boolean> serverToggles = new ConcurrentHashMap<String, Boolean>();
    private static final Map<String, Boolean> clientToggles = new ConcurrentHashMap<String, Boolean>();
    private static final Map<String, Handler> serverHandlers = new ConcurrentHashMap<String, Handler>();
    private static final Map<String, Handler> clientHandlers = new ConcurrentHashMap<String, Handler>();

    public static boolean toggleServer(String type) {
        boolean newState = !CNPCDebug.isServerEnabled(type = type.toLowerCase());
        serverToggles.put(type, newState);
        if (newState) {
            CNPCDebug.ensureHandler(type, false);
        }
        return newState;
    }

    public static boolean toggleClient(String type) {
        boolean newState = !CNPCDebug.isClientEnabled(type = type.toLowerCase());
        clientToggles.put(type, newState);
        if (newState) {
            CNPCDebug.ensureHandler(type, true);
        }
        return newState;
    }

    public static boolean isServerEnabled(String type) {
        Boolean val = serverToggles.get(type.toLowerCase());
        return val != null && val != false;
    }

    public static boolean isClientEnabled(String type) {
        Boolean val = clientToggles.get(type.toLowerCase());
        return val != null && val != false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void log(String type, boolean client, String msg) {
        String timestamp;
        Map<String, Boolean> toggles = client ? clientToggles : serverToggles;
        Boolean enabled = toggles.get(type = type.toLowerCase());
        if (enabled == null || !enabled.booleanValue()) {
            return;
        }
        Handler handler = CNPCDebug.ensureHandler(type, client);
        if (handler == null) {
            return;
        }
        String side = client ? "CLIENT" : "SERVER";
        SimpleDateFormat simpleDateFormat = DATE_FORMAT;
        synchronized (simpleDateFormat) {
            timestamp = DATE_FORMAT.format(new Date());
        }
        String formatted = "[" + timestamp + "][" + side + "][DEBUG-" + type.toUpperCase() + "] " + msg + System.lineSeparator();
        LogRecord record = new LogRecord(Level.INFO, formatted);
        handler.publish(record);
        handler.flush();
    }

    public static void logServer(String type, String msg) {
        CNPCDebug.log(type, false, msg);
    }

    public static void logClient(String type, String msg) {
        CNPCDebug.log(type, true, msg);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Handler ensureHandler(String type, boolean client) {
        Map<String, Handler> handlers = client ? clientHandlers : serverHandlers;
        Handler existing = handlers.get(type);
        if (existing != null) {
            return existing;
        }
        Class<CNPCDebug> clazz = CNPCDebug.class;
        synchronized (CNPCDebug.class) {
            existing = handlers.get(type);
            if (existing != null) {
                // ** MonitorExit[var4_4] (shouldn't be in output)
                return existing;
            }
            try {
                String timestamp;
                File dir = new File(DIR);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String side = client ? "client" : "server";
                File file = new File(dir, type + "_" + side + ".log");
                if (file.exists()) {
                    File backup = new File(dir, type + "_" + side + "-prev.log");
                    if (backup.exists()) {
                        backup.delete();
                    }
                    file.renameTo(backup);
                }
                StreamHandler handler = new StreamHandler(new FileOutputStream(file), new Formatter(){

                    @Override
                    public String format(LogRecord record) {
                        return record.getMessage();
                    }
                });
                handler.setLevel(Level.ALL);
                handlers.put(type, handler);
                SimpleDateFormat simpleDateFormat = DATE_FORMAT;
                synchronized (simpleDateFormat) {
                    timestamp = DATE_FORMAT.format(new Date());
                }
                LogRecord header = new LogRecord(Level.INFO, "[" + timestamp + "][" + (client ? "CLIENT" : "SERVER") + "][DEBUG-" + type.toUpperCase() + "] Debug logging started" + System.lineSeparator());
                ((Handler)handler).publish(header);
                ((Handler)handler).flush();
                // ** MonitorExit[var4_4] (shouldn't be in output)
                return handler;
            }
            catch (Exception e) {
                e.printStackTrace();
                // ** MonitorExit[var4_4] (shouldn't be in output)
                return null;
            }
        }
    }
}

