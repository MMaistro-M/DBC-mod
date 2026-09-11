/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.event;

import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.ability.IChainedAbility;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.ICustomNPCsEvent;

public interface IChainEvent
extends ICustomNPCsEvent {
    public IEntityLivingBase getEntity();

    public IPlayer getPlayer();

    public ICustomNpc<?> getNpc();

    public boolean isNPC();

    public IChainedAbility getChain();

    public IEntityLivingBase getTarget();

    public int getEntryIndex();

    public static interface InterruptEvent
    extends IChainEvent {
        public IDamageSource getDamageSource();

        public float getDamage();
    }

    public static interface CompleteEvent
    extends IChainEvent {
    }

    public static interface NextEvent
    extends IChainEvent {
    }

    public static interface StartEvent
    extends IChainEvent {
    }
}

