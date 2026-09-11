package JinRyuu.JRMCore.p.NC;

import JinRyuu.JRMCore.p.BAmh;
import JinRyuu.NarutoC.common.NC;
import JinRyuu.NarutoC.common.NCClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class NCPDou implements IMessage {
   byte b1;

   public NCPDou() {
   }

   public NCPDou(byte b1) {
      this.b1 = b1;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.b1);
   }

   public void fromBytes(ByteBuf buffer) {
      this.b1 = buffer.readByte();
   }

   public static class Handler extends BAmh<NCPDou> {
      public IMessage handleClientMessage(EntityPlayer p, NCPDou m, MessageContext ctx) {
         NCClient.phc.handleNCdou(m.b1, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, NCPDou m, MessageContext ctx) {
         NC.phs.handleNCdou(m.b1, p);
         return null;
      }
   }
}
