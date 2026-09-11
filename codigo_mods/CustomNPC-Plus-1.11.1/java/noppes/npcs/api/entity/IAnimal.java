/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityAnimal
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.passive.EntityAnimal;
import noppes.npcs.api.entity.IEntityLiving;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.item.IItemStack;

public interface IAnimal<T extends EntityAnimal>
extends IEntityLiving<T> {
    public boolean isBreedingItem(IItemStack var1);

    public boolean interact(IPlayer var1);

    public void setFollowPlayer(IPlayer var1);

    public IPlayer followingPlayer();

    public boolean isInLove();

    public void resetInLove();

    public boolean canMateWith(IAnimal var1);
}

