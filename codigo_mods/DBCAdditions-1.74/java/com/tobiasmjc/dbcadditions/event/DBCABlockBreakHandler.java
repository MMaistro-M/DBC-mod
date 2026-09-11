/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 *  net.minecraftforge.event.world.ExplosionEvent$Start
 */
package com.tobiasmjc.dbcadditions.event;

import com.tobiasmjc.dbcadditions.DBCAConfig;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ExplosionEvent;

public class DBCABlockBreakHandler {
    private int beerusID = 99;

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        int dimensionID = event.world.field_73011_w.field_76574_g;
        if (dimensionID == this.beerusID && !DBCAConfig.BeerusGriefing && event.getPlayer() != null && !event.getPlayer().field_71075_bZ.field_75098_d) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onBlockPlace(BlockEvent.PlaceEvent event) {
        if (event.world.field_73011_w.field_76574_g == this.beerusID && !DBCAConfig.BeerusGriefing && event.player != null && !event.player.field_71075_bZ.field_75098_d) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onExplosion(ExplosionEvent.Start event) {
        if (event.world.field_73011_w.field_76574_g == this.beerusID && !DBCAConfig.BeerusGriefing) {
            event.setCanceled(true);
        }
    }
}

