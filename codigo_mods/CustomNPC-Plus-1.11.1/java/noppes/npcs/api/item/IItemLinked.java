/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.item;

import noppes.npcs.api.handler.data.ILinkedItem;
import noppes.npcs.api.item.IItemCustomizable;

public interface IItemLinked
extends IItemCustomizable {
    public ILinkedItem getLinkedItem();

    public void setDurabilityValue(float var1);
}

