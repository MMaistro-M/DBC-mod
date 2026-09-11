/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  jdk.nashorn.api.scripting.ClassFilter
 *  jdk.nashorn.api.scripting.NashornScriptEngineFactory
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.IWorldAccess
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 */
package noppes.npcs.controllers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import jdk.nashorn.api.scripting.ClassFilter;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import kamkeel.npcs.network.packets.request.script.ScriptFilesPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.IWorldAccess;
import net.minecraftforge.event.world.WorldEvent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.controllers.data.ForgeDataScript;
import noppes.npcs.controllers.data.GlobalNPCDataScript;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerDataScript;
import noppes.npcs.scripted.ScriptWorld;
import noppes.npcs.util.CustomNPCsThreader;
import noppes.npcs.util.JsonException;
import noppes.npcs.util.NBTJsonUtil;
import somehussar.janino.EntityUnloadListener;

public class ScriptController {
    public static ScriptController Instance;
    public static boolean HasStart;
    private final ScriptEngineManager manager;
    private ScriptEngineFactory nashornFactory;
    public final Map<String, String> languages = new HashMap<String, String>();
    public Map<String, String> scripts = new HashMap<String, String>();
    public long lastLoaded = 0L;
    public File dir;
    public NBTTagCompound compound = new NBTTagCompound();
    public boolean shouldSave = false;
    public PlayerDataScript playerScripts = new PlayerDataScript(null);
    public long lastPlayerUpdate = 0L;
    public ForgeDataScript forgeScripts = new ForgeDataScript();
    public long lastForgeUpdate = 0L;
    public GlobalNPCDataScript globalNpcScripts = new GlobalNPCDataScript(null);
    public long lastGlobalNpcUpdate = 0L;
    public int globalRevision;
    private static final List<String> nashornNames;

    public ScriptController() {
        Instance = this;
        this.manager = new ScriptEngineManager();
        if (!CustomNpcs.proxy.isScriptingEnabled()) {
            return;
        }
        LogWriter.info("Script Engines Available:");
        try {
            this.nashornFactory = new NashornScriptEngineFactory();
            LogWriter.info("\u2192 standalone Nashorn loaded");
        }
        catch (NoClassDefFoundError e) {
            try {
                ScriptEngine eng = this.manager.getEngineByName("nashorn");
                if (eng != null) {
                    this.nashornFactory = eng.getFactory();
                    LogWriter.info("\u2192 built-in Nashorn loaded");
                }
            }
            catch (Throwable t) {
                LogWriter.error("No Nashorn engine available");
            }
        }
        for (ScriptEngineFactory fac : this.manager.getEngineFactories()) {
            if (fac.getExtensions().isEmpty()) continue;
            ScriptEngine scriptEngine = fac.getScriptEngine();
            try {
                scriptEngine.put("$RunTest", null);
                String ext = "." + fac.getExtensions().get(0).toLowerCase();
                LogWriter.info("Engine " + fac.getEngineName() + " running " + fac.getLanguageName() + " with extension: " + ext);
                this.languages.put(fac.getLanguageName(), ext);
            }
            catch (Exception exception) {}
        }
        this.languages.put("Java", ".java");
    }

    private File forgeScriptsFile() {
        return new File(this.dir, "forge_scripts.json");
    }

    public boolean loadForgeScripts() {
        this.forgeScripts.clear();
        File file = this.forgeScriptsFile();
        try {
            if (!file.exists()) {
                return false;
            }
            this.forgeScripts.readFromNBT(NBTJsonUtil.LoadFile(file));
            return true;
        }
        catch (Exception var3) {
            LogWriter.error("Error loading: " + file.getAbsolutePath(), var3);
            return false;
        }
    }

    public void saveForgeScripts() {
        File file = this.forgeScriptsFile();
        try {
            NBTJsonUtil.SaveFile(file, this.forgeScripts.writeToNBT(new NBTTagCompound()));
            this.forgeScripts.resetLastInited();
        }
        catch (IOException | JsonException var4) {
            var4.printStackTrace();
        }
    }

