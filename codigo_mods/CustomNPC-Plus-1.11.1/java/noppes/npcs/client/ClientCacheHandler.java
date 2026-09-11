/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import kamkeel.npcs.network.enums.EnumSyncType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ProfileClientConfig;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.gui.customoverlay.OverlayCustom;
import noppes.npcs.client.gui.hud.ClientHudManager;
import noppes.npcs.client.gui.hud.EnumHudComponent;
import noppes.npcs.client.gui.hud.HudComponent;
import noppes.npcs.client.gui.select.GuiSoundSelection;
import noppes.npcs.client.gui.select.GuiTextureSelection;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.util.CacheHashMap;

public class ClientCacheHandler {
    private static final CacheHashMap<String, CacheHashMap.CachedObject<ImageData>> imageDataCache = new CacheHashMap((long)ConfigClient.CacheLife * 60L * 1000L);
    public static PlayerData playerData = new PlayerData();
    public static HashMap<Integer, OverlayCustom> customOverlays = new HashMap();
    public static HashMap<UUID, HashMap<Integer, SkinOverlay>> skinOverlays = new HashMap();
    public static HashMap<UUID, AnimationData> playerAnimations = new HashMap();
    private static String activeServerKey = "";
    private static String lastServerKey = "";
    private static final Map<String, EnumMap<EnumSyncType, Integer>> clientRevisionCache = new HashMap<String, EnumMap<EnumSyncType, Integer>>();
    public static Party party;
    public static boolean allowProfiles;
    public static boolean allowParties;

    public static void setActiveServer(String serverKey, EnumMap<EnumSyncType, Integer> serverRevisions) {
        String previousKey;
        String normalizedKey = serverKey == null ? "" : serverKey;
        lastServerKey = previousKey = activeServerKey == null ? "" : activeServerKey;
        if (!normalizedKey.equals(activeServerKey)) {
            clientRevisionCache.clear();
        }
        if ((activeServerKey = normalizedKey).isEmpty()) {
            return;
        }
        EnumMap cached = clientRevisionCache.computeIfAbsent(activeServerKey, ignored -> new EnumMap(EnumSyncType.class));
        if (serverRevisions != null && !serverRevisions.isEmpty()) {
            cached.keySet().retainAll(serverRevisions.keySet());
        }
    }

    public static String getLastServerKey() {
        return lastServerKey == null ? "" : lastServerKey;
    }

    public static EnumMap<EnumSyncType, Integer> getCachedRevisionsForServer(String serverKey) {
        String key;
        String string = key = serverKey == null ? "" : serverKey;
        if (key.isEmpty()) {
            return new EnumMap<EnumSyncType, Integer>(EnumSyncType.class);
        }
        EnumMap<EnumSyncType, Integer> revisions = clientRevisionCache.get(key);
        if (revisions == null) {
            return new EnumMap<EnumSyncType, Integer>(EnumSyncType.class);
        }
        return new EnumMap<EnumSyncType, Integer>(revisions);
    }

    public static void updateClientRevision(EnumSyncType type, int revision) {
        String key;
        if (revision < 0) {
            return;
        }
        String string = key = activeServerKey == null ? "" : activeServerKey;
        if (key.isEmpty()) {
            return;
        }
        EnumMap revisions = clientRevisionCache.computeIfAbsent(key, ignored -> new EnumMap(EnumSyncType.class));
        revisions.put(type, revision);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ImageData getImageData(String directory) {
        CacheHashMap<String, CacheHashMap.CachedObject<ImageData>> cacheHashMap = imageDataCache;
        synchronized (cacheHashMap) {
            if (!imageDataCache.containsKey(directory)) {
                imageDataCache.put(directory, new CacheHashMap.CachedObject<ImageData>(new ImageData(directory)));
            }
            return (ImageData)((CacheHashMap.CachedObject)imageDataCache.get(directory)).getObject();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static ImageData getNPCTexture(String directory, boolean x64, ResourceLocation resource) {
        CacheHashMap<String, CacheHashMap.CachedObject<ImageData>> cacheHashMap = imageDataCache;
        synchronized (cacheHashMap) {
            if (!imageDataCache.containsKey(resource.func_110623_a())) {
                imageDataCache.put(resource.func_110623_a(), new CacheHashMap.CachedObject<ImageData>(new ImageData(directory, x64, resource)));
            }
            return (ImageData)((CacheHashMap.CachedObject)imageDataCache.get(resource.func_110623_a())).getObject();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean isCachedNPC(ResourceLocation resource) {
        CacheHashMap<String, CacheHashMap.CachedObject<ImageData>> cacheHashMap = imageDataCache;
        synchronized (cacheHashMap) {
            return imageDataCache.containsKey(resource.func_110623_a());
        }
    }

    public static void clearCache() {
        imageDataCache.clear();
        customOverlays.clear();
        skinOverlays.clear();
        playerAnimations.clear();
        ProfileClientConfig.reset();
        GuiSoundSelection.cachedDomains.clear();
        GuiTextureSelection.cachedTextures.clear();
        MusicController.Instance.stopAllSounds();
        HudComponent component = ClientHudManager.getInstance().getHudComponents().get((Object)EnumHudComponent.QuestTracker);
        if (component != null) {
            component.loadData(new NBTTagCompound());
            component.hasData = false;
        }
    }

    public static void clearSkinCache() {
        imageDataCache.clear();
        customOverlays.clear();
        skinOverlays.clear();
        GuiSoundSelection.cachedDomains.clear();
        GuiTextureSelection.cachedTextures.clear();
    }

    static {
        allowProfiles = true;
        allowParties = true;
    }
}

