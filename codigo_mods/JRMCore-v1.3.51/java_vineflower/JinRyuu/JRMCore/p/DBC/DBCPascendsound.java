package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPascendsound implements IMessage {
   int i;

   public DBCPascendsound() {
   }

   public DBCPascendsound(int i) {
      this.i = i;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.i);
   }

   public void fromBytes(ByteBuf buffer) {
      this.i = buffer.readInt();
   }

   public static class Handler extends BAmh<DBCPascendsound> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPascendsound m, MessageContext ctx) {
         DBCClient.phc.handleDBCascendsound(m.i, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPascendsound m, MessageContext ctx) {
         DBC.phs.handleDBCascendsound(m.i, p);
         return null;
      }
   }
}
