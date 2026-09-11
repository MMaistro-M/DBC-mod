/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.item.IItemStack;

public interface IEntity<T extends Entity> {
    public void spawnParticle(IParticle var1);

    public int getEntityId();

    public String getUniqueID();

    public double getYOffset();

    public double getWidth();

    public double getHeight();

    public double getX();

    public void setX(double var1);

    public double getY();

    public void setY(double var1);

    public double getZ();

    public void setZ(double var1);

    public double getMotionX();

    public void setMotionX(double var1);

    public double getMotionY();

    public void setMotionY(double var1);

    public double getMotionZ();

    public void setMotionZ(double var1);

    public void setMotion(double var1, double var3, double var5);

    public void setMotion(IPos var1);

    public IPos getMotion();

    public boolean isAirborne();

    public int getBlockX();

    public int getBlockY();

    public int getBlockZ();

    public void setPosition(double var1, double var3, double var5);

    public void setPosition(IPos var1);

    public IPos getPosition();

    public int getDimension();

    public void setDimension(int var1);

    public IEntity[] getCollidingEntities();

    public IEntity[] getSurroundingEntities(int var1);

    public IEntity[] getSurroundingEntities(int var1, int var2);

    public boolean isAlive();

    public Object getTempData(String var1);

    public void setTempData(String var1, Object var2);

    public boolean hasTempData(String var1);

    public void removeTempData(String var1);

    public void clearTempData();

    public String[] getTempDataKeys();

    public Object getStoredData(String var1);

    public void setStoredData(String var1, Object var2);

    public boolean hasStoredData(String var1);

    public void removeStoredData(String var1);

    public void clearStoredData();

    public String[] getStoredDataKeys();

    public long getAge();

    public void despawn();

    public boolean inWater();

    public boolean inLava();

    public boolean inFire();

    public boolean isBurning();

    public void setBurning(int var1);

    public void extinguish();

    public String getTypeName();

    public void dropItem(IItemStack var1);

    public IEntity getRider();

    public void setRider(IEntity var1);

    public IEntity getMount();

    public void setMount(IEntity var1);

    public int getType();

    public boolean typeOf(int var1);

    public void setRotation(float var1);

    public void setRotation(float var1, float var2);

    public float getRotation();

    public void setPitch(float var1);

    public float getPitch();

    public void knockback(int var1, float var2);

    public void knockback(double var1, double var3, double var5, float var7);

    public void knockback(IPos var1, float var2);

    public void setImmune(int var1);

    public void setInvisible(boolean var1);

    public void setSneaking(boolean var1);

    public void setSprinting(boolean var1);

    public boolean hasCollided();

    public boolean hasCollidedVertically();

    public boolean hasCollidedHorizontally();

    public boolean capturesDrops();

    public void setCapturesDrops(boolean var1);

    public void setCapturedDrops(IEntity<?>[] var1);

    public IEntity<?>[] getCapturedDrops();

    public boolean isSneaking();

    public boolean isSprinting();

    public T getMCEntity();

    public INbt getNbt();

    public INbt getAllNbt();

    public void setNbt(INbt var1);

    public INbt getNbtOptional();

    public void storeAsClone(int var1, String var2);

    public IWorld getWorld();

    public void updateEntity();
}

