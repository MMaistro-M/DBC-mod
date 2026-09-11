/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.collect.Iterables
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.common;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientRequestGameProfile;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerGameProfile;
import riskyken.armourersWorkshop.proxies.CommonProxy;

public final class GameProfileCache {
    private static final ExecutorService profileDownloader = Executors.newFixedThreadPool(2);
    private static final HashMap<String, GameProfile> downloadedCache = new HashMap();
    private static final Cache<String, Boolean> submitted = CacheBuilder.newBuilder().expireAfterWrite(20L, TimeUnit.MINUTES).build();
    private static final ArrayList<WaitingClient> waitingClients = new ArrayList();
    private static final Cache<String, GameProfile> clientProfileCache = CacheBuilder.newBuilder().expireAfterWrite(20L, TimeUnit.MINUTES).build();
    private static final Cache<String, Boolean> clientRequests = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static GameProfile getGameProfileClient(String name, IGameProfileCallback callback) {
        if (StringUtils.func_151246_b((String)name)) {
            return null;
        }
        Object object = clientProfileCache;
        synchronized (object) {
            GameProfile gameProfile = (GameProfile)clientProfileCache.getIfPresent((Object)name);
            if (gameProfile != null) {
                return gameProfile;
            }
        }
        object = clientRequests;
        synchronized (object) {
            if (!clientRequests.asMap().containsKey(name)) {
                clientRequests.put((Object)name, (Object)true);
                PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientRequestGameProfile(new GameProfile(null, name)));
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static GameProfile getGameProfile(GameProfile gameProfile, IGameProfileCallback callback) {
        if (gameProfile == null) {
            return null;
        }
        if (StringUtils.func_151246_b((String)gameProfile.getName())) {
            return null;
        }
        CommonProxy proxy = ArmourersWorkshop.getProxy();
        if (proxy.isLocalPlayer(gameProfile.getName())) {
            if (proxy.haveFullLocalProfile()) {
                return proxy.getLocalGameProfile();
            }
            return null;
        }
        GameProfile cachedProfile = null;
        HashMap<String, GameProfile> hashMap = downloadedCache;
        synchronized (hashMap) {
            cachedProfile = downloadedCache.get(gameProfile.getName());
        }
        if (cachedProfile != null) {
            return cachedProfile;
        }
        hashMap = submitted;
        synchronized (hashMap) {
            if (!submitted.asMap().containsKey(gameProfile.getName())) {
                submitted.put((Object)gameProfile.getName(), (Object)true);
                profileDownloader.submit(new ProfileDownloadThread(gameProfile, callback));
            }
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onServerSentProfile(GameProfile gameProfile) {
        Cache<String, GameProfile> cache = clientProfileCache;
        synchronized (cache) {
            clientProfileCache.put((Object)gameProfile.getName(), (Object)gameProfile);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void onClientRequstProfile(EntityPlayerMP playerEntity, GameProfile gameProfile) {
        ArrayList<WaitingClient> arrayList = waitingClients;
        synchronized (arrayList) {
            GameProfile cacheProfile = downloadedCache.get(gameProfile.getName());
            if (cacheProfile != null) {
                GameProfileCache.sendProfileToClient(playerEntity, cacheProfile);
                return;
            }
            waitingClients.add(new WaitingClient(playerEntity, gameProfile.getName()));
        }
        GameProfileCache.getGameProfile(gameProfile, null);
    }

    private static void sendProfileToClient(EntityPlayerMP playerEntity, GameProfile gameProfile) {
        PacketHandler.networkWrapper.sendTo((IMessage)new MessageServerGameProfile(gameProfile), playerEntity);
    }

    public static interface IGameProfileCallback {
        public void profileDownloaded(GameProfile var1);
    }

    public static class ProfileDownloadThread
    implements Runnable {
        private GameProfile gameProfile;
        private IGameProfileCallback callback;

        public ProfileDownloadThread(GameProfile gameProfile, IGameProfileCallback callback) {
            this.gameProfile = gameProfile;
            this.callback = callback;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public void run() {
            GameProfile newProfile = this.fillProfileProperties(this.gameProfile);
            if (newProfile != null) {
                Cloneable cloneable = downloadedCache;
                synchronized (cloneable) {
                    downloadedCache.put(newProfile.getName(), newProfile);
                }
                if (this.callback != null) {
                    this.callback.profileDownloaded(newProfile);
                }
                cloneable = waitingClients;
                synchronized (cloneable) {
                    for (int i = 0; i < waitingClients.size(); ++i) {
                        WaitingClient wc = (WaitingClient)waitingClients.get(i);
                        if (!wc.getProfileName().equals(newProfile.getName())) continue;
                        GameProfileCache.sendProfileToClient(wc.getEntityPlayer(), newProfile);
                        waitingClients.remove(i);
                    }
                }
            }
        }

        private GameProfile fillProfileProperties(GameProfile gameProfile) {
            Property property;
            if (!gameProfile.isComplete()) {
                gameProfile = ArmourersWorkshop.getProxy().getServer().func_152358_ax().func_152655_a(gameProfile.getName());
            }
            if (gameProfile == null) {
                return null;
            }
            if (gameProfile.isComplete() && !gameProfile.getProperties().containsKey((Object)"textures") && (property = (Property)Iterables.getFirst((Iterable)gameProfile.getProperties().get((Object)"textures"), null)) == null) {
                gameProfile = ArmourersWorkshop.getProxy().getServer().func_147130_as().fillProfileProperties(gameProfile, false);
                return gameProfile;
            }
            return gameProfile;
        }
    }

    public static class WaitingClient {
        private EntityPlayerMP entityPlayer;
        private String profileName;

        public WaitingClient(EntityPlayerMP entityPlayer, String profileName) {
            this.entityPlayer = entityPlayer;
            this.profileName = profileName;
        }

        public EntityPlayerMP getEntityPlayer() {
            return this.entityPlayer;
        }

        public String getProfileName() {
            return this.profileName;
        }
    }
}

