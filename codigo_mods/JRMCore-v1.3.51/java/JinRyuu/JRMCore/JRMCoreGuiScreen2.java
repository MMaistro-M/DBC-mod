package JinRyuu.JRMCore;

import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiScreen2 extends GuiScreen implements ClipboardOwner, GuiYesNoCallback {
   private static final Logger logger = LogManager.getLogger();
   public static Minecraft mc = Minecraft.func_71410_x();
   public static JRMCoreGuiScreen2 instance;
   public int guiID = 0;
   public int guiIDprev = 0;
   public int guiIDprev2 = 0;
   public int guiLeft;
   public int guiTop;
   public int xSize = 256;
   public int ySize = 159;
   public float xSize_lo;
   public float ySize_lo;
   public static String button1 = JRMCoreH.tjjrmc + ":button1.png";
   public static String wish = JRMCoreH.tjjrmc + ":gui.png";
   public static String icons = JRMCoreH.tjjrmc + ":icons.png";
   protected static List detailList = new ArrayList();
   private int y = 0;
   private int x = 0;
   private byte pwr = 0;

   public JRMCoreGuiScreen2(int w) {
      this.guiID = w;
      this.guiIDprev = this.guiID;
   }

   public void func_73866_w_() {
      wish = JRMCoreH.tjjrmc + ":gui.png";
      button1 = JRMCoreH.tjjrmc + ":button1.png";
      icons = JRMCoreH.tjjrmc + ":icons.png";
      instance = this;
      this.field_146292_n.clear();
      this.guiLeft = (this.field_146294_l - this.xSize) / 2;
      this.guiTop = (this.field_146295_m - this.ySize) / 2;
      int posX = this.field_146294_l / 2;
      int posY = this.field_146295_m / 2;
      if (JRMCoreH.Accepted == 0) {
         this.guiID = 0;
      }
   }

   public void func_146284_a(GuiButton button) {
   }

   public void func_73863_a(int x, int y, float f) {
      this.x = x;
      this.y = y;
      this.xSize_lo = x;
      this.ySize_lo = y;
      ScaledResolution var5 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
      int var6 = var5.func_78326_a();
      int var7 = var5.func_78328_b();
      FontRenderer var8 = mc.field_71466_p;
      this.field_146292_n.clear();
      this.guiLeft = (this.field_146294_l - this.xSize) / 2;
      this.guiTop = (this.field_146295_m - this.ySize) / 2;
      super.func_73863_a(x, y, f);
      this.drawDetails(x, y, var8);
   }

   public void menuButtons(String st) {
      menuButtons(st, this.field_146292_n, this.guiLeft, this.guiTop, this.ySize, this.x, this.y, this.field_146289_q);
   }

   public static void menuButtons(String st, List buttonList, int guiLeft, int guiTop, int ySize, int x, int y, FontRenderer fontRendererObj) {
      int i = 0;
      buttonList.add(new JRMCoreGuiButtons00(10, guiLeft - 22, guiTop + 145, 20, 20, "X", 0));
      String name = "";
      if (!JRMCoreConfig.ssurl.contains("empty") && JRMCoreConfig.ssurl.contains("ttp")) {
         name = "Server Shop";
         buttonList.add(
            new JRMCoreGuiButtons02(3099, guiLeft + i * 21, guiTop + ySize + 2, "$", st.equals("ST") ? 1 : 0, Color.GREEN.darker().darker().getRGB())
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
      }

      name = JRMCoreH.trl("jrmc", "News");
      buttonList.add(new JRMCoreGuiButtons02(31, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("News") ? 1 : 0, 8046079));
      drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
      i++;
      if (JRMCoreH.Accepted != 0 && JRMCoreH.Accepted != 2) {
         name = JRMCoreH.trl("jrmc", "CharSheet");
         buttonList.add(
            new JRMCoreGuiButtons02(100, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("CH") ? 1 : 0, 8046079)
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
      }

      if (JRMCoreH.Pwrtyp != 0 && (JRMCoreH.Pwrtyp == 1 || JRMCoreH.Pwrtyp == 2)) {
         name = JRMCoreH.trl("jrmc", "Skills");
         buttonList.add(
            new JRMCoreGuiButtons02(101, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("SK") ? 1 : 0, 8046079)
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
         name = JRMCoreH.Pwrtyp == 1 ? JRMCoreH.trl("jrmc", "KiTechniques") : JRMCoreH.trl("jrmc", "Jutsu");
         buttonList.add(
            new JRMCoreGuiButtons02(102, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("TE") ? 1 : 0, 8046079)
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
         name = JRMCoreH.Pwrtyp == 1
            ? JRMCoreH.trl("dbc", "SagaSystem")
            : (JRMCoreH.Pwrtyp == 2 ? JRMCoreH.trl("nc", "StorySystem") : JRMCoreH.trl("jrmc", "Story"));
         buttonList.add(
            new JRMCoreGuiButtons02(60, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Journal") ? 1 : 0, 8046079)
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
      }

      name = JRMCoreH.trl("jrmc", "GroupManager");
      buttonList.add(new JRMCoreGuiButtons02(62, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Group") ? 1 : 0, 8046079));
      drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
      i++;
      name = JRMCoreH.trl("jrmc", "ServerConfig");
      buttonList.add(
         new JRMCoreGuiButtons02(32, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Server") ? 1 : 0, 8046079)
      );
      drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
      i++;
      if (JRMCoreH.JYC()) {
         name = JRMCoreH.trl("jrmc", "Calendar");
         buttonList.add(
            new JRMCoreGuiButtons02(4902, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Calendar") ? 1 : 0, 8046079)
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
      }

      if (JRMCoreH.JFC()) {
         name = JRMCoreH.trl("jrmc", "Family");
         buttonList.add(
            new JRMCoreGuiButtons02(4903, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Family") ? 1 : 0, 8046079)
         );
         drawDetails(JRMCoreH.cct(name), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);
         i++;
      }
   }

   private static void drawDetails(String s1, String s2, int xpos, int ypos, int x, int y, FontRenderer var8) {
      int wpos = var8.func_78256_a(s1);
      var8.func_78276_b(s1, xpos, ypos, 0);
      if (xpos < x && xpos + wpos > x && ypos - 3 < y && ypos + 10 > y) {
         int ll = 200;
         Object[] txt = new Object[]{s2, "§8", 0, true, x + 5, y + 5, ll};
         detailList.add(txt);
      }
   }

   private static void drawDetails(String s1, int xpos, int ypos, int w, int h, int x, int y, FontRenderer var8) {
      if (xpos < x && xpos + w > x && ypos - 3 < y && ypos + h > y) {
         int ll = 200;
         Object[] txt = new Object[]{s1, "§8", 0, true, x + 5, y + 5, ll};
         detailList.add(txt);
      }
   }

   private void drawDetails(int x, int y, FontRenderer var8) {
      if (!detailList.isEmpty()) {
         Object[] o = (Object[])detailList.get(0);
         String desc = (String)o[0];
         int ll = Integer.parseInt("" + o[6]);
         int descw = var8.func_78256_a(desc);
         int desch = 1 + var8.func_78256_a(desc) / ll;
         mc.field_71446_o.func_110577_a(new ResourceLocation("jinryuumodscore:allw.png"));
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
         desch = JRMCoreH.txt(desc, (String)o[1], Integer.parseInt("" + o[2]), false, 0, 0, ll);
         ScaledResolution var5 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
         int var6 = var5.func_78326_a();
         int var7 = var5.func_78328_b();
         int xp = 0;
         int yp = 0;
         if (var6 < x + (descw < ll ? descw : ll) + 10) {
            xp += var6 - (x + (descw < ll ? descw : ll)) - 10;
         }

         if (var7 < y + desch * 10 + 10) {
            yp = -(desch * 10 + 20);
         }

         this.func_73729_b(x + xp, y + 10 + yp, 5, 5, (descw < ll ? descw : ll) + 10, desch * 10 + 10);
         JRMCoreH.txt(
            desc,
            (String)o[1],
            Integer.parseInt("" + o[2]),
            Boolean.parseBoolean("" + o[3]),
            Integer.parseInt("" + o[4]) + xp,
            Integer.parseInt("" + o[5]) + 10 + yp,
            ll
         );
         detailList.clear();
      }
   }

   @Override
   public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
   }

   public boolean func_73868_f() {
      return false;
   }
}
