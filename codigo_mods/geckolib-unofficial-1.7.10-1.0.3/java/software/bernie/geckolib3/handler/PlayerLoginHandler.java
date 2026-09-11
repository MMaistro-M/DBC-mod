/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 */
package software.bernie.geckolib3.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import software.bernie.geckolib3.resource.AnimationLibrary;
import software.bernie.geckolib3.resource.ModelLibrary;

public class PlayerLoginHandler {
    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        ModelLibrary.instance.syncPlayer(event.player);
        AnimationLibrary.instance.syncPlayer(event.player);
    }
}

