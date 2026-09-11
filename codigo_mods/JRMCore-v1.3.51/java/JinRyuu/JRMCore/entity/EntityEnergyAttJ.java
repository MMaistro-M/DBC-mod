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
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityEnAttacks;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ2;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.server.JGMathHelper;
import JinRyuu.JRMCore.server.JGPlayerClientServerHelper;
import JinRyuu.NarutoC.common.NCJutsus;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
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

public class EntityEnergyAttJ
extends EntityEnAttacks
implements IThrowableEntity,
IEntityAdditionalSpawnData,
IEntitySelector,
IProjectile {
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
    private byte speed;
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
    private float size;
    private int conn;
    private int waveCount = 20;
    private byte wave = 0;
    private Entity target;
    private int cost;
    private int costPerc;
    private int originDmg;
    public boolean shrink = false;
    private int pwrtyp = 0;
    private String nameJutsu;
    private boolean givenExp = false;
    public double motionXStart;
    public double motionYStart;
    public double motionZStart;
    public float minScale;
    public float maxScale;
    public float maxDamage;
    public boolean added = false;
    public int animation_speed = 0;
    public long animation_start = 0L;
    public int animation_id = 0;
    public int animation_id_Max = 0;
    public int animation_random_Max = 0;
    public ArrayList<Integer> animation_random = new ArrayList();
    public float render_scale = 0.0f;
    public float render_scale_max = 2.0f;
    public double dist = 0.0;
    public boolean shooterHolds;
    public boolean hadTarget = false;
    public boolean added2 = false;
    public long animation_start2 = 0L;
    public float waveScale = 1.0f;
    public double finalDist = 0.0;
    public int lastSegments = 0;
    private boolean run = true;
    public float startRotationPitch = 0.0f;
    public float startRotationYaw = 0.0f;

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

    public float getScale() {
        float damage = this.originDmg;
        byte perc = this.getPerc();
        byte den = this.getDen();
        float scale = JRMCoreH.calculateEnergyScale(damage, this.maxDamage, perc, null, den, this.minScale, this.maxScale);
        return scale;
    }

    public void setScales() {
        this.minScale = (float)JRMCoreConfig.JutsuSizeMin[this.getType()];
        this.maxScale = (float)JRMCoreConfig.JutsuSizeMax[this.getType()];
        this.maxDamage = JRMCoreH.getMaxEnergyDamage();
    }

    public float setScalesPost() {
        if (this.isWave()) {
            return 100.0f;
        }
        if (this.isBlast()) {
            return 5.0f;
        }
        if (this.isDisk()) {
            return 5.0f;
        }
        return 1.0f;
    }

    public float getSize() {
        return this.size;
    }

    private void shrinkWave() {
        JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
        if (!this.shrink) {
            this.shrink();
        }
    }

    private void shrink() {
        this.shrink = true;
        this.func_70096_w().func_75692_b(20, (Object)1);
    }

    public boolean isContinuesWave() {
        return this.getType() >= JRMCoreConfig.ContinuesJutsuAttacks.length ? false : JRMCoreConfig.ContinuesJutsuAttacks[this.getType()];
    }

    public int getShrink() {
        return this.func_70096_w().func_75679_c(20);
    }

    public byte getType() {
        return this.type;
    }

    public int getCol() {
        return this.color;
    }

    public byte getSpe() {
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

    public EntityEnergyAttJ(World par1World) {
        super(par1World);
        this.func_70105_a(this.size, this.size);
    }

    public EntityEnergyAttJ(EntityLivingBase par2EntityLivingBase, byte type, float speed, int dam, byte effect, byte color, byte density, byte sincantation, byte sfire, byte smove, byte perc, int dam1, int cost, int costPerc) {
        super(par2EntityLivingBase.field_70170_p);
        Vec3 vec3;
        float size1;
        this.type = type;
        this.shooterHolds = this.isContinuesWave();
        this.speed = (byte)((speed + 1.0f) * 10.0f + (float)(type == 2 ? 10 : 0) + (float)(density == 2 ? 40 : 0));
        this.dam = dam;
        this.perc = (byte)50;
        this.effect = effect;
        this.color = JRMCoreH.techNCCol[effect];
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
        if (this.getType() < 3) {
            this.setScales();
            size1 = this.getScale();
        } else {
            size1 = 1.0f;
        }
        this.size = 0.5f + size1;
        if (JRMCoreConfig.JutsuScalesWithUser) {
            this.size *= this.shootingEntity == null ? 1.0f : this.shootingEntity.field_70131_O / 1.8f;
        }
        this.shootingEntity = par2EntityLivingBase;
        this.pwrtyp = 0;
        if (this.shootingEntity instanceof EntityPlayer) {
            this.pwrtyp = JRMCoreH.PlyrPwr((EntityPlayer)this.shootingEntity);
        }
        this.explevel = effect;
        this.field_70155_l = 10.0;
        this.func_70105_a(this.size, this.size);
        double d8 = par2EntityLivingBase.field_70130_N + 1.0f + 0.3f;
        double d9 = ((EntityLivingBase)this.shootingEntity).field_70131_O + ((EntityLivingBase)this.shootingEntity).field_70131_O * 0.2f;
        if (this.shootingEntity instanceof EntityPlayer) {
            vec3 = this.shootingEntity.func_70040_Z();
        } else {
            float rotationYaw = this.shootingEntity.func_70079_am();
            float rotationPitch = this.shootingEntity.field_70125_A;
            float vx = -MathHelper.func_76126_a((float)this.rad(rotationYaw)) * MathHelper.func_76134_b((float)this.rad(rotationPitch));
            float vz = MathHelper.func_76134_b((float)this.rad(rotationYaw)) * MathHelper.func_76134_b((float)this.rad(rotationPitch));
            float vy = -MathHelper.func_76126_a((float)this.rad(rotationPitch));
            vec3 = Vec3.func_72443_a((double)vx, (double)vy, (double)vz);
        }
        double x = par2EntityLivingBase.field_70165_t + vec3.field_72450_a * d8;
        double y = par2EntityLivingBase.field_70163_u + vec3.field_72448_b * d8 + (double)(par2EntityLivingBase.field_70131_O * 0.7f);
        double z = par2EntityLivingBase.field_70161_v + vec3.field_72449_c * d8;
        this.func_70012_b(x, y, z, par2EntityLivingBase.field_70177_z, par2EntityLivingBase.field_70125_A);
        this.field_70129_M = this.size * 0.5f;
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)this.speed * 0.05f, 1.0f);
        this.strtX = (float)x;
        this.strtY = (float)y;
        this.strtZ = (float)z;
        this.motionXStart = this.field_70159_w;
        this.motionYStart = this.field_70181_x;
        this.motionZStart = this.field_70179_y;
    }

    @Override
    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(20, (Object)0);
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
        byte b;
        float DEAD_DIFFERENCE;
        double kulz;
        double kuly;
        double kulx;
        double kiZ;
        double kiY;
        double kiX;
        double y;
        if ((JGConfigClientSettings.configsChanged || this.run) && this.field_70170_p.field_72995_K && !this.field_70128_L) {
            this.field_70158_ak = JGConfigClientSettings.renderEnergyOutsideView;
        }
        if (this.isContinuesWave() && this.shooterHolds) {
            this.generateParticles(this, this.shootingEntity, this.color, true);
        }
        this.generateParticles(this, this, this.color, false);
        if (!this.run) {
            if (this.field_70125_A != this.startRotationPitch) {
                this.field_70125_A = this.startRotationPitch;
            }
            if (this.field_70177_z != this.startRotationYaw) {
                this.field_70177_z = this.startRotationYaw;
            }
        }
        boolean ROTATION_RELATED = true;
        if (this.run) {
            this.startRotationPitch = this.field_70125_A;
            this.startRotationYaw = this.field_70177_z;
            this.shooterHolds = this.isContinuesWave();
            if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)this.shootingEntity;
                ExtendedPlayer.get(player).setAnimKiShootOn(0);
            }
            this.run = false;
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.target != null && !this.target.field_70128_L && !this.shooterHolds && JGMathHelper.isMotionSmallerThanN(this, 0.001)) {
            this.func_70106_y();
        }
        if (JRMCoreConfig.WavesShrinkOnceLetGo) {
            if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && this.isContinuesWave() && this.shooterHolds) {
                double kulz2;
                double kuly2;
                double kulx2;
                double kiZ2;
                double kiY2;
                double kiX2;
                double d8 = ((EntityLivingBase)this.shootingEntity).field_70130_N + 1.0f + 0.3f;
                double d9 = ((EntityLivingBase)this.shootingEntity).field_70131_O + ((EntityLivingBase)this.shootingEntity).field_70131_O * 0.2f;
                Vec3 vec3 = this.shootingEntity.func_70040_Z();
                double x = this.shootingEntity.field_70165_t + vec3.field_72450_a * d8;
                double y2 = this.shootingEntity.field_70163_u + vec3.field_72448_b * d8 + (double)(this.shootingEntity.field_70131_O * 0.7f) + (double)(this.field_70170_p.field_72995_K ? JGPlayerClientServerHelper.clientPlayerPositioner(this.shootingEntity) : 0.0f);
                double z = this.shootingEntity.field_70161_v + vec3.field_72449_c * d8;
                if (x < 0.0) {
                    x *= -1.0;
                }
                if (y2 < 0.0) {
                    y2 *= -1.0;
                }
                if (z < 0.0) {
                    z *= -1.0;
                }
                if ((kiX2 = (double)this.strtX) < 0.0) {
                    kiX2 *= -1.0;
                }
                if ((kiY2 = (double)this.strtY) < 0.0) {
                    kiY2 *= -1.0;
                }
                if ((kiZ2 = (double)this.strtZ) < 0.0) {
                    kiZ2 *= -1.0;
                }
                if ((kulx2 = x - kiX2) < 0.0) {
                    kulx2 *= -1.0;
                }
                if ((kuly2 = y2 - kiY2) < 0.0) {
                    kuly2 *= -1.0;
                }
                if ((kulz2 = z - kiZ2) < 0.0) {
                    kulz2 *= -1.0;
                }
                float DEAD_DIFFERENCE2 = 0.2f;
                float DEAD_DIFFERENCE22 = 1.0f;
                if (kulx2 > (double)0.2f || kuly2 > 1.0 || kulz2 > (double)0.2f) {
                    this.shooterHolds = false;
                    EntityPlayer player = (EntityPlayer)this.shootingEntity;
                    ExtendedPlayer.get(player).setAnimKiShoot(0);
                    if (!this.field_70170_p.field_72995_K) {
                        JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
                        this.shrinkWave();
                    } else {
                        JRMCoreH.isShtng = false;
                    }
                }
            }
        } else if (this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && this.isContinuesWave() && this.shooterHolds) {
            double x = this.strtX;
            y = this.strtY;
            double z = this.strtZ;
            if (x < 0.0) {
                x *= -1.0;
            }
            if (y < 0.0) {
                y *= -1.0;
            }
            if (z < 0.0) {
                z *= -1.0;
            }
            if ((kiX = this.shootingEntity.field_70165_t) < 0.0) {
                kiX *= -1.0;
            }
            if ((kiY = this.shootingEntity.field_70163_u + (double)(this.field_70170_p.field_72995_K ? JGPlayerClientServerHelper.clientPlayerPositioner(this.shootingEntity) : 0.0f)) < 0.0) {
                kiY *= -1.0;
            }
            if ((kiZ = this.shootingEntity.field_70161_v) < 0.0) {
                kiZ *= -1.0;
            }
            if ((kulx = x - kiX) < 0.0) {
                kulx *= -1.0;
            }
            if ((kuly = y - kiY) < 0.0) {
                kuly *= -1.0;
            }
            if ((kulz = z - kiZ) < 0.0) {
                kulz *= -1.0;
            }
            DEAD_DIFFERENCE = 3.0f;
            if (kulx > 3.0 || kuly > 3.0 || kulz > 3.0) {
                this.shooterHolds = false;
                EntityPlayer player = (EntityPlayer)this.shootingEntity;
                ExtendedPlayer.get(player).setAnimKiShoot(0);
                if (!this.field_70170_p.field_72995_K) {
                    JRMCoreH.setByte(0, (EntityPlayer)this.shootingEntity, "jrmcFrng");
                } else {
                    JRMCoreH.isShtng = false;
                }
            }
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && JRMCoreConfig.ContinuesEnergyAttackEnemyLock && this.target != null && !this.target.field_70128_L && this.shooterHolds) {
            double x = this.field_70165_t;
            y = this.field_70163_u;
            double z = this.field_70161_v;
            if (x < 0.0) {
                x *= -1.0;
            }
            if (y < 0.0) {
                y *= -1.0;
            }
            if (z < 0.0) {
                z *= -1.0;
            }
            if ((kiX = this.target.field_70165_t) < 0.0) {
                kiX *= -1.0;
            }
            if ((kiY = this.target.field_70163_u) < 0.0) {
                kiY *= -1.0;
            }
            if ((kiZ = this.target.field_70161_v) < 0.0) {
                kiZ *= -1.0;
            }
            if ((kulx = x - kiX) < 0.0) {
                kulx *= -1.0;
            }
            if ((kuly = y - kiY) < 0.0) {
                kuly *= -1.0;
            }
            if ((kulz = z - kiZ) < 0.0) {
                kulz *= -1.0;
            }
            DEAD_DIFFERENCE = 0.5f;
            if (kulx > 0.5 || kuly > 0.5 || kulz > 0.5) {
                this.target.field_70165_t = x;
                this.target.field_70163_u = y;
                this.target.field_70161_v = z;
                this.target.field_70165_t = this.field_70165_t;
                this.target.field_70163_u = this.field_70163_u;
                this.target.field_70161_v = this.field_70161_v;
                this.target.field_70159_w = this.field_70159_w;
                this.target.field_70181_x = this.field_70181_x;
                this.target.field_70179_y = this.field_70179_y;
            }
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown && JRMCoreConfig.ContinuesEnergyAttackMoveOnLostTarget && this.target != null && !this.target.field_70128_L && this.shooterHolds && JGMathHelper.isMotionSmallerThanN(this, 0.001)) {
            double x = this.field_70165_t;
            y = this.field_70163_u;
            double z = this.field_70161_v;
            if (x < 0.0) {
                x *= -1.0;
            }
            if (y < 0.0) {
                y *= -1.0;
            }
            if (z < 0.0) {
                z *= -1.0;
            }
            if ((kiX = this.target.field_70165_t) < 0.0) {
                kiX *= -1.0;
            }
            if ((kiY = this.target.field_70163_u) < 0.0) {
                kiY *= -1.0;
            }
            if ((kiZ = this.target.field_70161_v) < 0.0) {
                kiZ *= -1.0;
            }
            if ((kulx = x - kiX) < 0.0) {
                kulx *= -1.0;
            }
            if ((kuly = y - kiY) < 0.0) {
                kuly *= -1.0;
            }
            if ((kulz = z - kiZ) < 0.0) {
                kulz *= -1.0;
            }
            if (kulx > (double)(DEAD_DIFFERENCE = this.size + 1.0f) || kuly > (double)DEAD_DIFFERENCE || kulz > (double)DEAD_DIFFERENCE) {
                this.target = null;
                this.hadTarget = false;
                this.field_70159_w = this.motionXStart;
                this.field_70181_x = this.motionYStart;
                this.field_70179_y = this.motionZStart;
            }
        }
        if (!this.field_70170_p.field_72995_K && JRMCoreConfig.WavesDieWhenTargetAway && this.shootingEntity != null && this.target != null && this.shootingEntity instanceof EntityPlayer && this.isContinuesWave()) {
            double x = this.field_70165_t;
            y = this.field_70163_u;
            double z = this.field_70161_v;
            if (x < 0.0) {
                x *= -1.0;
            }
            if (y < 0.0) {
                y *= -1.0;
            }
            if (z < 0.0) {
                z *= -1.0;
            }
            if ((kiX = this.target.field_70165_t) < 0.0) {
                kiX *= -1.0;
            }
            if ((kiY = this.target.field_70163_u) < 0.0) {
                kiY *= -1.0;
            }
            if ((kiZ = this.target.field_70161_v) < 0.0) {
                kiZ *= -1.0;
            }
            if ((kulx = x - kiX) < 0.0) {
                kulx *= -1.0;
            }
            if ((kuly = y - kiY) < 0.0) {
                kuly *= -1.0;
            }
            if ((kulz = z - kiZ) < 0.0) {
                kulz *= -1.0;
            }
            if (kulx > (double)(DEAD_DIFFERENCE = this.size + 1.0f) || kuly > (double)DEAD_DIFFERENCE || kulz > (double)DEAD_DIFFERENCE) {
                if (this.type >= 0 && this.type < 2) {
                    if (this.field_70131_O > 1.5f) {
                        this.createExplosion(0);
                    }
                    this.field_70170_p.func_72956_a((Entity)this, this.pwrtyp == 1 ? this.DBCExplSound : (this.pwrtyp == 2 ? this.NCExplSound : ""), 1.0f, 1.0f);
                }
                this.func_70106_y();
            }
        }
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && (b = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng")) == 0 && !this.shrink && JRMCoreConfig.WavesShrinkOnceLetGo) {
            this.shrink();
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.hadTarget && (this.target == null || this.target.field_70128_L)) {
            this.func_70106_y();
        }
        if (this.isContinuesWave() && this.target != null) {
            if (JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown) {
                this.field_70159_w *= (double)0.05f;
                this.field_70181_x *= (double)0.05f;
                this.field_70179_y *= (double)0.05f;
                this.target.field_70159_w *= (double)0.05f;
                this.target.field_70181_x *= (double)0.05f;
                this.target.field_70179_y *= (double)0.05f;
            } else {
                this.target.field_70159_w = this.field_70159_w;
                this.target.field_70181_x = this.field_70181_x;
                this.target.field_70179_y = this.field_70179_y;
                this.target.field_70165_t = this.field_70165_t;
                this.target.field_70163_u = this.field_70163_u;
                this.target.field_70161_v = this.field_70161_v;
            }
        }
        if (this.field_70173_aa == 1) {
            this.func_70105_a(this.size, this.size);
            this.field_70129_M = this.size * 0.5f;
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
        if (this.inGround) {
            int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (block == this.inTile && var19 == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround == 1) {
                    this.func_70106_y();
                    if (!this.field_70170_p.field_72995_K) {
                        if (this.type >= 0 && this.type < 2) {
                            if (this.field_70131_O > 1.5f) {
                                this.createExplosion(0);
                            }
                            this.field_70170_p.func_72956_a((Entity)this, this.pwrtyp == 1 ? this.DBCExplSound : (this.pwrtyp == 2 ? this.NCExplSound : ""), 1.0f, 1.0f);
                        }
                        if (this.density == 2) {
                            EntityPlayer shtr = (EntityPlayer)this.shootingEntity;
                            shtr.func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + this.nameJutsu + " failed!"));
                        }
                    }
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
            int test;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (!this.field_70170_p.field_72995_K && ((float)this.ticksInAir >= (float)JRMCoreConfig.EnergyAttackMaxLifeTickPercMulti * ((float)this.perc * 0.02f) || this.ticksInAir >= JRMCoreConfig.EnergyAttackMaxLifeTick)) {
                this.func_70106_y();
            }
            if (var4 != null) {
                var3 = Vec3.func_72443_a((double)var4.field_72307_f.field_72450_a, (double)var4.field_72307_f.field_72448_b, (double)var4.field_72307_f.field_72449_c);
            }
            if (!this.field_70170_p.field_72995_K) {
                Entity var5 = null;
                List var6 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(0.5, 0.5, 0.5));
                double var7 = 0.0;
                for (int var9 = 0; var9 < var6.size(); ++var9) {
                    double var14;
                    float var11;
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
            if (this.field_70159_w <= 0.01 && this.field_70181_x <= 0.01 && this.field_70179_y <= 0.01 && this.field_70159_w >= -0.01 && this.field_70181_x >= -0.01 && this.field_70179_y >= -0.01 && !this.shrink && !this.isContinuesWave()) {
                this.shrink();
            }
            if (!this.field_70170_p.field_72995_K) {
                Object var5 = null;
                AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
                List var6 = this.field_70170_p.func_72839_b((Entity)this, aabb);
                for (int var9 = 0; var9 < var6.size(); ++var9) {
                    int td;
                    float res;
                    float calc;
                    float power;
                    float spet;
                    float den;
                    Entity var10 = (Entity)var6.get(var9);
                    if (var10 == this.shootingEntity) continue;
                    if (this.isContinuesWave() && var10 instanceof EntityLivingBase) {
                        if (var4 == null) {
                            var4 = new MovingObjectPosition(var10);
                        }
                        if (this.target != null) continue;
                        this.target = var10;
                        continue;
                    }
                    if (this.isContinuesWave() && this.wave > 0 && this.shootingEntity instanceof EntityPlayer) {
                        this.shrinkWave();
                        continue;
                    }
                    if (!(var10 instanceof EntityEnAttacks) && var10 != this.shootingEntity) {
                        if (this.type < 0 || this.type >= 2 || !(this.field_70131_O > 1.5f)) continue;
                        this.createExplosion(0);
                        this.func_70106_y();
                        continue;
                    }
                    if (var10 instanceof EntityEnergyAttJ) {
                        int td2;
                        EntityEnergyAttJ t = (EntityEnergyAttJ)var10;
                        int d = (int)t.getDamage();
                        short eff = t.getEff();
                        int ad = JRMCoreH.cbadmg(this.effect, this.dam, eff, d);
                        if (ad == (td2 = JRMCoreH.cbtdmg(this.effect, this.dam, eff, d))) {
                            this.func_70106_y();
                        }
                        if (td2 == 0) {
                            t.func_70106_y();
                        } else {
                            t.setDamage(td2);
                        }
                        float dam = (float)(t.getDamage() / 2.0);
                        float spe = (float)t.getSpe() * 2.0f;
                        den = (float)t.getDen() * 10.0f;
                        float damt = (float)(this.damage / 2.0);
                        spet = (float)this.speed * 2.0f;
                        float dent = (float)this.density * 10.0f;
                        power = damt - dam + (spe - spet) + (dent - den);
                        calc = 1.0f - power * 0.01f;
                        if (this.conn == 0) {
                            ++this.conn;
                        }
                        if (this.conn != 1) continue;
                        if (power > 0.0f) {
                            int exp;
                            res = ((damt - dam) / damt + (spe - spet) / spe + (dent - den) / dent) / 3.0f;
                            this.field_70159_w *= (double)res;
                            this.field_70181_x *= (double)res;
                            this.field_70179_y *= (double)res;
                            t.field_70159_w = this.field_70159_w;
                            t.field_70181_x = this.field_70181_x;
                            t.field_70179_y = this.field_70179_y;
                            int n = exp = t.getAirTicks() < this.ticksInAir ? t.getAirTicks() : this.ticksInAir;
                            if (t.getAirTicks() < this.ticksInAir) {
                                this.ticksInAir = t.getAirTicks();
                            }
                        }
                        this.conn = 2;
                        continue;
                    }
                    if (!(var10 instanceof EntityEnergyAttJ2)) continue;
                    EntityEnergyAttJ2 t = (EntityEnergyAttJ2)var10;
                    int d = (int)t.getDamage();
                    short eff = t.getEff();
                    int ad = JRMCoreH.cbadmg(this.effect, this.dam, eff, d);
                    if (ad == (td = JRMCoreH.cbtdmg(this.effect, this.dam, eff, d))) {
                        this.func_70106_y();
                    }
                    if (td == 0) {
                        t.func_70106_y();
                    } else {
                        t.setDamage(td);
                    }
                    float dam = (float)(t.getDamage() / 2.0);
                    float spe = t.getSpe() * 2.0f;
                    den = (float)t.getDen() * 10.0f;
                    float damt = (float)(this.damage / 2.0);
                    spet = (float)this.speed * 2.0f;
                    float dent = (float)this.density * 10.0f;
                    power = damt - dam + (spe - spet) + (dent - den);
                    calc = 1.0f - power * 0.01f;
                    if (this.conn == 0) {
                        ++this.conn;
                    }
                    if (this.conn != 1) continue;
                    if (power > 0.0f) {
                        int exp;
                        res = ((damt - dam) / damt + (spe - spet) / spe + (dent - den) / dent) / 3.0f;
                        this.field_70159_w *= (double)res;
                        this.field_70181_x *= (double)res;
                        this.field_70179_y *= (double)res;
                        t.field_70159_w = this.field_70159_w;
                        t.field_70181_x = this.field_70181_x;
                        t.field_70179_y = this.field_70179_y;
                        int n = exp = t.getAirTicks() < this.ticksInAir ? t.getAirTicks() : this.ticksInAir;
                        if (t.getAirTicks() < this.ticksInAir) {
                            this.ticksInAir = t.getAirTicks();
                        }
                    }
                    this.conn = 2;
                }
            }
            if (var4 != null && var4.field_72308_g != this.shootingEntity) {
                if (var4.field_72308_g != null && (this.shootingEntity instanceof EntityPlayer || var4.field_72308_g instanceof EntityPlayer) && this.isContinuesWave() && this.shooterHolds) {
                    if (this.shootingEntity instanceof EntityPlayer) {
                        this.trgtX = (float)this.field_70165_t;
                        this.trgtY = (float)this.field_70163_u;
                        this.trgtZ = (float)this.field_70161_v;
                        byte b2 = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng");
                        if (b2 == 1) {
                            if (this.target != null) {
                                if (this.waveCount == 20) {
                                    this.wave = (byte)(this.wave + 1);
                                    if (JRMCoreConfig.ContinuesEnergyAttackTimer == 0 && this.wave > 2) {
                                        this.wave = (byte)2;
                                    }
                                    if (!this.field_70170_p.field_72995_K) {
                                        float cost2;
                                        EntityPlayer Player = (EntityPlayer)this.shootingEntity;
                                        byte curRel = JRMCoreH.getByte(Player, "jrmcRelease");
                                        int curEn = JRMCoreH.getInt(Player, "jrmcEnrgy");
                                        if (!((float)curEn - (cost2 = (float)((double)this.cost * (double)curRel * (double)0.01f * (double)((float)this.perc * 0.02f))) > 0.0f)) {
                                            this.func_70106_y();
                                        }
                                        if (cost2 < (float)curEn) {
                                            if (!JRMCoreH.isInCreativeMode(this.shootingEntity)) {
                                                JRMCoreH.setInt((float)curEn - cost2, Player, "jrmcEnrgy");
                                            }
                                            this.damage = (double)this.originDmg * (double)curRel * (double)0.01f * (double)this.perc * (double)0.02f * JRMCoreConfig.dat5696[this.type][1];
                                        } else {
                                            this.func_70106_y();
                                        }
                                    }
                                    int var23 = (int)this.damage;
                                    DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                                    if (this.target.func_70097_a(damagesource, (float)var23)) {
                                        // empty if block
                                    }
                                    if (JRMCoreConfig.ContinuesEnergyAttackTargetSlowdown) {
                                        this.field_70159_w *= (double)0.05f;
                                        this.field_70181_x *= (double)0.05f;
                                        this.field_70179_y *= (double)0.05f;
                                        this.target.field_70159_w *= (double)0.05f;
                                        this.target.field_70181_x *= (double)0.05f;
                                        this.target.field_70179_y *= (double)0.05f;
                                    } else {
                                        this.target.field_70159_w = this.field_70159_w;
                                        this.target.field_70181_x = this.field_70181_x;
                                        this.target.field_70179_y = this.field_70179_y;
                                        this.target.field_70165_t = this.field_70165_t;
                                        this.target.field_70163_u = this.field_70163_u;
                                        this.target.field_70161_v = this.field_70161_v;
                                    }
                                }
                                this.target.field_70159_w = this.field_70159_w;
                                this.target.field_70181_x = this.field_70181_x;
                                this.target.field_70179_y = this.field_70179_y;
                            }
                            --this.waveCount;
                            if (this.waveCount <= 0) {
                                this.waveCount = 20;
                            }
                            if (JRMCoreConfig.ContinuesEnergyAttackTimer > 0 && this.wave >= JRMCoreConfig.ContinuesEnergyAttackTimer) {
                                this.func_70106_y();
                            }
                        } else {
                            this.shrinkWave();
                        }
                    }
                    if (var4.field_72308_g instanceof EntityLivingBase) {
                        this.setTarget(var4.field_72308_g);
                    } else {
                        this.shrinkWave();
                    }
                } else if (var4.field_72308_g != null && this.isContinuesWave()) {
                    if (var4.field_72308_g instanceof EntityLivingBase) {
                        this.target = var4.field_72308_g;
                    } else {
                        this.shrinkWave();
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
                    if (JRMCoreH.DGE(var4.field_72308_g) && !this.givenExp) {
                        JRMCoreH.jrmcExp(this.shootingEntity, 1, this.getType());
                        this.givenExp = true;
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
                        if (this.type >= 0 && this.type <= 2 && this.field_70131_O > 1.5f) {
                            this.createExplosion(0);
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
            for (test = 0; this.field_70125_A - this.field_70127_C >= 180.0f && test < 20; ++test) {
                this.field_70127_C += 360.0f;
            }
            for (test = 0; this.field_70177_z - this.field_70126_B < -180.0f && test < 20; ++test) {
                this.field_70126_B -= 360.0f;
            }
            for (test = 0; this.field_70177_z - this.field_70126_B >= 180.0f && test < 20; ++test) {
                this.field_70126_B += 360.0f;
            }
            float var22 = 1.0f;
            float var11 = 0.0f;
            if (this.func_70090_H()) {
                for (int var26 = 0; var26 < 4; ++var26) {
                    float var27 = 0.25f;
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)var27, this.field_70163_u - this.field_70181_x * (double)var27, this.field_70161_v - this.field_70179_y * (double)var27, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                }
                var22 = 1.0f;
            }
            this.field_70159_w *= (double)var22;
            this.field_70181_x *= (double)var22;
            this.field_70179_y *= (double)var22;
            this.field_70181_x -= (double)var11;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.doBlockCollisions();
        }
        if (this.field_70170_p.field_72995_K && this.field_70128_L && this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && this.shooterHolds) {
            EntityPlayer player = (EntityPlayer)this.shootingEntity;
            ExtendedPlayer.get(player).setAnimKiShoot(0);
            this.shrinkWave();
        }
    }

    public void func_70106_y() {
        super.func_70106_y();
        if (this.field_70170_p.field_72995_K && this.field_70170_p.field_72995_K && this.field_70128_L && this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && this.shooterHolds) {
            EntityPlayer player = (EntityPlayer)this.shootingEntity;
            ExtendedPlayer.get(player).setAnimKiShoot(0);
            this.shrinkWave();
            JRMCoreH.isShtng = false;
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
    public void func_70014_b(NBTTagCompound nbt) {
        nbt.func_74777_a("xTile", (short)this.xTile);
        nbt.func_74777_a("yTile", (short)this.yTile);
        nbt.func_74777_a("zTile", (short)this.zTile);
        nbt.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.inTile));
        nbt.func_74774_a("inData", (byte)this.inData);
        nbt.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
        nbt.func_74780_a("damage", this.damage);
    }

    @Override
    public void func_70037_a(NBTTagCompound nbt) {
        this.xTile = nbt.func_74765_d("xTile");
        this.yTile = nbt.func_74765_d("yTile");
        this.zTile = nbt.func_74765_d("zTile");
        this.inTile = Block.func_149729_e((int)(nbt.func_74771_c("inTile") & 0xFF));
        this.inData = nbt.func_74771_c("inData") & 0xFF;
        boolean bl = this.inGround = nbt.func_74771_c("inGround") == 1;
        if (nbt.func_74764_b("damage")) {
            this.damage = nbt.func_74769_h("damage");
        }
    }

    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
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
        data.writeShort((int)this.effect);
        data.writeDouble(this.damage);
    }

    public void readSpawnData(ByteBuf data) {
        int first = data.readInt();
        this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
        int second = data.readInt();
        this.target = first == 0 ? this.target : this.field_70170_p.func_73045_a(second);
        this.perc = data.readByte();
        this.type = data.readByte();
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
        this.effect = data.readShort();
        this.damage = data.readDouble();
    }

    public boolean isWave() {
        return this.getType() == 0;
    }

    public boolean isBlast() {
        return this.getType() == 1;
    }

    public boolean isDisk() {
        return this.getType() == 2;
    }

    public boolean isFireElement() {
        return this.getEff() == 0;
    }

    public boolean isWindElement() {
        return this.getEff() == 1;
    }

    public boolean isLightningElement() {
        return this.getEff() == 2;
    }

    public boolean isEarthElement() {
        return this.getEff() == 3;
    }

    public boolean isWaterElement() {
        return this.getEff() == 4;
    }

    private void createExplosion(int type) {
        JRMCoreH.newExpl(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.size, false, this.damage, this.shootingEntity, (byte)type);
    }

    private void setTarget(Entity entity) {
        this.target = entity;
        this.hadTarget = true;
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

    public void generateParticles(EntityEnergyAttJ entityBlast, Entity entity, int color, boolean startSpawn) {
        if (entityBlast != null && entity != null && entityBlast.field_70170_p.field_72995_K) {
            for (int i = 0; i < 3; ++i) {
                for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                    double z2;
                    double y2;
                    double x2;
                    float green2;
                    float blue2;
                    float colorFixer = 0.7f;
                    float red = (float)(color >> 16 & 0xFF) / 255.0f;
                    float green = (float)(color >> 8 & 0xFF) / 255.0f;
                    float blue = (float)(color & 0xFF) / 255.0f;
                    red *= 0.7f;
                    green *= 0.7f;
                    blue *= 0.7f;
                    float red2 = red * 2.0f;
                    if (red2 > 1.0f) {
                        red2 = 1.0f;
                    }
                    if ((blue2 = blue * 2.0f) > 1.0f) {
                        blue2 = 1.0f;
                    }
                    if ((green2 = green * 2.0f) > 1.0f) {
                        green2 = 1.0f;
                    }
                    float alpha = 1.0f;
                    float out = 1.0f;
                    float in = 1.5f;
                    float life = 0.4f * entity.field_70131_O;
                    float extra_scale = 0.3f;
                    int dea = 30;
                    float target_fullsize_one1 = 0.32f;
                    float targetsizeMin = entity.field_70131_O * (8.0f / target_fullsize_one1) * 0.01f;
                    float target_fullsize_one2 = 0.32f;
                    float targetsizeMax = entity.field_70131_O * (26.0f / target_fullsize_one2) * 0.01f;
                    double x = (Math.random() * (double)(entity.field_70131_O * 2.0f) - (double)entity.field_70131_O) * (double)0.8f;
                    double y = (Math.random() * (double)(entity.field_70131_O * 2.0f) - (double)entity.field_70131_O) * (double)0.8f;
                    double z = (Math.random() * (double)(entity.field_70131_O * 2.0f) - (double)entity.field_70131_O) * (double)0.8f;
                    Vec3 vec3 = entity.func_70040_Z();
                    double d8 = entity.field_70130_N + (startSpawn ? 0.0f : 1.5f);
                    double d9 = entity.field_70131_O;
                    if (startSpawn) {
                        x2 = entityBlast.strtX();
                        y2 = entityBlast.strtY();
                        z2 = entityBlast.strtZ();
                        x2 += vec3.field_72450_a * d8;
                        y2 += vec3.field_72448_b * d9 + (double)(entity.field_70131_O * 0.4f);
                        z2 += vec3.field_72449_c * d8;
                    } else {
                        x2 = entityBlast.field_70165_t;
                        y2 = entityBlast.field_70163_u;
                        z2 = entityBlast.field_70161_v;
                    }
                    x2 += x;
                    y2 += y;
                    z2 += z;
                    float rotationYaw = -entityBlast.field_70177_z;
                    float rotationPitch = -entityBlast.field_70125_A;
                    double motionX = -MathHelper.func_76126_a((float)(rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(rotationPitch / 180.0f * (float)Math.PI));
                    double motionZ = MathHelper.func_76134_b((float)(rotationYaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(rotationPitch / 180.0f * (float)Math.PI));
                    double motionY = -MathHelper.func_76126_a((float)(rotationPitch / 180.0f * (float)Math.PI));
                    if (startSpawn) {
                        x2 += -motionX * 3.0;
                        y2 += -motionY * 3.0;
                        y2 -= (double)entityBlast.field_70131_O * 0.25;
                        z2 += -motionZ * 3.0;
                    }
                    motionX *= 0.5;
                    motionY *= 0.5;
                    motionY += (double)((float)(Math.random() * (double)0.1f) - 0.05f);
                    motionZ *= 0.5;
                    if (startSpawn) {
                        motionX *= -1.0;
                        motionY *= -1.0;
                        motionZ *= -1.0;
                    }
                    float scaleStart = ((float)(Math.random() * (double)0.02f) + 0.02f) * life * 0.3f;
                    float scaleEnd = ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.3f;
                    float scaleSpeed = 0.2f * life * 0.3f;
                    int textureID = (int)(Math.random() * 3.0) + 8;
                    EntityCusPar particle = new EntityCusPar("jinryuumodscore:bens_particles.png", entity.field_70170_p, 0.2f, 0.2f, x2, y2, z2, 0.0, 0.0, 0.0, -motionX, -motionY, -motionZ, 0.0f, textureID, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 30, 2, scaleStart, scaleEnd, scaleStart, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.6f, 0.0f, 0.9f, 0.95f, 0.06f, false, -1, true, null);
                    entity.field_70170_p.func_72838_d((Entity)particle);
                    EntityCusPar particle2 = new EntityCusPar("jinryuumodscore:bens_particles.png", entity.field_70170_p, 0.2f, 0.2f, x2, y2, z2, 0.0, 0.0, 0.0, -motionX, -motionY, -motionZ, 0.0f, textureID, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 30, 2, scaleStart * 0.8f, scaleEnd * 0.8f, scaleStart * 0.8f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.6f, 0.0f, 0.9f, 0.95f, 0.06f, false, -1, true, null);
                    entity.field_70170_p.func_72838_d((Entity)particle2);
                }
            }
        }
    }

    public float rad(float angle) {
        return angle * (float)Math.PI / 180.0f;
    }
}

