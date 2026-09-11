/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package foxz.command;

import foxz.command.CmdNoppes;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

@Deprecated
public class CommandNoppes
extends CommandBase {
    public CmdNoppes noppes = new CmdNoppes((Object)this);

    public String func_71517_b() {
        return this.noppes.commandHelper.name;
    }

    public String func_71518_a(ICommandSender var1) {
        return this.noppes.commandHelper.usage;
    }

    public void func_71515_b(ICommandSender var1, String[] var2) {
        var1.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + "[DEPRECATED] " + EnumChatFormatting.YELLOW + "The /noppes command is deprecated and no longer supported."));
        var1.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.YELLOW + "Please use " + EnumChatFormatting.GREEN + "/kam" + EnumChatFormatting.YELLOW + " commands instead."));
        var1.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.GRAY + "Type " + EnumChatFormatting.WHITE + "/kam help" + EnumChatFormatting.GRAY + " for available commands."));
        this.noppes.processCommand(var1, var2);
    }

    public List func_71516_a(ICommandSender par1, String[] par2) {
        return this.noppes.addTabCompletion(par1, par2);
    }

    public int func_82362_a() {
        return 2;
    }
}

