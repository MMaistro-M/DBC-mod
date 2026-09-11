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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Entitys;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.DBC.DBCPacketHandlerServer;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCInstantTransmission;
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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityInstantTransmission
extends Entity
implements IThrowableEntity,
IEntityAdditionalSpawnData,
IEntitySelector,
IProjectile {
    public static final float SOUND = 0.25f;
    public Entity shootingEntity;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData = 0;
    private boolean inGround = false;
    private int ticksInGround;
    private int ticksInAir = 0;
    private byte speed;
    private float size = 10.0f;
    private boolean teleported = false;
    private byte mode = 0;
    private byte skillLevel = 1;
    private byte shortTPMode = 0;
    private byte surroundMode = 0;

    public int getShrink() {
        return this.func_70096_w().func_75679_c(20);
    }

    public byte getSpe() {
        return this.speed;
    }

    public int getAirTicks() {
        return this.ticksInAir;
    }

    public void setAirTicks(int i) {
        this.ticksInAir = i;
    }

    public EntityInstantTransmission(World world) {
        super(world);
        this.func_70105_a(this.size, this.size);
    }

    public EntityInstantTransmission(EntityLivingBase userEntity) {
        super(userEntity.field_70170_p);
        this.speed = (byte)20;
        this.size = 0.5f;
        this.shootingEntity = userEntity;
        this.field_70155_l = 10.0;
        this.func_70105_a(this.size, this.size);
        double d10 = 2.0;
        double d8 = userEntity.field_70130_N + 1.0f;
        double d9 = userEntity.field_70131_O + 0.5f + this.size * 0.5f;
        Vec3 vec3 = userEntity.func_70040_Z();
        double x = userEntity.field_70165_t + vec3.field_72450_a * d8 - vec3.field_72450_a * 2.0;
        double y = userEntity.field_70163_u + vec3.field_72448_b * d8 + (double)(userEntity.field_70131_O * 0.55f) - vec3.field_72448_b * 2.0;
        double z = userEntity.field_70161_v + vec3.field_72449_c * d8 - vec3.field_72449_c * 2.0;
        this.field_70129_M = this.size * 0.5f;
        this.func_70012_b(x, y, z, userEntity.func_70079_am(), userEntity.field_70125_A);
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)this.speed * 0.05f, 1.0f);
    }

    public void setData(int mode, int skillLevel, int shortTPMode, int surroundMode) {
        this.mode = (byte)mode;
        this.skillLevel = (byte)skillLevel;
        this.shortTPMode = (byte)shortTPMode;
        this.surroundMode = (byte)surroundMode;
    }

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
        if (!this.field_70170_p.field_72995_K && !JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_ENABLED[0]) {
            this.func_70106_y();
            return;
        }
        this.onLand();
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (this.field_70163_u >= 250.0) {
            this.field_70181_x = 0.0;
        } else {
            this.size = 0.5f;
            this.func_70105_a(this.size, this.size);
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
                    // empty if block
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
            int t;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (!this.field_70170_p.field_72995_K && this.ticksInAir >= 2000) {
                this.func_70106_y();
            }
            if (this.ticksInAir == ((t = this.ticksInAir / 10 * 10) == 0 ? 10 : t)) {
                this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:" + JRMCoreH.techSnds(0, 2, 0), 1.0f, 1.0f);
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
                    if (!(var10 instanceof EntityLivingBase) || !var10.func_70067_L() || var10 == this.shootingEntity && this.ticksInAir < 5 || (var13 = (var12 = var10.field_70121_D.func_72314_b((double)(var11 = 0.0f), (double)var11, (double)var11)).func_72327_a(var17, var3)) == null || !((var14 = var17.func_72438_d(var13.field_72307_f)) < var7) && var7 != 0.0) continue;
                    var5 = var10;
                    var7 = var14;
                }
                if (var5 != null) {
                    var4 = new MovingObjectPosition(var5);
                }
            }
            if (var4 != null) {
                if (!this.field_70170_p.field_72995_K) {
                    // empty if block
                }
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
        data.writeByte((int)this.speed);
        data.writeFloat(this.size);
    }

    public void readSpawnData(ByteBuf data) {
        int first = data.readInt();
        this.shootingEntity = first == 0 ? this.shootingEntity : this.field_70170_p.func_73045_a(first);
        this.speed = data.readByte();
        this.size = data.readFloat();
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

    public void onLand() {
        if (this.shootingEntity != null && !this.teleported && !this.field_70170_p.field_72995_K) {
            for (int j = 0; j < JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SHORT_MAX_RANGE[this.skillLevel - 1]; ++j) {
                Block block;
                this.field_70165_t += this.field_70159_w;
                this.field_70163_u += this.field_70181_x;
                this.field_70161_v += this.field_70179_y;
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (!JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SHORT_GO_THROUGH_BLOCKS_ENABLED && (block = this.shootingEntity.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v)).func_149688_o() != Material.field_151579_a) {
                    block.func_149719_a((IBlockAccess)this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
                    AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
                    if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v))) {
                        String message = "Instant Transmission Failed! A block was in the way";
                        ((EntityPlayer)this.shootingEntity).func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                        this.func_70106_y();
                        return;
                    }
                }
                double r = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SHORT_TARGET_FINDER_RANGE;
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - r), (double)(this.field_70163_u - r), (double)(this.field_70161_v - r), (double)(this.field_70165_t + r), (double)(this.field_70163_u + r), (double)(this.field_70161_v + r));
                List entityList = this.field_70170_p.func_72839_b((Entity)this, aabb);
                for (int i = 0; i < entityList.size(); ++i) {
                    NBTTagCompound nbt2;
                    String fusionMembers2;
                    String[] fusionParticipants2;
                    EntityPlayer fusedPlayerPartner;
                    boolean isController;
                    String[] fusionParticipants;
                    String fusionMembers;
                    float value;
                    Block block2;
                    int k;
                    double z;
                    double y;
                    double x;
                    Entity targetEntity = (Entity)entityList.get(i);
                    if (targetEntity == null || !(targetEntity instanceof EntityLivingBase) || !targetEntity.func_70089_S() || targetEntity.equals((Object)this.shootingEntity) || JRMCoreH.isFusionSpectator(targetEntity)) continue;
                    if (targetEntity instanceof EntityPlayer && JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_DIMENSIONAL_RELEASE_SENSE_REQUIRED_ENABLED[0]) {
                        boolean targetCanBeSensed;
                        JGPlayerMP targetMP = new JGPlayerMP((EntityPlayer)targetEntity);
                        NBTTagCompound nbt22 = this.nbt((EntityPlayer)targetEntity, "pres");
                        targetMP.setNBT(nbt22);
                        byte targetRelease = targetMP.getRelease();
                        boolean bl = targetCanBeSensed = targetRelease > 0;
                        if (!targetCanBeSensed) {
                            String message = "Instant Transmission Failed! Target can not be sensed at 0% Release Level.";
                            ((EntityPlayer)this.shootingEntity).func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                            this.func_70106_y();
                            return;
                        }
                    }
                    EntityPlayer pl = (EntityPlayer)this.shootingEntity;
                    int groupID = JRMCoreH.getInt(pl, "JRMCGID");
                    ArrayList<EntityPlayer> teleportedEntities = new ArrayList<EntityPlayer>();
                    teleportedEntities.add(pl);
                    if (this.surroundMode != -1) {
                        int surroundPlayerLimit = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_PLAYER_LIMIT_SKILL_LEVEL[0][this.skillLevel - 1];
                        double r2 = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_TARGET_FINDER_RANGE;
                        double r2Y = 1.0;
                        AxisAlignedBB aabb2 = AxisAlignedBB.func_72330_a((double)(pl.field_70165_t - r2), (double)(pl.field_70163_u - 1.0), (double)(pl.field_70161_v - r2), (double)(pl.field_70165_t + r2), (double)(pl.field_70163_u + 1.0), (double)(pl.field_70161_v + r2));
                        List entityList2 = this.field_70170_p.func_72839_b((Entity)this, aabb2);
                        for (int k2 = 0; k2 < entityList2.size(); ++k2) {
                            boolean groupOnly;
                            Entity nearbyEntity = (Entity)entityList2.get(k2);
                            if (nearbyEntity == null || !(nearbyEntity instanceof EntityPlayer) || ((EntityPlayer)nearbyEntity).equals((Object)targetEntity) || ((EntityPlayer)nearbyEntity).equals((Object)pl) || !nearbyEntity.func_70089_S()) continue;
                            if (surroundPlayerLimit != -1 && teleportedEntities.size() - 1 > surroundPlayerLimit) break;
                            boolean bl = groupOnly = this.surroundMode == 0;
                            if (groupOnly) {
                                int egid = JRMCoreH.getInt((EntityPlayer)nearbyEntity, "JRMCGID");
                                if (egid != groupID || groupID == 0) continue;
                                teleportedEntities.add((EntityPlayer)nearbyEntity);
                                continue;
                            }
                            teleportedEntities.add((EntityPlayer)nearbyEntity);
                        }
                    }
                    JGPlayerMP jgPlayer = new JGPlayerMP(pl);
                    NBTTagCompound nbt = this.nbt(pl, "pres");
                    jgPlayer.setNBT(nbt);
                    String ste = jgPlayer.getStatusEffects();
                    boolean divine = JRMCoreH.StusEfcts(17, ste);
                    boolean creativeMode = JRMCoreH.isInCreativeMode((Entity)pl);
                    if (!creativeMode) {
                        int[] playerAttributes = jgPlayer.getAttributes();
                        byte race = jgPlayer.getRace();
                        byte classID = jgPlayer.getClassID();
                        byte powerType = jgPlayer.getPowerType();
                        int curEnergy = jgPlayer.getEnergy();
                        int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(pl, (int)powerType));
                        double FLAT_COST = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COST[0][0][this.skillLevel - 1];
                        double PERCENTAGE_COST = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COST[0][1][this.skillLevel - 1];
                        double costMulti = PERCENTAGE_COST / 100.0;
                        double energyCost = (double)maxEnergy * costMulti + FLAT_COST;
                        if (teleportedEntities.size() > 1) {
                            double costPerPlayerFlat = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[0][this.skillLevel - 1];
                            double costPerPlayerPerc = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_SURROUND_COST_PER_PLAYER[1][this.skillLevel - 1];
                            double costMultiPerPlayer = costPerPlayerPerc / 100.0;
                            double energyCostDim = (double)maxEnergy * costMultiPerPlayer + costPerPlayerFlat;
                            energyCost += energyCostDim * (double)(teleportedEntities.size() - 1);
                        }
                        if ((double)curEnergy < energyCost) {
                            String message = "Instant Transmission Failed! Not Enough Ki: " + (int)energyCost;
                            pl.func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                            this.func_70106_y();
                            return;
                        }
                        int remainingEnergy = curEnergy - (int)energyCost;
                        JRMCoreH.setInt(remainingEnergy, pl, "jrmcEnrgy");
                    }
                    pl.field_70170_p.func_72956_a((Entity)pl, divine ? "jinryuudragonbc:DBC4.blacktp" : "jinryuudragonbc:DBC5.instant_transmission", 0.25f, pl.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    if (this.shortTPMode == -1) {
                        x = targetEntity.field_70165_t;
                        y = targetEntity.field_70163_u;
                        z = targetEntity.field_70161_v;
                        Block block3 = this.shootingEntity.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
                        if (block3.func_149688_o() != Material.field_151579_a) {
                            y += 1.0;
                        }
                    } else if (this.shortTPMode == 0) {
                        x = targetEntity.field_70165_t - this.field_70159_w * 2.0;
                        y = targetEntity.field_70163_u - this.field_70181_x * 2.0;
                        z = targetEntity.field_70161_v - this.field_70179_y * 2.0;
                        block3: for (k = 0; k < 3; ++k) {
                            boolean found = false;
                            block2 = this.shootingEntity.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
                            for (int l = 0; l < 3; ++l) {
                                block2 = this.shootingEntity.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u + l, (int)this.field_70161_v);
                                if (block2.func_149688_o() == Material.field_151579_a) continue;
                                if (k == 2) {
                                    y += 1.0;
                                    continue block3;
                                }
                                value = k == 0 ? 1.0f : 0.0f;
                                x = targetEntity.field_70165_t - this.field_70159_w * (double)value;
                                y = targetEntity.field_70163_u - this.field_70181_x * (double)value;
                                z = targetEntity.field_70161_v - this.field_70179_y * (double)value;
                                continue block3;
                            }
                        }
                    } else {
                        x = targetEntity.field_70165_t + this.field_70159_w * 2.0;
                        y = targetEntity.field_70163_u + this.field_70181_x * 2.0;
                        z = targetEntity.field_70161_v + this.field_70179_y * 2.0;
                        block5: for (k = 0; k < 3; ++k) {
                            boolean found = false;
                            block2 = this.shootingEntity.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
                            for (int l = 0; l < 3; ++l) {
                                block2 = this.shootingEntity.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u + l, (int)this.field_70161_v);
                                if (block2.func_149688_o() == Material.field_151579_a) continue;
                                if (k == 2) {
                                    y += 1.0;
                                    continue block5;
                                }
                                value = k == 0 ? 1.0f : 0.0f;
                                x = targetEntity.field_70165_t + this.field_70159_w * (double)value;
                                y = targetEntity.field_70163_u + this.field_70181_x * (double)value;
                                z = targetEntity.field_70161_v + this.field_70179_y * (double)value;
                                continue block5;
                            }
                        }
                    }
                    JRMCoreH.playerUsedInstantTransmission(pl);
                    for (int k3 = teleportedEntities.size() - 1; k3 >= 0; --k3) {
                        EntityPlayer entity = (EntityPlayer)teleportedEntities.get(k3);
                        double x2 = entity.field_70165_t - pl.field_70165_t;
                        double y2 = 0.0;
                        double z2 = entity.field_70161_v - pl.field_70161_v;
                        ((EntityPlayerMP)entity).func_70080_a(x + x2, y + y2 + 1.0, z + z2, entity.field_70177_z - (float)(this.shortTPMode == 1 ? 180 : 0), entity.field_70125_A * (float)(this.shortTPMode == 1 ? -1 : 1));
                        ((EntityPlayerMP)entity).field_71135_a.func_147364_a(x + x2, y + y2 + 1.0, z + z2, entity.field_70177_z, entity.field_70125_A);
                        entity.func_71023_q(1);
                    }
                    if (JRMCoreH.isFused((Entity)pl) && (fusionMembers = nbt.func_74779_i("jrmcFuzion")).length() > 0 && !fusionMembers.equals(" ") && (fusionParticipants = fusionMembers.split(",")).length == 3 && (isController = fusionParticipants[0].equalsIgnoreCase(pl.func_70005_c_())) && (fusedPlayerPartner = pl.field_70170_p.func_72924_a(fusionParticipants[1])) != null && (fusionParticipants2 = (fusionMembers2 = (nbt2 = this.nbt(fusedPlayerPartner, "pres")).func_74779_i("jrmcFuzion")).split(",")).length == 3) {
                        ((EntityPlayerMP)fusedPlayerPartner).field_71135_a.func_147364_a(x, y + 1.5, z, fusedPlayerPartner.field_70177_z, fusedPlayerPartner.field_70125_A);
                        fusedPlayerPartner.func_71023_q(1);
                    }
                    JRMCoreH.playerUsedInstantTransmission(pl);
                    this.teleported = true;
                    this.field_70159_w = 0.0;
                    this.field_70181_x = 0.0;
                    this.field_70179_y = 0.0;
                    pl.field_70170_p.func_72956_a((Entity)pl, divine ? "jinryuudragonbc:DBC4.blacktp" : "jinryuudragonbc:DBC5.instant_transmission", 0.25f, pl.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    if (nbt.func_74764_b("jrmcInstantTransmissionTimers")) {
                        String instantTransmissionTimers = nbt.func_74779_i("jrmcInstantTransmissionTimers");
                        String[] values = instantTransmissionTimers.split(";");
                        int tpShort = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COOLDOWN[0][this.skillLevel - 1];
                        int tpLong = Integer.parseInt(values[1]);
                        JRMCoreH.setString(tpShort + ";" + tpLong, pl, "jrmcInstantTransmissionTimers");
                    } else {
                        String instantTransmissionTimers = JGConfigDBCInstantTransmission.CONFIG_INSTANT_TRANSMISSION_COOLDOWN[0][this.skillLevel - 1] + ";0";
                        JRMCoreH.setString(instantTransmissionTimers, pl, "jrmcInstantTransmissionTimers");
                    }
                    this.func_70106_y();
                    return;
                }
            }
            if (!this.field_70128_L) {
                String message = "Instant Transmission didn't find any targets!";
                ((EntityPlayerMP)this.shootingEntity).func_145747_a(new ChatComponentText(message).func_150255_a(DBCPacketHandlerServer.styleRed));
                this.func_70106_y();
                return;
            }
        }
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
}

