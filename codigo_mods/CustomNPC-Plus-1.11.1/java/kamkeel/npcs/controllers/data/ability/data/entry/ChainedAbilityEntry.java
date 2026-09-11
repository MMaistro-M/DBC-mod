/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.entry;

import java.util.UUID;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class ChainedAbilityEntry {
    private EntryType entryType = EntryType.REFERENCE;
    private String abilityReference = "";
    private Ability inlineAbility = null;
    private int delayTicks = 0;
    private boolean concurrentEnabled = true;

    public ChainedAbilityEntry() {
    }

    public ChainedAbilityEntry(String abilityReference, int delayTicks) {
        this.entryType = EntryType.REFERENCE;
        this.abilityReference = abilityReference != null ? abilityReference : "";
        this.delayTicks = Math.max(0, delayTicks);
    }

    public static ChainedAbilityEntry reference(String ref, int delay) {
        ChainedAbilityEntry entry = new ChainedAbilityEntry();
        entry.entryType = EntryType.REFERENCE;
        entry.abilityReference = ref != null ? ref : "";
        entry.delayTicks = Math.max(0, delay);
        return entry;
    }

    public static ChainedAbilityEntry inline(Ability ability, int delay) {
        ChainedAbilityEntry entry = new ChainedAbilityEntry();
        entry.entryType = EntryType.INLINE;
        entry.inlineAbility = ability;
        entry.delayTicks = Math.max(0, delay);
        return entry;
    }

    public EntryType getEntryType() {
        return this.entryType;
    }

    public boolean isInline() {
        return this.entryType == EntryType.INLINE;
    }

    public boolean isReference() {
        return this.entryType == EntryType.REFERENCE;
    }

    public String getAbilityReference() {
        return this.abilityReference;
    }

    public void setAbilityReference(String abilityReference) {
        this.abilityReference = abilityReference != null ? abilityReference : "";
    }

    public Ability getInlineAbility() {
        return this.inlineAbility;
    }

    public void setInlineAbility(Ability ability) {
        this.inlineAbility = ability;
    }

    public int getDelayTicks() {
        return this.delayTicks;
    }

    public void setDelayTicks(int delayTicks) {
        this.delayTicks = Math.max(0, delayTicks);
    }

    public boolean isConcurrentEnabled() {
        return this.concurrentEnabled;
    }

    public void setConcurrentEnabled(boolean concurrentEnabled) {
        this.concurrentEnabled = concurrentEnabled;
    }

    public Ability resolve() {
        if (this.entryType == EntryType.INLINE) {
            return this.inlineAbility;
        }
        AbilityController ctrl = AbilityController.Instance;
        if (ctrl == null) {
            return null;
        }
        return ctrl.resolveAbility(this.abilityReference);
    }

    public boolean convertToInline() {
        if (this.entryType != EntryType.REFERENCE) {
            return false;
        }
        AbilityController ctrl = AbilityController.Instance;
        if (ctrl == null) {
            return false;
        }
        Ability resolved = ctrl.resolveAbility(this.abilityReference);
        if (resolved == null) {
            return false;
        }
        NBTTagCompound nbt = resolved.writeNBT(true);
        nbt.func_74778_a("id", UUID.randomUUID().toString());
        this.inlineAbility = ctrl.fromNBT(nbt);
        this.entryType = EntryType.INLINE;
        this.abilityReference = "";
        return true;
    }

    public ChainedAbilityEntry deepCopy() {
        ChainedAbilityEntry copy = new ChainedAbilityEntry();
        copy.entryType = this.entryType;
        copy.abilityReference = this.abilityReference;
        copy.delayTicks = this.delayTicks;
        copy.concurrentEnabled = this.concurrentEnabled;
        if (this.inlineAbility != null) {
            copy.inlineAbility = AbilityController.Instance.fromNBT(this.inlineAbility.writeNBT(true));
        }
        return copy;
    }

    public NBTTagCompound writeNBT(boolean saveScripts) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("Delay", this.delayTicks);
        nbt.func_74757_a("Concurrent", this.concurrentEnabled);
        if (this.entryType == EntryType.INLINE && this.inlineAbility != null) {
            nbt.func_74782_a("InlineAbility", (NBTBase)this.inlineAbility.writeNBT(saveScripts));
        } else {
            nbt.func_74778_a("Reference", this.abilityReference);
        }
        return nbt;
    }

    public static ChainedAbilityEntry fromNBT(NBTTagCompound nbt) {
        if (nbt == null) {
            return null;
        }
        ChainedAbilityEntry entry = new ChainedAbilityEntry();
        entry.delayTicks = Math.max(0, nbt.func_74762_e("Delay"));
        boolean bl = entry.concurrentEnabled = nbt.func_74764_b("Concurrent") ? nbt.func_74767_n("Concurrent") : true;
        if (nbt.func_74764_b("InlineAbility")) {
            NBTTagCompound abilityNBT = nbt.func_74775_l("InlineAbility");
            entry.entryType = EntryType.INLINE;
            entry.inlineAbility = AbilityController.Instance != null ? AbilityController.Instance.fromNBT(abilityNBT) : null;
        } else {
            entry.entryType = EntryType.REFERENCE;
            entry.abilityReference = nbt.func_74779_i("Reference");
        }
        return entry;
    }

    public static enum EntryType {
        REFERENCE,
        INLINE;

    }
}

