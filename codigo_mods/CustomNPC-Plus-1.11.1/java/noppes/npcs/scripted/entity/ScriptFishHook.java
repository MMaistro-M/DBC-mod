/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityFishHook
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFishHook;
import noppes.npcs.api.entity.IFishHook;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptFishHook<T extends EntityFishHook>
extends ScriptEntity<T>
implements IFishHook {
    public ScriptFishHook(T entity) {
        super(entity);
    }

    @Override
    public IPlayer getCaster() {
        if (((EntityFishHook)this.entity).field_146042_b != null) {
            return (IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityFishHook)this.entity).field_146042_b);
        }
        return null;
    }

    @Override
    public void kill() {
        ((EntityFishHook)this.entity).func_70106_y();
    }

    @Override
    public int getType() {
        return 12;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 12 || super.typeOf(type);
    }
}

