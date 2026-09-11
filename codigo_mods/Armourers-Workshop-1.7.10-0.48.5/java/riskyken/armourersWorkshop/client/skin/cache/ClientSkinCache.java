/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.client.skin.cache;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.client.model.bake.ModelBakery;
import riskyken.armourersWorkshop.client.skin.cache.FastCache;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.data.ExpiringHashMap;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientRequestSkinData;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.utils.ModLogger;

@SideOnly(value=Side.CLIENT)
public class ClientSkinCache
implements ExpiringHashMap.IExpiringMapCallback<Skin> {
    public static ClientSkinCache INSTANCE;
    private final ExpiringHashMap<ISkinIdentifier, Skin> skinIDMap = new ExpiringHashMap(ConfigHandlerClient.clientModelCacheTime, this);
    private final HashSet<ISkinIdentifier> requestedSkinIDs = new HashSet();
    private final Executor skinRequestExecutor = Executors.newFixedThreadPool(1);

    public static void init() {
        INSTANCE = new ClientSkinCache();
    }

    protected ClientSkinCache() {
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public Skin getSkin(ISkinPointer skinPointer) {
        return this.getSkin(skinPointer.getIdentifier(), true);
    }

    public Skin getSkin(ISkinPointer skinPointer, boolean requestSkin) {
        return this.getSkin(skinPointer.getIdentifier(), requestSkin);
    }

    public Skin getSkin(ISkinIdentifier identifier) {
        return this.getSkin(identifier, true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Skin getSkin(ISkinIdentifier identifier, boolean requestSkin) {
        ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
        synchronized (expiringHashMap) {
            if (this.skinIDMap.containsKey(identifier)) {
                return this.skinIDMap.get(identifier);
            }
        }
        if (requestSkin) {
            this.requestSkinFromServer(identifier);
        }
        return null;
    }

    public void requestSkinFromServer(ISkinPointer skinPointer) {
        this.requestSkinFromServer(skinPointer.getIdentifier());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void requestSkinFromServer(ISkinIdentifier identifier) {
        HashSet<ISkinIdentifier> hashSet = this.requestedSkinIDs;
        synchronized (hashSet) {
            if (!this.requestedSkinIDs.contains(identifier)) {
                this.skinRequestExecutor.execute(new SkinRequestThread(identifier));
                this.requestedSkinIDs.add(identifier);
            }
        }
    }

    public boolean isSkinInCache(ISkinPointer skinPointer) {
        return this.isSkinInCache(skinPointer.getIdentifier());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean isSkinInCache(ISkinIdentifier identifier) {
        ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
        synchronized (expiringHashMap) {
            return this.skinIDMap.containsKey(identifier);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void markSkinAsDirty(ISkinIdentifier identifier) {
        ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
        synchronized (expiringHashMap) {
            this.skinIDMap.remove(identifier);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void receivedModelFromBakery(ModelBakery.BakedSkin bakedSkin) {
        SkinIdentifier identifierRequested = bakedSkin.getSkinIdentifierRequested();
        HashSet<ISkinIdentifier> hashSet = this.requestedSkinIDs;
        synchronized (hashSet) {
            ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
            synchronized (expiringHashMap) {
                if (this.skinIDMap.containsKey(identifierRequested)) {
                    Skin oldSkin = this.skinIDMap.get(identifierRequested);
                    this.skinIDMap.remove(identifierRequested);
                    oldSkin.cleanUpDisplayLists();
                    ModLogger.log("removing skin");
                }
                if (this.requestedSkinIDs.contains(identifierRequested)) {
                    this.skinIDMap.put(identifierRequested, bakedSkin.getSkin());
                    this.requestedSkinIDs.remove(identifierRequested);
                } else {
                    this.skinIDMap.put(bakedSkin.getSkinIdentifierUpdated(), bakedSkin.getSkin());
                    ModLogger.log(Level.WARN, "Got an unknown skin - Identifier: " + bakedSkin.getSkinIdentifierUpdated().toString());
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getCacheSize() {
        ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
        synchronized (expiringHashMap) {
            return this.skinIDMap.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getRequestQueueSize() {
        HashSet<ISkinIdentifier> hashSet = this.requestedSkinIDs;
        synchronized (hashSet) {
            return this.requestedSkinIDs.size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getModelCount() {
        int count = 0;
        ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
        synchronized (expiringHashMap) {
            Object[] keySet = this.skinIDMap.getKeySet().toArray();
            for (int i = 0; i < keySet.length; ++i) {
                ISkinIdentifier key = (ISkinIdentifier)keySet[i];
                Skin skin = this.skinIDMap.getQuiet(key);
                if (skin == null) continue;
                count += skin.getModelCount();
            }
        }
        return count;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getPartCount() {
        int count = 0;
        ExpiringHashMap<ISkinIdentifier, Skin> expiringHashMap = this.skinIDMap;
        synchronized (expiringHashMap) {
            Object[] keySet = this.skinIDMap.getKeySet().toArray();
            for (int i = 0; i < keySet.length; ++i) {
                ISkinIdentifier key = (ISkinIdentifier)keySet[i];
                Skin skin = this.skinIDMap.getQuiet(key);
                count += skin.getPartCount();
            }
        }
        return count;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearCache() {
        Object object = this.skinIDMap;
        synchronized (object) {
            Object[] keySet = this.skinIDMap.getKeySet().toArray();
            for (int i = 0; i < keySet.length; ++i) {
                ISkinIdentifier key = (ISkinIdentifier)keySet[i];
                Skin customArmourItemData = this.skinIDMap.get(key);
                this.skinIDMap.remove(key);
                customArmourItemData.cleanUpDisplayLists();
            }
        }
        object = this.requestedSkinIDs;
        synchronized (object) {
            this.requestedSkinIDs.clear();
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT & event.type == TickEvent.Type.CLIENT & event.phase == TickEvent.Phase.END) {
            this.skinIDMap.cleanupCheck();
        }
    }

    @Override
    public void itemExpired(Skin mapItem) {
        mapItem.cleanUpDisplayLists();
    }

    private static class SkinRequestThread
    implements Runnable {
        private ISkinIdentifier skinIdentifier;

        public SkinRequestThread(ISkinIdentifier skinIdentifier) {
            this.skinIdentifier = skinIdentifier;
        }

        @Override
        public void run() {
            Thread.currentThread().setPriority(1);
            Skin skin = FastCache.INSTANCE.loadSkin(this.skinIdentifier);
            if (skin != null) {
                ModelBakery.INSTANCE.receivedUnbakedModel(skin, new SkinIdentifier(this.skinIdentifier), new SkinIdentifier(this.skinIdentifier));
            } else {
                PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientRequestSkinData(this.skinIdentifier));
            }
        }
    }
}

