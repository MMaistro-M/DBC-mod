/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.scripted.wrapper;

import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.api.ability.IAbility;
import noppes.npcs.api.ability.IPlayerAbilityData;
import noppes.npcs.controllers.data.PlayerAbilityData;
import noppes.npcs.controllers.data.PlayerData;

public class ScriptPlayerAbilityData
implements IPlayerAbilityData {
    private final PlayerAbilityData data;
    private final PlayerData playerData;

    public ScriptPlayerAbilityData(PlayerData playerData) {
        this.playerData = playerData;
        this.data = playerData.abilityData;
    }

    @Override
    public String[] getUnlockedAbilities() {
        return this.data.getUnlockedAbilities();
    }

    @Override
    public void unlockAbility(String key) {
        Ability ability = AbilityController.Instance.peekAbility(key);
        if (ability == null) {
            return;
        }
        if (!ability.getAllowedBy().allowsPlayer()) {
            return;
        }
        String canonicalKey = ability.getId() != null ? ability.getId() : key;
        this.data.unlockAbility(canonicalKey);
    }

    @Override
    public void lockAbility(String key) {
        String canonicalKey = this.resolveCanonicalKey(key);
        this.data.lockAbility(canonicalKey);
    }

    @Override
    public boolean hasUnlockedAbility(String key) {
        String canonicalKey = this.resolveCanonicalKey(key);
        return this.data.hasUnlockedAbility(canonicalKey);
    }

    @Override
    public int getSelectedIndex() {
        return this.data.getSelectedIndex();
    }

    @Override
    public void setSelectedIndex(int index) {
        this.data.setSelectedIndex(index);
    }

    @Override
    public String getSelectedAbilityKey() {
        return this.data.getSelectedAbilityKey();
    }

    @Override
    public void selectNext() {
        this.data.selectNext();
    }

    @Override
    public void selectPrevious() {
        this.data.selectPrevious();
    }

    @Override
    public boolean isExecutingAbility() {
        return this.data.isExecutingAbility();
    }

    @Override
    public IAbility getCurrentAbility() {
        IAbility a = this.data.getCurrentAbility();
        return a != null ? ((Ability)a).deepCopy() : null;
    }

    @Override
    public void interruptCurrentAbility() {
        this.data.interruptCurrentAbility();
    }

    @Override
    public void completeCurrentAbility() {
        this.data.completeCurrentAbility();
    }

    @Override
    public boolean isOnCooldown() {
        return this.data.isOnCooldown();
    }

    @Override
    public boolean isOnCooldown(String key) {
        EntityPlayer player = this.playerData.player;
        if (player == null) {
            return false;
        }
        return this.data.isOnCooldown(key, player);
    }

    @Override
    public void resetCooldown() {
        this.data.resetCooldown();
    }

    @Override
    public void resetCooldown(String key) {
        this.data.resetCooldown(key);
    }

    @Override
    public void resetAllCooldowns() {
        this.data.resetAllCooldowns();
    }

    @Override
    public boolean activateAbility() {
        EntityPlayer player = this.playerData.player;
        if (player == null) {
            return false;
        }
        return this.data.activateAbility(player);
    }

    @Override
    public boolean activateAbility(String key) {
        EntityPlayer player = this.playerData.player;
        if (player == null) {
            return false;
        }
        return this.data.activateAbility(player, key);
    }

    @Override
    public int toggleAbility(String key) {
        return this.data.toggleAbility(key);
    }

    @Override
    public int getToggleState(String key) {
        return this.data.getToggleState(key);
    }

    @Override
    public void setToggleState(String key, int state) {
        this.data.setToggleState(key, state);
    }

    @Override
    public boolean isAbilityToggled(String key) {
        return this.data.isAbilityToggled(key);
    }

    private String resolveCanonicalKey(String key) {
        Ability ability = AbilityController.Instance.peekAbility(key);
        if (ability != null && ability.getId() != null) {
            return ability.getId();
        }
        return key;
    }
}

