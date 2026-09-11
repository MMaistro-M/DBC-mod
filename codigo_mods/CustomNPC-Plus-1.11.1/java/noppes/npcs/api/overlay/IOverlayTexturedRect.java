/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.overlay;

import noppes.npcs.api.overlay.ICustomOverlayComponent;

public interface IOverlayTexturedRect
extends ICustomOverlayComponent {
    public String getTexture();

    public IOverlayTexturedRect setTexture(String var1);

    public int getWidth();

    public int getHeight();

    public IOverlayTexturedRect setSize(int var1, int var2);

    public float getScale();

    public IOverlayTexturedRect setScale(float var1);

    public int getTextureX();

    public int getTextureY();

    public IOverlayTexturedRect setTextureOffset(int var1, int var2);
}

