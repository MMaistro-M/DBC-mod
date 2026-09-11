/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ICustomAttribute;

public interface IPlayerAttributes {
    public void recalculate(IPlayer var1);

    public ICustomAttribute[] getAttributes();

    public float getAttributeValue(String var1);

    public boolean hasAttribute(String var1);

    public ICustomAttribute getAttribute(String var1);
}

