/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.FamilyCH;
import JinRyuu.JRMCore.JRMCoreComTickH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.server.MinecraftServer;

public class FamilyCDataThread
extends Thread {
    public boolean run = true;

    @Override
    public void run() {
        while (this.run) {
            MinecraftServer server = JRMCoreComTickH.server;
            if (server != null && server.func_71278_l()) {
                if (!JRMCoreH.paused) {
                    FamilyCH.familyCNBT = FamilyCH.readFamilyInfoFromNBT(server);
                    FamilyCH.lastUpdateTick = 0;
                    try {
                        FamilyCDataThread.sleep(JRMCoreConfig.serverPlayerUpdateTick_DataFamilyC * 1000);
                    }
                    catch (InterruptedException interruptedException) {}
                    continue;
                }
                FamilyCH.lastUpdateTick = 0;
                try {
                    FamilyCDataThread.sleep(1000L);
                }
                catch (InterruptedException interruptedException) {}
                continue;
            }
            FamilyCH.lastUpdateTick = 0;
            this.run = false;
        }
    }
}

