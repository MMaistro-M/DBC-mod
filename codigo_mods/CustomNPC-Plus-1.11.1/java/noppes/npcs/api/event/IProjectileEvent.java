/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.event;

import noppes.npcs.api.IBlock;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IProjectile;
import noppes.npcs.api.event.ICustomNPCsEvent;

public interface IProjectileEvent
extends ICustomNPCsEvent {
    public IProjectile getProjectile();

    public IEntity getSource();

    public static interface ImpactEvent
    extends IProjectileEvent {
        public int getType();

        public IEntity getEntity();

        public IBlock getBlock();
    }

    public static interface UpdateEvent
    extends IProjectileEvent {
    }
}

