/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.gui;

import noppes.npcs.api.gui.ICustomGuiComponent;

public interface ILabel
extends ICustomGuiComponent {
    public String getText();

    public ILabel setText(String var1);

    public int getWidth();

    public int getHeight();

    public ILabel setSize(int var1, int var2);

    public float getScale();

    public ILabel setScale(float var1);

    public boolean getShadow();

    public void setShadow(boolean var1);
}

