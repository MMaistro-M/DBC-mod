/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package foxz.utils;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class Utils {
    public static <T> List<T> getNearbeEntityFromPlayer(Class<? extends T> cls, EntityPlayerMP player, int dis) {
        AxisAlignedBB range = player.field_70121_D.func_72314_b((double)dis, (double)dis, (double)dis);
        List list = player.field_70170_p.func_72872_a(cls, range);
        return list;
    }

    public static EntityPlayer getOnlinePlayer(String playername) {
        return MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(playername);
    }

    public static World getWorld(String t) {
        WorldServer[] ws;
        for (WorldServer w : ws = MinecraftServer.func_71276_C().field_71305_c) {
            if (w == null || !(w.field_73011_w.field_76574_g + "").equalsIgnoreCase(t)) continue;
            return w;
        }
        return null;
    }
}

