/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package foxz.commandhelper.permissions;

import foxz.commandhelper.AbstractCommandHelper;
import foxz.commandhelper.permissions.AbstractPermission;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerOnly
extends AbstractPermission {
    @Override
    public String errorMsg() {
        return "Player Only";
    }

    @Override
    public boolean delegate(AbstractCommandHelper parent, String[] args) {
        return parent.pcParam instanceof EntityPlayer;
    }
}

