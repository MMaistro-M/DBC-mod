/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  cpw.mods.fml.relauncher.Side
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.cache;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import java.util.ArrayList;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.data.ExpiringHashMap;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.cache.SkinRequestMessage;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public class SkinCacheLocalDatabase
implements ExpiringHashMap.IExpiringMapCallback<Skin> {
    private final ExpiringHashMap<Integer, Skin> cacheMapDatabase;
    private final Object cacheMapLock = new Object();
    private final ArrayList<SkinRequestMessage> skinLoadQueue;
    private final Object skinLoadQueueLock = new Object();
    private final ExpiringHashMap.IExpiringMapCallback<Skin> callback;

    public SkinCacheLocalDatabase(ExpiringHashMap.IExpiringMapCallback<Skin> callback) {
        this.callback = callback;
        this.cacheMapDatabase = new ExpiringHashMap(ConfigHandler.serverModelCacheTime, this);
        this.skinLoadQueue = new ArrayList();
        FMLCommonHandler.instance().bus().register((Object)this);
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
                    Skin skin = this.load(requestMessage.getSkinIdentifier().getSkinLocalId());
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
        int skinId = requestMessage.getSkinIdentifier().getSkinLocalId();
        Object object = this.cacheMapLock;
        synchronized (object) {
            if (!this.cacheMapDatabase.containsKey(skinId)) {
                if (softLoad) {
                    Object object2 = this.skinLoadQueueLock;
                    synchronized (object2) {
                        this.skinLoadQueue.add(requestMessage);
                    }
                    return null;
                }
                this.load(skinId);
            }
            if (this.cacheMapDatabase.containsKey(skinId)) {
                return this.cacheMapDatabase.get(skinId);
            }
            if (requestMessage.getPlayer() != null) {
                ModLogger.log(Level.ERROR, "Equipment id:" + String.valueOf(skinId) + " was requested by " + requestMessage.getPlayer().func_70005_c_() + " but was not found.");
            } else {
                ModLogger.log(Level.ERROR, "Equipment id:" + String.valueOf(skinId) + " was requested but was not found.");
            }
            return null;
        }
    }

    private Skin load(int skinId) {
        if (this.haveSkinOnDisk(skinId)) {
            Skin skin = this.loadSkinFromDisk(skinId);
            if (skin != null) {
                this.addSkinDataToCache(skin, skinId);
                if (skin.hashCode() != skinId) {
                    this.addSkinDataToCache(skin, skin.hashCode());
                }
                return skin;
            }
            ModLogger.log(Level.ERROR, String.format("Failed to load skin id:%s from disk.", String.valueOf(skinId)));
        } else {
            ModLogger.log(Level.WARN, String.format("The skin id:%s was not found on the disk.", String.valueOf(skinId)));
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void add(Skin skin) {
        Object object = this.cacheMapLock;
        synchronized (object) {
            this.addSkinDataToCache(skin, skin.lightHash());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int size() {
        Object object = this.cacheMapLock;
        synchronized (object) {
            return this.cacheMapDatabase.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clear() {
        Object object = this.cacheMapLock;
        synchronized (object) {
            this.cacheMapDatabase.clear();
        }
    }

    @SubscribeEvent
    public void onServerTickEvent(TickEvent.ServerTickEvent event) {
        if (event.side == Side.SERVER && event.type == TickEvent.Type.SERVER && event.phase == TickEvent.Phase.END) {
            this.cacheMapDatabase.cleanupCheck();
        }
    }

    @Override
    public void itemExpired(Skin mapItem) {
        if (this.callback != null) {
            this.callback.itemExpired(mapItem);
        }
    }

    private void addSkinDataToCache(Skin skin, int skinId) {
        if (skin == null) {
            return;
        }
        if (!this.haveSkinOnDisk(skinId)) {
            this.saveSkinToDisk(skin);
        }
        if (!this.cacheMapDatabase.containsKey(skinId)) {
            this.cacheMapDatabase.put(skinId, skin);
        }
    }

    private void saveSkinToDisk(Skin skin) {
        File file = new File(SkinIOUtils.getSkinDatabaseDirectory(), String.valueOf(skin.hashCode()));
        SkinIOUtils.saveSkinToFile(file, skin);
    }

    private boolean haveSkinOnDisk(int equipmentId) {
        File file = new File(SkinIOUtils.getSkinDatabaseDirectory(), String.valueOf(equipmentId));
        return file.exists();
    }

    private Skin loadSkinFromDisk(int equipmentId) {
        File file = new File(SkinIOUtils.getSkinDatabaseDirectory(), String.valueOf(equipmentId));
        return SkinIOUtils.loadSkinFromFile(file);
    }
}

