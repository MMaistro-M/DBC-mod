/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.IPos;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.item.IItemStack;

public interface IEntityLivingBase<T extends EntityLivingBase>
extends IEntity<T> {
    public float getHealth();

    public void setHealth(float var1);

    public void hurt(float var1);

    public void hurt(float var1, IEntity var2);

    public void hurt(float var1, IDamageSource var2);

    public void setMaxHurtTime(int var1);

    public int getMaxHurtTime();

    public double getMaxHealth();

    public double getFollowRange();

    public double getKnockbackResistance();

    public double getSpeed();

    public double getMeleeStrength();

    public void setMaxHealth(double var1);

    public void setFollowRange(double var1);

    public void setKnockbackResistance(double var1);

    public void setSpeed(double var1);

    public void setMeleeStrength(double var1);

    public boolean isAttacking();

    public void setAttackTarget(IEntityLivingBase var1);

    public IEntityLivingBase getAttackTarget();

    public int getAttackTargetTime();

    public void setLastAttacker(IEntity var1);

    public IEntity getLastAttacker();

    public int getLastAttackerTime();

    public boolean canBreatheUnderwater();

    @Override
    public int getType();

    @Override
    public boolean typeOf(int var1);

    public IPos getLookVector();

    public IBlock getLookingAtBlock(int var1, boolean var2, boolean var3, boolean var4);

    public IBlock getLookingAtBlock(int var1);

    public IPos getLookingAtPos(int var1, boolean var2, boolean var3, boolean var4);

    public IPos getLookingAtPos(int var1);

    public IEntity[] getLookingAtEntities(IEntity[] var1, int var2, double var3, double var5, boolean var7, boolean var8, boolean var9);

    public IEntity[] getLookingAtEntities(int var1, double var2, double var4, boolean var6, boolean var7, boolean var8);

    public IEntity[] getLookingAtEntities(int var1, double var2, double var4);

    @Override
    public T getMCEntity();

    public void swingHand();

    public void addPotionEffect(int var1, int var2, int var3, boolean var4);

    public void clearPotionEffects();

    public int getPotionEffect(int var1);

    public IItemStack getHeldItem();

    public void setHeldItem(IItemStack var1);

    public IItemStack getArmor(int var1);

    public void setArmor(int var1, IItemStack var2);

    public boolean isChild();

    public void renderBrokenItemStack(IItemStack var1);

    public boolean isOnLadder();

    public int getTotalArmorValue();

    public int getArrowCountInEntity();

    public void setArrowCountInEntity(int var1);

    public void dismountEntity(IEntity var1);

    public void setAIMoveSpeed(float var1);

    public float getAIMoveSpeed();

    public void setAbsorptionAmount(float var1);

    public float getAbsorptionAmount();

    public void setHurtTime(int var1);

    public void applyKnockback(float var1, IEntity var2);

    public void applyKnockback(float var1, double var2, double var4);

    public void forceKnockback(float var1, IEntity var2);

    public void forceKnockback(float var1, double var2, double var4);
}

