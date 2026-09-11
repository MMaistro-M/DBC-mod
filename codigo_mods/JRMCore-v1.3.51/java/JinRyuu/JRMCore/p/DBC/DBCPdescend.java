package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPdescend implements IMessage {
   byte i;

   public DBCPdescend() {
   }

   public DBCPdescend(byte i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readByte();
   }

   public static class Handler extends BAmh<DBCPdescend> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPdescend m, MessageContext ctx) {
         DBCClient.phc.handleDBCdescend(m.i, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPdescend m, MessageContext ctx) {
         DBC.phs.handleDBCdescend(m.i, p);
         return null;
      }
   }
}
