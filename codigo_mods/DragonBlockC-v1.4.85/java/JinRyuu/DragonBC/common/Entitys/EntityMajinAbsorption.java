/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Entitys;

import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.items.ItemVanity;
import JinRyuu.JRMCore.server.JGMathHelper;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigRaces;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.common.registry.IThrowableEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityMajinAbsorption
extends Entity
implements IThrowableEntity,
IEntityAdditionalSpawnData,
IEntitySelector,
IProjectile {
    public static final float SOUND = 0.25f;
    public static final HashMap<Class, Boolean> absorptionListResults = new HashMap();
    public Entity shootingEntity;
    public Entity target;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData = 0;
    private boolean inGround = false;
    private int ticksInGround;
    private int ticksInAir = 0;
    private float speed;
    private float size = 2.0f;
    private boolean teleported = false;
    private byte mode = 0;
    private byte skillLevel = 1;
    private int bodyColor = 0;
    private float userAttack = 1.0f;
    private float userPower = 1.0f;
    private int stateID = 0;
    public float targetW = 0.0f;
    public float targetH = 0.0f;
    public double visualH = 0.0;
    public double visualW = 0.0;
    public double prevX = 0.0;
    public double prevY = 0.0;
    public double prevZ = 0.0;
    public int prevCount = 0;
    public String absorptionData = "0,0,0+0";

    public int getShrink() {
        return this.func_70096_w().func_75679_c(20);
    }

    public float getSpe() {
        return this.speed;
    }

    public int getAirTicks() {
        return this.ticksInAir;
    }

    public void setAirTicks(int i) {
        this.ticksInAir = i;
    }

    public byte getSkillLevel() {
        return this.skillLevel;
    }

    public int getBodyColor() {
        return this.bodyColor;
    }

    public byte getMode() {
        return this.mode;
    }

    public EntityMajinAbsorption(World world) {
        super(world);
        this.func_70105_a(this.size, this.size);
    }

    public EntityMajinAbsorption(EntityLivingBase userEntity, int skillLevel, int bodyColor, float userPower, float userAttack, int stateID) {
        super(userEntity.field_70170_p);
        this.setData(skillLevel, bodyColor, userPower, userAttack, stateID);
        this.speed = 1.0f * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_SPEED_MULTI[stateID];
        this.size = 2.0f;
        this.shootingEntity = userEntity;
        this.field_70155_l = 10.0;
        this.func_70105_a(this.size, this.size);
        double d8 = userEntity.field_70130_N + 1.0f;
        double d9 = userEntity.field_70131_O + 0.5f + this.size * 0.5f;
        Vec3 vec3 = userEntity.func_70040_Z();
        double x = userEntity.field_70165_t + vec3.field_72450_a * d8;
        double y = userEntity.field_70163_u + vec3.field_72448_b * d8 + (double)(userEntity.field_70131_O * 0.55f);
        double z = userEntity.field_70161_v + vec3.field_72449_c * d8;
        this.field_70129_M = this.size * 0.5f;
        this.func_70012_b(x, y, z, userEntity.func_70079_am(), userEntity.field_70125_A);
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.speed, 1.0f);
        this.prevX = this.field_70165_t;
        this.prevY = this.field_70163_u;
        this.prevZ = this.field_70161_v;
    }

    public void setData(int skillLevel, int bodyColor, float userPower, float userAttack, int stateID) {
        this.skillLevel = (byte)skillLevel;
        this.bodyColor = bodyColor;
        this.userPower = userPower;
        if (userPower < 1.0f) {
            userPower = 1.0f;
        }
        this.userAttack = userAttack;
        if (userAttack < 1.0f) {
            userAttack = 1.0f;
        }
        this.stateID = stateID;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(20, (Object)0);
        this.field_70180_af.func_75682_a(21, (Object)Float.valueOf(0.0f));
        this.field_70180_af.func_75682_a(22, (Object)Float.valueOf(0.0f));
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
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (this.field_70163_u >= 250.0) {
            this.field_70181_x = 0.0;
        }
        if (this.field_70173_aa == 1) {
            this.func_70105_a(this.size, this.size);
            this.field_70129_M = this.size * 0.5f;
        }
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && (this.field_70173_aa >= JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MAX_TICK_LIFE[this.stateID][0] || this.prevX == this.field_70165_t && this.prevY == this.field_70163_u && this.prevZ == this.field_70161_v)) {
            ++this.prevCount;
            if (this.prevCount >= JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MAX_TICK_LIFE[this.stateID][1]) {
                this.func_70106_y();
            }
        } else {
            this.prevCount = 0;
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
        if (this.inGround) {
            int var19 = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (block == this.inTile && var19 == this.inData) {
                ++this.ticksInGround;
                int t = this.ticksInGround / 10 * 10;
                if (this.ticksInGround == (t == 0 ? 10 : t)) {
                    this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.majin_bodypart", 0.4f, 1.0f);
                }
                if (this.ticksInGround >= 1) {
                    this.field_70165_t -= this.field_70159_w;
                    this.field_70163_u -= this.field_70181_x;
                    this.field_70161_v -= this.field_70179_y;
                    this.field_70159_w = 0.0;
                    this.field_70181_x = 0.0;
                    this.field_70179_y = 0.0;
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
            Entity target;
            Entity var10;
            int var9;
            double var7;
            List var6;
            double HITBOX;
            Entity var5;
            int t;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (!this.field_70170_p.field_72995_K && this.ticksInAir >= 2000 && this.target == null && this.getMode() != 2) {
                this.func_70106_y();
            }
            if (this.ticksInAir == ((t = this.ticksInAir / 10 * 10) == 0 ? 10 : t)) {
                this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.majin_bodypart", 0.4f, 1.0f);
            }
            if (var4 != null) {
                var3 = Vec3.func_72443_a((double)var4.field_72307_f.field_72450_a, (double)var4.field_72307_f.field_72448_b, (double)var4.field_72307_f.field_72449_c);
            }
            if (!this.field_70170_p.field_72995_K) {
                var5 = null;
                HITBOX = 2.0;
                var6 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(2.0, 2.0, 2.0));
                var7 = 0.0;
                for (var9 = 0; var9 < var6.size(); ++var9) {
                    double var14;
                    float var11;
                    AxisAlignedBB var12;
                    MovingObjectPosition var13;
                    var10 = (Entity)var6.get(var9);
                    if (!(var10 instanceof EntityLivingBase) || !var10.func_70067_L() || var10 == this.shootingEntity && this.ticksInAir < 5 || (var13 = (var12 = var10.field_70121_D.func_72314_b((double)(var11 = 0.0f), (double)var11, (double)var11)).func_72327_a(var17, var3)) == null || !((var14 = var17.func_72438_d(var13.field_72307_f)) < var7) && var7 != 0.0) continue;
                    var5 = var10;
                    var7 = var14;
                }
                if (var5 != null) {
                    var4 = new MovingObjectPosition(var5);
                }
            }
            if (!this.field_70170_p.field_72995_K && this.target == null) {
                var5 = null;
                HITBOX = 4.0;
                var6 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(4.0, 4.0, 4.0));
                var7 = 0.0;
                for (var9 = 0; var9 < var6.size(); ++var9) {
                    double dist2;
                    double distZ2;
                    double distY2;
                    double distZ;
                    double distY;
                    var10 = (Entity)var6.get(var9);
                    if (!(var10 instanceof EntityLivingBase) || var10 == null || !((EntityLivingBase)var10).func_70089_S() || var10 == this.shootingEntity || !var10.func_70067_L() || this.isEntityOnTheBlacklist(var10) || JRMCoreH.isFusionSpectator(var10) || JRMCoreH.isInCreativeMode(var10)) continue;
                    if (this.target == null) {
                        this.target = var10;
                        this.mode = 1;
                        this.func_70096_w().func_75692_b(20, (Object)1);
                        this.func_70096_w().func_75692_b(21, (Object)Float.valueOf(this.target.field_70130_N));
                        this.func_70096_w().func_75692_b(22, (Object)Float.valueOf(this.target.field_70131_O));
                        break;
                    }
                    double distX = this.target.field_70165_t - this.field_70165_t;
                    if (distX < 0.0) {
                        distX *= -1.0;
                    }
                    if ((distY = this.target.field_70163_u + (double)(this.target.field_70131_O / 2.0f) - this.field_70163_u) < 0.0) {
                        distY *= -1.0;
                    }
                    if ((distZ = this.target.field_70161_v - this.field_70161_v) < 0.0) {
                        distZ *= -1.0;
                    }
                    double dist = distX + distY + distZ;
                    double distX2 = var10.field_70165_t - this.field_70165_t;
                    if (distX2 < 0.0) {
                        distX2 *= -1.0;
                    }
                    if ((distY2 = var10.field_70163_u - this.field_70163_u) < 0.0) {
                        distY2 *= -1.0;
                    }
                    if ((distZ2 = var10.field_70161_v - this.field_70161_v) < 0.0) {
                        distZ2 *= -1.0;
                    }
                    if (!(dist < (dist2 = distX2 + distY2 + distZ2))) break;
                    this.target = var10;
                    this.mode = 1;
                    this.func_70096_w().func_75692_b(20, (Object)1);
                    this.func_70096_w().func_75692_b(21, (Object)Float.valueOf(this.target.field_70130_N));
                    this.func_70096_w().func_75692_b(22, (Object)Float.valueOf(this.target.field_70131_O));
                    break;
                }
            }
            if (var4 != null && this.target == null && this.mode <= 0 && !this.field_70170_p.func_147439_a(var4.field_72311_b, var4.field_72312_c, var4.field_72309_d).func_149739_a().equals("tile.bedrock")) {
                this.xTile = var4.field_72311_b;
                this.yTile = var4.field_72312_c;
                this.zTile = var4.field_72309_d;
                this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
                this.inGround = true;
                if (this.inTile.func_149688_o() != Material.field_151579_a) {
                    this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this);
                }
            }
            Object object = this.getMode() == 1 ? this.target : (target = this.getMode() == 2 ? this.shootingEntity : null);
            if (target != null) {
                double distX = target.field_70165_t - this.field_70165_t;
                double distY = target.field_70163_u + (double)(target.field_70131_O / 2.0f) - this.field_70163_u;
                double distZ = target.field_70161_v - this.field_70161_v;
                double div = 20.0;
                double MIN_SPEED = 0.1;
                double HIT_DISTANCE = 0.3;
                this.field_70159_w = distX / 20.0;
                if (JGMathHelper.doubleSmallerThan(this.field_70159_w, MIN_SPEED)) {
                    this.field_70159_w = this.field_70159_w < 0.0 ? -MIN_SPEED : MIN_SPEED;
                }
                this.field_70181_x = distY / 20.0;
                if (JGMathHelper.doubleSmallerThan(this.field_70181_x, MIN_SPEED)) {
                    this.field_70181_x = this.field_70181_x < 0.0 ? -MIN_SPEED : MIN_SPEED;
                }
                this.field_70179_y = distZ / 20.0;
                if (JGMathHelper.doubleSmallerThan(this.field_70179_y, MIN_SPEED)) {
                    this.field_70179_y = this.field_70179_y < 0.0 ? -MIN_SPEED : MIN_SPEED;
                }
                this.field_70165_t += this.field_70159_w;
                this.field_70163_u += this.field_70181_x;
                this.field_70161_v += this.field_70179_y;
                if (!this.field_70170_p.field_72995_K) {
                    if (this.shootingEntity != null && this.shootingEntity.func_70089_S()) {
                        if (JGMathHelper.doubleSmallerThan(distX, HIT_DISTANCE) && JGMathHelper.doubleSmallerThan(distY, HIT_DISTANCE) && JGMathHelper.doubleSmallerThan(distZ, HIT_DISTANCE)) {
                            JGPlayerMP jgPlayer;
                            if (this.getMode() == 2) {
                                jgPlayer = new JGPlayerMP((EntityPlayer)this.shootingEntity);
                                jgPlayer.connectBaseNBT();
                                jgPlayer.setAbsorption(this.absorptionData);
                                this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.majin_absorption2", 0.4f, 1.0f);
                                this.func_70106_y();
                            } else if (this.target != null && ((EntityLivingBase)this.target).func_70089_S()) {
                                jgPlayer = new JGPlayerMP((EntityPlayer)this.shootingEntity);
                                jgPlayer.connectBaseNBT();
                                if (JRMCoreH.isPowerTypeKi(jgPlayer.getPowerType()) && JRMCoreH.isRaceMajin(jgPlayer.getRace())) {
                                    float powerTarget;
                                    float attackTarget = 1.0f;
                                    float healthTarget = 1.0f;
                                    float kiPowerTarget = 1.0f;
                                    if (this.target instanceof EntityPlayer) {
                                        JGPlayerMP jgPlayerTarget = new JGPlayerMP((EntityPlayer)this.target);
                                        jgPlayerTarget.connectBaseNBT();
                                        float release = (float)jgPlayerTarget.getRelease() / 100.0f;
                                        int strength = jgPlayerTarget.getAttribute(0);
                                        attackTarget = JRMCoreH.stat(0, (EntityPlayer)this.target, 0, strength, 0.0f);
                                        attackTarget = (int)(attackTarget * release);
                                        healthTarget = jgPlayerTarget.getHealth();
                                        kiPowerTarget = jgPlayerTarget.getEnergyPower();
                                        kiPowerTarget = (int)(kiPowerTarget * release);
                                    } else {
                                        if (((EntityLivingBase)this.target).func_110148_a(SharedMonsterAttributes.field_111264_e) != null) {
                                            attackTarget = (float)((EntityLivingBase)this.target).func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                                            kiPowerTarget = attackTarget / 2.0f;
                                        }
                                        healthTarget = ((EntityLivingBase)this.target).func_110143_aJ();
                                    }
                                    float targetHealthOG = healthTarget;
                                    attackTarget = (int)(attackTarget * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_TARGET_POWER_MULTI[this.stateID][0]);
                                    healthTarget = (int)(healthTarget * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_TARGET_POWER_MULTI[this.stateID][1]);
                                    kiPowerTarget = (int)(kiPowerTarget * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_TARGET_POWER_MULTI[this.stateID][2]);
                                    if (attackTarget < 1.0f) {
                                        attackTarget = 1.0f;
                                    }
                                    if (healthTarget < 1.0f) {
                                        healthTarget = 1.0f;
                                    }
                                    if (kiPowerTarget < 1.0f) {
                                        kiPowerTarget = 1.0f;
                                    }
                                    if ((powerTarget = attackTarget + kiPowerTarget + healthTarget) <= this.userPower) {
                                        String[] prevAbs = jgPlayer.getAbsorption().split(",");
                                        this.absorptionData = "";
                                        boolean isTargetPlayer = this.target instanceof EntityPlayer;
                                        for (int i = 0; i < 3; ++i) {
                                            String data;
                                            if (prevAbs.length <= i) {
                                                data = i == 2 ? "0+0" : "0";
                                            } else {
                                                data = prevAbs[i];
                                                if (i == 0) {
                                                    int gain;
                                                    int prevValue = 0;
                                                    if (prevAbs[i].length() > 0) {
                                                        prevValue = Integer.parseInt(prevAbs[i]);
                                                    }
                                                    if ((gain = (int)(powerTarget / this.userPower * JGConfigRaces.CONFIG_MAJIN_ABSORPTON_GAIN_MULTI)) < JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MIN_GAIN) {
                                                        gain = JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MIN_GAIN;
                                                    }
                                                    prevValue = JGConfigRaces.CONFIG_MAJIN_ADD_GAIN_ENABLED ? (prevValue += gain) : gain;
                                                    if (prevValue > JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MAX_LEVEL) {
                                                        prevValue = JGConfigRaces.CONFIG_MAJIN_ABSORPTON_MAX_LEVEL;
                                                    }
                                                    data = "" + prevValue;
                                                } else if (isTargetPlayer) {
                                                    if (i == 1) {
                                                        JGPlayerMP jgPlayerTarget = new JGPlayerMP((EntityPlayer)this.target);
                                                        jgPlayerTarget.connectBaseNBT();
                                                        data = "" + jgPlayerTarget.getRace();
                                                    } else if (i == 2) {
                                                        data = "";
                                                        ItemStack[] stack_vanities = new ItemStack[8];
                                                        for (int j = 0; j < 8; ++j) {
                                                            stack_vanities[j] = ExtendedPlayer.get((EntityPlayer)((EntityPlayer)this.target)).inventory.func_70301_a(3 + j);
                                                            if (stack_vanities[j] == null || !(stack_vanities[j].func_77973_b() instanceof ItemVanity)) continue;
                                                            data = data + (data.length() > 0 ? "-" : "") + Item.func_150891_b((Item)stack_vanities[j].func_77973_b()) + "+" + ((ItemVanity)stack_vanities[j].func_77973_b()).getColor(stack_vanities[j]);
                                                        }
                                                        if (data.length() == 0) {
                                                            data = "0+0";
                                                        }
                                                    }
                                                } else {
                                                    data = i == 2 ? "0+0" : "0";
                                                }
                                            }
                                            this.absorptionData = this.absorptionData + data + (i + 1 < 3 ? "," : "");
                                        }
                                        this.mode = (byte)2;
                                        this.func_70096_w().func_75692_b(20, (Object)2);
                                        ((EntityLivingBase)this.target).func_70606_j(1.0f);
                                        DamageSource damagesource = Ds.causeEntityMajinAbsorptionDamage(this, this.shootingEntity);
                                        if (this.target.func_70097_a(damagesource, targetHealthOG)) {
                                            this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.majin_absorption", 0.3f, 1.0f);
                                        } else {
                                            this.func_70106_y();
                                        }
                                    } else {
                                        this.func_70106_y();
                                    }
                                } else {
                                    this.func_70106_y();
                                }
                            } else {
                                this.func_70106_y();
                            }
                        }
                    } else {
                        this.func_70106_y();
                    }
                }
            } else {
                this.field_70165_t += this.field_70159_w;
                this.field_70163_u += this.field_70181_x;
                this.field_70161_v += this.field_70179_y;
            }
            this.mode = (byte)this.func_70096_w().func_75679_c(20);
            this.targetW = this.func_70096_w().func_111145_d(21);
            this.targetH = this.func_70096_w().func_111145_d(22);
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
        par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
        par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
        par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.inTile));
        par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
        par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        this.xTile = par1NBTTagCompound.func_74765_d("xTile");
        this.yTile = par1NBTTagCompound.func_74765_d("yTile");
        this.zTile = par1NBTTagCompound.func_74765_d("zTile");
        this.inTile = Block.func_149729_e((int)(par1NBTTagCompound.func_74771_c("inTile") & 0xFF));
        this.inData = par1NBTTagCompound.func_74771_c("inData") & 0xFF;
        this.inGround = par1NBTTagCompound.func_74771_c("inGround") == 1;
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

    public boolean func_70075_an() {
        return false;
    }

    public boolean func_82704_a(Entity var1) {
        return false;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.shootingEntity == null ? 0 : this.shootingEntity.func_145782_y());
        data.writeInt(this.target == null ? 0 : this.target.func_145782_y());
        data.writeFloat(this.speed);
        data.writeFloat(this.size);
        data.writeInt(this.bodyColor);
        data.writeByte((int)this.skillLevel);
        data.writeFloat(this.userPower);
        data.writeByte((int)this.mode);
        data.writeFloat(this.targetW);
        data.writeFloat(this.targetH);
    }

    public void readSpawnData(ByteBuf data) {
        int first = data.readInt();
        this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
        int second = data.readInt();
        this.target = second == 0 ? this.target : this.field_70170_p.func_73045_a(second);
        this.speed = data.readFloat();
        this.size = data.readFloat();
        this.bodyColor = data.readInt();
        this.skillLevel = data.readByte();
        this.userPower = data.readFloat();
        this.mode = data.readByte();
        this.targetW = data.readFloat();
        this.targetH = data.readFloat();
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

    public float rad(float angle) {
        return angle * (float)Math.PI / 180.0f;
    }

    public NBTTagCompound nbt(EntityPlayer p, String s) {
        NBTTagCompound nbt;
        if (s.contains("pres")) {
            if (!p.getEntityData().func_74764_b("PlayerPersisted")) {
                nbt = new NBTTagCompound();
                p.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
            } else {
                nbt = p.getEntityData().func_74775_l("PlayerPersisted");
            }
        } else {
            nbt = p.getEntityData();
        }
        return nbt;
    }

    public boolean addInstance(Class cl, boolean b) {
        absorptionListResults.put(cl, b);
        return b;
    }

    public boolean instanceOf(Entity entity) {
        if (JGConfigRaces.CONFIG_MAJIN_ENTITY_BLACKLIST.size() == 0) {
            return false;
        }
        Class<?> entityClass = entity.getClass();
        if (absorptionListResults != null && absorptionListResults.size() > 0 && absorptionListResults.containsKey(entityClass)) {
            return absorptionListResults.get(entityClass);
        }
        String name = entityClass.toString();
        try {
            if (JGConfigRaces.CONFIG_MAJIN_ENTITY_BLACKLIST.containsKey(name)) {
                return this.addInstance(entityClass, true);
            }
            for (String key : JGConfigRaces.CONFIG_MAJIN_ENTITY_BLACKLIST.keySet()) {
                Class<?> cl;
                if (!JRMCoreH.DBC() && key.startsWith("JinRyuu.DragonBC") || !JRMCoreH.NC() && key.startsWith("JinRyuu.NarutoC") || !(cl = Class.forName(key)).isAssignableFrom(entityClass)) continue;
                return this.addInstance(entityClass, true);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return this.addInstance(entityClass, false);
    }

    public boolean isEntityOnTheBlacklist(Entity entity) {
        return this.instanceOf(entity);
    }
}

