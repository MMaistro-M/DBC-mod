/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import kamkeel.npcs.controllers.SyncController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityVariant;
import kamkeel.npcs.controllers.data.ability.CustomAbility;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.conditions.ConditionHPThreshold;
import kamkeel.npcs.controllers.data.ability.conditions.ConditionHasEffect;
import kamkeel.npcs.controllers.data.ability.conditions.ConditionHitCount;
import kamkeel.npcs.controllers.data.ability.conditions.ConditionItem;
import kamkeel.npcs.controllers.data.ability.conditions.ConditionQuestCompleted;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.controllers.data.ability.data.effect.IEffectAction;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.controllers.data.ability.extender.IAbilityExtender;
import kamkeel.npcs.controllers.data.ability.gui.IAbilityFieldProvider;
import kamkeel.npcs.controllers.data.ability.gui.IChainedAbilityFieldProvider;
import kamkeel.npcs.controllers.data.ability.type.AbilityCharge;
import kamkeel.npcs.controllers.data.ability.type.AbilityCounter;
import kamkeel.npcs.controllers.data.ability.type.AbilityCutter;
import kamkeel.npcs.controllers.data.ability.type.AbilityDash;
import kamkeel.npcs.controllers.data.ability.type.AbilityDodge;
import kamkeel.npcs.controllers.data.ability.type.AbilityEffect;
import kamkeel.npcs.controllers.data.ability.type.AbilityGuard;
import kamkeel.npcs.controllers.data.ability.type.AbilityHazard;
import kamkeel.npcs.controllers.data.ability.type.AbilityHeavyHit;
import kamkeel.npcs.controllers.data.ability.type.AbilityShockwave;
import kamkeel.npcs.controllers.data.ability.type.AbilitySlam;
import kamkeel.npcs.controllers.data.ability.type.AbilityTeleport;
import kamkeel.npcs.controllers.data.ability.type.AbilityTrap;
import kamkeel.npcs.controllers.data.ability.type.AbilityVortex;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityBeam;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityDisc;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityDome;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityLaser;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityOrb;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityShield;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilitySweeper;
import kamkeel.npcs.controllers.data.ability.type.energy.AbilityWall;
import kamkeel.npcs.util.FileNameHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.ability.IChainedAbility;
import noppes.npcs.api.handler.IAbilityHandler;
import noppes.npcs.controllers.CategoryManager;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.TagController;
import noppes.npcs.controllers.data.AbilityScript;
import noppes.npcs.controllers.data.Category;
import noppes.npcs.controllers.data.ChainedAbilityScript;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.util.NBTJsonUtil;

