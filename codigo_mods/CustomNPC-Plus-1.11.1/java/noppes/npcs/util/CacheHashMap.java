/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CacheHashMap<K, V extends CachedObject<?>>
extends HashMap<K, V> {
    private final long maxCacheTime;
    private long saveInterval;

    public CacheHashMap(long maxCacheTime) {
        this.maxCacheTime = maxCacheTime;
    }

    public CacheHashMap(long maxCacheTime, long saveInterval) {
        this.maxCacheTime = maxCacheTime;
        this.saveInterval = saveInterval;
    }

    @Override
    public V get(Object key) {
        CachedObject object = (CachedObject)super.get(key);
        object.updateTimeAccessed();
        Iterator iterator = this.entrySet().iterator();
        while (iterator.hasNext()) {
            long saveTimeDiff;
            Map.Entry entry = iterator.next();
            long lifeTimeDiff = ((CachedObject)entry.getValue()).timeSinceAccessed();
            if (lifeTimeDiff > this.maxCacheTime) {
                ((CachedObject)entry.getValue()).save();
                iterator.remove();
                continue;
            }
            if (this.saveInterval <= 0L || (saveTimeDiff = ((CachedObject)entry.getValue()).timeSinceSaved()) <= this.saveInterval) continue;
            ((CachedObject)entry.getValue()).save();
            ((CachedObject)entry.getValue()).updateSaveTime();
        }
        return (V)object;
    }

    public static class CachedObject<T> {
        private long timeSaved;
        private long lastTimeAccessed;
        private final T object;

        public CachedObject(T object) {
            this.object = object;
            this.lastTimeAccessed = System.currentTimeMillis();
            this.timeSaved = System.currentTimeMillis();
        }

        public long timeSinceAccessed() {
            return System.currentTimeMillis() - this.lastTimeAccessed;
        }

        public long timeSinceSaved() {
            return System.currentTimeMillis() - this.timeSaved;
        }

        public void updateTimeAccessed() {
            this.lastTimeAccessed = System.currentTimeMillis();
        }

        public void updateSaveTime() {
            this.timeSaved = System.currentTimeMillis();
        }

        public void save() {
        }

        public T getObject() {
            return this.object;
        }
    }
}

