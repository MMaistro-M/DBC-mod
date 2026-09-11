package JinRyuu.JRMCore.p;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class CAmh<T extends IMessage> extends Amh<T> {
   @Override
   public final IMessage handleServerMessage(EntityPlayer player, T message, MessageContext ctx) {
      return null;
   }
}