    private File playerScriptsFile() {
        return new File(this.dir, "player_scripts.json");
    }

    private File npcScriptsFile() {
        return new File(this.dir, "npc_scripts.json");
    }

    public boolean loadPlayerScripts() {
        this.playerScripts.clear();
        File file = this.playerScriptsFile();
        try {
            if (!file.exists()) {
                return false;
            }
            this.playerScripts.readFromNBT(NBTJsonUtil.LoadFile(file));
            this.shouldSave = false;
            return true;
        }
        catch (Exception var3) {
            LogWriter.error("Error loading: " + file.getAbsolutePath(), var3);
            return false;
        }
    }

    public void savePlayerScripts() {
        File file = this.playerScriptsFile();
        try {
            NBTJsonUtil.SaveFile(file, this.playerScripts.writeToNBT(new NBTTagCompound()));
            this.playerScripts.resetLastInited();
        }
        catch (IOException | JsonException var4) {
            var4.printStackTrace();
        }
    }

    public PlayerDataScript getPlayerScripts(EntityPlayer player) {
        if (ConfigScript.IndividualPlayerScripts) {
            return PlayerData.get((EntityPlayer)player).scriptData;
        }
        return this.playerScripts;
    }

    public PlayerDataScript getPlayerScripts(IPlayer player) {
        if (ConfigScript.IndividualPlayerScripts) {
            return PlayerData.get((EntityPlayer)((EntityPlayer)player.getMCEntity())).scriptData;
        }
        return this.playerScripts;
    }

    public boolean loadGlobalNPCScripts() {
        this.globalNpcScripts.clear();
        File file = this.npcScriptsFile();
        try {
            if (!file.exists()) {
                return false;
            }
            this.globalNpcScripts.readFromNBT(NBTJsonUtil.LoadFile(file));
            this.shouldSave = false;
            return true;
        }
        catch (Exception var3) {
            LogWriter.error("Error loading: " + file.getAbsolutePath(), var3);
            return false;
        }
    }

    public void saveGlobalNpcScripts() {
        File file = this.npcScriptsFile();
        try {
            NBTJsonUtil.SaveFile(file, this.globalNpcScripts.writeToNBT(new NBTTagCompound()));
            this.globalNpcScripts.resetLastInited();
        }
        catch (IOException | JsonException var4) {
            var4.printStackTrace();
        }
    }

    public synchronized void saveForgeScriptsSync() {
        CustomNPCsThreader.customNPCThread.execute(this::saveForgeScripts);
    }

    public synchronized void savePlayerScriptsSync() {
        CustomNPCsThreader.customNPCThread.execute(() -> {
            File file = this.playerScriptsFile();
            try {
                NBTJsonUtil.SaveFile(file, this.playerScripts.writeToNBT(new NBTTagCompound()));
                this.playerScripts.resetLastInited();
            }
            catch (IOException | JsonException var4) {
                var4.printStackTrace();
            }
        });
    }

    public synchronized void saveGlobalScriptsSync() {
        CustomNPCsThreader.customNPCThread.execute(this::saveGlobalNpcScripts);
    }

    public void syncClientScripts(EntityPlayerMP player) {
        if (player != null) {
            ScriptFilesPacket.sendToPlayer(player, "Java");
        } else {
            ScriptFilesPacket.sendToAll("Java");
        }
    }

    public void loadCategories() {
        this.dir = new File(CustomNpcs.getWorldSaveDirectory(), "scripts");
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        if (!this.getSavedFile().exists()) {
            this.shouldSave = true;
        }
        new ScriptWorld(null).clearTempData();
        this.scripts.clear();
        for (String language : this.languages.keySet()) {
            String ext = this.languages.get(language);
            File scriptDir = new File(this.dir, language.toLowerCase());
            if (!scriptDir.exists()) {
                scriptDir.mkdir();
                continue;
            }
            this.loadDir(scriptDir, "", ext);
        }
        this.lastLoaded = System.currentTimeMillis();
        ++this.globalRevision;
    }

