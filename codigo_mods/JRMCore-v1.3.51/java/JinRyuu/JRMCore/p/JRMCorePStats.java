package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePStats implements IMessage {
   int curBody;
   int curEnergy;
   int curStamina;
   byte curRelease;
   byte b;

   public JRMCorePStats() {
   }

   public JRMCorePStats(int curBody, int curEnergy, int curStamina, byte curRelease, byte b) {
      this.curBody = curBody;
      this.curEnergy = curEnergy;
      this.curStamina = curStamina;
      this.curRelease = curRelease;
      this.b = b;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.curBody);
      buffer.writeInt(this.curEnergy);
      buffer.writeInt(this.curStamina);
      buffer.writeByte(this.curRelease);
      buffer.writeByte(this.b);
   }

   public void fromBytes(ByteBuf buffer) {
      this.curBody = buffer.readInt();
      this.curEnergy = buffer.readInt();
      this.curStamina = buffer.readInt();
      this.curRelease = buffer.readByte();
      this.b = buffer.readByte();
   }

   public static class Handler extends BAmh<JRMCorePStats> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePStats m, MessageContext ctx) {
         JRMCoreClient.phc.handleStats(m.curBody, m.curEnergy, m.curStamina, m.curRelease, m.b);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePStats m, MessageContext ctx) {
         return null;
      }
   }
}
