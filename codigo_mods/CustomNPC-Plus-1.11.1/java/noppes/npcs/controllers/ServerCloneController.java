/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package noppes.npcs.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.ICloneHandler;
import noppes.npcs.controllers.ServerTagMapController;
import noppes.npcs.controllers.data.CloneFolder;
import noppes.npcs.controllers.data.TagMap;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.util.NBTJsonUtil;

public class ServerCloneController
implements ICloneHandler {
    public static ServerCloneController Instance;
    protected Map<String, CloneFolder> folders = new LinkedHashMap<String, CloneFolder>();

    public ServerCloneController() {
        this.loadClones();
        this.loadFolders();
    }

    private void loadClones() {
        try {
            File dir = new File(this.getDir(), "..");
            File file = new File(dir, "clonednpcs.dat");
            if (file.exists()) {
                Map<Integer, Map<String, NBTTagCompound>> clones = this.loadOldClones(file);
                file.delete();
                file = new File(dir, "clonednpcs.dat_old");
                if (file.exists()) {
                    file.delete();
                }
                for (int tab : clones.keySet()) {
                    Map<String, NBTTagCompound> map = clones.get(tab);
                    for (String name : map.keySet()) {
                        this.saveClone(tab, name, map.get(name));
                    }
                }
            }
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public File getDir() {
        File dir = new File(CustomNpcs.getWorldSaveDirectory(), "clones");
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    private Map<Integer, Map<String, NBTTagCompound>> loadOldClones(File file) throws Exception {
        NBTTagCompound nbttagcompound1;
        HashMap<Integer, Map<String, NBTTagCompound>> clones = new HashMap<Integer, Map<String, NBTTagCompound>>();
        try (FileInputStream fis = new FileInputStream(file);){
            nbttagcompound1 = CompressedStreamTools.func_74796_a((InputStream)fis);
        }
        NBTTagList list = nbttagcompound1.func_150295_c("Data", 10);
        if (list == null) {
            return clones;
        }
        for (int i = 0; i < list.func_74745_c(); ++i) {
            HashMap<String, NBTTagCompound> tab;
            NBTTagCompound compound = list.func_150305_b(i);
            if (!compound.func_74764_b("ClonedTab")) {
                compound.func_74768_a("ClonedTab", 1);
            }
            if ((tab = (HashMap<String, NBTTagCompound>)clones.get(compound.func_74762_e("ClonedTab"))) == null) {
                tab = new HashMap<String, NBTTagCompound>();
                clones.put(compound.func_74762_e("ClonedTab"), tab);
            }
            String name = compound.func_74779_i("ClonedName");
            int number = 1;
            while (tab.containsKey(name)) {
                name = String.format("%s%s", compound.func_74779_i("ClonedName"), ++number);
            }
            compound.func_82580_o("ClonedName");
            compound.func_82580_o("ClonedTab");
            compound.func_82580_o("ClonedDate");
            this.cleanTags(compound);
            tab.put(name, compound);
        }
        return clones;
    }

    public NBTTagCompound getCloneData(ICommandSender player, String name, int tab) {
        File file = new File(new File(this.getDir(), tab + ""), name + ".json");
        if (!file.exists()) {
            if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("Could not find clone file"));
            }
            return null;
        }
        try {
            return NBTJsonUtil.LoadFile(file);
        }
        catch (Exception e) {
            LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
            }
            return null;
        }
    }

    public void saveClone(int tab, String name, NBTTagCompound compound) {
        try {
            File dir = new File(this.getDir(), tab + "");
            if (!dir.exists()) {
                dir.mkdir();
            }
            String filename = name + ".json";
            File file = new File(dir, filename + "_new");
            File file2 = new File(dir, filename);
            NBTJsonUtil.SaveFile(file, compound);
            this.addToTagMap(compound, name, tab);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public List<String> getClones(int tab) {
        ArrayList<String> list = new ArrayList<String>();
        File dir = new File(this.getDir(), tab + "");
        if (!dir.exists() || !dir.isDirectory()) {
            return list;
        }
        for (String file : dir.list()) {
            if (!file.endsWith(".json")) continue;
            list.add(file.substring(0, file.length() - 5));
        }
        return list;
    }

    public List<String> getClonesDate(int tab) {
        ArrayList<String> list = new ArrayList<String>();
        File dir = new File(this.getDir(), tab + "");
        if (!dir.exists() || !dir.isDirectory()) {
            return list;
        }
        File[] files = dir.listFiles();
        Arrays.sort(files, new Comparator<File>(){

            @Override
            public int compare(File f1, File f2) {
                return Long.compare(f1.lastModified(), f2.lastModified());
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (!fileName.endsWith(".json")) continue;
            list.add(fileName.substring(0, fileName.length() - 5));
        }
        return list;
    }

    public boolean removeClone(String name, int tab) {
        File file = new File(new File(this.getDir(), tab + ""), name + ".json");
        if (!file.exists()) {
            return false;
        }
        file.delete();
        this.removeFromTagMap(name, tab);
        return true;
    }

    public String addClone(NBTTagCompound nbttagcompound, String name, int tab) {
        this.cleanTags(nbttagcompound);
        this.saveClone(tab, name, nbttagcompound);
        return name;
    }

    public String addClone(NBTTagCompound nbttagcompound, String name, int tab, NBTTagCompound tempTags) {
        this.cleanTagList(nbttagcompound, tempTags);
        this.cleanTags(nbttagcompound);
        this.saveClone(tab, name, nbttagcompound);
        return name;
    }

    public void loadFolders() {
        this.folders.clear();
        File dir = this.getDir();
        File[] files = dir.listFiles();
        if (files != null) {
            ArrayList<File> folderDirs = new ArrayList<File>();
            for (File f : files) {
                String name;
                if (!f.isDirectory() || (name = f.getName()).startsWith("___")) continue;
                try {
                    Integer.parseInt(name);
                }
                catch (NumberFormatException numberFormatException) {
                    folderDirs.add(f);
                }
            }
            folderDirs.sort(Comparator.comparing(File::getName));
            for (File f : folderDirs) {
                CloneFolder folder = new CloneFolder(f.getName());
                folder.createdDate = f.lastModified();
                this.folders.put(f.getName(), folder);
            }
        }
    }

    public List<CloneFolder> getFolderList() {
        return new ArrayList<CloneFolder>(this.folders.values());
    }

    public List<String> getFolderNames() {
        return new ArrayList<String>(this.folders.keySet());
    }

    @Override
    public boolean hasFolder(String name) {
        return this.folders.containsKey(name);
    }

    public CloneFolder createFolder(String name) {
        if (!CloneFolder.isValidName(name) || this.folders.containsKey(name)) {
            return null;
        }
        CloneFolder folder = new CloneFolder(name);
        File dir = this.getFolderDir(name);
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.folders.put(name, folder);
        return folder;
    }

    public boolean renameFolder(String oldName, String newName) {
        File newDir;
        if (!this.folders.containsKey(oldName) || !CloneFolder.isValidName(newName) || this.folders.containsKey(newName)) {
            return false;
        }
        CloneFolder folder = this.folders.get(oldName);
        File oldDir = this.getFolderDir(oldName);
        if (!oldDir.renameTo(newDir = new File(this.getDir(), newName))) {
            return false;
        }
        this.folders.remove(oldName);
        folder.name = newName;
        this.folders.put(newName, folder);
        return true;
    }

    public boolean deleteFolder(String name) {
        if (!this.folders.containsKey(name)) {
            return false;
        }
        List<String> clones = this.getClones(name);
        if (!clones.isEmpty()) {
            return false;
        }
        File dir = this.getFolderDir(name);
        if (dir.exists()) {
            File tagMapNew;
            File tagMapOld;
            File tagMapFile = new File(dir, "___tagmap.dat");
            if (tagMapFile.exists()) {
                tagMapFile.delete();
            }
            if ((tagMapOld = new File(dir, "___tagmap.dat_old")).exists()) {
                tagMapOld.delete();
            }
            if ((tagMapNew = new File(dir, "___tagmap.dat_new")).exists()) {
                tagMapNew.delete();
            }
            dir.delete();
        }
        this.folders.remove(name);
        return true;
    }

    public File getFolderDir(String folderName) {
        return new File(this.getDir(), folderName);
    }

    public List<String> getClones(String folderName) {
        ArrayList<String> list = new ArrayList<String>();
        File dir = this.getFolderDir(folderName);
        if (!dir.exists() || !dir.isDirectory()) {
            return list;
        }
        for (String file : dir.list()) {
            if (!file.endsWith(".json")) continue;
            list.add(file.substring(0, file.length() - 5));
        }
        return list;
    }

    public List<String> getClonesDate(String folderName) {
        ArrayList<String> list = new ArrayList<String>();
        File dir = this.getFolderDir(folderName);
        if (!dir.exists() || !dir.isDirectory()) {
            return list;
        }
        File[] files = dir.listFiles();
        if (files == null) {
            return list;
        }
        Arrays.sort(files, new Comparator<File>(){

            @Override
            public int compare(File f1, File f2) {
                return Long.compare(f1.lastModified(), f2.lastModified());
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (!fileName.endsWith(".json")) continue;
            list.add(fileName.substring(0, fileName.length() - 5));
        }
        return list;
    }

    public NBTTagCompound getCloneData(ICommandSender player, String name, String folderName) {
        File file = new File(this.getFolderDir(folderName), name + ".json");
        if (!file.exists()) {
            if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText("Could not find clone file"));
            }
            return null;
        }
        try {
            return NBTJsonUtil.LoadFile(file);
        }
        catch (Exception e) {
            LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            if (player != null) {
                player.func_145747_a((IChatComponent)new ChatComponentText(e.getMessage()));
            }
            return null;
        }
    }

    public void saveClone(String folderName, String name, NBTTagCompound compound) {
        try {
            File dir = this.getFolderDir(folderName);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String filename = name + ".json";
            File file = new File(dir, filename + "_new");
            File file2 = new File(dir, filename);
            NBTJsonUtil.SaveFile(file, compound);
            this.addToTagMap(compound, name, folderName);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
    }

    public boolean removeClone(String name, String folderName) {
        File file = new File(this.getFolderDir(folderName), name + ".json");
        if (!file.exists()) {
            return false;
        }
        file.delete();
        this.removeFromTagMap(name, folderName);
        return true;
    }

    public String addClone(NBTTagCompound nbttagcompound, String name, String folderName) {
        this.cleanTags(nbttagcompound);
        this.saveClone(folderName, name, nbttagcompound);
        return name;
    }

    public String addClone(NBTTagCompound nbttagcompound, String name, String folderName, NBTTagCompound tempTags) {
        this.cleanTagList(nbttagcompound, tempTags);
        this.cleanTags(nbttagcompound);
        this.saveClone(folderName, name, nbttagcompound);
        return name;
    }

    public boolean moveClone(String cloneName, int fromTab, String toFolder) {
        NBTTagCompound data = this.getCloneData(null, cloneName, fromTab);
        if (data == null) {
            return false;
        }
        HashSet<UUID> tagUUIDs = this.getTagUUIDsFromTab(cloneName, fromTab);
        this.saveClone(toFolder, cloneName, data);
        this.setTagUUIDsForFolder(cloneName, toFolder, tagUUIDs);
        this.removeClone(cloneName, fromTab);
        return true;
    }

    public boolean moveClone(String cloneName, String fromFolder, int toTab) {
        NBTTagCompound data = this.getCloneData(null, cloneName, fromFolder);
        if (data == null) {
            return false;
        }
        HashSet<UUID> tagUUIDs = this.getTagUUIDsFromFolder(cloneName, fromFolder);
        this.saveClone(toTab, cloneName, data);
        this.setTagUUIDsForTab(cloneName, toTab, tagUUIDs);
        this.removeClone(cloneName, fromFolder);
        return true;
    }

    public boolean moveClone(String cloneName, String fromFolder, String toFolder) {
        NBTTagCompound data = this.getCloneData(null, cloneName, fromFolder);
        if (data == null) {
            return false;
        }
        HashSet<UUID> tagUUIDs = this.getTagUUIDsFromFolder(cloneName, fromFolder);
        this.saveClone(toFolder, cloneName, data);
        this.setTagUUIDsForFolder(cloneName, toFolder, tagUUIDs);
        this.removeClone(cloneName, fromFolder);
        return true;
    }

    public boolean moveClone(String cloneName, int fromTab, int toTab) {
        NBTTagCompound data = this.getCloneData(null, cloneName, fromTab);
        if (data == null) {
            return false;
        }
        HashSet<UUID> tagUUIDs = this.getTagUUIDsFromTab(cloneName, fromTab);
        this.saveClone(toTab, cloneName, data);
        this.setTagUUIDsForTab(cloneName, toTab, tagUUIDs);
        this.removeClone(cloneName, fromTab);
        return true;
    }

    public NBTTagCompound cleanTagList(NBTTagCompound nbttagcompound, NBTTagCompound tempTags) {
        int i;
        NBTTagList nbtTagList;
        HashSet<UUID> tagUUIDs = new HashSet<UUID>();
        if (nbttagcompound.func_74764_b("TagUUIDs")) {
            nbtTagList = nbttagcompound.func_150295_c("TagUUIDs", 8);
            for (i = 0; i < nbtTagList.func_74745_c(); ++i) {
                tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
            nbttagcompound.func_82580_o("TagUUIDs");
        }
        if (tempTags.func_74764_b("TempTagUUIDs")) {
            nbtTagList = tempTags.func_150295_c("TempTagUUIDs", 8);
            for (i = 0; i < nbtTagList.func_74745_c(); ++i) {
                tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
            tempTags.func_82580_o("TempTagUUIDs");
        }
        if (tagUUIDs.size() > 0) {
            nbtTagList = new NBTTagList();
            for (UUID uuid : tagUUIDs) {
                nbtTagList.func_74742_a((NBTBase)new NBTTagString(uuid.toString()));
            }
            nbttagcompound.func_74782_a("TagUUIDs", (NBTBase)nbtTagList);
        }
        return nbttagcompound;
    }

    public boolean addToTagMap(NBTTagCompound nbttagcompound, String name, int tab) {
        HashSet<UUID> tagUUIDs = new HashSet<UUID>();
        if (nbttagcompound.func_74764_b("TagUUIDs")) {
            NBTTagList nbtTagList = nbttagcompound.func_150295_c("TagUUIDs", 8);
            for (int i = 0; i < nbtTagList.func_74745_c(); ++i) {
                tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
        }
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(tab);
        if (!tagUUIDs.isEmpty()) {
            tagMap.putClone(name, tagUUIDs);
        } else {
            tagMap.removeClone(name);
        }
        ServerTagMapController.Instance.saveTagMap(tagMap);
        return true;
    }

    public boolean addToTagMap(NBTTagCompound nbttagcompound, String name, String folderName) {
        HashSet<UUID> tagUUIDs = new HashSet<UUID>();
        if (nbttagcompound.func_74764_b("TagUUIDs")) {
            NBTTagList nbtTagList = nbttagcompound.func_150295_c("TagUUIDs", 8);
            for (int i = 0; i < nbtTagList.func_74745_c(); ++i) {
                tagUUIDs.add(UUID.fromString(nbtTagList.func_150307_f(i)));
            }
        }
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(folderName);
        if (!tagUUIDs.isEmpty()) {
            tagMap.putClone(name, tagUUIDs);
        } else {
            tagMap.removeClone(name);
        }
        ServerTagMapController.Instance.saveTagMap(tagMap);
        return true;
    }

    public boolean removeFromTagMap(String name, int tab) {
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(tab);
        if (tagMap.removeClone(name)) {
            ServerTagMapController.Instance.saveTagMap(tagMap);
            return true;
        }
        return false;
    }

    public boolean removeFromTagMap(String name, String folderName) {
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(folderName);
        if (tagMap.removeClone(name)) {
            ServerTagMapController.Instance.saveTagMap(tagMap);
            return true;
        }
        return false;
    }

    protected HashSet<UUID> getTagUUIDsFromTab(String cloneName, int tab) {
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(tab);
        HashSet<UUID> uuids = tagMap.getUUIDs(cloneName);
        return uuids != null ? new HashSet<UUID>(uuids) : new HashSet();
    }

    protected HashSet<UUID> getTagUUIDsFromFolder(String cloneName, String folderName) {
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(folderName);
        HashSet<UUID> uuids = tagMap.getUUIDs(cloneName);
        return uuids != null ? new HashSet<UUID>(uuids) : new HashSet();
    }

    protected void setTagUUIDsForTab(String cloneName, int tab, HashSet<UUID> tagUUIDs) {
        if (tagUUIDs == null || tagUUIDs.isEmpty()) {
            return;
        }
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(tab);
        tagMap.putClone(cloneName, tagUUIDs);
        ServerTagMapController.Instance.saveTagMap(tagMap);
    }

    protected void setTagUUIDsForFolder(String cloneName, String folderName, HashSet<UUID> tagUUIDs) {
        if (tagUUIDs == null || tagUUIDs.isEmpty()) {
            return;
        }
        TagMap tagMap = ServerTagMapController.Instance.getTagMap(folderName);
        tagMap.putClone(cloneName, tagUUIDs);
        ServerTagMapController.Instance.saveTagMap(tagMap);
    }

    public void cleanTags(NBTTagCompound nbttagcompound) {
        NBTTagCompound adv;
        if (nbttagcompound.func_74764_b("ItemGiverId")) {
            nbttagcompound.func_74768_a("ItemGiverId", 0);
        }
        if (nbttagcompound.func_74764_b("TransporterId")) {
            nbttagcompound.func_74768_a("TransporterId", -1);
        }
        nbttagcompound.func_82580_o("StartPosNew");
        nbttagcompound.func_82580_o("StartPos");
        nbttagcompound.func_82580_o("MovingPathNew");
        nbttagcompound.func_82580_o("Pos");
        nbttagcompound.func_82580_o("Riding");
        if (!nbttagcompound.func_74764_b("ModRev")) {
            nbttagcompound.func_74768_a("ModRev", 1);
        }
        if (nbttagcompound.func_74764_b("TransformRole")) {
            adv = nbttagcompound.func_74775_l("TransformRole");
            adv.func_74768_a("TransporterId", -1);
            nbttagcompound.func_74782_a("TransformRole", (NBTBase)adv);
        }
        if (nbttagcompound.func_74764_b("TransformJob")) {
            adv = nbttagcompound.func_74775_l("TransformJob");
            adv.func_74768_a("ItemGiverId", 0);
            nbttagcompound.func_74782_a("TransformJob", (NBTBase)adv);
        }
        if (nbttagcompound.func_74764_b("TransformAI")) {
            adv = nbttagcompound.func_74775_l("TransformAI");
            adv.func_82580_o("StartPosNew");
            adv.func_82580_o("StartPos");
            adv.func_82580_o("MovingPathNew");
            nbttagcompound.func_74782_a("TransformAI", (NBTBase)adv);
        }
    }

    @Override
    public IEntity spawn(double x, double y, double z, int tab, String name, IWorld world, boolean ignoreProtection) {
        NBTTagCompound compound = this.getCloneData((ICommandSender)null, name, tab);
        if (compound == null) {
            throw new CustomNPCsException("Unknown clone tab:" + tab + " name:" + name, new Object[0]);
        }
        Entity entity = !ignoreProtection ? NoppesUtilServer.spawnCloneWithProtection(compound, (int)x, (int)y, (int)z, (World)world.getMCWorld()) : NoppesUtilServer.spawnClone(compound, (int)x, (int)y, (int)z, (World)world.getMCWorld());
        return entity == null ? null : NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEntity spawn(IPos pos, int tab, String name, IWorld world, boolean ignoreProtection) {
        return this.spawn((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), tab, name, world, ignoreProtection);
    }

    @Override
    public IEntity spawn(double x, double y, double z, int tab, String name, IWorld world) {
        return this.spawn(x, y, z, tab, name, world, true);
    }

    @Override
    public IEntity spawn(IPos pos, int tab, String name, IWorld world) {
        return this.spawn((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), tab, name, world);
    }

    @Override
    public IEntity[] getTab(int tab, IWorld world) {
        File dir = new File(this.getDir(), tab + "");
        if (!dir.exists() || !dir.isDirectory() || dir.listFiles() == null) {
            return new IEntity[0];
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (File file : dir.listFiles()) {
                if (!file.getName().endsWith(".json")) continue;
                NBTTagCompound compound = NBTJsonUtil.LoadFile(file);
                this.cleanTags(compound);
                Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world.getMCWorld());
                arrayList.add(entity == null ? null : NpcAPI.Instance().getIEntity(entity));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList.toArray(new IEntity[0]);
    }

    @Override
    public IEntity get(int tab, String name, IWorld world) {
        NBTTagCompound compound = this.getCloneData((ICommandSender)null, name, tab);
        if (compound == null) {
            throw new CustomNPCsException("Unknown clone tab:" + tab + " name:" + name, new Object[0]);
        }
        this.cleanTags(compound);
        Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world.getMCWorld());
        return entity == null ? null : NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public boolean has(int tab, String name) {
        NBTTagCompound compound = this.getCloneData((ICommandSender)null, name, tab);
        return compound != null;
    }

    @Override
    public void set(int tab, String name, IEntity entity) {
        NBTTagCompound compound = new NBTTagCompound();
        if (!entity.getMCEntity().func_98035_c(compound)) {
            throw new CustomNPCsException("Cannot save dead entities", new Object[0]);
        }
        this.cleanTags(compound);
        this.saveClone(tab, name, compound);
    }

    @Override
    public void remove(int tab, String name) {
        this.removeClone(name, tab);
    }

    @Override
    public String[] getFolders() {
        return this.getFolderNames().toArray(new String[0]);
    }

    @Override
    public IEntity spawn(double x, double y, double z, String folderName, String name, IWorld world, boolean ignoreProtection) {
        NBTTagCompound compound = this.getCloneData(null, name, folderName);
        if (compound == null) {
            throw new CustomNPCsException("Unknown clone folder:" + folderName + " name:" + name, new Object[0]);
        }
        Entity entity = !ignoreProtection ? NoppesUtilServer.spawnCloneWithProtection(compound, (int)x, (int)y, (int)z, (World)world.getMCWorld()) : NoppesUtilServer.spawnClone(compound, (int)x, (int)y, (int)z, (World)world.getMCWorld());
        return entity == null ? null : NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEntity spawn(IPos pos, String folderName, String name, IWorld world, boolean ignoreProtection) {
        return this.spawn((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), folderName, name, world, ignoreProtection);
    }

    @Override
    public IEntity spawn(double x, double y, double z, String folderName, String name, IWorld world) {
        return this.spawn(x, y, z, folderName, name, world, true);
    }

    @Override
    public IEntity spawn(IPos pos, String folderName, String name, IWorld world) {
        return this.spawn((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), folderName, name, world);
    }

    @Override
    public IEntity[] getFolder(String folderName, IWorld world) {
        File dir = this.getFolderDir(folderName);
        if (!dir.exists() || !dir.isDirectory() || dir.listFiles() == null) {
            return new IEntity[0];
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (File file : dir.listFiles()) {
                if (!file.getName().endsWith(".json")) continue;
                NBTTagCompound compound = NBTJsonUtil.LoadFile(file);
                this.cleanTags(compound);
                Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world.getMCWorld());
                arrayList.add(entity == null ? null : NpcAPI.Instance().getIEntity(entity));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList.toArray(new IEntity[0]);
    }

    @Override
    public IEntity get(String folderName, String name, IWorld world) {
        NBTTagCompound compound = this.getCloneData(null, name, folderName);
        if (compound == null) {
            throw new CustomNPCsException("Unknown clone folder:" + folderName + " name:" + name, new Object[0]);
        }
        this.cleanTags(compound);
        Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world.getMCWorld());
        return entity == null ? null : NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public boolean has(String folderName, String name) {
        NBTTagCompound compound = this.getCloneData(null, name, folderName);
        return compound != null;
    }

    @Override
    public void set(String folderName, String name, IEntity entity) {
        NBTTagCompound compound = new NBTTagCompound();
        if (!entity.getMCEntity().func_98035_c(compound)) {
            throw new CustomNPCsException("Cannot save dead entities", new Object[0]);
        }
        this.cleanTags(compound);
        this.saveClone(folderName, name, compound);
    }

    @Override
    public void remove(String folderName, String name) {
        this.removeClone(name, folderName);
    }
}

