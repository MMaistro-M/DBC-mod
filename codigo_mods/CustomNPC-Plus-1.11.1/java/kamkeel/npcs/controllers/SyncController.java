/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 */
package kamkeel.npcs.controllers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumSyncAction;
import kamkeel.npcs.network.enums.EnumSyncType;
import kamkeel.npcs.network.packets.data.LoginPacket;
import kamkeel.npcs.network.packets.data.ProfileSharedQuestPacket;
import kamkeel.npcs.network.packets.data.ability.AbilityCooldownSyncPacket;
import kamkeel.npcs.network.packets.data.ability.AbilityHotbarSyncPacket;
import kamkeel.npcs.network.packets.data.ability.PlayerAbilitySyncPacket;
import kamkeel.npcs.network.packets.data.large.SyncEffectPacket;
import kamkeel.npcs.network.packets.data.large.SyncPacket;
import kamkeel.npcs.network.packets.request.party.PartyInfoPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogCategory;
import noppes.npcs.controllers.data.EffectKey;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicCycle;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerEffect;
import noppes.npcs.controllers.data.Quest;
import noppes.npcs.controllers.data.QuestCategory;
import noppes.npcs.controllers.data.RecipeAnvil;
import noppes.npcs.controllers.data.RecipeCarpentry;

public class SyncController {
    public static boolean DEBUG_SYNC_LOGGING = false;
    private static final EnumMap<EnumSyncType, SyncCacheEntry> cacheEntries = new EnumMap(EnumSyncType.class);
    private static final ConcurrentHashMap<UUID, PlayerSyncState> playerSyncState = new ConcurrentHashMap();
    private static final EnumSyncType[] LOGIN_SYNC_TYPES = new EnumSyncType[]{EnumSyncType.FACTION, EnumSyncType.DIALOG_CATEGORY, EnumSyncType.QUEST_CATEGORY, EnumSyncType.WORKBENCH_RECIPES, EnumSyncType.CARPENTRY_RECIPES, EnumSyncType.ANVIL_RECIPES, EnumSyncType.CUSTOM_EFFECTS, EnumSyncType.MAGIC, EnumSyncType.MAGIC_CYCLE, EnumSyncType.CUSTOM_ABILITY, EnumSyncType.CHAINED_ABILITY};
    private static final String SERVER_IDENTITY_KEY = UUID.randomUUID().toString();

    private static void debug(String message, Object ... args) {
        if (DEBUG_SYNC_LOGGING) {
            System.out.println("[SyncController] " + String.format(message, args));
        }
    }

    public static void load() {
        cacheEntries.clear();
        playerSyncState.clear();
        SyncController.registerCache(EnumSyncType.FACTION, SyncController::factionsNBT);
        SyncController.registerCache(EnumSyncType.DIALOG_CATEGORY, SyncController::dialogCategoriesNBT);
        SyncController.registerCache(EnumSyncType.QUEST_CATEGORY, SyncController::questCategoriesNBT);
        SyncController.registerCache(EnumSyncType.WORKBENCH_RECIPES, SyncController::workbenchNBT);
        SyncController.registerCache(EnumSyncType.CARPENTRY_RECIPES, SyncController::carpentryNBT);
        SyncController.registerCache(EnumSyncType.ANVIL_RECIPES, SyncController::anvilNBT);
        SyncController.registerCache(EnumSyncType.CUSTOM_EFFECTS, SyncController::customEffectsNBT);
        SyncController.registerCache(EnumSyncType.MAGIC, SyncController::magicsNBT);
        SyncController.registerCache(EnumSyncType.MAGIC_CYCLE, SyncController::magicCyclesNBT);
        SyncController.registerCache(EnumSyncType.CUSTOM_ABILITY, SyncController::customAbilitiesNBT);
        SyncController.registerCache(EnumSyncType.CHAINED_ABILITY, SyncController::chainedAbilitiesNBT);
    }

    public static void syncPlayer(EntityPlayerMP player) {
        SyncController.syncPlayerInternal(player, true);
    }

    private static void syncPlayerInternal(EntityPlayerMP player, boolean includePostPackets) {
        PlayerSyncState state = playerSyncState.computeIfAbsent(player.func_110124_au(), x$0 -> new PlayerSyncState((UUID)x$0));
        for (EnumSyncType type : LOGIN_SYNC_TYPES) {
            CachedSyncPayload payload;
            SyncCacheEntry entry = cacheEntries.get((Object)type);
            if (entry == null) continue;
            int currentRevision = entry.getRevisionValue();
            int lastRevision = state.getRevision(type);
            if (lastRevision == currentRevision || (payload = entry.getPayload(type)) == null || lastRevision == payload.getRevision()) continue;
            SyncController.debug("Sending %s data to %s", new Object[]{type, player.func_70005_c_()});
            PacketHandler.Instance.sendToPlayer(new SyncPacket(type, payload), player);
            state.updateRevision(type, payload.getRevision());
        }
        if (includePostPackets) {
            SyncController.sendPostLoginPackets(player);
        }
    }

