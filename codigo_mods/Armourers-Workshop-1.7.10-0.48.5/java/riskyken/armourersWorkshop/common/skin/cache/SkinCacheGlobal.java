/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.cache;

import java.util.HashSet;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.common.data.BidirectionalHashMap;
import riskyken.armourersWorkshop.common.library.global.SkinDownloader;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.cache.SkinCacheLocalDatabase;
import riskyken.armourersWorkshop.common.skin.cache.SkinRequestMessage;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.utils.ModLogger;

public class SkinCacheGlobal {
    private final BidirectionalHashMap<Integer, Integer> cacheMapFileLink;
    private final Object cacheMapLock = new Object();
    private final SkinCacheLocalDatabase cacheLocalDatabase;
    private final HashSet<Integer> downloadingSet;
    private final Object downloadingSetLock = new Object();
    private final Executor executorSkinDownloader;
    private final CompletionService<Skin> completionServiceSkinDownloader;

    public SkinCacheGlobal(SkinCacheLocalDatabase cacheLocalDatabase) {
        this.cacheLocalDatabase = cacheLocalDatabase;
        this.cacheMapFileLink = new BidirectionalHashMap();
        this.downloadingSet = new HashSet();
        this.executorSkinDownloader = Executors.newFixedThreadPool(2);
        this.completionServiceSkinDownloader = new ExecutorCompletionService<Skin>(this.executorSkinDownloader);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void downloadSkin(ISkinIdentifier identifier) {
        HashSet<Integer> hashSet = this.downloadingSet;
        synchronized (hashSet) {
            if (!this.downloadingSet.contains(identifier.getSkinGlobalId())) {
                this.downloadingSet.add(identifier.getSkinGlobalId());
                this.completionServiceSkinDownloader.submit(new SkinDownloader.DownloadSkinCallable(null, identifier.getSkinGlobalId()));
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void doSkinLoading() {
        Future<Skin> futureSkin = this.completionServiceSkinDownloader.poll();
        if (futureSkin != null) {
            try {
                Skin skin = futureSkin.get();
                if (skin != null) {
                    Object object = this.cacheMapLock;
                    synchronized (object) {
                        int globalId = skin.serverId;
                        this.cacheLocalDatabase.add(skin);
                        this.cacheMapFileLink.put(globalId, skin.lightHash());
                        CommonSkinCache.INSTANCE.onGlobalSkinLoaded(skin, globalId);
                        Object object2 = this.downloadingSetLock;
                        synchronized (object2) {
                            this.downloadingSet.remove(skin.serverId);
                        }
                    }
                }
                ModLogger.log(Level.ERROR, "Failed to load skin from global database.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Skin get(ISkinIdentifier identifier, boolean softLoad) {
        return this.get(new SkinRequestMessage(identifier, null), softLoad);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Skin get(SkinRequestMessage requestMessage, boolean softLoad) {
        ISkinIdentifier identifier = requestMessage.getSkinIdentifier();
        int globalId = identifier.getSkinGlobalId();
        Object object = this.cacheMapLock;
        synchronized (object) {
            if (!this.cacheMapFileLink.containsKey(globalId)) {
                this.downloadSkin(identifier);
                return null;
            }
            if (this.cacheMapFileLink.containsKey(globalId)) {
                int id = this.cacheMapFileLink.get(globalId);
                SkinIdentifier newIdentifier = new SkinIdentifier(id, null, globalId, identifier.getSkinType());
                Skin skin = this.cacheLocalDatabase.get(newIdentifier, false);
                if (skin != null) {
                    return skin;
                }
                ModLogger.log(Level.WARN, "Somehow failed to load a skin that we should have. ID was " + id);
            } else if (requestMessage.getPlayer() != null) {
                ModLogger.log(Level.ERROR, "Skin [" + identifier.toString() + "] was requested by " + requestMessage.getPlayer().func_70005_c_() + " but was not found.");
            } else {
                ModLogger.log(Level.ERROR, "Skin [" + identifier.toString() + "] was requested but was not found.");
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean containsValue(int skinId) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            return this.cacheMapFileLink.containsValue(skinId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getBackward(int skinId) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            if (this.cacheMapFileLink.getMapBackward().containsKey(skinId)) {
                return this.cacheMapFileLink.getBackward(skinId);
            }
            return 0;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void remove(int globalId) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            this.cacheMapFileLink.remove(globalId);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int size() {
        Object object = this.cacheMapLock;
        synchronized (object) {
            return this.cacheMapFileLink.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clear() {
        Object object = this.cacheMapLock;
        synchronized (object) {
            Object object2 = this.downloadingSetLock;
            synchronized (object2) {
                this.cacheMapFileLink.clear();
                this.downloadingSet.clear();
            }
        }
    }
}

