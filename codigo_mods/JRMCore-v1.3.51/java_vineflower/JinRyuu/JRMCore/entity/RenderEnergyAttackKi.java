package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.JGRenderHelper;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderEnergyAttackKi extends RenderEnergyAttack<EntityEnergyAtt> {
   private ModelEnergy ener = new ModelEnergy();

   public void renderEnergy(EntityEnergyAtt entity, double par2, double par4, double par6, float par8, float par9) {
      this.transparent = false;
      this.updateEffect(entity);
      byte type = entity.getType();
      int shrink = entity.getShrink();
      float rotation = this.handleRotationFloat(entity, par9);
      int color = entity.destroyer
         ? (JGConfigClientSettings.CLIENT_Ki_3d[type] ? JRMCoreH.techCol[5] : JRMCoreH.techCol4[entity.getCol()])
         : JRMCoreH.techCol[entity.getCol()];
      int color2 = entity.destroyer
         ? (JGConfigClientSettings.CLIENT_Ki_3d[type] ? JRMCoreH.techCol4[entity.getCol()] : JRMCoreH.techCol[entity.getCol()])
         : (entity.getCol2() == -1 ? JRMCoreH.techCol2[entity.getCol()] : JRMCoreH.techCol3[entity.getCol2()]);
      double sx = entity.strtX();
      double sy = entity.strtY();
      double sz = entity.strtZ();
      double x = sx - entity.field_70165_t;
      double y = sy - entity.field_70163_u;
      double z = sz - entity.field_70161_v;
      if (shrink > 0) {
         this.updateEffect2(entity);
      } else {
         entity.dist = MathHelper.func_76133_a(x * x + y * y + z * z);
         entity.finalDist = entity.dist;
      }

      float scale = entity.getSize() * (JGConfigClientSettings.CLIENT_Ki_Scale / 10.0F);
      this.mode = 0;
      this.setVisuals(entity, type, (short)0, color, color2, scale, this.mode);
      if (JGConfigClientSettings.CLIENT_Ki_3d[type]) {
         this.render_3d(entity, par2, par4, par6, par8, par9, scale, sx, sy, sz, color, color2, rotation);
      } else {
         this.render_2d(entity, par2, par4, par6, par8, par9, scale, sx, sy, sz, color, color2, rotation);
      }
   }

   public void updateEffect(EntityEnergyAtt entity) {
      if (!JRMCoreClient.mc.func_147113_T()) {
         if (!entity.added) {
            entity.animation_id++;
            entity.animation_start = System.nanoTime() / 10000000L;
            entity.added = true;
            if (entity.render_scale < entity.render_scale_max) {
               entity.render_scale = entity.render_scale + entity.render_scale_max / 10.0F;
            }

            if (entity.render_scale > entity.render_scale_max) {
               entity.render_scale = entity.render_scale_max;
            }

            if (this.random_texture && entity.animation_random != null) {
               int size = entity.animation_random.size();
               entity.animation_random.clear();

               for (int i = 0; i < size; i++) {
                  entity.animation_random.add((int)(Math.random() * entity.animation_random_Max));
               }
            }
         }

         if (entity.animation_id >= entity.animation_id_Max) {
            entity.animation_id = 0;
         }

         if (System.nanoTime() / 10000000L - entity.animation_start > entity.animation_speed) {
            entity.added = false;
         }
      }
   }

   public void updateEffect2(EntityEnergyAtt entity) {
      if (!JRMCoreClient.mc.func_147113_T()) {
         if (!entity.added2) {
            entity.animation_start2 = System.nanoTime() / 10000000L;
            entity.added2 = true;
            if (!entity.isShield() && !entity.isExplosion()) {
               float shrinking = (float)entity.finalDist / 500.0F;
               double d = entity.dist - entity.getSpe() * shrinking;
               entity.dist = d < 0.0 ? 0.0 : d;
               float egy = (float)entity.finalDist / 100.0F;
               float current = (float)entity.dist / egy / 100.0F;
               entity.waveScale = current;
            } else {
               entity.waveScale = 0.3F;
            }
         }

         if (System.nanoTime() / 10000000L - entity.animation_start2 > 1L) {
            entity.added2 = false;
         }
      }
   }

   public void setVisuals(EntityEnergyAtt entity, byte type, short effect, int color, int color2, float scale, byte mode) {
      float scale_all = scale;
      entity.animation_id_Max = 1;
      entity.animation_random_Max = 1;
      entity.animation_speed = 7;
      this.rendermode_tail = 0;
      this.render_head = true;
      this.render_head_connect = true;
      this.render_middle = true;
      this.render_noise = true;
      this.render_tail = true;
      this.render_tail_connect = true;
      this.rotate_head = true;
      this.rotate_head_connect = true;
      this.rotate_middle = true;
      this.rotate_noise = true;
      this.rotate_tail = true;
      this.rotate_tail_connect = true;
      this.scaling_head = true;
      this.scaling_tail = true;
      this.scale_head_more = 0.0F;
      this.scale_tail_more = 0.0F;
      this.head_follow = false;
      this.tail_follow = false;
      this.random_texture = false;
      this.rotationSpeed = 40.0F;
      this.modifierTranslation = entity.dist / 2.0 + scale_all * (1.0F / scale_all);
      this.detail = 4;
      this.brightness = 200;
      this.alpha = 1.0F * (JGConfigClientSettings.CLIENT_Ki_Visibility / 10.0F);
      if (this.mode == 0) {
         this.setColors(color, this.alpha);
         this.setColors2(color2);
         this.texture = (entity.destroyer ? "dbc_god/" : "dbc/") + this.texture_type[type] + "/";
         if (entity.isWave()) {
            this.render_middle = false;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = true;
            this.tail_follow = true;
            this.scale_tail_more = 0.1F;
         } else if (entity.isBlast()) {
            scale_all *= 0.5F;
            this.render_head = false;
            this.render_head_connect = false;
            this.render_middle = false;
            this.render_noise = false;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isDisk()) {
            this.rendermode_tail = 1;
            this.render_head = false;
            this.render_head_connect = false;
            this.render_middle = false;
            this.render_noise = false;
            this.render_tail = true;
            this.render_tail_connect = false;
            this.scaling_head = false;
            this.scaling_tail = false;
         } else if (entity.isLaser()) {
            scale_all *= 0.5F;
            this.render_middle = false;
            this.head_follow = true;
            this.tail_follow = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.scale_tail_more = 0.15F;
         } else if (entity.isLargeBlast()) {
            this.render_head = false;
            this.render_head_connect = false;
            this.render_middle = false;
            this.render_noise = false;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isSpiral()) {
            this.head_follow = true;
            this.tail_follow = true;
            scale_all *= 0.5F;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.scale_tail_more = 0.15F;
         } else if (entity.isBarrage()) {
            scale_all *= 0.25F;
            this.render_head = false;
            this.render_head_connect = false;
            this.render_middle = false;
            this.render_noise = false;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isShield()) {
            this.render_head = false;
            this.render_head_connect = false;
            this.render_middle = false;
            this.render_noise = false;
            this.render_tail = true;
            this.render_tail_connect = false;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
            this.transparent = true;
         } else if (entity.isExplosion()) {
            this.render_head = false;
            this.render_head_connect = false;
            this.render_middle = false;
            this.render_noise = false;
            this.render_tail = true;
            this.render_tail_connect = false;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
            this.transparent = true;
         }
      }

      this.scale_head = 1.0F * scale_all * (1.0F + this.scale_head_more) * entity.waveScale;
      this.scale_head_connect = 1.0F * scale_all * entity.waveScale;
      this.scale_middle = 1.0F * scale_all * entity.waveScale;
      this.scale_noise = 1.0F * scale_all * entity.waveScale;
      this.scale_tail = 1.0F * scale_all * (1.0F + scale_all * this.scale_tail_more);
      this.scale_tail_connect = 1.0F * scale_all * entity.waveScale;
   }

   public void render_2d(
      EntityEnergyAtt entity,
      double par2,
      double par4,
      double par6,
      float par8,
      float par9,
      float scale,
      double sx,
      double sy,
      double sz,
      int color,
      int color2,
      float rotation
   ) {
      Tessellator tessellator = Tessellator.field_78398_a;
      long time = entity.field_70170_p.func_82737_E();
      float particleScale = 1.0F;
      float middle_start = 0.4F;
      float middle_max = 10.0F;
      this.glStart();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDepthMask(true);
      GL11.glDisable(2896);
      if (this.transparent) {
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glAlphaFunc(516, 0.003921569F);
         GL11.glDepthMask(false);
      }

      float X = 0.0F;
      float Y = 0.0F;
      float Z = 0.0F;
      float position = entity.waveScale;
      position *= position;
      X = (float)(-(entity.field_70165_t - sx) * 0.5) * position;
      Y = (float)(-(entity.field_70163_u - sy) * 0.5) * position;
      Z = (float)(-(entity.field_70161_v - sz) * 0.5) * position;
      if (X == 0.0F) {
         X = 0.0F;
      }

      if (Y == 0.0F) {
         Y = 0.0F;
      }

      if (Z == 0.0F) {
         Z = 0.0F;
      }

      this.glRotate(entity, par2 + X, par4 + Y, par6 + Z, par9);
      GL11.glRotated(90.0, 1.0, 0.0, 0.0);
      float particleMiddleWidth = 1.0F;
      float wave_start_size = 1.0F;
      float wave_end_size = 1.0F;
      int texture_id = 0;
      if (this.render_middle) {
         for (int i = 0; i < this.detail; i++) {
            float maxSize = 2.32F * particleScale;
            float correction = 0.86F;
            float scl = (float)entity.dist;
            int segments = (int)(scl / (maxSize * 0.86F));
            float div = segments / 10.0F;
            float size_divider = this.scaling_tail ? (div < 5.0F ? 5.0F : div) : 5.0F;
            float max_size = 10.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(0.001F, 0.0F, 0.001F);
            this.apply_detail_rotation(i);
            float Translate_middle = -1.0F;
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            if (this.rotate_middle) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
            }

            for (int k = 0; k < segments + 1; k++) {
               float size = 1.0F;
               if (this.scaling_tail) {
                  size = k / size_divider + 0.4F;
                  if (size > 10.0F) {
                     size = 10.0F;
                  }

                  particleMiddleWidth = size;
                  if (particleMiddleWidth > wave_end_size) {
                     wave_end_size = particleMiddleWidth;
                  }

                  if (k == 0) {
                     wave_start_size = size;
                  }
               }

               if (this.random_texture && entity.animation_random != null) {
                  if (entity.animation_random.size() > k) {
                     texture_id = entity.animation_random.get(k);
                  } else {
                     entity.animation_random.add((int)(Math.random() * entity.animation_random_Max));
                  }
               } else {
                  texture_id = entity.animation_id % entity.animation_id_Max;
               }

               FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("middle", texture_id));
               double length = entity.dist;
               if (length > maxSize) {
                  length = maxSize;
               }

               double minus = -scl / 2.0F + (segments - k) * (maxSize * 0.86F);
               float height = 1.0F;
               float height2 = 0.0F;
               if (k == 0) {
                  float scale0 = (float)(segments - minus);
                  if (scale0 < 0.0F) {
                     scale0 = 0.0F;
                  }

                  height = scale0;
                  if (height > 1.0F) {
                     height = 1.0F;
                  }

                  height2 = height;
                  minus += -(1.0F - height2);
               }

               JGRenderHelper.draw_tessellator(
                  tessellator,
                  this.brightness,
                  particleScale * particleMiddleWidth * this.scale_middle,
                  0.0F,
                  particleScale * height,
                  (float)(-minus),
                  this.red2,
                  this.green2,
                  this.blue2,
                  this.alpha
               );
            }

            if (segments > entity.lastSegments) {
               entity.lastSegments = segments;
            }

            if (segments < entity.lastSegments) {
               float size = 1.0F;
               if (this.scaling_tail) {
                  size = entity.lastSegments / size_divider + 0.4F;
                  if (size > 10.0F) {
                     size = 10.0F;
                  }

                  particleMiddleWidth = size;
                  if (particleMiddleWidth > wave_end_size) {
                     wave_end_size = particleMiddleWidth;
                  }
               }
            }

            GL11.glPopMatrix();
         }
      } else if (this.scaling_tail) {
         float maxSize = 2.32F * particleScale;
         float correction = 0.86F;
         float scl = (float)entity.dist;
         int segments = (int)(scl / (maxSize * 0.86F));
         float div = segments / 10.0F;
         float size_divider = this.scaling_tail ? (div < 5.0F ? 5.0F : div) : 5.0F;
         float max_size = 10.0F;
         float size = 0.0F / size_divider + 0.4F;
         if (size > 10.0F) {
            size = 10.0F;
         }

         wave_start_size = size;
         size = segments / size_divider + 0.4F;
         if (size > 10.0F) {
            size = 10.0F;
         }

         particleMiddleWidth = size;
         if (particleMiddleWidth > wave_end_size) {
            wave_end_size = particleMiddleWidth;
         }
      }

      if (this.render_noise) {
         for (int i = 0; i < this.detail; i++) {
            GL11.glPushMatrix();
            if (this.rotate_noise) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
            }

            texture_id = entity.animation_id % entity.animation_id_Max;
            FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("noise", texture_id));
            this.apply_detail_rotation(i);
            JGRenderHelper.draw_tessellator2(
               tessellator,
               this.brightness,
               particleScale * this.scale_noise,
               0.0F,
               (float)(entity.dist / 2.0),
               0.0F,
               this.red,
               this.green,
               this.blue,
               this.alpha
            );
            GL11.glPopMatrix();
         }
      }

      if (this.render_head) {
         if (!this.head_follow) {
            for (int i = 0; i < 2; i++) {
               GL11.glPushMatrix();
               if (this.rotate_head) {
                  GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
               }

               GL11.glTranslated(0.0, -this.modifierTranslation, 0.0);
               texture_id = entity.animation_id % entity.animation_id_Max;
               FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("head", texture_id));
               GL11.glRotated(-90.0, 1.0, 0.0, 0.0);
               GL11.glRotatef(i * 360 / 2, 0.0F, 1.0F, 0.0F);
               JGRenderHelper.draw_tessellator(
                  tessellator,
                  this.brightness,
                  particleScale * (this.scaling_head ? wave_start_size : 1.0F) * this.scale_head,
                  particleScale * (this.scaling_head ? wave_start_size : 1.0F) * this.scale_head,
                  this.red,
                  this.green,
                  this.blue,
                  this.alpha
               );
               GL11.glPopMatrix();
            }
         } else {
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, -this.modifierTranslation, 0.0);
            float rotationX = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9;
            GL11.glRotatef(rotationX, -1.0F, 0.0F, 0.0F);
            float rotationY = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F;
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(rotationY, 0.0F, 0.0F, -1.0F);
            boolean view2 = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
            GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-this.field_76990_c.field_78732_j * (view2 ? -1 : 1), 1.0F, 0.0F, 0.0F);
            texture_id = entity.animation_id % entity.animation_id_Max;
            FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("head", texture_id));
            GL11.glRotated(-90.0, 1.0, 0.0, 0.0);
            if (this.rotate_head) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 0.0F, 1.0F);
            }

            JGRenderHelper.draw_tessellator(
               tessellator,
               this.brightness,
               particleScale * (this.scaling_head ? wave_start_size : 1.0F) * this.scale_head,
               particleScale * (this.scaling_head ? wave_start_size : 1.0F) * this.scale_head,
               this.red,
               this.green,
               this.blue,
               this.alpha
            );
            GL11.glPopMatrix();
         }
      }

      if (this.render_head_connect) {
         for (int i = 0; i < this.detail; i++) {
            GL11.glPushMatrix();
            if (this.rotate_head_connect) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
            }

            GL11.glTranslated(0.0, -this.modifierTranslation, 0.0);
            texture_id = entity.animation_id % entity.animation_id_Max;
            FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("head_connect", texture_id));
            this.apply_detail_rotation(i);
            JGRenderHelper.draw_tessellator(
               tessellator,
               this.brightness,
               particleScale * (this.scaling_head ? wave_start_size : 1.0F) * this.scale_head_connect,
               particleScale,
               this.red,
               this.green,
               this.blue,
               this.alpha
            );
            GL11.glPopMatrix();
         }
      }

      if (this.render_tail) {
         if (this.rendermode_tail == 0 && !this.tail_follow || this.rendermode_tail == 2 && !this.tail_follow) {
            for (int i = 0; i < 2; i++) {
               GL11.glPushMatrix();
               if (this.rendermode_tail == 2) {
                  GL11.glTranslated(0.0, -1.0, 0.0);
                  GL11.glTranslated(0.0, 0.0, 0.25);
               }

               if (this.rotate_tail) {
                  GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
               }

               GL11.glTranslated(0.0, this.modifierTranslation, 0.0);
               texture_id = entity.animation_id % entity.animation_id_Max;
               FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("tail", texture_id));
               GL11.glRotated(-90.0, 1.0, 0.0, 0.0);
               GL11.glRotatef(i * 360 / 2, 0.0F, 1.0F, 0.0F);
               JGRenderHelper.draw_tessellator(
                  tessellator,
                  this.brightness,
                  particleScale * wave_end_size * this.scale_tail,
                  particleScale * wave_end_size * this.scale_tail,
                  this.red,
                  this.green,
                  this.blue,
                  this.alpha
               );
               GL11.glPopMatrix();
            }
         } else if ((this.rendermode_tail != 0 || !this.tail_follow) && (this.rendermode_tail != 2 || !this.tail_follow)) {
            if (this.rendermode_tail == 1) {
               for (int j = 0; j < scale * 5.0F; j++) {
                  for (int i = 0; i < 2; i++) {
                     GL11.glPushMatrix();
                     GL11.glTranslated(0.0, -1.0, 0.0);
                     GL11.glTranslated(0.0, 0.0, 0.25);
                     GL11.glTranslated(0.0, 0.0, j * 0.01);
                     GL11.glTranslated(0.0, this.modifierTranslation, 0.0);
                     if (this.rotate_tail) {
                        GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 0.0F, 1.0F);
                     }

                     texture_id = entity.animation_id % entity.animation_id_Max;
                     FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("tail", texture_id));
                     GL11.glRotatef(i * 360 / 2, 0.0F, 1.0F, 0.0F);
                     JGRenderHelper.draw_tessellator(
                        tessellator,
                        this.brightness,
                        particleScale * wave_end_size * this.scale_tail,
                        particleScale * wave_end_size * this.scale_tail,
                        this.red,
                        this.green,
                        this.blue,
                        this.alpha
                     );
                     GL11.glPopMatrix();
                  }
               }
            }
         } else {
            GL11.glPushMatrix();
            GL11.glTranslated(0.0, this.modifierTranslation, 0.0);
            if (this.rendermode_tail == 2) {
               GL11.glTranslated(0.0, -1.0, 0.0);
               GL11.glTranslated(0.0, 0.0, 0.25);
            }

            float rotationX = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9;
            GL11.glRotatef(rotationX, -1.0F, 0.0F, 0.0F);
            float rotationY = entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F;
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(rotationY, 0.0F, 0.0F, -1.0F);
            boolean view2 = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
            GL11.glRotatef(-this.field_76990_c.field_78735_i, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-this.field_76990_c.field_78732_j * (view2 ? -1 : 1), 1.0F, 0.0F, 0.0F);
            texture_id = entity.animation_id % entity.animation_id_Max;
            FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("tail", texture_id));
            GL11.glRotated(-90.0, 1.0, 0.0, 0.0);
            if (this.rotate_tail) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 0.0F, 1.0F);
            }

            JGRenderHelper.draw_tessellator(
               tessellator,
               this.brightness,
               particleScale * wave_end_size * this.scale_tail,
               particleScale * wave_end_size * this.scale_tail,
               this.red,
               this.green,
               this.blue,
               this.alpha
            );
            GL11.glPopMatrix();
         }
      }

      if (this.render_tail_connect) {
         for (int i = 0; i < this.detail; i++) {
            GL11.glPushMatrix();
            if (this.rendermode_tail == 2) {
               GL11.glTranslated(0.0, -1.0, 0.0);
               GL11.glTranslated(0.0, 0.0, 0.25);
            }

            if (this.rotate_tail_connect) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
            }

            GL11.glTranslated(0.0, this.modifierTranslation, 0.0);
            texture_id = entity.animation_id % entity.animation_id_Max;
            FMLClientHandler.instance().getClient().field_71446_o.func_110577_a(this.set_resource("tail_connect", texture_id));
            this.apply_detail_rotation(i);
            JGRenderHelper.draw_tessellator(
               tessellator,
               this.brightness,
               particleScale * wave_end_size * this.scale_tail_connect,
               particleScale * (this.rendermode_tail == 2 ? this.scale_tail_connect : 1.0F),
               this.red,
               this.green,
               this.blue,
               this.alpha
            );
            GL11.glPopMatrix();
         }
      }

      if (this.transparent) {
         GL11.glDisable(3042);
         GL11.glDepthMask(true);
      }

      GL11.glEnable(2896);
      this.glEnd();
   }

   public void render_3d(
      EntityEnergyAtt entity,
      double par2,
      double par4,
      double par6,
      float par8,
      float par9,
      float scale,
      double sx,
      double sy,
      double sz,
      int color,
      int color2,
      float rotation
   ) {
      this.field_76989_e = 0.0F;
      int shrink = entity.getShrink();
      byte type = entity.getType();
      byte perc = entity.getPerc();
      int dam = entity.getDam();
      byte[] sts = entity.getSts();
      byte den = entity.getDen();
      float var13x = this.handleRotationFloat(entity, par9);
      double x = sx - entity.field_70165_t;
      double y = sy - entity.field_70163_u;
      double z = sz - entity.field_70161_v;
      if (shrink > 0) {
         this.updateEffect2(entity);
      } else {
         entity.dist = MathHelper.func_76133_a(x * x + y * y + z * z);
         entity.finalDist = entity.dist;
      }

      float size = entity.getSize() * (JGConfigClientSettings.CLIENT_Ki_Scale / 10.0F);
      boolean dual_color = JGConfigClientSettings.CLIENT_DA9;
      if (type == 1 || type == 5 || type == 6) {
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glScalef(size, size, size);
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }

      if (type == 7 || type == 8) {
         GL11.glPushMatrix();
         float szaz = 120.0F;
         float egy = szaz / 100.0F;
         float offtr = entity.field_70173_aa / egy / 100.0F;
         offtr = 1.0F - offtr;
         offtr /= 10.0F;
         GL11.glEnable(3042);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 771);
         GL11.glAlphaFunc(516, 0.003921569F);
         GL11.glDepthMask(false);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         float alph = type == 7 ? 0.05F : offtr;
         JGRenderHelper.tex(this.field_76990_c, color, alph);
         GL11.glScalef(size, size, size);
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, var13x / 2.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color, alph);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, var13x / 2.0F, false);
         }

         GL11.glDisable(3042);
         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }

      if (type == 2) {
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glScalef(size, size, size);
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }

      if (type == 3) {
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }

      if (type == 4) {
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, JRMCoreH.techCol[4], this.alpha);
         GL11.glScalef(size, size, (float)(entity.dist * 2.0));
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glScalef(size, size, (float)(entity.dist * 2.0));
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel((byte)0, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)0, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }

      if (type == 0) {
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glScalef(size, size, size);
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
         GL11.glPushMatrix();
         JRMCoreClient.mc.field_71460_t.func_78483_a(0.0);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 1);
         GL11.glTranslatef((float)par2, (float)par4, (float)par6);
         GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 180.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glScalef(size, size, (float)(entity.dist * 2.0));
         if (dual_color) {
            GL11.glDepthMask(false);
         }

         this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel(type, entity, 0.0F, 0.0F, 0.0625F, var13x, false);
         }

         GL11.glEnable(2896);
         JRMCoreClient.mc.field_71460_t.func_78463_b(0.0);
         if (dual_color) {
            GL11.glDepthMask(true);
         }

         GL11.glPopMatrix();
      }
   }
}
