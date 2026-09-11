/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityThrowable
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.projectile.EntityThrowable;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;

public interface IThrowable<T extends EntityThrowable>
extends IEntity<T> {
    public IEntityLivingBase getThrower();

    public void kill();
}

