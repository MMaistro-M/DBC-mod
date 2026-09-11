package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class OpenGuiMessage implements IMessage {
   private int id;

   public OpenGuiMessage() {
   }

   public OpenGuiMessage(int id) {
      this.id = id;
   }

   public void fromBytes(ByteBuf buffer) {
      this.id = buffer.readInt();
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.id);
   }

   public static class Handler extends SAmh<OpenGuiMessage> {
      public IMessage handleServerMessage(EntityPlayer player, OpenGuiMessage message, MessageContext ctx) {
         player.openGui(mod_JRMCore.instance, message.id, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
         return null;
      }
   }
}
