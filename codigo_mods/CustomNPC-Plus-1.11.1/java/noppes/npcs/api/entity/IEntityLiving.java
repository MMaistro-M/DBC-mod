/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.EntityLiving;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;

public interface IEntityLiving<T extends EntityLiving>
extends IEntityLivingBase<T> {
    public boolean isNavigating();

    public void clearNavigation();

    public void navigateTo(double var1, double var3, double var5, double var7);

    @Override
    public T getMCEntity();

    public void playLivingSound();

    public void spawnExplosionParticle();

    public void setMoveForward(float var1);

    public void faceEntity(IEntity var1, float var2, float var3);

    public boolean canPickUpLoot();

    public void setCanPickUpLoot(boolean var1);

    public boolean isPersistent();

    public void enablePersistence();

    public void setCustomNameTag(String var1);

    public String getCustomNameTag();

    public boolean hasCustomNameTag();

    public void setAlwaysRenderNameTag(boolean var1);

    public boolean getAlwaysRenderNameTag();

    public void clearLeashed(boolean var1, boolean var2);

    public boolean allowLeashing();

    public boolean getLeashed();

    public IEntity getLeashedTo();

    public void setLeashedTo(IEntity var1, boolean var2);

    public boolean canBeSteered();
}

