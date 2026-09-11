package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePChar implements IMessage {
   byte b;
   int b2;

   public JRMCorePChar() {
   }

   public JRMCorePChar(byte b, int b2) {
      this.b = b;
      this.b2 = b2;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.b);
      buffer.writeInt(this.b2);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b = buffer.readByte();
      this.b2 = buffer.readInt();
   }

   public static class Handler extends BAmh<JRMCorePChar> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePChar m, MessageContext ctx) {
         JRMCoreClient.phc.handleChar(m.b, m.b2, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePChar m, MessageContext ctx) {
         JRMCore.phs.handleChar(m.b, m.b2, p);
         return null;
      }
   }
}
