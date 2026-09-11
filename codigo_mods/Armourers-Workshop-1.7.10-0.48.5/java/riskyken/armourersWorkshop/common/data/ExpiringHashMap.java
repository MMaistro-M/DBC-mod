/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ExpiringHashMap<K, V>
implements Runnable {
    private final HashMap<K, CacheMapObject> cacheMap = new HashMap();
    private int expiryTime;
    private final IExpiringMapCallback callback;
    private final ArrayList<V> cleanupList;
    private volatile Thread cleanupThread;

    public ExpiringHashMap(int expiryTime) {
        this(expiryTime, null);
    }

    public ExpiringHashMap(int expiryTime, IExpiringMapCallback callback) {
        this.expiryTime = expiryTime;
        this.callback = callback;
        this.cleanupList = new ArrayList();
        this.cleanupThread = new Thread(this);
        this.cleanupThread.setDaemon(true);
        this.cleanupThread.start();
    }

    public void setExpiryTime(int expiryTime) {
        this.expiryTime = expiryTime;
    }

    protected void finalize() throws Throwable {
        this.cleanupThread = null;
        super.finalize();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void put(K key, V value) {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            this.cacheMap.put(key, new CacheMapObject(value));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public V get(K key) {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            if (this.cacheMap.containsKey(key)) {
                return this.cacheMap.get(key).getMapItem();
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public V getQuiet(K key) {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            if (this.cacheMap.containsKey(key)) {
                return this.cacheMap.get(key).getMapItemQuiet();
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public V remove(K key) {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            if (this.cacheMap.containsKey(key)) {
                Object mapItem = this.cacheMap.get(key).mapItem;
                this.cacheMap.remove(key);
                return (V)mapItem;
            }
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean containsKey(K key) {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            CacheMapObject mapitem = this.cacheMap.get(key);
            if (mapitem != null) {
                mapitem.getMapItem();
                return true;
            }
            return false;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Set<K> getKeySet() {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            return this.cacheMap.keySet();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int size() {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            return this.cacheMap.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clear() {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            this.cacheMap.clear();
        }
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        while (this.cleanupThread == thisThread) {
            try {
                Thread.sleep(1000L);
                this.cleanup();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void cleanupCheck() {
        ArrayList<V> arrayList = this.cleanupList;
        synchronized (arrayList) {
            for (int i = 0; i < this.cleanupList.size(); ++i) {
                this.callback.itemExpired(this.cleanupList.get(i));
            }
            this.cleanupList.clear();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void cleanup() {
        HashMap<K, CacheMapObject> hashMap = this.cacheMap;
        synchronized (hashMap) {
            Object[] keySet = this.cacheMap.keySet().toArray();
            for (int i = 0; i < keySet.length; ++i) {
                Object key = keySet[i];
                CacheMapObject mapObject = this.cacheMap.get(key);
                long systemTime = System.currentTimeMillis();
                if (mapObject.lastAccessed + (long)this.expiryTime >= systemTime) continue;
                this.cacheMap.remove(key);
                if (this.callback == null) continue;
                ArrayList<V> arrayList = this.cleanupList;
                synchronized (arrayList) {
                    this.cleanupList.add(mapObject.mapItem);
                    continue;
                }
            }
        }
    }

    public static interface IExpiringMapCallback<V> {
        public void itemExpired(V var1);
    }

    protected class CacheMapObject {
        private final V mapItem;
        private long lastAccessed = System.currentTimeMillis();

        public CacheMapObject(V mapItem) {
            this.mapItem = mapItem;
        }

        public V getMapItem() {
            this.lastAccessed = System.currentTimeMillis();
            return this.mapItem;
        }

        public V getMapItemQuiet() {
            return this.mapItem;
        }

        public boolean equals(Object obj) {
            return this.mapItem.equals(obj);
        }

        public int hashCode() {
            return this.mapItem.hashCode();
        }

        public String toString() {
            return this.mapItem.toString();
        }
    }
}

