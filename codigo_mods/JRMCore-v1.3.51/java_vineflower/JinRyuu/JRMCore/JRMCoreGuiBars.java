package JinRyuu.JRMCore;

import JinRyuu.DragonBC.common.DBCClientTickHandler;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import java.time.Duration;
import java.time.Instant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiBars extends Gui {
   Minecraft mc;
   private int kihelp1;
   private int kihelp2;
   private short kihelp3;
   private float kihelp4;
   private float xSize_lo;
   private float ySize_lo;
   private long time_start;
   private boolean started;
   private byte swoop_id;
   static String[][] HUD_Properties;
   static String[] HUD_Images;
   final int CH_Scale;
   int CH_xMain;
   int CH_yMain;
   int id;
   int barAnimation;
   int max_barAnimation;
   private static boolean custom_hud_good = false;
   public static String icons = JRMCoreH.tjjrmc + ":";
   public static final int icon_Blocking = 0;
   public static final int icon_Flying = 1;
   public static final int icon_Release = 2;
   public static final int icon_Turbo = 2;
   public static final int icon_Kaioken = 3;
   public static final int icon_Swoop = 4;
   public static final int icon_FullMoon = 0;
   public static final int icon_GodPower = 0;
   public static final int icon_Strained = 5;
   public static final int icon_StrainIn = 5;
   public static final int icon_Fatigue = 5;

   public JRMCoreGuiBars() {
      this.mc = JRMCoreClient.mc;
      this.started = false;
      this.swoop_id = 0;
      this.CH_Scale = 2;
      this.CH_xMain = 0;
      this.CH_yMain = 0;
      this.id = 0;
      this.barAnimation = 100;
      this.max_barAnimation = 100;
   }

   public int getClientHealth() {
      return JRMCoreH.curBody;
   }

   public int getClientMaxHealth() {
      return JRMCoreH.maxBody;
   }

   public int getClientEnergy() {
      return JRMCoreH.curEnergy;
   }

   public int getClientMaxEnergy() {
      return JRMCoreH.maxEnergy;
   }

   public int getClientStamina() {
      return JRMCoreH.curStamina;
   }

   public int getClientMaxStamina() {
      return JRMCoreH.maxStamina;
   }

   public int getSmoothReleaseLevel() {
      return (int)JRMCoreHC.smoothReleaseLevel;
   }

   public void renderSafeZoneIndicator() {
      if (JGConfigClientSettings.safeZoneUIModeOn && JRMCoreEH.currentSafeZoneNames.size() > 0) {
         GL11.glPushMatrix();
         ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
         int var6 = var5.func_78326_a();
         int var7 = var5.func_78328_b();
         FontRenderer var8 = this.mc.field_71466_p;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         int TEXTURE_SIZE = 24;
         String text = JRMCoreEH.currentSafeZoneNames.get(JRMCoreEH.currentSafeZoneNames.size() - 1);
         int x = var6 / 2 - var8.func_78256_a(text) / 2;
         int y = 28;
         var8.func_78276_b(text, x + 1, y, 0);
         var8.func_78276_b(text, x - 1, y, 0);
         var8.func_78276_b(text, x, y + 1, 0);
         var8.func_78276_b(text, x, y - 1, 0);
         var8.func_78276_b(text, x, y, 16574720);
         ResourceLocation tx = new ResourceLocation("jinryuumodscore:icons3.png");
         this.mc.field_71446_o.func_110577_a(tx);
         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         this.func_73729_b(var6 / 2 - 8, 3, 192, 16, 24, 24);
         GL11.glDisable(3042);
         GL11.glPopMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }
   }

   public void swoop() {
      GL11.glPushMatrix();
      if (this.started && System.nanoTime() / 10000000L - this.time_start > 7L) {
         this.started = false;
      }

      if (!this.started) {
         this.started = true;
         this.time_start = System.nanoTime() / 10000000L;
         int last_id = this.swoop_id;

         while (this.swoop_id == last_id) {
            this.swoop_id = (byte)(Math.random() * 4.0);
         }
      }

      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int x = (var5.func_78326_a() - 256) / 2;
      int y = (var5.func_78328_b() - 256) / 2;
      ResourceLocation tx2 = new ResourceLocation("jinryuudragonbc:sw" + this.swoop_id + ".png");
      this.mc.field_71446_o.func_110577_a(tx2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glScalef(var5.func_78326_a() / 256.0F, var5.func_78328_b() / 256.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      this.func_73729_b(0, 0, 0, 0, 256, 256);
      GL11.glDisable(3042);
      GL11.glScalef(1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public void renderCG(int mode) {
      this.barAnimation--;
      if (this.barAnimation < 0) {
         this.barAnimation = this.max_barAnimation;
      }

      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a() + JGConfigClientSettings.CLIENT_hud1x;
      int var7 = var5.func_78328_b() - JGConfigClientSettings.CLIENT_hud1y;
      this.CH_xMain = JGConfigClientSettings.CLIENT_hud0x;
      this.CH_yMain = JGConfigClientSettings.CLIENT_hud0y;
      FontRenderer var8 = this.mc.field_71466_p;
      this.mc.field_71460_t.func_78478_c();
      byte HUD_Number = 8;
      byte img_HUD = 6;
      byte[] HUD_Number2 = new byte[]{6, 14, 14, 14, 14, 14, 7, 6};

      try {
         HUD_Properties = new String[8][];

         for (int i = 0; i < 8; i++) {
            HUD_Properties[i] = new String[HUD_Number2[i]];
            String data = JGConfigClientSettings.CLIENT_hud2_2.split(";")[i];

            for (int j = 0; j < HUD_Number2[i]; j++) {
               String data2 = data.split(",")[j];
               if (data2 != null && !data2.equals("")) {
                  HUD_Properties[i][j] = data2;
               }
            }
         }

         HUD_Images = new String[6];
         int[] img_ids = new int[]{5, 9, 9, 9, 9, 9};

         for (int i = 0; i < 6; i++) {
            HUD_Images[i] = HUD_Properties[i][img_ids[i]];
         }

         custom_hud_good = true;
      } catch (Exception e) {
         custom_hud_good = false;
      }

      if (custom_hud_good) {
         int X = 0;
         int Y = 1;
         int HUD_WIDTH = 2;
         int HUD_HEIGHT = 3;
         int HUD_DIRECTION = 5;

         for (int i = 0; i < 6; i++) {
            this.id = i;
            if (i == 0) {
               int w = 0;
               int h = 0;
               int x = get_Property(i, 0);
               int y = get_Property(i, 1);
               w = get_Property(i, 2);
               h = get_Property(i, 3);
               this.drawHUD(HUD_Images[i], x, y, 0, 0, w, h, 0, false);
            } else if (i > 0) {
               float max_barAnimation = 1.0F;
               float barAnimation = 0.0F;

               for (int j = 0; j < 2; j++) {
                  float width = 1.0F;
                  float height = 1.0F;
                  int posX1 = 0;
                  int posY1 = 0;
                  int posX2 = 0;
                  int posY2 = 0;
                  int w = 0;
                  int h = 0;
                  int direction = get_Property(i, 5);
                  if (j == 1) {
                     if (i == 1) {
                        float ki = this.getClientHealth();
                        float maxki = this.getClientMaxHealth();
                        float egy = maxki / 100.0F;
                        float curr = ki / egy;
                        max_barAnimation = 1.0F;
                        barAnimation = 1.0F - curr / 100.0F;
                     } else if (i == 2) {
                        float ki = this.getClientEnergy();
                        float maxki = this.getClientMaxEnergy();
                        float egy = maxki / 100.0F;
                        float curr = ki / egy;
                        max_barAnimation = 1.0F;
                        barAnimation = 1.0F - curr / 100.0F;
                     } else if (i == 3) {
                        float ki = this.getClientStamina();
                        float maxki = this.getClientMaxStamina();
                        float egy = maxki / 100.0F;
                        float curr = ki / egy;
                        max_barAnimation = 1.0F;
                        barAnimation = 1.0F - curr / 100.0F;
                     } else if (i == 4) {
                        float ki = JRMCoreH.TransSaiCurRg;
                        float maxki = 100.0F;
                        float egy = maxki / 100.0F;
                        float curr = ki / egy;
                        max_barAnimation = 1.0F;
                        barAnimation = 1.0F - curr / 100.0F;
                     } else if (i == 5) {
                        float ki = this.getSmoothReleaseLevel();
                        float maxki = 100.0F;
                        float egy = maxki / 100.0F;
                        float curr = ki / egy;
                        max_barAnimation = 1.0F;
                        barAnimation = 1.0F - curr / 100.0F;
                     }

                     if (direction == 0) {
                        width = (max_barAnimation - barAnimation) / max_barAnimation;
                     } else if (direction == 1) {
                        width = (max_barAnimation - barAnimation) / max_barAnimation;
                        posX1 = (int)(get_Property(i, 2) - get_Property(i, 2) * width);
                        posX2 = (int)(get_Property(i, 2) - get_Property(i, 2) * width);
                     } else if (direction == 2) {
                        height = (max_barAnimation - barAnimation) / max_barAnimation;
                     } else if (direction == 3) {
                        height = (max_barAnimation - barAnimation) / max_barAnimation;
                     }

                     if (j == 1 && direction != 2) {
                        posY2 = get_Property(i, 3);
                     } else if (j == 1 && direction == 2) {
                        posY2 = get_Property(i, 3) + (int)(get_Property(i, 3) * (1.0F - height));
                     }
                  }

                  int x = get_Property(i, 0) + posX1;
                  int y = get_Property(i, 1) + posY1;
                  if (j == 1 && direction == 2) {
                     y += (int)(get_Property(i, 3) * (1.0F - height));
                  }

                  w = (int)(get_Property(i, 2) * width);
                  h = (int)(get_Property(i, 3) * height);
                  if (j == 1) {
                     h++;
                  }

                  this.drawHUD2(HUD_Images[i], x, y, posX2, posY2, w, h, j, false);
                  if (i == 1 && j == 1) {
                     float health = JRMCoreClient.mc.field_71439_g.func_110143_aJ();
                     float healthmax = JRMCoreClient.mc.field_71439_g.func_110138_aP();
                     float maxperc = healthmax / 100.0F;
                     int var20 = (int)(health / maxperc);
                     if (this.getClientHealth() <= 0) {
                        max_barAnimation = 1.0F;
                        barAnimation = 1.0F - var20 / 100.0F;
                        if (direction == 0) {
                           width = (max_barAnimation - barAnimation) / max_barAnimation;
                        } else if (direction == 1) {
                           width = (max_barAnimation - barAnimation) / max_barAnimation;
                           posX1 = (int)(get_Property(i, 2) - get_Property(i, 2) * width);
                           posX2 = (int)(get_Property(i, 2) - get_Property(i, 2) * width);
                        } else if (direction == 2) {
                           height = (max_barAnimation - barAnimation) / max_barAnimation;
                        } else if (direction == 3) {
                           height = (max_barAnimation - barAnimation) / max_barAnimation;
                        }

                        if (j == 1 && direction != 2) {
                           posY2 = get_Property(i, 3);
                        } else if (j == 1 && direction == 2) {
                           posY2 = get_Property(i, 3) + (int)(get_Property(i, 3) * (1.0F - height));
                        }

                        x = get_Property(i, 0) + posX1;
                        y = get_Property(i, 1) + posY1;
                        if (j == 1 && direction == 2) {
                           y += (int)(get_Property(i, 3) * (1.0F - height));
                        }

                        w = (int)(get_Property(i, 2) * width);
                        h = (int)(get_Property(i, 3) * height);
                        if (j == 1) {
                           h++;
                        }

                        this.drawHUD2(HUD_Images[i], x, y, posX2, posY2, w, h, j, true);
                     }
                  }
               }

               int x = get_Property(i, 6);
               int y = get_Property(i, 7);
               int w = get_Property(i, 2) == 1 ? 100 : 20;
               int h = get_Property(i, 2) == 1 ? 20 : 100;
               String text = "";
               String text2 = "";
               int border = get_Property(i, 8);
               int min = 1;
               int max = 1;
               if (i == 1) {
                  float ki = this.getClientHealth();
                  float maxki = this.getClientMaxHealth();
                  min = (int)ki;
                  max = (int)maxki;
               } else if (i == 2) {
                  float ki = this.getClientEnergy();
                  float maxki = this.getClientMaxEnergy();
                  min = (int)ki;
                  max = (int)maxki;
               } else if (i == 3) {
                  float ki = this.getClientStamina();
                  float maxki = this.getClientMaxStamina();
                  min = (int)ki;
                  max = (int)maxki;
               } else if (i == 4) {
                  float ki = JRMCoreH.TransSaiCurRg;
                  float maxki = 100.0F;
                  min = (int)ki;
                  max = (int)maxki;
               } else if (i == 5) {
                  float ki = this.getSmoothReleaseLevel();
                  float maxki = 100.0F;
                  min = (int)ki;
                  max = (int)maxki;
               }

               if (JGConfigClientSettings.CLIENT_GR5) {
                  float egy = max / 100.0F;
                  float curr = min / egy;
                  text = HUD_Properties[this.id][13];
                  text2 = text.replace("|", ":").replace("#amount", (int)curr + "").replace("#max", "").replace("/", "") + "%";
               } else {
                  text = HUD_Properties[this.id][13];
                  text2 = text.replace("|", ":").replace("#amount", JRMCoreH.numSep(min) + "").replace("#max", JRMCoreH.numSep(max) + "");
               }

               int r = get_Property(i, 10);
               int g = get_Property(i, 11);
               int b = get_Property(i, 12);
               int rgb = r;
               rgb = (rgb << 8) + g;
               rgb = (rgb << 8) + b;
               if (!HUD_Properties[this.id][13].contains("#empty")) {
                  this.drawString2(var8, text2, x + this.CH_xMain, y + this.CH_yMain, border, false, rgb);
               }
            }
         }

         this.id++;
         int x = get_Property(6, 0) + this.CH_xMain;
         int y = get_Property(6, 1) + this.CH_yMain - 10;
         this.showSE(x, y, get_Property(6, 3), get_Property(6, 2));
         this.id++;
         x = get_Property(7, 0);
         y = get_Property(7, 1);
         int border = get_Property(7, 2);
         String bc = "" + JRMCoreH.numSepShort(JRMCoreHC.BPC_ME);
         String text = (JRMCoreH.BPMode == 1 ? "f" : "")
            + "BP "
            + bc
            + " "
            + JRMCoreH.DifficultySNmes[JRMCoreH.Dffclty]
            + (
               JRMCoreH.isRaceArcosian() && JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) > 0
                  ? " (PP: " + JRMCoreH.getArcRsrv() + ")"
                  : (JRMCoreH.isRaceMajin() && JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) > 4 ? " (Absorption: " + JRMCoreH.getMajinAbsorptionValue() + ")" : "")
            );
         int rgb = get_Property(7, 3);
         rgb = (rgb << 8) + get_Property(7, 4);
         rgb = (rgb << 8) + get_Property(7, 5);
         this.drawString2(var8, text, x + this.CH_xMain, y + this.CH_yMain, border, false, rgb);
      }
   }

   public void drawString2(FontRenderer fr, String s, int x, int y, int b, boolean sc, int clr) {
      if (custom_hud_good) {
         boolean doit = true;
         String data = "";
         if (this.id > 0 && this.id < 6) {
            data = HUD_Properties[this.id][13];
            if (data.contains("#empty")) {
               doit = false;
            }
         }

         if (doit) {
            GL11.glPushMatrix();
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            if (b > 0) {
               if (b > 1) {
                  b = 1;
                  fr.func_78276_b(s, x + b, y + b, 0);
                  fr.func_78276_b(s, x - b, y - b, 0);
               }

               fr.func_78276_b(s, x + b, y, 0);
               fr.func_78276_b(s, x - b, y, 0);
               fr.func_78276_b(s, x, y + b, 0);
               fr.func_78276_b(s, x, y - b, 0);
            }

            int value = 0;
            if (this.id > 0) {
               if (this.id < 6) {
                  if (get_Property(this.id, 4) == 1) {
                     value = this.plrClr(clr, 1);
                  } else {
                     value = clr;
                  }
               } else {
                  value = clr;
               }
            }

            fr.func_78276_b(s, x, y, value);
            GL11.glPopMatrix();
         }
      } else {
         fr.func_78276_b(s, x, y, 0);
      }
   }

   public int plrClr(int szin, int id) {
      byte pwr = JRMCoreH.Pwrtyp;
      int bc1 = 654591;
      boolean evl = JRMCoreH.Algnmnt_Evil(JRMCoreH.align);
      if (JRMCoreH.Algnmnt_Good(JRMCoreH.align)) {
         bc1 = 654591;
      } else if (JRMCoreH.Algnmnt_Neut(JRMCoreH.align)) {
         bc1 = 13478142;
      } else if (evl) {
         bc1 = 16522030;
      }

      int s = bc1;
      if (JRMCoreH.DBC()) {
         s = JRMCoreHDBC.getPlayerColor2(
            3, bc1, pwr, JRMCoreH.Race, JRMCoreH.State, JRMCoreH.StusEfctsMe(17), JRMCoreH.lgndb2(), JRMCoreH.StusEfctsMe(19), JRMCoreH.StusEfctsMe(20)
         );
      }

      if (id != 0 && id != 100) {
         float h2 = (s >> 16 & 0xFF) / 255.0F;
         float h3 = (s >> 8 & 0xFF) / 255.0F;
         float h4 = (s & 0xFF) / 255.0F;
         float h1 = 1.0F;
         float r1 = h1 * h2;
         float g1 = h1 * h3;
         float b1 = h1 * h4;
         float h22 = (szin >> 16 & 0xFF) / 255.0F;
         float h32 = (szin >> 8 & 0xFF) / 255.0F;
         float h42 = (szin & 0xFF) / 255.0F;
         float r2 = h1 * h22;
         float g2 = h1 * h32;
         float b2 = h1 * h42;
         float r = (r1 + r2) / 2.0F;
         float g = (g1 + g2) / 2.0F;
         float b = (b1 + b2) / 2.0F;
         if (r > 1.0F) {
            r = 1.0F;
         }

         if (g > 1.0F) {
            g = 1.0F;
         }

         if (b > 1.0F) {
            b = 1.0F;
         }

         int rgb = (int)(r * 255.0F);
         rgb = (rgb << 8) + (int)(g * 255.0F);
         return (rgb << 8) + (int)(b * 255.0F);
      } else {
         float h2 = (s >> 16 & 0xFF) / 255.0F;
         float h3 = (s >> 8 & 0xFF) / 255.0F;
         float h4 = (s & 0xFF) / 255.0F;
         float h1 = 1.0F;
         float value = id == 100 ? szin / 100.0F : 1.0F;
         GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F * value);
         return -1;
      }
   }

   public void drawHUD(String img, int posX, int posY, int x, int y, int w, int h, int id, boolean on) {
      if (img != null && w > 0 && h > 0 && x > -1 && y > -1) {
         int img_width = 255;
         int img_height = 255;
         if (w > 255) {
            w = 255;
         }

         if (h > 255) {
            h = 255;
         }

         if (x > 255) {
            x = 255;
         }

         if (y > 255) {
            y = 255;
         }

         if (y + id * h + h <= 255) {
            GL11.glPushMatrix();
            ResourceLocation tx2 = new ResourceLocation("jinryuumodscore:custom_hud/" + img + ".png");
            this.mc.field_71446_o.func_110577_a(tx2);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            if (!on) {
               if (this.id > 0 && id == 1 && this.id < 6 && get_Property(this.id, 4) == 1) {
                  this.plrClr(0, 0);
               } else if (this.id == 0 && get_Property(this.id, 4) == 1) {
                  this.plrClr(0, 0);
               }
            } else {
               GL11.glColor3f(0.85F, 0.15F, 0.3F);
            }

            this.func_73729_b(posX + this.CH_xMain, posY + this.CH_yMain, x, y + id * h, w, h);
            GL11.glPopMatrix();
         }
      }
   }

   public void drawHUD2(String img, int posX, int posY, int x, int y, int w, int h, int id, boolean on) {
      if (img != null && w > 0 && h > 0 && x > -1 && y > -1) {
         int img_width = 255;
         int img_height = 255;
         if (w > 255) {
            w = 255;
         }

         if (h > 255) {
            h = 255;
         }

         if (x > 255) {
            x = 255;
         }

         if (y > 255) {
            y = 255;
         }

         GL11.glPushMatrix();
         ResourceLocation tx2 = new ResourceLocation("jinryuumodscore:custom_hud/" + img + ".png");
         this.mc.field_71446_o.func_110577_a(tx2);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         if (!on) {
            if (this.id > 0 && id == 1 && this.id < 6 && get_Property(this.id, 4) == 1) {
               this.plrClr(0, 0);
            } else if (this.id == 0 && get_Property(this.id, 4) == 1) {
               this.plrClr(0, 0);
            }
         } else {
            GL11.glColor3f(1.0F, 0.3F, 0.4F);
         }

         this.func_73729_b(posX + this.CH_xMain, posY + this.CH_yMain, x, y, w, h);
         GL11.glPopMatrix();
      }
   }

   public static int get_Property(int i, int j) {
      if (custom_hud_good) {
         return HUD_Properties != null ? Integer.parseInt(HUD_Properties[i][j]) : -1;
      } else {
         return -1;
      }
   }

   public void renderBodyBar() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a() + JGConfigClientSettings.CLIENT_hud1x;
      int var7 = var5.func_78328_b() - JGConfigClientSettings.CLIENT_hud1y;
      FontRenderer var8 = this.mc.field_71466_p;
      this.mc.field_71460_t.func_78478_c();
      float ki = this.getClientHealth();
      float maxki = this.getClientMaxHealth();
      float st = this.getClientStamina();
      float maxst = this.getClientMaxStamina();
      int var61 = var7 / 2 + 25;
      int height = JGConfigClientSettings.get_hud_1_width();
      float max = maxki;
      float maxperc = height / max;
      float var22 = maxperc * ki;
      if (var22 > height) {
         var22 = height;
      }

      float maxstperc = height / maxst;
      float var22st = maxstperc * st;
      if (var22st > height) {
         var22st = height;
      }

      float health = JRMCoreClient.mc.field_71439_g.func_110143_aJ();
      float healthmax = JRMCoreClient.mc.field_71439_g.func_110138_aP();
      max = healthmax;
      maxperc = height / max;
      int var20 = (int)(maxperc * health);
      if (var20 > height) {
         var20 = height;
      }

      this.field_73735_i = -90.0F;
      ResourceLocation tx2 = new ResourceLocation("jinryuumodscore:health.png");
      this.mc.field_71446_o.func_110577_a(tx2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      int img_height = JGConfigClientSettings.get_hud_1_height();
      int guiLeft = var6 - (JGConfigClientSettings.get_hud_1_height() + 2);
      GL11.glPushMatrix();
      GL11.glTranslatef(guiLeft, var61, 0.0F);
      if (JGConfigClientSettings.CLIENT_GR6) {
         GL11.glTranslatef(0.0F, JGConfigClientSettings.get_hud_1_height() * 2.8F, 0.0F);
         GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
      }

      this.func_73729_b(0, 0, JGConfigClientSettings.get_hud_1_pos(0), 0, JGConfigClientSettings.get_hud_1_height(), JGConfigClientSettings.get_hud_1_width());
      if (var20 > 0) {
         this.func_73729_b(0, 0, JGConfigClientSettings.get_hud_1_pos(2), 0, JGConfigClientSettings.get_hud_1_height(), var20);
      }

      if (var22 > 0.0F) {
         this.func_73729_b(0, 0, JGConfigClientSettings.get_hud_1_pos(1), 0, JGConfigClientSettings.get_hud_1_width_hea(), (int)var22);
      }

      GL11.glTranslatef(JGConfigClientSettings.get_hud_1_width_st() + 2, 0.0F, 0.0F);
      if (var22st > 0.0F) {
         this.func_73729_b(0, 0, JGConfigClientSettings.get_hud_1_pos_stam(), 0, JGConfigClientSettings.get_hud_1_width_st(), (int)var22st);
      }

      GL11.glPopMatrix();
      GL11.glDisable(3042);
      if (JGConfigClientSettings.CLIENT_hud1x < 5) {
         int diff = JGConfigClientSettings.CLIENT_hud1x / 10;
         if (diff < -30) {
            diff = -30;
         }

         guiLeft -= diff;
      }

      float one = maxki / 100.0F;
      String var34;
      if (JGConfigClientSettings.CLIENT_GR5) {
         var34 = "" + (int)(ki / one) + "%";
      } else {
         var34 = "" + JRMCoreH.numSep((int)ki);
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int var38 = guiLeft + 12 - var8.func_78256_a(var34);
      int var37 = var61 + 45;
      var8.func_78276_b(var34, var38 + 1, var37, 0);
      var8.func_78276_b(var34, var38 - 1, var37, 0);
      var8.func_78276_b(var34, var38, var37 + 1, 0);
      var8.func_78276_b(var34, var38, var37 - 1, 0);
      var8.func_78276_b(var34, var38, var37, 8388564);
   }

   public void renderSAOHealthBar() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      this.mc.field_71460_t.func_78478_c();
      int ki = this.getClientHealth();
      int maxki = this.getClientMaxHealth();
      int lvl = JRMCoreH.sao_level;
      int var51 = 14;
      int var61 = 2;
      short var21 = 30;
      int width = 179;
      float max = maxki;
      float maxperc = width / max;
      maxperc = width / max;
      int var20 = (int)(maxperc * ki);
      if (var20 > width) {
         var20 = width;
      }

      float r = var20 > 50 ? 0.58F : (var20 > 25 ? 1.0F : 1.0F);
      float g = var20 > 50 ? 0.83F : (var20 > 25 ? 1.0F : 0.2F);
      float b = var20 > 50 ? 0.23F : (var20 > 25 ? 0.2F : 0.2F);
      GL11.glPushMatrix();
      this.field_73735_i = -90.0F;
      ResourceLocation tx2 = new ResourceLocation(JRMCoreH.tjsaoc + ":gui2.png");
      this.mc.field_71446_o.func_110577_a(tx2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      this.func_73729_b(var51, var61, 0, 0, 209, var21);
      if (var20 > 0) {
         GL11.glColor4f(r, g, b, 1.0F);
         this.func_73729_b(var51 + 18, var61 + 6, 0, 32, var20, 11);
      }

      GL11.glDisable(3042);
      String var34 = "" + ki + "/" + maxki;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int var38 = var51 + 170 - var8.func_78256_a(var34);
      int var37 = var61 + 15;
      float scale = 0.75F;
      GL11.glScalef(1.0F * scale, 1.0F * scale, 1.0F * scale);
      var8.func_78276_b(var34, var38 + 65, var37 + 15, JRMCoreH.techCol[1]);
      var8.func_78276_b("lv: " + lvl, var51 + var21 + 63, var37 + 15, JRMCoreH.techCol[1]);
      GL11.glPopMatrix();
   }

   public void renderFace() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int width = var5.func_78326_a();
      int height = var5.func_78328_b();
      this.xSize_lo = width / 2.0F;
      this.ySize_lo = height / 2.0F;
      JRMCoreGuiScreen.head(20, 60, 60, 5.0F, 10.0F, this.mc.field_71439_g);
   }

   public void renderKiBar() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      this.mc.field_71460_t.func_78478_c();
      float ki = this.getClientEnergy();
      float maxki = this.getClientMaxEnergy();
      int var51 = 0;
      int var61 = 0;
      if (JRMCoreH.DBC()) {
         var51 = JRMCoreHDBC.DBCgetConfigcwfb() + 10;
         var61 = JRMCoreHDBC.DBCgetConfigchfb() + 10;
      }

      short var21 = JGConfigClientSettings.get_hud_x();
      byte pwr = JRMCoreH.Pwrtyp;
      float width = JRMCoreH.isPowerTypeKi(pwr) ? var21 : (JRMCoreH.isPowerTypeChakra(pwr) ? 85 : var21);
      float max = maxki;
      if (max < 1.0F) {
         max = 1.0F;
      }

      float maxperc = width / max;
      float var22 = maxperc * ki;
      if (var22 > width) {
         var22 = width;
      }

      this.kihelp1 = var51;
      this.kihelp2 = var61;
      this.kihelp3 = var21;
      this.kihelp4 = var22;
      int var38 = 0;
      int var37 = 0;
      boolean var11 = false;
      int var12 = var11 ? 16777215 : 8453920;
      String var34 = ""
         + JRMCoreH.numSep((int)ki)
         + (
            JRMCoreH.isRaceArcosian() && JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) > 0
               ? " (PP: " + JRMCoreH.getArcRsrv() + ")"
               : (JRMCoreH.isRaceMajin() && JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) > 4 ? " (Absorption: " + JRMCoreH.getMajinAbsorptionValue() + ")" : "")
         );
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      if (JRMCoreH.isPowerTypeKi(pwr)) {
         var38 = JRMCoreHDBC.DBCgetConfigcwfn() + ((int)width - var8.func_78256_a(var34)) / 2 + this.kihelp1;
         var37 = JRMCoreHDBC.DBCgetConfigchfn() + 2;
      } else if (JRMCoreH.isPowerTypeChakra(pwr)) {
         var38 = (2 + var8.func_78256_a(var34)) / 2;
         var37 = 15;
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      var8.func_78276_b(var34, var38 + 1 + JGConfigClientSettings.CLIENT_hud0x, var37 + JGConfigClientSettings.CLIENT_hud0y, 0);
      var8.func_78276_b(var34, var38 - 1 + JGConfigClientSettings.CLIENT_hud0x, var37 + JGConfigClientSettings.CLIENT_hud0y, 0);
      var8.func_78276_b(var34, var38 + JGConfigClientSettings.CLIENT_hud0x, var37 + 1 + JGConfigClientSettings.CLIENT_hud0y, 0);
      var8.func_78276_b(var34, var38 + JGConfigClientSettings.CLIENT_hud0x, var37 - 1 + JGConfigClientSettings.CLIENT_hud0y, 0);
      var8.func_78276_b(var34, var38 + JGConfigClientSettings.CLIENT_hud0x, var37 + JGConfigClientSettings.CLIENT_hud0y, 8388564);
      this.kiBarHelper(this.kihelp1 + JGConfigClientSettings.CLIENT_hud0x, this.kihelp2 + JGConfigClientSettings.CLIENT_hud0y, this.kihelp3, this.kihelp4);
   }

   public String getIcons() {
      return icons + "icons4" + (JRMCoreH.Pwrtyp == 2 ? "nc" : "") + ".png";
   }

   public void drawIcon(int var51, int var61, int resourceID, float r, float g, float b, float w) {
      this.mc.func_110434_K().func_110577_a(new ResourceLocation(this.getIcons()));
      GL11.glColor4f(r, g, b, 1.0F);
      int h = resourceID % 16;
      int v = resourceID / 16;
      w = w > 100.0F ? 100.0F : w;
      int w2 = (int)(0.16F * (100.0F - w));
      this.func_73729_b(var51 + 2 + (JGConfigClientSettings.CLIENT_hud0 > 1 ? 50 : 0), var61 + w2 + 2, h * 16, v * 16 + w2, 16, 16 - w2);
   }

   public void drawIcon(int var51, int var61, int resourceID) {
      this.drawIcon(var51, var61, resourceID, 1.0F, 1.0F, 1.0F, 100.0F);
   }

   public void drawIconB(int var51, int var61, int resourceID, float r, float g, float b, float w) {
      this.mc.func_110434_K().func_110577_a(new ResourceLocation(this.getIcons()));
      GL11.glColor4f(r, g, b, 1.0F);
      int h = resourceID % 16;
      int v = resourceID / 16;
      w = w > 100.0F ? 100.0F : w;
      int w2 = (int)(0.16F * (100.0F - w));
      this.func_73729_b(var51 + 2, var61 + w2 + 2, h * 16, v * 16 + w2, 16, 16 - w2);
   }

   public void drawIconB(int var51, int var61, int resourceID) {
      this.drawIconB(var51, var61, resourceID, 1.0F, 1.0F, 1.0F, 100.0F);
   }

   public void drawIcon3rdFn(int var51, int var61) {
      this.drawIconB(var51, var61, 22, 1.0F, 1.0F, 1.0F, 100.0F);
   }

   public int getx1() {
      return JGConfigClientSettings.get_hud_start_x();
   }

   public int gety1() {
      return JGConfigClientSettings.get_hud_start_y();
   }

   public void kiBarHelper(int var51, int var61, short var21, float var22) {
      FontRenderer var8 = this.mc.field_71466_p;
      byte pwr = JRMCoreH.Pwrtyp;
      ResourceLocation tx = new ResourceLocation((pwr == 1 ? "jinryuumodscore:" : (pwr == 2 ? "jinryuunarutoc:" : "jinryuumodscore:")) + "energy.png");
      this.mc.field_71446_o.func_110577_a(tx);
      this.field_73735_i = -90.0F;
      String bc = "" + JRMCoreH.numSepShort(JRMCoreHC.BPC_ME);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      if (JRMCoreH.isPowerTypeKi(pwr)) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_73729_b(var51, var61, 0, 0 + JGConfigClientSettings.get_hud_pos(0), var21 + this.getx1(), JGConfigClientSettings.get_hud_y() + this.gety1());
         if (var22 > 0.0F) {
            int bc1 = 654591;
            boolean evl = JRMCoreH.Algnmnt_Evil(JRMCoreH.align);
            if (JRMCoreH.Algnmnt_Good(JRMCoreH.align)) {
               bc1 = 654591;
            } else if (JRMCoreH.Algnmnt_Neut(JRMCoreH.align)) {
               bc1 = 13478142;
            } else if (evl) {
               bc1 = 16522030;
            }

            int s = JRMCoreHDBC.getPlayerColor2(
               3, bc1, pwr, JRMCoreH.Race, JRMCoreH.State, JRMCoreH.StusEfctsMe(17), JRMCoreH.lgndb2(), JRMCoreH.StusEfctsMe(19), JRMCoreH.StusEfctsMe(20)
            );
            float h2 = (s >> 16 & 0xFF) / 255.0F;
            float h3 = (s >> 8 & 0xFF) / 255.0F;
            float h4 = (s & 0xFF) / 255.0F;
            float h1 = 1.0F;
            GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F);
            this.func_73729_b(
               var51 + this.getx1(),
               var61,
               0 + this.getx1(),
               JGConfigClientSettings.get_hud_pos(1) + this.gety1() + (JGConfigClientSettings.CLIENT_hud0 > 1 ? -2 : 0),
               (int)var22,
               JGConfigClientSettings.get_hud_y()
            );
         }

         if (JRMCoreH.curRelease >= 0) {
            float var5 = var21 / 100.0F * this.getSmoothReleaseLevel();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBlendFunc(775, 769);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(
               var51 - 3 + (int)var5 + this.getx1(),
               var61,
               0 + this.getx1(),
               0
                  + (
                     JGConfigClientSettings.CLIENT_hud0 > 1
                        ? JGConfigClientSettings.get_hud_pos(0) + JGConfigClientSettings.get_hud_height(2) - 6
                        : JGConfigClientSettings.get_hud_pos(2) + this.gety1()
                  ),
               6,
               JGConfigClientSettings.get_hud_y()
            );
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
         }

         var8.func_78276_b(
            (JRMCoreH.BPMode == 1 ? "f" : "") + "BP " + bc + " §8" + JRMCoreH.DifficultySNmes[JRMCoreH.Dffclty] + " §0" + this.getSmoothReleaseLevel() + "%",
            var51 + 2 + (JGConfigClientSettings.CLIENT_hud0 > 1 ? 55 : 10),
            var61 + 2 + (JGConfigClientSettings.CLIENT_hud0 > 1 ? 5 : 0),
            0
         );
         this.showSE(var51, var61, 0, 0);
      } else if (JRMCoreH.isPowerTypeChakra(pwr)) {
         int var16 = 0;
         int var17 = 25;
         short var18 = 48;
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         this.func_73729_b(var16 + JGConfigClientSettings.CLIENT_hud0x, var17 + JGConfigClientSettings.CLIENT_hud0y, 0, 86, var18, 85);
         if (var22 > 0.0F) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(var16 + JGConfigClientSettings.CLIENT_hud0x, var17 + 42 + JGConfigClientSettings.CLIENT_hud0y, 0, 42, var18, (int)var22 / 2 + 1);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(
               var16 + JGConfigClientSettings.CLIENT_hud0x,
               var17 + (42 - (int)var22 / 2) + JGConfigClientSettings.CLIENT_hud0y,
               0,
               42 - (int)var22 / 2,
               var18,
               (int)var22 / 2
            );
         }

         GL11.glDisable(3042);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         bc = "" + JRMCoreH.gkap(JRMCoreH.bpc(this.mc.field_71439_g, this.mc.field_71439_g.func_70005_c_(), JRMCoreH.Pwrtyp), "SL_ME");
         var8.func_78276_b(
            "SL " + bc + " §8" + JRMCoreH.DifficultySNmes[JRMCoreH.Dffclty],
            var16 + 5 + JGConfigClientSettings.CLIENT_hud0x,
            5 + JGConfigClientSettings.CLIENT_hud0y,
            0
         );
         String s = " §0" + this.getSmoothReleaseLevel() + "%";
         var8.func_78276_b(s, var18 / 2 - var8.func_78256_a(s) / 2 + JGConfigClientSettings.CLIENT_hud0x, 60 + JGConfigClientSettings.CLIENT_hud0y, 0);
         this.showSE(var16 + 45 + JGConfigClientSettings.CLIENT_hud0x, var17 - 20 + JGConfigClientSettings.CLIENT_hud0y, 0, 0);
      } else if (pwr == 3) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_73729_b(var51, var61, 0, 130, var21, 10);
         if (var22 > 0.0F) {
            int bc1 = 654591;
            if (JRMCoreH.align > 66) {
               bc1 = 654591;
            } else if (JRMCoreH.align <= 66 && JRMCoreH.align >= 33) {
               bc1 = 13478142;
            } else if (JRMCoreH.align < 33) {
               bc1 = 16522030;
            }

            int s = (JRMCoreH.Race == 1 || JRMCoreH.Race == 2) && JRMCoreH.State > 0 ? 16574610 : bc1;
            float h2 = (s >> 16 & 0xFF) / 255.0F;
            float h3 = (s >> 8 & 0xFF) / 255.0F;
            float h4 = (s & 0xFF) / 255.0F;
            float h1 = 1.0F;
            GL11.glColor4f(h1 * h2, h1 * h3, h1 * h4, 1.0F);
            this.func_73729_b(var51, var61, 0, 140, (int)var22, 10);
         }

         if (JRMCoreH.curRelease >= 0) {
            float var5 = var21 / 100.0F * this.getSmoothReleaseLevel();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(var51 - 3 + (int)var5, var61, 0, 150, 6, 10);
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         var8.func_78276_b(
            "BP " + bc + " §8" + JRMCoreH.DifficultySNmes[JRMCoreH.Dffclty] + " §0" + this.getSmoothReleaseLevel() + "%", var51 + 2, var61 + 2, 0
         );
         EntityPlayer var4x = this.mc.field_71439_g;
         ExtendedPlayer props = ExtendedPlayer.get(var4x);
         String block = props.getBlocking() == 1 ? "Blocking " : "";
         String fly = JRMCoreHDBC.DBCKiTechFly() ? "Flying " : "";
         String aura = JRMCoreH.StusEfctsMe(4) ? "Releasing " : "";
         String trbo = JRMCoreH.StusEfctsMe(3) ? "Turbo " : "";
         String kken = JRMCoreH.StusEfctsMe(5) ? "Kaioken " : "";
         var8.func_78276_b(block + fly + aura + trbo + kken, var51 + 2, var61 + 12, 0);
      }

      GL11.glDisable(3042);
   }

   public void showSE(int var51, int var61, int var71, int var81) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      var61 += JGConfigClientSettings.get_hud_y();
      FontRenderer var8 = this.mc.field_71466_p;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      String[] d0 = JRMCoreH.data(0, "0;0").split(";");
      String[] d18 = JRMCoreH.data(18, "0;0;0;0;0;0;0;0;0;0;0;0").split(";");
      String[] d20 = JRMCoreH.dataP(20, "0;0;0;0;0;0;0;0;0;0;0;0").split(";");
      int strn = Integer.parseInt(d20[4]);
      int strnAct = Integer.parseInt(d20[5]);
      int strnG = Integer.parseInt(d20[6]);
      int pn = JRMCoreH.pnp;
      int ko = Integer.parseInt(d20[9]);
      int GP = Integer.parseInt(d0[1]);
      String strnS = "(" + JRMCoreH.format_remTim(strn * 5) + ")";
      String strnAS = "(" + JRMCoreH.format_remTim(strnAct * 5) + ")";
      String strnGS = "(" + JRMCoreH.format_remTim(strnG * 5) + ")";
      String GPS = "(" + JRMCoreH.format_remTim(GP * 5) + ")";
      String pnS = "(" + JRMCoreH.format_remTim(pn * 5) + ")";
      String koS = "(" + JRMCoreH.format_remTim(ko * 5) + ")";
      String[] fuse = d18[2].split(",");
      int fusel0 = fuse.length == 3 ? Integer.parseInt(fuse[2]) : 0;
      String fusel = "(" + JRMCoreH.format_remTim(fusel0 * 5) + ")";
      fusel = fusel0 > 0 ? "Fused" + fusel + " " : "";
      int fuseo0 = fuse.length == 1 && !fuse[0].equals(" ") ? Integer.parseInt(fuse[0]) : 0;
      String fuzeo = "(" + JRMCoreH.format_remTim(fuseo0 * 5) + ")";
      fuzeo = fuseo0 > 0 ? "NoFuse" + fuzeo + " " : "";
      EntityPlayer var4x = this.mc.field_71439_g;
      ExtendedPlayer props = ExtendedPlayer.get(var4x);
      int resourceID = -1;
      boolean block = props.getBlocking() == 1;
      boolean instantTransmission = props.getBlocking() == 2;
      boolean fly = JRMCoreH.Pwrtyp == 1 ? JRMCoreHDBC.DBCKiTechFly() : false;
      boolean aura = JRMCoreH.StusEfctsMe(4);
      boolean trfm = JRMCoreH.StusEfctsMe(1);
      boolean trbo = JRMCoreH.StusEfctsMe(3);
      boolean kken = JRMCoreH.StusEfctsMe(5);
      boolean swop = JRMCoreH.StusEfctsMe(7);
      boolean flmn = JRMCoreH.StusEfctsMe(8);
      boolean majn = JRMCoreH.StusEfctsMe(12);
      boolean mstc = JRMCoreH.StusEfctsMe(13);
      boolean lgnd = JRMCoreH.StusEfctsMe(14);
      boolean vsna = JRMCoreH.StusEfctsMe(17);
      boolean ulin = JRMCoreH.StusEfctsMe(19);
      boolean god = JRMCoreH.StusEfctsMe(20);
      String gp = GP > 0 ? "GodPower" + GPS + " " : "";
      String strnd = strn > 0 ? "Strained" + strnS + " " : "";
      String strndA = strnAct > 0 ? "StrainIn" + strnAS + " " : "";
      String strndG = strnG > 0 ? "Fatigue" + strnGS + " " : "";
      String pnd = pn > 0 ? "Pain" + pnS + " " : "";
      String kod = ko > 0 ? "KO" + koS + " " : "";
      int[] f = new int[]{-1, 18, -1, 2, 2, 3, -1, 4, 7, -1, -1, -1, 9, 19, 10, 3, 11, 15, 16, 17, 23};
      this.mc.func_110434_K().func_110577_a(new ResourceLocation(this.getIcons()));
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      int i = 0;
      int j = 0;
      int rgb = get_Property(6, 4);
      rgb = (rgb << 8) + get_Property(6, 5);
      rgb = (rgb << 8) + get_Property(6, 6);
      if (JRMCoreH.pwr_cha(JRMCoreH.Pwrtyp) && JRMCoreH.Class == 2 && JRMCoreH.State > 0) {
         this.drawIcon(var51 + i, var61 + j, 13);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (JRMCoreH.pwr_cha(JRMCoreH.Pwrtyp) && JRMCoreH.Class == 1 && JRMCoreH.State > 0) {
         this.drawIcon(var51 + i, var61 + j, 12);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         if (var71 == 0) {
            if (var71 == 0) {
               i += 18;
            } else {
               j += 18;
            }
         } else {
            j += 18;
         }
      }

      if (JRMCoreH.StusEfctsMe(16)) {
         this.drawIcon(var51 + i, var61 + j, 11);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (vsna) {
         this.drawIcon(var51 + i, var61 + j, f[17]);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (lgnd) {
         this.drawIcon(var51 + i, var61 + j, 10);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (majn) {
         this.drawIcon(var51 + i, var61 + j, 9);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (mstc) {
         this.drawIcon(var51 + i, var61 + j, f[13]);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (ulin) {
         this.drawIcon(var51 + i, var61 + j, f[19]);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (god) {
         this.drawIcon(var51 + i, var61 + j, f[20]);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (block) {
         this.drawIcon(var51 + i, var61 + j, 0);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (fly) {
         this.drawIcon(var51 + i, var61 + j, 1);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (aura) {
         this.drawIcon(var51 + i, var61 + j, 2);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (trbo) {
         this.drawIcon(var51 + i, var61 + j, 2);
         this.drawIcon(var51 + i, var61 + j, 128);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (kken) {
         this.drawIcon(var51 + i, var61 + j, 3);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (trfm) {
         this.drawIcon(var51 + i, var61 + j, f[1]);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (JRMCoreH.Pwrtyp == 2 && JRMCoreH.State2 > 0) {
         this.drawIcon(var51 + i, var61 + j, 3);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (swop) {
         this.drawIcon(var51 + i, var61 + j, 4);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (flmn) {
         this.drawIcon(var51 + i, var61 + j, 7);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }
      }

      if (kod.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 20);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            kod,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(kod);
         }
      }

      if (gp.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 8);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            gp,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(gp);
         }
      }

      if (strnd.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 6);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            strnd,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(strnd);
         }
      }

      if (pnd.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, f[18]);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            pnd,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 2,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(pnd);
         }
      }

      if (strndA.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 5);
         this.drawIcon(var51 + i, var61 + j, 5, 1.0F, 0.5F, 0.5F, 30.0F + (24 - (strnAct > 24 ? 24 : strnAct)) * 2.92F);
         this.drawIcon(var51 + i, var61 + j, 129);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            strndA,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(strndA);
         }
      }

      if (strndG.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 8);
         this.drawIcon(var51 + i, var61 + j, 130);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            strndG,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(strndG);
         }
      }

      if (fuzeo.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 21);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            fuzeo,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(fuzeo);
         }
      }

      if (fusel.length() > 1) {
         this.drawIcon(var51 + i, var61 + j, 21);
         if (var71 == 0) {
            i += 18;
         } else {
            j += 18;
         }

         this.drawString2(
            var8,
            fusel,
            var51 + 2 + i + (!JGConfigClientSettings.CLIENT_hud2 && JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0) + (var71 == 1 ? 19 : 0),
            var61 + j + 12,
            var81,
            false,
            JGConfigClientSettings.CLIENT_hud2 ? rgb : 0
         );
         if (var71 == 0) {
            i += var8.func_78256_a(fusel);
         }
      }

      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      int var65 = 0;
      if (this.isInstantTransmissionOn() || instantTransmission) {
         int xDiff = 16;
         int yDiff = 8;
         this.drawIcon3rdFn(var6 / 2 + var65 - 9 + xDiff, var7 / 2 - 9 + yDiff);
         if (this.getInstantTransmissionPress() != null) {
            long timer = Duration.between(this.getInstantTransmissionPress(), Instant.now()).getSeconds();
            JRMCoreGuiScreen.drawStringWithBorder(var8, timer + "s", var6 / 2 - 5 + xDiff, var7 / 2 - 20 + yDiff, 16777215);
         }

         if (this.getInstantTransmissionPress() == null) {
            this.drawIcon(var6 / 2 + var65 - 9 + xDiff, var7 / 2 - 9 + yDiff, 130);
         }
      } else if (block) {
         this.drawIconB(var6 / 2 + var65 - 9, var7 / 2 - 9, 14);
         if (var71 == 0) {
            var65 += 18;
         } else {
            j += 18;
         }
      }

      GL11.glPopMatrix();
   }

   private boolean isInstantTransmissionOn() {
      return JRMCoreH.DBC() ? DBCClientTickHandler.instantTransmissionOn : false;
   }

   private Instant getInstantTransmissionPress() {
      return JRMCoreH.DBC() ? DBCClientTickHandler.instantTransmissionPress : null;
   }

   public void renderTrainGui() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      ResourceLocation tx = new ResourceLocation("jinryuumodscore:gui.png");
      this.mc.field_71446_o.func_110577_a(tx);
      int xSize = 140;
      int ySize = 72;
      int guiLeft = var6 - xSize - 5;
      int guiTop = 5;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
      this.func_73729_b(guiLeft, guiTop, 0, 159, xSize, ySize);
      String bc = "" + JRMCoreHC.BPC_ME;
      if (bc.contains("Data")) {
         bc = "0";
      }

      long s = Long.parseLong(bc);
      int train = 0;
      if (JRMCoreH.dat20 != null) {
         train = JRMCoreH.GTrnngCB;
      }

      if (JRMCoreH.DBC()) {
         int stlhv = DBCConfig.maxTrnExp - train;
         if (stlhv < 0) {
            stlhv = 0;
         }

         var8.func_78276_b("§8Training Info", guiLeft + 5, guiTop + 5, 0);
         if (stlhv <= 0) {
            int var221 = JRMCoreH.txt("You reached the servers training limit: " + DBCConfig.maxTrnExp, "§0", 0, true, guiLeft + 5, guiTop + 15, xSize - 4);
         } else {
            short var21 = JGConfigClientSettings.get_hud_x();
            int xSize1 = var21;
            float maxperc = xSize1 * 0.01F;
            int d = JRMCoreH.Dffclty;
            float p = JRMCoreH.Perc(d);
            float explmt = 10.0F * p;
            int var22 = (int)(maxperc * (100.0F * JRMCoreH.curExp / explmt));
            if (var22 > xSize1) {
               ;
            }

            var8.func_78276_b(
               "TP: "
                  + (
                     JRMCoreH.curTP < 100
                        ? "§2" + JRMCoreH.numSep(JRMCoreH.curTP) + ", " + ((int)explmt - JRMCoreH.curExp) + " Exp till TP gain"
                        : "§4" + JRMCoreH.numSep(JRMCoreH.curTP) + ", be below 100"
                  ),
               guiLeft + 5,
               guiTop + 15,
               0
            );
            var8.func_78276_b("§0Health & Energy:", guiLeft + 5, guiTop + 25, 0);
            var8.func_78276_b(
               ""
                  + (
                     this.getClientEnergy() > this.getClientMaxEnergy() / 2 && this.getClientHealth() > this.getClientMaxHealth() / 2
                        ? "§2OK"
                        : "§4not OK! Be above 50%"
                  ),
               guiLeft + 5,
               guiTop + 35,
               0
            );
            var8.func_78276_b("Can still gain " + stlhv + " Exp", guiLeft + 5, guiTop + 45, 0);
            var8.func_78276_b("" + (DBCH.HTCtrain && DBCH.mvng() ? "§2Conditions are met" : "§4Conditions are not met!"), guiLeft + 5, guiTop + 55, 0);
            if (DBCH.cbge > 0) {
               DBCH.cbge--;
            }

            if (DBCH.cbge > 0) {
               var8.func_78276_b(
                  "You have gained Exp!",
                  guiLeft + 5 + (DBCH.cbge < 100 ? (int)((100 - DBCH.cbge) * 1.5) : (DBCH.cbge > 450 ? (DBCH.cbge - 450) * 3 : 0)),
                  guiTop + 72,
                  0
               );
            } else if (DBCH.HTCtrain) {
               var8.func_78276_b((s / 1000L - DBCClientTickHandler.c) / 20L + 1L + " secs till Exp gain", guiLeft + 5, guiTop + 72, 0);
            }
         }

         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      }
   }

   public void renderEnChrgBar() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int scaleW = var5.func_78326_a();
      int scaleH = var5.func_78328_b();
      FontRenderer fontRenderer = this.mc.field_71466_p;
      ResourceLocation tx = new ResourceLocation("jinryuumodscore:icons.png");
      this.mc.field_71446_o.func_110577_a(tx);
      short charged = JRMCoreH.charged;
      byte selected = JRMCoreH.EnAtSlct;
      float charge = JRMCoreH.techDBCctWc(JRMCoreH.tech(selected), JRMCoreH.tech_statmods(JRMCoreH.tech(selected)[19]));
      int posX = scaleW / 2 - 30;
      int posY = scaleH / 2 + 60;
      short width = 60;
      float chargeMax = width * 1.0F / charge;
      float chargeWidth = chargeMax * charged;
      if (chargeWidth > width) {
         chargeWidth = width;
      }

      float overchargeWidth = chargeMax * (charged - charge);
      if (overchargeWidth > width) {
         overchargeWidth = width;
      }

      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      this.field_73735_i = -90.0F;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.func_73729_b(posX, posY, 0, 0, width, 10);
      if (chargeWidth > 0.0F) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_73729_b(posX, posY, 0, 10, (int)chargeWidth, 10);
      }

      if (overchargeWidth > 0.0F) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         this.func_73729_b(posX, posY, 60, 10, (int)overchargeWidth, 10);
      }

      if (JGConfigClientSettings.kiAttackChargePercentageOn) {
         String percentage = JRMCoreH.chrgPrc + "%";
         int textWidth = fontRenderer.func_78256_a(percentage);
         JRMCoreGuiScreen.drawStringWithBorder(fontRenderer, percentage, posX + 30 - textWidth / 2, posY + 2 - 2, 8388564);
      }

      boolean isFused = JRMCoreH.isFused();
      int WILo = JRMCoreH.PlyrAttrbts[3];
      int WIL = JRMCoreH.getPlayerAttribute(
         JRMCoreClient.mc.field_71439_g,
         JRMCoreH.PlyrAttrbts,
         3,
         JRMCoreH.State,
         JRMCoreH.State2,
         JRMCoreH.Race,
         JRMCoreH.PlyrSkillX,
         JRMCoreH.curRelease,
         JRMCoreH.getArcRsrv(),
         JRMCoreH.StusEfctsMe(14),
         JRMCoreH.StusEfctsMe(12),
         JRMCoreH.StusEfctsMe(5),
         JRMCoreH.StusEfctsMe(13),
         JRMCoreH.StusEfctsMe(19),
         JRMCoreH.StusEfctsMe(20),
         1,
         JRMCoreH.PlyrSkills,
         isFused,
         JRMCoreH.getMajinAbsorption()
      );
      int WIL2 = JRMCoreH.getPlayerAttribute(
         JRMCoreClient.mc.field_71439_g,
         JRMCoreH.PlyrAttrbts,
         3,
         0,
         0,
         JRMCoreH.Race,
         JRMCoreH.PlyrSkillX,
         JRMCoreH.curRelease,
         JRMCoreH.getArcRsrv(),
         JRMCoreH.StusEfctsMe(14),
         JRMCoreH.StusEfctsMe(12),
         false,
         false,
         false,
         false,
         1,
         JRMCoreH.PlyrSkills,
         isFused,
         JRMCoreH.getMajinAbsorption()
      );
      int stat = JRMCoreH.stat(this.mc.field_71439_g, 3, JRMCoreH.Pwrtyp, 4, WIL, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
      int stat2 = JRMCoreH.stat(this.mc.field_71439_g, 3, JRMCoreH.Pwrtyp, 4, WIL2, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
      boolean fakeMoon = JRMCoreH.tech(selected)[0].equals("KAFakeMoon");
      if (charged > JRMCoreH.techDBCctWc(JRMCoreH.tech(selected), JRMCoreH.tech_statmods(JRMCoreH.tech(selected)[19])) / 2.0F && !fakeMoon) {
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         int damage = (int)(
            (double)JRMCoreH.techDBCdmg(JRMCoreH.tech(selected), stat, JRMCoreH.tech_statmods(JRMCoreH.tech(selected)[19]))
               * JRMCoreH.chrgPrc
               * 0.02F
               * JRMCoreH.curRelease
               * 0.01F
               * JRMCoreConfig.dat5696[Integer.parseInt(JRMCoreH.tech(selected)[3])][1]
         );
         String potDam = "" + JRMCoreH.numSep(damage) + " (DMG)";
         int textWidth = fontRenderer.func_78256_a(potDam);
         JRMCoreGuiScreen.drawStringWithBorder(fontRenderer, potDam, posX + 30 - textWidth / 2, posY - 13 + 2 - 3, JRMCoreH.techCol[8]);
      }

      float costKi = (float)(
         (double)JRMCoreH.techDBCkic(JRMCoreH.tech(selected), stat2, JRMCoreH.tech_statmods(JRMCoreH.tech(selected)[19]))
            * JRMCoreH.chrgPrc
            * 0.02F
            * JRMCoreH.curRelease
            * 0.01F
      );
      if (fakeMoon) {
         int kacost = Integer.parseInt(JRMCoreH.tech(selected)[7]);
         costKi = kacost * JRMCoreH.chrgPrc * 0.04F;
         costKi = costKi > kacost ? kacost : costKi;
      }

      costKi *= (float)JRMCoreConfig.dat5696[Integer.parseInt(JRMCoreH.tech(selected)[3])][2];
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      String costki = "" + JRMCoreH.numSep((int)costKi) + " (KI)";
      int textWidth = fontRenderer.func_78256_a(costki);
      JRMCoreGuiScreen.drawStringWithBorder(fontRenderer, costki, posX + 30 - textWidth / 2, posY + 13 + 2, 8388564);
      GL11.glDisable(3042);
   }

   public void renderRageBar() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      ResourceLocation tx = new ResourceLocation("jinryuumodscore:icons.png");
      this.mc.field_71446_o.func_110577_a(tx);
      short stt = JRMCoreH.TransSaiTre[JRMCoreH.State];
      if (stt < 4) {
         int var51 = JRMCoreHDBC.DBCgetConfigcwfb() + 10;
         int var61 = JRMCoreHDBC.DBCgetConfigchfb() + 20;
         short var21 = JGConfigClientSettings.get_hud_x();
         short width = var21;
         float maxperc = width * 1.0F / 100.0F;
         float var22 = maxperc * JRMCoreH.TransSaiCurRg;
         if (var22 > width) {
            var22 = width;
         }

         byte rc = JRMCoreH.Race;
         if (var22 > 0.0F) {
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBlendFunc(775, 769);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.func_73729_b(
               var51 + JGConfigClientSettings.CLIENT_hud0x + (JGConfigClientSettings.CLIENT_hud0 > 1 ? 51 : 0),
               var61 - 10 + JGConfigClientSettings.CLIENT_hud0y + (JGConfigClientSettings.CLIENT_hud0 > 1 ? 5 : 0),
               0,
               141,
               (int)var22,
               2
            );
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
         }
      }
   }

   public void rendern() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      this.mc.field_71460_t.func_78478_c();
      float val = JRMCoreH.curn;
      int var51 = var6 - 24;
      int var61 = var7 / 2 + 33;
      int h = 41;
      float mp = h / 100.0F;
      float var2 = mp * val;
      if (var2 > h) {
         var2 = h;
      }

      this.field_73735_i = -90.0F;
      ResourceLocation tx2 = new ResourceLocation("jinryuumodscore:icons.png");
      this.mc.field_71446_o.func_110577_a(tx2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      this.func_73729_b(var51, var61, 121, 80, 11, h);
      if (var2 > 0.0F) {
         this.func_73729_b(var51, (int)(var61 + h - var2), 132, (int)(80 + h - var2), 11, (int)var2);
      }

      GL11.glDisable(3042);
   }

   public void rendera() {
      if (JGConfigUltraInstinct.CONFIG_UI_LEVELS > 0) {
         ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
         int var6 = var5.func_78326_a();
         int var7 = var5.func_78328_b();
         FontRenderer var8 = this.mc.field_71466_p;
         ResourceLocation tx = new ResourceLocation("jinryuumodscore:icons.png");
         this.mc.field_71446_o.func_110577_a(tx);
         int w = 180;
         int charged_percentage = (int)JRMCoreH.getHeatPercentageClient();
         int var51 = var6 / 2 - w / 2;
         int var61 = var7 - 70;
         float maxperc = w / 100.0F;
         float var22 = maxperc * charged_percentage;
         if (var22 > w) {
            var22 = w;
         }

         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         this.field_73735_i = -90.0F;
         float colorFlash = 0.2F;
         float r;
         float g;
         float b;
         if (charged_percentage == 100) {
            r = 0.8F;
            g = 0.8F;
            b = 0.8F;
         } else if (JRMCoreH.align < 33) {
            r = 1.0F;
            g = 0.3F;
            b = 0.3F;
         } else if (JRMCoreH.align < 67) {
            r = 0.9F;
            g = 0.45F;
            b = 1.0F;
         } else {
            r = 0.45F;
            g = 0.9F;
            b = 1.0F;
         }

         int playerTick = JRMCoreClient.mc.field_71439_g != null ? JRMCoreClient.mc.field_71439_g.field_70173_aa : 0;
         float tick = MathHelper.func_76126_a(playerTick * 0.1F) * 0.2F;
         if (tick < 0.0F) {
            tick *= -1.0F;
         }

         if (tick > 0.2F) {
            tick = 0.2F;
         }

         r += tick;
         if (r > 1.0F) {
            r = 1.0F;
         }

         g += tick;
         if (g > 1.0F) {
            g = 1.0F;
         }

         b += tick;
         if (b > 1.0F) {
            b = 1.0F;
         }

         GL11.glColor4f(r, g, b, 1.0F);
         this.func_73729_b(var51, var61, 0, 64, w + 2, 5);
         if (var22 > 0.0F) {
            float x = JRMCoreH.round((float)(System.currentTimeMillis() / 100L % 3L) / 3.0F * var22, 2);
            tick = var22 - x;
            GL11.glColor4f(r, g, b, 1.0F);
            this.func_73729_b(var51 + 1, var61, (int)(1.0F + x), 69, (int)tick, 5);
            this.func_73729_b((int)(var51 + 1 + tick), var61, 1, 69, (int)x, 5);
         }

         GL11.glDisable(3042);
      }
   }

   public void renderEnSideBar() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      String ic = "jinryuumodscore:icons5.png";
      String ic2 = "jinryuudragonbc:icons3.png";
      int xSize = 11;
      int ySize = 40;
      int guiLeft = var6 - (xSize + 2);
      int guiTop = (var7 - ySize) / 2;
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      ResourceLocation tx = new ResourceLocation(ic);
      ResourceLocation tx2 = new ResourceLocation(ic2);
      this.mc.field_71446_o.func_110577_a(tx);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_73735_i = -90.0F;
      this.func_73729_b(guiLeft, guiTop, 245, 0, xSize, ySize + 1);
      if (JRMCoreH.mrAtts) {
         this.func_73729_b(guiLeft, guiTop - ySize, 245, 0, xSize, ySize);
      }

      guiTop -= JRMCoreH.mrAtts ? ySize : 0;
      JRMCoreH.mrAtts = false;

      for (byte i = 0; i < 8; i++) {
         if (JRMCoreH.tech(i) != null) {
            if (i > 3) {
               JRMCoreH.mrAtts = true;
               this.mc.field_71446_o.func_110577_a(tx2);
            } else {
               this.mc.field_71446_o.func_110577_a(tx);
            }

            String type = JRMCoreH.tech(i)[3];
            String dam = JRMCoreH.tech(i)[5];
            String effect = JRMCoreH.tech(i)[6];
            String cd = JRMCoreH.tech(i)[9];
            String color = JRMCoreH.tech(i)[10];
            String den = JRMCoreH.tech(i)[11];
            int size = (int)(Integer.parseInt(dam) * 0.1F / Integer.parseInt(den));
            int var28 = 9;
            int var29 = 9;
            guiLeft = var6 - (var28 + 3);
            if (effect.contains("1")) {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               this.func_73729_b(guiLeft, guiTop + 1 + i * 10, 178, 0, var28, var29);
            }

            int col = Integer.parseInt(color);
            if (col == 0 && JRMCoreH.align > 66) {
               col = 2;
            }

            if (col == 0 && JRMCoreH.align <= 66 && JRMCoreH.align >= 33) {
               col = 3;
            }

            if (col == 0 && JRMCoreH.align < 33) {
               col = 4;
            }

            col = JRMCoreH.techCol[col];
            float h2 = (col >> 16 & 0xFF) / 255.0F;
            float h3 = (col >> 8 & 0xFF) / 255.0F;
            float h4 = (col & 0xFF) / 255.0F;
            if (JRMCoreH.mrAtts) {
               int ikon = Integer.parseInt(JRMCoreH.tech(i)[15]) - 1;
               int ikonH = ikon / 16;
               int ikonV = ikon - ikonH * 16;
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               this.func_73729_b(guiLeft, guiTop + 1 + i * 10, ikonV * var28, ikonH * var29, var28, var29);
            } else {
               GL11.glColor4f(h2, h3, h4, 1.0F);
               this.func_73729_b(guiLeft, guiTop + 1 + i * 10, 187 + size / 5 * var28, Integer.parseInt(type) * var29, var28, var29);
            }

            if (JRMCoreH.techCD(i) != 0.0F) {
               var8.func_78276_b(JRMCoreH.round(JRMCoreH.techCD(i) * 0.1F, 1) + "", guiLeft - 25, guiTop + 1 + i * 10, 0);
            }
         }
      }

      if (JRMCoreH.EnAtSlct > (JRMCoreH.mrAtts ? 7 : 3)) {
         JRMCoreH.EnAtSlct = 0;
      }

      this.mc.field_71446_o.func_110577_a(tx);
      byte selc = JRMCoreH.EnAtSlct;
      int x = 13;
      int y = 13;
      int Left = var6 - (x + 1);
      int Top = guiTop - 1;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.func_73729_b(Left, selc * 10 + Top, 232, 0, x, y);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public void renderEnSideBarNC() {
      ScaledResolution var5 = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = this.mc.field_71466_p;
      String ic2 = JRMCoreH.tjnc + ":icons2.png";
      int xSize = 11;
      int ySize = 40;
      int guiLeft = var6 - (xSize + 2);
      int guiTop = (var7 - ySize) / 2;
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      ResourceLocation tx2 = new ResourceLocation(ic2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_73735_i = -90.0F;
      this.mc.field_71446_o.func_110577_a(tx2);
      int var14 = 16;
      int var15 = 16;
      guiLeft = var6 - (var14 + 3);
      int ikon = JRMCoreHNC.iconInList();
      int ikonH = ikon / 16;
      int ikonV = ikon - ikonH * 16;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.func_73729_b(guiLeft, guiTop + 1 + 10, ikonV * var14, ikonH * var15, var14, var15);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }
}
