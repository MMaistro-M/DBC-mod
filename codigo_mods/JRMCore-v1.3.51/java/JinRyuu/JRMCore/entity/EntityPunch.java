/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCEnAttacks;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntityKiAttacksLight;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityPunch
extends EntityKiAttacksLight
implements IEntityAdditionalSpawnData,
IEntitySelector {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData = 0;
    private boolean inGround = false;
    public int canBePickedUp = 0;
    public int arrowShake = 0;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir = 0;
    private double damage = 0.0;
    private float size = 2.0f;
    private int knockbackStrength;
    private float explevel;
    private float prc;

    public EntityPunch(World par1World) {
        super(par1World);
        this.func_70105_a(this.size, this.size);
    }

    public EntityPunch(World par1World, double par2, double par4, double par6) {
        super(par1World);
        this.func_70105_a(this.size, this.size);
        this.func_70107_b(par2, par4, par6);
        this.field_70129_M = this.size / 2.0f;
    }

    public EntityPunch(World par1World, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase, float par4, float par5) {
        super(par1World);
        this.shootingEntity = par2EntityLivingBase;
        if (par2EntityLivingBase instanceof EntityPlayer) {
            this.canBePickedUp = 0;
        }
        this.field_70163_u = par2EntityLivingBase.field_70163_u + (double)par2EntityLivingBase.func_70047_e() - (double)0.1f;
        double var6 = par3EntityLivingBase.field_70165_t - par2EntityLivingBase.field_70165_t;
        double var8 = par3EntityLivingBase.field_70163_u + (double)par3EntityLivingBase.func_70047_e() - (double)0.7f - this.field_70163_u;
        double var10 = par3EntityLivingBase.field_70161_v - par2EntityLivingBase.field_70161_v;
        double var12 = MathHelper.func_76133_a((double)(var6 * var6 + var10 * var10));
        if (var12 >= 1.0E-7) {
            float var14 = (float)(Math.atan2(var10, var6) * 180.0 / Math.PI) - 90.0f;
            float var15 = (float)(-(Math.atan2(var8, var12) * 180.0 / Math.PI));
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            this.func_70012_b(par2EntityLivingBase.field_70165_t + var16, this.field_70163_u, par2EntityLivingBase.field_70161_v + var18, var14, var15);
            this.field_70129_M = this.size / 2.0f;
            float var20 = (float)var12 * 0.2f;
            this.setThrowableHeading(var6, var8 + (double)var20, var10, par4, par5);
        }
    }

    public EntityPunch(World par1World, EntityLivingBase par2EntityLivingBase, float par3, float prc, double dam) {
        super(par1World);
        this.damage = dam;
        this.shootingEntity = par2EntityLivingBase;
        this.explevel = prc;
        this.prc = prc;
        if (par2EntityLivingBase instanceof EntityPlayer) {
            this.canBePickedUp = 0;
        }
        this.func_70105_a(this.size, this.size);
        double d8 = par2EntityLivingBase.field_70130_N;
        double d9 = par2EntityLivingBase.field_70131_O;
        Vec3 vec3 = par2EntityLivingBase.func_70676_i(1.0f);
        double x = par2EntityLivingBase.field_70165_t + vec3.field_72450_a * d8;
        double y = par2EntityLivingBase.field_70163_u + vec3.field_72448_b * d8 + (double)(par2EntityLivingBase.field_70131_O * 0.55f);
        double z = par2EntityLivingBase.field_70161_v + vec3.field_72449_c * d8;
        this.func_70012_b(x, y, z, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
        this.field_70129_M = this.size * 0.5f;
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.setThrowableHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, par3 * 1.5f, 1.0f);
    }

    @Override
    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(16, (Object)0);
    }

    public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
        float var9 = MathHelper.func_76133_a((double)(par1 * par1 + par3 * par3 + par5 * par5));
        par1 /= (double)var9;
        par3 /= (double)var9;
        par5 /= (double)var9;
        par1 += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        par3 += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        par5 += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        this.field_70159_w = par1 *= (double)par7;
        this.field_70181_x = par3 *= (double)par7;
        this.field_70179_y = par5 *= (double)par7;
        float var10 = MathHelper.func_76133_a((double)(par1 * par1 + par5 * par5));
        this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
        this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, var10) * 180.0 / Math.PI);
        this.ticksInGround = 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.func_70107_b(par1, par3, par5);
        this.func_70101_b(par7, par8);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70016_h(double par1, double par3, double par5) {
        this.field_70159_w = par1;
        this.field_70181_x = par3;
        this.field_70179_y = par5;
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float var7 = MathHelper.func_76133_a((double)(par1 * par1 + par5 * par5));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, var7) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A;
            this.field_70126_B = this.field_70177_z;
            this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            this.ticksInGround = 0;
        }
    }

    public void func_70071_h_() {
        Block block;
        super.func_70071_h_();
        if (this.field_70173_aa == 1) {
            this.func_70105_a(this.size, this.size);
            this.field_70129_M = this.size / 2.0f;
        }
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float var1 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, var1) * 180.0 / Math.PI);
        }
        if ((block = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile)).func_149688_o() != Material.field_151579_a) {
            block.func_149719_a((IBlockAccess)this.field_70170_p, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v))) {
                this.inGround = true;
            }
        }
        if (this.arrowShake > 0) {
            // empty if block
        }
        if (this.inGround) {
            int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (block == this.inTile && var19 == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1) {
                    if (this.field_70170_p.field_72995_K || this.explevel == 2.0f) {
                        // empty if block
                    }
                    this.func_70106_y();
                }
            } else {
                this.inGround = false;
                this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            float var11;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (this.ticksInAir == 5) {
                this.func_70106_y();
            }
            if (var4 != null) {
                var3 = Vec3.func_72443_a((double)var4.field_72307_f.field_72450_a, (double)var4.field_72307_f.field_72448_b, (double)var4.field_72307_f.field_72449_c);
            }
            Entity var5 = null;
            List var6 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(0.5, 0.5, 0.5));
            double var7 = 0.0;
            for (int var9 = 0; var9 < var6.size(); ++var9) {
                double var14;
                AxisAlignedBB var12;
                MovingObjectPosition var13;
                Entity var10 = (Entity)var6.get(var9);
                if (!var10.func_70067_L() || var10 == this.shootingEntity && this.ticksInAir < 5 || (var13 = (var12 = var10.field_70121_D.func_72314_b((double)(var11 = 0.0f), (double)var11, (double)var11)).func_72327_a(var17, var3)) == null || !((var14 = var17.func_72438_d(var13.field_72307_f)) < var7) && var7 != 0.0) continue;
                var5 = var10;
                var7 = var14;
            }
            if (var5 != null) {
                var4 = new MovingObjectPosition(var5);
            }
            if (var4 != null) {
                if (!this.field_70170_p.field_72995_K) {
                    // empty if block
                }
                if (var4.field_72308_g != null && var4.field_72308_g instanceof EntityLivingBase && var4.field_72308_g != this.shootingEntity && var4.field_72308_g.func_70089_S()) {
                    if (this.explevel == 2.0f) {
                        this.damage *= 2.0;
                    }
                    float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    int var23 = (int)this.damage;
                    if (JRMCoreH.NC() && var4.field_72308_g instanceof EntityPlayer && JRMCoreH.clsTypOn((EntityPlayer)var4.field_72308_g) == 1 && JRMCoreH.getByte((EntityPlayer)var4.field_72308_g, "jrmcPwrtyp") == 2) {
                        JRMCoreH.jrmcEnergyDam(var4.field_72308_g, (int)(this.damage * (double)this.prc), null);
                    }
                    if (!(this.field_70170_p.field_72995_K || JRMCoreH.DBC() && !JRMCoreH.DGE(var4.field_72308_g) || this.shootingEntity == null)) {
                        JRMCoreH.jrmcExp(this.shootingEntity, 1);
                    }
                    DamageSource damagesource = null;
                    damagesource = this.shootingEntity == null ? Ds.causeEntityPunchDamage(this, this) : Ds.causeEntityPunchDamage(this, this.shootingEntity);
                    if (this.func_70027_ad()) {
                        var4.field_72308_g.func_70015_d(5);
                    }
                    if (var4.field_72308_g.func_70097_a(damagesource, (float)var23)) {
                        if (!this.field_70170_p.field_72995_K) {
                            this.field_70170_p.func_72956_a((Entity)this, JRMCEnAttacks.PunchExplSound, 0.5f, 0.9f / (this.field_70146_Z.nextFloat() * 0.4f + 0.8f));
                        }
                        if (var4.field_72308_g instanceof EntityLivingBase) {
                            float var25;
                            if (!this.field_70170_p.field_72995_K) {
                                EntityLivingBase var24 = (EntityLivingBase)var4.field_72308_g;
                                if (!this.field_70170_p.field_72995_K) {
                                    // empty if block
                                }
                            }
                            if (this.knockbackStrength > 0 && (var25 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                                var4.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)var25, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)var25);
                            }
                        }
                        this.func_70106_y();
                    } else {
                        this.field_70159_w *= (double)-0.1f;
                        this.field_70181_x *= (double)-0.1f;
                        this.field_70179_y *= (double)-0.1f;
                        this.field_70177_z += 180.0f;
                        this.field_70126_B += 180.0f;
                        this.func_70106_y();
                        this.ticksInAir = 0;
                    }
                } else {
                    this.xTile = var4.field_72311_b;
                    this.yTile = var4.field_72312_c;
                    this.zTile = var4.field_72309_d;
                    this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                    this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
                    this.field_70159_w = (float)(var4.field_72307_f.field_72450_a - this.field_70165_t);
                    this.field_70181_x = (float)(var4.field_72307_f.field_72448_b - this.field_70163_u);
                    this.field_70179_y = (float)(var4.field_72307_f.field_72449_c - this.field_70161_v);
                    float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    this.field_70165_t -= this.field_70159_w / (double)var20 * (double)0.05f;
                    this.field_70163_u -= this.field_70181_x / (double)var20 * (double)0.05f;
                    this.field_70161_v -= this.field_70179_y / (double)var20 * (double)0.05f;
                    this.inGround = true;
                    this.arrowShake = 0;
                    this.setIsCritical(false);
                    if (this.inTile.func_149688_o() != Material.field_151579_a) {
                        this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this);
                    }
                }
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70125_A = (float)(Math.atan2(this.field_70181_x, var20) * 180.0 / Math.PI);
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
            float var22 = 0.99f;
            var11 = 0.0f;
            if (this.func_70090_H()) {
                for (int var26 = 0; var26 < 4; ++var26) {
                    float var27 = 0.25f;
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)var27, this.field_70163_u - this.field_70181_x * (double)var27, this.field_70161_v - this.field_70179_y * (double)var27, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
                var22 = 0.8f;
            }
            this.field_70159_w *= (double)var22;
            this.field_70181_x *= (double)var22;
            this.field_70179_y *= (double)var22;
            this.field_70181_x -= (double)var11;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.doBlockCollisions();
        }
    }

    private void doBlockCollisions() {
        this.func_145775_I();
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2, EntityLivingBase e) {
        if (e.func_85032_ar()) {
            return false;
        }
        if (e.field_70170_p.field_72995_K) {
            return false;
        }
        e.field_70721_aZ = 1.5f;
        boolean flag = true;
        e.field_70735_aL = e.func_110143_aJ();
        this.damageEntity(par1DamageSource, par2, e);
        e.field_70739_aP = 0.0f;
        Entity entity = par1DamageSource.func_76346_g();
        if (entity != null && entity instanceof EntityLivingBase) {
            e.func_70604_c((EntityLivingBase)entity);
        }
        if (flag) {
            e.field_70170_p.func_72960_a((Entity)this, (byte)2);
            if (entity != null) {
                double d0 = entity.field_70165_t - e.field_70165_t;
                double d1 = entity.field_70161_v - e.field_70161_v;
                while (d0 * d0 + d1 * d1 < 1.0E-4) {
                    d0 = (Math.random() - Math.random()) * 0.01;
                    d1 = (Math.random() - Math.random()) * 0.01;
                }
                e.field_70739_aP = (float)(Math.atan2(d1, d0) * 180.0 / Math.PI) - e.field_70177_z;
                e.func_70653_a(entity, par2, d0, d1);
            } else {
                e.field_70739_aP = (int)(Math.random() * 2.0) * 180;
            }
        }
        if (e.func_110143_aJ() <= 0.0f) {
            if (flag) {
                // empty if block
            }
            e.func_70645_a(par1DamageSource);
        } else if (flag) {
            // empty if block
        }
        return true;
    }

    protected void damageEntity(DamageSource par1DamageSource, float par2, EntityLivingBase e) {
        if (!this.func_85032_ar()) {
            if ((par2 = ForgeHooks.onLivingHurt((EntityLivingBase)e, (DamageSource)par1DamageSource, (float)par2)) <= 0.0f) {
                return;
            }
            par2 = this.applyArmorCalculations(par1DamageSource, par2, e);
            float f1 = par2 = this.applyPotionDamageCalculations(par1DamageSource, par2, e);
            par2 = Math.max(par2 - e.func_110139_bj(), 0.0f);
            e.func_110149_m(e.func_110139_bj() - (f1 - par2));
            if (par2 != 0.0f) {
                float f2 = e.func_110143_aJ();
                e.func_70606_j(f2 - par2);
                e.func_110142_aN().func_94547_a(par1DamageSource, f2, par2);
                e.func_110149_m(e.func_110139_bj() - par2);
            }
        }
    }

    protected float applyArmorCalculations(DamageSource par1DamageSource, float par2, EntityLivingBase e) {
        if (!par1DamageSource.func_76363_c()) {
            int i = 25 - e.func_70658_aO();
            float f1 = par2 * (float)i;
            this.damageArmor(par2, e);
            par2 = f1 / 25.0f;
        }
        return par2;
    }

    protected void damageArmor(float par1, EntityLivingBase e) {
        if (e instanceof EntityPlayer) {
            ((EntityPlayer)e).field_71071_by.func_70449_g(par1);
        }
    }

    protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2, EntityLivingBase e) {
        if (e.func_70644_a(Potion.field_76429_m) && par1DamageSource != DamageSource.field_76380_i) {
            int i = (e.func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
            int j = 25 - i;
            float f1 = par2 * (float)j;
            par2 = f1 / 25.0f;
        }
        if (par2 <= 0.0f) {
            return 0.0f;
        }
        return par2;
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
        par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
        par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
        par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.inTile));
        par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
        par1NBTTagCompound.func_74774_a("shake", (byte)this.arrowShake);
        par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.func_74774_a("pickup", (byte)this.canBePickedUp);
        par1NBTTagCompound.func_74780_a("damage", this.damage);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.func_74765_d("xTile");
        this.yTile = par1NBTTagCompound.func_74765_d("yTile");
        this.zTile = par1NBTTagCompound.func_74765_d("zTile");
        this.inTile = Block.func_149729_e((int)(par1NBTTagCompound.func_74771_c("inTile") & 0xFF));
        this.inData = par1NBTTagCompound.func_74771_c("inData") & 0xFF;
        this.arrowShake = par1NBTTagCompound.func_74771_c("shake") & 0xFF;
        boolean bl = this.inGround = par1NBTTagCompound.func_74771_c("inGround") == 1;
        if (par1NBTTagCompound.func_74764_b("damage")) {
            this.damage = par1NBTTagCompound.func_74769_h("damage");
        }
        if (par1NBTTagCompound.func_74764_b("pickup")) {
            this.canBePickedUp = par1NBTTagCompound.func_74771_c("pickup");
        } else if (par1NBTTagCompound.func_74764_b("player")) {
            this.canBePickedUp = par1NBTTagCompound.func_74767_n("player") ? 1 : 0;
        }
    }

    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
        if (this.field_70170_p.field_72995_K || this.inGround) {
            // empty if block
        }
    }

    protected boolean func_70041_e_() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public void setDamage(double par1) {
        this.damage = par1;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setKnockbackStrength(int par1) {
        this.knockbackStrength = par1;
    }

    public boolean func_70075_an() {
        return false;
    }

    public void setIsCritical(boolean par1) {
        byte var2 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 | 1)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(var2 & 0xFFFFFFFE)));
        }
    }

    public boolean getIsCritical() {
        byte var1 = this.field_70180_af.func_75683_a(16);
        return (var1 & 1) != 0;
    }

    private void explode() {
        float var1 = 2.0f;
        this.field_70170_p.func_72876_a((Entity)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, var1, this.inGround);
    }

    public boolean func_82704_a(Entity var1) {
        return false;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.shootingEntity == null ? 0 : this.shootingEntity.func_145782_y());
        data.writeDouble(this.damage);
        data.writeFloat(this.size);
    }

    public void readSpawnData(ByteBuf data) {
        int first = data.readInt();
        this.damage = data.readDouble();
        this.size = data.readFloat();
        this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
    }
}

