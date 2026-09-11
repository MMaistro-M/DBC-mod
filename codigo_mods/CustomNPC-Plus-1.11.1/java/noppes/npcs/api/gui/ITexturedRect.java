/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.gui;

import noppes.npcs.api.gui.ICustomGuiComponent;

public interface ITexturedRect
extends ICustomGuiComponent {
    public String getTexture();

    public ITexturedRect setTexture(String var1);

    public int getWidth();

    public int getHeight();

    public ITexturedRect setSize(int var1, int var2);

    public float getScale();

    public ITexturedRect setScale(float var1);

    public int getTextureX();

    public int getTextureY();

    public ITexturedRect setTextureOffset(int var1, int var2);

    public boolean isAnimated();

    public int getFrameCount();

    public int getFrameTime();

    public ITexturedRect setAnimation(int var1, int var2);
}

