package JinRyuu.JRMCore.p.DBC;

import JinRyuu.DragonBC.common.DBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class DBCPchargepart implements IMessage {
   byte dbcchargepart;
   String dbcCharger;

   public DBCPchargepart() {
   }

   public DBCPchargepart(byte dbcchargepart, String dbcCharger) {
      this.dbcchargepart = dbcchargepart;
      this.dbcCharger = dbcCharger;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeByte(this.dbcchargepart);
      ByteBufUtils.writeUTF8String(buffer, this.dbcCharger);
   }

   public void fromBytes(ByteBuf buffer) {
      this.dbcchargepart = buffer.readByte();
      this.dbcCharger = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<DBCPchargepart> {
      public IMessage handleClientMessage(EntityPlayer p, DBCPchargepart m, MessageContext ctx) {
         DBCClient.phc.handleDBCchargepart(m.dbcchargepart, m.dbcCharger, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, DBCPchargepart m, MessageContext ctx) {
         DBC.phs.handleDBCchargepart(m.dbcchargepart, m.dbcCharger, p);
         return null;
      }
   }
}
