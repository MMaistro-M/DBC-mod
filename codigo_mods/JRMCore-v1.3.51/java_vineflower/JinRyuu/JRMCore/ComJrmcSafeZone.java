package JinRyuu.JRMCore;

import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.manager.JRMCoreManagerDBC;
import JinRyuu.JRMCore.manager.JRMCoreManagerNC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ComJrmcSafeZone extends CommandBase {
   private final int DBC_MASTERS = 16;
   private final int NC_MASTERS = 3;
   private final String[] MODES = new String[]{"add", "change", "remove"};
   private final String ADD = "add";
   private final String CHANGE = "change";
   private final String REMOVE = "remove";
   private final String name = "jrmcsafezone";

   public String func_71517_b() {
      return "jrmcsafezone";
   }

   public String func_71518_a(ICommandSender icommandsender) {
      return "/jrmcsafezone [playerName] (add/change/remove) (MasterName) (x) (y) (z) (safezone radiusXZ) (safezone radiusY) (duplication Check Radius) ((Safe Zone Name)).";
   }

   public int func_82362_a() {
      return 2;
   }

   public void func_71515_b(ICommandSender commandSender, String[] stringArray) {
      int commandLength = stringArray.length;
      if (commandLength <= 0) {
         throw new WrongUsageException("Usage: " + this.func_71518_a(commandSender), new Object[0]);
      }

      int stringID = 0;

      EntityPlayerMP entityplayermp;
      try {
         entityplayermp = func_82359_c(commandSender, stringArray[stringID++]);
      } catch (Exception e) {
         entityplayermp = func_71521_c(commandSender);
      }

      String mode = stringArray[stringID++].toLowerCase();
      String masterID = stringArray[stringID++];
      String[] list = this.getMasterIDList();
      int length = list.length;
      int id = -1;
      boolean dbc = JRMCoreH.DBC();
      boolean nc = JRMCoreH.NC();

      for (int i = 0; i < length; i++) {
         if (masterID.toLowerCase().equals(list[i].toLowerCase())) {
            if ((dbc || i <= 0 || i >= length - (nc ? 3 : 0)) && (nc || i <= length - 1 - (nc ? 3 : 0))) {
               id = i;
            }
            break;
         }
      }

      if (id == -1) {
         throw new WrongUsageException("ERROR: Master Name was not found! '" + masterID + "'", new Object[0]);
      }

      String x = stringArray[stringID++];
      String y = stringArray[stringID++];
      String z = stringArray[stringID++];
      String safezoneRXZ = "";
      String safezoneRY = "";
      String duplicationCheckR = "";
      String name = "";
      boolean add = mode.equals("add");
      boolean remove = mode.equals("remove");
      if (!remove) {
         safezoneRXZ = stringArray[stringID++];
         safezoneRY = stringArray[stringID++];
         duplicationCheckR = stringArray[stringID++];

         for (int i = stringID; i < commandLength; i++) {
            name = name
               + stringArray[stringID++].replace("(", "").replace(")", "")
               + (i + 1 < commandLength && !stringArray[stringID - 1].endsWith(")") ? " " : "");
            if (stringArray[stringID - 1].endsWith(")")) {
               break;
            }
         }

         if (name.length() > 0) {
            name = name.replace(":", "").replace(";", "");
         }
      }

      World world = commandSender.func_130014_f_();
      EntitySafeZone master = this.getEntityList(entityplayermp)[id];
      double xPos = 0.0;
      double yPos = 0.0;
      double zPos = 0.0;
      if (x != null && x.length() > 0 && !x.equals("~")) {
         xPos = Double.parseDouble(x);
      } else {
         xPos = entityplayermp.field_70165_t;
      }

      if (y != null && y.length() > 0 && !y.equals("~")) {
         yPos = Double.parseDouble(y);
      } else {
         yPos = entityplayermp.field_70163_u;
      }

      if (z != null && z.length() > 0 && !z.equals("~")) {
         zPos = Double.parseDouble(z);
      } else {
         zPos = entityplayermp.field_70161_v;
      }

      boolean sendMessage = true;
      if (!remove) {
         if (name != null && name.length() > 0) {
            master.name = name;
         }

         if (safezoneRXZ != null && safezoneRXZ.length() > 0) {
            master.safezoneRadiusXZ = Integer.parseInt(safezoneRXZ);
            if (master.safezoneRadiusXZ < 0) {
               master.safezoneRadiusXZ = 0;
            }
         }

         if (safezoneRY != null && safezoneRY.length() > 0) {
            master.safezoneRadiusY = Integer.parseInt(safezoneRY);
            if (master.safezoneRadiusY < 0) {
               master.safezoneRadiusY = 0;
            }
         }

         if (duplicationCheckR != null && duplicationCheckR.length() > 0) {
            master.duplicatesRadius = Integer.parseInt(duplicationCheckR);
            if (master.duplicatesRadius < 0) {
               master.duplicatesRadius = 0;
            }
         }
      }

      if (add) {
         if (name != null && name.length() > 0) {
            master.name = name;
         }

         if (safezoneRXZ != null && safezoneRXZ.length() > 0) {
            master.safezoneRadiusXZ = Integer.parseInt(safezoneRXZ);
            if (master.safezoneRadiusXZ < 0) {
               master.safezoneRadiusXZ = 0;
            }
         }

         if (safezoneRY != null && safezoneRY.length() > 0) {
            master.safezoneRadiusY = Integer.parseInt(safezoneRY);
            if (master.safezoneRadiusY < 0) {
               master.safezoneRadiusY = 0;
            }
         }

         if (duplicationCheckR != null && duplicationCheckR.length() > 0) {
            master.duplicatesRadius = Integer.parseInt(duplicationCheckR);
            if (master.duplicatesRadius < 0) {
               master.duplicatesRadius = 0;
            }
         }

         master.func_70107_b(xPos, yPos, zPos);
         world.func_72838_d(master);
         if (sendMessage) {
            this.notifyAdmins(commandSender, "Safe Zone Created: %s by %s", new Object[]{masterID, entityplayermp.func_70005_c_()});
         }
      } else {
         int r = 2;
         AxisAlignedBB ab = AxisAlignedBB.func_72330_a(xPos - r, yPos - r, zPos - r, xPos + r, yPos + r, zPos + r);
         List list2 = world.func_72872_a(master.getClass(), ab);

         for (int i = 0; list2.size() > i; i++) {
            EntitySafeZone entity = (EntitySafeZone)list2.get(i);
            if (entity.getClass().equals(master.getClass())) {
               if (remove) {
                  if (sendMessage) {
                     this.notifyAdmins(commandSender, "Safe Zone Removed: %s by %s", new Object[]{masterID, entityplayermp.func_70005_c_()});
                  }

                  entity.removeAllPlayers();
                  entity.func_70106_y();
               } else {
                  if (sendMessage) {
                     this.notifyAdmins(commandSender, "Safe Zone Changed: %s by %s", new Object[]{masterID, entityplayermp.func_70005_c_()});
                  }

                  entity.removeAllPlayers();
                  entity.name = name;
                  if (safezoneRXZ != null && safezoneRXZ.length() > 0) {
                     entity.safezoneRadiusXZ = Integer.parseInt(safezoneRXZ);
                     if (entity.safezoneRadiusXZ < 0) {
                        entity.safezoneRadiusXZ = 0;
                     }
                  }

                  if (safezoneRY != null && safezoneRY.length() > 0) {
                     entity.safezoneRadiusY = Integer.parseInt(safezoneRY);
                     if (entity.safezoneRadiusY < 0) {
                        entity.safezoneRadiusY = 0;
                     }
                  }

                  if (duplicationCheckR != null && duplicationCheckR.length() > 0) {
                     entity.duplicatesRadius = Integer.parseInt(duplicationCheckR);
                     if (entity.duplicatesRadius < 0) {
                        entity.duplicatesRadius = 0;
                     }
                  }
               }

               return;
            }
         }

         throw new WrongUsageException("ERROR: No Master was found to delete! '" + masterID + "'", new Object[0]);
      }
   }

   private void notifyAdmins(ICommandSender commandSender, String string, Object[] objects) {
      func_152373_a(commandSender, this, string, objects);
   }

   private String[] getMasterIDList() {
      ArrayList<String> entityNames = new ArrayList<>();
      entityNames.add("Empty");
      if (JRMCoreH.DBC()) {
         entityNames.addAll(Arrays.asList(JRMCoreManagerDBC.getMasterNames()));
      }

      if (JRMCoreH.NC()) {
         entityNames.addAll(Arrays.asList(JRMCoreManagerNC.getMasterNames()));
      }

      String[] names = new String[entityNames.size()];
      int i = 0;

      for (String name : entityNames) {
         names[i] = name;
         i++;
      }

      return names;
   }

   private EntitySafeZone[] getEntityList(EntityPlayerMP entityplayermp) {
      ArrayList<EntitySafeZone> entityNames = new ArrayList<>();
      entityNames.add(new EntitySafeZone(entityplayermp.field_70170_p));
      if (JRMCoreH.DBC()) {
         entityNames.addAll(Arrays.asList(JRMCoreManagerDBC.getMasters(entityplayermp)));
      }

      if (JRMCoreH.NC()) {
         entityNames.addAll(Arrays.asList(JRMCoreManagerNC.getMasters(entityplayermp)));
      }

      EntitySafeZone[] names = new EntitySafeZone[entityNames.size()];
      int i = 0;

      for (EntitySafeZone name : entityNames) {
         names[i] = name;
         i++;
      }

      return names;
   }

   public List func_71516_a(ICommandSender par1ICommandSender, String[] arrayString) {
      int length = arrayString.length;
      switch (length) {
         case 1:
            return func_71530_a(arrayString, this.getListOfPlayers());
         case 2:
            return func_71530_a(arrayString, this.MODES);
         case 3:
            return func_71530_a(arrayString, this.getMasterIDList());
         case 4:
            return func_71530_a(arrayString, new String[]{"~", "0.0"});
         case 5:
            return func_71530_a(arrayString, new String[]{"~", "0.0"});
         case 6:
            return func_71530_a(arrayString, new String[]{"~", "0.0"});
         case 7:
            return func_71530_a(arrayString, new String[]{"60"});
         case 8:
            return func_71530_a(arrayString, new String[]{"60"});
         case 9:
            return func_71530_a(arrayString, new String[]{"2"});
         case 10:
            return func_71530_a(arrayString, new String[]{"(Safe Zone Name)"});
         default:
            return null;
      }
   }

   protected String[] getListOfPlayers() {
      return MinecraftServer.func_71276_C().func_71213_z();
   }
}
