package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreEH;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import JinRyuu.JRMCore.server.JGMathHelper;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntitySafeZone extends EntityJRMC implements IEntityAdditionalSpawnData {
   public static final HashMap<Class, Boolean> safezoneListResults = new HashMap<>();
   public final float var1 = 8.0F;
   public final float maxDistanceForPlayer = 4.0F;
   protected Entity closestEntity;
   private int lookTime;
   private Class watchedClass;
   public int holdRotation = -1;
   private List playerList = new ArrayList();
   private int jumpTicks = 0;
   public int duplicatesRadius = 2;
   public int safezoneRadiusXZ = 60;
   public int safezoneRadiusY = 60;
   public String name = "";

   public EntitySafeZone(World world) {
      super(world);
      this.field_70728_aV = 0;
      this.func_94058_c("");
      this.func_94061_f(false);
      int r2 = this.field_71093_bK == 22 ? 20 : (this.field_71093_bK == 24 ? -54 : 0);
      this.safezoneRadiusXZ += r2;
      this.safezoneRadiusY += r2;
   }

   public EntitySafeZone(World world, String name) {
      this(world);
      this.name = name;
   }

   @Override
   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
   }

   protected boolean func_70780_i() {
      return true;
   }

   @Override
   protected boolean func_70692_ba() {
      return false;
   }

   protected void func_70619_bc() {
   }

   public void func_70108_f(Entity entity) {
   }

   public void func_70024_g(double par1, double par3, double par5) {
      this.field_70160_al = false;
   }

   protected void func_70018_K() {
      this.field_70133_I = false;
   }

   public boolean func_70104_M() {
      return false;
   }

   protected void func_82167_n(Entity entity) {
   }

   @Override
   public boolean func_70097_a(DamageSource damageSource, float par2) {
      return false;
   }

   @Override
   public boolean func_70652_k(Entity entity) {
      return false;
   }

   @Override
   public int MaxHealth() {
      return 2000;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public String getTexture() {
      return "jinryuumodscore:npcs/TrainingShadowDummy.png";
   }

   public boolean func_70085_c(EntityPlayer player) {
      return super.func_70085_c(player);
   }

   public void shouldExecute() {
      if (this.holdRotation == -1) {
         this.closestEntity = this.field_70170_p.func_72890_a(this, 4.0);
         if (this.closestEntity != null) {
            this.watchedClass = EntityPlayer.class;
            this.func_70671_ap()
               .func_75650_a(
                  this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + 2.0, this.closestEntity.field_70161_v, 10.0F, this.func_70646_bf()
               );
         }
      }
   }

   public boolean addInstance(Class cl, boolean b) {
      safezoneListResults.put(cl, b);
      return b;
   }

   public boolean instanceOf(Entity entity) {
      if (JRMCoreConfig.SafeZoneEntityBlacklist.size() == 0) {
         return false;
      }

      Class entityClass = entity.getClass();
      if (safezoneListResults != null && safezoneListResults.size() > 0 && safezoneListResults.containsKey(entityClass)) {
         return safezoneListResults.get(entityClass);
      }

      String name = entityClass.toString();

      try {
         if (!JRMCoreConfig.SafeZoneEntityBlacklist.containsKey(name)
            || JRMCoreConfig.SafeZoneEntityWhitelist.size() != 0 && JRMCoreConfig.SafeZoneEntityWhitelist.containsKey(name)) {
            for (String key : JRMCoreConfig.SafeZoneEntityWhitelist.keySet()) {
               if ((JRMCoreH.DBC() || !key.startsWith("JinRyuu.DragonBC")) && (JRMCoreH.NC() || !key.startsWith("JinRyuu.NarutoC"))) {
                  Class cl = Class.forName(key);
                  if (cl.isAssignableFrom(entityClass)) {
                     return this.addInstance(entityClass, false);
                  }
               }
            }

            for (String key : JRMCoreConfig.SafeZoneEntityBlacklist.keySet()) {
               if ((JRMCoreH.DBC() || !key.startsWith("JinRyuu.DragonBC")) && (JRMCoreH.NC() || !key.startsWith("JinRyuu.NarutoC"))) {
                  Class cl = Class.forName(key);
                  if (cl.isAssignableFrom(entityClass)) {
                     return this.addInstance(entityClass, true);
                  }
               }
            }

            return this.addInstance(entityClass, false);
         } else {
            return this.addInstance(entityClass, true);
         }
      } catch (Exception var7) {
         return this.addInstance(entityClass, false);
      }
   }

   public boolean isEntityOnTheBlacklist(Entity entity) {
      return this.instanceOf(entity);
   }

   public boolean isMostLikelyMe(Entity entity) {
      if (entity instanceof EntitySafeZone) {
         EntitySafeZone zone = (EntitySafeZone)entity;
         if (zone.getClass().toString().equals(this.getClass().toString())
            && this.name.equals(zone.name)
            && this.duplicatesRadius == zone.duplicatesRadius
            && this.safezoneRadiusY == zone.safezoneRadiusY
            && this.safezoneRadiusXZ == zone.safezoneRadiusXZ
            && JGMathHelper.doubleSmallerThan(this.field_70165_t - zone.field_70165_t, 1.0)
            && JGMathHelper.doubleSmallerThan(this.field_70163_u - zone.field_70163_u, 1.0)
            && JGMathHelper.doubleSmallerThan(this.field_70161_v - zone.field_70161_v, 1.0)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void func_70071_h_() {
      if (!this.field_70170_p.field_72995_K && this.field_70128_L) {
         this.func_70106_y();
      } else {
         if (this.field_70173_aa / 20 == 0) {
            boolean add = true;
            int length = JRMCoreEH.allSafeZones.size();

            for (int i = length - 1; i >= 0; i--) {
               EntitySafeZone safezone = (EntitySafeZone)JRMCoreEH.allSafeZones.get(i);
               if (safezone != null && this.isMostLikelyMe((Entity)JRMCoreEH.allSafeZones.get(i))) {
                  add = false;
               } else if (safezone == null || safezone.field_70128_L) {
                  JRMCoreEH.allSafeZones.remove(i);
               }
            }

            if (add) {
               JRMCoreEH.allSafeZones.add(this);
            }
         }

         if (!this.field_70170_p.field_72995_K) {
            int r = this.duplicatesRadius;
            if (r > 0) {
               List removeDuplicates = new ArrayList();
               AxisAlignedBB ab = AxisAlignedBB.func_72330_a(
                  this.field_70165_t - r,
                  this.field_70163_u - r,
                  this.field_70161_v - r,
                  this.field_70165_t + r,
                  this.field_70163_u + r,
                  this.field_70161_v + r
               );
               List list = this.field_70170_p.func_72839_b(this.field_70717_bb, ab);

               for (int i = 0; list.size() > i; i++) {
                  Entity entity = (Entity)list.get(i);
                  if (entity instanceof EntitySafeZone) {
                     EntitySafeZone p = (EntitySafeZone)entity;
                     if (!removeDuplicates.contains(p)) {
                        removeDuplicates.add(entity);
                     }
                  }
               }

               for (int i = 0; removeDuplicates.size() > i; i++) {
                  Entity entity = (Entity)removeDuplicates.get(i);
                  if (i > 0) {
                     entity.func_70106_y();
                     removeDuplicates.remove(entity);
                  }
               }
            }

            if (JRMCoreConfig.sfzns) {
               List list2 = this.createSafeZoneList();
               if (this.safezoneRadiusXZ != 0 && this.safezoneRadiusY != 0) {
                  for (int i = 0; list2.size() > i; i++) {
                     Entity entity = (Entity)list2.get(i);
                     if (this.isEntityOnTheBlacklist(entity)) {
                        entity.func_70106_y();
                     }

                     if (entity instanceof EntityEnAttacks) {
                        if (((EntityEnAttacks)entity).shootingEntity instanceof EntityPlayer) {
                           String t = JRMCoreH.trlai("jinryuujrmcore.entitymasters.nofightinsafe");
                           ((EntityPlayer)((EntityEnAttacks)entity).shootingEntity)
                              .func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
                        }

                        entity.func_70106_y();
                     }

                     if (entity instanceof EntityPunch) {
                        if (((EntityPunch)entity).shootingEntity instanceof EntityPlayer) {
                           String t = JRMCoreH.trlai("jinryuujrmcore.entitymasters.nofightinsafe");
                           ((EntityPlayer)((EntityPunch)entity).shootingEntity)
                              .func_145747_a(new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw));
                        }

                        entity.func_70106_y();
                     }

                     if (entity instanceof EntityPlayer) {
                        EntityPlayer p = (EntityPlayer)entity;
                        if (!this.playerList.contains(p)) {
                           if (this.name != null && this.name.length() > 0) {
                              this.playerList.add(entity);
                              p.func_145747_a(new ChatComponentText("jinryuujrmcore.entitymasters.insafezone:" + this.name));
                           } else {
                              this.playerList.add(entity);
                              p.func_145747_a(new ChatComponentText("jinryuujrmcore.entitymasters.insaafezone:" + this.name));
                           }
                        }
                     }
                  }
               }

               for (int i = 0; this.playerList.size() > i; i++) {
                  Entity entity = (Entity)this.playerList.get(i);
                  boolean delete = true;
                  if (list2.contains(entity)) {
                     delete = false;
                  }

                  if (delete) {
                     this.playerList.remove(entity);
                     EntityPlayer player = (EntityPlayer)entity;
                     if (this.name != null && this.name.length() > 0) {
                        player.func_145747_a(new ChatComponentText("jinryuujrmcore.entitymasters.leftsafe:" + this.name));
                     } else {
                        player.func_145747_a(new ChatComponentText("jinryuujrmcore.entitymasters.leftasafe:" + this.name));
                     }
                  }
               }
            } else if (this.playerList.size() > 0) {
               this.playerList.clear();
            }
         }

         super.func_70071_h_();
         this.shouldExecute();
      }
   }

   public void func_70106_y() {
      int length = JRMCoreEH.allSafeZones.size();

      for (int i = length - 1; i >= 0; i--) {
         EntitySafeZone safezone = (EntitySafeZone)JRMCoreEH.allSafeZones.get(i);
         if (safezone != null && this.isMostLikelyMe((Entity)JRMCoreEH.allSafeZones.get(i))) {
            JRMCoreEH.allSafeZones.remove(i);
            break;
         }
      }

      this.removeAllPlayers();
      super.func_70106_y();
   }

   @Override
   public void func_70636_d() {
      this.field_70181_x = 0.0;
      if (this.jumpTicks > 0) {
         this.jumpTicks--;
      }

      if (this.field_70716_bi > 0) {
         double d0 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / this.field_70716_bi;
         double d1 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / this.field_70716_bi;
         double d2 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / this.field_70716_bi;
         double d3 = MathHelper.func_76138_g(this.field_70712_bm - this.field_70177_z);
         this.field_70177_z = (float)(this.field_70177_z + d3 / this.field_70716_bi);
         this.field_70125_A = (float)(this.field_70125_A + (this.field_70705_bn - this.field_70125_A) / this.field_70716_bi);
         this.field_70716_bi--;
         this.func_70107_b(d0, d1, d2);
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
      } else if (!this.func_70613_aW()) {
         this.field_70159_w = 0.0;
         this.field_70181_x = 0.0;
         this.field_70179_y = 0.0;
      }

      if (Math.abs(this.field_70159_w) < 0.005) {
         this.field_70159_w = 0.0;
      }

      if (Math.abs(this.field_70181_x) < 0.005) {
         this.field_70181_x = 0.0;
      }

      if (Math.abs(this.field_70179_y) < 0.005) {
         this.field_70179_y = 0.0;
      }

      this.field_70170_p.field_72984_F.func_76320_a("ai");
      if (this.func_70610_aX()) {
         this.field_70703_bu = false;
         this.field_70702_br = 0.0F;
         this.field_70701_bs = 0.0F;
         this.field_70704_bt = 0.0F;
      } else if (this.func_70613_aW()) {
         if (this.func_70650_aV()) {
            this.field_70170_p.field_72984_F.func_76320_a("newAi");
            this.func_70619_bc();
            this.field_70170_p.field_72984_F.func_76319_b();
         } else {
            this.field_70170_p.field_72984_F.func_76320_a("oldAi");
            this.field_70170_p.field_72984_F.func_76319_b();
            this.field_70759_as = this.field_70177_z;
         }
      }

      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("jump");
      if (this.field_70703_bu) {
         if (this.func_70090_H() || this.func_70058_J()) {
            this.field_70181_x += 0.04F;
         } else if (this.field_70122_E && this.jumpTicks == 0) {
            this.func_70664_aZ();
            this.jumpTicks = 10;
         }
      } else {
         this.jumpTicks = 0;
      }

      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("travel");
      this.field_70702_br *= 0.0F;
      this.field_70701_bs *= 0.0F;
      this.field_70704_bt *= 0.0F;
      this.field_70170_p.field_72984_F.func_76319_b();
      this.field_70170_p.field_72984_F.func_76320_a("push");
      if (!this.field_70170_p.field_72995_K) {
         this.func_85033_bc();
      }

      this.field_70170_p.field_72984_F.func_76319_b();
      EntityPlayer var2 = this.field_70170_p.func_72890_a(this, 8.0);
      if (var2 != null && this.holdRotation == -1) {
         this.closestEntity = var2;
         this.field_70700_bx = 10 + this.field_70146_Z.nextInt(20);
      } else {
         this.field_70704_bt = (this.field_70146_Z.nextFloat() - 0.5F) * 20.0F;
      }

      if (this.closestEntity != null && this.holdRotation == -1) {
         this.func_70625_a(this.closestEntity, 10.0F, this.func_70646_bf());
         if (this.field_70700_bx-- <= 0 || this.closestEntity.field_70128_L || this.closestEntity.func_70068_e(this) > 64.0) {
            this.closestEntity = null;
         }
      }
   }

   @Override
   public boolean func_70601_bi() {
      return this.field_70170_p.func_72855_b(this.field_70121_D)
         && this.field_70170_p.func_72945_a(this, this.field_70121_D).isEmpty()
         && !this.field_70170_p.func_72953_d(this.field_70121_D);
   }

   public void func_70625_a(Entity p_70625_1_, float p_70625_2_, float p_70625_3_) {
      double d0 = p_70625_1_.field_70165_t - this.field_70165_t;
      double d2 = p_70625_1_.field_70161_v - this.field_70161_v;
      double d1;
      if (p_70625_1_ instanceof EntityLivingBase) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)p_70625_1_;
         d1 = entitylivingbase.field_70163_u + entitylivingbase.field_70131_O * 0.85F - 1.6F - (this.field_70163_u + this.func_70047_e());
      } else {
         d1 = (p_70625_1_.field_70121_D.field_72338_b + p_70625_1_.field_70121_D.field_72337_e) / 2.0 - (this.field_70163_u + this.func_70047_e());
      }

      double d3 = MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      float f2 = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0F;
      float f3 = (float)(-(Math.atan2(d1, d3) * 180.0 / Math.PI));
      this.field_70125_A = this.updateRotation(this.field_70125_A, f3, p_70625_3_);
      this.field_70177_z = this.updateRotation(this.field_70177_z, f2, p_70625_2_);
   }

   private float updateRotation(float currRot, float intendedRot, float maxIncrement) {
      float f3 = MathHelper.func_76142_g(intendedRot - currRot);
      if (f3 > maxIncrement) {
         f3 = maxIncrement;
      }

      if (f3 < -maxIncrement) {
         f3 = -maxIncrement;
      }

      return currRot + f3;
   }

   public void writeSpawnData(ByteBuf buffer) {
      buffer.writeInt(this.holdRotation);
      buffer.writeInt(this.duplicatesRadius);
      buffer.writeInt(this.safezoneRadiusXZ);
      buffer.writeInt(this.safezoneRadiusY);
      ByteBufUtils.writeUTF8String(buffer, this.name);
   }

   public void readSpawnData(ByteBuf buffer) {
      this.holdRotation = buffer.readInt();
      this.duplicatesRadius = buffer.readInt();
      this.safezoneRadiusXZ = buffer.readInt();
      this.safezoneRadiusY = buffer.readInt();
      this.name = ByteBufUtils.readUTF8String(buffer);
   }

   public void func_70014_b(NBTTagCompound nbt) {
      super.func_70014_b(nbt);
      nbt.func_74768_a("holdRotation", this.holdRotation);
      nbt.func_74768_a("duplicatesRadius", this.duplicatesRadius);
      nbt.func_74768_a("safezoneRadiusXZ", this.safezoneRadiusXZ);
      nbt.func_74768_a("safezoneRadiusY", this.safezoneRadiusY);
      nbt.func_74778_a("safezonename", this.name);
   }

   public void func_70037_a(NBTTagCompound nbt) {
      super.func_70037_a(nbt);
      if (nbt.func_74764_b("holdRotation")) {
         this.holdRotation = nbt.func_74762_e("holdRotation");
      }

      if (nbt.func_74764_b("duplicatesRadius")) {
         this.duplicatesRadius = nbt.func_74762_e("duplicatesRadius");
      }

      if (nbt.func_74764_b("safezoneRadiusXZ")) {
         this.safezoneRadiusXZ = nbt.func_74762_e("safezoneRadiusXZ");
      }

      if (nbt.func_74764_b("safezoneRadiusY")) {
         this.safezoneRadiusY = nbt.func_74762_e("safezoneRadiusY");
      }

      if (nbt.func_74764_b("safezonename")) {
         this.name = nbt.func_74779_i("safezonename");
      }
   }

   public AxisAlignedBB createSafeZoneHitBox() {
      int r2XZ = this.safezoneRadiusXZ;
      int r2Y = this.safezoneRadiusY;
      return AxisAlignedBB.func_72330_a(
         this.field_70165_t - r2XZ,
         this.field_70163_u - r2Y,
         this.field_70161_v - r2XZ,
         this.field_70165_t + r2XZ,
         this.field_70163_u + r2Y,
         this.field_70161_v + r2XZ
      );
   }

   public List createSafeZoneList() {
      AxisAlignedBB ab2 = this.createSafeZoneHitBox();
      return this.field_70170_p.func_72839_b(this, ab2);
   }

   public void removeAllPlayers() {
      for (int i = 0; this.playerList.size() > i; i++) {
         Entity entity = (Entity)this.playerList.get(i);
         EntityPlayer player = (EntityPlayer)entity;
         if (this.name != null && this.name.length() > 0) {
            player.func_145747_a(new ChatComponentText("jinryuujrmcore.entitymasters.leftsafe:" + this.name));
         } else {
            player.func_145747_a(new ChatComponentText("jinryuujrmcore.entitymasters.leftasafe:" + this.name));
         }
      }

      this.playerList.clear();
   }

   public Entity getClosestEntity() {
      return this.closestEntity;
   }
}
