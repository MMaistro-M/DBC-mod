package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPwish implements IMessage {
   int i;
   String s;

   public DBCPwish() {
   }

   public DBCPwish(int i, String s) {
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

   public static class Handler extends BAmh<DBCPwish> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPwish m, MessageContext ctx) {
         DBCClient.phc.handleDBCwish(m.i, m.s, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPwish m, MessageContext ctx) {
         DBC.phs.handleDBCwish(m.i, m.s, p);
         return null;
      }
   }
}
