/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability.data.entry;

import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.data.effect.IEffectAction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class AbilityEffectActionEntry {
    private String actionId = "";
    private NBTTagCompound config = new NBTTagCompound();

    public AbilityEffectActionEntry() {
    }

    public AbilityEffectActionEntry(String actionId) {
        this.actionId = actionId != null ? actionId : "";
        IEffectAction action = AbilityController.Instance.getEffectAction(this.actionId);
        if (action != null) {
            this.config = action.createDefaultConfig();
        }
    }

    public AbilityEffectActionEntry copy() {
        AbilityEffectActionEntry copy = new AbilityEffectActionEntry();
        copy.actionId = this.actionId;
        copy.config = (NBTTagCompound)this.config.func_74737_b();
        return copy;
    }

    public void apply(EntityLivingBase caster, EntityLivingBase target) {
        if (this.actionId.isEmpty()) {
            return;
        }
        IEffectAction action = AbilityController.Instance.getEffectAction(this.actionId);
        if (action != null) {
            action.apply(caster, target, this.config);
        }
    }

    public boolean isValid() {
        return !this.actionId.isEmpty() && AbilityController.Instance.getEffectAction(this.actionId) != null;
    }

    public NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74778_a("actionId", this.actionId);
        nbt.func_74782_a("config", (NBTBase)this.config);
        return nbt;
    }

    public void readNBT(NBTTagCompound nbt) {
        this.actionId = nbt.func_74779_i("actionId");
        this.config = nbt.func_74764_b("config") ? nbt.func_74775_l("config") : new NBTTagCompound();
    }

    public static AbilityEffectActionEntry fromNBT(NBTTagCompound nbt) {
        AbilityEffectActionEntry entry = new AbilityEffectActionEntry();
        entry.readNBT(nbt);
        return entry;
    }

    public String getActionId() {
        return this.actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId != null ? actionId : "";
    }

    public NBTTagCompound getConfig() {
        return this.config;
    }

    public void setConfig(NBTTagCompound config) {
        this.config = config != null ? config : new NBTTagCompound();
    }
}

