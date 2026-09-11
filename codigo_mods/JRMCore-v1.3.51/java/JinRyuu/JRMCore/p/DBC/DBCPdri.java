package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPdri implements IMessage {
   int i;

   public DBCPdri() {
   }

   public DBCPdri(int i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
   }

   public static class Handler extends BAmh<DBCPdri> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPdri m, MessageContext ctx) {
         DBCClient.phc.handleDBCdri(m.i, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPdri m, MessageContext ctx) {
         DBC.phs.handleDBCdri(m.i, p);
         return null;
      }
   }
}
