/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface IFramePart {
    public String getName();

    public int getPartId();

    public IFramePart setPart(String var1);

    public IFramePart setPart(int var1);

    public float[] getRotations();

    public IFramePart setRotations(float[] var1);

    public float[] getPivots();

    public IFramePart setPivots(float[] var1);

    public boolean isCustomized();

    public IFramePart setCustomized(boolean var1);

    public float getSpeed();

    public IFramePart setSpeed(float var1);

    public byte isSmooth();

    public IFramePart setSmooth(byte var1);
}

