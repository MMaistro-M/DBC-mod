/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityArrow
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.projectile.EntityArrow;
import noppes.npcs.api.entity.IArrow;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptArrow<T extends EntityArrow>
extends ScriptEntity<T>
implements IArrow {
    public ScriptArrow(T entity) {
        super(entity);
    }

    @Override
    public IEntity getShooter() {
        if (((EntityArrow)this.entity).field_70250_c != null) {
            return NpcAPI.Instance().getIEntity(((EntityArrow)this.entity).field_70250_c);
        }
        return null;
    }

    @Override
    public double getDamage() {
        return ((EntityArrow)this.entity).func_70242_d();
    }

    @Override
    public void kill() {
        ((EntityArrow)this.entity).func_70106_y();
    }

    @Override
    public int getType() {
        return 10;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 10 || super.typeOf(type);
    }
}

