/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityVillager
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.passive.EntityVillager;
import noppes.npcs.api.entity.IEntityLiving;
import noppes.npcs.api.entity.IEntityLivingBase;

public interface IVillager<T extends EntityVillager>
extends IEntityLiving<T> {
    public int getProfession();

    public boolean getIsTrading();

    public IEntityLivingBase getCustomer();
}

