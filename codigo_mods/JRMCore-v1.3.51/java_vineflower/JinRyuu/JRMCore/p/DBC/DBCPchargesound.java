package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPchargesound implements IMessage {
   int i;
   String s;

   public DBCPchargesound() {
   }

   public DBCPchargesound(int i, String s) {
      this.i = i;
      this.s = s;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
      ByteBufUtils.writeUTF8String(buffer, this.s);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
      this.s = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<DBCPchargesound> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPchargesound m, MessageContext ctx) {
         DBCClient.phc.handleDBCchargesound(m.i, m.s, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPchargesound m, MessageContext ctx) {
         DBC.phs.handleDBCchargesound(m.i, m.s, p);
         return null;
      }
   }
}
