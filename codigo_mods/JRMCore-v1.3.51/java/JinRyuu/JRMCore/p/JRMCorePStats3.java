package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePStats3 implements IMessage {
   byte b;
   byte b2;
   byte b3;

   public JRMCorePStats3() {
   }

   public JRMCorePStats3(byte b, byte b2, byte b3) {
      this.b = b;
      this.b2 = b2;
      this.b3 = b3;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.b);
      buffer.writeByte(this.b2);
      buffer.writeByte(this.b3);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b = buffer.readByte();
      this.b2 = buffer.readByte();
      this.b3 = buffer.readByte();
   }

   public static class Handler extends BAmh<JRMCorePStats3> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePStats3 m, MessageContext ctx) {
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePStats3 m, MessageContext ctx) {
         JRMCore.phs.handleStats3(m.b, m.b2, m.b3, p);
         return null;
      }
   }
}
