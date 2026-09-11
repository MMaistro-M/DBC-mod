/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package foxz.commandhelper;

import foxz.commandhelper.AbstractCommandHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ChMcLogger
extends AbstractCommandHelper {
    public ChMcLogger(Object sender) {
        super(sender);
    }

    public void sendmessage(String msg) {
        ICommandSender sender = this.pcParam;
        sender.func_145747_a((IChatComponent)new ChatComponentText(msg));
    }

    @Override
    public void help(String cmd, String desc, String usa) {
        if (usa.isEmpty()) {
            this.sendmessage(String.format("%s = %s", cmd, desc));
        } else {
            this.sendmessage(String.format("%s %s = %s", cmd, usa, desc));
        }
    }

    @Override
    public void cmdError(String cmd) {
        this.sendmessage(String.format("Unknow command '%s'", cmd));
    }

    @Override
    public void error(String err) {
        this.sendmessage(String.format("Error: %s", err));
    }
}

