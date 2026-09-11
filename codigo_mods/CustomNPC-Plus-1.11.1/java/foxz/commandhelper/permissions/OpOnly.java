/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 */
package foxz.commandhelper.permissions;

import foxz.commandhelper.AbstractCommandHelper;
import foxz.commandhelper.permissions.AbstractPermission;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class OpOnly
extends AbstractPermission {
    @Override
    public String errorMsg() {
        return "Op Only";
    }

    @Override
    public boolean delegate(AbstractCommandHelper parent, String[] args) {
        if (!(parent.pcParam instanceof EntityPlayer)) {
            return true;
        }
        return MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(((EntityPlayer)parent.pcParam).func_146103_bH());
    }
}

