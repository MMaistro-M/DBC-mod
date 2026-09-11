/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityThrowable
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.entity.IThrowable;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptThrowable<T extends EntityThrowable>
extends ScriptEntity<T>
implements IThrowable {
    public ScriptThrowable(T entity) {
        super(entity);
    }

    @Override
    public IEntityLivingBase getThrower() {
        if (((EntityThrowable)this.entity).func_85052_h() != null) {
            return (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)((EntityThrowable)this.entity).func_85052_h());
        }
        return null;
    }

    @Override
    public void kill() {
        ((EntityThrowable)this.entity).func_70106_y();
    }

    @Override
    public int getType() {
        return 11;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 11 || super.typeOf(type);
    }
}

