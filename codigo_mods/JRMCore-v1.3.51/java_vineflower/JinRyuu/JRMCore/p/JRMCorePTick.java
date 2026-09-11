package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePTick implements IMessage {
   private int c;
   private String d;

   public JRMCorePTick() {
   }

   public JRMCorePTick(int c, String d) {
      this.c = c;
      this.d = d;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.c);
      ByteBufUtils.writeUTF8String(buffer, this.d);
   }

   public void fromBytes(ByteBuf buffer) {
      this.c = buffer.readInt();
      this.d = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<JRMCorePTick> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePTick m, MessageContext ctx) {
         JRMCoreClient.phc.handleTick(m.c, m.d, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePTick m, MessageContext ctx) {
         JRMCore.phs.handleTick(m.c, m.d, p);
         return null;
      }
   }
}
