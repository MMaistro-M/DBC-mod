/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.client.fx;

import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;

public class EntityElementalStaffFX
extends EntityPortalFX {
    double x;
    double y;
    double z;
    EntityLivingBase player;

    public EntityElementalStaffFX(EntityLivingBase player, double d, double d1, double d2, double f1, double f2, double f3, int color) {
        super(player.field_70170_p, player.field_70165_t + d, player.field_70163_u + d1, player.field_70161_v + d2, f1, f2, f3);
        this.player = player;
        this.x = d;
        this.y = d1;
        this.z = d2;
        float[] colors = color <= 15 ? EntitySheep.field_70898_d[color] : new float[]{(float)(color >> 16 & 0xFF) / 255.0f, (float)(color >> 8 & 0xFF) / 255.0f, (float)(color & 0xFF) / 255.0f};
        this.field_70552_h = colors[0];
        this.field_70553_i = colors[1];
        this.field_70551_j = colors[2];
        this.field_70547_e = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        this.field_70145_X = false;
    }

    public void func_70071_h_() {
        float var1;
        if (this.player.field_70128_L) {
            this.func_70106_y();
            return;
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        float var2 = var1 = (float)this.field_70546_d / (float)this.field_70547_e;
        var1 = -var1 + var1 * var1 * 2.0f;
        var1 = 1.0f - var1;
        double dx = -MathHelper.func_76126_a((float)((float)((double)(this.player.field_70177_z / 180.0f) * Math.PI))) * MathHelper.func_76134_b((float)((float)((double)(this.player.field_70125_A / 180.0f) * Math.PI)));
        double dz = MathHelper.func_76134_b((float)((float)((double)(this.player.field_70177_z / 180.0f) * Math.PI))) * MathHelper.func_76134_b((float)((float)((double)(this.player.field_70125_A / 180.0f) * Math.PI)));
        this.field_70165_t = this.player.field_70165_t + this.x + dx + this.field_70159_w * (double)var1;
        this.field_70163_u = this.player.field_70163_u + this.y + this.field_70181_x * (double)var1 + (double)(1.0f - var2) - (double)(this.player.field_70125_A / 40.0f);
        this.field_70161_v = this.player.field_70161_v + this.z + dz + this.field_70179_y * (double)var1;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }

    public void func_70106_y() {
        super.func_70106_y();
    }
}

