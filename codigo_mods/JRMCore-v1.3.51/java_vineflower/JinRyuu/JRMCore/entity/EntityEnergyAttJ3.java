package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEnergyAttJ3 extends EntityEnAttacks implements IThrowableEntity, IEntityAdditionalSpawnData, IEntitySelector, IProjectile {
   private int xTile = -1;
   private int yTile = -1;
   private int zTile = -1;
   private Block inTile;
   private int inData = 0;
   private boolean inGround = false;
   private int ticksInGround;
   private double damage = 1.0;
   private String DBCExplSound = "jinryuudragonbc:DBC.expl";
   private String NCExplSound = "jinryuunarutoc:NC1.Explosion";
   private byte type;
   private int dam;
   private byte perc;
   private byte pmjID;
   private int cost;
   private int costPerc;
   private int originDmg;
   private int pwrtyp = 0;
   private String nameJutsu;
   public float size = 1.7F;
   private boolean used = false;
   private int health;
   private byte jtsre;
   private float rota;

   public byte getType() {
      return this.type;
   }

   public int getDam() {
      return this.dam;
   }

   public byte getPerc() {
      return this.perc;
   }

   public float getSizePerc() {
      return this.size;
   }

   public byte getjtsre() {
      return this.jtsre;
   }

   public float getrota() {
      return this.rota;
   }

   public EntityEnergyAttJ3(World par1World) {
      super(par1World);
      this.func_70105_a(this.size, this.size);
   }

   public EntityEnergyAttJ3(byte jtsre, EntityLivingBase entity, byte type, int dam, byte perc, int dam1, int cost, int costPerc) {
      super(entity.field_70170_p);
      this.jtsre = jtsre;
      this.type = type;
      this.dam = dam;
      this.perc = 50;
      this.cost = cost;
      this.costPerc = costPerc;
      this.originDmg = dam1;
      this.pmjID = perc;
      if (this.pmjID != -1) {
         this.nameJutsu = JRMCoreH.trl("nc", JRMCoreH.pmj[this.pmjID][0]);
      }

      this.damage = (double)this.dam * this.perc * 0.02F;
      this.shootingEntity = entity;
      this.pwrtyp = 0;
      if (this.shootingEntity instanceof EntityPlayer) {
         this.pwrtyp = JRMCoreH.PlyrPwr((EntityPlayer)this.shootingEntity);
      }

      this.field_70155_l = 10.0;
      this.func_70105_a(this.size, this.size);
      double d8 = entity.field_70130_N + 1.0F;
      double d9 = entity.field_70131_O;
      Vec3 vec3 = entity.func_70676_i(1.0F);
      double x = entity.field_70165_t + vec3.field_72450_a * d8;
      double y = entity.field_70163_u + vec3.field_72448_b * d8 + entity.field_70131_O * 0.55F;
      double z = entity.field_70161_v + vec3.field_72449_c * d8;
      int spot = -1;
      int checked = 0;

      for (int i = (int)y; spot == -1 && checked < 3; i--) {
         if (!entity.field_70170_p.func_147439_a((int)x, i, (int)z).func_149739_a().toLowerCase().contains("air")) {
            spot = i + 1;
            y = spot;
            break;
         }

         checked++;
      }

      this.func_70012_b(x, y, z, entity.field_70177_z, entity.field_70125_A);
      this.rota = entity.field_70177_z;
   }

   @Override
   protected void func_70088_a() {
      this.field_70180_af.func_75682_a(16, (byte)0);
   }

   public void func_70186_c(double par1, double par3, double par5, float par7, float par8) {
   }

   @SideOnly(Side.CLIENT)
   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
      this.func_70107_b(par1, par3, par5);
      this.func_70101_b(par7, par8);
   }

   @SideOnly(Side.CLIENT)
   public void func_70016_h(double par1, double par3, double par5) {
   }

   public void func_70071_h_() {
      if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
         this.func_70106_y();
      }

      if (this.field_70173_aa == 1) {
         this.func_70105_a(this.size, this.size);
      }

      if (this.field_70173_aa > 500) {
         this.func_70106_y();
      }

      super.func_70071_h_();
      if (!this.field_70170_p.field_72995_K && this.getDamage() <= 0.0) {
         this.func_70106_y();
      }

      Block block = this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
      if (block.func_149688_o() != Material.field_151579_a) {
         block.func_149719_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
         AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
         if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
            this.inGround = true;
         }
      }

      if (this.inGround) {
         int var19 = this.field_70170_p.func_72805_g((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
         if (block.func_149688_o() != Material.field_151579_a && !block.func_149739_a().toLowerCase().contains("air")) {
            this.ticksInGround++;
            this.field_70163_u += 0.1F;
         } else {
            this.inGround = false;
            this.ticksInGround = 0;
         }

         this.func_chins();
      } else {
         this.func_chins();
         this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
         this.doBlockCollisions();
      }
   }

   @Override
   public long getPower(Entity entity) {
      return (long)(this.getDamage() / 2.0);
   }

   private void doBlockCollisions() {
      this.func_145775_I();
   }

   @Override
   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
      par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
      par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
      par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
      par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
      par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
      par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
      par1NBTTagCompound.func_74780_a("damage", this.damage);
   }

   @Override
   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
      this.xTile = par1NBTTagCompound.func_74765_d("xTile");
      this.yTile = par1NBTTagCompound.func_74765_d("yTile");
      this.zTile = par1NBTTagCompound.func_74765_d("zTile");
      this.inTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("inTile") & 255);
      this.inData = par1NBTTagCompound.func_74771_c("inData") & 255;
      this.inGround = par1NBTTagCompound.func_74771_c("inGround") == 1;
      if (par1NBTTagCompound.func_74764_b("damage")) {
         this.damage = par1NBTTagCompound.func_74769_h("damage");
      }
   }

   public void func_70100_b_(EntityPlayer e) {
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
      return this.damage;
   }

   public void setKnockbackStrength(int par1) {
   }

   public boolean func_82704_a(Entity var1) {
      return false;
   }

   public void writeSpawnData(ByteBuf data) {
      data.writeInt(this.shootingEntity == null ? 0 : this.shootingEntity.func_145782_y());
      data.writeByte(this.perc);
      data.writeByte(this.type);
      data.writeByte(this.jtsre);
      data.writeInt(this.dam);
      data.writeFloat(this.size);
      data.writeFloat(this.rota);
   }

   public void readSpawnData(ByteBuf data) {
      int first = data.readInt();
      this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
      this.perc = data.readByte();
      this.type = data.readByte();
      this.jtsre = data.readByte();
      this.dam = data.readInt();
      this.size = data.readFloat();
      this.rota = data.readFloat();
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

   public void setJutsuName(String name) {
      this.nameJutsu = name;
   }

   public boolean func_70067_L() {
      return true;
   }

   public float func_70111_Y() {
      return 0.1F;
   }

   public AxisAlignedBB func_70114_g(Entity p_70114_1_) {
      return this.func_70046_E();
   }

   public AxisAlignedBB func_70046_E() {
      return this.field_70121_D;
   }

   private void func_chins() {
      Vec3 var17 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      Vec3 var3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
      var17 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      var3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      if (var4 != null) {
         var3 = Vec3.func_72443_a(var4.field_72307_f.field_72450_a, var4.field_72307_f.field_72448_b, var4.field_72307_f.field_72449_c);
      }

      if (!this.field_70170_p.field_72995_K) {
         Entity var5 = null;
         List entityList = this.field_70170_p
            .func_72839_b(this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(0.5, 0.5, 0.5));
         double var7 = 0.0;

         for (int n = 0; n < entityList.size(); n++) {
            Entity entity = (Entity)entityList.get(n);
            if (entity.func_70067_L() && entity != this.shootingEntity) {
               float var11 = 0.0F;
               AxisAlignedBB var12 = entity.field_70121_D.func_72314_b(var11, var11, var11);
               MovingObjectPosition var13 = var12.func_72327_a(var17, var3);
               if (var13 != null) {
                  double var14 = var17.func_72438_d(var13.field_72307_f);
                  if (var14 < var7 || var7 == 0.0) {
                     var5 = entity;
                     var7 = var14;
                  }
               }
            }
         }

         if (var5 != null) {
            new MovingObjectPosition(var5);
         }
      }

      if (!this.field_70170_p.field_72995_K) {
         AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
         List entityList = this.field_70170_p.func_72839_b(this, aabb);

         for (int n = 0; n < entityList.size(); n++) {
            Entity entity = (Entity)entityList.get(n);
            if (entity != this.shootingEntity && entity instanceof EntityEnAttacks) {
               long shieldPower = this.getPower(this);
               long targetPower = 0L;
               double targetDamage = 0.0;
               if (entity instanceof EntityEnergyAttJ) {
                  targetPower = ((EntityEnergyAttJ)entity).getPower(entity);
                  targetDamage = ((EntityEnergyAttJ)entity).getDamage();
               } else if (entity instanceof EntityEnergyAttJ2) {
                  targetPower = ((EntityEnergyAttJ2)entity).getPower(entity);
                  targetDamage = ((EntityEnergyAttJ2)entity).getDamage();
               }

               if (targetPower > shieldPower) {
                  if (entity instanceof EntityEnergyAttJ) {
                     ((EntityEnergyAttJ)entity).setDamage((float)targetDamage - (float)this.getDamage());
                  } else if (entity instanceof EntityEnergyAttJ2) {
                     ((EntityEnergyAttJ2)entity).setDamage((float)targetDamage - (float)this.getDamage());
                  }

                  this.func_70106_y();
               } else if (targetPower < shieldPower) {
                  this.setDamage((float)this.getDamage() - (float)targetDamage);
                  entity.func_70106_y();
               } else {
                  entity.func_70106_y();
                  this.func_70106_y();
               }

               this.field_70159_w = 0.0;
               this.field_70181_x = 0.0;
               this.field_70179_y = 0.0;
            }
         }
      }
   }

   public boolean func_70075_an() {
      return false;
   }
}
