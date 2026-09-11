package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.JGRenderHelper;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

public class RenderEnergyAttackKiCharge extends RenderEnergyAttack<EntityEng> {
   private boolean rotate_player_yaw;
   private boolean rotate_player_pitch;
   private ModelEnergy ener = new ModelEnergy();

   @Override
   public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
      if (entity != null && entity instanceof EntityEng && ((EntityEng)entity).user != null) {
         GL11.glPushMatrix();
         GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef((float)(-par2), (float)(-par4), (float)par6);
         GL11.glPushMatrix();
         this.renderEnergy((EntityEng)entity, par2, par4, par6, par8, par9);
         GL11.glPopMatrix();
         GL11.glPopMatrix();
      }
   }

   public void renderEnergy(EntityEng entity, double par2, double par4, double par6, float par8, float par9) {
      this.transparent = false;
      this.updateEffect(entity);
      byte type = (byte)entity.getType();
      float rotation = this.handleRotationFloat(entity, par9);
      int color = entity.destroyer
         ? (JGConfigClientSettings.CLIENT_Ki_3d[type] ? JRMCoreH.techCol[5] : JRMCoreH.techCol4[entity.getColor()])
         : JRMCoreH.techCol[entity.getColor()];
      int color2 = entity.destroyer
         ? (JGConfigClientSettings.CLIENT_Ki_3d[type] ? JRMCoreH.techCol4[entity.getColor()] : JRMCoreH.techCol[entity.getColor()])
         : (entity.getColor2() == -1 ? JRMCoreH.techCol2[entity.getColor()] : JRMCoreH.techCol3[entity.getColor2()]);
      EntityPlayer user = entity.user;
      int ticksExisted = entity.field_70173_aa;
      float scale = ticksExisted * (entity.getSize() / 100.0F);
      if (scale > entity.getSize()) {
         scale = entity.getSize();
      }

      scale *= JGConfigClientSettings.CLIENT_Ki_Charge_Scale / 10.0F;
      float height = -user.field_70131_O;
      float height2 = height * 0.8F;
      float width = user.field_70130_N;
      float width2 = width * 0.8F;
      this.mode = 2;
      double x = 0.0;
      double y = 0.0;
      double z = 0.0;
      x = 0.0;
      y = 0.0;
      z = 0.0;
      this.setVisuals(entity, type, color, color2, scale, this.mode);
      if (entity.isWave()) {
         x = 0.0;
         y = height2;
         z = width + 1.0F + scale / 20.0F;
      } else if (entity.isBlast()) {
         x = width2 * 0.35F;
         y = height2;
         z = width + 1.0F + scale / 20.0F;
      } else if (entity.isDisk()) {
         x = width2;
         y = height * 1.1F - 0.3F - scale / 20.0F;
         z = 0.0;
      } else if (entity.isLaser()) {
         x = width2 * 0.35F;
         y = height2;
         z = width + 1.0F + scale / 20.0F;
      } else if (entity.isLargeBlast()) {
         x = 0.0;
         y = height - 1.0F - scale / 2.0F;
         z = 0.0;
      } else if (entity.isSpiral()) {
         x = width2 * 0.35F;
         y = height2;
         z = width + 1.0F + scale / 20.0F;
      } else if (entity.isBarrage()) {
         x = width2 * 0.35F;
         y = height2;
         z = width + 1.0F + scale / 20.0F;
      } else if (entity.isShield() || entity.isExplosion()) {
         x = 0.0;
         y = height / 2.0F;
         z = 0.0;
      }

      if (JGConfigClientSettings.CLIENT_Ki_Charge_3d[type]) {
         this.render_3d(entity, x, y, z, par8, par9, scale, 0.0, 0.0, 0.0, color, color2, rotation);
      } else {
         this.render_2d(entity, x, y, z, par8, par9, scale, 0.0, 0.0, 0.0, color, color2, rotation);
      }
   }

   public void updateEffect(EntityEng entity) {
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

   public void setVisuals(EntityEng entity, byte type, int color, int color2, float scale, byte mode) {
      float scale_all = entity.render_scale * scale;
      entity.animation_id_Max = 1;
      entity.animation_random_Max = 1;
      entity.animation_speed = 7;
      this.rotate_player_yaw = false;
      this.rotate_player_pitch = false;
      this.rendermode_tail = 0;
      this.render_tail = true;
      this.render_tail_connect = true;
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
      this.modifierTranslation = 0.0F + scale_all * (1.0F / scale_all);
      this.detail = 4;
      this.brightness = 200;
      this.alpha = 1.0F * (JGConfigClientSettings.CLIENT_Ki_Charge_Visibility / 10.0F);
      if (this.mode == 2) {
         this.setColors(color, this.alpha);
         this.setColors2(color2);
         this.texture = (entity.destroyer ? "dbc_god/" : "dbc/") + "charge/" + this.texture_type[type] + "/";
         scale_all *= 0.5F;
         this.render_tail = true;
         this.render_tail_connect = true;
         this.scaling_head = false;
         this.scaling_tail = false;
         this.head_follow = false;
         this.tail_follow = true;
         this.rendermode_tail = 2;
         if (entity.isWave()) {
            this.rotate_player_yaw = true;
            this.rotate_player_pitch = true;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isBlast()) {
            this.rotate_player_yaw = true;
            this.rotate_player_pitch = true;
            scale_all *= 0.5F;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isDisk()) {
            this.rotate_player_yaw = true;
            this.rotate_player_pitch = true;
            this.rendermode_tail = 1;
            this.render_tail = true;
            this.render_tail_connect = false;
            this.scaling_head = false;
            this.scaling_tail = false;
         } else if (entity.isLaser()) {
            this.rotate_player_yaw = true;
            this.rotate_player_pitch = true;
            scale_all *= 0.5F;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isLargeBlast()) {
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isSpiral()) {
            this.rotate_player_yaw = true;
            this.rotate_player_pitch = true;
            scale_all *= 0.5F;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isBarrage()) {
            this.rotate_player_yaw = true;
            this.rotate_player_pitch = true;
            scale_all *= 0.25F;
            this.render_tail = true;
            this.render_tail_connect = true;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.head_follow = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
         } else if (entity.isShield()) {
            this.render_tail = true;
            this.render_tail_connect = false;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
            this.transparent = true;
         } else if (entity.isExplosion()) {
            this.render_tail = true;
            this.render_tail_connect = false;
            this.scaling_head = false;
            this.scaling_tail = false;
            this.tail_follow = true;
            this.rendermode_tail = 2;
            this.transparent = true;
         }

         this.render_tail_connect = false;
      }

      this.scale_head = 1.0F * scale_all * (1.0F + this.scale_head_more);
      this.scale_head_connect = 1.0F * scale_all;
      this.scale_middle = 1.0F * scale_all;
      this.scale_noise = 1.0F * scale_all;
      this.scale_tail = 1.0F * scale_all * (1.0F + scale_all * this.scale_tail_more);
      this.scale_tail_connect = 1.0F * scale_all;
   }

   public void render_2d(
      EntityEng entity,
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
      long time = entity.user.field_70170_p.func_82737_E();
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

      GL11.glTranslatef(0.0F, (float)par4, 0.0F);
      if (this.rotate_player_yaw) {
         GL11.glRotatef(entity.user.field_70177_z, 0.0F, 1.0F, 0.0F);
      }

      if (this.rotate_player_pitch) {
         GL11.glRotatef(entity.user.field_70125_A, -1.0F, 0.0F, 0.0F);
      }

      GL11.glTranslatef((float)par2, 0.0F, (float)par6);
      if (this.rotate_player_pitch) {
         GL11.glRotatef(entity.user.field_70125_A, 1.0F, 0.0F, 0.0F);
      }

      if (this.rotate_player_yaw) {
         GL11.glRotatef(entity.user.field_70177_z, 0.0F, -1.0F, 0.0F);
      }

      GL11.glRotated(90.0, 1.0, 0.0, 0.0);
      float particleMiddleWidth = 1.0F;
      float wave_start_size = 1.0F;
      float wave_end_size = 1.0F;
      int texture_id = 0;
      if (this.render_tail) {
         if (this.rendermode_tail == 0 && !this.tail_follow || this.rendermode_tail == 2 && !this.tail_follow) {
            for (int i = 0; i < 2; i++) {
               GL11.glPushMatrix();
               if (this.rendermode_tail == 2) {
               }

               if (this.rotate_tail) {
                  GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
               }

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
                     GL11.glTranslated(0.0, 0.0, j * 0.01);
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
            if (this.rendermode_tail == 2) {
            }

            EntityClientPlayerMP client = JRMCoreClient.mc.field_71439_g;
            float rotationX = client.field_70127_C + (client.field_70125_A - client.field_70127_C) * par9;
            float rotationY = client.field_70126_B + (client.field_70177_z - client.field_70126_B) * par9 - 180.0F;
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
            }

            if (this.rotate_tail_connect) {
               GL11.glRotatef((float)time % (360.0F / this.rotationSpeed) * this.rotationSpeed * 1.0F + this.rotationSpeed * par9, 0.0F, 1.0F, 0.0F);
            }

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
      EntityEng entity,
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
      EntityPlayer user = entity.user;
      int ticksExisted = entity.field_70173_aa;
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glDisable(2896);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      GL11.glDepthMask(false);
      if (entity.getColor() > JRMCoreH.techCol.length) {
         color = 0;
      }

      if (entity.getColor() > JRMCoreH.techCol2.length) {
         color2 = 0;
      }

      float u = -user.field_70131_O * 0.8F;
      int type = entity.getType();
      boolean dual_color = JGConfigClientSettings.CLIENT_DA9;
      if (entity.isBlast()) {
         scale /= 2.0F;
         if (scale > 0.5F) {
            scale = 0.5F;
         }

         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(user.field_70125_A, -1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, user.field_70130_N + 1.0F);
         GL11.glTranslatef(0.2F, 0.0F, 0.0F);
         GL11.glScalef(scale, scale, scale);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glRotatef(ticksExisted * 15.0F, 1.0F, 1.0F, 0.0F);
         this.ener.renderModel((byte)type, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)type, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }
      } else if (entity.isLargeBlast()) {
         GL11.glEnable(3042);
         GL11.glDisable(2896);
         GL11.glBlendFunc(770, 771);
         GL11.glAlphaFunc(516, 0.003921569F);
         GL11.glDepthMask(false);
         if (entity.getPartid() == 1) {
            scale *= 6.0F;
         }

         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, -user.field_70131_O - scale, 0.0F);
         GL11.glScalef(scale, scale, scale);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glRotatef(ticksExisted * 15.0F, 1.0F, 1.0F, 0.0F);
         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }
      } else if (entity.isShield() || entity.isExplosion()) {
         scale += 0.5F;
         scale *= 4.0F;
         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F + user.field_70131_O / 4.0F, 0.0F);
         GL11.glScalef(scale * user.field_70131_O / 2.0F, scale * user.field_70131_O / 2.0F, scale * user.field_70131_O / 2.0F);
         float szaz = 20.0F;
         float egy = szaz / 100.0F;
         float offtr = ticksExisted / egy / 100.0F;
         offtr /= 40.0F;
         if (offtr > 0.1F) {
            offtr = 0.1F;
         }

         JGRenderHelper.tex(this.field_76990_c, color, offtr);
         GL11.glRotatef(ticksExisted * 15.0F, 1.0F, 1.0F, 0.0F);
         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, offtr);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }

         GL11.glDisable(3042);
         GL11.glEnable(2896);
         GL11.glDepthMask(true);
      } else if (entity.isDisk()) {
         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(user.field_70130_N / 1.2F, -user.field_70131_O * 0.7F, 0.0F);
         GL11.glScalef(scale, scale, scale);
         GL11.glRotatef(ticksExisted * 30, 0.0F, 1.0F, 0.0F);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         this.ener.renderModel((byte)type, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)type, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }
      } else if (entity.isLaser()) {
         scale /= 4.0F;
         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(user.field_70125_A, -1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, user.field_70130_N + 1.0F);
         GL11.glTranslatef(0.2F, 0.0F, 0.0F);
         GL11.glScalef(scale, scale, scale);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glRotatef(ticksExisted * 15.0F, 1.0F, 1.0F, 0.0F);
         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }
      } else if (entity.isSpiral()) {
         scale /= 4.0F;
         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(user.field_70125_A, -1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, user.field_70130_N + 1.0F);
         GL11.glTranslatef(0.2F, 0.0F, 0.0F);
         GL11.glScalef(scale, scale, scale);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glRotatef(ticksExisted * 15.0F, 1.0F, 1.0F, 0.0F);
         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }
      } else if (entity.isWave()) {
         scale /= 2.0F;
         GL11.glTranslatef(0.0F, u, 0.0F);
         GL11.glRotatef(user.field_70177_z, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(user.field_70125_A, -1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, user.field_70130_N + 1.0F);
         GL11.glScalef(scale, scale, scale);
         JGRenderHelper.tex(this.field_76990_c, color, this.alpha);
         GL11.glRotatef(ticksExisted * 15.0F, 1.0F, 1.0F, 0.0F);
         this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         if (dual_color) {
            JGRenderHelper.tex(this.field_76990_c, color2, this.alpha);
            GL11.glScalef(0.8F, 0.8F, 0.8F);
            this.ener.renderModel((byte)1, entity, 0.0F, 0.0F, 0.0625F, 0.0F, false);
         }
      }

      GL11.glEnable(2896);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }
}
