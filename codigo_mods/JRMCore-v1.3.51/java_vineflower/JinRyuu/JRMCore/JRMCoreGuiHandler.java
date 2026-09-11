package JinRyuu.JRMCore;

import JinRyuu.JRMCore.i.ContainerCustomPlayer;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.i.GuiCustomPlayerInventory;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class JRMCoreGuiHandler implements IGuiHandler {
   public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
      return guiId == mod_JRMCore.GUI_CUSTOM_INV ? new ContainerCustomPlayer(player, player.field_71071_by, ExtendedPlayer.get(player).inventory) : null;
   }

   public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
      if (guiId == mod_JRMCore.GUI_CUSTOM_INV) {
         return new GuiCustomPlayerInventory(player, player.field_71071_by, ExtendedPlayer.get(player).inventory);
      } else if (guiId >= 0) {
         return new JRMCoreGuiScreen(guiId);
      } else {
         return guiId == -1 ? new JRMCoreGuiSCM() : null;
      }
   }
}
