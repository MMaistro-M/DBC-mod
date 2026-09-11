package JinRyuu.JRMCore.p.YC;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JYearsCH;
import JinRyuu.JRMCore.p.BAmh;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentText;

public class JYearsCP implements IMessage {
   public int jycdatey;
   public int jycdatem;
   public int jycdated;
   public String jycp;
   public int jycpy;

   public JYearsCP() {
   }

   public JYearsCP(int jycdatey, int jycdatem, int jycdated, String jycp, int jycpy) {
      this.jycdatey = jycdatey;
      this.jycdatem = jycdatem;
      this.jycdated = jycdated;
      this.jycp = jycp;
      this.jycpy = jycpy;
   }

   public void toBytes(ByteBuf buffer) {
      buffer.writeInt(this.jycdatey);
      buffer.writeShort(this.jycdatem);
      buffer.writeShort(this.jycdated);
      ByteBufUtils.writeUTF8String(buffer, this.jycp);
      buffer.writeInt(this.jycpy);
   }

   public void fromBytes(ByteBuf buffer) {
      this.jycdatey = buffer.readInt();
      this.jycdatem = buffer.readShort();
      this.jycdated = buffer.readShort();
      this.jycp = ByteBufUtils.readUTF8String(buffer);
      this.jycpy = buffer.readInt();
   }

   public static class Handler extends BAmh<JYearsCP> {
      public IMessage handleClientMessage(EntityPlayer player, JYearsCP m, MessageContext ctx) {
         JYearsCH.y = m.jycdatey;
         JYearsCH.m = m.jycdatem;
         JYearsCH.d = m.jycdated;
         JYearsCH.py = m.jycpy;
         if (m.jycp == "::") {
            JYearsCH.p = null;
         } else {
            JYearsCH.p = m.jycp.toString().replaceAll("::", "").split(":");
         }

         return null;
      }

      public IMessage handleServerMessage(EntityPlayer Player, JYearsCP m, MessageContext ctx) {
         if (m.jycpy == 1) {
            if (Player.field_71071_by.func_146028_b(Items.field_151166_bC)) {
               Player.field_71071_by.field_70459_e = true;
               Player.field_71071_by.func_146026_a(Items.field_151166_bC);
               Player.field_71071_by.field_70459_e = false;
               JRMCoreH.setFloat(0, Player, "JRYCAge");
            } else {
               Player.func_145747_a(new ChatComponentText("You need an Emerald to use Rebirth!"));
            }
         }

         return null;
      }
   }
}
