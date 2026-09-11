/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package kamkeel.npcs.controllers.data.ability;

import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.enums.AbilityPhase;
import net.minecraft.entity.EntityLivingBase;

public class ConcurrentSlot {
    private final Ability ability;
    private boolean started = false;
    private boolean completed = false;

    public ConcurrentSlot(Ability ability) {
        this.ability = ability;
    }

    public Ability getAbility() {
        return this.ability;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void start(EntityLivingBase caster, EntityLivingBase target) {
        this.ability.setWindUpTicks(0);
        this.ability.start(target);
        this.started = true;
        if (this.ability.getPhase() == AbilityPhase.ACTIVE) {
            this.ability.onExecute(caster, target);
        }
        if (this.ability.getPhase() == AbilityPhase.IDLE) {
            this.completed = true;
        }
    }

    public void tick(EntityLivingBase caster, EntityLivingBase target) {
        if (this.completed || !this.started) {
            return;
        }
        AbilityPhase oldPhase = this.ability.getPhase();
        boolean phaseChanged = this.ability.tick();
        AbilityPhase newPhase = this.ability.getPhase();
        switch (newPhase) {
            case ACTIVE: {
                if (phaseChanged && oldPhase == AbilityPhase.BURST_DELAY) {
                    this.ability.onExecute(caster, target);
                    if (this.ability.getPhase() == AbilityPhase.IDLE) {
                        this.completed = true;
                        return;
                    }
                }
                this.ability.onActiveTick(caster, target, this.ability.getCurrentTick());
                if (this.ability.getPhase() != AbilityPhase.IDLE) break;
                this.completed = true;
                break;
            }
            case BURST_DELAY: {
                break;
            }
            case WINDUP: {
                break;
            }
            case IDLE: {
                this.completed = true;
                break;
            }
            case DAZED: {
                this.completed = true;
            }
        }
    }

    public void interrupt() {
        if (!this.completed) {
            this.ability.interrupt();
            this.completed = true;
        }
    }
}

