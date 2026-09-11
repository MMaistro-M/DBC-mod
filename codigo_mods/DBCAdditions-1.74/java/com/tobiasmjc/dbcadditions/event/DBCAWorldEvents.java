/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.event.world.WorldEvent$Load
 */
package com.tobiasmjc.dbcadditions.event;

import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.JRMCoreMsnBundle;
import com.tobiasmjc.dbcadditions.data.missions.DBCAMissions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.world.WorldEvent;

public class DBCAWorldEvents {
    @SubscribeEvent
    public void onServerStart(WorldEvent.Load event) {
        MinecraftServer s = MinecraftServer.func_71276_C();
        if (s == null || s.field_71305_c.length <= 0 || s.func_71218_a(0) == null || s.func_71218_a(0).getChunkSaveLocation() == null) {
            return;
        }
        String missions = s.func_71218_a(0).getChunkSaveLocation() + "/data/missions";
        File dbcaDBSSH = new File(missions, "dbcaDBSSH.json");
        JRMCoreMsnBundle missionSideDBSSH = DBCAMissions.genSuperHeroSaga();
        if (!dbcaDBSSH.exists()) {
            JRMCoreM.msnGenWrt(dbcaDBSSH, missionSideDBSSH);
            JRMCoreM.missions.put("dbcaDBSSH", missionSideDBSSH);
        } else {
            JRMCoreMsnBundle rms = JRMCoreM.rd(dbcaDBSSH);
            JRMCoreM.missions.put("dbcaDBSSH", missionSideDBSSH.getVersion().equalsIgnoreCase(missionSideDBSSH.getVersion()) ? rms : missionSideDBSSH);
        }
    }
}

