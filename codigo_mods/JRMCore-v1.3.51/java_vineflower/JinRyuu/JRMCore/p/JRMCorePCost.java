package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePCost implements IMessage {
   short b;

   public JRMCorePCost() {
   }

   public JRMCorePCost(short b) {
      this.b = b;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeShort(this.b);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b = buffer.readShort();
   }

   public static class Handler extends BAmh<JRMCorePCost> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePCost m, MessageContext ctx) {
         JRMCoreClient.phc.handleCost(m.b, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePCost m, MessageContext ctx) {
         JRMCore.phs.handleCost(m.b, p);
         return null;
      }
   }
}
