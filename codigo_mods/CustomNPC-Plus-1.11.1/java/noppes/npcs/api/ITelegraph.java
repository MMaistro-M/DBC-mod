/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

import noppes.npcs.api.ITelegraphInstance;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

public interface ITelegraph {
    public String getType();

    public void setType(String var1);

    public float getRadius();

    public void setRadius(float var1);

    public float getInnerRadius();

    public void setInnerRadius(float var1);

    public float getLength();

    public void setLength(float var1);

    public float getWidth();

    public void setWidth(float var1);

    public float getAngle();

    public void setAngle(float var1);

    public int getDuration();

    public void setDuration(int var1);

    public int getColor();

    public void setColor(int var1);

    public int getWarningColor();

    public void setWarningColor(int var1);

    public int getWarningStartTick();

    public void setWarningStartTick(int var1);

    public boolean isAnimated();

    public void setAnimated(boolean var1);

    public float getHeightOffset();

    public void setHeightOffset(float var1);

    public ITelegraphInstance spawn(IWorld var1, double var2, double var4, double var6);

    public ITelegraphInstance spawn(IWorld var1, double var2, double var4, double var6, float var8);

    public ITelegraphInstance spawn(IEntity var1);

    public ITelegraphInstance spawn(IEntity var1, float var2);
}

