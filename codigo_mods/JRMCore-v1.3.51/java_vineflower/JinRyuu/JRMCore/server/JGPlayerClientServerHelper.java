package JinRyuu.JRMCore.server;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

public class JGPlayerClientServerHelper {
   public static float clientPlayerPositioner(Entity entity) {
      return entity instanceof EntityPlayerSP ? -1.6F : 0.0F;
   }
}
