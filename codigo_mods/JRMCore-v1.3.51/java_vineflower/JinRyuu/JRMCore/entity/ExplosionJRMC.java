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

public class ExplosionJRMC extends Explosion {
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
      this.field_77289_h = (int)(this.field_77289_h * this.field_77280_f);
      float f = this.field_77280_f;
      HashSet hashset = new HashSet();
      if (!this.ego) {
         for (int i = 0; i < this.field_77289_h; i++) {
            for (int j = 0; j < this.field_77289_h; j++) {
               for (int k = 0; k < this.field_77289_h; k++) {
                  if (i == 0 || i == this.field_77289_h - 1 || j == 0 || j == this.field_77289_h - 1 || k == 0 || k == this.field_77289_h - 1) {
                     double d3 = i / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
                     double d4 = j / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
                     double d5 = k / (this.field_77289_h - 1.0F) * 2.0F - 1.0F;
                     double d6 = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
                     d3 /= d6;
                     d4 /= d6;
                     d5 /= d6;
                     float f1 = this.field_77280_f * (0.7F + this.worldObj.field_73012_v.nextFloat() * 0.6F);
                     double d0 = this.field_77284_b;
                     double d1 = this.field_77285_c;
                     double d2 = this.field_77282_d;

                     for (float f2 = 0.3F; f1 > 0.0F; f1 -= f2 * 0.75F) {
                        int l = MathHelper.func_76128_c(d0);
                        int i1 = MathHelper.func_76128_c(d1);
                        int j1 = MathHelper.func_76128_c(d2);
                        Block block = this.worldObj.func_147439_a(l, i1, j1);
                        if (!this.ego && block.func_149688_o() != Material.field_151579_a) {
                           float f3 = this.field_77283_e != null
                              ? this.field_77283_e.func_145772_a(this, this.worldObj, l, i1, j1, block) * 0.2F
                              : block.getExplosionResistance(
                                    this.field_77283_e, this.worldObj, l, i1, j1, this.field_77284_b, this.field_77285_c, this.field_77282_d
                                 )
                                 * 0.2F;
                           f1 -= (f3 + 0.3F) * f2;
                        }

                        if (!this.ego
                           && f1 > 0.0F
                           && (this.field_77283_e == null || this.field_77283_e.func_145774_a(this, this.worldObj, l, i1, j1, block, f1))) {
                           hashset.add(new ChunkPosition(l, i1, j1));
                        }

                        d0 += d3 * f2;
                        d1 += d4 * f2;
                        d2 += d5 * f2;
                     }
                  }
               }
            }
         }
      }

      this.field_77281_g.addAll(hashset);
      this.field_77280_f *= 2.0F;
      int i = MathHelper.func_76128_c(this.field_77284_b - this.field_77280_f - 1.0);
      int j = MathHelper.func_76128_c(this.field_77284_b + this.field_77280_f + 1.0);
      int k = MathHelper.func_76128_c(this.field_77285_c - this.field_77280_f - 1.0);
      int l1 = MathHelper.func_76128_c(this.field_77285_c + this.field_77280_f + 1.0);
      int i2 = MathHelper.func_76128_c(this.field_77282_d - this.field_77280_f - 1.0);
      int j2 = MathHelper.func_76128_c(this.field_77282_d + this.field_77280_f + 1.0);
      List list = this.worldObj.func_72839_b(this.field_77283_e, AxisAlignedBB.func_72330_a(i, k, i2, j, l1, j2));
      Vec3 vec3 = Vec3.func_72443_a(this.field_77284_b, this.field_77285_c, this.field_77282_d);

