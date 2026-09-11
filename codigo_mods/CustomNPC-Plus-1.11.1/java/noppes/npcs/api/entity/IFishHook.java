/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityFishHook
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.projectile.EntityFishHook;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;

public interface IFishHook<T extends EntityFishHook>
extends IEntity<T> {
    public IPlayer getCaster();

    public void kill();
}

