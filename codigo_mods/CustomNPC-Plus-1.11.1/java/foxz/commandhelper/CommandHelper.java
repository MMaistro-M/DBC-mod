/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 */
package foxz.commandhelper;

import java.util.List;
import net.minecraft.command.ICommandSender;

public abstract class CommandHelper {
    public Helper commandHelper = new Helper();

    public List addTabCompletion(ICommandSender par1, String[] args) {
        return null;
    }

    public class Helper {
        public String name;
        public String usage;
        public String desc;
        public boolean hasEmptyCall;
    }
}

