/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.network.enums.EnumSyncType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.ICustomEffectHandler;
import noppes.npcs.api.handler.data.ICustomEffect;
import noppes.npcs.controllers.CategoryManager;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.EffectKey;
import noppes.npcs.controllers.data.EffectScript;
import noppes.npcs.controllers.data.PlayerEffect;
import noppes.npcs.scripted.event.player.PlayerEvent;
import noppes.npcs.util.NBTJsonUtil;

public class CustomEffectController
implements ICustomEffectHandler {
    public static CustomEffectController Instance = new CustomEffectController();
    public HashMap<Integer, HashMap<Integer, CustomEffect>> indexMapper = new HashMap();
    private HashMap<Integer, String> indexLabels = new HashMap();
    public HashMap<Integer, CustomEffect> customEffectsSync = new HashMap();
    public HashMap<Integer, EffectScript> customEffectScriptHandlers = new HashMap();
    private HashMap<Integer, String> bootOrder;
    public CategoryManager categoryManager = new CategoryManager();
    private int lastUsedID = 0;
    public ConcurrentHashMap<UUID, ConcurrentHashMap<EffectKey, PlayerEffect>> playerEffects = new ConcurrentHashMap();

    public CustomEffectController() {
        HashMap customEffects = new HashMap();
        this.registerEffectMap(0, customEffects);
    }

    public static CustomEffectController getInstance() {
        return Instance;
    }

    public <T extends CustomEffect> void registerEffectMap(int index, HashMap<Integer, T> effectHashMap) {
        this.indexMapper.put(index, effectHashMap);
    }

    public void registerEffectMapLabel(int index, String label) {
        this.indexLabels.put(index, label);
    }

    public HashMap<Integer, String> getIndexLabels() {
        return this.indexLabels;
    }

    public HashMap<Integer, CustomEffect> getCustomEffects() {
        return this.indexMapper.get(0);
    }

    public void load() {
        this.lastUsedID = 0;
        this.playerEffects.clear();
        this.bootOrder = new HashMap();
        LogWriter.info("Loading custom effects...");
        this.readCustomEffectMap();
        this.loadCustomEffects();
        LogWriter.info("Done loading custom effects.");
    }

    public void runEffects(EntityPlayer player) {
        ConcurrentHashMap<EffectKey, PlayerEffect> current = this.getPlayerEffects(player);
        for (Map.Entry entry : current.entrySet()) {
            int index;
            int id = ((EffectKey)entry.getKey()).getId();
            CustomEffect effect = this.get(id, index = ((EffectKey)entry.getKey()).getIndex());
            if (effect == null) continue;
            effect.runEffect(player, (PlayerEffect)entry.getValue());
        }
    }

    public void killEffects(EntityPlayer player) {
        ConcurrentHashMap<EffectKey, PlayerEffect> current = this.getPlayerEffects(player);
        Iterator iterator = current.entrySet().iterator();
        while (iterator.hasNext()) {
            int index;
            Map.Entry entry = iterator.next();
            int id = ((EffectKey)entry.getKey()).getId();
            CustomEffect effect = this.get(id, index = ((EffectKey)entry.getKey()).getIndex());
            if (effect != null) {
                if (!effect.lossOnDeath) continue;
                effect.onRemoved(player, (PlayerEffect)entry.getValue(), PlayerEvent.EffectEvent.ExpirationType.DEATH);
                iterator.remove();
                continue;
            }
            iterator.remove();
        }
    }

    public void delete(int id) {
        CustomEffect effect = this.get(id);
        if (effect != null) {
            CustomEffect foundEffect = this.getCustomEffects().remove(effect.getID());
            this.customEffectScriptHandlers.remove(effect.getID());
            if (foundEffect != null && foundEffect.name != null) {
                File dir = this.categoryManager.getItemDir(id);
                File file = new File(dir, foundEffect.name + ".json");
                if (file.exists()) {
                    file.delete();
                }
                this.categoryManager.removeItem(id);
                SyncController.syncRemove(EnumSyncType.CUSTOM_EFFECTS, foundEffect.getID());
                this.saveEffectLoadMap();
            }
        }
    }

    public int getUnusedId() {
        for (int catid : this.getCustomEffects().keySet()) {
            if (catid <= this.lastUsedID) continue;
            this.lastUsedID = catid;
        }
        ++this.lastUsedID;
        return this.lastUsedID;
    }

    public CustomEffect get(int id, int index) {
        HashMap<Integer, CustomEffect> effectMap = this.indexMapper.get(index);
        return effectMap != null ? effectMap.get(id) : null;
    }

    public CustomEffect get(int id) {
        return this.get(id, 0);
    }

    public boolean has(int id, int index) {
        HashMap<Integer, CustomEffect> effectMap = this.indexMapper.get(index);
        return effectMap != null && effectMap.containsKey(id);
    }

    public boolean has(int id) {
        return this.has(id, 0);
    }

    public CustomEffect get(String name, int index) {
        HashMap<Integer, CustomEffect> effectMap = this.indexMapper.get(index);
        if (effectMap != null) {
            for (CustomEffect effect : effectMap.values()) {
                if (!effect.getName().equalsIgnoreCase(name)) continue;
                return effect;
            }
        }
        return null;
    }

    public CustomEffect get(String name) {
        return this.get(name, 0);
    }

    public boolean has(String name, int index) {
        return this.get(name, index) != null;
    }

    public boolean has(String name) {
        return this.has(name, 0);
    }

    public ConcurrentHashMap<EffectKey, PlayerEffect> getPlayerEffects(EntityPlayer player) {
        UUID playerId = NoppesUtilServer.getUUID((Entity)player);
        ConcurrentHashMap<EffectKey, PlayerEffect> effects = this.playerEffects.get(playerId);
        if (effects == null) {
            effects = new ConcurrentHashMap();
            this.playerEffects.put(playerId, effects);
        }
        return effects;
    }

    public void removeEffect(EntityPlayer player, PlayerEffect effect, PlayerEvent.EffectEvent.ExpirationType type) {
        EffectKey key;
        if (effect == null) {
            return;
        }
        ConcurrentHashMap<EffectKey, PlayerEffect> currentEffects = this.getPlayerEffects(player);
        if (currentEffects.containsKey(key = new EffectKey(effect.id, effect.index))) {
            CustomEffect parent = this.get(effect.id, effect.index);
            if (parent != null) {
                parent.onRemoved(player, effect, type);
            }
            currentEffects.remove(key);
        }
    }

    private boolean hasOther(String name, int id) {
        for (CustomEffect effect : this.getCustomEffects().values()) {
            if (effect.getID() == id || !effect.getName().equalsIgnoreCase(name)) continue;
            return true;
        }
        return false;
    }

    public void clearEffects(EntityPlayer player) {
        ConcurrentHashMap<EffectKey, PlayerEffect> effects = this.getPlayerEffects(player);
        if (effects != null) {
            effects.clear();
        }
    }

    public void clearEffect(EntityPlayer player, int id) {
        this.clearEffect(player, id, 0);
    }

    public void clearEffect(EntityPlayer player, int id, int index) {
        ConcurrentHashMap<EffectKey, PlayerEffect> effects = this.getPlayerEffects(player);
        if (effects != null) {
            effects.remove(new EffectKey(id, index));
        }
    }

    public boolean hasEffect(EntityPlayer player, int id) {
        return this.hasEffect(player, id, 0);
    }

    public boolean hasEffect(EntityPlayer player, int id, int index) {
        return this.getPlayerEffects(player).containsKey(new EffectKey(id, index));
    }

    public int getEffectDuration(EntityPlayer player, int id) {
        return this.getEffectDuration(player, id, 0);
    }

    public int getEffectDuration(EntityPlayer player, int id, int index) {
        PlayerEffect effect = this.getPlayerEffects(player).get(new EffectKey(id, index));
        return effect != null ? effect.duration : -1;
    }

    public void applyEffect(EntityPlayer player, int id, int duration, byte level) {
        this.applyEffect(player, id, duration, level, 0);
    }

    public void applyEffect(EntityPlayer player, int id, int duration, byte level, int index) {
        if (player == null || id <= 0) {
            return;
        }
        ConcurrentHashMap<EffectKey, PlayerEffect> currentEffects = this.getPlayerEffects(player);
        CustomEffect parent = this.get(id, index);
        if (parent != null) {
            PlayerEffect playerEffect = new PlayerEffect(id, duration, level, index);
            currentEffects.put(new EffectKey(id, index), playerEffect);
            parent.onAdded(player, playerEffect);
        }
    }

    public void removeEffect(EntityPlayer player, int id) {
        this.removeEffect(player, id, 0, PlayerEvent.EffectEvent.ExpirationType.REMOVED);
    }

    public void removeEffect(EntityPlayer player, int id, PlayerEvent.EffectEvent.ExpirationType type) {
        this.removeEffect(player, id, 0, type);
    }

    public void removeEffect(EntityPlayer player, int id, int index) {
        this.removeEffect(player, id, index, PlayerEvent.EffectEvent.ExpirationType.REMOVED);
    }

    public void removeEffect(EntityPlayer player, int id, int index, PlayerEvent.EffectEvent.ExpirationType type) {
        EffectKey key;
        if (player == null || id <= 0) {
            return;
        }
        ConcurrentHashMap<EffectKey, PlayerEffect> currentEffects = this.getPlayerEffects(player);
        PlayerEffect effect = (PlayerEffect)currentEffects.get(key = new EffectKey(id, index));
        if (effect != null) {
            this.removeEffect(player, effect, type);
        }
    }

    public void decrementEffects(EntityPlayer player) {
        Iterator<PlayerEffect> iterator = this.getPlayerEffects(player).values().iterator();
        IPlayer iPlayer = NoppesUtilServer.getIPlayer(player);
        while (iterator.hasNext()) {
            PlayerEffect effect = iterator.next();
            if (effect == null) {
                iterator.remove();
                continue;
            }
            if (effect.duration == -100) continue;
            if (effect.duration <= 0) {
                CustomEffect parent = Instance.get(effect.id, effect.index);
                if (parent != null) {
                    parent.onRemoved(player, effect, PlayerEvent.EffectEvent.ExpirationType.RUN_OUT);
                }
                iterator.remove();
                continue;
            }
            --effect.duration;
        }
    }

    public File getMapDir() {
        File dir = CustomNpcs.getWorldSaveDirectory();
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    private void loadCustomEffects() {
        this.getCustomEffects().clear();
        File dir = this.getDir();
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.categoryManager.loadCategories(dir);
        this.loadEffectsFromDir(dir, 0);
        for (Map.Entry<Integer, Category> entry : this.categoryManager.getCategories().entrySet()) {
            File catDir = this.categoryManager.getCategoryDir(entry.getKey());
            this.loadEffectsFromDir(catDir, entry.getKey());
        }
        this.registerEffectMap(0, this.getCustomEffects());
        this.saveEffectLoadMap();
    }

    private void loadEffectsFromDir(File dir, int catId) {
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                CustomEffect effect = new CustomEffect();
                effect.readFromNBT(NBTJsonUtil.LoadFile(file));
                effect.name = file.getName().substring(0, file.getName().length() - 5);
                if (effect.id == -1) {
                    effect.id = this.getUnusedId();
                }
                int originalID = effect.id;
                int setID = effect.id;
                while (!(!this.bootOrder.containsKey(setID) && !this.getCustomEffects().containsKey(setID) || this.bootOrder.containsKey(setID) && this.bootOrder.get(setID).equalsIgnoreCase(effect.name))) {
                    ++setID;
                }
                effect.id = setID;
                if (originalID != setID) {
                    LogWriter.info("Found Custom Effect ID Mismatch: " + effect.name + ", New ID: " + setID);
                    effect.save();
                }
                this.getCustomEffects().put(effect.id, effect);
                this.categoryManager.registerItem(effect.id, catId);
            }
            catch (Exception e) {
                LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            }
        }
    }

    public HashMap<Integer, CustomEffect> getEffectMap(int index) {
        return this.indexMapper.get(index);
    }

    private File getDir() {
        return new File(CustomNpcs.getWorldSaveDirectory(), "customeffects");
    }

    public void saveEffectLoadMap() {
        try {
            File saveDir = this.getMapDir();
            File file = new File(saveDir, "customeffects.dat_new");
            File file1 = new File(saveDir, "customeffects.dat_old");
            File file2 = new File(saveDir, "customeffects.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)this.writeMapNBT(), (OutputStream)new FileOutputStream(file));
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
    }

    private NBTTagCompound writeMapNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList customEffectsList = new NBTTagList();
        for (Integer key : this.getCustomEffects().keySet()) {
            CustomEffect customEffect = this.getCustomEffects().get(key);
            if (customEffect.getName().isEmpty()) continue;
            NBTTagCompound effectCompound = new NBTTagCompound();
            effectCompound.func_74778_a("Name", customEffect.getName());
            effectCompound.func_74768_a("ID", key.intValue());
            customEffectsList.func_74742_a((NBTBase)effectCompound);
        }
        nbt.func_74782_a("CustomEffects", (NBTBase)customEffectsList);
        nbt.func_74768_a("lastID", this.lastUsedID);
        return nbt;
    }

    private void readCustomEffectMap() {
        this.bootOrder.clear();
        try {
            File file = new File(this.getMapDir(), "customeffects.dat");
            if (file.exists()) {
                this.loadCustomEffectMap(file);
            }
        }
        catch (Exception e) {
            try {
                File file = new File(this.getMapDir(), "customeffects.dat_old");
                if (file.exists()) {
                    this.loadCustomEffectMap(file);
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private void loadCustomEffectMap(File file) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file))));
        this.readCustomEffectMap(dis);
        dis.close();
    }

    private void readCustomEffectMap(DataInputStream stream) throws IOException {
        NBTTagCompound nbtCompound = CompressedStreamTools.func_74794_a((DataInputStream)stream);
        this.readMapNBT(nbtCompound);
    }

    private void readMapNBT(NBTTagCompound compound) {
        this.lastUsedID = compound.func_74762_e("lastID");
        NBTTagList list = compound.func_150295_c("CustomEffects", 10);
        if (list != null) {
            for (int i = 0; i < list.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound = list.func_150305_b(i);
                String effectName = nbttagcompound.func_74779_i("Name");
                Integer key = nbttagcompound.func_74762_e("ID");
                this.bootOrder.put(key, effectName);
            }
        }
    }

    public void deleteEffectFile(String prevName) {
        this.categoryManager.deleteFile(prevName + ".json");
    }

    @Override
    public ICustomEffect createEffect(String name) {
        if (this.has(name)) {
            return this.get(name);
        }
        CustomEffect effect = new CustomEffect();
        effect.name = name;
        if (effect.id == -1) {
            int id = this.getUnusedId();
            while (this.getCustomEffects().containsKey(id)) {
                id = this.getUnusedId();
            }
            effect.id = id;
        }
        this.getCustomEffects().put(effect.id, effect);
        return effect;
    }

    @Override
    public ICustomEffect getEffect(String name) {
        return this.get(name);
    }

    @Override
    public void deleteEffect(String name) {
        ICustomEffect effect = this.getEffect(name);
        if (effect != null) {
            this.delete(effect.getID());
        }
    }

    @Override
    public boolean hasEffect(IPlayer player, int id) {
        if (player == null || player.getMCEntity() == null) {
            return false;
        }
        return this.hasEffect((EntityPlayer)player.getMCEntity(), id);
    }

    @Override
    public boolean hasEffect(IPlayer player, ICustomEffect effect) {
        if (player == null || player.getMCEntity() == null) {
            return false;
        }
        return this.hasEffect((EntityPlayer)player.getMCEntity(), effect.getID(), effect.getIndex());
    }

    @Override
    public int getEffectDuration(IPlayer player, int id) {
        if (player == null || player.getMCEntity() == null) {
            return -1;
        }
        return this.getEffectDuration((EntityPlayer)player.getMCEntity(), id);
    }

    @Override
    public int getEffectDuration(IPlayer player, ICustomEffect effect) {
        if (effect == null) {
            return -1;
        }
        return this.getEffectDuration(player, effect.getID(), effect.getIndex());
    }

    @Override
    public void applyEffect(IPlayer player, int id, int duration, byte level) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        this.applyEffect((EntityPlayer)player.getMCEntity(), id, duration, level);
    }

    @Override
    public void applyEffect(IPlayer player, ICustomEffect effect, int duration, byte level) {
        this.applyEffect(player, effect.getID(), duration, level);
    }

    @Override
    public void removeEffect(IPlayer player, int id) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        this.removeEffect((EntityPlayer)player.getMCEntity(), id, PlayerEvent.EffectEvent.ExpirationType.REMOVED);
    }

    @Override
    public void removeEffect(IPlayer player, ICustomEffect effect) {
        this.removeEffect((EntityPlayer)player.getMCEntity(), (PlayerEffect)((Object)effect), PlayerEvent.EffectEvent.ExpirationType.REMOVED);
    }

    @Override
    public void clearEffects(IPlayer player) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        this.clearEffects((EntityPlayer)player);
    }

    @Override
    public void applyEffect(IPlayer player, int id, int duration, byte level, int index) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        this.applyEffect((EntityPlayer)player.getMCEntity(), id, duration, level, index);
    }

    @Override
    public void applyEffect(IPlayer player, ICustomEffect effect, int duration, byte level, int index) {
        this.applyEffect(player, effect.getID(), duration, level, index);
    }

    @Override
    public void removeEffect(IPlayer player, int id, int index) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        this.removeEffect((EntityPlayer)player.getMCEntity(), id, index, PlayerEvent.EffectEvent.ExpirationType.REMOVED);
    }

    @Override
    public void removeEffect(IPlayer player, ICustomEffect effect, int index) {
        this.removeEffect(player, effect.getID(), index);
    }

    @Override
    public void clearEffects(IPlayer player, int index) {
        if (player == null || player.getMCEntity() == null) {
            return;
        }
        EntityPlayer entity = (EntityPlayer)player.getMCEntity();
        ConcurrentHashMap<EffectKey, PlayerEffect> effects = this.getPlayerEffects(entity);
        Iterator<Map.Entry<EffectKey, PlayerEffect>> iterator = effects.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<EffectKey, PlayerEffect> entry = iterator.next();
            if (entry.getKey().getIndex() != index) continue;
            CustomEffect parent = this.get(entry.getKey().getId(), index);
            if (parent != null) {
                parent.onRemoved(entity, entry.getValue(), PlayerEvent.EffectEvent.ExpirationType.REMOVED);
            }
            iterator.remove();
        }
    }

    @Override
    public int getEffectDuration(IPlayer player, int id, int index) {
        if (player == null || player.getMCEntity() == null) {
            return -1;
        }
        return this.getEffectDuration((EntityPlayer)player.getMCEntity(), id, index);
    }

    @Override
    public int getEffectDuration(IPlayer player, ICustomEffect effect, int index) {
        return this.getEffectDuration(player, effect.getID(), index);
    }

    @Override
    public ICustomEffect getEffect(String name, int index) {
        return this.get(name, index);
    }

    @Override
    public ICustomEffect getEffect(int id, int index) {
        return this.get(id, index);
    }

    public Map<String, Integer> getCategoryScrollData() {
        return this.categoryManager.getCategoryScrollData();
    }

    public void moveItemToCategory(int itemId, int catId) {
        CustomEffect effect = this.getCustomEffects().get(itemId);
        if (effect == null) {
            return;
        }
        this.categoryManager.moveItem(itemId, effect.name + ".json", catId);
        this.saveEffectLoadMap();
    }

    public Map<String, Integer> getItemsByCategoryScrollData(int catId) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        List<Integer> itemIds = this.categoryManager.getItemsInCategory(catId, this.getCustomEffects().keySet());
        for (int itemId : itemIds) {
            CustomEffect effect = this.getCustomEffects().get(itemId);
            if (effect == null) continue;
            map.put(effect.name, effect.id);
        }
        return map;
    }

    public HashMap<String, HashSet<UUID>> getItemTagMapForCategory(int catId) {
        HashMap<String, HashSet<UUID>> tagMap = new HashMap<String, HashSet<UUID>>();
        List<Integer> itemIds = this.categoryManager.getItemsInCategory(catId, this.getCustomEffects().keySet());
        for (int itemId : itemIds) {
            CustomEffect effect = this.getCustomEffects().get(itemId);
            if (effect == null || effect.tagUUIDs.isEmpty()) continue;
            tagMap.put(effect.name, effect.tagUUIDs);
        }
        return tagMap;
    }

    @Override
    public ICustomEffect saveEffect(ICustomEffect customEffect) {
        if (customEffect.getID() < 0) {
            customEffect.setID(this.getUnusedId());
            while (this.has(customEffect.getName())) {
                customEffect.setName(customEffect.getName() + "_");
            }
        }
        while (this.hasOther(customEffect.getName(), customEffect.getID())) {
            customEffect.setName(customEffect.getName() + "_");
        }
        TagController.validateTagUUIDs(((CustomEffect)customEffect).tagUUIDs);
        this.getCustomEffects().remove(customEffect.getID());
        this.getCustomEffects().put(customEffect.getID(), (CustomEffect)customEffect);
        this.saveEffectLoadMap();
        File dir = this.categoryManager.getItemDir(customEffect.getID());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, customEffect.getName() + ".json_new");
        File file2 = new File(dir, customEffect.getName() + ".json");
        try {
            NBTTagCompound nbtTagCompound = ((CustomEffect)customEffect).writeToNBT(true);
            NBTJsonUtil.SaveFile(file, nbtTagCompound);
            if (file2.exists()) {
                file2.delete();
            }
            file.renameTo(file2);
            nbtTagCompound.func_82580_o("ScriptData");
            SyncController.syncUpdate(EnumSyncType.CUSTOM_EFFECTS, -1, nbtTagCompound);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        return this.getCustomEffects().get(customEffect.getID());
    }

    public CustomEffect cloneEffect(int originalId) {
        CustomEffect original = this.getCustomEffects().get(originalId);
        if (original == null) {
            return null;
        }
        int originalCatId = this.categoryManager.getItemCategory(originalId);
        NBTTagCompound nbt = original.writeToNBT(true);
        int newId = this.getUnusedId();
        nbt.func_74768_a("ID", newId);
        CustomEffect clone = new CustomEffect();
        clone.readFromNBT(nbt);
        String name = clone.getName();
        while (this.has(name)) {
            name = name + "_";
        }
        clone.name = name;
        if (originalCatId > 0) {
            this.categoryManager.registerItem(newId, originalCatId);
        }
        this.saveEffect(clone);
        return clone;
    }
}

