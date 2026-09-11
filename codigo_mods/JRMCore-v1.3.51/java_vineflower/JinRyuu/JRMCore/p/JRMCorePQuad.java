package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePQuad implements IMessage {
   int b1;
   int b2;
   int b3;
   int b4;

   public JRMCorePQuad() {
   }

   public JRMCorePQuad(int b1, int b2, int b3, int b4) {
      this.b1 = b1;
      this.b2 = b2;
      this.b3 = b3;
      this.b4 = b4;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.b1);
      buffer.writeInt(this.b2);
      buffer.writeInt(this.b3);
      buffer.writeInt(this.b4);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b1 = buffer.readInt();
      this.b2 = buffer.readInt();
      this.b3 = buffer.readInt();
      this.b4 = buffer.readInt();
   }

   public static class Handler extends BAmh<JRMCorePQuad> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePQuad m, MessageContext ctx) {
         JRMCoreClient.phc.handleQuad(m.b1, m.b2, m.b3, m.b4, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePQuad m, MessageContext ctx) {
         JRMCore.phs.handleQuad(m.b1, m.b2, m.b3, m.b4, p);
         return null;
      }
   }
}
