/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.common.registry.IThrowableEntity
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.DragonBC.common.DBCClientTickHandler;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityEnAttacks;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ3;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.server.JGMathHelper;
import JinRyuu.JRMCore.server.JGPlayerClientServerHelper;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import cpw.mods.fml.common.network.ByteBufUtils;
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
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityEnergyAtt
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
    private double damageOriginal = 1.0;
    private int damageTaken = 1;
    private int knockbackStrength;
    private float explevel;
    private float Expl = 4.0f;
    private String ExplSound = "jinryuudragonbc:DBC.expl";
    private String AirSound = "jinryuudragonbc:DBC.hafire";
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
    private short effect;
    private int color;
    private int color2;
    private byte density;
    private short sincantation;
    private short sfire;
    private short smove;
    private byte[] sts;
    private byte technum;
    private byte align;
    private float size;
    private int conn;
    private int waveCount = 20;
    private byte wave = 0;
    private Entity target;
    private int cost;
    private int originDmg;
    private boolean shrink = false;
    private byte relFired = (byte)100;
    public boolean givenExp = false;
    public double motionXStart;
    public double motionYStart;
    public double motionZStart;
    public boolean givenTP = false;
    public boolean destroyer = false;
    public float DAMAGE_REDUCTION = JGConfigDBCGoD.CONFIG_GOD_ENERGY_DAMAGE_MULTI;
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
    public float minScale;
    public float maxScale;
    public float maxDamage;
    private boolean run = true;
    private int cb = 50;
    private boolean kiClashed;
    private List kiClashedList = new ArrayList();
    public float startRotationPitch = 0.0f;
    public float startRotationYaw = 0.0f;
    private final byte REACTION_DEAD = 1;
    private final byte REACTION_KILL = (byte)2;
    private final byte REACTION_KILL_EFFECT = (byte)3;
    private final byte REACTION_DAMAGE = (byte)4;
    private final byte REACTION_DAMAGE_EFFECT = (byte)5;

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

    public int getShrink() {
        return this.func_70096_w().func_75679_c(20);
    }

    public byte getType() {
        return this.type;
    }

    public int getCol() {
        return this.color;
    }

    public int getCol2() {
        return this.color2;
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

    public int getAirTicks() {
        return this.ticksInAir;
    }

    public byte[] getSts() {
        return this.sts;
    }

    public void setAirTicks(int i) {
        this.ticksInAir = i;
    }

    public short getEff() {
        return this.effect;
    }

    public float getSize() {
        return this.size;
    }

    public float getScale() {
        float damage = this.originDmg;
        byte perc = this.getPerc();
        byte[] sts = this.getSts();
        byte den = this.getDen();
        float scale = JRMCoreH.calculateEnergyScale(damage, this.maxDamage, perc, sts, den, this.minScale, this.maxScale);
        return scale;
    }

    public void setScales() {
        this.minScale = (float)JRMCoreConfig.KiSizeMin[this.getType()];
        this.maxScale = (float)JRMCoreConfig.KiSizeMax[this.getType()];
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
        if (this.isLaser()) {
            return 5.0f;
        }
        if (this.isLargeBlast()) {
            return 10000.0f;
        }
        if (this.isSpiral()) {
            return 5.0f;
        }
        if (this.isBarrage()) {
            return 5.0f;
        }
        if (this.isShield()) {
            return 5.0f;
        }
        if (this.isExplosion()) {
            return 20.0f;
        }
        return 1.0f;
    }

    public EntityEnergyAtt(World par1World) {
        super(par1World);
        this.func_70105_a(this.size, this.size);
    }

    public EntityEnergyAtt(EntityLivingBase entityLiving, byte type, byte speed, int dam, byte effect, byte color, byte density, byte sincantation, byte sfire, byte smove, byte perc, int dam1, int cost, byte[] sts, byte technum) {
        this(entityLiving, type, speed, dam, effect, color, density, sincantation, sfire, smove, perc, dam1, cost, sts, technum, -100, -100);
    }

    public EntityEnergyAtt(EntityLivingBase entityLiving, byte type, byte speed, int dam, byte effect, byte color, byte density, byte sincantation, byte sfire, byte smove, byte perc, int dam1, int cost, byte[] sts, byte technum, byte release, byte align) {
        super(entityLiving.field_70170_p);
        double z;
        double y;
        double x;
        this.type = type;
        this.shooterHolds = this.isContinuesWave();
        this.speed = (byte)((float)((speed + 1) * 10 + (type == 2 ? 10 : 0) + (density == 2 ? 40 : 0)) * (1.0f + JRMCoreH.tech_statmod(sts, 0)));
        this.dam = dam;
        this.perc = perc;
        this.effect = effect;
        this.color = color;
        this.color2 = -1;
        this.density = (byte)(sts[5] + 1);
        this.sincantation = sincantation;
        this.sfire = sfire;
        this.smove = smove;
        this.sts = sts;
        this.technum = technum;
        this.cost = cost;
        this.originDmg = dam1;
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entityLiving;
            this.relFired = release != -100 ? release : JRMCoreH.getByte(player, "jrmcRelease");
            byte by = this.align = align != -100 ? align : JRMCoreH.getByte(player, "jrmcAlign");
            if (color == 0) {
                if (this.align > 66) {
                    this.color = 2;
                } else if (this.align <= 66 && this.align >= 33) {
                    this.color = 3;
                } else if (this.align < 33) {
                    this.color = 4;
                }
            }
        } else if (color == 0) {
            this.color = 3;
        }
        this.damageOriginal = this.damage = (double)dam1 * (double)this.relFired * (double)0.01f * (double)perc * (double)0.02f * JRMCoreConfig.dat5696[type][1];
        if (!this.isShield() && !this.isExplosion()) {
            this.setScales();
            float size1 = this.getScale();
            this.size = 0.5f + size1;
        } else {
            this.minScale = 0.001f;
            this.maxScale = 0.1f;
            this.maxDamage = JRMCoreH.getMaxEnergyDamage();
        }
        this.shootingEntity = entityLiving;
        this.explevel = !this.isShield() && !this.isExplosion() ? this.size * (float)JRMCoreConfig.eaes : 0.0f;
        this.field_70155_l = 10.0;
        if (JRMCoreConfig.eaesl > 0 && this.size > (float)JRMCoreConfig.eaesl) {
            this.size = JRMCoreConfig.eaesl;
        }
        if (JRMCoreConfig.ExplosionSizeLimit > 0.0 && (double)this.explevel > JRMCoreConfig.ExplosionSizeLimit) {
            this.explevel = (float)JRMCoreConfig.ExplosionSizeLimit;
        }
        if (this.isLargeBlast()) {
            this.size *= JRMCoreConfig.ealbm;
            this.explevel *= JRMCoreConfig.ealbm;
        }
        if (JRMCoreConfig.KiAttackScalesWithUser) {
            float extraScale = this.shootingEntity == null ? 1.0f : this.shootingEntity.field_70131_O / 1.8f;
            this.size *= extraScale;
            this.explevel *= extraScale;
        }
        this.func_70105_a(this.size, this.size);
        if (!this.isShield() && !this.isExplosion()) {
            Vec3 vec3;
            double d8 = entityLiving.field_70130_N + 1.0f + 1.0f;
            double d9 = entityLiving.field_70131_O + 0.2f;
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
            x = entityLiving.field_70165_t + vec3.field_72450_a * d8;
            y = entityLiving.field_70163_u + vec3.field_72448_b * d8 + (double)(entityLiving.field_70131_O * 0.7f);
            z = entityLiving.field_70161_v + vec3.field_72449_c * d8;
            this.func_70012_b(x, y, z, entityLiving.func_70079_am(), entityLiving.field_70125_A);
            this.field_70129_M = this.size * 0.5f;
            this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)((double)((float)this.speed * 0.05f) * JRMCoreConfig.dat5696[type][0]), 1.0f);
        } else {
            this.size = this.shootingEntity.field_70131_O * 3.0f * (!this.isExplosion() ? 1.0f : 2.0f);
            this.func_70105_a(this.size, this.size);
            x = entityLiving.field_70165_t;
            y = entityLiving.field_70163_u + (double)(entityLiving.field_70131_O * 0.55f);
            z = entityLiving.field_70161_v;
            this.func_70012_b(x, y, z, entityLiving.field_70177_z, entityLiving.field_70125_A);
            this.field_70129_M = this.size * 0.5f;
            this.field_70159_w = 0.0;
            this.field_70179_y = 0.0;
            this.field_70181_x = 0.0;
        }
        this.strtX = (float)x;
        this.strtY = (float)y;
        this.strtZ = (float)z;
        this.motionXStart = this.field_70159_w;
        this.motionYStart = this.field_70181_x;
        this.motionZStart = this.field_70179_y;
    }

    public EntityEnergyAtt(EntityLivingBase entityLiving, byte type, byte speed, int dam, byte effect, byte color, byte color2, byte density, byte sincantation, byte sfire, byte smove, byte perc, int dam1, int cost, byte[] sts, byte technum) {
        this(entityLiving, type, speed, dam, effect, color, color2, density, sincantation, sfire, smove, perc, dam1, cost, sts, technum, -100, -100);
    }

    public EntityEnergyAtt(EntityLivingBase entityLiving, byte type, byte speed, int dam, byte effect, byte color, byte color2, byte density, byte sincantation, byte sfire, byte smove, byte perc, int dam1, int cost, byte[] sts, byte technum, byte release, byte align) {
        super(entityLiving.field_70170_p);
        double z;
        double y;
        double x;
        this.type = type;
        this.shooterHolds = this.isContinuesWave();
        this.speed = (byte)((float)((speed + 1) * 10 + (type == 2 ? 10 : 0) + (density == 2 ? 40 : 0)) * (1.0f + JRMCoreH.tech_statmod(sts, 0)));
        this.dam = dam;
        this.perc = perc;
        this.effect = effect;
        this.color = color;
        this.color2 = color2;
        this.density = (byte)(sts[5] + 1);
        this.sincantation = sincantation;
        this.sfire = sfire;
        this.smove = smove;
        this.sts = sts;
        this.technum = technum;
        this.cost = cost;
        this.originDmg = dam1;
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entityLiving;
            this.relFired = release != -100 ? release : JRMCoreH.getByte(player, "jrmcRelease");
            byte by = this.align = align != -100 ? align : JRMCoreH.getByte(player, "jrmcAlign");
            if (color == 0) {
                if (this.align > 66) {
                    this.color = 2;
                } else if (this.align <= 66 && this.align >= 33) {
                    this.color = 3;
                } else if (this.align < 33) {
                    this.color = 4;
                }
            }
            if (color2 == 0) {
                if (this.align > 66) {
                    this.color2 = 2;
                } else if (this.align <= 66 && this.align >= 33) {
                    this.color2 = 3;
                } else if (this.align < 33) {
                    this.color2 = 4;
                }
            }
        } else {
            if (color == 0) {
                this.color = 3;
            }
            if (color2 == 0) {
                this.color2 = 3;
            }
        }
        this.damageOriginal = this.damage = (double)dam1 * (double)this.relFired * (double)0.01f * (double)perc * (double)0.02f * JRMCoreConfig.dat5696[type][1];
        if (!this.isShield() && !this.isExplosion()) {
            this.setScales();
            float size1 = this.getScale();
            this.size = 0.5f + size1;
        } else {
            this.minScale = 0.001f;
            this.maxScale = 0.1f;
            this.maxDamage = JRMCoreH.getMaxEnergyDamage();
        }
        this.shootingEntity = entityLiving;
        this.explevel = !this.isShield() && !this.isExplosion() ? this.size * (float)JRMCoreConfig.eaes : 0.0f;
        this.field_70155_l = 10.0;
        if (JRMCoreConfig.eaesl > 0 && this.size > (float)JRMCoreConfig.eaesl) {
            this.size = JRMCoreConfig.eaesl;
        }
        if (JRMCoreConfig.ExplosionSizeLimit > 0.0 && (double)this.explevel > JRMCoreConfig.ExplosionSizeLimit) {
            this.explevel = (float)JRMCoreConfig.ExplosionSizeLimit;
        }
        if (this.isLargeBlast()) {
            this.size *= JRMCoreConfig.ealbm;
            this.explevel *= JRMCoreConfig.ealbm;
        }
        if (JRMCoreConfig.KiAttackScalesWithUser) {
            float extraScale = this.shootingEntity == null ? 1.0f : this.shootingEntity.field_70131_O / 1.8f;
            this.size *= extraScale;
            this.explevel *= extraScale;
        }
        this.func_70105_a(this.size, this.size);
        if (!this.isShield() && !this.isExplosion()) {
            Vec3 vec3;
            double d8 = entityLiving.field_70130_N + 1.0f + 1.0f;
            double d9 = entityLiving.field_70131_O + 0.2f;
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
            x = entityLiving.field_70165_t + vec3.field_72450_a * d8;
            y = entityLiving.field_70163_u + vec3.field_72448_b * d8 + (double)(entityLiving.field_70131_O * 0.7f);
            z = entityLiving.field_70161_v + vec3.field_72449_c * d8;
            this.func_70012_b(x, y, z, entityLiving.func_70079_am(), entityLiving.field_70125_A);
            this.field_70129_M = this.size * 0.5f;
            this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)((double)((float)this.speed * 0.05f) * JRMCoreConfig.dat5696[type][0]), 1.0f);
        } else {
            this.size = this.shootingEntity.field_70131_O * 3.0f * (!this.isExplosion() ? 1.0f : 2.0f);
            this.func_70105_a(this.size, this.size);
            x = entityLiving.field_70165_t;
            y = entityLiving.field_70163_u + (double)(entityLiving.field_70131_O * 0.55f);
            z = entityLiving.field_70161_v;
            this.func_70012_b(x, y, z, entityLiving.field_70177_z, entityLiving.field_70125_A);
            this.field_70129_M = this.size * 0.5f;
            this.field_70159_w = 0.0;
            this.field_70179_y = 0.0;
            this.field_70181_x = 0.0;
        }
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

    public boolean isContinuesWave() {
        return this.getType() >= JRMCoreConfig.ContinuesKiAttacks.length ? false : JRMCoreConfig.ContinuesKiAttacks[this.getType()];
    }

    public void func_70071_h_() {
        Block block;
        byte b;
        int curBody;
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
        if (!this.isShield() && !this.isExplosion()) {
            int color2;
            int color = JRMCoreH.techCol[this.getCol()];
            int n = color2 = this.getCol2() == -1 ? JRMCoreH.techCol2[this.getCol()] : JRMCoreH.techCol3[this.getCol2()];
            if (this.isContinuesWave() && this.shooterHolds) {
                this.generateParticles(this, this.shootingEntity, color, color2, true);
            }
            this.generateParticles(this, this, color, color2, false);
        }
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
            if (!(this.field_70170_p.field_72995_K || JRMCoreConfig.dat5695[this.type] || this.field_70128_L)) {
                this.func_70106_y();
            }
            if (!this.field_70170_p.field_72995_K && !JRMCoreConfig.dat5709[this.type] && this.hasEffect()) {
                this.effect = 0;
            }
            this.run = false;
        }
        if (!(this.field_70170_p.field_72995_K || !this.isContinuesWave() || this.isShield() || this.isExplosion() || this.target == null || this.target.field_70128_L || this.shooterHolds || !JGMathHelper.isMotionSmallerThanN(this, 0.001))) {
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
                double d8 = ((EntityLivingBase)this.shootingEntity).field_70130_N + 1.0f + 1.0f;
                double d9 = ((EntityLivingBase)this.shootingEntity).field_70131_O + 0.2f;
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
                        DBCClientTickHandler.nuller();
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
                    DBCClientTickHandler.nuller();
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
            if (JRMCoreH.DBC() && this.target instanceof EntityDBC) {
                ((EntityDBC)this.target).lockedBy = this;
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
                if (this.hasEffect() && !this.isShield() && !this.isExplosion()) {
                    this.createExplosion(2);
                }
                this.func_70106_y();
            }
        }
        if (this.shootingEntity != null && this.isExplosion() && this.hasEffect()) {
            double kulz3;
            double kuly3;
            double kulx3 = this.shootingEntity.field_70165_t - this.field_70165_t;
            if (kulx3 < 0.0) {
                kulx3 *= -1.0;
            }
            if ((kuly3 = this.shootingEntity.field_70163_u - this.field_70163_u) < 0.0) {
                kuly3 *= -1.0;
            }
            if ((kulz3 = this.shootingEntity.field_70161_v - this.field_70161_v) < 0.0) {
                kulz3 *= -1.0;
            }
            if (kulx3 > 1.0 || kuly3 > 1.0 || kulz3 > 1.0) {
                this.shootingEntity.func_70107_b(this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v);
            }
        }
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K && this.shootingEntity != null && !this.field_70128_L) {
            if (this.isShield()) {
                if (JRMCoreConfig.ShieldsMoveWithUser) {
                    this.field_70165_t = this.shootingEntity.field_70165_t;
                    this.field_70163_u = this.shootingEntity.field_70163_u + (double)(this.shootingEntity.field_70131_O * 0.55f);
                    this.field_70161_v = this.shootingEntity.field_70161_v;
                } else {
                    int diff;
                    if ((diff *= (diff = (int)(this.field_70165_t - this.shootingEntity.field_70165_t)) > 0 ? 1 : -1) > 3) {
                        this.func_70106_y();
                    }
                    if ((diff *= (diff = (int)(this.field_70163_u - this.shootingEntity.field_70163_u)) > 0 ? 1 : -1) > 3) {
                        this.func_70106_y();
                    }
                    if ((diff *= (diff = (int)(this.field_70161_v - this.shootingEntity.field_70161_v)) > 0 ? 1 : -1) > 3) {
                        this.func_70106_y();
                    }
                }
            } else if (this.isExplosion() && JRMCoreConfig.ExplosionsMoveWithUser) {
                this.field_70165_t = this.shootingEntity.field_70165_t;
                this.field_70163_u = this.shootingEntity.field_70163_u + (double)(this.shootingEntity.field_70131_O * 0.55f);
                this.field_70161_v = this.shootingEntity.field_70161_v;
            }
        }
        if (!this.field_70170_p.field_72995_K && this.shootingEntity != null && JRMCoreConfig.dat5710 && (this.isShield() || this.isExplosion()) && (curBody = Integer.parseInt(JRMCoreH.data(this.shootingEntity.func_70005_c_(), 8, "200"))) == 0) {
            this.func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.shootingEntity != null && this.shootingEntity instanceof EntityPlayer && (b = JRMCoreH.getByte((EntityPlayer)this.shootingEntity, "jrmcFrng")) == 0 && !this.shrink && JRMCoreConfig.WavesShrinkOnceLetGo) {
            this.shrink();
        }
        if (!this.field_70170_p.field_72995_K && this.isContinuesWave() && this.hadTarget && (this.target == null || this.target.field_70128_L)) {
            this.func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K && this.isExplosion() && this.field_70173_aa >= JRMCoreConfig.dat5697) {
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
        if (this.inGround && !this.isShield() && !this.isExplosion()) {
            int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (!this.field_70170_p.field_72995_K) {
                if (block == this.inTile && var19 == this.inData) {
                    ++this.ticksInGround;
                    if (this.ticksInGround == 1) {
                        this.func_70106_y();
                        if (!this.field_70170_p.field_72995_K) {
                            if (this.hasEffect()) {
                                this.createExplosion(1);
                            }
                            this.playSoundAtEntity(this, JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer ? "jinryuudragonbc:DBC5.hakai" : this.ExplSound, 1.0f, 1.0f);
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
            }
        } else {
            int t;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition movingObject = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (!this.field_70170_p.field_72995_K && ((float)this.ticksInAir >= (float)JRMCoreConfig.EnergyAttackMaxLifeTickPercMulti * ((float)this.perc * 0.02f) || this.ticksInAir >= JRMCoreConfig.EnergyAttackMaxLifeTick)) {
                this.func_70106_y();
            }
            if (this.ticksInAir == ((t = this.ticksInAir / 10 * 10) == 0 ? 10 : t)) {
                this.playSoundAtEntity(this, "jinryuudragonbc:" + JRMCoreH.techSnds(this.type, 2, this.smove), this.isBarrage() ? 0.5f : 1.0f, 1.0f);
            }
            if (movingObject != null) {
                var3 = Vec3.func_72443_a((double)movingObject.field_72307_f.field_72450_a, (double)movingObject.field_72307_f.field_72448_b, (double)movingObject.field_72307_f.field_72449_c);
            }
            if (!this.field_70170_p.field_72995_K) {
                List entityList;
                Entity lastEntity = null;
                boolean MODE_OLD = false;
                boolean MODE_ONE = true;
                int MODE_TWO = 2;
                int MODE_THREE = 3;
                int MODE_FOUR = 4;
                int MODE_OFF = 5;
                byte mode = JRMCoreConfig.KiClosestEntityCheckSize;
                if (mode == 4) {
                    AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
                    entityList = this.field_70170_p.func_72839_b((Entity)this, aabb);
                } else if (mode == 3) {
                    AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
                    entityList = this.field_70170_p.func_72839_b((Entity)this, aabb.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y));
                } else if (mode == 2) {
                    AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
                    entityList = this.field_70170_p.func_72839_b((Entity)this, aabb.func_72314_b(0.5, 0.5, 0.5));
                } else {
                    entityList = mode == 1 ? this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y)) : this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(0.5, 0.5, 0.5));
                }
                if (mode != 5) {
                    double lastDistance = 0.0;
                    for (int n = 0; n < entityList.size(); ++n) {
                        double distance;
                        float var11;
                        AxisAlignedBB entityHitbox;
                        MovingObjectPosition movingObject2;
                        Entity entity = (Entity)entityList.get(n);
                        if (!(entity instanceof EntityLivingBase) || !entity.func_70067_L() || entity == this.shootingEntity && this.ticksInAir < 5 || (movingObject2 = (entityHitbox = entity.field_70121_D.func_72314_b((double)(var11 = 0.0f), (double)var11, (double)var11)).func_72327_a(var17, var3)) == null || !((distance = var17.func_72438_d(movingObject2.field_72307_f)) < lastDistance) && lastDistance != 0.0) continue;
                        lastEntity = entity;
                        lastDistance = distance;
                    }
                    if (lastEntity != null) {
                        movingObject = new MovingObjectPosition(lastEntity);
                    }
                }
            }
            if (this.field_70159_w <= 0.01 && this.field_70181_x <= 0.01 && this.field_70179_y <= 0.01 && this.field_70159_w >= -0.01 && this.field_70181_x >= -0.01 && this.field_70179_y >= -0.01 && !this.shrink && !this.isContinuesWave()) {
                this.shrink();
            }
            if (!this.field_70170_p.field_72995_K) {
                int n;
                List entityList = this.checkForEntitiesInside();
                for (n = 0; n < entityList.size(); ++n) {
                    Entity entity = (Entity)entityList.get(n);
                    if (entity == null || entity == this.shootingEntity || entity.field_70128_L) continue;
                    if (entity instanceof EntityEnergyAtt) {
                        EntityEnergyAtt entityKi = (EntityEnergyAtt)entity;
                        if (!entityKi.isShield() && !entityKi.isExplosion()) continue;
                        this.handleKiaiClash(entityKi);
                        continue;
                    }
                    if (!JRMCoreH.NC() || !(entity instanceof EntityEnergyAttJ3)) continue;
                    EntityEnergyAttJ3 entityShield = (EntityEnergyAttJ3)entity;
                    this.handleJutsuWallClash(entityShield);
                }
                for (n = 0; n < entityList.size(); ++n) {
                    float var25;
                    Entity entity = (Entity)entityList.get(n);
                    if (entity != this.shootingEntity) {
                        byte reaction = this.checkReaction(entity, true);
                        EntityEnergyAtt entityKi = null;
                        if (entity != null) {
                            if (entity instanceof EntityEnergyAtt) {
                                entityKi = (EntityEnergyAtt)entity;
                            }
                            if (entity instanceof EntityLivingBase) {
                                if (movingObject == null) {
                                    movingObject = new MovingObjectPosition(entity);
                                }
                                if (this.target == null && !JRMCoreH.isFusionSpectator(entity)) {
                                    this.setTarget(entity);
                                }
                            } else if (this.isContinuesWave() && this.wave > 0 && this.shootingEntity instanceof EntityPlayer) {
                                this.shrinkWave();
                            } else if (!(entity instanceof EntityEnAttacks || entity == this.shootingEntity || this.isShield() || this.isExplosion())) {
                                if (this.hasEffect() && (reaction <= 0 || reaction >= 6) && entity instanceof EntityLivingBase) {
                                    this.createExplosion(2);
                                }
                            } else if (entityKi != null && !(entityKi.shootingEntity instanceof EntityDBC) && !this.kiClashedList.contains(entity)) {
                                if (!this.isShield() && !this.isExplosion()) {
                                    boolean doit = true;
                                    byte result = 0;
                                    if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer && this.damage * (double)this.DAMAGE_REDUCTION / 2.0 > entityKi.damage / 2.0) {
                                        entityKi.func_70106_y();
                                        continue;
                                    }
                                    if (!(JRMCoreConfig.dat5705 == 1.0 || entityKi.isShield() || entityKi.isExplosion() || this.field_70128_L || entityKi.field_70128_L)) {
                                        result = this.killWeakerAttack(this, entityKi);
                                    }
                                    if (result == 0) {
                                        if (entityKi != null && !entityKi.field_70128_L && (entityKi.isShield() || entityKi.isExplosion())) {
                                            doit = false;
                                        }
                                        if (entityKi != null && !entityKi.field_70128_L && !this.field_70128_L) {
                                            this.kiClashed = true;
                                            this.kiClashedList.add(entity);
                                            if (doit && entityKi.shootingEntity != this.shootingEntity) {
                                                float dam = (float)(((EntityEnergyAtt)entity).getDamage() / 2.0);
                                                float spe = (float)((EntityEnergyAtt)entity).getSpe() * 2.0f;
                                                float den = (float)((EntityEnergyAtt)entity).getDen() * 10.0f;
                                                float damt = (float)(this.damage / 2.0);
                                                float spet = (float)this.speed * 2.0f;
                                                float dent = (float)this.density * 10.0f;
                                                long power = this.getPower(entityKi);
                                                if (power >= 0L) {
                                                    float res = ((damt - dam) / damt + (spe - spet) / spe + (dent - den) / dent) / 3.0f;
                                                    this.field_70159_w *= (double)res;
                                                    this.field_70181_x *= (double)res;
                                                    this.field_70179_y *= (double)res;
                                                    if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && ((EntityEnergyAtt)entity).destroyer && !(((EntityEnergyAtt)entity).damage * (double)this.DAMAGE_REDUCTION / 2.0 > this.damage / 2.0)) {
                                                        ((EntityEnergyAtt)entity).field_70159_w = this.field_70159_w;
                                                        ((EntityEnergyAtt)entity).field_70181_x = this.field_70181_x;
                                                        ((EntityEnergyAtt)entity).field_70179_y = this.field_70179_y;
                                                    }
                                                    if (((EntityEnergyAtt)entity).getAirTicks() < this.ticksInAir) {
                                                        this.ticksInAir = ((EntityEnergyAtt)entity).getAirTicks();
                                                    }
                                                }
                                                this.conn = 2;
                                            }
                                        }
                                    }
                                } else if (this.isShield() && !this.field_70170_p.field_72995_K) {
                                    boolean doit = true;
                                    if ((int)(Math.random() * 3.0) == 0) {
                                        doit = false;
                                    }
                                    if (doit && this.shootingEntity instanceof EntityPlayer) {
                                        boolean qckmth;
                                        boolean bl = qckmth = !entityKi.isShield() && !entityKi.isExplosion();
                                        if (qckmth) {
                                            String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                            JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1), (EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (!this.isExplosion() || !this.hasEffect() && entity.equals((Object)this.shootingEntity)) continue;
                    EntityEnergyAtt energyEntity = null;
                    if (entity instanceof EntityEnergyAtt) {
                        energyEntity = (EntityEnergyAtt)entity;
                    }
                    if (entity instanceof EntityLivingBase) {
                        if (movingObject == null) {
                            movingObject = new MovingObjectPosition(entity);
                        }
                        if (this.target == null && JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                            this.target = entity;
                        }
                    }
                    if (energyEntity != null && !(energyEntity.shootingEntity instanceof EntityDBC) && !this.kiClashedList.contains(entity) || entity == null) continue;
                    float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                    int var23 = (int)(this.damage * (double)(this.hasEffect() ? 3.0f : 1.0f) * (entity.equals((Object)this.shootingEntity) ? JRMCoreConfig.dat5701 / 100.0 : 1.0));
                    this.giveExperience(entity, 1);
                    DamageSource damagesource = null;
                    if (this.shootingEntity == null || this.shootingEntity instanceof EntityDBC && entity instanceof EntityDBC) {
                        this.func_70106_y();
                        return;
                    }
                    damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                    if (this.func_70027_ad()) {
                        entity.func_70015_d(5);
                    }
                    double motX = entity.field_70159_w;
                    double motY = entity.field_70181_x;
                    double motZ = entity.field_70179_y;
                    if (JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g) || !entity.func_70097_a(damagesource, (float)var23)) continue;
                    if (this.type < 7 && JRMCoreConfig.dat5706[this.type]) {
                        entity.field_70159_w = motX;
                        entity.field_70181_x = motY;
                        entity.field_70179_y = motZ;
                    }
                    if (!(entity instanceof EntityLivingBase)) continue;
                    if (!this.field_70170_p.field_72995_K && this.shootingEntity instanceof EntityPlayer) {
                        String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                        JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1), (EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                    }
                    if (this.knockbackStrength <= 0 || entity.equals((Object)this.shootingEntity) || this.type < 7 && !JRMCoreConfig.dat5706[this.type] || !((var25 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f)) continue;
                    entity.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)var25, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)var25);
                }
            }
            if (!this.isShield()) {
                if (movingObject != null && movingObject.field_72308_g != this.shootingEntity) {
                    if (!this.field_70170_p.field_72995_K && !this.isExplosion() && !JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g) && this.canSpiralNotGoThrough()) {
                        this.playSoundAtEntity(this, JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer ? "jinryuudragonbc:DBC5.hakai" : this.ExplSound, 1.0f, 1.0f);
                    }
                    if (movingObject.field_72308_g != null && (this.shootingEntity instanceof EntityPlayer || movingObject.field_72308_g instanceof EntityPlayer) && this.isContinuesWave() && this.shooterHolds) {
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
                                            if (!((float)curEn - (cost2 = (float)((double)this.cost * (double)curRel * (double)0.01f * (double)((float)this.perc * 0.02f) * JRMCoreConfig.dat5696[this.type][2])) > 0.0f)) {
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
                                        if (this.wave == 1 && movingObject.field_72308_g instanceof EntityLivingBase && !this.field_70170_p.field_72995_K) {
                                            EntityLivingBase var24 = (EntityLivingBase)movingObject.field_72308_g;
                                            if (this.shootingEntity instanceof EntityPlayer) {
                                                String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                                JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1), (EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                            }
                                        }
                                        int var23 = (int)this.damage;
                                        DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                                        if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g) && this.target.func_70097_a(damagesource, (float)var23)) {
                                            this.weakenSpiral();
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
                                    if (this.hasEffect() && !this.isShield() && !this.isExplosion()) {
                                        this.createExplosion(2);
                                    }
                                    this.func_70106_y();
                                }
                            } else {
                                this.shrinkWave();
                            }
                        }
                        if (movingObject.field_72308_g instanceof EntityLivingBase) {
                            if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                                this.setTarget(movingObject.field_72308_g);
                            }
                        } else {
                            this.shrinkWave();
                        }
                    } else if (movingObject.field_72308_g != null && (this.shootingEntity instanceof EntityPlayer || movingObject.field_72308_g instanceof EntityPlayer)) {
                        float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                        int var23 = (int)this.damage;
                        this.giveExperience(movingObject.field_72308_g, 1);
                        DamageSource damagesource = null;
                        if (this.shootingEntity == null || this.shootingEntity instanceof EntityDBC && movingObject.field_72308_g instanceof EntityDBC) {
                            this.func_70106_y();
                            return;
                        }
                        damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                        if (this.func_70027_ad()) {
                            movingObject.field_72308_g.func_70015_d(5);
                        }
                        double motX = movingObject.field_72308_g.field_70159_w;
                        double motY = movingObject.field_72308_g.field_70181_x;
                        double motZ = movingObject.field_72308_g.field_70179_y;
                        if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                            if (movingObject.field_72308_g.func_70097_a(damagesource, (float)var23)) {
                                this.weakenSpiral();
                                if (this.type < 7 && !JRMCoreConfig.dat5706[this.type]) {
                                    movingObject.field_72308_g.field_70159_w = motX;
                                    movingObject.field_72308_g.field_70181_x = motY;
                                    movingObject.field_72308_g.field_70179_y = motZ;
                                }
                                if (movingObject.field_72308_g instanceof EntityLivingBase) {
                                    float var25;
                                    if (!this.field_70170_p.field_72995_K && this.shootingEntity instanceof EntityPlayer) {
                                        boolean doit = true;
                                        if ((this.isBarrage() || this.isExplosion()) && (int)(Math.random() * 6.0) == 0) {
                                            doit = false;
                                        }
                                        if (doit) {
                                            String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                            JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1), (EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                                        }
                                    }
                                    if (this.knockbackStrength > 0 && (this.type >= 7 || JRMCoreConfig.dat5706[this.type]) && (var25 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                                        movingObject.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)var25, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)var25);
                                    }
                                }
                                if (this.canSpiralNotGoThrough() && !this.isShield() && !this.isExplosion()) {
                                    if (this.hasEffect()) {
                                        this.createExplosion(2);
                                    }
                                    this.func_70106_y();
                                }
                            } else if (movingObject.field_72308_g.func_70089_S() && !DBCConfig.KiAttackGoThroughInvulnerableEnemies && !this.isShield() && !this.isExplosion() && this.canSpiralNotGoThrough()) {
                                this.field_70159_w *= (double)-0.1f;
                                this.field_70181_x *= (double)-0.1f;
                                this.field_70179_y *= (double)-0.1f;
                                this.field_70177_z += 180.0f;
                                this.field_70126_B += 180.0f;
                                this.func_70106_y();
                                this.ticksInAir = 0;
                            }
                        }
                    } else {
                        this.xTile = movingObject.field_72311_b;
                        this.yTile = movingObject.field_72312_c;
                        this.zTile = movingObject.field_72309_d;
                        this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                        this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
                        this.inGround = true;
                        if (this.inTile.func_149688_o() != Material.field_151579_a) {
                            this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this);
                        }
                        if (movingObject.field_72308_g != null && this.shootingEntity != null) {
                            float var20 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                            int var23 = (int)this.damage;
                            DamageSource damagesource = null;
                            if (this.shootingEntity == null || this.shootingEntity instanceof EntityDBC && movingObject.field_72308_g instanceof EntityDBC) {
                                this.func_70106_y();
                                return;
                            }
                            damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                            if (this.func_70027_ad()) {
                                movingObject.field_72308_g.func_70015_d(5);
                            }
                            double motX = movingObject.field_72308_g.field_70159_w;
                            double motY = movingObject.field_72308_g.field_70181_x;
                            double motZ = movingObject.field_72308_g.field_70179_y;
                            if (!JRMCoreH.isFusionSpectator(movingObject == null ? null : movingObject.field_72308_g)) {
                                if (movingObject.field_72308_g.func_70097_a(damagesource, (float)var23)) {
                                    float var25;
                                    this.weakenSpiral();
                                    if (this.type < 7 && !JRMCoreConfig.dat5706[this.type]) {
                                        movingObject.field_72308_g.field_70159_w = motX;
                                        movingObject.field_72308_g.field_70181_x = motY;
                                        movingObject.field_72308_g.field_70179_y = motZ;
                                    }
                                    if (movingObject.field_72308_g instanceof EntityLivingBase && this.knockbackStrength > 0 && (this.type >= 7 || JRMCoreConfig.dat5706[this.type]) && (var25 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                                        movingObject.field_72308_g.func_70024_g(this.field_70159_w * (double)this.knockbackStrength * (double)0.6f / (double)var25, 0.1, this.field_70179_y * (double)this.knockbackStrength * (double)0.6f / (double)var25);
                                    }
                                    if (this.hasEffect() && !this.isShield() && !this.isExplosion()) {
                                        this.createExplosion(2);
                                    }
                                    this.func_70106_y();
                                } else if (!this.isShield() && !this.isExplosion()) {
                                    this.field_70159_w *= (double)-0.1f;
                                    this.field_70181_x *= (double)-0.1f;
                                    this.field_70179_y *= (double)-0.1f;
                                    this.field_70177_z += 180.0f;
                                    this.field_70126_B += 180.0f;
                                    this.func_70106_y();
                                    this.ticksInAir = 0;
                                }
                            }
                        }
                    }
                } else if ((this.wave > 0 || this.waveCount < 20) && this.target != null && this.target.field_70128_L) {
                    this.shrinkWave();
                }
            }
            if (!this.isShield() && !this.isExplosion()) {
                this.field_70165_t += this.field_70159_w;
                this.field_70163_u += this.field_70181_x;
                this.field_70161_v += this.field_70179_y;
            }
            this.ShieldPushAwayEntities();
            if ((double)(this.field_70125_A - this.field_70127_C) >= 180.0) {
                this.field_70127_C += 360.0f;
            }
            if (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            if (this.field_70177_z - this.field_70126_B >= 180.0f) {
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
            if (!this.isShield() && !this.isExplosion()) {
                this.field_70159_w *= (double)var22;
                this.field_70181_x *= (double)var22;
                this.field_70179_y *= (double)var22;
                this.field_70181_x -= (double)var11;
            }
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
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
            DBCClientTickHandler.nuller();
            JRMCoreH.isShtng = false;
        }
    }

    private byte checkReaction(Entity entity, boolean react) {
        String nev = EntityList.func_75621_b((Entity)entity);
        if (JRMCoreConfig.dat5704) {
            JRMCoreH.log("[JRMC CONSOLE] Name of Entity that was hit by a Ki Blast: " + nev + " | (Can be used for Reaction Config!)");
        }
        byte data = 0;
        if (entity instanceof EntityEnergyAtt) {
            nev = nev + "!" + ((EntityEnergyAtt)entity).getType();
        }
        if (JRMCoreConfig.dat5702.get(nev) != null) {
            data = JRMCoreConfig.dat5702.get(nev);
            if (react) {
                this.checkReacts(entity, data);
            }
        }
        if (JRMCoreConfig.dat5703.get(this.getType() + "." + nev) != null) {
            data = JRMCoreConfig.dat5703.get(this.getType() + "." + nev);
            if (react) {
                this.checkReacts(entity, data);
            }
        }
        return data;
    }

    private void checkReacts(Entity entity, byte data) {
        switch (data) {
            case 1: {
                this.func_70106_y();
                break;
            }
            case 2: {
                if (entity.field_70128_L) break;
                entity.func_70106_y();
                break;
            }
            case 3: {
                if (this.effect != 1 || entity.field_70128_L) break;
                entity.func_70106_y();
                break;
            }
            case 4: {
                int var23 = (int)this.damage;
                DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                double motX = entity.field_70159_w;
                double motY = entity.field_70181_x;
                double motZ = entity.field_70179_y;
                if (JRMCoreH.isFusionSpectator(entity) || !entity.func_70097_a(damagesource, (float)var23)) break;
                this.weakenSpiral();
                if (this.type >= 7 || JRMCoreConfig.dat5706[this.type]) break;
                entity.field_70159_w = motX;
                entity.field_70181_x = motY;
                entity.field_70179_y = motZ;
                break;
            }
            case 5: {
                if (this.effect != 1) break;
                int var23 = (int)this.damage;
                DamageSource damagesource = Ds.causeEntityEnergyAttDamage(this, this.shootingEntity);
                double motX = entity.field_70159_w;
                double motY = entity.field_70181_x;
                double motZ = entity.field_70179_y;
                if (JRMCoreH.isFusionSpectator(entity) || !entity.func_70097_a(damagesource, (float)var23)) break;
                this.weakenSpiral();
                if (this.type >= 7 || JRMCoreConfig.dat5706[this.type]) break;
                entity.field_70159_w = motX;
                entity.field_70181_x = motY;
                entity.field_70179_y = motZ;
                break;
            }
        }
    }

    @Override
    public long getPower(Entity entity) {
        String powerFormula = JRMCoreConfig.KiAttackPowerFormula;
        long Power = 0L;
        long damage = (long)((EntityEnergyAtt)entity).getDamage();
        long speed = ((EntityEnergyAtt)entity).getSpe();
        long density = ((EntityEnergyAtt)entity).getDen();
        String[] Formula = powerFormula.toLowerCase().replace(" ", "").replace("(", "").replace("damage", damage + "").replace("speed", speed + "").replace("density", density + "").split("\\)");
        for (int i = 0; i < Formula.length; ++i) {
            String formulaSegment;
            String string = formulaSegment = i == 0 ? Formula[i] : Formula[i].substring(1);
            String method = formulaSegment.contains("+") ? "+" : (formulaSegment.contains("-") ? "-" : (formulaSegment.contains("*") ? "*" : (formulaSegment.contains("/") ? "/" : (formulaSegment.contains("%") ? "%" : "null"))));
            long value1 = Long.parseLong(formulaSegment.split("\\" + method)[0]);
            long value2 = 1L;
            long result = value1;
            if (!method.equals("null")) {
                value2 = Long.parseLong(formulaSegment.split("\\" + method)[1]);
                result = JGMathHelper.StringMethod(method, value1, value2);
            }
            if (i > 0) {
                String method2 = Formula[i].substring(0, 1);
                Power = JGMathHelper.StringMethod(method2, Power, result);
                continue;
            }
            Power = result;
        }
        return Power;
    }

    private boolean canSpiralNotGoThrough() {
        return this.isSpiral() ? !JRMCoreConfig.dat5708[this.effect] : true;
    }

    private void createExplosion(int type) {
        if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer) {
            type = 10;
        }
        JRMCoreH.newExpl(this.field_70170_p, this, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.explevel, false, this.damage, this.shootingEntity, (byte)type);
    }

    private List checkForEntitiesInside() {
        AxisAlignedBB aabb = this.field_70121_D.func_72329_c();
        List entityList = this.field_70170_p.func_72839_b((Entity)this, aabb);
        return entityList;
    }

    private void giveExperience(Entity entity, int amount) {
        if (JRMCoreH.DGE(entity) && !this.givenExp) {
            JRMCoreH.jrmcExp(this.shootingEntity, amount, this.getType());
            this.givenExp = true;
        }
    }

    private void playSoundAtEntity(Entity entity, String s, float f, float f1) {
        this.field_70170_p.func_72956_a(entity, s, f, f1);
        if (this.isWave() && this.shooterHolds) {
            this.field_70170_p.func_72908_a((double)this.strtX(), (double)this.strtY(), (double)this.strtZ(), s, f, f1);
        }
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

    private void setTarget(Entity entity) {
        this.target = entity;
        this.hadTarget = true;
    }

    private byte killWeakerAttack(EntityEnergyAtt attack1, EntityEnergyAtt attack2) {
        long power2;
        boolean amIStronger;
        long power1 = this.getPower(attack1);
        boolean bl = amIStronger = power1 > (power2 = this.getPower(attack2));
        if (power1 != power2) {
            if (amIStronger) {
                if ((double)power1 / JRMCoreConfig.dat5705 >= (double)power2) {
                    return 2;
                }
            } else if ((double)power2 / JRMCoreConfig.dat5705 >= (double)power1) {
                this.func_70106_y();
                return 1;
            }
        }
        return 0;
    }

    private void handleJutsuWallClash(EntityEnergyAttJ3 shield) {
        if (JRMCoreH.NC() && shield != this.shootingEntity && shield instanceof EntityEnAttacks) {
            long shieldPower = shield.getPower(shield);
            long kiPower = this.getPower(this);
            double kiDamage = this.getDamage();
            if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer && kiPower > shieldPower) {
                shield.func_70106_y();
                return;
            }
            if (kiPower > shieldPower) {
                this.setDamage((float)this.getDamage() - (float)shield.getDamage());
                shield.func_70106_y();
            } else if (kiPower < shieldPower) {
                shield.setDamage((float)shield.getDamage() - (float)kiDamage);
                this.func_70106_y();
            } else {
                shield.func_70106_y();
                this.func_70106_y();
            }
            shield.field_70159_w = 0.0;
            shield.field_70181_x = 0.0;
            shield.field_70179_y = 0.0;
        }
    }

    private void handleKiaiClash(EntityEnergyAtt attack) {
        float dam = (float)attack.getDamage();
        if (JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer && this.damage * (double)this.DAMAGE_REDUCTION / 2.0 > (double)dam) {
            attack.func_70106_y();
            String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
            JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1), (EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
            return;
        }
        if (this.damage / 2.0 > (double)dam) {
            this.setDamage(this.getDamage() - (double)dam);
            attack.func_70106_y();
        } else if (this.damage / 2.0 < (double)dam) {
            attack.setDamage((double)dam - this.damage / 2.0);
            if (this.shootingEntity instanceof EntityPlayer) {
                boolean doit2 = false;
                if (JRMCoreConfig.dat5707 != 0 && (int)(Math.random() * 100.0) < JRMCoreConfig.dat5707) {
                    doit2 = true;
                }
                if (doit2) {
                    String s2 = JRMCoreH.getString((EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                    JRMCoreH.setString(JRMCoreH.tech_expgiv(s2, JRMCoreH.DBC() ? JRMCoreHDBC.DBCgetConfigTechExpRate() : 1), (EntityPlayer)this.shootingEntity, JRMCoreH.techNbt[this.technum]);
                }
            }
            this.func_70106_y();
        } else {
            this.func_70106_y();
            attack.func_70106_y();
        }
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

    public boolean isLaser() {
        return this.getType() == 3;
    }

    public boolean isLargeBlast() {
        return this.getType() == 5;
    }

    public boolean isSpiral() {
        return this.getType() == 4;
    }

    public boolean isBarrage() {
        return this.getType() == 6;
    }

    public boolean isShield() {
        return this.getType() == 7;
    }

    public boolean isExplosion() {
        return this.getType() == 8;
    }

    private boolean hasEffect() {
        return this.effect == 1;
    }

    private void ShieldPushAwayEntities() {
        if (!this.field_70170_p.field_72995_K && this.isShield() && this.hasEffect()) {
            Entity var5 = null;
            List var6 = this.checkForEntitiesInside();
            for (int var9 = 0; var9 < var6.size(); ++var9) {
                var5 = (Entity)var6.get(var9);
                if (var5.equals((Object)this.shootingEntity) || !(var5 instanceof EntityLivingBase)) continue;
                float res = 0.5f;
                var5.field_70159_w = ((double)res - (var5.field_70165_t - this.field_70165_t)) * -1.0;
                var5.field_70181_x = ((double)res - (var5.field_70163_u - this.field_70163_u)) * -1.0;
                var5.field_70179_y = ((double)res - (var5.field_70161_v - this.field_70161_v)) * -1.0;
                var5.field_70133_I = true;
            }
        }
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
        nbt.func_74776_a("size", this.size);
        nbt.func_74757_a("destroyer", this.destroyer);
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
        if (nbt.func_74764_b("size")) {
            this.size = nbt.func_74760_g("size");
        }
        if (nbt.func_74764_b("destroyer")) {
            this.destroyer = nbt.func_74767_n("destroyer");
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
        return this.damage * (double)(JGConfigDBCGoD.CONFIG_GOD_ENABLED && JGConfigDBCGoD.CONFIG_GOD_ENERGY_ENABLED && this.destroyer ? this.DAMAGE_REDUCTION : 1.0f);
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
        data.writeByte((int)this.speed);
        data.writeByte((int)this.perc);
        data.writeByte((int)this.effect);
        data.writeInt(this.color);
        data.writeInt(this.dam);
        data.writeByte((int)this.density);
        data.writeShort((int)this.sincantation);
        String se = "";
        if (this.sts != null) {
            for (int i = 0; i < this.sts.length; ++i) {
                se = se + "," + this.sts[i];
            }
            se = se.substring(1);
        }
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)se);
        data.writeByte((int)this.technum);
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
        data.writeFloat(this.explevel);
        data.writeInt(this.color2);
        data.writeDouble(this.damage);
        data.writeDouble(this.damageOriginal);
        data.writeInt(this.damageTaken);
        data.writeBoolean(this.destroyer);
    }

    public void readSpawnData(ByteBuf data) {
        int first = data.readInt();
        this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
        int second = data.readInt();
        this.target = first == 0 ? this.target : this.field_70170_p.func_73045_a(second);
        this.perc = data.readByte();
        this.type = data.readByte();
        this.speed = data.readByte();
        this.perc = data.readByte();
        this.effect = data.readByte();
        this.color = data.readInt();
        this.dam = data.readInt();
        this.density = data.readByte();
        this.sincantation = data.readShort();
        String[] se = ByteBufUtils.readUTF8String((ByteBuf)data).split(",");
        if (se.length < 3) {
            byte[] sts2 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            this.sts = sts2;
        } else {
            byte[] sts2 = new byte[se.length];
            for (int i = 0; i < se.length; ++i) {
                sts2[i] = Byte.parseByte(se[i]);
            }
            this.sts = sts2;
        }
        this.technum = data.readByte();
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
        this.explevel = data.readFloat();
        this.color2 = data.readInt();
        this.damage = data.readDouble();
        this.damageOriginal = data.readDouble();
        this.damageTaken = data.readInt();
        this.destroyer = data.readBoolean();
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

    public void generateParticles(EntityEnergyAtt entityBlast, Entity entity, int color, int color2, boolean startSpawn) {
        if (entityBlast != null && entity != null && entityBlast.field_70170_p.field_72995_K) {
            for (int i = 0; i < 3; ++i) {
                for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                    double z2;
                    double y2;
                    double x2;
                    float colorFixer = 0.7f;
                    float red = (float)(color >> 16 & 0xFF) / 255.0f;
                    float green = (float)(color >> 8 & 0xFF) / 255.0f;
                    float blue = (float)(color & 0xFF) / 255.0f;
                    red *= 0.7f;
                    green *= 0.7f;
                    blue *= 0.7f;
                    float red2 = (float)(color2 >> 16 & 0xFF) / 255.0f;
                    float green2 = (float)(color2 >> 8 & 0xFF) / 255.0f;
                    float blue2 = (float)(color2 & 0xFF) / 255.0f;
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

    public void weakenSpiral() {
        if (this.isSpiral() && !this.canSpiralNotGoThrough() && JRMCoreConfig.SpiralWeakensAfterHit > 0) {
            double minusDamage = (double)JRMCoreConfig.SpiralWeakensAfterHit / 100.0;
            if (JRMCoreConfig.SpiralWeakensBasedOnStartDamage) {
                if ((1.0 - minusDamage) * (double)this.damageTaken > this.damage || this.damage <= 0.0) {
                    this.func_70106_y();
                } else {
                    this.damage = this.damageOriginal * (1.0 - minusDamage * (double)this.damageTaken);
                }
                ++this.damageTaken;
            } else if (this.damage <= 0.0) {
                this.func_70106_y();
            } else {
                this.damage *= minusDamage;
            }
            if (this.damage < 0.0) {
                this.damage = 0.0;
                this.func_70106_y();
            }
        }
    }

    public float rad(float angle) {
        return angle * (float)Math.PI / 180.0f;
    }
}

