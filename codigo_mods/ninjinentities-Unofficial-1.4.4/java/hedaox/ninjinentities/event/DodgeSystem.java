/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 */
package hedaox.ninjinentities.event;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class DodgeSystem {
    public static final Random RANDOM = new Random();
    private final EntityLivingBase entity;
    public int dodge = 0;
    public int strikeBack = 0;

    public DodgeSystem(EntityLivingBase entity) {
        this.entity = entity;
    }

    public boolean tryDodge(Entity attacker) {
        if (attacker == null || this.dodge <= 0) {
            return false;
        }
        if (RANDOM.nextInt(100) < this.dodge) {
            for (int i = 0; i < 30; ++i) {
                if (!this.tryDodgePattern(attacker, i % 19 + 1)) continue;
                this.playDodgeSound();
                return true;
            }
        }
        return false;
    }

    private boolean tryDodgePattern(Entity attacker, int pattern) {
        double dodgeZ;
        double z;
        double distance;
        double sin;
        double[] angles = new double[]{10.0, 20.0, 30.0, 60.0, 90.0, -10.0, -20.0, -30.0, -30.0, -40.0, -40.0, -80.0, -60.0, 80.0, -180.0, 140.0, -140.0, 120.0, -120.0};
        double[] distances = new double[]{1.0, 1.15, 1.0, 1.15, 1.35, 1.0, 1.15, 1.0, 1.25, 1.15, 1.65, 1.75, 1.25, 1.75, 1.75, 1.25, 1.25, 1.75, 1.75};
        double baseX = this.entity.field_70165_t - attacker.field_70165_t;
        double baseZ = this.entity.field_70161_v - attacker.field_70161_v;
        double baseLength = Math.sqrt(baseX * baseX + baseZ * baseZ);
        if (baseLength == 0.0) {
            return false;
        }
        double offsetAngle = Math.toRadians(angles[pattern - 1]);
        double cos = Math.cos(offsetAngle);
        double dodgeX = (baseX /= baseLength) * cos - (baseZ /= baseLength) * (sin = Math.sin(offsetAngle));
        double x = this.entity.field_70165_t + dodgeX * (distance = distances[pattern - 1]);
        if (this.isPositionSafe(x, this.entity.field_70163_u, z = this.entity.field_70161_v + (dodgeZ = baseX * sin + baseZ * cos) * distance)) {
            this.entity.func_70107_b(x, this.entity.field_70163_u, z);
            return true;
        }
        return false;
    }

    public boolean tryStrikeBack(Entity attacker, double damage) {
        if (attacker == null || this.strikeBack <= 0) {
            return false;
        }
        if (RANDOM.nextInt(100) < this.strikeBack) {
            this.performCounterAttack(attacker, damage);
            return true;
        }
        return false;
    }

    public void performCounterAttack(Entity target, double damage) {
        float radius = this.entity.field_70130_N / 2.0f + 3.5f;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.entity.field_70165_t - (double)radius), (double)(this.entity.field_70163_u - (double)radius), (double)(this.entity.field_70161_v - (double)radius), (double)(this.entity.field_70165_t + (double)radius), (double)(this.entity.field_70163_u + (double)radius), (double)(this.entity.field_70161_v + (double)radius));
        List players = this.entity.field_70170_p.func_72872_a(EntityPlayer.class, aabb);
        for (EntityPlayer player : players) {
            player.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.entity), (float)damage);
            this.applyKnockback((Entity)player, 2.0f);
        }
        this.playCounterSound();
    }

    private boolean isPositionSafe(double x, double y, double z) {
        int blockZ;
        int blockY;
        int blockX = MathHelper.func_76128_c((double)x);
        return this.entity.field_70170_p.func_147437_c(blockX, blockY = MathHelper.func_76128_c((double)y), blockZ = MathHelper.func_76128_c((double)z)) && this.entity.field_70170_p.func_147437_c(blockX, blockY + 1, blockZ);
    }

    private void playDodgeSound() {
        this.entity.field_70170_p.func_72956_a((Entity)this.entity, "jinryuudragonbc:DBC4.dodge" + (RANDOM.nextInt(3) + 1), 1.0f, 1.0f);
    }

    private void playCounterSound() {
        this.entity.field_70170_p.func_72956_a((Entity)this.entity, "jinryuudragonbc:DBC3.force", 0.5f, this.entity.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
    }

    private void applyKnockback(Entity target, float strength) {
        target.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.entity.field_70177_z * (float)Math.PI / 180.0f)) * strength), 0.1, (double)(MathHelper.func_76134_b((float)(this.entity.field_70177_z * (float)Math.PI / 180.0f)) * strength));
    }
}

