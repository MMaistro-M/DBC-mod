/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.reflect.TypeToken
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;

public class UsageTracker {
    private static final String JAVA_FILE = "java_usages.json";
    private static final String JS_FILE = "js_usages.json";
    private static final long SAVE_INTERVAL_MS = 60000L;
    private static final int USAGE_SCORE_MULTIPLIER = 50;
    private static final int MAX_USAGE_BOOST = 5000;
    private static UsageTracker javaInstance;
    private static UsageTracker jsInstance;
    private static boolean initialized;
    private final Map<String, Integer> usageCounts = new ConcurrentHashMap<String, Integer>();
    private final File file;
    private final AtomicBoolean dirty = new AtomicBoolean(false);
    private final AtomicBoolean loaded = new AtomicBoolean(false);
    private static ScheduledExecutorService scheduler;
    private static final Gson GSON;

    public static synchronized UsageTracker getJavaInstance() {
        UsageTracker.ensureInitialized();
        if (javaInstance == null) {
            javaInstance = new UsageTracker(JAVA_FILE);
            javaInstance.load();
        }
        return javaInstance;
    }

    public static synchronized UsageTracker getJSInstance() {
        UsageTracker.ensureInitialized();
        if (jsInstance == null) {
            jsInstance = new UsageTracker(JS_FILE);
            jsInstance.load();
        }
        return jsInstance;
    }

    private static void ensureInitialized() {
        if (!initialized) {
            initialized = true;
            UsageTracker.initialize();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> UsageTracker.shutdown(), "UsageTracker-Shutdown"));
        }
    }

    public static void initialize() {
        if (scheduler == null || scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "UsageTracker-AutoSave");
                t.setDaemon(true);
                return t;
            });
            scheduler.scheduleAtFixedRate(() -> UsageTracker.saveAllIfDirty(), 60000L, 60000L, TimeUnit.MILLISECONDS);
        }
    }

    public static void shutdown() {
        UsageTracker.saveAllIfDirty();
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    private static void saveAllIfDirty() {
        if (javaInstance != null) {
            javaInstance.saveIfDirty();
        }
        if (jsInstance != null) {
            jsInstance.saveIfDirty();
        }
    }

    UsageTracker(String filename) {
        File dir = this.getDir();
        this.file = new File(dir, filename);
    }

    private File getDir() {
        File dir = new File(CustomNpcs.Dir, "tracked_usages");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public void recordUsage(String owner, String name, AutocompleteItem.Kind kind) {
        String key = UsageTracker.buildKey(owner, name, kind);
        this.usageCounts.merge(key, 1, Integer::sum);
        this.dirty.set(true);
    }

    public void recordUsage(AutocompleteItem item, String ownerFullName) {
        String name;
        String string = name = item.getSearchName() != null ? item.getSearchName() : item.getName();
        if (item.getKind() == AutocompleteItem.Kind.METHOD) {
            int paramCount = item.getParameterCount();
            name = name + "(" + paramCount + ")";
        }
        this.recordUsage(ownerFullName, name, item.getKind());
    }

    public int getUsageCount(String owner, String name, AutocompleteItem.Kind kind) {
        String key = UsageTracker.buildKey(owner, name, kind);
        return this.usageCounts.getOrDefault(key, 0);
    }

    public int getUsageCount(AutocompleteItem item, String ownerFullName) {
        String name;
        String string = name = item.getSearchName() != null ? item.getSearchName() : item.getName();
        if (item.getKind() == AutocompleteItem.Kind.METHOD) {
            int paramCount = item.getParameterCount();
            name = name + "(" + paramCount + ")";
        }
        return this.getUsageCount(ownerFullName, name, item.getKind());
    }

    public static int calculateUsageBoost(int usageCount) {
        if (usageCount <= 0) {
            return 0;
        }
        int boost = (int)(Math.log(usageCount + 1) / Math.log(2.0) * 50.0);
        return Math.min(boost, 5000);
    }

    public static String buildKey(String owner, String name, AutocompleteItem.Kind kind) {
        String ownerPart = owner != null ? owner : "";
        String kindPart = kind != null ? kind.name() : "UNKNOWN";
        return ownerPart + "|" + name + "|" + kindPart;
    }

    public void load() {
        if (this.loaded.get()) {
            return;
        }
        if (!this.file.exists()) {
            this.loaded.set(true);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file));){
            Type type = new TypeToken<Map<String, Integer>>(){}.getType();
            Map data = (Map)GSON.fromJson((Reader)reader, type);
            if (data != null) {
                this.usageCounts.putAll(data);
            }
            this.loaded.set(true);
        }
        catch (Exception e) {
            System.err.println("[UsageTracker] Failed to load " + this.file.getName() + ": " + e.getMessage());
            this.loaded.set(true);
        }
    }

    public void saveIfDirty() {
        if (!this.dirty.compareAndSet(true, false)) {
            return;
        }
        this.save();
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));){
            GSON.toJson(this.usageCounts, (Appendable)writer);
        }
        catch (Exception e) {
            System.err.println("[UsageTracker] Failed to save " + this.file.getName() + ": " + e.getMessage());
            this.dirty.set(true);
        }
    }

    public void clear() {
        this.usageCounts.clear();
        this.dirty.set(true);
    }

    public int size() {
        return this.usageCounts.size();
    }

    static {
        initialized = false;
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }
}

