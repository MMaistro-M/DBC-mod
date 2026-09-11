/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.handler.data.IFramePart;

public interface IFrame {
    public IFramePart[] getParts();

    public IFrame addPart(IFramePart var1);

    public IFrame removePart(String var1);

    public IFrame removePart(int var1);

    public IFrame clearParts();

    public int getDuration();

    public IFrame setDuration(int var1);

    public boolean isCustomized();

    public IFrame setCustomized(boolean var1);

    public float getSpeed();

    public IFrame setSpeed(float var1);

    public byte smoothType();

    public IFrame setSmooth(byte var1);
}

