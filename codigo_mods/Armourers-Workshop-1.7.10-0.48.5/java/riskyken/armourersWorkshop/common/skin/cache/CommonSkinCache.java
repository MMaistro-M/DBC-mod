/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayerMP
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.common.skin.cache;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.api.common.library.ILibraryFile;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.data.ExpiringHashMap;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerSendSkinData;
import riskyken.armourersWorkshop.common.skin.cache.SkinCacheGlobal;
import riskyken.armourersWorkshop.common.skin.cache.SkinCacheLocalDatabase;
import riskyken.armourersWorkshop.common.skin.cache.SkinCacheLocalFile;
import riskyken.armourersWorkshop.common.skin.cache.SkinRequestMessage;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

public final class CommonSkinCache
implements Runnable,
ExpiringHashMap.IExpiringMapCallback<Skin> {
    public static final CommonSkinCache INSTANCE = new CommonSkinCache();
    private final SkinCacheLocalDatabase cacheLocalDatabase;
    private final SkinCacheLocalFile cacheLocalFile;
    private final SkinCacheGlobal cacheGlobal;
    private volatile Thread serverSkinThread = null;
    private ArrayList<SkinRequestMessage> messageQueue = new ArrayList();
    private final Object messageQueueLock = new Object();
    private ArrayList<SkinRequestMessage> messageWaitQueue = new ArrayList();
    private final Object messageWaitQueueLock = new Object();
    private long lastMessageSendTick;
    private boolean madeDatabase = false;

    public CommonSkinCache() {
        this.cacheLocalDatabase = new SkinCacheLocalDatabase(this);
        this.cacheLocalFile = new SkinCacheLocalFile(this.cacheLocalDatabase);
        this.cacheGlobal = new SkinCacheGlobal(this.cacheLocalDatabase);
    }

    public void clearAll() {
        this.cacheLocalDatabase.clear();
        this.cacheLocalFile.clear();
        this.cacheGlobal.clear();
    }

    public void serverStarted() {
        SkinIOUtils.makeDatabaseDirectory();
        this.serverSkinThread = new Thread((Runnable)this, "Armourer's Workshop Server Skin Thread");
        this.serverSkinThread.start();
    }

    public void serverStopped() {
        this.clearAll();
        this.serverSkinThread = null;
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        ModLogger.log("Starting server skin thread.");
        while (this.serverSkinThread == thisThread) {
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException interruptedException) {
                // empty catch block
            }
            this.cacheLocalDatabase.doSkinLoading();
            this.cacheLocalFile.doSkinLoading();
            this.cacheGlobal.doSkinLoading();
            this.processMessageQueue();
        }
        ModLogger.log("Stopped server skin thread.");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clientRequestEquipmentData(ISkinIdentifier skinIdentifier, EntityPlayerMP player) {
        SkinRequestMessage queueMessage = new SkinRequestMessage(skinIdentifier, player);
        Object object = this.messageQueueLock;
        synchronized (object) {
            this.messageQueue.add(queueMessage);
        }
    }

    private void processMessageQueue() {
        if (ConfigHandler.serverSkinSendRate > 1) {
            long curTick = System.currentTimeMillis();
            if (curTick >= this.lastMessageSendTick + (long)(60000 / ConfigHandler.serverSkinSendRate)) {
                this.lastMessageSendTick = curTick;
                this.processNextMessage();
            }
        } else {
            this.processNextMessage();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void processNextMessage() {
        Object object = this.messageQueueLock;
        synchronized (object) {
            if (this.messageQueue.size() > 0) {
                this.processMessage(this.messageQueue.get(0));
                this.messageQueue.remove(0);
            }
        }
    }

    public Skin addSkinToCache(InputStream inputStream) {
        Skin skin = SkinIOUtils.loadSkinFromStream(inputStream);
        if (skin != null) {
            this.addEquipmentDataToCache(skin, null);
            return skin;
        }
        return null;
    }

    private void processMessage(SkinRequestMessage queueMessage) {
        ISkinIdentifier identifier = queueMessage.getSkinIdentifier();
        EntityPlayerMP player = queueMessage.getPlayer();
        if (identifier.hasLocalId()) {
            this.sendLocalDatabaseSkinToClient(queueMessage);
        } else if (identifier.hasLibraryFile()) {
            this.sendLocalFileSkinToClient(queueMessage);
        } else if (identifier.hasGlobalId()) {
            this.sendGlobalDatabaseSkinToClient(queueMessage);
        } else {
            ModLogger.log(Level.ERROR, "Player " + player.func_70005_c_() + " requested a skin with no vaid ID:" + identifier.toString());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void sendLocalDatabaseSkinToClient(SkinRequestMessage requestMessage) {
        Skin skin = this.cacheLocalDatabase.get(requestMessage, true);
        if (skin != null) {
            this.sendSkinToClient(skin, requestMessage);
        } else {
            Object object = this.messageWaitQueueLock;
            synchronized (object) {
                this.messageWaitQueue.add(requestMessage);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void sendLocalFileSkinToClient(SkinRequestMessage requestMessage) {
        Skin skin = this.cacheLocalFile.get(requestMessage, true);
        if (skin != null) {
            this.sendSkinToClient(skin, requestMessage);
        } else {
            Object object = this.messageWaitQueueLock;
            synchronized (object) {
                this.messageWaitQueue.add(requestMessage);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void sendGlobalDatabaseSkinToClient(SkinRequestMessage requestMessage) {
        Skin skin = this.cacheGlobal.get(requestMessage, true);
        if (skin != null) {
            this.sendSkinToClient(skin, requestMessage);
        } else {
            Object object = this.messageWaitQueueLock;
            synchronized (object) {
                this.messageWaitQueue.add(requestMessage);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onSkinLoaded(Skin skin, SkinRequestMessage requestMessage) {
        if (requestMessage.getPlayer() == null) {
            return;
        }
        Object object = this.messageWaitQueueLock;
        synchronized (object) {
            for (int i = 0; i < this.messageWaitQueue.size(); ++i) {
                if (this.messageWaitQueue.get(i).getSkinIdentifier() != requestMessage.getSkinIdentifier()) continue;
                this.sendSkinToClient(skin, requestMessage);
                this.messageWaitQueue.remove(i);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onGlobalSkinLoaded(Skin skin, int globalId) {
        Object object = this.messageWaitQueueLock;
        synchronized (object) {
            for (int i = 0; i < this.messageWaitQueue.size(); ++i) {
                if (this.messageWaitQueue.get(i).getSkinIdentifier().getSkinGlobalId() != globalId) continue;
                this.sendSkinToClient(skin, this.messageWaitQueue.get(i));
                this.messageWaitQueue.remove(i);
            }
        }
    }

    private void sendSkinToClient(Skin skin, SkinRequestMessage requestMessage) {
        SkinIdentifier requestIdentifier;
        skin.requestId = requestIdentifier = (SkinIdentifier)requestMessage.getSkinIdentifier();
        PacketHandler.networkWrapper.sendTo((IMessage)new MessageServerSendSkinData(requestIdentifier, this.getFullIdentifier(skin, requestIdentifier), skin), requestMessage.getPlayer());
    }

    public SkinIdentifier getFullIdentifier(Skin skin, ISkinIdentifier skinIdentifier) {
        int localId = skin.lightHash();
        ISkinType skinType = skin.getSkinType();
        ILibraryFile libraryFile = null;
        int globalId = 0;
        try {
            if (this.cacheLocalFile.containsValue(skin.lightHash())) {
                libraryFile = this.cacheLocalFile.getBackward(skin.lightHash());
            }
            if (this.cacheGlobal.containsValue(skin.lightHash())) {
                globalId = this.cacheGlobal.getBackward(skin.lightHash());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new SkinIdentifier(localId, libraryFile, globalId, skinType);
    }

    public void addEquipmentDataToCache(Skin skin, LibraryFile libraryFile) {
        this.cacheLocalDatabase.add(skin);
        if (libraryFile != null) {
            this.cacheLocalFile.add(libraryFile, skin.lightHash());
        }
    }

    public void clearFileNameIdLink(LibraryFile libraryFile) {
        this.cacheLocalFile.remove(libraryFile);
    }

    public Skin getSkin(ISkinPointer skinPointer) {
        return this.getSkin(skinPointer.getIdentifier());
    }

    public Skin getSkin(ISkinIdentifier identifier) {
        if (identifier.hasLocalId()) {
            return this.cacheLocalDatabase.get(identifier, false);
        }
        if (identifier.hasLibraryFile()) {
            return this.cacheLocalFile.get(identifier, false);
        }
        if (identifier.hasGlobalId()) {
            return this.cacheGlobal.get(identifier, false);
        }
        ModLogger.log(Level.ERROR, "Server requested a skin with no vaid ID:" + identifier.toString());
        return null;
    }

    public Skin softGetSkin(ISkinIdentifier identifier) {
        if (identifier.hasLocalId()) {
            return this.cacheLocalDatabase.get(identifier, true);
        }
        if (identifier.hasLibraryFile()) {
            return this.cacheLocalFile.get(identifier, true);
        }
        if (identifier.hasGlobalId()) {
            return this.cacheGlobal.get(identifier, true);
        }
        ModLogger.log(Level.ERROR, "Server requested a skin with no vaid ID:" + identifier.toString());
        return null;
    }

    public int size() {
        return this.cacheLocalDatabase.size();
    }

    public int fileLinkSize() {
        return this.cacheLocalFile.size();
    }

    public int globalLinkSize() {
        return this.cacheGlobal.size();
    }

    @Override
    public void itemExpired(Skin mapItem) {
        if (mapItem == null) {
            return;
        }
        int skinId = mapItem.lightHash();
        if (this.cacheLocalFile.containsValue(skinId)) {
            ILibraryFile libraryFile = this.cacheLocalFile.getBackward(skinId);
            this.cacheLocalFile.remove(libraryFile);
        }
        if (this.cacheGlobal.containsValue(skinId)) {
            int globalId = this.cacheGlobal.getBackward(skinId);
            this.cacheGlobal.remove(globalId);
        }
    }
}

