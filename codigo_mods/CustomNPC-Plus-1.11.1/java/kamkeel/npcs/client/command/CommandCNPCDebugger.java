/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package kamkeel.npcs.client.command;

import kamkeel.npcs.util.CNPCDebug;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommandCNPCDebugger
extends CommandBase {
    public String func_71517_b() {
        return "cnpcdebugger";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/cnpcdebugger <type> - Toggle client debug logging for a type (e.g. energy)";
    }

    public int func_82362_a() {
        return 0;
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.func_145747_a((IChatComponent)new ChatComponentText("\u00a7cUsage: /cnpcdebugger <type>"));
            return;
        }
        String type = args[0].toLowerCase();
        boolean newState = CNPCDebug.toggleClient(type);
        String stateText = newState ? "\u00a7aENABLED" : "\u00a7cDISABLED";
        sender.func_145747_a((IChatComponent)new ChatComponentText("\u00a77[CNPC+] \u00a7eClient debug '" + type + "' " + stateText));
    }
}

