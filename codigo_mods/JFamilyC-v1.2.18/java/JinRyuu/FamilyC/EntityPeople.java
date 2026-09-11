/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.IAnimals
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.FamilyC;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityPeople
extends EntityTameable
implements IAnimals {
    public int field_70881_d;
    private int breeding;

    public EntityPeople(World par1World) {
        super(par1World);
    }

    protected void func_70629_bd() {
        if (this.func_70874_b() != 0) {
            this.field_70881_d = 0;
        }
        super.func_70629_bd();
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.func_70874_b() != 0) {
            this.field_70881_d = 0;
        }
        if (this.field_70881_d > 0) {
            --this.field_70881_d;
            String s = "heart";
            if (this.field_70881_d % 10 == 0) {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02;
                this.field_70170_p.func_72869_a(s, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2);
            }
        } else {
            this.breeding = 0;
        }
    }

    protected void func_70785_a(Entity par1Entity, float par2) {
        if (par1Entity instanceof EntityPlayer) {
            EntityPlayer entityplayer;
            if (par2 < 3.0f) {
                double d0 = par1Entity.field_70165_t - this.field_70165_t;
                double d1 = par1Entity.field_70161_v - this.field_70161_v;
                this.field_70177_z = (float)(Math.atan2(d1, d0) * 180.0 / Math.PI) - 90.0f;
                this.field_70787_b = true;
            }
            if ((entityplayer = (EntityPlayer)par1Entity).func_71045_bC() == null || !this.func_70877_b(entityplayer.func_71045_bC())) {
                this.field_70789_a = null;
            }
        } else if (par1Entity instanceof EntityPeople) {
            EntityPeople EntityPeople2 = (EntityPeople)par1Entity;
            if (this.func_70874_b() > 0 && EntityPeople2.func_70874_b() < 0) {
                if ((double)par2 < 2.5) {
                    this.field_70787_b = true;
                }
            } else if (this.field_70881_d > 0 && EntityPeople2.field_70881_d > 0) {
                if (EntityPeople2.field_70789_a == null) {
                    EntityPeople2.field_70789_a = this;
                }
                if (EntityPeople2.field_70789_a == this && (double)par2 < 3.5) {
                    ++EntityPeople2.field_70881_d;
                    ++this.field_70881_d;
                    ++this.breeding;
                    if (this.breeding % 4 == 0) {
                        this.field_70170_p.func_72869_a("heart", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, 0.0, 0.0, 0.0);
                    }
                    if (this.breeding == 60) {
                        this.procreate((EntityPeople)par1Entity);
                    }
                } else {
                    this.breeding = 0;
                }
            } else {
                this.breeding = 0;
                this.field_70789_a = null;
            }
        }
    }

    private void procreate(EntityPeople par1EntityPeople) {
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        IAttributeInstance attributeinstance;
        if (this.func_85032_ar()) {
            return false;
        }
        this.field_70788_c = 60;
        if (!this.func_70650_aV() && (attributeinstance = this.func_110148_a(SharedMonsterAttributes.field_111263_d)).func_111127_a(field_110179_h) == null) {
            attributeinstance.func_111121_a(field_110181_i);
        }
        this.field_70789_a = null;
        this.field_70881_d = 0;
        return super.func_70097_a(par1DamageSource, par2);
    }

    public float func_70783_a(int par1, int par2, int par3) {
        return this.field_70170_p.func_147439_a(par1, par2 - 1, par3) == Blocks.field_150349_c ? 10.0f : this.field_70170_p.func_72801_o(par1, par2, par3) - 0.5f;
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("InLove", this.field_70881_d);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.field_70881_d = par1NBTTagCompound.func_74762_e("InLove");
    }

    protected Entity func_70782_k() {
        block5: {
            float f;
            block6: {
                block4: {
                    if (this.field_70788_c > 0) {
                        return null;
                    }
                    f = 8.0f;
                    if (this.field_70881_d <= 0) break block4;
                    List list = this.field_70170_p.func_72872_a(((Object)((Object)this)).getClass(), this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));
                    for (int i = 0; i < list.size(); ++i) {
                        EntityPeople EntityPeople2 = (EntityPeople)((Object)list.get(i));
                        if (EntityPeople2 == this || EntityPeople2.field_70881_d <= 0) continue;
                        return EntityPeople2;
                    }
                    break block5;
                }
                if (this.func_70874_b() != 0) break block6;
                List list = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));
                for (int i = 0; i < list.size(); ++i) {
                    EntityPlayer entityplayer = (EntityPlayer)list.get(i);
                    if (entityplayer.func_71045_bC() == null || !this.func_70877_b(entityplayer.func_71045_bC())) continue;
                    return entityplayer;
                }
                break block5;
            }
            if (this.func_70874_b() <= 0) break block5;
            List list = this.field_70170_p.func_72872_a(((Object)((Object)this)).getClass(), this.field_70121_D.func_72314_b((double)f, (double)f, (double)f));
            for (int i = 0; i < list.size(); ++i) {
                EntityPeople EntityPeople3 = (EntityPeople)((Object)list.get(i));
                if (EntityPeople3 == this || EntityPeople3.func_70874_b() >= 0) continue;
                return EntityPeople3;
            }
        }
        return null;
    }

    public boolean func_70601_bi() {
        int k;
        int j;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        return this.field_70170_p.func_147439_a(i, (j = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b)) - 1, k = MathHelper.func_76128_c((double)this.field_70161_v)) == Blocks.field_150349_c && this.field_70170_p.func_72883_k(i, j, k) > 8 && super.func_70601_bi();
    }

    public int func_70627_aG() {
        return 120;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected int func_70693_a(EntityPlayer par1EntityPlayer) {
        return 1 + this.field_70170_p.field_73012_v.nextInt(3);
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
        if (itemstack != null && this.func_70877_b(itemstack) && this.func_70874_b() == 0 && this.field_70881_d <= 0) {
            if (!par1EntityPlayer.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    par1EntityPlayer.field_71071_by.func_70299_a(par1EntityPlayer.field_71071_by.field_70461_c, (ItemStack)null);
                }
            }
            this.func_110196_bT();
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    public void func_110196_bT() {
        this.field_70881_d = 600;
        this.field_70789_a = null;
        this.field_70170_p.func_72960_a((Entity)this, (byte)18);
    }

    public boolean func_70880_s() {
        return this.field_70881_d > 0;
    }

    public void func_70875_t() {
        this.field_70881_d = 0;
    }

    public boolean canMateWith(EntityPeople par1EntityPeople) {
        return par1EntityPeople == this ? false : (((Object)((Object)par1EntityPeople)).getClass() != ((Object)((Object)this)).getClass() ? false : this.func_70880_s() && par1EntityPeople.func_70880_s());
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 18) {
            for (int i = 0; i < 7; ++i) {
                double d0 = this.field_70146_Z.nextGaussian() * 0.02;
                double d1 = this.field_70146_Z.nextGaussian() * 0.02;
                double d2 = this.field_70146_Z.nextGaussian() * 0.02;
                this.field_70170_p.func_72869_a("heart", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2);
            }
        } else {
            super.func_70103_a(par1);
        }
    }
}

