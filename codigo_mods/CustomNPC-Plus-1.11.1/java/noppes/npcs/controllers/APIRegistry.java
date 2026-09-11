/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers;

import java.util.LinkedHashMap;

public class APIRegistry {
    public static final APIRegistry Instance = new APIRegistry();
    private final LinkedHashMap<String, String> entries = new LinkedHashMap();

    public void register(String name, String url) {
        this.entries.put(name, url);
    }

    public void unregister(String name) {
        this.entries.remove(name);
    }

    public LinkedHashMap<String, String> getEntries() {
        return this.entries;
    }

    public int size() {
        return this.entries.size();
    }
}

