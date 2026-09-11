package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPascend implements IMessage {
   byte i;

   public DBCPascend() {
   }

   public DBCPascend(byte i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readByte();
   }

   public static class Handler extends BAmh<DBCPascend> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPascend m, MessageContext ctx) {
         DBCClient.phc.handleDBCascend(m.i, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPascend m, MessageContext ctx) {
         DBC.phs.handleDBCascend(m.i, p);
         return null;
      }
   }
}
