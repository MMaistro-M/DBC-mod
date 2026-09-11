package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.JRMCoreClient;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class JRMCorePStats3b implements IMessage {
   String PlyrSkills;
   String x;
   String y;
   String z;

   public JRMCorePStats3b() {
   }

   public JRMCorePStats3b(String PlyrSkills, String x, String y, String z) {
      this.PlyrSkills = PlyrSkills;
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public void toBytes(ByteBuf buffer) {
      ByteBufUtils.writeUTF8String(buffer, this.x);
      ByteBufUtils.writeUTF8String(buffer, this.y);
      ByteBufUtils.writeUTF8String(buffer, this.z);
      ByteBufUtils.writeUTF8String(buffer, this.PlyrSkills);
   }

   public void fromBytes(ByteBuf buffer) {
      this.x = ByteBufUtils.readUTF8String(buffer);
      this.y = ByteBufUtils.readUTF8String(buffer);
      this.z = ByteBufUtils.readUTF8String(buffer);
      this.PlyrSkills = ByteBufUtils.readUTF8String(buffer);
   }

   public static class Handler extends BAmh<JRMCorePStats3b> {
      public IMessage handleClientMessage(EntityPlayer p, JRMCorePStats3b m, MessageContext ctx) {
         JRMCoreClient.phc.handleStats3(m.PlyrSkills, m.x, m.y, m.z, p);
         return null;
      }

      public IMessage handleServerMessage(EntityPlayer p, JRMCorePStats3b m, MessageContext ctx) {
         return null;
      }
   }
}
