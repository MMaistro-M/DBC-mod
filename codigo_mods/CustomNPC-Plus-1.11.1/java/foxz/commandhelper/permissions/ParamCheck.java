/*
 * Decompiled with CFR 0.152.
 */
package foxz.commandhelper.permissions;

import foxz.commandhelper.AbstractCommandHelper;
import foxz.commandhelper.permissions.AbstractPermission;

public class ParamCheck
extends AbstractPermission {
    String err;

    @Override
    public String errorMsg() {
        return this.err;
    }

    @Override
    public boolean delegate(AbstractCommandHelper parent, String[] args) {
        String[] np = parent.currentHelper.usage.split(" ");
        int countRequired = 0;
        for (String command : np) {
            if (!command.startsWith("<")) continue;
            ++countRequired;
        }
        if (args.length < countRequired) {
            this.err = np[args.length] + " missing";
            return false;
        }
        return true;
    }
}

