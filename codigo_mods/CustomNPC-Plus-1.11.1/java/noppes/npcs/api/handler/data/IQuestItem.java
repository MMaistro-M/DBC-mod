/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.handler.data.IQuestInterface;

public interface IQuestItem
extends IQuestInterface {
    public void setLeaveItems(boolean var1);

    public boolean getLeaveItems();

    public void setIgnoreDamage(boolean var1);

    public boolean getIgnoreDamage();

    public void setIgnoreNbt(boolean var1);

    public boolean getIgnoreNbt();
}

