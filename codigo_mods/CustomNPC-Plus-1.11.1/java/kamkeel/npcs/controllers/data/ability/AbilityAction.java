/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.controllers.data.ability;

import java.util.UUID;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class AbilityAction {
    private SlotType slotType;
    private Ability inlineAbility;
    private ChainedAbility inlineChain;
    private String referenceId;
    private Boolean enabledOverride;
    private transient IAbilityAction cachedAction;
    private transient int cachedRevision = -1;

    private AbilityAction() {
    }

    public static AbilityAction inline(Ability ability) {
        AbilityAction slot = new AbilityAction();
        slot.slotType = SlotType.INLINE_ABILITY;
        slot.inlineAbility = ability;
        return slot;
    }

    public static AbilityAction abilityReference(String referenceId) {
        AbilityAction slot = new AbilityAction();
        slot.slotType = SlotType.ABILITY_REFERENCE;
        slot.referenceId = referenceId;
        return slot;
    }

    public static AbilityAction chainReference(String referenceId) {
        AbilityAction slot = new AbilityAction();
        slot.slotType = SlotType.CHAIN_REFERENCE;
        slot.referenceId = referenceId;
        return slot;
    }

    public static AbilityAction inlineChain(ChainedAbility chain) {
        AbilityAction slot = new AbilityAction();
        slot.slotType = SlotType.INLINE_CHAIN;
        slot.inlineChain = chain;
        return slot;
    }

    public SlotType getSlotType() {
        return this.slotType;
    }

    public boolean isReference() {
        return this.slotType == SlotType.ABILITY_REFERENCE || this.slotType == SlotType.CHAIN_REFERENCE;
    }

    public boolean isChainReference() {
        return this.slotType == SlotType.CHAIN_REFERENCE;
    }

    public boolean isInlineChain() {
        return this.slotType == SlotType.INLINE_CHAIN;
    }

    public boolean isChain() {
        return this.slotType == SlotType.CHAIN_REFERENCE || this.slotType == SlotType.INLINE_CHAIN;
    }

    public boolean isAbilityReference() {
        return this.slotType == SlotType.ABILITY_REFERENCE;
    }

    public String getReferenceId() {
        return this.referenceId;
    }

    public IAbilityAction getAction() {
        switch (this.slotType) {
            case INLINE_ABILITY: {
                return this.inlineAbility;
            }
            case INLINE_CHAIN: {
                return this.inlineChain;
            }
            case ABILITY_REFERENCE: {
                AbilityController ctrl = AbilityController.Instance;
                if (ctrl == null) {
                    return null;
                }
                int rev = ctrl.getCustomAbilityRevision();
                if (this.cachedAction != null && this.cachedRevision == rev) {
                    return this.cachedAction;
                }
                Ability resolved = ctrl.resolveAbility(this.referenceId);
                this.cachedAction = resolved;
                this.cachedRevision = rev;
                return this.cachedAction;
            }
            case CHAIN_REFERENCE: {
                AbilityController ctrl = AbilityController.Instance;
                if (ctrl == null) {
                    return null;
                }
                int rev = ctrl.getChainedAbilityRevision();
                if (this.cachedAction != null && this.cachedRevision == rev) {
                    return this.cachedAction;
                }
                ChainedAbility resolved = ctrl.resolveChainedAbility(this.referenceId);
                this.cachedAction = resolved;
                this.cachedRevision = rev;
                return this.cachedAction;
            }
        }
        return null;
    }

    public Ability getAbility() {
        IAbilityAction action = this.getAction();
        return action instanceof Ability ? (Ability)action : null;
    }

    public ChainedAbility getChainedAbility() {
        IAbilityAction action = this.getAction();
        return action instanceof ChainedAbility ? (ChainedAbility)action : null;
    }

    public void setEnabled(boolean enabled) {
        if (this.slotType == SlotType.INLINE_ABILITY) {
            if (this.inlineAbility != null) {
                this.inlineAbility.setEnabled(enabled);
            }
        } else if (this.slotType == SlotType.INLINE_CHAIN) {
            if (this.inlineChain != null) {
                this.inlineChain.setEnabled(enabled);
            }
        } else {
            this.enabledOverride = enabled;
        }
    }

    public boolean isSlotEnabled() {
        if (this.enabledOverride != null) {
            return this.enabledOverride;
        }
        IAbilityAction action = this.getAction();
        return action != null && action.isEnabled();
    }

    public Ability getInlineAbility() {
        return this.inlineAbility;
    }

    public ChainedAbility getInlineChain() {
        return this.inlineChain;
    }

    public boolean convertToInline() {
        if (this.slotType == SlotType.ABILITY_REFERENCE) {
            Ability resolved = this.getAbility();
            if (resolved == null) {
                return false;
            }
            NBTTagCompound nbt = resolved.writeNBT(true);
            nbt.func_74778_a("id", UUID.randomUUID().toString());
            this.inlineAbility = AbilityController.Instance.fromNBT(nbt);
            this.slotType = SlotType.INLINE_ABILITY;
            this.referenceId = null;
            this.cachedAction = null;
            this.cachedRevision = -1;
            return true;
        }
        if (this.slotType == SlotType.CHAIN_REFERENCE) {
            ChainedAbility resolved = this.getChainedAbility();
            if (resolved == null) {
                return false;
            }
            this.inlineChain = new ChainedAbility();
            NBTTagCompound chainNbt = resolved.writeNBT(true);
            chainNbt.func_74778_a("Id", UUID.randomUUID().toString());
            this.inlineChain.readNBT(chainNbt);
            this.slotType = SlotType.INLINE_CHAIN;
            this.referenceId = null;
            this.cachedAction = null;
            this.cachedRevision = -1;
            return true;
        }
        return false;
    }

    public NBTTagCompound writeNBT(boolean saveScripts) {
        switch (this.slotType) {
            case INLINE_CHAIN: {
                NBTTagCompound nbt = new NBTTagCompound();
                if (this.inlineChain != null) {
                    nbt.func_74782_a("InlineChain", (NBTBase)this.inlineChain.writeNBT(saveScripts));
                }
                return nbt;
            }
            case CHAIN_REFERENCE: {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.func_74778_a("ChainReference", this.referenceId);
                if (this.enabledOverride != null) {
                    nbt.func_74757_a("RefEnabled", this.enabledOverride.booleanValue());
                }
                return nbt;
            }
            case ABILITY_REFERENCE: {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.func_74778_a("Reference", this.referenceId);
                if (this.enabledOverride != null) {
                    nbt.func_74757_a("RefEnabled", this.enabledOverride.booleanValue());
                }
                return nbt;
            }
        }
        return this.inlineAbility != null ? this.inlineAbility.writeNBT(saveScripts) : new NBTTagCompound();
    }

    public static AbilityAction fromNBT(NBTTagCompound nbt) {
        if (nbt == null) {
            return null;
        }
        if (nbt.func_74764_b("InlineChain")) {
            ChainedAbility chain = new ChainedAbility();
            chain.readNBT(nbt.func_74775_l("InlineChain"));
            return AbilityAction.inlineChain(chain);
        }
        if (nbt.func_74764_b("ChainReference")) {
            AbilityAction slot = AbilityAction.chainReference(nbt.func_74779_i("ChainReference"));
            if (nbt.func_74764_b("RefEnabled")) {
                slot.enabledOverride = nbt.func_74767_n("RefEnabled");
            }
            return slot;
        }
        if (nbt.func_74764_b("Reference")) {
            AbilityAction slot = AbilityAction.abilityReference(nbt.func_74779_i("Reference"));
            if (nbt.func_74764_b("RefEnabled")) {
                slot.enabledOverride = nbt.func_74767_n("RefEnabled");
            }
            return slot;
        }
        if (AbilityController.Instance == null) {
            return null;
        }
        Ability ability = AbilityController.Instance.fromNBT(nbt);
        if (ability == null) {
            return null;
        }
        return AbilityAction.inline(ability);
    }

    public static enum SlotType {
        INLINE_ABILITY,
        ABILITY_REFERENCE,
        CHAIN_REFERENCE,
        INLINE_CHAIN;

    }
}

