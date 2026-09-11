/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.gui;

import noppes.npcs.api.gui.ICustomGuiComponent;

public interface IButton
extends ICustomGuiComponent {
    public int getWidth();

    public int getHeight();

    public IButton setSize(int var1, int var2);

    public String getLabel();

    public IButton setLabel(String var1);

    public String getTexture();

    public boolean hasTexture();

    public IButton setTexture(String var1);

    public int getTextureX();

    public int getTextureY();

    public IButton setTextureOffset(int var1, int var2);

    public void setScale(float var1);

    public float getScale();

    public void setEnabled(boolean var1);

    public boolean isEnabled();
}

