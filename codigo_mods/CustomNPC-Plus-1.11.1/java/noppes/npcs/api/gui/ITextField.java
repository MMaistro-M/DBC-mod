/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.gui;

import noppes.npcs.api.gui.ICustomGuiComponent;

public interface ITextField
extends ICustomGuiComponent {
    public int getWidth();

    public int getHeight();

    public ITextField setSize(int var1, int var2);

    public String getText();

    public ITextField setText(String var1);
}

