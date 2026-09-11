/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentProtection
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityTNTPrimed
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosionJRMC
extends Explosion {
    public boolean field_77286_a = false;
    public boolean field_82755_b = true;
    private int field_77289_h = JRMCoreConfig.eaei;
    private Random explosionRNG = new Random();
    private World worldObj;
    public double field_77284_b;
    public double field_77285_c;
    public double field_77282_d;
    public Entity field_77283_e;
    public float field_77280_f;
    public boolean ego;
    public double damage;
    public List field_77281_g = new ArrayList();
    private Map field_77288_k = new HashMap();
    public Entity palyer;
    public byte type;

    public ExplosionJRMC(World par1World, Entity par2Entity, double x, double y, double z, float size, boolean off, double dam, Entity origin, byte type) {
        super(par1World, par2Entity, x, y, z, size);
        this.worldObj = par1World;
        this.field_77283_e = par2Entity;
        this.field_77280_f = size;
        this.field_77284_b = x;
        this.field_77285_c = y;
        this.field_77282_d = z;
        this.ego = off;
        this.damage = dam;
        this.palyer = origin;
        this.type = type;
    }

    public void func_77278_a() {
        double d2;
        double d1;
        double d0;
        int k;
        int j;
        int i;
        this.field_77289_h = (int)((float)this.field_77289_h * this.field_77280_f);
        float f = this.field_77280_f;
        HashSet<ChunkPosition> hashset = new HashSet<ChunkPosition>();
        if (!this.ego) {
            for (i = 0; i < this.field_77289_h; ++i) {
                for (j = 0; j < this.field_77289_h; ++j) {
                    for (k = 0; k < this.field_77289_h; ++k) {
                        if (i != 0 && i != this.field_77289_h - 1 && j != 0 && j != this.field_77289_h - 1 && k != 0 && k != this.field_77289_h - 1) continue;
                        double d3 = (float)i / ((float)this.field_77289_h - 1.0f) * 2.0f - 1.0f;
                        double d4 = (float)j / ((float)this.field_77289_h - 1.0f) * 2.0f - 1.0f;
                        double d5 = (float)k / ((float)this.field_77289_h - 1.0f) * 2.0f - 1.0f;
                        double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        d0 = this.field_77284_b;
                        d1 = this.field_77285_c;
                        d2 = this.field_77282_d;
                        float f2 = 0.3f;
                        for (float f1 = this.field_77280_f * (0.7f + this.worldObj.field_73012_v.nextFloat() * 0.6f); f1 > 0.0f; f1 -= f2 * 0.75f) {
                            int l = MathHelper.func_76128_c((double)d0);
                            int i1 = MathHelper.func_76128_c((double)d1);
                            int j1 = MathHelper.func_76128_c((double)d2);
                            Block block = this.worldObj.func_147439_a(l, i1, j1);
                            if (!this.ego && block.func_149688_o() != Material.field_151579_a) {
                                float f3 = this.field_77283_e != null ? this.field_77283_e.func_145772_a((Explosion)this, this.worldObj, l, i1, j1, block) * 0.2f : block.getExplosionResistance(this.field_77283_e, this.worldObj, l, i1, j1, this.field_77284_b, this.field_77285_c, this.field_77282_d) * 0.2f;
                                f1 -= (f3 + 0.3f) * f2;
                            }
                            if (!this.ego && f1 > 0.0f && (this.field_77283_e == null || this.field_77283_e.func_145774_a((Explosion)this, this.worldObj, l, i1, j1, block, f1))) {
                                hashset.add(new ChunkPosition(l, i1, j1));
                            }
                            d0 += d3 * (double)f2;
                            d1 += d4 * (double)f2;
                            d2 += d5 * (double)f2;
                        }
                    }
                }
            }
        }
        this.field_77281_g.addAll(hashset);
        this.field_77280_f *= 2.0f;
        i = MathHelper.func_76128_c((double)(this.field_77284_b - (double)this.field_77280_f - 1.0));
        j = MathHelper.func_76128_c((double)(this.field_77284_b + (double)this.field_77280_f + 1.0));
        k = MathHelper.func_76128_c((double)(this.field_77285_c - (double)this.field_77280_f - 1.0));
        int l1 = MathHelper.func_76128_c((double)(this.field_77285_c + (double)this.field_77280_f + 1.0));
        int i2 = MathHelper.func_76128_c((double)(this.field_77282_d - (double)this.field_77280_f - 1.0));
        int j2 = MathHelper.func_76128_c((double)(this.field_77282_d + (double)this.field_77280_f + 1.0));
        List list = this.worldObj.func_72839_b(this.field_77283_e, AxisAlignedBB.func_72330_a((double)i, (double)k, (double)i2, (double)j, (double)l1, (double)j2));
        Vec3 vec3 = Vec3.func_72443_a((double)this.field_77284_b, (double)this.field_77285_c, (double)this.field_77282_d);
        for (int k2 = 0; k2 < list.size(); ++k2) {
            double d8;
            Entity entity = (Entity)list.get(k2);
            double d7 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / (double)this.field_77280_f;
            if (!(d7 <= 1.0) || (d8 = (double)MathHelper.func_76133_a((double)((d0 = entity.field_70165_t - this.field_77284_b) * d0 + (d1 = entity.field_70163_u + (double)entity.func_70047_e() - this.field_77285_c) * d1 + (d2 = entity.field_70161_v - this.field_77282_d) * d2))) == 0.0) continue;
            d0 /= d8;
            d1 /= d8;
            d2 /= d8;
            double d9 = this.worldObj.func_72842_a(vec3, entity.field_70121_D);
            double d10 = (1.0 - d7) * d9;
            int sdmg = (int)((1.0 - d7) * (this.damage / 1.25));
            if (this.palyer instanceof EntityPlayer || entity instanceof EntityPlayer) {
                entity.func_70097_a(Ds.causeExplosion(this.palyer), (float)sdmg);
            }
            double d11 = EnchantmentProtection.func_92092_a((Entity)entity, (double)d10);
            entity.field_70159_w += d0 * d11;
            entity.field_70181_x += d1 * d11;
            entity.field_70179_y += d2 * d11;
            if (!(entity instanceof EntityPlayer)) continue;
            this.field_77288_k.put((EntityPlayer)entity, Vec3.func_72443_a((double)(d0 * d10), (double)(d1 * d10), (double)(d2 * d10)));
        }
        this.field_77280_f = f;
    }

    public void func_77279_a(boolean par1) {
        Block block;
        int k;
        int j;
        int i;
        String snd = "";
        if (this.palyer instanceof EntityPlayer) {
            byte p = JRMCoreH.getByte((EntityPlayer)this.palyer, "PowerType");
            String string = snd = p == 2 ? "jinryuunarutoc:NC1.Explosion" : "jinryuudragonbc:DBC.expl";
        }
        if (this.type != 5) {
            this.worldObj.func_72908_a(this.field_77284_b, this.field_77285_c, this.field_77282_d, snd, 4.0f, (1.0f + (this.worldObj.field_73012_v.nextFloat() - this.worldObj.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
        }
        if (this.field_77280_f >= 2.0f && this.field_82755_b) {
            this.worldObj.func_72869_a("hugeexplosion", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0, 0.0, 0.0);
        } else {
            this.worldObj.func_72869_a("largeexplode", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0, 0.0, 0.0);
        }
        if (this.field_82755_b) {
            for (ChunkPosition chunkposition : this.field_77281_g) {
                i = chunkposition.field_151329_a;
                j = chunkposition.field_151327_b;
                k = chunkposition.field_151328_c;
                block = this.worldObj.func_147439_a(i, j, k);
                if (par1) {
                    double d0 = i;
                    double d1 = j;
                    double d2 = k;
                    double d3 = d0 - this.field_77284_b;
                    double d4 = d1 - this.field_77285_c;
                    double d5 = d2 - this.field_77282_d;
                    double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
                    d3 /= d6;
                    d4 /= d6;
                    d5 /= d6;
                    double d7 = 0.5 / (d6 / (double)this.field_77280_f + 0.1);
                    d3 *= (d7 *= (double)(this.worldObj.field_73012_v.nextFloat() * this.worldObj.field_73012_v.nextFloat() + 0.3f));
                    d4 *= d7;
                    d5 *= d7;
                }
                if (block.func_149688_o() == Material.field_151579_a) continue;
                this.worldObj.func_147468_f(i, j, k);
                block.func_149723_a(this.worldObj, i, j, k, (Explosion)this);
            }
        }
        if (this.field_77286_a) {
            for (ChunkPosition chunkposition : this.field_77281_g) {
                i = chunkposition.field_151329_a;
                j = chunkposition.field_151327_b;
                k = chunkposition.field_151328_c;
                block = this.worldObj.func_147439_a(i, j, k);
                Block block1 = this.worldObj.func_147439_a(i, j - 1, k);
                if (block.func_149688_o() == Material.field_151579_a && block1.func_149730_j() && this.explosionRNG.nextInt(3) != 0) continue;
            }
        }
    }

    public Map func_77277_b() {
        return this.field_77288_k;
    }

    public EntityLivingBase func_94613_c() {
        return this.field_77283_e == null ? null : (this.field_77283_e instanceof EntityTNTPrimed ? ((EntityTNTPrimed)this.field_77283_e).func_94083_c() : (this.field_77283_e instanceof EntityLivingBase ? (EntityLivingBase)this.field_77283_e : null));
    }
}

