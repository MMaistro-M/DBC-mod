/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.scripted.entity;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.IPos;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptEntity;

public class ScriptLivingBase<T extends EntityLivingBase>
extends ScriptEntity<T>
implements IEntityLivingBase {
    public ScriptLivingBase(T entity) {
        super(entity);
    }

    @Override
    public float getHealth() {
        return ((EntityLivingBase)this.entity).func_110143_aJ();
    }

    @Override
    public void setHealth(float health) {
        ((EntityLivingBase)this.entity).func_70606_j(health);
    }

    @Override
    public void hurt(float damage) {
        ((EntityLivingBase)this.entity).func_70097_a(DamageSource.field_76377_j, damage);
    }

    @Override
    public void hurt(float damage, IEntity source) {
        if (source.getType() == 1) {
            ((EntityLivingBase)this.entity).func_70097_a((DamageSource)new EntityDamageSource("player", source.getMCEntity()), damage);
        } else {
            ((EntityLivingBase)this.entity).func_70097_a((DamageSource)new EntityDamageSource(source.getTypeName(), source.getMCEntity()), damage);
        }
    }

    @Override
    public void hurt(float damage, IDamageSource damageSource) {
        ((EntityLivingBase)this.entity).func_70097_a(damageSource.getMCDamageSource(), damage);
    }

    @Override
    public void setMaxHurtTime(int time) {
        ((EntityLivingBase)this.entity).field_70771_an = time;
    }

    @Override
    public int getMaxHurtTime() {
        return ((EntityLivingBase)this.entity).field_70771_an;
    }

    @Override
    public double getMaxHealth() {
        return ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
    }

    @Override
    public double getFollowRange() {
        return ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e();
    }

    @Override
    public double getKnockbackResistance() {
        return ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111266_c).func_111126_e();
    }

    @Override
    public double getSpeed() {
        return ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();
    }

    @Override
    public double getMeleeStrength() {
        return ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
    }

    @Override
    public void setMaxHealth(double health) {
        ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(health);
    }

    @Override
    public void setFollowRange(double range) {
        ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(range);
    }

    @Override
    public void setKnockbackResistance(double knockbackResistance) {
        ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(knockbackResistance);
    }

    @Override
    public void setSpeed(double speed) {
        ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(speed);
    }

    @Override
    public void setMeleeStrength(double attackDamage) {
        ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(attackDamage);
    }

    @Override
    public boolean isAttacking() {
        return ((EntityLivingBase)this.entity).func_70643_av() != null;
    }

    @Override
    public void setAttackTarget(IEntityLivingBase living) {
        if (living == null) {
            ((EntityLivingBase)this.entity).func_70604_c(null);
        } else {
            ((EntityLivingBase)this.entity).func_70604_c(living.getMCEntity());
        }
    }

    @Override
    public IEntityLivingBase getAttackTarget() {
        return (IEntityLivingBase)NpcAPI.Instance().getIEntity((Entity)((EntityLivingBase)this.entity).func_70643_av());
    }

    @Override
    public int getAttackTargetTime() {
        return ((EntityLivingBase)this.entity).func_142015_aE();
    }

    @Override
    public void setLastAttacker(IEntity p_130011_1_) {
        ((EntityLivingBase)this.entity).func_130011_c(p_130011_1_.getMCEntity());
    }

    @Override
    public IEntity getLastAttacker() {
        return NpcAPI.Instance().getIEntity((Entity)((EntityLivingBase)this.entity).func_110144_aD());
    }

    @Override
    public int getLastAttackerTime() {
        return ((EntityLivingBase)this.entity).func_142013_aG();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return ((EntityLivingBase)this.entity).func_70648_aU();
    }

    @Override
    public int getType() {
        return 5;
    }

    @Override
    public boolean typeOf(int type) {
        return type == 5 || super.typeOf(type);
    }

    public boolean canSeeEntity(IEntity entity) {
        return ((EntityLivingBase)this.entity).func_70685_l(entity.getMCEntity());
    }

    @Override
    public IPos getLookVector() {
        Vec3 lookVec = ((EntityLivingBase)this.entity).func_70040_Z();
        return NpcAPI.Instance().getIPos(lookVec.field_72450_a, lookVec.field_72448_b, lookVec.field_72449_c);
    }

    @Override
    public IBlock getLookingAtBlock(int maxDistance, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return NpcAPI.Instance().getIBlock(this.getWorld(), this.getLookingAtPos(maxDistance, stopOnBlock, stopOnLiquid, stopOnCollision));
    }

    @Override
    public IBlock getLookingAtBlock(int maxDistance) {
        return this.getLookingAtBlock(maxDistance, true, false, false);
    }

    @Override
    public IPos getLookingAtPos(int maxDistance, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        Vec3 lookVec = ((EntityLivingBase)this.entity).func_70040_Z();
        return this.getWorld().rayCastPos(new double[]{((EntityLivingBase)this.entity).field_70165_t, ((EntityLivingBase)this.entity).field_70163_u + (double)((EntityLivingBase)this.entity).func_70047_e(), ((EntityLivingBase)this.entity).field_70161_v}, new double[]{lookVec.field_72450_a, lookVec.field_72448_b, lookVec.field_72449_c}, maxDistance, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IPos getLookingAtPos(int maxDistance) {
        return this.getLookingAtPos(maxDistance, true, false, false);
    }

    @Override
    public IEntity[] getLookingAtEntities(IEntity[] ignoreEntities, int maxDistance, double offset, double range, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        Vec3 lookVec = ((EntityLivingBase)this.entity).func_70040_Z();
        double[] startPos = new double[]{((EntityLivingBase)this.entity).field_70165_t, ((EntityLivingBase)this.entity).field_70163_u + (double)((EntityLivingBase)this.entity).func_70047_e(), ((EntityLivingBase)this.entity).field_70161_v};
        double[] lookVector = new double[]{lookVec.field_72450_a, lookVec.field_72448_b, lookVec.field_72449_c};
        if (ignoreEntities == null) {
            ignoreEntities = new IEntity[]{};
        }
        ArrayList<ScriptLivingBase> entities = new ArrayList<ScriptLivingBase>();
        entities.add(this);
        Collections.addAll(entities, ignoreEntities);
        return this.getWorld().rayCastEntities(entities.toArray(new IEntity[0]), startPos, lookVector, maxDistance, offset, range, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IEntity[] getLookingAtEntities(int maxDistance, double offset, double range, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return this.getLookingAtEntities(null, maxDistance, offset, range, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IEntity[] getLookingAtEntities(int maxDistance, double offset, double range) {
        return this.getLookingAtEntities(maxDistance, offset, range, true, false, true);
    }

    @Override
    public T getMCEntity() {
        return (T)((EntityLivingBase)this.entity);
    }

    public T getMinecraftEntity() {
        return this.getMCEntity();
    }

    @Override
    public float getRotation() {
        return ((EntityLivingBase)this.entity).field_70761_aq;
    }

    @Override
    public void setRotation(float rotation) {
        ((EntityLivingBase)this.entity).field_70761_aq = rotation;
    }

    @Override
    public void swingHand() {
        ((EntityLivingBase)this.entity).func_71038_i();
    }

    @Override
    public void addPotionEffect(int effect, int duration, int strength, boolean hideParticles) {
        if (effect < 0 || effect >= Potion.field_76425_a.length || Potion.field_76425_a[effect] == null) {
            return;
        }
        if (strength < 0) {
            strength = 0;
        }
        if (duration < 0) {
            duration = 0;
        }
        if (!Potion.field_76425_a[effect].func_76403_b()) {
            duration *= 20;
        }
        if (duration == 0) {
            ((EntityLivingBase)this.entity).func_82170_o(effect);
        } else {
            ((EntityLivingBase)this.entity).func_70690_d(new PotionEffect(effect, duration, strength));
        }
    }

    @Override
    public void clearPotionEffects() {
        ((EntityLivingBase)this.entity).func_70674_bp();
    }

    @Override
    public int getPotionEffect(int effect) {
        PotionEffect pf = ((EntityLivingBase)this.entity).func_70660_b(Potion.field_76425_a[effect]);
        if (pf == null) {
            return -1;
        }
        return pf.func_76458_c();
    }

    @Override
    public IItemStack getHeldItem() {
        return NpcAPI.Instance().getIItemStack(((EntityLivingBase)this.entity).func_70694_bm());
    }

    @Override
    public void setHeldItem(IItemStack item) {
        ((EntityLivingBase)this.entity).func_70062_b(0, item == null ? null : item.getMCItemStack());
    }

    @Override
    public IItemStack getArmor(int slot) {
        return NpcAPI.Instance().getIItemStack(((EntityLivingBase)this.entity).func_71124_b(slot + 1));
    }

    @Override
    public void setArmor(int slot, IItemStack item) {
        ((EntityLivingBase)this.entity).func_70062_b(slot + 1, item == null ? null : item.getMCItemStack());
    }

    @Override
    public boolean isChild() {
        return ((EntityLivingBase)this.entity).func_70631_g_();
    }

    @Override
    public void renderBrokenItemStack(IItemStack itemStack) {
        ((EntityLivingBase)this.entity).func_70669_a(itemStack.getMCItemStack());
    }

    @Override
    public boolean isOnLadder() {
        return ((EntityLivingBase)this.entity).func_70617_f_();
    }

    @Override
    public int getTotalArmorValue() {
        return ((EntityLivingBase)this.entity).func_70658_aO();
    }

    @Override
    public int getArrowCountInEntity() {
        return ((EntityLivingBase)this.entity).func_85035_bI();
    }

    @Override
    public void setArrowCountInEntity(int count) {
        ((EntityLivingBase)this.entity).func_85034_r(count);
    }

    @Override
    public void dismountEntity(IEntity entity) {
        ((EntityLivingBase)this.entity).func_110145_l(entity.getMCEntity());
    }

    @Override
    public void setAIMoveSpeed(float speed) {
        ((EntityLivingBase)this.entity).func_70659_e(speed);
    }

    @Override
    public float getAIMoveSpeed() {
        return ((EntityLivingBase)this.entity).func_70689_ay();
    }

    @Override
    public void setAbsorptionAmount(float amount) {
        ((EntityLivingBase)this.entity).func_110149_m(amount);
    }

    @Override
    public float getAbsorptionAmount() {
        return ((EntityLivingBase)this.entity).func_110139_bj();
    }

    @Override
    public void setHurtTime(int time) {
        ((EntityLivingBase)this.entity).field_70172_ad = time;
        ((EntityLivingBase)this.entity).field_70737_aN = time;
    }

    @Override
    public void applyKnockback(float strength, IEntity source) {
        double dirX = ((EntityLivingBase)this.entity).field_70165_t - source.getX();
        double dirZ = ((EntityLivingBase)this.entity).field_70161_v - source.getZ();
        this.applyKnockbackInternal(strength, dirX, dirZ, false);
    }

    @Override
    public void applyKnockback(float strength, double dirX, double dirZ) {
        this.applyKnockbackInternal(strength, dirX, dirZ, false);
    }

    @Override
    public void forceKnockback(float strength, IEntity source) {
        double dirX = ((EntityLivingBase)this.entity).field_70165_t - source.getX();
        double dirZ = ((EntityLivingBase)this.entity).field_70161_v - source.getZ();
        this.applyKnockbackInternal(strength, dirX, dirZ, true);
    }

    @Override
    public void forceKnockback(float strength, double dirX, double dirZ) {
        this.applyKnockbackInternal(strength, dirX, dirZ, true);
    }

    private void applyKnockbackInternal(float strength, double dirX, double dirZ, boolean ignoreResistance) {
        double len;
        if (!ignoreResistance) {
            double resistance = ((EntityLivingBase)this.entity).func_110148_a(SharedMonsterAttributes.field_111266_c).func_111126_e();
            if (((EntityLivingBase)this.entity).field_70170_p.field_73012_v.nextDouble() < resistance) {
                return;
            }
        }
        if ((len = (double)MathHelper.func_76133_a((double)(dirX * dirX + dirZ * dirZ))) <= 0.0) {
            return;
        }
        ((EntityLivingBase)this.entity).field_70160_al = true;
        float base = 0.4f * strength;
        ((EntityLivingBase)this.entity).field_70159_w /= 2.0;
        ((EntityLivingBase)this.entity).field_70181_x /= 2.0;
        ((EntityLivingBase)this.entity).field_70179_y /= 2.0;
        ((EntityLivingBase)this.entity).field_70159_w -= dirX / len * (double)base;
        ((EntityLivingBase)this.entity).field_70181_x += (double)base;
        ((EntityLivingBase)this.entity).field_70179_y -= dirZ / len * (double)base;
        if (((EntityLivingBase)this.entity).field_70181_x > 0.4) {
            ((EntityLivingBase)this.entity).field_70181_x = 0.4;
        }
        ((EntityLivingBase)this.entity).field_70133_I = true;
    }
}

