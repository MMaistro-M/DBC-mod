/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.jobs;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.IAvailability;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.jobs.IJob;

public interface IJobItemGiver
extends IJob {
    public void setCooldown(int var1);

    public void setCooldownType(int var1);

    public int getCooldownType();

    public void setGivingMethod(int var1);

    public int getGivingMethod();

    public void setLines(String[] var1);

    public String[] getLines();

    public void setAvailability(IAvailability var1);

    public IAvailability getAvailability();

    public void setItem(int var1, IItemStack var2);

    public IItemStack[] getItems();

    public boolean giveItems(IPlayer var1);

    public boolean canPlayerInteract(IPlayer var1);
}

