/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.world.World
 */
package noppes.npcs.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class ZoneParticleFX
extends EntityFX {
    private final float startAlpha;
    private final boolean glow;

    public ZoneParticleFX(World world, double x, double y, double z, double motionX, double motionY, double motionZ, float r, float g, float b, float alpha, float scale, int maxAge, float gravity, boolean glow) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.field_70159_w = motionX;
        this.field_70181_x = motionY;
        this.field_70179_y = motionZ;
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
        this.field_82339_as = alpha;
        this.startAlpha = alpha;
        this.field_70544_f = scale;
        this.field_70547_e = maxAge;
        this.field_70545_g = gravity;
        this.glow = glow;
        this.field_70145_X = true;
        this.func_70536_a(0);
    }

    public int func_70070_b(float partialTick) {
        return this.glow ? 0xF000F0 : super.func_70070_b(partialTick);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
            return;
        }
        float lifeRatio = (float)this.field_70546_d / (float)this.field_70547_e;
        this.field_82339_as = this.startAlpha * (1.0f - lifeRatio);
        this.field_70181_x -= 0.04 * (double)this.field_70545_g;
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
    }
}