public class AbilityController
implements IAbilityHandler {
    public static AbilityController Instance = new AbilityController();
    private final Map<String, Supplier<Ability>> abilityTypes = new LinkedHashMap<String, Supplier<Ability>>();
    private final Map<String, Ability> builtAbilities = new LinkedHashMap<String, Ability>();
    private final Map<String, Ability> customAbilities = new LinkedHashMap<String, Ability>();
    private final Map<String, Ability> customAbilitiesById = new LinkedHashMap<String, Ability>();
    public final HashMap<String, AbilityScript> abilityScriptHandlers = new HashMap();
    public final HashMap<String, ChainedAbilityScript> chainedAbilityScriptHandlers = new HashMap();
    private final Map<String, List<AbilityVariant>> externalVariants = new LinkedHashMap<String, List<AbilityVariant>>();
    private final List<IAbilityFieldProvider> fieldProviders = new ArrayList<IAbilityFieldProvider>();
    private final List<IChainedAbilityFieldProvider> chainedFieldProviders = new ArrayList<IChainedAbilityFieldProvider>();
    private final List<IAbilityExtender> extenders = new ArrayList<IAbilityExtender>();
    private final Map<String, Supplier<AbilityCondition>> conditionTypes = new HashMap<String, Supplier<AbilityCondition>>();
    private final List<Predicate<EntityPlayer>> flightCheckers = new ArrayList<Predicate<EntityPlayer>>();
    private final List<Predicate<EntityPlayer>> activationCheckers = new ArrayList<Predicate<EntityPlayer>>();
    private final Map<String, ChainedAbility> chainedAbilities = new LinkedHashMap<String, ChainedAbility>();
    private final Map<String, ChainedAbility> chainedAbilitiesById = new LinkedHashMap<String, ChainedAbility>();
    private int chainedAbilityRevision = 0;
    public final CategoryManager customAbilityCategories = new CategoryManager();
    public final CategoryManager chainedAbilityCategories = new CategoryManager();
    private final Map<String, Integer> customAbilityCatMap = new HashMap<String, Integer>();
    private final Map<String, Integer> chainedAbilityCatMap = new HashMap<String, Integer>();
    private final Map<String, IEffectAction> effectActions = new LinkedHashMap<String, IEffectAction>();
    private final Set<String> builtInTypeIds = new HashSet<String>();
    private int customAbilityRevision = 0;

    public AbilityController() {
        this.registerBuiltinTypes();
        this.registerBuiltinConditionTypes();
    }

    public void registerType(Supplier<Ability> factory) {
        Ability temp = factory.get();
        String typeId = temp.getTypeId();
        if (this.abilityTypes.containsKey(typeId)) {
            LogWriter.info("AbilityController: Overwriting type: " + typeId);
        }
        this.abilityTypes.put(typeId, factory);
        if (temp.isBuiltIn()) {
            this.builtInTypeIds.add(typeId);
        }
    }

    public Ability create(String typeId) {
        Supplier<Ability> factory = this.abilityTypes.get(typeId);
        return factory != null ? factory.get() : null;
    }

    public Ability fromNBT(NBTTagCompound nbt) {
        if (nbt == null || !nbt.func_74764_b("typeId")) {
            return null;
        }
        String typeId = nbt.func_74779_i("typeId");
        Supplier<Ability> factory = this.abilityTypes.get(typeId);
        if (factory == null) {
            LogWriter.info("AbilityController: Unknown ability type: " + typeId);
            return null;
        }
        Ability ability = factory.get();
        ability.readNBT(nbt);
        return ability;
    }

    private void registerBuiltinTypes() {
        this.registerType(CustomAbility::new);
        this.registerType(AbilitySlam::new);
        this.registerType(AbilityCharge::new);
        this.registerType(AbilityHeavyHit::new);
        this.registerType(AbilityCutter::new);
        this.registerType(AbilitySweeper::new);
        this.registerType(AbilityOrb::new);
        this.registerType(AbilityDisc::new);
        this.registerType(AbilityLaser::new);
        this.registerType(AbilityBeam::new);
        this.registerType(AbilityDash::new);
        this.registerType(AbilityTeleport::new);
        this.registerType(AbilityVortex::new);
        this.registerType(AbilityShockwave::new);
        this.registerType(AbilityGuard::new);
        this.registerType(AbilityCounter::new);
        this.registerType(AbilityDodge::new);
        this.registerType(AbilityEffect::new);
        this.registerType(AbilityHazard::new);
        this.registerType(AbilityTrap::new);
        this.registerType(AbilityDome::new);
        this.registerType(AbilityWall::new);
        this.registerType(AbilityShield::new);
    }

    private void registerBuiltinConditionTypes() {
        this.registerCondition(ConditionHPThreshold::new);
        this.registerCondition(ConditionHitCount::new);
        this.registerCondition(ConditionItem::new);
        this.registerCondition(ConditionQuestCompleted::new);
        this.registerCondition(ConditionHasEffect::new);
    }

    public void registerAbility(String name, Ability ability) {
        if (this.builtAbilities.containsKey(name)) {
            LogWriter.info("AbilityController: Overwriting built-in ability: " + name);
        }
        if (!ability.isBuiltIn()) {
            ability.setName(name);
        }
        this.builtAbilities.put(name, ability);
        LogWriter.info("Registered ability: " + name);
    }

    public Ability getAbility(String name) {
        return this.builtAbilities.get(name);
    }

    public Ability getAbilityByDisplayName(String displayName) {
        for (Ability ability : this.builtAbilities.values()) {
            if (!ability.getDisplayName().equals(displayName)) continue;
            return ability;
        }
        return null;
    }

    public Set<String> getAbilityNames() {
        return new LinkedHashSet<String>(this.builtAbilities.keySet());
    }

    public void load() {
        this.customAbilities.clear();
        this.customAbilitiesById.clear();
        this.abilityScriptHandlers.clear();
        this.customAbilityCatMap.clear();
        File dir = this.getDir();
        this.customAbilityCategories.loadCategories(dir, "chained");
        this.loadAbilitiesFromDir(dir, 0);
        for (Map.Entry<Integer, Category> entry : this.customAbilityCategories.getCategories().entrySet()) {
            File catDir = new File(dir, entry.getValue().title);
            this.loadAbilitiesFromDir(catDir, entry.getKey());
        }
        ++this.customAbilityRevision;
        LogWriter.info("Loaded " + this.customAbilities.size() + " custom abilities");
        this.loadChainedAbilities();
    }

    private void loadAbilitiesFromDir(File dir, int catId) {
        File[] files;
        File[] fileArray = files = dir.exists() ? dir.listFiles() : null;
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                File renamedFile;
                String filename = file.getName();
                String fileKey = filename.substring(0, filename.length() - 5);
                NBTTagCompound nbt = NBTJsonUtil.LoadFile(file);
                Ability ability = this.fromNBT(nbt);
                if (ability == null) continue;
                String name = FileNameHelper.sanitizeName(fileKey, "Ability");
                name = this.makeUniqueNameForLoad(this.customAbilities, dir, file, name, fileKey);
                boolean dirty = false;
                if (!name.equals(fileKey) && file.renameTo(renamedFile = new File(dir, name + ".json"))) {
                    file = renamedFile;
                    dirty = true;
                }
                ability.setName(name);
                String uuid = ability.getId();
                if (uuid == null || uuid.isEmpty()) {
                    uuid = UUID.randomUUID().toString();
                    ability.setId(uuid);
                    dirty = true;
                }
                if (dirty) {
                    NBTJsonUtil.SaveFile(file, ability.writeNBT(true));
                }
                this.customAbilities.put(name, ability);
                this.customAbilitiesById.put(uuid, ability);
                if (catId <= 0) continue;
                this.customAbilityCatMap.put(name, catId);
            }
            catch (Exception e) {
                LogWriter.error("Error loading custom ability: " + file.getAbsolutePath(), e);
            }
        }
    }

    private boolean nameFileExists(File dir, String name, File ignoredFile) {
        File existing = new File(dir, name + ".json");
        if (!existing.exists()) {
            return false;
        }
        return ignoredFile == null || !existing.getAbsolutePath().equals(ignoredFile.getAbsolutePath());
    }

    private String makeUniqueNameForLoad(Map<String, ?> loadedMap, File dir, File currentFile, String baseName, String selfKey) {
        String candidate = baseName;
        while (loadedMap.containsKey(candidate) && !candidate.equals(selfKey) || this.nameFileExists(dir, candidate, currentFile)) {
            candidate = candidate + "_";
        }
        return candidate;
    }

    private String makeUniqueNameForMap(Map<String, ?> map, String baseName, String selfName) {
        String candidate = baseName;
        while (map.containsKey(candidate) && (selfName == null || !candidate.equals(selfName))) {
            candidate = candidate + "_";
        }
        return candidate;
    }

    public boolean saveCustomAbility(Ability ability) {
        if (ability == null) {
            return false;
        }
        if (ability.isBuiltIn()) {
            return false;
        }
        String name = FileNameHelper.sanitizeName(ability.getName(), "Ability");
        ability.setName(name);
        String uuid = ability.getId();
        boolean isNew = uuid == null || uuid.isEmpty();
        int catId = 0;
        if (isNew) {
            uuid = UUID.randomUUID().toString();
            ability.setId(uuid);
            name = this.makeUniqueNameForMap(this.customAbilities, name, null);
            ability.setName(name);
        } else {
            Ability existing = this.customAbilitiesById.get(uuid);
            if (existing != null) {
                String oldName = existing.getName();
                catId = this.customAbilityCatMap.getOrDefault(oldName, 0);
                name = this.makeUniqueNameForMap(this.customAbilities, name, oldName);
                ability.setName(name);
                if (!oldName.equals(name)) {
                    File oldDir = this.getCustomAbilityDir(oldName);
                    File oldFile = new File(oldDir, oldName + ".json");
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                    this.customAbilities.remove(oldName);
                    this.customAbilityCatMap.remove(oldName);
                    if (catId > 0) {
                        this.customAbilityCatMap.put(name, catId);
                    }
                }
            } else {
                name = this.makeUniqueNameForMap(this.customAbilities, name, null);
                ability.setName(name);
            }
        }
        TagController.validateTagUUIDs(ability.getTagUUIDs());
        File dir = this.getCustomAbilityDir(name);
        File fileNew = new File(dir, name + ".json_new");
        File fileCurrent = new File(dir, name + ".json");
        try {
            NBTTagCompound nbt = ability.writeNBT(true);
            NBTJsonUtil.SaveFile(fileNew, nbt);
            if (fileCurrent.exists()) {
                fileCurrent.delete();
            }
            fileNew.renameTo(fileCurrent);
            if (fileNew.exists()) {
                fileNew.delete();
            }
            this.customAbilities.put(name, ability);
            this.customAbilitiesById.put(uuid, ability);
            ++this.customAbilityRevision;
            LogWriter.script("Saved custom ability: " + name + " [" + uuid + "]");
            SyncController.syncAllCustomAbilities();
            return true;
        }
        catch (Exception e) {
            LogWriter.error("Error saving custom ability: " + name, e);
            return false;
        }
    }

    public Ability cloneCustomAbility(String originalName) {
        Ability original = this.customAbilities.get(originalName);
        if (original == null || original.isBuiltIn()) {
            return null;
        }
        int originalCatId = this.customAbilityCatMap.getOrDefault(originalName, 0);
        NBTTagCompound nbt = original.writeNBT(true);
        nbt.func_74778_a("id", UUID.randomUUID().toString());
        Ability clone = this.fromNBT(nbt);
        if (clone == null) {
            return null;
        }
        String name = clone.getName();
        while (this.customAbilities.containsKey(name)) {
            name = name + "_";
        }
        clone.setName(name);
        this.saveCustomAbility(clone);
        if (originalCatId > 0) {
            this.customAbilityCatMap.put(clone.getName(), originalCatId);
        }
        return clone;
    }

    public boolean deleteCustomAbility(String name) {
        File dir;
        File file;
        String sanitized;
        if (name == null || name.isEmpty()) {
            return false;
        }
        Ability removed = this.customAbilities.remove(name);
        if (removed == null && !(sanitized = FileNameHelper.sanitizeTextInput(name)).equals(name) && (removed = this.customAbilities.remove(sanitized)) != null) {
            name = sanitized;
        }
        if (removed == null) {
            return false;
        }
        String uuid = removed.getId();
        if (uuid != null && !uuid.isEmpty()) {
            this.customAbilitiesById.remove(uuid);
            this.abilityScriptHandlers.remove(uuid);
        }
        if ((file = new File(dir = this.getCustomAbilityDir(name), name + ".json")).exists()) {
            file.delete();
        }
        this.customAbilityCatMap.remove(name);
        if (PlayerDataController.Instance != null) {
            ArrayList<PlayerData> snapshot = new ArrayList<PlayerData>(PlayerDataController.Instance.getAllPlayerData());
            for (PlayerData pData : snapshot) {
                if (pData.abilityData == null) continue;
                boolean changed = false;
                if (uuid != null && !uuid.isEmpty() && pData.abilityData.hasUnlockedAbility(uuid)) {
                    pData.abilityData.lockAbility(uuid);
                    changed = true;
                }
                if (pData.abilityData.hasUnlockedAbility(name)) {
                    pData.abilityData.lockAbility(name);
                    changed = true;
                }
                if (!changed) continue;
                pData.save();
            }
        }
        ++this.customAbilityRevision;
        LogWriter.script("Deleted custom ability: " + name);
        SyncController.syncAllCustomAbilities();
        return true;
    }

    public Ability getCustomAbility(String name) {
        Ability ability = this.customAbilities.get(name);
        if (ability != null) {
            return ability;
        }
        String sanitized = FileNameHelper.sanitizeTextInput(name);
        if (!sanitized.equals(name)) {
            return this.customAbilities.get(sanitized);
        }
        return null;
    }

    public Set<String> getCustomAbilityNames() {
        return new LinkedHashSet<String>(this.customAbilities.keySet());
    }

    public Map<String, Ability> getCustomAbilities() {
        return this.customAbilities;
    }

    public synchronized void setCustomAbilities(Map<String, Ability> synced) {
        this.customAbilities.clear();
        this.customAbilitiesById.clear();
        this.customAbilities.putAll(synced);
        for (Ability ability : synced.values()) {
            String uuid = ability.getId();
            if (uuid == null || uuid.isEmpty()) continue;
            this.customAbilitiesById.put(uuid, ability);
        }
        ++this.customAbilityRevision;
    }

    public int getCustomAbilityRevision() {
        return this.customAbilityRevision;
    }

    private File getDir() {
        File dir = new File(CustomNpcs.getWorldSaveDirectory(), "abilities");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private File getCustomAbilityDir(String name) {
        int catId = this.customAbilityCatMap.getOrDefault(name, 0);
        return this.customAbilityCategories.getCategoryDir(catId);
    }

    private File getChainedAbilityDir(String name) {
        int catId = this.chainedAbilityCatMap.getOrDefault(name, 0);
        return this.chainedAbilityCategories.getCategoryDir(catId);
    }

    public Map<String, Integer> getCustomAbilityCategoryScrollData() {
        return this.customAbilityCategories.getCategoryScrollData();
    }

    public Map<String, Integer> getCustomAbilityItemsByCategoryScrollData(int catId) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Map.Entry<String, Ability> entry : this.customAbilities.entrySet()) {
            int assignedCat = this.customAbilityCatMap.getOrDefault(entry.getKey(), 0);
            if (assignedCat != catId) continue;
            map.put(entry.getKey(), entry.getValue().getAllowedBy().ordinal());
        }
        return map;
    }

    public HashMap<String, HashSet<UUID>> getCustomAbilityTagMapForCategory(int catId) {
        HashMap<String, HashSet<UUID>> tagMap = new HashMap<String, HashSet<UUID>>();
        for (Map.Entry<String, Ability> entry : this.customAbilities.entrySet()) {
            int assignedCat = this.customAbilityCatMap.getOrDefault(entry.getKey(), 0);
            if (assignedCat != catId || entry.getValue().getTagUUIDs().isEmpty()) continue;
            tagMap.put(entry.getKey(), entry.getValue().getTagUUIDs());
        }
        return tagMap;
    }

    public void moveCustomAbilityToCategory(String name, int destCatId) {
        Ability ability = this.customAbilities.get(name);
        if (ability == null) {
            return;
        }
        int oldCatId = this.customAbilityCatMap.getOrDefault(name, 0);
        if (oldCatId == destCatId) {
            return;
        }
        File oldDir = this.customAbilityCategories.getCategoryDir(oldCatId);
        File newDir = this.customAbilityCategories.getCategoryDir(destCatId);
        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        File oldFile = new File(oldDir, name + ".json");
        File newFile = new File(newDir, name + ".json");
        if (oldFile.exists()) {
            oldFile.renameTo(newFile);
        }
        if (destCatId == 0) {
            this.customAbilityCatMap.remove(name);
        } else {
            this.customAbilityCatMap.put(name, destCatId);
        }
    }

    public Map<String, Integer> getChainedAbilityCategoryScrollData() {
        return this.chainedAbilityCategories.getCategoryScrollData();
    }

    public Map<String, Integer> getChainedAbilityItemsByCategoryScrollData(int catId) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Map.Entry<String, ChainedAbility> entry : this.chainedAbilities.entrySet()) {
            int assignedCat = this.chainedAbilityCatMap.getOrDefault(entry.getKey(), 0);
            if (assignedCat != catId) continue;
            map.put(entry.getKey(), entry.getValue().getAllowedBy().ordinal());
        }
        return map;
    }

    public void moveChainedAbilityToCategory(String name, int destCatId) {
        ChainedAbility chain = this.chainedAbilities.get(name);
        if (chain == null) {
            return;
        }
        int oldCatId = this.chainedAbilityCatMap.getOrDefault(name, 0);
        if (oldCatId == destCatId) {
            return;
        }
        File oldDir = this.chainedAbilityCategories.getCategoryDir(oldCatId);
        File newDir = this.chainedAbilityCategories.getCategoryDir(destCatId);
        if (!newDir.exists()) {
            newDir.mkdirs();
        }
        File oldFile = new File(oldDir, name + ".json");
        File newFile = new File(newDir, name + ".json");
        if (oldFile.exists()) {
            oldFile.renameTo(newFile);
        }
        if (destCatId == 0) {
            this.chainedAbilityCatMap.remove(name);
        } else {
            this.chainedAbilityCatMap.put(name, destCatId);
        }
    }

    private void loadChainedAbilities() {
        this.chainedAbilities.clear();
        this.chainedAbilitiesById.clear();
        this.chainedAbilityScriptHandlers.clear();
        this.chainedAbilityCatMap.clear();
        File dir = this.getChainedDir();
        this.chainedAbilityCategories.loadCategories(dir);
        this.loadChainedFromDir(dir, 0);
        for (Map.Entry<Integer, Category> entry : this.chainedAbilityCategories.getCategories().entrySet()) {
            File catDir = new File(dir, entry.getValue().title);
            this.loadChainedFromDir(catDir, entry.getKey());
        }
        ++this.chainedAbilityRevision;
        LogWriter.info("Loaded " + this.chainedAbilities.size() + " chained abilities");
    }

    private void loadChainedFromDir(File dir, int catId) {
        File[] files;
        File[] fileArray = files = dir.exists() ? dir.listFiles() : null;
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.isFile() || !file.getName().endsWith(".json")) continue;
            try {
                File renamedFile;
                String filename = file.getName();
                String fileKey = filename.substring(0, filename.length() - 5);
                NBTTagCompound nbt = NBTJsonUtil.LoadFile(file);
                ChainedAbility chain = new ChainedAbility();
                chain.readNBT(nbt);
                String name = FileNameHelper.sanitizeName(fileKey, "Chain");
                name = this.makeUniqueNameForLoad(this.chainedAbilities, dir, file, name, fileKey);
                boolean dirty = false;
                if (!name.equals(fileKey) && file.renameTo(renamedFile = new File(dir, name + ".json"))) {
                    file = renamedFile;
                    dirty = true;
                }
                chain.setName(name);
                String uuid = chain.getId();
                if (uuid == null || uuid.isEmpty()) {
                    uuid = UUID.randomUUID().toString();
                    chain.setId(uuid);
                    dirty = true;
                }
                if (dirty) {
                    NBTJsonUtil.SaveFile(file, chain.writeNBT(true));
                }
                this.chainedAbilities.put(name, chain);
                this.chainedAbilitiesById.put(uuid, chain);
                if (catId <= 0) continue;
                this.chainedAbilityCatMap.put(name, catId);
            }
            catch (Exception e) {
                LogWriter.error("Error loading chained ability: " + file.getAbsolutePath(), e);
            }
        }
    }

    public boolean saveChainedAbility(ChainedAbility chain) {
        boolean isNew;
        if (chain == null) {
            return false;
        }
        String name = FileNameHelper.sanitizeName(chain.getName(), "Chain");
        chain.setName(name);
        String uuid = chain.getId();
        boolean bl = isNew = uuid == null || uuid.isEmpty();
        if (isNew) {
            uuid = UUID.randomUUID().toString();
            chain.setId(uuid);
            name = this.makeUniqueNameForMap(this.chainedAbilities, name, null);
            chain.setName(name);
        } else {
            ChainedAbility existing = this.chainedAbilitiesById.get(uuid);
            if (existing != null) {
                String oldName = existing.getName();
                int catId = this.chainedAbilityCatMap.getOrDefault(oldName, 0);
                name = this.makeUniqueNameForMap(this.chainedAbilities, name, oldName);
                chain.setName(name);
                if (!oldName.equals(name)) {
                    File oldDir = this.getChainedAbilityDir(oldName);
                    File oldFile = new File(oldDir, oldName + ".json");
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                    this.chainedAbilities.remove(oldName);
                    this.chainedAbilityCatMap.remove(oldName);
                    if (catId > 0) {
                        this.chainedAbilityCatMap.put(name, catId);
                    }
                }
            } else {
                name = this.makeUniqueNameForMap(this.chainedAbilities, name, null);
                chain.setName(name);
            }
        }
        File dir = this.getChainedAbilityDir(name);
        File fileNew = new File(dir, name + ".json_new");
        File fileCurrent = new File(dir, name + ".json");
        try {
            NBTTagCompound nbt = chain.writeNBT(true);
            NBTJsonUtil.SaveFile(fileNew, nbt);
            if (fileCurrent.exists()) {
                fileCurrent.delete();
            }
            fileNew.renameTo(fileCurrent);
            if (fileNew.exists()) {
                fileNew.delete();
            }
            this.chainedAbilities.put(name, chain);
            this.chainedAbilitiesById.put(uuid, chain);
            ++this.chainedAbilityRevision;
            LogWriter.script("Saved chained ability: " + name + " [" + uuid + "]");
            SyncController.syncAllChainedAbilities();
            return true;
        }
        catch (Exception e) {
            LogWriter.error("Error saving chained ability: " + name, e);
            return false;
        }
    }

    public ChainedAbility cloneChainedAbility(String originalName) {
        ChainedAbility original = this.chainedAbilities.get(originalName);
        if (original == null) {
            return null;
        }
        int originalCatId = this.chainedAbilityCatMap.getOrDefault(originalName, 0);
        NBTTagCompound nbt = original.writeNBT(true);
        nbt.func_74778_a("Id", UUID.randomUUID().toString());
        ChainedAbility clone = new ChainedAbility();
        clone.readNBT(nbt);
        String name = clone.getName();
        while (this.chainedAbilities.containsKey(name)) {
            name = name + "_";
        }
        clone.setName(name);
        this.saveChainedAbility(clone);
        if (originalCatId > 0) {
            this.chainedAbilityCatMap.put(clone.getName(), originalCatId);
        }
        return clone;
    }

    public boolean deleteChainedAbility(String name) {
        File dir;
        File file;
        String sanitized;
        if (name == null || name.isEmpty()) {
            return false;
        }
        ChainedAbility removed = this.chainedAbilities.remove(name);
        if (removed == null && !(sanitized = FileNameHelper.sanitizeTextInput(name)).equals(name) && (removed = this.chainedAbilities.remove(sanitized)) != null) {
            name = sanitized;
        }
        if (removed == null) {
            return false;
        }
        String uuid = removed.getId();
        if (uuid != null && !uuid.isEmpty()) {
            this.chainedAbilitiesById.remove(uuid);
            this.chainedAbilityScriptHandlers.remove(uuid);
        }
        if ((file = new File(dir = this.getChainedAbilityDir(name), name + ".json")).exists()) {
            file.delete();
        }
        this.chainedAbilityCatMap.remove(name);
        if (PlayerDataController.Instance != null) {
            ArrayList<PlayerData> snapshot = new ArrayList<PlayerData>(PlayerDataController.Instance.getAllPlayerData());
            for (PlayerData pData : snapshot) {
                String chainNameKey;
                if (pData.abilityData == null) continue;
                String chainKey = "chain:" + (uuid != null && !uuid.isEmpty() ? uuid : name);
                if (pData.abilityData.hasUnlockedAbility(chainKey)) {
                    pData.abilityData.lockAbility(chainKey);
                }
                if ((chainNameKey = "chain:" + name).equals(chainKey) || !pData.abilityData.hasUnlockedAbility(chainNameKey)) continue;
                pData.abilityData.lockAbility(chainNameKey);
            }
        }
        ++this.chainedAbilityRevision;
        LogWriter.script("Deleted chained ability: " + name);
        SyncController.syncAllChainedAbilities();
        return true;
    }

    public ChainedAbility resolveChainedAbility(String key) {
        ChainedAbility sanitized;
        if (key == null || key.isEmpty()) {
            return null;
        }
        ChainedAbility byId = this.chainedAbilitiesById.get(key);
        if (byId != null) {
            return byId.deepCopy();
        }
        ChainedAbility chain = this.chainedAbilities.get(key);
        if (chain != null) {
            return chain.deepCopy();
        }
        for (Map.Entry<String, ChainedAbility> entry : this.chainedAbilities.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(key)) continue;
            return entry.getValue().deepCopy();
        }
        String sanitizedKey = FileNameHelper.sanitizeTextInput(key);
        if (!sanitizedKey.equals(key) && (sanitized = this.chainedAbilities.get(sanitizedKey)) != null) {
            return sanitized.deepCopy();
        }
        return null;
    }

    public ChainedAbility peekChainedAbility(String key) {
        ChainedAbility sanitized;
        if (key == null || key.isEmpty()) {
            return null;
        }
        ChainedAbility byId = this.chainedAbilitiesById.get(key);
        if (byId != null) {
            return byId;
        }
        ChainedAbility chain = this.chainedAbilities.get(key);
        if (chain != null) {
            return chain;
        }
        for (Map.Entry<String, ChainedAbility> entry : this.chainedAbilities.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(key)) continue;
            return entry.getValue();
        }
        String sanitizedKey = FileNameHelper.sanitizeTextInput(key);
        if (!sanitizedKey.equals(key) && (sanitized = this.chainedAbilities.get(sanitizedKey)) != null) {
            return sanitized;
        }
        return null;
    }

    public boolean canResolveChainedAbility(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        if (this.chainedAbilitiesById.containsKey(key)) {
            return true;
        }
        if (this.chainedAbilities.containsKey(key)) {
            return true;
        }
        for (String name : this.chainedAbilities.keySet()) {
            if (!name.equalsIgnoreCase(key)) continue;
            return true;
        }
        String sanitizedKey = FileNameHelper.sanitizeTextInput(key);
        return !sanitizedKey.equals(key) && this.chainedAbilities.containsKey(sanitizedKey);
    }

    @Override
    public ChainedAbility getChainedAbility(String name) {
        String sanitized;
        ChainedAbility chain = this.chainedAbilities.get(name);
        if (chain == null && !(sanitized = FileNameHelper.sanitizeTextInput(name)).equals(name)) {
            chain = this.chainedAbilities.get(sanitized);
        }
        return chain != null ? chain.deepCopy() : null;
    }

    public ChainedAbility getChainedAbilityByUUID(String uuid) {
        return this.chainedAbilitiesById.get(uuid);
    }

    public Set<String> getChainedAbilityNamesSet() {
        return new LinkedHashSet<String>(this.chainedAbilities.keySet());
    }

    public Map<String, ChainedAbility> getChainedAbilities() {
        return this.chainedAbilities;
    }

    @Override
    public String[] getChainedAbilityNames() {
        return this.chainedAbilities.keySet().toArray(new String[0]);
    }

    @Override
    public boolean hasChainedAbilityName(String name) {
        if (this.chainedAbilities.containsKey(name)) {
            return true;
        }
        String sanitized = FileNameHelper.sanitizeTextInput(name);
        return !sanitized.equals(name) && this.chainedAbilities.containsKey(sanitized);
    }

    @Override
    public boolean deleteChainedAbilityByName(String name) {
        return this.deleteChainedAbility(name);
    }

    @Override
    public boolean saveChainedAbility(IChainedAbility chain) {
        if (chain instanceof ChainedAbility) {
            return this.saveChainedAbility((ChainedAbility)chain);
        }
        return false;
    }

    public int getChainedAbilityRevision() {
        return this.chainedAbilityRevision;
    }

    public synchronized void setChainedAbilities(Map<String, ChainedAbility> synced) {
        this.chainedAbilities.clear();
        this.chainedAbilitiesById.clear();
        this.chainedAbilities.putAll(synced);
        for (ChainedAbility chain : synced.values()) {
            String uuid = chain.getId();
            if (uuid == null || uuid.isEmpty()) continue;
            this.chainedAbilitiesById.put(uuid, chain);
        }
        ++this.chainedAbilityRevision;
    }

    private File getChainedDir() {
        File dir = new File(CustomNpcs.getWorldSaveDirectory(), "abilities" + File.separator + "chained");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public Ability resolveAbility(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        Ability byUuid = this.customAbilitiesById.get(key);
        if (byUuid != null) {
            return byUuid.deepCopy();
        }
        Ability builtIn = this.builtAbilities.get(key);
        if (builtIn != null) {
            return builtIn.deepCopy();
        }
        String lowerKey = key.toLowerCase();
        for (Map.Entry<String, Ability> entry : this.builtAbilities.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(lowerKey)) continue;
            return entry.getValue().deepCopy();
        }
        for (Ability ability : this.builtAbilities.values()) {
            if (!key.equalsIgnoreCase(ability.getId())) continue;
            return ability.deepCopy();
        }
        Ability custom = this.customAbilities.get(key);
        if (custom != null) {
            return custom.deepCopy();
        }
        for (Map.Entry<String, Ability> entry : this.customAbilities.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(key)) continue;
            return entry.getValue().deepCopy();
        }
        return null;
    }

    public Set<String> getAbilityKeys() {
        String id;
        LinkedHashSet<String> keys = new LinkedHashSet<String>();
        for (Ability ability : this.builtAbilities.values()) {
            id = ability.getId();
            if (id == null || id.isEmpty()) continue;
            keys.add(id);
        }
        for (Ability ability : this.customAbilities.values()) {
            id = ability.getId();
            if (id == null || id.isEmpty()) continue;
            keys.add(id);
        }
        return keys;
    }

    public Set<String> getPlayerAbilityKeys() {
        String id;
        LinkedHashSet<String> keys = new LinkedHashSet<String>();
        for (Ability ability : this.builtAbilities.values()) {
            if (!ability.getAllowedBy().allowsPlayer() || (id = ability.getId()) == null || id.isEmpty()) continue;
            keys.add(id);
        }
        for (Ability ability : this.customAbilities.values()) {
            if (!ability.getAllowedBy().allowsPlayer() || (id = ability.getId()) == null || id.isEmpty()) continue;
            keys.add(id);
        }
        return keys;
    }

    public boolean hasAbility(String key) {
        return this.builtAbilities.containsKey(key) || this.customAbilities.containsKey(key) || this.customAbilitiesById.containsKey(key);
    }

    public Ability getCustomAbilityByUUID(String uuid) {
        return this.customAbilitiesById.get(uuid);
    }

    public Ability getCustomAbilityByName(String name) {
        return this.customAbilities.get(name);
    }

    public Ability peekAbility(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        Ability byUuid = this.customAbilitiesById.get(key);
        if (byUuid != null) {
            return byUuid;
        }
        Ability builtIn = this.builtAbilities.get(key);
        if (builtIn != null) {
            return builtIn;
        }
        for (Map.Entry<String, Ability> entry : this.builtAbilities.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(key)) continue;
            return entry.getValue();
        }
        for (Ability ability : this.builtAbilities.values()) {
            if (!key.equalsIgnoreCase(ability.getId())) continue;
            return ability;
        }
        Ability custom = this.customAbilities.get(key);
        if (custom != null) {
            return custom;
        }
        for (Map.Entry<String, Ability> entry : this.customAbilities.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase(key)) continue;
            return entry.getValue();
        }
        return null;
    }

    public boolean canResolveAbility(String key) {
        if (key == null || key.isEmpty()) {
            return false;
        }
        if (this.customAbilitiesById.containsKey(key)) {
            return true;
        }
        if (this.builtAbilities.containsKey(key)) {
            return true;
        }
        for (Ability ability : this.builtAbilities.values()) {
            if (!key.equals(ability.getId())) continue;
            return true;
        }
        return this.customAbilities.containsKey(key);
    }

    public IAbilityAction resolveAction(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        Ability ability = this.resolveAbility(key);
        if (ability != null) {
            return ability;
        }
        return this.resolveChainedAbility(key);
    }

    public boolean canResolveAction(String key) {
        if (this.canResolveAbility(key)) {
            return true;
        }
        return this.canResolveChainedAbility(key);
    }

    public void registerVariant(String typeId, AbilityVariant variant) {
        this.externalVariants.computeIfAbsent(typeId, k -> new ArrayList()).add(variant);
    }

    public List<AbilityVariant> getVariantsForType(String typeId) {
        List<AbilityVariant> ext;
        ArrayList<AbilityVariant> result = new ArrayList<AbilityVariant>();
        Ability temp = this.create(typeId);
        if (temp != null) {
            result.addAll(temp.getVariants());
        }
        if ((ext = this.externalVariants.get(typeId)) != null) {
            if (result.isEmpty()) {
                result.add(new AbilityVariant("ability.variant.base", a -> {}));
            }
            result.addAll(ext);
        }
        return result;
    }

    public void registerFieldProvider(IAbilityFieldProvider provider) {
        this.fieldProviders.add(provider);
    }

    public List<IAbilityFieldProvider> getFieldProviders() {
        return this.fieldProviders;
    }

    public void registerChainedFieldProvider(IChainedAbilityFieldProvider provider) {
        this.chainedFieldProviders.add(provider);
    }

    public List<IChainedAbilityFieldProvider> getChainedFieldProviders() {
        return this.chainedFieldProviders;
    }

    public void registerExtender(IAbilityExtender extender) {
        this.extenders.add(extender);
    }

    public List<IAbilityExtender> getExtenders() {
        return this.extenders;
    }

    public void registerCondition(Supplier<AbilityCondition> conditionFactory) {
        AbilityCondition temp = conditionFactory.get();
        String typeId = temp.getTypeId();
        if (this.conditionTypes.containsKey(typeId)) {
            LogWriter.info("AbilityController: Overwriting Condition type: " + typeId);
        }
        this.conditionTypes.put(typeId, conditionFactory);
    }

    public Supplier<AbilityCondition> getConditionType(String key) {
        return this.conditionTypes.get(key);
    }

    public String[] getConditionTypes() {
        return this.conditionTypes.keySet().toArray(new String[0]);
    }

    public List<String> getConditionNamespaces() {
        LinkedHashSet<String> seen = new LinkedHashSet<String>();
        for (String typeId : this.conditionTypes.keySet()) {
            String[] parts = typeId.split("\\.", 3);
            if (parts.length < 2) continue;
            seen.add(parts[1]);
        }
        return new ArrayList<String>(seen);
    }

    public String[] getConditionTypesByNamespace(String namespace) {
        String prefix = "condition." + namespace + ".";
        ArrayList<String> result = new ArrayList<String>();
        for (String typeId : this.conditionTypes.keySet()) {
            if (!typeId.startsWith(prefix)) continue;
            result.add(typeId);
        }
        return result.toArray(new String[0]);
    }

    public void registerFlightChecker(Predicate<EntityPlayer> checker) {
        this.flightCheckers.add(checker);
    }

    public boolean isPlayerFlying(EntityPlayer player) {
        if (player.field_71075_bZ.field_75100_b) {
            return true;
        }
        for (Predicate<EntityPlayer> checker : this.flightCheckers) {
            if (!checker.test(player)) continue;
            return true;
        }
        return false;
    }

    public void registerActivationChecker(Predicate<EntityPlayer> checker) {
        this.activationCheckers.add(checker);
    }

    public boolean canPlayerActivate(EntityPlayer player) {
        for (Predicate<EntityPlayer> checker : this.activationCheckers) {
            if (checker.test(player)) continue;
            return false;
        }
        return true;
    }

    public boolean fireOnAbilityStart(Ability ability, EntityLivingBase caster, EntityLivingBase target) {
        for (IAbilityExtender ext : this.extenders) {
            if (ext.onAbilityStart(ability, caster, target)) continue;
            return false;
        }
        return true;
    }

    public boolean fireOnAbilityTick(Ability ability, EntityLivingBase caster, EntityLivingBase target, AbilityPhase phase, int tick) {
        for (IAbilityExtender ext : this.extenders) {
            if (ext.onAbilityTick(ability, caster, target, phase, tick)) continue;
            return false;
        }
        return true;
    }

    public void fireOnAbilityComplete(Ability ability, EntityLivingBase caster, EntityLivingBase target, boolean interrupted) {
        for (IAbilityExtender ext : this.extenders) {
            ext.onAbilityComplete(ability, caster, target, interrupted);
        }
    }

    public boolean fireOnAbilityDamage(Ability ability, EntityLivingBase caster, EntityLivingBase target, float damage, float knockback, float knockbackUp, double knockbackDirX, double knockbackDirZ, float damageMultiplier) {
        for (IAbilityExtender ext : this.extenders) {
            if (!ext.onAbilityDamage(ability, caster, target, damage, knockback, knockbackUp, knockbackDirX, knockbackDirZ, damageMultiplier)) continue;
            return true;
        }
        return false;
    }

    public float fireModifyProjectileDamage(Ability ability, EntityLivingBase caster, float baseDamage) {
        float damage = baseDamage;
        for (IAbilityExtender ext : this.extenders) {
            damage = ext.modifyProjectileDamage(ability, caster, damage);
        }
        return damage;
    }

    public float fireModifyBarrierHealth(Ability ability, EntityLivingBase caster, float baseHealth) {
        float health = baseHealth;
        for (IAbilityExtender ext : this.extenders) {
            health = ext.modifyBarrierHealth(ability, caster, health);
        }
        return health;
    }

    public boolean fireOnAbilityHeal(Ability ability, EntityLivingBase caster, EntityLivingBase target, float healAmount) {
        for (IAbilityExtender ext : this.extenders) {
            if (!ext.onAbilityHeal(ability, caster, target, healAmount)) continue;
            return true;
        }
        return false;
    }

    public Boolean fireCheckConditions(AbilityCondition condition, EntityLivingBase caster, EntityLivingBase target) {
        for (IAbilityExtender ext : this.extenders) {
            Boolean result = ext.onCheckCondition(condition, caster, target);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    public Boolean fireCheckConditionsForPlayer(AbilityCondition condition, EntityLivingBase player) {
        for (IAbilityExtender ext : this.extenders) {
            Boolean result = ext.onCheckConditionForPlayer(condition, player);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    @Override
    public String[] getTypes() {
        return this.abilityTypes.keySet().toArray(new String[0]);
    }

    public boolean isBuiltInType(String typeId) {
        return this.builtInTypeIds.contains(typeId);
    }

    public boolean isConcurrentCapableType(String typeId) {
        Supplier<Ability> factory = this.abilityTypes.get(typeId);
        if (factory == null) {
            return false;
        }
        return factory.get().isConcurrentCapable();
    }

    public void registerEffectAction(IEffectAction action) {
        if (action == null || action.getId() == null) {
            return;
        }
        this.effectActions.put(action.getId(), action);
    }

    public IEffectAction getEffectAction(String id) {
        return this.effectActions.get(id);
    }

    public String[] getEffectActionIds() {
        return this.effectActions.keySet().toArray(new String[0]);
    }

    public Collection<IEffectAction> getEffectActions() {
        return this.effectActions.values();
    }

    public boolean hasEffectActions() {
        return !this.effectActions.isEmpty();
    }

    @Override
    public boolean hasType(String typeId) {
        return this.abilityTypes.containsKey(typeId);
    }

    public boolean isAllowedByPlayer(String typeId) {
        if (this.abilityTypes.containsKey(typeId)) {
            Ability temp = this.abilityTypes.get(typeId).get();
            return temp.getAllowedBy().allowsPlayer();
        }
        return false;
    }

    public boolean isAllowedByNPC(String typeId) {
        if (this.abilityTypes.containsKey(typeId)) {
            Ability temp = this.abilityTypes.get(typeId).get();
            return temp.getAllowedBy().allowsNpc();
        }
        return false;
    }

    public boolean isAllowedByBoth(String typeId) {
        if (this.abilityTypes.containsKey(typeId)) {
            Ability temp = this.abilityTypes.get(typeId).get();
            return temp.getAllowedBy() == UserType.BOTH;
        }
        return false;
    }

    @Override
    public String[] getAbilityNameArray() {
        return this.builtAbilities.keySet().toArray(new String[0]);
    }

    @Override
    public boolean hasAbilityName(String name) {
        return this.builtAbilities.containsKey(name);
    }

    @Override
    public String[] getCustomAbilityNameArray() {
        return this.customAbilities.keySet().toArray(new String[0]);
    }

    @Override
    public boolean hasCustomAbilityName(String name) {
        if (this.customAbilities.containsKey(name)) {
            return true;
        }
        String sanitized = FileNameHelper.sanitizeTextInput(name);
        return !sanitized.equals(name) && this.customAbilities.containsKey(sanitized);
    }

    @Override
    public boolean deleteCustomAbilityByName(String name) {
        return this.deleteCustomAbility(name);
    }
}

