/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;

public class MagicEntry {
    public float damage;
    public float split;

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74776_a("Dmg", this.damage);
        compound.func_74776_a("Split", this.split);
        return compound;
    }

    public void readToNBT(NBTTagCompound compound) {
        this.damage = compound.func_74760_g("Dmg");
        this.split = compound.func_74760_g("Split");
    }
}

