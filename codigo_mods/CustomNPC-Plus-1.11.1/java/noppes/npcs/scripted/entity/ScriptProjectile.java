/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IProjectile;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptProjectile<T extends EntityProjectile>
extends ScriptEntity<T>
implements IProjectile {
    protected T entity;

    public ScriptProjectile(T entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public IItemStack getItem() {
        return NpcAPI.Instance().getIItemStack(((EntityProjectile)((Object)this.entity)).getItemDisplay());
    }

    @Override
    public void setItem(IItemStack item) {
        if (item == null) {
            ((EntityProjectile)((Object)this.entity)).setThrownItem(null);
        } else {
            ((EntityProjectile)((Object)this.entity)).setThrownItem(item.getMCItemStack());
        }
    }

    @Override
    public boolean getHasGravity() {
        return ((EntityProjectile)((Object)this.entity)).hasGravity();
    }

    @Override
    public void setHasGravity(boolean bo) {
        ((EntityProjectile)((Object)this.entity)).setHasGravity(bo);
    }

    @Override
    public int getAccuracy() {
        return ((EntityProjectile)((Object)this.entity)).accuracy;
    }

    @Override
    public void setAccuracy(int accuracy) {
        ((EntityProjectile)((Object)this.entity)).accuracy = accuracy;
    }

    @Override
    public void setHeading(IEntity entity) {
        this.setHeading(entity.getX(), ((Entity)entity.getMCEntity()).field_70121_D.field_72338_b + entity.getHeight() / 2.0, entity.getZ());
    }

    @Override
    public void setHeading(double x, double y, double z) {
        float varF = ((EntityProjectile)((Object)this.entity)).hasGravity() ? (float)Math.sqrt((x -= ((EntityProjectile)((Object)this.entity)).field_70165_t) * x + (z -= ((EntityProjectile)((Object)this.entity)).field_70161_v) * z) : 0.0f;
        float angle = ((EntityProjectile)((Object)this.entity)).getAngleForXYZ(x, y -= ((EntityProjectile)((Object)this.entity)).field_70163_u, z, varF, false);
        float acc = (float)(20.0 - Math.floor((float)((EntityProjectile)((Object)this.entity)).accuracy / 5.0f));
        ((EntityProjectile)((Object)this.entity)).func_70186_c(x, y, z, angle, acc);
    }

    @Override
    public void setHeading(float yaw, float pitch) {
        ((EntityProjectile)((Object)this.entity)).field_70126_B = ((EntityProjectile)((Object)this.entity)).field_70177_z = yaw;
        ((EntityProjectile)((Object)this.entity)).field_70127_C = ((EntityProjectile)((Object)this.entity)).field_70125_A = pitch;
        double varX = -MathHelper.func_76126_a((float)(yaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(pitch / 180.0f * (float)Math.PI));
        double varZ = MathHelper.func_76134_b((float)(yaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(pitch / 180.0f * (float)Math.PI));
        double varY = -MathHelper.func_76126_a((float)(pitch / 180.0f * (float)Math.PI));
        float acc = (float)(20.0 - Math.floor((float)((EntityProjectile)((Object)this.entity)).accuracy / 5.0f));
        ((EntityProjectile)((Object)this.entity)).func_70186_c(varX, varY, varZ, -pitch, acc);
    }

    @Override
    public int getType() {
        return 7;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 7 || super.typeOf(type);
    }

    @Override
    public IEntity getThrower() {
        return NpcAPI.Instance().getIEntity((Entity)((EntityProjectile)((Object)this.entity)).func_85052_h());
    }

    @Override
    public void enableEvents() {
        if (ScriptContainer.Current == null) {
            throw new CustomNPCsException("Can only be called during scripts", new Object[0]);
        }
        if (!((EntityProjectile)((Object)this.entity)).scripts.contains(ScriptContainer.Current)) {
            ((EntityProjectile)((Object)this.entity)).scripts.add(ScriptContainer.Current);
        }
    }
}

