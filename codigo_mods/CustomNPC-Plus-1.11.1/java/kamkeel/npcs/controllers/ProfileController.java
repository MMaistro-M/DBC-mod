/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.data.profile.CNPCData;
import kamkeel.npcs.controllers.data.profile.EnumProfileOperation;
import kamkeel.npcs.controllers.data.profile.IProfileData;
import kamkeel.npcs.controllers.data.profile.Profile;
import kamkeel.npcs.controllers.data.profile.ProfileInfoEntry;
import kamkeel.npcs.controllers.data.profile.ProfileOperation;
import kamkeel.npcs.controllers.data.profile.Slot;
import kamkeel.npcs.network.packets.data.ProfileSharedQuestPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.EventHooks;
import noppes.npcs.LogWriter;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.IPlayerData;
import noppes.npcs.api.handler.IProfileHandler;
import noppes.npcs.api.handler.data.IProfile;
import noppes.npcs.api.handler.data.ISlot;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.util.CustomNPCsThreader;
import noppes.npcs.util.NBTJsonUtil;

public class ProfileController
implements IProfileHandler {
    private static final String MSG_PLAYER_NOT_FOUND = "Player not found.";
    private static final String MSG_PROFILE_LOCKED_CLONE = "Profile is locked; cannot clone slot.";
    private static final String MSG_SOURCE_SLOT_NOT_EXIST = "Source slot does not exist.";
    private static final String MSG_CANNOT_CLONE_CURRENT = "Cannot clone to the current active slot.";
    private static final String MSG_INVALID_DEST_SLOT = "Invalid destination slot id.";
    private static final String MSG_CLONE_SUCCESS = "Slot cloned successfully.";
    private static final String MSG_PROFILE_LOCKED_REMOVE = "Profile is locked; cannot remove slot.";
    private static final String MSG_CANNOT_REMOVE_ACTIVE = "Cannot remove the currently active slot.";
    private static final String MSG_SLOT_NOT_EXIST = "Slot does not exist.";
    private static final String MSG_REMOVE_SUCCESS = "Slot removed successfully.";
    private static final String MSG_PROFILE_LOCKED_CREATE = "Profile is locked; cannot create slot.";
    private static final String MSG_MAX_SLOTS_REACHED = "Maximum allowed slots reached.";
    private static final String MSG_NEW_SLOT_CREATED = "New slot created successfully.";
    private static final String MSG_PROFILE_LOCKED_CHANGE = "Profile is locked; cannot change slot.";
    private static final String MSG_SLOT_ALREADY_ACTIVE = "Slot is already active.";
    private static final String MSG_REGION_NOT_ALLOWED = "Profile switching not allowed from your current location.";
    private static final String MSG_CHANGE_SUCCESS = "Slot changed successfully.";
    private static final String MSG_CANCELLED = "Operation cancelled.";
    public static Map<String, IProfileData> profileTypes = new HashMap<String, IProfileData>();
    public static Map<UUID, Profile> activeProfiles = new HashMap<UUID, Profile>();
    public static String profile_directory = "profiles";
    public static ProfileController Instance;

    public ProfileController() {
        Instance = this;
        profileTypes = new HashMap<String, IProfileData>();
        activeProfiles = new HashMap<UUID, Profile>();
    }

    public static boolean registerProfileType(IProfileData type) {
        if (profileTypes.containsKey(type.getTagName())) {
            return false;
        }
        profileTypes.put(type.getTagName(), type);
        return true;
    }

    public static File getProfileDir() {
        try {
            File file = new File(CustomNpcs.getWorldSaveDirectory(), profile_directory);
            if (!file.exists()) {
                file.mkdir();
            }
            return file;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getBackupDir() {
        File base = ProfileController.getProfileDir();
        File backup = new File(base, "backup");
        if (!backup.exists()) {
            backup.mkdir();
        }
        return backup;
    }

    public synchronized void login(EntityPlayer player) {
        if (!ConfigMain.ProfilesEnabled) {
            return;
        }
        if (player == null) {
            return;
        }
        if (activeProfiles.containsKey(player.func_110124_au())) {
            Profile profile = activeProfiles.get(player.func_110124_au());
            profile.player = player;
        } else {
            NBTTagCompound compound = this.load(player);
            Profile profile = new Profile(player, compound);
            if (profile.getSlots().isEmpty()) {
                Slot defaultSlot = new Slot(0, "Default Slot");
                defaultSlot.setLastLoaded(System.currentTimeMillis());
                profile.getSlots().put(0, defaultSlot);
                profile.currentSlotId = 0;
                this.saveSlotData(player);
            }
            if (!profile.getSlots().containsKey(profile.currentSlotId)) {
                profile.currentSlotId = 0;
            }
            profile.player = player;
            activeProfiles.put(player.func_110124_au(), profile);
            PlayerData pdata = PlayerData.get(player);
            if (profile.getSlots().containsKey(pdata.profileSlot)) {
                profile.currentSlotId = pdata.profileSlot;
            }
            this.saveSlotData(player);
            this.verifySlotQuests(profile.player);
        }
    }

    public synchronized NBTTagCompound load(EntityPlayer player) {
        File saveDir = ProfileController.getProfileDir();
        String filename = player.func_110124_au().toString() + ".dat";
        try {
            File file = new File(saveDir, filename);
            if (file.exists()) {
                return NBTJsonUtil.loadNBTData(file);
            }
        }
        catch (Exception e) {
            LogWriter.error("Error loading profile file: " + filename, e);
        }
        return new NBTTagCompound();
    }

    public synchronized NBTTagCompound load(UUID uuid) {
        File saveDir = ProfileController.getProfileDir();
        String filename = uuid.toString() + ".dat";
        try {
            File file = new File(saveDir, filename);
            if (file.exists()) {
                return NBTJsonUtil.loadNBTData(file);
            }
        }
        catch (Exception e) {
            LogWriter.error("Error loading profile file: " + filename, e);
        }
        return new NBTTagCompound();
    }

    public synchronized void saveOffline(Profile profile, UUID uuid) {
        profile.setLocked(true);
        NBTTagCompound compound = profile.writeToNBT();
        String filename = uuid.toString() + ".dat";
        CustomNPCsThreader.customNPCThread.execute(() -> {
            try {
                File saveDir = ProfileController.getProfileDir();
                File fileNew = new File(saveDir, filename + "_new");
                File fileOld = new File(saveDir, filename);
                CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(fileNew));
                if (fileOld.exists()) {
                    fileOld.delete();
                }
                fileNew.renameTo(fileOld);
                this.backupProfile(uuid, compound);
            }
            catch (Exception e) {
                LogWriter.except(e);
            }
            finally {
                profile.setLocked(false);
            }
        });
    }

    public synchronized void save(EntityPlayer player, Profile profile) {
        profile.setLocked(true);
        NBTTagCompound compound = profile.writeToNBT();
        String filename = player.func_110124_au() + ".dat";
        CustomNPCsThreader.customNPCThread.execute(() -> {
            try {
                File saveDir = ProfileController.getProfileDir();
                File fileNew = new File(saveDir, filename + "_new");
                File fileOld = new File(saveDir, filename);
                CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(fileNew));
                if (fileOld.exists()) {
                    fileOld.delete();
                }
                fileNew.renameTo(fileOld);
                if (ConfigMain.AllowProfileBackups) {
                    this.backupProfile(player.func_110124_au(), compound);
                }
            }
            catch (Exception e) {
                LogWriter.except(e);
            }
            finally {
                profile.setLocked(false);
            }
        });
    }

    private void backupProfile(UUID uuid, NBTTagCompound compound) {
        try {
            File backupDir = new File(ProfileController.getBackupDir(), uuid.toString());
            if (!backupDir.exists()) {
                backupDir.mkdirs();
            }
            String dateStr = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File backupFile = new File(backupDir, dateStr + ".dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(backupFile));
            File[] backups = backupDir.listFiles((dir, name) -> name.endsWith(".dat"));
            if (backups != null && backups.length > ConfigMain.ProfileBackupAmount) {
                Arrays.sort(backups, Comparator.comparingLong(File::lastModified));
                for (int i = 0; i < backups.length - ConfigMain.ProfileBackupAmount; ++i) {
                    backups[i].delete();
                }
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean rollbackProfile(String username, File backupFile) {
        UUID uuid = this.getUUIDFromUsername(username);
        if (uuid == null) {
            return false;
        }
        try (FileInputStream fis = new FileInputStream(backupFile);){
            NBTTagCompound compound = CompressedStreamTools.func_74796_a((InputStream)fis);
            File saveDir = ProfileController.getProfileDir();
            File mainFile = new File(saveDir, uuid.toString() + ".dat");
            File fileNew = new File(saveDir, uuid.toString() + "_new");
            CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(fileNew));
            if (mainFile.exists()) {
                mainFile.delete();
            }
            fileNew.renameTo(mainFile);
            EntityPlayer player = NoppesUtilServer.getPlayerByName(username);
            if (player == null) return false;
            Profile newProfile = new Profile(player, compound);
            activeProfiles.put(uuid, newProfile);
            this.loadSlotData(player);
            boolean bl = true;
            return bl;
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        return false;
    }

    public synchronized void logout(EntityPlayer player) {
        if (player != null && activeProfiles.containsKey(player.func_110124_au())) {
            this.saveSlotData(player);
            Profile profile = activeProfiles.get(player.func_110124_au());
            this.save(player, profile);
        }
    }

    public Profile getProfile(EntityPlayer player) {
        if (player == null) {
            return null;
        }
        if (!activeProfiles.containsKey(player.func_110124_au())) {
            this.login(player);
        }
        return activeProfiles.get(player.func_110124_au());
    }

    public Profile getProfile(UUID uuid) {
        if (activeProfiles.containsKey(uuid)) {
            return activeProfiles.get(uuid);
        }
        NBTTagCompound compound = this.load(uuid);
        return new Profile(null, compound);
    }

    public Profile getProfile(String username) {
        EntityPlayer player = NoppesUtilServer.getPlayerByName(username);
        if (player != null) {
            return this.getProfile(player);
        }
        UUID uuid = this.getUUIDFromUsername(username);
        if (uuid == null) {
            return null;
        }
        return this.getProfile(uuid);
    }

    public UUID getUUIDFromUsername(String username) {
        try {
            String line;
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + username);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder temp = new StringBuilder();
            while ((line = in.readLine()) != null) {
                temp.append(line);
            }
            in.close();
            con.disconnect();
            String response = temp.toString();
            if (response.contains("\"id\"")) {
                int idIndex = response.indexOf("\"id\"");
                int colonIndex = response.indexOf(":", idIndex);
                int quoteStart = response.indexOf("\"", colonIndex);
                int quoteEnd = response.indexOf("\"", quoteStart + 1);
                String idString = response.substring(quoteStart + 1, quoteEnd);
                idString = idString.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5");
                return UUID.fromString(idString);
            }
        }
        catch (IOException e) {
            LogWriter.error("Error retrieving UUID for " + username, e);
        }
        return null;
    }

    private int getNextAvailableTempSlot(Profile profile) {
        int id = -1;
        while (profile.getSlots().containsKey(id)) {
            --id;
        }
        return id;
    }

    private ProfileOperation cloneSlotInternal(Profile profile, int sourceSlotId, int destinationSlotId, boolean temporary) {
        if (profile.isLocked()) {
            return ProfileOperation.locked(MSG_PROFILE_LOCKED_CLONE);
        }
        if (profile.player != null) {
            ArrayList<IProfileData> dataList = new ArrayList<IProfileData>(profileTypes.values());
            dataList.sort(Comparator.comparingInt(IProfileData::getSwitchPriority));
            for (IProfileData iProfileData : dataList) {
                if (iProfileData.verifySwitch(profile.player).getResult() == EnumProfileOperation.SUCCESS) continue;
                return iProfileData.verifySwitch(profile.player);
            }
        }
        if (!profile.getSlots().containsKey(sourceSlotId)) {
            return ProfileOperation.error(MSG_SOURCE_SLOT_NOT_EXIST);
        }
        if (destinationSlotId == profile.getCurrentSlotId()) {
            return ProfileOperation.error(MSG_CANNOT_CLONE_CURRENT);
        }
        if (temporary) {
            destinationSlotId = this.getNextAvailableTempSlot(profile);
        } else if (destinationSlotId <= 0) {
            return ProfileOperation.error(MSG_INVALID_DEST_SLOT);
        }
        ISlot sourceSlot = profile.getSlots().get(sourceSlotId);
        HashMap<String, NBTTagCompound> newComponents = new HashMap<String, NBTTagCompound>();
        for (String key : sourceSlot.getComponents().keySet()) {
            newComponents.put(key, (NBTTagCompound)sourceSlot.getComponentData(key).func_74737_b());
        }
        Slot slot = new Slot(destinationSlotId, "Cloned Slot " + destinationSlotId, System.currentTimeMillis(), temporary, newComponents);
        profile.getSlots().put(destinationSlotId, slot);
        return ProfileOperation.success(MSG_CLONE_SUCCESS);
    }

    private ProfileOperation removeSlotInternal(Profile profile, int slotId) {
        IPlayer scriptPlayer;
        PlayerDataScript handler;
        if (profile.isLocked()) {
            return ProfileOperation.locked(MSG_PROFILE_LOCKED_REMOVE);
        }
        if (slotId == profile.getCurrentSlotId()) {
            return ProfileOperation.error(MSG_CANNOT_REMOVE_ACTIVE);
        }
        if (!profile.getSlots().containsKey(slotId)) {
            return ProfileOperation.error(MSG_SLOT_NOT_EXIST);
        }
        if (profile.player != null && EventHooks.onProfileRemove(handler = ScriptController.Instance.getPlayerScripts(profile.player), scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)profile.player), profile, slotId, false)) {
            return ProfileOperation.error(MSG_CANCELLED);
        }
        profile.getSlots().remove(slotId);
        if (profile.player != null) {
            handler = ScriptController.Instance.getPlayerScripts(profile.player);
            scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)profile.player);
            EventHooks.onProfileRemove(handler, scriptPlayer, profile, slotId, true);
        }
        return ProfileOperation.success(MSG_REMOVE_SUCCESS);
    }

    public ProfileOperation createSlotInternal(Profile profile) {
        IPlayer scriptPlayer;
        PlayerDataScript handler;
        if (profile.isLocked()) {
            return ProfileOperation.locked(MSG_PROFILE_LOCKED_CREATE);
        }
        int newSlotId = 0;
        while (profile.getSlots().containsKey(newSlotId)) {
            ++newSlotId;
        }
        if (!this.allowSlotPermission(profile.player)) {
            return ProfileOperation.error(MSG_MAX_SLOTS_REACHED);
        }
        if (profile.player != null) {
            ArrayList<IProfileData> dataList = new ArrayList<IProfileData>(profileTypes.values());
            dataList.sort(Comparator.comparingInt(IProfileData::getSwitchPriority));
            for (IProfileData pd : dataList) {
                if (pd.verifySwitch(profile.player).getResult() == EnumProfileOperation.SUCCESS) continue;
                return pd.verifySwitch(profile.player);
            }
        }
        if (profile.player != null && EventHooks.onProfileCreate(handler = ScriptController.Instance.getPlayerScripts(profile.player), scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)profile.player), profile, newSlotId, false)) {
            return ProfileOperation.error(MSG_CANCELLED);
        }
        Slot newSlot = new Slot(newSlotId, "Slot " + newSlotId);
        newSlot.setLastLoaded(System.currentTimeMillis());
        profile.getSlots().put(newSlotId, newSlot);
        if (profile.player != null) {
            PlayerDataScript handler2 = ScriptController.Instance.getPlayerScripts(profile.player);
            this.verifySlotQuests(profile.player);
            this.save(profile.player, profile);
            if (profile.player instanceof EntityPlayerMP) {
                ProfileSharedQuestPacket.sendToPlayer((EntityPlayerMP)profile.player);
            }
            IPlayer scriptPlayer2 = (IPlayer)NpcAPI.Instance().getIEntity((Entity)profile.player);
            EventHooks.onProfileCreate(handler2, scriptPlayer2, profile, newSlotId, true);
        }
        return ProfileOperation.success(MSG_NEW_SLOT_CREATED);
    }

    private ProfileOperation changeSlotInternal(Profile profile, int newSlotId) {
        IPlayer scriptPlayer;
        PlayerDataScript handler;
        int prevSlot;
        if (profile.isLocked()) {
            return ProfileOperation.locked(MSG_PROFILE_LOCKED_CHANGE);
        }
        if (profile.getCurrentSlotId() == newSlotId) {
            return ProfileOperation.error(MSG_SLOT_ALREADY_ACTIVE);
        }
        if (!profile.getSlots().containsKey(newSlotId)) {
            return ProfileOperation.error(MSG_SLOT_NOT_EXIST);
        }
        if (profile.player != null) {
            if (ConfigMain.RegionProfileSwitching) {
                boolean allowed = CustomNpcsPermissions.hasCustomPermission(profile.player, CustomNpcsPermissions.PROFILE_REGION_BYPASS.name);
                if (!allowed) {
                    int playerDim = profile.player.field_71093_bK;
                    int playerX = (int)profile.player.field_70165_t;
                    int playerY = (int)profile.player.field_70163_u;
                    int playerZ = (int)profile.player.field_70161_v;
                    for (List<Integer> region : ConfigMain.RestrictedProfileRegions) {
                        if (region.size() != 7) continue;
                        int dim = region.get(0);
                        int x1 = Math.min(region.get(1), region.get(4));
                        int y1 = Math.min(region.get(2), region.get(5));
                        int z1 = Math.min(region.get(3), region.get(6));
                        int x2 = Math.max(region.get(1), region.get(4));
                        int y2 = Math.max(region.get(2), region.get(5));
                        int z2 = Math.max(region.get(3), region.get(6));
                        if (playerDim != dim || playerX < x1 || playerX > x2 || playerY < y1 || playerY > y2 || playerZ < z1 || playerZ > z2) continue;
                        allowed = true;
                        break;
                    }
                }
                if (!allowed) {
                    return ProfileOperation.error(MSG_REGION_NOT_ALLOWED);
                }
            }
            ArrayList<IProfileData> dataList = new ArrayList<IProfileData>(profileTypes.values());
            dataList.sort(Comparator.comparingInt(IProfileData::getSwitchPriority));
            for (IProfileData pd : dataList) {
                if (pd.verifySwitch(profile.player).getResult() == EnumProfileOperation.SUCCESS) continue;
                return pd.verifySwitch(profile.player);
            }
            prevSlot = profile.getCurrentSlotId();
            handler = ScriptController.Instance.getPlayerScripts(profile.player);
            if (EventHooks.onProfileChange(handler, scriptPlayer = (IPlayer)NpcAPI.Instance().getIEntity((Entity)profile.player), profile, newSlotId, prevSlot, false)) {
                return ProfileOperation.error(MSG_CANCELLED);
            }
            this.saveSlotData(profile.player, profile, prevSlot);
            this.loadSlotData(profile.player, profile, newSlotId);
            this.syncSharedQuestsToPlayer(profile.player);
            if (profile.player instanceof EntityPlayerMP) {
                ProfileSharedQuestPacket.sendToPlayer((EntityPlayerMP)profile.player);
            }
        } else {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        EventHooks.onProfileChange(handler, scriptPlayer, profile, newSlotId, prevSlot, true);
        return ProfileOperation.success(MSG_CHANGE_SUCCESS);
    }

    public ProfileOperation cloneSlot(EntityPlayer player, int sourceSlotId, int destinationSlotId, boolean temporary) {
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.cloneSlotInternal(profile, sourceSlotId, destinationSlotId, temporary);
        if (result.getResult() == EnumProfileOperation.SUCCESS && player != null) {
            this.save(player, profile);
        }
        return result;
    }

    public ProfileOperation cloneSlot(UUID uuid, int sourceSlotId, int destinationSlotId, boolean temporary) {
        Profile profile = this.getProfile(uuid);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.cloneSlotInternal(profile, sourceSlotId, destinationSlotId, temporary);
        if (result.getResult() == EnumProfileOperation.SUCCESS) {
            if (profile.player != null) {
                this.save(profile.player, profile);
            } else {
                this.saveOffline(profile, uuid);
            }
        }
        return result;
    }

    public ProfileOperation cloneSlot(String username, int sourceSlotId, int destinationSlotId, boolean temporary) {
        Profile profile = this.getProfile(username);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.cloneSlotInternal(profile, sourceSlotId, destinationSlotId, temporary);
        if (result.getResult() == EnumProfileOperation.SUCCESS) {
            if (profile.player != null) {
                this.save(profile.player, profile);
            } else {
                UUID uuid = this.getUUIDFromUsername(username);
                if (uuid != null) {
                    this.saveOffline(profile, uuid);
                }
            }
        }
        return result;
    }

    public ProfileOperation removeSlot(EntityPlayer player, int slotId) {
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.removeSlotInternal(profile, slotId);
        if (result.getResult() == EnumProfileOperation.SUCCESS && player != null) {
            this.save(player, profile);
        }
        return result;
    }

    public ProfileOperation removeSlot(String username, int slotId) {
        Profile profile = this.getProfile(username);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.removeSlotInternal(profile, slotId);
        if (result.getResult() == EnumProfileOperation.SUCCESS) {
            if (profile.player != null) {
                this.save(profile.player, profile);
            } else {
                UUID uuid = this.getUUIDFromUsername(username);
                if (uuid != null) {
                    this.saveOffline(profile, uuid);
                }
            }
        }
        return result;
    }

    public ProfileOperation changeSlot(EntityPlayer player, int newSlotId) {
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.changeSlotInternal(profile, newSlotId);
        if (result.getResult() == EnumProfileOperation.SUCCESS && player != null) {
            this.save(player, profile);
        }
        return result;
    }

    public ProfileOperation changeSlot(String username, int newSlotId) {
        Profile profile = this.getProfile(username);
        if (profile == null) {
            return ProfileOperation.error(MSG_PLAYER_NOT_FOUND);
        }
        ProfileOperation result = this.changeSlotInternal(profile, newSlotId);
        if (result.getResult() == EnumProfileOperation.SUCCESS) {
            if (profile.player != null) {
                this.save(profile.player, profile);
            } else {
                UUID uuid = this.getUUIDFromUsername(username);
                if (uuid != null) {
                    this.saveOffline(profile, uuid);
                }
            }
        }
        return result;
    }

    private void saveSlotData(EntityPlayer player, Profile profile, int slotId) {
        if (player == null || profile == null) {
            return;
        }
        if (profile.isLocked()) {
            return;
        }
        ISlot slot = profile.getSlots().get(slotId);
        if (slot == null) {
            slot = new Slot(slotId, "Slot " + slotId);
            profile.getSlots().put(slotId, slot);
        }
        for (IProfileData profileData : profileTypes.values()) {
            NBTTagCompound cloned = (NBTTagCompound)profileData.getCurrentNBT(player).func_74737_b();
            slot.setComponentData(profileData.getTagName(), cloned);
        }
        slot.setLastLoaded(System.currentTimeMillis());
    }

    public void saveSlotData(EntityPlayer player) {
        if (player == null || !activeProfiles.containsKey(player.func_110124_au())) {
            return;
        }
        Profile profile = activeProfiles.get(player.func_110124_au());
        this.saveSlotData(player, profile, profile.getCurrentSlotId());
    }

    private void loadSlotData(EntityPlayer player, Profile profile, int slotId) {
        if (player == null || profile == null) {
            return;
        }
        ISlot slot = profile.getSlots().get(slotId);
        if (slot == null) {
            return;
        }
        for (IProfileData profileData : profileTypes.values()) {
            NBTTagCompound data = slot.getComponents().containsKey(profileData.getTagName()) ? (NBTTagCompound)slot.getComponentData(profileData.getTagName()).func_74737_b() : new NBTTagCompound();
            profileData.setNBT(player, data);
        }
        for (IProfileData profileData : profileTypes.values()) {
            profileData.save(player);
        }
        profile.currentSlotId = slotId;
        PlayerData pdata = PlayerData.get(player);
        pdata.profileSlot = slotId;
        pdata.save();
        if (ConfigMain.AttributesEnabled) {
            AttributeController.getTracker(player).recalcAttributes(player);
        }
    }

    public void loadSlotData(EntityPlayer player) {
        if (player == null || !activeProfiles.containsKey(player.func_110124_au())) {
            return;
        }
        Profile profile = activeProfiles.get(player.func_110124_au());
        this.loadSlotData(player, profile, profile.getCurrentSlotId());
    }

    public List<ProfileInfoEntry> getProfileInfo(EntityPlayer player, int slotId) {
        ArrayList<ProfileInfoEntry> infoList = new ArrayList<ProfileInfoEntry>();
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return infoList;
        }
        ArrayList<IProfileData> dataList = new ArrayList<IProfileData>(profileTypes.values());
        dataList.sort(Comparator.comparingInt(IProfileData::getSwitchPriority));
        if (slotId == profile.getCurrentSlotId()) {
            for (IProfileData pd : dataList) {
                NBTTagCompound currentNBT = pd.getCurrentNBT(player);
                List<ProfileInfoEntry> subInfo = pd.getInfo(player, currentNBT);
                infoList.addAll(subInfo);
            }
        } else {
            if (!profile.getSlots().containsKey(slotId)) {
                return infoList;
            }
            ISlot slot = profile.getSlots().get(slotId);
            for (IProfileData pd : dataList) {
                if (!slot.getComponents().containsKey(pd.getTagName())) continue;
                NBTTagCompound sub = (NBTTagCompound)slot.getComponentData(pd.getTagName()).func_74737_b();
                List<ProfileInfoEntry> subInfo = pd.getInfo(player, sub);
                infoList.addAll(subInfo);
            }
        }
        return infoList;
    }

    public boolean allowSlotPermission(EntityPlayer player) {
        Profile profile = this.getProfile(player);
        int currentSlots = profile.getSlots().size();
        if (CustomNpcsPermissions.hasCustomPermission(player, CustomNpcsPermissions.PROFILE_MAX.name)) {
            return true;
        }
        int highestAllowed = 0;
        for (int i = 1; i <= 50; ++i) {
            String perm = "customnpcs.profile.max." + i;
            if (!CustomNpcsPermissions.hasCustomPermission(player, perm)) continue;
            highestAllowed = i;
        }
        if (highestAllowed == 0 || highestAllowed < ConfigMain.DefaultProfileSlots) {
            highestAllowed = ConfigMain.DefaultProfileSlots;
        }
        return currentSlots < highestAllowed;
    }

    @Override
    public IProfile getProfile(IPlayer player) {
        if (player == null || player.getMCEntity() == null) {
            return null;
        }
        return this.getProfile((EntityPlayer)player.getMCEntity());
    }

    @Override
    public boolean changeSlot(IPlayer player, int slotID) {
        if (player == null || player.getMCEntity() == null) {
            return false;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        ProfileOperation profileOperation = this.changeSlot(entityPlayer, slotID);
        return profileOperation.getResult() == EnumProfileOperation.SUCCESS;
    }

    @Override
    public boolean hasSlot(IPlayer player, int slotID) {
        if (player == null || player.getMCEntity() == null) {
            return false;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        Profile profile = this.getProfile(entityPlayer);
        if (profile == null) {
            return false;
        }
        return profile.getSlots().containsKey(slotID);
    }

    @Override
    public boolean removeSlot(IPlayer player, int slotID) {
        if (player == null || player.getMCEntity() == null) {
            return false;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        ProfileOperation profileOperation = this.removeSlot(entityPlayer, slotID);
        return profileOperation.getResult() == EnumProfileOperation.SUCCESS;
    }

    @Override
    public IPlayerData getSlotPlayerData(IPlayer player, int slotID) {
        if (player == null || player.getMCEntity() == null) {
            return null;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        Profile profile = this.getProfile(entityPlayer);
        if (profile == null) {
            return null;
        }
        return this.getSlotPlayerData(entityPlayer, slotID);
    }

    @Override
    public void saveSlotData(IPlayer player) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        EntityPlayer entityPlayer = (EntityPlayer)player.getMCEntity();
        this.saveSlotData(entityPlayer);
    }

    public IPlayerData getSlotPlayerData(EntityPlayer player, int slotID) {
        PlayerData playerData;
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return null;
        }
        if (profile.currentSlotId == slotID) {
            playerData = PlayerData.get(player);
        } else {
            Slot slot = (Slot)profile.getSlots().get(slotID);
            playerData = this.getSlotPlayerData(player, slot);
        }
        return playerData;
    }

    public PlayerData getSlotPlayerData(EntityPlayer player, Slot slot) {
        if (slot == null) {
            return null;
        }
        PlayerData playerData = new PlayerData();
        playerData.player = player;
        NBTTagCompound compound = slot.getComponentData(new CNPCData().getTagName());
        compound = compound == null ? new NBTTagCompound() : (NBTTagCompound)compound.func_74737_b();
        playerData.setNBT(compound);
        return playerData;
    }

    public void verifySlotQuests(EntityPlayer player) {
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return;
        }
        HashMap<Integer, Long> universalFinished = new HashMap<Integer, Long>();
        for (Integer questId : QuestController.Instance.sharedQuests.keySet()) {
            long maxTime = 0L;
            for (ISlot slot : profile.getSlots().values()) {
                IPlayerData data = this.getSlotPlayerData(player, slot.getId());
                if (data == null) continue;
                PlayerQuestData questData = (PlayerQuestData)data.getQuestData();
                Long t = questData.finishedQuests.get(questId);
                if (t == null || t <= maxTime) continue;
                maxTime = t;
            }
            if (maxTime <= 0L) continue;
            universalFinished.put(questId, maxTime);
        }
        profile.sharedQuestTimestamps = universalFinished;
        for (ISlot slot : profile.getSlots().values()) {
            IPlayerData data = this.getSlotPlayerData(player, slot.getId());
            if (data == null) continue;
            PlayerData playerData = (PlayerData)data;
            PlayerQuestData questData = (PlayerQuestData)data.getQuestData();
            questData.finishedQuests.putAll(universalFinished);
            slot.setComponentData(new CNPCData().getTagName(), playerData.getNBT());
        }
    }

    public void syncSharedQuestsToPlayer(EntityPlayer player) {
        Profile profile = this.getProfile(player);
        if (profile == null || profile.sharedQuestTimestamps.isEmpty()) {
            return;
        }
        PlayerQuestData questData = PlayerData.get((EntityPlayer)player).questData;
        for (Map.Entry<Integer, Long> entry : profile.sharedQuestTimestamps.entrySet()) {
            Long existing = questData.finishedQuests.get(entry.getKey());
            if (existing != null && entry.getValue() <= existing) continue;
            questData.finishedQuests.put(entry.getKey(), entry.getValue());
        }
    }

    public void shareQuestCompletion(EntityPlayer player, int questId, long completeTime) {
        Profile profile = this.getProfile(player);
        if (profile == null) {
            return;
        }
        Long existing = profile.sharedQuestTimestamps.get(questId);
        if (existing == null || completeTime > existing) {
            profile.sharedQuestTimestamps.put(questId, completeTime);
        }
        this.save(player, profile);
        if (player instanceof EntityPlayerMP) {
            ProfileSharedQuestPacket.sendToPlayer((EntityPlayerMP)player);
        }
    }
}

