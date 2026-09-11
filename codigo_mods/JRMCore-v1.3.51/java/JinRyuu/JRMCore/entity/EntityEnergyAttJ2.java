/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  JinRyuu.NarutoC.common.NCJutsus
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.common.registry.IThrowableEntity
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityCusPars;
import JinRyuu.JRMCore.entity.EntityEnAttacks;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ;
import JinRyuu.JRMCore.mod_JRMCore;
import JinRyuu.NarutoC.common.NCJutsus;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityEnergyAttJ2
extends EntityEnAttacks
implements IThrowableEntity,
IEntityAdditionalSpawnData,
IEntitySelector,
IProjectile {
    private final byte[] explosion_id = new byte[]{3, 4, 0, -1, -1, -1, -1};
    private final byte[] explosion_size = new byte[]{0, 0, 0, 0, 1, -1, -1};
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData = 0;
    private boolean inGround = false;
    private int ticksInGround;
    private int ticksInAir = 0;
    private double damage = 1.0;
    private int knockbackStrength;
    private float explevel;
    private String DBCExplSound = "jinryuudragonbc:DBC.expl";
    private String NCExplSound = "jinryuunarutoc:NC1.Explosion";
    private float strtX;
    private float strtY;
    private float strtZ;
    private float trgtX = 0.0f;
    private float trgtY = 0.0f;
    private float trgtZ = 0.0f;
    private byte type;
    private float speed;
    private int dam;
    private byte perc;
    private byte pmjID;
    private short effect;
    private int color;
    private byte density;
    private short sincantation;
    private short sfire;
    private short smove;
    private byte align;
    private int conn;
    private int waveCount = 20;
    private byte wave = 0;
    private Entity target;
    private int cost;
    private int costPerc;
    private int originDmg;
    private boolean shrink = false;
    private int pwrtyp = 0;
    private String nameJutsu;
    public final float[] maxSizeGrowth = new float[]{1.0f, 1.0f, 10.0f, 0.0f, 0.0f, 1.0f};
    public final float[] maxSizeGrowthSpeed = new float[]{0.3f, 0.3f, 1.2f, 0.0f, 0.0f, 1.0f};
    public float size = 0.0f;
    private boolean s_a = false;
    private boolean s_d = false;
    private final int s_wait = 3;
    private long s_start = 0L;
    public boolean chakra_trail = true;
    private int health;
    private byte jtsre;
    private Vec3 fir_vec3;
    private double fir_d8;
    private double fir_d9;

    public float strtX() {
        return this.strtX;
    }

    public float strtY() {
        return this.strtY;
    }

    public float strtZ() {
        return this.strtZ;
    }

    public float trgtX() {
        return this.trgtX;
    }

    public float trgtY() {
        return this.trgtY;
    }

    public float trgtZ() {
        return this.trgtZ;
    }

    public boolean getShrink() {
        return this.shrink;
    }

    public byte getType() {
        return this.type;
    }

    public int getCol() {
        return this.color;
    }

    public float getSpe() {
        return this.speed;
    }

    public int getDam() {
        return this.dam;
    }

    public byte getDen() {
        return this.density;
    }

    public byte getPerc() {
        return this.perc;
    }

    public float getSizePerc() {
        return this.size;
    }

    public int getAirTicks() {
        return this.ticksInAir;
    }

    public short getEff() {
        return this.effect;
    }

    public void setAirTicks(int i) {
        this.ticksInAir = i;
    }

    public float get_maxSizeGrowth() {
        return this.maxSizeGrowth[this.jtsre - 1];
    }

    public float get_maxSizeGrowthSpeed() {
        return this.maxSizeGrowthSpeed[this.jtsre - 1];
    }

    public byte getjtsre() {
        return this.jtsre;
    }

    public EntityEnergyAttJ2(World par1World) {
        super(par1World);
        this.func_70105_a(this.size, this.size);
    }

    public EntityEnergyAttJ2(byte var1, EntityLivingBase par2EntityLivingBase, byte type, float speed, int dam, byte effect, byte color, byte density, byte sincantation, byte sfire, byte smove, byte perc, int dam1, int cost, int costPerc) {
        super(par2EntityLivingBase.field_70170_p);
        this.jtsre = var1;
        this.type = type;
        this.speed = speed;
        this.dam = dam;
        this.perc = (byte)50;
        this.effect = effect;
        this.color = JRMCoreH.techNCCol[5];
        this.density = density;
        this.sincantation = sincantation;
        this.sfire = sfire;
        this.smove = smove;
        this.cost = cost;
        this.costPerc = costPerc;
        this.originDmg = dam1;
        this.pmjID = perc;
        if (this.pmjID != -1) {
            this.nameJutsu = JRMCoreH.trl("nc", JRMCoreH.pmj[this.pmjID][0]);
        }
        this.damage = (double)this.dam * (double)this.perc * (double)0.02f;
        this.size = 0.0f;
        this.shootingEntity = par2EntityLivingBase;
        this.pwrtyp = 0;
        if (this.shootingEntity instanceof EntityPlayer) {
            this.pwrtyp = JRMCoreH.PlyrPwr((EntityPlayer)this.shootingEntity);
        }
        this.explevel = effect;
        this.field_70155_l = 10.0;
        this.func_70105_a(this.size, this.size);
        double d8 = par2EntityLivingBase.field_70130_N + 1.0f;
        double d9 = par2EntityLivingBase.field_70131_O;
        Vec3 vec3 = par2EntityLivingBase.func_70676_i(1.0f);
        double x = par2EntityLivingBase.field_70165_t + vec3.field_72450_a * d8;
        double y = par2EntityLivingBase.field_70163_u + vec3.field_72448_b * d8 + (double)(par2EntityLivingBase.field_70131_O * 0.55f);
        double z = par2EntityLivingBase.field_70161_v + vec3.field_72449_c * d8;
        this.func_70012_b(x, y, z, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.speed * (this.getjtsre() == 6 ? 0.8f : 0.7f), 1.0f);
        this.strtX = (float)x;
        this.strtY = (float)y;
        this.strtZ = (float)z;
    }

    @Override
    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(16, (Object)0);
    }

    public void func_70186_c(double par1, double par3, double par5, float par7, float par8) {
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
        if (this.field_70170_p.field_72995_K && !JRMCoreClient.mc.func_147113_T() && this.getjtsre() == 3 && JGConfigClientSettings.CLIENT_GR3) {
            mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART1, Math.random() * 4.0 - 2.0, 0.0 + Math.random() * (double)(this.field_70131_O * 0.25f) + (double)(this.field_70131_O / 2.0f) - (double)(this.field_70131_O * 0.25f), Math.random() * 4.0 - 2.0, Math.random() * 0.05 - 0.025, Math.random() * 0.1 + 0.05, Math.random() * 0.05 - 0.025, 0.3f + this.size / 10.0f, 0.3f + this.size / 10.0f, 0.3f + this.size / 10.0f);
            mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART4, Math.random() * 4.0 - 2.0, 0.0 + (double)(this.field_70131_O / 2.0f), Math.random() * 4.0 - 2.0, Math.random() * 1.2 - 0.6, Math.random() * 0.2 + 0.1, Math.random() * 1.2 - 0.6, 0.05f, 0.01f, 0.1f);
        }
        if (this.field_70170_p.field_72995_K && !this.s_d) {
            if (!this.s_a) {
                this.func_70105_a(this.size, this.size);
                this.size += this.get_maxSizeGrowthSpeed();
                this.s_start = System.nanoTime() / 100000000L;
                this.s_a = true;
            }
            if (this.size >= this.get_maxSizeGrowth()) {
                this.s_d = true;
            }
            if (System.nanoTime() / 100000000L - this.s_start > 3L) {
                this.s_a = false;
            }
        }
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (this.type == 0 && !this.field_70170_p.field_72995_K && this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer) {
            byte b = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng");
            if (b == 0) {
                this.shrink = true;
            }
            if (this.shrink) {
                this.func_70106_y();
            }
        }
        if (this.field_70173_aa == 1) {
            this.func_70105_a(this.size, this.size);
        }
        super.func_70071_h_();
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
        if (this.inGround && this.getjtsre() > 2) {
            int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (block == this.inTile && var19 == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1) {
                    this.func_70106_y();
                }
            } else {
                this.inGround = false;
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            float var11;
            int var9;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (!this.field_70170_p.field_72995_K && (float)this.ticksInAir == 500.0f * ((float)this.perc * 0.02f)) {
                this.func_70106_y();
            }
            if (var4 != null) {
                var3 = Vec3.func_72443_a((double)var4.field_72307_f.field_72450_a, (double)var4.field_72307_f.field_72448_b, (double)var4.field_72307_f.field_72449_c);
            }
            if (!this.field_70170_p.field_72995_K) {
                Entity var5 = null;
                List var6 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(0.5, 0.5, 0.5));
                double var7 = 0.0;
                for (var9 = 0; var9 < var6.size(); ++var9) {
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
            }
            if (!this.field_70170_p.field_72995_K) {
                List var6;
                double e = 1.0;
                if (e < 1.0) {
                    e = 1.0;
                }
                Object var5 = null;
                if (this.getjtsre() < 3) {
                    var6 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
                } else {
                    AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
                    var6 = this.field_70170_p.func_72839_b((Entity)this, aabb);
                }
                for (var9 = 0; var9 < var6.size(); ++var9) {
                    int exp;
                    float res;
                    float calc;
                    float power;
                    float dent;
                    float spet;
                    float damt;
                    float den;
                    float spe;
                    float dam;
                    int td;
                    int ad;
                    short eff;
                    int d;
                    EntityEnAttacks t;
                    Entity var10 = (Entity)var6.get(var9);
                    if (var10 == this.shootingEntity) continue;
                    if (var10 instanceof EntityLivingBase) {
                        if (var4 == null) {
                            var4 = new MovingObjectPosition(var10);
                        }
                        if (this.target != null) continue;
                        this.func_70106_y();
                        JRMCoreH.newExpl(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, false, this.damage, this.shootingEntity, this.explosion_id[this.getjtsre() - 1]);
                        this.target = var10;
                        continue;
                    }
                    if (this.type == 0 && this.wave > 0 && this.shootingEntity instanceof EntityPlayer) {
                        JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
                        this.shrink = true;
                        continue;
                    }
                    if (!(var10 instanceof EntityEnAttacks) && var10 != this.shootingEntity && !(var10 instanceof EntityCusPar)) {
                        if (this.type < 0 || this.type >= 2 || !(this.field_70131_O > 1.5f)) continue;
                        JRMCoreH.newExpl(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, false, this.damage, this.shootingEntity, this.explosion_id[this.getjtsre() - 1]);
                        this.func_70106_y();
                        continue;
                    }
                    if (var10 instanceof EntityEnergyAttJ) {
                        t = (EntityEnergyAttJ)var10;
                        d = (int)((EntityEnergyAttJ)t).getDamage();
                        eff = ((EntityEnergyAttJ)t).getEff();
                        ad = JRMCoreH.cbadmg(this.effect, this.dam, eff, d);
                        if (ad == (td = JRMCoreH.cbtdmg(this.effect, this.dam, eff, d))) {
                            this.func_70106_y();
                        }
                        if (td == 0) {
                            ((EntityEnergyAttJ)t).func_70106_y();
                        } else {
                            ((EntityEnergyAttJ)t).setDamage(td);
                        }
                        dam = (float)(((EntityEnergyAttJ)t).getDamage() / 2.0);
                        spe = (float)((EntityEnergyAttJ)t).getSpe() * 2.0f;
                        den = (float)((EntityEnergyAttJ)t).getDen() * 10.0f;
                        damt = (float)(this.damage / 2.0);
                        spet = this.speed * 2.0f;
                        dent = (float)this.density * 10.0f;
                        power = damt - dam + (spe - spet) + (dent - den);
                        calc = 1.0f - power * 0.01f;
                        if (this.conn == 0) {
                            ++this.conn;
                        }
                        if (this.conn != 1) continue;
                        if (power > 0.0f) {
                            res = ((damt - dam) / damt + (spe - spet) / spe + (dent - den) / dent) / 3.0f;
                            this.field_70159_w *= (double)res;
                            this.field_70181_x *= (double)res;
                            this.field_70179_y *= (double)res;
                            ((EntityEnergyAttJ)t).field_70159_w = this.field_70159_w;
                            ((EntityEnergyAttJ)t).field_70181_x = this.field_70181_x;
                            ((EntityEnergyAttJ)t).field_70179_y = this.field_70179_y;
                            int n = exp = ((EntityEnergyAttJ)t).getAirTicks() < this.ticksInAir ? ((EntityEnergyAttJ)t).getAirTicks() : this.ticksInAir;
                            if (((EntityEnergyAttJ)t).getAirTicks() < this.ticksInAir) {
                                this.ticksInAir = ((EntityEnergyAttJ)t).getAirTicks();
                            }
                        }
                        this.conn = 2;
                        continue;
                    }
                    if (!(var10 instanceof EntityEnergyAttJ2)) continue;
                    t = (EntityEnergyAttJ2)var10;
                    d = (int)((EntityEnergyAttJ2)t).getDamage();
                    eff = ((EntityEnergyAttJ2)t).getEff();
                    ad = JRMCoreH.cbadmg(this.effect, this.dam, eff, d);
                    if (ad == (td = JRMCoreH.cbtdmg(this.effect, this.dam, eff, d))) {
                        this.func_70106_y();
                    }
                    if (td == 0) {
                        ((EntityEnergyAttJ2)t).func_70106_y();
                    } else {
                        ((EntityEnergyAttJ2)t).setDamage(td);
                    }
                    dam = (float)(((EntityEnergyAttJ2)t).getDamage() / 2.0);
                    spe = ((EntityEnergyAttJ2)t).getSpe() * 2.0f;
                    den = (float)((EntityEnergyAttJ2)t).getDen() * 10.0f;
                    damt = (float)(this.damage / 2.0);
                    spet = this.speed * 2.0f;
                    dent = (float)this.density * 10.0f;
                    power = damt - dam + (spe - spet) + (dent - den);
                    calc = 1.0f - power * 0.01f;
                    if (this.conn == 0) {
                        ++this.conn;
                    }
                    if (this.conn != 1) continue;
                    if (power > 0.0f) {
                        res = ((damt - dam) / damt + (spe - spet) / spe + (dent - den) / dent) / 3.0f;
                        this.field_70159_w *= (double)res;
                        this.field_70181_x *= (double)res;
                        this.field_70179_y *= (double)res;
                        ((EntityEnergyAttJ2)t).field_70159_w = this.field_70159_w;
                        ((EntityEnergyAttJ2)t).field_70181_x = this.field_70181_x;
                        ((EntityEnergyAttJ2)t).field_70179_y = this.field_70179_y;
                        int n = exp = ((EntityEnergyAttJ2)t).getAirTicks() < this.ticksInAir ? ((EntityEnergyAttJ2)t).getAirTicks() : this.ticksInAir;
                        if (((EntityEnergyAttJ2)t).getAirTicks() < this.ticksInAir) {
                            this.ticksInAir = ((EntityEnergyAttJ2)t).getAirTicks();
                        }
                    }
                    this.conn = 2;
                }
            }
            if (var4 != null) {
                if (this.target != null && this.shootingEntity instanceof EntityPlayer) {
                    this.trgtX = (float)this.field_70165_t;
                    this.trgtY = (float)this.field_70163_u;
                    this.trgtZ = (float)this.field_70161_v;
                    byte b = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng");
                    if (b == 1) {
                        if (this.waveCount == 20) {
                            this.wave = (byte)(this.wave + 1);
                            if (!this.field_70170_p.field_72995_K) {
                                float cost2;
                                EntityPlayer Player = (EntityPlayer)this.shootingEntity;
                                byte curRel = JRMCoreH.getByte(Player, "jrmcRelease");
                                int curEn = JRMCoreH.getInt(Player, "jrmcEnrgy");
                                if (!((float)curEn - (cost2 = (float)(this.cost * curRel) * 0.02f) * ((float)this.perc * 0.02f) > 0.0f)) {
                                    this.func_70106_y();
                                }
                                if (cost2 < 32000.0f) {
                                    if (!JRMCoreH.isInCreativeMode(this.shootingEntity)) {
                                        JRMCoreH.setInt((float)curEn - cost2 * ((float)this.perc * 0.02f), Player, "jrmcEnrgy");
                                    }
                                    this.damage = (float)(this.originDmg * curRel) * 0.02f;
                                } else {
                                    this.func_70106_y();
                                }
                            }
                            int var23 = (int)this.damage;
                            DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                            if (this.target.func_70097_a(damagesource, (float)var23)) {
                                // empty if block
                            }
                            this.field_70159_w *= (double)0.05f;
                            this.field_70181_x *= (double)0.05f;
                            this.field_70179_y *= (double)0.05f;
                            this.target.field_70159_w *= (double)0.05f;
                            this.target.field_70181_x *= (double)0.05f;
                            this.target.field_70179_y *= (double)0.05f;
                        }
                        this.target.field_70159_w = this.field_70159_w;
                        this.target.field_70181_x = this.field_70181_x;
                        this.target.field_70179_y = this.field_70179_y;
                        --this.waveCount;
                        if (this.waveCount <= 0) {
                            this.waveCount = 20;
                        }
                        if (this.wave >= 5) {
                            this.func_70106_y();
                        }
                    } else {
                        JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
                        this.shrink = true;
                    }
                } else if (var4.field_72308_g != null && this.type == 0) {
                    if (var4.field_72308_g instanceof EntityLivingBase) {
                        this.target = var4.field_72308_g;
                    } else {
                        JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
                        this.shrink = true;
                    }
                } else if (var4.field_72308_g != null) {
                    float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    int var23 = (int)this.damage;
                    if (this.density == 2 && this.shootingEntity != null) {
                        var23 = 0;
                        if (!this.field_70170_p.field_72995_K && var4.field_72308_g instanceof EntityPlayer) {
                            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                            EntityPlayer Player = (EntityPlayer)var4.field_72308_g;
                            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(Player);
                            String[] PlyrSkills = JRMCoreH.getString(Player, "jrmcSSlts").split(",");
                            int t = this.dam;
                            int t2 = t > 30 ? 30 : (t < 1 ? 1 : t);
                            NCJutsus.wgi((MinecraftServer)server, (String)("1;" + this.pmjID + ";" + this.dam + ";" + t2 + ";" + t2), (String)Player.func_70005_c_(), (boolean)false);
                            EntityPlayer shtr = (EntityPlayer)this.shootingEntity;
                            shtr.func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + "Target " + Player.func_70005_c_() + " under the effect of " + this.nameJutsu));
                        }
                    } else if (this.density == 2) {
                        this.func_70106_y();
                    }
                    if (JRMCoreH.DGE(var4.field_72308_g)) {
                        JRMCoreH.jrmcExp(this.shootingEntity, 1, this.getType());
                    }
                    DamageSource damagesource = null;
                    if (this.shootingEntity == null) {
                        this.func_70106_y();
                    } else {
                        damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                    }
                    if (this.func_70027_ad()) {
                        var4.field_72308_g.func_70015_d(5);
                    }
                    if (this.density != 2 && var4.field_72308_g.func_70097_a(damagesource, (float)var23)) {
                        float var25;
                        if (var4.field_72308_g instanceof EntityLivingBase && this.knockbackStrength > 0 && (var25 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                            var4.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)var25, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)var25);
                        }
                        if (this.type >= 0 && this.type <= 2 && this.field_70131_O > 1.5f) {
                            JRMCoreH.newExpl(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, false, this.damage, this.shootingEntity, this.explosion_id[this.getjtsre() - 1]);
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
                    float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    this.field_70165_t -= this.field_70159_w / (double)var20 * (double)0.05f;
                    this.field_70163_u -= this.field_70181_x / (double)var20 * (double)0.05f;
                    this.field_70161_v -= this.field_70179_y / (double)var20 * (double)0.05f;
                    this.inGround = true;
                    if (this.inTile.func_149688_o() != Material.field_151579_a) {
                        this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this);
                    }
                }
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
            var11 = 0.0f;
            if (this.func_70090_H()) {
                for (int var26 = 0; var26 < 4; ++var26) {
                    float var27 = 0.25f;
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)var27, this.field_70163_u - this.field_70181_x * (double)var27, this.field_70161_v - this.field_70179_y * (double)var27, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
            }
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.doBlockCollisions();
        }
    }

    @Override
    public long getPower(Entity entity) {
        return (long)(this.getDamage() / 2.0);
    }

    private void doBlockCollisions() {
        this.func_145775_I();
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
        par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
        par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
        par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.inTile));
        par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
        par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.func_74780_a("damage", this.damage);
        par1NBTTagCompound.func_74774_a("jtsre", this.jtsre);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.func_74765_d("xTile");
        this.yTile = par1NBTTagCompound.func_74765_d("yTile");
        this.zTile = par1NBTTagCompound.func_74765_d("zTile");
        this.inTile = Block.func_149729_e((int)(par1NBTTagCompound.func_74771_c("inTile") & 0xFF));
        this.inData = par1NBTTagCompound.func_74771_c("inData") & 0xFF;
        boolean bl = this.inGround = par1NBTTagCompound.func_74771_c("inGround") == 1;
        if (par1NBTTagCompound.func_74764_b("damage")) {
            this.damage = par1NBTTagCompound.func_74769_h("damage");
        }
        this.jtsre = par1NBTTagCompound.func_74771_c("jtsre");
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

    public boolean func_82704_a(Entity var1) {
        return false;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.shootingEntity == null ? 0 : this.shootingEntity.func_145782_y());
        data.writeInt(this.target == null ? 0 : this.target.func_145782_y());
        data.writeByte((int)this.perc);
        data.writeByte((int)this.type);
        data.writeByte((int)this.jtsre);
        data.writeInt(this.color);
        data.writeInt(this.dam);
        data.writeByte((int)this.density);
        data.writeShort((int)this.sincantation);
        data.writeShort((int)this.sfire);
        data.writeShort((int)this.smove);
        data.writeFloat(this.strtX);
        data.writeFloat(this.strtY);
        data.writeFloat(this.strtZ);
        data.writeFloat(this.size);
        data.writeFloat(this.trgtX);
        data.writeFloat(this.trgtY);
        data.writeFloat(this.trgtZ);
        data.writeByte(this.shrink ? 1 : 0);
    }

    public void readSpawnData(ByteBuf data) {
        int first = data.readInt();
        this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
        int second = data.readInt();
        this.target = first == 0 ? this.target : this.field_70170_p.func_73045_a(second);
        this.perc = data.readByte();
        this.type = data.readByte();
        this.jtsre = data.readByte();
        this.color = data.readInt();
        this.dam = data.readInt();
        this.density = data.readByte();
        this.sincantation = data.readShort();
        this.sfire = data.readShort();
        this.smove = data.readShort();
        this.strtX = data.readFloat();
        this.strtY = data.readFloat();
        this.strtZ = data.readFloat();
        this.size = data.readFloat();
        this.trgtX = data.readFloat();
        this.trgtY = data.readFloat();
        this.trgtZ = data.readFloat();
        this.shrink = data.readByte() == 1;
    }

    public Entity getThrower() {
        return null;
    }

    public void setThrower(Entity entity) {
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isInRangeToRenderVec3D(Vec3 par1Vec3) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0;
    }

    public boolean func_70112_a(double par1) {
        return true;
    }

    public void setJutsuName(String name) {
        this.nameJutsu = name;
    }

    public void func_70106_y() {
        this.field_70128_L = true;
    }
}

