/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;

public class CloneFolder {
    public String name;
    public long createdDate;

    public CloneFolder() {
        this.name = "";
        this.createdDate = System.currentTimeMillis();
    }

    public CloneFolder(String name) {
        this.name = name;
        this.createdDate = System.currentTimeMillis();
    }

    public void readNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("Name");
        this.createdDate = compound.func_74763_f("Created");
    }

    public NBTTagCompound writeNBT(NBTTagCompound compound) {
        compound.func_74778_a("Name", this.name);
        compound.func_74772_a("Created", this.createdDate);
        return compound;
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (name.length() > 32) {
            return false;
        }
        if (!name.equals(name.trim())) {
            return false;
        }
        if (name.equals(".") || name.equals("..")) {
            return false;
        }
        if (name.startsWith("___")) {
            return false;
        }
        try {
            Integer.parseInt(name);
            return false;
        }
        catch (NumberFormatException numberFormatException) {
            for (char c : name.toCharArray()) {
                if (c == '/' || c == '\\' || c == ':' || c == '*' || c == '?' || c == '\"' || c == '<' || c == '>' || c == '|') {
                    return false;
                }
                if (c >= ' ') continue;
                return false;
            }
            return true;
        }
    }
}

