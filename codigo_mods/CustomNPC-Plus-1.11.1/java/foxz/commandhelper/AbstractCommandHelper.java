/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.PlayerSelector
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package foxz.commandhelper;

import foxz.commandhelper.CommandHelper;
import foxz.commandhelper.annotations.Command;
import foxz.commandhelper.annotations.SubCommand;
import foxz.commandhelper.permissions.AbstractPermission;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;

public abstract class AbstractCommandHelper
extends CommandHelper {
    public Object ctorParm;
    public ICommandSender pcParam;
    public Object xParam;
    public AbstractCommandHelper parentCmdHelper;
    public AbstractCommandHelper rootCmdHelper;
    public Map<String, CommandHelper> commands = new HashMap<String, CommandHelper>();
    public CommandHelper.Helper currentHelper;

    public AbstractCommandHelper(Object sender) {
        this.ctorParm = sender;
        this.ctor();
    }

    public void ctor() {
        this.commandHelper.name = this.getClass().getAnnotation(Command.class).name();
        this.commandHelper.usage = this.getClass().getAnnotation(Command.class).usage();
        this.commandHelper.desc = this.getClass().getAnnotation(Command.class).desc();
        for (Class clazz : this.getClass().getAnnotation(Command.class).sub()) {
            try {
                String name = clazz.getAnnotation(Command.class).name().toUpperCase();
                Constructor ctor = clazz.getConstructor(Object.class);
                ctor.setAccessible(true);
                AbstractCommandHelper sc = (AbstractCommandHelper)ctor.newInstance(this.ctorParm);
                this.commands.put(name, sc);
            }
            catch (Exception ex) {
                Logger.getLogger(AbstractCommandHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (GenericDeclaration genericDeclaration : this.getClass().getDeclaredMethods()) {
            SubCommand sc = ((Method)genericDeclaration).getAnnotation(SubCommand.class);
            if (sc == null) continue;
            String name = sc.name();
            if (name.equals("")) {
                name = ((Method)genericDeclaration).getName();
            }
            this.commands.put(name.toUpperCase(), new MethodSubCmd(this, (Method)genericDeclaration));
        }
    }

    public abstract void help(String var1, String var2, String var3);

    public abstract void cmdError(String var1);

    public abstract void error(String var1);

    public void allHelp() {
        for (CommandHelper cur : this.commands.values()) {
            this.help(cur.commandHelper.name, cur.commandHelper.desc, "");
        }
        this.sendMsg(String.format("\u00a74noppes cmds are no longer supported |\u00a76 use /kamkeel", new Object[0]));
        this.sendMsg(String.format("\u00a77All bugs and issues will not be patched or maintained", new Object[0]));
    }

    public void sendMsg(String msg) {
        ICommandSender sender = this.pcParam;
        sender.func_145747_a((IChatComponent)new ChatComponentText(msg));
    }

    public Boolean processCommand(ICommandSender param, String[] args) {
        this.pcParam = param;
        if (this.parentCmdHelper == null) {
            this.rootCmdHelper = this;
        }
        if (args.length == 0) {
            this.allHelp();
            return true;
        }
        String cmd = args[0].toUpperCase();
        args = Arrays.copyOfRange(args, 1, args.length);
        if ((cmd.equals("HELP") || args.length == 0) && this.doHelp(param, args, cmd)) {
            return true;
        }
        CommandHelper ch = this.commands.get(cmd);
        if (ch == null) {
            this.cmdError(cmd);
            return false;
        }
        if (ch instanceof AbstractCommandHelper) {
            AbstractCommandHelper f = (AbstractCommandHelper)ch;
            f.parentCmdHelper = this;
            f.rootCmdHelper = this.rootCmdHelper;
            return f.processCommand(param, args);
        }
        if (ch instanceof MethodSubCmd) {
            MethodSubCmd m = (MethodSubCmd)ch;
            m.method.setAccessible(true);
            this.currentHelper = ch.commandHelper;
            try {
                for (AbstractPermission p : m.permissions) {
                    if (p.delegate(this, args)) continue;
                    this.error(p.errorMsg());
                    return false;
                }
                return (Boolean)m.method.invoke((Object)this, new Object[]{args});
            }
            catch (Exception ex) {
                Logger.getLogger(AbstractCommandHelper.class.getName()).log(Level.SEVERE, m.commandHelper.name, ex);
                return true;
            }
        }
        this.cmdError(cmd);
        return false;
    }

    private boolean doHelp(ICommandSender param, String[] args, String cmd) {
        CommandHelper ch;
        boolean isHelp = cmd.equals("HELP");
        if (args.length > 0) {
            cmd = args[0];
        }
        if ((ch = this.commands.get(cmd.toUpperCase())) != null) {
            if (ch.commandHelper.hasEmptyCall && !isHelp) {
                return false;
            }
            if (ch instanceof AbstractCommandHelper) {
                ((AbstractCommandHelper)ch).pcParam = param;
                ((AbstractCommandHelper)ch).allHelp();
            } else {
                if (ch instanceof MethodSubCmd && ((MethodSubCmd)ch).commandHelper.usage.isEmpty()) {
                    return false;
                }
                this.help(ch.commandHelper.name, ch.commandHelper.desc, ch.commandHelper.usage);
            }
        } else {
            this.allHelp();
        }
        return true;
    }

    @Override
    public List addTabCompletion(ICommandSender par1, String[] args) {
        if (args.length <= 1) {
            ArrayList<String> list = new ArrayList<String>();
            for (String command : this.commands.keySet()) {
                list.add(command.toLowerCase());
            }
            list.add("help");
            return CommandBase.func_71530_a((String[])args, (String[])list.toArray(new String[list.size()]));
        }
        CommandHelper ch = this.commands.get(args[0].toUpperCase());
        if (ch == null) {
            return null;
        }
        args = Arrays.copyOfRange(args, 1, args.length);
        this.currentHelper = ch.commandHelper;
        return ch.addTabCompletion(par1, args);
    }

    public List<PlayerData> getPlayersData(String username) {
        ArrayList<PlayerData> list = new ArrayList<PlayerData>();
        EntityPlayerMP[] players = PlayerSelector.func_82380_c((ICommandSender)this.pcParam, (String)username);
        if (players == null || players.length == 0) {
            PlayerData data = PlayerDataController.Instance.getDataFromUsername(username);
            if (data != null) {
                list.add(data);
            }
        } else {
            for (EntityPlayerMP player : players) {
                list.add(PlayerData.get((EntityPlayer)player));
            }
        }
        return list;
    }

    public <T> List<T> getNearbeEntityFromPlayer(Class<? extends T> cls, World world, int x, int y, int z, int range) {
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)).func_72314_b((double)range, (double)range, (double)range);
        List list = world.func_72872_a(cls, bb);
        return list;
    }

    protected class MethodSubCmd
    extends CommandHelper {
        public List<AbstractPermission> permissions = new ArrayList<AbstractPermission>();
        public Method method;

        public MethodSubCmd(AbstractCommandHelper ch, Method m) {
            SubCommand s = m.getAnnotation(SubCommand.class);
            this.commandHelper.name = s.name();
            if (this.commandHelper.name.equals("")) {
                this.commandHelper.name = m.getName();
            }
            this.commandHelper.usage = s.usage();
            this.commandHelper.desc = s.desc();
            this.commandHelper.hasEmptyCall = s.hasEmptyCall();
            this.method = m;
            for (Class c : s.permissions()) {
                try {
                    Constructor ctor = c.getDeclaredConstructor(new Class[0]);
                    ctor.setAccessible(true);
                    AbstractPermission i = (AbstractPermission)ctor.newInstance(new Object[0]);
                    this.permissions.add(i);
                }
                catch (Exception ex) {
                    Logger.getLogger(AbstractCommandHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public List addTabCompletion(ICommandSender par1, String[] args) {
            String[] np = AbstractCommandHelper.this.currentHelper.usage.split(" ");
            if (np.length < args.length) {
                return null;
            }
            String parameter = np[args.length - 1];
            if (parameter.equals("<player>")) {
                return CommandBase.func_71530_a((String[])args, (String[])MinecraftServer.func_71276_C().func_71213_z());
            }
            return null;
        }
    }
}

