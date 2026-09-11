/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 */
package hedaox.ninjinentities.lib;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Util {
    public static Minecraft mc = Minecraft.func_71410_x();

    public static EntityPlayer getNearbyPlayer(String playername, double range) {
        if (Util.mc.field_71439_g != null) {
            List players = Util.mc.field_71439_g.field_70170_p.func_72872_a(EntityPlayer.class, Util.mc.field_71439_g.field_70121_D.func_72314_b(range, range, range));
            for (EntityPlayer p : players) {
                if (p == null || !p.func_70005_c_().equalsIgnoreCase(playername)) continue;
                return p;
            }
        }
        return null;
    }
}

