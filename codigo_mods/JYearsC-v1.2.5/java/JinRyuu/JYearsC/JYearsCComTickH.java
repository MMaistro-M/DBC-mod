/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.WorldServer
 */
package JinRyuu.JYearsC;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JYearsCH;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.p.YC.JYearsCP;
import JinRyuu.JRMCore.p.YC.JYearsCPData;
import JinRyuu.JYearsC.JYearsCConfig;
import JinRyuu.JYearsC.mod_JYearsC;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class JYearsCComTickH {
    boolean charge = false;
    EntityPlayer player;
    private int tick = 0;
    private String date = "";
    private int tccb = 0;
    private static int[] mid = JYearsCH.mID;
    public static String[] dm = JYearsCH.dayNames;
    public static String[] mn = JYearsCH.monthNames;
    public static String[] datnc0 = null;
    public static String sentDatnc0 = null;
    String[] cp = null;

    private static int m(MinecraftServer server, int m, int y) {
        m = m + 1 == 4 ? JYearsCComTickH.y(server, y) : m + 1;
        JYearsCH.wcd(server, m + "", "m", false);
        return 0;
    }

    private static int y(MinecraftServer server, int y) {
        y = y > 1000000 ? 0 : y + 1;
        JYearsCH.wcd(server, y + "", "y", false);
        return 0;
    }

    public void serverTick(MinecraftServer server) {
        WorldServer dim0 = server.func_71218_a(0);
        int cur = server.func_71233_x();
        if (datnc0 == null || datnc0.length != cur) {
            datnc0 = new String[cur];
        }
        if (cur == 0 && sentDatnc0 != null) {
            sentDatnc0 = null;
            datnc0 = null;
        }
        for (int pl = 0; pl < cur; ++pl) {
            EntityPlayerMP p = server.func_71203_ab().func_152612_a(server.func_71213_z()[pl]);
            if (this.tick == (cur > 100 ? (int)((float)pl - 100.0f * ((float)pl / 100.0f)) : (int)(100.0f / (float)cur * (float)pl))) {
                int y = 0;
                byte m = 0;
                byte d = 0;
                try {
                    y = Integer.parseInt(JYearsCH.rcd(server, "y"));
                    m = Byte.parseByte(JYearsCH.rcd(server, "m"));
                    d = Byte.parseByte(JYearsCH.rcd(server, "d"));
                }
                catch (Exception e) {
                    y = 1;
                    m = 0;
                    d = 1;
                }
                String jycp = ":";
                int n = 32;
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(p.field_70165_t - (double)n), (double)(p.field_70163_u - (double)n), (double)(p.field_70161_v - (double)n), (double)(p.field_70165_t + (double)n), (double)(p.field_70163_u + (double)n), (double)(p.field_70161_v + (double)n));
                List l = p.field_70170_p.func_72872_a(EntityPlayer.class, aabb);
                for (int i = 0; i < l.size(); ++i) {
                    EntityPlayer p2 = (EntityPlayer)l.get(i);
                    jycp = jycp + ":" + p2.getDisplayName() + ";" + JRMCoreH.getFloat(p2, "JRYCAge");
                }
                int jycdatey = y;
                byte jycdatem = m;
                byte jycdated = d;
                int jycpy = (int)p.field_70163_u;
                PD.sendTo(new JYearsCP(jycdatey, jycdatem, jycdated, jycp, jycpy), p);
            }
            long t = dim0.func_72820_D() % 24000L;
            float a = JRMCoreH.getFloat((EntityPlayer)p, "JRYCAge");
            if (t == 1L || t == 6001L || t == 12001L || t == 18001L) {
                JRMCoreH.setFloat(a + 0.25f, (EntityPlayer)p, "JRYCAge");
                a = JRMCoreH.getFloat((EntityPlayer)p, "JRYCAge");
                int mls = JYearsCConfig.pls;
                int n = mls = mls < 20 ? 20 : mls;
                if (t == 6001L && a > (float)(mls - 10)) {
                    p.openGui((Object)mod_JYearsC.instance, 0, p.field_70170_p, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v);
                }
                if (t == 6001L && a > (float)mls) {
                    if (p.field_70170_p.field_73012_v.nextInt(5) == 0) {
                        p.func_70097_a(DamageSource.field_76377_j, 20000.0f);
                        JRMCoreH.setFloat(0, (EntityPlayer)p, "JRYCAge");
                    } else {
                        p.func_70097_a(DamageSource.field_76377_j, 4.0f);
                        p.func_145747_a((IChatComponent)new ChatComponentText("You getting Old. If you don't Rebirth Then you will die."));
                    }
                }
            }
            if (JRMCoreH.DBC() && p.field_71093_bK == 23) {
                for (int i = 0; i < 24; ++i) {
                    if (t != (long)(i * 1000)) continue;
                    JRMCoreH.setFloat(a + 4.0f, (EntityPlayer)p, "JRYCAge");
                }
            }
            if (this.date.length() <= 3) continue;
            p.func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + this.date));
        }
        if (this.date.length() > 3) {
            mod_JYearsC.logger.info(this.date);
            this.date = "0";
        }
        if (this.tccb > 0) {
            --this.tccb;
        } else {
            long tm = dim0.func_72820_D() % 24000L;
            if (tm >= 0L && tm <= 2L) {
                this.tccb = 20;
                String Y = JYearsCH.rcd(server, "y");
                String M = JYearsCH.rcd(server, "m");
                String D = JYearsCH.rcd(server, "d");
                int y = Integer.parseInt(Y != null && Y.length() > 0 ? Y : "0");
                int m = Integer.parseInt(M != null && M.length() > 0 ? M : "0");
                int d = Integer.parseInt(D != null && D.length() > 0 ? D : "0");
                int dy = d + 1 >= mid[m >= mid.length ? 0 : m] ? JYearsCComTickH.m(server, m, y) : d + 1;
                JYearsCH.wcd(server, dy + "", "d", false);
                Y = JYearsCH.rcd(server, "y");
                M = JYearsCH.rcd(server, "m");
                D = JYearsCH.rcd(server, "d");
                y = Integer.parseInt(Y != null && Y.length() > 0 ? Y : "0");
                m = Integer.parseInt(M != null && M.length() > 0 ? M : "0");
                d = Integer.parseInt(D != null && D.length() > 0 ? D : "0");
                int days = 0;
                int z = 0;
                for (int k = 0; k < y % dm.length + 1; ++k) {
                    for (int i = 0; i < mid.length; ++i) {
                        for (int j = 0; j < mid[i]; ++j) {
                            if (days > 4) {
                                days = 0;
                            }
                            if (i == m && j == d && k == y % dm.length) {
                                z = days;
                            }
                            ++days;
                        }
                    }
                }
                this.date = dm[z] + " on " + (d + 1) + " of " + mn[m] + " in " + y;
            }
        }
        this.sendToP(datnc0, sentDatnc0, cur, 0, server);
        if (this.tick >= 100) {
            this.tick = -1;
        }
        ++this.tick;
    }

    private void send(String[] temp, String send, int cur, int c) {
        if (temp != null) {
            String s = ":";
            for (int i = 0; i < cur; ++i) {
                if (temp[i] == null) continue;
                s = s + ":" + temp[i];
            }
            if (!(s = s.replaceAll("::", "")).equals(send) && !s.equals(":")) {
                JYearsCComTickH.JYearsCPData(c, s);
                JYearsCComTickH.sdm(s, c);
            }
            JYearsCComTickH.adn(c);
        }
    }

    private void sendToP(String[] temp, String send, int cur, int c, MinecraftServer server) {
        if (temp != null) {
            int i;
            String s = ":";
            for (i = 0; i < cur; ++i) {
                if (temp[i] == null) continue;
                s = s + ":" + temp[i];
            }
            if (!(s = s.replaceAll("::", "")).equals(send) && !s.equals(":")) {
                for (i = 0; i < cur; ++i) {
                    String[] s2;
                    EntityPlayerMP player = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[i]);
                    String s1 = temp[i];
                    String[] stringArray = s2 = send != null ? send.split(":") : null;
                    if (temp.length - 1 < i || s2 != null && s2.length > i && s1.equals(s2[i])) continue;
                    JYearsCComTickH.JYearsCPDataToP(c, s1, (EntityPlayer)player);
                }
                JYearsCComTickH.sdm(s, c);
            }
            JYearsCComTickH.adn(c);
        }
    }

    public static void sdm(String d, int c) {
        if (c == 0) {
            sentDatnc0 = d;
        }
    }

    public static void adn(int c) {
        if (c == 0) {
            datnc0 = null;
        }
    }

    private static void JYearsCPData(int d, String s) {
        PD.sendToAll(new JYearsCPData(d, s));
    }

    private static void JYearsCPDataToP(int d, String s, EntityPlayer p) {
        PD.sendTo(new JYearsCPData(d, s), (EntityPlayerMP)p);
    }

    private void onTickInGame() {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        this.serverTick(server);
    }

    public void onPlayerTick(EntityPlayer player) {
    }

    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent event) {
        if (event.phase.equals((Object)TickEvent.Phase.START)) {
            this.onTickInGame();
        }
    }
}

