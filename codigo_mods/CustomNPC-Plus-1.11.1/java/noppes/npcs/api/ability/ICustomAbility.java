/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.ability;

import noppes.npcs.api.ability.IAbility;

public interface ICustomAbility
extends IAbility {
    public int getDurationTicks();

    public void setDurationTicks(int var1);

    public int getTelegraphShapeType();

    public void setTelegraphShapeType(int var1);

    public int getTargetingModeType();

    public void setTargetingModeType(int var1);

    public int getTelegraphActiveTicks();

    public void setTelegraphActiveTicks(int var1);

    public boolean isSyncTelegraphWithDuration();

    public void setSyncTelegraphWithDuration(boolean var1);

    public float getTelegraphRadius();

    public void setTelegraphRadius(float var1);

    public float getTelegraphInnerRadius();

    public void setTelegraphInnerRadius(float var1);

    public float getTelegraphLength();

    public void setTelegraphLength(float var1);

    public float getTelegraphWidth();

    public void setTelegraphWidth(float var1);

    public float getTelegraphAngle();

    public void setTelegraphAngle(float var1);
}

