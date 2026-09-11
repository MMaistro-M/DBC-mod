/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability.type;

import noppes.npcs.api.ability.IAbility;

public interface IAbilityTeleport
extends IAbility {
    public int getMode();

    public void setMode(int var1);

    public int getBlinkCount();

    public void setBlinkCount(int var1);

    public int getBlinkDelayTicks();

    public void setBlinkDelayTicks(int var1);

    public float getBlinkRadius();

    public void setBlinkRadius(float var1);

    public float getBehindDistance();

    public void setBehindDistance(float var1);

    public boolean isRequireLineOfSight();

    public void setRequireLineOfSight(boolean var1);

    public boolean isDamageAtStart();

    public void setDamageAtStart(boolean var1);

    public boolean isDamageAtEnd();

    public void setDamageAtEnd(boolean var1);

    public float getDamage();

    public void setDamage(float var1);

    public float getDamageRadius();

    public void setDamageRadius(float var1);
}

