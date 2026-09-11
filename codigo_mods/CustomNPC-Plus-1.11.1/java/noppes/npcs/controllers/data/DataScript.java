/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.script.ScriptEngine;
import kamkeel.npcs.network.packets.request.script.EventScriptPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noppes.npcs.CustomNpcs;
import noppes.npcs.EventHooks;
import noppes.npcs.LogWriter;
import noppes.npcs.NBTTags;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.IHookDefinition;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.janino.EventJaninoScript;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptWorld;
import noppes.npcs.scripted.constants.EntityType;
import noppes.npcs.scripted.constants.JobType;
import noppes.npcs.scripted.constants.RoleType;
import noppes.npcs.scripted.entity.ScriptNpc;

public class DataScript
implements IScriptHandlerPacket {
    public List<IScriptUnit> eventScripts = new ArrayList<IScriptUnit>();
    private HashMap<EnumScriptType, ScriptContainer> scripts = new HashMap();
    private static final EntityType entities = new EntityType();
    private static final JobType jobs = new JobType();
    private static final RoleType roles = new RoleType();
    public String scriptLanguage = "ECMAScript";
    private EntityNPCInterface npc;
    public boolean enabled = false;
    private long lastGlobalsVersion = -1L;
    public ICustomNpc dummyNpc;
    public IWorld dummyWorld;
    public boolean clientNeedsUpdate = false;
    public boolean aiNeedsUpdate = false;
    public boolean hasInited = false;

    public DataScript(EntityNPCInterface npc) {
        for (int i = 0; i < 15; ++i) {
            this.setNPCScript(i, new ScriptContainer(this));
        }
        this.npc = npc;
        if (npc.wrappedNPC == null) {
            npc.wrappedNPC = new ScriptNpc<EntityNPCInterface>(this.npc);
        }
        this.dummyNpc = npc.wrappedNPC;
        if (npc.field_70170_p instanceof WorldServer) {
            this.dummyWorld = NpcAPI.Instance().getIWorld((World)((WorldServer)npc.field_70170_p));
        }
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.scripts = this.readScript(compound.func_150295_c("ScriptsContainers", 10));
        this.scriptLanguage = compound.func_74779_i("ScriptLanguage");
        if (this.scriptLanguage == null || this.scriptLanguage.isEmpty()) {
            this.scriptLanguage = !ScriptController.Instance.languages.isEmpty() ? (String)ScriptController.Instance.languages.keySet().toArray()[0] : "ECMAScript";
        }
        this.enabled = compound.func_74767_n("ScriptEnabled");
    }

    public void readEventsFromNBT(NBTTagCompound compound) {
        this.eventScripts = NBTTags.GetScript(compound, this);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74782_a("ScriptsContainers", (NBTBase)this.writeScript(this.scripts));
        compound.func_74778_a("ScriptLanguage", this.scriptLanguage);
        compound.func_74757_a("ScriptEnabled", this.enabled);
        return compound;
    }

    public NBTTagCompound writeEventsToNBT(NBTTagCompound compound) {
        compound.func_74768_a("TotalScripts", this.eventScripts.size());
        for (int i = 0; i < this.eventScripts.size(); ++i) {
            compound.func_74782_a("Tab" + i, (NBTBase)this.eventScripts.get(i).writeToNBT(new NBTTagCompound()));
        }
        return compound;
    }

    private HashMap<EnumScriptType, ScriptContainer> readScript(NBTTagList list) {
        int i;
        HashMap<EnumScriptType, ScriptContainer> scripts = new HashMap<EnumScriptType, ScriptContainer>();
        for (i = 0; i < 15; ++i) {
            scripts.put(EnumScriptType.values()[i], new ScriptContainer(this));
        }
        for (i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound compoundd = list.func_150305_b(i);
            ScriptContainer script = new ScriptContainer(this);
            script.readFromNBT(compoundd);
            if (!script.hasCode() && !this.npc.isRemote()) continue;
            scripts.put(EnumScriptType.values()[compoundd.func_74762_e("Type")], script);
        }
        return scripts;
    }

    private NBTTagList writeScript(HashMap<EnumScriptType, ScriptContainer> scripts) {
        NBTTagList list = new NBTTagList();
        for (Map.Entry<EnumScriptType, ScriptContainer> entry : scripts.entrySet()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            ScriptContainer container = entry.getValue();
            if (!container.hasCode()) continue;
            tagCompound.func_74768_a("Type", entry.getKey().ordinal());
            container.writeToNBT(tagCompound);
            list.func_74742_a((NBTBase)tagCompound);
        }
        return list;
    }

    public boolean callScript(EnumScriptType type, Event event, Object ... obs) {
        if (this.aiNeedsUpdate) {
            this.npc.updateAI = true;
            this.aiNeedsUpdate = false;
        }
        if (this.clientNeedsUpdate) {
            this.npc.updateClient = true;
            this.clientNeedsUpdate = false;
        }
        if (!this.isEnabled()) {
            return false;
        }
        if (!this.hasInited && !this.npc.isRemote() && type != EnumScriptType.INIT) {
            this.hasInited = true;
            for (IScriptUnit scriptUnit : this.eventScripts) {
                scriptUnit.setErrored(false);
            }
            EventHooks.onNPCInit(this.npc);
        }
        for (IScriptUnit script : this.eventScripts) {
            script.run(type, event);
        }
        ScriptContainer script = this.scripts.get((Object)type);
        if (script == null || script.errored || !script.hasCode()) {
            return false;
        }
        script.setEngine(this.getLanguage());
        if (script.engine == null) {
            return false;
        }
        int i = 0;
        while (i + 1 < obs.length) {
            IEntity<?> ob = obs[i + 1];
            if (ob instanceof Entity) {
                ob = NpcAPI.Instance().getIEntity((Entity)ob);
            }
            script.engine.put(obs[i].toString(), ob);
            i += 2;
        }
        if (ConfigDebug.ScriptLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            if (obs.length > 1 && obs[1] == null) {
                LogWriter.postScriptLog(EntityNPCInterface.field_110179_h, type, String.format("[%s] NPC %s (%s, %s, %s)", type.function.toUpperCase(), this.npc.display.name, (int)this.npc.field_70165_t, (int)this.npc.field_70163_u, (int)this.npc.field_70161_v));
            } else {
                LogWriter.postScriptLog(EntityNPCInterface.field_110179_h, type, String.format("[%s] NPC %s (%s, %s, %s) | Objects: %s", type.function.toUpperCase(), this.npc.display.name, (int)this.npc.field_70165_t, (int)this.npc.field_70163_u, (int)this.npc.field_70161_v, Arrays.toString(obs)));
            }
        }
        return this.callScript(script, type.function, event);
    }

    private boolean callScript(ScriptContainer script, String hookName, Event event) {
        ScriptEngine engine = script.engine;
        this.applyGlobalsToEngine(engine, hookName, event);
        script.run(engine);
        if (this.clientNeedsUpdate) {
            this.npc.updateClient = true;
            this.clientNeedsUpdate = false;
        }
        if (this.aiNeedsUpdate) {
            this.npc.updateAI = true;
            this.aiNeedsUpdate = false;
        }
        return event.isCanceled();
    }

    public boolean isEnabled() {
        return this.enabled && ScriptController.HasStart && !this.npc.field_70170_p.field_72995_K && !this.scripts.isEmpty() && CustomNpcs.proxy.isScriptingEnabled();
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.NPC;
    }

    @Override
    public void requestData() {
        EventScriptPacket.Get();
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        EventScriptPacket.Save(index, totalCount, nbt);
    }

    public Map<Long, String> getOldConsoleText() {
        TreeMap<Long, String> map = new TreeMap<Long, String>();
        for (Map.Entry<EnumScriptType, ScriptContainer> entry : this.scripts.entrySet()) {
            for (Map.Entry<Long, String> consoleEntry : entry.getValue().console.entrySet()) {
                map.put(consoleEntry.getKey(), " tab " + entry.getKey().ordinal() + ":\n" + consoleEntry.getValue());
            }
        }
        return map;
    }

    @Override
    public void callScript(EnumScriptType type, Event event) {
        this.callScript(type, event, "$$IGNORED$$", null);
    }

    @Override
    public void callScript(String hookName, Event event) {
        try {
            EnumScriptType enumScriptType = EnumScriptType.valueOfIgnoreCase(hookName);
            this.callScript(enumScriptType, event);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
    }

    public Map<String, String> getEditorGlobals(String hookName) {
        Map<String, GlobalDefinition> definitions = this.getGlobalDefinitions(hookName, null);
        LinkedHashMap<String, String> globals = new LinkedHashMap<String, String>();
        for (Map.Entry<String, GlobalDefinition> entry : definitions.entrySet()) {
            String typeName = entry.getValue().typeName;
            if (typeName == null || typeName.isEmpty()) continue;
            globals.put(entry.getKey(), typeName);
        }
        return globals;
    }

    private void applyGlobalsToEngine(ScriptEngine engine, String hookName, Event event) {
        Map<String, GlobalDefinition> definitions = this.getGlobalDefinitions(hookName, event);
        for (Map.Entry<String, GlobalDefinition> entry : definitions.entrySet()) {
            engine.put(entry.getKey(), entry.getValue().value);
        }
        long currentGlobalsVersion = NpcAPI.engineObjectsVersion;
        if (this.lastGlobalsVersion != currentGlobalsVersion) {
            engine.put("API", NpcAPI.Instance());
            for (Map.Entry<String, Object> engineObjects : NpcAPI.engineObjects.entrySet()) {
                engine.put(engineObjects.getKey(), engineObjects.getValue());
            }
            this.lastGlobalsVersion = currentGlobalsVersion;
        }
    }

    private Map<String, GlobalDefinition> getGlobalDefinitions(String hookName, Event event) {
        LinkedHashMap<String, GlobalDefinition> globals = new LinkedHashMap<String, GlobalDefinition>();
        globals.put("npc", new GlobalDefinition("ICustomNpc", this.dummyNpc));
        globals.put("world", new GlobalDefinition("IWorld", this.dummyWorld));
        globals.put("event", new GlobalDefinition(this.resolveEditorEventTypeName(hookName), event));
        globals.put("EntityType", new GlobalDefinition("noppes.npcs.scripted.constants.EntityType", entities));
        globals.put("RoleType", new GlobalDefinition("noppes.npcs.scripted.constants.RoleType", roles));
        globals.put("JobType", new GlobalDefinition("noppes.npcs.scripted.constants.JobType", jobs));
        return globals;
    }

    private String resolveEditorEventTypeName(String hookName) {
        String fallback = "INpcEvent";
        if (hookName == null || hookName.isEmpty()) {
            return "INpcEvent";
        }
        IHookDefinition definition = ScriptHookController.Instance.getHookDefinition(this.getContext().hookContext, hookName);
        if (definition == null) {
            return "INpcEvent";
        }
        String typeName = definition.getUsableTypeName();
        if (typeName == null || typeName.isEmpty()) {
            return "INpcEvent";
        }
        return typeName;
    }

    @Override
    public boolean isClient() {
        return this.npc.isRemote();
    }

    @Override
    public boolean getEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean bo) {
        this.enabled = bo;
    }

    @Override
    public String getLanguage() {
        return this.scriptLanguage;
    }

    @Override
    public void setLanguage(String lang) {
        this.scriptLanguage = lang;
    }

    @Override
    public void setScripts(List<IScriptUnit> list) {
        this.eventScripts = list;
    }

    @Override
    public List<IScriptUnit> getScripts() {
        return this.eventScripts;
    }

    @Override
    public String noticeString() {
        return "";
    }

    public void setWorld(World world) {
        if (world instanceof WorldServer) {
            this.dummyWorld = new ScriptWorld((WorldServer)world);
        }
    }

    public ScriptContainer getNPCScript(EnumScriptType scriptType) {
        return this.scripts.get((Object)scriptType);
    }

    public ScriptContainer getNPCScript(int ordinal) {
        return this.getNPCScript(EnumScriptType.values()[ordinal]);
    }

    public void setNPCScript(EnumScriptType scriptType, ScriptContainer container) {
        this.scripts.put(scriptType, container);
    }

    public void setNPCScript(int ordinal, ScriptContainer container) {
        this.setNPCScript(EnumScriptType.values()[ordinal], container);
    }

    public Collection<ScriptContainer> getNPCScripts() {
        return this.scripts.values();
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.NPC);
    }

    private static final class GlobalDefinition {
        private final String typeName;
        private final Object value;

        private GlobalDefinition(String typeName, Object value) {
            this.typeName = typeName;
            this.value = value;
        }
    }
}