    private void loadDir(File dir, String name, String ext) {
        for (File file : dir.listFiles()) {
            String filename = name + file.getName().toLowerCase();
            if (file.isDirectory()) {
                this.loadDir(file, filename + "/", ext);
                continue;
            }
            if (!filename.endsWith(ext)) continue;
            try {
                this.scripts.put(filename, this.readFile(file));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean loadStoredData() {
        this.loadCategories();
        File file = this.getSavedFile();
        try {
            if (!file.exists()) {
                return false;
            }
            this.compound = NBTJsonUtil.LoadFile(file);
            this.shouldSave = false;
        }
        catch (Exception e) {
            LogWriter.error("Error loading: " + file.getAbsolutePath(), e);
            return false;
        }
        return true;
    }

    private File getSavedFile() {
        return new File(this.dir, "world_data.json");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String readFile(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file));){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            String string = sb.toString();
            return string;
        }
    }

    public ScriptEngine getEngineByName(String language) {
        if (nashornNames.contains(language) && this.nashornFactory != null) {
            ScriptEngine scriptEngine;
            if (ConfigScript.EnableBannedClasses) {
                try {
                    ClassFilter filter = s -> {
                        for (String className : ConfigScript.BannedClasses) {
                            if (s.compareTo(className) != 0) continue;
                            return false;
                        }
                        return true;
                    };
                    NashornScriptEngineFactory nashornScriptEngineFactory = (NashornScriptEngineFactory)this.nashornFactory;
                    scriptEngine = nashornScriptEngineFactory.getScriptEngine(filter);
                }
                catch (Exception e) {
                    scriptEngine = this.nashornFactory.getScriptEngine();
                }
            } else {
                scriptEngine = this.nashornFactory.getScriptEngine();
            }
            scriptEngine.setBindings(this.manager.getBindings(), 200);
            return scriptEngine;
        }
        return this.manager.getEngineByName(language);
    }

    private static List<String> immutableList(String ... elements) {
        return Collections.unmodifiableList(Arrays.asList(elements));
    }

    public NBTTagList nbtLanguages() {
        NBTTagList list = new NBTTagList();
        for (String language : this.languages.keySet()) {
            NBTTagCompound compound = new NBTTagCompound();
            NBTTagList scripts = new NBTTagList();
            for (String script : this.getScripts(language)) {
                scripts.func_74742_a((NBTBase)new NBTTagString(script));
            }
            compound.func_74782_a("Scripts", (NBTBase)scripts);
            compound.func_74778_a("Language", language);
            list.func_74742_a((NBTBase)compound);
        }
        return list;
    }

    public List<String> getScripts(String language) {
        ArrayList<String> list = new ArrayList<String>();
        String ext = this.languages.get(language);
        if (ext == null) {
            return list;
        }
        for (String script : this.scripts.keySet()) {
            if (!script.endsWith(ext)) continue;
            list.add(script);
        }
        return list;
    }

    @SubscribeEvent
    public void addWorldAccess(WorldEvent.Load event) {
        event.world.func_72954_a((IWorldAccess)new EntityUnloadListener());
    }

    @SubscribeEvent
    public void invoke(WorldEvent.Save event) {
        if (!this.shouldSave || event.world.field_72995_K || event.world != MinecraftServer.func_71276_C().field_71305_c[0]) {
            return;
        }
        try {
            NBTJsonUtil.SaveFile(this.getSavedFile(), this.compound);
        }
        catch (Exception e) {
            LogWriter.except(e);
        }
        this.shouldSave = false;
    }

    static {
        HasStart = false;
        nashornNames = ScriptController.immutableList("nashorn", "Nashorn", "js", "JS", "JavaScript", "javascript", "ECMAScript", "ecmascript");
    }
}

