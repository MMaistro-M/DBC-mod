package JinRyuu.JRMCore;

import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiScreenE extends GuiScreen {
   int guiWidth = 256;
   int guiHeight = 256;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void hideHealth(RenderGameOverlayEvent e) {
      if (e != null) {
         if (e.type.equals(ElementType.ALL)) {
         }

         if (e.type.equals(ElementType.HEALTH) && e.isCancelable() && (JRMCoreH.Pwrtyp == 1 || JRMCoreH.Pwrtyp == 2 || JRMCoreH.Pwrtyp == 3)) {
            e.setCanceled(true);
         }

         if (e.type == ElementType.TEXT) {
         }

         if (e.type == ElementType.TEXT) {
            Entity target = JRMCoreCliTicH.lockOn;
            if (target == null || !JRMCoreConfig.lockon) {
               return;
            }

            Minecraft mc = JRMCoreClient.mc;
            EntityPlayer player = mc.field_71439_g;
            float reduction = 4.0F;
            int screenWidth = e.resolution.func_78326_a();
            int screenHeight = e.resolution.func_78328_b();
            double distanceSq = player.func_70032_d(target);
            float scale = (float)(10.0 / distanceSq);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glTranslatef(screenWidth / 2 - this.guiWidth / 2 * scale / reduction, screenHeight / 2 - this.guiHeight / 2 * scale / reduction, 0.0F);
            GL11.glScalef(scale / reduction, scale / reduction, scale / reduction);
            if (target != null) {
               mc.field_71446_o.func_110577_a(new ResourceLocation(JRMCoreH.tjjrmc, "gui/lo/" + JGConfigClientSettings.get_hud_lockon() + ".png"));
               this.func_73729_b(0, 0, 0, 0, this.guiWidth, this.guiHeight);
            }

            GL11.glDisable(3042);
            GL11.glPopMatrix();
         }
      }
   }
}
