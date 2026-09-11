/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.client.particles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class ParticleManager {
    public static final ParticleManager INSTANCE = new ParticleManager();

    public void spawnParticle(World world, EntityFX particle) {
        this.spawnParticle(world, particle, false);
    }

    public void spawnParticle(World world, EntityFX particle, boolean must) {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc != null && mc.field_71451_h != null && mc.field_71452_i != null) {
            if (!must) {
                int particleSetting = mc.field_71474_y.field_74362_aa;
                if (particleSetting == 2 || particleSetting == 1 && world.field_73012_v.nextInt(3) == 0) {
                    return;
                }
                double distanceX = mc.field_71451_h.field_70165_t - particle.field_70165_t;
                double distanceY = mc.field_71451_h.field_70163_u - particle.field_70163_u;
                double distanceZ = mc.field_71451_h.field_70161_v - particle.field_70161_v;
                int maxDistance = 16;
                if (distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ > (double)(maxDistance * maxDistance)) {
                    return;
                }
            }
            if (particle != null) {
                Minecraft.func_71410_x().field_71452_i.func_78873_a(particle);
            }
        }
    }
}

