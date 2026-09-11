package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePData2 implements IMessage {
   private String c;
   private String d;

   public JRMCorePData2() {
   }

   public JRMCorePData2(String c, String d) {
      this.c = c;
      this.d = d;
   }

   public void toBytes(ByteBuf buffer) {
      ByteBufUtils.writeUTF8String(buffer, this.c);
      ByteBufUtils.writeUTF8String(buffer, this.d);
   }

   public void fromBytes(ByteBuf buffer) {
      this.c = ByteBufUtils.readUTF8String(buffer);
      this.d = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<JRMCorePData2> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePData2 m, MessageContext ctx) {
         JRMCoreClient.phc.handleData2(m.c, m.d, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePData2 m, MessageContext ctx) {
         JRMCore.phs.handleData2(m.c, m.d, p);
         return null;
      }
   }
}
