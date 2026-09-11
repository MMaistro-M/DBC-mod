package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePCol implements IMessage {
   int i;
   byte b;

   public JRMCorePCol() {
   }

   public JRMCorePCol(int i, byte b) {
      this.i = i;
      this.b = b;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
      buffer.writeByte(this.b);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
      this.b = buffer.readByte();
   }

   public static class Handler extends BAmh<JRMCorePCol> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePCol m, MessageContext ctx) {
         JRMCoreClient.phc.handleCol(m.i, m.b, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePCol m, MessageContext ctx) {
         JRMCore.phs.handleCol(m.i, m.b, p);
         return null;
      }
   }
}
