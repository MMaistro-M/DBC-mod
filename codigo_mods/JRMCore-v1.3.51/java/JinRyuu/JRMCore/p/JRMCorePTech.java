package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePTech implements IMessage {
   byte b;
   String s;

   public JRMCorePTech() {
   }

   public JRMCorePTech(byte b, String s) {
      this.b = b;
      this.s = s;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.b);
      ByteBufUtils.writeUTF8String(buffer, this.s);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b = buffer.readByte();
      this.s = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<JRMCorePTech> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePTech m, MessageContext ctx) {
         JRMCoreClient.phc.handleTech(m.b, m.s, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePTech m, MessageContext ctx) {
         JRMCore.phs.handleTech(m.b, m.s, p);
         return null;
      }
   }
}
