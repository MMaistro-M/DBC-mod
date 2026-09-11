package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPtick implements IMessage {
   int i;

   public DBCPtick() {
   }

   public DBCPtick(int i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
   }

   public static class Handler extends BAmh<DBCPtick> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPtick m, MessageContext ctx) {
         DBCClient.phc.handleDBCtick(m.i, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPtick m, MessageContext ctx) {
         DBC.phs.handleDBCtick(m.i, p);
         return null;
      }
   }
}
