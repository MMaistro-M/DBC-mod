package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPduo implements IMessage {
   int i;
   int i2;

   public DBCPduo() {
   }

   public DBCPduo(int i, int i2) {
      this.i = i;
      this.i2 = i2;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
      buffer.writeInt(this.i2);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
      this.i2 = buffer.readInt();
   }

   public static class Handler extends BAmh<DBCPduo> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPduo m, MessageContext ctx) {
         DBCClient.phc.handleDBCjumpsound(m.i, m.i2, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPduo m, MessageContext ctx) {
         DBC.phs.handleDBCjumpsound(m.i, m.i2, p);
         return null;
      }
   }
}
