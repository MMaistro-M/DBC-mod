/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.ILinkedItem;
import noppes.npcs.api.item.IItemStack;

public interface ILinkedItemHandler {
    public ILinkedItem createItem(String var1);

    public IItemStack createItemStack(int var1);

    public void add(ILinkedItem var1);

    public ILinkedItem remove(int var1);

    public ILinkedItem get(int var1);

    public boolean contains(int var1);

    public boolean contains(ILinkedItem var1);
}

