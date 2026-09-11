/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class AbilityHotbarData {
    public static final String CHAIN_PREFIX = "chain:";
    public static final int TOTAL_SLOTS = 12;
    public int slot = -1;
    public String abilityKey = "";

    public AbilityHotbarData() {
    }

    public AbilityHotbarData(int slot) {
        this.slot = slot;
    }

    public boolean isChainKey() {
        return this.abilityKey != null && this.abilityKey.startsWith(CHAIN_PREFIX);
    }

    public String getResolveKey() {
        if (this.isChainKey()) {
            return this.abilityKey.substring(CHAIN_PREFIX.length());
        }
        return this.abilityKey;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74768_a("slot", this.slot);
        tag.func_74778_a("abilityKey", this.abilityKey != null ? this.abilityKey : "");
        compound.func_74782_a("AbilityHotbar" + this.slot, (NBTBase)tag);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.abilityKey = compound.func_74779_i("abilityKey");
    }

    public void reset() {
        this.abilityKey = "";
    }

    public boolean isEmpty() {
        return this.abilityKey == null || this.abilityKey.isEmpty();
    }
}

