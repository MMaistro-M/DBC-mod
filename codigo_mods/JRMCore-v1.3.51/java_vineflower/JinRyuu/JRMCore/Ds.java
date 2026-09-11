package JinRyuu.JRMCore;

import JinRyuu.JRMCore.entity.EntityPunch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class Ds {
   public static final String DS_PUNCH_DAMAGE = "Punchdam";
   public static final String DS_OUT_OF_HEALTH = "OutOfHealth";
   public static final String DS_CAUSE_EN_EXPLOSION = "causeEnExplosion";
   public static final String DS_ENERGY_ATTACK = "EnergyAttack";
   public static final String DS_BULLET_ATTACK = "BulletAttack";
   public static final String DS_UICounter = "UICounter";
   public static final String DS_OUT_OF_BODY = "OutOfBodyHealth";
   public static final String DS_NOT_ALIVE = "NotAlive";
   public static final String DS_MAJIN_ABSORPTION = "MajinAbsorption";
   public static DamageSource OutOfBodyHealth = new DamageSource("OutOfBodyHealth").func_76348_h();
   public static DamageSource NotAlive = new DamageSource("NotAlive").func_76348_h();

   public static DamageSource causeEntityPunchDamage(EntityPunch par0EntityArrow, Entity par1Entity) {
      return new EntityDamageSourceIndirect("Punchdam", par0EntityArrow, par1Entity).func_76349_b();
   }

   public static DamageSource causeEntityOutOfHealth(EntityPlayerMP player, Entity par1Entity) {
      return new EntityDamageSourceIndirect("OutOfHealth", player, par1Entity).func_76349_b();
   }

   public static DamageSource causeExplosion(Entity par1Entity) {
      return new EntityDamageSource("causeEnExplosion", par1Entity).func_76349_b();
   }

   public static DamageSource causeEntityEnergyAttDamage(Entity entityEnergyAtt, Entity par1Entity) {
      return new EntityDamageSourceIndirect("EnergyAttack", entityEnergyAtt, par1Entity).func_76349_b();
   }

   public static DamageSource causeEntityBulletDamage(Entity att, Entity tar) {
      return new EntityDamageSourceIndirect("BulletAttack", att, tar).func_76349_b();
   }

   public static DamageSource causeUICounterDamage(Entity att) {
      return new EntityDamageSource("UICounter", att).func_76349_b();
   }

   public static DamageSource causeEntityMajinAbsorptionDamage(Entity absorption, Entity par1Entity) {
      return new EntityDamageSourceIndirect("MajinAbsorption", absorption, par1Entity).func_76349_b();
   }
}
