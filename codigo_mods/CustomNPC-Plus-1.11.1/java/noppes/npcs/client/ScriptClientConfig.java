/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;

@SideOnly(value=Side.CLIENT)
public class ScriptClientConfig {
    private static boolean scriptingEnabled = true;
    private static boolean runLoadedScriptsFirst = true;
    private static boolean globalPlayerScripts = true;
    private static boolean globalForgeScripts = true;
    private static boolean globalNPCScripts = false;

    public static void readFromNBT(NBTTagCompound compound) {
        if (compound == null) {
            return;
        }
        scriptingEnabled = compound.func_74767_n("ScriptingEnabled");
        runLoadedScriptsFirst = compound.func_74767_n("RunLoadedScriptsFirst");
        globalPlayerScripts = compound.func_74767_n("GlobalPlayerScripts");
        globalForgeScripts = compound.func_74767_n("GlobalForgeScripts");
        globalNPCScripts = compound.func_74767_n("GlobalNPCScripts");
    }

    public static void reset() {
        scriptingEnabled = true;
        runLoadedScriptsFirst = true;
        globalPlayerScripts = true;
        globalForgeScripts = true;
        globalNPCScripts = false;
    }

    public static void setScriptingEnabled(boolean value) {
        scriptingEnabled = value;
    }

    public static void setRunLoadedScriptsFirst(boolean value) {
        runLoadedScriptsFirst = value;
    }

    public static void setGlobalPlayerScripts(boolean value) {
        globalPlayerScripts = value;
    }

    public static void setGlobalForgeScripts(boolean value) {
        globalForgeScripts = value;
    }

    public static void setGlobalNPCScripts(boolean value) {
        globalNPCScripts = value;
    }

    public static boolean isScriptingEnabled() {
        return scriptingEnabled;
    }

    public static boolean isRunLoadedScriptsFirst() {
        return runLoadedScriptsFirst;
    }

    public static boolean isGlobalPlayerScripts() {
        return globalPlayerScripts;
    }

    public static boolean isGlobalForgeScripts() {
        return globalForgeScripts;
    }

    public static boolean isGlobalNPCScripts() {
        return globalNPCScripts;
    }
}

