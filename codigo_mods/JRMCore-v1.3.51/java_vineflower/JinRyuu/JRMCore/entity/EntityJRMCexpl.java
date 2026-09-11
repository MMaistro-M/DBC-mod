package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.mod_JRMCore;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityJRMCexpl extends Entity {
   public int randomSoundDelay = 0;
   public int tex = 1;
   public float explsiz;
   public byte type;
   int Age;
   int MaxAge = 40;

   public EntityJRMCexpl(World par1World, byte type) {
      super(par1World);
      this.type = type;
   }

   public void func_70071_h_() {
      if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_GR4) {
         for (int k = 0; k < JGConfigClientSettings.get_da1(); k++) {
            if (this.type == 10) {
               if (this.field_70173_aa % 2 == 0 && this.field_70173_aa < 10) {
                  Entity pl = this;
                  float area = this.explsiz;

                  for (int i = 0; i < (int)area + 5; i++) {
                     float a = 1.0F;
                     float h1 = 1.0F;
                     float scale = 1.0F + area;
                     scale *= 0.01F;
                     double x = 0.0;
                     double y = 0.0;
                     double z = 0.0;
                     float size1 = 0.3F;
                     float motx = ((float)(Math.random() * size1) - size1 / 2.0F) * (area / 5.0F);
                     float moty = (float)(Math.random() * size1 / 2.0) * (area / 5.0F);
                     float motz = ((float)(Math.random() * size1) - size1 / 2.0F) * (area / 5.0F);
                     motx *= 0.1F;
                     moty *= 0.8F;
                     motz *= 0.1F;
                     float size = area;
                     x = (float)(Math.random() * size) - size / 2.0F;
                     y = (float)(Math.random() * size) - size / 2.0F;
                     z = (float)(Math.random() * size) - size / 2.0F;
                     Entity entity7 = new EntityCusPar(
                        "jinryuumodscore:bens_particles.png",
                        this.field_70170_p,
                        0.4F,
                        0.4F,
                        pl.field_70165_t,
                        pl.field_70163_u,
                        pl.field_70161_v,
                        x,
                        y,
                        z,
                        motx,
                        moty,
                        motz,
                        0.0F,
                        10,
                        12,
                        4,
                        32,
                        true,
                        (float)(Math.random() * 0.3F) + 0.3F,
                        false,
                        0.0F,
                        1,
                        "",
                        50,
                        1,
                        ((float)(Math.random() * 0.01F) + 0.02F) * scale,
                        ((float)(Math.random() * 0.02F) + 0.09F) * scale,
                        ((float)(Math.random() * 0.002F) + 0.003F) * scale,
                        1,
                        0.9647059F,
                        0.38431373F,
                        0.98039216F,
                        -0.01F,
                        -0.001F,
                        -0.001F,
                        0.8392157F,
                        0.32941177F,
                        0.9137255F,
                        3,
                        1.0F,
                        0.0F,
                        0.0F,
                        0.0F,
                        -0.05F,
                        false,
                        -1,
                        false,
                        null
                     );
                     ((EntityCusPar)entity7).setdata39((int)(Math.random() * 360.0));
                     this.field_70170_p.func_72838_d(entity7);
                  }
               }
            } else if (this.field_70173_aa != 1 || this.type != 3 && this.type != 4) {
               if (this.field_70173_aa == 1) {
                  if (this.type != 5) {
                     if (this.type != 0) {
                        if (JGConfigClientSettings.CLIENT_DA6) {
                           if (this.type != 3 && this.type != 4) {
                              float a = 1.0F;
                              float h1 = 1.0F;
                              float scale = 1.0F + this.explsiz;
                              scale *= 1.2F;
                              Entity pl = this;
                              double x = 0.0;
                              double y = 0.0;
                              double z = 0.0;
                              Entity entity7 = new EntityCusPar(
                                 "jinryuumodscore:bens_particles.png",
                                 this.field_70170_p,
                                 0.4F,
                                 0.4F,
                                 pl.field_70165_t,
                                 pl.field_70163_u,
                                 pl.field_70161_v,
                                 x,
                                 y,
                                 z,
                                 0.0,
                                 0.0,
                                 0.0,
                                 0.0F,
                                 (int)(Math.random() * 2.0) + 12,
                                 12,
                                 4,
                                 32,
                                 true,
                                 (float)(Math.random() * 0.3F) + 0.3F,
                                 false,
                                 0.0F,
                                 1,
                                 "",
                                 30,
                                 1,
                                 ((float)(Math.random() * 0.02F) + 0.02F) * scale,
                                 ((float)(Math.random() * 0.04F) + 0.09F) * scale,
                                 ((float)(Math.random() * 0.003F) + 0.005F) * scale,
                                 0,
                                 116.0F,
                                 187.0F,
                                 255.0F,
                                 0.0F,
                                 0.0F,
                                 0.0F,
                                 0.0F,
                                 0.0F,
                                 0.0F,
                                 3,
                                 1.0F,
                                 0.0F,
                                 0.0F,
                                 0.0F,
                                 -0.05F,
                                 false,
                                 -1,
                                 false,
                                 null
                              );
                              ((EntityCusPar)entity7).setdata39((int)(Math.random() * 360.0));
                              this.field_70170_p.func_72838_d(entity7);
                              scale *= 0.65F;
                              int num = (int)(Math.random() * 4.0) + 1;

                              for (int i = 0; i < num; i++) {
                                 Entity entity7x = new EntityCusPar(
                                    "jinryuumodscore:bens_particles.png",
                                    this.field_70170_p,
                                    0.4F,
                                    0.4F,
                                    pl.field_70165_t,
                                    pl.field_70163_u,
                                    pl.field_70161_v,
                                    x,
                                    y,
                                    z,
                                    0.0,
                                    0.0,
                                    0.0,
                                    0.0F,
                                    (int)(Math.random() * 2.0) + 6,
                                    4,
                                    4,
                                    64,
                                    true,
                                    (float)(Math.random() * 0.2F) + 0.2F,
                                    false,
                                    0.0F,
                                    1,
                                    "",
                                    15,
                                    1,
                                    ((float)(Math.random() * 0.02F) + 0.02F) * scale,
                                    ((float)(Math.random() * 0.04F) + 0.09F) * scale,
                                    ((float)(Math.random() * 0.003F) + 0.005F) * scale,
                                    0,
                                    116.0F,
                                    187.0F,
                                    255.0F,
                                    0.0F,
                                    0.0F,
                                    0.0F,
                                    0.0F,
                                    0.0F,
                                    0.0F,
                                    3,
                                    1.0F,
                                    0.0F,
                                    0.0F,
                                    0.0F,
                                    -0.05F,
                                    false,
                                    -1,
                                    false,
                                    null
                                 );
                                 ((EntityCusPar)entity7x).setdata39((int)(Math.random() * 360.0));
                                 this.field_70170_p.func_72838_d(entity7x);
                              }
                           }
                        } else {
                           if (this.type == 5) {
                              for (int i = 0; i < 5; i++) {
                                 if (this.field_70173_aa % 2 == 0) {
                                    this.func_exa();
                                    this.func_ex3();
                                 }
                              }
                           }

                           for (int i = 0; i < 5; i++) {
                              if (this.type == 1) {
                                 if (this.explsiz > 0.5F) {
                                    this.func_exa();
                                 }
                              } else if (this.type == 2) {
                                 for (int j = 0; j < 2; j++) {
                                    this.func_ex3();
                                 }
                              }
                           }
                        }
                     }
                  } else if (this.field_70173_aa < 15 && this.field_70173_aa % 2 == 0) {
                     this.func_exa();
                  }
               }

               if (this.type == 5) {
                  if (this.field_70173_aa < 15 && this.field_70173_aa % 2 == 0) {
                     this.func_exa();
                  }
               } else {
                  if (JGConfigClientSettings.CLIENT_DA6) {
                     if (this.type != 3 && this.type != 4 && this.type != 0 && this.field_70173_aa < 10) {
                        Entity pl = this;

                        for (int i = 0; i < (int)this.explsiz + 5; i++) {
                           float a = 1.0F;
                           float h1 = 1.0F;
                           float scale = 1.0F + this.explsiz;
                           scale *= 0.4F;
                           double x = 0.0;
                           double y = 0.0;
                           double z = 0.0;
                           float size1 = 0.5F;
                           float motx = ((float)(Math.random() * size1) - size1 / 2.0F) * (this.explsiz / 5.0F);
                           float moty = ((float)(Math.random() * size1) - size1 / 2.0F) * (this.explsiz / 5.0F);
                           float motz = ((float)(Math.random() * size1) - size1 / 2.0F) * (this.explsiz / 5.0F);
                           Entity entity7 = new EntityCusPar(
                              "jinryuumodscore:bens_particles.png",
                              this.field_70170_p,
                              0.4F,
                              0.4F,
                              pl.field_70165_t,
                              pl.field_70163_u,
                              pl.field_70161_v,
                              x,
                              y,
                              z,
                              motx,
                              moty,
                              motz,
                              0.0F,
                              10,
                              12,
                              4,
                              32,
                              true,
                              (float)(Math.random() * 0.3F) + 0.3F,
                              false,
                              0.0F,
                              1,
                              "",
                              50,
                              1,
                              ((float)(Math.random() * 0.01F) + 0.02F) * scale,
                              ((float)(Math.random() * 0.02F) + 0.09F) * scale,
                              ((float)(Math.random() * 0.002F) + 0.003F) * scale,
                              1,
                              116.0F,
                              187.0F,
                              255.0F,
                              -0.02F,
                              -0.02F,
                              -0.03F,
                              56.0F,
                              67.0F,
                              100.0F,
                              3,
                              1.0F,
                              0.0F,
                              0.0F,
                              0.0F,
                              -0.05F,
                              false,
                              -1,
                              false,
                              null
                           );
                           ((EntityCusPar)entity7).setdata39((int)(Math.random() * 360.0));
                           this.field_70170_p.func_72838_d(entity7);
                        }

                        for (int i = 0; i < (int)this.explsiz + 5; i++) {
                           float a = 1.0F;
                           float h1 = 1.0F;
                           float scale = 1.0F + this.explsiz;
                           scale *= 0.25F;
                           double x = 0.0;
                           double y = 0.0;
                           double z = 0.0;
                           float size1 = 0.3F;
                           float motx = ((float)(Math.random() * size1) - size1 / 2.0F) * (this.explsiz / 5.0F);
                           float moty = ((float)(Math.random() * size1) - size1 / 2.0F) * (this.explsiz / 5.0F);
                           float motz = ((float)(Math.random() * size1) - size1 / 2.0F) * (this.explsiz / 5.0F);
                           Entity entity7 = new EntityCusPar(
                              "jinryuumodscore:bens_particles.png",
                              this.field_70170_p,
                              0.4F,
                              0.4F,
                              pl.field_70165_t,
                              pl.field_70163_u,
                              pl.field_70161_v,
                              x,
                              y,
                              z,
                              motx,
                              moty,
                              motz,
                              0.0F,
                              10,
                              12,
                              4,
                              32,
                              true,
                              (float)(Math.random() * 0.3F) + 0.3F,
                              false,
                              0.0F,
                              1,
                              "",
                              50,
                              1,
                              ((float)(Math.random() * 0.01F) + 0.02F) * scale,
                              ((float)(Math.random() * 0.02F) + 0.09F) * scale,
                              ((float)(Math.random() * 0.002F) + 0.003F) * scale,
                              1,
                              1.0F,
                              1.0F,
                              1.0F,
                              -0.01F,
                              -0.005F,
                              -0.005F,
                              216.0F,
                              244.0F,
                              245.0F,
                              3,
                              1.0F,
                              0.0F,
                              0.0F,
                              0.0F,
                              -0.05F,
                              false,
                              -1,
                              false,
                              null
                           );
                           ((EntityCusPar)entity7).setdata39((int)(Math.random() * 360.0));
                           this.field_70170_p.func_72838_d(entity7);
                        }
                     }
                  } else if (this.type == 1 && this.explsiz > 0.5F && this.MaxAge * 0.8F >= this.Age) {
                     this.func_exa();
                  }

                  if (this.type == 0) {
                     this.func_ex1();
                     mod_JRMCore.proxy
                        .func_gcp(
                           this,
                           EntityCusPars.PART1,
                           Math.random() * 4.0 - 2.0,
                           0.0 + Math.random() * (this.field_70131_O * 0.25F) + this.field_70131_O / 2.0F - this.field_70131_O * 0.25F,
                           Math.random() * 4.0 - 2.0,
                           Math.random() * 0.05 - 0.025,
                           Math.random() * 0.1 + 0.05,
                           Math.random() * 0.05 - 0.025,
                           0.5F,
                           0.5F,
                           0.5F
                        );
                  }
               }
            } else {
               if (this.type == 3) {
                  this.field_70170_p
                     .func_72838_d(new EntityEnergyAttJ4(this.field_70170_p, (byte)0, this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v));
               }

               if (this.type == 4) {
                  this.field_70170_p
                     .func_72838_d(new EntityEnergyAttJ4(this.field_70170_p, (byte)1, this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v));
               }
            }
         }
      }

      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      if (this.Age++ >= this.MaxAge) {
         this.func_70106_y();
      }

      this.field_70181_x += 0.0;
      this.func_70091_d(0.0, 0.0, 0.0);
      if (this.field_70163_u == this.field_70167_r) {
         this.field_70159_w *= 1.0;
         this.field_70179_y *= 1.0;
      }

      this.field_70159_w *= 0.0;
      this.field_70181_x *= 0.0;
      this.field_70179_y *= 0.0;
      if (this.field_70122_E) {
         this.field_70159_w *= 0.0;
         this.field_70179_y *= 0.0;
      }
   }

   private void func_exa() {
      this.func_ex1();
      this.func_ex2();
      this.func_ex3();
   }

   private void func_ex1() {
      mod_JRMCore.proxy
         .func_gcp(
            this,
            EntityCusPars.PART2,
            Math.random() * 6.0 - 3.0,
            0.0 + this.field_70131_O / 2.0F,
            Math.random() * 6.0 - 3.0,
            Math.random() * 0.2 - 0.1,
            Math.random() * 0.2 + 0.1,
            Math.random() * 0.2 - 0.1,
            1.0F,
            1.0F,
            1.0F
         );
   }

   private void func_ex2() {
      mod_JRMCore.proxy
         .func_gcp(
            this,
            EntityCusPars.PART3,
            Math.random() * 6.0 - 3.0,
            0.0 + this.field_70131_O / 2.0F,
            Math.random() * 6.0 - 3.0,
            Math.random() * 0.1 - 0.075,
            Math.random() * 0.2 + 0.1,
            Math.random() * 0.15 - 0.075,
            0.5F,
            0.5F,
            0.5F
         );
   }

   private void func_ex3() {
      mod_JRMCore.proxy
         .func_gcp(
            this,
            EntityCusPars.PART4,
            Math.random() * 4.0 - 2.0,
            0.0 + this.field_70131_O / 2.0F,
            Math.random() * 4.0 - 2.0,
            Math.random() * 1.2 - 0.6,
            Math.random() * 0.2 + 0.1,
            Math.random() * 1.2 - 0.6,
            0.05F,
            0.01F,
            0.1F
         );
   }

   @SideOnly(Side.CLIENT)
   public String getTexture() {
      return "";
   }

   public boolean getCanSpawnHere() {
      return !this.field_70170_p.func_72855_b(this.field_70121_D);
   }

   public void onLivingUpdate() {
   }

   protected void func_70088_a() {
   }

   protected void func_70037_a(NBTTagCompound var1) {
   }

   protected void func_70014_b(NBTTagCompound var1) {
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
}
