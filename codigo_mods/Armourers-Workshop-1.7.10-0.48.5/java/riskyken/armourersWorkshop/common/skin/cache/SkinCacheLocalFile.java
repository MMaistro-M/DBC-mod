/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.cache;

import java.util.ArrayList;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.library.ILibraryFile;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.common.data.BidirectionalHashMap;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.cache.SkinCacheLocalDatabase;
import riskyken.armourersWorkshop.common.skin.cache.SkinRequestMessage;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public class SkinCacheLocalFile {
    private final BidirectionalHashMap<ILibraryFile, Integer> cacheMapFileLink;
    private final Object cacheMapLock = new Object();
    private final ArrayList<SkinRequestMessage> skinLoadQueue;
    private final Object skinLoadQueueLock = new Object();
    private final SkinCacheLocalDatabase cacheLocalDatabase;

    public SkinCacheLocalFile(SkinCacheLocalDatabase cacheLocalDatabase) {
        this.cacheLocalDatabase = cacheLocalDatabase;
        this.cacheMapFileLink = new BidirectionalHashMap();
        this.skinLoadQueue = new ArrayList();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void doSkinLoading() {
        Object object = this.cacheMapLock;
        synchronized (object) {
            Object object2 = this.skinLoadQueueLock;
            synchronized (object2) {
                if (this.skinLoadQueue.size() > 0) {
                    SkinRequestMessage requestMessage = this.skinLoadQueue.get(0);
                    Skin skin = this.load(requestMessage.getSkinIdentifier());
                    if (skin != null) {
                        CommonSkinCache.INSTANCE.onSkinLoaded(skin, requestMessage);
                    }
                    this.skinLoadQueue.remove(0);
                }
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
        ILibraryFile libraryFile = identifier.getSkinLibraryFile();
        Object object = this.cacheMapLock;
        synchronized (object) {
            if (!this.cacheMapFileLink.containsKey(libraryFile)) {
                if (softLoad) {
                    Object object2 = this.skinLoadQueueLock;
                    synchronized (object2) {
                        this.skinLoadQueue.add(requestMessage);
                    }
                    return null;
                }
                this.load(identifier);
            }
            if (this.cacheMapFileLink.containsKey(libraryFile)) {
                int id = this.cacheMapFileLink.get(libraryFile);
                SkinIdentifier newIdentifier = new SkinIdentifier(id, requestMessage.getSkinIdentifier().getSkinLibraryFile(), 0, requestMessage.getSkinIdentifier().getSkinType());
                Skin skin = this.cacheLocalDatabase.get(newIdentifier, false);
                if (skin != null) {
                    return skin;
                }
                ModLogger.log(Level.WARN, "Somehow failed to load a skin that we should have. ID was " + id);
            } else if (requestMessage.getPlayer() != null) {
                ModLogger.log(Level.ERROR, "Skin [" + libraryFile.getFullName() + "] was requested by " + requestMessage.getPlayer().func_70005_c_() + " but was not found.");
            } else {
                ModLogger.log(Level.ERROR, "Skin [" + libraryFile.getFullName() + "] was requested but was not found.");
            }
        }
        return null;
    }

    private Skin load(ISkinIdentifier skinIdentifier) {
        Skin skin = SkinIOUtils.loadSkinFromFileName(skinIdentifier.getSkinLibraryFile().getFullName() + ".armour");
        this.addSkinToCache(skin, skinIdentifier.getSkinLibraryFile());
        return skin;
    }

    private void addSkinToCache(Skin skin, ILibraryFile libraryFile) {
        if (skin == null) {
            return;
        }
        this.cacheLocalDatabase.add(skin);
        this.cacheMapFileLink.put(libraryFile, skin.lightHash());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void add(LibraryFile libraryFile, int skinId) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            this.cacheMapFileLink.put(libraryFile, skinId);
        }
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
    public ILibraryFile getBackward(int skinId) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            if (this.cacheMapFileLink.getMapBackward().containsKey(skinId)) {
                return this.cacheMapFileLink.getBackward(skinId);
            }
            return null;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void remove(ILibraryFile libraryFile) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            this.cacheMapFileLink.remove(libraryFile);
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
            this.cacheMapFileLink.clear();
        }
    }
}

