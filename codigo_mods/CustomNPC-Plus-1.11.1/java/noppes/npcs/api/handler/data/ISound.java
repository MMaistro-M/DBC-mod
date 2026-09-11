/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.IPos;
import noppes.npcs.api.entity.IEntity;

public interface ISound {
    public void setEntity(IEntity var1);

    public IEntity getEntity();

    public void setRepeat(boolean var1);

    public boolean repeats();

    public void setRepeatDelay(int var1);

    public int getRepeatDelay();

    public void setVolume(float var1);

    public float getVolume();

    public void setPitch(float var1);

    public float getPitch();

    public void setPosition(IPos var1);

    public void setPosition(float var1, float var2, float var3);

    public float getX();

    public float getY();

    public float getZ();
}

