package JinRyuu.JRMCore.p.NC;

import JinRyuu.JRMCore.p.BAmh;
import JinRyuu.NarutoC.common.NC;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class NCPchargesound implements IMessage {
   int i;

   public NCPchargesound() {
   }

   public NCPchargesound(int i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
   }

   public static class Handler extends BAmh<NCPchargesound> {
      public IMessage handleClientMessage(EntityPlayer p, NCPchargesound m, MessageContext ctx) {
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, NCPchargesound m, MessageContext ctx) {
         NC.phs.handleNCchargesound(m.i, p);
         return null;
      }
   }
}
