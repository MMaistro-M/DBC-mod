/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.event;

import noppes.npcs.api.entity.IEnergyBarrier;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.event.ICustomNPCsEvent;

public interface IEnergyBarrierEvent
extends ICustomNPCsEvent {
    public IEnergyBarrier getBarrier();

    public IEntity getOwner();

    public static interface DestroyedEvent
    extends IEnergyBarrierEvent {
    }

    public static interface HitEvent
    extends IEnergyBarrierEvent {
        public IEnergyProjectile getProjectile();

        public float getDamage();

        public void setDamage(float var1);
    }

    public static interface UpdateEvent
    extends IEnergyBarrierEvent {
    }

    public static interface SpawnedEvent
    extends IEnergyBarrierEvent {
    }
}

