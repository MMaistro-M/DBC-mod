package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePTrib implements IMessage {
   public void toBytes(ByteBuf buffer) {
      switch (FMLCommonHandler.instance().getEffectiveSide()) {
         case CLIENT:
         case SERVER:
         default:
            JRMCoreH.configToClient(buffer);
      }
   }

   public void fromBytes(ByteBuf buffer) {
      switch (FMLCommonHandler.instance().getEffectiveSide()) {
         case CLIENT:
         case SERVER:
         default:
            JRMCoreClient.phc.handleTri(buffer);
      }
   }

   public static class Handler extends BAmh<JRMCorePTrib> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePTrib m, MessageContext ctx) {
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePTrib m, MessageContext ctx) {
         return null;
      }
   }
}
