package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCore;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePUpgrade implements IMessage {
   byte b;

   public JRMCorePUpgrade() {
   }

   public JRMCorePUpgrade(byte b) {
      this.b = b;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.b);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b = buffer.readByte();
   }

   public static class Handler extends BAmh<JRMCorePUpgrade> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePUpgrade m, MessageContext ctx) {
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePUpgrade m, MessageContext ctx) {
         JRMCore.phs.handleUpgrade(m.b, p);
         return null;
      }
   }
}
