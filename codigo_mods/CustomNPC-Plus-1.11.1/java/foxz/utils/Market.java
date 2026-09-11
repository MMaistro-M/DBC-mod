/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package foxz.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;
import noppes.npcs.util.CacheHashMap;
import noppes.npcs.util.CustomNPCsThreader;
import noppes.npcs.util.MarketCachedObject;
import noppes.npcs.util.NBTJsonUtil;

public class Market {
    private static final CacheHashMap<String, MarketCachedObject> marketCache = new CacheHashMap(300000L, 60000L);

    public static void save(RoleTrader r, String name) {
        if (name.isEmpty()) {
            return;
        }
        NBTTagCompound roleCompound = r.writeNBT(new NBTTagCompound());
        roleCompound.func_74778_a("SaveName", name);
        Market.putMarketCache(name, roleCompound);
        Market.saveFile(roleCompound);
    }

    public static void saveFile(NBTTagCompound compound) {
        String name = compound.func_74779_i("SaveName");
        File file = Market.getFile(name + "_new");
        File file1 = Market.getFile(name);
        CustomNPCsThreader.customNPCThread.execute(() -> {
            try {
                if (ConfigMain.MarketDatFormat) {
                    CompressedStreamTools.func_74799_a((NBTTagCompound)compound, (OutputStream)new FileOutputStream(file));
                } else {
                    NBTJsonUtil.SaveFile(file, compound);
                }
                if (file1.exists()) {
                    file1.delete();
                }
                file.renameTo(file1);
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    public static void getMarket(RoleTrader role, String name) {
        NBTTagCompound data = Market.getMarketCache(name);
        if (data != null) {
            role.readNBT(data);
            return;
        }
        Market.load(role, name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NBTTagCompound getMarketCache(String name) {
        CacheHashMap<String, MarketCachedObject> cacheHashMap = marketCache;
        synchronized (cacheHashMap) {
            if (!marketCache.containsKey(name)) {
                return null;
            }
            return (NBTTagCompound)((MarketCachedObject)marketCache.get(name)).getObject();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void putMarketCache(String name, NBTTagCompound role) {
        CacheHashMap<String, MarketCachedObject> cacheHashMap = marketCache;
        synchronized (cacheHashMap) {
            marketCache.put(name, new MarketCachedObject(role));
        }
    }

    public static void load(RoleTrader role, String name) {
        if (role.npc.field_70170_p.field_72995_K) {
            return;
        }
        File file = Market.getFile(name);
        if (!file.exists()) {
            return;
        }
        try {
            if (ConfigMain.MarketDatFormat) {
                NBTTagCompound compound = NBTJsonUtil.loadNBTData(file);
                role.readNBT(compound);
            } else {
                role.readNBT(NBTJsonUtil.LoadFile(file));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static File getMarketDir() {
        try {
            File file = new File(CustomNpcs.getWorldSaveDirectory(), "markets");
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

    public static File getNewMarketDir() {
        try {
            File file = new File(CustomNpcs.getWorldSaveDirectory(), "markets_new");
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

    private static File getFile(String name) {
        String filename = name.toLowerCase();
        filename = ConfigMain.MarketDatFormat ? filename + ".dat" : filename + ".json";
        return new File(Market.getMarketDir(), filename);
    }

    public static void setMarket(EntityNPCInterface npc, String marketName) {
        if (marketName.isEmpty()) {
            return;
        }
        if (!Market.getFile(marketName).exists()) {
            Market.save((RoleTrader)npc.roleInterface, marketName);
        }
        Market.getMarket((RoleTrader)npc.roleInterface, marketName);
    }

    public static void convertMarketFiles(EntityPlayerMP sender, boolean type) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            int length;
            String fileType = type ? ".dat" : ".json";
            if (sender != null) {
                LogWriter.info("Market Conversion queued by " + sender.func_70005_c_());
                sender.func_145747_a((IChatComponent)new ChatComponentText("Market Conversion to " + fileType + " format"));
            }
            File dir = Market.getMarketDir();
            LogWriter.info("Converting Market to " + fileType + " format");
            File[] files = dir.listFiles();
            if (files != null && (length = files.length) != 0) {
                if (length > 100) {
                    LogWriter.info("Found " + length + " Market files... This may take a few minutes");
                }
                int tenPercent = (int)((double)length * 0.1);
                int progress = 0;
                File saveDir = Market.getNewMarketDir();
                if (saveDir == null) {
                    if (sender != null) {
                        sender.func_145747_a((IChatComponent)new ChatComponentText("markets_new folder already exists please delete it or rename it"));
                    }
                    LogWriter.error("markets_new folder already exists please delete it or rename it");
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
                    LogWriter.info("Converting Market: Progress: " + (progress += 10) + "%");
                }
            }
            if (sender != null) {
                sender.func_145747_a((IChatComponent)new ChatComponentText("Market Conversion complete"));
            }
            LogWriter.info("Market Converted - Please rename the markets_new folder to markets and restart");
        });
        executor.shutdown();
    }
}

