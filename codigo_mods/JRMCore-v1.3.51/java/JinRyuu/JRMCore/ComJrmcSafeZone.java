/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.manager.JRMCoreManagerDBC;
import JinRyuu.JRMCore.manager.JRMCoreManagerNC;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class ComJrmcSafeZone
extends CommandBase {
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
        EntityPlayerMP entityplayermp;
        int commandLength = stringArray.length;
        if (commandLength <= 0) {
            throw new WrongUsageException("Usage: " + this.func_71518_a(commandSender), new Object[0]);
        }
        int stringID = 0;
        try {
            entityplayermp = ComJrmcSafeZone.func_82359_c((ICommandSender)commandSender, (String)stringArray[stringID++]);
        }
        catch (Exception e) {
            entityplayermp = ComJrmcSafeZone.func_71521_c((ICommandSender)commandSender);
        }
        String mode = stringArray[stringID++].toLowerCase();
        String masterID = stringArray[stringID++];
        String[] list = this.getMasterIDList();
        int length = list.length;
        int id = -1;
        boolean dbc = JRMCoreH.DBC();
        boolean nc = JRMCoreH.NC();
        for (int i = 0; i < length; ++i) {
            if (!masterID.toLowerCase().equals(list[i].toLowerCase())) continue;
            if (!dbc && i > 0 && i < length - (nc ? 3 : 0) || !nc && i > length - 1 - (nc ? 3 : 0)) break;
            id = i;
            break;
        }
        if (id != -1) {
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
                for (int i = stringID; i < commandLength; ++i) {
                    name = name + stringArray[stringID++].replace("(", "").replace(")", "") + (i + 1 < commandLength && !stringArray[stringID - 1].endsWith(")") ? " " : "");
                    if (stringArray[stringID - 1].endsWith(")")) break;
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
            xPos = x != null && x.length() > 0 && !x.equals("~") ? Double.parseDouble(x) : entityplayermp.field_70165_t;
            yPos = y != null && y.length() > 0 && !y.equals("~") ? Double.parseDouble(y) : entityplayermp.field_70163_u;
            zPos = z != null && z.length() > 0 && !z.equals("~") ? Double.parseDouble(z) : entityplayermp.field_70161_v;
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
                world.func_72838_d((Entity)master);
                if (sendMessage) {
                    this.notifyAdmins(commandSender, "Safe Zone Created: %s by %s", new Object[]{masterID, entityplayermp.func_70005_c_()});
                }
                return;
            }
            int r = 2;
            AxisAlignedBB ab = AxisAlignedBB.func_72330_a((double)(xPos - (double)r), (double)(yPos - (double)r), (double)(zPos - (double)r), (double)(xPos + (double)r), (double)(yPos + (double)r), (double)(zPos + (double)r));
            List list2 = world.func_72872_a(((Object)((Object)master)).getClass(), ab);
            for (int i = 0; list2.size() > i; ++i) {
                EntitySafeZone entity = (EntitySafeZone)((Object)list2.get(i));
                if (!((Object)((Object)entity)).getClass().equals(((Object)((Object)master)).getClass())) continue;
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
            throw new WrongUsageException("ERROR: No Master was found to delete! '" + masterID + "'", new Object[0]);
        }
        throw new WrongUsageException("ERROR: Master Name was not found! '" + masterID + "'", new Object[0]);
    }

    private void notifyAdmins(ICommandSender commandSender, String string, Object[] objects) {
        ComJrmcSafeZone.func_152373_a((ICommandSender)commandSender, (ICommand)this, (String)string, (Object[])objects);
    }

    private String[] getMasterIDList() {
        ArrayList<String> entityNames = new ArrayList<String>();
        entityNames.add("Empty");
        if (JRMCoreH.DBC()) {
            entityNames.addAll(Arrays.asList(JRMCoreManagerDBC.getMasterNames()));
        }
        if (JRMCoreH.NC()) {
            entityNames.addAll(Arrays.asList(JRMCoreManagerNC.getMasterNames()));
        }
        String[] names = new String[entityNames.size()];
        int i = 0;
        Iterator iterator = entityNames.iterator();
        while (iterator.hasNext()) {
            String name;
            names[i] = name = (String)iterator.next();
            ++i;
        }
        return names;
    }

    private EntitySafeZone[] getEntityList(EntityPlayerMP entityplayermp) {
        ArrayList<EntitySafeZone> entityNames = new ArrayList<EntitySafeZone>();
        entityNames.add(new EntitySafeZone(entityplayermp.field_70170_p));
        if (JRMCoreH.DBC()) {
            entityNames.addAll(Arrays.asList(JRMCoreManagerDBC.getMasters(entityplayermp)));
        }
        if (JRMCoreH.NC()) {
            entityNames.addAll(Arrays.asList(JRMCoreManagerNC.getMasters(entityplayermp)));
        }
        EntitySafeZone[] names = new EntitySafeZone[entityNames.size()];
        int i = 0;
        Iterator iterator = entityNames.iterator();
        while (iterator.hasNext()) {
            EntitySafeZone name;
            names[i] = name = (EntitySafeZone)((Object)iterator.next());
            ++i;
        }
        return names;
    }

    public List func_71516_a(ICommandSender par1ICommandSender, String[] arrayString) {
        int length = arrayString.length;
        switch (length) {
            case 1: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])this.getListOfPlayers());
            }
            case 2: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])this.MODES);
            }
            case 3: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])this.getMasterIDList());
            }
            case 4: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"~", "0.0"});
            }
            case 5: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"~", "0.0"});
            }
            case 6: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"~", "0.0"});
            }
            case 7: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"60"});
            }
            case 8: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"60"});
            }
            case 9: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"2"});
            }
            case 10: {
                return ComJrmcSafeZone.func_71530_a((String[])arrayString, (String[])new String[]{"(Safe Zone Name)"});
            }
        }
        return null;
    }

    protected String[] getListOfPlayers() {
        return MinecraftServer.func_71276_C().func_71213_z();
    }
}

