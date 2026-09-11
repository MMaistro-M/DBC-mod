package JinRyuu.JRMCore.p.NC;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.NarutoC.common.NCTech;
import net.minecraft.entity.player.EntityPlayer;

public class NCPacketHandlerClient {
   public void handleJRNC(int c, String d, EntityPlayer Player) {
      if (d.equalsIgnoreCase("copy")) {
         d = JRMCoreH.jutsu + ";" + JRMCoreH.hsp;
         PD.sendToServer(new NCPData2(c, d));
      } else {
         NCTech.BLcopy = d;
      }

      switch (c) {
         case 0:
            String[] s2 = d.contains(";") ? d.toString().split(";") : null;
            JRMCoreH.inIll = s2;
      }
   }

   public void handleNCdou(byte b, EntityPlayer Player) {
   }
}
