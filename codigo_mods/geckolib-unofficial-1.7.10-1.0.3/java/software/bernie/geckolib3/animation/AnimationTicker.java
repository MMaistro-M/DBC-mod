/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  net.minecraft.client.Minecraft
 */
package software.bernie.geckolib3.animation;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import software.bernie.geckolib3.core.manager.AnimationData;

public class AnimationTicker {
    private final AnimationData data;

    public AnimationTicker(AnimationData data) {
        this.data = data;
    }

    @SubscribeEvent
    public void tickEvent(TickEvent.ClientTickEvent event) {
        if (Minecraft.func_71410_x().func_147113_T() && !this.data.shouldPlayWhilePaused) {
            return;
        }
        if (event.phase == TickEvent.Phase.END) {
            this.data.tick += 1.0;
        }
    }
}

