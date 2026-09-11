/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 */
package noppes.npcs.scripted.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLiving;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptLivingBase;

public class ScriptLiving<T extends EntityLiving>
extends ScriptLivingBase<T>
implements IEntityLiving {
    public ScriptLiving(T entity) {
        super(entity);
    }

    @Override
    public boolean isAttacking() {
        return super.isAttacking() || ((EntityLiving)this.entity).func_70638_az() != null;
    }

    @Override
    public void setAttackTarget(IEntityLivingBase living) {
        if (living == null) {
            ((EntityLiving)this.entity).func_70624_b(null);
        } else {
            ((EntityLiving)this.entity).func_70624_b(living.getMCEntity());
        }
        super.setAttackTarget(living);
    }

    @Override
    public IEntityLivingBase getAttackTarget() {
        IEntityLivingBase base = (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)((EntityLiving)this.entity).func_70638_az());
        return base != null ? base : super.getAttackTarget();
    }

    @Override
    public void navigateTo(double x, double y, double z, double speed) {
        ((EntityLiving)this.entity).func_70661_as().func_75492_a(x, y, z, speed);
    }

    @Override
    public void clearNavigation() {
        ((EntityLiving)this.entity).func_70661_as().func_75499_g();
    }

    @Override
    public boolean isNavigating() {
        return !((EntityLiving)this.entity).func_70661_as().func_75500_f();
    }

    @Override
    public boolean canSeeEntity(IEntity entity) {
        return ((EntityLiving)this.entity).func_70635_at().func_75522_a(entity.getMCEntity());
    }

    @Override
    public void playLivingSound() {
        ((EntityLiving)this.entity).func_70642_aH();
    }

    @Override
    public void spawnExplosionParticle() {
        ((EntityLiving)this.entity).func_70656_aK();
    }

    @Override
    public void setMoveForward(float speed) {
        ((EntityLiving)this.entity).func_70657_f(speed);
    }

    @Override
    public void faceEntity(IEntity entity, float pitch, float yaw) {
        ((EntityLiving)this.entity).func_70625_a(entity.getMCEntity(), pitch, yaw);
    }

    @Override
    public boolean canPickUpLoot() {
        return ((EntityLiving)this.entity).func_98052_bS();
    }

    @Override
    public void setCanPickUpLoot(boolean pickUp) {
        ((EntityLiving)this.entity).func_98053_h(pickUp);
    }

    @Override
    public boolean isPersistent() {
        return ((EntityLiving)this.entity).func_104002_bU();
    }

    @Override
    public void enablePersistence() {
        ((EntityLiving)this.entity).func_110163_bv();
    }

    @Override
    public void setCustomNameTag(String text) {
        ((EntityLiving)this.entity).func_94058_c(text);
    }

    @Override
    public String getCustomNameTag() {
        return ((EntityLiving)this.entity).func_94057_bL();
    }

    @Override
    public boolean hasCustomNameTag() {
        return ((EntityLiving)this.entity).func_94056_bM();
    }

    @Override
    public void setAlwaysRenderNameTag(boolean alwaysRender) {
        ((EntityLiving)this.entity).func_94061_f(alwaysRender);
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return ((EntityLiving)this.entity).func_94062_bN();
    }

    @Override
    public void clearLeashed(boolean sendPacket, boolean dropLeash) {
        ((EntityLiving)this.entity).func_110160_i(sendPacket, dropLeash);
    }

    @Override
    public boolean allowLeashing() {
        return ((EntityLiving)this.entity).func_110164_bC();
    }

    @Override
    public boolean getLeashed() {
        return ((EntityLiving)this.entity).func_110167_bD();
    }

    @Override
    public IEntity getLeashedTo() {
        return NpcAPI.Instance().getIEntity(((EntityLiving)this.entity).func_110166_bE());
    }

    @Override
    public void setLeashedTo(IEntity entity, boolean sendPacket) {
        ((EntityLiving)this.entity).func_110162_b(entity.getMCEntity(), sendPacket);
    }

    @Override
    public boolean canBeSteered() {
        return ((EntityLiving)this.entity).func_82171_bF();
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
        ((EntityLiving)this.entity).func_70671_ap().func_75650_a(this.getX(), this.getY(), this.getZ(), rotation, this.getPitch());
    }

    @Override
    public void setPitch(float rotation) {
        super.setPitch(rotation);
        ((EntityLiving)this.entity).func_70671_ap().func_75650_a(this.getX(), this.getY(), this.getZ(), this.getRotation(), rotation);
    }
}

