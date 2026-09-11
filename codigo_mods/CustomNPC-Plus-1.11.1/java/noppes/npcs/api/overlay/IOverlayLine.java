/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.overlay;

import noppes.npcs.api.overlay.ICustomOverlayComponent;

public interface IOverlayLine
extends ICustomOverlayComponent {
    public int getX1();

    public int getY1();

    public int getX2();

    public int getY2();

    public int getThickness();

    public void setX1(int var1);

    public void setY1(int var1);

    public void setX2(int var1);

    public void setY2(int var1);

    public void setThickness(int var1);
}

