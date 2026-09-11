package JinRyuu.JRMCore.entity;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.DragonBC.common.Npcs.EntityRRMecha;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon2;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityPrjtls_1 extends EntityArrow implements IEntityAdditionalSpawnData, IProjectile {
   public static final int MECHA_ROCKET1 = 0;
   public static final int MECHA_ROCKET2 = 1;
   public static final int MECHA_ROCKET3 = 2;
   public static final int GUN_SHOT1 = 3;
   public static final int GUN_SHOT2 = 4;
   public static final int BAZOOKA = 5;
   public static final int GUN_MAJOR = 6;
   public int[] damages = null;
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   private int inData = 0;
   private boolean inGround = false;
   public Entity field_70250_c;
   private int ticksInGround;
   private int ticksInAir = 0;
   public double field_70255_ao = 0.0;
   private int knockbackStrength;
   private float explevel;
   public int wpnTyp = -1;

   public int getTicksInGround() {
      return this.ticksInGround;
   }

   public int getWpnTyp() {
      return this.wpnTyp;
   }

   public EntityPrjtls_1(World world) {
      super(world);
      this.func_70105_a(0.5F, 0.5F);
   }

   public EntityPrjtls_1(World world, double par2, double par4, double par6) {
      super(world);
      this.func_70105_a(0.5F, 0.5F);
      this.func_70107_b(par2, par4, par6);
      this.field_70129_M = 0.0F;
   }

   public EntityPrjtls_1(World world, Entity shootingEntity, Entity target, float par4, float par5, int id) {
      super(world);
      this.func_70105_a(0.5F, 0.5F);
      this.wpnTyp = id;
      this.field_70250_c = shootingEntity;
      this.field_70251_a = 0;
      this.field_70163_u = shootingEntity.field_70163_u + shootingEntity.func_70047_e() - 0.1F - 1.0;
      double var6x = target.field_70165_t - shootingEntity.field_70165_t;
      double var8 = target.field_70163_u + target.func_70047_e() - 0.7F - this.field_70163_u;
      double var10 = target.field_70161_v - shootingEntity.field_70161_v;
      double var12 = MathHelper.func_76133_a(var6x * var6x + var10 * var10) * (shootingEntity instanceof EntityRRMecha ? 1.0 : 2.0);
      if (var12 >= 1.0E-7) {
         float var14 = (float)(Math.atan2(var10, var6x) * 180.0 / Math.PI) - 90.0F;
         float var15 = (float)(-(Math.atan2(var8, var12) * 180.0 / Math.PI));
         double var16 = var6x / var12;
         double var18 = var10 / var12;
         this.func_70012_b(shootingEntity.field_70165_t + var16, this.field_70163_u, shootingEntity.field_70161_v + var18, var14, var15);
         this.field_70129_M = 0.0F;
         float var20 = (float)var12 * 0.2F;
         this.func_70186_c(var6x, var8, var10, par4, par5);
      }

      if (this.damages == null) {
         this.damages = new int[]{
            DBCConfig.NPC_RRMech1_Dam,
            DBCConfig.NPC_RRMech2_Dam,
            DBCConfig.NPC_RRMech3_Dam,
            DBCConfig.RRSoldierDAM,
            DBCConfig.RRSoldier2DAM,
            DBCConfig.RRSoldier3DAM,
            DBCConfig.RRMajorDAM
         };
      }

      this.field_70255_ao = this.damages[id] / 2;
   }

   public EntityPrjtls_1(World world, EntityLivingBase shootingEntity, EntityLivingBase target, float par4, float par5) {
      super(world);
      this.field_70250_c = shootingEntity;
      this.field_70251_a = 0;
      this.field_70163_u = shootingEntity.field_70163_u + shootingEntity.func_70047_e() - 0.1F;
      double var6 = target.field_70165_t - shootingEntity.field_70165_t;
      double var8 = target.field_70163_u + target.func_70047_e() - 0.7F - this.field_70163_u;
      double var10 = target.field_70161_v - shootingEntity.field_70161_v;
      double var12 = MathHelper.func_76133_a(var6 * var6 + var10 * var10);
      if (var12 >= 1.0E-7) {
         float var14 = (float)(Math.atan2(var10, var6) * 180.0 / Math.PI) - 90.0F;
         float var15 = (float)(-(Math.atan2(var8, var12) * 180.0 / Math.PI));
         double var16 = var6 / var12;
         double var18 = var10 / var12;
         this.func_70012_b(shootingEntity.field_70165_t + var16, this.field_70163_u, shootingEntity.field_70161_v + var18, var14, var15);
         this.field_70129_M = 0.0F;
         float var20 = (float)var12 * 0.2F;
         this.func_70186_c(var6, var8 + var20, var10, par4, par5);
      }
   }

   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(16, (byte)0);
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

   public void func_70071_h_() {
      if (this.field_70173_aa >= 400) {
         this.func_70106_y();
      }

      boolean isRocket = this.wpnTyp != 3 && this.wpnTyp != 4 && this.wpnTyp != 6;
      if (isRocket && !this.field_70170_p.field_72995_K && (this.field_70173_aa == 1 || this.field_70173_aa % 5 == 0)) {
         this.field_70170_p.func_72956_a(this, "jinryuudragonbc:DBC4.rocket_travel", 0.3F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
      }

      if (this.field_70170_p.field_72995_K) {
         float sizeMulti = isRocket ? 1.0F : 0.5F;

         for (int i = 0; i < (isRocket ? 5 : 2); i++) {
            mod_JRMCore.proxy
               .func_gcp(
                  this,
                  EntityCusPars.PART5,
                  Math.random() * 0.5 - 0.25,
                  0.0 - this.field_70131_O / 2.0F,
                  Math.random() * 0.5 - 0.25,
                  Math.random() * 0.05 - 0.025,
                  Math.random() * 0.1 + 0.05,
                  Math.random() * 0.05 - 0.025,
                  0.05F * sizeMulti,
                  0.05F * sizeMulti,
                  0.05F * sizeMulti
               );
         }
      }

      this.func_70030_z();
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

      if (this.inGround) {
         if (isRocket) {
            JRMCoreH.newExpl(
               this.field_70170_p,
               this,
               this.field_70165_t,
               this.field_70163_u,
               this.field_70161_v,
               this.explevel,
               false,
               this.field_70255_ao,
               this.field_70250_c,
               (byte)5
            );
         }

         this.func_70106_y();
         int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
         if (block == this.inTile && var19 == this.inData) {
            this.ticksInGround++;
         } else {
            this.inGround = false;
            this.field_70159_w = this.field_70159_w * (this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70181_x = this.field_70181_x * (this.field_70146_Z.nextFloat() * 0.2F);
            this.field_70179_y = this.field_70179_y * (this.field_70146_Z.nextFloat() * 0.2F);
            this.ticksInGround = 0;
            this.ticksInAir = 0;
         }
      } else {
         this.ticksInAir++;
         Vec3 var17 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         Vec3 var3 = Vec3.func_72443_a(
            this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y
         );
         MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
         var17 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         var3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
         if (var4 != null) {
            var3 = Vec3.func_72443_a(var4.field_72307_f.field_72450_a, var4.field_72307_f.field_72448_b, var4.field_72307_f.field_72449_c);
         }

         if (!this.field_70170_p.field_72995_K) {
            Entity var5 = null;
            List var6 = this.field_70170_p
               .func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double var7 = 0.0;

            for (int var9 = 0; var9 < var6.size(); var9++) {
               Entity var10 = (Entity)var6.get(var9);
               if (var10.func_70067_L() && (var10 != this.field_70250_c || this.ticksInAir >= 5)) {
                  float var11 = 0.3F;
                  AxisAlignedBB var12 = var10.field_70121_D.func_72314_b(var11, var11, var11);
                  MovingObjectPosition var13 = var12.func_72327_a(var17, var3);
                  if (var13 != null && this.isNotRedRibbon(var10)) {
                     double var14 = var17.func_72438_d(var13.field_72307_f);
                     if (var14 < var7 || var7 == 0.0) {
                        var5 = var10;
                        var7 = var14;
                     }
                  }
               }
            }

            if (var5 != null) {
               var4 = new MovingObjectPosition(var5);
            }
         }

         if (var4 != null) {
            if (var4.field_72308_g != null && this.isNotRedRibbon(var4.field_72308_g)) {
               float var20 = MathHelper.func_76133_a(
                  this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y
               );
               int var23 = MathHelper.func_76143_f(var20 * this.field_70255_ao);
               if (this.func_70241_g()) {
                  var23 += this.field_70146_Z.nextInt(var23 / 2 + 2);
               }

               DamageSource damagesource = null;
               if (this.field_70250_c == null) {
                  damagesource = mod_DragonBC.causePrjctlsDamage(this, this);
               } else {
                  damagesource = mod_DragonBC.causePrjctlsDamage(this, this.field_70250_c);
               }

               if (this.func_70027_ad()) {
                  var4.field_72308_g.func_70015_d(5);
               }

               if (var4.field_72308_g.func_70097_a(damagesource, var23)) {
                  if (var4.field_72308_g instanceof EntityLivingBase && this.knockbackStrength > 0) {
                     float var25 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                     if (var25 > 0.0F) {
                        var4.field_72308_g
                           .func_70024_g(
                              this.field_70159_w * this.knockbackStrength * 0.6F / var25, 0.1, this.field_70179_y * this.knockbackStrength * 0.6F / var25
                           );
                     }
                  }

                  if (isRocket) {
                     JRMCoreH.newExpl(
                        this.field_70170_p,
                        this,
                        this.field_70165_t,
                        this.field_70163_u,
                        this.field_70161_v,
                        this.explevel,
                        false,
                        this.field_70255_ao,
                        this.field_70250_c,
                        (byte)5
                     );
                  }

                  this.func_70106_y();
               }
            } else {
               this.xTile = var4.field_72311_b;
               this.yTile = var4.field_72312_c;
               this.zTile = var4.field_72309_d;
               this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
               this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
               this.field_70159_w = (float)(var4.field_72307_f.field_72450_a - this.field_70165_t);
               this.field_70181_x = (float)(var4.field_72307_f.field_72448_b - this.field_70163_u);
               this.field_70179_y = (float)(var4.field_72307_f.field_72449_c - this.field_70161_v);
               float var20 = MathHelper.func_76133_a(
                  this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y
               );
               this.field_70165_t = this.field_70165_t - this.field_70159_w / var20 * 0.05F;
               this.field_70163_u = this.field_70163_u - this.field_70181_x / var20 * 0.05F;
               this.field_70161_v = this.field_70161_v - this.field_70179_y / var20 * 0.05F;
               this.inGround = true;
               this.func_70243_d(false);
               if (this.inTile.func_149688_o() != Material.field_151579_a) {
                  this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, this);
               }
            }
         }

         if (this.func_70241_g()) {
            for (int var9 = 0; var9 < 4; var9++) {
               this.field_70170_p
                  .func_72869_a(
                     "crit",
                     this.field_70165_t + this.field_70159_w * var9 / 4.0,
                     this.field_70163_u + this.field_70181_x * var9 / 4.0,
                     this.field_70161_v + this.field_70179_y * var9 / 4.0,
                     -this.field_70159_w,
                     -this.field_70181_x + 0.2,
                     -this.field_70179_y
                  );
            }
         }

         this.field_70165_t = this.field_70165_t + this.field_70159_w;
         this.field_70163_u = this.field_70163_u + this.field_70181_x;
         this.field_70161_v = this.field_70161_v + this.field_70179_y;
         float var20 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
         this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
         this.field_70125_A = (float)(Math.atan2(this.field_70181_x, var20) * 180.0 / Math.PI);

         while (this.field_70125_A - this.field_70127_C < -180.0F) {
            this.field_70127_C -= 360.0F;
         }

         while (this.field_70125_A - this.field_70127_C >= 180.0F) {
            this.field_70127_C += 360.0F;
         }

         while (this.field_70177_z - this.field_70126_B < -180.0F) {
            this.field_70126_B -= 360.0F;
         }

         while (this.field_70177_z - this.field_70126_B >= 180.0F) {
            this.field_70126_B += 360.0F;
         }

         this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
         this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
         float var22 = 0.99F;
         float var11 = 0.05F;
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

            var22 = 0.8F;
         }

         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.doBlockCollisions();
      }
   }

   private boolean isNotRedRibbon(Entity entity) {
      return !(entity instanceof EntityRRMecha) && !(entity instanceof EntityRedRibbon) && !(entity instanceof EntityRedRibbon2);
   }

   private void doBlockCollisions() {
      this.func_145775_I();
   }

   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
      par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
      par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
      par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
      par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
      par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
      par1NBTTagCompound.func_74780_a("damage", this.field_70255_ao);
      par1NBTTagCompound.func_74768_a("wpnTyp", this.wpnTyp);
   }

   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
      this.xTile = par1NBTTagCompound.func_74765_d("xTile");
      this.yTile = par1NBTTagCompound.func_74765_d("yTile");
      this.zTile = par1NBTTagCompound.func_74765_d("zTile");
      this.inTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("inTile") & 255);
      this.inData = par1NBTTagCompound.func_74771_c("inData") & 255;
      this.inGround = par1NBTTagCompound.func_74771_c("inGround") == 1;
      if (par1NBTTagCompound.func_74764_b("damage")) {
         this.field_70255_ao = par1NBTTagCompound.func_74769_h("damage");
      }

      this.wpnTyp = par1NBTTagCompound.func_74762_e("wpnTyp");
   }

   public void writeSpawnData(ByteBuf data) {
      data.writeInt(this.field_70250_c == null ? 0 : this.field_70250_c.func_145782_y());
      data.writeDouble((int)this.field_70255_ao);
      data.writeInt(this.wpnTyp);
   }

   public void readSpawnData(ByteBuf data) {
      int first = data.readInt();
      this.field_70250_c = first == 0 ? this.field_70250_c : this.field_70170_p.func_73045_a(first);
      this.field_70255_ao = data.readDouble();
      this.wpnTyp = data.readInt();
   }

   public void func_70100_b_(EntityPlayer par1EntityPlayer) {
      if (!this.field_70170_p.field_72995_K && this.inGround) {
         this.func_70106_y();
      }
   }

   protected boolean func_70041_e_() {
      return false;
   }

   @SideOnly(Side.CLIENT)
   public float func_70053_R() {
      return 0.0F;
   }

   public void func_70239_b(double par1) {
      this.field_70255_ao = par1;
   }

   public double func_70242_d() {
      return this.field_70255_ao;
   }

   public void func_70240_a(int par1) {
      this.knockbackStrength = par1;
   }

   public boolean func_70075_an() {
      return false;
   }

   public void func_70243_d(boolean par1) {
      byte var2 = this.field_70180_af.func_75683_a(16);
      if (par1) {
         this.field_70180_af.func_75692_b(16, (byte)(var2 | 1));
      } else {
         this.field_70180_af.func_75692_b(16, (byte)(var2 & -2));
      }
   }

   public boolean func_70241_g() {
      byte var1 = this.field_70180_af.func_75683_a(16);
      return (var1 & 1) != 0;
   }

   private void explode() {
      float var1 = 2.0F;
      this.field_70170_p.func_72876_a((Entity)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, var1, this.inGround);
   }

   protected void onImpact(MovingObjectPosition var4) {
   }
}
