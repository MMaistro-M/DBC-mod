/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerNotFoundException
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package kamkeel.npcs.command;

import java.util.Arrays;
import java.util.List;
import kamkeel.npcs.command.CommandKamkeelBase;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.RoleFollower;
import org.apache.commons.lang3.ArrayUtils;

public class NpcCommand
extends CommandKamkeelBase {
    public EntityNPCInterface selectedNpc;

    public String func_71517_b() {
        return "npc";
    }

    @Override
    public String getDescription() {
        return "NPC operation";
    }

    @Override
    public String getUsage() {
        return "<name> <command>";
    }

    @Override
    public boolean runSubCommands() {
        return false;
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        String npcname = args[0].replace("%", " ");
        String command = args[1];
        args = Arrays.copyOfRange(args, 2, args.length);
        if (command.equalsIgnoreCase("create")) {
            args = ArrayUtils.add(args, 0, npcname);
            this.processSubCommand(sender, command, args);
            return;
        }
        int x = sender.func_82114_b().field_71574_a;
        int y = sender.func_82114_b().field_71572_b;
        int z = sender.func_82114_b().field_71573_c;
        List<EntityNPCInterface> list = this.getEntities(EntityNPCInterface.class, sender.func_130014_f_(), x, y, z, 80);
        for (EntityNPCInterface npc : list) {
            String name = npc.display.getName().replace(" ", "_");
            if (!name.equalsIgnoreCase(npcname) || this.selectedNpc != null && !(this.selectedNpc.func_70092_e(x, y, z) > npc.func_70092_e(x, y, z))) continue;
            this.selectedNpc = npc;
        }
        if (this.selectedNpc == null) {
            ColorUtil.sendError(sender, "Npc " + npcname + " was not found");
            return;
        }
        this.processSubCommand(sender, command, args);
        this.selectedNpc = null;
    }

    @CommandKamkeelBase.SubCommand(desc="Set Home (respawn place)", usage="[x] [y] [z]")
    public void home(ICommandSender sender, String[] args) {
        double posX = sender.func_82114_b().field_71574_a;
        double posY = sender.func_82114_b().field_71572_b;
        double posZ = sender.func_82114_b().field_71573_c;
        if (args.length == 3) {
            posX = CommandBase.func_110666_a((ICommandSender)sender, (double)this.selectedNpc.field_70165_t, (String)args[0]);
            posY = CommandBase.func_110665_a((ICommandSender)sender, (double)this.selectedNpc.field_70163_u, (String)args[1].trim(), (int)0, (int)0);
            posZ = CommandBase.func_110666_a((ICommandSender)sender, (double)this.selectedNpc.field_70161_v, (String)args[2]);
        }
        this.selectedNpc.ais.startPos = new int[]{MathHelper.func_76128_c((double)posX), MathHelper.func_76128_c((double)posY), MathHelper.func_76128_c((double)posZ)};
        ColorUtil.sendResult(sender, String.format("Set NPC \u00a7e%s\u00a77 Home to \u00a7b[%.1f] [%.1f] [%.1f]\u00a77", this.selectedNpc.display.name, posX, posY, posZ));
    }

    @CommandKamkeelBase.SubCommand(desc="Set NPC visibility", usage="[true/false/semi]")
    public void visible(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            return;
        }
        boolean bo = args[0].equalsIgnoreCase("true");
        boolean semi = args[0].equalsIgnoreCase("semi");
        int current = this.selectedNpc.display.visible;
        this.selectedNpc.display.visible = semi ? 2 : (bo ? 0 : 1);
        String[] list = new String[]{"True", "False", "Semi"};
        if (current != this.selectedNpc.display.visible) {
            this.selectedNpc.updateClient = true;
        }
        ColorUtil.sendResult(sender, String.format("Set NPC \u00a7e%s\u00a77 Visibility to \u00a7b%s\u00a77", this.selectedNpc.display.name, list[this.selectedNpc.display.visible]));
    }

    @CommandKamkeelBase.SubCommand(desc="Delete an NPC")
    public void delete(ICommandSender sender, String[] args) {
        this.selectedNpc.delete();
    }

    @CommandKamkeelBase.SubCommand(desc="Sets the owner of an follower/companion", usage="[player]")
    public void owner(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            EntityPlayer player = null;
            if (this.selectedNpc.roleInterface instanceof RoleFollower) {
                player = ((RoleFollower)this.selectedNpc.roleInterface).owner;
            }
            if (this.selectedNpc.roleInterface instanceof RoleCompanion) {
                player = ((RoleCompanion)this.selectedNpc.roleInterface).owner;
            }
            if (player == null) {
                ColorUtil.sendResult(sender, String.format("NPC \u00a7e%s\u00a77 Owner: \u00a7b%s\u00a77", this.selectedNpc.display.name, "NULL"));
            } else {
                ColorUtil.sendResult(sender, String.format("NPC \u00a7e%s\u00a77 Owner: \u00a7b%s\u00a77", this.selectedNpc.display.name, player.getDisplayName()));
            }
        } else {
            EntityPlayerMP player = null;
            try {
                player = CommandBase.func_82359_c((ICommandSender)sender, (String)args[0]);
            }
            catch (PlayerNotFoundException playerNotFoundException) {
                // empty catch block
            }
            if (this.selectedNpc.roleInterface instanceof RoleFollower) {
                ((RoleFollower)this.selectedNpc.roleInterface).setOwner((EntityPlayer)player);
            }
            if (this.selectedNpc.roleInterface instanceof RoleCompanion) {
                ((RoleCompanion)this.selectedNpc.roleInterface).setOwner((EntityPlayer)player);
            }
            if (player == null) {
                ColorUtil.sendResult(sender, String.format("NPC \u00a7e%s\u00a77 Owner: \u00a7b%s\u00a77", this.selectedNpc.display.name, "NULL"));
            } else {
                ColorUtil.sendResult(sender, String.format("NPC \u00a7e%s\u00a77 Owner: \u00a7b%s\u00a77", this.selectedNpc.display.name, player.getDisplayName()));
            }
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Set NPC name", usage="[name]")
    public void name(ICommandSender sender, String[] args) {
        if (args.length < 1) {
            return;
        }
        String name = args[0];
        for (int i = 1; i < args.length; ++i) {
            name = name + " " + args[i];
        }
        ColorUtil.sendResult(sender, String.format("NPC \u00a7e%s\u00a77 Name set to \u00a7b%s\u00a77", this.selectedNpc.display.name, name));
        if (!this.selectedNpc.display.getName().equals(name)) {
            this.selectedNpc.display.setName(name);
            this.selectedNpc.updateClient = true;
        }
    }

    @CommandKamkeelBase.SubCommand(desc="Creates an NPC", usage="[name]")
    public void create(ICommandSender sender, String[] args) {
        EntityPlayerMP player = (EntityPlayerMP)sender;
        World pw = player.func_130014_f_();
        EntityCustomNpc npc = new EntityCustomNpc(pw);
        if (args.length > 0) {
            npc.display.name = args[0];
        }
        npc.func_70080_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, 0.0f);
        npc.ais.startPos = new int[]{MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v)};
        pw.func_72838_d((Entity)npc);
        npc.func_70606_j(npc.func_110138_aP());
        NoppesUtilServer.sendOpenGui((EntityPlayer)player, EnumGuiType.MainMenuDisplay, npc);
    }

    public List func_71516_a(ICommandSender par1, String[] args) {
        if (args.length == 2) {
            return CommandBase.func_71530_a((String[])args, (String[])new String[]{"create", "home", "visible", "delete", "owner", "name"});
        }
        if (args.length == 3 && args[1].equalsIgnoreCase("owner")) {
            return CommandBase.func_71530_a((String[])args, (String[])MinecraftServer.func_71276_C().func_71213_z());
        }
        return null;
    }

    @Override
    public int func_82362_a() {
        return 4;
    }

    public <T extends Entity> List<T> getEntities(Class<? extends T> cls, World world, int x, int y, int z, int range) {
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)).func_72314_b((double)range, (double)range, (double)range);
        List list = world.func_72872_a(cls, bb);
        return list;
    }
}

