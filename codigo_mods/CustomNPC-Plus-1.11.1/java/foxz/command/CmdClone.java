/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package foxz.command;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import foxz.commandhelper.permissions.PlayerOnly;
import foxz.utils.Utils;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.entity.EntityNPCInterface;

@Command(name="clone", desc="Clone operation (server side)")
public class CmdClone
extends ChMcLogger {
    public CmdClone(Object sender) {
        super(sender);
    }

    @SubCommand(desc="Add NPC(s) to clone storage", usage="<npc> <tab> [clonedname]", permissions={OpOnly.class, PlayerOnly.class, ParamCheck.class})
    public Boolean add(String[] args) {
        EntityPlayerMP player = (EntityPlayerMP)this.pcParam;
        int tab = 0;
        try {
            tab = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        List<EntityNPCInterface> list = Utils.getNearbeEntityFromPlayer(EntityNPCInterface.class, player, 80);
        for (EntityNPCInterface npc : list) {
            NBTTagCompound compound;
            if (!npc.display.name.equalsIgnoreCase(args[0])) continue;
            String name = npc.display.name;
            if (args.length > 2) {
                name = args[2];
            }
            if (!npc.func_70039_c(compound = new NBTTagCompound())) {
                return false;
            }
            ServerCloneController.Instance.addClone(compound, name, tab);
            return true;
        }
        return true;
    }

    @SubCommand(desc="List NPC from clone storage", usage="<tab>", permissions={OpOnly.class, ParamCheck.class})
    public Boolean list(String[] args) {
        this.sendmessage("--- Stored NPCs --- (server side)");
        int tab = 0;
        try {
            tab = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        for (String name : ServerCloneController.Instance.getClones(tab)) {
            this.sendmessage(name);
        }
        this.sendmessage("------------------------------------");
        return true;
    }

    @SubCommand(desc="Remove NPC from clone storage", usage="<name> <tab>", permissions={OpOnly.class, ParamCheck.class})
    public Boolean del(String[] args) {
        String nametodel = args[0];
        int tab = 0;
        try {
            tab = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        boolean deleted = false;
        for (String name : ServerCloneController.Instance.getClones(tab)) {
            if (!nametodel.equalsIgnoreCase(name)) continue;
            ServerCloneController.Instance.removeClone(name, tab);
            deleted = true;
            break;
        }
        if (!ServerCloneController.Instance.removeClone(nametodel, tab)) {
            this.sendmessage(String.format("Npc '%s' wasn't found", nametodel));
            return false;
        }
        return true;
    }

    @SubCommand(desc="Spawn cloned NPC", usage="<name> <tab> [[world:]x,y,z]] [newname]", permissions={OpOnly.class, ParamCheck.class})
    public boolean spawn(String[] args) {
        String name = args[0].replaceAll("%", " ");
        int tab = 0;
        try {
            tab = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        String newname = null;
        NBTTagCompound compound = ServerCloneController.Instance.getCloneData(this.pcParam, name, tab);
        if (compound == null) {
            this.sendmessage("Unknown npc");
            return false;
        }
        World world = this.pcParam.func_130014_f_();
        double posX = this.pcParam.func_82114_b().field_71574_a;
        double posY = this.pcParam.func_82114_b().field_71572_b;
        double posZ = this.pcParam.func_82114_b().field_71573_c;
        if (args.length > 2) {
            String[] par;
            String location = args[2];
            if (location.contains(":")) {
                par = location.split(":");
                location = par[1];
                world = Utils.getWorld(par[0]);
                if (world == null) {
                    this.sendmessage(String.format("'%s' is an unknown world", par[0]));
                    return false;
                }
            }
            if (location.contains(",")) {
                par = location.split(",");
                if (par.length != 3) {
                    this.sendmessage("Location need be x,y,z");
                    return false;
                }
                try {
                    posX = CommandBase.func_110666_a((ICommandSender)this.pcParam, (double)posX, (String)par[0]);
                    posY = CommandBase.func_110665_a((ICommandSender)this.pcParam, (double)posY, (String)par[1].trim(), (int)0, (int)0);
                    posZ = CommandBase.func_110666_a((ICommandSender)this.pcParam, (double)posZ, (String)par[2]);
                }
                catch (NumberFormatException ex) {
                    this.sendmessage("Location should be in numbers");
                    return false;
                }
                if (args.length > 3) {
                    newname = args[3];
                }
            } else {
                newname = location;
            }
        }
        if (posX == 0.0 && posY == 0.0 && posZ == 0.0) {
            this.sendmessage("Location needed");
            return false;
        }
        Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world);
        entity.func_70107_b(posX + 0.5, posY + 1.0, posZ + 0.5);
        if (entity instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)entity;
            npc.ais.startPos = new int[]{MathHelper.func_76128_c((double)posX), MathHelper.func_76128_c((double)posY), MathHelper.func_76128_c((double)posZ)};
            if (newname != null && !newname.isEmpty()) {
                npc.display.name = newname.replaceAll("%", " ");
            }
        }
        world.func_72838_d(entity);
        return true;
    }

    @SubCommand(desc="Spawn cloned NPC", usage="<name> <tab> <lenght> <width> [[world:]x,y,z]] [newname]", permissions={OpOnly.class, ParamCheck.class})
    public boolean grid(String[] args) {
        int height;
        int width;
        String name = args[0].replaceAll("%", " ");
        int tab = 0;
        try {
            tab = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        try {
            width = Integer.parseInt(args[2]);
            height = Integer.parseInt(args[3]);
        }
        catch (NumberFormatException ex) {
            this.sendmessage("lenght or width wasnt a number");
            return false;
        }
        String newname = null;
        NBTTagCompound compound = ServerCloneController.Instance.getCloneData(this.pcParam, name, tab);
        if (compound == null) {
            this.sendmessage("Unknown npc");
            return false;
        }
        World world = this.pcParam.func_130014_f_();
        double posX = this.pcParam.func_82114_b().field_71574_a;
        double posY = this.pcParam.func_82114_b().field_71572_b;
        double posZ = this.pcParam.func_82114_b().field_71573_c;
        if (args.length > 4) {
            String[] par;
            String location = args[4];
            if (location.contains(":")) {
                par = location.split(":");
                location = par[1];
                world = Utils.getWorld(par[0]);
                if (world == null) {
                    this.sendmessage(String.format("'%s' is an unknown world", par[0]));
                    return false;
                }
            }
            if (location.contains(",")) {
                par = location.split(",");
                if (par.length != 3) {
                    this.sendmessage("Location need be x,y,z");
                    return false;
                }
                try {
                    posX = CommandBase.func_110666_a((ICommandSender)this.pcParam, (double)posX, (String)par[0]);
                    posY = CommandBase.func_110665_a((ICommandSender)this.pcParam, (double)posY, (String)par[1].trim(), (int)0, (int)0);
                    posZ = CommandBase.func_110666_a((ICommandSender)this.pcParam, (double)posZ, (String)par[2]);
                }
                catch (NumberFormatException ex) {
                    this.sendmessage("Location should be in numbers");
                    return false;
                }
                if (args.length > 5) {
                    newname = args[5];
                }
            } else {
                newname = location;
            }
        }
        if (posX == 0.0 && posY == 0.0 && posZ == 0.0) {
            this.sendmessage("Location needed");
            return false;
        }
        for (int x = 0; x < width; ++x) {
            for (int z = 0; z < height; ++z) {
                Entity entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world);
                int xx = MathHelper.func_76128_c((double)posX) + x;
                int yy = Math.max(MathHelper.func_76128_c((double)posY) - 2, 1);
                int zz = MathHelper.func_76128_c((double)posZ) + z;
                for (int y = 0; y < 10; ++y) {
                    Block b = world.func_147439_a(xx, yy + y, zz);
                    Block b2 = world.func_147439_a(xx, yy + y + 1, zz);
                    if (b == null || b2 != null && b2.func_149668_a(world, xx, yy + y + 1, zz) != null) continue;
                    yy += y;
                    break;
                }
                entity.func_70107_b(posX + 0.5 + (double)x, (double)(yy + 1), posZ + 0.5 + (double)z);
                if (entity instanceof EntityNPCInterface) {
                    EntityNPCInterface npc = (EntityNPCInterface)entity;
                    npc.ais.startPos = new int[]{xx, yy, zz};
                    if (newname != null && !newname.isEmpty()) {
                        npc.display.name = newname.replaceAll("%", " ");
                    }
                }
                world.func_72838_d(entity);
            }
        }
        return true;
    }
}

