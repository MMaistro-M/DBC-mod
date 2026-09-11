package JinRyuu.JRMCore.entity;

import JinRyuu.DragonBC.common.DBCClientTickHandler;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.server.JGMathHelper;
import JinRyuu.JRMCore.server.JGPlayerClientServerHelper;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnergyAtt extends EntityEnAttacks implements IThrowableEntity, IEntityAdditionalSpawnData, IEntitySelector, IProjectile {
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   private int inData = 0;
   private boolean inGround = false;
   private int ticksInGround;
   private int ticksInAir = 0;
   private double damage = 1.0;
   private double damageOriginal = 1.0;
   private int damageTaken = 1;
   private int knockbackStrength;
   private float explevel;
   private float Expl = 4.0F;
   private String ExplSound = "jinryuudragonbc:DBC.expl";
   private String AirSound = "jinryuudragonbc:DBC.hafire";
   private float strtX;
   private float strtY;
   private float strtZ;
   private float trgtX = 0.0F;
   private float trgtY = 0.0F;
   private float trgtZ = 0.0F;
   private byte type;
   private byte speed;
   private int dam;
   private byte perc;
   private short effect;
   private int color;
   private int color2;
   private byte density;
   private short sincantation;
   private short sfire;
   private short smove;
   private byte[] sts;
   private byte technum;
   private byte align;
   private float size;
   private int conn;
   private int waveCount = 20;
   private byte wave = 0;
   private Entity target;
   private int cost;
   private int originDmg;
   private boolean shrink = false;
   private byte relFired = 100;
   public boolean givenExp = false;
   public double motionXStart;
   public double motionYStart;
   public double motionZStart;
   public boolean givenTP = false;
   public boolean destroyer = false;
   public float DAMAGE_REDUCTION = JGConfigDBCGoD.CONFIG_GOD_ENERGY_DAMAGE_MULTI;
   public boolean added = false;
   public int animation_speed = 0;
   public long animation_start = 0L;
   public int animation_id = 0;
   public int animation_id_Max = 0;
   public int animation_random_Max = 0;
   public ArrayList<Integer> animation_random = new ArrayList<>();
   public float render_scale = 0.0F;
   public float render_scale_max = 2.0F;
   public double dist = 0.0;
   public boolean shooterHolds;
   public boolean hadTarget = false;
   public boolean added2 = false;
   public long animation_start2 = 0L;
   public float waveScale = 1.0F;
   public double finalDist = 0.0;
   public int lastSegments = 0;
   public float minScale;
   public float maxScale;
   public float maxDamage;
   private boolean run = true;
   private int cb = 50;
   private boolean kiClashed;
   private List kiClashedList = new ArrayList();
   public float startRotationPitch = 0.0F;
   public float startRotationYaw = 0.0F;
   private final byte REACTION_DEAD = 1;
   private final byte REACTION_KILL = 2;
   private final byte REACTION_KILL_EFFECT = 3;
   private final byte REACTION_DAMAGE = 4;
   private final byte REACTION_DAMAGE_EFFECT = 5;

   public float strtX() {
      return this.strtX;
   }

   public float strtY() {
      return this.strtY;
   }

   public float strtZ() {
      return this.strtZ;
   }

   public float trgtX() {
      return this.trgtX;
   }

   public float trgtY() {
      return this.trgtY;
   }

   public float trgtZ() {
      return this.trgtZ;
   }

   public int getShrink() {
      return this.func_70096_w().func_75679_c(20);
   }

   public byte getType() {
      return this.type;
   }

   public int getCol() {
      return this.color;
   }

   public int getCol2() {
      return this.color2;
   }

   public byte getSpe() {
      return this.speed;
   }

   public int getDam() {
      return this.dam;
   }

   public byte getDen() {
      return this.density;
   }

   public byte getPerc() {
      return this.perc;
   }

   public int getAirTicks() {
      return this.ticksInAir;
   }

   public byte[] getSts() {
      return this.sts;
   }

   public void setAirTicks(int i) {
      this.ticksInAir = i;
   }

   public short getEff() {
      return this.effect;
   }

   public float getSize() {
      return this.size;
   }

   public float getScale() {
      float damage = this.originDmg;
      byte perc = this.getPerc();
      byte[] sts = this.getSts();
      byte den = this.getDen();
      return JRMCoreH.calculateEnergyScale(damage, this.maxDamage, perc, sts, den, this.minScale, this.maxScale);
   }

   public void setScales() {
      this.minScale = (float)JRMCoreConfig.KiSizeMin[this.getType()];
      this.maxScale = (float)JRMCoreConfig.KiSizeMax[this.getType()];
      this.maxDamage = JRMCoreH.getMaxEnergyDamage();
   }

   public float setScalesPost() {
      if (this.isWave()) {
         return 100.0F;
      } else if (this.isBlast()) {
         return 5.0F;
      } else if (this.isDisk()) {
         return 5.0F;
      } else if (this.isLaser()) {
         return 5.0F;
      } else if (this.isLargeBlast()) {
         return 10000.0F;
      } else if (this.isSpiral()) {
         return 5.0F;
      } else if (this.isBarrage()) {
         return 5.0F;
      } else if (this.isShield()) {
         return 5.0F;
      } else {
         return this.isExplosion() ? 20.0F : 1.0F;
      }
   }

   public EntityEnergyAtt(World par1World) {
      super(par1World);
      this.func_70105_a(this.size, this.size);
   }

   public EntityEnergyAtt(
      EntityLivingBase entityLiving,
      byte type,
      byte speed,
      int dam,
      byte effect,
      byte color,
      byte density,
      byte sincantation,
      byte sfire,
      byte smove,
      byte perc,
      int dam1,
      int cost,
      byte[] sts,
      byte technum
   ) {
      this(entityLiving, type, speed, dam, effect, color, density, sincantation, sfire, smove, perc, dam1, cost, sts, technum, (byte)-100, (byte)-100);
   }

   public EntityEnergyAtt(
      EntityLivingBase entityLiving,
      byte type,
      byte speed,
      int dam,
      byte effect,
      byte color,
      byte density,
      byte sincantation,
      byte sfire,
      byte smove,
      byte perc,
      int dam1,
      int cost,
      byte[] sts,
      byte technum,
      byte release,
      byte align
   ) {
      super(entityLiving.field_70170_p);
      this.type = type;
      this.shooterHolds = this.isContinuesWave();
      this.speed = (byte)(((speed + 1) * 10 + (type == 2 ? 10 : 0) + (density == 2 ? 40 : 0)) * (1.0F + JRMCoreH.tech_statmod(sts, 0)));
      this.dam = dam;
      this.perc = perc;
      this.effect = effect;
      this.color = color;
      this.color2 = -1;
      this.density = (byte)(sts[5] + 1);
      this.sincantation = sincantation;
      this.sfire = sfire;
      this.smove = smove;
      this.sts = sts;
      this.technum = technum;
      this.cost = cost;
      this.originDmg = dam1;
      if (entityLiving instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entityLiving;
         this.relFired = release != -100 ? release : JRMCoreH.getByte(player, "jrmcRelease");
         this.align = align != -100 ? align : JRMCoreH.getByte(player, "jrmcAlign");
         if (color == 0) {
            if (this.align > 66) {
               this.color = 2;
            } else if (this.align <= 66 && this.align >= 33) {
               this.color = 3;
            } else if (this.align < 33) {
               this.color = 4;
            }
         }
      } else if (color == 0) {
         this.color = 3;
      }

      this.damage = (double)dam1 * this.relFired * 0.01F * perc * 0.02F * JRMCoreConfig.dat5696[type][1];
      this.damageOriginal = this.damage;
      if (!this.isShield() && !this.isExplosion()) {
         this.setScales();
         float size1 = this.getScale();
         this.size = 0.5F + size1;
      } else {
         this.minScale = 0.001F;
         this.maxScale = 0.1F;
         this.maxDamage = JRMCoreH.getMaxEnergyDamage();
      }

      this.shootingEntity = entityLiving;
      if (!this.isShield() && !this.isExplosion()) {
         this.explevel = this.size * (float)JRMCoreConfig.eaes;
      } else {
         this.explevel = 0.0F;
      }

      this.field_70155_l = 10.0;
      if (JRMCoreConfig.eaesl > 0 && this.size > JRMCoreConfig.eaesl) {
         this.size = JRMCoreConfig.eaesl;
      }

      if (JRMCoreConfig.ExplosionSizeLimit > 0.0 && this.explevel > JRMCoreConfig.ExplosionSizeLimit) {
         this.explevel = (float)JRMCoreConfig.ExplosionSizeLimit;
      }

      if (this.isLargeBlast()) {
         this.size = this.size * JRMCoreConfig.ealbm;
         this.explevel = this.explevel * JRMCoreConfig.ealbm;
      }

      if (JRMCoreConfig.KiAttackScalesWithUser) {
         float extraScale = this.shootingEntity == null ? 1.0F : this.shootingEntity.field_70131_O / 1.8F;
         this.size *= extraScale;
         this.explevel *= extraScale;
      }

      this.func_70105_a(this.size, this.size);
      double y;
      double z;
      double x;
      if (!this.isShield() && !this.isExplosion()) {
         double d8 = entityLiving.field_70130_N + 1.0F + 1.0F;
         double d9 = entityLiving.field_70131_O + 0.2F;
         Vec3 vec3;
         if (this.shootingEntity instanceof EntityPlayer) {
            vec3 = this.shootingEntity.func_70040_Z();
         } else {
            float rotationYaw = this.shootingEntity.func_70079_am();
            float rotationPitch = this.shootingEntity.field_70125_A;
            float vx = -MathHelper.func_76126_a(this.rad(rotationYaw)) * MathHelper.func_76134_b(this.rad(rotationPitch));
            float vz = MathHelper.func_76134_b(this.rad(rotationYaw)) * MathHelper.func_76134_b(this.rad(rotationPitch));
            float vy = -MathHelper.func_76126_a(this.rad(rotationPitch));
            vec3 = Vec3.func_72443_a(vx, vy, vz);
         }

         x = entityLiving.field_70165_t + vec3.field_72450_a * d8;
         y = entityLiving.field_70163_u + vec3.field_72448_b * d8 + entityLiving.field_70131_O * 0.7F;
         z = entityLiving.field_70161_v + vec3.field_72449_c * d8;
         this.func_70012_b(x, y, z, entityLiving.func_70079_am(), entityLiving.field_70125_A);
         this.field_70129_M = this.size * 0.5F;
         this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float) Math.PI)
            * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI);
         this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float) Math.PI)
            * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI);
         this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0F * (float) Math.PI);
         this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)(this.speed * 0.05F * JRMCoreConfig.dat5696[type][0]), 1.0F);
      } else {
         this.size = this.shootingEntity.field_70131_O * 3.0F * (!this.isExplosion() ? 1.0F : 2.0F);
         this.func_70105_a(this.size, this.size);
         x = entityLiving.field_70165_t;
         y = entityLiving.field_70163_u + entityLiving.field_70131_O * 0.55F;
         z = entityLiving.field_70161_v;
         this.func_70012_b(x, y, z, entityLiving.field_70177_z, entityLiving.field_70125_A);
         this.field_70129_M = this.size * 0.5F;
         this.field_70159_w = 0.0;
         this.field_70179_y = 0.0;
         this.field_70181_x = 0.0;
      }

      this.strtX = (float)x;
      this.strtY = (float)y;
      this.strtZ = (float)z;
      this.motionXStart = this.field_70159_w;
      this.motionYStart = this.field_70181_x;
      this.motionZStart = this.field_70179_y;
   }

   public EntityEnergyAtt(
      EntityLivingBase entityLiving,
      byte type,
      byte speed,
      int dam,
      byte effect,
      byte color,
      byte color2,
      byte density,
      byte sincantation,
      byte sfire,
      byte smove,
      byte perc,
      int dam1,
      int cost,
      byte[] sts,
      byte technum
   ) {
      this(entityLiving, type, speed, dam, effect, color, color2, density, sincantation, sfire, smove, perc, dam1, cost, sts, technum, (byte)-100, (byte)-100);
   }

   public EntityEnergyAtt(
      EntityLivingBase entityLiving,
      byte type,
      byte speed,
      int dam,
      byte effect,
      byte color,
      byte color2,
      byte density,
      byte sincantation,
      byte sfire,
      byte smove,
      byte perc,
      int dam1,
      int cost,
      byte[] sts,
      byte technum,
      byte release,
      byte align
   ) {
      super(entityLiving.field_70170_p);
      this.type = type;
      this.shooterHolds = this.isContinuesWave();
      this.speed = (byte)(((speed + 1) * 10 + (type == 2 ? 10 : 0) + (density == 2 ? 40 : 0)) * (1.0F + JRMCoreH.tech_statmod(sts, 0)));
      this.dam = dam;
      this.perc = perc;
      this.effect = effect;
      this.color = color;
      this.color2 = color2;
      this.density = (byte)(sts[5] + 1);
      this.sincantation = sincantation;
      this.sfire = sfire;
      this.smove = smove;
      this.sts = sts;
      this.technum = technum;
      this.cost = cost;
      this.originDmg = dam1;
      if (entityLiving instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)entityLiving;
         this.relFired = release != -100 ? release : JRMCoreH.getByte(player, "jrmcRelease");
         this.align = align != -100 ? align : JRMCoreH.getByte(player, "jrmcAlign");
         if (color == 0) {
            if (this.align > 66) {
               this.color = 2;
            } else if (this.align <= 66 && this.align >= 33) {
               this.color = 3;
            } else if (this.align < 33) {
               this.color = 4;
            }
         }

         if (color2 == 0) {
            if (this.align > 66) {
               this.color2 = 2;
            } else if (this.align <= 66 && this.align >= 33) {
               this.color2 = 3;
            } else if (this.align < 33) {
               this.color2 = 4;
            }
         }
      } else {
         if (color == 0) {
            this.color = 3;
         }

         if (color2 == 0) {
            this.color2 = 3;
         }
      }

      this.damage = (double)dam1 * this.relFired * 0.01F * perc * 0.02F * JRMCoreConfig.dat5696[type][1];
      this.damageOriginal = this.damage;
      if (!this.isShield() && !this.isExplosion()) {
         this.setScales();
         float size1 = this.getScale();
         this.size = 0.5F + size1;
      } else {
         this.minScale = 0.001F;
         this.maxScale = 0.1F;
         this.maxDamage = JRMCoreH.getMaxEnergyDamage();
      }

      this.shootingEntity = entityLiving;
      if (!this.isShield() && !this.isExplosion()) {
         this.explevel = this.size * (float)JRMCoreConfig.eaes;
      } else {
         this.explevel = 0.0F;
      }

      this.field_70155_l = 10.0;
      if (JRMCoreConfig.eaesl > 0 && this.size > JRMCoreConfig.eaesl) {
         this.size = JRMCoreConfig.eaesl;
      }

      if (JRMCoreConfig.ExplosionSizeLimit > 0.0 && this.explevel > JRMCoreConfig.ExplosionSizeLimit) {
         this.explevel = (float)JRMCoreConfig.ExplosionSizeLimit;
      }

      if (this.isLargeBlast()) {
         this.size = this.size * JRMCoreConfig.ealbm;
         this.explevel = this.explevel * JRMCoreConfig.ealbm;
      }

      if (JRMCoreConfig.KiAttackScalesWithUser) {
         float extraScale = this.shootingEntity == null ? 1.0F : this.shootingEntity.field_70131_O / 1.8F;
         this.size *= extraScale;
         this.explevel *= extraScale;
      }

      this.func_70105_a(this.size, this.size);
      double y;
      double z;
      double x;
      if (!this.isShield() && !this.isExplosion()) {
         double d8 = entityLiving.field_70130_N + 1.0F + 1.0F;
         double d9 = entityLiving.field_70131_O + 0.2F;
         Vec3 vec3;
         if (this.shootingEntity instanceof EntityPlayer) {
            vec3 = this.shootingEntity.func_70040_Z();
         } else {
            float rotationYaw = this.shootingEntity.func_70079_am();
            float rotationPitch = this.shootingEntity.field_70125_A;
            float vx = -MathHelper.func_76126_a(this.rad(rotationYaw)) * MathHelper.func_76134_b(this.rad(rotationPitch));
            float vz = MathHelper.func_76134_b(this.rad(rotationYaw)) * MathHelper.func_76134_b(this.rad(rotationPitch));
            float vy = -MathHelper.func_76126_a(this.rad(rotationPitch));
            vec3 = Vec3.func_72443_a(vx, vy, vz);
         }

         x = entityLiving.field_70165_t + vec3.field_72450_a * d8;
         y = entityLiving.field_70163_u + vec3.field_72448_b * d8 + entityLiving.field_70131_O * 0.7F;
         z = entityLiving.field_70161_v + vec3.field_72449_c * d8;
         this.func_70012_b(x, y, z, entityLiving.func_70079_am(), entityLiving.field_70125_A);
         this.field_70129_M = this.size * 0.5F;
         this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0F * (float) Math.PI)
            * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI);
         this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0F * (float) Math.PI)
            * MathHelper.func_76134_b(this.field_70125_A / 180.0F * (float) Math.PI);
         this.field_70181_x = -MathHelper.func_76126_a(this.field_70125_A / 180.0F * (float) Math.PI);
         this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)(this.speed * 0.05F * JRMCoreConfig.dat5696[type][0]), 1.0F);
      } else {
         this.size = this.shootingEntity.field_70131_O * 3.0F * (!this.isExplosion() ? 1.0F : 2.0F);
         this.func_70105_a(this.size, this.size);
         x = entityLiving.field_70165_t;
         y = entityLiving.field_70163_u + entityLiving.field_70131_O * 0.55F;
         z = entityLiving.field_70161_v;
         this.func_70012_b(x, y, z, entityLiving.field_70177_z, entityLiving.field_70125_A);
         this.field_70129_M = this.size * 0.5F;
         this.field_70159_w = 0.0;
         this.field_70179_y = 0.0;
         this.field_70181_x = 0.0;
      }

      this.strtX = (float)x;
      this.strtY = (float)y;
      this.strtZ = (float)z;
      this.motionXStart = this.field_70159_w;
      this.motionYStart = this.field_70181_x;
      this.motionZStart = this.field_70179_y;
   }

   @Override
   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(20, 0);
   }

   public void func_70186_c(double par1, double par3, double par5, float par7, float par8) {
      float var9 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
      par1 /= var9;
      par3 /= var9;
      par5 /= var9;
      par1 += this.field_70146_Z.nextGaussian() * 0.0075F * par8;
      par3 += this.field_70146_Z.nextGaussian() * 0.0075F * par8;
      par5 += this.field_70146_Z.nextGaussian() * 0.0075F * par8;
      par1 *= par7;
      par3 *= par7;
      par5 *= par7;
      this.field_70159_w = par1;
      this.field_70181_x = par3;
      this.field_70179_y = par5;
      float var10 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
      this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
      this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, var10) * 180.0 / Math.PI);
      this.ticksInGround = 0;
   }

   @SideOnly(Side.CLIENT)
   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
      this.func_70107_b(par1, par3, par5);
      this.func_70101_b(par7, par8);
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double par1, double par3, double par5) {
      this.field_70159_w = par1;
      this.field_70181_x = par3;
      this.field_70179_y = par5;
      if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float var7 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
         this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
         this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, var7) * 180.0 / Math.PI);
         this.field_70127_C = this.field_70125_A;
         this.field_70126_B = this.field_70177_z;
         this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
         this.ticksInGround = 0;
      }
   }

   public boolean isContinuesWave() {
      return this.getType() >= JRMCoreConfig.ContinuesKiAttacks.length ? false : JRMCoreConfig.ContinuesKiAttacks[this.getType()];
   }

   public void func_70071_h_() {
      if ((JGConfigClientSettings.configsChanged || this.run) && this.field_70170_p.field_72995_K && !this.field_70128_L) {
         this.field_70158_ak = JGConfigClientSettings.renderEnergyOutsideView;
      }

      if (!this.isShield() && !this.isExplosion()) {
         int color = JRMCoreH.techCol[this.getCol()];
         int color2 = this.getCol2() == -1 ? JRMCoreH.techCol2[this.getCol()] : JRMCoreH.techCol3[this.getCol2()];
         if (this.isContinuesWave() && this.shooterHolds) {
            this.generateParticles(this, this.shootingEntity, color, color2, true);
         }

         this.generateParticles(this, this, color, color2, false);
      }

      if (!this.run) {
         if (this.field_70125_A != this.startRotationPitch) {
            this.field_70125_A = this.startRotationPitch;
         }

         if (this.field_70177_z != this.startRotationYaw) {
            this.field_70177_z = this.startRotationYaw;
         }
      }

      boolean ROTATION_RELATED = true;
      if (this.run) {
         this.startRotationPitch = this.field_70125_A;
         this.startRotationYaw = this.field_70177_z;
         this.shooterHolds = this.isContinuesWave();
         if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)this.shootingEntity;
            ExtendedPlayer.get(player).setAnimKiShootOn(0);
         }

         if (!this.field_70170_p.field_72995_K && !JRMCoreConfig.dat5695[this.type] && !this.field_70128_L) {
            this.func_70106_y();
         }

         if (!this.field_70170_p.field_72995_K && !JRMCoreConfig.dat5709[this.type] && this.hasEffect()) {
            this.effect = 0;
         }

         this.run = false;
      }

      if (!this.field_70170_p.field_72995_K
         && this.isContinuesWave()
         && !this.isShield()
         && !this.isExplosion()
         && this.target != null
         && !this.target.field_70128_L
         && !this.shooterHolds
         && JGMathHelper.isMotionSmallerThanN(this, 0.001)) {
         this.func_70106_y();
      }

      if (JRMCoreConfig.WavesShrinkOnceLetGo) {
         if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && this.isContinuesWave() && this.shooterHolds) {
            double d8 = ((EntityLivingBase)this.shootingEntity).field_70130_N + 1.0F + 1.0F;
            double d9 = ((EntityLivingBase)this.shootingEntity).field_70131_O + 0.2F;
            Vec3 vec3 = this.shootingEntity.func_70040_Z();
            double x = this.shootingEntity.field_70165_t + vec3.field_72450_a * d8;
            double y = this.shootingEntity.field_70163_u
               + vec3.field_72448_b * d8
               + this.shootingEntity.field_70131_O * 0.7F
               + (this.field_70170_p.field_72995_K ? JGPlayerClientServerHelper.clientPlayerPositioner(this.shootingEntity) : 0.0F);
            double z = this.shootingEntity.field_70161_v + vec3.field_72449_c * d8;
            if (x < 0.0) {
               x *= -1.0;
            }

            if (y < 0.0) {
               y *= -1.0;
            }

            if (z < 0.0) {
               z *= -1.0;
            }

            double kiX = this.strtX;
            if (kiX < 0.0) {
               kiX *= -1.0;
            }

            double kiY = this.strtY;
            if (kiY < 0.0) {
               kiY *= -1.0;
            }

            double kiZ = this.strtZ;
            if (kiZ < 0.0) {
               kiZ *= -1.0;
            }

            double kulx = x - kiX;
            if (kulx < 0.0) {
               kulx *= -1.0;
            }

            double kuly = y - kiY;
            if (kuly < 0.0) {
               kuly *= -1.0;
            }

            double kulz = z - kiZ;
            if (kulz < 0.0) {
               kulz *= -1.0;
            }

            float DEAD_DIFFERENCE = 0.2F;
            float DEAD_DIFFERENCE2 = 1.0F;
            if (kulx > 0.2F || kuly > 1.0 || kulz > 0.2F) {
               this.shooterHolds = false;
               EntityPlayer player = (EntityPlayer)this.shootingEntity;
               ExtendedPlayer.get(player).setAnimKiShoot(0);
               if (!this.field_70170_p.field_72995_K) {
                  JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
                  this.shrinkWave();
               } else {
                  DBCClientTickHandler.nuller();
                  JRMCoreH.isShtng = false;
               }
            }
         }
      } else if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && this.isContinuesWave() && this.shooterHolds) {
         double x = this.strtX;
         double y = this.strtY;
         double z = this.strtZ;
         if (x < 0.0) {
            x *= -1.0;
         }

         if (y < 0.0) {
            y *= -1.0;
         }

         if (z < 0.0) {
            z *= -1.0;
         }

         double kiX = this.shootingEntity.field_70165_t;
         if (kiX < 0.0) {
            kiX *= -1.0;
         }

         double kiY = this.shootingEntity.field_70163_u
            + (this.field_70170_p.field_72995_K ? JGPlayerClientServerHelper.clientPlayerPositioner(this.shootingEntity) : 0.0F);
         if (kiY < 0.0) {
            kiY *= -1.0;
         }

         double kiZ = this.shootingEntity.field_70161_v;
         if (kiZ < 0.0) {
            kiZ *= -1.0;
         }

         double kulx = x - kiX;
         if (kulx < 0.0) {
            kulx *= -1.0;
         }

         double kuly = y - kiY;
         if (kuly < 0.0) {
            kuly *= -1.0;
         }

         double kulz = z - kiZ;
         if (kulz < 0.0) {
            kulz *= -1.0;
         }

         float DEAD_DIFFERENCE = 3.0F;
         if (kulx > 3.0 || kuly > 3.0 || kulz > 3.0) {
            this.shooterHolds = false;
            EntityPlayer player = (EntityPlayer)this.shootingEntity;
            ExtendedPlayer.get(player).setAnimKiShoot(0);
            if (!this.field_70170_p.field_72995_K) {
               JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
            } else {
               DBCClientTickHandler.nuller();
               JRMCoreH.isShtng = false;
            }
         }
      }

      if (!this.field_70170_p.field_72995_K
         && this.isContinuesWave()
         && JRMCoreConfig.ContinuesEnergyAttackEnemyLock
         && this.target != null
         && !this.target.field_70128_L
         && this.shooterHolds) {
         double x = this.field_70165_t;
         double y = this.field_70163_u;
         double z = this.field_70161_v;
         if (x < 0.0) {
            x *= -1.0;
         }

         if (y < 0.0) {
            y *= -1.0;
         }

         if (z < 0.0) {
            z *= -1.0;
         }

         double kiX = this.target.field_70165_t;
         if (kiX < 0.0) {
            kiX *= -1.0;
         }

         double kiY = this.target.field_70163_u;
         if (kiY < 0.0) {
            kiY *= -1.0;
         }

         double kiZ = this.target.field_70161_v;
         if (kiZ < 0.0) {
            kiZ *= -1.0;
         }

         double kulx = x - kiX;
         if (kulx < 0.0) {
            kulx *= -1.0;
         }

         double kuly = y - kiY;
         if (kuly < 0.0) {
            kuly *= -1.0;
         }

         double kulz = z - kiZ;
         if (kulz < 0.0) {
            kulz *= -1.0;
         }

         float DEAD_DIFFERENCE = 0.5F;
         if (kulx > 0.5 || kuly > 0.5 || kulz > 0.5) {
            this.target.field_70165_t = x;
            this.target.field_70163_u = y;
            this.target.field_70161_v = z;
            this.target.field_70165_t = this.field_70165_t;
            this.target.field_70163_u = this.field_70163_u;
            this.target.field_70161_v = this.field_70161_v;
            this.target.field_70159_w = this.field_70159_w;
            this.target.field_70181_x = this.field_70181_x;
            this.target.field_70179_y = this.field_70179_y;
         }

         if (JRMCoreH.DBC() && this.target instanceof EntityDBC) {
            ((EntityDBC)this.target).lockedBy = this;
         }
      }

      if (!this.field_70170_p.field_72995_K
         && this.isContinuesWave()
         && JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown
         && JRMCoreConfig.ContinuesEnergyAttackMoveOnLostTarget
         && this.target != null
         && !this.target.field_70128_L
         && this.shooterHolds
         && JGMathHelper.isMotionSmallerThanN(this, 0.001)) {
         double x = this.field_70165_t;
         double y = this.field_70163_u;
         double z = this.field_70161_v;
         if (x < 0.0) {
            x *= -1.0;
         }

         if (y < 0.0) {
            y *= -1.0;
         }

         if (z < 0.0) {
            z *= -1.0;
         }

         double kiX = this.target.field_70165_t;
         if (kiX < 0.0) {
            kiX *= -1.0;
         }

         double kiY = this.target.field_70163_u;
         if (kiY < 0.0) {
            kiY *= -1.0;
         }

         double kiZ = this.target.field_70161_v;
         if (kiZ < 0.0) {
            kiZ *= -1.0;
         }

         double kulx = x - kiX;
         if (kulx < 0.0) {
            kulx *= -1.0;
         }

         double kuly = y - kiY;
         if (kuly < 0.0) {
            kuly *= -1.0;
         }

         double kulz = z - kiZ;
         if (kulz < 0.0) {
            kulz *= -1.0;
         }

         float DEAD_DIFFERENCE = this.size + 1.0F;
         if (kulx > DEAD_DIFFERENCE || kuly > DEAD_DIFFERENCE || kulz > DEAD_DIFFERENCE) {
            this.target = null;
            this.hadTarget = false;
            this.field_70159_w = this.motionXStart;
            this.field_70181_x = this.motionYStart;
            this.field_70179_y = this.motionZStart;
         }
      }

      if (!this.field_70170_p.field_72995_K
         && JRMCoreConfig.WavesDieWhenTargetAway
         && this.shootingEntity != null
         && this.target != null
         && this.shootingEntity instanceof EntityPlayer
         && this.isContinuesWave()) {
         double x = this.field_70165_t;
         double y = this.field_70163_u;
         double z = this.field_70161_v;
         if (x < 0.0) {
            x *= -1.0;
         }

         if (y < 0.0) {
            y *= -1.0;
         }

         if (z < 0.0) {
            z *= -1.0;
         }

         double kiX = this.target.field_70165_t;
         if (kiX < 0.0) {
            kiX *= -1.0;
         }

         double kiY = this.target.field_70163_u;
         if (kiY < 0.0) {
            kiY *= -1.0;
         }

         double kiZ = this.target.field_70161_v;
         if (kiZ < 0.0) {
            kiZ *= -1.0;
         }

         double kulx = x - kiX;
         if (kulx < 0.0) {
            kulx *= -1.0;
         }

         double kuly = y - kiY;
         if (kuly < 0.0) {
            kuly *= -1.0;
         }

         double kulz = z - kiZ;
         if (kulz < 0.0) {
            kulz *= -1.0;
         }

         float DEAD_DIFFERENCE = this.size + 1.0F;
         if (kulx > DEAD_DIFFERENCE || kuly > DEAD_DIFFERENCE || kulz > DEAD_DIFFERENCE) {
            if (this.hasEffect() && !this.isShield() && !this.isExplosion()) {
               this.createExplosion(2);
            }

            this.func_70106_y();
         }
      }

      if (this.shootingEntity != null && this.isExplosion() && this.hasEffect()) {
         double kulx = this.shootingEntity.field_70165_t - this.field_70165_t;
         if (kulx < 0.0) {
            kulx *= -1.0;
         }

         double kuly = this.shootingEntity.field_70163_u - this.field_70163_u;
         if (kuly < 0.0) {
            kuly *= -1.0;
         }

         double kulz = this.shootingEntity.field_70161_v - this.field_70161_v;
         if (kulz < 0.0) {
            kulz *= -1.0;
         }

         if (kulx > 1.0 || kuly > 1.0 || kulz > 1.0) {
            this.shootingEntity.func_70107_b(this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v);
         }
      }

      if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
         this.func_70106_y();
      }

      if (!this.field_70170_p.field_72995_K && this.shootingEntity != null && !this.field_70128_L) {
         if (this.isShield()) {
            if (JRMCoreConfig.ShieldsMoveWithUser) {
               this.field_70165_t = this.shootingEntity.field_70165_t;
               this.field_70163_u = this.shootingEntity.field_70163_u + this.shootingEntity.field_70131_O * 0.55F;
               this.field_70161_v = this.shootingEntity.field_70161_v;
            } else {
               int diff = (int)(this.field_70165_t - this.shootingEntity.field_70165_t);
               diff *= diff > 0 ? 1 : -1;
               if (diff > 3) {
                  this.func_70106_y();
               }

               diff = (int)(this.field_70163_u - this.shootingEntity.field_70163_u);
               diff *= diff > 0 ? 1 : -1;
               if (diff > 3) {
                  this.func_70106_y();
               }

               diff = (int)(this.field_70161_v - this.shootingEntity.field_70161_v);
               diff *= diff > 0 ? 1 : -1;
               if (diff > 3) {
                  this.func_70106_y();
               }
            }
         } else if (this.isExplosion() && JRMCoreConfig.ExplosionsMoveWithUser) {
            this.field_70165_t = this.shootingEntity.field_70165_t;
            this.field_70163_u = this.shootingEntity.field_70163_u + this.shootingEntity.field_70131_O * 0.55F;
            this.field_70161_v = this.shootingEntity.field_70161_v;
         }
      }

      if (!this.field_70170_p.field_72995_K && this.shootingEntity != null && JRMCoreConfig.dat5710 && (this.isShield() || this.isExplosion())) {
         int curBody = Integer.parseInt(JRMCoreH.data(this.shootingEntity.func_70005_c_(), 8, "200"));
         if (curBody == 0) {
            this.func_70106_y();
         }
      }

      if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer) {
         byte b = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng");
         if (b == 0 && !this.shrink && JRMCoreConfig.WavesShrinkOnceLetGo) {
            this.shrink();
         }
      }

      if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.hadTarget && (this.target == null || this.target.field_70128_L)) {
         this.func_70106_y();
      }

      if (!this.field_70170_p.field_72995_K && this.isExplosion() && this.field_70173_aa >= JRMCoreConfig.dat5697) {
         this.func_70106_y();
      }

      if (this.isContinuesWave() && this.target != null) {
         if (JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown) {
            this.field_70159_w *= 0.05F;
            this.field_70181_x *= 0.05F;
            this.field_70179_y *= 0.05F;
            this.target.field_70159_w *= 0.05F;
            this.target.field_70181_x *= 0.05F;
            this.target.field_70179_y *= 0.05F;
         } else {
            this.target.field_70159_w = this.field_70159_w;
            this.target.field_70181_x = this.field_70181_x;
            this.target.field_70179_y = this.field_70179_y;
            this.target.field_70165_t = this.field_70165_t;
            this.target.field_70163_u = this.field_70163_u;
            this.target.field_70161_v = this.field_70161_v;
         }
      }

      if (this.field_70173_aa == 1) {
         this.func_70105_a(this.size, this.size);
         this.field_70129_M = this.size * 0.5F;
      }

      super.func_70071_h_();
      if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
         float var1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
         this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, var1) * 180.0 / Math.PI);
      }

      Block block = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
      if (block.func_149688_o() != Material.field_151579_a) {
         block.func_149719_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
         AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
         if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
            this.inGround = true;
         }
      }

      if (this.inGround && !this.isShield() && !this.isExplosion()) {
         int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
         if (!this.field_70170_p.field_72995_K) {
            if (block == this.inTile && var19 == this.inData) {
               this.ticksInGround++;
               if (this.ticksInGround == 1) {
                  this.func_70106_y();
                  if (!this.field_70170_p.field_72995_K) {
                     if (this.hasEffect()) {
                        this.createExplosion(1);
                     }

                     this.playSoundAtEntity(
                        this,
                        JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer
                           ? "jinryuudragonbc:DBC5.hakai"
                           : this.ExplSound,
                        1.0F,
                        1.0F
                     );
                  }
               }
            } else {
               this.inGround = false;
               this.field_70159_w = this.field_70159_w * (this.field_70146_Z.nextFloat() * 0.2F);
               this.field_70181_x = this.field_70181_x * (this.field_70146_Z.nextFloat() * 0.2F);
               this.field_70179_y = this.field_70179_y * (this.field_70146_Z.nextFloat() * 0.2F);
               this.ticksInGround = 0;
               this.ticksInAir = 0;
            }
         }
      } else {
         this.ticksInAir++;
         Vec3 var17 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 var3 = Vec3.func_72443_a(
            this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y
         );
         MovingObjectPosition movingObject = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
         var17 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         var3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if (!this.field_70170_p.field_72995_K
            && (
               this.ticksInAir >= JRMCoreConfig.EnergyAttackMaxLifeTickPercMulti * (this.perc * 0.02F)
                  || this.ticksInAir >= JRMCoreConfig.EnergyAttackMaxLifeTick
            )) {
            this.func_70106_y();
         }

         int t = this.ticksInAir / 10 * 10;
         if (this.ticksInAir == (t == 0 ? 10 : t)) {
            this.playSoundAtEntity(this, "jinryuudragonbc:" + JRMCoreH.techSnds(this.type, 2, this.smove), this.isBarrage() ? 0.5F : 1.0F, 1.0F);
         }

         if (movingObject != null) {
            var3 = Vec3.func_72443_a(
               movingObject.field_72307_f.field_72450_a, movingObject.field_72307_f.field_72448_b, movingObject.field_72307_f.field_72449_c
            );
         }

         if (!this.field_70170_p.field_72995_K) {
            Entity lastEntity = null;
            byte MODE_OLD = 0;
            byte MODE_ONE = 1;
            byte MODE_TWO = 2;
            byte MODE_THREE = 3;
            byte MODE_FOUR = 4;
            byte MODE_OFF = 5;
            byte mode = JRMCoreConfig.KiClosestEntityCheckSize;
            List entityList;
            if (mode == 4) {
               AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
               entityList = this.field_70170_p.func_72839_b(this, aabb);
            } else if (mode == 3) {
               AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
               entityList = this.field_70170_p.func_72839_b(this, aabb.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y));
            } else if (mode == 2) {
               AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
               entityList = this.field_70170_p.func_72839_b(this, aabb.func_72314_b(0.5, 0.5, 0.5));
            } else if (mode == 1) {
               entityList = this.field_70170_p.func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y));
            } else {
               entityList = this.field_70170_p
                  .func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(0.5, 0.5, 0.5));
            }

            if (mode != 5) {
               double lastDistance = 0.0;

               for (int n = 0; n < entityList.size(); n++) {
                  Entity entity = (Entity)entityList.get(n);
                  if (entity instanceof EntityLivingBase && entity.func_70067_L() && (entity != this.shootingEntity || this.ticksInAir >= 5)) {
                     float var11 = 0.0F;
                     AxisAlignedBB entityHitbox = entity.field_70121_D.func_72314_b(var11, var11, var11);
                     MovingObjectPosition movingObject2 = entityHitbox.func_72327_a(var17, var3);
                     if (movingObject2 != null) {
                        double distance = var17.func_72438_d(movingObject2.field_72307_f);
                        if (distance < lastDistance || lastDistance == 0.0) {
                           lastEntity = entity;
                           lastDistance = distance;
                        }
                     }
                  }
               }

               if (lastEntity != null) {
                  movingObject = new MovingObjectPosition(lastEntity);
               }
            }
         }

         if (this.field_70159_w <= 0.01
            && this.field_70181_x <= 0.01
            && this.field_70179_y <= 0.01
            && this.field_70159_w >= -0.01
            && this.field_70181_x >= -0.01
            && this.field_70179_y >= -0.01
            && !this.shrink
            && !this.isContinuesWave()) {
            this.shrink();
         }

         if (!this.field_70170_p.field_72995_K) {
            List entityList = this.checkForEntitiesInside();

            for (int n = 0; n < entityList.size(); n++) {
               Entity entity = (Entity)entityList.get(n);
               if (entity != null && entity != this.shootingEntity && !entity.field_70128_L) {
                  if (entity instanceof EntityEnergyAtt) {
                     EntityEnergyAtt entityKi = (EntityEnergyAtt)entity;
                     if (entityKi.isShield() || entityKi.isExplosion()) {
                        this.handleKiaiClash(entityKi);
                     }
                  } else if (JRMCoreH.NC() && entity instanceof EntityEnergyAttJ3) {
                     EntityEnergyAttJ3 entityShield = (EntityEnergyAttJ3)entity;
                     this.handleJutsuWallClash(entityShield);
                  }
               }
            }

            for (int n = 0; n < entityList.size(); n++) {
               Entity entity = (Entity)entityList.get(n);
               if (entity != this.shootingEntity) {
                  byte reaction = this.checkReaction(entity, true);
                  EntityEnergyAtt entityKi = null;
                  if (entity != null) {
                     if (entity instanceof EntityEnergyAtt) {
                        entityKi = (EntityEnergyAtt)entity;
                     }

                     if (entity instanceof EntityLivingBase) {
                        if (movingObject == null) {
                           movingObject = new MovingObjectPosition(entity);
                        }

                        if (this.target == null && !JRMCoreH.isFusionSpectator(entity)) {
                           this.setTarget(entity);
                        }
                     } else if (this.isContinuesWave() && this.wave > 0 && this.shootingEntity instanceof EntityPlayer) {
                        this.shrinkWave();
                     } else if (!(entity instanceof EntityEnAttacks) && entity != this.shootingEntity && !this.isShield() && !this.isExplosion()) {
                        if (this.hasEffect() && (reaction <= 0 || reaction >= 6) && entity instanceof EntityLivingBase) {
                           this.createExplosion(2);
                        }
                     } else if (entityKi != null && !(entityKi.shootingEntity instanceof EntityDBC) && !this.kiClashedList.contains(entity)) {
                        if (!this.isShield() && !this.isExplosion()) {
                           boolean doit = true;
                           byte result = 0;
                           if (JGConfigDBCGoD.CONFIG_GOD_ENABLED
                              && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED
                              && this.destroyer
                              && this.damage * this.DAMAGE_REDUCTION / 2.0 > entityKi.damage / 2.0) {
                              entityKi.func_70106_y();
                              continue;
                           }

                           if (JRMCoreConfig.dat5705 != 1.0
                              && !entityKi.isShield()
                              && !entityKi.isExplosion()
                              && !this.field_70128_L
                              && !entityKi.field_70128_L) {
                              result = this.killWeakerAttack(this, entityKi);
                           }

                           if (result == 0) {
                              if (entityKi != null && !entityKi.field_70128_L && (entityKi.isShield() || entityKi.isExplosion())) {
                                 doit = false;
                              }

                              if (entityKi != null && !entityKi.field_70128_L && !this.field_70128_L) {
                                 this.kiClashed = true;
                                 this.kiClashedList.add(entity);
                                 if (doit && entityKi.shootingEntity != this.shootingEntity) {
                                    float dam = (float)(((EntityEnergyAtt)entity).getDamage() / 2.0);
                                    float spe = ((EntityEnergyAtt)entity).getSpe() * 2.0F;
                                    float den = ((EntityEnergyAtt)entity).getDen() * 10.0F;
                                    float damt = (float)(this.damage / 2.0);
                                    float spet = this.speed * 2.0F;
                                    float dent = this.density * 10.0F;
                                    long power = this.getPower(entityKi);
                                    if (power >= 0L) {
                                       float res = ((damt - dam) / damt + (spe - spet) / spe + (dent - den) / dent) / 3.0F;
                                       this.field_70159_w *= res;
                                       this.field_70181_x *= res;
                                       this.field_70179_y *= res;
                                       if (JGConfigDBCGoD.CONFIG_GOD_ENABLED
                                          && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED
                                          && ((EntityEnergyAtt)entity).destroyer
                                          && !(((EntityEnergyAtt)entity).damage * this.DAMAGE_REDUCTION / 2.0 > this.damage / 2.0)) {
                                          ((EntityEnergyAtt)entity).field_70159_w = this.field_70159_w;
                                          ((EntityEnergyAtt)entity).field_70181_x = this.field_70181_x;
                                          ((EntityEnergyAtt)entity).field_70179_y = this.field_70179_y;
                                       }

                                       if (((EntityEnergyAtt)entity).getAirTicks() < this.ticksInAir) {
                                          this.ticksInAir = ((EntityEnergyAtt)entity).getAirTicks();
                                       }
                                    }

                                    this.conn = 2;
                                 }
                              }
                           }
                        } else if (this.isShield() && !this.field_70170_p.field_72995_K) {
                           boolean doit = true;
                           if ((int)(Math.random() * 3.0) == 0) {
                              doit = false;
                           }

                           if (doit && this.shootingEntity instanceof EntityPlayer) {
                              boolean qckmth = !entityKi.isShield() && !entityKi.isExplosion();
                              if (qckmth) {
                                 String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                 JRMCoreH.setString(
                                    JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1),
                                    (EntityPlayer)this.shootingEntity,
                                    JRMCoreH.techNbt[this.technum]
                                 );
                              }
                           }
                        }
                     }
                  }
               }

               if (this.isExplosion() && (this.hasEffect() || !entity.equals(this.shootingEntity))) {
                  EntityEnergyAtt energyEntity = null;
                  if (entity instanceof EntityEnergyAtt) {
                     energyEntity = (EntityEnergyAtt)entity;
                  }

                  if (entity instanceof EntityLivingBase) {
                     if (movingObject == null) {
                        movingObject = new MovingObjectPosition(entity);
                     }

                     if (this.target == null && JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                        this.target = entity;
                     }
                  }

                  if ((energyEntity == null || energyEntity.shootingEntity instanceof EntityDBC || this.kiClashedList.contains(entity)) && entity != null) {
                     float var20 = MathHelper.func_76133_a(
                        this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y
                     );
                     int var23 = (int)(
                        this.damage * (this.hasEffect() ? 3.0F : 1.0F) * (entity.equals(this.shootingEntity) ? JRMCoreConfig.dat5701 / 100.0 : 1.0)
                     );
                     this.giveExperience(entity, 1);
                     DamageSource damagesource = null;
                     if (this.shootingEntity == null || this.shootingEntity instanceof EntityDBC && entity instanceof EntityDBC) {
                        this.func_70106_y();
                        return;
                     }

                     damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                     if (this.func_70027_ad()) {
                        entity.func_70015_d(5);
                     }

                     double motX = entity.field_70159_w;
                     double motY = entity.field_70181_x;
                     double motZ = entity.field_70179_y;
                     if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g) && entity.func_70097_a(damagesource, var23)) {
                        if (this.type < 7 && JRMCoreConfig.dat5706[this.type]) {
                           entity.field_70159_w = motX;
                           entity.field_70181_x = motY;
                           entity.field_70179_y = motZ;
                        }

                        if (entity instanceof EntityLivingBase) {
                           if (!this.field_70170_p.field_72995_K && this.shootingEntity instanceof EntityPlayer) {
                              String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                              JRMCoreH.setString(
                                 JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1),
                                 (EntityPlayer)this.shootingEntity,
                                 JRMCoreH.techNbt[this.technum]
                              );
                           }

                           if (this.knockbackStrength > 0 && !entity.equals(this.shootingEntity) && (this.type >= 7 || JRMCoreConfig.dat5706[this.type])) {
                              float var25 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                              if (var25 > 0.0F) {
                                 entity.func_70024_g(
                                    this.field_70159_w * this.knockbackStrength * 0.6F / var25, 0.1, this.field_70179_y * this.knockbackStrength * 0.6F / var25
                                 );
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (!this.isShield()) {
            if (movingObject != null && movingObject.field_72308_g != this.shootingEntity) {
               if (!this.field_70170_p.field_72995_K
                  && !this.isExplosion()
                  && !JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)
                  && this.canSpiralNotGoThrough()) {
                  this.playSoundAtEntity(
                     this,
                     JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer
                        ? "jinryuudragonbc:DBC5.hakai"
                        : this.ExplSound,
                     1.0F,
                     1.0F
                  );
               }

               if (movingObject.field_72308_g != null
                  && (this.shootingEntity instanceof EntityPlayer || movingObject.field_72308_g instanceof EntityPlayer)
                  && this.isContinuesWave()
                  && this.shooterHolds) {
                  if (this.shootingEntity instanceof EntityPlayer) {
                     this.trgtX = (float)this.field_70165_t;
                     this.trgtY = (float)this.field_70163_u;
                     this.trgtZ = (float)this.field_70161_v;
                     byte b = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng");
                     if (b == 1) {
                        if (this.target != null) {
                           if (this.waveCount == 20) {
                              this.wave++;
                              if (JRMCoreConfig.ContinuesEnergyAttackTimer == 0 && this.wave > 2) {
                                 this.wave = 2;
                              }

                              if (!this.field_70170_p.field_72995_K) {
                                 EntityPlayer Player = (EntityPlayer)this.shootingEntity;
                                 byte curRel = JRMCoreH.getByte(Player, "jrmcRelease");
                                 int curEn = JRMCoreH.getInt(Player, "jrmcEnrgy");
                                 float cost2 = (float)((double)this.cost * curRel * 0.01F * (this.perc * 0.02F) * JRMCoreConfig.dat5696[this.type][2]);
                                 if (!(curEn - cost2 > 0.0F)) {
                                    this.func_70106_y();
                                 }

                                 if (cost2 < curEn) {
                                    if (!JRMCoreH.isInCreativeMode(this.shootingEntity)) {
                                       JRMCoreH.setInt(curEn - cost2, Player, "jrmcEnrgy");
                                    }

                                    this.damage = (double)this.originDmg * curRel * 0.01F * this.perc * 0.02F * JRMCoreConfig.dat5696[this.type][1];
                                 } else {
                                    this.func_70106_y();
                                 }
                              }

                              if (this.wave == 1 && movingObject.field_72308_g instanceof EntityLivingBase && !this.field_70170_p.field_72995_K) {
                                 EntityLivingBase var24 = (EntityLivingBase)movingObject.field_72308_g;
                                 if (this.shootingEntity instanceof EntityPlayer) {
                                    String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                    JRMCoreH.setString(
                                       JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1),
                                       (EntityPlayer)this.shootingEntity,
                                       JRMCoreH.techNbt[this.technum]
                                    );
                                 }
                              }

                              int var23 = (int)this.damage;
                              DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                              if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)
                                 && this.target.func_70097_a(damagesource, var23)) {
                                 this.weakenSpiral();
                              }

                              if (JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown) {
                                 this.field_70159_w *= 0.05F;
                                 this.field_70181_x *= 0.05F;
                                 this.field_70179_y *= 0.05F;
                                 this.target.field_70159_w *= 0.05F;
                                 this.target.field_70181_x *= 0.05F;
                                 this.target.field_70179_y *= 0.05F;
                              } else {
                                 this.target.field_70159_w = this.field_70159_w;
                                 this.target.field_70181_x = this.field_70181_x;
                                 this.target.field_70179_y = this.field_70179_y;
                                 this.target.field_70165_t = this.field_70165_t;
                                 this.target.field_70163_u = this.field_70163_u;
                                 this.target.field_70161_v = this.field_70161_v;
                              }
                           }

                           this.target.field_70159_w = this.field_70159_w;
                           this.target.field_70181_x = this.field_70181_x;
                           this.target.field_70179_y = this.field_70179_y;
                        }

                        this.waveCount--;
                        if (this.waveCount <= 0) {
                           this.waveCount = 20;
                        }

                        if (JRMCoreConfig.ContinuesEnergyAttackTimer > 0 && this.wave >= JRMCoreConfig.ContinuesEnergyAttackTimer) {
                           if (this.hasEffect() && !this.isShield() && !this.isExplosion()) {
                              this.createExplosion(2);
                           }

                           this.func_70106_y();
                        }
                     } else {
                        this.shrinkWave();
                     }
                  }

                  if (movingObject.field_72308_g instanceof EntityLivingBase) {
                     if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                        this.setTarget(movingObject.field_72308_g);
                     }
                  } else {
                     this.shrinkWave();
                  }
               } else if (movingObject.field_72308_g == null
                  || !(this.shootingEntity instanceof EntityPlayer) && !(movingObject.field_72308_g instanceof EntityPlayer)) {
                  this.xTile = movingObject.field_72311_b;
                  this.yTile = movingObject.field_72312_c;
                  this.zTile = movingObject.field_72309_d;
                  this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                  this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
                  this.inGround = true;
                  if (this.inTile.func_149688_o() != Material.field_151579_a) {
                     this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, this);
                  }

                  if (movingObject.field_72308_g != null && this.shootingEntity != null) {
                     float var20 = MathHelper.func_76133_a(
                        this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y
                     );
                     int var23 = (int)this.damage;
                     DamageSource damagesource = null;
                     if (this.shootingEntity == null || this.shootingEntity instanceof EntityDBC && movingObject.field_72308_g instanceof EntityDBC) {
                        this.func_70106_y();
                        return;
                     }

                     damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                     if (this.func_70027_ad()) {
                        movingObject.field_72308_g.func_70015_d(5);
                     }

                     double motX = movingObject.field_72308_g.field_70159_w;
                     double motY = movingObject.field_72308_g.field_70181_x;
                     double motZ = movingObject.field_72308_g.field_70179_y;
                     if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                        if (movingObject.field_72308_g.func_70097_a(damagesource, var23)) {
                           this.weakenSpiral();
                           if (this.type < 7 && !JRMCoreConfig.dat5706[this.type]) {
                              movingObject.field_72308_g.field_70159_w = motX;
                              movingObject.field_72308_g.field_70181_x = motY;
                              movingObject.field_72308_g.field_70179_y = motZ;
                           }

                           if (movingObject.field_72308_g instanceof EntityLivingBase
                              && this.knockbackStrength > 0
                              && (this.type >= 7 || JRMCoreConfig.dat5706[this.type])) {
                              float var25 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                              if (var25 > 0.0F) {
                                 movingObject.field_72308_g
                                    .func_70024_g(
                                       this.field_70159_w * this.knockbackStrength * 0.6F / var25,
                                       0.1,
                                       this.field_70179_y * this.knockbackStrength * 0.6F / var25
                                    );
                              }
                           }

                           if (this.hasEffect() && !this.isShield() && !this.isExplosion()) {
                              this.createExplosion(2);
                           }

                           this.func_70106_y();
                        } else if (!this.isShield() && !this.isExplosion()) {
                           this.field_70159_w *= -0.1F;
                           this.field_70181_x *= -0.1F;
                           this.field_70179_y *= -0.1F;
                           this.field_70177_z += 180.0F;
                           this.field_70126_B += 180.0F;
                           this.func_70106_y();
                           this.ticksInAir = 0;
                        }
                     }
                  }
               } else {
                  float var20 = MathHelper.func_76133_a(
                     this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y
                  );
                  int var23 = (int)this.damage;
                  this.giveExperience(movingObject.field_72308_g, 1);
                  DamageSource damagesource = null;
                  if (this.shootingEntity == null || this.shootingEntity instanceof EntityDBC && movingObject.field_72308_g instanceof EntityDBC) {
                     this.func_70106_y();
                     return;
                  }

                  damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                  if (this.func_70027_ad()) {
                     movingObject.field_72308_g.func_70015_d(5);
                  }

                  double motX = movingObject.field_72308_g.field_70159_w;
                  double motY = movingObject.field_72308_g.field_70181_x;
                  double motZ = movingObject.field_72308_g.field_70179_y;
                  if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                     if (movingObject.field_72308_g.func_70097_a(damagesource, var23)) {
                        this.weakenSpiral();
                        if (this.type < 7 && !JRMCoreConfig.dat5706[this.type]) {
                           movingObject.field_72308_g.field_70159_w = motX;
                           movingObject.field_72308_g.field_70181_x = motY;
                           movingObject.field_72308_g.field_70179_y = motZ;
                        }

                        if (movingObject.field_72308_g instanceof EntityLivingBase) {
                           if (!this.field_70170_p.field_72995_K && this.shootingEntity instanceof EntityPlayer) {
                              boolean doit = true;
                              if ((this.isBarrage() || this.isExplosion()) && (int)(Math.random() * 6.0) == 0) {
                                 doit = false;
                              }

                              if (doit) {
                                 String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                 JRMCoreH.setString(
                                    JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1),
                                    (EntityPlayer)this.shootingEntity,
                                    JRMCoreH.techNbt[this.technum]
                                 );
                              }
                           }

                           if (this.knockbackStrength > 0 && (this.type >= 7 || JRMCoreConfig.dat5706[this.type])) {
                              float var25 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                              if (var25 > 0.0F) {
                                 movingObject.field_72308_g
                                    .func_70024_g(
                                       this.field_70159_w * this.knockbackStrength * 0.6F / var25,
                                       0.1,
                                       this.field_70179_y * this.knockbackStrength * 0.6F / var25
                                    );
                              }
                           }
                        }

                        if (this.canSpiralNotGoThrough() && !this.isShield() && !this.isExplosion()) {
                           if (this.hasEffect()) {
                              this.createExplosion(2);
                           }

                           this.func_70106_y();
                        }
                     } else if (movingObject.field_72308_g.func_70089_S()
                        && !DBCConfig.KiAttackGoThroughInvulnerableEnemies
                        && !this.isShield()
                        && !this.isExplosion()
                        && this.canSpiralNotGoThrough()) {
                        this.field_70159_w *= -0.1F;
                        this.field_70181_x *= -0.1F;
                        this.field_70179_y *= -0.1F;
                        this.field_70177_z += 180.0F;
                        this.field_70126_B += 180.0F;
                        this.func_70106_y();
                        this.ticksInAir = 0;
                     }
                  }
               }
            } else if ((this.wave > 0 || this.waveCount < 20) && this.target != null && this.target.field_70128_L) {
               this.shrinkWave();
            }
         }

         if (!this.isShield() && !this.isExplosion()) {
            this.field_70165_t = this.field_70165_t + this.field_70159_w;
            this.field_70163_u = this.field_70163_u + this.field_70181_x;
            this.field_70161_v = this.field_70161_v + this.field_70179_y;
         }

         this.ShieldPushAwayEntities();
         if (this.field_70125_A - this.field_70127_C >= 180.0) {
            this.field_70127_C += 360.0F;
         }

         if (this.field_70177_z - this.field_70126_B < -180.0F) {
            this.field_70126_B -= 360.0F;
         }

         if (this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
         }

         float var22 = 1.0F;
         float var11 = 0.0F;
         if (this.func_70090_H()) {
            for (int var26 = 0; var26 < 4; var26++) {
               float var27 = 0.25F;
               this.field_70170_p
                  .func_72869_a(
                     "bubble",
                     this.field_70165_t - this.field_70159_w * var27,
                     this.field_70163_u - this.field_70181_x * var27,
                     this.field_70161_v - this.field_70179_y * var27,
                     this.field_70159_w,
                     this.field_70181_x,
                     this.field_70179_y
                  );
            }

            var22 = 1.0F;
         }

         if (!this.isShield() && !this.isExplosion()) {
            this.field_70159_w *= var22;
            this.field_70181_x *= var22;
            this.field_70179_y *= var22;
            this.field_70181_x -= var11;
         }

         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      }

      if (this.field_70170_p.field_72995_K
         && this.field_70128_L
         && this.shootingEntity != null
         && this.shootingEntity instanceof EntityPlayer
         && this.shooterHolds) {
         EntityPlayer player = (EntityPlayer)this.shootingEntity;
         ExtendedPlayer.get(player).setAnimKiShoot(0);
         this.shrinkWave();
      }
   }

   public void func_70106_y() {
      super.func_70106_y();
      if (this.field_70170_p.field_72995_K
         && this.field_70170_p.field_72995_K
         && this.field_70128_L
         && this.shootingEntity != null
         && this.shootingEntity instanceof EntityPlayer
         && this.shooterHolds) {
         EntityPlayer player = (EntityPlayer)this.shootingEntity;
         ExtendedPlayer.get(player).setAnimKiShoot(0);
         this.shrinkWave();
         DBCClientTickHandler.nuller();
         JRMCoreH.isShtng = false;
      }
   }

   private byte checkReaction(Entity entity, boolean react) {
      String nev = EntityList.func_75621_b(entity);
      if (JRMCoreConfig.dat5704) {
         JRMCoreH.log("[JRMC CONSOLE] Name of Entity that was hit by a Ki Blast: " + nev + " | (Can be used for Reaction Config!)");
      }

      byte data = 0;
      if (entity instanceof EntityEnergyAtt) {
         nev = nev + "!" + ((EntityEnergyAtt)entity).getType();
      }

      if (JRMCoreConfig.dat5702.get(nev) != null) {
         data = JRMCoreConfig.dat5702.get(nev);
         if (react) {
            this.checkReacts(entity, data);
         }
      }

      if (JRMCoreConfig.dat5703.get(this.getType() + "." + nev) != null) {
         data = JRMCoreConfig.dat5703.get(this.getType() + "." + nev);
         if (react) {
            this.checkReacts(entity, data);
         }
      }

      return data;
   }

   private void checkReacts(Entity entity, byte data) {
      switch (data) {
         case 1:
            this.func_70106_y();
            break;
         case 2:
            if (!entity.field_70128_L) {
               entity.func_70106_y();
            }
            break;
         case 3:
            if (this.effect == 1 && !entity.field_70128_L) {
               entity.func_70106_y();
            }
            break;
         case 4:
            int var23 = (int)this.damage;
            DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
            double motX = entity.field_70159_w;
            double motY = entity.field_70181_x;
            double motZ = entity.field_70179_y;
            if (!JRMCoreH.isFusionSpectator(entity) && entity.func_70097_a(damagesource, var23)) {
               this.weakenSpiral();
               if (this.type < 7 && !JRMCoreConfig.dat5706[this.type]) {
                  entity.field_70159_w = motX;
                  entity.field_70181_x = motY;
                  entity.field_70179_y = motZ;
               }
            }
            break;
         case 5:
            if (this.effect == 1) {
               int var23 = (int)this.damage;
               DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
               double motX = entity.field_70159_w;
               double motY = entity.field_70181_x;
               double motZ = entity.field_70179_y;
               if (!JRMCoreH.isFusionSpectator(entity) && entity.func_70097_a(damagesource, var23)) {
                  this.weakenSpiral();
                  if (this.type < 7 && !JRMCoreConfig.dat5706[this.type]) {
                     entity.field_70159_w = motX;
                     entity.field_70181_x = motY;
                     entity.field_70179_y = motZ;
                  }
               }
            }
      }
   }

   @Override
   public long getPower(Entity entity) {
      String powerFormula = JRMCoreConfig.KiAttackPowerFormula;
      long Power = 0L;
      long damage = (long)((EntityEnergyAtt)entity).getDamage();
      long speed = ((EntityEnergyAtt)entity).getSpe();
      long density = ((EntityEnergyAtt)entity).getDen();
      String[] Formula = powerFormula.toLowerCase()
         .replace(" ", "")
         .replace("(", "")
         .replace("damage", damage + "")
         .replace("speed", speed + "")
         .replace("density", density + "")
         .split("\\)");

      for (int i = 0; i < Formula.length; i++) {
         String formulaSegment = i == 0 ? Formula[i] : Formula[i].substring(1);
         String method = formulaSegment.contains("+")
            ? "+"
            : (
               formulaSegment.contains("-")
                  ? "-"
                  : (formulaSegment.contains("*") ? "*" : (formulaSegment.contains("/") ? "/" : (formulaSegment.contains("%") ? "%" : "null")))
            );
         long value1 = Long.parseLong(formulaSegment.split("\\" + method)[0]);
         long value2 = 1L;
         long result = value1;
         if (!method.equals("null")) {
            value2 = Long.parseLong(formulaSegment.split("\\" + method)[1]);
            result = JGMathHelper.StringMethod(method, value1, value2);
         }

         if (i > 0) {
            String method2 = Formula[i].substring(0, 1);
            Power = JGMathHelper.StringMethod(method2, Power, result);
         } else {
            Power = result;
         }
      }

      return Power;
   }

   private boolean canSpiralNotGoThrough() {
      return this.isSpiral() ? !JRMCoreConfig.dat5708[this.effect] : true;
   }

   private void createExplosion(int type) {
      if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer) {
         type = 10;
      }

      JRMCoreH.newExpl(
         this.field_70170_p,
         this,
         this.field_70165_t,
         this.field_70163_u,
         this.field_70161_v,
         this.explevel,
         false,
         this.damage,
         this.shootingEntity,
         (byte)type
      );
   }

   private List checkForEntitiesInside() {
      AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
      return this.field_70170_p.func_72839_b(this, aabb);
   }

   private void giveExperience(Entity entity, int amount) {
      if (JRMCoreH.DGE(entity) && !this.givenExp) {
         JRMCoreH.jrmcExp(this.shootingEntity, amount, this.getType());
         this.givenExp = true;
      }
   }

   private void playSoundAtEntity(Entity entity, String s, float f, float f1) {
      this.field_70170_p.func_72956_a(entity, s, f, f1);
      if (this.isWave() && this.shooterHolds) {
         this.field_70170_p.func_72908_a(this.strtX(), this.strtY(), this.strtZ(), s, f, f1);
      }
   }

   private void shrinkWave() {
      JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
      if (!this.shrink) {
         this.shrink();
      }
   }

   private void shrink() {
      this.shrink = true;
      this.func_70096_w().func_75692_b(20, 1);
   }

   private void setTarget(Entity entity) {
      this.target = entity;
      this.hadTarget = true;
   }

   private byte killWeakerAttack(EntityEnergyAtt attack1, EntityEnergyAtt attack2) {
      long power1 = this.getPower(attack1);
      long power2 = this.getPower(attack2);
      boolean amIStronger = power1 > power2;
      if (power1 != power2) {
         if (amIStronger) {
            if (power1 / JRMCoreConfig.dat5705 >= power2) {
               return 2;
            }
         } else if (power2 / JRMCoreConfig.dat5705 >= power1) {
            this.func_70106_y();
            return 1;
         }
      }

      return 0;
   }

   private void handleJutsuWallClash(EntityEnergyAttJ3 shield) {
      if (JRMCoreH.NC() && shield != this.shootingEntity && shield instanceof EntityEnAttacks) {
         long shieldPower = shield.getPower(shield);
         long kiPower = this.getPower(this);
         double kiDamage = this.getDamage();
         if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer && kiPower > shieldPower) {
            shield.func_70106_y();
            return;
         }

         if (kiPower > shieldPower) {
            this.setDamage((float)this.getDamage() - (float)shield.getDamage());
            shield.func_70106_y();
         } else if (kiPower < shieldPower) {
            shield.setDamage((float)shield.getDamage() - (float)kiDamage);
            this.func_70106_y();
         } else {
            shield.func_70106_y();
            this.func_70106_y();
         }

         shield.field_70159_w = 0.0;
         shield.field_70181_x = 0.0;
         shield.field_70179_y = 0.0;
      }
   }

   private void handleKiaiClash(EntityEnergyAtt attack) {
      float dam = (float)attack.getDamage();
      if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer && this.damage * this.DAMAGE_REDUCTION / 2.0 > dam) {
         attack.func_70106_y();
         String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
         JRMCoreH.setString(
            JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1),
            (EntityPlayer)this.shootingEntity,
            JRMCoreH.techNbt[this.technum]
         );
      } else {
         if (this.damage / 2.0 > dam) {
            this.setDamage(this.getDamage() - dam);
            attack.func_70106_y();
         } else if (this.damage / 2.0 < dam) {
            attack.setDamage(dam - this.damage / 2.0);
            if (this.shootingEntity instanceof EntityPlayer) {
               boolean doit2 = false;
               if (JRMCoreConfig.dat5707 != 0 && (int)(Math.random() * 100.0) < JRMCoreConfig.dat5707) {
                  doit2 = true;
               }

               if (doit2) {
                  String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                  JRMCoreH.setString(
                     JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1),
                     (EntityPlayer)this.shootingEntity,
                     JRMCoreH.techNbt[this.technum]
                  );
               }
            }

            this.func_70106_y();
         } else {
            this.func_70106_y();
            attack.func_70106_y();
         }
      }
   }

   public boolean isWave() {
      return this.getType() == 0;
   }

   public boolean isBlast() {
      return this.getType() == 1;
   }

   public boolean isDisk() {
      return this.getType() == 2;
   }

   public boolean isLaser() {
      return this.getType() == 3;
   }

   public boolean isLargeBlast() {
      return this.getType() == 5;
   }

   public boolean isSpiral() {
      return this.getType() == 4;
   }

   public boolean isBarrage() {
      return this.getType() == 6;
   }

   public boolean isShield() {
      return this.getType() == 7;
   }

   public boolean isExplosion() {
      return this.getType() == 8;
   }

   private boolean hasEffect() {
      return this.effect == 1;
   }

   private void ShieldPushAwayEntities() {
      if (!this.field_70170_p.field_72995_K && this.isShield() && this.hasEffect()) {
         Entity var5 = null;
         List var6 = this.checkForEntitiesInside();

         for (int var9 = 0; var9 < var6.size(); var9++) {
            var5 = (Entity)var6.get(var9);
            if (!var5.equals(this.shootingEntity) && var5 instanceof EntityLivingBase) {
               float res = 0.5F;
               var5.field_70159_w = (res - (var5.field_70165_t - this.field_70165_t)) * -1.0;
               var5.field_70181_x = (res - (var5.field_70163_u - this.field_70163_u)) * -1.0;
               var5.field_70179_y = (res - (var5.field_70161_v - this.field_70161_v)) * -1.0;
               var5.field_70133_I = true;
            }
         }
      }
   }

   @Override
   public void func_70014_b(NBTTagCompound nbt) {
      nbt.func_74777_a("xTile", (short)this.xTile);
      nbt.func_74777_a("yTile", (short)this.yTile);
      nbt.func_74777_a("zTile", (short)this.zTile);
      nbt.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
      nbt.func_74774_a("inData", (byte)this.inData);
      nbt.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
      nbt.func_74780_a("damage", this.damage);
      nbt.func_74776_a("size", this.size);
      nbt.func_74757_a("destroyer", this.destroyer);
   }

   @Override
   public void func_70037_a(NBTTagCompound nbt) {
      this.xTile = nbt.func_74765_d("xTile");
      this.yTile = nbt.func_74765_d("yTile");
      this.zTile = nbt.func_74765_d("zTile");
      this.inTile = Block.func_149729_e(nbt.func_74771_c("inTile") & 255);
      this.inData = nbt.func_74771_c("inData") & 255;
      this.inGround = nbt.func_74771_c("inGround") == 1;
      if (nbt.func_74764_b("damage")) {
         this.damage = nbt.func_74769_h("damage");
      }

      if (nbt.func_74764_b("size")) {
         this.size = nbt.func_74760_g("size");
      }

      if (nbt.func_74764_b("destroyer")) {
         this.destroyer = nbt.func_74767_n("destroyer");
      }
   }

   public void func_70100_b_(EntityPlayer par1EntityPlayer) {
   }

   protected boolean func_70041_e_() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public float func_70053_R() {
      return 0.0F;
   }

   public void setDamage(double par1) {
      this.damage = par1;
   }

   public double getDamage() {
      return this.damage * (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer ? this.DAMAGE_REDUCTION : 1.0F);
   }

   public void setKnockbackStrength(int par1) {
      this.knockbackStrength = par1;
   }

   public boolean func_70075_an() {
      return false;
   }

   public boolean func_82704_a(Entity var1) {
      return false;
   }

   public void writeSpawnData(ByteBuf data) {
      data.writeInt(this.shootingEntity == null ? 0 : this.shootingEntity.func_145782_y());
      data.writeInt(this.target == null ? 0 : this.target.func_145782_y());
      data.writeByte(this.perc);
      data.writeByte(this.type);
      data.writeByte(this.speed);
      data.writeByte(this.perc);
      data.writeByte(this.effect);
      data.writeInt(this.color);
      data.writeInt(this.dam);
      data.writeByte(this.density);
      data.writeShort(this.sincantation);
      String se = "";
      if (this.sts != null) {
         for (int i = 0; i < this.sts.length; i++) {
            se = se + "," + this.sts[i];
         }

         se = se.substring(1);
      }

      ByteBufUtils.writeUTF8String(data, se);
      data.writeByte(this.technum);
      data.writeShort(this.sfire);
      data.writeShort(this.smove);
      data.writeFloat(this.strtX);
      data.writeFloat(this.strtY);
      data.writeFloat(this.strtZ);
      data.writeFloat(this.size);
      data.writeFloat(this.trgtX);
      data.writeFloat(this.trgtY);
      data.writeFloat(this.trgtZ);
      data.writeByte(this.shrink ? 1 : 0);
      data.writeFloat(this.explevel);
      data.writeInt(this.color2);
      data.writeDouble(this.damage);
      data.writeDouble(this.damageOriginal);
      data.writeInt(this.damageTaken);
      data.writeBoolean(this.destroyer);
   }

   public void readSpawnData(ByteBuf data) {
      int first = data.readInt();
      this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
      int second = data.readInt();
      this.target = first == 0 ? this.target : this.field_70170_p.func_73045_a(second);
      this.perc = data.readByte();
      this.type = data.readByte();
      this.speed = data.readByte();
      this.perc = data.readByte();
      this.effect = data.readByte();
      this.color = data.readInt();
      this.dam = data.readInt();
      this.density = data.readByte();
      this.sincantation = data.readShort();
      String[] se = ByteBufUtils.readUTF8String(data).split(",");
      if (se.length < 3) {
         byte[] sts2 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
         this.sts = sts2;
      } else {
         byte[] sts2 = new byte[se.length];

         for (int i = 0; i < se.length; i++) {
            sts2[i] = Byte.parseByte(se[i]);
         }

         this.sts = sts2;
      }

      this.technum = data.readByte();
      this.sfire = data.readShort();
      this.smove = data.readShort();
      this.strtX = data.readFloat();
      this.strtY = data.readFloat();
      this.strtZ = data.readFloat();
      this.size = data.readFloat();
      this.trgtX = data.readFloat();
      this.trgtY = data.readFloat();
      this.trgtZ = data.readFloat();
      this.shrink = data.readByte() == 1;
      this.explevel = data.readFloat();
      this.color2 = data.readInt();
      this.damage = data.readDouble();
      this.damageOriginal = data.readDouble();
      this.damageTaken = data.readInt();
      this.destroyer = data.readBoolean();
   }

   public Entity getThrower() {
      return null;
   }

   public void setThrower(Entity entity) {
   }

   @SideOnly(Side.CLIENT)
   public boolean isInRangeToRenderVec3D(Vec3 par1Vec3) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public double getMaxRenderDistanceSquared() {
      return 65536.0;
   }

   public boolean func_70112_a(double par1) {
      return true;
   }

   public void generateParticles(EntityEnergyAtt entityBlast, Entity entity, int color, int color2, boolean startSpawn) {
      if (entityBlast != null && entity != null && entityBlast.field_70170_p.field_72995_K) {
         for (int i = 0; i < 3; i++) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); k++) {
               float colorFixer = 0.7F;
               float red = (color >> 16 & 0xFF) / 255.0F;
               float green = (color >> 8 & 0xFF) / 255.0F;
               float blue = (color & 0xFF) / 255.0F;
               red *= 0.7F;
               green *= 0.7F;
               blue *= 0.7F;
               float red2 = (color2 >> 16 & 0xFF) / 255.0F;
               float green2 = (color2 >> 8 & 0xFF) / 255.0F;
               float blue2 = (color2 & 0xFF) / 255.0F;
               float alpha = 1.0F;
               float out = 1.0F;
               float in = 1.5F;
               float life = 0.4F * entity.field_70131_O;
               float extra_scale = 0.3F;
               int dea = 30;
               float target_fullsize_one1 = 0.32F;
               float targetsizeMin = entity.field_70131_O * (8.0F / target_fullsize_one1) * 0.01F;
               float target_fullsize_one2 = 0.32F;
               float targetsizeMax = entity.field_70131_O * (26.0F / target_fullsize_one2) * 0.01F;
               double x = (Math.random() * (entity.field_70131_O * 2.0F) - entity.field_70131_O) * 0.8F;
               double y = (Math.random() * (entity.field_70131_O * 2.0F) - entity.field_70131_O) * 0.8F;
               double z = (Math.random() * (entity.field_70131_O * 2.0F) - entity.field_70131_O) * 0.8F;
               Vec3 vec3 = entity.func_70040_Z();
               double d8 = entity.field_70130_N + (startSpawn ? 0.0F : 1.5F);
               double d9 = entity.field_70131_O;
               double x2;
               double y2;
               double z2;
               if (startSpawn) {
                  x2 = entityBlast.strtX();
                  y2 = entityBlast.strtY();
                  z2 = entityBlast.strtZ();
                  x2 += vec3.field_72450_a * d8;
                  y2 += vec3.field_72448_b * d9 + entity.field_70131_O * 0.4F;
                  z2 += vec3.field_72449_c * d8;
               } else {
                  x2 = entityBlast.field_70165_t;
                  y2 = entityBlast.field_70163_u;
                  z2 = entityBlast.field_70161_v;
               }

               x2 += x;
               y2 += y;
               z2 += z;
               float rotationYaw = -entityBlast.field_70177_z;
               float rotationPitch = -entityBlast.field_70125_A;
               double motionX = -MathHelper.func_76126_a(rotationYaw / 180.0F * (float) Math.PI)
                  * MathHelper.func_76134_b(rotationPitch / 180.0F * (float) Math.PI);
               double motionZ = MathHelper.func_76134_b(rotationYaw / 180.0F * (float) Math.PI)
                  * MathHelper.func_76134_b(rotationPitch / 180.0F * (float) Math.PI);
               double motionY = -MathHelper.func_76126_a(rotationPitch / 180.0F * (float) Math.PI);
               if (startSpawn) {
                  x2 += -motionX * 3.0;
                  y2 += -motionY * 3.0;
                  y2 -= entityBlast.field_70131_O * 0.25;
                  z2 += -motionZ * 3.0;
               }

               motionX *= 0.5;
               motionY *= 0.5;
               motionY += (float)(Math.random() * 0.1F) - 0.05F;
               motionZ *= 0.5;
               if (startSpawn) {
                  motionX *= -1.0;
                  motionY *= -1.0;
                  motionZ *= -1.0;
               }

               float scaleStart = ((float)(Math.random() * 0.02F) + 0.02F) * life * 0.3F;
               float scaleEnd = ((float)(Math.random() * 0.01F) + 0.02F) * life * 0.3F;
               float scaleSpeed = 0.2F * life * 0.3F;
               int textureID = (int)(Math.random() * 3.0) + 8;
               Entity particle = new EntityCusPar(
                  "jinryuumodscore:bens_particles.png",
                  entity.field_70170_p,
                  0.2F,
                  0.2F,
                  x2,
                  y2,
                  z2,
                  0.0,
                  0.0,
                  0.0,
                  -motionX,
                  -motionY,
                  -motionZ,
                  0.0F,
                  textureID,
                  8,
                  3,
                  32,
                  false,
                  0.0F,
                  false,
                  0.0F,
                  1,
                  "",
                  30,
                  2,
                  scaleStart,
                  scaleEnd,
                  scaleStart,
                  0,
                  red,
                  green,
                  blue,
                  0.0F,
                  0.0F,
                  0.0F,
                  1.0F,
                  1.0F,
                  1.0F,
                  2,
                  0.6F,
                  0.0F,
                  0.9F,
                  0.95F,
                  0.06F,
                  false,
                  -1,
                  true,
                  null
               );
               entity.field_70170_p.func_72838_d(particle);
               Entity particle2 = new EntityCusPar(
                  "jinryuumodscore:bens_particles.png",
                  entity.field_70170_p,
                  0.2F,
                  0.2F,
                  x2,
                  y2,
                  z2,
                  0.0,
                  0.0,
                  0.0,
                  -motionX,
                  -motionY,
                  -motionZ,
                  0.0F,
                  textureID,
                  8,
                  3,
                  32,
                  false,
                  0.0F,
                  false,
                  0.0F,
                  1,
                  "",
                  30,
                  2,
                  scaleStart * 0.8F,
                  scaleEnd * 0.8F,
                  scaleStart * 0.8F,
                  0,
                  red2,
                  green2,
                  blue2,
                  0.0F,
                  0.0F,
                  0.0F,
                  1.0F,
                  1.0F,
                  1.0F,
                  2,
                  0.6F,
                  0.0F,
                  0.9F,
                  0.95F,
                  0.06F,
                  false,
                  -1,
                  true,
                  null
               );
               entity.field_70170_p.func_72838_d(particle2);
            }
         }
      }
   }

   public void weakenSpiral() {
      if (this.isSpiral() && !this.canSpiralNotGoThrough() && JRMCoreConfig.SpiralWeakensAfterHit > 0) {
         double minusDamage = JRMCoreConfig.SpiralWeakensAfterHit / 100.0;
         if (!JRMCoreConfig.SpiralWeakensBasedOnStartDamage) {
            if (this.damage <= 0.0) {
               this.func_70106_y();
            } else {
               this.damage *= minusDamage;
            }
         } else {
            if (!((1.0 - minusDamage) * this.damageTaken > this.damage) && !(this.damage <= 0.0)) {
               this.damage = this.damageOriginal * (1.0 - minusDamage * this.damageTaken);
            } else {
               this.func_70106_y();
            }

            this.damageTaken++;
         }

         if (this.damage < 0.0) {
            this.damage = 0.0;
            this.func_70106_y();
         }
      }
   }

   public float rad(float angle) {
      return angle * (float) Math.PI / 180.0F;
   }
}
