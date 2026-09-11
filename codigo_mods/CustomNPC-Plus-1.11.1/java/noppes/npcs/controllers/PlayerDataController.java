/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerSelector
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.controllers.BankController;
import noppes.npcs.controllers.data.Bank;
import noppes.npcs.controllers.data.PlayerBankData;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.controllers.data.PlayerMail;
import noppes.npcs.util.CacheHashMap;
import noppes.npcs.util.CustomNPCsThreader;
import noppes.npcs.util.NBTJsonUtil;

public class PlayerDataController {
    public static PlayerDataController Instance;
    public HashMap<String, String> nameUUIDs;
    private final CacheHashMap<String, CacheHashMap.CachedObject<PlayerData>> playerDataCache = new CacheHashMap(3600000L);

    public PlayerDataController() {
        Instance = this;
        File dir = this.getSaveDir();
        LogWriter.info("Loading PlayerData...");
        if (!this.getPlayerDataMap()) {
            int length;
            LogWriter.info("Generating PlayerData map file...");
            File[] files = dir.listFiles();
            HashMap<String, String> map = new HashMap<String, String>();
            if (files != null && (length = files.length) != 0) {
                if (length > 100) {
                    LogWriter.info("Found " + length + " PlayerData files... This may take a few minutes");
                }
                int tenPercent = (int)((double)length * 0.1);
                int progress = 0;
                for (int i = 0; i < length; ++i) {
                    File file = files[i];
                    if (file.isDirectory() || !file.getName().endsWith(".json") && !file.getName().endsWith(".dat")) continue;
                    try {
                        NBTTagCompound compound;
                        if (file.getName().endsWith(".json") && (compound = NBTJsonUtil.LoadFile(file)).func_74764_b("PlayerName")) {
                            map.put(compound.func_74779_i("PlayerName"), file.getName().substring(0, file.getName().length() - 5));
                        }
                        if (file.getName().endsWith(".dat") && (compound = NBTJsonUtil.loadNBTData(file)).func_74764_b("PlayerName")) {
                            map.put(compound.func_74779_i("PlayerName"), file.getName().substring(0, file.getName().length() - 4));
                        }
                    }
                    catch (Exception e) {
                        LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
                    }
                    if (tenPercent == 0 || progress == 100 || i % tenPercent != 0) continue;
                    LogWriter.info("Creating PlayerMap: Progress: " + (progress += 10) + "%");
                }
            }
            this.nameUUIDs = map;
            this.savePlayerDataMap();
        }
        LogWriter.info("Done loading PlayerData");
    }

