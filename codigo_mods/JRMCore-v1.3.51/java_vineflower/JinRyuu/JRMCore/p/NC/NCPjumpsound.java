package JinRyuu.JRMCore.p.NC;

import JinRyuu.JRMCore.p.BAmh;
import JinRyuu.NarutoC.common.NC;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class NCPjumpsound implements IMessage {
   int i;

   public NCPjumpsound() {
   }

   public NCPjumpsound(int i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
   }

   public static class Handler extends BAmh<NCPjumpsound> {
      public IMessage handleClientMessage(EntityPlayer p, NCPjumpsound m, MessageContext ctx) {
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, NCPjumpsound m, MessageContext ctx) {
         NC.phs.handleNCjumpsound(m.i, p);
         return null;
      }
   }
}
