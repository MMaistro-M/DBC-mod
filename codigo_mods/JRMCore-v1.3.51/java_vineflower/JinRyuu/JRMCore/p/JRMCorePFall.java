package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePFall implements IMessage {
   byte b;

   public JRMCorePFall() {
   }

   public JRMCorePFall(byte b) {
      this.b = b;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.b);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b = buffer.readByte();
   }

   public static class Handler extends BAmh<JRMCorePFall> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePFall m, MessageContext ctx) {
         JRMCoreClient.phc.handleFall(m.b, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePFall m, MessageContext ctx) {
         JRMCore.phs.handleFall(m.b, p);
         return null;
      }
   }
}
