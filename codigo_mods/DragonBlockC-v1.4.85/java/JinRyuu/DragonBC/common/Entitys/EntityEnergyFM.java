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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Entitys;

import JinRyuu.JRMCore.JRMCoreH;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityEnergyFM
extends Entity
implements IThrowableEntity,
IEntityAdditionalSpawnData,
IEntitySelector,
IProjectile {
    public Entity shootingEntity;
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    private int inData = 0;
    private boolean inGround = false;
    private int ticksInGround;
    private int ticksInAir = 0;
    private float Expl = 4.0f;
    private String ExplSound = "jinryuudragonbc:DBC.expl";
    private String AirSound = "jinryuudragonbc:DBC.hafire";
    private float strtX;
    private float strtY;
    private float strtZ;
    private float trgtX = 0.0f;
    private float trgtY = 0.0f;
    private float trgtZ = 0.0f;
    private byte speed;
    private boolean shrink = false;
    private byte relFired = (byte)100;
    private float size = 10.0f;
    private int cb = 50;
    private boolean kiClashed;
    private List kiClashedList = new ArrayList();

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

    public byte getSpe() {
        return this.speed;
    }

    public int getAirTicks() {
        return this.ticksInAir;
    }

    public void setAirTicks(int i) {
        this.ticksInAir = i;
    }

    public EntityEnergyFM(World par1World) {
        super(par1World);
        this.func_70105_a(this.size, this.size);
    }

    public EntityEnergyFM(EntityLivingBase par2EntityLivingBase) {
        super(par2EntityLivingBase.field_70170_p);
        this.speed = (byte)20;
        int sbh = (int)(this.field_70163_u > 65.0 ? this.field_70163_u - 65.0 : 1.0) * 4;
        this.size = 0.5f + (float)(sbh / 5 * 100) * 0.02f / 8.0f;
        if (this.size > 10.0f) {
            this.size = 10.0f;
        }
        this.shootingEntity = par2EntityLivingBase;
        this.field_70155_l = 10.0;
        this.func_70105_a(this.size, this.size);
        double d8 = par2EntityLivingBase.field_70130_N + 1.0f;
        double d9 = par2EntityLivingBase.field_70131_O + 0.5f + this.size * 0.5f;
        Vec3 vec3 = par2EntityLivingBase.func_70676_i(1.0f);
        double x = par2EntityLivingBase.field_70165_t + vec3.field_72450_a * d8;
        double y = par2EntityLivingBase.field_70163_u + vec3.field_72448_b * d8 + (double)(par2EntityLivingBase.field_70131_O * 0.55f);
        double z = par2EntityLivingBase.field_70161_v + vec3.field_72449_c * d8;
        this.func_70012_b(x, y, z, 0.0f, 0.0f);
        this.field_70129_M = this.size * 0.5f;
        this.field_70159_w = 0.0;
        this.field_70179_y = 0.0;
        this.field_70181_x = 1.0;
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, (float)this.speed * 0.05f, 1.0f);
        this.strtX = (float)x;
        this.strtY = (float)y;
        this.strtZ = (float)z;
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
        if (!this.field_70170_p.field_72995_K && this.shootingEntity == null) {
            this.func_70106_y();
        }
        if (this.field_70163_u >= 250.0) {
            this.field_70181_x = 0.0;
        } else {
            int sbh = (int)(this.field_70163_u > 80.0 ? this.field_70163_u - 80.0 : 1.0) * 4;
            this.size = 0.5f + (float)(sbh / 5 * 100) * 0.02f / 8.0f;
            this.func_70105_a(this.size, this.size);
        }
        this.field_70159_w = 0.0;
        this.field_70179_y = 0.0;
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
                        this.field_70170_p.func_72956_a((Entity)this, this.AirSound, 1.0f, 1.0f);
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
            float var11;
            ++this.ticksInAir;
            Vec3 var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition var4 = this.field_70170_p.func_147447_a(var17, var3, false, true, false);
            var17 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            var3 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (!this.field_70170_p.field_72995_K && this.ticksInAir >= 2000) {
                this.func_70106_y();
            }
            for (int var6 = 1; var6 < 3; ++var6) {
            }
            int t = this.ticksInAir / 10 * 10;
            if (this.ticksInAir == (t == 0 ? 10 : t)) {
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
                    this.field_70170_p.func_72956_a((Entity)this, this.ExplSound, 1.0f, 1.0f);
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
            float var22 = 1.0f;
            var11 = 0.0f;
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

    public boolean func_70075_an() {
        return false;
    }

    public boolean func_82704_a(Entity var1) {
        return false;
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.shootingEntity == null ? 0 : this.shootingEntity.func_145782_y());
        data.writeByte((int)this.speed);
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
        this.speed = data.readByte();
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
}