      for (int k2 = 0; k2 < list.size(); k2++) {
         Entity entity = (Entity)list.get(k2);
         double d7 = entity.func_70011_f(this.field_77284_b, this.field_77285_c, this.field_77282_d) / this.field_77280_f;
         if (d7 <= 1.0) {
            double d0 = entity.field_70165_t - this.field_77284_b;
            double d1 = entity.field_70163_u + entity.func_70047_e() - this.field_77285_c;
            double d2 = entity.field_70161_v - this.field_77282_d;
            double d8 = MathHelper.func_76133_a(d0 * d0 + d1 * d1 + d2 * d2);
            if (d8 != 0.0) {
               d0 /= d8;
               d1 /= d8;
               d2 /= d8;
               double d9 = this.worldObj.func_72842_a(vec3, entity.field_70121_D);
               double d10 = (1.0 - d7) * d9;
               int sdmg = (int)((1.0 - d7) * (this.damage / 1.25));
               if (this.palyer instanceof EntityPlayer || entity instanceof EntityPlayer) {
                  entity.func_70097_a(Ds.causeExplosion(this.palyer), sdmg);
               }

               double d11 = EnchantmentProtection.func_92092_a(entity, d10);
               entity.field_70159_w += d0 * d11;
               entity.field_70181_x += d1 * d11;
               entity.field_70179_y += d2 * d11;
               if (entity instanceof EntityPlayer) {
                  this.field_77288_k.put((EntityPlayer)entity, Vec3.func_72443_a(d0 * d10, d1 * d10, d2 * d10));
               }
            }
         }
      }

      this.field_77280_f = f;
   }

   public void func_77279_a(boolean par1) {
      String snd = "";
      if (this.palyer instanceof EntityPlayer) {
         byte p = JRMCoreH.getByte((EntityPlayer)this.palyer, "PowerType");
         snd = p == 2 ? "jinryuunarutoc:NC1.Explosion" : "jinryuudragonbc:DBC.expl";
      }

      if (this.type != 5) {
         this.worldObj
            .func_72908_a(
               this.field_77284_b,
               this.field_77285_c,
               this.field_77282_d,
               snd,
               4.0F,
               (1.0F + (this.worldObj.field_73012_v.nextFloat() - this.worldObj.field_73012_v.nextFloat()) * 0.2F) * 0.7F
            );
      }

      if (this.field_77280_f >= 2.0F && this.field_82755_b) {
         this.worldObj.func_72869_a("hugeexplosion", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0, 0.0, 0.0);
      } else {
         this.worldObj.func_72869_a("largeexplode", this.field_77284_b, this.field_77285_c, this.field_77282_d, 1.0, 0.0, 0.0);
      }

      if (this.field_82755_b) {
         for (ChunkPosition chunkposition : this.field_77281_g) {
            int i = chunkposition.field_151329_a;
            int j = chunkposition.field_151327_b;
            int k = chunkposition.field_151328_c;
            Block block = this.worldObj.func_147439_a(i, j, k);
            if (par1) {
               double d0 = i;
               double d1 = j;
               double d2 = k;
               double d3 = d0 - this.field_77284_b;
               double d4 = d1 - this.field_77285_c;
               double d5 = d2 - this.field_77282_d;
               double d6 = MathHelper.func_76133_a(d3 * d3 + d4 * d4 + d5 * d5);
               d3 /= d6;
               d4 /= d6;
               d5 /= d6;
               double d7 = 0.5 / (d6 / this.field_77280_f + 0.1);
               d7 *= this.worldObj.field_73012_v.nextFloat() * this.worldObj.field_73012_v.nextFloat() + 0.3F;
               d3 *= d7;
               d4 *= d7;
               d5 *= d7;
            }

            if (block.func_149688_o() != Material.field_151579_a) {
               this.worldObj.func_147468_f(i, j, k);
               block.func_149723_a(this.worldObj, i, j, k, this);
            }
         }
      }

      if (this.field_77286_a) {
         for (ChunkPosition chunkposition : this.field_77281_g) {
            int i = chunkposition.field_151329_a;
            int j = chunkposition.field_151327_b;
            int k = chunkposition.field_151328_c;
            Block block = this.worldObj.func_147439_a(i, j, k);
            Block block1 = this.worldObj.func_147439_a(i, j - 1, k);
            if (block.func_149688_o() == Material.field_151579_a && block1.func_149730_j() && this.explosionRNG.nextInt(3) == 0) {
            }
         }
      }
   }

   public Map func_77277_b() {
      return this.field_77288_k;
   }

   public EntityLivingBase func_94613_c() {
      return this.field_77283_e == null
         ? null
         : (
            this.field_77283_e instanceof EntityTNTPrimed
               ? ((EntityTNTPrimed)this.field_77283_e).func_94083_c()
               : (this.field_77283_e instanceof EntityLivingBase ? (EntityLivingBase)this.field_77283_e : null)
         );
   }
}
