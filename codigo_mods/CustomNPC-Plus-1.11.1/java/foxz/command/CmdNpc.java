/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package foxz.command;

import foxz.commandhelper.ChMcLogger;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.OpOnly;
import foxz.commandhelper.permissions.PlayerOnly;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.RoleFollower;

@Command(name="npc", desc="NPC manipulation", usage="<name> <command>")
public class CmdNpc
extends ChMcLogger {
    public EntityNPCInterface selectedNpc;

    CmdNpc(Object ctorParm) {
        super(ctorParm);
    }

    @SubCommand(desc="Set Home (respawn place)", usage="")
    public void home(String[] args) {
        double posX = this.pcParam.func_82114_b().field_71574_a;
        double posY = this.pcParam.func_82114_b().field_71572_b;
        double posZ = this.pcParam.func_82114_b().field_71573_c;
        if (args.length == 3) {
            posX = CommandBase.func_110666_a((ICommandSender)this.pcParam, (double)this.selectedNpc.field_70165_t, (String)args[0]);
            posY = CommandBase.func_110665_a((ICommandSender)this.pcParam, (double)this.selectedNpc.field_70163_u, (String)args[1].trim(), (int)0, (int)0);
            posZ = CommandBase.func_110666_a((ICommandSender)this.pcParam, (double)this.selectedNpc.field_70161_v, (String)args[2]);
        }
        this.selectedNpc.ais.startPos = new int[]{MathHelper.func_76128_c((double)posX), MathHelper.func_76128_c((double)posY), MathHelper.func_76128_c((double)posZ)};
    }

    @SubCommand(desc="Set npc visibility", usage="")
    public void visible(String[] args) {
        if (args.length < 1) {
            return;
        }
        boolean bo = args[0].equalsIgnoreCase("true");
        boolean semi = args[0].equalsIgnoreCase("semi");
        int current = this.selectedNpc.display.visible;
        this.selectedNpc.display.visible = semi ? 2 : (bo ? 0 : 1);
        if (current != this.selectedNpc.display.visible) {
            this.selectedNpc.updateClient = true;
        }
    }

    @SubCommand(desc="Delete an NPC", usage="")
    public void delete(String[] args) {
        this.selectedNpc.delete();
    }

    @SubCommand(desc="Owner", usage="")
    public void owner(String[] args) {
        if (args.length < 1) {
            EntityPlayer player = null;
            if (this.selectedNpc.roleInterface instanceof RoleFollower) {
                player = ((RoleFollower)this.selectedNpc.roleInterface).owner;
            }
            if (this.selectedNpc.roleInterface instanceof RoleCompanion) {
                player = ((RoleCompanion)this.selectedNpc.roleInterface).owner;
            }
            if (player == null) {
                this.sendmessage("No owner");
            } else {
                this.sendmessage("Owner is: " + player.func_70005_c_());
            }
        } else {
            EntityPlayerMP player = CommandBase.func_82359_c((ICommandSender)this.pcParam, (String)args[0]);
            if (this.selectedNpc.roleInterface instanceof RoleFollower) {
                ((RoleFollower)this.selectedNpc.roleInterface).setOwner((EntityPlayer)player);
            }
            if (this.selectedNpc.roleInterface instanceof RoleCompanion) {
                ((RoleCompanion)this.selectedNpc.roleInterface).setOwner((EntityPlayer)player);
            }
        }
    }

    @SubCommand(desc="Set npc name", usage="")
    public void name(String[] args) {
        if (args.length < 1) {
            return;
        }
        String name = args[0];
        for (int i = 1; i < args.length; ++i) {
            name = name + " " + args[i];
        }
        if (!this.selectedNpc.display.name.equals(name)) {
            this.selectedNpc.display.name = name;
            this.selectedNpc.updateClient = true;
        }
    }

    @SubCommand(desc="Creates an NPC", usage="[name]", permissions={PlayerOnly.class, OpOnly.class})
    public void create(String[] args) {
        EntityPlayerMP player = (EntityPlayerMP)this.pcParam;
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

    @Override
    public List addTabCompletion(ICommandSender par1, String[] args) {
        if (args.length == 2) {
            return CommandBase.func_71530_a((String[])args, (String[])new String[]{"create", "home", "visible", "delete", "owner", "name"});
        }
        if (args.length == 3 && args[1].equalsIgnoreCase("owner")) {
            return CommandBase.func_71530_a((String[])args, (String[])MinecraftServer.func_71276_C().func_71213_z());
        }
        return super.addTabCompletion(par1, args);
    }
}

