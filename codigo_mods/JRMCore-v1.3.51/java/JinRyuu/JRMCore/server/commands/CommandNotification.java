package JinRyuu.JRMCore.server.commands;

import JinRyuu.JRMCore.client.notification.JGNotificationHandler;
import JinRyuu.JRMCore.p.JRMCorePData3;
import JinRyuu.JRMCore.p.PD;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandNotification extends CommandBase {
   private final String name = "jrmcnotification";

   public String func_71517_b() {
      return "jrmcnotification";
   }

   public String func_71518_a(ICommandSender commandSender) {
      return "/jrmcnotification ([sendToAll] OR [sendToDimension:dimensionID] OR [sendToAllAround:dimensionID,x,y,z,range] OR [playerName]) {Title;Description;Category;IconID;RenderLocationID;ColorDecimalCode}";
   }

   public int func_82362_a() {
      return 2;
   }

   public List func_71516_a(ICommandSender commandSender, String[] stringArray) {
      int length = stringArray.length;
      switch (length) {
         case 1:
            return func_71530_a(stringArray, this.getListOfPlayers());
         case 2:
            return func_71530_a(stringArray, new String[]{"{Title;Description;Info;0;0;16777215}"});
         default:
            return null;
      }
   }

   protected String[] getListOfPlayers() {
      String[] players = MinecraftServer.func_71276_C().func_71213_z();
      int length = players.length + 3;
      String[] list = new String[length];

      for (int i = 0; i < length; i++) {
         if (i < players.length) {
            list[i] = players[i];
         } else if (i == players.length) {
            list[i] = "sendtoall";
         } else if (i == players.length + 1) {
            list[i] = "sendtodimension:0";
         } else if (i == players.length + 2) {
            list[i] = "sendtoallaround:0;50;50;50;5";
         }
      }

      return list;
   }

   public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
      if (stringArray.length <= 1) {
         throw new WrongUsageException(this.func_71518_a(commandSender), new Object[0]);
      }

      String sendTo = stringArray[0];
      String text = "";

      for (int i = 1; i < stringArray.length; i++) {
         text = text + stringArray[i] + (i == stringArray.length - 1 ? "" : " ");
      }

      String[] array = text.replace("{", "").replace("}", "").split(";");
      String title = array[0];
      String description = array[1];
      String category = array[2].toLowerCase();
      byte categoryID = (byte)JGNotificationHandler.getCategoryIDAll(category);
      byte iconID = Byte.parseByte(array[3]);
      if (iconID < 0) {
         iconID = 0;
      }

      byte renderLocationID = Byte.parseByte(array[4]);
      if (renderLocationID < 0) {
         renderLocationID = 0;
      }

      if (renderLocationID > 8) {
         renderLocationID = 8;
      }

      int colorCode = Integer.parseInt(array[5]);
      if (sendTo.toLowerCase().equals("sendtoall")) {
         PD.sendToAll(new JRMCorePData3(title, description, categoryID, iconID, renderLocationID, colorCode));
      } else if (sendTo.toLowerCase().contains("sendtodimension")) {
         int dimensionID = Integer.parseInt(sendTo.split(":")[1]);
         PD.sendToDimension(new JRMCorePData3(title, description, categoryID, iconID, renderLocationID, colorCode), dimensionID);
      } else if (!sendTo.toLowerCase().contains("sendtoallaround")) {
         EntityPlayerMP player = null;

         try {
            player = func_82359_c(commandSender, sendTo);
         } catch (Exception var16) {
         }

         if (player != null) {
            PD.sendTo(new JRMCorePData3(title, description, categoryID, iconID, renderLocationID, colorCode), player);
         }
      } else {
         String[] values = sendTo.split(":")[1].split(";");
         int[] data = new int[5];

         for (int i = 0; i < 5; i++) {
            data[i] = Integer.parseInt(values[i]);
         }

         PD.sendToAllAround(new JRMCorePData3(title, description, categoryID, iconID, renderLocationID, colorCode), data[0], data[1], data[2], data[3], data[4]);
      }
   }

   public boolean isUsernameIndex(int id) {
      return id == 0;
   }
}
