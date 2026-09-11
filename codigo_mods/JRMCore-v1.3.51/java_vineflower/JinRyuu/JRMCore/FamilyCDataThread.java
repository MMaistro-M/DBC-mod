package JinRyuu.JRMCore;

import net.minecraft.server.MinecraftServer;

public class FamilyCDataThread extends Thread {
   public boolean run = true;

   @Override
   public void run() {
      while (this.run) {
         MinecraftServer server = JRMCoreComTickH.server;
         if (server == null || !server.func_71278_l()) {
            FamilyCH.lastUpdateTick = 0;
            this.run = false;
         } else if (!JRMCoreH.paused) {
            FamilyCH.familyCNBT = FamilyCH.readFamilyInfoFromNBT(server);
            FamilyCH.lastUpdateTick = 0;

            try {
               sleep(JRMCoreConfig.serverPlayerUpdateTick_DataFamilyC * 1000);
            } catch (InterruptedException var3) {
            }
         } else {
            FamilyCH.lastUpdateTick = 0;

            try {
               sleep(1000L);
            } catch (InterruptedException var4) {
            }
         }
      }
   }
}
