/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  net.minecraft.client.Minecraft
 */
package invtweaks.forge;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import invtweaks.InvTweaks;
import net.minecraft.client.Minecraft;

public class ForgeClientTick {
    private InvTweaks instance;

    public ForgeClientTick(InvTweaks inst) {
        this.instance = inst;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent tick) {
        if (tick.phase == TickEvent.Phase.START) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            if (mc.field_71441_e != null) {
                if (mc.field_71462_r != null) {
                    this.instance.onTickInGUI(mc.field_71462_r);
                } else {
                    this.instance.onTickInGame();
                }
            }
        }
    }
}

