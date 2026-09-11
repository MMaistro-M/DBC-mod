/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.config.ConfigScript;

public class ScriptConfigSync {
    public static NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74757_a("ScriptingEnabled", ConfigScript.ScriptingEnabled);
        compound.func_74757_a("RunLoadedScriptsFirst", ConfigScript.RunLoadedScriptsFirst);
        compound.func_74757_a("GlobalPlayerScripts", ConfigScript.GlobalPlayerScripts);
        compound.func_74757_a("GlobalForgeScripts", ConfigScript.GlobalForgeScripts);
        compound.func_74757_a("GlobalNPCScripts", ConfigScript.GlobalNPCScripts);
        return compound;
    }
}

