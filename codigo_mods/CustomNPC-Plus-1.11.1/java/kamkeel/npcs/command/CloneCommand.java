/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package kamkeel.npcs.command;

import foxz.utils.Utils;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.block.Block;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.data.CloneFolder;
import noppes.npcs.entity.EntityNPCInterface;

public class CloneCommand
extends CommandKamkeelBase {
    public String func_71517_b() {
        return "clone";
    }

    @Override
    public String getDescription() {
        return "Clone operation (server side)";
    }

    @CommandKamkeelBase.SubCommand(desc="Add NPC(s) to clone storage", usage="<npc> <tab|folder> [clonedname]", permission=4)
    public void add(ICommandSender sender, String[] args) {
        String folder;
        int tab;
        block7: {
            tab = -1;
            folder = null;
            try {
                tab = Integer.parseInt(args[1]);
                if (tab < 1 || tab > 15) {
                    ColorUtil.sendError(sender, "Tab must be within 1-15");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                folder = args[1];
                if (ServerCloneController.Instance != null && ServerCloneController.Instance.hasFolder(folder)) break block7;
                ColorUtil.sendError(sender, String.format("Unknown folder: %s", folder));
                return;
            }
        }
        int x = sender.func_82114_b().field_71574_a;
        int y = sender.func_82114_b().field_71572_b;
        int z = sender.func_82114_b().field_71573_c;
        List<EntityNPCInterface> list = this.getEntities(EntityNPCInterface.class, sender.func_130014_f_(), x, y, z, 80);
        for (EntityNPCInterface npc : list) {
            NBTTagCompound compound;
            if (!npc.display.getName().equalsIgnoreCase(args[0])) continue;
            String name = npc.display.getName();
            if (args.length > 2) {
                name = args[2];
            }
            if (!npc.func_70039_c(compound = new NBTTagCompound())) {
                return;
            }
            if (folder != null) {
                ServerCloneController.Instance.addClone(compound, name, folder);
                ColorUtil.sendResult(sender, String.format("Added NPC \u00a7e%s\u00a77 to Folder \u00a7b%s\u00a77", name, folder));
                continue;
            }
            ServerCloneController.Instance.addClone(compound, name, tab);
            ColorUtil.sendResult(sender, String.format("Added NPC \u00a7e%s\u00a77 to Tab \u00a7b%d\u00a77", name, tab));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="List NPC from clone storage", usage="<tab|folder>", permission=2)
    public void list(ICommandSender sender, String[] args) {
        String folder;
        int tab;
        block4: {
            ColorUtil.sendMessage(sender, "--- Stored NPCs --- (server side)");
            tab = -1;
            folder = null;
            try {
                tab = Integer.parseInt(args[1]);
                if (tab < 1 || tab > 15) {
                    ColorUtil.sendError(sender, "Tab must be within 1-15");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                folder = args[1];
                if (ServerCloneController.Instance != null && ServerCloneController.Instance.hasFolder(folder)) break block4;
                ColorUtil.sendError(sender, String.format("Unknown folder: %s", folder));
                return;
            }
        }
        List<String> clones = folder != null ? ServerCloneController.Instance.getClones(folder) : ServerCloneController.Instance.getClones(tab);
        for (String name : clones) {
            ColorUtil.sendMessage(sender, name);
        }
        ColorUtil.sendMessage(sender, "------------------------------------");
    }

    @CommandKamkeelBase.SubCommand(desc="Remove NPC from clone storage", usage="<name> <tab|folder>", permission=4)
    public void del(ICommandSender sender, String[] args) throws CommandException {
        boolean success;
        String folder;
        int tab;
        String nametodel;
        block7: {
            nametodel = args[0];
            tab = -1;
            folder = null;
            try {
                tab = Integer.parseInt(args[1]);
                if (tab < 1 || tab > 15) {
                    ColorUtil.sendError(sender, "Tab must be within 1-15");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                folder = args[1];
                if (ServerCloneController.Instance != null && ServerCloneController.Instance.hasFolder(folder)) break block7;
                ColorUtil.sendError(sender, String.format("Unknown folder: %s", folder));
                return;
            }
        }
        if (!(success = folder != null ? ServerCloneController.Instance.removeClone(nametodel, folder) : ServerCloneController.Instance.removeClone(nametodel, tab))) {
            ColorUtil.sendError(sender, String.format("NPC '%s' was not found", nametodel));
        } else if (folder != null) {
            ColorUtil.sendResult(sender, String.format("Removed NPC \u00a7e%s\u00a77 from Folder \u00a7b%s\u00a77", nametodel, folder));
        } else {
            ColorUtil.sendResult(sender, String.format("Removed NPC \u00a7e%s\u00a77 from Tab \u00a7b%d\u00a77", nametodel, tab));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Spawn cloned NPC", usage="<name> <tab|folder> [[world:]x,y,z]] [newname]", permission=2)
    public void spawn(ICommandSender sender, String[] args) throws CommandException {
        String folder;
        int tab;
        String name;
        block16: {
            name = args[0].replaceAll("%", " ");
            tab = -1;
            folder = null;
            try {
                tab = Integer.parseInt(args[1]);
                if (tab < 1 || tab > 15) {
                    ColorUtil.sendError(sender, "Tab must be within 1-15");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                folder = args[1];
                if (ServerCloneController.Instance != null && ServerCloneController.Instance.hasFolder(folder)) break block16;
                ColorUtil.sendError(sender, String.format("Unknown folder: %s", folder));
                return;
            }
        }
        String newname = null;
        NBTTagCompound compound = folder != null ? ServerCloneController.Instance.getCloneData(sender, name, folder) : ServerCloneController.Instance.getCloneData(sender, name, tab);
        if (compound == null) {
            ColorUtil.sendError(sender, "Unknown npc");
            return;
        }
        World world = sender.func_130014_f_();
        double posX = sender.func_82114_b().field_71574_a;
        double posY = sender.func_82114_b().field_71572_b;
        double posZ = sender.func_82114_b().field_71573_c;
        if (args.length > 2) {
            String[] par;
            String location = args[2];
            if (location.contains(":")) {
                par = location.split(":");
                location = par[1];
                world = Utils.getWorld(par[0]);
                if (world == null) {
                    ColorUtil.sendError(sender, String.format("'%s' is an unknown world", par[0]));
                    return;
                }
            }
            if (location.contains(",")) {
                par = location.split(",");
                if (par.length != 3) {
                    ColorUtil.sendError(sender, "Location need be x,y,z");
                    return;
                }
                try {
                    posX = CloneCommand.func_110666_a((ICommandSender)sender, (double)posX, (String)par[0]);
                    posY = CloneCommand.func_110665_a((ICommandSender)sender, (double)posY, (String)par[1].trim(), (int)0, (int)0);
                    posZ = CloneCommand.func_110666_a((ICommandSender)sender, (double)posZ, (String)par[2]);
                }
                catch (NumberFormatException ex) {
                    ColorUtil.sendError(sender, "Location should be in numbers");
                    return;
                }
                if (args.length > 3) {
                    newname = args[3];
                }
            } else {
                newname = location;
            }
        }
        if (posX == 0.0 && posY == 0.0 && posZ == 0.0) {
            ColorUtil.sendError(sender, "Location needed");
            return;
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
    }

    @CommandKamkeelBase.SubCommand(desc="Spawn cloned NPC in a grid", usage="<name> <tab|folder> <width> <height> [[world:]x,y,z]] [newname]", permission=2)
    public void grid(ICommandSender sender, String[] args) throws CommandException {
        int height;
        int width;
        String folder;
        int tab;
        String name;
        block21: {
            name = args[0].replaceAll("%", " ");
            tab = -1;
            folder = null;
            try {
                tab = Integer.parseInt(args[1]);
                if (tab < 1 || tab > 15) {
                    ColorUtil.sendError(sender, "Tab must be within 1-15");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                folder = args[1];
                if (ServerCloneController.Instance != null && ServerCloneController.Instance.hasFolder(folder)) break block21;
                ColorUtil.sendError(sender, String.format("Unknown folder: %s", folder));
                return;
            }
        }
        try {
            width = Integer.parseInt(args[2]);
            height = Integer.parseInt(args[3]);
        }
        catch (NumberFormatException ex) {
            ColorUtil.sendError(sender, "length or width was not a number");
            return;
        }
        String newname = null;
        NBTTagCompound compound = folder != null ? ServerCloneController.Instance.getCloneData(sender, name, folder) : ServerCloneController.Instance.getCloneData(sender, name, tab);
        if (compound == null) {
            ColorUtil.sendError(sender, "Unknown npc");
            return;
        }
        World world = sender.func_130014_f_();
        double posX = sender.func_82114_b().field_71574_a;
        double posY = sender.func_82114_b().field_71572_b;
        double posZ = sender.func_82114_b().field_71573_c;
        if (args.length > 4) {
            String[] par;
            String location = args[4];
            if (location.contains(":")) {
                par = location.split(":");
                location = par[1];
                world = Utils.getWorld(par[0]);
                if (world == null) {
                    ColorUtil.sendError(sender, String.format("'%s' is an unknown world", par[0]));
                }
            }
            if (location.contains(",")) {
                par = location.split(",");
                if (par.length != 3) {
                    ColorUtil.sendError(sender, "Location need be x,y,z");
                    return;
                }
                try {
                    posX = CloneCommand.func_110666_a((ICommandSender)sender, (double)posX, (String)par[0]);
                    posY = CloneCommand.func_110665_a((ICommandSender)sender, (double)posY, (String)par[1].trim(), (int)0, (int)0);
                    posZ = CloneCommand.func_110666_a((ICommandSender)sender, (double)posZ, (String)par[2]);
                }
                catch (NumberFormatException ex) {
                    ColorUtil.sendError(sender, "Location should be in numbers");
                    return;
                }
                if (args.length > 5) {
                    newname = args[5];
                }
            } else {
                newname = location;
            }
        }
        if (posX == 0.0 && posY == 0.0 && posZ == 0.0) {
            ColorUtil.sendError(sender, "Location needed");
            return;
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
    }

    @CommandKamkeelBase.SubCommand(desc="List all custom clone folders", usage="", permission=2)
    public void listfolders(ICommandSender sender, String[] args) {
        if (ServerCloneController.Instance == null) {
            ColorUtil.sendError(sender, "Folder system not initialized");
            return;
        }
        ColorUtil.sendMessage(sender, "--- Clone Folders ---");
        for (CloneFolder f : ServerCloneController.Instance.getFolderList()) {
            ColorUtil.sendMessage(sender, f.name);
        }
        ColorUtil.sendMessage(sender, "------------------------------------");
    }

    @CommandKamkeelBase.SubCommand(desc="Create a custom clone folder", usage="<name>", permission=4)
    public void createfolder(ICommandSender sender, String[] args) {
        if (ServerCloneController.Instance == null) {
            ColorUtil.sendError(sender, "Folder system not initialized");
            return;
        }
        String name = args[0];
        if (!CloneFolder.isValidName(name)) {
            ColorUtil.sendError(sender, String.format("Invalid folder name: %s", name));
            return;
        }
        if (ServerCloneController.Instance.hasFolder(name)) {
            ColorUtil.sendError(sender, String.format("Folder '%s' already exists", name));
            return;
        }
        CloneFolder created = ServerCloneController.Instance.createFolder(name);
        if (created != null) {
            ColorUtil.sendResult(sender, String.format("Created folder \u00a7b%s", name));
        } else {
            ColorUtil.sendError(sender, String.format("Failed to create folder '%s'", name));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Rename a custom clone folder", usage="<oldname> <newname>", permission=4)
    public void renamefolder(ICommandSender sender, String[] args) {
        if (ServerCloneController.Instance == null) {
            ColorUtil.sendError(sender, "Folder system not initialized");
            return;
        }
        String oldName = args[0];
        String newName = args[1];
        if (!ServerCloneController.Instance.hasFolder(oldName)) {
            ColorUtil.sendError(sender, String.format("Unknown folder: %s", oldName));
            return;
        }
        if (!CloneFolder.isValidName(newName)) {
            ColorUtil.sendError(sender, String.format("Invalid folder name: %s", newName));
            return;
        }
        if (ServerCloneController.Instance.renameFolder(oldName, newName)) {
            ColorUtil.sendResult(sender, String.format("Renamed folder \u00a7b%s\u00a77 to \u00a7b%s", oldName, newName));
        } else {
            ColorUtil.sendError(sender, String.format("Failed to rename folder '%s'", oldName));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Delete an empty custom clone folder", usage="<name>", permission=4)
    public void deletefolder(ICommandSender sender, String[] args) {
        if (ServerCloneController.Instance == null) {
            ColorUtil.sendError(sender, "Folder system not initialized");
            return;
        }
        String name = args[0];
        if (!ServerCloneController.Instance.hasFolder(name)) {
            ColorUtil.sendError(sender, String.format("Unknown folder: %s", name));
            return;
        }
        if (ServerCloneController.Instance.deleteFolder(name)) {
            ColorUtil.sendResult(sender, String.format("Deleted folder \u00a7b%s", name));
        } else {
            ColorUtil.sendError(sender, String.format("Failed to delete folder '%s' (folder must be empty)", name));
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Move a clone between tabs/folders", usage="<clonename> <from_tab|folder> <to_tab|folder>", permission=4)
    public void move(ICommandSender sender, String[] args) {
        boolean success;
        String toFolder;
        int toTab;
        String fromFolder;
        int fromTab;
        String cloneName;
        block10: {
            block9: {
                if (ServerCloneController.Instance == null) {
                    ColorUtil.sendError(sender, "Folder system not initialized");
                    return;
                }
                cloneName = args[0];
                fromTab = -1;
                fromFolder = null;
                try {
                    fromTab = Integer.parseInt(args[1]);
                    if (fromTab < 1 || fromTab > 15) {
                        ColorUtil.sendError(sender, "Source tab must be within 1-15");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    fromFolder = args[1];
                    if (ServerCloneController.Instance.hasFolder(fromFolder)) break block9;
                    ColorUtil.sendError(sender, String.format("Unknown source folder: %s", fromFolder));
                    return;
                }
            }
            toTab = -1;
            toFolder = null;
            try {
                toTab = Integer.parseInt(args[2]);
                if (toTab < 1 || toTab > 15) {
                    ColorUtil.sendError(sender, "Destination tab must be within 1-15");
                    return;
                }
            }
            catch (NumberFormatException ex) {
                toFolder = args[2];
                if (ServerCloneController.Instance.hasFolder(toFolder)) break block10;
                ColorUtil.sendError(sender, String.format("Unknown destination folder: %s", toFolder));
                return;
            }
        }
        if (success = fromFolder != null && toFolder != null ? ServerCloneController.Instance.moveClone(cloneName, fromFolder, toFolder) : (fromFolder != null ? ServerCloneController.Instance.moveClone(cloneName, fromFolder, toTab) : (toFolder != null ? ServerCloneController.Instance.moveClone(cloneName, fromTab, toFolder) : ServerCloneController.Instance.moveClone(cloneName, fromTab, toTab)))) {
            String from = fromFolder != null ? fromFolder : String.valueOf(fromTab);
            String to = toFolder != null ? toFolder : String.valueOf(toTab);
            ColorUtil.sendResult(sender, String.format("Moved \u00a7e%s\u00a77 from \u00a7b%s\u00a77 to \u00a7b%s", cloneName, from, to));
        } else {
            ColorUtil.sendError(sender, String.format("Failed to move '%s'", cloneName));
        }
    }

    public World getWorld(String t) {
        WorldServer[] ws;
        for (WorldServer w : ws = MinecraftServer.func_71276_C().field_71305_c) {
            if (w == null || !(w.field_73011_w.field_76574_g + "").equalsIgnoreCase(t)) continue;
            return w;
        }
        return null;
    }

    public <T extends Entity> List<T> getEntities(Class<? extends T> cls, World world, int x, int y, int z, int range) {
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)).func_72314_b((double)range, (double)range, (double)range);
        List list = world.func_72872_a(cls, bb);
        return list;
    }
}