    public static void beginLogin(EntityPlayerMP player) {
        playerSyncState.computeIfAbsent(player.func_110124_au(), x$0 -> new PlayerSyncState((UUID)x$0));
        PacketHandler.Instance.sendToPlayer(new LoginPacket(SyncController.getServerCacheKey(), SyncController.getServerRevisionSnapshot()), player);
    }

    public static void handleClientRevisionReport(EntityPlayerMP player, String serverKey, String previousServerKey, Map<EnumSyncType, Integer> clientRevisions) {
        String currentServerKey = SyncController.getServerCacheKey();
        PlayerSyncState state = playerSyncState.computeIfAbsent(player.func_110124_au(), x$0 -> new PlayerSyncState((UUID)x$0));
        if (!currentServerKey.equals(serverKey)) {
            state.reset();
            SyncController.syncPlayer(player);
            return;
        }
        if (!(currentServerKey.isEmpty() || previousServerKey != null && currentServerKey.equals(previousServerKey))) {
            state.reset();
            SyncController.syncPlayer(player);
            return;
        }
        if (clientRevisions == null || clientRevisions.isEmpty()) {
            SyncController.debug("Client %s reported no cached revisions; forcing full sync", player.func_70005_c_());
            state.reset();
        } else {
            state.applyHandshake(clientRevisions);
        }
        SyncController.syncPlayer(player);
    }

    private static void sendPostLoginPackets(EntityPlayerMP player) {
        DBCAddon.instance.syncPlayer(player);
        SyncController.syncPlayerData(player, false);
        PartyInfoPacket.sendPartyData(player);
        ProfileSharedQuestPacket.sendToPlayer(player);
        PlayerData data = PlayerData.get((EntityPlayer)player);
        if (data != null) {
            data.skinOverlays.updateClient();
        }
    }

    private static EnumMap<EnumSyncType, Integer> getServerRevisionSnapshot() {
        EnumMap<EnumSyncType, Integer> snapshot = new EnumMap<EnumSyncType, Integer>(EnumSyncType.class);
        for (EnumSyncType type : LOGIN_SYNC_TYPES) {
            SyncCacheEntry entry = cacheEntries.get((Object)type);
            if (entry == null) continue;
            snapshot.put(type, entry.getRevisionValue());
        }
        return snapshot;
    }

    private static String getServerCacheKey() {
        MinecraftServer server = MinecraftServer.func_71276_C();
        if (server == null) {
            return "";
        }
        if (!server.func_71262_S()) {
            return "";
        }
        return SERVER_IDENTITY_KEY;
    }

    public static int getCurrentRevision(EnumSyncType type) {
        SyncCacheEntry entry = cacheEntries.get((Object)type);
        return entry == null ? -1 : entry.getRevisionValue();
    }

