/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.FamilyC.mod_FamilyC;
import JinRyuu.JRMCore.FamilyCH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class FamilyCComTickH {
    private int tick = 0;
    private boolean ro = true;

    public void serverTick(MinecraftServer server) {
        if (this.ro && FamilyCConfig.dcr) {
            FamilyCH.wpfdD(server);
            FamilyCH.wcfdD(server);
            this.ro = false;
        }
        int cur = server.func_71233_x();
        for (int pl = 0; pl < cur; ++pl) {
            try {
                String[] prt;
                String prid;
                int l;
                EntityPlayerMP player = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                if (this.tick != (cur > 100 ? (int)((float)pl - 100.0f * ((float)pl / 100.0f)) : (int)(100.0f / (float)cur * (float)pl))) continue;
                String fmd = FamilyCH.rfmd(server, player.func_70005_c_());
                if (fmd.contains("1")) {
                    JRMCoreH.setString("0", (EntityPlayer)player, FamilyCH.FID);
                    JRMCoreH.setString("0", (EntityPlayer)player, FamilyCH.FIDo);
                    FamilyCH.wfmd(server, "0", player.func_70005_c_(), true);
                }
                String fid = JRMCoreH.getString((EntityPlayer)player, FamilyCH.FID);
                String fd = FamilyCH.rfi(server, fid);
                String[] famD = fd.split("!");
                int i2 = 0;
                boolean b = false;
                if (famD != null && famD.length > 0) {
                    for (int i = 0; i < famD.length; ++i) {
                        String n = famD[i];
                        String[] fm = n.split(",");
                        for (int f1 = 0; f1 < fm.length; ++f1) {
                            String n2 = fm[f1];
                            String[] n3 = n2.split(":");
                            if (n3[0].equals(player.func_70005_c_())) {
                                b = true;
                            }
                            ++i2;
                        }
                    }
                }
                if (!b && fid.length() > 1) {
                    JRMCoreH.setString("0", (EntityPlayer)player, FamilyCH.FID);
                    JRMCoreH.setString("0", (EntityPlayer)player, FamilyCH.FIDo);
                }
                if ((l = (prid = JRMCoreH.getString((EntityPlayer)player, FamilyCH.prID)).length()) == 0) {
                    JRMCoreH.setString("v", (EntityPlayer)player, FamilyCH.prID);
                }
                if (l <= 2 || !prid.contains(";") || (prt = prid.toString().split(";")).length <= 3) continue;
                int i = Integer.parseInt(prt[4]) - 1;
                if (i <= 0) {
                    boolean bool = true;
                    while (bool) {
                        Random ran = new Random();
                        int r = ran.nextInt(1000000);
                        if (FamilyCH.rcfd(server, r + "").length() >= 2) continue;
                        FamilyCH.wcfd(server, prt[1] + ":" + prt[2] + ":" + prt[3], r, false);
                        String pm = FamilyCH.rpfd(server, prt[1]);
                        String pd = FamilyCH.rpfd(server, prt[2]);
                        pm = (pm.contains(";") || pm.length() > 2 ? pm + ";" : "") + r + ":" + prt[2];
                        FamilyCH.wpfd(server, pm, prt[1], false);
                        pd = (pd.contains(";") || pd.length() > 2 ? pd + ";" : "") + r + ":" + prt[1];
                        FamilyCH.wpfd(server, pd, prt[2], false);
                        EntityNPC c = new EntityNPC(player.field_70170_p, prt[0], prt[1], prt[2], prt[3], r, prt[5]);
                        c.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, 0.0f, 0.0f);
                        c.setCnam((byte)1);
                        c.setNPCAge(0.5f);
                        player.field_70170_p.func_72838_d((Entity)c);
                        JRMCoreH.setString("b", (EntityPlayer)player, FamilyCH.prID);
                        if (JRMCoreConfig.DebugInfo) {
                            mod_FamilyC.logger.info("ChildData: DNS:" + prt[0] + " Hair:" + prt[5]);
                        }
                        bool = false;
                    }
                    continue;
                }
                String s = prt[0] + ";" + prt[1] + ";" + prt[2] + ";" + prt[3] + ";" + i + ";" + prt[5];
                JRMCoreH.setString(s, (EntityPlayer)player, FamilyCH.prID);
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        if (this.tick >= 100) {
            this.tick = -1;
        }
        ++this.tick;
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

