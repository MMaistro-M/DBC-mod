/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.wrapper;

import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.AbilityAction;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.DataAbilities;
import noppes.npcs.api.ability.IAbility;
import noppes.npcs.api.ability.IDataAbilities;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.entity.EntityNPCInterface;

public class ScriptDataAbilities
implements IDataAbilities {
    private final DataAbilities data;
    private final EntityNPCInterface npc;

    public ScriptDataAbilities(EntityNPCInterface npc) {
        this.npc = npc;
        this.data = npc.abilities;
    }

    @Override
    public boolean isEnabled() {
        return this.data.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.data.enabled = enabled;
    }

    @Override
    public IAbility[] getAbilities() {
        List<Ability> live = this.data.getAbilities();
        IAbility[] copies = new IAbility[live.size()];
        for (int i = 0; i < live.size(); ++i) {
            copies[i] = live.get(i).deepCopy();
        }
        return copies;
    }

    @Override
    public void addAbility(IAbility ability) {
        if (ability instanceof Ability) {
            Ability a = (Ability)ability;
            if (!a.getAllowedBy().allowsNpc()) {
                return;
            }
            this.data.addAbility(a);
        }
    }

    @Override
    public void addAbilityReference(String key) {
        Ability ability = AbilityController.Instance.peekAbility(key);
        if (ability == null) {
            return;
        }
        if (!ability.getAllowedBy().allowsNpc()) {
            return;
        }
        String canonicalKey = ability.getId() != null ? ability.getId() : key;
        this.data.addAbilityReference(canonicalKey);
    }

    @Override
    public void removeAbility(String abilityId) {
        this.data.removeAbility(abilityId);
    }

    @Override
    public IAbility getAbility(String abilityId) {
        Ability a = this.data.getAbility(abilityId);
        return a != null ? a.deepCopy() : null;
    }

    @Override
    public boolean hasAbility(String abilityId) {
        return this.data.getAbility(abilityId) != null;
    }

    @Override
    public boolean isAbilityReference(String abilityId) {
        List<AbilityAction> slots = this.data.getAbilityActions();
        for (int i = 0; i < slots.size(); ++i) {
            Ability a;
            AbilityAction slot = slots.get(i);
            if (slot.isAbilityReference()) {
                if (!slot.getReferenceId().equals(abilityId)) continue;
                return true;
            }
            if (slot.isReference() || (a = slot.getAbility()) == null || !abilityId.equals(a.getId())) continue;
            return false;
        }
        return false;
    }

    @Override
    public boolean convertToInline(String abilityId) {
        List<AbilityAction> slots = this.data.getAbilityActions();
        for (int i = 0; i < slots.size(); ++i) {
            AbilityAction slot = slots.get(i);
            if (!slot.isAbilityReference() || !slot.getReferenceId().equals(abilityId)) continue;
            return this.data.convertToInline(i);
        }
        return false;
    }

    @Override
    public void clearAbilities() {
        this.data.clearAbilities();
    }

    @Override
    public IAbility getCurrentAbility() {
        Ability a = this.data.getCurrentAbility();
        return a != null ? a.deepCopy() : null;
    }

    @Override
    public IAbility getSourceAbility(String abilityId) {
        return this.data.getAbility(abilityId);
    }

    @Override
    public IAbility getSourceCurrentAbility() {
        return this.data.getCurrentAbility();
    }

    @Override
    public boolean isExecutingAbility() {
        return this.data.isExecutingAbility();
    }

    @Override
    public void interruptCurrentAbility() {
        this.data.interruptCurrentAbility(null, 0.0f);
    }

    @Override
    public void completeCurrentAbility() {
        this.data.completeCurrentAbility();
    }

    @Override
    public int getGlobalCooldown() {
        return (int)this.data.getRemainingCooldown();
    }

    @Override
    public void setGlobalCooldown(int ticks) {
        if (ticks <= 0) {
            this.data.resetCooldown();
        } else {
            this.data.setCooldownEndTime(this.npc.field_70170_p.func_82737_E() + (long)ticks);
        }
    }

    @Override
    public void resetCooldowns() {
        this.data.resetCooldown();
    }

    @Override
    public boolean forceStartAbility(String abilityId) {
        return this.forceStartAbility(abilityId, null);
    }

    @Override
    public boolean forceStartAbility(String abilityId, Object target) {
        Ability ability = this.data.getAbility(abilityId);
        if (ability == null) {
            return false;
        }
        EntityLivingBase targetEntity = this.resolveTarget(target);
        return this.data.forceStartAbility(ability, targetEntity);
    }

    @Override
    public boolean executeAbility(String key) {
        return this.executeAbility(key, null);
    }

    @Override
    public boolean executeAbility(String key, Object target) {
        Ability ability = AbilityController.Instance.peekAbility(key);
        if (ability == null) {
            return false;
        }
        if (!ability.getAllowedBy().allowsNpc()) {
            return false;
        }
        EntityLivingBase targetEntity = this.resolveTarget(target);
        return this.data.executeAbility(key, targetEntity);
    }

    @Override
    public IAbility createAbility(String typeId) {
        if (AbilityController.Instance == null) {
            return null;
        }
        return AbilityController.Instance.create(typeId);
    }

    private EntityLivingBase resolveTarget(Object target) {
        Object mcEntity;
        if (target == null) {
            return this.npc.func_70638_az();
        }
        if (target instanceof EntityLivingBase) {
            return (EntityLivingBase)target;
        }
        if (target instanceof IEntityLivingBase && (mcEntity = ((IEntityLivingBase)target).getMCEntity()) instanceof EntityLivingBase) {
            return (EntityLivingBase)mcEntity;
        }
        return null;
    }
}

