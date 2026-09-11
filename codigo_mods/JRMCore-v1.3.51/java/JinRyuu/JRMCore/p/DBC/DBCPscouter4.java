package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPscouter4 implements IMessage {
   int i;

   public DBCPscouter4() {
   }

   public DBCPscouter4(int i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
   }

   public static class Handler extends BAmh<DBCPscouter4> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPscouter4 m, MessageContext ctx) {
         DBCClient.phc.handleDBCscouter4(m.i, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPscouter4 m, MessageContext ctx) {
         DBC.phs.handleDBCscouter4(m.i, p);
         return null;
      }
   }
}
