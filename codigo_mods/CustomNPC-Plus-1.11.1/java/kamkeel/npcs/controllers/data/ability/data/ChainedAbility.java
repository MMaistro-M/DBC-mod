/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package kamkeel.npcs.controllers.data.ability.data;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.conditions.AbilityCondition;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.controllers.data.ability.data.entry.ChainedAbilityEntry;
import kamkeel.npcs.controllers.data.ability.enums.UserType;
import kamkeel.npcs.util.FileNameHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.ability.IChainedAbility;
import noppes.npcs.controllers.data.ChainedAbilityScript;
import noppes.npcs.controllers.data.IScriptUnit;

public class ChainedAbility
implements IChainedAbility,
IAbilityAction {
    private String id = "";
    private String name = "";
    private String displayName = "";
    private boolean enabled = true;
    private int weight = 10;
    private boolean windUpAll = true;
    private int cooldownTicks = 100;
    private float minRange = 0.0f;
    private float maxRange = 20.0f;
    private List<AbilityCondition> conditions = new ArrayList<AbilityCondition>();
    private List<ChainedAbilityEntry> entries = new ArrayList<ChainedAbilityEntry>();
    private NBTTagCompound customData = new NBTTagCompound();
    private transient ChainedAbilityScript instanceScript;

    public ChainedAbility() {
    }

    public ChainedAbility(String name) {
        this.setName(name);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id != null ? id : "";
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        String fallback = this.name != null && !this.name.isEmpty() ? this.name : "Chain";
        this.name = FileNameHelper.sanitizeName(name, fallback);
    }

    public String getDisplayName() {
        String result = this.displayName != null && !this.displayName.isEmpty() ? this.displayName : FileNameHelper.toDisplayName(this.name);
        return result != null ? result.replaceAll("&([0-9a-fk-or])", "\u00a7$1") : "";
    }

    public String getRawDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName != null ? displayName : "";
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = Math.max(1, weight);
    }

    @Override
    public boolean isWindUpAll() {
        return this.windUpAll;
    }

    public void setWindUpAll(boolean windUpAll) {
        this.windUpAll = windUpAll;
    }

    @Override
    public int getCooldownTicks() {
        return this.cooldownTicks;
    }

    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    @Override
    public float getMinRange() {
        return this.minRange;
    }

    public void setMinRange(float minRange) {
        this.minRange = Math.max(0.0f, minRange);
    }

    @Override
    public float getMaxRange() {
        return this.maxRange;
    }

    public void setMaxRange(float maxRange) {
        this.maxRange = Math.max(0.0f, maxRange);
    }

    @Override
    public UserType getAllowedBy() {
        if (this.entries.isEmpty()) {
            return UserType.BOTH;
        }
        boolean allAllowPlayer = true;
        boolean allAllowNpc = true;
        for (ChainedAbilityEntry entry : this.entries) {
            Ability a = entry.resolve();
            if (a == null) continue;
            UserType ut = a.getAllowedBy();
            if (!ut.allowsPlayer()) {
                allAllowPlayer = false;
            }
            if (ut.allowsNpc()) continue;
            allAllowNpc = false;
        }
        if (allAllowPlayer && allAllowNpc) {
            return UserType.BOTH;
        }
        if (allAllowPlayer) {
            return UserType.PLAYER_ONLY;
        }
        if (allAllowNpc) {
            return UserType.NPC_ONLY;
        }
        return UserType.NONE;
    }

    @Override
    public List<AbilityCondition> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<AbilityCondition> conditions) {
        this.conditions = conditions != null ? conditions : new ArrayList();
    }

    public List<ChainedAbilityEntry> getEntries() {
        return this.entries;
    }

    public void setEntries(List<ChainedAbilityEntry> entries) {
        this.entries = entries != null ? entries : new ArrayList();
    }

    public NBTTagCompound getCustomData() {
        return this.customData;
    }

    @Override
    public int getEntryCount() {
        return this.entries.size();
    }

    @Override
    public String getEntryReference(int index) {
        if (index < 0 || index >= this.entries.size()) {
            return null;
        }
        ChainedAbilityEntry entry = this.entries.get(index);
        if (entry.isInline()) {
            Ability a = entry.getInlineAbility();
            return a != null ? a.getName() : "";
        }
        return entry.getAbilityReference();
    }

    @Override
    public boolean isEntryInline(int index) {
        if (index < 0 || index >= this.entries.size()) {
            return false;
        }
        return this.entries.get(index).isInline();
    }

    @Override
    public int getEntryDelay(int index) {
        if (index < 0 || index >= this.entries.size()) {
            return 0;
        }
        return this.entries.get(index).getDelayTicks();
    }

    public void addEntry(ChainedAbilityEntry entry) {
        if (entry != null) {
            this.entries.add(entry);
        }
    }

    public void addEntry(String abilityReference, int delayTicks) {
        this.entries.add(new ChainedAbilityEntry(abilityReference, delayTicks));
    }

    public void removeEntry(int index) {
        if (index >= 0 && index < this.entries.size()) {
            this.entries.remove(index);
        }
    }

    public void moveEntryUp(int index) {
        if (index > 0 && index < this.entries.size()) {
            ChainedAbilityEntry entry = this.entries.remove(index);
            this.entries.add(index - 1, entry);
        }
    }

    public void moveEntryDown(int index) {
        if (index >= 0 && index < this.entries.size() - 1) {
            ChainedAbilityEntry entry = this.entries.remove(index);
            this.entries.add(index + 1, entry);
        }
    }

    @Override
    public boolean checkConditions(EntityLivingBase caster, EntityLivingBase target) {
        for (AbilityCondition c : this.conditions) {
            Boolean extendedCondition;
            if (!c.getUserType().allowsNpc() || !((extendedCondition = AbilityController.Instance.fireCheckConditions(c, caster, target)) != null ? extendedCondition == false : !c.check(caster, target))) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean checkConditionsForPlayer(EntityLivingBase caster) {
        for (AbilityCondition c : this.conditions) {
            Boolean extendedCondition;
            if (!c.getUserType().allowsPlayer() || c.requiresTarget() || !((extendedCondition = AbilityController.Instance.fireCheckConditionsForPlayer(c, caster)) != null ? extendedCondition == false : !c.check(caster, null))) continue;
            return false;
        }
        return true;
    }

    public List<String> validate() {
        AbilityController controller;
        ArrayList<String> errors = new ArrayList<String>();
        if (this.name == null || this.name.isEmpty()) {
            errors.add("Name is required");
        }
        if (this.entries.isEmpty()) {
            errors.add("At least one ability entry is required");
        }
        if ((controller = AbilityController.Instance) != null) {
            for (int i = 0; i < this.entries.size(); ++i) {
                ChainedAbilityEntry entry = this.entries.get(i);
                if (entry.isInline()) {
                    if (entry.getInlineAbility() != null) continue;
                    errors.add("Entry " + (i + 1) + ": inline ability is null");
                    continue;
                }
                String ref = entry.getAbilityReference();
                if (ref == null || ref.isEmpty()) {
                    errors.add("Entry " + (i + 1) + ": ability reference is empty");
                    continue;
                }
                if (controller.canResolveAbility(ref)) continue;
                errors.add("Entry " + (i + 1) + ": cannot resolve ability '" + ref + "'");
            }
        }
        return errors;
    }

    public ChainedAbility deepCopy() {
        ChainedAbility copy = new ChainedAbility();
        copy.readNBT(this.writeNBT(false));
        return copy;
    }

    @Override
    public boolean isChain() {
        return true;
    }

    @Override
    public IAbilityAction deepCopyAction() {
        return this.deepCopy();
    }

    @Override
    public NBTTagCompound writeNBT(boolean saveScripts) {
        ChainedAbilityScript chainedAbilityScript;
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74778_a("Id", this.id);
        nbt.func_74778_a("Name", this.name);
        nbt.func_74778_a("DisplayName", this.displayName);
        nbt.func_74757_a("Enabled", this.enabled);
        nbt.func_74768_a("Weight", this.weight);
        nbt.func_74757_a("WindUpAll", this.windUpAll);
        nbt.func_74768_a("CooldownTicks", this.cooldownTicks);
        nbt.func_74776_a("MinRange", this.minRange);
        nbt.func_74776_a("MaxRange", this.maxRange);
        NBTTagList condList = new NBTTagList();
        for (AbilityCondition abilityCondition : this.conditions) {
            condList.func_74742_a((NBTBase)abilityCondition.writeNBT());
        }
        nbt.func_74782_a("Conditions", (NBTBase)condList);
        NBTTagList entryList = new NBTTagList();
        for (ChainedAbilityEntry entry : this.entries) {
            entryList.func_74742_a((NBTBase)entry.writeNBT(saveScripts));
        }
        nbt.func_74782_a("Entries", (NBTBase)entryList);
        nbt.func_74782_a("customData", (NBTBase)((NBTTagCompound)this.customData.func_74737_b()));
        if (saveScripts && (chainedAbilityScript = this.getScriptHandler()) != null) {
            NBTTagCompound scriptData = new NBTTagCompound();
            chainedAbilityScript.writeToNBT(scriptData);
            nbt.func_74782_a("ScriptData", (NBTBase)scriptData);
        }
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        this.id = nbt.func_74779_i("Id");
        this.setName(nbt.func_74779_i("Name"));
        this.displayName = nbt.func_74779_i("DisplayName");
        this.enabled = !nbt.func_74764_b("Enabled") || nbt.func_74767_n("Enabled");
        this.weight = nbt.func_74764_b("Weight") ? nbt.func_74762_e("Weight") : 10;
        this.windUpAll = !nbt.func_74764_b("WindUpAll") || nbt.func_74767_n("WindUpAll");
        this.cooldownTicks = nbt.func_74764_b("CooldownTicks") ? nbt.func_74762_e("CooldownTicks") : 100;
        this.minRange = nbt.func_74760_g("MinRange");
        this.maxRange = nbt.func_74764_b("MaxRange") ? nbt.func_74760_g("MaxRange") : 20.0f;
        this.conditions.clear();
        NBTTagList condList = nbt.func_150295_c("Conditions", 10);
        for (int i = 0; i < condList.func_74745_c(); ++i) {
            AbilityCondition c = AbilityCondition.fromNBT(condList.func_150305_b(i));
            if (c == null) continue;
            this.conditions.add(c);
        }
        this.entries.clear();
        NBTTagList entryList = nbt.func_150295_c("Entries", 10);
        for (int i = 0; i < entryList.func_74745_c(); ++i) {
            ChainedAbilityEntry entry = ChainedAbilityEntry.fromNBT(entryList.func_150305_b(i));
            if (entry == null) continue;
            this.entries.add(entry);
        }
        this.customData = (NBTTagCompound)nbt.func_74775_l("customData").func_74737_b();
        if (nbt.func_150297_b("ScriptData", 10) && this.getScriptHandler() == null) {
            ChainedAbilityScript handler = new ChainedAbilityScript(this.id);
            handler.readFromNBT(nbt.func_74775_l("ScriptData"));
            this.setScriptHandler(handler);
        }
    }

    public ChainedAbilityScript getScriptHandler() {
        return AbilityController.Instance.chainedAbilityScriptHandlers.get(this.id);
    }

    public void setScriptHandler(ChainedAbilityScript handler) {
        AbilityController.Instance.chainedAbilityScriptHandlers.put(this.id, handler);
    }

    public ChainedAbilityScript getOrCreateScriptHandler() {
        ChainedAbilityScript handler = this.getScriptHandler();
        if (handler == null) {
            handler = new ChainedAbilityScript(this.id);
            AbilityController.Instance.chainedAbilityScriptHandlers.put(this.id, handler);
        }
        return handler;
    }

    public ChainedAbilityScript getOrCreateInstanceScript() {
        if (this.instanceScript != null) {
            return this.instanceScript;
        }
        ChainedAbilityScript template = this.getScriptHandler();
        if (template == null || !template.getEnabled() || template.container == null) {
            return null;
        }
        this.instanceScript = new ChainedAbilityScript(this.id);
        this.instanceScript.setLanguage(template.getLanguage());
        this.instanceScript.setEnabled(true);
        IScriptUnit clone = template.container.createInstanceScope(this.instanceScript);
        this.instanceScript.addScriptUnit(clone);
        return this.instanceScript;
    }

    public void clearInstanceScript() {
        this.instanceScript = null;
    }
}

