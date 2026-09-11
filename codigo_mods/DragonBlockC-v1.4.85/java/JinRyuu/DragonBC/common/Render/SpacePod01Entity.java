/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.p.DBC.DBCPdri;
import JinRyuu.JRMCore.p.PD;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class SpacePod01Entity
extends Entity {
    private boolean field_70279_a = true;
    private double speedMultiplier = 0.07;
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    @SideOnly(value=Side.CLIENT)
    private double velocityX;
    @SideOnly(value=Side.CLIENT)
    private double velocityY;
    @SideOnly(value=Side.CLIENT)
    private double velocityZ;
    public String texture;
    private float dS;
    private float T = 0.05f;
    private int emptyCounter = 0;

    public boolean shouldRenderInPass(int pass) {
        return pass == 0;
    }

    public SpacePod01Entity(World par1World) {
        super(par1World);
        this.field_70156_m = true;
        this.func_70105_a(1.5f, 1.5f);
        this.field_70129_M = this.field_70131_O / 2.0f;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(17, (Object)new Integer(0));
        this.field_70180_af.func_75682_a(18, (Object)new Integer(1));
        this.field_70180_af.func_75682_a(19, (Object)new Float(0.0f));
    }

    public AxisAlignedBB func_70114_g(Entity par1Entity) {
        return par1Entity.field_70121_D;
    }

    public AxisAlignedBB func_70046_E() {
        return this.field_70121_D;
    }

    public boolean func_70104_M() {
        return true;
    }

    public SpacePod01Entity(World par1World, double par2, double par4, double par6) {
        this(par1World);
        this.func_70107_b(par2, par4 + (double)this.field_70129_M, par6);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.field_70169_q = par2;
        this.field_70167_r = par4;
        this.field_70166_s = par6;
    }

    public double func_70042_X() {
        return (double)this.field_70131_O * 0.0 - (double)0.3f;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
            boolean flag;
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0f);
            this.func_70018_K();
            boolean bl = flag = par1DamageSource.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.func_76346_g()).field_71075_bZ.field_75098_d;
            if (flag || this.getDamageTaken() > 40.0f) {
                if (this.field_70153_n != null) {
                    this.field_70153_n.func_70078_a((Entity)this);
                }
                this.func_145778_a(ItemsDBC.SpacePod01Item, 1, 0.0f);
                if (!flag) {
                    // empty if block
                }
                this.func_70106_y();
            }
            return true;
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70057_ab() {
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0f);
    }

    public boolean func_70067_L() {
        return !this.field_70128_L;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
        if (this.field_70279_a) {
            this.boatPosRotationIncrements = par9 + 5;
        } else {
            double d3 = par1 - this.field_70165_t;
            double d4 = par3 - this.field_70163_u;
            double d5 = par5 - this.field_70161_v;
            double d6 = d3 * d3 + d4 * d4 + d5 * d5;
            if (d6 <= 1.0) {
                return;
            }
            this.boatPosRotationIncrements = 3;
        }
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.field_70159_w = this.velocityX;
        this.field_70181_x = this.velocityY;
        this.field_70179_y = this.velocityZ;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70016_h(double par1, double par3, double par5) {
        this.velocityX = this.field_70159_w = par1;
        this.velocityY = this.field_70181_x = par3;
        this.velocityZ = this.field_70179_y = par5;
    }

    public void func_70071_h_() {
        double d5;
        double d4;
        if (!this.field_70170_p.field_72995_K && this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer) {
            boolean isKOd;
            NBTTagCompound tag = JRMCoreH.nbt((Entity)((EntityPlayer)this.field_70153_n), "pres");
            boolean bl = isKOd = tag.func_74762_e("jrmcHar4va") > 0;
            if (isKOd) {
                this.field_70153_n.func_70078_a(null);
            }
        }
        super.func_70071_h_();
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if (this.getDamageTaken() > 0.0f) {
            this.setDamageTaken(this.getDamageTaken() - 1.0f);
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        int b0 = 5;
        double d0 = 0.0;
        for (int i = 0; i < b0; ++i) {
            double d1 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(i + 0) / (double)b0 - 0.125;
            double d2 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (double)(i + 1) / (double)b0 - 0.125;
            AxisAlignedBB axisAlignedBB = AxisAlignedBB.func_72330_a((double)this.field_70121_D.field_72340_a, (double)d1, (double)this.field_70121_D.field_72339_c, (double)this.field_70121_D.field_72336_d, (double)d2, (double)this.field_70121_D.field_72334_f);
        }
        double d3 = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        if (d3 > 0.26249999999999996) {
            d4 = Math.cos((double)this.field_70177_z * Math.PI / 180.0);
            d5 = Math.sin((double)this.field_70177_z * Math.PI / 180.0);
            int j = 0;
            while ((double)j < 1.0 + d3 * 60.0) {
                double d6 = this.field_70146_Z.nextFloat() * 2.0f - 1.0f;
                double d = (double)(this.field_70146_Z.nextInt(2) * 2 - 1) * 0.7;
                ++j;
            }
        }
        if (this.field_70170_p.field_72995_K && this.field_70279_a) {
            if (this.boatPosRotationIncrements > 0) {
                d4 = this.field_70165_t + (this.boatX - this.field_70165_t) / (double)this.boatPosRotationIncrements;
                d5 = this.field_70163_u + (this.boatY - this.field_70163_u) / (double)this.boatPosRotationIncrements;
                double d11 = this.field_70161_v + (this.boatZ - this.field_70161_v) / (double)this.boatPosRotationIncrements;
                double d10 = MathHelper.func_76138_g((double)(this.boatYaw - (double)this.field_70177_z));
                this.field_70177_z = (float)((double)this.field_70177_z + d10 / (double)this.boatPosRotationIncrements);
                this.field_70125_A = (float)((double)this.field_70125_A + (this.boatPitch - (double)this.field_70125_A) / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
                this.func_70107_b(d4, d5, d11);
                this.func_70101_b(this.field_70177_z, this.field_70125_A);
            } else {
                d4 = this.field_70165_t + this.field_70159_w;
                d5 = this.field_70163_u + this.field_70181_x;
                double d11 = this.field_70161_v + this.field_70179_y;
                this.func_70107_b(d4, d5, d11);
                this.field_70159_w *= (double)0.99f;
                this.field_70181_x *= (double)0.95f;
                this.field_70179_y *= (double)0.99f;
            }
        } else {
            double d12;
            double S = 0.0;
            if (this.field_70153_n != null) {
                if (this.field_70153_n instanceof EntityPlayer) {
                    NBTTagCompound tag = JRMCoreH.nbt((Entity)((EntityPlayer)this.field_70153_n), "");
                    if (tag.func_74762_e("DBCdriF") == 1) {
                        S = 0.55;
                        this.dS += this.T;
                        tag.func_74768_a("DBCdriF", 0);
                    } else if (tag.func_74762_e("DBCdriF") == 2) {
                        S = -0.55;
                        this.dS -= this.T;
                        tag.func_74768_a("DBCdriF", 0);
                    } else {
                        S = 0.0;
                        tag.func_74768_a("DBCdriF", 0);
                    }
                    if (tag.func_74762_e("DBCdriY") == 3) {
                        this.field_70181_x += 0.5;
                        if (this.field_70181_x > 0.5) {
                            this.field_70181_x = 0.5;
                        }
                        tag.func_74768_a("DBCdriY", 0);
                    } else if (tag.func_74762_e("DBCdriY") == 4) {
                        if (this.field_70170_p.func_147439_a((int)this.field_70165_t + 0, (int)this.field_70163_u - 2, (int)this.field_70161_v + 0).func_149688_o() == Material.field_151579_a) {
                            this.field_70181_x -= 0.5;
                            if (this.field_70181_x < -0.5) {
                                this.field_70181_x = -0.5;
                            }
                            tag.func_74768_a("DBCdriY", 0);
                        }
                    } else {
                        this.field_70181_x = 0.0;
                        tag.func_74768_a("DBCdriY", 0);
                    }
                    if (tag.func_74762_e("DBCdriS") == 5) {
                        this.field_70177_z -= 4.0f;
                        tag.func_74768_a("DBCdriS", 0);
                    } else if (tag.func_74762_e("DBCdriS") == 6) {
                        this.field_70177_z += 4.0f;
                        tag.func_74768_a("DBCdriS", 0);
                    } else {
                        tag.func_74768_a("DBCdriS", 0);
                    }
                }
                if (this.dS > 0.5f) {
                    this.dS = 0.5f;
                }
                if (this.dS < -0.5f) {
                    this.dS = -0.5f;
                }
                double e = Math.cos((double)this.field_70177_z * Math.PI / 180.0) * S;
                double r = Math.sin((double)this.field_70177_z * Math.PI / 180.0) * -S;
                this.field_70179_y = e;
                this.field_70159_w = r;
                this.field_70153_n.field_70143_R = 0.0f;
                this.field_70143_R = 0.0f;
                if (this.field_70154_o != null) {
                    this.field_70154_o.field_70143_R = 0.0f;
                }
                this.field_70153_n.field_70122_E = false;
                this.dri(10);
            } else {
                S = 0.0;
                this.dS = 0.0f;
            }
            this.field_70143_R = 0.0f;
            if (this.field_70153_n == null) {
                this.field_70159_w *= 0.3900000095367432;
                this.field_70179_y *= 0.3900000095367432;
                if (DBCConfig.spdc > 0) {
                    ++this.emptyCounter;
                    if (this.emptyCounter == 20 * DBCConfig.spdc) {
                        this.func_145778_a(ItemsDBC.SpacePod01Item, 1, 0.0f);
                        this.func_70106_y();
                    }
                }
            } else {
                this.emptyCounter = 0;
            }
            this.func_70091_d(this.field_70159_w * DBCConfig.cnfSpc, this.field_70181_x * DBCConfig.cnfSpc, this.field_70179_y * DBCConfig.cnfSpc);
            if (this.field_70123_F && d3 > 0.2) {
                if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
                    this.func_70106_y();
                    this.func_145778_a(ItemsDBC.SpacePod01Item, 1, 0.0f);
                }
            } else if (this.field_70153_n != null) {
                this.field_70159_w *= 0.3900000095367432;
                this.field_70181_x *= 0.349999988079071;
                this.field_70179_y *= 0.3900000095367432;
            }
            this.field_70125_A = 0.0f;
            d5 = this.field_70177_z;
            double d11 = this.field_70169_q - this.field_70165_t;
            double d10 = this.field_70166_s - this.field_70161_v;
            if (d11 * d11 + d10 * d10 > 0.001) {
                d5 = (float)(Math.atan2(d10, d11) * 180.0 / Math.PI);
            }
            if ((d12 = MathHelper.func_76138_g((double)(d5 - (double)this.field_70177_z))) > 20.0) {
                d12 = 20.0;
            }
            if (d12 < -20.0) {
                d12 = -20.0;
            }
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
            if (!this.field_70170_p.field_72995_K) {
                int l;
                List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b((double)0.2f, 0.0, (double)0.2f));
                if (list != null && !list.isEmpty()) {
                    for (l = 0; l < list.size(); ++l) {
                        Entity entity = (Entity)list.get(l);
                        if (entity == this.field_70153_n || !entity.func_70104_M() || !(entity instanceof SpacePod01Entity)) continue;
                        this.field_70159_w = 0.0;
                        this.field_70181_x = 0.0;
                        this.field_70179_y = 0.0;
                    }
                }
                for (l = 0; l < 4; ++l) {
                    int i1 = MathHelper.func_76128_c((double)(this.field_70165_t + ((double)(l % 2) - 0.5) * 0.8));
                    int j1 = MathHelper.func_76128_c((double)(this.field_70161_v + ((double)(l / 2) - 0.5) * 0.8));
                    for (int k1 = 0; k1 < 2; ++k1) {
                        int l1 = MathHelper.func_76128_c((double)this.field_70163_u) + k1;
                        Block i2 = this.field_70170_p.func_147439_a(i1, l1, j1);
                        if (i2 == Blocks.field_150433_aE) {
                            this.field_70170_p.func_147468_f(i1, l1, j1);
                            continue;
                        }
                        if (i2 != Blocks.field_150392_bi) continue;
                        this.field_70170_p.func_147443_d(i1, l1, j1, 0, 0);
                    }
                }
                if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
                    this.field_70153_n = null;
                }
            }
        }
    }

    public void func_70043_V() {
        if (this.field_70153_n != null) {
            double d0 = Math.cos((double)this.field_70177_z * Math.PI / 180.0) * 0.4;
            double d1 = Math.sin((double)this.field_70177_z * Math.PI / 180.0) * 0.4;
            this.field_70153_n.func_70107_b(this.field_70165_t + d0, this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W(), this.field_70161_v + d1);
        }
    }

    public void dri(int a) {
        float s = this.field_70153_n != null ? this.dS : 0.0f;
        PD.sendTo(new DBCPdri((int)(s * 10.0f)), (EntityPlayerMP)this.field_70153_n);
    }

    protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
    }

    protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public boolean func_130002_c(EntityPlayer par1EntityPlayer) {
        return this.interact(par1EntityPlayer);
    }

    public boolean interact(EntityPlayer par1EntityPlayer) {
        if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != par1EntityPlayer) {
            return true;
        }
        if (!this.field_70170_p.field_72995_K) {
            par1EntityPlayer.func_70078_a((Entity)this);
        }
        return true;
    }

    public void setDamageTaken(float par1) {
        this.field_70180_af.func_75692_b(19, (Object)Float.valueOf(par1));
    }

    public float getDamageTaken() {
        return this.field_70180_af.func_111145_d(19);
    }

    public void setTimeSinceHit(int par1) {
        this.field_70180_af.func_75692_b(17, (Object)par1);
    }

    public int getTimeSinceHit() {
        return this.field_70180_af.func_75679_c(17);
    }

    public void setForwardDirection(int par1) {
        this.field_70180_af.func_75692_b(18, (Object)par1);
    }

    public int getForwardDirection() {
        return this.field_70180_af.func_75679_c(18);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70270_d(boolean par1) {
        this.field_70279_a = par1;
    }
}

