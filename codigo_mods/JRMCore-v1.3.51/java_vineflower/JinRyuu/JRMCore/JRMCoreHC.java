package JinRyuu.JRMCore;

import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.p.JRMCorePTri;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerClient;
import com.google.common.collect.Maps;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ReportedException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class JRMCoreHC {
   public static boolean t1s = false;
   public static boolean t2s = false;
   public static boolean t5s = false;
   public static long BPC_ME = 1L;
   public static long BPC_ME2 = 1L;
   public static float smoothReleaseLevel = 0.0F;
   private static Map mto = Maps.newHashMap();
   public static Pattern paturl = Pattern.compile(
      "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", 42
   );
   static int atv = 16;
   static int attackTime = atv;
   public static HashMap<String, String> kbx97f = new HashMap<>();

   public static void bt(String p_110577_1_) {
      Object object = (ITextureObject)mto.get(p_110577_1_);
      if (object == null) {
         object = new JRMCTexture(p_110577_1_);
         lt(p_110577_1_, (ITextureObject)object);
      }

      bt(((ITextureObject)object).func_110552_b());
   }

   static void bt(int p_94277_0_) {
      GL11.glBindTexture(3553, p_94277_0_);
   }

   public static boolean lt(String p_110579_1_, final ITextureObject p_110579_2_) {
      boolean flag = true;
      ITextureObject p_110579_2_2 = p_110579_2_;

      try {
         p_110579_2_.func_110551_a(JRMCoreClient.mc.func_110442_L());
      } catch (IOException ioexception) {
         mod_JRMCore.logger.error("Failed to load texture: " + p_110579_1_);
         p_110579_2_2 = TextureUtil.field_111001_a;
         mto.put(p_110579_1_, p_110579_2_2);
         flag = false;
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Registering texture");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Resource location being registered");
         crashreportcategory.func_71507_a("Resource location", p_110579_1_);
         crashreportcategory.func_71500_a("Texture object class", new Callable() {
            private static final String __OBFID = "CL_00001065";

            public String call() {
               return p_110579_2_.getClass().getName();
            }
         });
         throw new ReportedException(crashreport);
      }

      mto.put(p_110579_1_, p_110579_2_2);
      return flag;
   }

   public static void dtm(float x, float y, int u, int v, float width, float height, float z) {
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78374_a(x, y + 0.0F, z, (u + 0) * f, (v + 0) * f1);
      tessellator.func_78374_a(x, y + height, z, (u + 0) * f, (v + height) * f1);
      tessellator.func_78374_a(x + width, y + height, z, (u + width) * f, (v + height) * f1);
      tessellator.func_78374_a(x + width, y + 0.0F, z, (u + width) * f, (v + 0) * f1);
      tessellator.func_78381_a();
   }

   public static void dtr(float x, float y, int u, int v, float width, float height, float z) {
      float f = 1.0F / width;
      float f1 = 1.0F / height;
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78374_a(x, y + 0.0F, z, (u + 0) * f, (v + 0) * f1);
      tessellator.func_78374_a(x, y + height, z, (u + 0) * f, (v + height) * f1);
      tessellator.func_78374_a(x + width, y + height, z, (u + width) * f, (v + height) * f1);
      tessellator.func_78374_a(x + width, y + 0.0F, z, (u + width) * f, (v + 0) * f1);
      tessellator.func_78381_a();
   }

   public static void Blocking() {
      if (JRMCoreClient.mc.field_71474_y.field_74312_F.func_151470_d()) {
         attackTime = 0;
      } else if (attackTime < atv) {
         attackTime++;
      }

      EntityPlayer var4 = JRMCoreClient.mc.field_71439_g;
      ItemStack var11 = var4.field_71071_by.func_70448_g();
      ExtendedPlayer props = ExtendedPlayer.get(var4);
      int mode = JRMCoreH.DBC() ? DBCPacketHandlerClient.getDBCPlayerBlockMode() : 1;
      boolean b = !JRMCoreKeyHandler.Fn.func_151470_d() && JRMCoreClient.mc.field_71474_y.field_74313_G.func_151470_d() || mode == 2;
      if (b && props.getBlocking() != mode && attackTime >= atv && var11 == null) {
         triForce(2, mode, 0);
         props.setBlocking(mode);
      } else if ((!b || attackTime < atv) && props.getBlocking() != 0) {
         triForce(2, 0, 0);
         props.setBlocking(0);
      }
   }

   public static void triForce(int i, int j, int k) {
      PD.sendToServer(new JRMCorePTri((byte)i, (byte)j, (byte)k));
   }

   public static String getKey(int i) {
      String k = "";
      if (i >= 0) {
         k = Keyboard.getKeyName(i);
         if (k != null) {
            return k;
         }
      } else if (i >= -100) {
         k = Mouse.getButtonName(100 + i);
         if (k != null) {
            return "BUTTON " + (Integer.parseInt(k.replaceFirst("BUTTON", "")) + 1);
         }
      }

      return "keycode" + i;
   }

   public void ay2MmU(String c) {
      Thread one = new Thread() {
         @Override
         public void run() {
            String rvf = "http://updateinfo.jingames.net/getLatestPost.php";
            String line = null;
            StringBuilder content = new StringBuilder();
            int empty = 0;
            int count = 0;

            try {
               URL url = new URL("http://updateinfo.jingames.net/getLatestPost.php");
               InputStreamReader isr = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8);
               BufferedReader reader = new BufferedReader(isr);

               while ((line = reader.readLine()) != null) {
                  if (line.length() > 3) {
                     content.append(line.replaceAll(" ", " ") + "/n");
                     count++;
                     empty = 0;
                  } else {
                     if (empty < 1) {
                        content.append(line.replaceAll(" ", " ") + "/n");
                        count++;
                     }

                     empty++;
                  }

                  if (count > 18) {
                     break;
                  }
               }

               Version.news = content.toString();
               reader.close();
               isr.close();
            } catch (Exception e) {
               e.printStackTrace(System.err);
            }

            this.interrupt();
         }
      };
      one.start();
   }

   public static void ri(
      Tessellator p_78439_0_, float p_78439_1_, float p_78439_2_, float p_78439_3_, float p_78439_4_, int p_78439_5_, int p_78439_6_, float p_78439_7_
   ) {
      p_78439_0_.func_78382_b();
      p_78439_0_.func_78375_b(0.0F, 0.0F, 1.0F);
      p_78439_0_.func_78374_a(0.0, 0.0, 0.0, p_78439_1_, p_78439_4_);
      p_78439_0_.func_78374_a(1.0, 0.0, 0.0, p_78439_3_, p_78439_4_);
      p_78439_0_.func_78374_a(1.0, 1.0, 0.0, p_78439_3_, p_78439_2_);
      p_78439_0_.func_78374_a(0.0, 1.0, 0.0, p_78439_1_, p_78439_2_);
      p_78439_0_.func_78381_a();
      p_78439_0_.func_78382_b();
      p_78439_0_.func_78375_b(0.0F, 0.0F, -1.0F);
      p_78439_0_.func_78374_a(0.0, 1.0, 0.0F - p_78439_7_, p_78439_1_, p_78439_2_);
      p_78439_0_.func_78374_a(1.0, 1.0, 0.0F - p_78439_7_, p_78439_3_, p_78439_2_);
      p_78439_0_.func_78374_a(1.0, 0.0, 0.0F - p_78439_7_, p_78439_3_, p_78439_4_);
      p_78439_0_.func_78374_a(0.0, 0.0, 0.0F - p_78439_7_, p_78439_1_, p_78439_4_);
      p_78439_0_.func_78381_a();
      float f5 = 0.5F * (p_78439_1_ - p_78439_3_) / p_78439_5_;
      float f6 = 0.5F * (p_78439_4_ - p_78439_2_) / p_78439_6_;
      p_78439_0_.func_78382_b();
      p_78439_0_.func_78375_b(-1.0F, 0.0F, 0.0F);

      for (int k = 0; k < p_78439_5_; k++) {
         float f7 = (float)k / p_78439_5_;
         float f8 = p_78439_1_ + (p_78439_3_ - p_78439_1_) * f7 - f5;
         p_78439_0_.func_78374_a(f7, 0.0, 0.0F - p_78439_7_, f8, p_78439_4_);
         p_78439_0_.func_78374_a(f7, 0.0, 0.0, f8, p_78439_4_);
         p_78439_0_.func_78374_a(f7, 1.0, 0.0, f8, p_78439_2_);
         p_78439_0_.func_78374_a(f7, 1.0, 0.0F - p_78439_7_, f8, p_78439_2_);
      }

      p_78439_0_.func_78381_a();
      p_78439_0_.func_78382_b();
      p_78439_0_.func_78375_b(1.0F, 0.0F, 0.0F);

      for (int var14 = 0; var14 < p_78439_5_; var14++) {
         float f7 = (float)var14 / p_78439_5_;
         float f8 = p_78439_1_ + (p_78439_3_ - p_78439_1_) * f7 - f5;
         float f9 = f7 + 1.0F / p_78439_5_;
         p_78439_0_.func_78374_a(f9, 1.0, 0.0F - p_78439_7_, f8, p_78439_2_);
         p_78439_0_.func_78374_a(f9, 1.0, 0.0, f8, p_78439_2_);
         p_78439_0_.func_78374_a(f9, 0.0, 0.0, f8, p_78439_4_);
         p_78439_0_.func_78374_a(f9, 0.0, 0.0F - p_78439_7_, f8, p_78439_4_);
      }

      p_78439_0_.func_78381_a();
      p_78439_0_.func_78382_b();
      p_78439_0_.func_78375_b(0.0F, 1.0F, 0.0F);

      for (int var15 = 0; var15 < p_78439_6_; var15++) {
         float f7 = (float)var15 / p_78439_6_;
         float f8 = p_78439_4_ + (p_78439_2_ - p_78439_4_) * f7 - f6;
         float f9 = f7 + 1.0F / p_78439_6_;
         p_78439_0_.func_78374_a(0.0, f9, 0.0, p_78439_1_, f8);
         p_78439_0_.func_78374_a(1.0, f9, 0.0, p_78439_3_, f8);
         p_78439_0_.func_78374_a(1.0, f9, 0.0F - p_78439_7_, p_78439_3_, f8);
         p_78439_0_.func_78374_a(0.0, f9, 0.0F - p_78439_7_, p_78439_1_, f8);
      }

      p_78439_0_.func_78381_a();
      p_78439_0_.func_78382_b();
      p_78439_0_.func_78375_b(0.0F, -1.0F, 0.0F);

      for (int var16 = 0; var16 < p_78439_6_; var16++) {
         float f7 = (float)var16 / p_78439_6_;
         float f8 = p_78439_4_ + (p_78439_2_ - p_78439_4_) * f7 - f6;
         p_78439_0_.func_78374_a(1.0, f7, 0.0, p_78439_3_, f8);
         p_78439_0_.func_78374_a(0.0, f7, 0.0, p_78439_1_, f8);
         p_78439_0_.func_78374_a(0.0, f7, 0.0F - p_78439_7_, p_78439_1_, f8);
         p_78439_0_.func_78374_a(1.0, f7, 0.0F - p_78439_7_, p_78439_3_, f8);
      }

      p_78439_0_.func_78381_a();
   }
}
