/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IAttributeDefinition;
import noppes.npcs.api.handler.data.IPlayerAttributes;

public interface IAttributeHandler {
    public IPlayerAttributes getPlayerAttributes(IPlayer var1);

    public IAttributeDefinition getAttributeDefinition(String var1);

    public IAttributeDefinition[] getAllAttributesArray();
}