    public static NBTTagCompound workbenchNBT() {
        RecipeController controller = RecipeController.Instance;
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (RecipeCarpentry recipe : controller.globalRecipes.values()) {
            list.func_74742_a((NBTBase)recipe.writeNBT(false));
        }
        compound.func_74782_a("recipes", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound carpentryNBT() {
        RecipeController controller = RecipeController.Instance;
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (RecipeCarpentry recipe : controller.carpentryRecipes.values()) {
            list.func_74742_a((NBTBase)recipe.writeNBT(false));
        }
        compound.func_74782_a("recipes", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound anvilNBT() {
        RecipeController controller = RecipeController.Instance;
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (RecipeAnvil recipe : controller.anvilRecipes.values()) {
            list.func_74742_a((NBTBase)recipe.writeNBT(false));
        }
        compound.func_74782_a("recipes", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound factionsNBT() {
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (Faction faction : FactionController.getInstance().factions.values()) {
            NBTTagCompound factioNBT = new NBTTagCompound();
            faction.writeNBT(factioNBT);
            list.func_74742_a((NBTBase)factioNBT);
        }
        compound.func_74782_a("Factions", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound dialogCategoriesNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList categoryList = new NBTTagList();
        for (DialogCategory category : DialogController.Instance.categories.values()) {
            NBTTagCompound questCompound = new NBTTagCompound();
            NBTTagList dialogList = new NBTTagList();
            for (int dialogID : category.dialogs.keySet()) {
                Dialog quest = category.dialogs.get(dialogID);
                dialogList.func_74742_a((NBTBase)quest.writeToNBT(new NBTTagCompound()));
            }
            questCompound.func_74782_a("Data", (NBTBase)dialogList);
            questCompound.func_74782_a("CatNBT", (NBTBase)category.writeSmallNBT(new NBTTagCompound()));
            categoryList.func_74742_a((NBTBase)questCompound);
        }
        compound.func_74782_a("DialogCategories", (NBTBase)categoryList);
        return compound;
    }

    public static NBTTagCompound questCategoriesNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagList categoryList = new NBTTagList();
        for (QuestCategory category : QuestController.Instance.categories.values()) {
            NBTTagCompound questCompound = new NBTTagCompound();
            NBTTagList questList = new NBTTagList();
            for (int questID : category.quests.keySet()) {
                Quest quest = category.quests.get(questID);
                questList.func_74742_a((NBTBase)quest.writeToNBT(new NBTTagCompound()));
            }
            questCompound.func_74782_a("Data", (NBTBase)questList);
            questCompound.func_74782_a("CatNBT", (NBTBase)category.writeSmallNBT(new NBTTagCompound()));
            categoryList.func_74742_a((NBTBase)questCompound);
        }
        compound.func_74782_a("QuestCategories", (NBTBase)categoryList);
        return compound;
    }

    public static NBTTagCompound customEffectsNBT() {
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (CustomEffect effect : CustomEffectController.getInstance().getCustomEffects().values()) {
            list.func_74742_a((NBTBase)effect.writeToNBT(false));
        }
        compound.func_74782_a("Data", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound magicsNBT() {
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (Magic magic : MagicController.getInstance().magics.values()) {
            NBTTagCompound magicCompound = new NBTTagCompound();
            magic.writeNBT(magicCompound);
            list.func_74742_a((NBTBase)magicCompound);
        }
        compound.func_74782_a("Data", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound magicCyclesNBT() {
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (MagicCycle cycle : MagicController.getInstance().cycles.values()) {
            NBTTagCompound cycleCompound = new NBTTagCompound();
            cycle.writeNBT(cycleCompound);
            list.func_74742_a((NBTBase)cycleCompound);
        }
        compound.func_74782_a("Data", (NBTBase)list);
        return compound;
    }

    public static NBTTagCompound customAbilitiesNBT() {
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (Map.Entry<String, Ability> entry : AbilityController.Instance.getCustomAbilities().entrySet()) {
            NBTTagCompound abilityNBT = entry.getValue().writeNBT(false);
            abilityNBT.func_74778_a("CustomAbilityId", entry.getKey());
            list.func_74742_a((NBTBase)abilityNBT);
        }
        compound.func_74782_a("Data", (NBTBase)list);
        return compound;
    }

    public static void syncAllCustomAbilities() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.CUSTOM_ABILITY);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.CUSTOM_ABILITY, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.CUSTOM_ABILITY, payload.getRevision());
    }

    public static NBTTagCompound chainedAbilitiesNBT() {
        NBTTagList list = new NBTTagList();
        NBTTagCompound compound = new NBTTagCompound();
        for (Map.Entry<String, ChainedAbility> entry : AbilityController.Instance.getChainedAbilities().entrySet()) {
            NBTTagCompound chainNBT = entry.getValue().writeNBT(false);
            chainNBT.func_74778_a("ChainedAbilityId", entry.getKey());
            list.func_74742_a((NBTBase)chainNBT);
        }
        compound.func_74782_a("Data", (NBTBase)list);
        return compound;
    }

    public static void syncAllChainedAbilities() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.CHAINED_ABILITY);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.CHAINED_ABILITY, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.CHAINED_ABILITY, payload.getRevision());
    }

    public static void syncPlayerData(EntityPlayerMP player, boolean update) {
        PlayerData data = PlayerData.get((EntityPlayer)player);
        if (data != null) {
            if (update) {
                PacketHandler.Instance.sendToPlayer(new SyncPacket(EnumSyncType.PLAYERDATA, EnumSyncAction.UPDATE, -1, data.getSyncNBT()), player);
            } else {
                PacketHandler.Instance.sendToPlayer(new SyncPacket(EnumSyncType.PLAYERDATA, EnumSyncAction.RELOAD, -1, data.getSyncNBTFull()), player);
            }
        }
    }

    public static void syncRemove(EnumSyncType enumSyncType, int id) {
        Map<EnumSyncType, Integer> revisions = SyncController.invalidateCaches(enumSyncType);
        int revision = revisions.getOrDefault((Object)enumSyncType, SyncController.getCurrentRevision(enumSyncType));
        PacketHandler.Instance.sendToAll(new SyncPacket(enumSyncType, EnumSyncAction.REMOVE, id, revision, new NBTTagCompound()));
        SyncController.updateAllPlayerRevisions(revisions);
    }

    public static void syncAllDialogs() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.DIALOG_CATEGORY);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.DIALOG_CATEGORY, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.DIALOG_CATEGORY, payload.getRevision());
    }

    public static void syncAllQuests() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.QUEST_CATEGORY);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.QUEST_CATEGORY, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.QUEST_CATEGORY, payload.getRevision());
    }

    public static void syncAllWorkbenchRecipes() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.WORKBENCH_RECIPES);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.WORKBENCH_RECIPES, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.WORKBENCH_RECIPES, payload.getRevision());
    }

    public static void syncAllCarpentryRecipes() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.CARPENTRY_RECIPES);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.CARPENTRY_RECIPES, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.CARPENTRY_RECIPES, payload.getRevision());
    }

    public static void syncAllAnvilRecipes() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.ANVIL_RECIPES);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.ANVIL_RECIPES, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.ANVIL_RECIPES, payload.getRevision());
    }

    public static void syncAllCustomEffects() {
        CachedSyncPayload payload = SyncController.rebuildNow(EnumSyncType.CUSTOM_EFFECTS);
        if (payload == null) {
            return;
        }
        PacketHandler.Instance.sendToAll(new SyncPacket(EnumSyncType.CUSTOM_EFFECTS, payload));
        SyncController.updateAllPlayerRevisions(EnumSyncType.CUSTOM_EFFECTS, payload.getRevision());
    }

    @SideOnly(value=Side.CLIENT)
    public static void clientSync(EnumSyncType enumSyncType, int revision, NBTTagCompound fullCompound) {
        switch (enumSyncType) {
            case FACTION: {
                NBTTagList list = fullCompound.func_150295_c("Factions", 10);
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    Faction faction = new Faction();
                    faction.readNBT(list.func_150305_b(i));
                    FactionController.getInstance().factionsSync.put(faction.id, faction);
                }
                FactionController.getInstance().factions = FactionController.getInstance().factionsSync;
                FactionController.getInstance().factionsSync = new HashMap();
                break;
            }
            case DIALOG_CATEGORY: {
                if (!fullCompound.func_82582_d()) {
                    NBTTagList categories = fullCompound.func_150295_c("DialogCategories", 10);
                    for (int j = 0; j < fullCompound.func_150295_c("DialogCategories", 10).func_74745_c(); ++j) {
                        NBTTagCompound categoryCompound = categories.func_150305_b(j);
                        if (categoryCompound.func_82582_d()) continue;
                        Object category = new DialogCategory();
                        ((DialogCategory)category).readSmallNBT(categoryCompound.func_74775_l("CatNBT"));
                        NBTTagList dialogList = categoryCompound.func_150295_c("Data", 10);
                        if (DialogController.Instance.categoriesSync.containsKey(((DialogCategory)category).id)) {
                            category = DialogController.Instance.categoriesSync.get(((DialogCategory)category).id);
                            ((DialogCategory)category).readSmallNBT(categoryCompound.func_74775_l("CatNBT"));
                        }
                        for (int i = 0; i < dialogList.func_74745_c(); ++i) {
                            Dialog dialog = new Dialog();
                            dialog.readNBT(dialogList.func_150305_b(i));
                            dialog.category = category;
                            ((DialogCategory)category).dialogs.put(dialog.id, dialog);
                        }
                        DialogController.Instance.categoriesSync.put(((DialogCategory)category).id, (DialogCategory)category);
                    }
                }
                HashMap<Integer, Dialog> dialogs = new HashMap<Integer, Dialog>();
                for (DialogCategory category : DialogController.Instance.categoriesSync.values()) {
                    for (Dialog dialog : category.dialogs.values()) {
                        dialogs.put(dialog.id, dialog);
                    }
                }
                DialogController.Instance.categories = DialogController.Instance.categoriesSync;
                DialogController.Instance.dialogs = dialogs;
                DialogController.Instance.categoriesSync = new HashMap();
                break;
            }
            case QUEST_CATEGORY: {
                if (!fullCompound.func_82582_d()) {
                    NBTTagList categories = fullCompound.func_150295_c("QuestCategories", 10);
                    for (int j = 0; j < fullCompound.func_150295_c("QuestCategories", 10).func_74745_c(); ++j) {
                        NBTTagCompound categoryCompound = categories.func_150305_b(j);
                        if (categoryCompound.func_82582_d()) continue;
                        Object category = new QuestCategory();
                        ((QuestCategory)category).readSmallNBT(categoryCompound.func_74775_l("CatNBT"));
                        NBTTagList questList = categoryCompound.func_150295_c("Data", 10);
                        if (QuestController.Instance.categoriesSync.containsKey(((QuestCategory)category).id)) {
                            category = QuestController.Instance.categoriesSync.get(((QuestCategory)category).id);
                            ((QuestCategory)category).readSmallNBT(categoryCompound.func_74775_l("CatNBT"));
                        }
                        for (int i = 0; i < questList.func_74745_c(); ++i) {
                            Quest quest = new Quest();
                            quest.readNBT(questList.func_150305_b(i));
                            quest.category = category;
                            ((QuestCategory)category).quests.put(quest.id, quest);
                        }
                        QuestController.Instance.categoriesSync.put(((QuestCategory)category).id, (QuestCategory)category);
                    }
                }
                HashMap<Integer, Quest> quests = new HashMap<Integer, Quest>();
                for (QuestCategory category : QuestController.Instance.categoriesSync.values()) {
                    for (Quest quest : category.quests.values()) {
                        quests.put(quest.id, quest);
                    }
                }
                QuestController.Instance.categories = QuestController.Instance.categoriesSync;
                QuestController.Instance.quests = quests;
                QuestController.Instance.categoriesSync = new HashMap();
                break;
            }
            case PLAYERDATA: {
                ClientCacheHandler.playerData.setSyncNBTFull(fullCompound);
                break;
            }
            case MAGIC: {
                NBTTagList list = fullCompound.func_150295_c("Data", 10);
                MagicController mc = MagicController.getInstance();
                mc.magicSync.clear();
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    Magic magic = new Magic();
                    magic.readNBT(list.func_150305_b(i));
                    mc.magicSync.put(magic.id, magic);
                }
                mc.magics.clear();
                mc.magics.putAll(mc.magicSync);
                mc.magicSync.clear();
                break;
            }
            case MAGIC_CYCLE: {
                NBTTagList list = fullCompound.func_150295_c("Data", 10);
                MagicController mc = MagicController.getInstance();
                mc.cyclesSync.clear();
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    MagicCycle cycle = new MagicCycle();
                    cycle.readNBT(list.func_150305_b(i));
                    mc.cyclesSync.put(cycle.id, cycle);
                }
                mc.cycles.clear();
                mc.cycles.putAll(mc.cyclesSync);
                mc.cyclesSync.clear();
                break;
            }
            case WORKBENCH_RECIPES: {
                NBTTagList list = fullCompound.func_150295_c("recipes", 10);
                if (list == null) {
                    return;
                }
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound recipeCompound = list.func_150305_b(i);
                    RecipeCarpentry recipe = RecipeCarpentry.create(recipeCompound);
                    recipe.readNBT(recipeCompound);
                    RecipeController.syncRecipes.put(recipe.id, recipe);
                }
                RecipeController.reloadGlobalRecipes(RecipeController.syncRecipes);
                RecipeController.syncRecipes = new HashMap();
                break;
            }
            case CARPENTRY_RECIPES: {
                NBTTagList list = fullCompound.func_150295_c("recipes", 10);
                if (list == null) {
                    return;
                }
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound recipeCompound = list.func_150305_b(i);
                    RecipeCarpentry recipe = RecipeCarpentry.create(recipeCompound);
                    recipe.readNBT(recipeCompound);
                    RecipeController.syncRecipes.put(recipe.id, recipe);
                }
                RecipeController.Instance.carpentryRecipes = RecipeController.syncRecipes;
                RecipeController.syncRecipes = new HashMap();
                break;
            }
            case ANVIL_RECIPES: {
                NBTTagList list = fullCompound.func_150295_c("recipes", 10);
                if (list == null) {
                    return;
                }
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    RecipeAnvil recipe = new RecipeAnvil();
                    recipe.readNBT(list.func_150305_b(i));
                    RecipeController.syncAnvilRecipes.put(recipe.id, recipe);
                }
                RecipeController.Instance.anvilRecipes = RecipeController.syncAnvilRecipes;
                RecipeController.syncAnvilRecipes = new HashMap();
                break;
            }
            case CUSTOM_EFFECTS: {
                NBTTagList list = fullCompound.func_150295_c("Data", 10);
                CustomEffectController ce = CustomEffectController.getInstance();
                ce.customEffectsSync.clear();
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    CustomEffect effect = new CustomEffect();
                    effect.readFromNBT(list.func_150305_b(i));
                    ClientCacheHandler.getImageData(effect.icon);
                    ce.customEffectsSync.put(effect.id, effect);
                }
                ce.getCustomEffects().clear();
                ce.getCustomEffects().putAll(ce.customEffectsSync);
                ce.customEffectsSync.clear();
                break;
            }
            case CUSTOM_ABILITY: {
                NBTTagList list = fullCompound.func_150295_c("Data", 10);
                LinkedHashMap<String, Ability> sync = new LinkedHashMap<String, Ability>();
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound nbt = list.func_150305_b(i);
                    String name = nbt.func_74779_i("CustomAbilityId");
                    Ability ability = AbilityController.Instance.fromNBT(nbt);
                    if (ability == null) continue;
                    ability.setName(name);
                    sync.put(name, ability);
                }
                AbilityController.Instance.setCustomAbilities(sync);
                break;
            }
            case CHAINED_ABILITY: {
                NBTTagList list = fullCompound.func_150295_c("Data", 10);
                LinkedHashMap<String, ChainedAbility> sync = new LinkedHashMap<String, ChainedAbility>();
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    NBTTagCompound nbt = list.func_150305_b(i);
                    String id = nbt.func_74779_i("ChainedAbilityId");
                    ChainedAbility chain = new ChainedAbility();
                    chain.readNBT(nbt);
                    chain.setName(id);
                    sync.put(id, chain);
                }
                AbilityController.Instance.setChainedAbilities(sync);
                break;
            }
        }
        ClientCacheHandler.updateClientRevision(enumSyncType, revision);
    }

    @SideOnly(value=Side.CLIENT)
    public static void clientUpdate(EnumSyncType enumSyncType, int category_id, int revision, NBTTagCompound compound) {
        switch (enumSyncType) {
            case FACTION: {
                Faction faction = new Faction();
                faction.readNBT(compound);
                FactionController.getInstance().factions.put(faction.id, faction);
                break;
            }
            case DIALOG: {
                DialogCategory category = DialogController.Instance.categories.get(category_id);
                Dialog dialog = new Dialog();
                dialog.category = category;
                dialog.readNBT(compound);
                DialogController.Instance.dialogs.put(dialog.id, dialog);
                category.dialogs.put(dialog.id, dialog);
                break;
            }
            case QUEST: {
                QuestCategory category = QuestController.Instance.categories.get(category_id);
                Quest quest = new Quest();
                quest.category = category;
                quest.readNBT(compound);
                QuestController.Instance.quests.put(quest.id, quest);
                category.quests.put(quest.id, quest);
                break;
            }
            case QUEST_CATEGORY: {
                QuestCategory category = new QuestCategory();
                category.readSmallNBT(compound.func_74775_l("CatNBT"));
                NBTTagList list = compound.func_150295_c("Data", 10);
                if (QuestController.Instance.categoriesSync.containsKey(category.id)) {
                    category = QuestController.Instance.categoriesSync.get(category.id);
                    category.readSmallNBT(compound.func_74775_l("CatNBT"));
                }
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    Quest quest = new Quest();
                    quest.readNBT(list.func_150305_b(i));
                    quest.category = category;
                    category.quests.put(quest.id, quest);
                }
                QuestController.Instance.categories.put(category.id, category);
                break;
            }
            case DIALOG_CATEGORY: {
                DialogCategory category = new DialogCategory();
                category.readSmallNBT(compound.func_74775_l("CatNBT"));
                NBTTagList list = compound.func_150295_c("Data", 10);
                if (DialogController.Instance.categoriesSync.containsKey(category.id)) {
                    category = DialogController.Instance.categoriesSync.get(category.id);
                    category.readSmallNBT(compound.func_74775_l("CatNBT"));
                }
                for (int i = 0; i < list.func_74745_c(); ++i) {
                    Dialog dialog = new Dialog();
                    dialog.readNBT(list.func_150305_b(i));
                    dialog.category = category;
                    category.dialogs.put(dialog.id, dialog);
                }
                DialogController.Instance.categories.put(category.id, category);
                break;
            }
            case MAGIC_CYCLE: {
                MagicCycle cycle = new MagicCycle();
                cycle.readNBT(compound);
                MagicController.getInstance().cycles.put(cycle.id, cycle);
                break;
            }
            case MAGIC: {
                Magic magic = new Magic();
                magic.readNBT(compound);
                MagicController.getInstance().magics.put(magic.id, magic);
                break;
            }
            case PLAYERDATA: {
                ClientCacheHandler.playerData.setSyncNBT(compound);
                break;
            }
            case CUSTOM_EFFECTS: {
                CustomEffect effect = new CustomEffect();
                effect.readFromNBT(compound);
                ClientCacheHandler.getImageData(effect.icon);
                CustomEffectController.Instance.getCustomEffects().put(effect.id, effect);
                break;
            }
        }
        ClientCacheHandler.updateClientRevision(enumSyncType, revision);
    }

    public static void syncUpdate(EnumSyncType type, int cat, NBTTagCompound compound) {
        Map<EnumSyncType, Integer> revisions = SyncController.invalidateCaches(type);
        int revision = revisions.getOrDefault((Object)type, SyncController.getCurrentRevision(type));
        PacketHandler.Instance.sendToAll(new SyncPacket(type, EnumSyncAction.UPDATE, cat, revision, compound));
        SyncController.updateAllPlayerRevisions(revisions);
    }

    public static NBTTagCompound updateQuestCat(QuestCategory questCategory) {
        NBTTagCompound questCompound = new NBTTagCompound();
        NBTTagList questList = new NBTTagList();
        for (int questID : questCategory.quests.keySet()) {
            Quest quest = questCategory.quests.get(questID);
            questList.func_74742_a((NBTBase)quest.writeToNBT(new NBTTagCompound()));
        }
        questCompound.func_74782_a("Data", (NBTBase)questList);
        questCompound.func_74782_a("CatNBT", (NBTBase)questCategory.writeSmallNBT(new NBTTagCompound()));
        return questCompound;
    }

    public static NBTTagCompound updateDialogCat(DialogCategory dialogCategory) {
        NBTTagCompound dialogCompound = new NBTTagCompound();
        NBTTagList dialogList = new NBTTagList();
        for (int questID : dialogCategory.dialogs.keySet()) {
            Dialog dialog = dialogCategory.dialogs.get(questID);
            dialogList.func_74742_a((NBTBase)dialog.writeToNBT(new NBTTagCompound()));
        }
        dialogCompound.func_74782_a("Data", (NBTBase)dialogList);
        dialogCompound.func_74782_a("CatNBT", (NBTBase)dialogCategory.writeSmallNBT(new NBTTagCompound()));
        return dialogCompound;
    }

    @SideOnly(value=Side.CLIENT)
    public static void clientSyncRemove(EnumSyncType enumSyncType, int id, int revision) {
        switch (enumSyncType) {
            case FACTION: {
                FactionController.getInstance().factions.remove(id);
                break;
            }
            case DIALOG: {
                Dialog dialog = DialogController.Instance.dialogs.remove(id);
                if (dialog == null) break;
                dialog.category.dialogs.remove(id);
                break;
            }
            case DIALOG_CATEGORY: {
                DialogCategory dialogCategory = DialogController.Instance.categories.remove(id);
                if (dialogCategory == null) break;
                DialogController.Instance.dialogs.keySet().removeAll(dialogCategory.dialogs.keySet());
                break;
            }
            case QUEST: {
                Quest quest = QuestController.Instance.quests.remove(id);
                if (quest == null) break;
                quest.category.quests.remove(id);
                break;
            }
            case QUEST_CATEGORY: {
                QuestCategory questCategory = QuestController.Instance.categories.remove(id);
                if (questCategory == null) break;
                QuestController.Instance.quests.keySet().removeAll(questCategory.quests.keySet());
                break;
            }
            case MAGIC: {
                for (MagicCycle cycle : MagicController.getInstance().cycles.values()) {
                    cycle.associations.remove(id);
                }
                MagicController.getInstance().cycles.remove(id);
                break;
            }
            case MAGIC_CYCLE: {
                MagicController.getInstance().cycles.remove(id);
                break;
            }
            case CUSTOM_EFFECTS: {
                CustomEffectController.Instance.getCustomEffects().remove(id);
            }
        }
        ClientCacheHandler.updateClientRevision(enumSyncType, revision);
    }

    public static void syncEffects(EntityPlayerMP playerMP) {
        ConcurrentHashMap<EffectKey, PlayerEffect> playerEffects = CustomEffectController.getInstance().getPlayerEffects((EntityPlayer)playerMP);
        PlayerData playerData = PlayerData.get((EntityPlayer)playerMP);
        playerData.effectData.setEffects(playerEffects);
        NBTTagCompound compound = playerData.getPlayerEffects();
        PacketHandler.Instance.sendToPlayer(new SyncEffectPacket(compound), playerMP);
    }

    public static void syncAbilities(EntityPlayerMP playerMP) {
        PlayerData playerData = PlayerData.get((EntityPlayer)playerMP);
        if (playerData == null) {
            return;
        }
        PlayerAbilitySyncPacket.sendToPlayer(playerMP);
        AbilityHotbarSyncPacket.sendToPlayer(playerMP);
        AbilityCooldownSyncPacket.sendToPlayer(playerMP);
    }

    public static void syncAbilityCooldowns(EntityPlayerMP playerMP) {
        PlayerData playerData = PlayerData.get((EntityPlayer)playerMP);
        if (playerData == null) {
            return;
        }
        AbilityCooldownSyncPacket.sendToPlayer(playerMP);
    }

    @SideOnly(value=Side.CLIENT)
    public static void clientSyncEffects(NBTTagCompound compound) {
        PlayerData playerData = PlayerData.get((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        if (playerData != null) {
            playerData.setPlayerEffects(compound);
        }
    }

    private static void registerCache(EnumSyncType type, Supplier<NBTTagCompound> supplier) {
        cacheEntries.put(type, new SyncCacheEntry(type, supplier));
    }

    private static CachedSyncPayload rebuildNow(EnumSyncType type) {
        SyncCacheEntry entry = cacheEntries.get((Object)type);
        if (entry == null) {
            return null;
        }
        entry.invalidate();
        return entry.getPayload(type);
    }

    public static Map<EnumSyncType, Integer> invalidateCaches(EnumSyncType type) {
        EnumSet<EnumSyncType> targets = SyncController.getInvalidationTargets(type);
        EnumMap<EnumSyncType, Integer> revisions = new EnumMap<EnumSyncType, Integer>(EnumSyncType.class);
        for (EnumSyncType target : targets) {
            SyncCacheEntry entry = cacheEntries.get((Object)target);
            if (entry == null) continue;
            int newRevision = entry.invalidate();
            revisions.put(target, newRevision);
        }
        return revisions;
    }

    private static EnumSet<EnumSyncType> getInvalidationTargets(EnumSyncType type) {
        switch (type) {
            case DIALOG_CATEGORY: 
            case DIALOG: {
                return EnumSet.of(EnumSyncType.DIALOG_CATEGORY);
            }
            case QUEST_CATEGORY: 
            case QUEST: {
                return EnumSet.of(EnumSyncType.QUEST_CATEGORY);
            }
            case FACTION: {
                return EnumSet.of(EnumSyncType.FACTION);
            }
            case MAGIC: {
                return EnumSet.of(EnumSyncType.MAGIC);
            }
            case MAGIC_CYCLE: {
                return EnumSet.of(EnumSyncType.MAGIC_CYCLE);
            }
            case WORKBENCH_RECIPES: {
                return EnumSet.of(EnumSyncType.WORKBENCH_RECIPES);
            }
            case CARPENTRY_RECIPES: {
                return EnumSet.of(EnumSyncType.CARPENTRY_RECIPES);
            }
            case ANVIL_RECIPES: {
                return EnumSet.of(EnumSyncType.ANVIL_RECIPES);
            }
            case CUSTOM_EFFECTS: {
                return EnumSet.of(EnumSyncType.CUSTOM_EFFECTS);
            }
            case CUSTOM_ABILITY: {
                return EnumSet.of(EnumSyncType.CUSTOM_ABILITY);
            }
            case CHAINED_ABILITY: {
                return EnumSet.of(EnumSyncType.CHAINED_ABILITY);
            }
        }
        return EnumSet.noneOf(EnumSyncType.class);
    }

    private static void updateAllPlayerRevisions(EnumSyncType type, int revision) {
        if (revision < 0) {
            return;
        }
        for (PlayerSyncState state : playerSyncState.values()) {
            state.updateRevision(type, revision);
        }
    }

    private static void updateAllPlayerRevisions(Map<EnumSyncType, Integer> revisions) {
        if (revisions.isEmpty()) {
            return;
        }
        for (PlayerSyncState state : playerSyncState.values()) {
            for (Map.Entry<EnumSyncType, Integer> entry : revisions.entrySet()) {
                state.updateRevision(entry.getKey(), entry.getValue());
            }
        }
    }

    private static final class PlayerSyncState {
        private final EnumMap<EnumSyncType, Integer> revisions = new EnumMap(EnumSyncType.class);

        private PlayerSyncState(UUID ignored) {
        }

        private synchronized int getRevision(EnumSyncType type) {
            Integer value = this.revisions.get((Object)type);
            return value == null ? -1 : value;
        }

        private synchronized void updateRevision(EnumSyncType type, int revision) {
            this.revisions.put(type, revision);
        }

        private synchronized void applyHandshake(Map<EnumSyncType, Integer> incoming) {
            if (incoming == null || incoming.isEmpty()) {
                return;
            }
            this.revisions.putAll(incoming);
        }

        private synchronized void reset() {
            this.revisions.clear();
        }
    }

    private static final class SyncCacheEntry {
        private final Supplier<NBTTagCompound> supplier;
        private volatile CachedSyncPayload payload;
        private int revision = 0;
        private boolean dirty = true;

        private SyncCacheEntry(EnumSyncType type, Supplier<NBTTagCompound> supplier) {
            this.supplier = supplier;
        }

        private synchronized CachedSyncPayload getPayload(EnumSyncType requestedType) {
            byte[] bytes;
            if (!this.dirty && this.payload != null) {
                return this.payload;
            }
            int payloadRevision = this.revision;
            NBTTagCompound data = this.supplier.get();
            ByteBuf buffer = Unpooled.buffer();
            try {
                buffer.writeInt(requestedType.ordinal());
                buffer.writeInt(EnumSyncAction.RELOAD.ordinal());
                buffer.writeInt(-1);
                buffer.writeInt(payloadRevision);
                ByteBufUtils.writeBigNBT(buffer, data);
                bytes = new byte[buffer.readableBytes()];
                buffer.readBytes(bytes);
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to serialize sync payload for " + (Object)((Object)requestedType), e);
            }
            finally {
                buffer.release();
            }
            byte[][] chunks = SyncCacheEntry.splitIntoChunks(bytes);
            this.payload = new CachedSyncPayload(payloadRevision, bytes, chunks);
            this.dirty = false;
            return this.payload;
        }

        private synchronized int invalidate() {
            this.dirty = true;
            this.payload = null;
            ++this.revision;
            return this.revision;
        }

        private synchronized int getRevisionValue() {
            return this.revision;
        }

        private synchronized void reset() {
            this.dirty = true;
            this.payload = null;
            this.revision = 0;
        }

        private static byte[][] splitIntoChunks(byte[] payload) {
            int totalSize = payload.length;
            int chunkCount = (totalSize + 10000 - 1) / 10000;
            if (chunkCount <= 0) {
                chunkCount = 1;
            }
            byte[][] chunks = new byte[chunkCount][];
            for (int i = 0; i < chunkCount; ++i) {
                int offset = i * 10000;
                int length = Math.min(10000, totalSize - offset);
                byte[] chunk = new byte[length];
                System.arraycopy(payload, offset, chunk, 0, length);
                chunks[i] = chunk;
            }
            return chunks;
        }
    }

    public static final class CachedSyncPayload {
        private final int revision;
        private final byte[] payload;
        private final byte[][] chunks;

        private CachedSyncPayload(int revision, byte[] payload, byte[][] chunks) {
            this.revision = revision;
            this.payload = payload;
            this.chunks = chunks;
        }

        public int getRevision() {
            return this.revision;
        }

        public byte[] getPayload() {
            return this.payload;
        }

        public byte[][] getChunks() {
            return this.chunks;
        }
    }
}

