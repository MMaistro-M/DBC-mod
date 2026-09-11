/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.World
 */
package noppes.npcs.client.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class EntityRainbowFX
extends EntityFX {
    public static float[][] colorTable = new float[][]{{1.0f, 0.0f, 0.0f}, {1.0f, 0.5f, 0.0f}, {1.0f, 1.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {0.0f, 0.0f, 1.0f}, {0.0f, 4375.0f, 0.0f, 1.0f}, {0.5625f, 0.0f, 1.0f}};
    float reddustParticleScale;

    public EntityRainbowFX(World world, double d, double d1, double d2, double f, double f1, double f2) {
        this(world, d, d1, d2, 1.0f, f, f1, f2);
    }

    public EntityRainbowFX(World world, double d, double d1, double d2, float f, double f1, double f2, double f3) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.field_70159_w *= (double)0.1f;
        this.field_70181_x *= (double)0.1f;
        this.field_70179_y *= (double)0.1f;
        if (f1 == 0.0) {
            f1 = 1.0;
        }
        int i = world.field_73012_v.nextInt(colorTable.length);
        this.field_70552_h = colorTable[i][0];
        this.field_70553_i = colorTable[i][1];
        this.field_70551_j = colorTable[i][2];
        this.field_70544_f *= 0.75f;
        this.field_70544_f *= f;
        this.reddustParticleScale = this.field_70544_f;
        this.field_70547_e = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.field_70547_e = (int)((float)this.field_70547_e * f);
        this.field_70145_X = false;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = ((float)this.field_70546_d + f) / (float)this.field_70547_e * 32.0f;
        if (f6 < 0.0f) {
            f6 = 0.0f;
        }
        if (f6 > 1.0f) {
            f6 = 1.0f;
        }
        this.field_70544_f = this.reddustParticleScale * f6;
        super.func_70539_a(tessellator, f, f1, f2, f3, f4, f5);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
        this.func_70536_a(7 - this.field_70546_d * 8 / this.field_70547_e);
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        if (this.field_70163_u == this.field_70167_r) {
            this.field_70159_w *= 1.1;
            this.field_70179_y *= 1.1;
        }
        this.field_70159_w *= (double)0.96f;
        this.field_70181_x *= (double)0.96f;
        this.field_70179_y *= (double)0.96f;
        if (this.field_70122_E) {
            this.field_70159_w *= (double)0.7f;
            this.field_70179_y *= (double)0.7f;
        }
    }
}

