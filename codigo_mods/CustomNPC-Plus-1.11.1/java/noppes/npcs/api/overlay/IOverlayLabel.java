/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.overlay;

import noppes.npcs.api.overlay.ICustomOverlayComponent;

public interface IOverlayLabel
extends ICustomOverlayComponent {
    public String getText();

    public IOverlayLabel setText(String var1);

    public int getWidth();

    public int getHeight();

    public IOverlayLabel setSize(int var1, int var2);

    public float getScale();

    public IOverlayLabel setScale(float var1);

    public boolean getShadow();

    public void setShadow(boolean var1);
}

