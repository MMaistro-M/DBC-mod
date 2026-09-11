/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargePreviewManager;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.client.event.RenderWorldLastEvent;

public class EnergyChargePreviewRenderer {
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        if (EnergyChargePreviewManager.ClientInstance == null || !EnergyChargePreviewManager.ClientInstance.hasPreviews()) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        EntityClientPlayerMP player = mc.field_71439_g;
        if (player == null) {
            return;
        }
        float partialTicks = event.partialTicks;
        for (EntityEnergyProjectile entity : EnergyChargePreviewManager.ClientInstance.getPreviews()) {
            if (entity == null || entity.field_70128_L) continue;
            double x = entity.field_70169_q + (entity.field_70165_t - entity.field_70169_q) * (double)partialTicks - RenderManager.field_78725_b;
            double y = entity.field_70167_r + (entity.field_70163_u - entity.field_70167_r) * (double)partialTicks - RenderManager.field_78726_c;
            double z = entity.field_70166_s + (entity.field_70161_v - entity.field_70166_s) * (double)partialTicks - RenderManager.field_78723_d;
            RenderManager.field_78727_a.func_147940_a((Entity)entity, x, y, z, entity.field_70177_z, partialTicks);
        }
    }
}

