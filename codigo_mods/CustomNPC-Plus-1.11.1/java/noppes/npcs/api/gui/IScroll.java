/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.gui;

import noppes.npcs.api.gui.ICustomGuiComponent;

public interface IScroll
extends ICustomGuiComponent {
    public int getWidth();

    public int getHeight();

    public IScroll setSize(int var1, int var2);

    public String[] getList();

    public IScroll setList(String[] var1);

    public int getDefaultSelection();

    public IScroll setDefaultSelection(int var1);

    public boolean isMultiSelect();

    public IScroll setMultiSelect(boolean var1);
}

