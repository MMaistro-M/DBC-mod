/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 */
package JinRyuu.JYearsC;

import JinRyuu.JRMCore.JYearsCH;
import JinRyuu.JYearsC.JYearsCClient;
import JinRyuu.JYearsC.JYearsCKeyHandler;
import JinRyuu.JYearsC.mod_JYearsC;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;

public class JYearsCCliTickH {
    public Minecraft mc = JYearsCClient.mc;
    public int testing = 0;
    public int check = 0;
    private static int[] mid = JYearsCH.mID;
    public static String[] dm = JYearsCH.dayNames;
    public static String[] mn = JYearsCH.monthNames;
    public int agin = 0;

    public void onRenderTickInGUI(GuiScreen guiscreen) {
        if (this.mc.field_71439_g != null) {
            // empty if block
        }
        if (this.mc.field_71439_g == null || this.testing == 1) {
            // empty if block
        }
    }

    public void onTickInGame() {
        EntityClientPlayerMP plyr = this.mc.field_71439_g;
        if (plyr != null && !plyr.field_70128_L && this.mc.field_71441_e != null && JYearsCKeyHandler.Calendar.func_151470_d()) {
            plyr.openGui((Object)mod_JYearsC.instance, 0, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
        }
    }

    public void onRenderTick() {
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase.equals((Object)TickEvent.Phase.END)) {
            this.onRenderTick();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals((Object)TickEvent.Phase.START)) {
            EntityClientPlayerMP Cplayer = FMLClientHandler.instance().getClientPlayerEntity();
            this.onTickInGame();
        }
    }
}

