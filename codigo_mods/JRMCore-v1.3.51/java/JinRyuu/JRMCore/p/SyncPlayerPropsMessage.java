package JinRyuu.JRMCore.p;

import JinRyuu.JRMCore.i.ExtendedPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class SyncPlayerPropsMessage implements IMessage {
   private NBTTagCompound data;

   public SyncPlayerPropsMessage() {
   }

   public SyncPlayerPropsMessage(EntityPlayer player) {
      this.data = new NBTTagCompound();
      ExtendedPlayer.get(player).saveNBTData(this.data);
   }

   public void fromBytes(ByteBuf buffer) {
      this.data = ByteBufUtils.readTag(buffer);
   }

   public void toBytes(ByteBuf buffer) {
      ByteBufUtils.writeTag(buffer, this.data);
   }

   public static class Handler extends CAmh<SyncPlayerPropsMessage> {
      @SideOnly(Side.CLIENT)
      public IMessage handleClientMessage(EntityPlayer player, SyncPlayerPropsMessage message, MessageContext ctx) {
         ExtendedPlayer.get(player).loadNBTData(message.data);
         return null;
      }
   }
}
