/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NPCLogLevel;
import noppes.npcs.NPCStamp;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.constants.EnumScriptType;

public class LogWriter {
    private static final String name = "CustomNPCs";
    private static final Logger logger = Logger.getLogger("CustomNPCs");
    private static final WeakHashMap<UUID, NPCStamp> InitCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> TickCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> InteractCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> DialogCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> DamagedCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> KilledCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> AttackCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> TargetCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> CollideCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> KillsCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> DialogCloseCacheMap = new WeakHashMap();
    private static final WeakHashMap<UUID, NPCStamp> TimerCacheMap = new WeakHashMap();
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
    private static Handler handler;

    public static void postScriptLog(UUID npcUUID, EnumScriptType type, String message) {
        switch (type) {
            case INIT: {
                if (CustomNpcs.InitIgnore) break;
                LogWriter.scriptLogCalculator(InitCacheMap, npcUUID, message);
                break;
            }
            case TICK: {
                if (CustomNpcs.TickIgnore) break;
                LogWriter.scriptLogCalculator(TickCacheMap, npcUUID, message);
                break;
            }
            case INTERACT: {
                if (CustomNpcs.InteractIgnore) break;
                LogWriter.scriptLogCalculator(InteractCacheMap, npcUUID, message);
                break;
            }
            case DIALOG: {
                if (CustomNpcs.DialogIgnore) break;
                LogWriter.scriptLogCalculator(DialogCacheMap, npcUUID, message);
                break;
            }
            case DAMAGED: {
                if (CustomNpcs.DamagedIgnore) break;
                LogWriter.scriptLogCalculator(DamagedCacheMap, npcUUID, message);
                break;
            }
            case KILLED: {
                if (CustomNpcs.KilledIgnore) break;
                LogWriter.scriptLogCalculator(KilledCacheMap, npcUUID, message);
                break;
            }
            case ATTACK: {
                if (CustomNpcs.AttackIgnore) break;
                LogWriter.scriptLogCalculator(AttackCacheMap, npcUUID, message);
                break;
            }
            case TARGET: {
                if (CustomNpcs.TargetIgnore) break;
                LogWriter.scriptLogCalculator(TargetCacheMap, npcUUID, message);
                break;
            }
            case COLLIDE: {
                if (CustomNpcs.CollideIgnore) break;
                LogWriter.scriptLogCalculator(CollideCacheMap, npcUUID, message);
                break;
            }
            case KILLS: {
                if (!CustomNpcs.KillsIgnore) {
                    LogWriter.scriptLogCalculator(KillsCacheMap, npcUUID, message);
                }
            }
            case DIALOG_CLOSE: {
                if (CustomNpcs.DialogCloseIgnore) break;
                LogWriter.scriptLogCalculator(DialogCloseCacheMap, npcUUID, message);
                break;
            }
            case TIMER: {
                if (CustomNpcs.TimerIgnore) break;
                LogWriter.scriptLogCalculator(TimerCacheMap, npcUUID, message);
                break;
            }
        }
    }

    public static void scriptLogCalculator(WeakHashMap<UUID, NPCStamp> map, UUID npcUUID, String message) {
        if (map.containsKey(npcUUID)) {
            NPCStamp stamp = map.get(npcUUID);
            long secondsSinceFirst = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime() - stamp.makeDate.getTime());
            long millisecSinceLast = TimeUnit.MILLISECONDS.toMillis(stamp.recentDate.getTime() - stamp.makeDate.getTime());
            double frequency = (float)ConfigDebug.ScriptFrequency / 60.0f;
            if (secondsSinceFirst > 120L) {
                LogWriter.script(message);
                stamp.counter = 1;
                stamp.makeDate = new Date();
                stamp.recentDate = new Date();
            } else if (secondsSinceFirst < 10L && stamp.counter < 3) {
                LogWriter.script(message);
                stamp.recentDate = new Date();
            } else if (millisecSinceLast < (long)ConfigDebug.ScriptIgnoreTime) {
                stamp.recentDate = new Date();
            } else if (secondsSinceFirst > 10L && (double)stamp.counter / (double)secondsSinceFirst > frequency) {
                LogWriter.script("[SPAM]:" + message);
                stamp.counter = 0;
                stamp.recentDate = new Date();
            }
            ++stamp.counter;
        } else {
            map.put(npcUUID, new NPCStamp());
            LogWriter.script(message);
        }
    }

    public static void script(Object msg) {
        logger.log(NPCLogLevel.CNPCLog, msg.toString());
        handler.flush();
    }

    public static void info(Object msg) {
        logger.log(Level.INFO, msg.toString());
        handler.flush();
    }

    public static void error(Object msg) {
        logger.log(Level.SEVERE, msg.toString());
        handler.flush();
    }

    public static void error(Object msg, Exception e) {
        logger.log(Level.SEVERE, msg.toString());
        logger.log(Level.SEVERE, e.getMessage(), e);
        handler.flush();
    }

    public static void except(Exception e) {
        logger.log(Level.SEVERE, e.getMessage(), e);
        handler.flush();
    }

    static {
        try {
            File dir = new File("logs");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "CustomNPCs-latest.log");
            File lock = new File(dir, "CustomNPCs-latest.log.lck");
            File file1 = new File(dir, "CustomNPCs-1.log");
            File file2 = new File(dir, "CustomNPCs-2.log");
            File file3 = new File(dir, "CustomNPCs-3.log");
            if (lock.exists()) {
                lock.delete();
            }
            if (file3.exists()) {
                file3.delete();
            }
            if (file2.exists()) {
                file2.renameTo(file3);
            }
            if (file1.exists()) {
                file1.renameTo(file2);
            }
            if (file.exists()) {
                file.renameTo(file1);
            }
            handler = new StreamHandler(new FileOutputStream(file), new Formatter(){

                @Override
                public String format(LogRecord record) {
                    String line = "";
                    if (record.getLevel() != NPCLogLevel.CNPCLog) {
                        StackTraceElement element = Thread.currentThread().getStackTrace()[8];
                        line = "[" + element.getClassName() + ":" + element.getLineNumber() + "] ";
                    }
                    String time = "[" + dateformat.format(new Date(record.getMillis())) + "][" + record.getLevel() + "]" + line;
                    if (record.getThrown() != null) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        record.getThrown().printStackTrace(pw);
                        return time + sw.toString();
                    }
                    return time + record.getMessage() + System.getProperty("line.separator");
                }
            });
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(handler.getFormatter());
            consoleHandler.setLevel(Level.FINEST);
            logger.addHandler(consoleHandler);
            logger.setLevel(Level.ALL);
            LogWriter.info(new Date().toString());
        }
        catch (SecurityException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

