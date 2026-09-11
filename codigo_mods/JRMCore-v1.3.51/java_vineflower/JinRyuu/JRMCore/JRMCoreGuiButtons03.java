package JinRyuu.JRMCore;

import JinRyuu.JRMCore.client.notification.JGNotificationHandler;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtons03 extends GuiButton {
   public static String icons = JRMCoreH.tjjrmc + ":icons3.png";
   public static int newsAnimation = 0;
   public static int noticiationAnimation = 0;
   public static int noticiationAnimationLast = 0;
   public static Instant animation = null;
   public static float rotation = 0.0F;
   private int col;
   private int type;
   private int resourceID;

   public JRMCoreGuiButtons03(int par1, int par2, int par3, String par6Str, int type, int col, int resourceID) {
      super(par1, par2, par3, 20, 20, par6Str);
      this.field_146120_f = 20;
      this.field_146121_g = 20;
      this.col = col;
      this.type = type;
      this.resourceID = resourceID;
   }

   public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
      if (this.field_146125_m) {
         int t = (int)(System.currentTimeMillis() / 100L % 20L);
         newsAnimation = t - 10;
         newsAnimation = newsAnimation < 0 ? newsAnimation * -1 : newsAnimation;
         boolean hasNewContent = JGNotificationHandler.hasNewContent;
         if (!hasNewContent) {
            animation = null;
         }

         FontRenderer var4 = par1Minecraft.field_71466_p;
         ResourceLocation txx = new ResourceLocation(icons);
         par1Minecraft.func_110434_K().func_110577_a(txx);
         GL11.glColor4f(0.7F, 0.7F, 0.7F, 1.0F);
         this.field_146123_n = par2 >= this.field_146128_h
            && par3 >= this.field_146129_i
            && par2 < this.field_146128_h + this.field_146120_f
            && par3 < this.field_146129_i + this.field_146121_g;
         int var5 = this.func_146114_a(this.field_146123_n);
         boolean redBack = this.resourceID == 10 || this.resourceID == 9 || this.resourceID == 8;
         int j = this.col == 0 ? 16449280 : this.col;
         float h2 = (j >> 16 & 0xFF) / 255.0F;
         float h3 = (j >> 8 & 0xFF) / 255.0F;
         float h4 = (j & 0xFF) / 255.0F;
         float h1 = redBack ? 2.0F : 1.0F;
         if (var5 == 2) {
            int r = (int)(h2 * 254.0F);
            int g = (int)(h3 * 254.0F);
            int b = (int)(h4 * 254.0F);
            float[] hsb = Color.RGBtoHSB(r, g, b, null);
            float hue = hsb[0];
            float saturation = 0.33F;
            float brightness = hsb[2];
            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
            h2 = (rgb >> 16 & 0xFF) / 255.0F;
            h3 = (rgb >> 8 & 0xFF) / 255.0F;
            h4 = (rgb & 0xFF) / 255.0F;
         }

         GL11.glColor3f(h1 * h2, h1 * h3, h1 * h4);
         this.func_73729_b(
            this.field_146128_h,
            this.field_146129_i,
            0 + var5 * 20 + (redBack ? 80 - var5 * 20 : 0),
            156 + this.type * 20 + (redBack ? var5 * 20 : 0),
            this.field_146120_f,
            this.field_146121_g
         );
         this.func_146119_b(par1Minecraft, par2, par3);
         int var6 = 14737632;
         if (!this.field_146124_l) {
            var6 = -6250336;
         } else if (this.field_146123_n) {
            var6 = 16777120;
         }

         if (this.resourceID != 0 && this.resourceID != 10) {
            float color = var5 != 2 && this.type != 1 ? 0.6F : 1.0F;
            GL11.glColor4f(color, color, color, 1.0F);
         } else {
            int r = (int)(h2 * 254.0F);
            int g = (int)(h3 * 254.0F);
            int b = (int)(h4 * 254.0F);
            float[] hsb = Color.RGBtoHSB(r, g, b, null);
            float hue = 0.0F;
            float saturation = 0.0F;
            float brightness = 1.0F;
            brightness = 0.75F + newsAnimation / 40.0F;
            brightness = brightness < 0.75F ? 0.75F : (brightness > 1.0F ? 1.0F : brightness);
            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
            h2 = (rgb >> 16 & 0xFF) / 255.0F;
            h3 = (rgb >> 8 & 0xFF) / 255.0F;
            h4 = (rgb & 0xFF) / 255.0F;
            GL11.glColor3f(h1 * h2, h1 * h3, h1 * h4);
         }

         txx = new ResourceLocation(icons);
         par1Minecraft.func_110434_K().func_110577_a(txx);
         GL11.glPushMatrix();
         GL11.glTranslatef(this.field_146128_h + 2, this.field_146129_i + 2, 0.0F);
         boolean isNotification = this.resourceID == 10;
         if (isNotification && animation != null) {
            float rotation = (float)Duration.between(animation, Instant.now()).toMillis() / 10.0F;
            JRMCoreGuiButtons03.rotation += rotation;
            rotation = (float)Math.sin(JRMCoreGuiButtons03.rotation / 25.0F) * 20.0F;
            GL11.glTranslatef(8.0F, 8.0F, 0.0F);
            GL11.glRotatef(rotation, 0.0F, 0.0F, 1.0F);
         }

         this.func_73729_b(
            isNotification && hasNewContent && animation != null ? -8 : 0,
            isNotification && hasNewContent && animation != null ? -8 : 0,
            this.resourceID * 16,
            JRMCoreH.Pwrtyp == 2 ? 16 : (JRMCoreH.Pwrtyp == 3 ? 32 : 0),
            16,
            16
         );
         GL11.glPopMatrix();
         if (isNotification) {
            float rotation = (float)Math.sin(JRMCoreGuiButtons03.rotation / 30.0F) * 3.0F;
            int MUTED = 0;
            int NEW = 1;
            int NORMAL = 2;
            int state = hasNewContent ? 1 : 2;
            int bonusY = (int)rotation;
            if (state == 1) {
               if (JRMCoreClient.mc.field_71439_g.field_70173_aa != noticiationAnimationLast) {
                  noticiationAnimation++;
                  if (noticiationAnimation > 3) {
                     noticiationAnimation = 0;
                  }

                  noticiationAnimationLast = JRMCoreClient.mc.field_71439_g.field_70173_aa;
               }
            } else if (bonusY != 0) {
               bonusY = 0;
            }

            rotation = (float)Math.sin(JRMCoreGuiButtons03.rotation / 25.0F) * 20.0F;
            GL11.glPushMatrix();
            GL11.glTranslatef(this.field_146128_h + 2, this.field_146129_i + 2 + bonusY, 0.0F);
            GL11.glTranslatef(8.0F, 8.0F, 0.0F);
            GL11.glRotatef(rotation, 0.0F, 0.0F, 1.0F);
            this.func_73729_b(isNotification ? -8 : 0, isNotification ? -8 : 0, 16, 48 + state * 16, 16, 16);
            GL11.glPopMatrix();
            if (state == 1) {
               animation = Instant.now();
            }
         }
      }
   }
}
