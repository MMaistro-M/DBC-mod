package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePData implements IMessage {
   private int c;
   private String d;

   public JRMCorePData() {
   }

   public JRMCorePData(int c, String d) {
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

   public static class Handler extends BAmh<JRMCorePData> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePData m, MessageContext ctx) {
         JRMCoreClient.phc.handleData(m.c, m.d, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePData m, MessageContext ctx) {
         JRMCore.phs.handleData(m.c, m.d, p);
         return null;
      }
   }
}
