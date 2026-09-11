/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api;

import noppes.npcs.api.entity.IEntity;

public interface ITelegraphInstance {
    public String getInstanceId();

    public double getX();

    public double getY();

    public double getZ();

    public float getYaw();

    public void setPosition(double var1, double var3, double var5);

    public void followEntity(IEntity var1);

    public void stopFollowing();

    public boolean isFollowing();

    public int getRemainingTicks();

    public int getTotalTicks();

    public float getProgress();

    public boolean isWarning();

    public void remove();

    public void lockPosition();
}

