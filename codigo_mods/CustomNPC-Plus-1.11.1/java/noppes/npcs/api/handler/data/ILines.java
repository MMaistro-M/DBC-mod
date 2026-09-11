/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.handler.data.ILine;

public interface ILines {
    public ILine createLine(String var1);

    public ILine getLine(boolean var1);

    public ILine getLine(int var1);

    public void setLine(int var1, ILine var2);

    public void removeLine(int var1);

    public void clear();

    public boolean isEmpty();

    public Integer[] getKeys();
}

