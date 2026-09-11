/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityAuraFX
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.world.World;

public class EntityGodParticle
extends EntityAuraFX {
    public EntityGodParticle(World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) {
        super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
        this.func_70536_a(0);
        this.field_70544_f = (float)(Math.random() * 1.0) + 0.4f;
        this.func_70538_b(255.0f, 255.0f, 255.0f);
        this.field_70547_e = (int)(10.0 / (Math.random() * 0.8 + 0.2));
        this.field_70159_w *= 1.0;
        this.field_70181_x *= 8.0;
        this.field_70179_y *= 1.0;
    }
}

