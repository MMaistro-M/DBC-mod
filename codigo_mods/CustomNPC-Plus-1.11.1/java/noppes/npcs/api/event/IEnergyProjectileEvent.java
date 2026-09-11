/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.event;

import noppes.npcs.api.IBlock;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.event.ICustomNPCsEvent;

public interface IEnergyProjectileEvent
extends ICustomNPCsEvent {
    public IEnergyProjectile getProjectile();

    public IEntity getOwner();

    public static interface ExpiredEvent
    extends IEnergyProjectileEvent {
    }

    public static interface BlockImpactEvent
    extends IEnergyProjectileEvent {
        public IBlock getBlock();
    }

    public static interface EntityImpactEvent
    extends IEnergyProjectileEvent {
        public IEntity getTarget();

        public float getDamage();

        public void setDamage(float var1);
    }

    public static interface UpdateEvent
    extends IEnergyProjectileEvent {
        public int getTick();
    }

    public static interface FiredEvent
    extends IEnergyProjectileEvent {
    }
}

