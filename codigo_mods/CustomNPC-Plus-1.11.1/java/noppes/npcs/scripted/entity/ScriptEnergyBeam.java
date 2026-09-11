/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.scripted.entity;

import kamkeel.npcs.entity.EntityAbilityBeam;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.entity.IEnergyBeam;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.scripted.entity.ScriptEnergyProjectile;

public class ScriptEnergyBeam<T extends EntityAbilityBeam>
extends ScriptEnergyProjectile<T>
implements IEnergyBeam {
    public ScriptEnergyBeam(T entity) {
        super(entity);
    }

    @Override
    public int getType() {
        return 15;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 15 || super.typeOf(type);
    }

    @Override
    public int getEnergyType() {
        return 1;
    }

    @Override
    public float getBeamWidth() {
        return ((EntityAbilityBeam)this.entity).getBeamWidth();
    }

    @Override
    public void setBeamWidth(float width) {
        ((EntityAbilityBeam)this.entity).setBeamWidth(width);
    }

    @Override
    public float getHeadSize() {
        return ((EntityAbilityBeam)this.entity).getHeadSize();
    }

    @Override
    public void setHeadSize(float size) {
        ((EntityAbilityBeam)this.entity).setHeadSize(size);
    }

    @Override
    public boolean isAttachedToOwner() {
        return ((EntityAbilityBeam)this.entity).isAttachedToOwner();
    }

    @Override
    public void setAttachedToOwner(boolean attached) {
        ((EntityAbilityBeam)this.entity).setAttachedToOwner(attached);
    }

    @Override
    public boolean shouldRenderTailOrb() {
        return ((EntityAbilityBeam)this.entity).shouldRenderTailOrb();
    }

    @Override
    protected void launchFromOwner(EntityLivingBase target) {
        ((EntityAbilityBeam)this.entity).startFiring(target);
    }

    @Override
    public void fireAt(IEntity target) {
        ((EntityAbilityBeam)this.entity).setAttachedToOwner(false);
        super.fireAt(target);
    }

    @Override
    public void fireAt(double x, double y, double z) {
        ((EntityAbilityBeam)this.entity).setAttachedToOwner(false);
        super.fireAt(x, y, z);
    }

    @Override
    public void fireDirection(float yaw, float pitch) {
        ((EntityAbilityBeam)this.entity).setAttachedToOwner(false);
        super.fireDirection(yaw, pitch);
    }
}

