package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import java.util.ArrayList;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityEng extends Entity {
   private String mot;
   private int type;
   private int color;
   private int color2;
   private float size;
   private byte partid;
   public boolean destroyer = false;
   public float minScale;
   public float maxScale;
   public float maxDamage;
   public boolean added = false;
   public int animation_speed = 0;
   public long animation_start = 0L;
   public int animation_id = 0;
   public int animation_id_Max = 0;
   public int animation_random_Max = 0;
   public ArrayList<Integer> animation_random = new ArrayList<>();
   public float render_scale = 0.0F;
   public float render_scale_max = 2.0F;
   public EntityPlayer user;

   public String getmot() {
      return this.mot;
   }

   public int getType() {
      return this.type - 1;
   }

   public int getColor() {
      return this.color;
   }

   public int getColor2() {
      return this.color2;
   }

   public float getSize() {
      return this.size;
   }

   public byte getPartid() {
      return this.partid;
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

   public EntityEng(World w, double poX, double poY, double poZ, String mot, int type, int color, float size, int partid) {
      super(w);
      this.func_70105_a(2.0F, 5.0F);
      this.field_70165_t = poX;
      this.field_70163_u = poY;
      this.field_70161_v = poZ;
      this.field_70159_w = 0.0;
      this.field_70181_x = 0.0;
      this.field_70179_y = 0.0;
      this.type = type;
      this.color = color;
      this.color2 = -1;
      if (!this.isShield() && !this.isExplosion()) {
         this.setScales();
         float size1 = size;
         this.size = 0.5F + size1;
         if (JRMCoreConfig.eaesl > 0 && size > JRMCoreConfig.eaesl) {
            this.size = JRMCoreConfig.eaesl;
         }

         if (this.isLargeBlast()) {
            this.size = this.size * JRMCoreConfig.ealbm;
         }
      }

      this.partid = (byte)partid;
      this.mot = mot;
      this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      this.moveToUser();
      if (JRMCoreConfig.KiAttackScalesWithUser) {
         this.size = this.size * (this.user == null ? 1.0F : this.user.field_70131_O / 1.8F);
      }

      if (this.user != null && (this.isShield() || this.isExplosion())) {
         this.size = this.user.field_70131_O * 3.0F * (!this.isExplosion() ? 1.0F : 2.0F);
      }
   }

   protected void func_70088_a() {
   }

   protected void func_70037_a(NBTTagCompound nbt) {
   }

   protected void func_70014_b(NBTTagCompound nbt) {
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

   public void func_70071_h_() {
      if (this.field_70170_p.field_72995_K && !JRMCoreClient.mc.func_147113_T()) {
         if (this.user == null) {
            this.moveToUser();
         }

         if (this.user != null) {
            this.createParticles();
         }

         if (this.user != null && !this.user.field_70128_L) {
            ExtendedPlayer props = ExtendedPlayer.get(this.user);
            if (props.getAnimKiShoot() != 0 && props.getAnimKiShootOn() != 0) {
               this.func_70080_a(
                  this.user.field_70165_t, this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F), this.user.field_70161_v, 0.0F, 0.0F
               );
            } else {
               this.func_70106_y();
            }
         } else {
            this.func_70106_y();
         }
      }
   }

   private void moveToUser() {
      if (this.mot.length() > 1) {
         this.user = this.field_70170_p.func_72924_a(this.mot);
         if (this.user != null) {
            this.func_70080_a(
               this.user.field_70165_t, this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F), this.user.field_70161_v, 0.0F, 0.0F
            );
         } else {
            this.func_70106_y();
         }
      } else {
         this.func_70106_y();
      }
   }

   private void createParticles() {
      if (this.user != null
         && this.user.field_70170_p.field_72995_K
         && (this.isWave() || this.isBlast() || this.isLargeBlast() || this.isLaser() || this.isSpiral())) {
         int coloring = JRMCoreH.techCol[this.getColor()];
         int coloring2 = JRMCoreH.techCol2[this.getColor()];
         this.generateParticles(this, this.user, coloring, coloring2);
      }

      if (JGConfigClientSettings.CLIENT_DA16) {
         for (int k = 0; k < JGConfigClientSettings.get_da1(); k++) {
            if (this.getPartid() == 1) {
               float h1 = 1.0F;
               float pl_scale = 2.0F;
               pl_scale = this.user.field_70131_O;
               float scale = this.field_70173_aa * (this.size / 100.0F);
               if (scale > this.size) {
                  scale = this.size;
               }

               float spe2 = 4.0F * scale + 2.0F + pl_scale / 2.0F;
               double x = Math.random() * spe2 - spe2 / 2.0F;
               double y = -1.05F;
               double z = Math.random() * spe2 - spe2 / 2.0F;
               double motx = -x / 50.0 / (pl_scale / 2.0F);
               double motz = -z / 50.0 / (pl_scale / 2.0F);
               float h2 = (JRMCoreH.techCol[this.color] >> 16 & 0xFF) / 255.0F;
               float h3 = (JRMCoreH.techCol[this.color] >> 8 & 0xFF) / 255.0F;
               float h4 = (JRMCoreH.techCol[this.color] & 0xFF) / 255.0F;
               float red = h1 * h2;
               float green = h1 * h3;
               float blue = h1 * h4;
               Entity entity7 = new EntityCusPar(
                  "jinryuudragonbc:bens_particles_attack.png",
                  this.field_70170_p,
                  0.2F,
                  0.2F,
                  this.user.field_70165_t,
                  this.user.field_70163_u,
                  this.user.field_70161_v,
                  x,
                  y,
                  z,
                  motx,
                  0.1 + Math.random() * 0.025F,
                  motz,
                  0.0F,
                  (int)(Math.random() * 3.0) + 56,
                  8,
                  3,
                  32,
                  false,
                  0.0F,
                  false,
                  0.0F,
                  1,
                  "",
                  (27 + (int)spe2) * ((int)(pl_scale / 3.0F) + 1),
                  0,
                  0.001F + (float)(Math.random() * 0.002F),
                  0.0F,
                  0.0F,
                  0,
                  red,
                  green,
                  blue,
                  0.0F,
                  0.0F,
                  0.0F,
                  0.0F,
                  0.0F,
                  0.0F,
                  3,
                  0.0F,
                  0.0F,
                  0.0F,
                  0.0F,
                  0.05F,
                  false,
                  -1,
                  true,
                  this.user
               );
               entity7.field_70170_p.func_72838_d(entity7);
            } else if (this.getPartid() != 4) {
               if (this.getPartid() == 2) {
                  if (this.field_70173_aa % 5 == 0) {
                     float a = 1.0F;
                     float h1 = 1.0F;
                     float scale = this.field_70173_aa * (this.field_70131_O / 100.0F);
                     float pl_s = 1.0F;
                     double x = 0.0;
                     double y = this.user.field_70131_O * 0.7F - 1.5F;
                     y = this.user.field_70131_O * 0.7F - 1.0F;
                     pl_s = this.user.field_70131_O / 2.0F;
                     double z = 0.0;
                     int num = (int)(Math.random() * 4.0) + 1;

                     for (int i = 0; i < num; i++) {
                        int id = (int)(Math.random() * 4.0);
                        float rota = (float)(Math.random() * 0.4F) + 0.4F;
                        float rota4 = (float)(Math.random() * 0.4F) + 0.4F;
                        float scalem = ((float)(Math.random() * 0.02F) + 0.04F) * pl_s;
                        float scales = ((float)(Math.random() * 0.002F) + 0.001F) * pl_s;
                        int rota1 = (int)(Math.random() * 360.0);
                        int rota2 = (int)(Math.random() * 360.0);
                        int rota3 = (int)(Math.random() * 360.0);
                        boolean rot = (int)(Math.random() * 2.0) == 0;
                        float rotsp = (float)(Math.random() * rota) + 0.001F;
                        float h2 = (JRMCoreH.techCol2[this.color] >> 16 & 0xFF) / 255.0F;
                        float h3 = (JRMCoreH.techCol2[this.color] >> 8 & 0xFF) / 255.0F;
                        float h4 = (JRMCoreH.techCol2[this.color] & 0xFF) / 255.0F;
                        float red = h1 * h2;
                        float green = h1 * h3;
                        float blue = h1 * h4;
                        Entity entity = new EntityCusPar(
                           "jinryuudragonbc:bens_particles_attack.png",
                           this.field_70170_p,
                           2.0F,
                           2.0F,
                           this.user.field_70165_t,
                           this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F),
                           this.user.field_70161_v,
                           x,
                           y,
                           z,
                           0.0,
                           0.0,
                           0.0,
                           0.0F,
                           id,
                           4,
                           4,
                           64,
                           true,
                           0.0F,
                           true,
                           0.0F,
                           1,
                           "",
                           25,
                           0,
                           0.1F,
                           scalem,
                           scales,
                           0,
                           red,
                           green,
                           blue,
                           0.0F,
                           0.0F,
                           0.0F,
                           0.0F,
                           0.0F,
                           0.0F,
                           2,
                           0.0F,
                           0.0F,
                           0.95F,
                           0.98F,
                           0.2F,
                           false,
                           -1,
                           true,
                           this.user
                        );
                        ((EntityCusPar)entity).setdata39(rota1);
                        ((EntityCusPar)entity).setdata40(rota2);
                        ((EntityCusPar)entity).setdata41(rota3);
                        ((EntityCusPar)entity).setdata42(3);
                        ((EntityCusPar)entity).setdata45(1.5F);
                        ((EntityCusPar)entity).setRotate(rot);
                        ((EntityCusPar)entity).setRotation_Sp(rotsp);
                        entity.field_70170_p.func_72838_d(entity);
                     }
                  }
               } else if (this.getPartid() == 3 && this.field_70173_aa % 2 == 0) {
                  float life = 0.8F * this.user.field_70131_O;
                  float extra_scale = 1.0F + (this.user.field_70131_O > 2.1F ? this.user.field_70131_O / 2.0F : 0.0F) / 5.0F;
                  float width = this.user.field_70130_N * 3.0F;
                  double x = (Math.random() * 1.0 - 0.5) * (width * 0.8F);
                  double y = Math.random() * (this.field_70131_O * 0.8F) - 0.6F;
                  double z = (Math.random() * 1.0 - 0.5) * (width * 0.8F);
                  double motx = Math.random() * 0.05F - 0.03F;
                  double moty = (Math.random() * 0.1F + 0.1F) * (life * extra_scale * 0.18);
                  double motz = Math.random() * 0.05F - 0.03F;
                  float red = 255.0F;
                  float green = 217.0F;
                  float blue = 25.0F;
                  Entity entity = new EntityCusPar(
                     "jinryuudragonbc:bens_particles_attack.png",
                     this.user.field_70170_p,
                     0.2F,
                     0.2F,
                     this.user.field_70165_t,
                     this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F),
                     this.user.field_70161_v,
                     x,
                     y,
                     z,
                     motx,
                     moty,
                     motz,
                     (float)(Math.random() * 0.01F) - 0.005F,
                     (int)(Math.random() * 3.0) + 59,
                     8,
                     3,
                     32,
                     false,
                     0.0F,
                     false,
                     0.0F,
                     1,
                     "",
                     (int)(30.0F * life * 1.6F),
                     2,
                     ((float)(Math.random() * 0.01F) + 0.01F) * life * extra_scale,
                     ((float)(Math.random() * 0.005F) + 0.005F) * life * extra_scale,
                     0.03F * life * extra_scale,
                     0,
                     red,
                     green,
                     blue,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     0.0F,
                     2,
                     0.0F,
                     0.0F,
                     0.3F,
                     0.35F,
                     0.02F,
                     false,
                     -1,
                     false,
                     null
                  );
                  this.user.field_70170_p.func_72838_d(entity);
               }
            } else {
               if (this.field_70173_aa % 2 == 0) {
                  float a = 1.0F;
                  float h1 = 1.0F;
                  float scale = this.field_70173_aa * (this.field_70131_O / 100.0F);
                  float pl_s = 1.0F;
                  double x = 0.0;
                  double y = this.user.field_70131_O * 0.7F - 1.0F;
                  pl_s = this.user.field_70131_O / 2.0F;
                  double z = 0.0;
                  int num = (int)(Math.random() * 4.0) + 1;

                  for (int i = 0; i < num; i++) {
                     int id = (int)(Math.random() * 3.0) + 4;
                     float rot = (float)(Math.random() * 0.02F) + 0.01F;
                     float scalem = ((float)(Math.random() * 0.15F) + 0.155F) * pl_s;
                     float scales = scalem * 0.01F;
                     boolean forg = (int)(Math.random() * 2.0) == 0;
                     float h2 = (JRMCoreH.techCol[this.color] >> 16 & 0xFF) / 255.0F;
                     float h3 = (JRMCoreH.techCol[this.color] >> 8 & 0xFF) / 255.0F;
                     float h4 = (JRMCoreH.techCol[this.color] & 0xFF) / 255.0F;
                     float red = h1 * h2;
                     float green = h1 * h3;
                     float blue = h1 * h4;
                     Entity entity = new EntityCusPar(
                        "jinryuudragonbc:bens_particles_attack.png",
                        this.field_70170_p,
                        2.0F,
                        2.0F,
                        this.user.field_70165_t,
                        this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F),
                        this.user.field_70161_v,
                        x,
                        y,
                        z,
                        0.0,
                        0.0,
                        0.0,
                        0.0F,
                        id,
                        4,
                        4,
                        64,
                        forg,
                        rot,
                        false,
                        0.0F,
                        1,
                        "",
                        5,
                        1,
                        0.145F * pl_s,
                        scalem,
                        scales,
                        0,
                        red,
                        green,
                        blue,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        2,
                        0.0F,
                        0.0F,
                        0.75F,
                        0.78F,
                        0.3F,
                        false,
                        -1,
                        true,
                        this.user
                     );
                     ((EntityCusPar)entity).setdata39((int)(Math.random() * 360.0));
                     entity.field_70170_p.func_72838_d(entity);
                  }
               }

               if (this.field_70173_aa % 3 == 0) {
                  float a = 1.0F;
                  float h1 = 1.0F;
                  float scale = this.field_70173_aa * (this.field_70131_O / 100.0F);
                  float pl_s = 1.0F;
                  double x = 0.0;
                  double y = this.user.field_70131_O * 0.7F - 1.0F;
                  pl_s = this.user.field_70131_O / 2.0F;
                  double z = 0.0;
                  pl_s = this.user.field_70131_O / 2.0F;
                  int num = 4;
                  boolean forg = (int)(Math.random() * 2.0) == 0;
                  float rot = (float)(Math.random() * 0.02F) + 0.01F;

                  for (int i = 0; i < num; i++) {
                     int id = 7;
                     float scalem = ((float)(Math.random() * 0.03F) + 0.13F) * pl_s;
                     float scales = scalem * 0.1F;
                     float rota = 360.0F / num * i + (int)(Math.random() * (360 / num));
                     float h2 = (JRMCoreH.techCol[this.color] >> 16 & 0xFF) / 255.0F;
                     float h3 = (JRMCoreH.techCol[this.color] >> 8 & 0xFF) / 255.0F;
                     float h4 = (JRMCoreH.techCol[this.color] & 0xFF) / 255.0F;
                     float red = h1 * h2;
                     float green = h1 * h3;
                     float blue = h1 * h4;
                     int life = 30;
                     float transp_sp = 0.18F;
                     float transp_min = 0.75F;
                     float transp_max = 0.78F;
                     Entity entity = new EntityCusPar(
                        "jinryuudragonbc:bens_particles_attack.png",
                        this.field_70170_p,
                        2.0F,
                        2.0F,
                        this.user.field_70165_t,
                        this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F),
                        this.user.field_70161_v,
                        x,
                        y,
                        z,
                        0.0,
                        0.0,
                        0.0,
                        0.0F,
                        id,
                        4,
                        4,
                        64,
                        forg,
                        rot,
                        false,
                        0.0F,
                        1,
                        "",
                        30,
                        1,
                        0.12F * pl_s,
                        scalem,
                        scales,
                        0,
                        red,
                        green,
                        blue,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        2,
                        0.0F,
                        0.0F,
                        0.75F,
                        0.78F,
                        0.18F,
                        false,
                        -1,
                        true,
                        this.user
                     );
                     ((EntityCusPar)entity).setdata39(rota);
                     entity.field_70170_p.func_72838_d(entity);
                     h2 = (JRMCoreH.techCol2[this.color] >> 16 & 0xFF) / 255.0F;
                     h3 = (JRMCoreH.techCol2[this.color] >> 8 & 0xFF) / 255.0F;
                     h4 = (JRMCoreH.techCol2[this.color] & 0xFF) / 255.0F;
                     red = h1 * h2;
                     green = h1 * h3;
                     blue = h1 * h4;
                     Entity entity2 = new EntityCusPar(
                        "jinryuudragonbc:bens_particles_attack.png",
                        this.field_70170_p,
                        2.0F,
                        2.0F,
                        this.user.field_70165_t,
                        this.user.field_70163_u + (this.user instanceof EntityPlayerSP ? -1.6F : 0.0F),
                        this.user.field_70161_v,
                        x,
                        y,
                        z,
                        0.0,
                        0.0,
                        0.0,
                        0.0F,
                        id,
                        4,
                        4,
                        64,
                        true,
                        rot,
                        false,
                        0.0F,
                        1,
                        "",
                        30,
                        1,
                        0.096F * pl_s,
                        scalem * 0.8F,
                        scales * 0.8F,
                        0,
                        red,
                        green,
                        blue,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        2,
                        0.0F,
                        0.0F,
                        0.75F,
                        0.78F,
                        0.18F,
                        false,
                        -1,
                        true,
                        this.user
                     );
                     ((EntityCusPar)entity2).setdata39(rota);
                     entity2.field_70170_p.func_72838_d(entity2);
                  }
               }
            }
         }
      }
   }

   public void generateParticles(EntityEng entityBlast, Entity entity, int color, int color2) {
      if (entityBlast != null && entity != null && entityBlast.field_70170_p.field_72995_K) {
         EntityPlayer user = entityBlast.user;
         int ticksExisted = entityBlast.field_70173_aa;
         float scale = ticksExisted * (entityBlast.getSize() / 100.0F);
         if (scale > entityBlast.getSize()) {
            scale = entityBlast.getSize();
         }

         for (int i = 0; i < 1.0F + scale; i++) {
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
               float alpha = scale / 2.0F < 1.0F ? scale / 2.0F : 1.0F;
               float out = 1.5F * scale;
               float in = 1.5F;
               float life = 0.4F * entity.field_70131_O;
               float extra_scale = 0.2F;
               int dea = 30;
               float target_fullsize_one1 = 0.32F;
               float targetsizeMin = entity.field_70131_O * (8.0F / target_fullsize_one1) * 0.01F;
               float target_fullsize_one2 = 0.32F;
               float targetsizeMax = entity.field_70131_O * (26.0F / target_fullsize_one2) * 0.01F;
               double x = Math.random() * out - out / 2.0F;
               double y = Math.random() * out - out / 2.0F;
               double z = Math.random() * out - out / 2.0F;
               double x2 = 0.0;
               double y2 = 0.0;
               double z2 = 0.0;
               x2 = entity.field_70165_t;
               y2 = entity.field_70163_u;
               z2 = entity.field_70161_v;
               y2 += entity instanceof EntityPlayerSP ? -1.6F : 0.0F;
               double motionX = 0.0;
               double motionZ = 0.0;
               double motionY = 0.0;
               float height = user.field_70131_O;
               float height2 = height * 0.8F;
               float width = user.field_70130_N;
               float width2 = width * 0.8F;
               if (entityBlast.isWave()) {
                  Vec3 vec3 = entity.func_70040_Z();
                  double kiX = 0.0;
                  double kiY = 0.0;
                  kiX = 1.0;
                  kiY = -1.0;
                  double d8 = entity.field_70130_N + kiX;
                  double d9 = entity.field_70131_O;
                  x2 += vec3.field_72450_a * d8;
                  y2 += vec3.field_72448_b * d8 + height2 * 0.92F;
                  z2 += vec3.field_72449_c * d8;
               } else if (entityBlast.isBlast() || entityBlast.isSpiral() || entityBlast.isLaser()) {
                  Vec3 vec3 = entity.func_70040_Z();
                  double kiX = 0.0;
                  double kiY = 0.0;
                  kiX = 1.0;
                  kiY = -1.0;
                  double d8 = entity.field_70130_N + kiX;
                  double d9 = entity.field_70131_O;
                  x2 += vec3.field_72450_a * d8;
                  y2 += vec3.field_72448_b * d8 + height2 * 0.92F;
                  z2 += vec3.field_72449_c * d8;
               } else if (!entityBlast.isDisk()) {
                  if (entityBlast.isLargeBlast()) {
                     double kiX = 0.0;
                     double kiY = 0.0;
                     kiX = 1.0;
                     kiY = -1.0;
                     y2 += entity.field_70131_O + 1.0F + scale / 2.0F;
                  } else if (!entityBlast.isShield() && entityBlast.isExplosion()) {
                  }
               }

               x2 += x;
               y2 += y;
               z2 += z;
               motionX = x * 0.02;
               motionY = y * 0.02;
               motionZ = z * 0.02;
               float scaleStart = ((float)(Math.random() * 0.02F) + 0.02F) * life * 0.2F;
               float scaleEnd = ((float)(Math.random() * 0.01F) + 0.02F) * life * 0.2F;
               float scaleSpeed = 0.2F * life * 0.2F;
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
                  0.6F * alpha,
                  0.0F * alpha,
                  0.9F * alpha,
                  0.95F * alpha,
                  0.06F * alpha,
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
                  0.6F * alpha,
                  0.0F * alpha,
                  0.9F * alpha,
                  0.95F * alpha,
                  0.06F * alpha,
                  false,
                  -1,
                  true,
                  null
               );
               entity.field_70170_p.func_72838_d(particle2);
               if (!entityBlast.isWave()
                  && !entityBlast.isBlast()
                  && !entityBlast.isSpiral()
                  && !entityBlast.isLaser()
                  && !entityBlast.isDisk()
                  && !entityBlast.isLargeBlast()
                  && !entityBlast.isShield()
                  && entityBlast.isExplosion()) {
               }
            }
         }
      }
   }

   public float rad(float angle) {
      return angle * (float) Math.PI / 180.0F;
   }

   public boolean func_70112_a(double par1) {
      if (JGConfigClientSettings.renderdistanceMultiplierKiAttackCharge == 10000) {
         return true;
      }

      double d1 = this.field_70121_D.func_72320_b();
      d1 *= 64.0 * this.field_70155_l;
      return par1 < d1 * d1 * (JGConfigClientSettings.renderdistanceMultiplierKiAttackCharge / 100.0);
   }
}