    public boolean getPlayerDataMap() {
        File file;
        try {
            file = new File(CustomNpcs.getWorldSaveDirectory(), "playerdatamap.dat");
            if (file.exists()) {
                this.loadPlayerDataMap(file);
                return true;
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        try {
            file = new File(CustomNpcs.getWorldSaveDirectory(), "playerdatamap.dat_old");
            if (file.exists()) {
                this.loadPlayerDataMap(file);
                return true;
            }
        }
        catch (Exception e2) {
            LogWriter.except(e2);
        }
        return false;
    }

    private void loadPlayerDataMap(File file) throws IOException {
        DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.loadPlayerDatasMap(var1);
        var1.close();
    }

    public void loadPlayerDatasMap(DataInputStream stream) throws IOException {
        NBTTagCompound nbttagcompound = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.readNBT(nbttagcompound);
    }

    public synchronized void savePlayerDataMap() {
        CustomNPCsThreader.customNPCThread.execute(() -> {
            try {
                File saveDir = CustomNpcs.getWorldSaveDirectory();
                File file = new File(saveDir, "playerdatamap.dat_new");
                File file1 = new File(saveDir, "playerdatamap.dat_old");
                File file2 = new File(saveDir, "playerdatamap.dat");
                CompressedStreamTools.func_74799_a((NBTTagCompound)this.writeNBT(), (OutputStream)new FileOutputStream(file));
                if (file1.exists()) {
                    file1.delete();
                }
                file2.renameTo(file1);
                if (file2.exists()) {
                    file2.delete();
                }
                file.renameTo(file2);
                if (file.exists()) {
                    file.delete();
                }
            }
            catch (Exception e) {
                LogWriter.except(e);
            }
        });
    }

    public File getSaveDir() {
        try {
            File file = new File(CustomNpcs.getWorldSaveDirectory(), "playerdata");
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

    public File getNewSaveDir() {
        try {
            File file = new File(CustomNpcs.getWorldSaveDirectory(), "playerdata_new");
            if (file.exists()) {
                return null;
            }
            file.mkdir();
            return file;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public NBTTagCompound loadPlayerDataOld(String player) {
        File file;
        String filename;
        File saveDir;
        block29: {
            saveDir = this.getSaveDir();
            filename = player;
            if (filename.isEmpty()) {
                filename = "noplayername";
            }
            filename = filename + ".dat";
            try {
                NBTTagCompound comp;
                file = new File(saveDir, filename);
                if (!file.exists()) break block29;
                try (FileInputStream fis2 = new FileInputStream(file);){
                    comp = CompressedStreamTools.func_74796_a((InputStream)fis2);
                }
                file.delete();
                file = new File(saveDir, filename + "_old");
                if (!file.exists()) return comp;
                file.delete();
                return comp;
            }
            catch (Exception e) {
                LogWriter.except(e);
            }
        }
        try {
            file = new File(saveDir, filename + "_old");
            if (!file.exists()) return new NBTTagCompound();
            try (FileInputStream fis = new FileInputStream(file);){
                NBTTagCompound nBTTagCompound = CompressedStreamTools.func_74796_a((InputStream)fis);
                return nBTTagCompound;
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        return new NBTTagCompound();
    }

    public NBTTagCompound loadPlayerData(String player) {
        File saveDir = this.getSaveDir();
        String filename = player;
        if (filename.isEmpty()) {
            filename = "noplayername";
        }
        filename = ConfigMain.DatFormat ? filename + ".dat" : filename + ".json";
        try {
            File file = new File(saveDir, filename);
            if (file.exists()) {
                if (ConfigMain.DatFormat) {
                    return NBTJsonUtil.loadNBTData(file);
                }
                return NBTJsonUtil.LoadFile(file);
            }
        }
        catch (Exception e) {
            LogWriter.error("Error loading: " + filename, e);
        }
        return new NBTTagCompound();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void putPlayerDataCache(String uuid, PlayerData playerCompound) {
        CacheHashMap<String, CacheHashMap.CachedObject<PlayerData>> cacheHashMap = this.playerDataCache;
        synchronized (cacheHashMap) {
            this.playerDataCache.put(uuid, new CacheHashMap.CachedObject<PlayerData>(playerCompound));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public PlayerData getPlayerDataCache(String uuid) {
        CacheHashMap<String, CacheHashMap.CachedObject<PlayerData>> cacheHashMap = this.playerDataCache;
        synchronized (cacheHashMap) {
            if (!this.playerDataCache.containsKey(uuid)) {
                return null;
            }
            return (PlayerData)((CacheHashMap.CachedObject)this.playerDataCache.get(uuid)).getObject();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removePlayerDataCache(String uuid) {
        CacheHashMap<String, CacheHashMap.CachedObject<PlayerData>> cacheHashMap = this.playerDataCache;
        synchronized (cacheHashMap) {
            this.playerDataCache.remove(uuid);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearCache() {
        CacheHashMap<String, CacheHashMap.CachedObject<PlayerData>> cacheHashMap = this.playerDataCache;
        synchronized (cacheHashMap) {
            this.playerDataCache.clear();
        }
    }

    public PlayerBankData getBankData(EntityPlayer player, int bankId) {
        Bank bank = BankController.getInstance().getBank(bankId);
        PlayerBankData data = PlayerData.get((EntityPlayer)player).bankData;
        if (!data.hasBank(bank.id)) {
            data.loadNew(bank.id);
        }
        return data;
    }

    public ArrayList<PlayerData> getAllPlayerData() {
        ArrayList<PlayerData> playerDataList = new ArrayList<PlayerData>();
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        for (Object o : list) {
            if (!(o instanceof EntityPlayer)) continue;
            playerDataList.add(PlayerData.get((EntityPlayer)o));
        }
        return playerDataList;
    }

    public PlayerData getPlayerData(EntityPlayer player) {
        PlayerData data = this.getPlayerDataCache(player.func_110124_au().toString());
        if (data != null) {
            data.player = player;
            return data;
        }
        data = (PlayerData)player.getExtendedProperties("CustomNpcsData");
        if (data == null) {
            data = new PlayerData();
            player.registerExtendedProperties("CustomNpcsData", (IExtendedEntityProperties)data);
            data.player = player;
            data.scriptData = new PlayerDataScript(player);
            data.load();
        }
        data.player = player;
        return data;
    }

    public static EntityPlayer getPlayerFromUUID(UUID uuid) {
        MinecraftServer server = MinecraftServer.func_71276_C();
        if (server != null) {
            for (Object playerObj : server.func_71203_ab().field_72404_b) {
                EntityPlayer player;
                if (!(playerObj instanceof EntityPlayer) || !(player = (EntityPlayer)playerObj).func_110124_au().equals(uuid)) continue;
                return player;
            }
        }
        return null;
    }

    public String hasPlayer(String username) {
        for (String name : this.nameUUIDs.keySet()) {
            if (!name.equalsIgnoreCase(username)) continue;
            return name;
        }
        return "";
    }

    public String getPlayerUUIDFromName(String username) {
        for (String name : this.nameUUIDs.keySet()) {
            if (!name.equalsIgnoreCase(username)) continue;
            return this.nameUUIDs.get(name);
        }
        return "";
    }

    public PlayerData getDataFromUsername(String username) {
        EntityPlayerMP player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(username);
        PlayerData data = null;
        if (player == null) {
            for (String name : this.nameUUIDs.keySet()) {
                if (!name.equalsIgnoreCase(username)) continue;
                data = new PlayerData();
                data.setNBT(Instance.loadPlayerData(this.nameUUIDs.get(name)));
                break;
            }
        } else {
            data = PlayerData.get((EntityPlayer)player);
        }
        return data;
    }

    public PlayerData getData(UUID uuid) {
        PlayerData data;
        EntityPlayer player = PlayerDataController.getPlayerFromUUID(uuid);
        if (player == null) {
            data = new PlayerData();
            data.setNBT(Instance.loadPlayerData(uuid.toString()));
        } else {
            data = PlayerData.get(player);
        }
        return data;
    }

    public void addPlayerMessage(String username, PlayerMail mail) {
        mail.time = System.currentTimeMillis();
        EntityPlayerMP player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(username);
        PlayerData data = this.getDataFromUsername(username);
        data.mailData.playermail.add(mail.copy());
        data.save();
    }

    public List<PlayerData> getPlayersData(ICommandSender sender, String username) {
        ArrayList<PlayerData> list = new ArrayList<PlayerData>();
        EntityPlayerMP[] players = PlayerSelector.func_82380_c((ICommandSender)sender, (String)username);
        if (players == null || players.length == 0) {
            PlayerData data = Instance.getDataFromUsername(username);
            if (data != null) {
                list.add(data);
            }
        } else {
            for (EntityPlayerMP player : players) {
                list.add(PlayerData.get((EntityPlayer)player));
            }
        }
        return list;
    }

    public void putPlayerMap(String playerName, String uuid) {
        this.nameUUIDs.put(playerName, uuid);
        this.savePlayerDataMap();
    }

    public boolean hasMail(EntityPlayer player) {
        return PlayerData.get((EntityPlayer)player).mailData.hasMail();
    }

    public void readNBT(NBTTagCompound compound) {
        this.nameUUIDs = new HashMap();
        NBTTagList list = compound.func_150295_c("PlayerDataMap", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                String playerName = nbttagcompound.func_74779_i("Name");
                String uuid = nbttagcompound.func_74779_i("UUID");
                this.nameUUIDs.put(playerName, uuid);
            }
        }
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList playerList = new NBTTagList();
        for (String key : this.nameUUIDs.keySet()) {
            NBTTagCompound playerCompound = new NBTTagCompound();
            playerCompound.func_74778_a("Name", key);
            playerCompound.func_74778_a("UUID", this.nameUUIDs.get(key));
            playerList.func_74742_a((NBTBase)playerCompound);
        }
        nbt.func_74782_a("PlayerDataMap", (NBTBase)playerList);
        return nbt;
    }

    public void generatePlayerMap(EntityPlayerMP sender) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            int length;
            if (sender != null) {
                LogWriter.info("PlayerMap regeneration queued by " + sender.func_70005_c_());
                sender.func_145747_a((IChatComponent)new ChatComponentText("You have initiated PlayerMap regeneration"));
            }
            this.nameUUIDs.clear();
            File dir = this.getSaveDir();
            LogWriter.info("Generating PlayerData map file...");
            File[] files = dir.listFiles();
            HashMap<String, String> map = new HashMap<String, String>();
            if (files != null && (length = files.length) != 0) {
                if (length > 100) {
                    LogWriter.info("Found " + length + " PlayerData files... This may take a few minutes");
                }
                int tenPercent = (int)((double)length * 0.1);
                int progress = 0;
                for (int i = 0; i < length; ++i) {
                    File file = files[i];
                    if (file.isDirectory() || !file.getName().endsWith(".json") && !file.getName().endsWith(".dat")) continue;
                    try {
                        NBTTagCompound compound;
                        if (file.getName().endsWith(".json") && (compound = NBTJsonUtil.LoadFile(file)).func_74764_b("PlayerName")) {
                            map.put(compound.func_74779_i("PlayerName"), file.getName().substring(0, file.getName().length() - 5));
                        }
                        if (file.getName().endsWith(".dat") && (compound = NBTJsonUtil.loadNBTData(file)).func_74764_b("PlayerName")) {
                            map.put(compound.func_74779_i("PlayerName"), file.getName().substring(0, file.getName().length() - 4));
                        }
                    }
                    catch (Exception e) {
                        LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
                    }
                    if (tenPercent == 0 || progress == 100 || i % tenPercent != 0) continue;
                    LogWriter.info("Creating PlayerMap: Progress: " + (progress += 10) + "%");
                }
            }
            this.nameUUIDs = map;
            this.savePlayerDataMap();
            if (sender != null) {
                sender.func_145747_a((IChatComponent)new ChatComponentText("PlayerMap regeneration complete"));
            }
        });
        executor.shutdown();
    }

    public void convertPlayerFiles(EntityPlayerMP sender, boolean type) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            int length;
            String fileType = type ? ".dat" : ".json";
            if (sender != null) {
                LogWriter.info("PlayerData Conversion queued by " + sender.func_70005_c_());
                sender.func_145747_a((IChatComponent)new ChatComponentText("PlayerData Conversion to " + fileType + " format"));
            }
            File dir = this.getSaveDir();
            LogWriter.info("Converting PlayerData to " + fileType + " format");
            File[] files = dir.listFiles();
            if (files != null && (length = files.length) != 0) {
                if (length > 100) {
                    LogWriter.info("Found " + length + " PlayerData files... This may take a few minutes");
                }
                int tenPercent = (int)((double)length * 0.1);
                int progress = 0;
                File saveDir = Instance.getNewSaveDir();
                if (saveDir == null) {
                    if (sender != null) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText("playerdata_new folder already exists please delete it or rename it"));
                    }
                    LogWriter.error("playerdata_new folder already exists please delete it or rename it");
                    return;
                }
                for (int i = 0; i < length; ++i) {
                    File file = files[i];
                    if (file.isDirectory() || !file.getName().endsWith(".json") && !file.getName().endsWith(".dat")) continue;
                    try {
                        String filename = "error";
                        boolean valid = false;
                        NBTTagCompound compound = new NBTTagCompound();
                        if (type) {
                            if (file.getName().endsWith(".json") && (compound = NBTJsonUtil.LoadFile(file)).func_74764_b("PlayerName")) {
                                filename = file.getName().substring(0, file.getName().length() - 5);
                                valid = true;
                            }
                        } else if (file.getName().endsWith(".dat") && (compound = NBTJsonUtil.loadNBTData(file)).func_74764_b("PlayerName")) {
                            filename = file.getName().substring(0, file.getName().length() - 4);
                            valid = true;
                        }
                        if (valid) {
                            try {
                                File newFile = new File(saveDir, filename + "_new" + fileType);
                                File oldFile = new File(saveDir, filename + fileType);
                                if (type) {
                                    CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(newFile));
                                } else {
                                    NBTJsonUtil.SaveFile(newFile, compound);
                                }
                                if (oldFile.exists()) {
                                    oldFile.delete();
                                }
                                newFile.renameTo(oldFile);
                            }
                            catch (Exception e) {
                                LogWriter.except(e);
                            }
                        }
                    }
                    catch (Exception e) {
                        LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
                    }
                    if (tenPercent == 0 || progress == 100 || i % tenPercent != 0) continue;
                    LogWriter.info("Converting PlayerData: Progress: " + (progress += 10) + "%");
                }
            }
            if (sender != null) {
                sender.func_145747_a((IChatComponent)new ChatComponentText("PlayerData Conversion complete"));
            }
            LogWriter.info("PlayerData Converted - Please rename the playerdata_new folder to playerdata");
        });
        executor.shutdown();
    }
}

