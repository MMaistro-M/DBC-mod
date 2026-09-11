/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityAbilityOrb;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.entity.IEnergyOrb;
import noppes.npcs.scripted.entity.ScriptEnergyProjectile;

public class ScriptEnergyOrb<T extends EntityAbilityOrb>
extends ScriptEnergyProjectile<T>
implements IEnergyOrb {
    public ScriptEnergyOrb(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 14;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 14 || super.typeOf(type);
    }

    @Override
    public int getEnergyType() {
        return 0;
    }

    @Override
    protected void launchFromOwner(EntityLivingBase target) {
        ((EntityAbilityOrb)this.entity).startMoving(target);
    }
}

