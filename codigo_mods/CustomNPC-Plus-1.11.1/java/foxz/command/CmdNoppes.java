/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package foxz.command;

import foxz.command.CmdClone;
import foxz.command.CmdConfig;
import foxz.command.CmdDialog;
import foxz.command.CmdFaction;
import foxz.command.CmdNpc;
import foxz.command.CmdQuest;
import foxz.command.CmdScript;
import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.ParamCheck;
import foxz.commandhelper.permissions.PlayerOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;

@Command(name="noppes", desc="noppes root command", sub={CmdClone.class, CmdScript.class, CmdQuest.class, CmdDialog.class, CmdConfig.class})
public class CmdNoppes
extends ChMcLogger {
    public CmdFaction cmdfaction;
    public CmdNpc cmdnpc;
    public static Map<String, Class<?>> SlayMap = new LinkedHashMap();

    public CmdNoppes(Object sender) {
        super(sender);
        this.cmdfaction = new CmdFaction(this.ctorParm);
        this.cmdnpc = new CmdNpc(this.ctorParm);
        SlayMap.clear();
        SlayMap.put("all", EntityLivingBase.class);
        SlayMap.put("mobs", EntityMob.class);
        SlayMap.put("animals", EntityAnimal.class);
        SlayMap.put("items", EntityItem.class);
        SlayMap.put("xporbs", EntityXPOrb.class);
        SlayMap.put("npcs", EntityNPCInterface.class);
        HashMap list = new HashMap(EntityList.field_75625_b);
        for (String name : list.keySet()) {
            Class cls = (Class)list.get(name);
            if (EntityNPCInterface.class.isAssignableFrom(cls) || !EntityLivingBase.class.isAssignableFrom(cls)) continue;
            SlayMap.put(name.toLowerCase(), (Class<?>)list.get(name));
        }
        SlayMap.remove("monster");
        SlayMap.remove("mob");
    }

    @SubCommand(name="faction", desc="Faction operations", usage="<player> <faction> <command>", permissions={OpOnly.class, ParamCheck.class})
    public Boolean faction(String[] args) {
        String playername = args[0];
        String factionname = args[1];
        this.cmdfaction.data = this.getPlayersData(playername);
        if (this.cmdfaction.data.isEmpty()) {
            this.sendmessage(String.format("Unknow player '%s'", playername));
            return false;
        }
        try {
            this.cmdfaction.selectedFaction = FactionController.getInstance().get(Integer.parseInt(factionname));
        }
        catch (NumberFormatException e) {
            this.cmdfaction.selectedFaction = FactionController.getInstance().getFactionFromName(factionname);
        }
        if (this.cmdfaction.selectedFaction == null) {
            this.sendmessage(String.format("Unknow facion '%s", factionname));
            return false;
        }
        args = Arrays.copyOfRange(args, 2, args.length);
        this.cmdfaction.processCommand(this.pcParam, args);
        for (PlayerData playerdata : this.cmdfaction.data) {
            playerdata.save();
        }
        return true;
    }

    @SubCommand(desc="NPC manipulations", usage="<npc> <command>", permissions={OpOnly.class, ParamCheck.class})
    public boolean npc(String[] args) {
        String npcname = args[0].replace("%", " ");
        if ((args = Arrays.copyOfRange(args, 1, args.length))[0].equalsIgnoreCase("create")) {
            this.cmdnpc.processCommand(this.pcParam, new String[]{args[0], npcname});
            return true;
        }
        int x = this.pcParam.func_82114_b().field_71574_a;
        int y = this.pcParam.func_82114_b().field_71572_b;
        int z = this.pcParam.func_82114_b().field_71573_c;
        List<EntityNPCInterface> list = this.getNearbeEntityFromPlayer(EntityNPCInterface.class, this.pcParam.func_130014_f_(), x, y, z, 80);
        EntityNPCInterface closest = null;
        for (EntityNPCInterface npc : list) {
            String name = npc.display.name.replace(" ", "_");
            if (!name.equalsIgnoreCase(npcname) || closest != null && !(closest.func_70092_e(x, y, z) > npc.func_70092_e(x, y, z))) continue;
            closest = npc;
        }
        if (closest != null) {
            this.cmdnpc.selectedNpc = closest;
            this.cmdnpc.processCommand(this.pcParam, args);
            this.cmdnpc.selectedNpc = null;
        } else {
            this.sendmessage(String.format("NPC '%s' was not found", npcname));
        }
        return true;
    }

    @SubCommand(name="slay", desc="Kills given entity within range. Also has all, mobs, animal options. Can have multiple types", usage="<type>.. [range]", permissions={PlayerOnly.class, OpOnly.class, ParamCheck.class})
    public Boolean slay(String[] args) {
        EntityPlayerMP player = (EntityPlayerMP)this.pcParam;
        ArrayList toDelete = new ArrayList();
        boolean deleteNPCs = false;
        for (String delete : args) {
            Class<?> cls = SlayMap.get(delete = delete.toLowerCase());
            if (cls != null) {
                toDelete.add(cls);
            }
            if (delete.equals("mobs")) {
                toDelete.add(EntityGhast.class);
                toDelete.add(EntityDragon.class);
            }
            if (!delete.equals("npcs")) continue;
            deleteNPCs = true;
        }
        int count = 0;
        int range = 120;
        try {
            range = Integer.parseInt(args[args.length - 1]);
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
        AxisAlignedBB box = player.field_70121_D.func_72314_b((double)range, (double)range, (double)range);
        List list = player.field_70170_p.func_72872_a(EntityLivingBase.class, box);
        for (Entity entity : list) {
            if (entity instanceof EntityPlayer || entity instanceof EntityTameable && ((EntityTameable)entity).func_70909_n() || entity instanceof EntityNPCInterface && !deleteNPCs || !this.delete(entity, toDelete)) continue;
            ++count;
        }
        if (toDelete.contains(EntityXPOrb.class)) {
            list = player.field_70170_p.func_72872_a(EntityXPOrb.class, box);
            for (Entity entity : list) {
                entity.field_70128_L = true;
                ++count;
            }
        }
        if (toDelete.contains(EntityItem.class)) {
            list = player.field_70170_p.func_72872_a(EntityItem.class, box);
            for (Entity entity : list) {
                entity.field_70128_L = true;
                ++count;
            }
        }
        player.func_145747_a((IChatComponent)new ChatComponentTranslation(count + " entities deleted", new Object[0]));
        return true;
    }

    private boolean delete(Entity entity, ArrayList<Class<?>> toDelete) {
        for (Class<?> delete : toDelete) {
            if (delete == EntityAnimal.class && entity instanceof EntityHorse || !delete.isAssignableFrom(entity.getClass())) continue;
            entity.field_70128_L = true;
            return true;
        }
        return false;
    }

    @Override
    public List addTabCompletion(ICommandSender par1, String[] args) {
        if (args[0].equalsIgnoreCase("slay")) {
            return CommandBase.func_71530_a((String[])args, (String[])SlayMap.keySet().toArray(new String[SlayMap.size()]));
        }
        if (args[0].equalsIgnoreCase("npc") && args.length == 3) {
            return this.cmdnpc.addTabCompletion(par1, Arrays.copyOfRange(args, 1, args.length));
        }
        if (args[0].equalsIgnoreCase("faction") && args.length == 4) {
            return CommandBase.func_71530_a((String[])args, (String[])new String[]{"add", "subtract", "set", "reset", "drop", "create"});
        }
        return super.addTabCompletion(par1, args);
    }
}

