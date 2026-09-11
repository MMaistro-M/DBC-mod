/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  org.apache.logging.log4j.Level
 */
package noppes.npcs.config;

import cpw.mods.fml.common.FMLLog;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import kamkeel.npcs.developer.Developer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.legacy.LegacyConfig;
import org.apache.logging.log4j.Level;

public class ConfigScript {
    public static Configuration config;
    public static final String GENERAL = "Scripting General";
    public static final String CUSTOMIZATION = "Scripting Customization";
    public static Property ScriptingEnabledProperty;
    public static boolean ScriptingEnabled;
    public static Property ScriptOpsOnlyProperty;
    public static boolean ScriptOpsOnly;
    public static Property GlobalPlayerScriptsProperty;
    public static boolean GlobalPlayerScripts;
    public static Property GlobalForgeScriptsProperty;
    public static boolean GlobalForgeScripts;
    public static Property GlobalNPCScriptsProperty;
    public static boolean GlobalNPCScripts;
    public static Property ScriptingECMA6Property;
    public static boolean ScriptingECMA6;
    public static Property ExpandedScriptLimitProperty;
    public static int ExpandedScriptLimit;
    public static Property ScriptDevIDsProperty;
    public static String ScriptDevIDs;
    public static Property EnableBannedClassesProperty;
    public static boolean EnableBannedClasses;
    public static Property RunLoadedScriptsFirstProperty;
    public static boolean RunLoadedScriptsFirst;
    public static Property BannedClassesProperty;
    public static final HashSet<String> BannedClasses;
    public static int ActionManagerTickDefault;
    public static Property ClearActionsOnDeathProperty;
    public static boolean ClearActionsOnDeath;
    public static Property ShowImplementationFieldsInAutocompleteProperty;
    public static boolean ShowImplementationFieldsInAutocomplete;
    public static boolean IndividualPlayerScripts;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void init(File configFile) {
        config = new Configuration(configFile);
        try {
            config.load();
            ScriptingEnabledProperty = config.get(GENERAL, "Enable Scripting", true, "Enables/Disables ALL scripting. You can still see and write code in the scripter, but these scripts won't run. True by default");
            ScriptingEnabled = ScriptingEnabledProperty.getBoolean(true);
            ScriptOpsOnlyProperty = config.get(GENERAL, "OPs Only", false, "Only ops can see and edit scripts");
            ScriptOpsOnly = ScriptOpsOnlyProperty.getBoolean(false);
            GlobalPlayerScriptsProperty = config.get(GENERAL, "Enable Global Player Scripts", true, "Enables global player event scripts to be used in the scripter. You can still see and write code in the scripter, but these scripts won't run. True by default.");
            GlobalPlayerScripts = GlobalPlayerScriptsProperty.getBoolean(true);
            GlobalForgeScriptsProperty = config.get(GENERAL, "Enable Global Forge Event Scripts", true, "Enables global forge event scripts to be used in the scripter. You can still see and write code in the scripter, but these scripts won't run. True by default.");
            GlobalForgeScripts = GlobalForgeScriptsProperty.getBoolean(true);
            GlobalNPCScriptsProperty = config.get(GENERAL, "Enable Global NPC Scripts", false, "Enables global NPC scripts to be used in the scripter. You can still see and write code in the scripter, but these scripts won't run. False by default, use with caution!");
            GlobalNPCScripts = GlobalNPCScriptsProperty.getBoolean(false);
            ScriptingECMA6Property = config.get(CUSTOMIZATION, "ECMA6 Scripting Language", false, "Enables/Disables the use of the the ECMA6 Javascript standard instead of ECMA5.1.\nEnabling this adds many more features to JS in scripts. Only use if on Java 8 or higher.\nNot all ECMA 6 language is supported through this functionality.");
            ScriptingECMA6 = ScriptingECMA6Property.getBoolean(false);
            ExpandedScriptLimitProperty = config.get(CUSTOMIZATION, "Script Tag Limit", 2, "If scripts are too long (>65535 characters), they normally won't be saved in NBT data.\nThis config adds additional compound tags to scripts that need it, so you can store much larger scripts!\nEvery additional compound tag adds 65535 more characters to your script length limit. Use incrementally, with caution.");
            ExpandedScriptLimit = ExpandedScriptLimitProperty.getInt(2);
            ScriptDevIDsProperty = config.get(CUSTOMIZATION, "Script Dev UUIDs", "", "Comma separated list of player UUIDs that can see and edit scripts. If ScriptsOpsOnly is true,\nops and players with these IDs can see and edit scripts. Example:\nb876ec32-e396-476b-a115-8438d83c67d4,069a79f4-44e9-4726-a5be-fca90e38aaf5,be951074-c7ea-4f02-a725-bf017bc88650\nGet a player's UUID from a site like NameMC or the API IPlayer.getUniqueID() function!\nIf left empty and ScriptsOpsOnly is false, anyone can see and edit scripts with a scripter.");
            ScriptDevIDs = ScriptDevIDsProperty.getString();
            EnableBannedClassesProperty = config.get(CUSTOMIZATION, "Enable Banned Classes", false, "Enables the Banned Classes Functionality");
            EnableBannedClasses = EnableBannedClassesProperty.getBoolean(false);
            RunLoadedScriptsFirstProperty = config.get(CUSTOMIZATION, "Run Loaded Scripts First", true, "If scripts have been loaded from the scripting GUI, the script engine will evaluates them by\nmerging your loaded scripts with your main script, then running the combined script.\nThis config determines the order in while the loaded scripts are merged with your main scripts: before (true), or after (false).");
            RunLoadedScriptsFirst = RunLoadedScriptsFirstProperty.getBoolean(true);
            BannedClassesProperty = config.get(CUSTOMIZATION, "Banned Classes", "java.net.URL,java.net.URI", "Comma separated list of classes that cannot be used in scripts through Java.for().\nClasses must be fully written out with library names preceding them.\nThis is a feature ONLY AVAILABLE ON NASHORN.");
            IndividualPlayerScripts = config.get(CUSTOMIZATION, "Individual Player Scripts", false, "Acts similar to CNPC 1.12 where Player Scripts like Init are run PER Player").getBoolean(false);
            ActionManagerTickDefault = config.get(CUSTOMIZATION, "Action Manager Tick Default", ActionManagerTickDefault, "How frequent to update the action manager ticking tasks").getInt(ActionManagerTickDefault);
            ClearActionsOnDeathProperty = config.get(CUSTOMIZATION, "Clear Actions On Death", true, "If true, clears the Action Manager for players and NPCs when they die");
            ClearActionsOnDeath = ClearActionsOnDeathProperty.getBoolean(true);
            ShowImplementationFieldsInAutocompleteProperty = config.get(CUSTOMIZATION, "Autocomplete Implementation Fields", false, "If true, shows fields from implementation classes in autocomplete menu like 'event.npc'.\n This can be useful for advanced scripters who want to use fields from implementation classes.");
            ShowImplementationFieldsInAutocomplete = ShowImplementationFieldsInAutocompleteProperty.getBoolean(false);
            if (CustomNpcs.legacyExist) {
                ScriptingEnabled = LegacyConfig.ScriptingEnabled;
                ScriptingEnabledProperty.set(ScriptingEnabled);
                ScriptOpsOnly = LegacyConfig.ScriptOpsOnly;
                ScriptOpsOnlyProperty.set(ScriptOpsOnly);
                GlobalPlayerScripts = LegacyConfig.GlobalPlayerScripts;
                GlobalPlayerScriptsProperty.set(GlobalPlayerScripts);
                GlobalForgeScripts = LegacyConfig.GlobalForgeScripts;
                GlobalForgeScriptsProperty.set(GlobalForgeScripts);
                GlobalNPCScripts = LegacyConfig.GlobalNPCScripts;
                GlobalNPCScriptsProperty.set(GlobalNPCScripts);
                ScriptingECMA6 = LegacyConfig.ScriptingECMA6;
                ScriptingECMA6Property.set(ScriptingECMA6);
                ExpandedScriptLimit = LegacyConfig.ExpandedScriptLimit;
                ExpandedScriptLimitProperty.set(ExpandedScriptLimit);
                ScriptDevIDs = LegacyConfig.ScriptDevIDs;
                ScriptDevIDsProperty.set(ScriptDevIDs);
            }
            if (ExpandedScriptLimit < 0) {
                ExpandedScriptLimit = 0;
            }
            if (ActionManagerTickDefault < 0) {
                ActionManagerTickDefault = 1;
            }
            try {
                String[] uuidStrings;
                Developer.ScriptUser.clear();
                for (String s : uuidStrings = ScriptDevIDs.split(",")) {
                    Developer.ScriptUser.add(UUID.fromString(s));
                }
            }
            catch (Exception uuidStrings) {
                // empty catch block
            }
            String bannedClassesString = BannedClassesProperty.getString();
            try {
                BannedClasses.clear();
                BannedClasses.addAll(Arrays.asList(bannedClassesString.split(",")));
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        catch (Exception e) {
            FMLLog.log((Level)Level.ERROR, (Throwable)e, (String)"CNPC+ has had a problem loading its scripting configuration", (Object[])new Object[0]);
        }
        finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public static boolean isScriptDev(EntityPlayer player) {
        return Developer.ScriptUser.contains(player.func_110124_au()) || Developer.Universal.contains(player.func_110124_au());
    }

    public static boolean canScript(EntityPlayer player, CustomNpcsPermissions.Permission perm) {
        boolean isOp = MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(player.func_146103_bH());
        boolean scriptDev = ConfigScript.isScriptDev(player);
        if (ScriptOpsOnly) {
            return isOp || scriptDev;
        }
        return scriptDev || CustomNpcsPermissions.hasPermission(player, perm);
    }

    static {
        ScriptingEnabled = true;
        ScriptOpsOnly = false;
        GlobalPlayerScripts = true;
        GlobalForgeScripts = true;
        GlobalNPCScripts = false;
        ScriptingECMA6 = false;
        ExpandedScriptLimit = 2;
        ScriptDevIDs = "";
        EnableBannedClasses = false;
        RunLoadedScriptsFirst = true;
        BannedClasses = new HashSet();
        ActionManagerTickDefault = 5;
        ClearActionsOnDeath = true;
        ShowImplementationFieldsInAutocomplete = false;
        IndividualPlayerScripts = false;
    }
}

