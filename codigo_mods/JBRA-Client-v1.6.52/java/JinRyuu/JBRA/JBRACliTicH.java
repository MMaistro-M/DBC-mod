/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.player.EntityPlayer
 */
package JinRyuu.JBRA;

import JinRyuu.JBRA.JBRAEnRen;
import JinRyuu.JBRA.RenderPlayerJBRA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;

public class JBRACliTicH {
    public Minecraft mc = JRMCoreClient.mc;
    private EntityRenderer renderer;
    private EntityRenderer prevRenderer;
    private RenderPlayerJBRA RenderPlayerJBRA = new RenderPlayerJBRA();

    public void onPreRenderTick() {
        EntityClientPlayerMP plyr = this.mc.field_71439_g;
        if (plyr != null && !plyr.field_70128_L && this.mc.field_71441_e != null) {
            boolean jbraview = true;
            if (jbraview) {
                if (JRMCoreConfig.forceJBRA && !(RenderManager.field_78727_a.field_78729_o.get(EntityPlayer.class) instanceof RenderPlayerJBRA)) {
                    RenderManager.field_78727_a.field_78729_o.put(EntityPlayer.class, this.RenderPlayerJBRA);
                    this.RenderPlayerJBRA.func_76976_a(RenderManager.field_78727_a);
                }
                if (this.renderer == null) {
                    this.renderer = new JBRAEnRen(this.mc);
                }
                if (this.mc.field_71460_t != this.renderer) {
                    this.prevRenderer = this.mc.field_71460_t;
                    this.mc.field_71460_t = this.renderer;
                }
            } else if (this.prevRenderer != null && this.mc.field_71460_t != this.prevRenderer) {
                this.mc.field_71460_t = this.prevRenderer;
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            this.onPreRenderTick();
        }
    }
}

