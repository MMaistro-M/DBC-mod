/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

import noppes.npcs.api.INbt;
import noppes.npcs.api.ability.IAbilityAction;
import noppes.npcs.api.handler.data.IMagicData;

public interface IAbility
extends IAbilityAction {
    public String getId();

    public void setId(String var1);

    @Override
    public String getName();

    public void setName(String var1);

    public String getDisplayName();

    public void setDisplayName(String var1);

    public String getTypeId();

    @Override
    public boolean isEnabled();

    public void setEnabled(boolean var1);

    @Override
    public int getWeight();

    public void setWeight(int var1);

    @Override
    public float getMinRange();

    public void setMinRange(float var1);

    @Override
    public float getMaxRange();

    public void setMaxRange(float var1);

    @Override
    public int getCooldownTicks();

    public void setCooldownTicks(int var1);

    public int getWindUpTicks();

    public void setWindUpTicks(int var1);

    public int getDazedTicks();

    public void setDazedTicks(int var1);

    public boolean isInterruptible();

    public void setInterruptible(boolean var1);

    public boolean isIgnoreIFrames();

    public void setIgnoreIFrames(boolean var1);

    public int getLockMovementType();

    public void setLockMovementType(int var1);

    public int getRotationModeType();

    public void setRotationModeType(int var1);

    public int getRotationPhaseType();

    public void setRotationPhaseType(int var1);

    public float getTrackSpeedValue();

    public void setTrackSpeedValue(float var1);

    public boolean isMovementLockedDuringWindup();

    public boolean isMovementLockedDuringActive();

    public boolean isRotationLockedDuringWindup();

    public boolean isRotationLockedDuringActive();

    public boolean isExecuting();

    public int getPhaseInt();

    public int getCurrentTick();

    public int getAllowedByType();

    public void setAllowedByType(int var1);

    public boolean isIgnoreCooldown();

    public void setIgnoreCooldown(boolean var1);

    public boolean isPerAbilityCooldown();

    public void setPerAbilityCooldown(boolean var1);

    public INbt getNbt();

    public void setNbt(INbt var1);

    public boolean isBurstEnabled();

    public void setBurstEnabled(boolean var1);

    public int getBurstAmount();

    public void setBurstAmount(int var1);

    public int getBurstDelay();

    public void setBurstDelay(int var1);

    public boolean isBurstReplayAnimations();

    public void setBurstReplayAnimations(boolean var1);

    public boolean isBurstOverlap();

    public void setBurstOverlap(boolean var1);

    public boolean isToggleable();

    public int getToggleStates();

    public void setToggleStates(int var1);

    public String getToggleStateLabel(int var1);

    public IMagicData getMagicData();
}

