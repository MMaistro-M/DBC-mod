/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.ability.IAbility;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.INpcEvent;

public interface IAbilityEvent
extends INpcEvent {
    public IEntityLivingBase getEntity();

    public IPlayer getPlayer();

    public boolean isNPC();

    public IAbility getAbility();

    public IEntityLivingBase getTarget();

    public static interface TickEvent
    extends IAbilityEvent {
        public int getAbilityPhase();

        public int getTick();
    }

    @Cancelable
    public static interface HitEvent
    extends IAbilityEvent {
        public IEntityLivingBase getHitEntity();

        public float getDamage();

        public void setDamage(float var1);

        public float getKnockback();

        public void setKnockback(float var1);

        public float getKnockbackUp();

        public void setKnockbackUp(float var1);
    }

    public static interface ToggleUpdateEvent
    extends IAbilityEvent {
        public int getTick();

        public int getState();

        public boolean isEnabled();

        public void setEnabled(boolean var1);
    }

    @Cancelable
    public static interface ToggleEvent
    extends IAbilityEvent {
        public boolean isTogglingOn();

        public int getOldState();

        public int getNewState();
    }

    public static interface CompleteEvent
    extends IAbilityEvent {
    }

    public static interface InterruptEvent
    extends IAbilityEvent {
        public IDamageSource getDamageSource();

        public float getDamage();
    }

    @Cancelable
    public static interface ExecuteEvent
    extends IAbilityEvent {
    }

    @Cancelable
    public static interface StartEvent
    extends IAbilityEvent {
    }
}

